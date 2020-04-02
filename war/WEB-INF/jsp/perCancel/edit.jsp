<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	$("<input id ='agencyList' name='agencyList'/>").val('1');
	$.ajaxSetup({
		cache : false
	});
	$.fn.showPopover.defaults.placement = "right";
	$.fn.showPopover.defaults.viewport = null;
	
	$("#myForm").validate(
			{
				submitHandler : function(form) {
					$("#myManage").triggerHandler("taijiModalPost",
							[ $("#myForm"), {
								table : "edit"
							} ]);
				}
			});
	$("#submit").click(function() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var checkNodes = zTree.getNodesByFilter(function(node){
			var status = node.getCheckStatus();
			/* if(node.level==0)return false; */
			if(status.checked === true || status.half === true) return true;
			else return false;
		},false,null);
		
		
		for(var n in checkNodes){
			var checkId=checkNodes[n].id;
			console.log(checkId);
			$("<input type='hidden' name='agencyIds'/>").val(checkId).insertAfter("#agencyList");
		}
			$("[name='agencyIds']").each(function(){
				console.log($(this).val());
		});
		// 		$("#myForm").trigger("submit");
		/*  $("#passwd").val(hex_sha512($("#passwd1").val()));
		$("#confirm_pw").val(hex_sha512($("#confirm_pw1").val()));  */
			$("#myManage").triggerHandler("taijiModalPost", [ $("#myForm"), {
			table : "edit"
			} ]);
	});
	
	
});


$(function(){
	var settings = {
			check: {
				enable: true
			}

	};
	var data=${json};
	$.fn.zTree.init($("#tree"), settings, data);
	
	$("#submit").click(function(){
//			$("#myManage").triggerHandler("taijiModalPost",[$("#confForm"),{table:"add"}]);
	});
});
</script>
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">预注销管理--修改</h4>
	</div>
	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="myForm" name="myForm"
			cssClass="form-horizontal" action="${rootUrl }app/customerservice/card/perCancel/edit"
			method="post">
			<label class="control-label" for="agencyList">选择预注销立即释放网点：</label>
			<!-- <div class="form-group  m-t-5" align="left">
							<div class="checkbox-inline ">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010104001" id="check1"><label for="check1">货车帮</label>
				             </div>	
				             <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010104003" id="check2"><label for="check2">中交智慧</label>
				             </div>
				             <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010104004" id="check3"><label for="check3">裕福支付</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010104005" id="check4"><label for="check4">上海易路通达</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102006" id="check5"><label for="check5">贵阳银行</label>
				             </div>
				              <div class="checkbox-inline">			                
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102007" id="check6"><label for="check6">中国工商银行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102008" id="check7"><label for="check7">中国邮政储蓄银行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010106004" id="check8"><label for="check8">黔通智联（自营）</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102003" id="check9"><label for="check9">中国银行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188012" id="check10"><label for="check10">天地汇</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102015" id="check11"><label for="check11">农业银行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102014" id="check12"><label for="check12">贵州交通银行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188011" id="check13" ><label for="check13">传化物流</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188013" id="check14"><label for="check14">中润汇德</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102010" id="check15"><label for="check15">乌当农商行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188006" id="check16"><label for="check16">河南双合</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102017" id="check17"><label for="check17">兴业银行贵州分行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010102018" id="check18"><label for="check18">中国建设银行</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188021" id="check19"><label for="check19">中铁中基裕福</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188019" id="check20"><label for="check20">微行助手</label>
				             </div>
				              <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188020" id="check21"><label for="check21">北京聚利</label>
				             </div>
				             <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188016" id="check22"><label for="check22">北京特微</label>
				             </div>
				             <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188022" id="check23"><label for="check23">智载科技</label>
				             </div>
				             <div class="checkbox-inline">
								<input type="checkbox" class="magic-checkbox" name="agencyList"  value="52010188023" id="check24"><label for="check24">小贷公司</label>
				             </div>
			
			 </div>
 -->
			<input  type='hidden' id ='agencyList' name='agencyList'/>
		<%-- <input type="hidden" id="roleId" name="roleId" value="${package.id }"/> --%>
			<ul id="tree" class="ztree"></ul>
		</form:form>
	</div>
	

<!-- <div class="modal-body"> -->
<form name="confForm" id="confForm" >
</form>
<!-- </div> -->
	
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
		<a href="#" class="btn btn-sm btn-success" id="submit">保存</a>
	</div>

</body>
</html>