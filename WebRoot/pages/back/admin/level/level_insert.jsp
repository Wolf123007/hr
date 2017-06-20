<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/back/admin/level/LevelServletBack/insert";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/level.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td colspan="3"><span class="title">增加职位级别数据</span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">等级名称：</td>
  					<td width="40%"><input type="text" name="level.title" id="level.title" class="init" onblur="validateTitle()"></td>
  					<td width="45%"><span id="level.titleMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">最低工资：</td>
  					<td width="40%"><input type="text" name="level.losal" id="level.losal" class="init" onblur="validateLosal()"></td>
  					<td width="45%"><span id="level.losalMsg"></span></td>
  				</tr>
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%">最高工资：</td>
  					<td width="40%"><input type="text" name="level.hisal" id="level.hisal" class="init" onblur="validateHisal()"></td>
  					<td width="45%"><span id="level.hisalMsg"></span></td>
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
