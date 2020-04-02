<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		 /* $("<input id ='agencyList' name='agencyList'/>").val('1'); */
		$.ajaxSetup({
			cache : false
		});
		$.fn.showPopover.defaults.placement = "right";
		$.fn.showPopover.defaults.viewport = null;

		$("#myForm").validate(
				{
					/* rules: {
						loginName:{required="required": true,maxlength:50},
						name:{required="required": true,maxlength:50},
						passwd:{required="required":true},
						confirm_pw:{required="required":true, equalTo:"#passwd"}
					},
					messages: {
						passwd: {
							required="required": "密码必填！"
						},
						confirm_pw: {
							required="required": "确认密码必填！",
							equalTo: "请检查两次输入是否一致"
						}
					},  */
					submitHandler : function(form) {
						$("#myManage").triggerHandler("taijiModalPost",
								[ $("#myForm"), {
									table : "add"
								} ]);
					}
				}); 
		$("#submit").click(function() {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			var checkNodes = zTree.getNodesByFilter(function(node){
				var status = node.getCheckStatus();
				if(node.level==0)return false;
				if(status.checked === true || status.half === true) return true;
				else return false;
			},false,null);
			if($(checkNodes).length == 0){
				$.Taiji.showWarn("请至少选择一个网点!")
				return false;
			}		
			
			for(var n in checkNodes){
				var checkId=checkNodes[n].id;
				//console.log(checkId);
				$("<input type='hidden'  name='agencyIds'/>").val(checkId).insertAfter("#agencyList");
			}
			debugger;
 			$("[name='agencyIds']").each(function(){
 				console.log($(this).val());
			});
			// 		$("#myForm").trigger("submit");
			/*  $("#passwd").val(hex_sha512($("#passwd1").val()));
			$("#confirm_pw").val(hex_sha512($("#confirm_pw1").val()));  */
 			$("#myManage").triggerHandler("taijiModalPost", [ $("#myForm"), {
				table : "add"
 			} ]);
		});
		
		
	});

	function validPackageNum(obj){
		 obj.value = obj.value.replace(/[^\d]/g,"");
		 if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
		        obj.value= parseFloat(obj.value); 
		    } 
	}
	
	$(function(){
		var settings = {
				check: {
					enable: true
				}

		};
		var data=${json};
		$.fn.zTree.init($("#tree"), settings, data);
		
		$("#submit").click(function(){
// 			$("#myManage").triggerHandler("taijiModalPost",[$("#confForm"),{table:"add"}]);
		});
	});

</script>
<style type="text/css">
	table{
		talbe
	}
</style>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">新增记账卡套餐信息</h4>
	</div>

	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="myForm" name="myForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/package/account/add"
			method="post">
			<table class="table table-bordered table-striped">
				<tr>
					<td>
						<label for='packageName' class="control-label">优惠套餐名称</label>
					</td>
					<td>
						<form:input path="packageName" cssClass="form-control"
						required="required" maxlength="20" placeholder="优惠套餐名称必填" />
					</td>
				</tr>
				<tr>
					<td>
						<label for='packageNum' class="control-label">套餐编号</label>
					</td>
					<td>
						<form:input path="packageNum" cssClass="form-control"
						required="required" placeholder="套餐编号" htmlEscape="false" maxlength="3"  />
					</td>
				</tr>
				
				<tr>
					<td>
						<label for='status' class="control-label">状态</label>
					</td>
					<td>
						<form:select path="packageState" class="form-control m-r-5"
						     data-style="btn-white">
							<form:option selected="selected" value="">--请选择--</form:option>
							<form:option value="1">有效</form:option>
							<form:option value="2">无效</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label" for='startTime'>开始时间</label>
					</td>
					<td>
						<div class="form-inline">
							<form:input cssStyle="width:200px" path="startTime"
								readonly="true" cssClass="form-control" />
							<button type="button" class="btn btn-default "
								onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});">
								<i class="fa fa-calendar"></i>
							</button>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label" for="endTime">结束时间</label>
					</td>
					<td>
						<div class="form-inline">
							<form:input cssStyle="width:200px" path="endTime"
								readonly="true" cssClass="form-control" />
							<button type="button" class="btn btn-default form-control"
								onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'});">
								<i class="fa fa-calendar"></i>
							</button>
					 	</div> 
					</td>
				</tr>				
			</table>
			
			<label class="control-label" for="agencyList">选择记账卡优惠套餐网点：</label>
        	<input  type='hidden' id ='agencyList' name='agencyList'/>
		</form:form>
	</div>

<form name="confForm" id="confForm" >
	<!-- <input  type='hidden' id ='agencyList' name='agencyList'/> -->
<%-- <input type="hidden" id="roleId" name="roleId" value="${package.id }"/> --%>
	<ul id="tree" class="ztree"></ul>
</form>

	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a> <a
			href="#" class="btn btn-sm btn-success" id="submit">保存</a>
	</div>

</body>
</html>