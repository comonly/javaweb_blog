<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vip.chencheng.entity.*" %>

<%List<AllKindInfo> rootKindList=(List<AllKindInfo> )request.getAttribute("rootKindList"); %>
<%List<AllKindInfo> kindList=(List<AllKindInfo> )request.getAttribute("kindList"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>每月账单种类列表</title>
  </head>

<body>
<iframe name="secoundFrame" class="secoundFrame" id="secoundFrame" 
	style="float:right;width:800px;height:500px;display:none;">
</iframe>
<div><!-- 核心展示部分 -->
账单类名管理&nbsp;&nbsp;<a style="text-decoration:none;"  target="secoundFrame" 
		onclick="openFloat('secoundFrame');closeFloat('akkindList');"
		href="/bloglow_for_git/kindmanage/allKind?akkind=${kindList[0].akkind}&isroot=false">查看二级目录</a>
<table>
	<tr>
		<td >编号(Change)</td>
		<td width="100px">类别</td>
		<td min-width="1000px">默认备注信息</td>
		<td width="100px">状态</td>
		<td>父类名</td>
	</tr>
<c:forEach var="kindInfo" items="<%=kindList %>">
	<tr>
		<td onclick="viewDetail(${kindInfo.akid},'${kindInfo.akvalue}','${kindInfo.akdetail}',${kindInfo.akstate},${kindInfo.akfather},${kindInfo.akkind});" style="cursor:pointer;">
			${kindInfo.akid }</td>
		<td>${kindInfo.akvalue }</td>
		<td>${kindInfo.akdetail}</td>
		<td><c:choose><c:when test="${kindInfo.akstate ne 1 }">正常</c:when>
			<c:otherwise>待处理</c:otherwise></c:choose></td>
		<td>${kindInfo.akfather }</td>
		
	</tr>
</c:forEach>
</table>
</div>

<div id="rightFloatHide" class="floatDiv" style="width:10px;"><!-- 右侧展示所有种类的一个列表部分 -->
	<a href="javascript:void(0)" onclick="openFloat('akkindList');"> √</a>
</div>

<div id="changedetail" style="display:none;" class="floatDiv"><!-- 修改某一项的具体内容 -->
	<a href="javascript:void(0)" onclick="closeFloat('changedetail');openFloat('akkindList');"> X</a>
	<form action="/bloglow_for_git/kindmanage/allKindChange/" method="post">
		<p>编号：<span id="changeid"></span></p>
		<input type="hidden" name="akid" id="changeId" />
		<p>值：<input name="akvalue" id="changeValue"></p>
		<p>父级id：<input name="akfather" id="changeFather"></p>
		<p>
			种类：<select name="akkind" id="changeKind">
				<option value="1">1&nbsp;&nbsp;根类 (改本列)</option>
			<c:forEach var="kindInfo" items="<%=rootKindList%>">
				<option value="${kindInfo.akid }">${kindInfo.akid }&nbsp;&nbsp;${kindInfo.akvalue }</option>
			</c:forEach>
			</select>
		</p>
		<p>默认备注信息：<textarea name="akdetail" id="changeDetail"></textarea></p>
		<p>状态：
			 <input type="radio" name="akstate" id="akstate0" value="0">正常
			 <input type="radio" name="akstate" id="akstate1" value="1">隐藏选项
		</p>
		<p><input type="submit" /></p>
	</form>
</div>
<div id="addNewList" style="display:none;" class="floatDiv"><!-- 添加新的选项信息-->
	<a href="javascript:void(0)" onclick="closeFloat('addNewList');openFloat('akkindList');"> X</a>
	<form action="/bloglow_for_git/kindmanage/allKindAdd/" method="post">
		<p>
			种类：<select name="akkind" id="addkind">
			<c:forEach var="kindInfo" items="<%=rootKindList%>">
				<option value="${kindInfo.akid }">${kindInfo.akid }&nbsp;&nbsp;${kindInfo.akvalue }</option>
			</c:forEach>
			</select>
			
			<script >document.getElementById("addkind").value=${kindList[0].akkind};</script><!-- 初始化添加的种类 -->
		</p>
		<p>显示值：<input name="akvalue" id="changeValue"></p>
		<p>父级id：<input name="akfather" value="0"></p>
		<p>默认备注信息：<textarea name="akdetail" id="changeDetail"></textarea></p>
		<p>状态：
			 <input type="radio" name="akstate" value="0" checked="checked">正常
			 <input type="radio" name="akstate" value="1">隐藏选项
		</p>
		<p><input type="submit" /></p>
	</form>
</div>


<div id="akkindList" class="floatDiv" style="width:180px;"><!-- 右侧展示所有种类的一个列表部分 -->
<a href="javascript:void(0)" onclick="closeFloat('akkindList');"> X</a>
所有类别(<a style="text-decoration:none;" href="/bloglow_for_git/kindmanage/allKind?akkind=1">修改</a>)
(<a style="text-decoration:none;" href="javascript:void(0)" onclick="openFloat('addNewList');closeFloat('akkindList');">添加</a>)
<table>
<c:forEach var="kindInfo" items="<%=rootKindList%>">
	<tr>
		<td><a style="text-decoration:none;" 
			href="/bloglow_for_git/kindmanage/allKind?akkind=${kindInfo.akid}">${kindInfo.akid }</a></td>
		<td>${kindInfo.akvalue}</td>
		<td><c:choose><c:when test="${kindInfo.akstate ne 1 }">正常</c:when>
			<c:otherwise>待处理</c:otherwise></c:choose></td>
	</tr>
</c:forEach>
</table>
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
<script>
//关闭悬浮窗
function closeFloat(idname){
	document.getElementById(idname).style.display="none";
}
function openFloat(idname){
	document.getElementById(idname).style.display="";
}
//显示更改信息的弹窗
function viewDetail(akid,akvalue,akdetail,akstate,akfather,akkind){
	openFloat("changedetail");
	closeFloat("akkindList");

	document.getElementById("changeid").innerText=akid;
	document.getElementById("changeId").value=akid;
	document.getElementById("changeValue").value=akvalue;
	document.getElementById("changeDetail").value=akdetail;
	document.getElementById("changeFather").value=akfather;
	document.getElementById("changeKind").value=akkind;
	if(akstate==1){
		document.getElementById("akstate0").checked=false;
		document.getElementById("akstate1").checked=true;
	}else{
		document.getElementById("akstate1").checked=false;
		document.getElementById("akstate0").checked=true;
	}

}

</script>
</body>
</html>
