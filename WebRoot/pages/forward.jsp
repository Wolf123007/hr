<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>
  </head>
  
  <body>
  	<script type="text/javascript">
  		window.alert("${ msg }");
  		window.location = "<%=basePath%>${ url }?${paramName == null ? "null" : paramName}=${paramValue == null ? "null" : paramValue }";
  	</script>
  </body>
</html>
