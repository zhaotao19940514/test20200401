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
		$("#serviceHall\\.id").val(id);
		$("#serviceHall\\.name").val(name);
	}
	
	 function jisuan(obj) { 
 		   obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”和“.”以外的字符  
 		    var maxCount = 10000;
		    var inboundNum =  $("#a").val().toString();
			if(inboundNum>maxCount){
				 $.Taiji.showWarn('一次最多10000'); 
				 inboundNum =$("#a").val(maxCount);
			}
			if(inboundNum<=0){
				inboundNum =$("#a").val("");
			}
			if(startId ==null||startId*1==0){
				$("#endId").val(); 
			}
		} 
	 $(":input[name='endId']").click(function(){
		 var inboundNum = $("#a").val();
		 var startId = $("#b").val();
		 var url = '${rootUrl }';
		 if(inboundNum == null || inboundNum == "" || inboundNum == "NaN"||startId == null || startId == "" || startId == "NaN"){
				$.Taiji.showWarn("请先输入起始编号和数量");
			}else{
				var data = {};
				data.startId = startId;
				data.inboundNum = inboundNum;
				$.ajax({
					url:url+"app/administration/inventory/devicewarehousing/card/adda",
					data:JSON.stringify(data),
					type:"POST",
					contentType : 'application/json',
					dataType : 'json',
					success:function(response){
						console.log(response);
						if(response.endId != null && response.endId != undefined){
							var endId = response.endId;
							$("#endId").val(endId); 
							/* $.Taiji.showNote("通过！");  */
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
		<h4 class="modal-title">新增设备入库信息--添加</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="addForm" name="addForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/inventory/devicewarehousing/card/add"
			method="post">
			<%-- <div class="form-group">
				<label class="col-sm-2 control-label">网点编号</label>
				<div class="col-sm-10">
					<form:input path="serviceHallId" cssClass="form-control"
						placeholder="网点编号必填" />
				</div>
			</div> --%>
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
				<label class="col-sm-2 control-label">入库单编号</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="batchId" required="required"
							cssClass="form-control" placeholder="入库单编号必填" readonly="true" />
					</div>
				</div>
			</div> --%>
			<%-- <div class="form-group">
				<label class="col-sm-2 control-label">所属网点</label>
				<div class="col-sm-10">
					<div class="input-group" style="width: 280px">
						<form:hidden path="serviceHall.id" />
						<form:input path="serviceHall.name" cssClass="form-control"
							placeholder="请选择用户所管理的网点（必填）" readonly="true"/>
						<!-- <input id="serviceHall" name="serviceHall" class="form-control" placeholder="请选择用户所管理的网点"/> -->
						<div class="input-group-btn">
							 <%@ include
								file="/WEB-INF/jsp/administration/inventory/devicewarehousing/card/selectUnit.jsp"%>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="form-group">
				<label class="col-sm-2 control-label">入库网点</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="serviceHallName" required="required"
							cssClass="form-control" placeholder="入库网点必填" readonly="true" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">型号</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="model" required="required"
							cssClass="form-control" placeholder="产品类型必填" readonly="true" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">品牌</label>
				<div class="col-sm-6">
					<form:select path="brand" class="form-control">
						<form:option value="">请选择</form:option>
						<form:option value="1">捷德</form:option>
						<form:option value="2">握奇</form:option>
						<form:option value="3">天喻</form:option>
						<form:option value="4">明华</form:option>
						<form:option value="5">楚天龙</form:option>
						<form:option value="6">金邦达</form:option>
						<form:option value="7">航天信息</form:option>
						<form:option value="8">鸿博</form:option>
						<form:option value="9">恒宝</form:option>
						<form:option value="10">复旦</form:option>
						<form:option value="11">精工伟达</form:option>
						<form:option value="12">华大智宝</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">产品类型</label>
				<div class="col-sm-6">
					<form:select path="type" class="form-control">
						<option value=211>储值卡</option>
						<option value=111>记账卡</option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">数量</label>
				<div class="col-sm-6">
					<div  class="input-group" style="width: 280px">
 						<form:input path="inboundNum" required="required" id="a" 
							cssClass="form-control" placeholder="数量必填" /> 
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">起始编号</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<form:input path="startId" required="required" id="b" 
							cssClass="form-control" placeholder="起始编号必填" /> 
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">结束编号</label>
				<div class="col-sm-6">
					<div class="input-group" style="width: 280px">
						<input id="endId" name="endId" type="text" 
						class="form-control" placeholder="结束编号必填" readonly="true" /> 
					</div>
				</div>
			</div>
			<div id="confForm" class="confForm"
				style="display: none; position: absolute;">
				<ul id="tree" class="ztree"
					style="margin-top: 0; width: 180px; height: 300px;"></ul>
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