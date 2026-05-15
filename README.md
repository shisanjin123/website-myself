# Brix 个人博客管理系统

一个基于 Vue 3 + Spring Boot 3.x + MySQL 的三层架构个人博客管理系统。

## 技术栈

### 前端
- Vue 3 + Vite
- Vue Router 4
- Pinia 状态管理
- Element Plus UI 组件库
- MdEditor Markdown 编辑器
- Axios HTTP 客户端

### 后端
- Spring Boot 3.2
- MyBatis-Plus ORM
- MySQL 8.0
- JWT 认证
- Swagger/OpenAPI 3.0

### 部署
- Docker Compose

## 项目结构

```
website-myself/
├── frontend/          # 前端项目
├── backend/           # 后端项目
├── docker/            # Docker 配置
│   ├── nginx/
│   └── mysql/
├── docker-compose.yml
├── .gitignore
└── README.md
```

## 快速开始

### Docker 部署（推荐）

```bash
# 一键启动所有服务
docker compose up -d

# 查看运行状态
docker compose ps

# 查看日志
docker compose logs -f

# 停止服务
docker compose down

# 停止并清除数据（重新初始化数据库）
docker compose down -v
```

启动后访问：
- 博客首页: http://localhost
- 后台管理: http://localhost/admin
- API 文档: http://localhost:8080/swagger-ui.html
- 默认管理员账号: `admin` / `admin123`

### 本地开发

#### 1. 启动后端

```bash
cd backend
mvn spring-boot:run
```

后端将运行在 http://localhost:8080

#### 2. 启动前端

```bash
cd frontend
npm install
npm run dev
```

前端将运行在 http://localhost:3000

#### 3. 访问应用

- 博客首页: http://localhost:3000
- 后台管理: http://localhost:3000/admin
- 默认管理员账号: `admin` / `admin123`

## 功能特性

### 博客界面（公开）
- 首页：个人介绍、技术栈展示、精选项目
- 项目页：项目列表展示
- 文章页：文章列表、分类筛选
- 文章详情：Markdown 渲染

### 后台管理（需认证）
- 仪表盘：数据概览
- 个人介绍管理
- 技术栈管理（增删改查）
- 项目管理（增删改查）
- 文章管理（Markdown 编辑器）
- 分类管理（增删改查）

## 数据库设计

### 表结构
- `admin` - 管理员表
- `personal_intro` - 个人介绍表
- `tech_stack` - 技术栈表
- `project` - 项目表
- `article` - 文章表
- `category` - 分类表

## 配置说明

### 后端配置

`backend/src/main/resources/application.yml`:
- 数据库连接配置
- JWT 密钥配置
- Swagger 配置

### 前端配置

`frontend/vite.config.js`:
- 开发服务器端口
- API 代理配置

## 许可证

MIT License
