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
/**
 * type 0注销  1强制注销
 * cardType 1记账卡  2储值卡
 */
$(function(){
	provider=0;
	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false
		}
	});
	$("#my-table").on('click','#cancelBtnOff',function(){
		$.Taiji.showWarn("该卡已注销");
	});
	$("#searchBtn").click(function(){
		provider=0;
	});
	$("#readCardBtn").click(function() {
		var cardReader = new CardReader();
// 		cardReader.openCard();
		var cardId = cardReader.getCardId();
		var cardBalance = cardReader.getBalance();
		cardReader.closeCard();
		console.log("金额:"+cardBalance);
		if(isNaN(cardId)){
			$.Taiji.showWarn("未读到卡信息");
			return;
		}
		provider=1;
		$("#cardId").val(cardId);
		$("#cardBalance").val(cardBalance);
		$("#listForm").submit();
	});
	//处理储值卡预注销问题
	$("#my-table").on('click','#cancelBtn',function(){
		var rowCardId = $(this).attr("rowCardId");
		$("#rowCardId").val(rowCardId);
		$.Taiji.defConfirm("你确定要对该卡进行注销？").done(function(){
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			doCancel(2,0,1);
		});
		
	});
	//记账卡注销
	$("#my-table").on('click','#preCancelBtn',function(){
		var cardReader = null;
		var rowJCardId = $(this).attr("rowJCardId");
		$("#rowJCardId").val(rowJCardId);
		var type = 1;
		var cardType = $(this).attr("param");
		if(provider==1){
			$.Taiji.defConfirm("你确定要对该卡进行注销？").done(function(){
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				doPreCancel(cardType,provider);
			});
		}else{
			$.Taiji.defConfirm("你确定要对该卡进行注销？").done(function(){
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				noOrderPrecancel(cardType,provider);
			});
		}
	});
	//强制注销
	$("#my-table").on('click','#forceCancel',function(){
		var cardType = $(this).attr("param");
		var type = 1;
		var rowJCardId = $(this).attr("rowJCardId");
		$("#rowJCardId").val(rowJCardId);
		//1 卡类型  provider有无卡
		$.Taiji.defConfirm("你确定要对该卡进行强制注销？").done(function(){
			doCancel(cardType,type,0,provider);
		});
	});
});
//做有卡预注销时(读卡指令)
function doPreCancel(cardType,provider){
 	  var cardReader = new CardReader();
	  var checkCardId = cardReader.getCardId();
	  var cardBalance = cardReader.getBalance();
	  var cardId = $("#rowCardId").val();
	  if(cardType=='1'){
		  cardId = $("#rowJCardId").val();
	  }
	  if(cardId!=checkCardId){
		  $.Taiji.showWarn('卡号前后读取不一致,请重新读卡查询');
		  return;
	  }
	  
	  var data = {};
	  	  var branchName = $("#branchName").val();
	  	  data.branchName = branchName;
	  	  data.provider = provider; 
	 	  data.cardId = cardId;
	 	  data.cardType = cardType;
	 	  data.cardBalance = cardBalance;
	 	  if(cardType=='2'){
	 		 var bankCardId = $("#bankCardId").val().replace(/\s*/g,'');
	 	 	 var cusType = $("#cusType").val();
	 		 var refundType = $("#refundType").val();
	 		 var bankType = $("#bankType").val();
	 		 if(refundType==2){
	 			bankType=0;
	 	 	 }
	 		 var province  = $("#province").val();
	 		 var sell  = $("#sell").val();
	 		 var cusName = $("#cusName").val();
	 		 var cusTel = $("#cusTel").val();
	 	 	 data.bankCardId = bankCardId;
	 	 	 data.cusType = cusType;
	 	 	 data.bankType = bankType;
	 	 	 data.sell = sell;
	 	 	 data.province=province;
	 	 	 data.cusTel = cusTel;
	 	 	 data.cusName = cusName;
	 	 	 data.refundType=refundType;
	 	   }
	  var method = 'POST';
	  var url = rootUrl+"app/customerservice/card/cancel/doPreCancel";
	  var terminateConditions = "data.orderStatus == 2";
 	  cardReader.openCard();
	  cardReader.autoExecuteApdu(url,data,method,terminateConditions,function(){
    		  if(cardType==2){
    			  var type = 0;
    			  var inputFile = $("#file").val();
    			  if(null!=inputFile&&inputFile!='undefined'&&inputFile!=''){
    			 	 upload(cardType,type,provider);
    			  }else{
					 doCancel(cardType,type,provider);
    			  }
			  }else{
				  $.Taiji.hideLoading();
				  $.Taiji.showNote("预注销成功");
				  $("#listForm").submit();
			  }
		  cardReader.closeCard();
	  });
}
//无卡预注销(无指令时)
function noOrderPrecancel(cardType,provider){
	  $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
	  //var cardId = $('#cardId').val();	
	  var cardId;
	  if(cardType=='1'){
		  cardId = $("#rowJCardId").val();
	  } else{
		  cardId = $("#rowCardId").val();
	  }
	  var cardBalance = -1;
	  var data = {};
		  data.provider = provider;
		  data.cardId = cardId;
		  data.cardType = cardType;
	 	  data.cardBalance = cardBalance;
	 	  if(cardType==2){
	 		 var bankCardId = $("#bankCardId").val().replace(/\s*/g,'');
	 		 var refundType = $("#refundType").val();
	 	 	 var cusType = $("#cusType").val();
	 		 var bankType = $("#bankType").val();
	 		 var branchName = $("#branchName").val();
	 	 	 if(refundType==2){
	 	 		bankType=0;
	 	 	 }
	 		 var province  = $("#province").val();
	 		 var sell  = $("#sell").val();
	 		 var cusName = $("#cusName").val();
	 		 var cusTel = $("#cusTel").val();
	 	     data.branchName = branchName;
	 		 data.cusName = cusName;
	 	 	 data.bankCardId = bankCardId;
	 	 	 data.cusType = cusType;
	 	 	 data.bankType = bankType;
	 	 	 data.sell = sell;
	 	 	 data.province=province;
	 	 	 data.cusTel = cusTel;
	 	 	 data.refundType=refundType;
	 	   }
		  data.applyStep = 1;
		  $.ajax({
			      url : rootUrl+"app/customerservice/card/cancel/doPreCancel",
			      type : "POST",
			      dataType : "json",
			      data:JSON.stringify(data),
			      contentType: "application/json",
			      async:true,
			      success : function(responseText) {
			    	  if(responseText.status==1){
			    		  if(cardType==2){
			    			  var type = 0;
			    			  var inputFile = $("#file").val();
			    			  if(null!=inputFile&&inputFile!='undefined'&&inputFile!=''){
			    			 	 upload(cardType,type,provider);
			    			  }else{
								 doCancel(cardType,type,provider);
			    			  }
						  }else{
							  $.Taiji.hideLoading();
							  $.Taiji.showNote("预注销成功");
							  $("#listForm").submit();
						  }
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
//注销
function doCancel(cardType,type,provider){
	var cardBalance = $("#cardBalance").val();
	if(provider==0){
		cardBalance=-1;
	}
	 if(type ==1){
		 $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
	 }
	  // 如果是无卡注销 这里的cardId会是空值 会导致注销失败
	  //var cardId = $('#cardId').val();
	  var cardId;
	  if(cardType=='1'){
		  cardId = $("#rowJCardId").val();
	  }else{
		  cardId = $("#rowCardId").val();
	  }
	  if(type=='1'){
		  cardId = $("#rowJCardId").val();
	  }
	  var data = {};
	  data.cardId = cardId;
	  data.cardType = cardType;
	  data.type = type;
	  data.cardBalance = cardBalance;
	  data.provider = provider;
	  $.ajax({
        url : rootUrl+"app/customerservice/card/cancel/doCancel",
        type : "POST",
        dataType : "json",
        data:JSON.stringify(data),
        contentType: "application/json",
        async:true,
        success : function(responseText) {
          $("#closeBtn").click();	
      	  $("#listForm").submit();
      	  	  $.Taiji.hideLoading();
        },
        error:function(responseText){
        	$.Taiji.showWarn(responseText.responseText);
        	$.Taiji.hideLoading();
        }
    });
}
function upload(cardType,type,provider){
	var formData = new FormData();
	var length=$('#file')[0].files.length;
	console.log("length："+$('#file')[0].files.length);
	for(var i=0;i<length;i++ ){
		formData.append('file', $('#file')[0].files[i]);
	}
	formData.append('cardId',$("#rowCardId").val());
	$.ajax({
	    url: rootUrl+"app/customerservice/card/cancel/fileUpload",
	    type: "post",
        dataType: "json",
        cache: false,
        data: formData,
        processData: false,// 不处理数据
        contentType: false, // 不设置内容类型
	    success: function (responseText) {
	    	if(responseText.status==1){
	    		doCancel(cardType,type,provider)
	    	}
	    },
	    error: function (responseText) {
	    	$.Taiji.showWarn("附件上传失败!");
	    }
	})  
}
</script>

</head>
<body>
<input type="hidden" id="rowJCardId" />
<input id="rowCardId" type="hidden" value='${cardId}'/>
<input type="hidden" id="customerName" value='${customerName }' />
<input type="hidden" id="cardBalance" value='-1'/>
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
                            <h4 class="panel-title">销卡</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/card/cancel/manage" method="post">
								<div class="form-group">
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
									<form:input path="vehicleId"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:select path="vehicleColor"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">--请选择车牌颜色--</form:option>
										<form:options items="${vehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
									</form:select>
								</div>
								<button class="btn btn-md btn-default btn btn-success btn-small"  id="readCardBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>读卡</button>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" id='searchBtn' type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
						<div   class="taiji_search_result table-responsive">
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