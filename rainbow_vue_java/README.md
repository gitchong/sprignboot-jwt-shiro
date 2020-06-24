# rainbow_vue_jwt

##  使用 vue + springboot + shiro + jwt 搭建一个基本的RPAC骨架
 [前端地址](https://github.com/makePromise/rainbow_vue)
 
### 技术交流：QQ--1649471814 
 
### 项目运行
#### 导入rainbow.sql，修改application.yml的mysql连接地址和redis的地址，项目的端口号为8088

### 项目思路
![Image text](https://github.com/makePromise/rainbow_vue/blob/master/src/assets/jwt.jpg)
#### 1.首先输入用户名与密码进行登入，如果成功返回一个加密的JWT密匙（同时后台把密匙存储在redis），失败的话直接返回401错误(帐号或密码不正确)。
#### 2.以后访问都要在请求头上带上这个JWT密匙，后台鉴权重写了Shiro的入口过滤器JWTFilter(BasicHttpAuthenticationFilter)，判断请求
#### Header里面是否包含Authorization字段，有就进行Shiro的Token登录认证授权(判断redis是否存在toekn密匙,不存在则失效，重新登陆)，没有就无访问

### 基本步骤
   
#### 1.封装系统统一返回响应类JsonResult  
#### 2.基础druid连接池监控
#### 3.集成mybatis-generator代码生成器
#### 4.集成mybatis内部的分页插件实现分页查询
#### 5.自定义系统异常类,全局拦截异常
#### 6.集成shiro和Jwt做鉴权机制
#### 7.集成redis缓存,保存token和权限(完成部分)
#### 8.利用aop做操作日志记录
#### 9.开发业务

特点：
- 使用authentication目录存储jwt和shiro的相关内容；
- 自定义request 日期处理函数，绑定了各种日期的转换方法。原理：通过注入RequestMappingHandlerAdapter获取ConfigurableWebBindingInitializer
然后获取GenericConversionService，然后添加自己的Converter.
- 使用JsonResult统一返回数据，JsonResult实现了HashMap
- 自定义了函数式的JedisExecutor,
    ```
  public interface JedisExecutor<T, R> {
      R excute(T t) throws RedisConnectException;
  }
  
  
   private <T> T excuteByJedis(JedisExecutor<Jedis, T> j) throws RedisConnectException {
           try (Jedis jedis = jedisPool.getResource()) {
               return j.excute(jedis);
           } catch (Exception e) {
               throw new RedisConnectException(e.getMessage());
           }
       }
  
    Long dbSize = this.excuteByJedis(
                  j -> {
                      Client client = j.getClient();
                      client.dbSize();
                      return client.getIntegerReply();
                  }
          );
  ```
- 集成了shiro,可以通过String token = (String) SecurityUtils.getSubject().getPrincipal();来获取token.token的销毁使用redis del就ok.
- 使用@RestControllerAdvice来自定义全局异常，
- 自定义了各种异常，但是没有  
- 自定义了Properties, 使用@ConfigurationProperties来引入。
- 定了各种工具类。IPUtils,SpringContextUtil,HttpContextUtil,等。

缺点：
- 使用jedis来做redis存储，jedis使用try() 来自动关闭jedisPool获取的Resource.
- 登录判断直接在LoginController中走的用户名密码校验。
- jwt只是用用户名和加密后的密码来计算jwt值。
- 自定义了各种异常，但是没有异常码。


