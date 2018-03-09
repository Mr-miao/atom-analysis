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
<html lang="zh-CN">
	<head>	
		<!-- 标准的meta信息 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <!-- 标准的meta信息  end-->
    
    <!-- 标题 -->
    <title>欢迎</title>
	<script>
		var webPath = '<%=basePath%>';
	</script>
	<!-- 必须引入的js，详情请见util-required.js -->
    <script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required-less.js"></script>
	<style type="text/css">
		body{background:url('<%=basePath%>re/img/1.jpg') no-repeat;overflow:hidden; }
	</style>
	</head>
	<body>	
	<div class="container-ant">
		<div class="row-ant">
			<div class="col-sm-12-ant">
				<!-- 显示用户信息区域 -->
				<c:import url="/WEB-INF/pages/template/header.jsp"/>
			</div>
		</div>
	</div>
	<div class="container-ant">
		
		<!-- 显示导航菜单区域 -->
		<div class="col-sm-2-ant"><c:import url="/WEB-INF/pages/template/leftmenu.jsp"/></div>
		
		<!-- 页面底部脚本区域 -->
		<div class="col-sm-10-ant" ><c:import url="/WEB-INF/pages/template/footer.jsp"/></div>
		
		<!-- 条件查询区域 -->
		<div class="col-sm-10-ant" style="text-indent:4em;color:#424F63;font-size:14px;margin-top:48px;"><b>欢迎进入柜外清管理平台!</b></div>
	</div>
		
	</body>
	
	<script>
		
	</script>
</html>
