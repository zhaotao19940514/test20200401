<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/posDeviceHeader.jsp"%>
</head> 


	<script type="text/javascript">
	
	$(function() {
			$.ajaxSetup({cache : false});
			$("#myManage").taiji({
				enableAclCheck : true,
				search:{
					 autoSearch:false
					}
			});
			$("#my-table").on('click','#cSupplyBtnOff',function(){
				$.Taiji.showWarn("卡状态为挂失并且卡类型为储值卡才能补卡");
			});
	});
    function oldCardCancel(oldCardId,cardType,cardReader){
    	var newCardId = $("#newCardId").val();
    	if(cardType=='22'){
    		cardType=2;
    	}else if(cardType=='23'){
    		cardType=1;
    	}
        var data = {
        			cardId:oldCardId,
        			provider:0,
        			cardBalance:-1,
        			newCardId:newCardId,
        			cardType:cardType,
        			supplyOrReplace:false,
        			supReason:1,
        			applyStep:1
       			 };
         $.ajax({
            url : rootUrl+"app/customerservice/card/supply/oldCardCancel",
            type : "POST",
            dataType : "json",
            data:JSON.stringify(data),
            contentType: "application/json",
            async:true,
            success : function(responseText) {
            		if(responseText.status==1){
		              applyCard(oldCardId,cardReader);
            		}else{
            			$.Taiji.hideLoading();
            			$.Taiji.showWarn(responseText.message);
            		}
            },
	         error:function(responseText){
	           	$.Taiji.showWarn(responseText.message);
	           	$.Taiji.hideLoading();
	           }
        });
    }

    function applyCard(oldCardId,cardReader){
         var newCardId = cardReader.getCardId();
       	 var newCardType = cardReader.getIssuerTypeIdentifier();
       	 var netId = cardReader.getCardNetId();
       	 var url = rootUrl+"app/customerservice/card/supply/cardApplyAndConfirm";
       	 var data = {};
       	 data.newCardId = newCardId;
       	 data.oldCardId = oldCardId;
       	 data.newCardType = newCardType;
       	 data.supReason = 1;
       	 data.provider = 1;
       	 data.cardId = oldCardId
       	 data.netId = netId;
       	 var method = 'POST';
       	 var terminateConditions = "data.cardId != '' && data.cardId != null && data.cardId != undefined";
       	 cardReader.openCard();
      	 cardReader.autoExecuteApdu(url,data,method,terminateConditions,function(){
      		cardReader.closeCard();
      		$("#listForm").submit();
      		$.Taiji.showNote("补卡成功");
      		$("#closeBtn").click();
      	 });
        
    }
    
	</script>
<body>
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

		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>

			<!-- begin row -->
			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12" >
					<!-- begin panel -->
					<div id="myManage" class="panel panel-inverse">
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
							<h4 class="panel-title">补卡</h4>
						</div>
						<div class="panel-body">
							<form:form modelAttribute="queryModel"
								cssClass="taiji_search_form form-inline m-t-5 " id="listForm"
								name="listForm"
								action="${rootUrl }app/customerservice/card/supply/manage"
								method="post">
								<div class="form-group">
									<form:input path="cardId" maxlength="100" cssClass="form-control" placeholder="卡号" />
									<form:input path="vehicleId" maxlength="100" cssClass="form-control" placeholder="车牌号码" />
									<form:select path="vehicleColor"  cssClass="form-control m-r-5"  data-style="btn-white" data-width="160px">
										<form:option value="">--请选择车牌颜色--</form:option>
										<form:options items="${vehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
									</form:select>
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5"
									type="button">
									<i class="fa fa-search  m-r-10 "></i>查询
								</button>
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
							</form:form>
						</div>
						<div class="taiji_search_result table-responsive" id="loading">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>客户名称</th>
										<th>证件类型</th>
										<th>证件号码</th>
										<th>车牌号码</th>
										<th>车牌颜色</th>
										<th>卡号</th>
										<th>卡片状态</th>
										<th>卡片类型</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager"></div>

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
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->

</body>
</html>