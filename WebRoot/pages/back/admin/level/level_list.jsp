<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/back/admin/level/LevelServletBack/update";
String deleteUrl = basePath + "pages/back/admin/level/LevelServletBack/delete?p=p";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/level.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
  		<div class="title">职位数据列表</div>
  		<c:if test="${ allLevels != null }" var="res">
  			<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
  					<td width="15%"><input type="checkbox" onclick="checkboxSelect(this, 'levid')"></td>
  					<td width="45%">等级名称</td>
  					<td width="20%">最低工资</td>
  					<td width="20%">最高工资</td>
  					<td>操作</td>
  				</tr>
				<c:forEach items="${ allLevels }" var="level">
				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
					<td><input type="checkbox" name="levid" id="levid" value="${ level.levid }"></td>
					<td>
						<input type="text" name="title-${ level.levid }" id="title-${ level.levid }" value="${ level.title }">
						<span id="title-${ level.levid }Msg"></span>
					</td>
					<td>
						<input type="text" name="losal-${ level.levid }" id="losal-${ level.levid }" value="${ level.losal }">
						<span id="losal-${ level.levid }Msg"></span>
					</td>
					<td>
						<input type="text" name="hisal-${ level.levid }" id="hisal-${ level.levid }" value="${ level.hisal }">
						<span id="hisal-${ level.levid }Msg"></span>
					</td>
					<td><input type="button" value="修改" onclick="updateData(${ level.levid })"></td>
				</tr>
				</c:forEach>
  			</table>
  			<input type="button" value="批量删除" onclick="deleteAll('<%=deleteUrl%>', 'ids', 'levid')">
			<form action="<%= updateUrl %>" method="post" id="levelForm">
				<input type="hidden" name="level.title" id="level.title">
				<input type="hidden" name="level.levid" id="level.levid">
				<input type="hidden" name="level.losal" id="level.losal">
				<input type="hidden" name="level.hisal" id="level.hisal">
			</form>
  		</c:if>
  	</div>
  </body>
</html>
