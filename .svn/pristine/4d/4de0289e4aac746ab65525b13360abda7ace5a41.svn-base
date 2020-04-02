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
			 
			 $("#charge").click(function(){
					var val =$('#tradeFee').val();
					var patrn = /^[0-9]*$/;
					  if ($("#cardId").val()=="" && $("#vehiclePlate").val()=="") {
					    $.Taiji.showWarn('卡号和车牌不能同时为空!');
					    return;
					  }
					  if (val<=0) {
						    $.Taiji.showWarn('请输入充值金额!');
						    return;
					  }
					  if (!patrn.test(val)) {
						    $.Taiji.showWarn('充值金额不可以使用符号!');
						    return;
					  }
					  if(val % 100 != 0){
						  $.Taiji.showWarn('充值金额必须为100元的整数倍!');
						    return;
					  }
					  if($("#chargeType").val() == ""){
						  $.Taiji.showWarn('请选择收费类型!');
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
						var chargeType=$("#chargeType").val();
						$.Taiji.defConfirm("确定要给用户充值"+ ($('#tradeFee').val()+"元？\n\n请确认！")).done(function(){
						 	var data = {};
							data.tradeFee = tradeFee;
							data.vehiclePlate=vehiclePlate;
							data.vehiclePlateColor=vehiclePlateColor;
							data.chargeType=chargeType;
							data.cardId=cardId;
							data.tradeType=101;
							data.passWord=$("#passWord").val();
							$("#charge").attr("disabled",true); 
							accountCharge(data);
							$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				}); 
			 });
			 
			  function accountCharge(data){
				  console.log(data);
					$.ajax({
						url : "accountCharge",
						type : "POST",
						data:JSON.stringify(data),
						dataType : "json",
						contentType: "application/json",
						async:true,
						success : function(responseText) {
							if(responseText.status==1){
								$.Taiji.hideLoading();
								$.Taiji.showNote("充值后账户金额为:"+responseText.postBalance/100+"元");
								$("#charge").attr("disabled",false);
								$(".taiji_search_submit").click();
							}else{
								$.Taiji.hideLoading();
								$("#charge").attr("disabled",false);
								$.Taiji.showWarn(responseText.message);
							}
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
                            <h4 class="panel-title">账户充值</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/customerservice/finance/recharge/manage" method="post">
								<div class="form-group">
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
									<button id="readCard" class="btn btn-success btn-default" type="button"><i class="fa  fa-credit-card   m-r-10  "></i>读卡</button>
									
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
									<%-- <form:input path="vehiclePlate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:label path="vehiclePlateColor">车牌颜色：</form:label>
									<form:select path="vehiclePlateColor"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
										<form:option value="">--请选择--</form:option>
										<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
									</form:select> --%>
								<button id="select" class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
								<input id="Button1" type="button" class="btn  btn-success m-r-5" value="导出EXCEL" onclick="javascript:method1('tableExcel')" />
									<input type="button" onclick="doPrint()" class="btn  btn-success m-r-5" value="打印"/>
								<br>
									<form:input path="tradeFee"  maxlength="100"  cssClass="form-control"  placeholder="充值金额(元)" />
									<form:label path="chargeType">收费类型：</form:label>
									<form:select path="chargeType"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
										<form:option value="">--请选择--</form:option>
										<form:options items="${chargeType}" itemLabel="value" itemValue="code"/>
									</form:select>
									<form:label path="passWord">用户账密码</form:label>
									<form:input  path="passWord"    cssClass="form-control"  placeholder="资金安全性校验!"/>
									<button id="charge" class="taiji_search btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-usd   m-r-10"></i>充值</button>
									
									<div>
								</div>
                        	</form:form>
                        	<div>
								<span>账户余额</span>
								<span id="accountBalance"></span>
								<span style="color:#F00; font-weight:bold; " >元</span>
							</div>
                        </div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<!--startprint-->
							<table class="table table-striped table-bordered  table-hover" id="tableExcel">
								<thead>
									<tr>
										<th>序号</th>
										<th>交易类型</th>
										<th>充值网点</th>
										<th>交易时间</th>
										<th>交易前金额(元)</th>
										<th>交易金额(元)</th>
										<th>预期交易后金额(元)</th>
										<!-- <th>操作</th> -->
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<!--endprint-->
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