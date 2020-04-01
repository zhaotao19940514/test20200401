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
		
		$("#cardObuBindingBtn").click(function() {
//			 	debugger; 
			var cardReader = new CardReader();//由页面填入端口号
			var cardId = cardReader.getCardId();
			if(cardId == undefined || cardId == null || cardId == "" || isNaN(cardId)){
				cardReader.closeCard();
				$.Taiji.showWarn("未读到卡信息！");
			}else {
				cardObuBinding(cardId, cardReader);
			}
		});
		
		function cardObuBinding(cardId, cardReader) {
			//debugger;
			var url = '${rootUrl}'
					+ "app/apply/quickapply/obuBinding/cardObuBinding";
			var data = {};
			data.cardId = cardId;
			var method = 'POST';
			var terminateConditions = "data.orderStatus == 2";
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			cardReader.openCard();
			cardReader.autoExecuteApdu(url, data, method, terminateConditions,
					function() {
						cardReader.closeCard();
						$.Taiji.hideLoading();
						$.Taiji.showNote("卡签绑定成功！");
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
                            <h4 class="panel-title">卡签绑定</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form cssClass="form-inline m-t-5 " id="listForm" name="listForm" action="" method="post">
                      			<p style="font-size: 16px">请将用户卡放在读卡器上，点击"卡签绑定"按钮</p>
							 	<button id="cardObuBindingBtn" class="btn btn-md btn-success" type="button"><i class="fa  fa  m-r-10"></i>卡签绑定</button> 
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
