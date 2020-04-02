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
                            <h4 class="panel-title">记账卡套餐管理</h4>
                        </div>
                        <div class="panel-toolbar ">
                     		<a href="${rootUrl }app/administration/package/account/add"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加</a>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/administration/package/account/manage" method="post">
								<div class="form-group">
									<form:label path="packageName">套餐名称</form:label>
									<form:input path="packageName"  maxlength="100"  cssClass="form-control"/>	
									<form:label path="serviceHallList">机构名称</form:label>
									<form:input path="serviceHallList"  maxlength="100"  cssClass="form-control"/>																					
								</div>
								<%--  <div class="form-group m-5">
									<form:label path="agencyList">机构名称</form:label>
									<select id="agencyId" name="agencyId"  class="selectpicker">
										<option value="" selected="selected">请选择</option>
										<option value="52010104001">货车帮</option>
										<option value="52010104003">中交智慧</option>
										<option value="52010104004">裕福支付</option>
										<option value="52010104005">上海易路通达</option>
										<option value="52010102006">贵阳银行</option>
										<option value="52010102007">中国工商银行</option>
										<option value="52010102008">中国邮政储蓄银行</option>
										<option value="52010106004">黔通智联（自营）</option>
										<option value="52010102003">中国银行</option>
										<option value="52010188012">天地汇</option>
										<option value="52010102015">农业银行</option>
										<option value="52010102014">贵州交通银行</option>
										<option value="52010188011">传化物流</option>
										<option value="52010188013">中润汇德</option>
										<option value="52010102010">乌当农商行</option>
										<option value="52010188006">河南双合</option>
										<option value="52010102017">兴业银行贵州分行</option>
										<option value="52010102018">中国建设银行</option>
										<option value="52010188021">中铁中基裕福</option>
										<option value="52010188019">微行助手</option>
										<option value="52010188020">北京聚利</option>
										<option value="52010188016">北京特微</option>
										<option value="52010188022">智载科技</option>
										<option value="52010188023">小贷公司</option>										
								</select>
								</div>   --%>
								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
										<form:option value="10">10</form:option>
										<form:option value="20" selected="selected" >20</form:option>
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
										<th>状态</th>
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