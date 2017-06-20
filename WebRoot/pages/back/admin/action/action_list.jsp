<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/back/admin/action/ActionServletBack/update";
String deleteUrl = basePath + "pages/back/admin/action/ActionServletBack/delete?p=p";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/action.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<div class="title">权限组数据列表</div>
  		<c:if test="${ allGroups != null }" var="res">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%"><input type="checkbox" onclick="checkboxSelect(this, 'gid')"></td>
  					<td width="40%">权限名称</td>
  					<td width="40%">权限组</td>
  					<td width="45%">权限路径</td>
  					<td>操作</td>
  				</tr>
				<c:forEach items="${ allActions }" var="action">
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td><input type="checkbox" name="actid" id="actid" value="${ action.actid }"></td>
					<td>
						<input type="text" name="title-${ action.actid }" id="title-${ action.actid }" value="${ action.title }">
						<span id="title-${ action.actid }Msg"></span>
					</td>
					
					<td>
						<select name="gid-${ action.actid }" id="gid-${ action.actid }">
							<c:forEach items="${ allGroups }" var="groups">
								<option value="${ groups.gid }" ${ action.groups.gid == groups.gid ? "selected" : "" }>${ groups.title }</option>
							</c:forEach>
						</select>
							<span id="gid-${ action.actid }Msg"></span>
					</td>
					
					<td>
						<input type="text" name="url-${ action.actid }" id="url-${ action.actid }" value="${ action.url }" size="60">
						<span id="url-${ action.actid }Msg"></span>
					</td>
					
					<td><input type="button" value="修改" onclick="updateData(${ action.actid })"></td>
				</tr>
				</c:forEach>
  			</table>
  			<input type="button" value="批量删除" onclick="deleteAll('<%=deleteUrl%>', 'ids', 'actid')">
			<form action="<%= updateUrl %>" method="post" id="actionForm">
				<input type="hidden" name="action.title" id="action.title">
				<input type="hidden" name="action.actid" id="action.actid">
				<input type="hidden" name="action.url" id="action.url">
				<input type="hidden" name="action.groups.gid" id="action.groups.gid">
			</form>
  		</c:if>
  	</div>
  </body>
</html>
