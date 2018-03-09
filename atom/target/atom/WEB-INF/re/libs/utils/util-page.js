// JavaScript Document
//预览视频文件
function playVideo(path,winId){
	var htm='';
	$(winId).html('');
	if(path!=undefined&&path!=null){
		 htm='<div style="margin-top:20px">'+
			    '<video class="col-xs-12" controls="controls">'+
			       '<source src="'+path+'" type="video/mp4">'+
			    '</video>'+
			 '</div>';
		$(winId).append(htm);
	}
	openWindow('视频播放', '700px', '500px',winId);
}
//预览音频文件
function playAudio(path,winId){
	var htm='';
	$(winId).html('');
	if(path!=undefined&&path!=null){
		 htm='<div style="margin-top:40px">'+
			    '<audio class="col-xs-12" controls="controls">'+
			       '<source src="'+path+'" type="audio/mpeg">'+
			    '</audio>'+
			 '</div>';
		$(winId).append(htm);
	}
	openWindow('音频播放', '700px', '300px',winId);
}
//动态获取下拉框
    function genSelect(url,param,elemId){
    	goAjax(url,param,function(response){
			var data=response.data;
			 var objSelectNow=document.getElementById(elemId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
  				  objOption.text= data[i].abName;
  				  objOption.value=data[i].id;
  				  objSelectNow.options.add(objOption);
			    }
			 }
    	});
    	
    };
    //动态获取多选框
    function genCheckbox(url,param,elemId){ 
   	 var ul=document.createElement("ul");
    	goAjax(url,param,function(response){
			var data=response.data;
			var checkboxId=param.paraValue;
			var objSelectNow=document.getElementById(elemId);
			 
			    for (var i = 0; i < data.length; i++) {
  		        var checkBox=document.createElement("input");
  		        checkBox.setAttribute("type","checkbox");
  		        checkBox.setAttribute("id",elemId+checkboxId);
  		        checkBox.setAttribute("name", checkboxId);
  		        checkBox.setAttribute("value",data[i].abName);
  		        var li=document.createElement("li");
  		        li.appendChild(checkBox);        
  		        li.appendChild(document.createTextNode(data[i].abName));

  		        ul.appendChild(li); 
			    }
   	});
    	 $("#"+elemId).append(ul);
    }
    //动态获取单选框
    function genRadio(url,param,elemId){
   	 var ul=document.createElement("ul");
    	goAjax(url,param,function(response){
			var data=response.data;
			var radioId=param.paraValue;
			var objSelectNow=document.getElementById(elemId);
			 
			    for (var i = 0; i < data.length; i++) {
  		        var checkBox=document.createElement("input");
  		        checkBox.setAttribute("type","radio");
  		        checkBox.setAttribute("id",elemId+radioId);
  		        checkBox.setAttribute("name", data[i].abName);
  		        var li=document.createElement("li");
  		        li.appendChild(checkBox);       
  		        li.appendChild(document.createTextNode(data[i].abName));
  		        ul.appendChild(li); 
			    }
   	});
    	 $("#"+elemId).append(ul);
    }
    //动态生成功能菜单
    function genFuncMenu(jsonarr){
    	
    	if(jsonarr==null){
			alert("该用户无任何操作权限！");
		}else{
			$.each(jsonarr,function(i,json){
//				alert(json.optMethod);
				if(json.optMethod!=null){
//					alert(123);
//					alert(json.optMethod);
					//新增
					if(json.optMethod.indexOf("save")>=0){
						$('.btn-group').append('<button id="add" index=1 type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus"></span> 新增</button>');
					}
					//删除
					else if(json.optMethod.indexOf("delete")>=0){
						$('.btn-group').append('<button id="delete" index=2 type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span> 删除</button>');
					}
					//导出
					else if(json.optMethod.indexOf("exportDataToExcel")>=0){
						$('.btn-group').append('<button id="export" index=6 type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span> 导出</button>');
					}
					//批量更新
					else if(json.optMethod.indexOf("updateBatch")>=0 ){
						$('.btn-group').append('<button id="update-batch" index=4 type="button" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-tasks"> </span> 批量编辑</button>');
					}
					//更新
					else if(json.optMethod.indexOf("update")>=0 ){
						$('.btn-group').append('<button id="update" index=3 type="button" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-pencil"></span> 编辑</button>');
					}
					//高级查询
					else if(json.optMethod.indexOf("advCondition")>=0 ){
						$('.form-inline').append('<button type="button" id="query-advance" class="btn btn-default">高级</button>');
					}
					//导入
					else if(json.optMethod.indexOf("importData")>=0 ){
						$('.btn-group').append('<button id="importData" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> 导入</button>');
					}
					//角色分配
					else if(json.optMethod.indexOf("allocateRole")>=0 ){
						$('.btn-group').append('<button id="allocate-role" index=7 type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 角色分配</button>');
					}
					//权限分配
					else if(json.optMethod.indexOf("allocateOpt")>=0 ){
						//$('.btn-group').append('<button id="allocate-opt" index=8 type="button" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span> 权限分配</button>');
					}				
					//主密钥管理
					else if( json.optMethod.indexOf("masterKey")>=0){
						$('.btn-group').append('<button id="masterKey" index=8 type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>主密钥下发</button>');
					}
					//工作密钥管理
					else if( json.optMethod.indexOf("lmkKey")>=0){
						$('.btn-group').append('<button id="lmkKey" index=8 type="button" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>工作密钥下发</button>');
					}//柜员头像批量上传
					else if(json.optMethod.indexOf("mulitUpload")>=0 ){
						$('.btn-group').append('<button id="mulitUpload" index=8 type="button" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>柜员头像批量上传</button>');
					}
					//角色分配
					else if(json.optMethod.indexOf("allocate")>=0 ){
						$('.btn-group').append('<button id="allocate" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-wrench"></span> 角色分配</button>');
					}//重置密码
					else if(json.optMethod.indexOf("changePassword")>=0 ){
						$('.btn-group').append('<button id="changePassword" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-pencil"></span> 重置密码</button>');
					}
					else if(json.optMethod.indexOf("stopTask")>=0 ){
						$('.btn-group').append('<button id="stopTask" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-off"></span>立即停止任务</button>');
					}
					//二级服务器同步
					else if(json.optMethod.indexOf("secondSendSync")>=0  ){
						$('.btn-group').append('<button id="secondSyncMng" type="button" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-wrench"></span> 二级服务器同步</button>');
					}//联通性检测
					else if(json.optMethod.indexOf("secondCheck")>=0){
						$('.btn-group').append('<button id="secondSyncCheck" type="button" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-pencil"></span> 联通性检测</button>');
					}
				}

			});
			//按钮排序
			$('.btn-group').append($('.btn-group button').not('#detail').sort(function(a,b){
				return $(a).attr('index')-$(b).attr('index');
			}));
		
		}
    }
    
    //获取机构下拉列表
    function genBranchSelect(url,param,eleId){
    	goAjax(url,param,function(response){
			 var data=response.data;
			 var objSelectNow=document.getElementById(eleId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
      				objOption.text= data[i].branchName;
    				objOption.value=data[i].branchId;
   			//	objOption.setAttribute('relationship',data[i].branchRelationship);
    				objSelectNow.options.add(objOption);  
			    }
			 }
 			$(objSelectNow).tinyselect();
    	});
    	
    }
    //去除末级机构的下拉
    function genBranchEndSelect(url,param,eleId){
    	goAjax(url,param,function(response){
			var data=response.data;
			var objSelectNow=document.getElementById(eleId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
      			  if(data[i].ifLeaf==0){
      				objOption.text= data[i].branchName;
    				objOption.value=data[i].branchId;
    				objOption.setAttribute('relationship',data[i].branchRelationship);
    				objSelectNow.options.add(objOption);  
      			  }
			    }
			 }
 			$(objSelectNow).tinyselect();
	    });
    }
  //未过滤的机构下拉
    function genBranchAllSelect(url,param,eleId){
    	goAjax(url,param,function(response){
			var data=response.data;
			var objSelectNow=document.getElementById(eleId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
	      			var objOption = document.createElement("OPTION");
	  				objOption.text= data[i].branchName;
					objOption.value=data[i].branchId;
					//objOption.setAttribute('relationship',data[i].branchRelationship);
					objSelectNow.options.add(objOption);  
			    }
			 }
 			$(objSelectNow).tinyselect();
	    });
    } 
  //获取机构名字下拉列表
    function genBranchNameSelect(url,param,eleId){
    	goAjax(url,param,function(response){
			var data=response.data;
			 var objSelectNow=document.getElementById(eleId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
  				  objOption.text= data[i].branchName;
  				  objOption.value=data[i].branchName;
  				  objOption.setAttribute('relationship',data[i].branchRelationship);
  				  objSelectNow.options.add(objOption);
			    }
			 }
 			$(objSelectNow).tinyselect();
    	});
    	
    }
    //编辑所用上级机构下拉
    function genBranchEditSelect(url,param,eleId){
    	goAjax(url,param,function(response){
			var data=response.data;
			var objSelectNow=document.getElementById(eleId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
      			  if(data[i].ifLeaf==0){
      				objOption.text= data[i].branchName;
    				objOption.value=data[i].branchId;
    				objOption.setAttribute('relationship',data[i].branchRelationship);
    				objSelectNow.options.add(objOption);  
      			  }
			    }
			 }
 			$(objSelectNow).tinyselect();
	    });
    }
    //获取用户下拉
    function genUserSelect(url,param,eleId){
    	goAjax(url,param,function(response){
			var data=response.data;
			 var objSelectNow=document.getElementById(eleId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
  				  objOption.text= data[i].userName;
  				  objOption.value=data[i].id;
  				  //objOption.onchange="getValue(this.value)";
  				  objSelectNow.options.add(objOption);
			    }
			 }
 		//	$(objSelectNow).tinyselect();
    	});
    	
    }
    //获取设备类型下拉
    function genDeviceTypeSelect(url,param,elemId){
    	goAjax(url,param,function(response){
			var data=response.data;
			 var objSelectNow=document.getElementById(elemId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
  				  objOption.text= data[i].deviceTypeName;
  				  objOption.value=data[i].id;
  				  objSelectNow.options.add(objOption);
			    }
			 }
    	});
    	
    };
    //获取设备厂商
    function genDeviceFactorySelect(url,param,elemId){
    	goAjax(url,param,function(response){
			var data=response.data;
			 var objSelectNow=document.getElementById(elemId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
  				  objOption.text= data[i].factoryName;
  				  objOption.value=data[i].id;
  				  objSelectNow.options.add(objOption);
			    }
			 }
 		//	$(objSelectNow).tinyselect();
    	});
    	
    };
    
    //获取版本号
    function genVersionNoSelect(url,param,elemId){
    	goAjax(url,param,function(response){
			var data=response.data;
			 var objSelectNow=document.getElementById(elemId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
  				  objOption.text= data[i].versionNo;
  				  objOption.value=data[i].versionNo;
  				  objSelectNow.options.add(objOption);
			    }
			 }
    	});
    };
    
    //获取软件类型
    function genVersionTypeSelect(url,param,elemId){
    	goAjax(url,param,function(response){
			var data=response.data;
			 var objSelectNow=document.getElementById(elemId);
 			if(response.flag==0){
			    for (var i = 0; i < data.length; i++) {
      			  var objOption = document.createElement("OPTION");
  				  objOption.text= data[i].typeName;
  				  objOption.value=data[i].id;
  				  objSelectNow.options.add(objOption);
			    }
			 }
    	});
    	
    };
    
$(function(){
	//回车查询
	$(document).keyup(function(event){
		  if(event.keyCode ==13){
			//验证请求输入
				var vflag = validateAnt('.query-area');
				if(!vflag){
					return false;
				}
				//生成请求参数
				var paramNew = getQueryParam();
				pagerList(paramNew);
		  }
	});
	//控制页面高度
	$('#content_page').css({'height':$(document).height()-$('#_title').height()-$('.sticky-footer').height(),'overflow':'auto'});
	//点击列表行可以选中
	//jquery的on方法，.on('click','tr',function(){}),'click'绑定要触发的事件，
	//‘tr’绑定要触发的元素，最后是触发时的逻辑
	$('#tb-data tbody').on('click','tr',function(e){
		$('#tb-data tbody tr').children().children('input[type=checkbox]').prop("checked", false);
		if($(e.currentTarget).children().children('input[type=checkbox]').prop("checked")==false){
			$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",true);
		}else{
			$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",false);
		}
	});
	
	//阻止父元素的事件冒泡
	$('#tb-data tbody').on('click','input[type=checkbox]',function(e){
		e.stopPropagation();
		if($(e.currentTarget).children().children('input[type=checkbox]').prop("checked")==false){
			$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",true);
		}else{
			$(e.currentTarget).children().children('input[type=checkbox]').prop("checked",false);
		}
	});
	
	//单击列排序
	$('#tb-data .tb-sort').click(function(e){
		e.stopPropagation();
		$('#tb-data .tb-sort').css('background', "url('"+webPath+"re/libs/bootstrap-ant/images/sort_both.png') no-repeat center right");
		if($(this).attr('tb-sort')==undefined || $(this).attr('tb-sort')=="asc"){
			//升序排序
			$(this).attr('tb-sort', 'desc').css('background', "url('"+webPath+"re/libs/bootstrap-ant/images/sort_desc.png') no-repeat center right");
			orderQueryFun($(this).attr('col'), 'desc');
		}else if($(this).attr('tb-sort')=="desc"){
			//降序排序
			$(this).attr('tb-sort', 'asc').css('background', "url('"+webPath+"re/libs/bootstrap-ant/images/sort_asc.png') no-repeat center right");
			orderQueryFun($(this).attr('col'), 'asc');
		}
	});
	
});


/**
 * 在新增、编辑弹窗时，建议调用此方法
 */
var openWindowEditReady = function(jEl, width, height, contentDom){
	//在新增之前，要重置一下新增窗口中表单的信息
	resetForm(contentDom);//参数为窗口的id
	
	if(jEl.attr('id')=="add"){
		$(contentDom).attr('type','add');
		$('#edit-win input, #edit-win textarea, #edit-win select').removeAttr('disabled');
		//打开编辑窗口
		if($(contentDom).attr('category')=='updateRecord'){
			openWindow('第一步：输入任务名称、选择消息推送时间、选择软件版本', width, height, contentDom);
		}else{
			openWindow('新增', width, height, contentDom);
		}
		//针对设备修改清空厂商和类型下拉
		$('.deviceFactoryId  option[value=""]').attr("selected",true);
		$('.deviceTypeId  option[value=""]').attr("selected",true);
		//清空下拉搜索
		$('#edit-win').find('div .selectbox').text('');
		$('#edit-win .tinyselect').removeClass('detailTinyselect');
		$('#edit-win .branchContactPeople option[value=""]').attr('elected',true);
		//移除用户隐藏域
		$('.load-password-win').removeClass('hideLoadPassword');
	}else if(jEl.attr('id')=="update" || jEl.attr('id')=="detail"){
		if(jEl.attr('id')=="detail"){
			$(contentDom).attr('type','detail');
		}else{
			$(contentDom).attr('type','update');
		}
		//得到行记录的id值
		var recordId = $("input[name='check-data']:checked").val();
		var recordIds = $("input[name='check-data']:checked").length;
		if(recordId==undefined||recordIds>1){
			alert("请先选择一条记录");
			return false;
		}
		
		goAjax(queryByIdUrl,{recordId:recordId},function(data){
			if(data.flag==0){
				//遍历data对象
				for(var item in data.data){
					if(item=="ifFolder"){
						if(data.data[item]==1){
							$("#ifFolder").attr("checked",true);
						}else{
							$("#ifFolder").attr("checked",false);
						}
						
					//通过id设值只能是input:text, input:password, select, textarea，对于其他的要单独处理
					}else if($('[id="'+item+'"]').length==1){
						$('[id="'+item+'"]').val(data.data[item]);
					}else if($('[id="'+item+'"]').length==0){
						//其他类型的表单组件
						if($("input:checkbox[name='"+item+"']").length>0){//表单类型为checkbox
							$("input:checkbox[name='"+item+"']").each(function(index, ele){
								if($(ele).val()==data.data[item]){
									$(ele).prop("checked",true);
								}
							});
						}
					}else if($('[id="'+item+'"]').length>0){
						alert('页面错误，id='+item+'重复');
						return false;
					}
					
/*					if(item=='deviceFactoryId'){
						var select = $(document.getElementById('typeFactory.id'));
						select.find('option[value="'+data.data['deviceFactoryId']+'"]').prop('selected',true);
					}
					if(item=="branchId.branchId"){
						selectQuery(data.data["branchId.branchId"],"#edit-win");
						$('.branchId  option[value="'+data.data["branchId.branchId"]+'"]').attr("selected",true);
					}*/
					if(item=="branchId"){
						$(".branchId").val(data.data["branchId"]["branchId"]);
/*						selectQuery(data.data["branchId"]["branchId"],"#edit-win");
						
						$('.branchId  option[value="'+data.data["branchId"]["branchId"]+'"]').attr("selected",true);*/
					}else if(item=="deviceFactoryId"){
						$(".deviceFactoryId").val(data.data["deviceFactoryId"]["id"]);
						
					}else if(item=="typeFactory"){
						$(".typeFactory").val(data.data["typeFactory"]["id"]);
						
					}else if(item=="deviceTypeId"){
						$(".deviceTypeId").val(data.data["deviceTypeId"]["id"]);
					}
				}
				if(jEl.attr('id')=="detail"){
					if(item=="branchId"){
						$(".branchId").val(data.data["branchId"]["branchId"]);
/*						selectQuery(data.data["branchId"]["branchId"],"#edit-win");
						
						$('.branchId  option[value="'+data.data["branchId"]["branchId"]+'"]').attr("selected",true);*/
					}else if(item=="deviceFactoryId"){
						$(".deviceFactoryId").val(data.data["deviceFactoryId"]["id"]);
						
					}else if(item=="deviceTypeId"){
						$(".deviceTypeId").val(data.data["deviceTypeId"]["id"]);
					}
					//打开详情窗口
					openWindow('详情', width, height, contentDom);
					//禁用下拉搜索
					$('#edit-win .tinyselect').addClass('detailTinyselect');
					//禁用所有表单
					$('#edit-win input, #edit-win textarea, #edit-win select').attr('disabled','disabled');
					//$('#edit-win button').attr('disabled','disabled');
				}else{
					//打开编辑窗口
					openWindow('编辑', width, height, contentDom);
					//禁用下拉搜索
					$('#edit-win .tinyselect').removeClass('detailTinyselect');
					$('#edit-win input[type=hidden]').val(recordId);
					$('#edit-win input, #edit-win textarea, #edit-win select').removeAttr('disabled');
					$('#edit-win input, #edit-win textarea, #edit-win select').each(function(index, ele){
						if($(ele).attr('editabled')=="false"){
							$(ele).attr('disabled', 'disabled');
						}
					});
				}
			}else{
				alert('数据查询失败');
			}
		});
		//如果id等于详情
	}else if(jEl.attr('id')=="detailNew"){
		//隐藏用户登录名和密码
		$('.load-password-win').addClass('hideLoadPassword');
		$(contentDom).attr('type','detailNew');
		//得到行记录的id值
		var recordId = $("input[name='check-data']:checked").val();
		var recordIds = $("input[name='check-data']:checked").length;
		if(recordId==undefined||recordIds>1){
			alert("请先选择一条记录");
			return false;
		}
		
		goAjax(queryByIdUrl,{recordId:recordId},function(data){
			if(data.flag==0){
				//遍历data对象
				for(var item in data.data){
					//通过id设值只能是input:text, input:password, select, textarea，对于其他的要单独处理
					if($('[id="'+item+'"]').length==1){
						$('[id="'+item+'"]').val(data.data[item]);
					}else if($('[id="'+item+'"]').length==0){
						//其他类型的表单组件
						if($("input:checkbox[name='"+item+"']").length>0){//表单类型为checkbox
							$("input:checkbox[name='"+item+"']").each(function(index, ele){
								if($(ele).val()==data.data[item]){
									$(ele).prop("checked",true);
								}
							});
						}
					}else if($('[id="'+item+'"]').length>0){
						alert('页面错误，id='+item+'重复');
						return false;
					}
					if(item='branchId'){
						selectQuery(data.data["branchId"].branchId,"#edit-win");
						$('.branchId  option[value="'+data.data["branchId"].branchId+'"]').attr("selected",true);	
					
					}
					if(item=="branchId"){
						$(".branchId").val(data.data["branchId"]["branchId"]);
/*						selectQuery(data.data["branchId"]["branchId"],"#edit-win");
						
						$('.branchId  option[value="'+data.data["branchId"]["branchId"]+'"]').attr("selected",true);*/
					}else if(item=="deviceFactoryId"){
						$(".deviceFactoryId").val(data.data["deviceFactoryId"]["id"]);
						
					}else if(item=="deviceTypeId"){
						$(".deviceTypeId").val(data.data["deviceTypeId"]["id"]);
					}
				}
				//打开编辑窗口
				openWindow('详情', width, height, contentDom);
				if(jEl.attr('id')=="detailNew"){
					//禁用下拉搜索
					$('#edit-win .tinyselect').addClass('detailTinyselect');
					//禁用所有表单
					$('#edit-win input, #edit-win textarea, #edit-win select').attr('disabled','disabled');
					//$('#edit-win button').attr('disabled','disabled');
				} 
			}else{
				alert('数据查询失败');
			}
		});
	//如果属性为update-new	
	}else if(jEl.attr('id')=="updateNew"){
		//还原用户隐藏域和下拉搜索禁用
		$('#edit-win .tinyselect').removeClass('detailTinyselect');
		$('.load-password-win').addClass('hideLoadPassword');
		$(contentDom).attr('type','updateNew');
		 
		//得到行记录的id值
		var recordId = $("input[name='check-data']:checked").val();
		var recordIds = $("input[name='check-data']:checked").length;
		if(recordId==undefined||recordIds>1){
			alert("请先选择一条记录");
			return false;
		}
		
		goAjax(queryByIdUrl,{recordId:recordId},function(data){
			if(data.flag==0){
				//遍历data对象
				for(var item in data.data){
					if(item=="branchId"){
						selectQuery(data.data["branchId"].branchId,"#edit-win");
						$('.branchId  option[value="'+data.data["branchId"].branchId+'"]').attr("selected",true);
					}else if(item=="branchContactPeople"){
						$('#edit-win .contactPeople ul').find('li').each(function(i,e){
							$(e).removeClass('selected');
							if($(e).attr('data-value')==data.data["branchContactPeople"]){
								$(e).addClass('selected');
								$('#edit-win .contactPeople').find('div .selectbox').text($(e).text());
							}
						});
						$('.branchContactPeople  option[value="'+data.data["branchContactPeople"]+'"]').attr("selected",true);
					}else if(item=="secondServer"){
						$('.secondServerId  option[value="'+data.data["secondServer"]+'"]').attr("selected",true);
					}
					//通过id设值只能是input:text, input:password, select, textarea，对于其他的要单独处理
					
					if($('[id="'+item+'"]').length==1){
						$('[id="'+item+'"]').val(data.data[item]);
					}else if($('[id="'+item+'"]').length==0){
						//其他类型的表单组件
						if($("input:checkbox[name='"+item+"']").length>0){//表单类型为checkbox
							$("input:checkbox[name='"+item+"']").each(function(index, ele){
								if($(ele).val()==data.data[item]){
									$(ele).prop("checked",true);
								}
							});
						}
					}else if($('[id="'+item+'"]').length>0){
						alert('页面错误，id='+item+'重复');
						return false;
					}
					if(item=="branchId"){
						$(".branchId").val(data.data["branchId"]["branchId"]);
/*						selectQuery(data.data["branchId"]["branchId"],"#edit-win");
						
						$('.branchId  option[value="'+data.data["branchId"]["branchId"]+'"]').attr("selected",true);*/
					}else if(item=="deviceFactoryId"){
						$(".deviceFactoryId").val(data.data["deviceFactoryId"]["id"]);
						
					}else if(item=="deviceTypeId"){
						$(".deviceTypeId").val(data.data["deviceTypeId"]["id"]);
					}
				}
				
				//打开编辑窗口
				openWindow('编辑', width, height, contentDom);
				
					//禁用所有表单
				
					$('#edit-win input[type=hidden]').val(recordId);
					$('#edit-win input, #edit-win textarea, #edit-win select').removeAttr('disabled');
					$('#edit-win input, #edit-win textarea, #edit-win select').each(function(index, ele){
						if($(ele).attr('editabled')=="false"){
							$(ele).attr('disabled', 'disabled');
						}
					});
					//$('#edit-win button').removeAttr('disabled');
				
			}else{
				alert('数据查询失败');
			}
		});
		
	}
}

/**
 * 新增、创建确认提交时，可使用此方法进行默认提交
 */
var defaultSumitWindowEdit = function(contentDom){
	var url = "";
	if($(contentDom).attr('type')=="add"){
		url = addUrl;
	}else if($(contentDom).attr('type')=="update"){
		url = updateUrl;
	}else if($(contentDom).attr('type')=="updateNew"){
		url = updateUrl;
	}else{
		layer.closeAll();
		return false;
	}
	
	//表单验证
	var vflag = validateAnt(contentDom);
	if(!vflag){
		return false;
	}
	//生成请求参数
	var paramAjax = genReqData(contentDom);
	
    goAjax(url, paramAjax, function(data){
		if(data.flag!=0){
			alert(data.msg);
		}else{
			alert('操作成功');
			layer.closeAll();
			//刷新表数据
			pagerList({});
		}
		
	});
}

//根据查询参数获取表格数据
var pagerList = function(param){
	var tbAjaxUrl = pagerUrl;
	//得到所有单耗参考标准信息
	goAjax(tbAjaxUrl, param, function(data){
		if(data.flag!=0){
			alert('数据查询失败');
		}else{
			showTbData(data, null, true, tbAjaxUrl, param);
		}
		
	});
	
}

/**
 * 从查询条件栏中获得请求参数
 */
var getQueryParam = function(){
	//验证请求输入
	var vflag = validateAnt('.query-area');
	if(!vflag){
	
		return false;
	}
	//生成请求参数
	var param = genReqData('.query-area');
	
	var paramNew = {};
	if(param!=null && param!=undefined){
		for(item in param){
			if(item.endWith('-query')){
				var key = item.substring(0, item.indexOf('-query'));
				if(param[item]!=undefined && param[item]!=""){
					paramNew[key]=param[item];
				}
			}
		}
	}
	paramNew['reqParam.pageSize'] = $('#kkpager_size_val').val();
	return paramNew;
}

/**
 * 表格列排序提交时间
 */
var orderQueryFun = function(columnName, orderFlag){
	//验证请求输入
	var vflag = validateAnt('.query-area');
	if(!vflag){
	
		return false;
	}
	//生成请求参数
	var paramNew = getQueryParam();
	//添加要排序字段的名称和值
	if(paramNew==undefined){
		paramNew = {};
	}
	paramNew['reqParam.orderName'] = columnName;
	paramNew['reqParam.orderFlag'] = orderFlag;
	pagerList(paramNew);
}

/**
 * //单击批量编辑按钮
 */
var onUpdateBatchClick = function(){
	$('#update-batch').click(function(){
		//得到行记录的id值
		var recordId = _getTabeCheckItems();
		
		if(recordId==undefined || recordId.length==0){
			alert("请先选择要编辑的数据");
		}else{
			$('#tb-batch-data').html('');
			var htmHead = "<thead><tr>";
			var htmBody = "<tbody>";
			$(recordId).each(function(i, record){
				htmBody+="<tr>";
				//对于type=“hidden”和class=“none”不进行显示
				$('#edit-win input, #edit-win select, #edit-win textarea').each(function(index, ele){
					if($(ele).attr('type')=="hidden" || $(ele).hasClass('none')){
						//直接写入
						var eleHtml = $(ele).prop("outerHTML");
						var $ele = $(eleHtml);
						$ele.prop('id', $ele.prop('id')+"-"+record).prop('name', $ele.prop('name')+"-"+record);
						
						htmBody+=$ele.prop('outerHTML');
					}else if($(ele).attr('type')=="checkbox"){
						var eleHtml = $(ele).parent().prop("outerHTML");//注意parent一定是lable标签
						var $ele = $(eleHtml);
						$ele.children(":first").prop('id', $ele.children(":first").prop('id')+"-"+record);
						$ele.children(":first").prop('name', $ele.children(":first").prop('name')+"-"+record);
						if($(ele).attr('group-index')=="start"){
							if(i==0){
								var nameZn = $(ele).parent().parent().prev().html();//得到中文名称
								htmHead+='<th width="150">'+nameZn+'</th>';
							}
							htmBody+="<td style='width:400px;'>"+$ele.prop('outerHTML');
						}else if($(ele).attr('group-index')=="end"){
							htmBody+=$ele.prop('outerHTML')+"</td>";
						}else{
							htmBody+=$ele.prop('outerHTML');
						}
						
					}else{
						if(i==0){
							var nameZn = $(ele).parent().prev().html();//得到中文名称
							htmHead+='<th width="150">'+nameZn+'</th>';
						}
						
						var eleHtml = $(ele).prop("outerHTML");
						var $ele = $(eleHtml);
						$ele.prop('id', $ele.prop('id')+"-"+record).prop('name', $ele.prop('name')+"-"+record);
						
						htmBody+='<td>'+$ele.prop('outerHTML')+'</td>';
					}
				});
				htmBody+="</tr>"
			});
			$('#tb-batch-data').html(htmHead+htmBody);
			DIComponent();
			//上面是动态生成表单组件，现在是动态加载数据
			//遍历data对象
			for(var tbIndex in tbJsonItems){
				var record = tbJsonItems[tbIndex]['id'];
				for(var item in tbJsonItems[tbIndex]){
					//通过id设值只能是input:text, input:password, select, textarea，对于其他的要单独处理
					if($('[id="'+item+'-'+record+'"]').length==1){
						$('[id="'+item+'-'+record+'"]').val(tbJsonItems[tbIndex][item]);
					}else if($('[id="'+item+'-'+record+'"]').length==0){
						//其他类型的表单组件
						if($("input:checkbox[name='"+item+'-'+record+"']").length>0){//表单类型为checkbox
							$("input:checkbox[name='"+item+'-'+record+"']").each(function(index, ele){
								if($(ele).val()==tbJsonItems[tbIndex][item]){
									$(ele).prop("checked",true);
								}
							});
						}
					}else if($('[id="'+item+'-'+record+'"]').length>0){
						alert('页面错误，id='+item+'-'+record+'重复');
						return false;
					}
				}
			}
			
			//设置不可编辑组件
			$('#tb-batch-data input, #tb-batch-data textarea, #tb-batch-data select').each(function(index, ele){
				if($(ele).attr('editabled')=="false"){
					$(ele).attr('disabled', 'disabled');
				}
			});
			
			var _winIndex = openWindow('批量编辑', "100%", "100%", "#batch-edit-win");
			layer.full(_winIndex);//窗口全屏
			
			
			//批量编辑的提交按钮
			$('#batch-edit-btn-submit').unbind('click');
			$('#batch-edit-btn-submit').bind('click',function(){
				var saveDataAry=[];
				$('#tb-batch-data tbody tr').each(function(index, ele){
					//生成请求参数
					var param = genReqArray('#tb-batch-data tbody tr:eq('+index+')');
					var paramNew = {};
					if(param!=null && param!=undefined){
						for(item in param){
							var key = item.substring(0, item.indexOf('-'));
							if(param[item]!=undefined && param[item]!=""){
								paramNew[key]=param[item];
							}
						}
					}
					saveDataAry.push(paramNew);
				});
				
				//saveDataAry.push({achievement:"12354",address:"云南省昭通市昭阳区1432号",age:"1003",birthday:"1990-04-11",cellphone:"15987114465","headmaster":{id:"1"},id:"1",introduce:"我是一名大学生",name:"徐瑞",password:"cc",sex:"0",stuno:"2005080741",tuition:"1001.3"},{id:1, stuno:'ddd'},{id:1, stuno:'ddd'});
				$.ajax({ 
		            type:"POST", 
		            url:updateBatchUrl, 
		            dataType:"json",      
		            contentType:"application/json;charset=utf-8",               
		            data:JSON.stringify(saveDataAry), 
		            success:function(data){ 
		            	if(data!=undefined && data.flag==0){
		            		alert('编辑成功！');
		            		window.location.reload();
		            	}else{
		            		alert('编辑失败！');
		            	}
		            } 
		         });
			});
		}
	});
}

var enableDefaultFramework = function(editWinWidth, editWinHeight){
	/*数据查询逻辑，建议名称不能改变*/
	pagerList({});
	
	//条件查询
	$('#query').click(function(){
		//验证请求输入
		var vflag = validateAnt('.query-area');
		if(!vflag){
			return false;
		}
		//生成请求参数
		var paramNew = getQueryParam();
		pagerList(paramNew);
	});
	
	//高级查询
	$('#query-advance').click(function(){
		layer.open({
			type: 1,
			move:false,
			title:'高级条件查询',
			//skin: 'layui-layer-rim', //加上边框
			area: ['710px', "500px"], //宽高
			//offset: '30px',
			shade: [0.8, '#393D49'],
			zIndex : 3,
			content: $('#query-advance-win')
		});
	});
	
	//新增、编辑时打开窗口
	$('#add, #update, #detail,#updateNew').click(function(){
		//在新增、编辑弹窗时，建议调用此方法
		openWindowEditReady($(this), editWinWidth, editWinHeight, '#edit-win');
	});
	
	
	//新增、编辑表单输入后的提交按钮
	$('#edit-win-btn').click(function(){
		defaultSumitWindowEdit('#edit-win');
	});
	//删除按钮
	$('#delete').click(function(){
		
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
				goAjax(deleUrl, {"recordIds":recordIds}, function(data){
					if(data.flag!=0){
						alert(data.msg);
						alert("删除数据失败！");
						//刷新表数据
						pagerList({});
					}else{
						alert("删除数据成功！");
						//刷新表数据
						pagerList({});
					}
				});
	        }
		}
	});
	
	//上传按钮
	$("#fileimportbtn").click(function(){
		//阻止表单默认提交行为
		$("#importExcel").submit(function(e){
			e.preventDefault();
		});
		if($("#file").val()!=''){
			goAjaxFileupload(importExcelUrl,genFileuploadData('.compose-editor'),function(data){
				if(data.flag!=0){
					alert("导入失败");
				}else{
					alert("导入成功");
					layer.closeAll();
					//刷新表数据
					pagerList({});
				}
			});
		}else{
			layer.msg("请选择要上传的文件");
		}
	});
	//导入按钮
	$("#import").click(function(){
		$("#file").val('');
		layer.open({
			type: 1,
			move:false,
			title:"文件上传",
			shade: [0.8, '#393D49'],
			zIndex : 3,
			content:$("#fileup-win")
		});	 
	});
	
	//导出按钮
	$("#export").click(function(){
		dowonloadFile(exportExcelUrl);
	});
	
	//清除按钮
	$("#clear").click(function(){
		$("#file").val("");
	});
	
	//导出模板按钮
	$("#download").click(function(){
		dowonloadFile(excelModuleUrl);
	});
	
	//权限分配按钮
	/*$('#allocate-opt').click(function(){
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
				$('#allocate-roleName').val(response.data.roleName);
				$('#allocate-roleDesc').val(response.data.roleDesc);
				$('#allocate-roleId').val(response.data.roleId);
				//查询模块（Module）、交易(Transation)、操作(Opt)生成树
				goAjax(queryModuleUrl,{},function(response){
    				if(response.flag==0){
    					var module = response.data;
    					var trans = response.trans;
    					var opt = response.opt;
    					
        				zNodes = module;
        				//生成树
        		    	var tree = $.fn.zTree.init($("#power-list"), setting, zNodes);
        				//给树增加trans和opt表中的节点
        		    	addTransNodes(tree,trans);
        		    	addOptNodes(tree,opt);
        		    	//打开layer
            			openWindow('权限分配','600px','480px','#allocate-power-win');
        		    	//回显opt_role中的数据
        		    	goAjax(listOptRole,{roleId:$('#tb-data tbody input[name=check-data]:checked').val()},function(response){
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
	});*/
	
}

//全选选中和取消事件
function selectAll() {
	//#checkedAll是全选的那个checkbox
	if ($("#checkAll").prop("checked")) {
	//：checkbox  是选中了所有的<input> type为 checkbox的对象
		$(":checkbox").prop("checked", true);
	} else {
	    $(":checkbox").prop("checked", false);
	}
}

function selectAllByName(name) {
	//#checkedAll是全选的那个checkbox
	if ($("#checkAll").prop("checked")) {
		$("input[name='" + name + "']").prop("checked", true);
	} else {
		$("input[name='" + name + "']").prop("checked", false);
	}
}

/**
 * 高级条件查询
 * param - 表单对象实体
 */
var enableQueryAdvance = function(tbName){
	goAjax(webPath+'_advance/_getQueryAdvanceTb.html', {tbName:tbName}, function(data){
		var c = data;
		if(data!=undefined && data!=null){
			var vo = data.data;
			$('#query-advance-win').html('');
			if(vo!=undefined && vo!=null){
				for(var i in vo){
					var htm = '<div class="row">'+
					  '<div class="col-xs-3 tx-center"><label for="query-advance-'+vo[i][0]+'">'+vo[i][0]+'</label></div>'+
					  '<div class="col-xs-3">'+
					    '<select class="form-control" id="advance-query-'+vo[i][0]+'">'+
					      '<option value="">请选择表达式</option>'+
			              '<option value="1">等于（=）</option>'+
			              '<option value="2">like（匹配）</option>'+
			              '<option value="3">&lt;&gt;（不等于）</option>'+
			              '<option value="4">&gt;（大于）</option>'+
			              '<option value="5">&lt;（小于）</option>'+
			              '<option value="5">范围（between..and）</option>'+
			            '</select>'+
					  '</div>'+
					  '<div class="col-xs-6">'+
					    '<input type="text" class="form-control" id="advance-value-'+vo[i][0]+'" placeholder="范围表达式，请用&quot;,&quot;号隔开">'+
					  '</div>'+
					'</div>';
					$('#query-advance-win').append(htm);
				}
				var submitHtm = '<div class="row">'+
											'<div class="col-xs-12 tx-right top-default">'+
										'<lable class="radio-inline"><input type="radio" name="advance-plus-value" value="1" checked>且（AND）</lable>'+
										'<lable class="radio-inline"><input type="radio" name="advance-plus-value" value="2">或（OR）</lable>'+
										'<button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 确认并查询</button></div>'+
								'</div>';
				$('#query-advance-win').append(submitHtm);
			}
		}
	})
	
	
}

