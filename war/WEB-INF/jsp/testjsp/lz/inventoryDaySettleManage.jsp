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
                            <h4 class="panel-title">库存日结</h4>
                        </div>
                        

<div class="form-group m-5">


			<div class="form-group m-5">

						<div  class="input-group">
							 <label class="control-label">日期 </label>
	 						<input Style="width:150px"   id="startTime"/>
						    <span>
								<button   type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button>
							</span>
							<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
									<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
						</div>
										</div>
                        
						<div   class="taiji_search_result taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr>
										<th>网点名称</th>
										<th width="200px">日期</th>
										<th>渠道名称</th>
										<th width="200px">卡库存数量</th>
										<th width="200px">OBU库存数量</th>
									</tr>
								</thead>
								<tbody>
								<tr>
										<th>安顺东快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										<th>1900</th>
									
									</tr>
									<tr>
										<th>镇宁快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
									</tr>
									<tr>
										<th>黄果树快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
									</tr>
									<tr>
										<th>永宁快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
									</tr>
									<tr>
										<th>晴隆快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
									</tr>
									<tr>
										<th>普安快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
									</tr>
									<tr>
										<th>凯里黔通卡客服中心（自营）</th>
										
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
									</tr>
									<tr>
										<th>榕江快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
									</tr>
									<tr>
										<th>从江东快捷充值点</th>
										<th width="200px">20200221</th>
										<th>黔通智联（自营）</th>
										<th width="200px">2000</th>
										
										<th>1900</th>
									
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
