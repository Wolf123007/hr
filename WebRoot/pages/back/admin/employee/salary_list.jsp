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
    		<div><span class="title">雇员薪资变化列表</span></div>
    		<div id="splitSearchDiv">
				<jsp:include page="/pages/split_page_plugin_search.jsp"/>
				<br>
			</div>
        	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="100%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td><strong>雇员编号</strong></td>
  					<td><strong>管理员</strong></td>
  					<td><strong>原始工资</strong></td>
  					<td><strong>新工资</strong></td>
  					<td><strong>修改日期</strong></td>
  					<td><strong>原因</strong></td>
  					<td><strong>备注</strong></td>
  				</tr>
  				<c:if test="${ allSalarys != null }" var="res">
  					<c:forEach items="${ allSalarys }" var="salary">
  						<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
		  					<td>${ salary.employee.eid }</td>
		  					<td>${ salary.admin.aid }</td>
		  					<td>${ salary.oldsal}</td>
		  					<td>${ salary.newsal }</td>
		  					<td>${ salary.cdate }</td>
		  					<td>${ salary.reason }</td>
		  					<td>${ salary.note }</td>
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
