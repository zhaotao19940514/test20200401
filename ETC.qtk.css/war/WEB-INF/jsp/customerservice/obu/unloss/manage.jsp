<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false
		}
	});
	$("#my-table").on('click','#otransferBtnOff',function(){
		$.Taiji.showWarn("请先挂失");
	});
	$("#my-table").on('click','#ountransferBtn',function(){
		var obuId = $(this).attr("param");
		$.Taiji.defConfirm("你确定要对该卡进行解挂？").done(function(){
			doUnLossOBU(obuId);	
		});
	});
});
function doUnLossOBU(obuId){
	
	$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
	  var data = {};
	  data.obuId = obuId;
	  $.ajax({
        url : rootUrl+"app/customerservice/obu/unloss/unlossOBU",
        type : "POST",
        dataType : "json",
        data:JSON.stringify(data),
        contentType: "application/json",
        async:true,
        success : function(responseText) {
      	  $("#listForm").submit();
      	  if(responseText.status==1){
      		  $.Taiji.showNote(responseText.message);
      	  }else{
      		  $.Taiji.showWarn(responseText.message);
      	  }
      	  $.Taiji.hideLoading();
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
<script type="text/javascript">
	$(function(){
		$("#readObuBtn").click(function(){
			var reader = new ObuOfflineReader();
			reader.openObuDev();
			var obuId = reader.readContractSerialNo();
			reader.closeObuDev();
			if(isNaN(obuId)){
				$.Taiji.showWarn("未读到电子标签信息");
				return;
			}
			$("#obuId").val(obuId);
			$("#listForm").submit();
		});
	})
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
                            <h4 class="panel-title">OBU解挂</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/obu/unloss/manage" method="post">
								<div class="form-group">
									<form:input path="obuId"  maxlength="100"  cssClass="form-control"  placeholder="电子标签序列号" />
									<form:input path="vehicleId"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:select path="vehicleColor"  cssClass="form-control m-r-5"  data-style="btn-white" data-width="160px">
										<form:option value="">--请选择车牌颜色--</form:option>
										<form:option value="0">蓝色</form:option>
										<form:option value="1">黄色</form:option>
										<form:option value="2">黑色</form:option>
										<form:option value="3">白色</form:option>
										<form:option value="4">渐变绿色</form:option>
										<form:option value="5">黄绿双拼色</form:option>
										<form:option value="6">蓝白渐变色</form:option>
									</form:select>
								</div>
								<button class="btn btn-md btn-default btn btn-success btn-small" id="readObuBtn" type="button"><i class="fa  fa-credit-card  m-r-10  "></i>读电子标签</button>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
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
										<th>电子标签序列号</th>
										<th>电子标签状态</th>
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