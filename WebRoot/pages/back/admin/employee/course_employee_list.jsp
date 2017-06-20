<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String listDetailsUrl = basePath + "pages/back/admin/course/DetailsServletBack/list";
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
		  					<td><strong>发布者</strong></td>
		  					<td><strong>课程天数</strong></td>
		  					<td><strong>开始日期</strong></td>
		  					<td><strong>结束日期</strong></td>
		  					<td><strong>课程状态</strong></td>
		  					<td><strong>操作</strong></td>
		  				</tr>
		  				<c:if test="${ allCourses != null }" var="res">
		  					<c:forEach items="${ allCourses }" var="course">
		  						<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
				  					<td>${ course.cname }</td>
				  					<td>${ course.admin.aid }</td>
				  					<td>${ course.total}</td>
				  					<td>${ course.begin }</td>
				  					<td>${ course.end }</td>
				  					<td>
				  						<c:if test="${ course.status == 1 }">正常</c:if>
				  						<c:if test="${ course.status == 0 }">暂停</c:if>
				  					</td>
				  					<td><a href="<%=listDetailsUrl%>?course.cid=${course.cid}">查看详情</a></td>
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
