<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/jsp/assets.jsp" %>

<script type="text/javascript" src='${rootUrl }myjs/ocx/misposClient.js'></script>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script type="text/javascript" src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" ></script>
<script type="text/javascript">
		$(function(){
			$.ajaxSetup({cache : false});
			$("#myManage").taiji({
				enableAclCheck:true,
				search:{
					autoSearch:false
				}
			});
			
			$("#exportBtn").click(function(){
				var beforeDate = $("#beforeDate").val();
				var afterDate = $("#afterDate").val();
				var cardId =$("#cardId").val(); 
				var status =$("#status").val();
				debugger;
				var url = rootUrl+"app/customerservice/finance/expenserefundaudit/exportExcel?beforeDate="+beforeDate+"&afterDate="+afterDate+"&cardId="+cardId+"&status="+status;
				/* if(null!=cardId&&cardId!=''&&cardId!='undefined'){
					url+="&cardId="+cardId;
				} 
				if(null!=beforeDate&&beforeDate!=''&&beforeDate!='undefined' && null!=afterDate&&afterDate!=''&&afterDate!='undefined'){
					url+="&beforeDate="+beforeDate;
				}  */
				window.location.href = url;
			});
	 
			
			$("#readCard").click(function(){
				var cardReader = new CardReader();
				var cardId =cardReader.getCardId();
				$("#cardId").val(cardId);
			});
			
			$("#select").click(function(){
					var card  = $("#cardId").val(); 
					var data={};
					data.cardId=card;
					$.ajax({
						url : "findAccountBalanceByCardId",
						type : "POST",
						data:JSON.stringify(data),
						dataType : "json",
						contentType: "application/json",
						async:true,
						success : function(responseText) {
							$("#accountBalance").html(responseText.accountBalance/100);
						}
					});
			});
			
			$("#my-table").on('click','#deleteById',function(){
				debugger;
				var id=$(this).attr("param");
				var data={};
				data.id=id;
				$.ajax({
					url : "deleteById",
					type : "POST",
					data:JSON.stringify(data),
					dataType : "json",
					contentType: "application/json",
					async:true,
					success : function(responseText) {
						$.Taiji.showNote(responseText.message);
						$(".taiji_search_submit").click();
					}
				});
			});
			
			
			 
			$("#docDownloadValue").click(function(){
		        $("#docDownloadSubmitValue").click();
		    });
			
			$("#docDownloadAccount").click(function(){
		        $("#docDownloadSubmitAccount").click();
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
                            <h4 class="panel-title">消费退费审核</h4>
                        </div>
                        <div class="panel-toolbar ">
                     		<button id="docDownloadValue" type="button" class="btn btn-primary btn-sm"><i class="fa fa-download"> </i>下载导入省中心退费模板（储值）</button>
			                       <form action="${rootUrl}app/expense/dlocx/valueExcel" method="post" class="hide">
			   							 <button id="docDownloadSubmitValue" type="submit" class="btn btn-primary btn-sm" style="display: none" ></button>
								   </form>
							<button id="docDownloadAccount" type="button" class="btn btn-primary btn-sm"><i class="fa fa-download"> </i>下载导入省中心退费模板（记账）</button>
			                       <form action="${rootUrl}app/expense/dlocx/accountExcel" method="post" class="hide">
			   							 <button id="docDownloadSubmitAccount" type="submit" class="btn btn-primary btn-sm" style="display: none" ></button>
								   </form>
						</div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/customerservice/finance/expenserefundaudit/manage" method="post">
								<div class="form-group">
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
									<button id="readCard" class="btn btn-md btn-default" type="button"><i class="fa  fa-credit-card   m-r-10  "></i>读卡</button>
									<form:label path="status">审批状态：</form:label>
									<form:select path="status"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
										<form:options items="${auditStatusType}" itemLabel="value"
											itemValue="code" />
									</form:select>
									 <div class="form-group m-t-5">
										<label class="control-label">查询起始日期</label>
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
									
								</div>
									
									<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
										<form:option value="10">10</form:option>
										<form:option value="30" selected="selected">30</form:option>
										<form:option value="50">50</form:option>
									</form:select>
								</div>	
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
								<a href="${rootUrl }app/customerservice/finance/expenserefundaudit/file"  class="taiji_modal	 taiji_acl btn  btn-success m-5"><i class="fa fa-upload" > </i>导入Excel</a>
									<a href="#" id = "exportBtn" class="btn btn-md btn-default btn btn-success btn-small">导出</a>
									
                        	</form:form>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<!-- <th>流水ID</th> -->
										<th>ETC卡号</th>
										<th>申请录入时间</th>
										<th>申请交易金额</th>
										<th>同意退费金额</th>
										<th>申请人联系方式</th>
										<th>申请退费银行卡号</th>
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