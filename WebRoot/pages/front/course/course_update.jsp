<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updateUrl = basePath + "pages/front/course/CourseServletFront/update";
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
    <script type="text/javascript" src="js/course.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/WdatePicker.js"></script> 
  </head>
  
  <body>
  	<div id = "mainDiv">
		<jsp:include page="/pages/menu-top.jsp"/>
		<div class="nav-down">
			<jsp:include page="/pages/menu-bar.jsp"/>
		    <div class="rightcon">
		    	<div class="right_con" style="padding-left: 50px">
		        	<form action="<%=updateUrl%>" method="post" enctype="multipart/form-data" onsubmit="return validateUpdate()">
		        		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#f2f2f2" width="90%">
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td colspan="4"><span class="title">编辑课程信息</span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td>课程名称：</td>
			  					<td><input type="text" name="course.cname" id="course.cname" class="init" onblur="validateCname()" value="${ course.cname }"></td>
			  					<td><span id="course.cnameMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td>开始日期：</td>
			  					<td><input type="text" name="course.begin" id="course.begin" class="init" readonly onclick="WdatePicker()" value="${ course.begin }"></td>
			  					<td><span id="course.beginMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td>结束日期：</td>
			  					<td><input type="text" name="course.end" id="course.end" class="init" readonly onclick="WdatePicker()" value="${ course.end }"></td>
			  					<td><span id="course.endMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td>培训天数：</td>
			  					<td><input type="text" name="course.total" id="course.total" class="init" onblur="validateTotal()" value="${ course.total }" readonly></td>
			  					<td><span id="course.totalMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td>课程状态：</td>
			  					<td>
			  						<input type="radio" name="course.status" id="course.status" class="init" onblur="validateTotal()" value="1" ${ course.status == 1 ? "checked" : ""}>正常
			  						<input type="radio" name="course.status" id="course.status" class="init" onblur="validateTotal()" value="0" ${ course.status == 0 ? "checked" : ""}>暂停
			  					</td>
			  					<td><span id="course.statusMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td>课程图片：</td>
			  					<td><input type="file" name="pic" id="pic" class="init" onchange="preview(this)"></td>
			  					<td><span id="picMsg"></span></td>
			  					<td rowspan="5"><div id="preview"><img class="myimg" src="upload/course/${ course.photo }"></div></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td>课程简介：</td>
			  					<td><input type="text" name="course.note" id="course.note" class="init" onblur="validateNote()" value="${ course.note }"></td>
			  					<td><span id="course.noteMsg"></span></td>
			  				</tr>
			  				<tr onmouseover="changeColor(this, 'white')" onmouseout="changeColor(this, '#f2f2f2')">
			  					<td colspan="4">
			  						<input type="hidden" name="course.photo" id="course.photo" value=${ course.photo }>
			  						<input type="hidden" name="course.cid" id="course.cid" value=${ course.cid }>
									<input type="submit" value="修改">
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
