<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript" src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" ></script>
</head>
<body>
<script type="text/javascript">
	$(function(){
		$.ajaxSetup({cache : false});
		$("#myManage").taiji({
			enableAclCheck:true,
			search:{
				 autoSearch:false
			}
		});
		var th_h = $(".th-h");
		var th_d = $(".th-d");
		thChange();
		$(".taiji_search_submit").click(function(){
			thChange();
		});
		function thChange() {
			var cardId = $("#cardId").val(); 
			var th = $("#tb_th");
			th.empty();
		    if(cardId==null||cardId==""){
		    	th.append(th_h);
		    } else{
		    	th.append(th_d);
		    }
		}
	});
</script>
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
                            <h4 class="panel-title">退款导入日志</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/finance/refundimplog/manage" method="POST">
								<div class="form-group">
									<div class="form-group m-5">
										<label class="control-label">卡号</label>
					 					<form:input cssStyle="width:150px"   path="cardId" cssClass="form-control" />
									</div>
									<div class="form-group m-5">
										<label class="control-label">导入时间</label>
										<div  class="input-group">
					 						<form:input cssStyle="width:150px"   path="beforeDate" readonly="true"  cssClass="form-control" />
										    <span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('beforeDate'),dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'afterDate\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
										<label class="control-label">至</label>
										<div  class="input-group">
						  					<form:input cssStyle="width:150px" path="afterDate"  readonly="true" cssClass="form-control"   />
											<span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('afterDate'),dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beforeDate\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5 btn btn-primary btn-small" id="searchBtn" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
						<div id="tb_th" class="taiji_search_result table-responsive ">
							<table id="my-table" class="th-h table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>文件名</th>
										<th>操作数量</th>
										<th>成功数</th>
										<th>失败数</th>
										<th>操作时间</th>
										<th>工号</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<table id="my-table" class="th-d table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>卡号</th>
										<th>文件名</th>
										<th>信息</th>
										<th>操作时间</th>
										<th>工号</th>
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