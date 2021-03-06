// JavaScript Document
function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
	if (m >= 1 && m <= 9) {
        m = "0" + m;
    }
	if (d >= 1 && d <= 9) {
        d = "0" + d;
    }
    return y+"-"+m+"-"+d;
}

function getNowFormatYearMonth() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    var currentdate = date.getFullYear() + seperator1 + month;
    return currentdate;
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    
    var hour = date.getHours();
    if(hour >= 0 && hour <= 9) {
    	hour = "0" + hour;
    }
    
	var minutes = date.getMinutes();
	if(minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
	
	var seconds = date.getSeconds();
	if(seconds >= 0 && seconds <= 9) {
		seconds = "0" + seconds;
    }
	
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hour + seperator2 + minutes + seperator2+ seconds;
    return currentdate;
}

function getNowFormatTime() {
    var date = new Date();
    var seperator2 = ":";
    
    var hour = date.getHours();
    if(hour >= 0 && hour <= 9) {
    	hour = "0" + hour;
    }
    
	var minutes = date.getMinutes();
	if(minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
	

	
    var currentdate = hour + seperator2 + minutes;
    return currentdate;
}

function getNowNotSecondsFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
	var minutes = date.getMinutes();
	if(minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
	
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + minutes;
    return currentdate;
}

function getFirstMonthDay(year, month){    
	if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
   var   firstdate = year + '-' + month + '-01';  
   return firstdate;
}  

function getLastMonthDay(year, month){
	if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
   var  day = new Date(year,month,0);   
   var lastdate = year + '-' + month + '-' + day.getDate();//获取当月最后一天日期    
//给文本控件赋值。同下  
   return lastdate;  
}  

function genDateTool(dom, format,startView, minView){
	$(dom).datetimepicker({
		format: format,
		autoclose:true,
		minView:minView,
		startView:startView,
		language:"zh-CN"
	});
}