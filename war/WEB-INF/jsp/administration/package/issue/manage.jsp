<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/ztree/js/jquery.ztree.js" type="text/javascript"></script>
<link rel="stylesheet" href="${rootUrl }plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src='${rootUrl }myjs/ocx/misposClient.js'></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true
	});
});
$(document).ready(function(){
	    var r = '${ pageModel.agencyList}';
	    var result = r.split(",");
	    for(var i=0;i<result.length;i++){
	      $("input[value='"+result[i]+"']").attr("checked","checked");
	  }
	});
</script>

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
		
		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>
			
			<!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div id="myManage" class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">发行套餐管理</h4>
                        </div>
                        <div class="panel-toolbar ">
                     		<a href="${rootUrl }app/administration/package/issue/add"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加</a>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/administration/package/issue/manage" method="post">
								<div class="form-group">
									<form:label path="packageName">名称:</form:label>
									<form:input path="packageName"  maxlength="200"  cssClass="form-control"  placeholder="名称" />
<!-- 									<select name="" id=""> -->
<!-- 										<option value="">全部</option> -->
<%-- 										<c:forEach items = "${status}" var="s"> --%>
<%-- 											<option value="${s.code}">${s.value}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
								<label class="control-label" path="vehicleType">车种:</label>
								<form:select path="vehicleType" class="form-control m-r-5"
							title="请选择客货类别" data-style="btn-white" data-width="180px">
							<form:option selected="selected" value="">--请选择--</form:option>
							<form:option value="客车">客车</form:option>
							<form:option value="货车">货车</form:option>
						</form:select>								
						</div>
								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
										<form:option value="10" selected="selected">10</form:option>
										<form:option value="20">20</form:option>
										<form:option value="30">30</form:option>									
										</form:select>
								</div>
						
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
									    <th>套餐编号</th>
										<th>套餐名称</th>
										<th>车种</th>
										<th>发行类型</th>
										<th>生效时间</th>
										<th>失效时间</th>
										<th>充值金额(元)</th>
										<th>OBU费用(元)</th>
										<th>卡费用(元)</th>
										<th>更新时间</th>
										<th>创建人</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager">
	                        </div>
	                       
             		  </div>
					</div>
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
					
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->

</body>
</html>