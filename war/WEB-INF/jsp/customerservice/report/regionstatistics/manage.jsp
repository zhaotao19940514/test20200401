<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html xmlns="http://www.w3.org/1999/xhtml"> 
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
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
	});
	
})
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
        tableToExcel('table1')
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
                            <h4 class="panel-title">地区充值消费统计</h4>
                        </div>                       
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/customerservice/report/regionstatistics/manage" method="post">
                      			<%-- <div class="form-group m-t-5">
										<label class="control-label">地区：</label> <select
											id="AgencyId" name="regionId" class="form-control  m-r-5"
											data-style="btn-white" data-width="100px">
											<c:forEach items="${Region}" var="an">
												<option value="${an.name}">${an.value}</option>
											</c:forEach>
										</select>
								</div> --%>
                      			<%-- <div class="form-group m-t-2"> 
									<label class="control-label">查询日期</label>
									<form:input path="handleDate"   cssClass="form-control"/>	
								</div>	 --%>																													
								<div class="form-group m-t-5">
						 <label class="control-label">日期</label>
						<div  class="input-group">
	 						<form:input cssStyle="width:150px"   path="startDate" readonly="true"  cssClass="form-control" />
						    <span class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startDate'),dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endDate\')}'});"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
					</div>
					<div class="form-group m-t-5">	
						<label class="control-label">至</label>
						<div  class="input-group">
		  					<form:input cssStyle="width:150px" path="endDate" readonly="true"  cssClass="form-control"   />
							<span class="input-group-btn">
								<button type="button" class="btn btn-default"  onclick="WdatePicker({el:$dp.$('endDate'),dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'startDate\')}'});"><i class="fa fa-calendar"></i></button>
							</span>
						</div>
	 				</div>	 								
								<%-- <div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize"  cssClass="selectpicker"  data-style="btn-white" data-width="80px">
										<form:option value="10">10</form:option>
										<form:option value="30" selected="selected">30</form:option>
										<form:option value="50">50</form:option>
									</form:select>
								</div> --%>
								<input id="Button1" type="button" value="导出EXCEL" class="btn  btn-success m-r-5" onclick="javascript:method1('table1')" />
                                <button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button> 
                      			
                        	</form:form>
						</div>
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<!--startprint-->
							<table class="table table-striped table-bordered  table-hover" id="table1">
								<thead>
									<tr> 										
										<th>地区名称</th>										
										<th>圈存金额(元)</th>
										<th>半条流水金额(元)</th>
										<th>账户充值金额(元)</th>
										<th>账户冲正金额(元)</th>
										<th>退款金额(元)</th>
										<th>补交金额(元)</th>
										<th>合计</th>
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
		<!-- <a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		end scroll to top btn
	</div> -->
	<!-- end page container -->
	
	

</body>
</html>
