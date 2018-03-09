/*针对于增删改查页面的常用方法，便于二次开发*
 */

/**
 * 得到表格选中的记录的主键id
 */
var _getTabeCheckItem = function(){
	return $("input[name='check-data']:checked").val();
}

/**
 * 得到表格选中的记录的主键id
 */
var _getTabeCheckItems = function(){
	var recordIds = new Array();
	$("input[name='check-data']:checked").each(function(index, ele){
		recordIds.push($(ele).val());
	});
	return recordIds;
}