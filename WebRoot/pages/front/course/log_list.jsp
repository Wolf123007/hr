<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String listLogUrl = basePath + "pages/front/course/LogServletFront/list";
String updateStatusUrl = basePath + "pages/front/course/LogServletFront/updateStatus";
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
    <script type="text/javascript" src="js/log.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<jsp:include page="/pages/menu-top.jsp"/>
		<div class="nav-down">
			<jsp:include page="/pages/menu-bar.jsp"/>
		    <div class="rightcon">
		    	<div class="right_con" style="padding-left: 50px">
		    		<div><span class="title">培训课程考勤列表</span></div>
		    		<div id="splitSearchDiv">
						<jsp:include page="/pages/split_page_plugin_search.jsp"/>
						<br>
					</div>
		        	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
		  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
		  					<td><strong>考勤日期</strong></td>
		  					<td><strong>课程次数</strong></td>
		  					<td><strong>考勤</strong></td>
		  				</tr>
		  				<c:if test="${ allLogs != null }" var="res">
		  					<c:forEach items="${ allLogs }" var="log">
		  						<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
				  					<td>${ log.recdate }</td>
				  					<td>${ log.num}</td>
				  					<td>
				  						<c:if test="${ log.status == 0 }">
				  							<input type="button" value="签到" onclick="updateStatus('<%=updateStatusUrl%>?details.dtid=<%=request.getParameter("details.dtid")%>', 1, ${ log.lid })">
				  							<input type="button" value="旷课" onclick="updateStatus('<%=updateStatusUrl%>?details.dtid=<%=request.getParameter("details.dtid")%>', 2, ${ log.lid })">
				  							<input type="button" value="迟到" onclick="updateStatus('<%=updateStatusUrl%>?details.dtid=<%=request.getParameter("details.dtid")%>', 3, ${ log.lid })">
				  						</c:if>
				  						<c:if test="${ log.status == 1 }">签到</c:if>
				  						<c:if test="${ log.status == 2 }">旷课</c:if>
				  						<c:if test="${ log.status == 3 }">迟到</c:if>
				  					</td>>
				  				</tr>
		  					</c:forEach>
		  				</c:if>
		  			</table>
		  			<div id="splitBarDiv" style="float:right">
						<jsp:include page="/pages/split_page_plugin_bars.jsp"/>
					</div>
				</div>
			</div>
		</div>
  	</div>
  </body>
</html>
