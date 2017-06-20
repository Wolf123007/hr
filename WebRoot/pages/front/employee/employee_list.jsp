<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/front/employee/EmployeeServletFront/updatePre";
String updateStatusUrl = basePath + "pages/front/employee/EmployeeServletFront/updateStatus?p=p";
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
		<jsp:include page="/pages/menu-top.jsp"/>
		<div class="nav-down">
			<jsp:include page="/pages/menu-bar.jsp"/>
		    <div class="rightcon">
		    	<div class="right_con" style="padding-left: 50px">
		    		<div><span class="title">雇员数据列表</span></div>
		    		<div id="splitSearchDiv">
						<jsp:include page="/pages/split_page_plugin_search.jsp"/>
						<br>
					</div>
		        	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="80%">
		  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
		  					<td><input type="checkbox" onclick="checkboxSelect(this, 'eid')"></td>
		  					<td><strong>编号</strong></td>
		  					<td><strong>姓名</strong></td>
		  					<td><strong>性别</strong></td>
		  					<td><strong>学历</strong></td>
		  					<td><strong>部门 </strong></td>
		  					<td><strong>职位</strong></td>
		  					<td><strong>工资</strong></td>
		  					<td><strong>入职时间</strong></td>
		  					<td><strong>状态</strong></td>
		  					<td><strong>操作</strong></td>
		  				</tr>
		  				<c:if test="${ allEmployees != null }" var="res">
		  					<c:forEach items="${ allEmployees }" var="employee">
		  						<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
				  					<td><input type="checkbox" name="eid" id="eid" value="${ employee.eid }"></td>
				  					<td>${ employee.eid }</td>
				  					<td>${ employee.ename }</td>
				  					<td>${ employee.sex}</td>
				  					<td>${ employee.edu }</td>
				  					<td>${ employee.dname }</td>
				  					<td>${ employee.job }</td>
				  					<td>${ employee.sal }</td>
				  					<td>${ employee.indate }</td>
				  					<td>
				  						<c:if test="${ employee.status == 1 }">在职</c:if>
				  						<c:if test="${ employee.status == 0 }">离职</c:if>
				  					</td>
				  					<td><a href="<%=updateUrl%>?employee.eid=${ employee.eid }">修改</a></td>
				  				</tr>
		  					</c:forEach>
		  				</c:if>
		  			</table>
		  			<c:if test="${ param.status != 0 }">
		  				<input type="button" value="离职" onclick="updateAll('<%=updateStatusUrl%>', 'ids', 'eid')">
		  			</c:if>
		  			<div id="splitBarDiv" style="float:right">
						<jsp:include page="/pages/split_page_plugin_bars.jsp"/>
					</div>
		        </div>
		    </div>
		</div>
  	</div>
  </body>
</html>
