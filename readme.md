## modules
* eureka-server:服务注册、服务发现
* admin-server:服务监控
* service-parent:微服务中心parent
    * account-service:账户中心示例
    * activiti-service:工作流引擎服务
* tools:工具
    * excel-tools:excel基本导入导出
    * fs-tools:文件基本上传下载，支持fastdfs与本地
    * redis-tools:redis基本操作
    * skywalking-tools:skywalking logback配置
* zuul-gateway:服务网关
* oauth2-server:oauth2验证服务器，使用jwt作为token
* config-server:配置推送服务器，使用git作为配置仓库，通过spring cloud bus推送
* zipkin-server:服务链路跟踪查询，通过spring cloud bus收集并记录在mysql或者ElasticSearch中

## 结构

![结构](/docs/structure.png)

## contact
* <nju_cy@163.com>