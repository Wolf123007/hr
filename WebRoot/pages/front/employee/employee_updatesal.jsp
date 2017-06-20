<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updatesalUrl = basePath + "pages/front/employee/EmployeeServletFront/updateSal";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>HR项目实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css" >
    <link type="text/css" rel="stylesheet" href="css/login.css" >
    <link type="text/css" rel="stylesheet" href="css/style.css" >
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/employee.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
  </head>
  
  <body>
  	<div id = "mainDiv">
		<jsp:include page="/pages/menu-top.jsp"/>
		<div class="nav-down">
			<jsp:include page="/pages/menu-bar.jsp"/>
		    <div class="rightcon">
		    	<div class="right_con">
		        	<p style="text-align:left; margin-top:50px">
		        	<h3>雇员待遇变更</h3>
		        	<form action="<%=updatesalUrl%>" method="post" onsubmit="return validateUpdateSal()">
		        		<c:if test="${ employee != null }" var="res">
		        			<div class="form_add">员工姓名：${ employee.ename }</div>
		        			<div class="form_add">原工资级别：${ employee.level.title }(${employee.level.losal}~${employee.level.hisal})</div>
		        			<div class="form_add">原工资：${ employee.sal }</div>
		        			<div><h3>现有信息变更</h3></div>
		        			<div class="form_add">
		        				<span class="list">现工资级别</span>
		        				<span class="list">
		        					<select class="list" id="employee.level.levid" name="employee.level.levid">
										<c:if test="${ allLevels != null }" var="res">
											<c:forEach items="${ allLevels }" var="level">
												<option value="${ level.levid }" ${ level.levid == employee.level.levid ? "selected" : "" }>${ level.title }( 工资范围： ${ level.losal } ~ ${ level.hisal })</option>
											</c:forEach>
										</c:if>
									</select>
								</span>
							</div>
							<div class="form_add">
								<span class="list">现工资：</span>
								<span class="list"><input name="employee.sal" id="employee.sal" type="text" class="init" onblur="validateSal()"/></span>
								<span id="employee.salMsg"></span>
							</div>
							<div class="form_add">
								<span class="list">变更原因：</span>
								<span class="list"><input name="salary.reason" id="salary.reason" type="text" class="init" onblur="validateReason()"/></span>
								<span id="salary.reasonMsg"></span>
							</div>
							<div class="form_add">
								<span class="list">备注：</span>
								<span class="list"><input name="salary.note" id="salary.note" type="text" class="init" onblur="validateNote()"/></span>
								<span id="salary.noteMsg"></span>
							</div>
							<div class="form_add">
								<span class="list">
									<input type="hidden" name="employee.eid" id="employee.eid" value="${ employee.eid }">
									<input type="submit" value="修改">
								</span>
								<span class="list">
									<input type="button" name="button1" id="button1" value="返回" onclick="history.go(-1)">
								</span>
							</div>
		        		</c:if>
		        	</form>
		        </div>
		    </div>
		</div>
  	</div>
  </body>
</html>
