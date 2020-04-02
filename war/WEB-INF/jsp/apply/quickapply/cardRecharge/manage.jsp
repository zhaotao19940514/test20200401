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
		enableAclCheck:true,
		search:{
			 autoSearch:false,
			 autoRefresh : {
				    enable : false,
				    interval : 10000
				   }
		}
		
	}).editable({
		selector:".editable",//元素选择器，对目标元素启用编辑
		url:"editGrid",//编辑提交的url
	    success: editGridSuccess//服务器返回成功的回调方法
	}).on("taijiSearchSuccess",function(event,result){
		 
	}).on("taijiEditSuccess",function(event,result){
		
	});
	
	$("#addBtn").click(function(){
		$(this).showModal({size:"modal-lg",backdrop:false,data:{customerName:"sample"}});
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
var clicktag = 0;
function manage(chargeId){
	if (clicktag == 0) {
		 clicktag = 1;
	var r =confirm("确定重新上传吗") ;
	if(r==true){
	/* document.write("您按了确认"); */
	document.getElementById(chargeId).value="新按钮";
	$.ajax({
		   url : "${rootUrl }app/tbs/recharge/edit1/"+chargeId,   
		   data : '',
		   type : 'POST',
		   dataType : 'json',
		   success : function(data) {
		   var message =data.message;     //返回结果data */   
		/*  var zt = document.getElementById(chargeId);
		    if (zt.style.display != "none"){
		    	zt.setAttribute("style","display:none"); 
 				 

			 }else{ 
			}   */
		    },
		    error : function(data) {  //错误的结果
		    }
		  
		 });
	
	}else{
		
	}
	 setTimeout(function () { clicktag = 0 }, 10000);}
};

 
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
                            <h4 class="panel-title">卡片圈存</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/apply/quickapply/cardRecharge/manage" method="post">
                      			<br>
								<div class="form-group m-5">
									<form:input path="cardId" size="25" maxlength="50" placeholder="黔通卡号"  cssClass="form-control" readonly="true"/>
								</div> 
								<button class="taiji_search_reset btn btn-md btn-success m-r-5" type="button"><i class="fa  fa  m-r-10  "></i>读卡</button> 
								<div class="form-group m-5">
									<form:input path="balance" size="25" maxlength="50" placeholder="能圈存的金额:(元)"  cssClass="form-control" readonly="true"/>
								</div> 
								<div class="form-group m-5">
									<form:input path="balance" size="25" maxlength="50" placeholder="充值金额"  cssClass="form-control"/>
								</div>
								<button class="taiji_search_reset btn btn-md btn-success m-r-5" type="button"><i class="fa  fa  m-r-10  "></i>圈存</button> 
                        	</form:form>
						</div>
						<div   class="taiji_table_float table-responsive">
							<table class="table table-striped table-bordered  table-hover">
								<thead>
									<tr> 
													<th>黔通卡号</th>
													<th>充值前余额(元)</th>
													<th>充值金额(元)</th>
													<th>充值后余额(元)</th>
													<th>充值时间</th>
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
	
<%-- <%
	HttpSession sess = request.getSession();
	String message = (String)sess.getAttribute("mes");
	if(message == ""){
		 
		%>
		<%	
	}else{
		%>
		<script type="text/javascript">
		alert("<%=message%>");
		</script>
		<%
		sess.setAttribute("mes","");
	}
 %> --%>

</body>
</html>
