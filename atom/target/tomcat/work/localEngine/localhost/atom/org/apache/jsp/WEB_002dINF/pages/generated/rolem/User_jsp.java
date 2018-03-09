package org.apache.jsp.WEB_002dINF.pages.generated.rolem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class User_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextPath = basePath + "resources/";

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-CN\">\r\n");
      out.write("  <head>\r\n");
      out.write("  \t<!-- 标准的meta信息 -->\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("      \t<!-- 定义基准路径的全局变量 -->\r\n");
      out.write("    <script>\r\n");
      out.write("\t\tvar webPath = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<script src=\"");
      out.print(basePath );
      out.write("re/libs/bootstrap/js/jquery.min.js\"></script> \r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<script src=\"");
      out.print(basePath );
      out.write("re/libs/layer/layer.js\"></script>\r\n");
      out.write("\t<script src=\"");
      out.print(basePath );
      out.write("re/libs/searchableSelect/jquery.searchableSelect.js\"></script>\r\n");
      out.write("\t<link href=\"");
      out.print(basePath );
      out.write("re/libs/searchableSelect/jquery.searchableSelect.css\" rel=\"stylesheet\">\r\n");
      out.write(" \t<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("re/libs/utils/util-required.js\"></script>\r\n");
      out.write("    <!-- 标准的meta信息  end-->\r\n");
      out.write("    \r\n");
      out.write("    <!-- 标题 -->\r\n");
      out.write("    <title>用户管理</title>\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    <!-- style声明，仅仅只是对当前页面的样式设置，否则请使用公共style表 -->\r\n");
      out.write("    <style>\r\n");
      out.write("    \t.hideLoadPassword{\r\n");
      out.write("    \tdisplay:none;\r\n");
      out.write("    \t}\r\n");
      out.write("    \t#tb-allacate-data td{max-width:300px;min-width:50px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;}\r\n");
      out.write("    \t\r\n");
      out.write("\t</style>\r\n");
      out.write("\t\r\n");
      out.write("    <script>\r\n");
      out.write("    \tvar pagerUrl = webPath+\"rolem/user/_pager.html\";\r\n");
      out.write("    \tvar queryByIdUrl = webPath + \"rolem/user/_findById.html\";\r\n");
      out.write("    \tvar queryByWhereUrl =webPath+\"rolem/user/_pager.html\";\r\n");
      out.write("    \tvar updateUrl = webPath + \"rolem/user/_update.html\";\r\n");
      out.write("    \tvar updateBatchUrl = webPath + \"rolem/user/_updateBatch.html\";\r\n");
      out.write("    \tvar addUrl = webPath + \"rolem/user/_add.html\";\r\n");
      out.write("    \tvar deleUrl = webPath + \"rolem/user/_delete.html\";\r\n");
      out.write("    \tvar excelModuleUrl = webPath + \"rolem/user/_excelModule.html\";\r\n");
      out.write("    \tvar exportExcelUrl = webPath + \"rolem/user/_excelExport.html\";\r\n");
      out.write("    \tvar importExcelUrl = webPath + \"rolem/user/_excelImport.html\";\r\n");
      out.write("    \tvar resetPasswordUrl = webPath + \"rolem/user/_resetPassword.html\";\r\n");
      out.write("    \tvar queryUserRoleByUserIdUrl=webPath + \"userRole/queryUserRoleById.html\";\r\n");
      out.write("    \tvar queryRolesUrl=webPath+\"role/pagerRole.html\";\r\n");
      out.write("    \tvar allocateUserRoleUrl=webPath+\"userRole/allocateUserRole.html\";\r\n");
      out.write("    \tvar deleteUserRoleUrl=webPath+\"userRole/deleteUserRole.html\";\r\n");
      out.write("    \tvar genFuncMenuUrl = webPath + \"_menu/genFuncMenu.html?trn=userServiceImpl\";\r\n");
      out.write("    \tvar findBranchUrl = webPath+\"rolem/branch/_findAll.html\";\r\n");
      out.write("    \r\n");
      out.write("    \tvar tbJsonItems = null;\r\n");
      out.write("    \t\r\n");
      out.write("    \t//自定义序列化对象的方法\r\n");
      out.write("   \t \tArray.prototype.serializeObject = function (lName) {\r\n");
      out.write("            var o = {};\r\n");
      out.write("            $t = this;\r\n");
      out.write("            for (var i = 0; i < $t.length; i++) {\r\n");
      out.write("                for (var item in $t[i]) {\r\n");
      out.write("                    o[lName+'[' + i + '].' + item.toString()] = $t[i][item].toString();\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("            return o;\r\n");
      out.write("        };\r\n");
      out.write("        \r\n");
      out.write("\t\tvar showTbData = function(data, tbDom, reloadPagerLan, ajaxUrl, paramUrl){\r\n");
      out.write("\t        if(data.data==null || data.data==''){\r\n");
      out.write("\t        \tvar noRecord = '<tr>'+\r\n");
      out.write("\t                        \t'<th colspan=\"8\" style=\"text-align:center;\">暂无记录！</th>'+\r\n");
      out.write("\t                            '</tr>';\r\n");
      out.write("\t        \t$(\"#tb-data tbody\").html(noRecord);\r\n");
      out.write("\t        \t$('#update-batch').unbind('click');//取消批量编辑按钮的单击事件\r\n");
      out.write("\t        }else{\r\n");
      out.write("\t        \ttbJsonItems = data.data;\r\n");
      out.write("\t        \tvar htm = '';\r\n");
      out.write("\t        \t$(\"#tb-data tbody\").html(htm);\r\n");
      out.write("\t        \t$(data.data).each(function(index, ele){\r\n");
      out.write("\t        \t\t//查找该用户对应的角色关系\r\n");
      out.write("\t        \t\t var userRole=\"\";\r\n");
      out.write("\t        \t\tvar recordId=ele.id;\r\n");
      out.write("\t        \t\tgoAjax(queryUserRoleByUserIdUrl,{recordId:recordId},function(data){\r\n");
      out.write("\t        \t\t\t\r\n");
      out.write("\t        \t\t\t$(data.data).each(function(index, ele){\r\n");
      out.write("\t        \t\t\t\tvar roleName=ele.roleName;\r\n");
      out.write("\t        \t\t\t\t\r\n");
      out.write("\t        \t\t\t\tuserRole=userRole+roleName+\",\";\r\n");
      out.write("\t        \t\t\t})\r\n");
      out.write("\t        \t\t\tuserRole=userRole.substring(0, userRole.length-1); \r\n");
      out.write("\t        \t\t\t\r\n");
      out.write("\t        \t\t\thtm = '<tr>'+\r\n");
      out.write("\t\t\t\t\t\t'<td><input type=\"checkbox\" name=\"check-data\" value=\"'+notNull(ele.id)+'\"></td>'+\r\n");
      out.write("\t\t\t\t\t\t'<td>'+notNull(ele[\"userLoad\"])+'</td>'+\r\n");
      out.write("\t\t\t\t\t    '<td>'+notNull(ele[\"userName\"])+'</td>'+\r\n");
      out.write("\t\t\t\t\t    '<td>'+notNull(ele[\"userPhone\"])+'</td>'+\r\n");
      out.write("\t\t\t\t\t    '<td>'+notNull(ele[\"userCall\"])+'</td>'+\r\n");
      out.write("\t\t\t\t\t    '<td>'+notNull(ele[\"userEmail\"])+'</td>'+\r\n");
      out.write("\t\t\t\t\t    '<td>'+notNull(ele[\"branchName\"])+'</td>'+\r\n");
      out.write("\t\t\t\t\t    '<td>'+userRole+'</td>'+\r\n");
      out.write("\t\r\n");
      out.write("\t                    '</tr>';\r\n");
      out.write("\t        \t\t\t$(\"#tb-data\").append(htm);\r\n");
      out.write("\t        \t  });\t\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t        \tif(reloadPagerLan){\r\n");
      out.write("\t        \t\t//添加分页栏\r\n");
      out.write("\t\t\t\t\tgenPager(data.pager, ajaxUrl, paramUrl, null);\r\n");
      out.write("\t        \t}\r\n");
      out.write("\t        }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t  \t//获取角色分页查询\r\n");
      out.write("\t  \tvar showRoleData = function(data, tbDom, reloadPagerLan, ajaxUrl, paramUrl){\r\n");
      out.write("\t        if(data.data==null || data.data==''){\r\n");
      out.write("\t        \tvar noRecord = '<tr>'+\r\n");
      out.write("\t                        \t'<th colspan=\"3\" style=\"text-align:center;\">暂无记录！</th>'+\r\n");
      out.write("\t                            '</tr>';\r\n");
      out.write("\t        \t$(\"#tb-allacate-data tbody\").html(noRecord);\r\n");
      out.write("\t        }else{\r\n");
      out.write("\t        \tvar htm = '';\r\n");
      out.write("\t        \t$(\"#tb-allacate-data tbody\").html(htm);\r\n");
      out.write("\t        \t$(data.data).each(function(index, ele){\r\n");
      out.write("\t        \t\thtm = '<tr>'+\r\n");
      out.write("\t\t\t\t\t'<td><input type=\"checkbox\" name=\"check-allocate-data\" value=\"'+notNull(ele.roleId)+'\" roleName=\"'+notNull(ele.roleName)+'\"></td>'+\r\n");
      out.write("\t\t\t\t    '<td>'+notNull(ele[\"roleName\"])+'</td>'+\r\n");
      out.write("\t\t\t\t    '<td>'+notNull(ele[\"roleDesc\"])+'</td>'+\r\n");
      out.write("                    '</tr>';\r\n");
      out.write("        \t\t\t$(\"#tb-allacate-data\").append(htm);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t        \tif(reloadPagerLan){\r\n");
      out.write("\t        \t\t//添加分页栏\r\n");
      out.write("\t\t\t\t\tgenPager(data.pager, ajaxUrl, paramUrl,tbDom,'kkpager-role',null);\r\n");
      out.write("\t        \t}\r\n");
      out.write("\t        \t//onTdClick();//监听表格单击事件\r\n");
      out.write("\t        }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//用户权限分配弹框的全选和取消\r\n");
      out.write("\t\tfunction chooseAll() {\r\n");
      out.write("\t\t\t//#checkedAll是全选的那个checkbox\r\n");
      out.write("\t\t\tif ($(\"#checkAll-allacate\").prop(\"checked\")) {\r\n");
      out.write("\t\t\t//：checkbox  是选中了所有的<input> type为 checkbox的对象\r\n");
      out.write("\t\t\t\t$(\":checkbox\").prop(\"checked\", true);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t    $(\":checkbox\").prop(\"checked\", false);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("    \t$(function(){\r\n");
      out.write("    \t\t//根据权限改变功能菜单\r\n");
      out.write("    \t\tgoAjax(genFuncMenuUrl,{},function(response){\r\n");
      out.write("    \t\t\tgenFuncMenu(response.data);\r\n");
      out.write("    \t\t\t//启用默认框架的初始化方式\r\n");
      out.write("        \t\tenableDefaultFramework('700px', '400px');\r\n");
      out.write("        \t\t//将update的id的属性修改为updateNew\r\n");
      out.write("    \t\t\t$('#update').attr('id','updateNew');\r\n");
      out.write("        \t\t//解绑详情按钮\r\n");
      out.write("        \t\t$('#detail').attr('id','detailNew');\r\n");
      out.write("        \t\t//重置密码按钮\r\n");
      out.write("        \t\t$('#changePassword').click(function(){\r\n");
      out.write("        \t\t\t//得到行记录的id值\r\n");
      out.write("        \t\t\tvar recordId = $(\"input[name='check-data']:checked\").val();\r\n");
      out.write("        \t\t\tvar recordIds = $(\"input[name='check-data']:checked\").length;\r\n");
      out.write("        \t\t\tif(recordId==undefined){\r\n");
      out.write("        \t\t\t\talert(\"请先选择至少一个用户\");\r\n");
      out.write("        \t\t\t\treturn false;\r\n");
      out.write("        \t\t\t}else{\r\n");
      out.write("        \t\t\t\t if (!confirm(\"确认要将选中用户密码重置为 6个0吗？\")) {\r\n");
      out.write("     \t\t\t            return false;\r\n");
      out.write("     \t\t\t         }else{//<b style='color:red;'>6个0</b>\r\n");
      out.write("     \t\t\t        \tvar checkedIds='';\r\n");
      out.write("     \t        \t\t\t$(\"input[name='check-data']:checked\").each(function(){\r\n");
      out.write("     \t        \t\t\t\tcheckedIds+=$(this).val()+'#';\r\n");
      out.write("     \t        \t\t\t});\r\n");
      out.write("     \t        \t\t\t//重置密码请求\r\n");
      out.write("     \t        \t\t\tgoAjax(resetPasswordUrl,{recordIds:checkedIds},function(data){\r\n");
      out.write("     \t        \t\t\t\tif(data.flag!=0){\r\n");
      out.write("     \t        \t\t\t\t\talert('重置密码失败!');\r\n");
      out.write("     \t        \t\t\t\t}else{\r\n");
      out.write("     \t        \t\t\t\t\talert(data.msg);\r\n");
      out.write("     \t        \t\t\t\t\tlayer.closeAll();\r\n");
      out.write("     \t        \t\t\t\t\t//刷新表数据\r\n");
      out.write("     \t        \t\t\t\t\t//若编辑的是当前登录用户,需重新登录\r\n");
      out.write("     \t        \t\t\t\t\tif(data.reLoad==1){\r\n");
      out.write("     \t        \t\t\t\t\t\talert('重置了当前登录用户，需重新登录！');\r\n");
      out.write("     \t                    \t\t    window.location.href = webPath+'login.html' ;\r\n");
      out.write("     \t        \t\t\t\t\t}else{\r\n");
      out.write("     \t        \t\t\t\t\t\tpagerList({});\r\n");
      out.write("     \t        \t\t\t\t\t}\r\n");
      out.write("     \t        \t\t\t\t}\r\n");
      out.write("     \t        \t\t\t});\r\n");
      out.write("     \t\t\t        }\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("        \t\t});\r\n");
      out.write("    \t\t\t//权限分配\r\n");
      out.write("    \t\t\t$(\"#allocate\").click(function(){\r\n");
      out.write("    \t\t\t\tvar recordId = $(\"input[name='check-data']:checked\").val();\r\n");
      out.write("    \t\t\t\tvar checkeds= $(\"input[name='check-data']:checked\").length;\r\n");
      out.write("    \t\t\t\tif(recordId==undefined||checkeds>1){\r\n");
      out.write("    \t\t\t\t\talert(\"请选择要分配的用户\");\r\n");
      out.write("    \t\t\t\t\treturn false;\r\n");
      out.write("    \t\t\t\t}\r\n");
      out.write("    \t\t\t\t//弹出权限分配框\r\n");
      out.write("    \t\t\t\tlayer.open({\r\n");
      out.write("    \t\t\t\t\ttype: 1,\r\n");
      out.write("    \t\t\t\t\tmove:false,\r\n");
      out.write("    \t\t\t\t\ttitle:'角色分配',\r\n");
      out.write("    \t\t\t\t\t//skin: 'layui-layer-rim', //加上边框\r\n");
      out.write("    \t\t\t\t\tarea: ['710px', \"400px\"], //宽高\r\n");
      out.write("    \t\t\t\t\t//offset: '30px',\r\n");
      out.write("    \t\t\t\t\tshade: [0.8, '#393D49'],\r\n");
      out.write("    \t\t\t\t\tzIndex : 3,\r\n");
      out.write("    \t\t\t\t\tcontent: $('#allocate-win')\r\n");
      out.write("    \t\t\t\t});\r\n");
      out.write("    \t\t\t\t//获取角色数据\r\n");
      out.write("    \t\t\t\tgoAjax(queryRolesUrl,{},function(data){\r\n");
      out.write("    \t\t\t\t\t/* var html='';\r\n");
      out.write("    \t\t\t\t\t$(\"#tb-allacate-data tbody\").html(html);\r\n");
      out.write("    \t\t\t\t\t$(data.data).each(function(index, ele){\r\n");
      out.write("    \t\t\t\t\t\thtml = '<tr>'+\r\n");
      out.write("    \t\t\t\t\t\t'<td><input type=\"checkbox\" name=\"check-allocate-data\" value=\"'+notNull(ele.roleId)+'\" roleName=\"'+notNull(ele.roleName)+'\"></td>'+\r\n");
      out.write("    \t\t\t\t\t    '<td>'+notNull(ele[\"roleName\"])+'</td>'+\r\n");
      out.write("    \t\t\t\t\t    '<td>'+notNull(ele[\"roleDesc\"])+'</td>'+\r\n");
      out.write("    \t                    '</tr>';\r\n");
      out.write("    \t        \t\t\t$(\"#tb-allacate-data\").append(html);\r\n");
      out.write("    \t\t\t\t\t}) */\r\n");
      out.write("    \t\t\t\t\tif(data.flag!=0){\r\n");
      out.write("    \t\t\t\t\t\talert('数据查询失败');\r\n");
      out.write("    \t\t\t\t\t}else{\r\n");
      out.write("    \t\t\t\t\t\tshowRoleData(data, 'tb-allacate-data', false, queryRolesUrl, {});\r\n");
      out.write("    \t\t\t\t\t}\r\n");
      out.write("    \t\t\t\t\t//回显权限数据\r\n");
      out.write("    \t\t\t\t\tgoAjax(queryUserRoleByUserIdUrl,{recordId:recordId},function(data){\r\n");
      out.write("    \t\t\t\t\t\t$(data.data).each(function(index, ele){\r\n");
      out.write("    \t\t\t\t\t\t\tvar roleId=ele.roleId;\r\n");
      out.write("    \t\t\t\t\t\t\t$(\"input[name='check-allocate-data']\").each(function(){\r\n");
      out.write("    \t\t\t\t\t\t\t    var checkId=$(this).val();\r\n");
      out.write("    \t\t\t\t\t\t\t\tif(roleId==checkId){\r\n");
      out.write("    \t\t\t\t\t\t\t\t   $(this).prop(\"checked\",true);\r\n");
      out.write("    \t\t\t\t\t\t\t   }\r\n");
      out.write("    \t\t\t\t\t\t\t})\r\n");
      out.write("    \t\t\t\t\t\t})\r\n");
      out.write("    \t\t\t\t\t});\r\n");
      out.write("    \t\t\t\t});\r\n");
      out.write("    \t\t\t\t//给用户分配角色权限\r\n");
      out.write("    \t\t\t\t\r\n");
      out.write("    \t\t\t\t$(\"#allocate-btn-submit\").click(function(){\r\n");
      out.write("    \t\t\t\t\tvar checkeds=$(\"input[name='check-allocate-data']:checked\").length;\r\n");
      out.write("    \t\t\t\t\tif(checkeds<1||checkeds==undefined){\r\n");
      out.write("    \t\t\t\t\t\talert(\"至少分配一个角色\");\r\n");
      out.write("    \t\t\t\t\t\treturn false;\r\n");
      out.write("    \t\t\t\t\t}\r\n");
      out.write("    \t\t\t\t\tvar userRoles = [];\r\n");
      out.write("    \t\t\t\t\t$(\"input[name='check-allocate-data']:checked\").each(function(index,ele){\r\n");
      out.write("    \t\t\t\t\t\tvar user_role = new Object();\r\n");
      out.write("    \t\t\t\t\t\tuser_role.userId=recordId;\r\n");
      out.write("    \t\t\t\t\t\tuser_role.roleId=$(ele).val();\r\n");
      out.write("    \t\t\t\t\t\tuser_role.roleName=$(this).attr(\"roleName\");\r\n");
      out.write("    \t\t\t\t\t\tuserRoles.push(user_role);\r\n");
      out.write("    \t\t\t\t\t});\r\n");
      out.write("    \t\t\t\t\tconsole.log(userRoles);\r\n");
      out.write("    \t\t\t\t\t$.ajax({\r\n");
      out.write("    \t    \t\t\t\turl:allocateUserRoleUrl,\r\n");
      out.write("    \t    \t            data:$.param(userRoles.serializeObject('userRoles')),    //手动把数据转换拼接{\"userRoles\":userRoles}\r\n");
      out.write("    \t    \t            type:'post',\r\n");
      out.write("    \t    \t            dataType:'json',\r\n");
      out.write("    \t    \t            traditional:true,    //这里必须设置\r\n");
      out.write("    \t    \t            success:function(response){\r\n");
      out.write("    \t    \t            \tif(response.flag==0){\r\n");
      out.write("    \t        \t\t\t\t\talert('添加权限成功');    \t        \t\t\t\t\t\r\n");
      out.write("    \t        \t\t\t\t\t//layer.closeAll();\r\n");
      out.write("    \t        \t\t\t\t\twindow.location.reload();\r\n");
      out.write("    \t        \t\t\t\t}else{\r\n");
      out.write("    \t        \t\t\t\t\talert('添加权限失败');\r\n");
      out.write("    \t        \t\t\t\t}\r\n");
      out.write("    \t    \t            }\r\n");
      out.write("    \t    \t\t\t});\r\n");
      out.write("    \t\t\t\t});\r\n");
      out.write("    \t\t\t});\r\n");
      out.write("        \t\t//解绑编辑提交按钮\r\n");
      out.write("        \t\t$('#edit-win-btn').unbind('click');\r\n");
      out.write("        \t\t$('#edit-win-btn').click(function(){\r\n");
      out.write("        \t\t\tif($('#edit-win').attr('type')=='detailNew'){\r\n");
      out.write("        \t\t\t\tlayer.closeAll();\r\n");
      out.write("        \t\t\t}else{\r\n");
      out.write("        \t\t\t\t//表单验证\r\n");
      out.write("            \t\t\tvar vflag = validateAnt('#edit-win');\r\n");
      out.write("            \t\t\tif(!vflag){\r\n");
      out.write("            \t\t\t\treturn false;\r\n");
      out.write("            \t\t\t}\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("        \t\t\t//生成请求参数\r\n");
      out.write("        \t\t\tvar paramAjax = genReqData('#edit-win');\r\n");
      out.write("        \t\t\tif($('#edit-win').attr('type')=='add'){\r\n");
      out.write("        \t\t\t\tgoAjax(addUrl, paramAjax, function(data){\r\n");
      out.write("            \t\t\t\tif(data.flag!=0){\r\n");
      out.write("            \t\t\t\t\talert(data.msg);\r\n");
      out.write("            \t\t\t\t}else{\r\n");
      out.write("            \t\t\t\t\talert('操作成功');\r\n");
      out.write("            \t\t\t\t\tlayer.closeAll();\r\n");
      out.write("            \t\t\t\t\t//刷新表数据\r\n");
      out.write("            \t\t\t\t\tpagerList({});\r\n");
      out.write("            \t\t\t\t}\r\n");
      out.write("            \t\t\t});\r\n");
      out.write("        \t\t\t}else if($('#edit-win').attr('type')=='updateNew'){\r\n");
      out.write("        \t\t\t\tgoAjax(updateUrl, paramAjax, function(data){\r\n");
      out.write("            \t\t\t\tif(data.flag!=0){\r\n");
      out.write("            \t\t\t\t\talert(data.msg);\r\n");
      out.write("            \t\t\t\t}else{\r\n");
      out.write("            \t\t\t\t\talert('操作成功');\r\n");
      out.write("            \t\t\t\t\tlayer.closeAll();\r\n");
      out.write("            \t\t\t\t\t//刷新表数据\r\n");
      out.write("            \t\t\t\t\t//若编辑了当前用户需重新登录\r\n");
      out.write("                \t\t\t\tif(data.reLoad==1){\r\n");
      out.write("                \t\t\t\t\talert('编辑了当前登录用户，需重新登录!');\r\n");
      out.write("                \t\t\t\t\twindow.location.href = webPath+'login.html' ;\r\n");
      out.write("                \t\t\t\t}else{\r\n");
      out.write("                \t\t\t\t\t//若编辑成功且没有编辑当前用户刷新页面\r\n");
      out.write("                \t\t\t\t\tpagerList({});\r\n");
      out.write("                \t\t\t\t}\r\n");
      out.write("            \t\t\t\t}\r\n");
      out.write("            \t\t\t});\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("        \t\t\t\r\n");
      out.write("        \t\t});\r\n");
      out.write("        \t\tonUpdateBatchClick();//监听批量编辑按钮\r\n");
      out.write("    \t\t});\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\t//生成高级查询的窗口\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\t//以下内容是动态加载下拉框、单选框、多选框数据\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t\t\tgenBranchSelect(findBranchUrl,{},\"branchId.branchId-query\");\r\n");
      out.write("\t\t    genBranchSelect(findBranchUrl,{},\"branchId.branchId\");\r\n");
      out.write("\t\t     \r\n");
      out.write("\r\n");
      out.write("         \r\n");
      out.write("\t\t//用户权限分配弹框的点中行单选\r\n");
      out.write("\t\t\t$('#tb-allacate-data tbody').on('click','tr',function(e){\r\n");
      out.write("\t\t\t\t$('#tb-allacate-data tbody tr').children().children('input[type=checkbox]').prop(\"checked\", false);\r\n");
      out.write("\t\t\t\tif($(e.currentTarget).children().children('input[type=checkbox]').prop(\"checked\")==false){\r\n");
      out.write("\t\t\t\t\t$(e.currentTarget).children().children('input[type=checkbox]').prop(\"checked\",true);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(e.currentTarget).children().children('input[type=checkbox]').prop(\"checked\",false);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//阻止父元素的事件冒泡\r\n");
      out.write("\t\t\t$('#tb-allacate-data tbody').on('click','input[type=checkbox]',function(e){\r\n");
      out.write("\t\t\t\te.stopPropagation();\r\n");
      out.write("\t\t\t\tif($(e.currentTarget).children().children('input[type=checkbox]').prop(\"checked\")==false){\r\n");
      out.write("\t\t\t\t\t$(e.currentTarget).children().children('input[type=checkbox]').prop(\"checked\",true);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$(e.currentTarget).children().children('input[type=checkbox]').prop(\"checked\",false);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("    </script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <div class=\"container-ant\">\r\n");
      out.write("\t<div class=\"row-ant\">\r\n");
      out.write("\t\t<div class=\"col-sm-12-ant\">\r\n");
      out.write("\t\t\t<!-- 显示用户信息区域 -->\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fimport_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"container-ant\">\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 显示导航菜单区域 -->\r\n");
      out.write("\t<div class=\"col-sm-2-ant\">");
      if (_jspx_meth_c_005fimport_005f1(_jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 页面底部脚本区域 -->\r\n");
      out.write("\t<div class=\"col-sm-10-ant\" >");
      if (_jspx_meth_c_005fimport_005f2(_jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 条件查询区域 -->\r\n");
      out.write("\t<div class=\"col-sm-10-ant right\" id=\"content_page\">\r\n");
      out.write("\t\t<div class=\"panel panel-default query-area\">\r\n");
      out.write("\t\t    <div class=\"panel-body\" id=\"query-win\">\r\n");
      out.write("\t\t        <div class=\"form-inline\">\r\n");
      out.write("\t\t\t\t\t<div class=\" form-group\" style=\"margin-top:12px\">\r\n");
      out.write("\t\t\t\t\t  <label for=\"userName-query\">用户姓名\r\n");
      out.write("\t\t\t\t\t\t <input type=\"text\" class=\"form-control\"  id=\"userName-query\">\r\n");
      out.write("\t\t\t\t\t  </label>&nbsp;&nbsp;&nbsp;\r\n");
      out.write("<!-- \t\t\t\t\t  <label for=\"branchId-query\">所属机构\r\n");
      out.write("\t\t\t\t\t\t<select id=\"branchId.branchId-query\">\r\n");
      out.write("\t\t\t\t\t\t   <option value=\"\"></option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t </label> -->\r\n");
      out.write("\t\t\t\t\t<label for=\"branchId-query\">所属机构\r\n");
      out.write("\t\t            <select class=\"form-control\" id=\"branchId.branchId-query\" style=\"margin-top:-5px\" > \r\n");
      out.write("\t\t              <option></option>\r\n");
      out.write("\t\t            </select>\r\n");
      out.write("\t\t          </label> \r\n");
      out.write("                       <!-- 查询按钮 -->\r\n");
      out.write("                    <!--  <button type=\"button\" id=\"query-advance\" class=\"btn btn-default\" style=\"float:right;margin-right:30px;\">高级</button> -->\r\n");
      out.write("\t\t\t\t      <button type=\"button\" id=\"query\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span> 查&nbsp;询</button>\r\n");
      out.write("\t\t\t\t  </div>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t  </div>\r\n");
      out.write("\t\t<!-- 条件查询区域 end-->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 数据显示区域 -->            \r\n");
      out.write("\t\t<div class=\"panel panel-default data-area\">\r\n");
      out.write("\t\t  <div class=\"panel-body\">\r\n");
      out.write("\t\t\t<div class=\"btn-group menu-lan\" style=\"position:relative\">\r\n");
      out.write("\t\t\t  <button id=\"detail\" type=\"button\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-th\"></span> 详情</button>\r\n");
      out.write("<!-- \t\t\t  <button id=\"allocate\" type=\"button\" class=\"btn btn-info btn-sm\"><span class=\"glyphicon glyphicon-wrench\"></span> 角色分配</button>\r\n");
      out.write("\t\t\t  <button id=\"changePassword\" type=\"button\" class=\"btn btn-info btn-sm\"><span class=\"glyphicon glyphicon-pencil\"></span> 重置密码</button> -->\r\n");
      out.write("\t\t\t  <!--<button id=\"add\" type=\"button\" class=\"btn btn-primary btn-sm\"><span class=\"glyphicon glyphicon-plus\"></span> 新增</button>\r\n");
      out.write("\t\t\t  <button id=\"update\" type=\"button\" class=\"btn btn-warning btn-sm\"><span class=\"glyphicon glyphicon-pencil\"></span> 编辑</button>\r\n");
      out.write("\t\t\t  <button id=\"update-batch\" type=\"button\" class=\"btn btn-success btn-sm\"><span class=\"glyphicon glyphicon-tasks\"> </span> 批量编辑</button>\r\n");
      out.write("\t\t\t  <button id=\"delete\" type=\"button\" class=\"btn btn-danger btn-sm\"><span class=\"glyphicon glyphicon-trash\"></span> 删除</button>\r\n");
      out.write("\t\t\t  <button id=\"import\" type=\"button\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-file\" aria-hidden=\"true\"></span> 导入</button>\r\n");
      out.write("\t\t\t  <button id=\"export\" type=\"button\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-file\" aria-hidden=\"true\"></span> 导出</button>-->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- 数据表格 -->\r\n");
      out.write("\t\t    <table id=\"tb-data\" class=\"table table-bordered table-striped table-hover\">\r\n");
      out.write("\t\t      <thead>\r\n");
      out.write("\t\t      <tr>\r\n");
      out.write("\t\t      \t<th><input type=\"checkbox\" id=\"checkAll\" onclick=\"selectAll()\"/></th>\r\n");
      out.write("\t\t      \t<th class=\"tb-sort\" col=\"userLoad\">用户登录名</th>\r\n");
      out.write("\t\t\t\t<th class=\"tb-sort\" col=\"userName\">用户姓名</th>\r\n");
      out.write("\t\t\t\t<th class=\"tb-sort\" col=\"userPhone\">移动电话</th>\r\n");
      out.write("\t\t\t\t<th class=\"tb-sort\" col=\"userCall\">座机电话</th>\r\n");
      out.write("\t\t\t\t<th class=\"tb-sort\" col=\"userEmail\">邮箱</th>\r\n");
      out.write("\t\t\t\t<th class=\"tb-sort\" col=\"branchId.branch_id\">所属机构</th>\r\n");
      out.write("\t\t\t\t<th class=\"tb-sort\" col=\"userRoleId\">所属角色</th>\r\n");
      out.write("\r\n");
      out.write("\t\t      </tr>\r\n");
      out.write("\t\t      </thead>\r\n");
      out.write("\t\t      <tbody>\r\n");
      out.write("\t\t      </tbody>\r\n");
      out.write("\t\t    </table>\r\n");
      out.write("\t\t    <!-- 分页栏 -->\r\n");
      out.write("\t\t    <div class=\"row-ant\"><div class=\"col-sm-12-ant\"><div class=\"pager-lan\" id=\"kkpager\"></div></div></div>\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 数据显示区域 end-->   \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 增加、编辑时的窗口 -->\r\n");
      out.write("\t\t<div id=\"edit-win\" class=\"none\">\r\n");
      out.write("\t\t\t<input id=\"id\" type=\"hidden\" value=\"\">\r\n");
      out.write("\t\t\t<table class=\"table border-none\">\r\n");
      out.write("\t\t\t<tr class='load-password-win'>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">登录帐号名<span style=\"color:red\">*</span></th>\r\n");
      out.write("\t\t\t\t<td width=\"30%\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"form-control\"  id=\"userLoad\" name=\"userLoad\" vtype=\"required\" vmsg=\"登录帐号名不能为空\"  unique=\"true\" >\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">用户密码<span style=\"color:red\">*</span></th>\r\n");
      out.write("\t\t\t\t<td width=\"30%\">\r\n");
      out.write("\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"userPwd\" name=\"userPwd\" vtype=\"required\" vmsg=\"用户密码不能为空\"   >\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">用户姓名<span style=\"color:red\">*</span></th>\r\n");
      out.write("\t\t\t\t<td width=\"30%\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"form-control\"  id=\"userName\" name=\"userName\" vtype=\"required\" vmsg=\"用户姓名不能为空\"   >\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">移动电话<span style=\"color:red\">*</span></th>\r\n");
      out.write("\t\t\t\t<td width=\"30%\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"form-control\"  id=\"userPhone\" name=\"userPhone\" vtype=\"mobile\" vmsg=\"手机号码格式有误【移动电话】\"   >\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">座机电话<span style=\"color:red\">*</span></th>\r\n");
      out.write("\t\t\t\t<td width=\"30%\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"form-control\"  id=\"userCall\" name=\"userCall\" vtype=\"required\" vmsg=\"座机电话不能为空\"   >\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">邮箱<span style=\"color:red\">*</span></th>\r\n");
      out.write("\t\t\t\t<td width=\"30%\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"form-control\"  id=\"userEmail\" name=\"userEmail\" vtype=\"email\" vmsg=\"邮箱地址格式有误【邮箱】\"   >\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th width=\"20%\">所属机构<span style=\"color:red\">*</span> </th>\r\n");
      out.write("\t\t\t\t<td width=\"30%\">\r\n");
      out.write("\t\t\t\t<select class=\"form-control branchId\" id=\"branchId.branchId\" name=\"branchId.branchId\"   foreign-key=\"true\" vtype=\"required\" vmsg=\"所属机构不能为空\" >\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t          <td colspan=\"4\"><button id=\"edit-win-btn\" class=\"btn btn-primary btn-block\">确认</button></td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t\t    </table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 增加、编辑时的窗口 end-->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 高级查询窗口 -->\r\n");
      out.write("\t\t<div id=\"query-advance-win\" class=\"none\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 高级查询窗口 end-->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 批量编辑窗口 -->\r\n");
      out.write("\t\t<div id=\"batch-edit-win\" class=\"none\">\r\n");
      out.write("\t\t\t<div id=\"batch-edit-content\">\r\n");
      out.write("\t\t\t\t<table id=\"tb-batch-data\" class=\"table table-bordered table-striped table-hover\">\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"tx-right\"><button id=\"batch-edit-btn-submit\" type=\"button\" class=\"btn btn-primary\" style=\"width:200px !important;\">确定</button></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 批量编辑窗口 end-->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 文件上传弹出框 -->\r\n");
      out.write("\t\t<div id=\"fileup-win\" class=\"none\">\r\n");
      out.write("\t\t   <div class=\"col-md-12\">\r\n");
      out.write("                <div class=\"panel\">\r\n");
      out.write("                    <div class=\"panel-heading\">\r\n");
      out.write("                      \t\r\n");
      out.write("                    <span class=\"tools pull-right\">\r\n");
      out.write("                      <a class=\"fa fa-chevron-down\" href=\"javascript:;\"></a>\r\n");
      out.write("                      <a class=\"fa fa-times\" href=\"javascript:;\"></a>\r\n");
      out.write("                    </span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"panel-body\">\r\n");
      out.write("                    \t<form method=\"post\" role=\"form-horizontal\" enctype=\"multipart/form-data\" id=\"importExcel\">                             \r\n");
      out.write("                            <div class=\"compose-editor\">                                  \r\n");
      out.write("                                <input id=\"file\" type=\"file\" class=\"default\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <hr/>\r\n");
      out.write("                            <div class=\"compose-btn\">\r\n");
      out.write("                            \t <button type=\"button\" id=\"download\" class=\"btn btn-info btn-md\">\r\n");
      out.write("\t\t\t\t\t\t           <span class=\"glyphicon glyphicon-download\"></span>模板下载\r\n");
      out.write("\t\t\t\t\t\t         </button>\r\n");
      out.write("\t                             <button id=\"fileimportbtn\" class=\"btn btn-primary btn-md\">\r\n");
      out.write("                                \t<span class=\"glyphicon glyphicon-upload\"></span> 导入</button>\r\n");
      out.write("                                <button type=\"button\" class=\"btn btn-md btn-default\" id=\"clear\">\r\n");
      out.write("                                \t<span class=\"glyphicon glyphicon-remove\"></span> 清除</button>\r\n");
      out.write("                            </div>\r\n");
      out.write("                           </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\t\t</div><!-- 文件上传弹出框 end-->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 用户分配权限弹出框 -->\r\n");
      out.write("\t\t <div id=\"allocate-win\" class=\"none\" style=\"margin-left:10px;margin-right:10px;margin-top:10px\">\r\n");
      out.write("\t\t\t<div id=\"allocate-content\">\r\n");
      out.write("\t\t\t    <div style=\"height:270px; overflow-y: scroll;\">\r\n");
      out.write("\t\t\t\t<table id=\"tb-allacate-data\" class=\"table table-bordered table-hover\">\r\n");
      out.write("\t\t\t\t  <thead>\r\n");
      out.write("\t\t\t      <tr>\r\n");
      out.write("\t\t\t      \t<th class=\"col-xs-2\"><input type=\"checkbox\" id=\"checkAll-allacate\" onclick=\"chooseAll()\"/></th>\r\n");
      out.write("\t\t\t\t\t<th class=\"col-xs-4\">角色名称</th>\r\n");
      out.write("\t                <th class=\"col-xs-6\">角色描述</th>\r\n");
      out.write("\t\t\t      </tr>\r\n");
      out.write("\t\t\t      </thead>\r\n");
      out.write("\t\t\t      <tbody>\r\n");
      out.write("\t\t\t      </tbody>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- 分页栏 -->\r\n");
      out.write("\t\t        <div class=\"row-ant\"><div class=\"col-sm-12-ant\"><div class=\"pager-lan\" id=\"kkpager-role\"></div></div></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"tx-right\"><button id=\"allocate-btn-submit\" type=\"button\" class=\"btn btn-primary\" style=\"width:100% !important;\">确定</button></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- 用户分配权限弹出框 end-->\r\n");
      out.write("\t</div>\r\n");
      out.write(" \r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fimport_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_005fimport_005f0 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_005fimport_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fimport_005f0.setParent(null);
    // /WEB-INF/pages/generated/rolem/User.jsp(373,3) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fimport_005f0.setUrl("/WEB-INF/pages/template/header.jsp");
    int[] _jspx_push_body_count_c_005fimport_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fimport_005f0 = _jspx_th_c_005fimport_005f0.doStartTag();
      if (_jspx_th_c_005fimport_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fimport_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fimport_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fimport_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.reuse(_jspx_th_c_005fimport_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fimport_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_005fimport_005f1 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_005fimport_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fimport_005f1.setParent(null);
    // /WEB-INF/pages/generated/rolem/User.jsp(381,27) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fimport_005f1.setUrl("/WEB-INF/pages/template/leftmenu.jsp");
    int[] _jspx_push_body_count_c_005fimport_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fimport_005f1 = _jspx_th_c_005fimport_005f1.doStartTag();
      if (_jspx_th_c_005fimport_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fimport_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fimport_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fimport_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.reuse(_jspx_th_c_005fimport_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fimport_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_005fimport_005f2 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_005fimport_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fimport_005f2.setParent(null);
    // /WEB-INF/pages/generated/rolem/User.jsp(384,29) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fimport_005f2.setUrl("/WEB-INF/pages/template/footer.jsp");
    int[] _jspx_push_body_count_c_005fimport_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fimport_005f2 = _jspx_th_c_005fimport_005f2.doStartTag();
      if (_jspx_th_c_005fimport_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fimport_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fimport_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fimport_005f2.doFinally();
      _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.reuse(_jspx_th_c_005fimport_005f2);
    }
    return false;
  }
}
