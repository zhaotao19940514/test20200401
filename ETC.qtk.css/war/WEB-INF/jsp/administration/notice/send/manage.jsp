<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false
		}
	});
	$("#my-table").on('click','#reSend',function(){
		var rowId = $(this).attr("rowId");
		$.Taiji.defConfirm("确定要重发该条通知吗？").done(function(){
			doSend(rowId);
		});
		
	});	
});
function doSend(rowId){
		  $.ajax({
			      url : rootUrl+"app/administration/notice/send/update?rowId="+rowId,
			      type : "POST",
			      dataType : "json",
			      contentType: "application/json",
			      async:true,
			      success : function(responseText) {
			    	  if(responseText.status==1){
			    		  $.Taiji.showNote("修改状态成功，请等待定时器发送~");
			    	  }else{
			    		  $.Taiji.showWarn("修改状态失败");
			    	  }
			    	  $("#searchBtn").click();
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
                            <h4 class="panel-title">发送开卡开签通知</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/administration/notice/send/manage" method="POST">
								<div class="form-group">
									<form:input path="vehiclePlate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5 btn btn-primary btn-small" type="button" id='searchBtn'><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>渠道号</th>
										<th>车牌号码</th>
										<th>车牌颜色</th>
										<th>用户证件号</th>
										<th>证件类型</th>
										<th>卡号</th>
										<th>obu号</th>
										<th>发送时间</th>
										<th>通知类型</th>
										<th>发送状态</th>
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