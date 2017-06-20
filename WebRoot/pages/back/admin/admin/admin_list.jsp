<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/back/admin/admin/AdminServletBack/update";
String deleteUrl = basePath + "pages/back/admin/admin/AdminServletBack/delete?p=p";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/admin.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<div class="title">管理员信息列表</div>
  		<c:if test="${ allAdmins != null }" var="res">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="100%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td><input type="checkbox" onclick="checkboxSelect(this, 'aid')"></td>
  					<td>管理员ID</td>
  					<td>角色</td>
  					<td>密码</td>
  					<td>类型</td>
  					<td>最后登录日期</td>
  					<td>操作</td>
  				</tr>
				<c:forEach items="${ allAdmins }" var="admin">
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td><input type="checkbox" name="aid" id="aid" value="${ admin.aid }"></td>
					<td>${ admin.aid }</td>
  					<td>
  						<select name="rid-${ admin.aid }" id="rid-${ admin.aid }" ${ admin.type == 0 ? "disabled" : "" }>
	  						<c:if test="${ allRoles != null }" var="res">
	  							<c:forEach items="${ allRoles }" var="role">
	  								<option value="${ role.rid }" ${ role.rid == admin.role.rid ? "selected" : "" }>${ role.title }</option>
	  							</c:forEach>
	  						</c:if>
  						</select>
  						<span id="ridMsg"></span>
  					</td>
					<td>
						<input type="password" name="password-${ admin.aid }" id="password-${ admin.aid }" value="${ admin.password }">
						<span id="password-${ admin.aid }Msg"></span>
					</td>
					<td>
  						<select name="type-${ admin.aid }" id="type-${ admin.aid }" onchange="setRole2(this.value, '${ admin.aid }')">
  							<option value="1" ${ admin.type == 1 ? "selected" : "" }>后台系统管理员</option>
  							<option value="0" ${ admin.type == 0 ? "selected" : "" }>前台人事管理员</option>
  						</select>
  						<span id="type-${ admin.aid }Msg"></span>
  					</td>
					<td>${ admin.lastdate }</td>
					<td><input type="button" value="修改" onclick="updateData('${ admin.aid }')" ${ badmin.flag != 1 ? "disabled" : "" }></td>
				</tr>
				</c:forEach>
  			</table>
  			<input type="button" value="批量删除" onclick="deleteAll('<%=deleteUrl%>', 'ids', 'aid')">
			<form action="<%= updateUrl %>" method="post" id="adminForm">
				<input type="hidden" name="admin.aid" id="admin.aid">
				<input type="hidden" name="admin.role.rid" id="admin.role.rid">
				<input type="hidden" name="admin.password" id="admin.password">
				<input type="hidden" name="admin.type" id="admin.type">
			</form>
  		</c:if>
  	</div>
  </body>
</html>
