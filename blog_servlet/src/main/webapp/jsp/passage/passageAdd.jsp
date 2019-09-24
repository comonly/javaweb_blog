<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vip.chencheng.entity.*" %>
<%List<AllKindInfo> kindList=(List<AllKindInfo>)request.getAttribute("kindList"); %>
<%List<AllKindInfo> belongList=(List<AllKindInfo>)request.getAttribute("belongList"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

  <title>文章修改</title>

</head>
  
<body>

<form ACTION="/bloglow_for_git/passage/passAddSubmit/" METHOD="POST">
	<table width="100%" height="279" border="0">
		<tr>
			<td height="30">
				文章标题
			</td>
			<td>
				<label>
					<input name="title" type="text" id="title" size="100" />
				</label>

			</td>
		</tr>
		<tr>
			<td height="30">图片</td>
			<td>
				<label>
					<input name="image" type="text" size="100" />
				</label>
			</td>
		</tr>
		<tr>
			<td>
				关键字
			</td>
			<td>
				<label>
					<input name="keyword" type="text"  size="100" />
				</label>
			</td>
		</tr>
		<tr>
			<td>文章</td>
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
					style="width: 96%; height: 300px; visibility: hidden;"></textarea>
			</td>
		</tr>
		<tr>
			<td>来源</td>
			<td>
				<label>
					<input name="coming" type="text" />
					对应网址：
					<input name="comingUrl" type="text" size="80" />
				</label>
			</td>
		</tr>
		<tr>
			<td>文章类型</td>
			<td>
				<label>
					<select name="passageKind" id="passageKind">
						<c:forEach var="kindInfo" items="<%=kindList%>">
							<option value="${kindInfo.akid}">${kindInfo.akid}.&nbsp;${kindInfo.akvalue }</option>
						</c:forEach>
					</select>
					（选择类别）
				</label>
			</td>
			<td>文章归属</td>
			<td>
				<label>
					<select name="passageBelong" id="passageBelong">
						<c:forEach var="kindInfo" items="<%=belongList%>">
							<option value="${kindInfo.akid}">${kindInfo.akid}.&nbsp;${kindInfo.akvalue }</option>
						</c:forEach>
					</select>
					（选择归属）
				</label>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="添加文章" />
			</td>
		</tr>
	</table>

</form>





</body>
</html>
