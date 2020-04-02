<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#logManage").taiji({
		enableAclCheck:true,
		search:{
            seachResultType:'tbody',
            autoSearch:false
        }
	});
});
</script>

</head>
<body>
	<div id="form-target"></div>
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>

	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
		<!-- end #sidebar -->
		<!-- 内容页 -->
		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>

			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">

					<div id="logManage" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">客服系统操作日志</h4>
						</div>

						<div class="panel-body">
							<form:form cssClass="taiji_search_form form-inline m-t-5"
								modelAttribute="queryModel" id="listForm" name="listForm"
								action="${rootUrl}app/system/operateLog/manage" method="post">
								<!-- 查询条件 -->
								<div class="taiji_search_condition  m-t-5"></div>
								<div class="form-group m-t-5">
									<label class="control-label">日志日期</label>
									<div class="input-group">
										<form:input cssStyle="width:150px" path="startTime"
											readonly="true" cssClass="form-control" />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default"
												onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'endTime\')}'});">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
								</div>
								<div class="form-group m-t-5">
									<label class="control-label">至</label>
									<div class="input-group">
										<form:input cssStyle="width:150px" path="endTime"
											readonly="true" cssClass="form-control" />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default"
												onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startTime\')}'});">
												<i class="fa fa-calendar"></i>
											</button>
										</span>
									</div>
								</div>

								<button class="taiji_search_submit btn btn-md btn-success m-l-5"
									type="button">
									<i class="fa fa-search  m-r-10 "></i>查询
								</button>
								<button class="taiji_search_reset btn btn-md btn-default"
									type="button">
									<i class="fa  fa-refresh  m-r-10  "></i>重置
								</button>
								<br/>
								<div class="form-group m-t-5">
                                    <label class="control-label">业务类型</label>
                                    <form:select path="serviceType" cssClass="form-control">
                                        <form:options itemLabel="serviceName"/>
                                    </form:select>
                                </div>
                                <div class="form-group m-t-5">
                                    <label class="control-label">操作类型</label>
                                    <form:select path="operateType" cssClass="form-control">
                                        <form:options itemLabel="value"/>
                                    </form:select>
                                </div>
                                <div class="form-group m-t-5">
                                    <%@ include file="/WEB-INF/jsp/pagesize.jsp"%>
                                </div>
								<button
									class="taiji_search_collapse btn btn-md btn-success m-l-5"
									data-toggle="collapse" data-target="#collapse-content"
									type="button">
									<i class="fa fa-angle-double-down  m-r-10 "></i>更多条件
								</button>
								<div class="collapse" id="collapse-content">
								    <div class="form-group m-t-5">
                                        <label class="control-label">操作用户名</label>
                                        <form:input path="username" cssClass="form-control"
                                            maxlength="200" size="20" />
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">etc卡号</label>
                                        <form:input path="relatedCardId" cssClass="form-control"
                                            maxlength="20" size="25" />
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">obu编号</label>
                                        <form:input path="relatedObuId" cssClass="form-control"
                                            maxlength="16" size="20" />
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">操作用户IP</label>
                                        <form:input path="remoteIp" cssClass="form-control"
                                        maxlength="15" size="15" />
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">车牌号</label>
                                        <form:input path="plateNum" cssClass="form-control"
                                            maxlength="10" size="12" />
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">车牌颜色</label>
<%--                                         <form:input path="plateColor" cssClass="form-control" --%>
<%--                                             maxlength="200" size="50" /> --%>
                                        <form:select path="plateColor" itemLabel="value" cssClass="form-control">
                                            <form:option value="">请选择</form:option>
                                            <form:options itemLabel="value"/>
                                        </form:select>
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">etc用户编号</label>
                                        <form:input path="relatedCustomerId" cssClass="form-control"
                                            maxlength="20" size="20" />
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">充值流水号</label>
                                        <form:input path="relatedRechargeId" cssClass="form-control"
                                            maxlength="40" size="40" />
                                    </div>
                                    <div class="form-group m-t-5">
                                        <label class="control-label">日志内容</label>
                                        <form:input path="data" cssClass="form-control"
                                            maxlength="200" size="50" />
                                    </div>
								</div>
							</form:form>
							<div class="taiji_search_result">
	                            <table class="table table-striped table-bordered  table-hover">
	                                <thead>
	                                    <tr>
	                                        <th>用户</th>
	                                        <th>时间</th>
	                                        <th>业务类型</th>
	                                        <th>操作类型</th>
	                                        <th>ip</th>
	                                        <th>日志</th>
	                                        <th>操作</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                </tbody>
	                            </table>
	                        </div>
						</div>
	                        <div class="panel-footer text-right ">
	                            <div class="pageturn taiji_pager"></div>
	                        </div>
						
					</div>
				</div>
			</div>
		</div>
		<!-- 内容页 -->
		<!-- 版权页 -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- 版权页 -->
	</div>
</body>
</html>