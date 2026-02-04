# API 接口文档

本文档详细介绍了项目中所有可用的 REST API 接口。

## 目录

- [内容管理接口](#内容管理接口)
- [创作者管理接口](#创作者管理接口)

---

## 内容管理接口

基础路径: `/contents`

### 1. 获取所有视频内容

- **请求方法**: `GET`
- **请求路径**: `/contents`
- **描述**: 获取所有视频内容信息
- **参数**: 无
- **返回值**: `List<Content>` - 包含所有视频内容信息的列表
- **示例响应**:
```json
[
  {
    "video_id": "vid001",
    "video_type": "tutorial",
    "title": "Spring Boot 入门教程",
    "description": "Spring Boot 基础知识讲解",
    "create_time": "2023-01-01 10:00:00",
    "user_id": "user001",
    "nickname": "编程小助手",
    "avatar": "https://example.com/avatar.jpg",
    "video_url": "https://example.com/video.mp4",
    "video_cover_url": "https://example.com/cover.jpg",
    "liked_count": 10,
    "disliked_count": 2,
    "video_play_count": 100
  }
]
```

### 2. 根据ID获取单个视频内容

- **请求方法**: `GET`
- **请求路径**: `/contents/{id}`
- **描述**: 根据ID获取单个视频内容信息
- **路径参数**:
  - `id` (String): 要查询的视频内容ID
- **返回值**: `Content` - 对应ID的视频内容对象
- **示例响应**:
```json
{
  "video_id": "vid001",
  "video_type": "tutorial",
  "title": "Spring Boot 入门教程",
  "description": "Spring Boot 基础知识讲解",
  "create_time": "2023-01-01 10:00:00",
  "user_id": "user001",
  "nickname": "编程小助手",
  "avatar": "https://example.com/avatar.jpg",
  "video_url": "https://example.com/video.mp4",
  "video_cover_url": "https://example.com/cover.jpg",
  "liked_count": 10,
  "disliked_count": 2,
  "video_play_count": 100
}
```

### 3. 分页获取视频内容

- **请求方法**: `GET`
- **请求路径**: `/contents/page`
- **描述**: 分页获取视频内容，支持懒加载功能
- **查询参数**:
  - `currentPage` (Integer, 可选): 当前页码，默认为1
  - `pageSize` (Integer, 可选): 每页大小，默认为10
- **返回值**: `PageResult<Content>` - 分页结果，包含当前页数据和分页信息
- **示例响应**:
```json
{
  "items": [...],
  "total": 100,
  "pageSize": 10,
  "currentPage": 1,
  "totalPages": 10,
  "hasNextPage": true
}
```

### 4. 根据标题模糊查询视频内容

- **请求方法**: `GET`
- **请求路径**: `/contents/search`
- **描述**: 根据标题关键词进行模糊查询
- **查询参数**:
  - `title` (String): 标题关键词
- **返回值**: `List<Content>` - 匹配标题关键词的视频内容列表

### 5. 根据标题分页查询视频内容

- **请求方法**: `GET`
- **请求路径**: `/contents/search/page`
- **描述**: 根据标题关键词进行模糊查询并分页返回结果
- **查询参数**:
  - `title` (String): 标题关键词
  - `currentPage` (Integer, 可选): 当前页码，默认为1
  - `pageSize` (Integer, 可选): 每页大小，默认为10
- **返回值**: `PageResult<Content>` - 分页结果，包含当前页数据和分页信息

### 6. 点赞视频

- **请求方法**: `POST`
- **请求路径**: `/contents/{id}/like`
- **描述**: 为指定ID的视频增加点赞数量
- **路径参数**:
  - `id` (String): 要点赞的视频ID
- **返回值**: `Content_Result<Void>` - 操作结果
- **示例响应**:
```json
{
  "code": 200,
  "message": "点赞成功",
  "data": null
}
```

### 7. 不喜欢视频

- **请求方法**: `POST`
- **请求路径**: `/contents/{id}/dislike`
- **描述**: 为指定ID的视频增加不喜欢数量
- **路径参数**:
  - `id` (String): 要不喜欢的视频ID
- **返回值**: `Content_Result<Void>` - 操作结果
- **示例响应**:
```json
{
  "code": 200,
  "message": "不喜欢成功",
  "data": null
}
```

### 8. 取消点赞视频

- **请求方法**: `DELETE`
- **请求路径**: `/contents/{id}/like`
- **描述**: 为指定ID的视频减少点赞数量
- **路径参数**:
  - `id` (String): 要取消点赞的视频ID
- **返回值**: `Content_Result<Void>` - 操作结果
- **示例响应**:
```json
{
  "code": 200,
  "message": "取消点赞成功",
  "data": null
}
```

### 9. 取消不喜欢视频

- **请求方法**: `DELETE`
- **请求路径**: `/contents/{id}/dislike`
- **描述**: 为指定ID的视频减少不喜欢数量
- **路径参数**:
  - `id` (String): 要取消不喜欢的视频ID
- **返回值**: `Content_Result<Void>` - 操作结果
- **示例响应**:
```json
{
  "code": 200,
  "message": "取消不喜欢成功",
  "data": null
}
```

### 10. 获取视频播放地址

- **请求方法**: `GET`
- **请求路径**: `/contents/{id}/video-url`
- **描述**: 为前端提供指定视频的播放地址
- **路径参数**:
  - `id` (String): 视频ID
- **返回值**: `String` - 视频播放地址字符串

### 11. 获取视频封面地址

- **请求方法**: `GET`
- **请求路径**: `/contents/{id}/cover-url`
- **描述**: 为前端提供指定视频的封面地址，用于显示封面图片
- **路径参数**:
  - `id` (String): 视频ID
- **返回值**: `String` - 视频封面地址字符串

### 12. 获取完整视频信息

- **请求方法**: `GET`
- **请求路径**: `/contents/{id}/detail`
- **描述**: 返回包含封面和视频链接的完整视频信息
- **路径参数**:
  - `id` (String): 视频ID
- **返回值**: `Content` - 完整的视频内容对象

---

## 创作者管理接口

基础路径: `/creators`

### 1. 获取所有创作者信息

- **请求方法**: `GET`
- **请求路径**: `/creators`
- **描述**: 获取所有创作者的列表数据
- **参数**: 无
- **返回值**: `List<Creator>` - 包含所有创作者信息的列表
- **示例响应**:
```json
[
  {
    "id": "creator001",
    "username": "编程小助手",
    "email": "helper@example.com",
    "avatar": "https://example.com/avatar.jpg",
    "bio": "专注于编程教学",
    "created_at": "2023-01-01"
  }
]
```

### 2. 根据ID获取单个创作者信息

- **请求方法**: `GET`
- **请求路径**: `/creators/{id}`
- **描述**: 根据ID获取单个创作者信息
- **路径参数**:
  - `id` (String): 要查询的创作者ID
- **返回值**: `Creator` - 对应ID的创作者对象
- **示例响应**:
```json
{
  "id": "creator001",
  "username": "编程小助手",
  "email": "helper@example.com",
  "avatar": "https://example.com/avatar.jpg",
  "bio": "专注于编程教学",
  "created_at": "2023-01-01"
}
```

---

## 响应格式说明

### Content 对象字段说明
- `video_id` (String): 视频唯一标识符
- `video_type` (String): 视频类型
- `title` (String): 视频标题
- `description` (String): 视频描述
- `create_time` (String): 创建时间
- `user_id` (String): 用户ID
- `nickname` (String): 用户昵称
- `avatar` (String): 用户头像地址
- `video_url` (String): 视频播放地址
- `video_cover_url` (String): 视频封面地址
- `liked_count` (Long): 点赞数量
- `disliked_count` (Long): 不喜欢数量
- `video_play_count` (Long): 播放次数

### Creator 对象字段说明
- `id` (String): 创作者唯一标识符
- `username` (String): 用户名
- `email` (String): 邮箱地址
- `avatar` (String): 头像地址
- `bio` (String): 个人简介
- `created_at` (String): 创建时间

### User 对象字段说明
- `id` (Long): 用户ID，主键自增长
- `username` (String): 用户名，唯一且非空
- `password` (String): 密码，MD5加密
- `email` (String): 邮箱地址，唯一且非空
- `avatar` (String): 头像URL
- `nickname` (String): 用户昵称
- `signature` (String): 个性签名
- `role` (Integer): 角色权限（0：普通用户，1：管理员，2：超级管理员）
- `created_at` (LocalDateTime): 创建时间
- `updated_at` (LocalDateTime): 更新时间

### Favorite 对象字段说明
- `id` (Long): 收藏夹ID，主键自增长
- `user_id` (Long): 用户ID，关联user表的外键
- `name` (String): 收藏夹名称，非空
- `description` (String): 收藏夹描述
- `created_at` (LocalDateTime): 创建时间
- `updated_at` (LocalDateTime): 更新时间

### SearchRecord 对象字段说明
- `id` (Long): 搜索记录ID，主键自增长
- `keyword` (String): 搜索关键词，非空
- `count` (Integer): 搜索次数，默认值为1
- `created_at` (LocalDateTime): 创建时间
- `updated_at` (LocalDateTime): 更新时间

### Content_Result 对象字段说明
- `code` (Integer): 状态码，200表示成功，-1表示错误
- `message` (String): 响应消息，描述操作结果
- `data` (泛型): 响应数据，可能为null

---

## 用户管理接口

基础路径: `/api/user`

**注意**: 所有API接口返回的 `Result` 类型均指 `User_Result` 类型。

### 1. 用户登录

- **请求方法**: `POST`
- **请求路径**: `/api/user/account/login`
- **描述**: 用户登录验证
- **请求体**: `{"username": "...", "password": "..."}`
- **返回值**: `User_Result<User>` - 登录成功返回用户信息和令牌
- **示例响应**:
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "id": 1,
    "username": "example_user",
    "token": "jwt_token_here"
  }
}
```

### 2. 用户注册

- **请求方法**: `POST`
- **请求路径**: `/api/user/account/register`
- **描述**: 用户注册新账户
- **请求体**: `{"username": "...", "password": "...", "email": "..."}`
- **返回值**: `User_Result<User>` - 注册成功返回新创建的用户信息
- **示例响应**:
```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "id": 1,
    "username": "example_user"
  }
}
```

### 3. 用户登出

- **请求方法**: `GET`
- **请求路径**: `/api/user/account/logout`
- **描述**: 用户登出，清除登录状态
- **参数**: 无
- **返回值**: `User_Result<Void>` - 登出结果
- **示例响应**:
```json
{
  "code": 200,
  "message": "登出成功"
}
```

### 4. 获取用户信息

- **请求方法**: `GET`
- **请求路径**: `/api/user/personal/info`
- **描述**: 获取当前登录用户的基本信息
- **参数**: 无
- **返回值**: `User_Result<User>` - 用户信息
- **示例响应**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "username": "example_user",
    "email": "user@example.com",
    "avatar": "https://example.com/avatar.jpg",
    "nickname": "Example User",
    "signature": "Hello World!"
  }
}
```

### 5. 更新用户信息

- **请求方法**: `POST`
- **请求路径**: `/api/user/info/update`
- **描述**: 更新用户个人信息（昵称、签名）
- **请求体**: `{"nickname": "...", "signature": "..."}`
- **返回值**: `User_Result<Void>` - 更新结果
- **示例响应**:
```json
{
  "code": 200,
  "message": "更新成功"
}
```

### 6. 更新头像

- **请求方法**: `POST`
- **请求路径**: `/api/user/avatar/update`
- **描述**: 更新用户头像
- **参数**: `multipart/form-data` 文件上传
- **返回值**: `User_Result<String>` - 更新结果，包含新头像URL
- **示例响应**:
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "avatar": "https://example.com/new_avatar.jpg"
  }
}
```

### 7. 更新密码

- **请求方法**: `POST`
- **请求路径**: `/api/user/password/update`
- **描述**: 更新用户密码
- **请求体**: `{"oldPassword": "...", "newPassword": "..."}`
- **返回值**: `User_Result<Void>` - 更新结果
- **示例响应**:
```json
{
  "code": 200,
  "message": "密码更新成功"
}
```

### 8. 获取指定用户信息

- **请求方法**: `GET`
- **请求路径**: `/api/user/info/get-one`
- **描述**: 根据用户ID获取用户信息
- **查询参数**: `uid=1`
- **返回值**: `User_Result<User>` - 指定用户信息
- **示例响应**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "username": "example_user",
    "avatar": "https://example.com/avatar.jpg",
    "nickname": "Example User",
    "signature": "Hello World!"
  }
}
```

---

## 错误处理

当请求出现错误时，系统将返回适当的HTTP状态码：
- `400 Bad Request`: 请求参数错误
- `404 Not Found`: 请求的资源不存在
- `500 Internal Server Error`: 服务器内部错误