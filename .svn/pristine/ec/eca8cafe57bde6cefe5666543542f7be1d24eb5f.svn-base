<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/ztree/js/jquery.ztree.js" type="text/javascript"></script>
<link rel="stylesheet" href="${rootUrl }plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>

<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false});
	$("#resourceManage").taiji({
		enableAclCheck:true,
		width:500,
		height:500
	}).on("taijiAddSuccess",function(event,data){
		var theId = $.trim($(data).find("#result_id").html());
		//if(theId.indexOf("NOT_MENU_")>=0){
		//	return;
		//}
		var theParentId = $.trim($(data).find("#result_menuId").html());
		var theName = $.trim($(data).find("#result_name").html());
		//$.tree.focused().create({data:theName,attributes:{id:theId}},"#"+theParentId);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var parent = zTree.getNodeByParam("id",theParentId);	
		zTree.addNodes(parent,{"name":theName,"id":theId});
	}).on("taijiEditSuccess",function(event,data){
		var theId = $.trim($(data).find("#result_id").html());
		var theName = $.trim($(data).find("#result_name").html());
		//$.tree.focused().rename("#"+theId,theName);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var node = zTree.getNodeByParam("id",theId);
		if(node){node.name = theName;}
		zTree.updateNode(node);
	}).on("taijiRemoveSuccess",function(event,data){
		var id=$(data).find("#result_id").html();
		var theId = $.trim(id);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var node = zTree.getNodeByParam("id",theId);
		zTree.removeNode(node);
		//$.tree.focused().remove("#"+theId);
	});
	
	$("select").change(function(){
		$("#queryButton").click();
	});

	function myAdd(data){
		//var result = $(data).find("#taiji_resultMessage");
		//if($(result).hasClass("taiji_success")){
			var theId = $.trim($(data).find("#result_id").html());
			if(theId.indexOf("NOT_MENU_")>=0){
				return;
			}
			var theParentId = $.trim($(data).find("#result_menuId").html());
			var theName = $.trim($(data).find("#result_name").html());
			$.tree.focused().create({data:theName,attributes:{id:theId}},"#"+theParentId);
		//}
	}

	function myEdit(data){
		//var result = $(data).find("#taiji_resultMessage");
		//if($(result).hasClass("taiji_success")){
			var theId = $.trim($(data).find("#result_id").html());
			var theName = $.trim($(data).find("#result_name").html());
			$.tree.focused().rename("#"+theId,theName);
		//}
	}

	function myRemove(data){
		//var result = $(data).find("#taiji_resultMessage");
		//if($(result).hasClass("taiji_success")){
			var id=$(data).find("#result_id").html();
			var theId = $.trim(id);
			$.tree.focused().remove("#"+theId);
		//}
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
			    <div class="col-md-2">
			    	<%@ include file="tree.jsp" %>
			    </div>
			    <div class="col-md-10">
			        <!-- begin panel -->
                    <div id="resourceManage" class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">资源</h4>
                        </div>
                        
                        <div class="panel-toolbar ">
                        <a href="#"  class="taiji_modal	 taiji_acl btn  btn-success m-r-10" id="add_a" style="display: none">
                        	<i class="fa fa-plus m-r-5"></i><span id="addLinkName"></span>
						</a>
						<a href="#" class="taiji_modal	 taiji_acl btn  btn-success m-r-10" id="add_n" style="display: none">
							<i class="fa fa-plus m-r-5"></i>添加非菜单
						</a>
                        	<form:form  cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" method="post" action="${rootUrl}app/acl/resource/manage">
								
								<div class="form-group m-5">
									<form:input path="name" size="10" maxlength="50" placeholder="资源名称    "  cssClass="form-control"/>
								</div>
								<div class="form-group m-5">
									<label class="control-label">资源类型</label>
									<select id="menuType" name="menuType"  class="form-control">
										<option value="">----</option>
										<option value="COLUMN" style="display: none">栏目菜单</option>
										<option value="BOX_TAB">标签菜单</option>
										<option value="NOT_MENU">非菜单</option>
									</select>
								</div>
								<input type="hidden" id="id" name="id" value="root"></input>
								<input type="hidden" id="resourceType" name="resourceType" value=""></input>
								<button id="queryButton" class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
                        	</form:form>
                        </div>
                       
                        <div class="panel-body">
							<div   class="taiji_search_result table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th>序号</th>
											<th>名称</th>
											<th>图片</th>
											<th>菜单类型</th>
											<th>url</th>
											<th>请求</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
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
