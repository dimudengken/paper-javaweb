# 视频功能特性说明文档

## 新增字段说明

### 1. Content 实体类新增字段
- `video_url` (String): 存储视频的实际播放地址
- `video_cover_url` (String): 存储视频封面图片地址

### 2. 数据库表结构要求
在 `video_data` 表中需要添加以下字段：
```sql
ALTER TABLE video_data ADD COLUMN video_url VARCHAR(500);
ALTER TABLE video_data ADD COLUMN video_cover_url VARCHAR(500);
```

## API 接口说明

### 1. 点赞/不喜欢功能接口
- `POST /contents/{id}/like` - 给指定视频点赞（liked_count +1）
- `POST /contents/{id}/dislike` - 给指定视频点踩（disliked_count +1）
- `DELETE /contents/{id}/like` - 取消点赞（liked_count -1）
- `DELETE /contents/{id}/dislike` - 取消点踩（disliked_count -1）

### 2. 视频资源获取接口
- `GET /contents/{id}/video-url` - 获取指定视频的播放地址
- `GET /contents/{id}/cover-url` - 获取指定视频的封面地址
- `GET /contents/{id}/detail` - 获取完整的视频详细信息（包含封面和视频链接）

## 前端集成指南

### 1. 显示视频封面
```html
<!-- 在HTML中显示视频封面 -->
<img src="/contents/{{videoId}}/cover-url" alt="视频封面" onclick="playVideo('{{videoId}}')" style="cursor: pointer;" />
```

### 2. 点击封面跳转到视频播放
```javascript
// JavaScript函数处理点击封面事件
function playVideo(videoId) {
    // 获取视频播放地址
    fetch('/contents/' + videoId + '/video-url')
        .then(response => response.text())
        .then(videoUrl => {
            // 跳转到视频播放页面或在模态框中播放
            window.open(videoUrl, '_blank'); // 或者在当前页面播放
        });
}
```

### 3. 点赞/不喜欢按钮功能
```javascript
// 点赞功能
function likeVideo(videoId) {
    fetch('/contents/' + videoId + '/like', { method: 'POST' })
        .then(response => response.json())
        .then(success => {
            if (success) {
                // 更新UI，例如增加点赞计数显示
                updateLikeCount(videoId);
            }
        });
}
```

## 后端实现说明

### 1. 数据库映射
- 在 `ContentMapper.xml` 中已配置新字段的映射关系
- 所有SELECT查询语句均已更新，包含新字段

### 2. 业务逻辑层
- `ContentService` 和 `ContentServiceImpl` 已添加获取视频和封面地址的方法
- 点赞/不喜欢功能已在Service层实现，包含异常处理

### 3. 控制器层
- `ContentController` 已添加相关API接口
- 包含完整的JavaDoc注释，便于维护

## 注意事项

1. 确保数据库表 `video_data` 包含 `video_url` 和 `video_cover_url` 字段
2. 前端需要处理异步请求的结果
3. 视频和封面URL应该使用绝对路径或完整的HTTP地址
4. 所有API接口返回布尔值表示操作是否成功
5. 如果视频不存在，获取视频或封面URL会返回null

## 扩展建议

1. 可以添加视频上传功能，自动设置 `video_url` 和 `video_cover_url`
2. 可以为视频封面添加缓存策略以提高性能
3. 可以实现视频封面的懒加载功能