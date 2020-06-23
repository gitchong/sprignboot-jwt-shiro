# company-frame

#### 介绍
基于spring boot 2.1.6、shiro、jwt、redis、swagger2、mybatis 、thymeleaf、layui 后台管理系统， 权限控制的方式为 RBAC。代码通熟易懂 、JWT（无状态token）过期自动刷新，数据全程 ajax 获取，封装 ajax 工具类、菜单无线层级展示，解决 layui.tree 树形组件，回显问题。数据交互都是以 JSON 格式交互。后台接口RESTful 风格，支持前后端分离，app公用一套接口。 

#### 软件架构
软件架构说明
* 核心框架：spring boot 2.1.6
* 持久层框架：mybatis
* 数据库连接池：alibaba druid
* 安全框架：apache shiro
* 无状态 JWT
* 缓存框架：redis(自定义 RedisTemplate 序列化)
* 日志框架：logback
* 接口文档：swagger 2.9.2
* 前端模板：thymeleaf+layui2x

#### **部署**

- 下载redis 启动redis
- 创建company_frame数据库
- 导入company_frame.sql
- 启动项目
- 接口文档访问 http://localhost:8080/swagger-ui.html#/
- 登录地址 http://localhost:8080/index/login
- 登录密码 都是 666666


特点：
1、有BaseResponseCode
2、vo目录下req,resp下分别存储controller返回和接收的视图
3、大量使用 BeanUtils.copyProperties(sysUser,respVO);  从SysUser类中copy同名元素到respVo视图返回类，post的时候是是图层拷贝到entity,
get的时候是entity->vo. BeanUtils.copyProperties(vo,sysUser);
4、数据操作
    更新：先select,后修改，修改数量不对，抛出自定义异常，数据库操作，其他操作
5、界面需要什么，就一次性组装返回。
6、url中带标志性参数/{userId},requestBody 中待传入的参数@PathVariable("userId")String userId, @RequestBody List<String> roleIds)   
7、controller里mapping具体操作的写在前面，例如user 写在前面，users卸载后面。
8、编号使用redis.incr生成数字部分，使用固定头产生YXD0001的效果。数字部分使用guava leftPad补全。
9、多次数据库操作使用了事务    @Transactional(rollbackFor = Exception.class)
####  还提供了配套视频讲解
[网易云课堂获取视频](https://study.163.com/course/introduction.htm?share=2&shareId=480000002172852&courseId=1209674835&_trace_c_p_k2_=69ae6b3d440249759ad085487f89f73a)

![](https://gitee.com/yingxue985/company-frame/raw/master/src/main/resources/static/images/class/23.jpg) 
![](https://gitee.com/yingxue985/company-frame/raw/master/src/main/resources/static/images/class/20.jpg) 
![](https://gitee.com/yingxue985/company-frame/raw/master/src/main/resources/static/images/class/15.jpg) 
![](https://gitee.com/yingxue985/company-frame/raw/master/src/main/resources/static/images/class/21.jpg) 
![](https://gitee.com/yingxue985/company-frame/raw/master/src/main/resources/static/images/class/22.jpg) 
![](https://gitee.com/yingxue985/company-frame/raw/master/src/main/resources/static/images/class/24jpg)


