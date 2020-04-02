<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false
		} 
	});
	$("#preActive").click(function(){
		reader = new ObuOfflineReader();
		reader.openObuDev();
		var obuId = reader.readContractSerialNo();
		if(isNaN(obuId)){
			$.Taiji.showWarn("未读到电子标签信息");
			return;
		}
		var o=reader.preActive();
		console.log(o);
		reader.closeObuDev();
		var data={
				obuId:obuId
			};
		$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
		$.ajax({
	        url : rootUrl+"app/customerservice/obu/preactive/doPreactive",
	        type : "POST",
	        dataType : "json",
	        data:JSON.stringify(data),
	        contentType: "application/json", 	 	 	
	        async:true,
	        success : function(responseText) {
	        	$.Taiji.hideLoading();
	        	if(responseText.status==1){
	         	  	 $.Taiji.showNote(responseText.message);
	        	}else{
	        		 $.Taiji.showWarn(responseText.message);
	        	}
	        },
			error:function(responseText){
				$.Taiji.hideLoading();
				 $.Taiji.showWarn(responseText.message);
			}
	    });
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
			<table id="my-table" class="table table-bordered  table-hover">
				<thead>
					<tr>
						<td align="center" height=60 " valign="middle">
							<div style="color:red;font-size:25px;text-align:left">
									　　只有支持预激活功能的OBU才能进行预激活操作，在发卡发OBU成功之后，直接把OBU放在发行设备上点预激活按钮，
									    验证是否预激活成功就是把相应的卡插进已进行预激活的OBU里，会显示“已预激活”字样，就OK了 
							</div>
						</td>
					</tr>
				
				
				</thead>
			</table>
			
			<ol class="breadcrumb pull-right">
			</ol>
			<button class="btn btn-md btn-success m-r-5" type="button" id="preActive"><i class="fa fa-creative-commons-sa  m-r-10 "></i>预激活</button>
			
		</div>
		<!-- end #content -->
		
					
	</div>
	<!-- end page container -->

</body>
</html>