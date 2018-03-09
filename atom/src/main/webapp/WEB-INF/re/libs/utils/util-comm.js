// JavaScript Document

String.prototype.startWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substr(0,str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  
String.prototype.endWith=function(str){  
    if(str==null||str==""||this.length==0||str.length>this.length)  
      return false;  
    if(this.substring(this.length-str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
}  

var isNull=function(str){  
    if(str==undefined || str==null || str==""){
    	return true;
    }else{
    	return false;
    }
}  

/**
 * 重置页面表单
 * @param dom
 */
var resetForm = function (dom) {
    $(dom+" input:text," +dom+" input:password," +dom+" input:hidden," +dom+" input:file").val('');
    $(dom+" input:checkbox," +dom+" input:radio").prop("checked",false);
    $(dom+" textarea").val('');
    $(dom+' select option[value=""]').prop("selected", "selected");
}
var sysAlert = function (msg){
	layer.alert(msg, {
        skin: 'layui-layer-lan'
        ,offset: '50px'
        ,closeBtn: 0
        ,shift: 0 //动画类型
    });
}

/**
 * 用于生成分页栏
 * pager-分页实体
 * ajaxUrl-请求分页数据的后台url路径，应带请求参数
 * paramUrl-请求参数对象
 * tb-Dom 要动态加载的table id标签
 */
var genPager = function(pager, ajaxUrl, paramUrl, tbDom, pagerid, order){
	if(pagerid==null){
		pagerid = 'kkpager';
	}
	
	
	if(pager!=null){
		kkpager.generPageHtml({
			pagerid:pagerid,
			pno : pager.currentPage+1,
			//总页码
			total : pager.totalPage,
			//总数据条数
			totalRecords : pager.rowCount,
			isShowTotalRecords:true,
			pageSize:pager.pageSize,
			mode : 'click',
			click : function(n){
				var kkPager_items_size = $('#kkpager_size_val').val();//每页显示记录数
				var ifReloadPager = false;
				
				if(kkPager_items_size!=this._config.pageSize){
					ifReloadPager = true;
				}
				
				paramUrl["currentPage"] = n-1; //动态添加属性
				if(kkPager_items_size!=undefined && kkPager_items_size!=null && kkPager_items_size!=""){
					paramUrl["pageSize"] = kkPager_items_size;
				}
		        //这里可以做自已的处理
				goAjax(ajaxUrl,paramUrl,function(data){
					if(data.flag!=0){
						sysAlert('数据查询失败');
					}else{
						if(order==null){
							if(ifReloadPager){
								showTbData(data, tbDom, true, ajaxUrl, paramUrl);	
							}else{
								showTbData(data, tbDom, false);	
							}
						}else if(order==2){
							showTbDataTotal(data, tbDom, false);	
						}
					}
				});
				
		        
		        //处理完后可以手动条用selectPage进行页码选中切换
		        this.selectPage(n);
		        $('#kkpager_size_val').val(kkPager_items_size);
		    }
		}, true);
		
	}
}


/**
 * 对ajax进行封装
 * tbAjaxUrl-ajax请求路径
 * paramUrl-请求参数
 * successFunc-成功之后的处理逻辑function
 */
var goAjax = function(tbAjaxUrl, paramUrl, successFunc, type){
	var index ;
	var reqType = "post";
	if(type!=null){
		reqType = type;
	}
	$.ajax({
		url:tbAjaxUrl,
		data:paramUrl,    
		type:reqType,    
		cache:false,    
		dataType:'json', 
		success: successFunc,    
		error : function(XMLHttpRequest, textStatus, errorThrown) {  
			alert("后台通信异常！");    
		},beforeSend:function(){
	    	index = openLoading();
	    },complete:function(jqXHR){
	    	closeLoading(index);
	    }
	});
}

/**
 * 对ajax进行封装
 * tbAjaxUrl-ajax请求路径
 * paramUrl-请求参数
 * successFunc-成功之后的处理逻辑function
 */
var goAjaxFileSubmit = function(url, dom, successFunc){
	$(dom).ajaxSubmit({  
        type: 'post',  
        url: url,   
        contentType: "application/x-www-form-urlencoded; charset=utf-8",   
        dataType:'json', 
        success: successFunc,
        error : function(XMLHttpRequest, textStatus, errorThrown) {  
			alert("后台通信异常！");    
		},beforeSend:function(){
	    	index = openLoading();
	    },complete:function(jqXHR){
	    	closeLoading(index);
	    }
  }); 
	
}

var goAjaxFileupload = function(url,formdataObj,successFunc){
	$.ajax({
		url:url,
		data:formdataObj,    
		type:'post',    
		dataType:'json',	
		cache:false,
		contentType:false,
		processData:false, 
		success: successFunc,    
		error : function(XMLHttpRequest, textStatus, errorThrown) {  
			alert("后台通信异常！");    
		},beforeSend:function(){
	    	index = openLoading();
	    },complete:function(jqXHR){
	    	closeLoading(index);
	    }
	});
}

/**
 * 得到非空字符串，一般对json的value进行处理
 */
var notNull = function(val){
	if(val==null || val==undefined || val=="null" || val=="NaN"){
		return '';
	}else{
		return val;
	}
}

var notNullDig = function(val){
	if(val==null || val==undefined || val=="null"){
		return 0;
	}else{
		return Number(val);
	}
}

/**
 * 自动生成ajax请求参数
 * key为id，radio时为name
 * @param parentDom 把parentDom元素的所有表单控件当为请求参数
 */
var genReqData = function(parentDom){
	var obj = {};
	var dataUnique = "";//存储唯一性属性
	$(parentDom+" input").each(function(index, element){
		var el = $(element);
		var type = el.attr('type');
		if(type=="text" || type=="hidden" || type=="password" || type=="number" || type=="email"){
			var key = el.attr('id');
			//如果key里包含有-edit，则将-edit替换为空
			if(key!=undefined&&key.indexOf('-edit')>=0){
				key = key.replace('-edit','');
			}
			if(el.val()!=undefined && el.val()!=""){
				if(el.attr('form-type')=='date'&&el.attr('form-format')=="yyyy-MM-dd"){
					obj[key] = el.val()+" 00:00:00";
				}else{
					obj[key] = el.val();
				}
			}
			//检查是否进行唯一性判断
			if(el.attr('unique')=="true"){
				dataUnique+=key+",";
			}
		}else if(type=="checkbox"){//多个checkbox用,分隔
			var key = el.attr('name');
			var val ='';
			if(key=="ifFolder"){
				$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i, e1){
					var jE1 = $(e1);
				    val =1 ;
				    obj[key] = val;
				});
				
			}if(key=="ifReturn"){
				$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i, e1){
					var jE1 = $(e1);
				    val =1 ;
				    obj[key] = val;
				});
			}else{
				$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i, e1){
					var jE1 = $(e1);
				    val += jE1.val() ;
				    val += ',';
				});
				if(val!="" && val!=null){
					val = val.substr(0, val.length-1);
					obj[key] = val;
				}
			}
		
		}else if(type=="radio"){
			var key = el.attr('name');
			var value = $('input:radio[name='+el.attr('name')+']:checked').val();
			if(value!=undefined && value!=""){
				obj[key] = value;
			}
		}else if(type="time"){
			var key = el.attr('id');
			obj[key] = el.val();
		}
		
	});
	
	$(parentDom+" select").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		//如果key里包含有-edit，则将-edit替换为空
		if(key!=undefined&&key.indexOf('-edit')>=0){
			key = key.replace('-edit','');
		}

			var value = el.val();
		
		
		if(value!=undefined && value!=""){
			obj[key] = value;
		}
	});
	
	$(parentDom+" textarea").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		var value = el.val();
		if(value!=undefined && value!=""){
			obj[key] = value;
		}
	});
	
	
   var treeObj=$.fn.zTree.getZTreeObj("power-list");
    if(treeObj!=null){
    	   var nodes=treeObj.getCheckedNodes(true);
    	    var v="";
    	    for(var i=0;i<nodes.length;i++){
    	    v+=nodes[i].id + ",";
    	    }
    	    obj["secondServerBranch"] = v;
    }
    
    var tree=$.fn.zTree.getZTreeObj("power-list-edit");
    if(tree!=null){
    	   var nodesA=tree.getCheckedNodes(true);
    	    var vA="";
    	    for(var i=0;i<nodesA.length;i++){
    	    vA+=nodesA[i].id + ",";
    	    }
    	    obj["secondServerBranch"] = vA;
    }
    var tree=$.fn.zTree.getZTreeObj("msg-tree");
    if(tree!=null){
    	   var nodesB=tree.getCheckedNodes(true);
    	    var vB="";
    	    for(var i=0;i<nodesB.length;i++){
    	    vB+=nodesB[i].id + ",";
    	    }
    	    obj["msgRange"] = vB;
    }
 

	
	obj['validateParam.uniqueT']=dataUnique;
	
	return obj;
}

var genReqArray = function(parentDom){
	var obj = {};
	$(parentDom+" input").each(function(index, element){
		var el = $(element);
		var foreignKey = el.attr('foreign-key');//判断是否为外键的主键值
		var type = el.attr('type');
		if(type=="text" || type=="hidden" || type=="password" || type=="number" || type=="email"){
			var key = el.attr('id');
			if(el.val()!=undefined && el.val()!=""){
				if(foreignKey=="true"){
					key = key.substring(0, key.indexOf('.id'));
					obj[key] = {"id":el.val()};
				}else{
					obj[key] = el.val();
				}
			}
		}else if(type=="checkbox"){//多个checkbox用,分隔
			var key = el.attr('name');
			var val ='';
			if(key=="ifFolder"){
				$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i,e1){
					var jE1 = $(e1);
				    val = 1 ;
				    obj[key] = val;
				});
			}else{
				$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i, e1){
					var jE1 = $(e1);
				    val += jE1.val() ;
				    val += ',';
				});
				if(val!="" && val!=null){
					val = val.substr(0, val.length-1);
					obj[key] = val;
				}
			}
			
			
		
		}else if(type=="radio"){
			var key = el.attr('name');
			var value = $('input:radio[name='+el.attr('name')+']:checked').val();
			if(value!=undefined && value!=""){
				if(foreignKey=="true"){
					key = key.substring(0, key.indexOf('.id'));
					obj[key] = {"id":value};
				}else{
					obj[key] = value;
				}
			}
		}
		
	});
	
	$(parentDom+" select").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		var value = el.val();
		var foreignKey = el.attr('foreign-key');//判断是否为外键的主键值
		if(foreignKey=="true"){
			var keyNew = key.substring(0, key.indexOf('.id'))+"-";
			if(value!=undefined && value!=""){
				obj[keyNew] = {"id":value};
			}
		}else{
			if(value!=undefined && value!=""){
				obj[key] = value;
			}
		}
	});
	
	$(parentDom+" textarea").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		var value = el.val();
		if(value!=undefined && value!=""){
			obj[key] = value;
		}
	});
	return obj;
}

/*
 * 自动生成带文件上传元素的ajax请求参数
 */
var genFileuploadData = function(parentDom){
	var formdata = new FormData();
	$(parentDom+" input").each(function(index, element){
		var el = $(element);
		var type = el.attr('type');
		if(type=="text" || type=="hidden" || type=="password" || type=="number"){
			var key = el.attr('id');
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,el.val());
			}else{
				formdata.append(key,el.val());
			}
			
		}else if(type=="checkbox"){//多个checkbox用,分隔
			var key = el.attr('name');
			var val ='';
			$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i, e1){
				var jE1 = $(e1);
			    val += jE1.val() ;
			    val += ',';
			});
			if(val!=null){
				val = val.substr(0, val.length-1);
			}
			formdata.append(key,val);
		}else if(type=="radio"){
			var key = el.attr('name');
			formdata.append(key, $('input:radio[name='+el.attr('name')+']:checked').val());
		}else if(type=="select"){
			var key=el.attr("id");
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,$(ele).children(':selected').val());
			}else{
				formdata.append(key,$(ele).children(':selected').val());
			}
			
		}else if(type=="textarea"){
			var key=el.attr("id");
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,el.val());
			}else{
				formdata.append(key,el.val());
			}
			
		}else if(type=="file"){
			var key=el.attr("id");
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,el.get(0).files[0]);
			}else /*if(key!=undefined&&key.indexOf("-more")>=0){
				key = key.substring(0,key.indexOf("-more"));
				formdata.append(key,el.get(0).files[0]);
			}else{*/
				{
				formdata.append(key,el.get(0).files[0]);
			}
			
		}
	});
	
	$(parentDom+" select").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		if(key.indexOf("-edit")>=0){
			key = key.replace("-edit","");
			formdata.append(key,el.val());
		}else{
			formdata.append(key,el.val());
		}
		
	});
	
	$(parentDom+" textarea").each(function(index, element){
		var el = $(element);
		formdata.append(key,el.val());
		//formdata[key] = el.val();
		
		var key=el.attr("id");
		if(key!=undefined&&key.indexOf("-edit")>=0){
			key = key.replace("-edit","");
			formdata.append(key,el.val());
		}else{
			formdata.append(key,el.val());
		}
	});
	return formdata;
}

//媒体上传，多文件
var genMediaFileuploadData = function(parentDom){
	var formdata = new FormData();
	var fileArr = new Array();
	var mediaDatailVal='';
	$(parentDom+" input").each(function(index, element){
		var el = $(element);
		var type = el.attr('type');
		if(type=="text" || type=="hidden" || type=="password" || type=="number"){
			$(parentDom+" input:text[name='mediaDatail']").each(function(i, e1){
				var jE1 = $(e1);
			    val = jE1.val() ;
			    mediaDatailVal += val+'#';
			    //获得内容，将内容用“#”号分隔开
			});
			var key = el.attr('name');
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,mediaDatailVal);
			}else{
				formdata.append(key,mediaDatailVal);
			}
			
		}else if(type=="checkbox"){//多个checkbox用,分隔
			var key = el.attr('name');
			var val ='';
			$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i, e1){
				var jE1 = $(e1);
			    val += jE1.val() ;
			    val += ',';
			});
			if(val!=null){
				val = val.substr(0, val.length-1);
			}
			formdata.append(key,val);
		}else if(type=="radio"){
			var key = el.attr('name');
			formdata.append(key, $('input:radio[name='+el.attr('name')+']:checked').val());
		}else if(type=="select"){
			var key=el.attr("id");
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,$(ele).children(':selected').val());
			}else{
				formdata.append(key,$(ele).children(':selected').val());
			}
			
		}else if(type=="textarea"){
			var key=el.attr("id");
			formdata.append(key,el.val());
		}else if(type=="file"){
			
			var key=el.attr("name");
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,el.get(0).files[0]);
			}else /*if(key!=undefined&&key.indexOf("-more")>=0){
				key = key.substring(0,key.indexOf("-more"));
				formdata.append(key,el.get(0).files[0]);
			}else{*/
				{
				formdata.append(key,el.get(0).files[0]);
			}
			
		}
	});
	
	$(parentDom+" select").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		if(key!=undefined&&key.indexOf("-edit")>=0){
			key = key.replace("-edit","");
			formdata.append(key,el.val());
		}else{
			formdata.append(key,el.val());
		}
		
	});
	
	$(parentDom+" textarea").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		formdata.append(key,el.val());
		//formdata[key] = el.val();
	});
	formdata.append("ifZip",1);
	return formdata;
}


//媒体上传，多文件
var genMediaFileuploadData12 = function(parentDom){
	var formdata = new FormData();
	var fileArr = new Array();
	var mediaDatailVal='';
	var dataUnique = "";//存储唯一性属性
	$(parentDom+" input").each(function(index, element){
		var el = $(element);
		var type = el.attr('type');
		if(type=="text" || type=="hidden" || type=="password" || type=="number"){
			
			var key = el.attr('name');
			formdata.append(key, el.val());
/*			$(parentDom+" input:text[name='"+el.attr('name')+"']").each(function(i, e1){
				var jE1 = $(e1);
			    val = jE1.val() ;
			    mediaDatailVal += val+'#';
			    //获得内容，将内容用“#”号分隔开
			});
			var key = el.attr('name');
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,mediaDatailVal);
			}else{
				formdata.append(key,mediaDatailVal);
			}*/
			if(el.attr('unique')=="true"){
				dataUnique+=key+",";
			}
		}else if(type=="checkbox"){//多个checkbox用,分隔
			var key = el.attr('name');
			var val ='';
			$(parentDom+" input:checkbox[name='"+el.attr('name')+"']:checked").each(function(i, e1){
				var jE1 = $(e1);
			    val += jE1.val() ;
			    val += ',';
			});
			if(val!=null){
				val = val.substr(0, val.length-1);
			}
			formdata.append(key,val);
		}else if(type=="radio"){
			var key = el.attr('name');
			formdata.append(key, $('input:radio[name='+el.attr('name')+']:checked').val());
		}else if(type=="select"){
			var key=el.attr("id");
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,$(ele).children(':selected').val());
			}else{
				formdata.append(key,$(ele).children(':selected').val());
			}
			
		}else if(type=="textarea"){
			var key=el.attr("id");
			formdata.append(key,el.val());
		}else if(type=="file"){
			
			var key=el.attr("name");
			
			if(key!=undefined&&key.indexOf("-edit")>=0){
				key = key.replace("-edit","");
				formdata.append(key,el.get(0).files[0]);
			}else /*if(key!=undefined&&key.indexOf("-more")>=0){
				key = key.substring(0,key.indexOf("-more"));
				formdata.append(key,el.get(0).files[0]);
			}else{*/
				{
				formdata.append(key,el.get(0).files[0]);
			}
			
		}
	});
	
	$(parentDom+" select").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		if(key!=undefined&&key.indexOf("-edit")>=0){
			key = key.replace("-edit","");
			formdata.append(key,el.val());
		}else{
			formdata.append(key,el.val());
		}
		
	});
	
	$(parentDom+" textarea").each(function(index, element){
		var el = $(element);
		var key = el.attr('id');
		formdata.append(key,el.val());
		//formdata[key] = el.val();
	});
	formdata.append("ifZip",1);
	formdata.append("validateParam.uniqueT",dataUnique);
	return formdata;
}

var closePage = function(){
	self.opener=null;
	self.close();
}

var openLoading = function(){
	var index = layer.load(1, {
		offset: '200px',
	    shade: [0.9,'#fff'] //0.1透明度的白色背景
	});
	return index;
}

var closeLoading = function(index){
	layer.close(index);
}

var toThousands = function(num) {
	if(!isNaN(num)){//传入的值是数字的话执行如下的步骤
		var num = (num).toString(), result = '';
		var numlength = num.indexOf(".");
		var arrStr ='';
		var arrfloat = '';
		var newnum = '';
		if(num.indexOf(".")==-1){
			
			while (num.length > 3) {
		        result = ',' + num.slice(-3) + result;//slice(-3)截取末尾三个数
		        num = num.slice(0, num.length - 3);
		    }
		    if (num) { result = num + result; }
		    return result;
		}else{
			newnum = num.split(".");
			arrStr = newnum[0];
		    arrfloat = newnum[1];
			while (arrStr.length > 3) {
			    
		        result = ',' + arrStr.slice(-3) + result;
		        arrStr = arrStr.slice(0, arrStr.length - 3);
		        
		    }
		    if (arrStr) { result = arrStr + result+'.'+arrfloat.slice(0,2);		     }
		    return result;
		}
	}else{
		return num;
	}
}

//金额转换
var Convert = function(money, id) {
    var s = money; //获取小数型数据
     s += "";
    if(!isNaN(money)&&money!=''){//判断输入的是否是数字
    	   if (s.indexOf(".") == -1) s +='.0'; //如果没有小数点，在后面补个小数点和0
    	    if (/\.\d$/.test(s)) s += "0"; //正则判断
    	    if (/\.$/.test(s)) s += "00"; //正则判断
    	    return s;
    	}else{
    	   return s;
    	}
}

/**
 * 弹出层
 */
var openWindow = function(title, width, height, contentDom){
	var index = layer.open({
		type: 1,
		move:false,
		title:title,
		//skin: 'layui-layer-rim', //加上边框
		area: [width, height], //宽高
		//offset: '30px',
		shade: [0.8, '#393D49'],
		zIndex : 3,
		content: $(contentDom),
		cancel: function(index, layero){ 
		  layer.closeAll();
		  return false; 
		},
	});
	return index;
}

//文件下载
var dowonloadFile = function(url){
	var form=$("<form></form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method","post");
	form.attr("action",url);
	$("body").append(form);//将表单放置在web中
	form.submit();//表单提交 
}

//编辑时的下拉搜索回显
var selectQuery=function(data,contentDom){
	$(contentDom+' .itemcontainer').find('li').each(function(i,e){
		$(e).removeClass('selected');
		if($(e).attr('data-value')==data){
			$(e).addClass('selected');
			$(contentDom).find('.selectbox').text($(e).text());
		}
	});
}
