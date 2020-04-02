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
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" type="text/javascript"></script>
 <script type="text/javascript">
		$(function(){
			$.ajaxSetup({cache : false});
			$("#myManage").taiji({
				enableAclCheck:true,
				search:{
					autoSearch:false
				}
			}).on("taijiOperateSuccess",function(){
				$(".taiji_search_submit").click();
			}) ;
			
			$("#readCard").click(function(){
				var cardReader = new CardReader();
				var cardId =cardReader.getCardId();
				if(isNaN(cardId)){
					$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
					return;
				}
				var preBalance =cardReader.getBalance();
				$("#cardId").val(cardId);
				$("#preBalance").html(preBalance/100);
				$("#select").click();
			});
			
			$("#select").click(function(){
				var card  = $("#cardId").val();
				if(card==""){
					return;
				}
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
						if(responseText.status==-1){
							$.Taiji.showWarn(responseText.message);
						}
						$("#accountBalance").html(responseText.accountBalance/100);
					}
				});
			});
			
			 $("#Btn").click(function(){
				 var cardReader = new CardReader();
					 if($("#cardId").val()==""){
						$.Taiji.showWarn("请确认是否输入了充值卡号!");
						return;
					}
					if($("#cardId").val().length!=20){
						$.Taiji.showWarn("卡号位数错误 !");
						return;
					}
					if($("#paidAmount").val()<=0){
						$.Taiji.showWarn("请确认是否输入了充值金额!");
						return;
					}
					if($("#chargeType").val()==""){
						$.Taiji.showWarn("请确认是否选择了收费类型!");
						return;
					}
					if($("#chargeType").val()==105 && $("#passWord").val()==""){
						$.Taiji.showWarn("请确认是否输入了用户账密码!如果以前未设置过，请联系网点负责人进行设置！");
						return;
					}
					var val =$('#paidAmount').val();
					/* var val1 =$('#giftAmount').val(); */              //   赠送金额   将来开放   此时默认为0
					var val1 = 0;
					var patrn = /^[0-9]*$/;
					  if (!patrn.test(val)||!patrn.test(val1)) {
						  $.Taiji.showWarn("充值金额不能包含符号 !");
							return;
					  }
					var paidAmount =$("#paidAmount").val()*100;
					/* var giftAmount =$("#giftAmount").val()*100; */
					var giftAmount =0;
			 		var cardId = $("#cardId").val();
			 		var chargeType=$("#chargeType").val();
					var preBalance= cardReader.getBalance();
					var card  = cardReader.getCardId();
					if(isNaN(card)){
						$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
						return;
					}
					if(card!=$("#cardId").val()){
			   	 		$.Taiji.showWarn("当前读卡器的卡与要操作的卡不一致,请检查!");
			   	 		return;
					}
					if(val % 100 != 0){
						  $.Taiji.showWarn('充值金额必须为100元的整数倍!');
						    return;
					  }
					var data = {};														 //   赠送金额   将来开放   此时默认为0
																						/* +Number($("#giftAmount").val()))*100 */
					  $.Taiji.defConfirm("确定要给"+$('#cardId').val()+"这张卡片充值"+ (Number($("#paidAmount").val())+"元？\n\n请确认！")).done(function(){
						data.cardId = cardId;
						data.preBalance=preBalance;
						data.paidAmount=paidAmount;
						data.giftAmount=giftAmount;
						data.chargeType=chargeType;
						if($("#chargeType").val()==105){
							var passWord  =	$("#passWord").val();
							if(passWord=='123'){
								 $.Taiji.showWarn('不推荐使用默认账户密码[123]，请先进行用户账密码修改!');
								    return;
							}
							data.passWord=passWord;
						}
						if(card!=data.cardId){
				   	 		$.Taiji.showWarn("当前读卡器的卡与要操作的卡不一致,请检查!");
				   	 		return;
						}
						cardChargeCheck(data,cardReader);
						$("#Btn").attr("disabled",true);
						$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				  });
				 
			}); 
		
			 $("#chargeType").change(function() {
				 //如果选择账户消费，则需输入用户账户密码，否则无权操作
					var type = $(this).val();
					if(type==105){
						$("#accountPassWord").show();
					}else{
						$("#accountPassWord").hide();
					}
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
						debugger;
						if(responseText.status==1){
							if(responseText.chargeStatus==1){
							    cardReader.openCard();
								var command = responseText.command;
								var preBalance= data.preBalance;
								var cosResponse = cardReader.executeMultiApdus(command);
								var rechargeId = responseText.rechargeId;
								var paidAmount =data.paidAmount;
								var giftAmount =data.giftAmount;
								var chargeType=$("#chargeType").val();
								var tradeType=responseText.tradeType;
								var postBalance=responseText.postBalance;
								var dataObj={};
								dataObj.paidAmount = paidAmount;
								dataObj.giftAmount = giftAmount;
								dataObj.preBalance = preBalance;
								dataObj.chargeType=chargeType;
								dataObj.tradeType=tradeType;
								dataObj.command=command;
								dataObj.commandType = -1;   //标识第几次进入圈存申请
								dataObj.cosResponse=cosResponse;
								dataObj.rechargeId=rechargeId;
								dataObj.cardId = data.cardId;
								dataObj.postBalance=postBalance;
								dataObj.passWord=responseText.passWord;
							    cardChargeByCOS(dataObj,cardReader);
							}else{
								$.Taiji.hideLoading();
								$.Taiji.showWarn("该卡存在半条流水,请进行圈存修复!");
								$("#Btn").attr("disabled",false); 
								cardReader.closeCard();
							}// =2 去修复
						}else{
							$.Taiji.hideLoading();
							$.Taiji.showWarn(responseText.message);
							$("#Btn").attr("disabled",false); 
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
							if(response.commandType==2){
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
								confirmData.rechargeId = response.rechargeId;
								confirmData.tradeType=data.tradeType;
								confirmData.commandType = response.commandType;
								confirmData.command = commands;
								confirmData.postBalance=data.postBalance;
								cardChargeConfirmByCOS(confirmData,cardReader);
							}else if(response.commandType==1){
								var command = response.command;
								var preBalance= data.preBalance;
								var cosResponse = cardReader.executeMultiApdus(command);
								var rechargeId = data.rechargeId;
								var paidAmount =data.paidAmount;
								var giftAmount =data.giftAmount;
								var chargeType=data.chargeType;
								var tradeType=data.tradeType;
								var postBalance=data.postBalance;
								var dataObj={};
								dataObj.paidAmount = paidAmount;
								dataObj.giftAmount = giftAmount;
								dataObj.preBalance = preBalance;
								dataObj.commandType = response.commandType;
								dataObj.chargeType=chargeType;
								dataObj.tradeType=tradeType;
								dataObj.command=command;
								dataObj.cosResponse=cosResponse;
								dataObj.rechargeId=rechargeId;
								dataObj.cardId = data.cardId;
								dataObj.postBalance=postBalance;
							    cardChargeByCOS(dataObj,cardReader);
							}
					}else{
						$.Taiji.hideLoading();
						$.Taiji.showWarn(response.message);
						$("#Btn").attr("disabled",false); 
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
					success : function(response) {
						if(response.postBalance!=null && response.postBalance!="" && response.postBalance != undefined){
							$.Taiji.showNote(response.message);
						}else{
							$.Taiji.showWarn("卡片圈存失败!");
						}
						cardReader.closeCard();
						$.Taiji.hideLoading();
						$("#Btn").attr("disabled",false); 
						$(".taiji_search_submit").click();
						$("#readCard").click();
					}
				});
			}
			
		});
		function doPrint() {

		    bdhtml = window.document.body.innerHTML;
		    sprnstr = "<!--startprint-->";
		    eprnstr = "<!--endprint-->";
		    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
		    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));

		    //window.document.body.innerHTML =document.getElementById("mainbody").innerHTML; 
		    //window.print();

		    window.document.body.innerHTML = prnhtml;
		    window.print();
		    window.document.body.innerHTML = bdhtml;
		}

		var idTmr;
		function  getExplorer() {
		    var explorer = window.navigator.userAgent ;
		    //ie 
		    if (explorer.indexOf("MSIE") >= 0) {
		        return 'ie';
		    }
		    //firefox 
		    else if (explorer.indexOf("Firefox") >= 0) {
		        return 'Firefox';
		    }
		    //Chrome
		    else if(explorer.indexOf("Chrome") >= 0){
		        return 'Chrome';
		    }
		    //Opera
		    else if(explorer.indexOf("Opera") >= 0){
		        return 'Opera';
		    }
		    //Safari
		    else if(explorer.indexOf("Safari") >= 0){
		        return 'Safari';
		    }
		}
		function method1(tableid) {//整个表格拷贝到EXCEL中
		    if(getExplorer()=='ie')
		    {
		        var curTbl = document.getElementById(tableid);
		        var oXL = new ActiveXObject("Excel.Application");

		        //创建AX对象excel 
		        var oWB = oXL.Workbooks.Add();
		        //获取workbook对象 
		        var xlsheet = oWB.Worksheets(1);
		        //激活当前sheet 
		        var sel = document.body.createTextRange();
		        sel.moveToElementText(curTbl);
		        //把表格中的内容移到TextRange中 
		        sel.select;
		        //全选TextRange中内容 
		        sel.execCommand("Copy");
		        //复制TextRange中内容  
		        xlsheet.Paste();
		        //粘贴到活动的EXCEL中       
		        oXL.Visible = true;
		        //设置excel可见属性

		        try {
		            var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
		        } catch (e) {
		            print("Nested catch caught " + e);
		        } finally {
		            oWB.SaveAs(fname);

		            oWB.Close(savechanges = false);
		            //xls.visible = false;
		            oXL.Quit();
		            oXL = null;
		            //结束excel进程，退出完成
		            //window.setInterval("Cleanup();",1);
		            idTmr = window.setInterval("Cleanup();", 1);

		        }

		    }
		    else
		    {
		        tableToExcel('tableExcel')
		    }
		}
		function Cleanup() {
		    window.clearInterval(idTmr);
		    CollectGarbage();
		}
		var tableToExcel = (function() {
		      var uri = 'data:application/vnd.ms-excel;base64,',
		      template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
		        base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
		        format = function(s, c) {
		            return s.replace(/{(\w+)}/g,
		            function(m, p) { return c[p]; }) }
		        return function(table, name) {
		        if (!table.nodeType) table = document.getElementById(table)
		        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
		        window.location.href = uri + base64(format(template, ctx))
		      }
		    })()
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
                            <h4 class="panel-title">圈存</h4>
                        </div>
                       
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/customerservice/finance/cardrecharge/manage" method="post">
								<div class="form-group">
									<form:label  path="cardId">黔通卡号</form:label>
									<form:input id="cardId" path="cardId"  maxlength="100"  cssClass="form-control"   />
									<button id="readCard" class="btn btn-md btn-success" type="button"><i class="fa  fa-credit-card   m-r-10  "></i>读卡</button>
									<form:label path="paidAmount">充值金额</form:label>
									<form:input  path="paidAmount"    cssClass="form-control"  placeholder="充值金额(元)"/><!-- <span style="color:#F00; font-weight:bold;">百元</span> -->
									<form:label path="chargeType">收费类型：</form:label>
									<form:select path="chargeType"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
										<form:options items="${chargeType}" itemLabel="value" itemValue="code"/>
									</form:select>
									<span id="accountPassWord" style="display: none">
										<form:label path="passWord">用户账密码</form:label>
										<form:input  path="passWord"    cssClass="form-control"  placeholder="资金安全性校验!"/>
									</span>
								</div>
								<%-- <form:label path="giftAmount">赠送金额</form:label>
								<form:input path="giftAmount"     cssClass="form-control"   /> <span style="color:#F00; font-weight:bold">百元</span> --%>
								<button id="select" class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button id="Btn" class="taiji_search btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-circle-thin  m-r-5 " ></i>圈存</button>
								<input id="Button1" type="button" class="btn  btn-success m-r-5" value="导出EXCEL" onclick="javascript:method1('tableExcel')" />
								<input type="button" onclick="doPrint()" class="btn  btn-success m-r-5" value="打印"/>
                        	</form:form>
                        	<div>
									<span>卡片余额</span>
									<span id="preBalance"></span>
									<span style="color:#F00; font-weight:bold; " >元</span>
							</div>
							<div>
									<span>账户金额</span>
									<span id="accountBalance"></span>
									<span style="color:#F00; font-weight:bold; " >元</span>
							</div>
                        </div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<!--startprint-->
							<table class="table table-striped table-bordered  table-hover" id="tableExcel">
								<thead>
									<tr>
										<th>黔通卡号</th>
										<th>圈存网点编号</th>
										<th>充值前余额(元)</th>
										<th>充值金额(元)</th>
										<th>充值后余额(元)</th>
										<th>充值时间</th>
										<th>流水号</th>
										<th>交易类型</th>
										<th>收费类型</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<!--endprint-->
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
<!-- 		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a> -->
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->

</body>
</html>