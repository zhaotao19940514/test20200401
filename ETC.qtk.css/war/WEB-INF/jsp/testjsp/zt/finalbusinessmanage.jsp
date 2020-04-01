<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>

<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
<script type="text/javascript" src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" ></script>
<script type="text/javascript">
$(function(){
	$("#beforeDate").val("2020-01-01");
    $("#afterDate").val("2020-01-01");
})
</script>

</head>
<body>
<input type="hidden"  class="btn" id="showModel">
<div id="modalBtn" class="modal">
<div class="modal-content">
<header class="modal-header">
<h4>错误信息</h4> 
</header>
<div class="modal-bodys" style="width:200%;height:200%">
	
</div>
<footer>
	<input id="cancel" type="hidden" />
	<input id="sure" type="hidden" />
</footer>
</div>
</div>
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
                            <h4 class="panel-title">终端业务办理统计</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/card/cancelrefund/manage" method="POST">
								<input type='hidden' id='searchExportMark' name='searchExportMark' value=0 />
								<div class="form-group">
									<form:select path="cardId"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">贵阳地区</form:option>
									</form:select>
									<form:select path="cardId"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">毕节充值点</form:option>
									</form:select>
									<form:input path="cardId"  maxlength="100"  cssClass="form-control"  placeholder="请输入终端类型" />
									<div class="form-group m-t-5">
										<label class="control-label">办理日期</label>
										
										<div  class="input-group">
					 						<form:input cssStyle="width:150px"   path="beforeDate" readonly="true"  cssClass="form-control" />
										    <span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('beforeDate'),dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'afterDate\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
									<div class="form-group m-t-5">	
										<label class="control-label">至</label>
										<div  class="input-group">
						  					<form:input cssStyle="width:150px" path="afterDate"  readonly="true" cssClass="form-control"   />
											<span class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('afterDate'),dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beforeDate\')}'});"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
			 						</div>
									
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5 btn btn-primary btn-small" type="button" id="searchBtn"><i class="fa fa-search  m-r-10 "></i>查询</button>
                        	</form:form>
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>开户数</th>
										<th>obu数</th>
										<th>充值次数</th>
										<th>换卡数量</th>
										<th>换obu数量</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>1</th>
										<th>11</th>
										<th>14</th>
										<th>15</th>
										<th>13</th>
										<th>12</th>
									</tr>
									<tr>
										<th>2</th>
										<th>14</th>
										<th>54</th>
										<th>65</th>
										<th>23</th>
										<th>12</th>
									</tr>
									<tr>
										<th>3</th>
										<th>24</th>
										<th>24</th>
										<th>65</th>
										<th>13</th>
										<th>13</th>
									</tr>
									<tr>
										<th>4</th>
										<th>18</th>
										<th>64</th>
										<th>85</th>
										<th>29</th>
										<th>112</th>
									</tr>
									<tr>
										<th>5</th>
										<th>11</th>
										<th>14</th>
										<th>45</th>
										<th>39</th>
										<th>14</th>
									</tr>
									<tr>
										<th>6</th>
										<th>13</th>
										<th>24</th>
										<th>45</th>
										<th>49</th>
										<th>16</th>
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