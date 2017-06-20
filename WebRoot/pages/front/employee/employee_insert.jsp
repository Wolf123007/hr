<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/front/employee/EmployeeServletFront/insert";
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
    <script type="text/javascript" src="<%= basePath %>js/WdatePicker.js"></script> 
  </head>
  
  <body>
  	<div id = "mainDiv">
		<jsp:include page="/pages/menu-top.jsp"/>
		<div class="nav-down">
			<jsp:include page="/pages/menu-bar.jsp"/>
		    <div class="rightcon">
		    	<div class="right_con" style="padding-left: 50px">
		        	<form action="<%=insertUrl%>" method="post" enctype="multipart/form-data" onsubmit="return validateInsert()">
		        		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="100%">
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td colspan="3"><span class="title">增加员工资料</span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员编号：</strong></td>
			  					<td width="40%"><input type="text" name="employee.eid" id="employee.eid" class="init" onblur="validateEid()"></td>
			  					<td width="45%"><span id="employee.eidMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员姓名：</strong></td>
			  					<td width="40%"><input type="text" name="employee.ename" id="employee.ename" class="init" onblur="validateEname()"></td>
			  					<td width="45%"><span id="employee.enameMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员性别：</strong></td>
			  					<td width="40%">
			  						<input type="radio" name="employee.sex" id="employee.sex" class="init" value="男" checked>男
			  						<input type="radio" name="employee.sex" id="employee.sex" class="init" value="女">女
			  					</td>
			  					<td width="45%"><span id="employee.sexMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>身份证号：</strong></td>
			  					<td width="40%"><input type="text" name="employee.idcard" id="employee.idcard" class="init" onblur="validateIdcard()"></td>
			  					<td width="45%"><span id="employee.idcardMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员生日：</strong></td>
			  					<td width="40%"><input readonly type="text" name="employee.birthday" id="employee.birthday"" class="init" onclick="WdatePicker()" onblur="validateBirthday()"></td>
			  					<td width="45%"><span id="employee.birthdayMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>毕业院校：</strong></td>
			  					<td width="40%"><input type="text" name="employee.school" id="employee.school" class="init" onblur="validateSchool()"></td>
			  					<td width="45%"><span id="employee.schoolMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>所学专业：</strong></td>
			  					<td width="40%"><input type="text" name="employee.profession" id="employee.profession" class="init" onblur="validateProfession()"></td>
			  					<td width="45%"><span id="employee.professionMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>毕业日期：</strong></td>
			  					<td width="40%"><input readonly type="text" name="employee.grad" id="employee.grad"" class="init" onclick="WdatePicker()" onblur="validateGrad()"></td>
			  					<td width="45%"><span id="employee.gradMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员学历：</strong></td>
			  					<td width="40%">
									<select class="list" id="employee.edu" name="employee.edu">
										<option>专科以下</option>
										<option>专科</option>
										<option selected="selected">本科</option>
										<option>硕士</option>
										<option>博士</option>
									</select>
								</td>
			  					<td width="45%"><span id="employee.eduMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>所在部门：</strong></td>
			  					<td width="40%">
									<select class="list" id="employee.dept.did" name="employee.dept.did">
										<c:if test="${ allDepts != null }" var="res">
											<c:forEach items="${ allDepts }" var="dept">
												<option value="${ dept.did }">${ dept.dname }</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
			  					<td width="45%"><span id="employee.dept.didMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员职位：</strong></td>
			  					<td width="40%">
									<select class="list" id="employee.jobs.jid" name="employee.jobs.jid">
										<c:if test="${ allJobs != null }" var="res">
											<c:forEach items="${ allJobs }" var="jobs">
												<option value="${ jobs.jid }">${ jobs.title }</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
			  					<td width="45%"><span id="employee.jobs.jidMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>工资级别：</strong></td>
			  					<td width="40%">
									<select class="list" id="employee.level.levid" name="employee.level.levid">
										<c:if test="${ allLevels != null }" var="res">
											<c:forEach items="${ allLevels }" var="level">
												<option value="${ level.levid }">${ level.title }( 工资范围： ${ level.losal } ~ ${ level.hisal })</option>
											</c:forEach>
										</c:if>
									</select>
								</td>
			  					<td width="45%"><span id="employee.level.levidMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>税前工资：</strong></td>
			  					<td width="40%"><input type="text" name="employee.sal" id="employee.sal" class="init" onblur="validateSal()">元</td>
			  					<td width="45%"><span id="employee.salMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员状态：</strong></td>
			  					<td width="40%">
			  						<input type="radio" name="employee.status" id="employee.status" class="init" value="1" checked>在职
			  						<input type="radio" name="employee.status" id="employee.status" class="init" value="0">离职
			  					</td>
			  					<td width="45%"><span id="employee.statusMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>雇员照片：</strong></td>
			  					<td width="40%">
			  						<div id="preview"></div>
									<input type="file" onchange="preview(this)" name="pic" id="pic" class="init">
			  					</td>
			  					<td width="45%"><span id="picMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td width="15%"><strong>备注信息：</strong></td>
			  					<td width="40%"><textarea cols="30" rows="5" name="employee.note" id="employee.note" class="init" onblur="validateNote()"></textarea></td>
			  					<td width="45%"><span id="employee.noteMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td colspan="3">
									<input type="submit" value="增加">
									<input type="reset" value="重置">
								</td>
			  				</tr>
			  			</table>
		        	</form>
		        </div>
		    </div>
		</div>
  	</div>
  </body>
</html>
