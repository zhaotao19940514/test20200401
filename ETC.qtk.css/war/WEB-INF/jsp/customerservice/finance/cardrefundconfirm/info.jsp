<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				subRefundInfo();
			});

		});
		function subRefundInfo(){
			var refundBalance = 0;
			var selPlay =$('#refundBalance').css('display');
			var textPlay =$('#selStr').css('display');
			if(selPlay=='none'){
				refundBalance = Math.round(parseFloat($("#refundSel").val()*100));
			}
			if(textPlay=='none'){
				refundBalance = Math.round(parseFloat($("#refundBalance").val()*100));
			}
			console.log("refundBalance:"+refundBalance);
			var refundCardId = $("#refundCardId").val();
			var data = {};
				data.refundCardId = refundCardId;
				data.balance = '${balance}';
				data.refundBalance = refundBalance;
			$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
			$.ajax({
			      url : rootUrl+"app/customerservice/finance/cardrefundconfirm/saveRefundInfo",
			      type : "POST",
			      dataType : "json",
			      data:JSON.stringify(data),
			      contentType: "application/json",
			      async:true,
			      success : function(responseText) {
			    	  $.Taiji.hideLoading();
			    	  if(responseText.status==1){
			    		  $.Taiji.showNote(responseText.message);
			    		  $("#searchBtn").click();
			    		  $("#closeBtn").click();
			    	  }else{
			    		  $.Taiji.showWarn(responseText.message);
			    	  }
			      },
			      error:function(responseText){
			      	$.Taiji.showWarn(responseText.message);
			      	$.Taiji.hideLoading();
			      }
	  		});
		}
		
		function onblurText(){
			var text = $("#refundBalance").val();
			if(''==text||text=='undefined'){
				$("#selStr").show();
			}
		} 
		 function onfocusText(){
			 $("#selStr").hide();
		} 
		 function textKeyUp(){
			 var text = $("#refundBalance").val();
			 if(''==text||text=='undefined'){
				 $("#selStr").show();
			 }
		 }
		 function refundSelChange(){
			 var val = $("#refundSel").val();
			 if(val!='qxz'){
					$("#refundBalance").hide();
				}else{
					$("#refundBalance").show();
				}
		 }
		function setUnitValue(id,name){
			$("#unit\\.id").val(id);
			$("#unit\\.name").val(name);
		}
		</script>
	</head>
<body>
<input type="hidden" id="refundCardId" value="${cardId }">
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">请输入退款金额</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModel2" id="myForm" name="myForm" cssClass="form-horizontal">
	<table class="table table-bordered table-striped">
		<tr id='selStr'>
		<%-- <td>
			<form:select path="" id="vehiclePlateColor" cssClass="dataChange form-control m-r-5">
				<c:forEach var ="c" items="${list }">
						<form:option value='${ c/100}'>${c/100}</form:option>
					</c:forEach>
				</form:select>
		</td> --%>
			<td>
			<!-- div class="filter-box" style='width:300px'>
				<div class="filter-text">
					<input class="filter-title" type="text" readonly placeholder="pleace select" />
					<i class="icon icon-filter-arrow"></i>
				</div> -->
				<select  id="refundSel" class="form-control m-r-4" style='width:300px' onchange="refundSelChange()">
					<option value='qxz' selected>请选择...</option>
					<c:forEach var ="c" items="${list }">
						<option value='${ c/100}'>${c/100}</option>
					</c:forEach>
				</select>
			<!-- </div> -->

			</td>
		</tr>
		<tr>
			<td>
				<input  class="form-control" style='width:300px' required="required" onblur="onblurText();" onfocus = "onfocusText();"  id="refundBalance" onkeyup="textKeyUp()" placeholder="请输入退款金额" />
			</td>
		</tr>
		
	</table>
</form:form>	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal" id="closeBtn">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">提交</a>
</div>

</body>
</html>