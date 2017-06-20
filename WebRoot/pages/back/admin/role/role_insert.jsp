<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/back/admin/role/RoleServletBack/insert";
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
  		<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="100%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td colspan="3"><span class="title">增加角色数据</span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">角色名称：</td>
  					<td width="40%"><input type="text" name="role.title" id="role.title" class="init" onblur="validateTitle()"></td>
  					<td width="45%"><span id="role.titleMsg"></span></td>
  				</tr>
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td>具备权限：</td>
					<td>
						<table>
							<c:forEach items="${ allGroups }" var="groupslist">
								<tr>
									<c:forEach items="${ groupslist }" var="groups">
										<td>
											<input type="checkbox" name="gid" id="gid" value="${ groups.gid }" class="init">${ groups.title }
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</table>
					</td>
					<td><span id="gidMsg"></span></td>
				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td>角色简介：</td>
  					<td><input type="text" name="role.note" id="role.note" class="init" onblur="validateNote()"></td>
  					<td><span id="role.noteMsg"></span></td>
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
