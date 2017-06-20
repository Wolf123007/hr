<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/back/admin/action/ActionServletBack/insert";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/action.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="100%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td colspan="3"><span class="title">增加权限组信息</span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">权限名称：</td>
  					<td width="40%"><input type="text" name="action.title" id="action.title" class="init" onblur="validateTitle()"></td>
  					<td width="45%"><span id="action.titleMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td>所属权限组：</td>
  					<td>
  						<select name="action.groups.gid" id="action.groups.gid">
	  						<c:if test="${ allGroups != null }" var="res">
	  							<c:forEach items="${ allGroups }" var="groups">
	  								<option value="${ groups.gid }">${ groups.title }</option>
	  							</c:forEach>
	  						</c:if>
  						</select>
  					</td>
  					<td><span id="gidMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td>操作路径：</td>
  					<td><input type="text" name="action.url" id="action.url" class="init" onblur="validateUrl()"></td>
  					<td><span id="action.urlMsg"></span></td>
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
