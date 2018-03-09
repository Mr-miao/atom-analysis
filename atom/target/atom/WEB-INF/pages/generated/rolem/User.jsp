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
      	<!-- 定义基准路径的全局变量 -->
    <script>
		var webPath = '<%=basePath%>';
	</script>
	<script src="<%=basePath %>re/libs/bootstrap/js/jquery.min.js"></script> 
	<%-- <script src="<%=basePath %>re/libs/bootstrap/js/bootstrap.min.js"></script>  --%>
	<script src="<%=basePath %>re/libs/layer/layer.js"></script>
	<script src="<%=basePath %>re/libs/searchableSelect/jquery.searchableSelect.js"></script>
	<link href="<%=basePath %>re/libs/searchableSelect/jquery.searchableSelect.css" rel="stylesheet">
 	<script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required.js"></script>
    <!-- 标准的meta信息  end-->
    
    <!-- 标题 -->
    <title>用户管理</title>

    
    <!-- style声明，仅仅只是对当前页面的样式设置，否则请使用公共style表 -->
    <style>
    	.hideLoadPassword{
    	display:none;
    	}
    	#tb-allacate-data td{max-width:300px;min-width:50px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;}
    	
	</style>
	
    <script>
    	var pagerUrl = webPath+"rolem/user/_pager.html";
    	var queryByIdUrl = webPath + "rolem/user/_findById.html";
    	var queryByWhereUrl =webPath+"rolem/user/_pager.html";
    	var updateUrl = webPath + "rolem/user/_update.html";
    	var updateBatchUrl = webPath + "rolem/user/_updateBatch.html";
    	var addUrl = webPath + "rolem/user/_add.html";
    	var deleUrl = webPath + "rolem/user/_delete.html";
    	var excelModuleUrl = webPath + "rolem/user/_excelModule.html";
    	var exportExcelUrl = webPath + "rolem/user/_excelExport.html";
    	var importExcelUrl = webPath + "rolem/user/_excelImport.html";
    	var resetPasswordUrl = webPath + "rolem/user/_resetPassword.html";
    	var queryUserRoleByUserIdUrl=webPath + "userRole/queryUserRoleById.html";
    	var queryRolesUrl=webPath+"role/pagerRole.html";
    	var allocateUserRoleUrl=webPath+"userRole/allocateUserRole.html";
    	var deleteUserRoleUrl=webPath+"userRole/deleteUserRole.html";
    	var genFuncMenuUrl = webPath + "_menu/genFuncMenu.html?trn=userServiceImpl";
    	var findBranchUrl = webPath+"rolem/branch/_findAll.html";
    
    	var tbJsonItems = null;
    	
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
        
		var showTbData = function(data, tbDom, reloadPagerLan, ajaxUrl, paramUrl){
	        if(data.data==null || data.data==''){
	        	var noRecord = '<tr>'+
	                        	'<th colspan="8" style="text-align:center;">暂无记录！</th>'+
	                            '</tr>';
	        	$("#tb-data tbody").html(noRecord);
	        	$('#update-batch').unbind('click');//取消批量编辑按钮的单击事件
	        }else{
	        	tbJsonItems = data.data;
	        	var htm = '';
	        	$("#tb-data tbody").html(htm);
	        	$(data.data).each(function(index, ele){
	        		//查找该用户对应的角色关系
	        		 var userRole="";
	        		var recordId=ele.id;
	        		goAjax(queryUserRoleByUserIdUrl,{recordId:recordId},function(data){
	        			
	        			$(data.data).each(function(index, ele){
	        				var roleName=ele.roleName;
	        				
	        				userRole=userRole+roleName+",";
	        			})
	        			userRole=userRole.substring(0, userRole.length-1); 
	        			
	        			htm = '<tr>'+
						'<td><input type="checkbox" name="check-data" value="'+notNull(ele.id)+'"></td>'+
						'<td>'+notNull(ele["userLoad"])+'</td>'+
					    '<td>'+notNull(ele["userName"])+'</td>'+
					    '<td>'+notNull(ele["userPhone"])+'</td>'+
					    '<td>'+notNull(ele["userCall"])+'</td>'+
					    '<td>'+notNull(ele["userEmail"])+'</td>'+
					    '<td>'+notNull(ele["branchName"])+'</td>'+
					    '<td>'+userRole+'</td>'+
	
	                    '</tr>';
	        			$("#tb-data").append(htm);
	        	  });	
				});
	        	if(reloadPagerLan){
	        		//添加分页栏
					genPager(data.pager, ajaxUrl, paramUrl, null);
	        	}
	        }
		}
	  	//获取角色分页查询
	  	var showRoleData = function(data, tbDom, reloadPagerLan, ajaxUrl, paramUrl){
	        if(data.data==null || data.data==''){
	        	var noRecord = '<tr>'+
	                        	'<th colspan="3" style="text-align:center;">暂无记录！</th>'+
	                            '</tr>';
	        	$("#tb-allacate-data tbody").html(noRecord);
	        }else{
	        	var htm = '';
	        	$("#tb-allacate-data tbody").html(htm);
	        	$(data.data).each(function(index, ele){
	        		htm = '<tr>'+
					'<td><input type="checkbox" name="check-allocate-data" value="'+notNull(ele.roleId)+'" roleName="'+notNull(ele.roleName)+'"></td>'+
				    '<td>'+notNull(ele["roleName"])+'</td>'+
				    '<td>'+notNull(ele["roleDesc"])+'</td>'+
                    '</tr>';
        			$("#tb-allacate-data").append(htm);
				});
	        	if(reloadPagerLan){
	        		//添加分页栏
					genPager(data.pager, ajaxUrl, paramUrl,tbDom,'kkpager-role',null);
	        	}
	        	//onTdClick();//监听表格单击事件
	        }
		}
		//用户权限分配弹框的全选和取消
		function chooseAll() {
			//#checkedAll是全选的那个checkbox
			if ($("#checkAll-allacate").prop("checked")) {
			//：checkbox  是选中了所有的<input> type为 checkbox的对象
				$(":checkbox").prop("checked", true);
			} else {
			    $(":checkbox").prop("checked", false);
			}
		}
    	$(function(){
    		//根据权限改变功能菜单
    		goAjax(genFuncMenuUrl,{},function(response){
    			genFuncMenu(response.data);
    			//启用默认框架的初始化方式
        		enableDefaultFramework('700px', '400px');
        		//将update的id的属性修改为updateNew
    			$('#update').attr('id','updateNew');
        		//解绑详情按钮
        		$('#detail').attr('id','detailNew');
        		//重置密码按钮
        		$('#changePassword').click(function(){
        			//得到行记录的id值
        			var recordId = $("input[name='check-data']:checked").val();
        			var recordIds = $("input[name='check-data']:checked").length;
        			if(recordId==undefined){
        				alert("请先选择至少一个用户");
        				return false;
        			}else{
        				 if (!confirm("确认要将选中用户密码重置为 6个0吗？")) {
     			            return false;
     			         }else{//<b style='color:red;'>6个0</b>
     			        	var checkedIds='';
     	        			$("input[name='check-data']:checked").each(function(){
     	        				checkedIds+=$(this).val()+'#';
     	        			});
     	        			//重置密码请求
     	        			goAjax(resetPasswordUrl,{recordIds:checkedIds},function(data){
     	        				if(data.flag!=0){
     	        					alert('重置密码失败!');
     	        				}else{
     	        					alert(data.msg);
     	        					layer.closeAll();
     	        					//刷新表数据
     	        					//若编辑的是当前登录用户,需重新登录
     	        					if(data.reLoad==1){
     	        						alert('重置了当前登录用户，需重新登录！');
     	                    		    window.location.href = webPath+'login.html' ;
     	        					}else{
     	        						pagerList({});
     	        					}
     	        				}
     	        			});
     			        }
        			}
        		});
    			//权限分配
    			$("#allocate").click(function(){
    				var recordId = $("input[name='check-data']:checked").val();
    				var checkeds= $("input[name='check-data']:checked").length;
    				if(recordId==undefined||checkeds>1){
    					alert("请选择要分配的用户");
    					return false;
    				}
    				//弹出权限分配框
    				layer.open({
    					type: 1,
    					move:false,
    					title:'角色分配',
    					//skin: 'layui-layer-rim', //加上边框
    					area: ['710px', "400px"], //宽高
    					//offset: '30px',
    					shade: [0.8, '#393D49'],
    					zIndex : 3,
    					content: $('#allocate-win')
    				});
    				//获取角色数据
    				goAjax(queryRolesUrl,{},function(data){
    					/* var html='';
    					$("#tb-allacate-data tbody").html(html);
    					$(data.data).each(function(index, ele){
    						html = '<tr>'+
    						'<td><input type="checkbox" name="check-allocate-data" value="'+notNull(ele.roleId)+'" roleName="'+notNull(ele.roleName)+'"></td>'+
    					    '<td>'+notNull(ele["roleName"])+'</td>'+
    					    '<td>'+notNull(ele["roleDesc"])+'</td>'+
    	                    '</tr>';
    	        			$("#tb-allacate-data").append(html);
    					}) */
    					if(data.flag!=0){
    						alert('数据查询失败');
    					}else{
    						showRoleData(data, 'tb-allacate-data', false, queryRolesUrl, {});
    					}
    					//回显权限数据
    					goAjax(queryUserRoleByUserIdUrl,{recordId:recordId},function(data){
    						$(data.data).each(function(index, ele){
    							var roleId=ele.roleId;
    							$("input[name='check-allocate-data']").each(function(){
    							    var checkId=$(this).val();
    								if(roleId==checkId){
    								   $(this).prop("checked",true);
    							   }
    							})
    						})
    					});
    				});
    				//给用户分配角色权限
    				
    				$("#allocate-btn-submit").click(function(){
    					var checkeds=$("input[name='check-allocate-data']:checked").length;
    					if(checkeds<1||checkeds==undefined){
    						alert("至少分配一个角色");
    						return false;
    					}
    					var userRoles = [];
    					$("input[name='check-allocate-data']:checked").each(function(index,ele){
    						var user_role = new Object();
    						user_role.userId=recordId;
    						user_role.roleId=$(ele).val();
    						user_role.roleName=$(this).attr("roleName");
    						userRoles.push(user_role);
    					});
    					console.log(userRoles);
    					$.ajax({
    	    				url:allocateUserRoleUrl,
    	    	            data:$.param(userRoles.serializeObject('userRoles')),    //手动把数据转换拼接{"userRoles":userRoles}
    	    	            type:'post',
    	    	            dataType:'json',
    	    	            traditional:true,    //这里必须设置
    	    	            success:function(response){
    	    	            	if(response.flag==0){
    	        					alert('添加权限成功');    	        					
    	        					//layer.closeAll();
    	        					window.location.reload();
    	        				}else{
    	        					alert('添加权限失败');
    	        				}
    	    	            }
    	    			});
    				});
    			});
        		//解绑编辑提交按钮
        		$('#edit-win-btn').unbind('click');
        		$('#edit-win-btn').click(function(){
        			if($('#edit-win').attr('type')=='detailNew'){
        				layer.closeAll();
        			}else{
        				//表单验证
            			var vflag = validateAnt('#edit-win');
            			if(!vflag){
            				return false;
            			}
        			}
        			//生成请求参数
        			var paramAjax = genReqData('#edit-win');
        			if($('#edit-win').attr('type')=='add'){
        				goAjax(addUrl, paramAjax, function(data){
            				if(data.flag!=0){
            					alert(data.msg);
            				}else{
            					alert('操作成功');
            					layer.closeAll();
            					//刷新表数据
            					pagerList({});
            				}
            			});
        			}else if($('#edit-win').attr('type')=='updateNew'){
        				goAjax(updateUrl, paramAjax, function(data){
            				if(data.flag!=0){
            					alert(data.msg);
            				}else{
            					alert('操作成功');
            					layer.closeAll();
            					//刷新表数据
            					//若编辑了当前用户需重新登录
                				if(data.reLoad==1){
                					alert('编辑了当前登录用户，需重新登录!');
                					window.location.href = webPath+'login.html' ;
                				}else{
                					//若编辑成功且没有编辑当前用户刷新页面
                					pagerList({});
                				}
            				}
            			});
        			}
        			
        		});
        		onUpdateBatchClick();//监听批量编辑按钮
    		});
    		
    		//生成高级查询的窗口
    		
    		//以下内容是动态加载下拉框、单选框、多选框数据

	
			genBranchSelect(findBranchUrl,{},"branchId.branchId-query");
		    genBranchSelect(findBranchUrl,{},"branchId.branchId");
		     

         
		//用户权限分配弹框的点中行单选
			$('#tb-allacate-data tbody').on('click','tr',function(e){
				$('#tb-allacate-data tbody tr').children().children('input[type=checkbox]').prop("checked", false);
				if($(e.currentTarget).children().children('input[type=checkbox]').prop("checked")==false){
					$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",true);
				}else{
					$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",false);
				}
			});
			
			//阻止父元素的事件冒泡
			$('#tb-allacate-data tbody').on('click','input[type=checkbox]',function(e){
				e.stopPropagation();
				if($(e.currentTarget).children().children('input[type=checkbox]').prop("checked")==false){
					$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",true);
				}else{
					$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",false);
				}
			});
			
		});
    </script>
  </head>
  <div class="container-ant">
	<div class="row-ant">
		<div class="col-sm-12-ant">
			<!-- 显示用户信息区域 -->
			<c:import url="/WEB-INF/pages/template/header.jsp"/>
		</div>
	</div>
</div>
<body>
<div class="container-ant">
	
	<!-- 显示导航菜单区域 -->
	<div class="col-sm-2-ant"><c:import url="/WEB-INF/pages/template/leftmenu.jsp"/></div>
	
	<!-- 页面底部脚本区域 -->
	<div class="col-sm-10-ant" ><c:import url="/WEB-INF/pages/template/footer.jsp"/></div>
	
	<!-- 条件查询区域 -->
	<div class="col-sm-10-ant right" id="content_page">
		<div class="panel panel-default query-area">
		    <div class="panel-body" id="query-win">
		        <div class="form-inline">
					<div class=" form-group" style="margin-top:12px">
					  <label for="userName-query">用户姓名
						 <input type="text" class="form-control"  id="userName-query">
					  </label>&nbsp;&nbsp;&nbsp;
<!-- 					  <label for="branchId-query">所属机构
						<select id="branchId.branchId-query">
						   <option value=""></option>
						</select>
					 </label> -->
					<label for="branchId-query">所属机构
		            <select class="form-control" id="branchId.branchId-query" style="margin-top:-5px" > 
		              <option></option>
		            </select>
		          </label> 
                       <!-- 查询按钮 -->
                    <!--  <button type="button" id="query-advance" class="btn btn-default" style="float:right;margin-right:30px;">高级</button> -->
				      <button type="button" id="query" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查&nbsp;询</button>
				  </div>
		    </div>
		</div>
	  </div>
		<!-- 条件查询区域 end-->
		
		
		<!-- 数据显示区域 -->            
		<div class="panel panel-default data-area">
		  <div class="panel-body">
			<div class="btn-group menu-lan" style="position:relative">
			  <button id="detail" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th"></span> 详情</button>
<!-- 			  <button id="allocate" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-wrench"></span> 角色分配</button>
			  <button id="changePassword" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil"></span> 重置密码</button> -->
			  <!--<button id="add" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus"></span> 新增</button>
			  <button id="update" type="button" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-pencil"></span> 编辑</button>
			  <button id="update-batch" type="button" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-tasks"> </span> 批量编辑</button>
			  <button id="delete" type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span> 删除</button>
			  <button id="import" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> 导入</button>
			  <button id="export" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> 导出</button>-->
			</div>
			<!-- 数据表格 -->
		    <table id="tb-data" class="table table-bordered table-striped table-hover">
		      <thead>
		      <tr>
		      	<th><input type="checkbox" id="checkAll" onclick="selectAll()"/></th>
		      	<th class="tb-sort" col="userLoad">用户登录名</th>
				<th class="tb-sort" col="userName">用户姓名</th>
				<th class="tb-sort" col="userPhone">移动电话</th>
				<th class="tb-sort" col="userCall">座机电话</th>
				<th class="tb-sort" col="userEmail">邮箱</th>
				<th class="tb-sort" col="branchId.branch_id">所属机构</th>
				<th class="tb-sort" col="userRoleId">所属角色</th>

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
		<div id="edit-win" class="none">
			<input id="id" type="hidden" value="">
			<table class="table border-none">
			<tr class='load-password-win'>
				<th width="20%">登录帐号名<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="userLoad" name="userLoad" vtype="required" vmsg="登录帐号名不能为空"  unique="true" >
				</td>
				<th width="20%">用户密码<span style="color:red">*</span></th>
				<td width="30%">
					<input type="password" class="form-control" id="userPwd" name="userPwd" vtype="required" vmsg="用户密码不能为空"   >
				</td>
			</tr>
			<tr>
				<th width="20%">用户姓名<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="userName" name="userName" vtype="required" vmsg="用户姓名不能为空"   >
				</td>
				<th width="20%">移动电话<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="userPhone" name="userPhone" vtype="mobile" vmsg="手机号码格式有误【移动电话】"   >
				</td>
			</tr>
			<tr>
				<th width="20%">座机电话<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="userCall" name="userCall" vtype="required" vmsg="座机电话不能为空"   >
				</td>
				<th width="20%">邮箱<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="userEmail" name="userEmail" vtype="email" vmsg="邮箱地址格式有误【邮箱】"   >
				</td>
			</tr>
			<tr>
				<th width="20%">所属机构<span style="color:red">*</span> </th>
				<td width="30%">
				<select class="form-control branchId" id="branchId.branchId" name="branchId.branchId"   foreign-key="true" vtype="required" vmsg="所属机构不能为空" >
				</select>
				</td>
			</tr>
	        <tr>
	          <td colspan="4"><button id="edit-win-btn" class="btn btn-primary btn-block">确认</button></td>
	        </tr>
		    </table>
		</div>
		<!-- 增加、编辑时的窗口 end-->
		
		<!-- 高级查询窗口 -->
		<div id="query-advance-win" class="none">
			
		</div>
		<!-- 高级查询窗口 end-->
		
		<!-- 批量编辑窗口 -->
		<div id="batch-edit-win" class="none">
			<div id="batch-edit-content">
				<table id="tb-batch-data" class="table table-bordered table-striped table-hover">
					
				</table>
			</div>
			<div class="tx-right"><button id="batch-edit-btn-submit" type="button" class="btn btn-primary" style="width:200px !important;">确定</button></div>
		</div>
		<!-- 批量编辑窗口 end-->
		
		<!-- 文件上传弹出框 -->
		<div id="fileup-win" class="none">
		   <div class="col-md-12">
                <div class="panel">
                    <div class="panel-heading">
                      	
                    <span class="tools pull-right">
                      <a class="fa fa-chevron-down" href="javascript:;"></a>
                      <a class="fa fa-times" href="javascript:;"></a>
                    </span>
                    </div>
                    <div class="panel-body">
                    	<form method="post" role="form-horizontal" enctype="multipart/form-data" id="importExcel">                             
                            <div class="compose-editor">                                  
                                <input id="file" type="file" class="default">
                            </div>
                            <hr/>
                            <div class="compose-btn">
                            	 <button type="button" id="download" class="btn btn-info btn-md">
						           <span class="glyphicon glyphicon-download"></span>模板下载
						         </button>
	                             <button id="fileimportbtn" class="btn btn-primary btn-md">
                                	<span class="glyphicon glyphicon-upload"></span> 导入</button>
                                <button type="button" class="btn btn-md btn-default" id="clear">
                                	<span class="glyphicon glyphicon-remove"></span> 清除</button>
                            </div>
                           </form>
                    </div>
                </div>
            </div>
		</div><!-- 文件上传弹出框 end-->
		
		<!-- 用户分配权限弹出框 -->
		 <div id="allocate-win" class="none" style="margin-left:10px;margin-right:10px;margin-top:10px">
			<div id="allocate-content">
			    <div style="height:270px; overflow-y: scroll;">
				<table id="tb-allacate-data" class="table table-bordered table-hover">
				  <thead>
			      <tr>
			      	<th class="col-xs-2"><input type="checkbox" id="checkAll-allacate" onclick="chooseAll()"/></th>
					<th class="col-xs-4">角色名称</th>
	                <th class="col-xs-6">角色描述</th>
			      </tr>
			      </thead>
			      <tbody>
			      </tbody>
				</table>
				</div>
				<!-- 分页栏 -->
		        <div class="row-ant"><div class="col-sm-12-ant"><div class="pager-lan" id="kkpager-role"></div></div></div>
			</div>
			<div class="tx-right"><button id="allocate-btn-submit" type="button" class="btn btn-primary" style="width:100% !important;">确定</button></div>
		</div>
		<!-- 用户分配权限弹出框 end-->
	</div>
 
</body>
</html>
