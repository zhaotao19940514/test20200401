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
	$("#myManage").taiji({
		enableAclCheck:true
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
                            <h4 class="panel-title">设备入库</h4>
                        </div>
                        <div class="panel-toolbar ">
                     		<a href="${rootUrl }app/administration/inventory/devicewarehousing/add"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加</a>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/administration/inventory/devicewarehousing/manage" method="post">
								<div class="form-group">
									<form:label path="typeofproduction">产品类型:</form:label>
<!-- 									<select name="" id=""> -->
<!-- 										<option value="">全部</option> -->
<%-- 										<c:forEach items = "${status}" var="s"> --%>
<%-- 											<option value="${s.code}">${s.value}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
									<form:select path="typeofproduction"  cssClass="selectpicker" data-style="btn-white" data-width="120px">
										<form:option value="">全部</form:option>
										<form:options items="${status}" itemLabel="value"/>
									</form:select>
									<form:label path="brand">品牌:</form:label>
									<form:select path="brand"  cssClass="selectpicker" data-style="btn-white" data-width="80px">
										<form:option value="">全部</form:option>
										<form:options items="${status}" itemLabel="value"/>
									</form:select>
									<form:label path="typehood">型号:</form:label>
									<form:select path="typehood"  cssClass="selectpicker" data-style="btn-white" data-width="120px">
										<form:option value="">全部</form:option>
										<form:options items="${status}" itemLabel="value"/>
									</form:select>
									<form:label path="storageNo">入库单编号:</form:label>
									<form:input path="storageNo"  maxlength="100"  cssClass="form-control"  placeholder="入库单编号" />
									<form:label path="status">状态:</form:label>
									<form:select path="status"  cssClass="selectpicker" data-style="btn-white" data-width="80px">
										<form:option value="">全部</form:option>
										<form:options items="${storageStatus}" itemLabel="value" itemValue="code"/>
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
										<th>序号</th>
										<th>入库单编号</th>
										<th>产品类型</th>
										<th>品牌</th>
										<th>型号</th>
										<th>数量</th>
										<th>起始编号</th>
										<th>结束编号</th>
										<th>入库网点</th>
										<th>业务员</th>
										<th>状态</th>
										<th>操作时间</th>
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