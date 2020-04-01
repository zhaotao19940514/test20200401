<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<link
	href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css"
	rel="stylesheet" />
<script
	src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js"
	type="text/javascript"></script>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#myManage").taiji({
			enableAclCheck : true,
			search : {
				 autoSearch:false
			}
		});
	})
</script>
<script type="text/javascript">
function table2Excel (){
	if(checkDate()){
		var fileName = $("#startTime").val()+"至"+$("#endTime").val()+"FTP处理结果.xls"
		var sheetName = $("#startTime").val()+"至"+$("#endTime").val();
		tablesToExcel(['tableExcel'], [sheetName], fileName, 'Excel');
	}else{
		return;
	}
}
function checkDate(){
	if($("#startTime").val()==""||$("#endTime").val()==""){
		$.Taiji.showWarn("请选择日期后继续！");
		return false;
	}
	return true;
}

var tablesToExcel = (function() {
	   //定义表格格式
    var uri = 'data:application/vnd.ms-excel;base64,'
        , tmplWorkbookXML = '<?xml version="1.0"?><?mso-application progid="Excel.Sheet"?><Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet" xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet">'
        + '<DocumentProperties xmlns="urn:schemas-microsoft-com:office:office"><Author>Axel Richter</Author><Created>{created}</Created></DocumentProperties>'
        + '<Styles>'
        + '<Style ss:ID="Currency"><NumberFormat ss:Format="Currency"></NumberFormat></Style>'
        + '<Style ss:ID="Date"><NumberFormat ss:Format="Medium Date"></NumberFormat></Style>'
        + '</Styles>'
        + '{worksheets}</Workbook>'
        , tmplWorksheetXML = '<Worksheet ss:Name="{nameWS}"><Table>'
        +'<Column ss:Width="73.5"/>'
        +'<Column ss:Width="216"/>'
        +'<Column ss:Width="96.75"/>'
        +'<Column ss:Width="120" ss:Span="1"/>'
        +'<Column ss:Index="6" ss:Width="96.75"/>'
        +'<Column ss:Width="100.5"/>'
        +'<Column ss:Width="110.25" ss:Span="1"/>'
        +'{rows}</Table></Worksheet>'
        , tmplCellXML = '<Cell{attributeStyleID}{attributeFormula}><Data ss:Type="{nameType}">{data}</Data></Cell>'
        , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
        , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
    return function(tables, wsnames, wbname, appname) {
        var ctx = "";
        var workbookXML = "";
        var worksheetsXML = "";
        var rowsXML = "";
        for (var i = 0; i < tables.length; i++) {
            if (!tables[i].nodeType) tables[i] = document.getElementById(tables[i]);
//          控制要导出的行数
            for (var j = 0; j < tables[i].rows.length; j++) {
                rowsXML += '<Row>';
//                控制导出的列数
                for (var k = 0; k < tables[i].rows[j].cells.length; k++) {
                    var dataType = tables[i].rows[j].cells[k].getAttribute("data-type");
                    var dataStyle = tables[i].rows[j].cells[k].getAttribute("data-style");
                    var dataValue = tables[i].rows[j].cells[k].getAttribute("data-value");
                    dataValue = (dataValue)?dataValue:tables[i].rows[j].cells[k].innerHTML;
                    var dataFormula = tables[i].rows[j].cells[k].getAttribute("data-formula");
                    dataFormula = (dataFormula)?dataFormula:(appname=='Calc' && dataType=='DateTime')?dataValue:null;
                    ctx = {  attributeStyleID: (dataStyle=='Currency' || dataStyle=='Date')?' ss:StyleID="'+dataStyle+'"':''
                        , nameType: (dataType=='Number' || dataType=='DateTime' || dataType=='Boolean' || dataType=='Error')?dataType:'String'
                        , data: (dataFormula)?'':dataValue
                        , attributeFormula: (dataFormula)?' ss:Formula="'+dataFormula+'"':''
                    };
                    rowsXML += format(tmplCellXML, ctx);
                }
                rowsXML += '</Row>'
            }
            ctx = {rows: rowsXML, nameWS: wsnames[i] || 'Sheet' + i};
            worksheetsXML += format(tmplWorksheetXML, ctx);
            rowsXML = "";
        }
        ctx = {created: (new Date()).getTime(), worksheets: worksheetsXML};
        workbookXML = format(tmplWorkbookXML, ctx);
//      查看后台的打印输出
      //  console.log(workbookXML);
        var link = document.createElement("A");
        link.href = uri + base64(workbookXML);
        link.download = wbname || 'Workbook.xls';
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }
})();
</script>
</head>
<body>
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>

	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
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
								<a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">贵阳银行FTP处理结果查询</h4>
						</div>
						<div class="panel-body">
							<form:form cssClass="taiji_search_form form-inline m-t-5 "
								modelAttribute="queryModel" id="listForm" name="listForm"
								action="${rootUrl}app/report/gybftpdetail/manage" method="post">
								<br>
								<div class="form-group m-t-5">
									<label class="control-label">日期</label>
									<div class="input-group">
										<form:input  cssStyle="width:150px"   path="startTime" readonly="true"  cssClass="form-control" />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
										
										</span>
									</div>
								</div>
								<div class="form-group m-t-5">
									<label class="control-label">至</label>
									<div class="input-group">
										<form:input cssStyle="width:150px" path="endTime"  readonly="true" cssClass="form-control"   />
										<span class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button>
								</span>
									</div>
								</div>

								<button class="taiji_search_submit btn btn-md btn-success m-r-5"
									type="button">
									<i class="fa fa-search  m-r-10 "></i>查询
								</button>
								<a href="#" onclick="table2Excel()	" class=" btn btn-md btn-success m-r-5">导出EXCEL</a>
								<button class="taiji_search_reset btn btn-md btn-default"
									type="button">
									<i class="fa  fa-refresh  m-r-10  "></i>重置
								</button>
							</form:form>
						</div>
						<div
							class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover"
								id="tableExcel">
								<thead>
								<tr>
								<th colspan="5" style="text-align:center;">贵阳银行控制/解控成功信息</th>
								</tr>
									<tr>
										<th>文件名</th>
										<th>提交控制数量</th>
										<th>实际控制数量</th>
										<th>提交解控数量</th>
										<th>实际解控数量</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager"></div>
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
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->



</body>
</html>
