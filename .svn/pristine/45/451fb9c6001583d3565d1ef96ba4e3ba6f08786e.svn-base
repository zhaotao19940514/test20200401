<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function() {
		$.ajaxSetup({cache : false});
		$('.modal-body').taiji();
		$("#submit").click(function() {
			$("#myManage").triggerHandler("taijiModalPost", [ $("#editForm"), {
				table : "edit"
			} ]);
		});
		$("#obuSel").hide();
		 var cType='${pageModel.type}';
		 if(cType==1||cType==2){
			 $("#czCard").show();
			 $("#obuSel").hide();
		 }else{
			 $("#czCard").hide();
			 $("#obuSel").show();
		 }
		$("#type").change(function() {
			var type = $(this).val();
			if(type==1||type==2){
				$("#czCard").show();
				$("#obuSel").hide();
			}
			if(type==3){
				$("#czCard").hide();
				$("#obuSel").show();
			}
		});
	});
</script>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">黔通卡设备型号信息--修改</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="pageModel"
			id="editForm" name="editForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/inventory/devicemodel/card/edit"
			method="post">
			<!-- style="margin-left:200px;" -->
			<form:hidden path="id" />
			<table class="table table-bordered table-striped">
				<tr>
					<td><form:label path="type">产品类型</form:label></td>
					<td><form:select path="type" cssClass="form-control m-r-5"
							 data-style="btn-white" data-width="150px">
							<form:options items="${equipmentType}" itemLabel="value"
								itemValue="code" />
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="brand">品牌</form:label></td>
					<td id='czCard'>
					<form:select path="brand"
						class="form-control" data-style="btn-white" data-width="180px">
							 <c:forEach items='${cardType}' var='it'>
								<form:option value='${it.code}'>${it.value}</form:option>
							</c:forEach>
					</form:select>
					</td>
					<td id='obuSel'>
						<form:select path="brand"
							class="form-control" data-style="btn-white" data-width="180px">
								<c:forEach items='${obuType}' var='ut'>
									<form:option value='${ut.code}' class='unitIdType1'>${ut.value}</form:option>
								</c:forEach> 
						</form:select>
					</td>
					
				</tr>
				<tr>
					<td><form:label path="model">型号</form:label></td>
					<td><form:input path="model" maxlength="50"
							cssClass="form-control" placeholder="型号必填" /></td>
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
			class="btn btn-primary btn-sm">保存</a> <a href="#"
			class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>
