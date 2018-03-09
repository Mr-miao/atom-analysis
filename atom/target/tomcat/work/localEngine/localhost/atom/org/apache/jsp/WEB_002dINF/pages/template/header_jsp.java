package org.apache.jsp.WEB_002dINF.pages.template;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.nantian.atom.generated.po.rolem.User;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextPath = basePath + "resources/";
	
	String name = ((User)(request.getSession().getAttribute("user"))).getUserName();

      out.write("        \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title></title>\r\n");
      out.write("<script>\r\n");
      out.write("\tvar webPath = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("\tvar userName = '");
      out.print(name );
      out.write("';\r\n");
      out.write("</script>\r\n");
      out.write("<style>\r\n");
      out.write("\t#_title{background:#fff !important;height:50px;}\r\n");
      out.write("\t#_title li{list-style:none;}\r\n");
      out.write("\t#_title ._user li{float:right;line-height:50px;color:#428BCA;}\r\n");
      out.write("</style>\r\n");
      out.write("<script>\r\n");
      out.write("\tvar logoutUrl = webPath + \"logout.html\";\r\n");
      out.write("    var changePasswordUrl=webPath+\"rolem/user/_changePassword.html\";\r\n");
      out.write("\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$('#_userName').text(userName);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$('#_logout').click(function(){\r\n");
      out.write("\t\t\tif(confirm('确定注销')){\r\n");
      out.write("\t\t\t\tlocation.href = logoutUrl;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$('#_change_pwd').click(function(){\r\n");
      out.write("\t\t\t$('#first-input').val('');\r\n");
      out.write("\t\t\t$('#second-input').val('');\r\n");
      out.write("\t\t\t//弹出修改密码区域\r\n");
      out.write("\t\t\tlayer.open({\r\n");
      out.write("\t\t\t\ttype: 1,\r\n");
      out.write("\t\t\t\tmove:true,\r\n");
      out.write("\t\t\t\ttitle:'修改密码',\r\n");
      out.write("\t\t\t\t//skin: 'layui-layer-rim', //加上边框\r\n");
      out.write("\t\t\t\tarea: ['250px', \"210px\"], //宽高\r\n");
      out.write("\t\t\t\toffset: '80px',\r\n");
      out.write("\t\t\t\tshade: [0.8, '#393D49'],\r\n");
      out.write("\t\t\t\tzIndex : 3,\r\n");
      out.write("\t\t\t\tcontent: $('#change-password-win')\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//修改密码提交\r\n");
      out.write("\t\t$('#change-password-submit').click(function(){\r\n");
      out.write("            //判断两次密码是否一致\r\n");
      out.write("\t\t\tvar firstInput=$('#first-input').val();\r\n");
      out.write("\t\t\tvar secondInput=$('#second-input').val();\r\n");
      out.write("\t\t\tif(firstInput!=secondInput){\r\n");
      out.write("\t\t\t\talert('两次密码输入不一致，请重新输入');\r\n");
      out.write("\t\t\t\t$('#first-input').val('');\r\n");
      out.write("\t\t\t\t$('#second-input').val('');\r\n");
      out.write("\t\t\t}else if(firstInput==\"\"||secondInput==\"\"){\r\n");
      out.write("\t\t\t\talert('密码不能为空，请重新输入');\r\n");
      out.write("\t\t\t\t$('#first-input').val('');\r\n");
      out.write("\t\t\t\t$('#second-input').val('');\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tif(!confirm('确定修改密码？')){\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//发送密码修改请求\r\n");
      out.write("\t\t\t\t\tgoAjax(changePasswordUrl,{recordId:secondInput},function(data){\r\n");
      out.write("\t\t\t\t\t\tif(data.flag!=0){\r\n");
      out.write("\t\t\t\t\t\t\talert('修改密码失败！请重新修改');\r\n");
      out.write("\t\t\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\talert('修改密码成功！');\r\n");
      out.write("\t\t\t\t\t\t\t//跳转至登陆页面重新登陆\r\n");
      out.write("\t\t\t\t\t\t\twindow.location.href = webPath+'login.html' ;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"_title\" class=\"row-ant _head\">\r\n");
      out.write("\t\t <div class=\"col-sm-7-ant\" id=\"logo-has\">\r\n");
      out.write("\t\t \t<img src=\"");
      out.print(basePath );
      out.write("re/img/logo.png\" height=\"50\">\r\n");
      out.write("\t\t </div>\r\n");
      out.write("\t\t <div class=\"col-sm-1-ant\" id=\"sys-logo\">\r\n");
      out.write("\t\t \t&nbsp;\r\n");
      out.write("\t\t </div>\r\n");
      out.write("\t\t <div class=\"col-sm-4-ant _user\">\r\n");
      out.write("            <li>\r\n");
      out.write("\t            <span id=\"_userName\">徐瑞</span>，欢迎您&nbsp;&nbsp;\r\n");
      out.write("\t            <button id=\"_logout\" type=\"button\" class=\"btn btn-danger btn-sm\"><span class=\"glyphicon glyphicon-off\"></span> 注销</button>&nbsp;&nbsp;\r\n");
      out.write("\t            <button id=\"_change_pwd\" type=\"button\" class=\"btn btn-warning btn-sm\"><span class=\"glyphicon glyphicon-lock\"></span> 修改密码</button>\r\n");
      out.write("            </li>\r\n");
      out.write("\t\t </div>\r\n");
      out.write("\t</div>\r\n");
      out.write("    <!--修改密码框 -->\r\n");
      out.write("\t\t<div id=\"change-password-win\" class=\"none\" style=\"width: 230px\">\r\n");
      out.write("\t\t\t <input type='password' class=\"form-control\" id=\"first-input\" style=\"margin-top:20px; margin-left:10px  \" placeholder='请输入密码' vtype=\"required\"  vmsg=\"密码不能为空\"/>\r\n");
      out.write("\t\t\t <input type='password' class=\"form-control\" id='second-input' style=\"margin-top:20px; margin-left:10px\" placeholder='再次输入密码' vtype=\"required\"  vmsg=\"密码不能为空\"/>\r\n");
      out.write("\t\t\t <center>\r\n");
      out.write("\t\t\t <button id=\"change-password-submit\" type=\"button\" class=\"btn btn-primary\" style=\"width:230px !important;margin-top:20px; margin-left:10px\">确定</button>\r\n");
      out.write("\t\t\t </center>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!--修改密码框end -->\r\n");
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
