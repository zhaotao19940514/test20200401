<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
			cache : false
		});
		$("#myManage").taiji({
			enableAclCheck : true,
			search : {
				autoSearch : false
			}
		});
		$("#printBut").click(
				function() {
					//window.print();
					var bdhtml = window.document.body.innerHTML;//获取当前页的html代码 
					var startStr = "<!--startprint-->";//设置打印开始区域 
					var endStr = "<!--endprint-->";//设置打印结束区域 
					var printHtml = bdhtml.substring(bdhtml.indexOf(startStr)
							+ startStr.length, bdhtml.indexOf(endStr));//从标记里获取需要打印的页面 
					window.document.body.innerHTML = printHtml;//需要打印的页面 
					window.print();
					window.document.body.innerHTML = bdhtml;//还原界面 
					location.reload();
				});
	});
</script>
<style type="text/css">
* {
	margin: auto;
	text-align: center;
}

#content {
	margin: auto;
	width: 960px;
}

#tableContent {
	text-align: left;
}

#tableContent td {
	text-align: left;
	width: 300px;
	height: 22px;
	font-size: 15px;
}
</style>
<title>打印</title>
</head>
<body id="content">
	<div id="myManage" class="panel panel-inverse">
		<!--startprint-->
		<div id="printContent">
			<c:if test="${empty errorMessage}">
				<h2>${title }</h2>
				<br>
				<table id="tableContent">
					<% int t1=0; int t2=0; int t3=0;  int t5=0; %>
					<c:forEach items="${customerInfo}" var='vo' varStatus="voStatus">
						<% 
							if(t1%2 == 0){
								out.print("<tr>");
							}
						%>
							<td>${vo }</td>
						<% 
							if(t1%2 == 1){
								out.print("</tr>");
							}
							t1++;
						%>
					</c:forEach>
					<tr><td style="height: 15px;"> </td></tr>
					<c:forEach items="${vehicleInfo}" var='vo' varStatus="voStatus">
						<% 
							if(t2%2 == 0){
								out.print("<tr>");
							}
						%>
							<td>${vo }</td>
						<% 
							if(t2%2 == 1){
								out.print("</tr>");
							}
							t2++;
						%>
					</c:forEach>
					<tr><td style="height: 15px;"> </td></tr>
					<c:forEach items="${issuePackage}" var='vo' varStatus="voStatus">
						<% 
							if(t3%3 == 0){
								out.print("<tr>");
							}
						%>
							<td>${vo }</td>
						<% 
							if(t3%3 == 2){
								out.print("</tr>");
							}
							t3++;
						%>
					</c:forEach>
					<tr><td style="height: 15px;"> </td></tr>
					<c:forEach items="${issue}" var='vo' varStatus="voStatus">
						<tr>
							<td colspan="3">${vo }</td>
						</tr>
					</c:forEach>
					<tr><td style="height: 15px;"> </td></tr>
					<c:forEach items="${footer}" var='vo' varStatus="voStatus">
						<% 
							if(t5%3 == 0){
								out.print("<tr>");
							}
						%>
							<td>${vo }</td>
						<% 
							if(t5%3 == 2){
								out.print("</tr>");
							}
							if(t5 == 2)
								out.print("<tr><td> </td></tr>");
							t5++;
						%>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${not empty errorMessage}">
				<h2>生成业务回执单失败</h2>
				<br>
				<br>
				<p style="font-size: 32px;color: red;">"${errorMessage }"</p>
			</c:if>
		</div>
		<!--endprint-->
		<br>
		<br>
		<br>
		<c:if test="${empty errorMessage}">
			<input type="button" id="printBut" value="--打印--">
		</c:if>
	</div>
</body>
</html>