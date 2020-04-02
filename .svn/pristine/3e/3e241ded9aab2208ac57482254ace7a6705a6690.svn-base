<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false,
			 autoRefresh : {
				    enable : false,
				    interval : 10000
				   }
		}
	}).on("taijiAsyncSuccess",function(event,result){
	}).on("taijiSearchSuccess",function(event,result){
	});
	
	$("#select").click(function(){
		
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(!startTime || !endTime){
			$.Taiji.showWarn("请选择日期后继续");
			return false;
		}
		
		$.Taiji.defConfirm("该功能耗时较大！请耐心等待！切勿重复点击！高峰时期请勿使用此功能！").done(function(){
			
			//var days = DateDiff(startTime,endTime);
			//alert(startTime.toLocaleDateString())
		});
		
		var data={};
		data.startTime = startTime;
		data.endTime = endTime;
	    $.ajax({
	        url:"${rootUrl}app/customerservice/report/lssuancePerBank/manage",
	        data:JSON.stringify(data),
	        type:"POST",
	        contentType:'application/json;charset=UTF-8',
	        dataType : 'json',
	        success:function(data){
	        	//清空数据
	        	$("#tbody1  tr:not(:first)").html("");
	        	$("#tbody2  tr:not(:first)").html("");
				//往表格填数据
	        	var tempHtml = "";
	        	var tempHtml2 = "";
                for(var i = 0; i< data.result1.length; i++)
                {
                     tempHtml += "<tr><td>"+data.result1[i].channelName+"</td><td>"+data.result1[i].guiCount+"</td><td>"+data.result1[i].noGuiCount+"</td></tr>";
                }
                for(var i = 0; i< data.result2.length; i++)
                {
                     tempHtml2 += "<tr><td>"+data.result2[i].name+"</td><td>"+data.result2[i].guiCount+"</td><td>"+data.result2[i].noGuiCount+"</td></tr>";
                }
                
                $("#tbody1").append(tempHtml);
                $("#tbody2").append(tempHtml2);
	        },
	        error:function(error){
	        	$.Taiji.hideLoading();
				$.Taiji.showWarn("查询失败，请联系管理员");
	        }
	    });
	});
	
	$("#reset").click(function(){
		$("#startTime").val("");
		$("#endTime").val("");
	});

	$("#excel2").click(function method() {//整个表格拷贝到EXCEL中
		 alert("in");
	     if(getExplorer()=='ie')
	     {
	         var curTbl = document.getElementById("my-table");
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
	         tableToExcel('my-table')
	     }
	});
});


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
                            <h4 class="panel-title">各银行发行量查询</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/report/lssuancePerBank/manage" method="POST">
								<!-- 查询条件 -->
								<div class="taiji_search_condition  m-t-5">
								</div>
								<div class="form-group m-t-5">
									<label class="control-label">日志日期</label>
									<div  class="input-group">
				 						<form:input cssStyle="width:150px"   path="startTime" readonly="false"  cssClass="form-control" />
									    <span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
								</div>
								<div class="form-group m-t-5">	
									<label class="control-label">至</label>
									<div  class="input-group">
					  					<form:input cssStyle="width:150px" path="endTime"  readonly="false" cssClass="form-control"   />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button>
										</span>
									</div>
				 				</div>	
								<button id="select" class=" btn btn-md btn-success m-r-5 btn btn-primary btn-small" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button id="reset" class=" btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
								<a href="${rootUrl }app/customerservice/report/lssuancePerBank/export" data-selector="#startTime,#endTime" class="taiji_export {fileUrl:'${rootUrl }app/customerservice/report/lssuancePerBank/export/file',confirm_message:'您确认要操作吗?'} btn  btn-success m-r-10 ">
									<i class="fa fa-file-excel-o m-r-10"></i>导出Excel
								</a>
                        	</form:form>
                        </div>
						<div   class=" table-responsive">
							<table id="my-table" class="table table-bordered  table-hover" >
								<thead>
									<tr>
										<th colspan="3" style="text-align: center">ALL贵籍&非贵籍 拆分五大行</th>
									</tr>
								</thead>
								<tbody id="tbody1">
									<tr>
										<th>单位名称</th>
										<th>贵籍发行量</th>
										<th>非贵籍发行量</th>
									</tr>
								</tbody>
								<thead>
									<tr>
										<th colspan="3" style="text-align: center;border-top-width: 5px">ALL贵籍&非贵籍</th>
									</tr>
								</thead>
								<tbody id="tbody2">
									<tr>
										<th>单位名称</th>
										<th>贵籍发行量</th>
										<th>非贵籍发行量</th>
									</tr>
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