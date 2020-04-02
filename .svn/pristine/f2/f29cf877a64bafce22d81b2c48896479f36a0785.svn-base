<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false,
			 search:{
				 autoSearch:false
				}
		}
	});
	
	$("#readCard").click(function(){
		var cardReader = new CardReader();
		var cardId =cardReader.getCardId();
		if(isNaN(cardId)){
			$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
			return;
		}
		
		$("#cardId").val(cardId);
	});
	$("#my-table").on('click','#supply',function(){
		var cardReader = new CardReader();
		var cardId = $(this).attr("param");
		var paidAmount = $(this).attr("param2");
		var enableTime= $(this).attr("param3");
		var giftAmount =0;
 		var chargeType=$("#chargeType").val();
		var preBalance= cardReader.getBalance();
		var card  = cardReader.getCardId();
		var data ={};
		if(isNaN(card)){
			$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
			return;
		}
		$.Taiji.defConfirm("确定要给"+cardId+"这张卡片补卡额"+ (Number(paidAmount/100)+"元？\n\n请确认！")).done(function(){
			data.cardId = cardId;
			data.preBalance=preBalance;
			data.paidAmount=paidAmount;
			data.giftAmount=giftAmount;
			data.enableTime=enableTime;
			if(card!=cardId){
	   	 		$.Taiji.showWarn("当前读卡器的卡与要操作的卡不一致,请检查!");
	   	 		return;
			}
			cardChargeCheck(data,cardReader);
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
		});
		
		 function cardChargeCheck(data,cardReader){
				$.ajax({
					url : "cardChargeCheck",
					type : "POST",
					data:JSON.stringify(data),
					dataType : "json",
					contentType: "application/json",
					async:true,
					success : function(responseText) {
						console.log("responseText"+responseText);
						cardReader.openCard();
						if(responseText.status==1){
							if(responseText.chargeStatus==1){
								var command = responseText.command;
								var preBalance= data.preBalance;
								var cosResponse = cardReader.executeMultiApdus(command);
								var rechargeId = responseText.rechargeId;
								var paidAmount =data.paidAmount;
								var giftAmount =data.giftAmount;
								var postBalance=responseText.postBalance;
								var dataObj={};
								dataObj.paidAmount = paidAmount;
								dataObj.giftAmount = giftAmount;
								dataObj.preBalance = preBalance;
								dataObj.command=command;
								dataObj.enableTime=data.enableTime;
								dataObj.cosResponse=cosResponse;
								dataObj.rechargeId=rechargeId;
								dataObj.cardId = data.cardId;
								dataObj.postBalance=postBalance;
								cardChargeByCOS(dataObj,cardReader);
							}else{
								$.Taiji.hideLoading();
								$.Taiji.showWarn("该卡存在半条流水,请先进行圈存修复!");
								cardReader.closeCard();
							}// =2 去修复
						}else{
							$.Taiji.hideLoading();
							$.Taiji.showWarn(responseText.message);
							cardReader.closeCard();
						}
					}
				});
			}
			
			function cardChargeByCOS(data,cardReader){
				$.ajax({
					url:"cardChargeByCOS",
					data:JSON.stringify(data),
					type:"POST",
					dataType : "json",
					contentType: "application/json",
					success:function(response){
						if(response.status==1){
							var cardId= $("#cardId").val();
							var commands = response.command;
							//在卡上执行command 并获取返回值
							var cosResponse = cardReader.executeMultiApdus(commands);
							var paidAmount =data.paidAmount;
							var giftAmount =data.giftAmount;
							var confirmData = {};
							confirmData.paidAmount = paidAmount;
							confirmData.giftAmount = giftAmount;
							confirmData.cosResponse = cosResponse;
							confirmData.cardId=cardId;
							confirmData.enableTime=data.enableTime;
							confirmData.rechargeId = response.rechargeId;
							confirmData.command = commands;
							confirmData.postBalance=data.postBalance;
							cardChargeConfirmByCOS(confirmData,cardReader);
						}else{
							$.Taiji.hideLoading();
							$.Taiji.showWarn(response.message);
							cardReader.closeCard();
						}
					}
				});
				
			}

			function cardChargeConfirmByCOS(data,cardReader){
				$.ajax({
					url :"cardChargeConfirmByCOS",
					type : "POST",
					dataType : "json",
					data : JSON.stringify(data),
					contentType: "application/json",
					async:true,
					error : function(response){
						$.Taiji.showWarn(response.message);
						cardReader.closeCard();
						$.Taiji.hideLoading();
						$(".taiji_search_submit").click();
					},
					success : function(response) {
						if(response.postBalance!=null && response.postBalance!="" && response.postBalance != undefined){
							$.Taiji.showNote(response.message);
						}else{
							$.Taiji.showWarn(response.message);
						}
						cardReader.closeCard();
						$.Taiji.hideLoading();
						$(".taiji_search_submit").click();
						$("#readCard").click();
					}
				});
			}
		
		
	})
	
	$("#wirteCard").click(function(){
		$("#href").click();
	});
	
});
</script>

</head>
<body>
<input type="hidden" id="cardBalance" />
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
                            <h4 class="panel-title">补卡额</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/finance/supplyCardBalance/manage" method="post">
								<div class="form-group">
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
								</div>
								<button id="readCard" class="btn btn-md btn-default btn btn-success btn-small"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>读卡</button>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
							 <button id="wirteCard"  class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-circle-thin  m-r-10 ">人工录入</i></button>
                        </div>
                        <div style="display: none;">
                        	<a id="href" href="${rootUrl }app/customerservice/finance/supplyCardBalance/login" class="taiji_modal {width:550,height:600} taiji_acl m-r-10">人工录入</a>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>卡号</th>
										<th>客户名称</th>
										<th>车牌号码</th>
										<th>车牌颜色</th>
										<th>补卡金额</th>
										<th>录入时间</th>
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