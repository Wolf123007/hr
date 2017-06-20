<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updatePreUrl = basePath + "pages/back/admin/role/RoleServletBack/updatePre";
String deleteUrl = basePath + "pages/back/admin/role/RoleServletBack/delete?p=p";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/role.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<div class="title">角色信息列表列表</div>
  		<c:if test="${ allRoles != null }" var="res">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td><input type="checkbox" onclick="checkboxSelect(this, 'rid')"></td>
  					<td>角色名称</td>
  					<td>备注</td>
  					<td>操作</td>
  				</tr>
				<c:forEach items="${ allRoles }" var="role">
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td><input type="checkbox" name="rid" id="rid" value="${ role.rid }"></td>
					<td>${ role.title }</td>
					<td>${ role.note }</td>
					<td><a href="<%=updatePreUrl%>?role.rid=${role.rid}">修改</a></td>
				</tr>
				</c:forEach>
  			</table>
  			<input type="button" value="批量删除" onclick="deleteAll('<%=deleteUrl%>', 'ids', 'rid')">
  		</c:if>
  	</div>
  </body>
</html>
