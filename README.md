# javaweb_blog
java web项目，实现文章相关操作、及相关评论、简单的查询。简单实现博客的基本功能。包括：

文章增、改、查。

评论添加修改。

文章种类包含两个下拉框选项，可以用来分类。有对应的分类管理界面。

# 项目运行方法

使用mysql数据库，创建名为passage_low_for_git的数据库。

执行命令：mysql -u root  -p  passage_low_for_git < e:\mysql_passage_low_for_git_20190923.sql（数据库文件路径）导入数据库。

将项目导入eclipse，修改配置后编译运行（tomcat环境）。

成功在tomcat中运行后，通过http://localhost:8088/bloglow_for_git/访问。