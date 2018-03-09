package org.apache.jsp.WEB_002dINF.pages.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-CN\">\r\n");
      out.write("  <head>\r\n");
      out.write("  \t<!-- 标准的meta信息 -->\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("    <!-- 标准的meta信息  end-->\r\n");
      out.write("    \r\n");
      out.write("    <!-- 标题 -->\r\n");
      out.write("    <title>柜外清管理平台</title>\r\n");
      out.write("    \r\n");
      out.write("    <style>\r\n");
      out.write("    \t\r\n");
      out.write("    </style>\r\n");
      out.write("\r\n");
      out.write("\t<!-- 定义基准路径的全局变量 -->\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tvar webPath = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<!-- 必须引入的js，详情请见util-required.js -->\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("re/libs/utils/util-required-less.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("re/libs/utils/util-required.js\"></script>\r\n");
      out.write("    <!-- style声明，仅仅只是对当前页面的样式设置，否则请使用公共style表 -->\r\n");
      out.write("    <style>\r\n");
      out.write("\t</style>\r\n");
      out.write("  </head>\r\n");
      out.write("<body onkeydown=\"keyLogin();\">\r\n");
      out.write("\t<!-- <div id=\"login-box\">\r\n");
      out.write("\t\t<div class=\"\">\r\n");
      out.write("\t\t\t<h1>柜外清管理后台</h1>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<form role=\"form\">\r\n");
      out.write("\t\t\t  <div class=\"form-group\">\r\n");
      out.write("\t\t\t    <input type=\"text\" class=\"form-control input-lg\" id=\"userName\" placeholder=\"您的登录名\">\r\n");
      out.write("\t\t\t  </div>\r\n");
      out.write("\t\t\t  <div class=\"form-group\">\r\n");
      out.write("\t\t\t    <input type=\"password\" class=\"form-control input-lg\" id=\"pwd\" placeholder=\"您的密码\">\r\n");
      out.write("\t\t\t  </div>\r\n");
      out.write("\t\t\t  <button id=\"login\" type=\"button\" class=\"btn btn-primary btn-block btn-lg\">登&nbsp;&nbsp;录</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div> -->\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"container-fluid\">\r\n");
      out.write("\t\t<div class=\"row\" style=\"margin-top:80px;\">\r\n");
      out.write("\t\t\t<div class=\"col-sm-2\">&nbsp;</div>\r\n");
      out.write("\t\t\t<div class=\"col-sm-10\"><h1>柜外清管理平台</h1></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"row\" style=\"background-color:#565656;height:280px;\">\r\n");
      out.write("\t\t\t<div class=\"col-sm-2\">&nbsp;</div>\r\n");
      out.write("\t\t\t<div class=\"col-sm-5\"><img src=\"");
      out.print(basePath);
      out.write("re/img/11.png\" height=\"280\"/></div>\r\n");
      out.write("\t\t\t<div class=\"col-sm-3\" style=\"height:280px;\">\r\n");
      out.write("\t\t\t\t<div class=\"panel panel-default\" style=\"margin-top:40px;padding-top:10px;\">\r\n");
      out.write("\t\t\t\t  <div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t    <div class=\"input-group\">\r\n");
      out.write("\t\t\t\t\t  <span class=\"input-group-addon\" id=\"basic-addon1\"><span class=\"glyphicon glyphicon-user\"> </span></span>\r\n");
      out.write("\t\t\t\t\t  <input id=\"userName\" type=\"text\" class=\"form-control\" placeholder=\"请输入您的登录名\" >\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"input-group\" style=\"margin-top:18px;margin-bottom:18px;\">\r\n");
      out.write("\t\t\t\t\t  <span class=\"input-group-addon\" id=\"basic-addon2\"><span class=\"glyphicon glyphicon-asterisk\"> </span></span>\r\n");
      out.write("\t\t\t\t\t  <input id=\"pwd\" type=\"password\" class=\"form-control\" placeholder=\"请输入您的密码\" >\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<input id=\"login\" type=\"button\" class=\"btn btn-primary btn-block\" value=\"确定\" />\r\n");
      out.write("\t\t\t\t  </div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"col-sm-2\">&nbsp;</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$('#login').click(function(){\r\n");
      out.write("\t\t\tlogin();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#userName\").focus();\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction keyLogin(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t if (event.keyCode==13)  //回车键的键值为13\r\n");
      out.write("\t\t\t login(); //调用登录按钮的登录事件\r\n");
      out.write("\t\t}\r\n");
      out.write("  function login(){\r\n");
      out.write("\t  var userL = $(\"#userName\").val();\r\n");
      out.write("\t  var userP = $(\"#pwd\").val();\r\n");
      out.write("\t  var url = webPath+'login/loginValid.html';\r\n");
      out.write("\t  goAjax(url,{userLoad:userL,userPwd:userP},function(data){\r\n");
      out.write("\t\t  if(data.flag=='0'){\r\n");
      out.write("\t\t\t window.location.href = webPath+'main.html' ;\r\n");
      out.write("\t\t  }else{\r\n");
      out.write("\t\t     alert(\"用户名或密码错误\");\r\n");
      out.write("\t\t  }\r\n");
      out.write("\t  });\r\n");
      out.write("  }\r\n");
      out.write("</script>\r\n");
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
