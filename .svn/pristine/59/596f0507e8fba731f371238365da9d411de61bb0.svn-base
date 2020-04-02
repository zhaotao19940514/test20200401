<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<link rel="stylesheet"
	href="${rootUrl }plugins/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script src="${rootUrl }plugins/ztree/js/jquery.ztree.js"
	type="text/javascript"></script>
<script>
	$(function() {
		$(document).on('click.bs.dropdown.data-api', '.dropdown .noclear',
				function(e) {
					e.stopPropagation()
				});
		var agencyId = "${loginUser.staff.serviceHall.agencyId}";
		var json = [ {
			"name" : "${loginUser.name}",
			"id" : agencyId,
			"isParent" : true
		} ];
		var settings = {
			async : {
				enable : true,
				url : "${rootUrl }app/hall/getByParent",
				autoParam : [ "id=agencyId" ]
			},
			view : {
				selectedMulti : false,
				autoCancelSelected : false
			},
			callback : {
				onClick : function(event, treeId, node) {
					$("#selectServiceHallBtn1").dropdown('toggle');
					setServiceHallValue1(node.id, node.name);
				}
			}
		};
		$.fn.zTree.init($("#serviceHallTrees"), settings, json);
	});
</script>
</head>

<div class="dropdown" style="display: inline-block;">
	<a id="selectServiceHallBtn1" href="#" data-toggle="dropdown"
		class="dropdown-toggle" style="font-size: 14px; padding-left: 10px">选择</a>
	<div class="dropdown-menu dropdown-menu-left noclear"
		style="min-width: 250px">
		<ul id="serviceHallTrees" class="ztree"></ul>
	</div>
</div>