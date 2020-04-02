<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<link href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" />
<script src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true
	}).editable({
		selector:".editable",//元素选择器，对目标元素启用编辑
		url:"editGrid",//编辑提交的url
	    success: editGridSuccess//服务器返回成功的回调方法
	}).on("taijiSearchSuccess",function(event,result){
		/*
		单独设置性别参数
		$(".editable.male").editable({
			 url:"editGrid",//编辑提交的url
			 source: [
			            {"value": '0', "text": '女'},
			            {"value": '1', "text": '男'}
			        ],
			 success: editGridSuccess//服务器返回成功的回调方法
		}); */
	}).on("taijiEditSuccess",function(event,result){
		
	});
	
	$("#addBtn").click(function(){
		$(this).showModal({size:"modal-lg",backdrop:false,data:{loginName:"sample"}});
		return false;
	});
	
	function editGridSuccess(response, newValue) {
    	if($.isPlainObject(response)&&response.success===true){
    		$.Taiji.showNote(response.msg);
    	}else{
        	var result=$.parseJSON($(response).find("#taiji_note").text());
        	if(result&&result.success===false){
        		return $.Taiji.base64.decode(result.msg);	        	
        	}
        }
    }
});


</script>
</head>
<body >
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
                            <h4 class="panel-title">卡黑名单查询（三天一次刷新）</h4>
                        </div>
                        
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/acl/cardBlackList/manage" method="post">
								<div class="form-group m-5">
									<form:input path="cardId" size="50" maxlength="50" placeholder="卡号"  cssClass="form-control"/>
								</div>
<!-- 								<div class="form-group m-5"> -->
<!-- 									<label class="control-label">状态</label> -->
<%-- 									<form:select path="status" cssClass="selectpicker" data-style="btn-white" data-width="80px"> --%>
<%-- 										<form:option value="">全部</form:option> --%>
<%-- 										<form:options items="${statuses}" itemLabel="value"/> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
										<form:option value="2">2</form:option>
										<form:option value="10">10</form:option>
										<form:option value="16">16</form:option>
										<form:option value="20">20</form:option>
										<form:option value="50">50</form:option>
										<form:option value="100">100</form:option>
									</form:select>
								</div>

								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
						</div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
										<th width="60px">卡号</th>
										<th width="60px">黑名单类型</th>
										<th width="200px">部中心接收时间</th>
										<th width="200px">黑名单生成时间</th>
										<th width="60px">发行方ID</th>
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
