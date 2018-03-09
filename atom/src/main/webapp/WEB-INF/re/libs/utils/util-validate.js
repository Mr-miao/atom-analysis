//validateAnt('#add-win');
//<input type="text" vtype="required" vmsg="">
var minNum="";//较小数
var maxNum="";//较大数
var min="";//最小值
var max="";//最大值
var finishTime="";//结束时间
var minAndMaxMsg="";//错误提示信息
var validateAnt = function(parentDom){
	var flagSuccess = true;
	//获取父id下所有的（文本框，密码框，多行文本框），当提交时，对其进行数据验证
    $(""+parentDom+" :text, "+parentDom+" select, "+parentDom+" number, "+parentDom+" textarea, "+parentDom+" :password, "+parentDom+" :file").each(function () {
    	var _validate = $(this).attr("vtype"); //获取vtype属性的值
    	var _msg = $(this).attr("vmsg"); //获取vmsg属性的值
        if (_validate) {
          var arr = _validate.split('|'); //用空格将其拆分成数组
          var msg = null;
          if(_msg!=null && _msg!=""){
        	  msg = _msg.split('|'); //用空格将其拆分成数组
          }
          for (var i = 0; i < arr.length; i++) {
            //逐个进行验证，不通过跳出返回false,通过则继续
        	var showInfo = msg==null?msg:msg[i];
            if (!checkValidate($(this), arr[i], $(this).val(), showInfo)){
            	flagSuccess = false;
            	return false;
            }
            else{
            	continue;
            }
          }
        }
    });
    return flagSuccess;
}

var defaults = {
  //提示信息
  tips_success: '', //验证成功时的提示信息，默认为空
  tips_required: '不能为空',
  tips_email: '邮箱地址格式有误',
  tips_num: '请填写数字',
  tips_chinese: '请填写中文',
  tips_mobile: '手机号码格式有误',
  tips_idcard: '身份证号码格式有误',
  tips_pwdequal: '两次密码不一致',
  tips_money:'金额格式输入错误',
  tips_number5:'必须为5位数字',
  tips_yyyyMMdd:'日期格式不正确，应为yyyy-MM-dd',
  tips_yyyy:'年份格式不正确，应为yyyy',
  tips_todayAfter:'必须大于当前日期',
  tips_todayAndAfter:'不能小于当前日期',
  tips_todayBefore:'不能大于当前日期',
  tips_float:'必须为数字',
  tips_floatOrNull:'必须为数字',
  tips_ip:'ip地址不正确',
  tips_en:"必须为英文",

  //正则   [_a-z\d\-\./]+@[_a-z\d\-]+(\.[_a-z\d\-]+)*(\.(info|biz|com|edu|gov|net|am|bz|cn|cx|hk|jp|tw|vc|vn))$
 // reg_email: /^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i,//^\w+\@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/i,  //验证邮箱
  reg_email:/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
  reg_num: /^\d+$/,								  //验证数字
  reg_chinese: /^[\u4E00-\u9FA5]+$/,				 //验证中文
  reg_mobile: /^1[3458]{1}[0-9]{9}$/,				//验证手机
  reg_idcard: /^\d{14}\d{3}?\w$/,					 //验证身份证
  reg_money: /^\d+(\.\d+)?$/,		//验证金额
  reg_number5: /^\d{5}$/, //5位数字
  reg_yyyyMMdd:/^(\d{4})-(\d{2})-(\d{2})$/,	//校验日期
  reg_yyyy:/^\d{4}$/,
  reg_float:/^\d+\.{0,1}\d*$/,
  reg_floatOrNull:/^\d*(\.*\d*)?$/,
  reg_ip:/^((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))$/,
  reg_en:/^[a-zA-Z]+$/,
};

//验证方法
var checkValidate = function (obj, _match, _val, msg) {
	//根据验证情况，显示相应提示信息，返回相应的值
  switch (_match) {
    case 'required':
      return _val ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_required:msg, false);
	case 'money':
      return chk(_val, defaults.reg_money) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_money:msg, false);
    case 'email':
      return chk(_val, defaults.reg_email) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_email:msg, false);
    case 'num':
      return chk(_val, defaults.reg_num) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_num:msg, false);
    case 'numOrnull':
        return numOrnull(_val, defaults.reg_num) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_num:msg, false);  
    case 'chinese':
      return chk(_val, defaults.reg_chinese) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_chinese:msg, false);
    case 'mobile':
      return chk(_val, defaults.reg_mobile) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_mobile:msg, false);
    case 'idcard':
      return chk(_val, defaults.reg_idcard) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_idcard:msg, false);
    case 'n5':
        return chk(_val, defaults.reg_number5) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_number5:msg, false);
    case 'yyyyMMdd':
        return chk(_val, defaults.reg_yyyyMMdd) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_yyyyMMdd:msg, false);
    case 'yyyy':
        return chk(_val, defaults.reg_yyyy) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_yyyy:msg, false);
    case 'todayAfter':
        return todayAfter(_val) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_todayAfter:msg, false);
    case 'todayAndAfter':
        return todayAndAfter(_val) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_todayAndAfter:msg, false);
    case 'todayBefore':
        return todayBefore(_val) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_todayBefore:msg, false);
    case 'floatOrNull':
        return chk(_val, defaults.reg_floatOrNull) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_floatOrNull:msg, false);
    case 'float':
        return chk(_val, defaults.reg_float) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_float:msg, false);
    case 'pwd1':
      pwd1 = _val; //实时获取存储pwd1值
      return true;
    case 'pwd2':
      pwd2 = _val; //实时获取存储pwd2值
      isCheckPwd = true; //开启两次密码是否一致验证
      return pwdEqual(_val, pwd1) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_pwdequal:msg, false);
    case 'min':
        min = _val; //实时获取存储min值
        return true;
    case 'max':
        max = _val; //实时获取存储max值
       return maxAndmin(msg)?showMsg(obj, defaults.tips_success, true):showMsg(obj, minAndMaxMsg, false); 
    case 'yszl'://元素质量
       return isyszl(_val, defaults.reg_money)?showMsg(obj, defaults.tips_success, true):showMsg(obj, msg==null?defaults.tips_money:msg, false);
    case 'select'://增加时下拉框必须填写
    	return selectEd(obj,msg)?showMsg(obj, defaults.tips_success, true):showMsg(obj, msg, false);
    case 'finishTime'://结束时间
    	return compareTime(obj)?showMsg(obj, defaults.tips_success, true):showMsg(obj, msg, false);
    case 'minNum'://前者
    	minNum=_val;
    	return compareNum()?showMsg(obj, defaults.tips_success, true):showMsg(obj, msg, false); 
    case 'maxNum'://后者
    	maxNum=_val;
    case 'ip'://ip校验
    	return chk(_val, defaults.reg_ip) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_ip:msg, false);
    case 'en'://英文校验
    	return chk(_val, defaults.reg_en) ? showMsg(obj, defaults.tips_success, true) : showMsg(obj, msg==null?defaults.tips_en:msg, false);
    
    default: 
      return true;
  }
}

//判断最最小值与最大值是否正确
var maxAndmin=function(msg){
	if((min==null||min=="")&&(max==null||max=="")){
		return true;
	}
	if((min==null||min=="")&&(max!=null||max!="")){
		minAndMaxMsg=msg+"元素含量最大值最小值必须同时填写！";
		return false;
	}
	if((min!=null||min!="")&&(max==null||max=="")){
		minAndMaxMsg=msg+"元素含量最大值最小值必须同时填写！";
		return false;
	}
	if(max<min){
		minAndMaxMsg=msg+"元素含量最大值必须大于最小值！";
		return false;
	}
	return true;
}

//下拉框必须填写
var selectEd =function(val,msg){
	val=$(val).children(':selected').val();
	if(val==""||val=="无"){
		return false;
	}
	return true;
}
//元素质量格式
var isyszl=function(str, reg){
	if(str!=null&&str!=""){
		 return reg.test(str);
	}
	return true;
}
//判断两次密码是否一致(返回bool值)
var pwdEqual = function(val1, val2) {
  return val1 == val2 ? true : false;
}
//判断给定日期是否大于当前日期
var todayAfter = function(val) {
  var today = GetDateStr(0);
  return val > today;
}

//判断给定日期是否大于等于当前日期
var todayAndAfter = function(val) {
  var today = GetDateStr(0);
  return val >= today;
}

//判断给定日期是否小于当前日期
var todayBefore = function(val) {
	var today = GetDateStr(0);
	return val <= today;
}


//正则匹配(返回bool值)
var chk = function (str, reg) {
  return reg.test(str);
}

//为null或数字
var numOrnull=function(val,reg){
	if(val==null||val==""){
		return true;
	}
	return reg.test(val);
}
//显示信息
var showMsg = function (obj, msg, mark) {
		//alert($(obj).val());
	if(!mark){
	  //alert(msg);
	  var id = $(obj).attr('id');
	  var arr = id.split('.');
	  var ids = '';
	  for(var i=0;i<arr.length;i++){
		  if(i<arr.length-1){
			  ids += arr[i]+"\\.";
		  }else{
			  ids += arr[i];
		  }
	  }
	  layer.tips(msg,'#'+ids,{
		  tips: [1, '#cc0000']
	  });
	}
	return mark;
}

//比较开始时间和结束时间
var compareTime= function(obj){
	var a =$(obj).val();
	var b=$(obj).parent().prev().prev().children().val();
	if(a>b){
		return true;
	}else{
		return false;
	}
}

//比较两个数大小
var compareNum=function(){
	if(parseFloat(maxNum)>parseFloat(minNum)){
		return true;
	}
	return false;
}

