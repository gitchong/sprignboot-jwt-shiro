# story-admin

本项目为前后端分离的Web应用后端程序，采用技术框架如下：
1. springboot v2.1.2.RELEASE
2. shiro
3. jwt
4. redis
5. mybatis-plus v3.1.2 

包含
1. 代码生成器
2. 文档swagger2, http://localhost:9430/swagger-ui.html

使用jwt采用token有效期内刷新机制更新Token。

项目已实现功能包括：
1. 用户登录
2. 用户管理
3. 角色管理
4. 权限管理
5. 菜单管理

特点：
 - 定时任务
    - 定义了quartz.proprties文件。使用SchedulerFactoryBean注入了相关配置。
    - 数据库备份：采用runtime.exe 执行mysql mysqldump的方式生成流，写入文件，执行完毕，使用数据库记录备份的相关信息。 

