<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
	});
</script>
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">图片查看</h4>
</div>
<div class="modal-body">
	<table class="table table-bordered">
		<c:forEach items="${picBase64 }" var="vo"  varStatus="voStatus" >
				<tr>
					<td colspan="4" style="text-align: center;">
						 <img id="screenShotImg" style="width:500px;" src="${vo }" />
					</td>
				</tr>
		</c:forEach>
	</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
	
</body>
</html>