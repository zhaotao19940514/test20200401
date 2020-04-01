<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/WEB-INF/jsp/deviceInclude/posDeviceHeader.jsp"%>
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
           .floatCss{
          		float: left;
          }
          .displayCss{
          		display: none;
          }
          .moneyCss{
          		color: red;
          		width: 110px;
          		color: red;
          		font-weight: bolder;
          		font-size: 16px;
          }
          .essential {
				color: red;
		  }
</style>
</head>
<body>
	<script type="text/javascript">
		$(function() {
			var rechargeType = null;
			var cardCostType = null;
			var obuCostType = null;
			var issueType = null;
			var totalCash = null;
			var totalPos = null;
			var packageNum = null;
			var vehicleId = '${vehicleInfo.vehicleId }';
			var vehicleType = '${vehicleInfo.type }';
			var serviceHallId = '${serviceHallId }';
			
			$.ajaxSetup ({ cache: false});
			$("#myManage").taiji({
				enableAclCheck : true,
				search:{
					 autoSearch:false
				}
			});

			//套餐改变动态查询计算套餐金额
			$("#packageNum").change(function(){
				rechargeType = null;//发行套餐改变重置参数
        		cardCostType = null;
        		obuCostType = null;
        		issueType = null;
        		totalCash = null;
        		totalPos = null;
        		packageNum = null;
	        	$("#rechargeMoney").val(0.0);//重置页面显示
	        	$("#playRechargePos").hide();
                $("#playRechargeCash").hide();
                $("#cardCost").val(0.0);
                $("#playCardPos").hide();
                $("#playCardCash").hide();
                $("#obuCost").val(0.0);
                $("#playObuPos").hide();
                $("#playObuCash").hide();
	        	$("#totalMoney").val(0.0);
	        	$("#totalCash").val(0.0);
	        	$("#totalPos").val(0.0);
	        	$("#issuantDevice").val('');
// 				debugger;
				packageNum = $("#packageNum").val();
				var url = '${rootUrl }';
		        if(packageNum !=null && packageNum != ""){
		        	$.post(url+"app/apply/quickapply/equipmentissue/moneyPlayType/"+packageNum,function(data,status){
			            if(status=="success"){
			            	if(data.success){
				                $("#totalMoney").val(data.totalMoney);
				                $("#totalCash").val(data.totalCash);
					        	$("#totalPos").val(data.totalPos);
				                $("#rechargeMoney").val(data.rechargeMoney);
				                $("#cardCost").val(data.cardCost);
				                $("#obuCost").val(data.obuCost);
				                $("#issuantDevice").val(data.issuantDevice);
				                rechargeType = data.rechargeType;
				        		cardCostType = data.cardCostType;
				        		obuCostType = data.obuCostType;
				        		issueType = data.issueType;
				        		totalCash = data.totalCash;
				        		totalPos = data.totalPos;
				        		if(obuCostType == 0){
				        			$("#playObuPos").show();
				        			$("#playObuCash").hide();
				        		}else{
				        			$("#playObuPos").hide();
				        			$("#playObuCash").show();
				        		}
				        		if(rechargeType == 0){
				        			$("#playRechargePos").show();
				        			$("#playRechargeCash").hide();
				        		}else{
				        			$("#playRechargePos").hide();
				        			$("#playRechargeCash").show();
				        		}
				        		if(cardCostType == 0){
				        			$("#playCardPos").show();
				        			$("#playCardCash").hide();
				        		}else{
				        			$("#playCardPos").hide();
				        			$("#playCardCash").show();
				        		}
// 				        		debugger;
			            	}else{
			            		packageNum = null;//失败，把全局变量packageNum置为null,让支付保存发放不能运行
			            		$.Taiji.showWarn(data.message);
			            	}
			            }else {
			            	packageNum = null;//失败，把全局变量packageNum置为null,让支付保存发放不能运行
			            	$.Taiji.showWarn("套餐信息获取失败，请联系管理员！");
						}

			        });
		        }
			});
			//支付保存发行套餐
			$("#payBtn").click(function(){
// 				debugger;
				$(".addDisabled").attr("disabled","disabled");
				if(packageNum == null || packageNum == "" || issueType == null){
					$.Taiji.showWarn("请先选择发行套餐！");
					$(".addDisabled").removeAttr("disabled");
					return;
				}else{
					/* if(!(vehicleType == 1 || vehicleType == 2 || vehicleType == 3 || vehicleType == 4)){
						if(issueType != 1){
							$.Taiji.showWarn('货车不能发行OBU！  请选择发卡套餐！');
							$(".addDisabled").removeAttr("disabled");
							return;
						}
					} */
					var type = 0;
					if(issueType == 1) type = 1;
					if(issueType == 2) type = 2;
// 					if(issueType == 3) type = 0;
					$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
					vehiclePlateOnlyCheck(type,vehicleId);//车牌唯一性校验
				}
			});
			
			//车牌唯一性校验
			function vehiclePlateOnlyCheck(type,vehicleId) {
				var url = '${rootUrl }';
				var data = {};
				data.type = type;
				data.vehicleId = vehicleId;
				$.ajax({
					url:url+"app/apply/quickapply/equipmentissue/vehiclePlateOnlyCheck",
					data:JSON.stringify(data),
					type:"POST",
					contentType : 'application/json',
			        dataType : 'json',
					success:function(response){
						if(response.success){
							saveCarIssuePackage(packageNum,vehicleId);//保存发行套餐
		            	}else{
		            		$.Taiji.hideLoading();
			            	$.Taiji.showWarn(response.message);
			            	$(".addDisabled").removeAttr("disabled");
		            	}
					},
					error:function(error){
						console.log(error);
						$.Taiji.hideLoading();
						$.Taiji.showWarn('车牌唯一性校验请求失败');
						$(".addDisabled").removeAttr("disabled");
					}
				});
			};
			
			//保存发行套餐方法
			function saveCarIssuePackage(packageNum,vehicleId) {
				var url = '${rootUrl }';
				var data = {};
				data.packageNum = packageNum;
				data.vehicleId = vehicleId;
				data.remarks = $("#remarks").val();
				$.ajax({
					url:url+"app/apply/quickapply/equipmentissue/saveCarIssuePackage",
					data:JSON.stringify(data),
					type:"POST",
					contentType : 'application/json',
			        dataType : 'json',
					success:function(response){
						if(response.success){
							$.Taiji.showNote(response.message);
							payPackage(response);//保存发行套餐成功，调用支付方法
// 							$("#closeBtn").click();
		            	}else{
		            		$.Taiji.hideLoading();
			            	$.Taiji.showWarn(response.message);
		            	}
					},
					error:function(error){
						$.Taiji.hideLoading();
						console.log(error);
						$.Taiji.showWarn('发行套餐保存请求失败');
					}
				});
			};
			
			//支付发行套餐费用
			function payPackage(responsePackage) {
// 				debugger;
				if(totalCash == null || totalPos == null){
					$.Taiji.hideLoading();
                	$.Taiji.showWarn("收费金额不能为空，支付失败！");
                	return;
				}
				var money = totalCash;
				var prompt = "是否已收现金:" + money + "元？";
				if(money > 0){
					$.Taiji.defConfirm(prompt).done(function(){
						$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
						var data={};
						//获取需要消费的钱，转换为分
						var posMoney = totalPos*100;
						if(posMoney > 0){
							var misposClient = new MisPosClient();
							data.amount=posMoney;
							ajaxPosCommand(data,misposClient,responsePackage);//调用pos机消费方法
						}else{
							payCarIssuePackage(responsePackage);
						}
					});
					$.Taiji.hideLoading();
				}else{
					$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
					var data={};
					//获取需要消费的钱，转换为分
					var posMoney = totalPos*100;
					if(posMoney > 0){
						var misposClient = new MisPosClient();
						data.amount=posMoney;
						ajaxPosCommand(data,misposClient,responsePackage);//调用pos机消费方法
					}else{
						payCarIssuePackage(responsePackage);
					}
				}
				
			};
			
			//pos机消费方法
			function ajaxPosCommand(data,misposClient,responsePackage){
				var url = '${rootUrl }';
				$.ajax({
					url:url+"app/ocx/mispos/posTradeCos",
					data:JSON.stringify(data),
					type:'POST',
					contentType : 'application/json',
			        dataType : 'json',
			        success:function(response){
			        	var command = response.command;
			            var posResponse = misposClient.trade(command);
			            data.command = command;
			            data.posResponse = posResponse;
			            console.log(posResponse);
			            ajaxPosConsumeConfirm(data,responsePackage);
			        },
			        error:function(response){
			        	$.Taiji.hideLoading();
			        	console.log(response);
			        }
				});
			};
			//pos机消费成功后，调用        pos机消费确认，打消费日志
			function ajaxPosConsumeConfirm(data,responsePackage){
				var url = '${rootUrl }';
				data.vehiclePlate = '${vehicleInfo.vehiclePlate }';
				data.vehicleColor = '${vehicleInfo.vehiclePlateColor }';
	            $.ajax({
	                url:url+"app/ocx/mispos/posTradeConfirm",
	                data:JSON.stringify(data),
	                type:'POST',
	                contentType : 'application/json',
	                dataType : 'json',
	                success:function(response){
	                	if(response.success){
	                		payCarIssuePackage(responsePackage);//调用支付发行套餐方法
	                	}else{
	                		$.Taiji.hideLoading();
	                		$.Taiji.showWarn(response.message);
	                	}
	                },
	                error:function(response){
	                	$.Taiji.hideLoading();
	                	$.Taiji.showWarn("消费日志出错！");
	                }
	            });
	        };
			
			//支付发行套餐费用（保存数据到数据库）
			function payCarIssuePackage(responsePackage) {
				var url = '${rootUrl }';
				var data = {};
				data.carIssuePackageInfo = responsePackage.carIssuePackageInfo;
				$.ajax({
					url:url+"app/apply/quickapply/equipmentissue/payCarIssuePackage",
					data:JSON.stringify(data),
					type:"POST",
					contentType : 'application/json',
			        dataType : 'json',
					success:function(response){
						if(response.success){
							$.Taiji.hideLoading();
							$.Taiji.showNote(response.message);
							$("#closeBtn").click();
		            	}else{
		            		$.Taiji.hideLoading();
			            	$.Taiji.showWarn(response.message);
		            	}
					},
					error:function(error){
						$.Taiji.hideLoading();
						console.log(error);
						$.Taiji.showWarn('支付发行费用请求失败！');
					}
				});
			};
			//加载完成，判断是否显示套餐备注
			if(serviceHallId != null && serviceHallId == '5201010600401140003'){
				$("#remarksTr").show();
			}
		});
	</script>
<div class="modal-header">
<!-- 	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> -->
	<h4 class="modal-title">发套餐选择</h4>
</div>
<div id="myManage" class="modal-body">
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
			<p class="caption">发行套餐信息</p>
			<table border="1">
				<tr style="display: none;" id="remarksTr">
					<td class="titile">备注：</td>
					<td colspan="5"><textarea class="form-control" rows="" cols="" id="remarks" maxlength="120"></textarea></td>
				</tr>
				<tr>
					<td class="titile"><span class="essential">*</span>发行套餐：</td>
					<td class="details">
						<select name="packageNum" id="packageNum" class="addDisabled form-control m-r-5" data-style="btn-white" data-width="165px">
							<option value="">--请选择--</option>
							<c:forEach items="${issueManagers}" var='issm'>
								<option value="${issm.packageNum}">${issm.packageName}</option>
							</c:forEach>
						</select></td>
					<td class="titile">现金共计（元）：</td>
					<td class="details"><input name="" value="0" id="totalCash" style="width: 165px;color: red; font-weight: bolder;font-size: 16px;" readonly="readonly" class="form-control" /></td>
					<td class="titile">POS共计（元）：</td>
					<td class="details"><input name="" value="0" id="totalPos" style="width: 165px;color: red; font-weight: bolder;font-size: 16px;" readonly="readonly" class="form-control" /></td>
				</tr>
				<tr>
					<td class="titile">充值金额（元）：</td><td class="details">
						<span class="floatCss"><input name="" value="0" id="rechargeMoney" readonly="readonly"  class="moneyCss form-control"/></span>
						<span class="displayCss" id="playRechargePos">刷POS</span>
						<span class="displayCss" id="playRechargeCash">收现金</span></td>
					<td class="titile">卡金额（元）：</td><td class="details">
						<span class="floatCss"><input name="" value="0" id="cardCost" readonly="readonly" class="moneyCss form-control" /></span>
						<span class="displayCss" id="playCardPos">刷POS</span>
						<span class="displayCss" id="playCardCash">收现金</span></td>
					<td class="titile">OBU金额（元）：</td><td class="details" >
						<span class="floatCss"><input name="" value="0" id="obuCost" readonly="readonly" class="moneyCss form-control" /></span>
						<span class="displayCss" id="playObuPos">刷POS</span>
						<span class="displayCss" id="playObuCash">收现金</span></td>
				</tr>
				<tr>
					<td class="titile">套餐总金额（元）：</td>
					<td class="details" ><input name="" value="0" id="totalMoney" style="width: 165px;color: red; font-weight: bolder;font-size: 16px;" readonly="readonly" class="form-control" /></td>
					<td class="titile">可发行设备：</td>
					<td class="details" ><input name="" value="" id="issuantDevice" style="width: 165px;font-weight: bolder;font-size: 16px;" readonly="readonly" class="form-control" /></td>
					<td class="titile">操作：</td><td class="details" colspan="1" style="text-align: center;"><button class="addDisabled btn btn-default btn-success" style="width: 120px" type="button" id="payBtn">确认保存并支付</button></td>
				</tr>
			</table>
   		</form:form>
	</div>
</div>
<div class="modal-footer">
	<a href="#" id="closeBtn" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<!-- <a href="#" id="issuePackageBtn" class="btn btn-sm btn-success" >支付并保存</a> -->
</div>

</body>
</html>