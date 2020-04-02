<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji().on("taijiSearchSuccess",function(event,result){
		$("#${today}").addClass("cal-today");
		$("#searchCondition").text($(result).find(".searchCondtion").html());
	});
	$("#myManage").on("click",".cal-click",function(){
		alert($(this).attr("id"));
		return false;
	});
	$("#todayId").click(function(){
		$("#year").val("${year}");
		$("#month").val("${month}");
		$("#queryButton").click();
		return false;
	});
});
</script>
<style type="text/css">
body {
    font-family: "Arial","Microsoft YaHei","黑体","宋体","sans-serif";
    color: #2c3e50;
}
.cal-box {
	background-color:white;
/* 	border: 2px solid #6cd3fb; */
	padding-bottom: 30px;
}
.cal-header{
	height: 60px;
	padding: 10px;
	border-bottom: 1px solid #d0f7fd;
}
.cal-body{
	padding: 0 10px;
}
.cal-table{
	width:100%;
	text-align: center;
	vertical-align: middle;
	border-collapse:collapse;
	border-spacing: 0;
}
.cal-table tr{
	height: 61px;
	border-bottom: 1px solid #e3e4e6;
}
.cal-table thead>tr{
	height: 40px;
}
.cal-table th{
	font-size: 16px;
	text-align: center;
}
.cal-table td{
	color: #000000;
    font-size: 20px;
}
.cal-table td>a{
	text-decoration:none;
	color: #000000;
    display: block;
    font-size: 24px;
    height:60px;
    line-height:60px;
    margin: auto;
    width: 70px;
}
.cal-table td>a:hover{
	text-decoration:none;
	border: 3px solid #ffbb00;
}
.cal-table td>a.test{
	border: 3px solid #ffbb00;
}
.cal-table td>a.cal-today{
	background-color:#b6c2c9;
}
#myManage1::before,#myManage1::after {
	  content:"";  
      position: absolute;  
      top: 14px;  
      width: 16px;  
      height: 16px;  
      background-color: white;  
      z-index:1;  
      border-radius:10px;  
      box-shadow: inset 1px 2px 2px 2px #aaaaaa;
}
#myManage::before{
	left:180px;
}
#myManage::after{
	right:180px;
}

</style>
</head>
<body>
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>

<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
		<!-- end #sidebar -->
		<!-- 内容页 -->
				<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>
			<!-- begin page-header -->
			<h1 class="page-header">示例管理页面</h1>
			<!-- end page-header -->
			
		<div class="row">
<div class="col-md-12">
   <div id="myManage" class="panel panel-default" style="width:586px;position: relative;">
     <div class="panel-heading">
         <h4 class="panel-title">工作日</h4>
     </div>
	<div class="cal-box">
		<form class="taiji_search_form cal-header form-inline" action="${rootUrl}app/system/workday/manage" method="post">
			
			<label class="control-label" id="searchCondition" style="font-size: 18px;"></label>
			<div class="form-group m-t-5">
				<select name="year" id="year" class="form-control">
					<c:forEach items="${years }"  var="y">
						<option value="${y}"  <c:if test="${y eq year}">selected</c:if> >${y }月</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group m-t-5">
				<select name="month" id="month" class="form-control">
					<c:forEach items="${months }"  var="m">
						<option value="${m }"  <c:if test="${m eq month}">selected</c:if> >${m.value }月</option>
					</c:forEach>
				</select>
			</div>
			<button id="queryButton" class="taiji_search_submit btn btn-md btn-success m-l-5" type="button" ><i class="fa fa-calendar  m-r-10 "></i>查询</button>
			<button id="todayId" class="btn btn-md btn-default" style="margin-left: 3%" type="button" ><i class="fa fa-clock-o  m-r-10 "></i>今天</button>
		</form>
		<div class="taiji_search_result cal-body">
			<table class="cal-table">
				<thead>
					<tr>
						<th>日</th>
						<th>一</th>
						<th>二</th>
						<th>三</th>
						<th>四</th>
						<th>五</th>
						<th>六</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

</div>  <!-- end panel -->
</div>
		</div>
		</div>
	
	<!-- 内容页 -->

<!-- 版权页 -->
<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
<!-- 版权页 -->
</div>
</body>
</html>