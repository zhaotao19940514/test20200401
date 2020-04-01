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
	 
			$("#readCard").click(function(){
				var cardReader = new CardReader();
				var card  = cardReader.getCardId();
				 if(isNaN(card)){
					$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
					return;
				} 
				$("#cardId").val(card); 
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
						$("#account").show();
						$("#accountBalance").html(responseText.accountBalance/100);
					}
				});
			});
			 
			 $("#reverse").click(function(){
				 var val =$('#tradeFee').val();
					var patrn = /^[0-9]*$/;
					  if ($("#cardId").val()=="") {
					    $.Taiji.showWarn('卡号不能为空!');
					    return;
					  }
					  if (val<=0) {
						    $.Taiji.showWarn('请输入充值金额!');
						    return;
					  }
					  if (!patrn.test(val)) {
						    $.Taiji.showWarn('冲正金额不可以使用符号!');
						    return;
					  }
					  if(val % 100 != 0){
						  $.Taiji.showWarn('冲正金额必须为100元的整数倍!');
						    return;
					  }
					  if($("#passWord").val()==""){
						  $.Taiji.showWarn('请确认是否输入了用户账密码!如果以前未设置过，请联系网点负责人进行设置！');
						    return;
					  }
					  	var cardId=$("#cardId").val(); 
					  	var vehiclePlate=$("#vehiclePlate").val();
					  	var vehiclePlateColor=$("#vehiclePlateColor").val();
						var tradeFee =$("#tradeFee").val()*100;
					 $.Taiji.defConfirm("确定对该账户冲正"+ ($('#tradeFee').val()+"元？\n\n请确认！")).done(function(){
						 	var data = {};
							data.tradeFee = tradeFee;
							data.vehiclePlate=vehiclePlate;
							data.vehiclePlateColor=vehiclePlateColor;
							data.cardId=cardId;
							data.passWord=$("#passWord").val();
							if(data.passWord==123){
								$.Taiji.showWarn('交易密码不能为初始值[123]，不利于账户安全，请在用户账户密码管理处进行修改!');
							    return;
							}
							$("#reverse").attr("disabled",true); 
							accountReverse(data);
							$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				    }); 
				}); 
			 
		
			  function accountReverse(data){
				  console.log(data);
					$.ajax({
						url : "accountReverse",
						type : "POST",
						data:JSON.stringify(data),
						dataType : "json",
						contentType: "application/json",
						async:true,
						success : function(responseText) {
							if(responseText.status==1){
								$.Taiji.hideLoading();
								$.Taiji.showNote("冲正后账户金额为:"+responseText.postBalance/100+"元");
								$("#reverse").attr("disabled",false);
								 $(".taiji_search_submit").click();
								 /*$("#readCard").click(); */
							}else{
								$.Taiji.hideLoading();
								$("#reverse").attr("disabled",false);
								$.Taiji.showWarn(responseText.message);
								
							}
						}
					});
				}
			  
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
                            <h4 class="panel-title">账户冲正</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/customerservice/finance/accountreverse/manage" method="post">
								<div class="form-group">
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
									<button id="readCard" class="btn btn-md btn-success" type="button"><i class="fa  fa-credit-card   m-r-10  "></i>读卡</button>
									
									<div class="form-group m-t-5">
										<label class="control-label">查询日期</label>
										<div  class="input-group">
					 						<form:input cssStyle="width:150px"   path="startTime" readonly="true"  cssClass="form-control" />
										    <span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
									<div class="form-group m-t-5">	
										<label class="control-label">至</label>
										<div  class="input-group">
						  					<form:input cssStyle="width:150px" path="endTime"  readonly="true" cssClass="form-control"   />
											<span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
			 						</div>	
								</div>
									<form:input path="vehiclePlate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:label path="vehiclePlateColor">车牌颜色：</form:label>
									<form:select path="vehiclePlateColor"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
										<form:option value="">--请选择--</form:option>
										<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
									</form:select>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
								<br>
								<form:input path="tradeFee"  maxlength="100"  cssClass="form-control"  placeholder="冲正金额(元)" />
								<form:label path="passWord">用户账密码</form:label>
									<form:input  path="passWord"    cssClass="form-control"  placeholder="资金安全性校验!"/>
									<button id="reverse" class="taiji_search btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-usd   m-r-10 "></i>冲正</button>
                        	</form:form>
                        	<div id="account" style="display: none;">
								<span>账户余额</span>
								<span id="accountBalance"></span>
								<span style="color:#F00; font-weight:bold; " >元</span>
							</div>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>交易类型</th>
										<th>充值网点</th>
										<th>交易时间</th>
										<th>交易前金额(元)</th>
										<th>交易金额(元)</th>
										<th>预期交易后金额(元)</th>
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