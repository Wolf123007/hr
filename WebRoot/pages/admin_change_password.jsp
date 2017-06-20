<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updatePasswordUrl = basePath + "/AdminLogin/updatePassword";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<form action="<%=updatePasswordUrl%>" method="post" onsubmit="return validateUpdatePassword()">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td colspan="3">密码修改</td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">原始密码：</td>
  					<td width="40%"><input type="text" name="oldpass" id="oldpass" class="init" onblur="validateOldpass()"></td>
  					<td width="45%"><span id="oldpassMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">新的密码：</td>
  					<td width="40%"><input type="text" name="newpass" id="newpass" class="init" onblur="validateNewpass()"></td>
  					<td width="45%"><span id="newpassMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">确认密码：</td>
  					<td width="40%"><input type="text" name="conf" id="conf" class="init" onblur="validateConf()"></td>
  					<td width="45%"><span id="confMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td colspan="3">
						<input type="submit" value="更新">
						<input type="reset" value="重置">
					</td>
  				</tr>
  			</table>
  		</form>
  	</div>
  </body>
</html>
