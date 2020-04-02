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
		//页面加载完成隐藏单位证件类型
		/* $(".obuType1").each(function () {
	      if(!$(this).parent().is('span')) {
	          $(this).wrap("<span style='display:none'></span>");
	      }
	   });
		
		$("#customerType1").change(function() {
			var type = $(this).val();
			if(type==1||type==2){
				$(".cardType1").each(function(){
	           	 if($(this).parent().is('span')){
	                 $(this).unwrap();
	             }
	       		 });
				$(".obuType1").each(function () {
	                if(!$(this).parent().is('span')) {
	                    $(this).wrap("<span style='display:none'></span>");
	                }
	            });
			}
			if(type==3){
				$(".obuType1").each(function(){
	           	 if($(this).parent().is('span')){
	                 $(this).unwrap();
	             }
	      		});
				$(".cardType1").each(function () {
	                if(!$(this).parent().is('span')) {
	                    $(this).wrap("<span style='display:none'></span>");
	                }
	            });
			}
		}); */
		$(".obuType1").hide();
		$("#customerType1").change(function() {
			var type = $(this).val();
			if(type==1||type==2){
				$(".cardType1").show();
				$(".obuType1").hide();
			}
			if(type==3){
				$(".obuType1").show();
				$(".cardType1").hide();
			}
		});
	});
</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">黔通卡设备型号管理--添加</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="addForm" name="addForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/inventory/devicemodel/card/add"
			method="post">
				<%-- <div class="form-group m-3">
										<form:label path="type">产品类型</form:label>
										<form:select path="type" cssClass="form-control m-r-5"
											id="customerType" data-style="btn-white" data-width="150px">
											<form:options items="${equipmentType}" itemLabel="value"
												itemValue="code" />
										</form:select>
										<form:label path="brand">品牌</form:label>
										<select id="idType" name="customerIdType" class="form-control"
											data-style="btn-white" data-width="180px">
											<option value="">--请选择--</option>
											<c:forEach items="${cardType}" var='it'>
												<option value="${it.code}" class='cardType'>${it.value}</option>
											</c:forEach>
											<c:forEach items="${obuType}" var='ut'>
												<option class='obuType' value="${ut.code}">${ut.value}</option>
											</c:forEach>
										</select>
									</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">型号</label>
								<div class="col-sm-6">
									<div class="input-group" style="width: 280px">
										<form:input path="model" required="required"
											cssClass="form-control"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">备注</label>
								<div class="col-sm-6">
									<div class="input-group" style="width: 280px">
										<form:input path="remarks" required="required"
											cssClass="form-control" placeholder="如有非列举卡、标签品牌需先向部路网中心报备" />
									</div>
								</div>
							</div>
							<div class="form-group m-3">
										<form:label path="model">型号</form:label>
										<form:input path="model" maxlength="50"
											cssClass="form-control" placeholder="型号必填" />
										<form:label path="remarks">备注</form:label>
										<form:input path="remarks" maxlength="50" cssClass="form-control"
											placeholder="如有非列举卡、标签品牌需先向部路网中心报备" />
									</div> --%>
			<table class="table table-bordered table-striped">
				<tr>
					<td><form:label path="type">产品类型</form:label></td>
					<td><form:select path="type" cssClass="form-control m-r-5"
							id="customerType1" data-style="btn-white" data-width="150px">
							<form:options items="${equipmentType}" itemLabel="value"
								itemValue="code" />
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="brand">品牌</form:label></td>
					<td><form:select path="brand" id="idType" name="customerIdType"
						class="form-control" data-style="btn-white" data-width="180px">
							<option value="">--请选择--</option>
							<c:forEach items="${cardType}" var='it'>
								<option value="${it.code}" class='cardType1'>${it.value}</option>
							</c:forEach>
							<c:forEach items="${obuType}" var='ut'>
								<option class='obuType1' value="${ut.code}">${ut.value}</option>
							</c:forEach>
					</form:select></td>
				</tr>
				<tr>
					<td><form:label path="model">型号</form:label></td>
					<td><form:input path="model" maxlength="50"
							cssClass="form-control" placeholder="电子标签型号必填（卡型号非必填）" /></td>
				</tr>
				<tr>
					<td><form:label path="remarks">备注</form:label></td>
					<td><form:input path="remarks" maxlength="50"
							cssClass="form-control" placeholder="如有非列举卡、标签品牌需先向部路网中心报备" /></td>
				</tr>
			</table>
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