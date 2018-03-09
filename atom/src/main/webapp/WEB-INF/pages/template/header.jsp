<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nantian.atom.generated.po.rolem.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextPath = basePath + "resources/";
	
	String name = ((User)(request.getSession().getAttribute("user"))).getUserName();
%>        
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script>
	var webPath = '<%=basePath%>';
	var userName = '<%=name %>';
</script>
<style>
	#_title{background:#fff !important;height:50px;}
	#_title li{list-style:none;}
	#_title ._user li{float:right;line-height:50px;color:#428BCA;}
</style>
<script>
	var logoutUrl = webPath + "logout.html";
    var changePasswordUrl=webPath+"rolem/user/_changePassword.html";

	$(function(){
		
		$('#_userName').text(userName);
		
		$('#_logout').click(function(){
			if(confirm('确定注销')){
				location.href = logoutUrl;
			}
		});
		
		$('#_change_pwd').click(function(){
			$('#first-input').val('');
			$('#second-input').val('');
			//弹出修改密码区域
			layer.open({
				type: 1,
				move:true,
				title:'修改密码',
				//skin: 'layui-layer-rim', //加上边框
				area: ['250px', "210px"], //宽高
				offset: '80px',
				shade: [0.8, '#393D49'],
				zIndex : 3,
				content: $('#change-password-win')
			});
		});
		//修改密码提交
		$('#change-password-submit').click(function(){
            //判断两次密码是否一致
			var firstInput=$('#first-input').val();
			var secondInput=$('#second-input').val();
			if(firstInput!=secondInput){
				alert('两次密码输入不一致，请重新输入');
				$('#first-input').val('');
				$('#second-input').val('');
			}else if(firstInput==""||secondInput==""){
				alert('密码不能为空，请重新输入');
				$('#first-input').val('');
				$('#second-input').val('');
			}else{
				if(!confirm('确定修改密码？')){
					return false;
				}else{
					//发送密码修改请求
					goAjax(changePasswordUrl,{recordId:secondInput},function(data){
						if(data.flag!=0){
							alert('修改密码失败！请重新修改');
							return false;
						}else{
							alert('修改密码成功！');
							//跳转至登陆页面重新登陆
							window.location.href = webPath+'login.html' ;
						}
					});
				}
			}
		});
	});
</script>
</head>
<body>
	
	<div id="_title" class="row-ant _head">
		 <div class="col-sm-7-ant" id="logo-has">
		 	<img src="<%=basePath %>re/img/logo.png" height="50">
		 </div>
		 <div class="col-sm-1-ant" id="sys-logo">
		 	&nbsp;
		 </div>
		 <div class="col-sm-4-ant _user">
            <li>
	            <span id="_userName">徐瑞</span>，欢迎您&nbsp;&nbsp;
	            <button id="_logout" type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-off"></span> 注销</button>&nbsp;&nbsp;
	            <button id="_change_pwd" type="button" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-lock"></span> 修改密码</button>
            </li>
		 </div>
	</div>
    <!--修改密码框 -->
		<div id="change-password-win" class="none" style="width: 230px">
			 <input type='password' class="form-control" id="first-input" style="margin-top:20px; margin-left:10px  " placeholder='请输入密码' vtype="required"  vmsg="密码不能为空"/>
			 <input type='password' class="form-control" id='second-input' style="margin-top:20px; margin-left:10px" placeholder='再次输入密码' vtype="required"  vmsg="密码不能为空"/>
			 <center>
			 <button id="change-password-submit" type="button" class="btn btn-primary" style="width:230px !important;margin-top:20px; margin-left:10px">确定</button>
			 </center>
		</div>
		<!--修改密码框end -->
</body>
</html>