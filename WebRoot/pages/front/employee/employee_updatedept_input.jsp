<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String inputUrl = basePath + "pages/front/employee/EmployeeServletFront/updateDeptPre";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/employee.js"></script>
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
		    		<p style="text-align:left; margin-top:50px">            
        			<h3>雇员职位变更</h3>
		        	<form action="<%=inputUrl%>" method="post" onsubmit="return validateInput()">
			            <div class="form_add">
			          		  员工编号<span class="list"><input name="employee.eid" id="employee.eid" type="text" class="init" onblur="validateEid()" /></span>
			            	<span id="employee.eidMsg"></span>
			            </div>
			            <div class="form_add">
			          		<span class="list"> </span>
			          		<span class="list">  <input name="" type="submit" value="查询" /> </span>
			            </div>
           			 </form>
		        </div>
		    </div>
		</div>
  	</div>
  </body>
</html>
