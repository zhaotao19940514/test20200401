<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${rootUrl }plugins/ztree/js/jquery.ztree.js" type="text/javascript"></script>
<link rel="stylesheet" href="${rootUrl }plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<!-- ztree初始化 -->
<script type="text/javascript">
$(function(){
var zTreeObj;
var setting = {
		check : {
			enable:true,
			chkStyle: "checkbox"
		},	
		view : {
			selectedMulti: false,
			autoCancelSelected: false
		},callback: {
            //树复选框选中事件
            onCheck: zTreeOnCheck
       },
		async : {
			enable : true,
			//生产
			url : "${ rootUrl }"+"app/administration/agencypermission/agencyJsonInfo"
		}
};

function zTreeOnCheck(event, treeId, treeNode) {
	addTable();
};
// ztree数据
var zNodes = [
	{name: "<-----请选择需要批量操作的渠道", open:true , isParent:true , agencyId:"00000" ,children: [
		
	]}
];
//ztree加载
   zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
});
</script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false}); 
	$.fn.showPopover.defaults.placement="right";
	$.fn.showPopover.defaults.viewport=null;
});

$("#submit").click(function(){
	var obj = $.fn.zTree.getZTreeObj("tree");  //ztree的Id  zTreeId
	var checkedNodes = obj.getCheckedNodes();
	var agencyIdList = new Array();
	for(var i=1;i<checkedNodes.length;i++){
		agencyIdList.push(checkedNodes[i].agencyId);
	}
	$("#agencyIdList").val(agencyIdList);
	$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
});

$("#agencyId").change(function(){
		addTable();
})
$("#cardType").change(function(){
		addTable();
})

$("#batchType").change(function(){
		addTable();
})


function addTable(){
	var batchTypeInfo =$("#batchType").val();
	var agencyInfo = $("#agencyId").find("option:selected").val();
	var cardTypeInfo = $("#cardType").find("option:selected").val();
	console.log(batchTypeInfo);
	if(batchTypeInfo==""){
		return;
	}
	if(batchTypeInfo==0){
		//添加控制渠道
		var obj = $.fn.zTree.getZTreeObj("tree");
		var checkedNodes = obj.getCheckedNodes();
		$("#agencyTable").html('');
		$("#agencyTable").append(
				"<tr>"
				+"<th style='font-size:13px;'></th>"
					+"	<th style='font-size:13px;'>控制渠道</th>"
					+"	<th style='font-size:13px;'>被控制渠道</th>"
						+"<th style='font-size:13px;'>卡类型</th>"
				+"</tr>"
		
		)
		for(var i=1;i<checkedNodes.length;i++){
			$("#agencyTable").append(
						"<tr>	"
						+"<th style='font-size:13px;'>渠道信息</th>	"
						+"<th style='font-size:13px;'>"+$("#agencyId").find("option:selected").text()+"-"+$("#agencyId").find("option:selected").val()+"</th>"
						+"<th style='font-size:13px;'>"+checkedNodes[i].name+"-"+checkedNodes[i].agencyId+"</th>"
						+"<th style='font-size:13px;'>"+$("#cardType").find("option:selected").text()+"</th>"
						+"</tr>	"
			)
		}
	}
	if(batchTypeInfo==1){
		//被控渠道
		var obj = $.fn.zTree.getZTreeObj("tree");
		var checkedNodes = obj.getCheckedNodes();
		$("#agencyTable").html('');
		$("#agencyTable").append(
				"<tr>"
				+"<th style='font-size:13px;'></th>"
					+"	<th style='font-size:13px;'>控制渠道</th>"
					+"	<th style='font-size:13px;'>被控制渠道</th>"
						+"<th style='font-size:13px;'>卡类型</th>"
				+"</tr>"
		
		)
		for(var i=1;i<checkedNodes.length;i++){
			$("#agencyTable").append(
					"<tr>	"
					+"<th style='font-size:13px;'>渠道信息</th>	"
					+"<th style='font-size:13px;'>"+checkedNodes[i].name+"-"+checkedNodes[i].agencyId+"</th>"
					+"<th style='font-size:13px;'>"+$("#agencyId").find("option:selected").text()+"-"+$("#agencyId").find("option:selected").val()+"</th>"
					+"<th style='font-size:13px;'>"+$("#cardType").find("option:selected").text()+"</th>"
					+"</tr>	"
		)

		}
	}
}
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">渠道权限管理--批量删除</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="batchDeleteModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/administration/agencypermission/batchDelete" method="POST">
				<form:input id="agencyIdList"  path="agencyList" cssStyle="display:none"/>
				<div style="float: right;display: inline-block;overflow:auto;width:550px;height: 600px">
					<span  style="color: red;font-size: 15px;" >当前操作会删除以下权限信息 请确认无误后提交</span>
											<table class="table table-bordered" id="agencyTable">
					<tr>
					<th style="font-size:13px;"></th>
							<th style="font-size:13px;">控制渠道</th>
							<th style="font-size:13px;">被控制渠道</th>
							<th style="font-size:13px;">卡类型</th>
					</tr>
			</table>
				</div>
				<div>
					<div style="width:230px;height:60px;">
						<span style="font-size: 15px">渠道名称</span><select
						id="agencyId"
						name="agencyId"
						class="taiji_autocomplete form-control"
						data-url="${rootUrl }app/administration/agencypermission/queryByAgencyName"
						data-placeholder="请选择渠道" style="width: 20em;">
						<option></option>
						</select>
				</div>
				
						<div style="width:100px;height:60px;">
							<span style="font-size: 15px">批量删除类型</span>
							<select name="batchType" id="batchType" class="taiji form-control" style="width:240px;">
							<option value="">---------请选择批量删除类型----------------</option>
							<option value="0">根据控制渠道批量删除</option>
							<option value="1">根据被控制渠道批量删除</option>
							</select>
						</div>
					<div style="width:100px;height:50px;">
						<span style="font-size: 15px">卡类型</span>	
								<select id="cardType" name="cardTypeCode" style="width:240px;height:30px;display:inline-block;font-size:13px" data-style="btn-white" data-width="300px">
													<option value="">-----------请选择卡类型--------------</option>
													<c:forEach items="${cardTypeCode}" var="code">
													<option value="${code.code }">${code.value }</option>
													</c:forEach>
												</select>
					</div>	
					<div style="height:30px;">
					</div>
			<div style="width:320px;height:300px;overflow:auto;">	
				<span id="tree" class="ztree"></span>
				</div>
			</div>
   </form:form> 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>