```
# sc-plat
sc-plat
|------ doc
        ---sql 文件
|------ plat-dependencies 公共依赖jar 
|------common
       |------ common-framework 公共模块
       |------ flyway-core-starter 版本升级管理模块
       |------ plat-spring-boot-starter-mybatis 公共mybatis模块
       |------ plat-spring-boot-starter-redis 公共redis模块
       |------ plat-spring-boot-starter-swagger 公共swagger模块
       |------ plat-spring-boot-starter-web 公共web模块
|------management-web 后台管理微服务
|------plat-gateway 网关中心
|------management-web 后台管理微服务
|------plat-product
       |----------- product-server-api 商品api
       |----------- product-server 商品微服务
|------plat-trade
       |----------- product-trade-api 交易api
       |----------- product-server-server 交易微服务
|------plat-security-server 认证中心
```

**## \**技术栈\****



| 后端技术 | 版本号           

| -------------------- | -------------------- |               

| SpringBoot|2.2.5.RELEASE           

| SpringCloud|Hoxton.SR3

| SpringCloud Alibaba| 2.2.1.RELEASE

| MyBatis Plus|3.3.2

| Lombok |1.16.14

| Knife4j | 2.0.2

| nacos 服务注册，配置中心

| springcloud-gateway 网关中心

| springcloud sentinel 