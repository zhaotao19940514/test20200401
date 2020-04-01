<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>

<style type="text/css">
          .titile{
          	text-align:right;
          	font-size: 13px;
          };
          .details{
          	text-align:left;
          }
          .caption{
          		font-size: 15px;
          		font-weight: bold;
          		margin: 0px;
          }
          .essential {
				color: red;
		  }
</style>
</head>
<body>
	<script type="text/javascript">
		var cardBrand = 0;
		var cardModel = "0";
		$(function() {
			var vehicleId = '${vehicleInfo.vehicleId }';
			var userId = '${vehicleInfo.customerId }';
			var vehicleType = '${vehicleInfo.type }';
			
			$.ajaxSetup ({ cache: false});
			$("#myManage").taiji({
				enableAclCheck : true,
				search:{
					 autoSearch:false
				}
			});
			//读卡
			$("#readCardBtn").click(function() {
// 			 	debugger; 
				var cardReader = new CardReader();
				var CardId = cardReader.getCardId();
				var CardType = cardReader.getIssuerTypeIdentifier();
				var netId = cardReader.getCardNetId();
				$("#newCardId").val(CardId);
				$("#newCardType").val(CardType);
				$("#netId").val(netId);
				if(CardType == 22){
					$("#cardTypeText").val("储值卡");
				}else if(CardType == 23){
					$("#cardTypeText").val("记账卡");
				}else{
					$("#cardTypeText").val("未知类型");
				}
			});
			//发卡
            $("#cardIssueBtn").click(function(){
            	var cardReader = new CardReader();
            	cardInventoryCheck(cardReader);//调用库存校验
            });
			//发行套餐校验
// 			function carIssuePackageCheck(vehicleId,cardReader) {
// 				var url = '${rootUrl }';
// 				var data = {};
// 				data.type = 1;
// 				data.vehicleId = vehicleId;
// 				$.ajax({
// 					url:url+"app/apply/emergency/equipmentissue/carIssuePackageCheck",
// 					data:JSON.stringify(data),
// 					type:"POST",
// 					contentType : 'application/json',
// 			        dataType : 'json',
// 					success:function(response){
// 						if(response.success){
// 							cardInventoryCheck(cardReader);//发行套餐校验成功，调用库存校验
// 	                	}else{
// 	                		$.Taiji.showWarn(response.message);
// 	                	}
// 					},
// 					error:function(error){
// 						console.log(error);
// 						$.Taiji.showWarn('发行套餐请求校验失败！');
// 					}
// 				});
// 			};
		
 			//卡库存校验
			function cardInventoryCheck(cardReader) {
				var cardId = $("#newCardId").val();
				var url = '${rootUrl }';
				if(cardId == null || cardId == "" || isNaN(cardId)){
					$.Taiji.showWarn("请先读卡！");
				}else{
					var data = {};
					data.type = 1;
					data.cardId = cardId;
					$.ajax({
						url:url+"app/apply/emergency/equipmentissue/inventoryVerify",
						data:JSON.stringify(data),
						type:"POST",
						contentType : 'application/json',
				        dataType : 'json',
						success:function(response){
							if(response.success){
								cardBrand = Number(response.brand);
								cardModel = response.model;
				            	applyCard(userId,vehicleId,cardReader);//库存校验成功，调用发卡方法
			            	}else{
				            	$.Taiji.showWarn(response.message+"校验失败！");
			            	}
						},
						error:function(error){
							console.log(error);
							$.Taiji.showWarn('请求校验失败！');
						}
					});
		        }
			};
			//发卡方法
			function applyCard(userId,vehicleId,cardReader){
	// 			debugger;
				var newCardId = $("#newCardId").val();
				var newCardType = $("#newCardType").val();
				var netId = $("#netId").val(); 
				var brand = cardBrand;
				var model = cardModel;
				var bindingType = $("#bindingType").val();
				var cardCategory = $("#cardCategory").val();
				var packageNo = $("#packageNo").val();
				var pkgId = $("#pkgId").val();
				var url = '${rootUrl}'+"app/apply/emergency/equipmentissue/cardApplyAndConfirm";
				if(!validate(newCardId,bindingType,cardCategory,newCardType,pkgId)){//发卡信息校验
					return;	
				} 
				var data = {};
				data.whetherToOpenCard = true;
				data.newCardId = newCardId;
				data.newCardType = newCardType;
				data.userId = userId;
				data.vehicleId = vehicleId;
				data.netId = netId;
				data.packageNo = packageNo;
				data.pkgId = pkgId;
				data.brand = brand;
				data.model = model;
				data.bindingType = bindingType;
				data.cardCategory = cardCategory;
				var method = 'POST';
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				var terminateConditions = "data.cardId != '' && data.cardId != null	&& data.cardId != undefined";
				cardReader.openCard();
				cardReader.autoExecuteApdu(url,data,method,terminateConditions,function(){
					cardReader.closeCard();
					$.Taiji.hideLoading();
					$("#closeBtn").click();
					$.Taiji.showNote("卡发行成功！");
					if(vehicleType == 21 || vehicleType == 22 || vehicleType == 23 || vehicleType == 24){
						var prompt = "卡发行成功！应急车请点击确定进行卡签绑定！若绑定出错，请去卡签绑定页面绑定！";
// 						$.Taiji.defConfirm(prompt).done(function(){
// 							cardObuBindingMethod();
// 						});
						alert(prompt);
						cardObuBindingMethod();
					}
				});
			}
			
			//发卡信息校验
			function validate(newCardId,bindingType,cardCategory,newCardType,pkgId) {
				var cardType = $('input:radio:checked').val();
				if(newCardId == null || newCardId == "" || isNaN(newCardId)){
					$.Taiji.showWarn("请先读卡！");
					return false;
				}else if(cardType == null || cardType =="" ){
					$.Taiji.showWarn("请选择发卡类型！");
					return false;
				}else if(cardType != newCardType){
					$.Taiji.showWarn("选择的发卡类型与卡类型不符合！");
					return false;
				}else if(bindingType == null || bindingType == ""){
					$.Taiji.showWarn("请选择绑定类型！");
					return false;
				}else if(cardCategory == null || cardCategory == ""){
					$.Taiji.showWarn("请选择卡片类别！");
					return false;
				}else if(cardType == 23 && (pkgId == null || pkgId == "")){
					$.Taiji.showWarn("请选择服务费套餐！");
					return false;
				}else{
					return true;
				}
			}
			//卡签绑定方法
			function cardObuBindingMethod() {
	//			debugger; 
				var cardReader = new CardReader();//由页面填入端口号
				var cardId = cardReader.getCardId();
				if(cardId == undefined || cardId == null || cardId == "" || isNaN(cardId)){
					cardReader.closeCard();
					$.Taiji.showWarn("未读到卡信息！");
				}else {
					cardObuBinding(cardId, cardReader);
				}
			};
			//卡签绑定实现方法
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
			//根据选择的卡类型，切换服务费套餐
			$('input[type=radio][name=cardTypeChoose]').change(function() {
		        if (this.value == 22) {
		        	$("#pkgId option:first").prop("selected", 'selected'); 
// 		            $(".pkgOption").hide();
		            $("#pkgId").attr("disabled", true);
		            $("#pkgMark").hide();
		        }else if (this.value == 23) {
// 		        	$(".pkgOption").show();
		        	$("#pkgId").removeAttr("disabled");
		        	$("#pkgMark").show();
		        }
		    });
			
		});
	</script>
<div class="modal-header">
<!-- 	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
	<h4 class="modal-title">发卡</h4>
</div>
<div class="modal-body">
	<div class="form-group">
		<form:form modelAttribute="customerInfo" cssClass="form-horizontal" action="" method="post">
			<p class="caption">客户信息</p>
			<table border="1">
				<tr style="display: none;">
					<td colspan="6"><form:input cssStyle="width:180px" path="customerId" id="customerId" class="form-control" disabled="true" /></td>
				</tr>
				<tr>
					<td class="titile">客户名称：</td><td class="details"><form:input cssStyle="width:180px" path="customerName" class="form-control" disabled="true" /></td>
					<td class="titile" width="91px">证件类型：</td><td class="details">
						<form:select path="customerIdType" disabled="true" cssClass="form-control m-r-5"
												 style="width:175px" >
												<form:options items="${customerIDTypes}" itemLabel="value"
													itemValue="typeCode"/>
											</form:select></td>
					<td class="titile" width="130px">证件号码：</td><td class="details"><form:input cssStyle="width:180px" path="customerIdNum" class="form-control" disabled="true" /></td>
				</tr>
			</table>
   		</form:form>
	</div>
	<div class="form-group">
		<form:form modelAttribute="vehicleInfo" cssClass="form-horizontal" action="" method="post">
			<p class="caption">车辆信息</p>
			<table border="1">
				<tr>
					<td class="titile">车牌号码：</td><td class="details"><form:input cssStyle="width:180px" disabled="true" path="vehiclePlate" class="form-control"  placeholder="必填"/></td>
					<td class="titile">车牌颜色：</td><td class="details">
										<form:select path="vehiclePlateColor" disabled="true"  cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">全部</form:option>
											<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile">使用性质：</td><td class="details">
										<form:select path="useCharacter" disabled="true" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:option value="">全部</form:option>
											<form:options items="${vehicleUseCharacters}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
				</tr>
				<tr>
					<td class="titile">客货类别：</td><td class="details">
										<form:select path="vehicleType" disabled="true" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:options items="${vehicleTypeSimples}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
					<td class="titile" ><c:choose>
											<c:when test="${vehicleInfo.vehicleType eq 1 }"><span>载重（kg）：</span></c:when>
											<c:otherwise><span>座位数：</span></c:otherwise>
										</c:choose></td>
					<td class="details" ><c:choose>
											<c:when test="${vehicleInfo.vehicleType eq 1 }"><span><form:input path="permittedWeight" disabled="true" cssStyle="width:180px" class="form-control" /></span></c:when>
											<c:otherwise><span><form:input path="approvedCount" disabled="true" cssStyle="width:180px" class="form-control" /></span></c:otherwise>
										</c:choose></td>
					<td class="titile">车型：</td><td class="details">
										<form:select path="type" disabled="true" cssClass="form-control m-r-5" data-style="btn-white" data-width="120px">
											<form:options items="${vehicleTypes}" itemLabel="value" itemValue="typeCode"/>
										</form:select></td>
				</tr>
				<tr>
					<td class="titile">品牌型号：</td><td class="details"><form:input path="vehicleModel" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">车辆识别代码：</td><td class="details"><form:input path="VIN" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">发动机号：</td><td class="details"><form:input path="engineNum" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
				</tr>
				<tr>
					<td class="titile">车轮数：</td><td class="details"><form:input path="wheelCount" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">车轴数：</td><td class="details"><form:input path="axleCount" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
					<td class="titile">外廓尺寸（长*宽*高）</td><td class="details"><form:input path="outsideDimensions" disabled="true" cssStyle="width:180px" class="form-control"  placeholder="必填"/></td>
				</tr>
			</table>
   		</form:form>
	</div>
	<div class="form-group">
		<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="" method="post">
			<p class="caption">卡片信息</p>
				<table border="1">
					<tr>
						<td class="titile"><span class="essential">*</span>发行类型：</td><td class="details" >
<!-- 														<span><input type="radio" name="cardTypeChoose" value="22">储值卡</span> -->
														<span><input type="radio" name="cardTypeChoose" value="23" checked="checked">记账卡</span>
														</td>
						<td class="titile"><span style="display: none;" id="pkgMark" class="essential">*</span>服务费套餐：</td><td class="details">
										<select id="pkgId" name="pkgId"  class="form-control"
											data-style="btn-white" data-width="180px">
											<option value="">--请选择--</option>
											<option value="5">0元手续费套餐</option>
											<%-- <c:forEach items="${accountPackages}" var='actp'>
												<option class="pkgOption" value="${actp.packageNum}">${actp.packageName}</option>
											</c:forEach> --%>
										</select></td>
						<td class="titile"><span class="essential">*</span>卡表面号：</td><td class="details" width="235px"><span style="float: left;"><form:input path="newCardId" id="newCardId" readonly="true" cssStyle="width:180px" cssClass="form-control" /></span><button class="btn btn-default  btn-success" type="button" id="readCardBtn">读卡</button></td>
					</tr>
					<tr>
						<td class="titile"><span class="essential">*</span>生效日期：</td><td class="details"><form:input path="" readonly="true" cssClass="form-control" value="${startT }"/></td>
						<td class="titile"><span class="essential">*</span>失效日期：</td><td class="details"><form:input path="" readonly="true" cssClass="form-control" value="${endT }"/></td>
						<td class="titile"><span class="essential">*</span>卡片类型：</td><td class="details"><span style="float: left;"><form:input path="" id="cardTypeText" readonly="true" cssStyle="width:235px" cssClass="form-control" /></span>
						<span style="display: none;">
						<form:input path="newCardType" id="newCardType" readonly="true" cssClass="form-control" />
						<form:input path="netId" id="netId" readonly="true" cssClass="form-control" />
						<form:input path="packageNo" id="packageNo" readonly="true" cssClass="form-control" />
						</span></td>
					</tr>
					<tr>
						<td class="titile"><span class="essential">*</span>绑定类型：</td><td class="details">
										<form:select path="bindingType" id="bindingType" cssClass="form-control" data-style="btn-white" data-width="120px">
											<form:option value="1">独立账户</form:option>
											<form:option value="2">绑定贷记卡账户</form:option>
											<form:option value="3">绑定借记卡账户</form:option>
											<form:option value="4">绑定其他支付账户</form:option>
											<form:option value="5">绑定银行对公账户</form:option>
										</form:select></td>
						<td class="titile"><span class="essential">*</span>卡片类别：</td><td class="details" colspan="1">
										<form:select path="cardCategory" id="cardCategory" cssClass="form-control" data-style="btn-white" data-width="120px">
											<form:option value="1">单卡</form:option>
											<form:option value="2">二卡分离</form:option>
											<form:option value="3">二卡合一</form:option>
										</form:select></td>
							<td class="titile" colspan="2"></td>
					</tr>
				</table>
	   </form:form> 
	</div>
</div>
<div class="modal-footer">
	<a href="#" id="closeBtn" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" id="cardIssueBtn" class="btn btn-sm btn-success" >发卡</a>
</div>

</body>
</html>