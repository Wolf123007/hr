<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/back/admin/admin/AdminServletBack/insert";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/admin.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="100%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td colspan="3"><span class="title">增加的管理员</span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">登录ID：</td>
  					<td width="40%"><input type="text" name="admin.aid" id="admin.aid" class="init" onblur="validateAid()"></td>
  					<td width="45%"><span id="admin.aidMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">登录密码：</td>
  					<td width="40%"><input type="password" name="admin.password" id="admin.password" class="init" onblur="validatePassword()"></td>
  					<td width="45%"><span id="admin.passwordMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td>类型：</td>
  					<td>
  						<select name="admin.type" id="admin.type" onchange="setRole(this.value)">
  							<option value="1">后台系统管理员</option>
  							<option value="0">前台人事管理员</option>
  						</select>
  					</td>
  					<td><span id="admin.typeMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td>角色：</td>
  					<td>
  						<select name="admin.role.rid" id="admin.role.rid">
	  						<c:if test="${ allRoles != null }" var="res">
	  							<c:forEach items="${ allRoles }" var="role">
	  								<option value="${ role.rid }">${ role.title }</option>
	  							</c:forEach>
	  						</c:if>
  						</select>
  					</td>
  					<td><span id="ridMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td colspan="3">
						<input type="submit" value="增加">
						<input type="reset" value="重置">
					</td>
  				</tr>
  			</table>
  		</form>
  	</div>
  </body>
</html>
