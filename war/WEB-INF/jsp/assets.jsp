<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<title>
	<c:if test="${not empty requestScope.currentColumnResource}">
		${currentColumnResource.name}-
	</c:if><fmt:message key="webapp.name"  />
</title>
<link rel="shortcut icon" href="${rootUrl }images/favicon.jpg" />
<c:if test="${empty assets}" >
<link href="${rootUrl }plugins/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
<link href="${rootUrl }css/manage.css" rel="stylesheet" />
<link href="${rootUrl }css/stylex.css" rel="stylesheet" />
<link href="${rootUrl }css/chosen.css" rel="stylesheet" />
<link href="${rootUrl }css/magic-check.min.css" rel="stylesheet" />
<link href="${rootUrl }plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet" />
<script src="${rootUrl }plugins/jquery/jquery-1.12.4.min.js"></script>
<script src="${rootUrl }plugins/jquery/jquery.fix.clone.js"></script>
<script src="${rootUrl }plugins/form/jquery.form.js"></script>
<script src="${rootUrl }plugins/metadata/jquery.metadata.js"></script>
<script src="${rootUrl }plugins/chosen/chosen.jquery-1.6.min.js"></script>
<script src="${rootUrl }plugins/chosen/ajax-chosen.js"></script>
<script src="${rootUrl }plugins/base64/base64.js"></script>
<script src="${rootUrl }plugins/jquery-ui-1.10.4/ui/minified/jquery-ui.min.js"></script>
<script src="${rootUrl }plugins/validate/js/1.15/jquery.validate.min.js"></script>
<script src="${rootUrl }plugins/validate/js/jquery.validate.taiji.js"></script>
<script src="${rootUrl }plugins/validate/js/messages_cn.js"></script>
<script src="${rootUrl }plugins/bootstrap-3.1.1/js/bootstrap.min.js"></script>
<script src="${rootUrl }plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script src="${rootUrl }plugins/slimscroll/jquery.slimscroll-1.2.0.min.js"></script>
<script src="${rootUrl }plugins/icheck/icheck.js"></script>
<script src="${rootUrl }plugins/jquery-confirm/jquery-confirm.js"></script>
<script src="${rootUrl }js/tjlib/jquery.taiji-3.0.js" type="text/javascript"></script>
<script src="${rootUrl }js/tjlib/apps.js"></script>
</c:if>
<c:if test="${not empty assets}" >
<link href="${rootUrl }plugins/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" />
<link href="${rootUrl }css/manage.css" rel="stylesheet" />
<script src="${rootUrl }plugins/jquery/jquery-1.12.4.min.js"></script>
<script src="${rootUrl }js/tjlib/jquery.tjlib.js" type="text/javascript"></script>
<script src="${rootUrl }js/tjlib/jquery.taiji-3.0.min.js" type="text/javascript"></script>
<script src="${rootUrl }js/tjlib/apps.js"></script>
</c:if>
<!--[if lt IE 9]>
	<script src="${rootUrl }js/crossbrowserjs/html5shiv.js"></script>
	<script src="${rootUrl }js/crossbrowserjs/respond.min.js"></script>
	<script src="${rootUrl }js/crossbrowserjs/excanvas.min.js"></script>
<![endif]-->
	
<script  type="text/javascript">
var rootUrl='${rootUrl}';

$(document).ready(function() {
	App.init();
	
	$("#${param.myMenuId}").parents("ul.nav li").andSelf().addClass("active");
// 	$("#sidebar ul.nav li.active").each(function(){
// 		$("ol.breadcrumb").append($("<li>",{text:$(this).children("a").text()}));
// 	});
// 	$("ol.breadcrumb>li:last").addClass("active");
	if($("#sidebar ul.nav li.active").length){
		$("h1.page-header").text($("#sidebar ul.nav li.active:last").text());
	}
	
	$("#modPasswd,#modMyself").click(function(){
		$(this).showModal();
		return false;
	});
});
</script>
