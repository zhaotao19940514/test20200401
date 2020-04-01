<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<link rel="stylesheet" href="${rootUrl }loader/loader.css" type="text/css"/>
<%@include file="/WEB-INF/jsp/deviceInclude/cardDeviceHeader.jsp"%>
 <script type="text/javascript">
		$(function(){
			var status=0;
			$.ajaxSetup({cache : false});
			$("#myManage").taiji({
				enableAclCheck:true,
				search:{
					autoSearch:false
				}
			});
		
			$("#readOldCard").click(function(){
				var cardReader = new CardReader();
				var card  = cardReader.getCardId();
				var cardBalance = cardReader.getBalance();
				 if(isNaN(card)){
					$.Taiji.showWarn("读卡器未读到卡片,请检查设备上是否有卡以及设备是否正常工作!");
					return;
				}
				$("#oldCardId").val(card);
				$("#oldPreBalance").val(cardBalance);
			});
			
			
			
		});
</script>



</head>
<body>
	<div style="display: none;"  id="loading" class="loading">正在处理,请稍候...</div>
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
                            <h4 class="panel-title">余额补领123</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl}app/customerservice/finance/balancesupply/manage" method="post">
								<div class="form-group">
									<form:label  path="oldCardId">旧卡卡号</form:label>
									<form:input id="oldCardId" path="oldCardId"  maxlength="100"  cssClass="form-control"   />
									<button id="readOldCard" class="btn btn-md btn-success " type="button"><i class="fa  fa-credit-card   m-r-10"></i>读旧卡</button>
									<form:input path="vehiclePlate"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:label path="vehiclePlateColor">车牌颜色：</form:label>
									<form:select path="vehiclePlateColor"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="100px">
										<form:option value="">--请选择--</form:option>
										<form:options items="${vehiclePlateColorTypes}" itemLabel="value" itemValue="typeCode"/>
									</form:select>
									<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询旧卡信息</button>
									<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>清空</button>
									<%-- <br>
									--%>
								</div>
								<div style="display: none">
									<form:input path="cardStatus"   cssClass="form-control"  placeholder="判断是否为读卡得到的旧卡信息" />
								</div>
                        	</form:form>
                       		 	 <div>
									<span>可补领金额</span>
									<span id="oldPreBalance"></span>
									<span style="color:#F00; font-weight:bold;  " >元</span>
								</div>
								<!--<div>
									<span>新卡余额</span>
									<span id="newPreBalance"></span>
									<span style="color:#F00; font-weight:bold; " >元</span>
								</div> -->
                        </div>
						<div   class="taiji_search_result table-responsive">
							<table id="my-table" class="table table-bordered  table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>客户名称</th>
										<th>证件类型</th>
										<th>证件号码</th>
										<th>车牌号码</th>
										<th>车牌颜色</th>
										<th>卡表面号</th>
										<th>卡片状态</th>
										<th>卡片类型</th>
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
</html>>