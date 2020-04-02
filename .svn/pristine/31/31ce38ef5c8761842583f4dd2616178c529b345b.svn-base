<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />

<script type="text/javascript">
$(function(){
	var settings = {
			check: {
				enable: true
			}

	};
	var data=${json};
	$.fn.zTree.init($("#tree"), settings, data);
	
	$("#submit").click(function(){
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var checkNodes = zTree.getNodesByFilter(function(node){
			var status = node.getCheckStatus();
			if(node.level==0)return false;
			if(status.checked === true || status.half === true) return true;
			else return false;
		},false,null);
		if($(checkNodes).length == 0){
			alert("请至少选中一个权限");
			return false;
		}		
		
		for(var n in checkNodes){
			var checkId=checkNodes[n].id;
			$("<input type='hidden' name='resourceIds'/>").val(checkId).insertAfter("#roleId");
		}
		$("#myManage").triggerHandler("taijiModalPost",[$("#confForm"),{table:"edit"}]);
	});
});

</script>
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">角色管理--配置</h4>
</div>
<div class="modal-body">
<form name="confForm" id="confForm" action="${rootUrl }app/acl/role/conf" method="post">
<input type="hidden" id="roleId" name="roleId" value="${role.id }"/>
	<ul id="tree" class="ztree"></ul>
</form>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>
</body>
</html>

