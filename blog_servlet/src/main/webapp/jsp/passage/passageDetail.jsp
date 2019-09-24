<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vip.chencheng.entity.*" %>
<%List<CommentInfo> commentInfoList=(List<CommentInfo>)request.getAttribute("commentInfoList"); %>
<%PassageInfo passageInfo=(PassageInfo)request.getAttribute("passageInfo"); %>
<c:set var="passageInfo" value="${passageInfo}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>详细文章页</title>
  </head>
  
<body>
	<div style="float:left;width:72%;padding-left:10px;"><!-- 文章主体部分 -->
		<div><h1>${passageInfo.ptitle }</h1></div><!-- 标题 -->
		<div style="font-style:italic;font-size:14px;">
			${passageInfo.ptime }&nbsp;${passageInfo.plabel }&nbsp;${passageInfo.pkindName }&nbsp;&nbsp;&nbsp;&nbsp;
			<a style="text-decoration:none;" href="/bloglow_for_git/passage/passChange/${passageInfo.pnumber }" target="_blank">修改</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#026EC9;">${passageInfo.pbelongName }</span>
		
		
		</div><!-- 时间，关键字，种类名 -->
		
		<div>${passageInfo.pcount }</div><!-- 正文 -->
		
		<div>${passageInfo.pcoming }&nbsp;${passageInfo.pcomingUrl }</div><!-- 来源和网址 -->
		
		
		<div><!-- 添加新的评论 -->
			<form ACTION="/bloglow_for_git/passage/commentAddSubmit/" METHOD="POST">
				<td>新加评论</td>
				<input type="hidden" name="pnumber" value="${passageInfo.pnumber }"/>
				评论标题：<input type="text" name="title"/>
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
						style="width: 98%; height: 260px; visibility: hidden;"></textarea>
				</td>
				<p><input type="submit" value="提交评论"></p>
			</form>
			
			<c:if test="${not empty commentInfoList}">评论列表</c:if>
			<c:forEach var="commentInfo" items="<%=commentInfoList%>">
				<div>
					<span id="${commentInfo.ctitle }">
						<h2 style="display:inline;color:#5BC648;">${commentInfo.ctitle }</h2>
						&nbsp;&nbsp;&nbsp;&nbsp;${commentInfo.ctime }&nbsp;&nbsp;&nbsp;&nbsp;
					</span><!-- 标题，时间 -->
					<span><a href="/bloglow_for_git/passage/commentChange/${commentInfo.cnumber}" target="_blank">修改</a></span>
					<br/>
					<span>${commentInfo.ccount }</span><!-- 内容 -->
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div>
		
	</div>
	
	<div  style="position: fixed;display: block;top: 2px;right: 2px;width: 300px;"><!-- 评论部分 -->
		文章内容的h2标题锚点：
		<c:forEach var="h2titleone" items="${h2title}">
			<div>
				<span style="display:block;margin:10px;"><a style="text-decoration:none;color:#5BC648;" href="#${h2titleone}">${h2titleone }</a></span><!-- 标题列表，快速查看-->
			</div>
		</c:forEach>
		
	
		<c:if test="${not empty commentInfoList}">评论列表</c:if>
		<c:forEach var="commentInfo" items="<%=commentInfoList%>">
			<div>
				<span style="display:block;margin:10px;"><a style="text-decoration:none;color:#5BC648;" href="#${commentInfo.ctitle }">${commentInfo.ctitle }</a></span><!-- 标题列表，快速查看-->
			</div>
		</c:forEach>

		
	</div>
	

<link href="/bloglow_for_git/extends/kindeditor-4.1.10/plugins/code/prettify.css" rel="stylesheet" type="text/css"></script>
<script charset="utf-8" src="/bloglow_for_git/extends/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<script type="text/javascript">
prettyPrint();
</script>
 
</body>
</html>
