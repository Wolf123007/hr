<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/back/admin/dept/DeptServletBack/update";
String deleteUrl = basePath + "pages/back/admin/dept/DeptServletBack/delete?p=p";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/dept.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<div class="title">部门数据列表</div>
  		<c:if test="${ allDepts != null }" var="res">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%"><input type="checkbox" onclick="checkboxSelect(this, 'did')"></td>
  					<td width="40%">部门名称</td>
  					<td width="45%">部门人数</td>
  					<td>操作</td>
  				</tr>
				<c:forEach items="${ allDepts }" var="dept">
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td><input type="checkbox" name="did" id="did" value="${ dept.did }"></td>
					<td>
						<input type="text" name="dname-${ dept.did }" id="dname-${ dept.did }" value="${ dept.dname }">
						<span id="dname-${ dept.did }Msg"></span>
					</td>
					<td>${ dept.current }</td>
					<td><input type="button" value="修改" onclick="updateData(${ dept.did })"></td>
				</tr>
				</c:forEach>
  			</table>
  			<input type="button" value="批量删除" onclick="deleteAll('<%=deleteUrl%>', 'ids', 'did')">
			<form action="<%= updateUrl %>" method="post" id="deptForm">
				<input type="hidden" name="dept.dname" id="dept.dname">
				<input type="hidden" name="dept.did" id="dept.did">
			</form>
  		</c:if>
  	</div>
  </body>
</html>
