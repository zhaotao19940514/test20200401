<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script type="text/javascript">
$(function(){
	provider=0;
	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false
		}
	});
})	
	
</script>

</head>
<body>
<input type="hidden" id="cardBalance" value='-1'/>
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
                            <h4 class="panel-title">退费处理查询</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/administration/refund/manage" method="post">
								<div class="form-group">、
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
									<form:input path="vehiclePlate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号" />
									<div class="form-group m-5">
									<label class="control-label">状态</label>
									<form:select path="status" cssClass="selectpicker" data-style="btn-white" data-width="80px">
										<form:option value="">全部</form:option>
										<form:option value="0">未处理</form:option>
										<form:option value="1">已处理</form:option>
									</form:select>
								</div>
								</div>
								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
										<form:option value="2">2</form:option>
										<form:option value="10">10</form:option>
										<form:option value="16">16</form:option>
										<form:option value="20">20</form:option>
										<form:option value="50">50</form:option>
										<form:option value="100">100</form:option>
									</form:select>
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" id='searchBtn' type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>流水号</th>
										<th>卡号</th>
										<th>车牌号</th>
										<th>进站时间</th>
										<th>进站口</th>
										<th>出站时间</th>
										<th>出站口</th>
										<th>流水金额</th>
										<th>实际退费金额</th>
										<th>退费类型</th>
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