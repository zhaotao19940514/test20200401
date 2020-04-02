<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
		 $(document).on('click.bs.dropdown.data-api', '.dropdown .noclear', function (e) { e.stopPropagation() });
		 $.ajaxSetup({cache : false});
			$("#myManage").taiji({
				enableAclCheck:true,
				search:{
					 autoSearch:false,
					 search:{
						 autoSearch:false
						}
				}
			});
		  
	});
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">查看文件</h4>
</div>
<div class="modal-body">
<table class="table table-bordered">
			<tr>
				<td colspan="4" style="text-align: center;">
					 <img id="screenShotImg" style="width:500px;" src="${fn:escapeXml(imgBase64) }" />
				</td>
			</tr>
		</table>
</div>
<div class="modal-footer">
	<a id="close" href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>