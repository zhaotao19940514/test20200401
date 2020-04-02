<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#logManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false,
			 autoRefresh : {
				    enable : false,
				    interval : 10000
				   }
		}
	}).on("taijiAsyncSuccess",function(event,result){
		if(result!="error"){
			window.location="${rootUrl }app/system/oplog/asyncExport/download?fileName="+result;
		}
	}).on("taijiSearchSuccess",function(event,result){
	});
});
</script>

</head>
<body>
<div id="form-target"></div>
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>

<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
		<!-- end #sidebar -->
		<!-- 内容页 -->
				<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>
			
		<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			
			<div id="logManage" class="panel panel-inverse">
				<div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">操作日志</h4>
                        </div>
                        
                  <div class="panel-toolbar">
                        <a href="${rootUrl }app/system/oplog/multiRemove" data-selector="#myTable-column [name='ids']:checked" class="btn  btn-success m-r-10 taiji_operate {confirm_message:'您确认要删除选中记录吗?',refresh:true}">
							<i class="fa fa-times m-r-10"></i>批量删除
						</a>
						<a href="${rootUrl }app/system/oplog/multiRemove" data-selector="#myTable-column [name='ids']:checked" class="btn  btn-success m-r-10 taiji_operate {confirm_message:'您确认要操作选中记录吗?'}">
							<i class="fa fa-hand-o-up m-r-10"></i>批量操作
						</a>
						<a href="${rootUrl }app/system/oplog/asyncExport/runTask" data-selector="#startTime,#endTime,#file" class="taiji_async {process:'${rootUrl }app/system/oplog/asyncExport/runTask/process',interval:1000,confirm_message:'导出可能花较多时间，您确认要操作吗?'} btn  btn-success m-r-10 ">
							<i class="fa fa-tasks m-r-10"></i>异步导出
						</a>
						<a href="${rootUrl }app/system/oplogExport/multiUser/runTask" data-selector="#startTime,#endTime,#file" class="taiji_async {process:'${rootUrl }app/system/oplogExport/multiUser/process',interval:1000,confirm_message:'导出可能花较多时间，您确认要操作吗?'} btn  btn-success m-r-10 ">
							<i class="fa fa-tasks m-r-10"></i>多用户异步导出
						</a>
						<a href="${rootUrl }app/system/oplog/export" data-selector="#startTime,#endTime" class="taiji_export {fileUrl:'${rootUrl }app/system/oplog/export/file',confirm_message:'您确认要操作吗?'} btn  btn-success m-r-10 ">
							<i class="fa fa-file-excel-o m-r-10"></i>导出Excel
						</a>
						<a href="${rootUrl }app/system/oplog/word" class="btn  btn-success  m-r-10 ">
							<i class="fa fa-file-word-o m-r-10"></i>导出Word
						</a>
				</div>	
				<div class="panel-body">		
				<form:form cssClass="taiji_search_form form-inline m-t-5" modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/system/oplog/manage" method="post" enctype="multipart/form-data">
				<!-- 查询条件 -->
					<div class="taiji_search_condition  m-t-5">
					</div>
					<div class="form-group m-t-5">
						<label class="control-label">日志日期</label>
						<div  class="input-group">
	 						<form:input cssStyle="width:150px"   path="startTime" readonly="true"  cssClass="form-control" />
						    <span class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
					</div>
					<div class="form-group m-t-5">	
						<label class="control-label">至</label>
						<div  class="input-group">
		  					<form:input cssStyle="width:150px" path="endTime"  readonly="true" cssClass="form-control"   />
							<span class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
	 				</div>	
	 				
	 				<div class="form-group m-t-5">
						<label class="control-label">操作用户</label>
						<div  class="input-group">
							<input type="hidden"  name="userId" id="userId">
							<form:input path="userName" cssClass="form-control"   size="10" maxlength="50" />
							<span class="input-group-btn">
								<a href="${rootUrl }app/acl/userSelect/manage?targetId=userId&targetName=userName&multiple=0"  class="taiji_modal_lg btn  btn-info"><i class="fa fa-user"></i></a>
							</span>
						</div>
					</div>	
						
					<button class="taiji_search_submit btn btn-md btn-success m-l-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
					<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
				    <button class="taiji_search_collapse btn btn-md btn-success m-l-5" data-toggle="collapse" data-target="#collapse-content" type="button" ><i class="fa fa-angle-double-down  m-r-10 "></i>更多条件</button>
					<div  class="collapse" id="collapse-content" >
						<div class="form-group m-t-5">	
							<label class="control-label">日志类型</label>
								<form:select path="logType" cssClass="form-control" >
								<form:option value="">全部</form:option>
								<form:options items="${logTypes}" itemLabel="value"/>
							</form:select>
						</div>	
						<div class="form-group m-t-5">
						
							<label class="control-label">IP</label>
							<form:input path="ip" cssClass="form-control" maxlength="50" />
						</div>	
						<div class="form-group m-t-5">
								<label class="control-label">日志内容</label>
								<form:input path="info" cssClass="form-control" maxlength="200" size="50"/>
						</div>	
						
						<div class="form-group  m-t-5">
							<div class="checkbox-inline ">
								<input type="checkbox" class="magic-checkbox" name="checkbox"  value="check1" id="check1"><label for="check1">复选框1</label>
				             </div>	
				             <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="checkbox"  value="check2" checked="checked"  id="check2"><label for="check2">复选框2</label>
				             </div>
				             <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="checkbox"   value="check2" checked="checked"  id="check3"><label for="check3">复选框3</label>
				             </div>
			             </div>
			             <div class="form-group m-t-5">
			             	<%@ include file="/WEB-INF/jsp/pagesize.jsp" %>
						</div>
						  <div class="form-group m-t-5">
							<label class="control-label">多选</label>
							<select class="selectpicker" multiple data-selected-text-format="count>3"  title='请选择（可多选）'>
							      <option value="1">多选一</option>
							      <option value="2">多选二</option>
							      <option value="3">多选三</option>
							      <option value="4">多选四</option>
							  </select>
						</div>
                          <div class="form-group m-5">
							<label class="control-label">选择文件</label>
							<input type="file" id="file" name="file" class="form-control"/>
						</div>
			             <div class="form-group  m-t-5">
                              <label class="control-label">是否本地</label>
                              <div class="radio-inline">
                              	  <input type="radio" name="radiobox" value="1"  class="magic-radio" id="radio1"  checked="checked" ><label for="radio1">是</label>
                              </div>
                              <div class="radio-inline">
                              	  <input type="radio" name="radiobox" value="0"  class="magic-radio" id="radio2"><label for="radio2">否</label>
                              </div>
                          </div>
				  </div>
				</form:form>	
			</div>
			<div id="myTable-body"  class="taiji_search_result taiji_table_float taiji_column_float table-responsive" data-column-width="60">
				<table class="table table-striped table-bordered  table-hover" style="width:1200px">
					<thead>
						<tr>
							<th style="width:50px;height: 42px;"><input type="checkbox"  name="checkAll" class='taiji_check_all' id="checkAll"/><label for="checkAll">&nbsp;</label></th>
							<th >用户</th>
							<th style="width: 240px" >时间</th>
							<th >IP</th>
							<th class="taiji_column_compress"><span>日志类型</span></th>
							<th width="600px">日志</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		
			<div class="panel-footer text-right ">
							<div class="pageturn taiji_pager">
	                        </div>
	                       
             		  </div>
		</div>
		</div>
		</div>
		</div>
	
	<!-- 内容页 -->

<!-- 版权页 -->
<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
<!-- 版权页 -->
</div>
</body>
</html>