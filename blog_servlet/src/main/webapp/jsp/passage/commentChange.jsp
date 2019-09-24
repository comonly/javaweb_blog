<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vip.chencheng.entity.*" %>
<!-- 作用域中已经存在的commentInfo，可以直接用 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>修改评论</title>

  </head>
  
<body>
	<div><!-- 修改评论 -->
		<form ACTION="/bloglow_for_git/passage/commentChangeSubmit/" METHOD="POST">
			<td>更新评论</td>
			<input type="hidden" name="cnumber" value="${commentInfo.cnumber }"/>
			评论标题：<input type="text" name="ctitle" value="${commentInfo.ctitle }"/>
			<script charset="utf-8" src="/bloglow_for_git/extends/kindeditor-4.1.10/kindeditor.js"></script>
			<script charset="utf-8" src="/bloglow_for_git/extends/kindeditor-4.1.10/lang/zh_CN.js"></script>
			<script>
				KindEditor.ready(function(K) {
					K.create('#editor_id',
						{
							uploadJson : '/bloglow_for_git/extends/kindeditor-4.1.10/jsp/upload_json.jsp',
							fileManagerJson : '/bloglow_for_git/extends/kindeditor-4.1.10/jsp/file_manager_json.jsp',
							allowFileManager : true
						});
				});
			</script>
 			<td>
				<textarea id="editor_id" name="content"
					style="width: 68%; height: 260px; visibility: hidden;">${commentInfo.ccount }</textarea>
			</td>
			<p><input type="submit" value="提交评论"></p>
		</form>
	</div>
</body>
</html>
