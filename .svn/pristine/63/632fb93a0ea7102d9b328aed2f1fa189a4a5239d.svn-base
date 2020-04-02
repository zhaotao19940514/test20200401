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
// 	var applyStep = 1;//请求到第几次
	var params = {
			vali : true,
			cardid : "",
			bankid : "",
			username : "",
			userid : "",
			plate : "",
			color : "",
			userIdType : "",
			userPhoneNo : ""
	};
	
	
	$(function() {
		$("#myManage").taiji({
			enableAclCheck:true,
			search:{
				 autoSearch:false
			}
		});
		$("#readCardBtn").click(function(){
            try{
            	var cardReader = new CardReader();
				var cardId = cardReader.getCardId();
				if(!isNaN(cardId)){
					$("#etcCardId").val(cardId);					
					params.cardid = cardId;
				}
            }catch(err){
            	$.Taiji.showWarn("读卡失败");
            }finally{
            	var data = {};
				var posTradeType = 'READCARD';
				data.posTradeType = posTradeType;
				ajaxPosCommand(data);
            }
		});

		$("#validate").click(function(){
			var data = {};
			var posTradeType = 'CARDVALID';
			var userName = $("#userName").val();
			var userID = $("#userId").val();
			var plate = $("#plate").val();
			var color = $("#color").val();
			var userIdType = $("#userIdType").val();
			var userPhoneNo = $("#userPhoneNo").val();
			data.posTradeType = posTradeType;
			data.userName = userName;
			data.userID = userID;
			data.vehiclePlate = plate;
			data.vehicleColor = color.toString();
			data.userIdType = userIdType.toString();
			data.userPhoneNo = userPhoneNo;
			params.username = userName;
			params.userid = userID;
			params.plate = plate;
			params.color = color.toString();
			params.userIdType = userIdType.toString();
			params.userPhoneNo = userPhoneNo;
			if(userName == '' || userID == '' || plate == '' || color == '' || userIdType == '' || userPhoneNo == ''){
				alert("验证需要填写姓名，证件号，用户证件类型，用户手机号，车牌号，车牌颜色");
				return
			}
			ajaxPosValidate(data);
		}) ;
		
		$("#bindBtn").click(function(){
			var data = {};
			var posTradeType = 'CARDBINDING';
			var cardNo = $("#bankCardId").val();
			var etcCardNo = $("#etcCardId").val();
			var userName = $("#userName").val();
			var userID = $("#userId").val();
			var vehiclePlate = $("#plate").val();
			var vehicleColor = $("#color").val();
			var userIdType = $("#userIdType").val();
			var userPhoneNo = $("#userPhoneNo").val();
			if(cardNo=='' || etcCardNo == '' || userName == '' || userID =='' || userIdType == '' || userPhoneNo == '' || vehiclePlate == '' || vehicleColor == ''){
				alert("请填写客户名，证件号，用户证件类型，用户手机号，车牌号，车牌颜色，卡号及银行卡号");
				return
			}
			if(params.vali){
				alert("请先验证姓名，证件号，用户证件类型，用户手机号，车牌号，车牌颜色");
				return
			}
			if(params.cardid != etcCardNo || params.bankid != cardNo || params.userid != userID || params.username != userName 
					|| params.plate != vehiclePlate || params.color != vehicleColor.toString() || params.userIdType != userIdType.toString() || params.userPhoneNo != userPhoneNo ){
				alert("签约所用信息与读取信息及验证信息不一致，请检查姓名，证件号，车牌号，车牌颜色，证件类型，用户手机号，卡号或银行卡号是否发生更改")
				return
			}
			data.posTradeType = posTradeType;
			data.userName = userName;
			data.userID = userID;
			data.cardNo = cardNo;
			data.etcCardNo = etcCardNo;
			data.vehiclePlate = vehiclePlate;
			data.vehicleColor = vehicleColor.toString();
			data.userIdType = userIdType.toString();
			data.userPhoneNo = userPhoneNo;
			ajaxPosBind(data);
            
		}) ;
		
		$("#unBindBtn").click(function(){
			var data = {};
			var posTradeType = 'CARDUNBINDING';
			var cardNo = $("#bankCardId").val();
			var etcCardNo = $("#etcCardId").val();
			var userName = $("#userName").val();
			var userID = $("#userId").val();
			var vehiclePlate = $("#plate").val();
			var vehicleColor = $("#color").val();
			var userIdType = $("#userIdType").val();
			var userPhoneNo = $("#userPhoneNo").val();
			if(cardNo=='' || etcCardNo == '' || userName == '' || userID =='' || userIdType == '' || userPhoneNo == '' || vehiclePlate == '' || vehicleColor == ''){
				alert("请填写客户名，证件号，用户证件类型，用户手机号，车牌号，车牌颜色，卡号及银行卡号");
				return
			}
			if(params.vali){
				alert("请先验证姓名，证件号，用户证件类型，用户手机号，车牌号，车牌颜色");
				return
			}
			if(params.cardid != etcCardNo || params.bankid != cardNo || params.userid != userID || params.username != userName 
					|| params.plate != vehiclePlate || params.color != vehicleColor.toString() || params.userIdType != userIdType.toString() || params.userPhoneNo != userPhoneNo ){
				alert("解约所用信息与读取信息及验证信息不一致，请检查姓名，证件号，车牌号，车牌颜色，证件类型，用户手机号，卡号或银行卡号是否发生更改")
				return
			}
			data.posTradeType = posTradeType;
			data.userName = userName;
			data.userID = userID;
			data.cardNo = cardNo;
			data.etcCardNo = etcCardNo;
			data.vehiclePlate = vehiclePlate;
			data.vehicleColor = vehicleColor.toString();
			data.userIdType = userIdType.toString();
			data.userPhoneNo = userPhoneNo;
			ajaxPosUnBind(data);
            
		}) ;
		
		function ajaxPosUnBind(data) {
			$.ajax({
				url : "posCommonCos",
				data : JSON.stringify(data),
				type : 'POST',
				contentType : 'application/json',
				dataType : 'json',
				success : function(response) {
					var command = response.command;
					console.log(command);
					console.log(MisPosClient);
					var misposClient = new MisPosClient();
					var posResponse = misposClient.trade(command);
					console.log(posResponse);
					var split = posResponse.split(',');
					ajaxResult(split);
					if(misposClient.validPosResponse(posResponse)){
		            	$.Taiji.showWarn("解绑成功");
		            	params.vali = true;
					}else{
		            	$.Taiji.showWarn($.Taiji.showWarn(split[12]));
					}
				},
				error : function(response) {
					console.log(response);
				}
			});
		};
		
		
		
		
		function ajaxPosBind(data) {
			$.ajax({
				url : "posCommonCos",
				data : JSON.stringify(data),
				type : 'POST',
				contentType : 'application/json',
				dataType : 'json',
				success : function(response) {
					var command = response.command;
					console.log(command);
					console.log(MisPosClient);
					var misposClient = new MisPosClient();
					var posResponse = misposClient.trade(command);
					console.log(posResponse);
					var split = posResponse.split(',');
					ajaxResult(split);
					if(misposClient.validPosResponse(posResponse)){
		            	$.Taiji.showWarn("绑定成功");
		            	params.vali = true;
					}else{
		            	$.Taiji.showWarn($.Taiji.showWarn(split[12]));
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
		
		function ajaxPosValidate(data) {
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
		
		
		
		function ajaxPosCommand(data) {
			$.ajax({
				url : "posCommonCos",
				data : JSON.stringify(data),
				type : 'POST',
				contentType : 'application/json',
				dataType : 'json',
				success : function(response) {
					var command = response.command;
					console.log(command);
					console.log(MisPosClient);
					var misposClient = new MisPosClient();
					var posResponse = misposClient.trade(command);
					console.log(posResponse);
					var bankId = misposClient.getResponseBankCardNo(posResponse);
					$("#bankCardId").val(bankId);
					params.bankid = bankId;
				},
				error : function(response) {
					console.log(response);
				}
			});
		}
		
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
                            <h4 class="panel-title">工行pos签约</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/contract/manage" method="post">
                      			<div class="form-group">
	                      			<form:input path="userName"  maxlength="100"  cssClass="form-control"  placeholder="客户姓名" />
									<form:input path="plate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号" />								
									<form:select path="color"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">选择车牌颜色</form:option>
										<form:options items="${CssVehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
									</form:select>		
									<form:select path="userIdType"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">选择证件类型</form:option>
										<form:options items="${CustomerIDType}" itemLabel="value"/>
									</form:select>		
									<form:input path="userId"  maxlength="100"  cssClass="form-control"  placeholder="客户证件号" />
									<form:input path="userPhoneNo"  maxlength="100"  cssClass="form-control"  placeholder="用户手机号码" />																
								</div>
								<button class="btn btn-md btn-default btn btn-success btn-small"  id="validate"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>验证</button>
                      			<br/>
                      			<div class="form-group">
	                      			<form:input path="etcCardId"  maxlength="100"  cssClass="form-control"  placeholder="ETC卡号" />
									<form:input path="bankCardId"  maxlength="100"  cssClass="form-control"  placeholder="银行卡号" />								
								</div>	
						        <button class="btn btn-md btn-default btn btn-success btn-small"  id="readCardBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>读卡</button>
						        <button class="btn btn-md btn-default btn btn-success btn-small"  id="bindBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>签约</button>
						        <button class="btn btn-md btn-default btn btn-success btn-small"  id="unBindBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>解约</button>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>

								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
										<form:option value="10">10</form:option>
										<form:option value="30" selected="selected">30</form:option>
										<form:option value="50">50</form:option>
									</form:select>
								</div>
                        	</form:form>
						</div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr> 										
										<th>操作时间</th>
										<th>ETC卡号</th>
										<th>银行卡号</th>
										<th>操作类型</th>
										<th>操作工号</th>
										<th>操作指令</th>
										<th>操作结果</th>
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
