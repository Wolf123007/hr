<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updatesalUrl = basePath + "pages/front/employee/EmployeeServletFront/updateDept";
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
		        	<h3>雇员职位变更</h3>
		        	<form action="<%=updatesalUrl%>" method="post" onsubmit="return validateUpdateDept()">
		        		<c:if test="${ employee != null }" var="res">
		        			<div class="form_add">员工姓名：${ employee.ename }</div>
		        			<div class="form_add">原部门：${ employee.dname }</div>
		        			<div class="form_add">原职位：${ employee.job }</div>
		        			<div><h3>现有信息变更</h3></div>
		        			<div class="form_add">
		        				<span class="list">现部门：</span>
		        				<span class="list">
		        					<select class="list" id="employee.dept.did" name="employee.dept.did">
										<c:if test="${ allDepts != null }" var="res">
											<c:forEach items="${ allDepts }" var="dept">
												<option value="${ dept.did }" ${ dept.did == employee.dept.did ? "selected" : "" }>${ dept.dname }</option>
											</c:forEach>
										</c:if>
									</select>
								</span>
							</div>
							<div class="form_add">
		        				<span class="list">现职位：</span>
		        				<span class="list">
		        					<select class="list" id="employee.jobs.jid" name="employee.jobs.jid">
										<c:if test="${ allJobs != null }" var="res">
											<c:forEach items="${ allJobs }" var="jobs">
												<option value="${ jobs.jid }" ${ jobs.jid == employee.jobs.jid ? "selected" : "" }>${ jobs.title }</option>
											</c:forEach>
										</c:if>
									</select>
								</span>
							</div>
							<div class="form_add">
								<span class="list">变更原因：</span>
								<span class="list"><input name="work.reason" id="work.reason" type="text" class="init" onblur="validateReason2()"/></span>
								<span id="work.reasonMsg"></span>
							</div>
							<div class="form_add">
								<span class="list">备注：</span>
								<span class="list"><input name="work.note" id="work.note" type="text" class="init" onblur="validateNote2()"/></span>
								<span id="work.noteMsg"></span>
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
