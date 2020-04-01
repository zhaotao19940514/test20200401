<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<script type="text/javascript">
			$(function(){
				$.ajaxSetup ({ cache: false}); 
				$(".ztb").click(function(){
					$(".article_content").css("font-size", "18px");
				});
				$(".ztm").click(function(){
					$(".article_content").css("font-size", "16px");
				});
				$(".zts").click(function(){
					$(".article_content").css("font-size", "12px");
				});
			});
		</script>
<style type="text/css">
.article_body {
	border: 1px #dddddd solid;
	padding: 10px 66px 38px 66px;
}

.article_h1 {
	line-height: 56px;
	font-family: "微软雅黑", "宋体";
	font-size: 38px;
	text-align: center;
	padding: 26px 0 26px 0;
	font-weight: normal;
}

.article_title {
	position: relative;
	border-bottom: 1px #dcdcdc solid;
	padding-top: 7px;
	text-align: left;
	color: #666666;
	font-size: 14px;
	height: 35px;
	overflow: hidden;
}

.font_span {
	margin: 0 10px;
}

.font_right {
	float: right;
}

.article_content {
	line-height: 30px;
	margin: 0;
	padding: 40px 0 40px 0;
	font-size: 16px;
	border-bottom: 1px #dcdcdc solid;
}

.attachment_down {
	line-height: 30px;
}
</style>
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">通知公告管理--预览</h4>
	</div>

	<div class="modal-body">
		<div class="article_body">
			<h1 class="article_h1">${fn:escapeXml(pageModel.articleTitle)}</h1>
			<div class="article_title">
				<javatime:format value="${pageModel.insertTime}"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<span class="font_span">来源：${fn:escapeXml(pageModel.articleSource)}</span>
				<span class="font_span font_right">【字体： <a
					href="javascript:void(0);" class="ztb">大</a> <a
					href="javascript:void(0);" class="ztm">中</a> <a
					href="javascript:void(0);" class="zts">小</a> 】</label>
			</div>
			<div class="article_content">
				${pageModel.articleContent}
				<c:if test="${!empty pageModel.createUserName}">
				(${fn:escapeXml(pageModel.createUserName)})
			</c:if>
			</div>
			<c:if test="${fn:length(attachments) gt 0 }">
				<div class="attachment_down">
					<span style="font-size: 20px;">附件下载</span>
					<c:forEach items="${attachments}" var="atta" varStatus="vo">
						<div style="margin-top: 5px;">
							<a
								href="${rootUrl}app/preparation/notification/download/${atta.id}">附件${vo.index+1}：${atta.filename }</a>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>