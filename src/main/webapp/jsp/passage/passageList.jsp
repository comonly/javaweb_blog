<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vip.chencheng.entity.*" %>
<%List<AllKindInfo> kindList=(List<AllKindInfo>)request.getAttribute("kindList"); %>
<%List<PassageInfo> passageInfoList=(List<PassageInfo> )request.getAttribute("passageInfoList"); %>
<%List<PassageInfo> belongList=(List<PassageInfo> )request.getAttribute("belongList"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文章列表</title>
  </head>
  
<body>
<div>
	<c:forEach var="passageInfo" items="<%=passageInfoList%>">
		<p>
			<span>${passageInfo.pnumber }&nbsp;&nbsp;
			<span style="color:#026EC9;">${passageInfo.pbelongName }</span>
			
			<a style="color:black;text-decoration:none;" target="_blank" href="/bloglow_for_git/passage/passDetail/${passageInfo.pnumber }">${passageInfo.ptitle }</a></span>
			&nbsp;&nbsp;&nbsp;
			<span style="color:#FFAA25;">${passageInfo.plabel }</span>
			<span style="color:#026EC9;">${passageInfo.pkindName }</span>
			<span><a target="_blank"  href="/bloglow_for_git/passage/passChange/${passageInfo.pnumber }" style="color:black;text-decoration:none;">修改</a></span>
			
		</p>
	</c:forEach>
</div>

<div id="rightFloatHide" class="floatDiv" style="width:20px;"><!-- 右侧展示所有种类的一个列表部分 -->
	<a href="javascript:void(0)" onclick="openFloat('rightFloat');"> √</a>
</div>

<div id="rightFloat" class="floatDiv" style="width:120px;"><!-- 右侧展示所有种类的一个列表部分 -->
<a href="javascript:void(0)" onclick="closeFloat('rightFloat');"> X</a>
	<div>
		<a href="/bloglow_for_git/passage/passIndex?pageIndex=0&kind=<%=request.getParameter("kind")%>&belong=<%=request.getParameter("belong")%>&select=<%=request.getParameter("select")%>">查看全部</a><br/><br/>
	</div>
	
	<div>
		<a href="/bloglow_for_git/passage/passAdd">增加新的文章</a>
	</div>
	
	<div>
		当前查询内容：</br>
		pageIndex=<%=request.getParameter("pageIndex")%></br>
		kind=<%=request.getParameter("kind")%></br>
		belong=<%=request.getParameter("belong")%></br>
		select=<%=request.getParameter("select")%>
	</div>
	<div><!-- 按关键字查看-->
		查找<br/>
		<form action="/bloglow_for_git/passage/passIndex" method="get">
			<input type="text" name="select">
			<input type="submit" value="查找" />
		</form>
	</div>
	<div><!-- 按分类查看的处理类表单，需要传递进去筛选条件 -->
		分类查看<br/>
		<form action="/bloglow_for_git/passage/passIndex" method="get">
			<select name="kind">
				<c:forEach var="kindInfo" items="<%=kindList%>">
					<option value="${kindInfo.akid}">${kindInfo.akid}.&nbsp;${kindInfo.akvalue }</option>
				</c:forEach>
			</select>
			<input type="submit" value="查找" />
		</form>
	</div>
	<div><!-- 按分类查看的处理类表单，需要传递进去筛选条件 -->
		归属查看<br/>
		<form action="/bloglow_for_git/passage/passIndex" method="get">
			<select name="belong">
				<c:forEach var="kindInfo" items="<%=belongList%>">
					<option value="${kindInfo.akid}">${kindInfo.akid}.&nbsp;${kindInfo.akvalue }</option>
				</c:forEach>
			</select>
			<input type="submit" value="查找" />
		</form>
	</div>
</div>
<style>
.floatDiv{
	position: fixed;
	display: block;
	top: 2px;
	right: 2px;
	width: 300px;
	background: #F0F3F5;
	padding: 10px 10px 10px 10px;
	border: 1px solid #d6d6d6;
}
</style>
<script type="text/javascript">
//关闭悬浮窗
function closeFloat(idname){
	document.getElementById(idname).style.display="none";
}
function openFloat(idname){
	document.getElementById(idname).style.display="";
}
</script>
</body>
</html>
