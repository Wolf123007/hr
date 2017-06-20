<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String listLogUrl = basePath + "pages/back/admin/course/LogServletBack/list";
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
		    		<div><span class="title">培训课程列表</span></div>
		    		<div id="splitSearchDiv">
						<jsp:include page="/pages/split_page_plugin_search.jsp"/>
						<br>
					</div>
		        	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
		  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
		  					<td><strong>课程名称</strong></td>
		  					<td><strong>雇员编号</strong></td>
		  					<td><strong>姓名</strong></td>
		  					<td><strong>成绩</strong></td>
		  					<td><strong>考勤</strong></td>
		  				</tr>
		  				<c:if test="${ allDetails != null  && course != null}" var="res">
		  					<c:forEach items="${ allDetails }" var="details">
		  						<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
				  					<td>${ course.cname }</td>
				  					<td>${ details.employee.eid }</td>
				  					<td>${ details.ename }</td>
				  					<td>${ details.score}</td>
				  					<td><a href="<%=listLogUrl%>?details.dtid=${details.dtid}">个人考勤</a></td>
				  				</tr>
		  					</c:forEach>
		  				</c:if>
		  			</table>
		  			<div id="splitBarDiv" style="float:right">
						<jsp:include page="/pages/split_page_plugin_bars.jsp"/>
					</div>
  	</div>
  </body>
</html>
