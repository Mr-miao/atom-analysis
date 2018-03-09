package org.apache.jsp.WEB_002dINF.pages.template;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class leftmenu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write("    \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title></title>\r\n");
      out.write("<script>\r\n");
      out.write("\tvar webPath = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("</script>\r\n");
      out.write("<style>\r\n");
      out.write("\t#_nav_content{background:#424F63;width:100%;height:100%;color:#fff;padding:0px;margin:0px;text-indent:15px;overflow:hidden;font-size:12px;}\r\n");
      out.write("\t#_nav_content ul, #_nav_content li{list-style:none;padding:0px;margin:0px;}\r\n");
      out.write("\t#_nav_content li{padding:10px 0px;margin-bottom:1px;}\r\n");
      out.write("\t#_nav_content li:hover{background:#353F4F;color:#4BC5A7;cursor:pointer;}\r\n");
      out.write("\t#_nav_content ._menu_current{background:#353F4F;color:#4BC5A7;cursor:pointer;}\r\n");
      out.write("\t#_nav_content ._menu_nav{background:transparent url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center;}\r\n");
      out.write("\t#_nav_content ._menu_nav:hover{background:transparent url(");
      out.print(basePath);
      out.write("re/img/plus.png) no-repeat 93% center;}\r\n");
      out.write("\t#_nav_content ._children{display:none;}\r\n");
      out.write("\t#zhankai-div{position:fixed;top:100px;display:none;z-index:13}\r\n");
      out.write("\t#shousuo{text-align:left;height:40px;}\r\n");
      out.write("</style>\r\n");
      out.write("<script>\r\n");
      out.write("\tvar getMenuUrl = webPath + \"_menu/findAllMenu.html\";\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//设置导航菜单高度\r\n");
      out.write("\t\t$('#_nav_content').css({\"height\":$(document).height()-$('#_title').height()});\r\n");
      out.write("\t\t//拉取导航菜单\r\n");
      out.write("\t\tgoAjax(getMenuUrl,{},function(data){\r\n");
      out.write("\t\t\tif(data.flag==\"0\"){\r\n");
      out.write("\t\t\t\tvar moduleList = data.moduleList;\r\n");
      out.write("\t\t\t\tvar transList = data.transList;\r\n");
      out.write("\t\t\t\tmoduleList = transAppendModule(moduleList,transList);\r\n");
      out.write("\t\t\t\tvar arr = findRootNodes(moduleList);\r\n");
      out.write("\t\t\t\tvar dest = findChildren(arr,moduleList);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tvar str = genEle(dest);\r\n");
      out.write("\t\t\t\tvar container = '<div></div>';\r\n");
      out.write("\t\t\t\tvar div = $(container).html(str).get(0);\r\n");
      out.write("\t\t\t\tvar allEle = $(div).find('ul');\r\n");
      out.write("\t\t\t\tvar rootEle = genRootEle(allEle);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\talert(\"加载导航菜单失败\");\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//将元素追加到收缩按钮后\r\n");
      out.write("\t\t\t$('#shousuo').after(genChildEle(rootEle,allEle).get(0));\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//默认展开选中的导航菜单\r\n");
      out.write("\t\t\tvar curUrl = window.location.href;\r\n");
      out.write("\t\t\t$.each($('._menu_leaf'),function(i,ele){\r\n");
      out.write("\t\t\t\tif(curUrl.indexOf($(ele).attr('url'))>0){\r\n");
      out.write("\t\t\t\t\t$(ele).parent().slideDown('normal');\r\n");
      out.write("\t\t\t\t\t$(ele).parent().parent().slideDown('normal');\r\n");
      out.write("\t\t\t\t\t$(ele).addClass('_menu_current');\r\n");
      out.write("\t\t\t\t\t$(ele).parent().prev().css('background','url(");
      out.print(basePath);
      out.write("re/img/open.png) no-repeat 93% center');\r\n");
      out.write("\t\t\t\t\t$(ele).parent().parent().prev().css('background','url(");
      out.print(basePath);
      out.write("re/img/open.png) no-repeat 93% center');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$('._menu_nav').click(function(){\r\n");
      out.write("\t\t\t\tif($(this).parent().prev()[0].getAttribute(\"class\") != \"_menu_nav\"){\r\n");
      out.write("\t\t\t\t\t$(this).siblings().children(\"._children\").css(\"display\",\"none\");\r\n");
      out.write("\t\t\t\t\t$(this).siblings().children(\"._menu_nav\").css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t$(this).siblings(\"._menu_nav\").css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\tif($(this).next(\"._children\").css(\"display\") != \"block\"){\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t$(this).css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/open.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t\t$(this).siblings(\"._children\").not(\".selected\").slideUp(150);\r\n");
      out.write("\t\t\t\t\t\t$(this).siblings(\"._menu_nav\").not(\".selected\").css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t\t$(this).next(\"._children\").addClass(\"selected\").css(\"display\",\"block\");\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(this).next(\"._children\").addClass(\"selected\").css(\"display\",\"none\");\r\n");
      out.write("\t\t\t\t\t\t$(this).css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t\t$(this).siblings(\"._menu_nav\").not(\".selected\").css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$(this).next(\"._children\").removeClass(\"selected\");\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tif($(this).next(\"._children\").css(\"display\") != \"block\"){\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t$(this).css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/open.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t\t$(this).siblings(\"._children\").not(\".selected\").slideUp(150);\r\n");
      out.write("\t\t\t\t\t\t$(this).siblings(\"._menu_nav\").not(\".selected\").css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t\t$(this).next(\"._children\").addClass(\"selected\").css(\"display\",\"block\");\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$(this).next(\"._children\").addClass(\"selected\").css(\"display\",\"none\");\r\n");
      out.write("\t\t\t\t\t\t$(this).css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t\t$(this).siblings(\"._menu_nav\").not(\".selected\").css(\"background\",\"url(");
      out.print(basePath);
      out.write("re/img/plus-white.png) no-repeat 93% center\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$(this).next(\"._children\").removeClass(\"selected\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$('._menu_leaf').click(function(){\r\n");
      out.write("\t\t\t\t$('._menu_leaf').removeClass('_menu_current');\r\n");
      out.write("\t\t\t\t$(this).addClass('_menu_current');\r\n");
      out.write("\t\t\t\tif($(this).attr('url')!=undefined&&$(this).attr('url')!=''&&$(this).attr('url')!=null&&$(this).attr('url')!='null'){\r\n");
      out.write("\t\t\t\t\twindow.location.href = webPath+$(this).attr('url');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t \r\n");
      out.write("\t\t\t$('._children').each(function(index, ele){\r\n");
      out.write("\t\t\t\tvar paddingLeft = $(this).prev().css('text-indent');\r\n");
      out.write("\t\t\t\tvar paddingLeftValue = paddingLeft.substring(0, paddingLeft.indexOf('px'));\r\n");
      out.write("\t\t\t\tvar childrenValue = (parseInt(paddingLeftValue)+18)+\"px\";\r\n");
      out.write("\t\t\t\t$(this).css('text-indent', childrenValue);\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}); \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#shousuo\").click(function(){\r\n");
      out.write("\t\t\t$('#_nav_content').fadeOut();\t\r\n");
      out.write("\t\t\t$('.right').animate({width:\"100%\"});\r\n");
      out.write("\t\t\t$(\"#zhankai-div\").show();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#zhankai\").click(function(){\r\n");
      out.write("\t\t\t$('#_nav_content').fadeIn();\t\r\n");
      out.write("\t\t\t$('.right').animate({width:\"83%\"});\t\r\n");
      out.write("\t\t\t$(\"#zhankai-div\").hide();\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("var transAppendModule = function(moduleList,transList){\r\n");
      out.write("\t$.each(moduleList,function(i,module){\r\n");
      out.write("\t\tmodule.children = [];\r\n");
      out.write("\t\t$.each(transList,function(j,trans){\r\n");
      out.write("\t\t\tif(module.id==trans.moduleId){\r\n");
      out.write("\t\t\t\tmodule.children.push(trans);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\treturn moduleList;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//生成根节点\r\n");
      out.write("var findRootNodes = function(moduleList){\r\n");
      out.write("\tvar arr= [];\r\n");
      out.write("\t$.each(moduleList,function(i,json){\r\n");
      out.write("\t\tif(json.modulePid==0){\r\n");
      out.write("\t\t\tarr.push(json);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\treturn arr;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//根节点下面添加children\r\n");
      out.write("var findChildren = function(fatherList,moduleList){\r\n");
      out.write("\tvar arr = [];\r\n");
      out.write("\t$.each(fatherList,function(i,father){\r\n");
      out.write("\t\t$.each(moduleList,function(j,child){\r\n");
      out.write("\t\t\tif(father.id==child.modulePid){\r\n");
      out.write("\t\t\t\tfather.children.push(child);\r\n");
      out.write("\t\t\t\tarr.push(child);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t$.each(arr,function(k,json){\r\n");
      out.write("\t\tif((json.children!=undefined)){\r\n");
      out.write("\t\t\tfindChildren(arr,moduleList);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\treturn fatherList;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("var dom = '';\r\n");
      out.write("\r\n");
      out.write("//将节点转换为元素\r\n");
      out.write("var genEle = function(nodeList){\r\n");
      out.write("\tdom += jsonarrToUL(nodeList)\r\n");
      out.write("\t$.each(nodeList,function(i,node){\r\n");
      out.write("\t\tif((node.children!=undefined)&&(node.children.length>0)){\r\n");
      out.write("\t\t\tgenEle(node.children);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\treturn dom;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//将json转换为UL\r\n");
      out.write("var jsonarrToUL = function(jsonarr){\r\n");
      out.write("\tvar box = '';\r\n");
      out.write("\tif(jsonarr!=undefined&&jsonarr.length>0){\r\n");
      out.write("\t\tif((jsonarr[0].modulePid!=undefined)&&(jsonarr[0].modulePid==0)){\r\n");
      out.write("\t\t\tbox = '<ul pid=\"'+(jsonarr[0].modulePid)+'\">';\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tbox = '<ul pid=\"'+(jsonarr[0].modulePid==undefined?jsonarr[0].moduleId:jsonarr[0].modulePid)+'\" class=\"_children\">';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.each(jsonarr,function(i,json){\r\n");
      out.write("\t\t\tif((json.children==undefined)||(json.children.length==0)){\r\n");
      out.write("\t\t\t\tbox += '<li pid=\"'+json.id+'\" class=\"_menu_leaf\" url=\"'+json.trnUrl+'\"><span class=\"'+json.modIcon+'\"></span> '+(json.modCnName==undefined?json.serCnName:json.modCnName)+'</li>';\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tbox += '<li pid=\"'+json.id+'\" class=\"_menu_nav\"><span class=\"'+json.modIcon+'\"></span> '+(json.modCnName==undefined?json.serCnName:json.modCnName)+'</li>';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\treturn box + '</ul>';\r\n");
      out.write("\t}\r\n");
      out.write("\treturn box;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//生成根元素\r\n");
      out.write("var genRootEle = function(arr){\r\n");
      out.write("\tvar root = [];\r\n");
      out.write("\t$.each(arr,function(i,ul){\r\n");
      out.write("\t\tif($(ul).attr('pid')==0){\r\n");
      out.write("\t\t\troot.push(ul);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\treturn $(root);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//在根元素后追加子元素\r\n");
      out.write("var genChildEle = function(rootEle,allEle){\r\n");
      out.write("\tvar temp;\r\n");
      out.write("\t$.each($(rootEle).children(),function(i,li){\r\n");
      out.write("\t\t$.each(allEle,function(j,ul){\r\n");
      out.write("\t\t\tif($(ul).attr('pid')==$(li).attr('pid')){\r\n");
      out.write("\t\t\t\t$(li).after($(ul));\r\n");
      out.write("\t\t\t\tallEle.splice(j,1);\r\n");
      out.write("\t\t\t\ttemp = $(ul);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\tif(allEle.length>0){\r\n");
      out.write("\t\t\tgenChildEle(temp,allEle);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\treturn rootEle;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body id=\"left_body\">\r\n");
      out.write("\t<div id=\"zhankai-div\">\r\n");
      out.write("\t\t<img id=\"zhankai\" src=\"");
      out.print(basePath );
      out.write("re/img/shousuo3.png\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"_nav_content\">\r\n");
      out.write("\t\t<li id=\"shousuo\"><label class=\"col-sm-9\" style=\"color:#ccc;padding:0;font-family:Monospace;\">柜外清管理平台</label><span class=\"col-sm-3 glyphicon glyphicon-chevron-left\"></span> &nbsp;&nbsp;</li>\r\n");
      out.write("\t</div>\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
}
