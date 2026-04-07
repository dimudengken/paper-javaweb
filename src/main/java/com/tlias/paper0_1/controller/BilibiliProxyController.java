package com.tlias.paper0_1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tlias.paper0_1.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/bilibili")
public class BilibiliProxyController {

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    private ContentMapper contentMapper;

    /**
     * 智能获取视频详情（兼容 AV 号和 BV 号）
     * 前端调用示例:
     * /api/bilibili/video-info?vid=BV1xx411c7mD
     * /api/bilibili/video-info?vid=av170001
     */
    @GetMapping(value = "/video-info", produces = "application/json;charset=UTF-8")
    public String getVideoInfo(@RequestParam("vid") String vid) {

        String bilibiliUrl = "https://api.bilibili.com/x/web-interface/view?";

        // 智能判断是 AV 号还是 BV 号
        if (vid != null && vid.toUpperCase().startsWith("BV")) {
            // 是 BV 号
            bilibiliUrl += "bvid=" + vid;
        } else if (vid != null && vid.toLowerCase().startsWith("av")) {
            // 是 AV 号，提取纯数字部分作为 aid
            String aid = vid.substring(2);
            bilibiliUrl += "aid=" + aid;
        } else {
            // 纯数字或其他情况，默认当做 aid 尝试
            bilibiliUrl += "aid=" + vid;
        }

        // 伪造请求头突破防爬
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(bilibiliUrl, HttpMethod.GET, entity, String.class);
            String rawJsonStr = response.getBody();

            // 1. 解析 B 站原生的庞大 JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(rawJsonStr);

            // 如果 B 站返回错误（如视频不存在）
            if (rootNode.path("code").asInt() != 0) {
                return "{\"code\": -1, \"message\": \"B站API返回错误\"}";
            }

            JsonNode dataNode = rootNode.path("data");

            // 2. 创建我们自己精简后的 JSON 对象
            ObjectNode resultNode = mapper.createObjectNode();
            resultNode.put("code", 200);
            resultNode.put("message", "解析成功");

            ObjectNode simplifiedData = mapper.createObjectNode();

            // 提取基础信息
            simplifiedData.put("bvid", dataNode.path("bvid").asText());
            simplifiedData.put("title", dataNode.path("title").asText());
            simplifiedData.put("desc", dataNode.path("desc").asText());
            simplifiedData.put("cover_url", dataNode.path("pic").asText());

            // 提取作者信息
            ObjectNode authorNode = mapper.createObjectNode();
            authorNode.put("uid", dataNode.path("owner").path("mid").asText());
            authorNode.put("nickname", dataNode.path("owner").path("name").asText());
            authorNode.put("avatar", dataNode.path("owner").path("face").asText());
            simplifiedData.set("author", authorNode);

            // 提取统计信息 (用于推荐算法的基础数据)
            ObjectNode statsNode = mapper.createObjectNode();
            statsNode.put("view_count", dataNode.path("stat").path("view").asLong());
            statsNode.put("danmaku_count", dataNode.path("stat").path("danmaku").asLong());
            statsNode.put("like_count", dataNode.path("stat").path("like").asLong());
            statsNode.put("coin_count", dataNode.path("stat").path("coin").asLong());
            statsNode.put("favorite_count", dataNode.path("stat").path("favorite").asLong());
            simplifiedData.set("stats", statsNode);

            // 提取核心分P列表 (抛弃乱七八糟的分辨率等信息)
            ArrayNode partsArray = mapper.createArrayNode();
            JsonNode originalPages = dataNode.path("pages");
            if (originalPages.isArray()) {
                for (JsonNode pageNode : originalPages) {
                    ObjectNode partInfo = mapper.createObjectNode();
                    partInfo.put("cid", pageNode.path("cid").asLong());
                    partInfo.put("page", pageNode.path("page").asInt());
                    partInfo.put("part_title", pageNode.path("part").asText());
                    partInfo.put("duration", pageNode.path("duration").asLong());
                    partsArray.add(partInfo);
                }
            }
            simplifiedData.set("parts_info", partsArray);

            resultNode.set("data", simplifiedData);

            // 3. 将精简后的 JSON 返回给前端
            String resultJson = resultNode.toString();
            
            // 4. 同时更新数据库中的统计数据
            try {
                updateVideoStats(dataNode, vid);
            } catch (Exception dbError) {
                dbError.printStackTrace();
                // 数据库更新失败不影响接口返回
            }
            
            return resultJson;

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"code\": -1, \"message\": \"后端解析数据失败\"}";
        }
    }

    /**
     * 更新视频统计数据到数据库
     * @param dataNode B 站 API 返回的数据节点
     * @param vid 视频 ID（AV 号或 BV 号）
     */
    private void updateVideoStats(JsonNode dataNode, String vid) {
        // 提取统计数据
        long viewCount = dataNode.path("stat").path("view").asLong();
        long likeCount = dataNode.path("stat").path("like").asLong();
        long coinCount = dataNode.path("stat").path("coin").asLong();
        long favoriteCount = dataNode.path("stat").path("favorite").asLong();
        long shareCount = dataNode.path("stat").path("share").asLong();
        
        // 获取视频的真实 ID（优先使用 aid，如果没有则用 bvid）
        String videoId = dataNode.path("aid").asText();
        if (videoId.isEmpty() || videoId.equals("0")) {
            videoId = dataNode.path("bvid").asText();
        }
        
        // 调用 Mapper 更新数据库
        contentMapper.updateVideoStats(videoId, likeCount, favoriteCount, shareCount, coinCount, viewCount);
    }
}