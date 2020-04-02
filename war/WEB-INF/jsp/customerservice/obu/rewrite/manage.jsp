<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<%@ include file="/WEB-INF/jsp/deviceInclude/obuDeviceHeader.jsp"%>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({cache : false});
	$("#myManage").taiji({
		enableAclCheck:true,
		search : {
            autoSearch : false
        }
	});
		$("#my-table").on('click','#rewriteBtn1',function(){
			 $.Taiji.showWarn("OBU状态为正常才能重写");
		});
		$("#my-table").on('click','#rewriteBtn',function(){
			var reader = new ObuOfflineReader();
			reader.openObuDev();
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			var oldObuId = $(this).attr("param");
			var vehicleNum = $(this).attr("vehicleNum");
			var vecolor = $(this).attr("vecolor");
			var oldInfo = $("#oldInfo").val();
			var newObuId = reader.readContractSerialNo();
			if(isNaN(newObuId)){
				$.Taiji.showWarn("请先读OBU");
				return;
			}
			if(oldObuId!=newObuId){
				$.Taiji.showWarn("读取的OBU序列号与查询的序列号不一致,请重新查询");
				return;
			}
			vehicleInfoCheck(oldObuId,vehicleNum,vecolor);
            /* var sysData = {}; 
            sysData.oldInfo = oldInfo;
            sysData.newObuId = newObuId;
            sysData.plateNum = vehicleNum;
            sysData.plateColor =vecolor;
            var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",sysData,false,function(){
                vehInfoByObuId(reader);
            }); */
            
            /* var vehData = {
            		
            		
            };
            reader.writeVehicleInfoWithAjax (url,data,function(){
            	reader.closeObuDev();
            }); */
		});
});	

function writeSystemInfo(vehicleNum,vecolor){
	var reader = new ObuOfflineReader();
	reader.openObuDev();
	var newObuId = reader.readContractSerialNo();
	var oldInfo = $("#oldInfo").val();
	var sysData = {}; 
    sysData.oldInfo = oldInfo;
    sysData.newObuId = newObuId;
    sysData.plateNum = vehicleNum;
    sysData.plateColor =vecolor;
    var result = reader.writeSystemInfoWithAjax('${rootUrl}'+"app/ocx/obu/sysinfo",sysData,false,function(){
        vehInfoByObuId(reader);
    });
}
function vehicleInfoCheck(oldObuId,vehicleNum,vecolor){
	$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
	var data={};
	data.oldObuId = oldObuId;
	$.ajax({
		url:rootUrl+"app/customerservice/obu/rewrite/vehicleInfoCheck",
		data:JSON.stringify(data),
		type:"POST",
		contentType : 'application/json',
        dataType : 'json',
		success:function(response){
				if(response.status!=0){
					writeSystemInfo(vehicleNum,vecolor);
				}else{
					$.Taiji.hideLoading();
					$.Taiji.showWarn(response.message);
				}
		},
		error:function(error){
			$.Taiji.hideLoading();
			$.Taiji.showWarn('OBU重写失败！');
		}
	});
}

function vehInfoByObuId(reader){
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
                 if(null!=response.outsideDimensions&&''!=response.outsideDimensions){
                	 var outside =response.outsideDimensions.split("X");
                     data.vehicleLength = mmToDm(outside[0]);
                     data.vehicleWidth = mmToDm(outside[1]);
                     data.vehicleHeight = mmToDm(outside[2]); 
                 }else{
                	 data.vehicleLength = 0;
                     data.vehicleWidth = 0;
                     data.vehicleHeight = 0; 
                 }
                 if(null!=response.wheelCount&&''!=response.wheelCount){
                	 data.wheelsCount = response.wheelCount;
                 }else{
                	 data.wheelsCount = 0;
                 }
                 if(null!=response.axleCount&&''!=response.axleCount){
                	 data.axleCount = response.axleCount;
                 }else{
                	 data.axleCount = 0;
                 }
                 if(null!=response.axleDistance&&''!=response.axleDistance){
                	 data.wheelBase = response.axleDistance;
                 }else{
                	 data.wheelBase = 0;
                 }
                 if(response.type==1 || response.type==2 || response.type==3 || response.type==4){
                     data.loadOrSeat = response.approvedCount;
                 }else{
                     data.loadOrSeat = response.permittedWeight;
                 }
                 if(null!=response.vehicleModel&&''!=response.vehicleModel){
                	 data.vehicleFeature = response.vehicleModel; 
                 }else{
                	 data.vehicleFeature = 0;
                 }
                 if(null!=response.engineNum&&''!=response.engineNum){
                	 data.engineNum = response.engineNum;
                 }else{
                	 data.engineNum = 0;
                 }
                 
                 var url = '${rootUrl}'+"app/ocx/obu/vehicleinfo";
                 reader.writeVehicleInfoWithAjax(url,data,false,function(){
                	 reader.closeObuDev();
                	 $.Taiji.hideLoading();
                	 var clickType = $("#clickType").val();
                	 if(clickType=='1'){
                		 $("#closeBtn").click();
                		 $("#listForm").submit();
                	 }
                	 $.Taiji.showNote("成功");
                 });
                 
                 
            
        },
        error:function(error){
        }
    });
    
};
function mmToDm(varMm) {
	return Math.round(varMm/100);
}
</script>

</head>
<body>
<script type="text/javascript">
	$(function(){
		$("#readObuBtn").click(function(){
			var reader = new ObuOfflineReader();
			reader.openObuDev();
			var oldInfo = reader.readSysInfo();
			var obuId = reader.readContractSerialNo();
			if(isNaN(obuId)){
                $.Taiji.showWarn("未读到电子标签信息");
                return;
            }
			$("#oldInfo").val(oldInfo);
			$("#obuId").val(obuId);
			$("#listForm").submit();
// 			console.log(reader.readFullVehicleInfo());
			reader.closeObuDev();
		});
	})
</script>
<input type="hidden" id="oldInfo" value='0'/>
<input type="hidden" id="clickType"/>
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
		<!-- end #sidebar -->
		<input type="text" id="vehId" />
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
                            <h4 class="panel-title">OBU重写</h4>
                        </div>
                        <div class="panel-body">
                        	<form:form modelAttribute="queryModel" cssClass="taiji_search_form form-inline m-t-5 " id="listForm" name="listForm" action="${rootUrl }app/customerservice/obu/rewrite/manage" method="post">
								<div class="form-group">
									<form:input path="obuId"  maxlength="100"  cssClass="form-control"  placeholder="电子标签序列号" />
									<form:input path="vehicleId"  maxlength="100"  cssClass="form-control"  placeholder="车牌号码" />
									<form:select path="vehicleColor"  cssClass="form-control  m-r-5" data-style="btn-white" data-width="160px">
										<form:option value="">--请选择车牌颜色--</form:option>
										<form:options items="${vehiclePlateColorType}" itemLabel="value" itemValue="typeCode"/>
									</form:select>
								</div>
								<button class="btn btn-md btn-default btn-md btn-success m-r-5" id="readObuBtn" type="button"><i class="fa  fa-credit-card   m-r-10  "></i>读电子标签</button>
								<!-- <button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>  -->
								<button class="taiji_search_reset btn btn-md btn-default btn btn-primary btn-small " type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
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