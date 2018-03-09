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
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=8"> -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    	<!-- 定义基准路径的全局变量 -->
    <script>
		var webPath = '<%=basePath%>';
	</script>
	<script src="<%=basePath %>re/libs/bootstrap/js/jquery.min.js"></script> 
	<script src="<%=basePath %>re/libs/bootstrap/js/bootstrap.min.js"></script> 
	<script src="<%=basePath %>re/libs/layer/layer.js"></script>
 	<script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required.js"></script>
    <!-- 标准的meta信息  end-->
    
    <!-- 标题 -->
    <title>角色管理</title>


    
    <!-- style声明，仅仅只是对当前页面的样式设置，否则请使用公共style表 -->
    <style>
    	 #auth-list span{
    		color:#444 !important;
    	}
    	textarea{
    		width:100%;
    		rows:2;
    		resize: none;
    	}
	</style>
    <script>
    	/*定义查的url-为了规范，在此处统一定义CURD的URL，便于查看和修改*/
    	var pagerUrl = webPath+"role/pagerRole.html";//分页查询
    	var queryByIdUrl = webPath + "role/queryRoleById.html";//根据主键查询，主要是查看详情，编辑时用到
    	var updateUrl = webPath + "role/updateRole.html";
    	var addUrl = webPath + "role/addRole.html";
    	var deleUrl = webPath + "role/deleRole.html";
    	var queryModuleUrl = webPath + "module/pagerModual.html";
    	var batchAddPower = webPath + "optRole/batchInsertOptRole.html";
    	var listOptRole = webPath + "optRole/listOptRole.html";
    	var genFuncMenuUrl = webPath + "_menu/genFuncMenu.html?trn=roleServiceImpl";
    	
    	
    	//自定义序列化对象的方法
   	 	Array.prototype.serializeObject = function (lName) {
            var o = {};
            $t = this;
            for (var i = 0; i < $t.length; i++) {
                for (var item in $t[i]) {
                    o[lName+'[' + i + '].' + item.toString()] = $t[i][item].toString();
                }
            }
            return o;
        };
    	
    	//添加trans节点
    	var addTransNodes = function(tree,trans){
    		var treeNodes = tree.transformToArray(tree.getNodes());
			for(var i=0;i<treeNodes.length;i++){
				for(var j=0;j<trans.length;j++){
	    			if(trans[j].moduleId==treeNodes[i].id){
	    				trans[j].modCnName = trans[j].serCnName;
	    				trans[j].trans_id = trans[j].id;
	    				tree.addNodes(treeNodes[i],trans[j]);
	    			}
	    		}
			}
    	}
    	
    	//添加opt节点
    	var addOptNodes = function(tree,opt){
    		var treeNodes = tree.transformToArray(tree.getNodes());
			for(var i=0;i<treeNodes.length;i++){
				for(var j=0;j<opt.length;j++){
	    			if(opt[j].optServerId==treeNodes[i].trans_id){
	    				opt[j].modCnName = opt[j].optName;
	    				opt[j].opt_id = opt[j].optId;
	    				tree.addNodes(treeNodes[i],opt[j]);
	    			}
	    		}
			}
    	}
    	
    	
    	//ztree配置
		var setting = {
   			check: {
   				enable: true
   			},
   			data: {
   				simpleData: {
   					enable:true,
   					idKey: "id",
   					pIdKey: "modulePid",
   					rootPId: 0
   				},
   				key: {
   					name: "modCnName"
   				}
   			},
   			callback:{
   				onClick:function(event,treeId,treeNode){
   				}
   			}
    	};
    	var zNodes ;
    	
    	
    	//动态加载表格
		//data-表格数据 
		//tbDom-要装载的表格id标签
		//reloadPagerLan-是否取消重载分页栏
		//如果reloadPagerLan为true时，必须设置ajaxUrl和paramUrl的值
		var showTbData = function(data, tbDom, reloadPagerLan, ajaxUrl, paramUrl){
	        if(data.data==null || data.data==''){
	        	var noRecord = '<tr>'+
	                        	'<th colspan="6" style="text-align:center;">暂无记录！</th>'+
	                            '</tr>';
	        	$("#tb-data tbody").html(noRecord);
	        }else{
	        	var htm = '';
	        	$("#tb-data tbody").html(htm);
	        	$(data.data).each(function(index, ele){
					htm = '<tr>'+
						//注意name="check-data"不能变
						'<td><input pRole="'+ele.rolePid+'" type="checkbox" name="check-data" value="'+notNull(ele.roleId)+'"></td>'+
				      	'<td>'+(++index)+'</td>'+
				      	'<td>'+notNull(ele.roleName)+'</td>'+
				      	'<td>'+notNull(ele.roleDesc)+'</td>'+
				      	//'<td>'+notNull(ele.branchName)+'</td>'+
				      //	'<td>'+notNull(ele.rolePname)+'</td>'+
				      	/* '<td>'+notNull(ele.roleBack3)+'</td>'+ */
	                    '</tr>';
					$("#tb-data").append(htm);
				});
	        	if(reloadPagerLan){
	        		//添加分页栏
					genPager(data.pager, ajaxUrl, paramUrl, null);
	        	}
	        	//onTdClick();//监听表格单击事件
	        }
		}
    	
    	$(function(){
    		//根据权限改变功能菜单
    		goAjax(genFuncMenuUrl,{},function(response){
    			genFuncMenu(response.data);
    			//启用默认框架的初始化方式
        		enableDefaultFramework('700px', '550px');
        		onUpdateBatchClick();//监听批量编辑按钮
        		//删除按钮
        		$('#delete').unbind('click');
        		$('#delete').click(function(){
        			//得到行记录的id值
        			var recordId = $("input[name='check-data']:checked").val();
        			var recordIds1 = $("input[name='check-data']:checked").length;
        			if(recordId==undefined||recordIds1<1){
        				alert("请先选择至少一个角色");
        				return false;
        			}
        			//获取有选中角色
        			var recordIds=[]
        			$("input[name='check-data']:checked").each(function(){
	        			  var roleId=$(this).val();
	        			  recordIds+=roleId+'#';
	        	    })
        			if (!confirm("确认要删除？")) {
        				return false;
        			}else{
        				//查找所有角色个数，若只有一个角色不能删除
            			goAjax(pagerUrl,{},function(data){
            				if(data.flag==0){
            					if(data.size==1){
            						alert('当前数据只存在一个角色，禁止删除!');
            					}else{
            						//发送删除请求
       								goAjax(deleUrl,{recordIds:recordIds},function(data1){
       	 								if(data1.flag==-1){
       	 									alert(data1.msg);
       	 									//刷新表数据
       	 									pagerList({});
       	 								}else if(data1.flag==0){
       	 									alert("删除数据成功！");
       	 									//刷新表数据
       	 									pagerList({});
       	 								}
       	 							  });
            					}
            				}
            			});
        			}
        		});
        		//详情按钮
        		$('#detail').unbind('click');
        		$('#detail').click(function(){
        			//设置为编辑
        			$('#win-btn').attr('method','detail');
        			var checkboxs = $("input[name='check-data']:checked").length;
        			var recordId = $("input[name='check-data']:checked").val();
        			if(recordId==undefined||checkboxs!=1){
        				alert("请选择一条记录");
        				return false;
        			}
        			//禁用
        			$('#edit-win input, #edit-win textarea, #edit-win select').attr('disabled','disabled');
        			$('#auth-list').css('pointer-events','none');
        			//权限分配layer中的数据
        			goAjax(queryByIdUrl,{'recordId':recordId},function(response){
        				if(response.flag==0){
        					//回显角色信息
        					$('#roleName').val(response.data.roleName);
        					$('#roleDesc').val(response.data.roleDesc);
        					$('#roleId').val(response.data.roleId);
        					$('#rolePid').val($("input[name='check-data']:checked").attr('prole'));
        					//$('#branchId option[value="'+response.data.branchId+'"').attr("selected",true);
        					//selectQuery(response.data.branchId,'#edit-win');
        					//查询模块（Module）、交易(Transation)、操作(Opt)生成树
        					goAjax(queryModuleUrl,{"pRole":$("input[name='check-data']:checked").attr('prole')},function(response){
        	    				if(response.flag==0){
        	    					var module = response.moduleList;
                 					var trans = response.transList;
                 					var opt = response.optList;
        	    					
        	        				zNodes = module;
        	        				//生成树
        	        		    	var tree = $.fn.zTree.init($("#auth-list"), setting, zNodes);
        	        				//给树增加trans和opt表中的节点
        	        				
        	        		    	addTransNodes(tree,trans);
        	        		    	
        	        		    	addOptNodes(tree,opt);
        	        		    	
        	        		    	//打开layer
        	            			openWindow('详情','710px','auto','#edit-win');
        	        		    	//回显opt_role中的数据
        	        		    	goAjax(listOptRole,{roleId:$('#roleId').val()},function(response){
        	        		    		if(response.flag==0){
        	        		    			var nodes = response.data;
        	        		    			if(nodes!=null&&nodes!=undefined){
        	        		    				for(var i=0;i<nodes.length;i++){
        		        		    				for(var j=0;j<tree.transformToArray(tree.getNodes()).length;j++){
        		        		    					if(tree.transformToArray(tree.getNodes())[j].opt_id==nodes[i].optId){
        	    	        		    					tree.checkNode(tree.transformToArray(tree.getNodes())[j], true, true);
        	    	        		    				}
        		        		    				}
        		        		    				
        		        		    			}
        	        		    			}
        	        		    		}else{
        	        		    			alert('权限数据查询失败');
        	        		    		}
        	        		    	});
        	    				}else{
        	    					alert('权限数据查询失败');
        	    				}
        	    				
        	    			});
        				}else{
        					alert('数据查询失败');
        				}
        			});
        		});
        		//新增按钮
        		$('#add').unbind('click');
        		$('#add').click(function(){
        			$('#edit-win input, #edit-win textarea, #edit-win select').removeAttr('disabled');
        			$('#auth-list').css('pointer-events','auto');
        			//设置为新增
        			$('#win-btn').attr('method','add');
        			//清空表单
        			resetForm('#edit-win');//参数为窗口的id
        			//清空机构下拉
        			//$('#branchId option[value=""').attr("selected",true);
        			$('.selectbox').text('');
     				//查询模块（Module）、交易(Transation)、操作(Opt)生成树
     				goAjax(queryModuleUrl,{},function(response){
         				if(response.flag==0){
         					var module = response.moduleList;
         					var trans = response.transList;
         					var opt = response.optList;
             				zNodes = module;
             				//生成树
             		    	var tree = $.fn.zTree.init($("#auth-list"), setting, zNodes);
             				//给树增加trans和opt表中的节点
             				
             		    	addTransNodes(tree,trans);
             		    	
             		    	addOptNodes(tree,opt);
         				}else{
         					alert('权限数据查询失败');
         				}
         				
         			});
     				//打开layer
        			openWindow('新增','710px','auto','#edit-win');
        		});
        		
        		//编辑按钮
        		$('#update').unbind('click');
        		$('#update').click(function(){
        			$('#edit-win input, #edit-win textarea, #edit-win select').removeAttr('disabled');
        			$('#auth-list').css('pointer-events','auto');
        			//设置为编辑
        			$('#win-btn').attr('method','update');
        			var checkboxs = $("input[name='check-data']:checked").length;
        			var recordId = $("input[name='check-data']:checked").val();
        			if(recordId==undefined||checkboxs!=1){
        				alert("请选择一条记录");
        				return false;
        			}
        			
        			//权限分配layer中的数据
        			goAjax(queryByIdUrl,{'recordId':recordId},function(response){
        				
        				if(response.flag==0){
        					//回显角色信息
        					$('#roleName').val(response.data.roleName);
        					$('#roleDesc').val(response.data.roleDesc);
        					$('#roleId').val(response.data.roleId);
        					
        					$('#rolePid').val($("input[name='check-data']:checked").attr('prole'));
        					//$('#branchId option[value="'+response.data.branchId+'"').attr("selected",true);
        					//selectQuery(response.data.branchId,'#edit-win');
        					//查询模块（Module）、交易(Transation)、操作(Opt)生成树
        					
        					goAjax(queryModuleUrl,{"pRole":$("input[name='check-data']:checked").attr('prole')},function(response){
        	    				if(response.flag==0){
        	    					
        	    					var module = response.moduleList;
        	    					
                 					var trans = response.transList;
                 					
                 					var opt = response.optList;
                 					
        	        				zNodes = module;
        	        				//生成树
        	        		    	var tree = $.fn.zTree.init($("#auth-list"), setting, zNodes);
        	        				//给树增加trans和opt表中的节点
        	        		    	addTransNodes(tree,trans);
        	        		    	addOptNodes(tree,opt);
        	        		    	//打开layer
        	            			openWindow('编辑','710px','auto','#edit-win');
        	        		    	//回显opt_role中的数据
        	        		    	goAjax(listOptRole,{roleId:$('#roleId').val()},function(response){
        	        		    		if(response.flag==0){
        	        		    			var nodes = response.data;
        	        		    			if(nodes!=null&&nodes!=undefined){
        	        		    				for(var i=0;i<nodes.length;i++){
        		        		    				for(var j=0;j<tree.transformToArray(tree.getNodes()).length;j++){
        		        		    					if(tree.transformToArray(tree.getNodes())[j].opt_id==nodes[i].optId){
        	    	        		    					tree.checkNode(tree.transformToArray(tree.getNodes())[j], true, true);
        	    	        		    				}
        		        		    				}
        		        		    				
        		        		    			}
        	        		    			}
        	        		    		}else{
        	        		    			alert('权限数据查询失败');
        	        		    		}
        	        		    	});
        	    				}else{
        	    					alert('权限数据查询失败');
        	    				}
        	    				
        	    			});
        				}else{
        					alert('数据查询失败');
        				}
        			});
        		});
        		
    		}); 
    		//生成高级查询的窗口
			// enableQueryAdvance(); 
    		
    		//新增、编辑提交按钮
    		$('#win-btn').click(function(){
    			var vflag = validateAnt('#edit-win');
    			if(!vflag){
    				return false;
    			}
    			var url = '';
    			var role = genReqData('#edit-win');
    			var branch = {};
    		//	role.branchId = $('#branchId').val();
    			
    			var tree = $.fn.zTree.getZTreeObj("auth-list");
    			var checkedNodes = tree.getCheckedNodes(true);
    			var optRoles = [];
    			for(var i=0;i<checkedNodes.length;i++){
	    			if(checkedNodes[i].opt_id!=undefined&&checkedNodes[i].opt_id!=null){
    					var opt_role = new Object();
    					opt_role.optId = checkedNodes[i].opt_id;
    					opt_role.roleId = 0;
    					optRoles.push(opt_role);
    				}
    			}
    			if(optRoles.length==0){
    				layer.tips('请选择要添加的权限','#auth-tips',{
    					  tips: [1, '#cc0000']
    				  });
    				return;
    			}
    			//新增
    			if($('#win-btn').attr('method')=='add'){
    				url = addUrl
    			}
    			//编辑
    			else if($('#win-btn').attr('method')=='update'){
    				url = updateUrl;
    			}
    			//详情
    			else if($('#win-btn').attr('method')=='detail'){
    				layer.closeAll();
    				return;
    			}
    			$.ajax({
    				url:url,
    	            data:$.param(optRoles.serializeObject('optRoles'))+'&'+$.param(role),    //手动把数据转换拼接{"optRoles":optRoles}
    	            type:'post',
    	            dataType:'json',
    	            traditional:true,    //这里必须设置
    	            beforeSend:function(){
    	    	    	index = openLoading();
    	    	    },
    	    	    complete:function(jqXHR){
    	    	    	closeLoading(index);
    	    	    },
    	    	    error : function(XMLHttpRequest, textStatus, errorThrown) {  
    	    			alert("后台通信异常！");    
    	    		},
    	            success:function(response){
    	            	if(response.flag==0){
        					alert('操作成功');
        					layer.closeAll();
        					//刷新表数据
        					pagerList({});
        				}else{
        					alert(response.msg);
        				}
    	            }
    			});
    		});
    		//新增、编辑窗的机构下拉
    		
		});
    </script>
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
	<div class="col-sm-2-ant"><c:import url="../template/leftmenu.jsp"/></div>
	
	<!-- 页面底部脚本区域 -->
	<div class="col-sm-10-ant" ><c:import url="/WEB-INF/pages/template/footer.jsp"/></div>
	
	<!-- 条件查询区域 -->
	<div class="col-sm-10-ant right" id="content_page">
		<div class="panel panel-default query-area">
		    <div class="panel-body">
		        <div class="form-inline">
		          <div class="form-group">
		            <label for="deviceNo">角色名称</label>
		            <input type="text" class="form-control" id="roleName-query">
		          </div>
				<!--   查询按钮  -->
		          <button type="button" id="query" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查&nbsp;询</button>
		        </div>
		    </div> 
		</div>
		<!-- 条件查询区域 end-->
		
		
		<!-- 数据显示区域 -->            
		<div class="panel panel-default data-area">
		  <div class="panel-body">
			<div class="btn-group menu-lan">
			  <button id="detail" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th"></span> 详情</button>
			</div>
			<!-- 数据表格 -->
		    <table id="tb-data" class="table table-bordered table-striped table-hover table-responsive">
		      <thead>
		      <tr>
		      	<th><input type="checkbox" id="checkAll" onclick="selectAll()"/></th>
		        <th>序号</th>
		        <th class="tb-sort" col="roleName">角色名</th>
		        <th class="tb-sort" col="roleDesc">角色描述</th>
		        <!-- <th class="tb-sort" col="branch.branchId">所属机构</th> -->
		      <!--   <th class="tb-sort" col="rolePid">上级角色</th> -->
		      </tr>
		      </thead>
		      <tbody>
		      </tbody>
		    </table>
		    <!-- 分页栏 -->
		    <div class="row-ant"><div class="col-sm-12-ant"><div class="pager-lan" id="kkpager"></div></div></div>
		  </div>
		</div>
		<!-- 数据显示区域 end-->   
		
		<!-- 增加、编辑时的窗口 -->
		<div id="edit-win" class="none" submitType="noFile">
			<input id="roleId" type="hidden" value="">
			<input id="rolePid" type="hidden" value="">
			<table class="table border-none">
			  <tr>
		        <th width="20%">角色名称<span>*</span></th>
		        <td colspan="3" width="30%">
		        	<input type="text" class="form-control" id="roleName" vtype="required" vmsg="角色名称不能为空" unique="true">
		        </td>
		      </tr>
		      <tr>
		      	<th width="20%">角色描述</th>
		        <td colspan="3" width="30%">
		        	<textarea class="form-control" id="roleDesc" vtype="" vmsg=""></textarea>
		        </td>
		      </tr>
		      <tr>
		      	<div id="auth-menu"> 
                   	<th colspan="4">权限选择<br>
                   		<div id="auth-tips" style="height:280px;overflow:auto"><ul id="auth-list" class="ztree"></ul></div>
                   	</th>
                   	
                </div>
              </tr>
		      <tr>
		        <td colspan="4"><button method="" id="win-btn" class="btn btn-primary btn-block">确认</button></td>
		      </tr>
		    </table>
		</div>
		<!-- 增加、编辑时的窗口 end-->
	</div>
</div>




</body>
</html>