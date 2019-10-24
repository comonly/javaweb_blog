# blog_spring_backend
java web项目，通过spring-mvc实现文章相关操作。包括：

文章增、改、查、删。返回json类型数据。

# 项目运行方法

使用mysql数据库，创建名为passage_low_for_git的数据库。

执行命令：mysql -u root  -p  passage_low_for_git < e:\mysql_passage_low_for_git_20190923.sql（数据库文件路径）导入数据库。

将项目导入eclipse，修改配置后编译运行（tomcat环境）。

成功在tomcat中运行后，通过Postman调用以下接口获取数据

# 接口
访问文章列表： http://localhost:8080/blog_spring_backend/passage/passIndex

查询某篇文章： http://localhost:8080/blog_spring_backend/passage/passDetail?id=1049

修改文章： http://localhost:8080/blog_spring_backend/passage/passChangeSubmit

    参数格式同查询某篇文章获取的结果一致。

新增文章： http://localhost:8080/blog_spring_backend/passage/passAddSubmit

    参数格式同查询某篇文章获取的结果一致。

删除文章： http://localhost:8080/blog_spring_backend/passage/passDelete?id=1054

# 说明

本项目仅用来演示 spring-mvc 的使用，以及前后端分离的开发模式。功能上实现比较简单且粗超。

使用maven管理包。