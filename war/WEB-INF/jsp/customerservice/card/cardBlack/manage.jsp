<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>

</head>
<body>
<script type="text/javascript">
	$(function(){ 
		$.ajaxSetup ({ cache: false});
		$("#myManage").taiji({
			enableAclCheck:true,
			search:{
				autoSearch:false
			}
		});
		$("#readCardBtn").click(function() {
			/* debugger; */
			var cardReader = new CardReader();//由页面填入端口号
			var CardId = cardReader.getCardId();
			$("#cardId").val(CardId);
			cardReader.closeCard();
		});
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
                            <h4 class="panel-title">卡黑名单查询</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/customerservice/card/cardBlack/manage" method="post">
								<div class="form-group">
									<div class="form-group m-5">
										<form:label path="cardId">卡号</form:label>
										<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
									</div>
									<div class="form-group m-5">
										<form:label path="vehiclePlate">车牌号码</form:label>
										<form:input path="vehiclePlate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									</div>
									<div class="form-group m-5">
										<form:label path="vehiclePlateColor">车牌颜色</form:label>
										<form:select path="vehiclePlateColor"  cssClass="form-control m-r-5" data-style="btn-white" data-width="100px">
											<form:option value="">--请选择--</form:option>
											<form:options items="${vehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
										</form:select>
									</div>
									<div class="form-group m-5">
										<form:label path="cardBlackQueryType">查询类型</form:label>
										<form:select path="cardBlackQueryType"  cssClass="form-control m-r-5" data-style="btn-white" data-width="100px">
											<form:options items="${cardBlackQueryType}" itemLabel="value" itemValue="typeCode"/>
										</form:select>
									</div>
									<div class="form-group m-5">
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
										<th>卡号</th>
										<th>黑名单类型</th>
										<th>黑名单生效时间</th>
										<th>黑名单下黑/反白时间</th>
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