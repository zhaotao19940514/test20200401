<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<link href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" />
<script src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#exprot").click(function(){
		var status = $("#status").val();
		var statustext = $("#status").find("option:selected").text();
		var batchId = $("#batchId").val();
		if(status==null || batchId==""){
			$.Taiji.showWarn("请完整填写后导出");
			return;
		}
		var xhr = new XMLHttpRequest();
        xhr.open('post', '/css/app/apply/baseinfo/inport/exportExcel', true);
        xhr.responseType = 'blob';
        xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
        xhr.onload = function () {
            if (this.status == 200) {
                var blob = this.response;
                var a = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                a.href = url;
                //设置文件名称
           		a.download =batchId+'-'+statustext+'.xls';
                a.click();
            }
        }
        xhr.send(JSON.stringify({
           "status" : status,
           "batchId" : batchId
        }));
	})
		
	$("#myManage").taiji({
		enableAclCheck:true,
		search : {
			autoSearch : false
		}
	})
	//下载模板
	$("#docDownload").click(function(){
		   $("download").click();
		});
});
</script>
</head>
<body >
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
                            <h4 class="panel-title">批量开卡导入用户车辆数据</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/apply/baseinfo/inport/manage" method="post">
                        	<div class="form-group">
									<div class="form-group m-5">
									<%-- <form:label path="batchId">批次号</form:label>
										<form:input path="batchId" cssClass="form-control" placeholder="批次号必填"/> --%>
										<div class="form-group m-t-5">
										<label class="control-label">查询注销起始日期</label>
										<div  class="input-group">
					 						<form:input cssStyle="width:150px"   path="beforeDate" readonly="true"  cssClass="form-control" />
										    <span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('beforeDate'),dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'afterDate\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
									<div class="form-group m-t-5">	
										<label class="control-label">至</label>
										<div  class="input-group">
						  					<form:input cssStyle="width:150px" path="afterDate"  readonly="true" cssClass="form-control"   />
											<span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('afterDate'),dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beforeDate\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
			 						</div> 
										<form:label path="status">订单状态</form:label>
										<form:select path="status"  cssClass="form-control m-r-5" data-style="btn-white" data-width="100px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${types}" itemLabel="value" itemValue="code"/>
										</form:select>
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
										<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
						<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
						<a href="${rootUrl }app/apply/baseinfo/inport/file"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-upload" > </i>导入Excel</a>
       					<a  href="/css/app/user/dlocx/infomationExcel" class="btn btn-primary"><i class="fa fa-download"> </i>下载网点导入模板</a>
       					<button id="exprot" type="button" class="btn btn-primary"><i class="fa fa-download"> </i>导出数据</button>
										</div>
												</div>
                        	
                        	
                        	</form:form>
						</div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
									<th>序号</th>
										<th>用户类型</th>
										<th>证件类型</th>
										<th>证件号</th>
										<th>车牌</th>
										<th>车牌颜色</th>
										<th>手机号</th>
										<th>订单号</th>
										<th>响应信息</th>
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
