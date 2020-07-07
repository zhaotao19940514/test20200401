<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
<%@include file="/WEB-INF/jsp/deviceInclude/posDeviceHeader.jsp"%>
<script type="text/javascript">
'user strict';
$(function(){
// 	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search : {
			autoSearch : false
		}
	});/* .on("taijiSearchSuccess",function(){
		$(".exchangeBtn").click(function(){
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			executeTimes=0;
			reader.openObuDev();
	        var oldInfo = reader.readFullSysInfo();
	        var data = {}; 
	        data.oldInfo = oldInfo;
	        var newObuId = reader.readContractSerialNo();
	        var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",data,false,function(){
	        	reader.closeObuDev();
	        	var type = 1;
	            doApplyObu(type,oldObuId);
	            $.Taiji.hideLoading();
	        });
		});
	}); */
	obuStatus = 5;
	$("#readObuBtn").click(function(){
		var reader = new ObuOfflineReader();
		reader.openObuDev();
// 		reader.readSysInfo();
		var oldObuId = reader.readContractSerialNo();
		if(oldObuId == undefined || oldObuId == null || oldObuId == ''){
			$.Taiji.showWarn("未读到电子标签信息");
			return;
		}
		$("#obuId").val(oldObuId);
		$("#listForm").submit();
		if(null!=oldObuId&&oldObuId!=''&&oldObuId!='undefined'){
			obuStatus = 4;
		}
		reader.closeObuDev();
	});
	$('#my-table').on('click','#exchangeOff',function(){
		var param=$(this).attr("param");
		if(param=='0'||param=='11'){
			$.Taiji.showWarn("请先进行离线安装");
		}else if(param=='4'||param=='5'){
			$.Taiji.showWarn("该OBU已注销");
		}
	});
	$("#searchBtn").click(function(){
		obuStatus = 5;
	});
});
/*
 * 注销
 * obuStatus:点击读签按钮为有签注销:4  否则为无签:5
 */
function doObuCancel(oldObuId,obuStatus,vehiclePlate,vehColor){
	 $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
	var supReason = $("#supReason").val();
	var data = {};
		data.oldObuId = oldObuId; 
		data.obuStatus = obuStatus,
		data.obuId = $("#newObuId").val();
		data.supReason = supReason;
		data.newObuId=$("#newObuId").val(),
	$.ajax({
		url:rootUrl+"app/customerservice/obu/exchange/OBUExchange",
		data:JSON.stringify(data),
		type:"POST",
		contentType : 'application/json',
           dataType : 'json',
		success:function(response){
					if(response.status==1){
					doApplyObu(oldObuId,vehiclePlate,vehColor);
				}else{
					$.Taiji.hideLoading();
					$("#listForm").submit();
					$.Taiji.showWarn(response.message);
				}
			
		},
		error:function(error){
			$.Taiji.hideLoading();
			$.Taiji.showWarn('OBU注销失败！请联系管理员');
		}
	});
	
}
//发行新OBU
function doApplyObu(oldObuId,vehiclePlate,vehColor){
	var newObuId = $("#newObuId").val();
	var data = {
				oldObuId:oldObuId,
				obuId:$("#newObuId").val(),
				applyOrChange:true,
				type:1
			   };
	$.ajax({
		url:rootUrl+"app/apply/quickapply/equipmentissue/obuApplyAndConfirm",
		data:JSON.stringify(data),
		type:"POST",
		contentType : 'application/json',
           dataType : 'json',
		success:function(response){
			 if(response.status==1){
				doApplyObu2(oldObuId,vehiclePlate,vehColor);
			}else{
				$.Taiji.hideLoading();
				$("#listForm").submit();
				$.Taiji.showWarn(response.message);
				}
			  
		},
		error:function(error){
			$.Taiji.hideLoading();
			$.Taiji.showWarn(error.message);
		}
	});
}
function doApplyObu2(oldObuId,vehiclePlate,vehColor){
	var newObuId = $("#newObuId").val();
	var data = {
				oldObuId:oldObuId,
				obuId:newObuId,
				applyOrChange:true,
				type:2
			   };
	$.ajax({
		url:rootUrl+"app/apply/quickapply/equipmentissue/obuApplyAndConfirm",
		data:JSON.stringify(data),
		type:"POST",
		contentType : 'application/json',
           dataType : 'json',
		success:function(response){
			$.Taiji.hideLoading();
			console.log(response);
			if(response.code=="SUCCESS"){
				writeObuSysInfo(vehiclePlate,vehColor);
			}else{
				reader.closeObuDev();
				$.Taiji.showWarn(response.message);
				$.Taiji.hideLoading();
			}
		},
		error:function(error){
			$.Taiji.hideLoading();
			$.Taiji.showWarn(error.message);
		}
	});
}

//写系统信息
function writeObuSysInfo(vehiclePlate,vehColor){
	console.log("进系统信息了......");
	var reader = new ObuOfflineReader();
	var vehicleId = $(this).attr("param");
	var oldInfo = $("#oldInfo").val();
	/* var veColor = $("#vecolor").val();
	var vehicleNum = $("#vehicleNum").val(); */
	var newObuId = $("#newObuId").val();
	//var newObuId = reader.readContractSerialNo();
    var sysData = {}; 
    sysData.oldInfo = oldInfo;
    sysData.newObuId = newObuId;
    sysData.plateNum = vehiclePlate;
    sysData.plateColor =vehColor;
    reader.openObuDev();
    var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",sysData,false,function(){
    	console.log("成功了。。。");
        vehInfoByObuId(reader);
    });
	
}
//写车辆信息
function vehInfoByObuId(reader){
	var supReason = $("#supReason").val();
	var obuId = reader.readContractSerialNo();
	var data = {
            obuId:obuId
    };
    $.ajax({
        url:rootUrl+"app/customerservice/obu/rewrite/vehInfoByObuId",
        data:JSON.stringify(data),
        type:"POST",
        contentType : 'application/json',
        dataType : 'json',
        success:function(response){
                 var oldInfo = reader.readFullVehicleInfo();
                 var data = {};
                 data.oldInfo = oldInfo;
                 data.plateNum = response.vehiclePlate;
                 data.plateColor = response.vehiclePlateColor;
                 data.vehicleType = response.type;
                 data.userType = 1;
                 if(null!=response.customerInfo.customerType){
                 	data.userType = response.customerInfo.customerType;
                 }
                 var outside =response.outsideDimensions.split("X");
                 data.vehicleLength =outside[0];
                 data.vehicleWidth = outside[1];
                 data.vehicleHeight = outside[2]; 
                 data.wheelsCount = response.wheelCount;
                 data.axleCount = response.axleCount;
                 data.wheelBase = response.axleDistance;
                 if(response.vehicleType==1){
                     data.loadOrSeat = response.approvedCount;
                 }else{
                     data.loadOrSeat = response.permittedWeight;
                 }
                 data.vehicleFeature = response.vehicleModel; 
                 data.engineNum = response.engineNum;
                 var url = '${rootUrl}'+"app/ocx/obu/vehicleinfo";
                 reader.writeVehicleInfoWithAjax(url,data,false,function(){
                		 reader.closeObuDev();
                		 $.Taiji.hideLoading();
         				 $("#listForm").submit();
         				 debugger;
         				 $.Taiji.showNote("成功");
                		 $("#closeBtn").click();
                	 
                 });
        },
        error:function(error){
        }
    });
}



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
                            <h4 class="panel-title">OBU更换</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/obu/exchange/manage" method="post">
								<div class="form-group">
									<form:input path="obuId"  maxlength="100"  cssClass="form-control"  placeholder="电子标签序列号" />
									<form:input path="vehicleId"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:select path="vehicleColor"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">--请选择车牌颜色--</form:option>
										<form:options items="${vehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
									</form:select>
								</div>
								<button class=" btn btn-md btn-default btn btn-success btn-small" id="readObuBtn" type="button"><i class="fa  fa-credit-card   m-r-10  "></i>读电子标签</button>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" id="searchBtn" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
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
										<th>电子标签序列号</th>
										<th>电子标签状态</th>
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
</html>