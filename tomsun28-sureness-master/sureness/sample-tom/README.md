## sample-tom  

sureness 30分钟例子项目  

- 基于`springboot,jpa...`，[数据源sql](src/main/resources/db)  
- 自定义数据源,使用从数据库加载账户信息,资源角色,过滤资源等信息,这样便于动态调整      
- 除了使用了默认的`jwt,basic auth`方式认证鉴权,新增自定义认证鉴权(自定义`subject subjectCreator processor...`)
- 推荐使用`postman`测试,测试样例为`sample-tom-postman.json`,导入`postman`即可  

特点：
- 使用ResponseEntity组装返回数据，
2、StandardCharsets.UTF_8