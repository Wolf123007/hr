<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/back/admin/groups/GroupsServletBack/update";
String deleteUrl = basePath + "pages/back/admin/groups/GroupsServletBack/delete?p=p";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/groups.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<div class="title">权限组数据列表</div>
  		<c:if test="${ allGroups != null }" var="res">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%"><input type="checkbox" onclick="checkboxSelect(this, 'gid')"></td>
  					<td width="40%">权限组名称</td>
  					<td width="45%">权限组介绍</td>
  					<td>操作</td>
  				</tr>
				<c:forEach items="${ allGroups }" var="groups">
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td><input type="checkbox" name="gid" id="gid" value="${ groups.gid }"></td>
					<td>
						<input type="text" name="title-${ groups.gid }" id="title-${ groups.gid }" value="${ groups.title }">
						<span id="title-${ groups.gid }Msg"></span>
					</td>
					<td>
						<input type="text" name="note-${ groups.gid }" id="note-${ groups.gid }" value="${ groups.note }">
						<span id="note-${ groups.gid }Msg"></span>
					</td>
					<td><input type="button" value="修改" onclick="updateData(${ groups.gid })"></td>
				</tr>
				</c:forEach>
  			</table>
  			<input type="button" value="批量删除" onclick="deleteAll('<%=deleteUrl%>', 'ids', 'gid')">
			<form action="<%= updateUrl %>" method="post" id="groupsForm">
				<input type="hidden" name="groups.title" id="groups.title">
				<input type="hidden" name="groups.gid" id="groups.gid">
				<input type="hidden" name="groups.note" id="groups.note">
			</form>
  		</c:if>
  	</div>
  </body>
</html>
