<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="${rootUrl }myplugins/trumbowyg/ui/trumbowyg.css">
<script src="${rootUrl }myplugins/trumbowyg/trumbowyg.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${rootUrl }myplugins/trumbowyg/langs/zh_cn.min.js"></script>

<script type="text/javascript">
	$(function() {
		$.ajaxSetup({
			cache : false
		});
		var base64 = new Base64();
		$(".modal-body").taiji({});
		$("#submit").click(function() {
			debugger;
			$("#myManage").triggerHandler("taijiModalPost", [ $("#editForm"), {
				table : "edit"
			} ]);
		});
		$("#editor").trumbowyg(
				{
					lang : 'zh_cn',
					btns : [ [ 'viewHTML' ], [ 'formatting' ],
							'btnGrp-semantic', [ 'superscript', 'subscript' ],
							'btnGrp-justify', 'btnGrp-lists',
							[ 'horizontalRule' ], [ 'removeformat' ] ]
				});

		$("#fileDiv").on(
				"change",
				"input[type='file']",
				function() {
					var id = $(this).attr("id");
					var ids = id.split('_');
					var range = ids[0];
					var filePath = $(this).val();
					if (filePath != null && "" != filePath) {
						$("#" + range + "a").hide();
						$("#" + range + "label").show();
						$("#" + range + "label")
								.attr("display", "inline-block");
						var arr = filePath.split('\\');
						var fileName = arr[arr.length - 1];
						$("#" + range + "div").html(fileName);
						var title = $(".articleTitle").val();
						if (title == "") {
							$(".articleTitle").val(
									fileName.substring(0, fileName
											.lastIndexOf(".")));
						}
					} else {
						$("#" + range + "div").html("");
						$("#" + range + "a").show();
						return false
					}
				});
	});

	function addFile() {
		var range = Math.ceil(Math.random() * 1000);
		$("#fileDiv")
				.append(
						" <div class='"+range+"id' style='margin-top:5px;'><div style='width:300px;display:inline;'>"
								+ "<a style='display:inline;' href='javascript:;' class='file' id='"+range+"a'>选择文件<input class='myfiles' id='"+range+"_id' name='proofFiles' type='file' style='float:left;' /></a>"
								+ "<label style='display:inline-block;width:300px;' id='"+range+"div'></label><label id='"+range+"label' style='width:82px;display:none;'></label></div>"
								+ "<button type='button' onclick=removeFile('"
								+ range
								+ "')  style='color:red;float:none;font-size:14px;padding:2px 10px;margin-left:20px;' class='btn btn-sm btn-white '>删除</button></div>");
		$("#" + range + "_id").click();
	}
	function removeFile(range) {
		$("." + range + "id").remove();
	}

	$(".deleteName").click(function() {
		$(".modal-body").taiji("ajaxHref", this, {
			method : "POST",
			bsSuccess : function(data, note) {
				$.Taiji.showNote(note);
				removeFile(data);
			}
		});
		return false;
	});
	$(function() {
		$(".modal-content")
				.attr(
						"style",
						"width:80vw !important;margin-left:-21vw;");
		$("img").attr("style","max-width: 70vw;");
	});
</script>
<!-- <style type="text/css">
.fileDiv input {
	position: absolute;
	font-size: 100px;
	left: 5px;
	top: 0;
	width: 100px;
	height: 25px;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}

.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}
</style> -->
</head>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">通知公告管理--修改</h4>
	</div>

	<div class="modal-body">
		<form:form modelAttribute="pageModel" id="editForm" name="editForm"
			cssClass="form-horizontal"
			action="${rootUrl }app/administration/notify/edit" method="post">
			<form:hidden path="id" />
			<input type="hidden" name="type" id="type"
				value="${fn:escapeXml(type)}" />
			  <table class="table table-bordered table-striped">
					<tr>
						<td><label class="control-label">标题</label></td>
						<td><form:input path="title" class="form-control" placeholder="请填写标题" /></td>
					</tr>
					<tr>
						<th><label class="control-label">是否发布</label></th>
						<td>
							<form:select path="status" class="form-control">
							<form:option value="1">是</form:option>
							<form:option value="0">否</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">发布日期</label></td>
						<td>
							 <div class="form-inline">
								<form:input cssStyle="width:1080px"
									path="releaseDate" readonly="true" cssClass="form-control" />
								<button type="button" class="btn btn-default"
									onclick="WdatePicker({el:$dp.$('releaseDate'),dateFmt:'yyyy-MM-dd'});">
									<i class="fa fa-calendar"></i>
								</button>
							</div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">内容</label></td>
						<td>
							<textarea id="editor" name="content">${pageModel.content}</textarea>
						</td>
					</tr>
			</table>   
			 <%-- <div class="form-group">
				<label class="col-sm-2 control-label">标题</label>
				<div class="col-sm-10">
					<form:input path="title" class="form-control" placeholder="请填写标题" />
				</div>
			</div> 
			<div class="form-group">
				<label class="col-sm-2 control-label">是否发布</label>
				<div class="col-sm-10">
					<form:select path="status" class="form-control">
						<form:option value="1">是</form:option>
						<form:option value="0">否</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">日期</label>
				<div class="form-inline">
					<form:input cssStyle="width:200px" path="releaseDate" readonly="true"
						cssClass="form-control" />
					<button type="button" class="btn btn-default"
						onclick="WdatePicker({el:$dp.$('releaseDate'),dateFmt:'yyyy-MM-dd'});">
						<i class="fa fa-calendar"></i>
					</button>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">内容</label>
				<div class="col-sm-10">
					<textarea id="editor" name="content">${pageModel.content}</textarea>
				</div>
			</div>  --%>
			<div class="modal-footer">

				<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
				<a href="#" class="btn btn-sm btn-success" id="submit">保存</a>

			</div>
		</form:form>

	</div>


</body>
</html>