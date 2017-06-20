<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "AdminLogin/login" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>HR项目实战</title>
<link type="text/css" rel="stylesheet" href="css/mldn.css">
<link type="text/css" rel="stylesheet" href="css/login.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/mldn.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
</head>
<body>
	<div id="logo">
		<img src="images/logo-head.jpg">
	</div>
	<div id="mainDiv">
		<form id="slick-login" method="post" action="<%=loginUrl%>" onsubmit="return validateLogin()"> 
			<label for="username">用户名：</label>
			<input type="text" name="admin.aid" class="init" placeholder="用户名" id="admin.aid" onblur="validateAid()">
			<span id="admin.aidMsg"></span>
			
			<label for="password">密&nbsp;&nbsp;码：</label>
			<input type="password" name="admin.password" class="init" placeholder="密码" id="admin.password" onblur="validatePassword()"> 			
			<span id="admin.passwordMsg"></span>
			
			<label for="code">验证码：</label>
			<input type="text" name="code" class="init" placeholder="验证码" id="code" maxlength="4" size="4" onblur="validateCode()">
			<img src="pages/image.jsp">
			<span id="codeMsg"></span>
			
			<input type="submit" value="登录">
		</form>
	</div>
</body>
</html>
