<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<script type="text/javascript">
	$(function(){
		var json = [
		<c:forEach items="${resourceType}" var="type">
			{"name":"${type.value}","id":"type_${type}","isParent":true,"open":true},
		</c:forEach>
		];
		
		var settings = {
			async: {
				enable: true,
				url:getUrl
			},
			view:{
				selectedMulti: false,
				autoCancelSelected: false
			},
			callback:{
				beforeCollapse:function(){
					return true;
				},
				onCollapse:function(){
				},
				beforeExpand:function(treeId,node){
// 					var parent = node.getParentNode();
// 					var zTree = $.fn.zTree.getZTreeObj(treeId);
// 					var nodes = zTree.getNodesByFilter(function(node,data){
// 						if((node.level === data.level && node.id !== data.id) || node.level > data.level) return true;
// 						else return false;
// 					},false,parent,{"level":node.level,"id":node.id});
// 					for(var n in nodes){
// 						zTree.expandNode(nodes[n],false);
// 					}
					return true;
				},
				onClick:function(event,treeId,node){
					$("#listForm").resetForm();
					var id=node.id || "-1";
					if(id.indexOf("type_")>=0){
						$("#id").val("");
						$("#menuType").val("COLUMN");
						$("#menuType_span").hide();
						$("#resourceType").val(id.replace("type_",""));
						
						$("#add_a").attr("href","${rootUrl }app/acl/resource/add?menuType=COLUMN&type="+id.replace("type_","")).show();
						$("#addLinkName").html("添加二级菜单");
						
						$("#add_n").attr("href","#").hide();
						
					}else if(id.indexOf("COLUMN_")>=0){
						var typeNode=node.getParentNode();
						var tt=typeNode.id.replace("type_","");
						$("#id").val(id.replace("COLUMN_",""));
						$("#menuType").val("");
						$("#menuType_span").show();
						$("#resourceType").val("");
						
						$("#add_a").attr("href","${rootUrl }app/acl/resource/add?menuType=BOX_TAB&menuId="+id.replace("COLUMN_","")+"&type="+tt).show();
						$("#addLinkName").html("添加三级菜单");
						
						$("#add_n").attr("href","${rootUrl }app/acl/resource/add?menuType=NOT_MENU&menuId="+id.replace("COLUMN_","")+"&type="+tt).show();
					}else if(id.indexOf("BOX_TAB_")>=0){
						var typeNode=node.getParentNode().getParentNode();
						var tt=typeNode.id.replace("type_","");
						
						$("#id").val(id.replace("BOX_TAB_",""));
						$("#menuType").val("NOT_MENU");
						$("#menuType_span").hide();
						$("#resourceType").val("");
						
						$("#add_a").attr("href","#").hide();
						$("#addLinkName").html("");
						
						$("#add_n").attr("href","${rootUrl }app/acl/resource/add?menuType=NOT_MENU&menuId="+id.replace("BOX_TAB_","")+"&type="+tt).show();
					}else{//非菜单
						$("#add_a").hide();
						$("#addLinkName").html("");
						
						$("#add_n").attr("href","#").hide();
					}
					$("#queryButton").trigger("click");
				},	
				onExpand:function(event,treeId,node){
					
				}
			}
		};
		
		function getUrl(treeId, treeNode){
			if(treeNode.id.indexOf("type_") > -1){
				return "${rootUrl}app/acl/resource/getByParent?resourceType="+treeNode.id.replace("type_","");
			}else if(treeNode.id.indexOf("COLUMN_") > -1){
				return "${rootUrl}app/acl/resource/getByParent?id="+treeNode.id.replace("COLUMN_","");
			}else if(treeNode.id.indexOf("BOX_TAB_") > -1){
				return "${rootUrl}app/acl/resource/getByParent?id="+treeNode.id.replace("BOX_TAB_","");
			}
		}
		
		$.fn.zTree.init($("#tree"), settings, json);


	});
</script>
	<!--tree-->
<div >	
			<ul id="tree" class="ztree"></ul>
	</div>
	<!--tree end-->
