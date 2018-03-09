$(function(){
	DIComponent();
});

var DIComponent = function(){
	$('input:text').each(function(){
		if($(this).attr('form-type')=="date"){//查找页面中含有form-type属性未date的input标签，注入日历选择控件
			var format = $(this).attr('form-format');
			var dom = '#'+$(this).attr('id');
			if(format==undefined || format=="yyyy-MM-dd"){
				genDateTool(dom, 'yyyy-mm-dd', 2, 2)
			}else if(format=="yyyy-MM-dd HH"){//yyyy-mm-dd hh:ii:ss
				genDateTool(dom, 'yyyy-mm-dd hh', 1, 1);
			}else if(format=="yyyy"){//yyyy-mm-dd hh:ii:ss
				genDateTool(dom, 'yyyy', 4, 4);
			}else if(format=="yyyy-MM"){//yyyy-mm-dd hh:ii:ss
				genDateTool(dom, 'yyyy-mm', 3, 3);
			}else if(format=="yyyy-MM-dd HH:mm"){//yyyy-mm-dd hh:ii:ss
				genDateTool(dom, 'yyyy-mm-dd hh:ii', 0, 0);
			}else if(format=="yyyy-MM-dd HH:mm:ss"){//yyyy-mm-dd hh:ii:ss
				genDateTool(dom, 'yyyy-mm-dd hh:ii:ss', 0, 0);
			}
		}else if($(this).attr('form-type')=="money"){
			//金额输入框的js
			//输入框失焦时，执行的是Convert函数的逻辑，所以发往后台的最终参数也是由Convert
			$(this).blur(function () {
			    var $moneyValue = Convert($(this).val().replace(/,/g,''), $(this).attr('id'));
			    $(this).val($moneyValue);
			});
			//只允许输入数字键或小数点
			$(this).keydown(function(event){
				var key = event.which;
				if((key>47 && key<59) || key==110 || key==190 || (key>95 && key<106) || key==8){
					
				}else{
					return false;
				}
			} );
		    //键盘弹起监听，每抬起一次起键盘数据就会刷新一次，实现动态显示金额输入
			$(this).keyup(function(){
				var firsta = $(this).val();//获取金额框的值
				var seconda = toThousands(firsta.replace(/,/g,''));//去除金额框内的逗号再将金额框的值传给toThousands
				$(this).val(seconda);

			});
		}else if($(this).attr('form-type')=="digit"){//数字
			$(this).keydown(function(event){
				var key = event.which;
				if((key>47 && key<59) || (key>95 && key<106) || key==8){
					
				}else{
					return false;
				}
			});
		}
	});
}