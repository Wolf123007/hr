<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/back/admin/jobs/JobsServletBack/update";
String deleteUrl = basePath + "pages/back/admin/jobs/JobsServletBack/delete?p=p";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/jobs.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<div class="title">职位数据列表</div>
  		<c:if test="${ allJobs != null }" var="res">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%"><input type="checkbox" onclick="checkboxSelect(this, 'jid')"></td>
  					<td width="40%">职位名称</td>
  					<td width="45%">职位介绍</td>
  					<td>操作</td>
  				</tr>
				<c:forEach items="${ allJobs }" var="jobs">
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td><input type="checkbox" name="jid" id="jid" value="${ jobs.jid }"></td>
					<td>
						<input type="text" name="title-${ jobs.jid }" id="title-${ jobs.jid }" value="${ jobs.title }">
						<span id="title-${ jobs.jid }Msg"></span>
					</td>
					<td>
						<input type="text" name="note-${ jobs.jid }" id="note-${ jobs.jid }" value="${ jobs.note }">
						<span id="note-${ jobs.jid }Msg"></span>
					</td>
					<td><input type="button" value="修改" onclick="updateData(${ jobs.jid })"></td>
				</tr>
				</c:forEach>
  			</table>
  			<input type="button" value="批量删除" onclick="deleteAll('<%=deleteUrl%>', 'ids', 'jid')">
			<form action="<%= updateUrl %>" method="post" id="jobsForm">
				<input type="hidden" name="jobs.title" id="jobs.title">
				<input type="hidden" name="jobs.jid" id="jobs.jid">
				<input type="hidden" name="jobs.note" id="jobs.note">
			</form>
  		</c:if>
  	</div>
  </body>
</html>
