<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${rootUrl }plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script src="${rootUrl }plugins/ztree/js/jquery.ztree.js" type="text/javascript"></script>
<script>
	$(function(){
		 $(document).on('click.bs.dropdown.data-api', '.dropdown .noclear', function (e) { e.stopPropagation() });
		 
		 var json = [{"name":"${loginUser.unit.name}","id":"${loginUser.unit.id}","isParent":true}];
 		var settings = {
 			async: {
 				enable: true,
 				url:"${rootUrl }app/acl/unit/getByParent",
 				autoParam: ["id=parentId"]
 			},
 			view:{
 				selectedMulti: false,
 				autoCancelSelected: false
 			},
 			callback:{
 				onClick:function(event,treeId,node){
 					$("#selectUnitBtn").dropdown('toggle');
 					setUnitValue(node.id,node.name);
 				}
 			}
 		};
		$.fn.zTree.init($("#unitTree"), settings, json);   
	});
</script>
</head>

<div class="dropdown" style="display: inline-block;">
	<a id="selectUnitBtn" href="#" data-toggle="dropdown" class="dropdown-toggle" style="font-size:14px;padding-left:10px">选择</a>
	<div class="dropdown-menu dropdown-menu-left noclear" style="min-width: 250px">
		<ul id="unitTree" class="ztree"></ul>
	</div>
</div>