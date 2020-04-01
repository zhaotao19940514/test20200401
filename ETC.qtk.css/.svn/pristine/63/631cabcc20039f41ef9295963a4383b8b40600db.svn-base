<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
	<meta http-equiv="expires" content="0" />
	<meta http-equiv=
	"pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<script type="text/javascript">
		$(function(){
			$.ajaxSetup({
				cache : false
			});
			$("#submit").click(function(){
				var agencyId = $("#agencyId").val();
				var rowId=$("#rowId").val();
				var openNotify=$("#openNotify").val();
				var openObuNotify=$("#openObuNotify").val();
				var cardNotice=$("#cardNotice").val();
				var bankSignUrl=$("#bankSignUrl").val();
				var cardNfSwitch=$("#cardNfSwitch").val();
				var activeNfSwitch=$("#activeNfSwitch").val();
				var OBUNfSwitch=$("#OBUNfSwitch").val();
				var cardNoticeSwitch=$("#cardNoticeSwitch").val();
				var signNoticeSwitch=$("#signNoticeSwitch").val();
				 var data = {};
				  data.agencyId =agencyId ;
				  data.rowId =rowId ;
				  data.openNotify =openNotify ;
				  data.openObuNotify = openObuNotify;
				  data.cardNotice = cardNotice;
				  data.bankSignUrl = bankSignUrl;
				  data.cardNfSwitch = cardNfSwitch;
				  data.activeNfSwitch =activeNfSwitch ;
				  data.OBUNfSwitch =OBUNfSwitch ;
				  data.cardNoticeSwitch =cardNoticeSwitch ;
				  data.signNoticeSwitch=signNoticeSwitch;
				 $.ajax({
				      url : rootUrl+"app/administration/notice/noticeconfig/edit",
				      type : "POST",
				      data:JSON.stringify(data),
				      dataType : "json",
				      contentType: "application/json",
				      async:true,
				      success : function(responseText) {
				    	  if(responseText.status==1){
				    		  $.Taiji.showNote("修改成功");
				    	  }else{
				    		  $.Taiji.showWarn("修改失败");
				    	  }
				    	  $("#searchBtn").click();
				    	  $("#closeBtn").click();
				      },
				      error:function(responseText){
				      	$.Taiji.showWarn(responseText.message);
				      	$.Taiji.hideLoading();
				      }
		  		});
				
			})
		});
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">修改</h4>
</div>
<div class="modal-body">
<input id="rowId" type="hidden" value="${bankVersion.id }" />
<form:form modelAttribute="queryModel1" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label for='agencyId' class="control-label"><b>渠道号</b></label>
			</td>
			<td>
				<form:input path="agencyId" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" disabled="true" value='${bankVersion.agencyId}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='checkContract' class="control-label"><b>签约检测通知</b></label>
			</td>
			<td>
				<form:input path="checkContract" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" value='${bankVersion.checkContract}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='openNotify' class="control-label"><b>开卡通知</b></label>
			</td>
			<td>
				<form:input path="openNotify" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" value='${bankVersion.openNotify}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='openObuNotify' class="control-label"><b>开签通知</b></label>
			</td>
			<td>
				<form:input path="openObuNotify" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" value='${bankVersion.openObuNotify}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardNotice' class="control-label"><b>卡预注销通知</b></label>
			</td>
			<td>
				<form:input path="cardNotice" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" value='${bankVersion.cardNotice}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='bankSignUrl' class="control-label"><b>服务器地址</b></label>
			</td>
			<td>
				<form:input path="bankSignUrl" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" value='${bankVersion.bankSignUrl}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardNfSwitch' class="control-label"><b>开卡通知开关</b></label>
			</td>
			<td>
				<select id='cardNfSwitch' style='height:30px;width:100px'>
					<c:if test="${bankVersion.cardNfSwitch==0}">
						<option value=0>关</option>
						<option value=1>开</option>
					</c:if>
					<c:if test="${bankVersion.cardNfSwitch==1}">
						<option value=1>开</option>
						<option value=0>关</option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='activeNfSwitch' class="control-label"><b>激活通知开关</b></label>
			</td>
			<td>
				<select id='activeNfSwitch' style='height:30px;width:100px'>
					<c:if test="${bankVersion.activeNfSwitch==0}">
						<option value=0>关</option>
						<option value=1>开</option>
					</c:if>
					<c:if test="${bankVersion.activeNfSwitch==1}">
						<option value=1>开</option>
						<option value=0>关</option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='OBUNfSwitch' class="control-label"><b>签通知开关</b></label>
			</td>
			<td>
				<select id='OBUNfSwitch' style='height:30px;width:100px'>
					<c:if test="${bankVersion.OBUNfSwitch==0}">
						<option value=0>关</option>
						<option value=1>开</option>
					</c:if>
					<c:if test="${bankVersion.OBUNfSwitch==1}">
						<option value=1>开</option>
						<option value=0>关</option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardNoticeSwitch' class="control-label"><b>卡预注销通知开关</b></label>
			</td>
			<td>
				<select id='cardNoticeSwitch' style='height:30px;width:100px'>
					<c:if test="${bankVersion.cardNoticeSwitch==0}">
						<option value=0>关</option>
						<option value=1>开</option>
					</c:if>
					<c:if test="${bankVersion.cardNoticeSwitch==1}">
						<option value=1>开</option>
						<option value=0>关</option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='signNoticeSwitch' class="control-label"><b>签预注销通知开关</b></label>
			</td>
			<td>
				<select id='signNoticeSwitch' style='height:30px;width:100px'>
					<c:if test="${bankVersion.signNoticeSwitch==0}">
						<option value=0>关</option>
						<option value=1>开</option>
					</c:if>
					<c:if test="${bankVersion.signNoticeSwitch==1}">
						<option value=1>开</option>
						<option value=0>关</option>
					</c:if>
				</select>
			
			</td>
		</tr>
	</table>
</form:form>
	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>