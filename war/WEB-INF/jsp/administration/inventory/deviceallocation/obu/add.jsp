<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function() {
		$.ajaxSetup ({ cache: false});
		$('.modal-footer').taiji();
		$("#submit").click(function() {
			$("#myManage").triggerHandler("taijiModalPost", [ $("#addForm"), {
				table : "add"
			} ]);
		});
		 $("#b").keyup(function(){
				jisuan(this);
			});
		$("#a").keyup(function(){
				jisuan(this);
			}); 
		 $("#endId").keyup(function(){
				jisuan(this);
			}); 
	});
	function setServiceHallValue(id, name) {
		$("#outServiceHall\\.serviceHallId").val(id);
		$("#outServiceHall\\.name").val(name);
	}
	function setServiceHallValue1(id, name) {
		$("#inServiceHall\\.serviceHallId").val(id);
		$("#inServiceHall\\.name").val(name);
	}
	/*  function jisuan(obj) { 
		   obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”和“.”以外的字符  
		   var maxCount = 10000;
		    var outBoundNum =  $("#a").val().toString();
		    var length_inboundNum = outBoundNum.length;
			var startId = $("#b").val().toString(); 
			var length_startId = startId.length;
			if(outBoundNum>maxCount){
				 $.Taiji.showWarn('一次最多10000'); 
				 outBoundNum =$("#a").val(maxCount);
			}
			if(outBoundNum<=0){
				outBoundNum =$("#a").val("");
			}
			if(startId ==null||startId*1==0){
				$("#endId").val(); 
			}else{
				var tmp_front_startId = startId.substring(0,length_startId-length_inboundNum-2)
				var tmp_after_startId = startId.substring(length_startId-length_inboundNum-2,length_startId);
				var endId = tmp_front_startId+(tmp_after_startId*1+outBoundNum*1-1).toString(); 
				$("#endId").val(endId); 
			}
		}  */
		function jisuan(obj) { 
		    obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”和“.”以外的字符  
		   	var maxCount = 10000;
		    var outBoundNum =  $("#a").val().toString();
			if(outBoundNum>maxCount){
				 $.Taiji.showWarn('一次最多10000'); 
				 outBoundNum =$("#a").val(maxCount);
			}
			if(outBoundNum<=0||outBoundNum.value ==""){
				inBoundNum =$("#a").val("");
			}
			if(startId ==null||startId*1==0){
				$("#endId").val(""); 
			}
		} 
		$(":input[name='endId']").click(function() {
			 var outBoundNum =  $("#a").val();
			 var startId = $("#b").val();
			 var url = '${rootUrl }';
			if(outBoundNum == null || outBoundNum == "" || outBoundNum == "NaN"||startId == null || startId == "" || startId == "NaN"){
				$.Taiji.showWarn("请先输入起始编号和数量");
			}else{
				var data = {};
				data.startId = startId;
				data.outBoundNum = outBoundNum;
				$.ajax({
					url:url+"app/administration/inventory/deviceallocation/obu/adda",
					data:JSON.stringify(data),
					type:"POST",
					contentType : 'application/json',
			        dataType : 'json',
					success:function(response){
						console.log(response);
						if(response.endId != null && response.endId != undefined){
							var endId = response.endId;
							$("#endId").val(endId); 
							/* $.Taiji.showNote("通过！"); */
		            	}else{
			            	$.Taiji.showWarn(response.message+"失败！");
		            	}
					},
					error:function(error){
						console.log(error);
						$.Taiji.showWarn('卡起始编号错误！');
					}
				});
			}
		}); 
</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">新增电子标签设备调拨信息--添加</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="addForm" name="addForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/inventory/deviceallocation/obu/add"
			method="post">
			<div class="form-group">
				<label class="col-sm-2 control-label">业务员</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="userName" required="required"
							cssClass="form-control" placeholder="业务员" readonly="true"/>
					</div>
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="col-sm-2 control-label">调拨单编号</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="batchId" required="required"
							cssClass="form-control" placeholder="入库单编号必填" readonly="true" />
					</div>
				</div>
			</div> --%>
			<div class="form-group">
				<label class="col-sm-2 control-label">调出网点</label>
				<div class="col-sm-10">
					<div class="input-group" style="width: 280px">
						<form:hidden path="outServiceHall.serviceHallId" />
						<form:input path="outServiceHall.name" cssClass="form-control"
							placeholder="请选择用户所管理的网点（必填）" readonly="true"/>
						<!-- <input id="serviceHall" name="serviceHall" class="form-control" placeholder="请选择用户所管理的网点"/> -->
						<div class="input-group-btn">
							<%@ include
								file="/WEB-INF/jsp/administration/inventory/devicewarehousing/card/selectUnit.jsp"%>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">调入网点</label>
				<div class="col-sm-10">
					<div class="input-group" style="width: 280px">
						<form:hidden path="inServiceHall.serviceHallId" />
						<form:input path="inServiceHall.name" cssClass="form-control"
							placeholder="请选择用户所管理的网点（必填）" readonly="true"/>
						<!--  <input id="serviceHalls.name" name="serviceHalls" class="form-control" placeholder="请选择用户所管理的网点"/>   -->
						<div class="input-group-btn">
							<%@ include
								file="/WEB-INF/jsp/administration/inventory/deviceallocation/card/selectUnit.jsp"%>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">产品类型</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="model" required="required"
							cssClass="form-control" placeholder="产品类型必填" readonly="true" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">调拨数量</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="outboundNum" required="required" id="a"
							cssClass="form-control" placeholder="数量必填" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">调拨起始编号</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="startId" required="required" id="b"
							cssClass="form-control" placeholder="起始编号必填" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">调拨结束编号</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
					<input id="endId" name="endId" type="text" 
						readonly = "readonly"
						class="form-control" placeholder="结束编号必填" /> 
						<%-- <form:input path="endId" required="required"
							cssClass="form-control" placeholder="结束编号必填" /> --%>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<div class="modal-footer">
		<a id="submit" href="javaScript:void(0);"
			class="btn btn-primary btn-sm">保存</a>
		<!--  -->
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>