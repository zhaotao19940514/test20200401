<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title>内部错误</title>
<link rel="stylesheet" type="text/css" href="${rootUrl }css/main.css" />
</head>
<body>
<br/><br/><br/><br/><br/>
<div align="center"><img src="${rootUrl }images/500/500-2.jpg"></img></div>
<br/>
<div align="center" style="color: red;font-size: 18px">
<%
	try
	{
		// The Servlet spec guarantees this attribute will be available
		Throwable exception = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		if (exception != null)
		{
			//out.println("错误信息(查看系统日志获取更多)：" + exception.getMessage());
			request.setAttribute("exception", "错误信息(查看系统日志获取更多)：" + exception.getMessage());
		}
		else
		{
			//out.println("<br/>无具体错误信息.");
			request.setAttribute("exception", "无具体错误信息.");
		}
		// Display cookies
		//out.println("<br/>当前的Cookies：");
		//Cookie[] cookies = request.getCookies();
		//if (cookies != null)
		//{
			//for (int i = 0; i < cookies.length; i++)
		//	{
		//		out.println(cookies[i].getName() + "=[" + cookies[i].getValue() + "]");
		//	}
	//	}
	}
	catch (Exception ex)
	{
		ex.printStackTrace(new java.io.PrintWriter(out));
	}
%>
${fn:escapeXml(exception) }
</div>
<br/>
<div align="center"><a href="javascript:void(0)" onclick="javascript:history.back();return false;">返回上一页</a></div>
</body>
</html>

