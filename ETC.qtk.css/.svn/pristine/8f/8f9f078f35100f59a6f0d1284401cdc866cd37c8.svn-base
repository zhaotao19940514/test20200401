<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<head>
<title>SSO错误</title>
<link rel="shortcut icon" href="${rootUrl }images/favicon.jpg" />
<link rel="stylesheet" type="text/css" href="${rootUrl }css/admin.css" />

<script src="${rootUrl }js/jquery/jquery.js" type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.tjlib.js" type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.taiji.all.js" type="text/javascript"></script>
</head>
<body>
	
	<!-- LOGO -->
	<%-- <%@ include file="/WEB-INF/jsp/top.jsp" %>--%>
	<!-- LOGO -->

<div id="boxmain">
	<!-- 左侧菜单 -->
	<%@ include file="/WEB-INF/jsp/left.jsp" %>
	<!-- 左侧菜单 -->

	<!-- 内容页 -->
<div id="boxright">
	<!--main-->
	<div id="main" style="text-align: center">
		<div style="font-size: 20px;font-weight: bold;padding-top: 120px;color: red;font-family: 黑体;">
			<img src="${rootUrl}images/warn.png" style="vertical-align: middle"/>
			${reason }
			<br/><br/><br/>
			<a href="${rootUrl}app/common/logout"  style="font-size: 20px;font-weight: bold;font-family: 黑体;">
			退出
			</a>
		</div>
		<div style="display: none">
		</div>
	</div>
	<!-- 内容页 -->
</div>
</div>
	<!-- 版权页 -->
	<%@ include file="/WEB-INF/jsp/bottom.jsp" %>
	<!-- 版权页 -->
</body>
</html>
