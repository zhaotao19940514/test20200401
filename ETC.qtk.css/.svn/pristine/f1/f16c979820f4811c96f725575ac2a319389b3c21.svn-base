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
<script src="${rootUrl }plugins/datepicker/WdatePicker.js" type="text/javascript"></script>
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
                            <h4 class="panel-title">通行费电子发票打印</h4>
                        </div>
                        

<div class="form-group m-5">


			<div class="form-group m-5">

						<div  class="input-group">
							 <label class="control-label">卡号</label>
	 						<input Style="width:150px"   id="startTime"/>
						    <span>
								<button   type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button>
							</span>
							<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
									<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
									<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" >打印发票</button>
						</div>
										</div>
                        
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
										<th>卡号</th>
										<th width="200px">入口站</th>
										<th ><span>出口站</span></th>
										<th width="200px">金额(分)</th>
									</tr>
								</thead>
								<tbody>
								<tr>
										<th>52011346160000033353</th>
										<th ><span>G50S南岸</span></th>
										<th width="200px">丰盛</th>
										<th width="200px">730</th>
									</tr>
									<tr>
										<th>52011346160000033353</th>
										<th ><span>黄花</span></th>
										<th width="200px">长株交点</th>
										<th width="200px">543</th>
									</tr>
									<tr>
										<th>52011346160000033353</th>
										<th ><span>S4512520010010</span></th>
										<th width="200px">六寨5</th>
										<th width="200px">7668</th>
									</tr>
									<tr>
										<th>52011346160000033353</th>
										<th ><span>松山湖</span></th>
										<th width="200px">横市互通收费单元</th>
										<th width="200px">47033</th>
									</tr>
									<tr>
										<th>52011346160000033353</th>
										<th ><span>高新中航黎阳站</span></th>
										<th width="200px">赶水</th>
										<th width="200px">8123</th>
									</tr>
									<tr>
										<th>52011346160000033353</th>
										<th ><span>莆田</span></th>
										<th width="200px">祥谦</th>
										<th width="200px">44</th>
									</tr>
									<tr>
										<th>52011346160000033353</th>
										<th ><span>祥谦</span></th>
										<th width="200px">祥谦互通</th>
										<th width="200px">44</th>
									</tr>
									<tr>
										<th>52011346160000033353</th>
										<th ><span>G0060520030070</span></th>
										<th width="200px">武鸣北互通8</th>
										<th width="200px">1995</th>
									</tr>
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
