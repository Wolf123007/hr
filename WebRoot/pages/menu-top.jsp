<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String logoutUrl = basePath + "AdminLogin/logout";
%>
<span>个人后台管理系统</span>
<div class="nav-top">
	<div class="nav-topright">
		<p>上次登陆时间：${ fadmin.lastdate }   登陆IP：${ fadmin.ip }</p>
	    <span>您好：${ fadmin.aid }</span>
	    <span><a href="<%=logoutUrl%>">注销</a></span>
	</div>
</div>
