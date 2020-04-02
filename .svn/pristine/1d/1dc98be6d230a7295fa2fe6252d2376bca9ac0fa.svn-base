<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<link href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" />
<script src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>

</head>
<body >
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
		$("#cardPinUnlockingBtn").click(function() {
//			 	debugger; 
			var cardReader = new CardReader();//由页面填入端口号
			var oldCardId = $("#cardId").val();
			if(oldCardId == undefined || oldCardId == null || oldCardId == "" || isNaN(oldCardId)){
				cardReader.closeCard();
				$.Taiji.showWarn("请先读卡！");
			}
			var cardId = cardReader.getCardId();
			if(cardId == undefined || cardId == null || cardId == "" || isNaN(cardId)){
				cardReader.closeCard();
				$.Taiji.showWarn("未读到卡信息！");
			}else if (cardId == oldCardId) {
				cardPinUnlocking(cardReader,cardId);
			}else{
				cardReader.closeCard();
				$.Taiji.showWarn("解锁卡与读得的卡号不一致！");
			}
		});
		
		function cardPinUnlocking(cardReader,cardId) {
			//debugger;
			var url = '${rootUrl}'
					+ "app/customerservice/card/cardPinUnlocking/unlocking";
			var data = {};
			data.command = null;
			data.cosResponse = null;
			data.pinType = null;
			data.cardId = cardId;
			var method = 'POST';
			var terminateConditions = "data.cardUnblockStatus == 1";
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			cardReader.openCard();
			cardReader.autoExecuteApdu(url, data, method, terminateConditions,
					function() {
						cardReader.closeCard();
						$.Taiji.hideLoading();
						$.Taiji.showNote("PIN码解锁成功！");
					});
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
                            <h4 class="panel-title">卡PIN码锁解锁</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="reqModel" cssClass="form-inline m-t-5 " id="listForm" name="listForm" action="" method="post">
                      			<p style="font-size: 16px">请将用户卡放在读卡器上，先点击"读卡"按钮，再点击"PIN码解锁"按钮</p>
                      			<div class="form-group">
									<div class="form-group m-5">
										<form:label path="">卡表面号</form:label>
										<form:input path="" id="cardId"  maxlength="100" readonly="true" cssClass="form-control"  placeholder="卡表面号" />
										<button class="btn btn-default btn-success" type="button" id="readCardBtn"><i class="fa fa-credit-card m-r-10 "></i>读卡</button>
							 			<button id="cardPinUnlockingBtn" class="btn btn-md btn-success" type="button"><i class="fa  fa  m-r-10"></i>PIN码解锁</button> 
							 		</div>
							 	</div>
                        	</form:form>
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager">
	                        </div>
	                       
             		  </div>
					</div>

			    </div>

			</div>

		</div>
		
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
	</div>

</body>
</html>
