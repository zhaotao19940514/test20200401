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
	var provider=0;
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false
		}
	
	});
	$("#my-table").on('click','#precancelBtnOff',function(){
		$.Taiji.showWarn("该卡已申请注销");
	});
	$('#my-table').on('click','#preCancelBtn',function(){
		var cardReader = new CardReader();
		var cardId = $(this).attr("param");
		$.Taiji.defConfirm("你确定要对该卡进行预注销？").done(function(){
			doPreCancel(cardId,provider,cardReader);	
		});
	});
	$("#readCardBtn").click(function() {
		var cardReader = new CardReader();
		var cardId = cardReader.getCardId();
		cardReader.closeCard();
		if(isNaN(cardId)){
			$.Taiji.showWarn("未读到卡信息");
			return;
		}
		provider = 1;
		$("#cardId").val(cardId);
	});
});

function doPreCancel(cardId,provider,cardReader){
	  $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
	  if(provider==1){
		  precancel(cardId,provider,cardReader);
	  }else{
		  noOrderPrecancel(cardId,provider);
	  } 
}

function precancel(cardId,provider,cardReader){
	  var checkCardId = cardReader.getCardId();
	  var cardId = $('#cardId').val();
	  if(cardId!=checkCardId){
		  $.Taiji.showWarn('卡号前后读取不一致,请重新读卡查询');
		  return;
	  }
	  var data = {};
	  	  data.provider = provider;
	 	  data.cardId = cardId;
	  var method = 'POST';
	  var url = rootUrl+"app/customerservice/card/precancel/doPreCancel";
	  var terminateConditions = "data.orderStatus == 2";
	  cardReader.openCard();
	  cardReader.autoExecuteApdu(url,data,method,terminateConditions,function(){
		  $.Taiji.hideLoading();
		  $.Taiji.showNote("预注销成功");
		  $("#listForm").submit();
		  cardReader.closeCard();
	  });
}
function noOrderPrecancel(cardId,provider){
	  var data = {};
  		  data.provider = provider;
  		  data.cardId = cardId;
		  data.applyStep = 1;
		  $.ajax({
			      url : rootUrl+"app/customerservice/card/precancel/doPreCancel",
			      type : "POST",
			      dataType : "json",
			      data:JSON.stringify(data),
			      contentType: "application/json",
			      async:true,
			      success : function(responseText) {
			    	$("#listForm").submit();
			    	$.Taiji.hideLoading();
			    	$.Taiji.showNote(responseText.message);
			      },
			      error:function(responseText){
			      	$.Taiji.showWarn(responseText.message);
			      	$.Taiji.hideLoading();
			      }
	  });
}
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
                            <h4 class="panel-title">预销卡</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/card/precancel/manage" method="post">
								<div class="form-group">
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="卡号" />
									<form:input path="vehicleId"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:select path="vehicleColor"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">--请选择车牌颜色--</form:option>
										<form:options items="${vehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
									</form:select>
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="btn btn-md btn-default btn btn-success btn-small"  id="readCardBtn"  type="button"> <i class="fa  fa-credit-card  m-r-10  "></i>读卡</button>
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