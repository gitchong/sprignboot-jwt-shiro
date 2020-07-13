## sample-bootstrap  

sureness 10分钟例子项目  

- 基于`springboot`
- 从默认的配置文件`sureness.yml`加载账户信息,资源角色,过滤资源等信息  
- 使用默认的`sureness-config`  
- 使用默认的`jwt,basic auth`方式认证鉴权
- 例子中包含`restful api,websocket`  
- 保护入口: `SurenessFilterExample`  
- 推荐使用`postman`测试,测试样例为`sample-bootstrap-postman.json`,导入`postman`即可  

特点：
- 对response进行了封装， 实现了CommonUtil.responseWrite(ResponseEntity
                                         .status(HttpStatus.FORBIDDEN).body(e5.getMessage()), servletResponse);
- 使用logback做日志配置
- SpringContextHolder工具类

                                      