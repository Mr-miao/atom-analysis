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
    <title>系统参数设置</title>

	<!-- 定义基准路径的全局变量 -->
	<script>
		var webPath = '<%=basePath%>';
	</script>
	<!-- 必须引入的js，详情请见util-required.js -->
	<script src="<%=basePath %>re/libs/bootstrap/js/jquery.min.js"></script> 
	<script src="<%=basePath %>re/libs/bootstrap/js/bootstrap.min.js"></script> 
	<script src="<%=basePath %>re/libs/layer/layer.js"></script>
    <script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required.js"></script>
    
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
       /*设置查询区域的输入框的宽度，底部间距使其右浮动*/
    	/*设置多行文本框不可拖动*/
 
	</style>
	
    <script>
    	var pagerUrl = webPath+"sysm/parameterConf/_pager.html";
    	var queryByIdUrl = webPath + "sysm/parameterConf/_findById.html";
    	var queryByWhereUrl =webPath+"sysm/parameterConf/_pager.html";
    	var updateUrl = webPath + "sysm/parameterConf/_update.html";
    	var updateBatchUrl = webPath + "sysm/parameterConf/_updateBatch.html";
    	var addUrl = webPath + "sysm/parameterConf/_add.html";
    	var deleUrl = webPath + "sysm/parameterConf/_delete.html";
    	var genFuncMenuUrl = webPath + "_menu/genFuncMenu.html?trn=parameterConfServiceImpl";
    	
    	var tbJsonItems = null;
    	
    	
		var showTbData = function(data, tbDom, reloadPagerLan, ajaxUrl, paramUrl){
	        if(data.data==null || data.data==''){
	        	var noRecord = '<tr>'+
	                        	'<th colspan="6" style="text-align:center;">暂无记录！</th>'+
	                            '</tr>';
	        	$("#tb-data tbody").html(noRecord);
	        	$('#update-batch').unbind('click');//取消批量编辑按钮的单击事件
	        }else{
	        	tbJsonItems = data.data;
	        	var htm = '';
	        	$("#tb-data tbody").html(htm);
	        	$(data.data).each(function(index, ele){
					htm = '<tr>'+
						'<td><input type="checkbox" name="check-data" value="'+notNull(ele.id)+'"></td>'+
				      	'<td>'+(++index)+'</td>'+
					    '<td>'+notNull(ele["paraName"])+'</td>'+
					    '<td>'+notNull(ele["paraEnglishName"])+'</td>'+
					    '<td>'+notNull(ele["paraValue"])+'</td>'+
					    '<td>'+notNull(ele["paraDesc"])+'</td>'+

	                    '</tr>';
					$("#tb-data").append(htm);
				});
	        	if(reloadPagerLan){
	        		//添加分页栏
					genPager(data.pager, ajaxUrl, paramUrl, null);
	        	}
	        }
		}
	  	
		
    
    	$(function(){
    		//根据权限改变功能菜单
    		goAjax(genFuncMenuUrl,{},function(response){
    			genFuncMenu(response.data);
    			//启用默认框架的初始化方式
        		enableDefaultFramework('700px', '400px');
        		onUpdateBatchClick();//监听批量编辑按钮
    		});
    		
    		//生成高级查询的窗口
    		
    		//以下内容是动态加载下拉框、单选框、多选框数据





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
  					<label for="paraName-query">参数名称</label>
					<input type="text" class="form-control"  id="paraName-query"  >
   				
                       <!-- 查询按钮 -->
                     <!--  <button type="button" id="query-advance" class="btn btn-default" style="float:right;margin-right:500px;">高级</button>  -->
				      <button type="button" id="query" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查&nbsp;询</button>
		          </div>
				  
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
		        <th>序号</th>
				<th class="tb-sort" col="paraName">参数名称</th>
				<th class="tb-sort" col="paraEnglishName">参数英文名称</th>
				<th class="tb-sort" col="paraValue">参数值</th>
				<th class="tb-sort" col="paraDesc">参数描述</th>

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
			<tr>
				<th width="20%">参数名称<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="paraName" name="paraName" vtype="required" vmsg="参数名称不能为空"  unique="true" >
				</td>
				<th width="20%">参数值<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="paraValue" name="paraValue" vtype="required" vmsg="不能为空"   >
				</td>
			</tr>
			<tr>
				<th width="20%">参数英文名称<span style="color:red">*</span></th>
				<td width="30%">
				<input type="text" class="form-control"  id="paraEnglishName" name="paraDesc" vtype="required" vmsg="不能为空"  unique="true" >
				</td>
				<th width="20%">参数描述</th>
				<td width="30%">
				<input type="text" class="form-control"  id="paraDesc" name="paraDesc" vtype="" vmsg=""   >
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
	</div>
</div>

</body>
</html>
