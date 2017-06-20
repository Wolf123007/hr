<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
		<jsp:include page="/pages/menu-top.jsp"/>
		<div class="nav-down">
			<jsp:include page="/pages/menu-bar.jsp"/>
		    <div class="rightcon">
		    	<div class="right_con">
		        	<p style="text-align:left; margin-top:50px">右侧内容自适应哦！我是左对齐</p>
		            <p style="text-align:center">右s侧内容自适应哦！我是居中</p>
		            <p style="text-align:right">右侧内容自适应哦！我是右对齐</p>
		            <h1>我是标题1。。。</h1>
		            <h2>我是标题2。。。</h2>
		            <h3>我是标题3。。。</h3>
		            <h4>我是标题4。。。</h4>
		            <h5>我是标题5。。。</h5>
		        </div>
		    </div>
		</div>
  	</div>
  </body>
</html>
