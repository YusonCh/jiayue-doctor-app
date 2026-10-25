# 家约医生问诊服务 APP

一个面向家庭医生签约与在线问诊场景的全栈应用，包含患者端、医生端、管理后台与即时消息后端，并用 **TOPSIS** 多指标决策算法实现了医生的智能排序推荐。

> **注意**：数据库账号、JWT 密钥等敏感配置已从源码中移除，改为通过环境变量注入（见「配置」一节）。

## 功能概览

**移动端（患者 / 医生双角色）**
- 注册登录、角色区分，资料维护（姓名、科室、职称、所在医院）
- 按科室、医院、级别检索医生
- 实时图文问诊：文字、图片、语音、位置消息
- 健康档案：身高体重、血压、心率、既往病史记录
- 就诊记录、医生评价与评分、好友关系

**管理后台**
- 用户 / 医生 / 患者 / 科室数据维护

**算法**
- 基于科室、医院等级、评分、接诊量等多项指标，用 TOPSIS 计算贴近度，对医生候选集排序推荐（`im/src/main/java/com/qys/im/Topsis`）

## 技术栈

| 层 | 技术 |
|---|---|
| 移动端 | uni-app（Vue 2）、uView UI、uni_modules 组件 |
| 管理后台 | Vue 3、Vite、Element Plus、Pinia、Vue Router、Axios |
| 主后端 | Spring Boot 2.7、MyBatis、MySQL 8、Netty 4（WebSocket）、JWT（jjwt）、PageHelper |
| 辅助服务 | Spring Boot（问卷 / 用户服务） |
| 数据库 | MySQL 8，15 张表 |

## 目录结构

```
.
├── app/          uni-app 移动端（患者端 + 医生端）
│   ├── pages/        页面：登录注册、聊天、健康档案、个人中心
│   ├── components/   业务组件（聊天输入、科室/级别选择等）
│   └── uni_modules/  第三方 uni-app 插件（uView、图表等，MIT）
├── im/           Spring Boot 主后端
│   └── src/main/java/com/qys/im/
│       ├── controller/   REST 接口
│       ├── netty/        WebSocket 长连接（聊天）
│       ├── service/ dao/ pojo/  MyBatis 三层
│       ├── Topsis/       医生推荐算法
│       └── utils/        JWT 工具
├── system/       问卷 / 用户辅助服务（早期实验模块）
├── web/          Vue 3 + Element Plus 管理后台
└── db/
    └── im.sql    MySQL 结构与演示数据
```

## 配置

后端从环境变量读取敏感配置，未设置时使用本地开发默认值：

| 变量 | 说明 | 默认值 |
|---|---|---|
| `DB_HOST` | MySQL 地址 | `127.0.0.1` |
| `DB_PORT` | MySQL 端口 | `3306` |
| `DB_NAME` | 数据库名（`im` 模块用 `im`，`system` 模块用 `doctor`） | `im` / `doctor` |
| `DB_USERNAME` | 数据库账号 | `root` |
| `DB_PASSWORD` | 数据库密码 | 空 |
| `JWT_SECRET` | JWT 签名密钥 | `dev-only-secret-change-me` |

```powershell
$env:DB_PASSWORD = "your-password"
$env:JWT_SECRET  = "a-long-random-string"
```

> 生产/公网部署时请务必设置 `JWT_SECRET`，默认值仅供本地调试。

## 环境要求

| 依赖 | 版本 | 说明 |
|---|---|---|
| JDK | 8 / 11 / 17 | Spring Boot 2.7 均可运行 |
| Maven | 3.6+ | 可不装：项目自带 `mvnw` 包装器，首次运行自动下载 |
| Node.js | 16+ | 仅管理后台需要 |
| MySQL | 8.0 | 需支持 `utf8mb4` |
| HBuilderX | 最新版 | 仅移动端 `app` 需要（也可用 uni-app CLI） |

> Linux / macOS 下首次使用包装器需要赋予执行权限：`chmod +x im/mvnw system/mvnw`

## 快速开始

### 1. 初始化数据库

```bash
mysql -u root -p < db/im.sql
```

导入后会得到 `im` 库（15 张表）与演示数据。`system` 模块还需要单独的 `doctor` 库。

### 2. 启动主后端 `im`

```bash
cd im
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

- HTTP 接口：`http://127.0.0.1:8080`
- WebSocket 聊天：`ws://127.0.0.1:8804/ws`

### 3. 启动辅助服务 `system`（可选）

```bash
cd system
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

监听 `http://127.0.0.1:8088`。

### 4. 管理后台 `web`

```bash
cd web
npm install
npm run dev
```

### 5. 移动端 `app`

用 HBuilderX 打开 `app/` 目录运行到浏览器或手机模拟器。运行前先改 `app/common/settings.js` 里的后端地址：

```js
export default {
  url: 'http://127.0.0.1:8080',
}
```

Android 模拟器访问宿主机请改用 `10.0.2.2`。

## 演示账号

数据库演示数据中的账号密码统一为 `123456`（例如 `user1`、`user11`），仅用于本地演示。

## 已知限制

- 代码结构保留了早期版本的组织方式，暂未做工程化重构
- `system` 模块功能与 `im` 部分重叠，属于早期实验代码，可按需删除
- 图片上传使用本地磁盘路径，未接入对象存储
- 未包含单元测试与 CI

## License

[MIT](LICENSE)
