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
    <title>柜外清管理平台</title>
    
    <style>
    	
    </style>

	<!-- 定义基准路径的全局变量 -->
	<script>
		var webPath = '<%=basePath%>';
		
		
	</script>
	<!-- 必须引入的js，详情请见util-required.js -->
    <script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required-less.js"></script>
    <script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required.js"></script>
    <!-- style声明，仅仅只是对当前页面的样式设置，否则请使用公共style表 -->
    <style>
	</style>
  </head>
<body onkeydown="keyLogin();">
	<!-- <div id="login-box">
		<div class="">
			<h1>柜外清管理后台</h1>
		</div>
		<div>
			<form role="form">
			  <div class="form-group">
			    <input type="text" class="form-control input-lg" id="userName" placeholder="您的登录名">
			  </div>
			  <div class="form-group">
			    <input type="password" class="form-control input-lg" id="pwd" placeholder="您的密码">
			  </div>
			  <button id="login" type="button" class="btn btn-primary btn-block btn-lg">登&nbsp;&nbsp;录</button>
			</form>
		</div>
	</div> -->
	
	
	<div class="container-fluid">
		<div class="row" style="margin-top:80px;">
			<div class="col-sm-2">&nbsp;</div>
			<div class="col-sm-10"><h1>柜外清管理平台</h1></div>
		</div>
		<div class="row" style="background-color:#565656;height:280px;">
			<div class="col-sm-2">&nbsp;</div>
			<div class="col-sm-5"><img src="<%=basePath%>re/img/11.png" height="280"/></div>
			<div class="col-sm-3" style="height:280px;">
				<div class="panel panel-default" style="margin-top:40px;padding-top:10px;">
				  <div class="panel-body">
				    <div class="input-group">
					  <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-user"> </span></span>
					  <input id="userName" type="text" class="form-control" placeholder="请输入您的登录名" >
					</div>
					<div class="input-group" style="margin-top:18px;margin-bottom:18px;">
					  <span class="input-group-addon" id="basic-addon2"><span class="glyphicon glyphicon-asterisk"> </span></span>
					  <input id="pwd" type="password" class="form-control" placeholder="请输入您的密码" >
					</div>
					<input id="login" type="button" class="btn btn-primary btn-block" value="确定" />
				  </div>
				</div>
			
				
			</div>
			<div class="col-sm-2">&nbsp;</div>
		</div>
	</div>
	




</body>

<script>
	$(function(){
		$('#login').click(function(){
			login();
		});
		
		$("#userName").focus();
	});
	function keyLogin(){
		
		 if (event.keyCode==13)  //回车键的键值为13
			 login(); //调用登录按钮的登录事件
		}
  function login(){
	  var userL = $("#userName").val();
	  var userP = $("#pwd").val();
	  var url = webPath+'login/loginValid.html';
	  goAjax(url,{userLoad:userL,userPwd:userP},function(data){
		  if(data.flag=='0'){
			 window.location.href = webPath+'main.html' ;
		  }else{
		     alert("用户名或密码错误");
		  }
	  });
  }
</script>
</html>