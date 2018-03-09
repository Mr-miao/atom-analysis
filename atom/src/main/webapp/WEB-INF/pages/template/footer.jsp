<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextPath = basePath + "resources/";
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script>
	var webPath = '<%=basePath%>';
</script>
<style type="text/css">
  footer {
    background: #fff;
    padding: 12px;
    color: #7A7676;
    font-size: 12px;
    position: static;
    bottom: 0;
    width: 100%;
    border-top: 1px solid #eff0f4;
  }
  footer.sticky-footer {
    position: fixed;
    bottom: 0;
    width: 100%;
    z-index:13;
  }
   
</style>
</head>
<body>
	 <footer class="sticky-footer">
		柜外清管理平台
	</footer>
</body>
</html>