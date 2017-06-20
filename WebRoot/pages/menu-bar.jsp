<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String updatePasswordUrl = basePath + "/pages/admin_change_password.jsp";
%>
<div class="leftmenu1">
        <div class="menu-oc"><img src="images/menu-all.png" /></div>
    	<ul>
        	<li>
            	<a class="a_list a_list1">全局设置</a>
                <div class="menu_list menu_list_first">
                	<a class="lista_first" href="<%=updatePasswordUrl%>">安全设置</a>
                  
              </div>
            </li>
            <li>
            	<a class="a_list a_list2">员工基本信息</a>
                <div class="menu_list">
                	<a href="<%=basePath%>pages/front/employee/EmployeeServletFront/insertPre">员工入职</a>
                    <a href="<%=basePath%>pages/front/employee/EmployeeServletFront/list">员工列表</a>
                    <a href="<%=basePath%>pages/front/employee/EmployeeServletFront/listStatus?status=1">在职列表</a>
                    <a href="<%=basePath%>pages/front/employee/EmployeeServletFront/listStatus?status=0">离职列表</a>
                    <a href="<%=basePath%>pages/front/employee/employee_updatedept_input.jsp">职位变更</a>
                    <a href="<%=basePath%>pages/front/employee/employee_updatesal_input.jsp">待遇变更</a>
              </div>
            </li>
            <li>
            	<a class="a_list a_list3">培训管理</a>
                <div class="menu_list">
              	<a href="<%=basePath%>pages/front/course/CourseServletFront/insertPre">发布课程</a>
               <a href="<%=basePath%>pages/front/course/CourseServletFront/list">课程管理</a>
                    
                    
                 
              </div>
            </li>
          
      </ul>
    </div>
    <div class="leftmenu2">
    	<div class="menu-oc1"><img src="images/menu-all.png" /></div>
       <ul>
        	<li>
            	<a class="j_a_list j_a_list1"></a>
                <div class="j_menu_list j_menu_list_first">
                	<span class="sp1"><i></i>全局设置</span>
                	  <a class="lista_first" href="<%=updatePasswordUrl%>">安全设置</a>
           
                </div>
            </li>
            <li>
            	<a class="j_a_list j_a_list2"></a>
                <div class="j_menu_list">
                	<span class="sp2"><i></i>员工基本信息</span>
                	<a href="<%=basePath%>pages/front/employee/EmployeeServletFront/insertPre">员工入职</a>
                    <a href="<%=basePath%>pages/front/employee/EmployeeServletFront/list">员工列表</a>
                    <a href="<%=basePath%>pages/front/employee/EmployeeServletFront/listStatus?status=1">在职列表</a>
                    <a href="<%=basePath%>pages/front/employee/EmployeeServletFront/listStatus?status=0">离职列表</a>
                    <a href="<%=basePath%>pages/front/employee/employee_updatedept_input.jsp">职位变更</a>
                    <a href="<%=basePath%>pages/front/employee/employee_updatesal_input.jsp">待遇变更</a>
                </div>
            </li>
            <li>
            	<a class="j_a_list j_a_list3"></a>
                <div class="j_menu_list">
                	<span class="sp3"><i></i>培训管理</span>
              	<a href="<%=basePath%>pages/front/course/CourseServletFront/insertPre">发布课程</a>
               <a href="<%=basePath%>pages/front/course/CourseServletFront/list">课程管理</a>
                </div>
            </li>
        </ul>
    </div>