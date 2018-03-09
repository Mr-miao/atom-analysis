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
<style>
	#_nav_content{background:#424F63;width:100%;height:100%;color:#fff;padding:0px;margin:0px;text-indent:15px;overflow:hidden;font-size:12px;}
	#_nav_content ul, #_nav_content li{list-style:none;padding:0px;margin:0px;}
	#_nav_content li{padding:10px 0px;margin-bottom:1px;}
	#_nav_content li:hover{background:#353F4F;color:#4BC5A7;cursor:pointer;}
	#_nav_content ._menu_current{background:#353F4F;color:#4BC5A7;cursor:pointer;}
	#_nav_content ._menu_nav{background:transparent url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center;}
	#_nav_content ._menu_nav:hover{background:transparent url(<%=basePath%>re/img/plus.png) no-repeat 93% center;}
	#_nav_content ._children{display:none;}
	#zhankai-div{position:fixed;top:100px;display:none;z-index:13}
	#shousuo{text-align:left;height:40px;}
</style>
<script>
	var getMenuUrl = webPath + "_menu/findAllMenu.html";
	
	$(function(){
		//设置导航菜单高度
		$('#_nav_content').css({"height":$(document).height()-$('#_title').height()});
		//拉取导航菜单
		goAjax(getMenuUrl,{},function(data){
			if(data.flag=="0"){
				var moduleList = data.moduleList;
				var transList = data.transList;
				moduleList = transAppendModule(moduleList,transList);
				var arr = findRootNodes(moduleList);
				var dest = findChildren(arr,moduleList);
				
				var str = genEle(dest);
				var container = '<div></div>';
				var div = $(container).html(str).get(0);
				var allEle = $(div).find('ul');
				var rootEle = genRootEle(allEle);
				
			}else{
				alert("加载导航菜单失败");
				return;
			}
			//将元素追加到收缩按钮后
			$('#shousuo').after(genChildEle(rootEle,allEle).get(0));
			
			//默认展开选中的导航菜单
			var curUrl = window.location.href;
			$.each($('._menu_leaf'),function(i,ele){
				if(curUrl.indexOf($(ele).attr('url'))>0){
					$(ele).parent().slideDown('normal');
					$(ele).parent().parent().slideDown('normal');
					$(ele).addClass('_menu_current');
					$(ele).parent().prev().css('background','url(<%=basePath%>re/img/open.png) no-repeat 93% center');
					$(ele).parent().parent().prev().css('background','url(<%=basePath%>re/img/open.png) no-repeat 93% center');
				}
			});
			
			$('._menu_nav').click(function(){
				if($(this).parent().prev()[0].getAttribute("class") != "_menu_nav"){
					$(this).siblings().children("._children").css("display","none");
					$(this).siblings().children("._menu_nav").css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
					$(this).siblings("._menu_nav").css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
					if($(this).next("._children").css("display") != "block"){
						
						$(this).css("background","url(<%=basePath%>re/img/open.png) no-repeat 93% center");
						$(this).siblings("._children").not(".selected").slideUp(150);
						$(this).siblings("._menu_nav").not(".selected").css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
						$(this).next("._children").addClass("selected").css("display","block");
					}else{
						$(this).next("._children").addClass("selected").css("display","none");
						$(this).css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
						$(this).siblings("._menu_nav").not(".selected").css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
					}
					$(this).next("._children").removeClass("selected");
				}else{
					if($(this).next("._children").css("display") != "block"){
						
						$(this).css("background","url(<%=basePath%>re/img/open.png) no-repeat 93% center");
						$(this).siblings("._children").not(".selected").slideUp(150);
						$(this).siblings("._menu_nav").not(".selected").css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
						$(this).next("._children").addClass("selected").css("display","block");
					}else{
						$(this).next("._children").addClass("selected").css("display","none");
						$(this).css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
						$(this).siblings("._menu_nav").not(".selected").css("background","url(<%=basePath%>re/img/plus-white.png) no-repeat 93% center");
					}
					$(this).next("._children").removeClass("selected");
				}
			});
			$('._menu_leaf').click(function(){
				$('._menu_leaf').removeClass('_menu_current');
				$(this).addClass('_menu_current');
				if($(this).attr('url')!=undefined&&$(this).attr('url')!=''&&$(this).attr('url')!=null&&$(this).attr('url')!='null'){
					window.location.href = webPath+$(this).attr('url');
				}
			});
			 
			$('._children').each(function(index, ele){
				var paddingLeft = $(this).prev().css('text-indent');
				var paddingLeftValue = paddingLeft.substring(0, paddingLeft.indexOf('px'));
				var childrenValue = (parseInt(paddingLeftValue)+18)+"px";
				$(this).css('text-indent', childrenValue);
			});
		}); 
		
		$("#shousuo").click(function(){
			$('#_nav_content').fadeOut();	
			$('.right').animate({width:"100%"});
			$("#zhankai-div").show();
		});
		$("#zhankai").click(function(){
			$('#_nav_content').fadeIn();	
			$('.right').animate({width:"83%"});	
			$("#zhankai-div").hide();	
		});
		
	});

var transAppendModule = function(moduleList,transList){
	$.each(moduleList,function(i,module){
		module.children = [];
		$.each(transList,function(j,trans){
			if(module.id==trans.moduleId){
				module.children.push(trans);
			}
		});
	});
	return moduleList;
}

//生成根节点
var findRootNodes = function(moduleList){
	var arr= [];
	$.each(moduleList,function(i,json){
		if(json.modulePid==0){
			arr.push(json);
		}
	});
	return arr;
}

//根节点下面添加children
var findChildren = function(fatherList,moduleList){
	var arr = [];
	$.each(fatherList,function(i,father){
		$.each(moduleList,function(j,child){
			if(father.id==child.modulePid){
				father.children.push(child);
				arr.push(child);
			}
		});
	});
	$.each(arr,function(k,json){
		if((json.children!=undefined)){
			findChildren(arr,moduleList);
		}
	});
	return fatherList;
}

var dom = '';

//将节点转换为元素
var genEle = function(nodeList){
	dom += jsonarrToUL(nodeList)
	$.each(nodeList,function(i,node){
		if((node.children!=undefined)&&(node.children.length>0)){
			genEle(node.children);
		}
	});
	return dom;
}

//将json转换为UL
var jsonarrToUL = function(jsonarr){
	var box = '';
	if(jsonarr!=undefined&&jsonarr.length>0){
		if((jsonarr[0].modulePid!=undefined)&&(jsonarr[0].modulePid==0)){
			box = '<ul pid="'+(jsonarr[0].modulePid)+'">';
		}else{
			box = '<ul pid="'+(jsonarr[0].modulePid==undefined?jsonarr[0].moduleId:jsonarr[0].modulePid)+'" class="_children">';
		}
		$.each(jsonarr,function(i,json){
			if((json.children==undefined)||(json.children.length==0)){
				box += '<li pid="'+json.id+'" class="_menu_leaf" url="'+json.trnUrl+'"><span class="'+json.modIcon+'"></span> '+(json.modCnName==undefined?json.serCnName:json.modCnName)+'</li>';
			}else{
				box += '<li pid="'+json.id+'" class="_menu_nav"><span class="'+json.modIcon+'"></span> '+(json.modCnName==undefined?json.serCnName:json.modCnName)+'</li>';
			}
		});
		return box + '</ul>';
	}
	return box;
}

//生成根元素
var genRootEle = function(arr){
	var root = [];
	$.each(arr,function(i,ul){
		if($(ul).attr('pid')==0){
			root.push(ul);
		}
	});
	return $(root);
}

//在根元素后追加子元素
var genChildEle = function(rootEle,allEle){
	var temp;
	$.each($(rootEle).children(),function(i,li){
		$.each(allEle,function(j,ul){
			if($(ul).attr('pid')==$(li).attr('pid')){
				$(li).after($(ul));
				allEle.splice(j,1);
				temp = $(ul);
			}
		});
		if(allEle.length>0){
			genChildEle(temp,allEle);
		}
	});
	return rootEle;
}

</script>
</head>
<body id="left_body">
	<div id="zhankai-div">
		<img id="zhankai" src="<%=basePath %>re/img/shousuo3.png">
	</div>

	<div id="_nav_content">
		<li id="shousuo"><label class="col-sm-9" style="color:#ccc;padding:0;font-family:Monospace;">柜外清管理平台</label><span class="col-sm-3 glyphicon glyphicon-chevron-left"></span> &nbsp;&nbsp;</li>
	</div>	
</body>
</html>