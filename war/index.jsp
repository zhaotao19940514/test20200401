<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!-- 有sso时直接到首页，sso会拦截后跳到登录页面 -->
<c:redirect url="/app/index?myMenuId=index"/>
