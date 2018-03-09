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

      	<!-- 定义基准路径的全局变量 -->
    <script>
		var webPath = '<%=basePath%>';
    	var showDataListUrl = webPath+"dataimport/showData.html";//获取excel指定列数据
    	var importDataUrl=webPath+"dataimport/importData.html";//获取excel指定列数据
    	$(function(){
    		//启用默认框架的初始化方式
    		
    		//以下内容是动态加载下拉框、单选框、多选框数据
    		//导入按钮

    		
    		$('#importData-win-next').click(function(){ 
    			var param="";
    		//	var versionId=$('#versionList tbody input[type=radio]:checked').val();
    		
    		var excelFile=$('#excelFile').val();

			if(excelFile==""||excelFile==null){
				alert("请选择excel文件");
				$('#excelFile').focus();
				return false;
			}
    			var tableName=$('#tableName').val();
    			var rowNum=$('#rowNum').val();
    			
    			var firstVal=$('#firstVal').val();
    			var lastVal=$('#lastVal').val();
    			if(lastVal==""){
    				lastVal = "-1";
    			}
    			var formdata = genMediaFileuploadData12('#excelInfo-win');
    			param="{tableName:"+tableName+";rowNum:"+rowNum+";firstVal:"+firstVal+";lastVal:"+lastVal+"}";
    			var url=showDataListUrl+"?param="+param;
    			
    			
					goAjaxFileupload(url,formdata,function(resp){
        				if(resp.flag==0){
        					showDataTable(resp);
        					openWindow('第二步：选择数据表字段与excel列对应关系', "60%", "60%", "#excel-table-List"); 	
        					
        				}else{
        					alert("读取excel数据失败");
        				}
        			});    		       			      			      			
    			
    		});
    		//上一步按钮
			$('#importData-win-prev').click(function(){	
				layer.closeAll();
				winIndex = openWindow('第一步：配置excel信息', "60%", "60%", "#excelInfo-win");	
			});
    		
    		$('#submit-win-end').click(function(){
    			
    			var tableName=$('#tableName').val();
    			var rowNum=$('#rowNum').val();
    			
    			var firstVal=$('#firstVal').val();
    			var lastVal=$('#lastVal').val();
    			
    			
    			var param=genData('#excel-table-List');   			
   				goAjax(importDataUrl, param, function(data){
   					
           			if(data.flag!=0){
           				alert(data.msg);
           			}else{
           				alert('操作成功');
           				layer.closeAll();
           				//刷新表数据
           				pagerList({});
           			}
           		});
    			
    		});
    		
		});

		function clearData(){
			$("#chooseTableComnList tbody").val="";
			$("#rowNum").val="";
			$("#firstVal").val="";
			$("#firstVal").val="";
			$("#excelFile").val="";
		}
		
		var genData = function(parentDom){
			var obj = {};
			var formdata = new FormData();
			var fileArr = new Array();
			var mediaDatailVal='';
			var comnkey = "comnName";
			var val ='';
			var tableName=$('#tableName').val();
			obj["tableName"] = tableName;
			
			var rowNum=$('#rowNum').val();
			var excelNewName =$('#excelNewName').val();
			var comnVal =$('#comnVal').val();
			obj["comnVal"] = comnVal;
			var firstVal=$('#firstVal').val();
			var lastVal=$('#lastVal').val();
			if(lastVal==""){
				obj["lastVal"] = "-1";
			}else{
				obj["lastVal"] = lastVal;
			}
			obj["rowNum"] = rowNum;
			obj["firstVal"] = firstVal;
			
			
			var excelFile=$('#excelFile').val();
			obj["excelFile"] = excelNewName;
			$(parentDom+" input:hidden[name='dataComn']").each(function(index, element){
				var el = $(element);
				var type = el.attr('type');
				if( type=="hidden"){  
					
					val += el.val();  				  
					val += '#';
    				};	
			});
			if(val!="" && val!=null){
				val = val.substr(0, val.length-1);
				obj[comnkey] = val;
			}
			var comnType = "comnType";
			val="";
			$(parentDom+" input:hidden[name='comnType']").each(function(index, element){
				var el = $(element);
				var type = el.attr('type');

				if( type=="hidden"){    				    				    				
					val += el.val();;   				  
					val += '#';
    				};	
			});
			if(val!="" && val!=null){
				val = val.substr(0, val.length-1);
				obj[comnType] = val;
			}
			
			var excelkey = "excelComnName";
			var excelval ='';
			var selVal='';
			$(parentDom+" select").each(function(index, element){
				var key = "excelComnName";
				var ele = $(element);
				selVal+= ele.val();
				selVal += '#';
			})
			obj[excelkey] = selVal;
			return obj;
		}
		
		
		function getExcelComn(resp,rm){
			var td= '<td><select class="form-control" name="excelComn"  id="excelComn'+rm+'" vtype="required"  vmsg="excel列不能为空">';
			if(resp.excelcomnList==null || resp.excelcomnList==''){
				
			}else{
				var comnVal=0;
				$(resp.excelcomnList).each(function(index, ele){
					
					td=td+"<option value='"+comnVal+"'>"+ele+"</option>";	
					comnVal=comnVal+1;
				});
				
			}
			td=td+"</select></td>";
			
			return td;
		}
		function showDataTable(resp){
			
			
			if(resp.data==null || resp.data==''){
	        	var noRecord = '<tr>'+
	                        	'<th colspan="3" style="text-align:center;">暂无记录！</th>'+
	                            '</tr>';
	        	$("#chooseTableComnList tbody").html(noRecord);
	        }else{	        	
	        	var htm='<tr >'+
		      	'<td><input type="hidden" value="'+resp.fileName+'" name="excelNewName" id="excelNewName"><input type="hidden" value="'+resp.keySeq+'" name="comnVal" id="comnVal"></td>'+
			    '<td>数据库表字段名</td>'+
			    '<td>excel列名</td>'+
                '</tr>';
	        	$("#chooseTableComnList tbody").html(htm);
	        	var comnList="";	        	        	
	        	$(resp.data).each(function(index, ele){	
	        		comnList=getExcelComn(resp,index);
					htm = '<tr>'+
				      	'<td>'+(++index)+'</td>'+
					    '<td><input  type="hidden"  id="'+notNull(ele["comnName"])+'" name="dataComn"  value="'+notNull(ele["comnName"])+'"><input  type="hidden"  id="'+notNull(ele["comnType"])+'" name="comnType"  value="'+notNull(ele["comnType"])+'">'+notNull(ele["comnChinese"])+'</td>'+
					//    '<td><input  type="text"  id="'+notNull(ele["comnType"])+'" name="comnType"  value="'+notNull(ele["comnType"])+'"></td>'+
					    comnList+
	                    '</tr>';
					$("#chooseTableComnList tbody").append(htm);
				});

	        }			
		}
	</script>
<style>

</style>
    <!-- 标准的meta信息  end-->
    
    <!-- 标题 -->
    <title>批量导入</title>

	<!-- 必须引入的js，详情请见util-required.js -->
	<script src="<%=basePath %>re/libs/bootstrap/js/jquery.min.js"></script> 
	<script src="<%=basePath %>re/libs/bootstrap/js/bootstrap.min.js"></script> 
	<script src="<%=basePath %>re/libs/layer/layer.js"></script>
    <script type="text/javascript" src="<%=basePath %>re/libs/utils/util-required.js"></script>
	
    
    <!-- style声明，仅仅只是对当前页面的样式设置，否则请使用公共style表 -->
	   
  </head>

<body>
	<!-- 条件查询区域 -->
					
		<!-- excel文件信息窗口 -->
		<!-- 第一步 -->
		
		<div id="excelInfo-win" class="none"  >
		
		<table class="table border-none">
			<tr style="padding-bottom:0px;margin-bottom:0px;">
				<td colspan="4" style="border:none;padding-bottom:0px;margin-bottom: 0px;">
					<table class="table table-bordered table-striped" >
						<tr style="padding-bottom:0px">
							<th width="25%">excel栏位名称所在行：</th>
							<td><input  type="text"  id="rowNum" name="rowNum"  vtype="required" vmsg="不能为空！【栏位名称】" value="1"></td>
						</tr>
						<tr style="padding-bottom:0px">
							<th width="25%">第一个数据行：</th>
							<td><input  type="text"  id="firstVal" name="firstVal"  vtype="" vmsg="" value="2"></td>
						</tr>
						<tr style="padding-bottom:0px">

							<th width="25%">最后一个数据行：</th>
							<td><input  type="text"  id="lastVal" name="lastVal"  vtype="" vmsg="" value=""></td>						
						</tr>
						<tr style="padding-bottom:0px">
							<th width="25%">excel文件：</th>
							<td><input  type="file"  id="excelFile" name="excelFile"  vtype="required" vmsg="不能为空！【excel文件】"></td>
						</tr>
						<tr>
			        		<td colspan="4"  style="border:none;"><button id="importData-win-next" class="btn btn-primary btn-block" style="margin-top:20px;">下一步</button></td>
			      		</tr>
					</table>
				</td>
			</tr>
		</table>						      			
		</div>
		
		<!-- excel文件信息窗口 end-->
		
		<!-- 字段与文件表格匹配窗口 -->
		<!-- 第二步 -->
		<div id="excel-table-List" class="none col-sm-12">
			
			<table id="chooseTableComnList" class="table table-bordered table-striped" >
				<tbody>
						  					  
				</tbody>
			</table>
			
			<div class="panel">
				<div class="panel-body">
				    <div class="col-sm-12">
				    	<div class="col-sm-6"><button id="importData-win-prev" class="btn btn-primary btn-block">上一步</button></div>
				    	<div class="col-sm-6"><button id="submit-win-end"  class="btn btn-danger btn-block">开始导入</button></div>				    	
				    </div>
				 </div>
			</div>	
		</div>
		<!-- 增加、编辑时的窗口 end-->
		



</body>
</html>
