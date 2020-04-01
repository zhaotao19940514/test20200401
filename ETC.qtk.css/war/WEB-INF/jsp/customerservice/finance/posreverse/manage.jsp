<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/posDeviceHeader.jsp"%>
<link href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" />
<script src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">

	
	
	$(function() {
		$("#myManage").taiji({
			enableAclCheck:true,
			search:{
				 autoSearch:false
			}
		});
		
		$("#my-table").on('click','#unLossBtn',function(){
			var amount = $(this).attr("param");
			$.Taiji.defConfirm("你确定要撤销该笔交易？").done(function(){
				var data = {};
				var posTradeType = 'CONSUMEUNDO';
				data.amount = amount;
				data.posTradeType = posTradeType;
				doUnLoss(data);	
			});
		});


		
		function doUnLoss(data) {
			$.ajax({
				url : "posCommonCos",
				data : JSON.stringify(data),
				type : 'POST',
				contentType : 'application/json',
				dataType : 'json',
				success : function(response) {
					var command = response.command;
					console.log(command);
					var misposClient = new MisPosClient();
					var posResponse = misposClient.trade(command);
					console.log(posResponse);
					var split = posResponse.split(',');
					var sp = split+$("#referNo").val();
					ajaxResult(sp);
					if(misposClient.validPosResponse(posResponse)){
						params.vali = false;
		            	$.Taiji.showWarn("验证成功");
					}else{
		            	$.Taiji.showWarn(split[12]);
					}
				},
				error : function(response) {
					console.log(response);
				}
			});
		}
		
		
		
		function ajaxResult(split) {
			$.ajax({				
				url: "result?split=" + encodeURIComponent(split),
				type : 'GET',
				contentType : 'application/json',
				dataType : 'json',
				success : function(response) {
					
				}
	        })
		};
		
	});
</script>
</head>
<body >
	<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceBody.jsp"%>
	<%@include file="/WEB-INF/jsp/deviceInclude/posDeviceBody.jsp"%>
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
                            <h4 class="panel-title">pos撤销冲正</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/customerservice/finance/posreverse/manage" method="post">
                      			<div class="form-group">
	                      			<form:input path="referNo"  maxlength="100"  cssClass="form-control"  placeholder="检索参考号" />
								</div>	

								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>

                        	</form:form>
						</div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr> 										
										<th>操作时间</th>
										<th>金额</th>
										<th>银行卡号</th>
										<th>交易结果</th>
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
