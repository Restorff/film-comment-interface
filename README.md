# 影评系统后端接口

* 本项目基于Springboot架构，整合MyBatis、Swagger进行开发

# 使用方法
将项目下载到本地后更改配置文件的设置
1. redis的ip和密码，没有远程服务器的可以在本地虚拟机里安装配置一个redis，在resource下的application.properties下
2. 阿里云OSS的KeyID和KeySecret，在resource下的application.properties下，开发参考阿里云开发文档:[https://help.aliyun.com/document_detail/84781.html](http://help.aliyun.com/document_detail/84781.html)
3. 阿里云短信服务的KeyID和KeySecret，在 **service包下面的MsmServiceImpl类** 下，具体开发参考阿里云开发文档：[https://help.aliyun.com/document_detail/55284.html](http://help.aliyun.com/document_detail/55284.html)
4. mysql数据库的链接字符串和账号密码，注意修改pom文件中数据库版本，数据库脚本文件参考文件根目录，有一百多条电影数据。在resource下的application.properties下

# 测试方式：
项目跑起来之后，在浏览器输入[http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#),即可测试接口数据。

# 管理后台界面地址
https://github.com/Restorff/filmcomment-front-admin

# 用户前台界面地址
https://github.com/Restorff/film-comment-user-front
