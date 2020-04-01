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
				var agencyId = $("#agenId").val();
				var agencyName = $("#agencyName").val();
				var checkContract=$("#checkContract").val();
				var openNotify=$("#openNotify").val();
				var openObuNotify=$("#openObuNotify").val();
				var cardNotice=$("#cardNotice").val();
				var bankSignUrl=$("#bankSignUrl").val();
				var cardNfSwitch=$("#cardNfSwitch").val();
				var activeNfSwitch=$("#activeNfSwitch").val();
				var oBUNfSwitch=$("#oBUNfSwitch").val();
				var cardNoticeSwitch=$("#cardNoticeSwitch").val();
				var signNoticeSwitch=$("#signNoticeSwitch").val();
				var signNotice = $("#signNotice").val();
				var changeCard = $("#changeCard").val();
				 var data = {};
				  data.agencyId =agencyId ;
				  data.agencyName =agencyName ;
				  data.checkContract =checkContract ;
				  data.openNotify =openNotify ;
				  data.openObuNotify = openObuNotify;
				  data.cardNotice = cardNotice;
				  data.bankSignUrl = bankSignUrl;
				  data.cardNfSwitch = cardNfSwitch;
				  data.activeNfSwitch =activeNfSwitch ;
				  data.oBUNfSwitch =oBUNfSwitch ;
				  data.cardNoticeSwitch =cardNoticeSwitch ;
				  data.signNoticeSwitch=signNoticeSwitch;
				  data.changeCard = changeCard;
				  data.signNotice = signNotice;
				  debugger;
				 $.ajax({
				      url : rootUrl+"app/administration/notice/noticeconfig/add",
				      type : "POST",
				      data:JSON.stringify(data),
				      dataType : "json",
				      contentType: "application/json",
				      async:true,
				      success : function(responseText) {
				    	  if(responseText.status==1){
				    		  $.Taiji.showNote("添加成功");
				    	  }else{
				    		  $.Taiji.showWarn(responseText.message);
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
	<h4 class="modal-title">添加</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="queryModel" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<label for='agenId' class="control-label"><b>渠道号</b></label>
			</td>
			<td>
				<form:input path="agencyId" id='agenId' cssClass="form-control  m-r-5" required="required"  htmlEscape="false" />
			</td>
		</tr>
		<tr>
			<td>
				<label for='agencyName' class="control-label"><b>渠道名称</b></label>
			</td>
			<td>
				<form:input path="agencyName" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" />
			</td>
		</tr>
		<tr>
			<td>
				<label for='checkContract' class="control-label"><b>签约检测通知</b></label>
			</td>
			<td>
				<form:input path="checkContract" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" />
			</td>
		</tr>
		<tr>
			<td>
				<label for='openNotify' class="control-label"><b>开卡通知</b></label>
			</td>
			<td>
				<form:input path="openNotify" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" />
			</td>
		</tr>
		<tr>
			<td>
				<label for='openObuNotify' class="control-label"><b>开签通知</b></label>
			</td>
			<td>
				<form:input path="openObuNotify" cssClass="form-control  m-r-5" required="required"  htmlEscape="false"  />
			</td>
		</tr>
		<tr>
			<td>
				<label for='changeCard' class="control-label"><b>换卡通知</b></label>
			</td>
			<td>
				<form:input path="changeCard" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" />
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardNotice' class="control-label"><b>卡预注销通知</b></label>
			</td>
			<td>
				<form:input path="cardNotice" cssClass="form-control  m-r-5" required="required"  htmlEscape="false"  />
			</td>
		</tr>
		<tr>
			<td>
				<label for='signNotice' class="control-label"><b>签预注销通知</b></label>
			</td>
			<td>
				<form:input path="signNotice" cssClass="form-control  m-r-5" required="required"  htmlEscape="false"  />
			</td>
		</tr>
		<tr>
			<td>
				<label for='bankSignUrl' class="control-label"><b>服务器地址</b></label>
			</td>
			<td>
				<form:input path="bankSignUrl" cssClass="form-control  m-r-5" required="required"  htmlEscape="false" />
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardNfSwitch' class="control-label"><b>开卡通知开关</b></label>
			</td>
			<td>
				<select id='cardNfSwitch' style='height:30px;width:100px'>
						<option value=0>关</option>
						<option value=1>开</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='activeNfSwitch' class="control-label"><b>激活通知开关</b></label>
			</td>
			<td>
				<select id='activeNfSwitch' style='height:30px;width:100px'>
					<option value=0>关</option>
					<option value=1>开</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='oBUNfSwitch' class="control-label"><b>签通知开关</b></label>
			</td>
			<td>
				<select id='oBUNfSwitch' style='height:30px;width:100px'>
					<option value=0>关</option>
					<option value=1>开</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='cardNoticeSwitch' class="control-label"><b>卡预注销通知开关</b></label>
			</td>
			<td>
				<select id='cardNoticeSwitch' style='height:30px;width:100px'>
					<option value=0>关</option>
					<option value=1>开</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for='signNoticeSwitch' class="control-label"><b>签预注销通知开关</b></label>
			</td>
			<td>
				<select id='signNoticeSwitch' style='height:30px;width:100px'>
					<option value=0>关</option>
					<option value=1>开</option>
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