<%@page import="com.nantian.atom.generated.po.rolem.User"%>
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
	<script src="<%=basePath %>re/libs/bootstrap/js/bootstrap.min.js"></script> 
	<script src="<%=basePath %>re/libs/layer/layer.js"></script>
 	<script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required.js"></script>
    <!-- 标准的meta信息  end-->
    
    <!-- 标题 -->
    <title>机构管理</title>

    
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
    	var pagerUrl = webPath+"rolem/branch/_pager.html";
    	var queryByIdUrl = webPath + "rolem/branch/_findById.html";
    	///var queryByWhereUrl =webPath+"rolem/branch/_pager.html";
    	var findAllBranchUrl =webPath+"rolem/branch/_findAll.html";
    	var updateUrl = webPath + "rolem/branch/_update.html";
    	var addUrl = webPath + "rolem/branch/_add.html";
    	var deleteBranchUrl = webPath + "rolem/branch/_deleteByBranchId.html";
    	var excelModuleUrl = webPath + "rolem/branch/_excelModule.html";
/*     	var exportExcelUrl = webPath + "rolem/branch/_excelExport.html";
    	var importExcelUrl = webPath + "rolem/branch/_excelImport.html"; */
    	var genFuncMenuUrl = webPath + "_menu/genFuncMenu.html?trn=branchServiceImpl";
    	var findAllUserUrl = webPath+"rolem/user/_findAll.html";
    	var userContactNumberUrl = webPath+"rolem/user/_findByUserName.html";
    	var findByUserIdUrl = webPath+"rolem/user/_findById.html";
    	var findAllEditUrl = webPath+"rolem/branch/_findAllEdit.html";
    	var validateSessionBranchUrl = webPath+"rolem/branch/_validateSessionBranch.html";
    	var editSessionCheckUrl = webPath+"rolem/branch/_editSessionCheck.html";
    	var tbJsonItems = null;
    	
		var showTbData = function(data, tbDom, reloadPagerLan, ajaxUrl, paramUrl){
	        if(data.data==null || data.data==''){
	        	var noRecord = '<tr>'+
	                        	'<th colspan="10" style="text-align:center;">暂无记录！</th>'+
	                            '</tr>';
	        	$("#tb-data tbody").html(noRecord);
	        	$('#update-batch').unbind('click');//取消批量编辑按钮的单击事件
	        }else{
	        	tbJsonItems = data.data;
	        	var htm = '';
	        	$("#tb-data tbody").html(htm);
	        	$(data.data).each(function(index, ele){
	        		var branchId=ele.branchParentId;
	        		var branchUserId=ele.branchContactPeople;
	        		var branchParentName='';
	        		var branchContactPeople='';
	        		//根据id查找联系人名字
	        		//if(branchUserId!=null){
	        			goAjax(findByUserIdUrl,{recordId:branchUserId},function(data){
		        			$(data.data).each(function(index,ele2){
		        				branchContactPeople=ele2.userName;
		        			});
		        			//显示机构信息	        		
			        		if(branchId==0){
			        			htm = '<tr>'+
								'<td><input type="checkbox" name="check-data" value="'+notNull(ele.branchId)+'"></td>'+
							    '<td>'+notNull(ele["branchNo"])+'</td>'+
							    '<td>'+notNull(ele["branchName"])+'</td>'+
							    '<td>'+notNull(branchContactPeople)+'</td>'+
							    '<td>'+notNull(ele["branchContactNumber"])+'</td>'+
							    '<td>'+"该机构为顶级机构"+'</td>'+
							    '<td>'+notNull(ele["branchAddr"])+'</td>'+

			                    '</tr>';
							    $("#tb-data").append(htm);
			        		}else{
			        			goAjax(queryByIdUrl,{recordId:branchId},function(data){
				        		    $(data.data).each(function(index, ele1){
				        				branchParentName=ele1.branchName;
				        			});
			        		    	htm = '<tr>'+
									'<td><input type="checkbox" name="check-data" value="'+notNull(ele.branchId)+'"></td>'+
								    '<td>'+notNull(ele["branchNo"])+'</td>'+
								    '<td>'+notNull(ele["branchName"])+'</td>'+
								    '<td>'+notNull(branchContactPeople)+'</td>'+
								    '<td>'+notNull(ele["branchContactNumber"])+'</td>'+
								    '<td>'+branchParentName+'</td>'+
								    '<td>'+notNull(ele["branchAddr"])+'</td>'+

				                    '</tr>';
								    $("#tb-data").append(htm);
				        		});
			        		}
		        		});
	        		//}
				});
	        	if(reloadPagerLan){
	        		//添加分页栏
					genPager(data.pager, ajaxUrl, paramUrl, null);
	        	}
	        }
		}
	  	//根据联系人加载联系电话
		function getValue(val){
			if(val==""){
				$('#branchContactNumber').val(''); 
				$('#branchContactNumber-edit').val(''); 
			}else{
			//去用户表表查用户联系电话
			goAjax(findByUserIdUrl,{recordId:val},function(data){
				if(data.flag==0){
					$(data.data).each(function(index,ele){
						$('#branchContactNumber').val(ele.userPhone);
						$('#branchContactNumber-edit').val(ele.userPhone);
					});
				}else{
					alert("未找到用户id为： "+val+"的对象");
				}
			});
			}
		}
	  	//过滤后的机构下拉
		//genBranchEndSelect(findAllBranchUrl,{},"branchParentId-edit");
	  	genBranchEditSelect(findAllEditUrl,{},"branchParentId-edit");
		genBranchEndSelect(findAllBranchUrl,{},"branchParentId");
		genUserSelect(findAllUserUrl,{},"branchContactPeople");
		genUserSelect(findAllUserUrl,{},"branchContactPeople-edit");
	  	//判断是所选上否为
    	$(function(){
    		//根据权限改变功能菜单
    		goAjax(genFuncMenuUrl,{},function(response){
    			genFuncMenu(response.data);
    			//启用默认框架的初始化方式
        		enableDefaultFramework('700px', '400px');
    			$('#add').unbind('click');
    			$('#add').click(function(){
    				$('#edit-win').attr('type','add');
    				$('#edit-win input, #edit-win textarea, #edit-win select').removeAttr('disabled');
    				//清空下拉搜索
    				$('#edit-win').find('div .selectbox').text('');
    				$('#edit-win .tinyselect').removeClass('detailTinyselect');
    				$('.branchContactPeople').find('option').each(function(){
    					$(this).attr('selected',false);
    				});
    				goAjax(validateSessionBranchUrl,{},function(data){
    					if(data.flag==0){
    						if(data.ifLeaf==1){
    							alert('当前操作用户所属机构为末级机构，不能执行新增机构操作');
    						}else{
    							//打开编辑窗口
    		    				openWindow('新增', '700px', '400px', '#edit-win');
    						}
    					}else{
    						alert('验证失败！');
    					}
    				});
    			});
    			$('#detail').unbind('click');
    			$('#update').unbind('click');
    			//编辑详情按钮触发事件
        		$('#update,#detail').click(function(){
        			if($(this).attr('id')=='update'){
        				$('#edit-win-edit').attr('type','update')
        			}else{
        				$('#edit-win-edit').attr('type','detail');
        			}
        			//清空下拉搜索框
        			$('#edit-win-edit .branchParent .itemcontainer').find('li').each(function(){
        				$(this).removeClass('selected');
        			});
        			$('#edit-win-edit .contactPeople .itemcontainer').find('li').each(function(){
        				$(this).removeClass('selected');
        			}); 
        			//清空下拉框
        			$('.branchParentId-edit').find('option').each(function(){
        				//$(this).removeClass('selected');
        				$(this).attr('selected',false);
        			});
        			$('.branchContactPeople-edit').find('option').each(function(){
        				//$(this).removeClass('selected');
        				$(this).attr('selected',false);
        			});
        			$('#edit-win-edit .branchParent .selectbox').text('');
        			$('#edit-win-edit .contactPeople .selectbox').text('');
        			//得到行记录的id值
        			var recordId = $("input[name='check-data']:checked").val();
        			var recordIds = $("input[name='check-data']:checked").length;
        			if(recordId==undefined||recordIds>1){
        				alert("请先选择一条记录");
        				return false;
        			}
        			var branchParentId=0;
        			goAjax(queryByIdUrl,{recordId:recordId},function(data){
        				if(data.flag==0){
        					//遍历data对象
        					for(var item in data.data){
        						 if(data.data["branchParentId"]==0){
        							 branchParentId=1;
        						 }
        						 if(item=="branchParentId"){
        							//过滤掉当前机构
        							$('#edit-win-edit .branchParent .itemcontainer').find('li[data-value="'+recordId+'"]').css("display","none");
        							//下拉搜索回显
        							$('#edit-win-edit .branchParent ul').find('li').each(function(i,e){
        								$(e).removeClass('selected');
        								if($(e).attr('data-value')==data.data["branchParentId"]){
        									$(e).addClass('selected');
        									$('#edit-win-edit .branchParent').find('div .selectbox').text($(e).text());
        								} 
        							});
        							$('.branchParentId-edit  option[value="'+data.data["branchParentId"]+'"]').attr("selected",true);
        						}else if(item=="branchContactPeople"){
       								$('#edit-win-edit .contactPeople ul').find('li').each(function(i,e){
           								$(e).removeClass('selected');
           								if($(e).attr('data-value')==data.data["branchContactPeople"]){
           									$(e).addClass('selected');
           									$('#edit-win-edit .contactPeople').find('div .selectbox').text($(e).text());
           								}
           							});
           							$('.branchContactPeople-edit  option[value="'+data.data["branchContactPeople"]+'"]').attr("selected",true);
        						}
        						//通过id设值只能是input:text, input:password, select, textarea，对于其他的要单独处理
        						
        						else if($('[id="'+item+'-edit"]').length==1){
        							$('[id="'+item+'-edit"]').val(data.data[item]);
        						}else if($('[id="'+item+'-edit"]').length==0){
        							//其他类型的表单组件
        							if($("input:checkbox[name='"+item+"']").length>0){//表单类型为checkbox
        								$("input:checkbox[name='"+item+"']").each(function(index, ele){
        									if($(ele).val()==data.data[item]){
        										$(ele).prop("checked",true);
        									}
        								});
        							}
        						}else if($('[id="'+item+'-edit"]').length>0){
        							alert('页面错误，id='+item+'重复');
        							return false;
        						}
        					}
        					//打开编辑窗口
    						if($('#edit-win-edit').attr('type')=="detail"){
    							//禁用下拉搜索
    							$('#edit-win-edit .tinyselect').addClass('detailTinyselect');
    							//禁用所有表单
    							$('#edit-win-edit input, #edit-win-edit textarea, #edit-win-edit select').attr('disabled','disabled');
    							//$('#edit-win button').attr('disabled','disabled');
    							//打开编辑窗口
            					openWindow('详情', '700px', '400px', "#edit-win-edit");
    						}else{
    							goAjax(editSessionCheckUrl,{},function(data){
    								if(data.flag==0){
    									if(recordId==data.branchId){
    										$('#edit-win-edit .branchParent').find('.tinyselect').addClass('detailTinyselect');
    										//解禁除select外的所有表单
    	   								    $('#edit-win-edit select').attr('disabled','disabled');
    	        							$('#edit-win-edit input, #edit-win-edit textarea').removeAttr('disabled');
    									}
    								}
    							});
    							if(branchParentId==1){
    								$('#edit-win-edit .branchParent').find('.tinyselect').addClass('detailTinyselect');
   									alert('该机构是顶级机构,该上级机构和是否为末级机构将被禁止编辑!');
   									$('#edit-win-edit .branchParent').find('.tinyselect').addClass('detailTinyselect');
   								    //解禁除select外的所有表单
   								    $('#edit-win-edit select').attr('disabled','disabled');
        							$('#edit-win-edit input, #edit-win-edit textarea').removeAttr('disabled');
    							}else{
    								$('#edit-win-edit .branchParent').find('.tinyselect .item').each(function(){
    									 if($(this).attr('data-value')==0){
    										 $(this).css('display','none');
    									 }
    								});
    								//解禁所有表单
        							$('#edit-win-edit input, #edit-win-edit textarea, #edit-win-edit select').removeAttr('disabled');
        							$('#edit-win-edit .branchParent').find('.tinyselect').removeClass('detailTinyselect');
        							
    							}
    							//解禁下拉搜索
    							$('#edit-win-edit .contactPeople').find('.tinyselect').removeClass('detailTinyselect');
    							
    							//$('#edit-win button').attr('disabled','disabled');
    							//打开编辑窗口
            					openWindow('编辑', '700px', '400px', "#edit-win-edit");
    						}
        				}else{
        					alert('数据查询失败');
        				}
        			});
        		});
    			//新增提交按钮
    			$('#edit-win-btn').unbind('click');
    			$('#edit-win-btn').click(function(){
    				//表单验证
    				var vflag = validateAnt('#edit-win');
    				if(!vflag){
    					return false;
    				}
    				var branchNo=$('#branchNo').val();
    				var reg=/^[A-Za-z0-9]{5,10}$/;
    				if(!reg.test(branchNo)){
    					layer.tips('机构编号只能是数字、字母或数字字母组合，且长度在10以内','#branchNo',{
  						  tips: [1, '#cc0000']
  					    });
    					return false;
    				}
    				if($('#edit-win .branchParent').find('.selectbox').text()==''){
    					layer.tips('上级机构不能为空','#edit-win .branchParent .selectbox',{
    						  tips: [1, '#cc0000']
    					});
    				}else{
    					//生成请求参数
        				var paramAjax = genReqData('#edit-win');
        				
        			    goAjax(addUrl, paramAjax, function(data){
        					if(data.flag!=0){
        						alert(data.msg);
        					}else{
        						alert('操作成功');
        						layer.closeAll();
        						//刷新表数据
        						window.location.reload();
        					}
        					
        				});
    				}
    				
    			});
        		//编辑详情提交按钮
        		$("#edit-win-submit").click(function(){
        			if($('#edit-win-edit').attr('type')=='detail'){//详情是关闭弹框
        				layer.closeAll();
        			}else{
        				//表单验证
            			var vflag = validateAnt('#edit-win-edit');
            			if(!vflag){
            				return false;
            			}
            			//判断机构编号的长度和值
            			var branchNo=$('#branchNo-edit').val();
        				var reg=/^[A-Za-z0-9]{5,10}$/;
        				if(!reg.test(branchNo)){
        					layer.tips('机构编号只能是数字、字母或数字字母组合，且长度在10以内','#branchNo-edit',{
      						  tips: [1, '#cc0000']
      					    });
        					return false;
        				}
            			//编辑执行更新机构请求
            			var paramAjax = genReqData("#edit-win-edit");
            			goAjax(updateUrl, paramAjax, function(data){
                			if(data.flag!=0){
                				alert(data.msg);
                			}else{
                				alert('操作成功');
                				layer.closeAll();
                				//刷新表数据
                				window.location.reload();
                			}
                		});
        			}
        			
        		});
        		onUpdateBatchClick();//监听批量编辑按钮
        		
    			$("#delete").unbind("click");
    			$("#delete").click(function(){
    				//得到行记录的id值
    				var recordId = $("input[name='check-data']:checked").val();
    				if(recordId==undefined){
    					alert("请先选择要删除的数据");
    				}else{
    				 if (!confirm("确认要删除？")) {
    			            return false;
    			        }else{
    			        	recordIds='';
    				        $("input[name='check-data']:checked").each(function(){
    				        	recordIds+=$(this).val()+'#';
    				        });
    						goAjax(deleteBranchUrl, {"recordIds":recordIds}, function(data){
    							if(data.flag==-1){
    								alert(data.msg);
    								//刷新列表
    								pagerList({});
    							}else if(data.flag==-2){
    								alert(data.msg);
    								//刷新列表
    								pagerList({});
    							}else if(data.flag==0){
    								alert("删除数据成功！");
    								//刷新表数据
    								window.location.reload();
    							}else{
    								alert("删除数据失败!");
    								//刷新表数据
    								pagerList({});
    							}
    						});
    			        }
    				}
    			});
    			
    		});
    		
    		//生成高级查询的窗口
    		
    		//以下内容是动态加载下拉框、单选框、多选框数据

			//genSelect(webPath+"paramitem/_pagerParamitem.html",{"paraValue":"level"},"branchLevel");
			//genSelect(webPath+"paramitem/_pagerParamitem.html",{"paraValue":"level"},"branchLevel-query");
			//genSelect(webPath+"paramitem/_pagerParamitem.html",{"paraValue":""},"branchParentId");
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
	<div class="col-sm-2-ant"><c:import url="/WEB-INF/pages/template/leftmenu.jsp"/></div>
	
	<!-- 页面底部脚本区域 -->
	<div class="col-sm-10-ant" ><c:import url="/WEB-INF/pages/template/footer.jsp"/></div>
	
	<!-- 条件查询区域 -->
	<div class="col-sm-10-ant right" id="content_page">
		<div class="panel panel-default query-area">
		    <div class="panel-body">
		        <div class="form-inline">
		          <div class="form-group">
				    <label for="branchNo-query">机构编号
						<input type="text" class="form-control"  id="branchNo-query" style="margin-top:-5px">
					</label>	
				    <label for="branchName-query">机构名字
				        <!-- <select  id="branchName-query">
						  <option value=""></option>
						</select> -->
						<input type="text" class="form-control"  id="branchName-query" style="margin-top:-5px">
					</label>
				  </div>	
                       <!-- 查询按钮 -->
                    <!--  <button type="button" id="query-advance" class="btn btn-default" style="float:right;margin-right:30px;">高级</button> -->
				      <button type="button" id="query" class="btn btn-primary" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查&nbsp;询</button>
		        
				  
		        </div>
		    </div>
		</div>
		<!-- 条件查询区域 end-->
		
		
		<!-- 数据显示区域 -->            
		<div class="panel panel-default data-area">
		  <div class="panel-body">
			<div class="btn-group menu-lan">
			  <button id="detail" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th"></span> 详情</button>
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
				<th class="tb-sort" col="branchNo">机构编号</th>
				<th class="tb-sort" col="branchName">机构名字</th>
				<th class="tb-sort" col="branchContactPeople">机构联系人</th>
				<th class="tb-sort" col="branchContactNumber">机构联系电话</th>
				<th class="tb-sort" col="branchParentId">上级机构</th>
				<th class="tb-sort" col="branchAddr">机构地址</th>

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
		
		<!-- 增加时的窗口 -->
		<div id="edit-win" class="none">
			<input id="branchId" type="hidden" value="">
			<table class="table border-none">
			<tr>
				<th width="20%">机构编号<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="branchNo" name="branchNo" vtype="required" vmsg="机构编号不能为空" unique="true">
				</td>
				<th width="20%">机构名字 <span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="branchName" name="branchName" vtype="required"  vmsg="机构名字不能为空！" >
				</td>
			</tr>
			<tr class="contactPeople">
			    <th width="20%">机构联系人</th>
				<td width="30%">
				<select class="form-control branchContactPeople" id="branchContactPeople" onchange="getValue(this.value)" name="branchContactPeople">
				   <option value=""></option>
				</select>
				</td>
			    <th width="20%">机构联系电话</th>
				<td width="30%">
				<input type="text" class="form-control" placeholder="跟机构联系人同步，无需输入" id="branchContactNumber" readonly name="branchContactNumber">
				</td>
			</tr>
			<tr >
			   <th width="20%">机构地址<span style="color:red">*</span></th>
				<td  colspan="3">
				<input type="text" class="form-control"  id="branchAddr" name="branchAddr" vtype="required" vmsg="机构地址不能为空" >
				</td>
			</tr>
			<tr class="branchParent">
				<th width="20%">上级机构<span style="color:red">*</span></th>
				<td width="30%">
				<select class="form-control branchParentId" id="branchParentId"   name="branchParentId">
				</select>
				</td>
				<th width="20%">末级机构</th>
				<td width="30%">
				<select class="form-control" id="ifLeaf" name="ifLeaf" vtype="required" vmsg="末级机构不能为空">
				  <option value="1">是</option>
				  <option value="0">否</option>
				</select>
				</td>
			</tr>
		      <tr>
		        <td colspan="4"><button id="edit-win-btn" class="btn btn-primary btn-block">确认</button></td>
		      </tr>
		    </table>
		</div>
		<!-- 增加的窗口 end-->
		
		<!-- 编辑时的窗口 -->
		 <div id="edit-win-edit" class="none">
			<input id="branchId-edit" type="hidden" value="">
			<input id="oldBranchNo-edit" type="hidden" value="">
			
			<table class="table border-none">
			<tr>
				<th width="20%">机构编号<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="branchNo-edit" name="branchNo" vtype="" unique="true">
				
				</td>
				<th width="20%">机构名字 <span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="branchName-edit" name="branchName" vtype="required"  vmsg="机构名字不能为空！" >
				</td>
			</tr>
			<tr class="contactPeople">
			    <th width="20%">机构联系人 </th>
				<td width="30%">
				<select class="form-control branchContactPeople-edit" id="branchContactPeople-edit" onchange="getValue(this.value)" name="branchContactPeople">
				  <option value=""></option>
				</select>
				</td>
			    <th width="20%">机构联系电话 </th>
				<td width="30%">
				<input type="text" class="form-control" placeholder="跟机构联系人同步，无需输入" id="branchContactNumber-edit" readonly name="branchContactNumber">
				</td>
			</tr>
			<tr >
			   <th width="20%">机构地址</th>
				<td  colspan="3">
				<input type="text" class="form-control"  id="branchAddr-edit" name="branchAddr" vtype="required" vmsg="机构地址不能为空" >
				</td>
			</tr>
			<tr class="branchParent">
				<th width="20%">上级机构<span style="color:red">*</span></th>
				<td width="30%">
				<select class="form-control branchParentId-edit" id="branchParentId-edit" name="branchParentId" onchange="getBranchParent(this.value)" vtype="required" vmsg="上级机构不能为空">
				   <option value="0">顶级机构</option>
				</select>
				</td>
				<th width="20%">末级机构</th>
				<td width="30%">
				<select class="form-control" id="ifLeaf-edit" name="ifLeaf" vtype="required" vmsg="末级机构不能为空">
				  <option value="1">是</option>
				  <option value="0">否</option>
				</select>
				</td>
			</tr>
		      <tr>
		        <td colspan="4"><button id="edit-win-submit" class="btn btn-primary btn-block">确认</button></td>
		      </tr>
		    </table>
		</div>
		<!-- 编辑时窗口 end-->
		
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
	</div>
</div>

</body>
</html>
