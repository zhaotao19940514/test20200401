<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
            autoSearch:false
        }
	});
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
                            <h4 class="panel-title">车辆管理</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/apply/emergency/vehiclemanagement/manage" method="post">
								<div class="form-group">
									<div class="form-group m-5">
										<form:label path="vehiclePlate">车牌号码</form:label>
									    <form:input path="vehiclePlate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									</div>
									<div class="form-group m-5">
									    <form:label path="vehiclePlateColor">车牌颜色</form:label>
										<form:select path="vehiclePlateColor"  cssClass="form-control m-r-5" data-style="btn-white" data-width="100px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select>
									</div>
									<div class="form-group m-5">
										<form:label path="ownerIdType">所有人证件类型</form:label>
										<form:select path="ownerIdType"  cssClass="form-control m-r-5" data-style="btn-white" data-width="150px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${customerIDTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select>
									</div>
									<div class="form-group m-5">
										<form:label path="ownerIdNum">所有人证件号</form:label>
										<form:input path="ownerIdNum"  maxlength="100"  cssClass="form-control"  placeholder="所有人证件号" />								    								       										
									</div>
									<div class="form-group m-5">
										<form:label path="ownerName">所有人名称</form:label>
										<form:input path="ownerName"  maxlength="100"  cssClass="form-control"  placeholder="所有人名称" />
									</div>
									<div class="form-group m-5">
										<form:label path="customerId">客户编号</form:label>
									    <form:input path="customerId"  maxlength="100"  cssClass="form-control"  placeholder="客户编号" />
									</div>
									<div class="form-group m-5">
										<label class="control-label">每页条数</label>
										<form:select path="pageSize"  cssClass="form-control m-r-5"  data-style="btn-white" data-width="80px">
											<form:option value="2">2</form:option>
											<form:option value="6">6</form:option>
											<form:option value="10" selected="selected">10</form:option>
											<form:option value="15">15</form:option>
											<form:option value="30">30</form:option>
										</form:select>
										<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
										<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
									</div>							    								       																		         
								</div>
                        	</form:form>
                        </div>
						<div class="taiji_search_result taiji_table_float table-responsive">
							<table id="my-table" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>车辆编号</th>
										<th>客户编号</th>
										<th>客户名称</th>
<!-- 										<th>客户证件类型</th> -->
										<th>客户证件号码</th>
										<th>车牌号码</th>
										<th>车牌颜色</th>
										<th>车型</th>
 										<th>是否应急车辆</th> 
 										<th>更新时间</th>
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