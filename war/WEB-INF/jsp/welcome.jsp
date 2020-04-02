<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title><fmt:message key="webapp.name" />--登录</title>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>
<script src="${rootUrl}js/cookie.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji().on("taijiCVE",function(event,responseText){
		var cve=$.parseJSON($(responseText).find("#taiji_ejson").text());
		if(cve.validCode&&cve.validCode=="验证码错误"||cve.password&&cve.password=="密码错误"){
			$("#getValidCode").click();
		}
	});
	
	$("#login_button").click(function(){
		var hexsha=hex_sha512($("#password").val());
	 	$("#myManage").taiji("ajaxHref",this,
			   {type:"POST",
		   		data:{username:$("#username").val(),password:hexsha,validCode:$("#validCode").val()},
		 });
		return false;
	});
	
	$("input").keydown(function(event){
		if (event.keyCode === 13 ) {
			$("#login_button").click();
		}
	});
	
	i=1;
	$("#getValidCode").click(function(){
		i++;
		if(i>10){
			alert("刷新验证码太频繁了！")
			return;
		}
	    $(this).html("<img   src='${rootUrl }app/common/validCodeImage.html?ee="+i+"'/>");
	});
});
</script>
</head>

<body>
<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="login-container fade">
	    <!-- begin login -->
        <div class="login bg-black animated fadeInDown">
            <!-- begin brand -->
            <div class="login-header">
                <div class="brand" style="text-align: center;">
<!--                     <span class="taiji-logo" style="height:28px" ></span> -->
                                                             黔通卡客服系统
<!--                     <small>黔通卡客服系统</small> -->
                </div>
                <div class="icon">
                    <i class="fa fa-sign-in"></i>
                </div>
            </div>
            <!-- end brand -->
            <div id="myManage" class="login-content">
            	<form  id="loginForm" name="loginForm" class="margin-bottom-0" action="${rootUrl }app/common/login" method="post">
                    <div class="form-group m-b-20">
                    	<input name="username" id="username" class="form-control input-lg"  placeholder="请输入用户名" /> 
                    </div>
                    <div class="form-group m-b-20">
                        <input type="password"  id="password" name="password" class="form-control input-lg" placeholder="请输入密码" />
                    </div>
                   <div class="input-group m-b-20" style="width:100%">
                        <input id="validCode"  name="validCode" class="form-control input-lg"  placeholder="请输入验证码" autocomplete="off"/>
                        <label id="getValidCode" class="input-group-addon" title="单击刷新验证码" style="width:96px;padding: 0px;"><img src="${rootUrl }app/common/validCodeImage.html?ee=1"></label>
                    </div>
                    <div class="login-buttons">
                        <a id="login_button" href="${rootUrl }app/common/login" class="btn btn-success btn-block btn-lg" >登录</a>
                    </div>
                </form>
                <div style="padding-top:20px;">
                	<a id="tplogin"  href="${oauthUrl}" class="btn btn-link btn-sm ">第三方登录</a>
                </div>
            </div>
        </div>
        <!-- end login -->
	</div>
	<!-- end page container -->
	
</body>
</html>
