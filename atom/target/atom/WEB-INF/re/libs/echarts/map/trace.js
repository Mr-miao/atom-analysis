/**
 * 对应“物流监控”，“印刷监控”  *
 */
var namespacecloudPrint = Founder.namespace("rp.cloudPrint.trace");
namespacecloudPrint.productionOrderPage=function(){
	this.condition={'libId':'','createBeginTime':'','createEndTime':'','completeBeginTime':"",'completeEndTime':"",'orderNo':'','publisherId':'','name':'','printShopId':'','isWarningOrder':false};  //查询条件
	this.sortColumn="orderNo";  //排序字段  默认为创建时间
	this.sortDir="desc";  // 升序(asc),降序(desc)，默认为降序
	this.currPageIndex=0;   //当前页码 从0开始
	this.pageSize=10;   //每页条数
	this.state=-1;   //订单状态    （生产中订单：-1 ；印前中订单： 40;印刷中订单：41；印后中订单：42）
	this.isPageInit=false;   //是否为初始化分页列表
	this.init=function(){	
		$('.select2:visible').select2({allowClear: true});
		this.initPage();
		this.initDialog();
		this.appendEvent();
		this.event();		
	}; 	
	
	/**
	 * 初始化分页列表
	 */
	this.initPage=function(){
		this.isPageInit=true;
		var obj=this;
		
		$.pagination($("#totalRecords").text(),obj.currPageIndex,obj.pageSize,function(pageIndex,container){
			if(obj.isPageInit)
			{
				obj.isPageInit=false;					
			}
		else
			{					
			   obj.currPageIndex=pageIndex;
				obj.getData();
			}
		});
	};     
	this.initDialog=function(){
		$('#div-feedback').dialog({
			width:420,
			resizable:false,
			autoOpen:false,
			dialogClass:'ui-dialog-blue',
			modal:true,
			buttons:[{
				'class':'btn blue',
				'text':'确定',
				click:function(){					
					$(this).dialog('close');
				}
			}]
		});  //初始化反馈信息对话框	
		$('#div-info-dialog').dialog({
			autoOpen:false,
			dialogClass:'ui-dialog-blue',
			title:'提示',
			modal:true,			
			buttons:[{
				'class':'btn blue',
				'text':'确定',
				click:function(){
					$(this).dialog('close');
				}
			}]
		});   //初始化dialog
	};  
	this.appendEvent=function(){
		$('.portlet-body').on('click','.append',function(){
			var e=e ||window.event;
		   $.getJSONAsync('isAppend.action','POST',{'mediaId':$(this).parents('tr').data('mediaid')},'json',false, function(data){
			   if(data==0)
				   {
					   $('#div-info-dialog').dialog('open').html('该产品已被删除,无法加印');
					   e.preventDefault?e.preventDefault()():e.returnValue=false;
				   }	   	  

		   },function(){
			   $('#div-info-dialog').dialog('open').html('系统错误,无法加印');
			   e.preventDefault?e.preventDefault()():e.returnValue=false;
		   });
		});  //可否加印
		
	};   //注册条件中的事件

	this.event=function(){
		var obj=this;	
		$('.item-orderType').on('click','li',function(){
			if(!$(this).hasClass('active'))
			{
				$(this).addClass('active').siblings().removeClass('active');
				obj.condition.libId=$(this).data('libid')|| '';
				obj.currPageIndex=0;
				obj.getData();
			}
		});  //订单类别筛选
		$('.item-time:visible').on('click','li',function(){
			if($(this).hasClass('active'))
				{
					$(this).removeClass('active');
					if($(this).closest('.right').hasClass('createDate'))
						{
							obj.condition.createBeginTime='';
							obj.condition.createEndTime='';
						}
					else
						{
							obj.condition.completeBeginTime='';
							obj.condition.completeEndTime='';
						}
				}
			else
				{
					$(this).addClass('active').siblings().removeClass('active');
					if($(this).closest('.right').hasClass('createDate'))
						{
							if(this.id=='today')
								{
									obj.condition.createBeginTime=getToday()+' 0:0:0';
									obj.condition.createEndTime=getToday()+' 23:59:59';
								}
							else if(this.id=='yesterday')
								{
									obj.condition.createBeginTime=formatYesterday()+' 0:0:0';
									obj.condition.createEndTime=formatYesterday()+' 23:59:59';
								}
							else if(this.id=='week')
								{
									obj.condition.createBeginTime=getWeekStartDate()+' 0:0:0';
									obj.condition.createEndTime=getWeekEndDate()+' 23:59:59';
								}
							else
								{
									obj.condition.createBeginTime=getMonthStartDate()+' 0:0:0';
									obj.condition.createEndTime=getMonthEndDate()+' 23:59:59';
								}
						}
					else
						{
							if(this.id=='today')
							{
								obj.condition.completeBeginTime=getToday()+' 0:0:0';
								obj.condition.completeEndTime=getToday()+' 23:59:59';
							}
							else if(this.id=='yesterday')
							{
								obj.condition.completeBeginTime=formatYesterday()+' 0:0:0';
								obj.condition.completeEndTime=formatYesterday()+' 23:59:59';
							}
						   else	if(this.id=='week')
							{
								obj.condition.completeBeginTime=getWeekStartDate()+' 0:0:0';
								obj.condition.completeEndTime=getWeekEndDate()+' 23:59:59';
							}
							else
							{
								obj.condition.completeBeginTime=getMonthStartDate()+' 0:0:0';
								obj.condition.completeEndTime=getMonthEndDate()+' 23:59:59';
							}
						}					
				}
			obj.currPageIndex=0;
			obj.getData();
		});  // //日期筛选
		$('#btnSearch').click(function(){
			obj.condition.libId=$('.item-orderType li.active').data('libid')||'';
			var createBeginTime=$('.createDate #txtStart').val();
			var createEndTime= $('.createDate #txtEnd').val();
			var completeBeginTime=$('.completeDate #txtStart').val();
			var completeEndTime= $('.completeDate #txtEnd').val();
			if(createBeginTime!='' || createEndTime!='')
				{
					$('.item-time:visible .createDate li').removeClass('active');
					obj.condition.createBeginTime=createBeginTime==''?'':createBeginTime+' 0:0:0';
					obj.condition.createEndTime=createEndTime==''?'':createEndTime+' 23:59:59';
				}
			else if($('.createDate li.active').length==0)
				{
					obj.condition.createBeginTime='';
					obj.condition.createEndTime='';
				}
			if(completeBeginTime!='' || completeEndTime!='')
			{
				$('.item-time:visible .completeDate li').removeClass('active');
				obj.condition.completeBeginTime=completeBeginTime==''?'':completeBeginTime+' 0:0:0';
				obj.condition.completeEndTime=completeEndTime==''?'':completeEndTime+' 23:59:59';
			}
			else if($('.completeDate li.active').length==0)
			{
				obj.condition.completeBeginTime='';
				obj.condition.completeBeginTime='';
			}
			obj.condition.orderNo=$.trim($('#txtOrderNo').val());
			obj.condition.publisherId=$('#publisher').val() || '';			
			obj.condition.name=escape($.trim($('#txtName').val()));
			obj.condition.printShopId=$('#printShop').val();
			obj.currPageIndex=0;
			obj.getData();
		}); //查询
		$('#btnReset').click(function(){
			$('#txtOrderNo').val('');
			$('#txtName').val('');
			$('#publisher').val('').select2({allowClear:true});
			$('#printShop').val('').select2({allowClear:true});
			obj.condition.orderNo=obj.condition.name=obj.condition.publisherId=obj.condition.printShopId='';
			obj.currPageIndex=0;
			obj.getData();
		});  //重置
		$('#page-ctn .portlet-title :checkbox ').change(function(){
			if($(this).is(':checked'))
				{
					obj.condition.isWarningOrder=true;
				}
			else
				{
					obj.condition.isWarningOrder=false;
				}
			obj.currPageIndex=0;
			obj.getData();
		}); //预警订单筛选
		$("#page-ctn .portlet-body").on("click","table>thead th",function(e){	
			if($(this).index()!=1 && $(this).index()!=5 &&  $(this).index()!=6)
				return;
			obj.sortColumn=$.trim($(this).data('columnname'));					
			if($(this).is(".sorting_desc"))
				{					
					obj.sortDir="asc";
				}
			else 
				{					
					obj.sortDir="desc";
				}				
			obj.currPageIndex=0;			
			obj.getData();
			
		});   //排序
		$('#page-ctn .portlet-body').on('click','.btn-feedback',function(){
			obj.getFeedBack($(this).parents('tr').data('id'));
		});  //查看反馈信息	
		$("a.orderState").on("click",'',function(){
			obj.state=$(this).data('id');
			var title=$(this).find('.visual').html();
			$("#page-ctn .portlet-title .caption").html(title+"订单");
			obj.currPageIndex=0;			
			obj.getData();
		});//查看不同状态的订单
	};
	this.getFeedBack=function(orderId){
		$.getJSONNoCache("getFeedBack.action","GET",{'orderId':orderId},"text",false,function(data){			
			  $("#div-feedback").html(data);    //替换内容		
			  $("#div-feedback").dialog('open');
			});	
	};  //获得订单反馈信息
	
	/**
	 * ajax请求数据	
	 */
	this.getData=function(){
		var obj=this;
		var portlet=$('#page-ctn .portlet');
		App.blockUI(portlet);
		var data={"state":this.state, "sortColumn":this.sortColumn, "sortDir":this.sortDir,"pageSize":this.pageSize,"pageIndex":this.currPageIndex};
		$.getJSONNoCache("printOrderReturn.action","GET",$.extend(data,this.condition),"text",false,function(data){
			 App.unblockUI(portlet);
			  $("#page-ctn .portlet-body").html(data);    //替换内容			
				obj.initPage();          //重新初始化分页列表
			},
			function(){
				App.unblockUI(portlet);
            	$("#page-ctn .portlet-body").html("<p class='errorMsg'>获取数据失败，刷新页面试试</p>");
			});		
		
	};  
	
};   //印刷监控
namespacecloudPrint.logisticsTracePage=function(){
	this.condition={'libId':'','dispatchBeginTime':'','dispatchEndTime':'','orderNo':'','publisherId':'','name':'','provinceId':'','cityId':'','isWarningOrder':false};  //订单查询条件
	this.printShop={'isPageInit':true,'pageSize':10,'currPageIndex':0,'provinceName':'','printShopName':''};
	this.order={'isPageInit':true,'pageSize':10,'currPageIndex':0,'printShopId':''};
	this.sortColumn="orderNo";  //排序字段  默认为创建时间
	this.sortDir="desc";  // 升序(asc),降序(desc)，默认为降序
	this.init=function(){
		$('.select2:visible').select2({allowClear: true});
		this.initMap();
		this.initOrderPage();
		this.initPrintShopPage();
		this.event();
		var productionOrder=new namespacecloudPrint.productionOrderPage();
		productionOrder.initDialog();
		productionOrder.appendEvent();
		var table=$("#all")[0];
		var i=0;
		for(i=0;i<table.rows[0].cells.length;i++){
			if(table.rows[0].cells[i].innerHTML=="产品名称")
				break;
		}
		for(var j=1;j<table.rows.length;j++){
			if(table.rows[j].cells[i].innerHTML.length>10){
				table.rows[j].cells[i].innerHTML=table.rows[j].cells[i].innerHTML.substr(0,15)+"...";
			}
		}
	};
	this.initPrintShopPage=function(){		
		var obj=this;
		obj.printShop.isPageInit=true;
		$("#printShop #pagination").pagination($("#printShop #totalRecords").text(), {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 4, //主体页数
			current_page:obj.printShop.currPageIndex,
			items_per_page:obj.printShop.pageSize,
			prev_text: "前一页",
			next_text: "后一页",
			callback:function(pageIndex,container){
				if(obj.printShop.isPageInit)
					{
						obj.printShop.isPageInit=false;					
					}
				else
					{						
					   obj.printShop.currPageIndex=pageIndex;
						obj.getPrintShopData();
					}
				 
			}			
		});	
	};  //印厂分页
	this.initOrderPage=function(){
		var obj=this;
		obj.order.isPageInit=true;
		$("#order #pagination").pagination($("#order #totalRecords").text(), {
			num_edge_entries: 1, //边缘页数
			num_display_entries: 4, //主体页数
			current_page:obj.order.currPageIndex,
			items_per_page:obj.order.pageSize,
			prev_text: "前一页",
			next_text: "后一页",
			callback:function(pageIndex,container){
				if(obj.order.isPageInit)
					{
						obj.order.isPageInit=false;					
					}
				else
					{						
					   obj.order.currPageIndex=pageIndex;
						obj.getOrderData();
					}
				 
			}			
		});	
	};  //订单分页
	this.event=function(){
		var obj=this;
		$('.item-orderType').on('click','li',function(){
			if(!$(this).hasClass('active'))
			{
				$(this).addClass('active').siblings().removeClass('active');
				obj.condition.libId=$(this).data('libid')|| '';
				obj.order.currPageIndex=0;
				obj.getOrderData();
			}	
		});  //订单类别筛选
		$('.item-time').on('click','li',function(){
			if($(this).hasClass('active'))
				{
					$(this).removeClass('active');					
					obj.condition.dispatchBeginTime='';
					obj.condition.dispatchEndTime='';	
				}
			else
				{
					$(this).addClass('active').siblings().removeClass('active');				
					if(this.id=='today')
					{
						obj.condition.dispatchBeginTime=getToday()+' 0:0:0';
						obj.condition.dispatchEndTime=getToday()+' 23:59:59';
					}
					else if(this.id=='yesterday')
					{
						obj.condition.dispatchBeginTime=formatYesterday()+' 0:0:0';
						obj.condition.dispatchEndTime=formatYesterday()+' 23:59:59';
					}
				   else	if(this.id=='week')
					{
						obj.condition.dispatchBeginTime=getWeekStartDate()+' 0:0:0';
						obj.condition.dispatchEndTime=getWeekEndDate()+' 23:59:59';
					}
					else
					{
						obj.condition.dispatchBeginTime=getMonthStartDate()+' 0:0:0';
						obj.condition.dispatchEndTime=getMonthEndDate()+' 23:59:59';
					}										
				}
			obj.order.currPageIndex=0;
			obj.getOrderData();
		});  // //日期筛选
		$('#btnSearch').click(function(){
			obj.condition.libId=$('.item-orderType li.active').data('libid')||'';
			var dispatchBeginTime=$('.dispatchDate #txtStart').val();
			var dispatchEndTime= $('.dispatchDate #txtEnd').val();			
			if(dispatchBeginTime!='' || dispatchEndTime!='')
				{
					$('.item-time:visible .dispatchDate li').removeClass('active');
					obj.condition.dispatchBeginTime=dispatchBeginTime==''?'':dispatchBeginTime+' 0:0:0';
					obj.condition.dispatchEndTime=dispatchEndTime==''?'':dispatchEndTime+' 23:59:59';
				}
			else if($('.createDate li.active').length==0)
				{
					obj.condition.dispatchBeginTime='';
					obj.condition.dispatchEndTime='';
				}		
			obj.condition.orderNo=$.trim($('#txtOrderNo').val());
			obj.condition.publisherId=$('#publisher').val() || '';			
			obj.condition.name=escape($.trim($('#txtName').val()));
			obj.condition.provinceId=$('#province').val();
			obj.condition.cityId=$('#city').val();
			obj.order.currPageIndex=0;
			obj.getOrderData();
		}); //查询
		$('#btnReset').click(function(){
			$('#txtOrderNo').val('');
			$('#txtName').val('');
			$('#publisher').val('').select2({allowClear:true});
			$('#province').val('').select2({allowClear:true});
			$('#city').val('').select2({allowClear:true});
			obj.condition.orderNo=obj.condition.name=obj.condition.publisherId=obj.condition.provinceId=obj.condition.cityId='';
			obj.order.currPageIndex=0;
			obj.getOrderData();
		});  //重置		
		$('#province').change(function(){
			var provinceId=$(this).val();
			if(provinceId!='')
				{		
			
					$.getJSONNoCache("getCityListByProvinceId.action","GET",{'provinceId':provinceId},"json",false,function(data){
						var options='<option></option>';
						$.each(data,function(){
							options+="<option value="+this.id+">"+this.name+"</option>";
						});
						$('#city').html(options).select2({allowClear:true});						
					});
				}
		});
		$('#order .portlet-title :checkbox ').change(function(){
			if($(this).is(':checked'))
				{
					obj.condition.isWarningOrder=true;
				}
			else
				{
					obj.condition.isWarningOrder=false;
				}
			obj.order.currPageIndex=0;
			obj.getOrderData();
		}); //安全印制文件订单筛选
		$("#order .portlet-body").on("click","table>thead th",function(e){	
			if($(this).index()!=1 && $(this).index()!=6 &&  $(this).index()!=8)
				return;
			obj.sortColumn=$.trim($(this).data('columnname'));					
			if($(this).is(".sorting_desc"))
				{					
					obj.sortDir="asc";
				}
			else 
				{					
					obj.sortDir="desc";
				}				
			obj.order.currPageIndex=0;
			obj.order.printShopId=$(this).parents('tr').data('id');
			obj.getOrderData();
		});   //排序
		$('#order .portlet-body').on('click','.btn-feedback',function(){
			(new namespacecloudPrint.productionOrderPage).getFeedBack($(this).parents('tr').data('id'));
		});  //查看反馈信息	
		$('#printShop .portlet-body').on('click','.orderHref',function(){
			obj.order.currPageIndex=0;
			obj.order.printShopId=$(this).parents('tr').data('id');
			obj.getOrderData();
			return false;
		});  //查看印厂对应的订单
		$('#href-search').click(function(){
			var val=$.trim($('#printShopName').val());
			if(val==$('#printShopName').attr('placeholder'))
				{
					val='';
				}
			obj.printShop.printShopName=escape(val);
			obj.getPrintShopData();
		});  //根据关键字搜索
		$('#href-allPrint').click(function(){
			obj.printShop.provinceName='';
			obj.printShop.printShopName='';
            obj.printShop.currPageIndex=0;
            obj.order.currPageIndex=0;
			obj.order.printShopId='';			
            obj.getPrintShopData();
            obj.getOrderData();
		}); //所有印厂、订单
	};  
	this.initMap=function(){
		var obj=this;
		var data=[];
		$('#printShopNum option').each(function(){
			data.push({'name':this.value,'value':Number(this.innerHTML)});
		});
		 // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('map'));         
        var option = {
        		legend:{
        			x:'left',
        			data:['印厂总数']
        		},
        	    tooltip : {
        	        trigger: 'item',
        	        formatter:'印厂总数<br/>{b}:{c}'
        	    },
        	    series : [
        	        {
        	            name: '印厂总数',
        	            type: 'map',
        	            mapType: 'china',
        	            selectedMode : 'single',
        	            itemStyle:{
        	                normal:{label:{show:true}},
        	                emphasis:{label:{show:true}}
        	            },
        	            data:data
        	        }
        	    ]
        	};
        // 为echarts对象加载数据 
        myChart.setOption(option);       
        //点击
        myChart.on( echarts.config.EVENT.MAP_SELECTED, function (param){
            var selected = param.selected;
             var str='';
            for (var p in selected) {
                if (selected[p]) {
                    str = p;//省
                    //alert(str);
                }
            }
            if(str!=''&&str!='南海诸岛')
            	{
		            obj.printShop.provinceName=escape(str);
		            obj.printShop.currPageIndex=0;
		            obj.getPrintShopData();
            	}
           
        });
		
	}; //初始化地图
	this.getPrintShopData=function(){
		var obj=this;
		var portlet=$('#printShop .portlet');
		App.blockUI(portlet);
		var data={"pageSize":this.printShop.pageSize,"pageIndex":this.printShop.currPageIndex,'province':this.printShop.provinceName,'printShopName':this.printShop.printShopName};
		$.getJSONNoCache("printShopReturn.action","GET",data,"text",false,function(data){
			 App.unblockUI(portlet);
			  $("#printShop .portlet-body").html(data);    //替换内容			
				obj.initPrintShopPage();          //重新初始化分页列表
			},
			function(){
				App.unblockUI(portlet);
            	$("#printShop .portlet-body").html("<p class='errorMsg'>获取数据失败，刷新页面试试</p>");
			});		
		
	};  
	this.getOrderData=function(){		
		var obj=this;
		var portlet=$('#order .portlet');
		App.blockUI(portlet);		

		var data={"sortColumn":this.sortColumn, "sortDir":this.sortDir,"pageSize":this.order.pageSize,"pageIndex":this.order.currPageIndex,'printShopId':this.order.printShopId};
		$.getJSONNoCache("dispatchingOrderReturn.action","GET",$.extend(data,obj.condition),"text",false,function(data){
			 App.unblockUI(portlet);
			  $("#order .portlet-body").html(data);    //替换内容			
				obj.initOrderPage();          //重新初始化分页列表
			},
			function(){
				App.unblockUI(portlet);
            	$("#order .portlet-body").html("<p class='errorMsg'>获取数据失败，刷新页面试试</p>");
			});		
		
	};  
};  //物流监控