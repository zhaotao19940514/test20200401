<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="header" class="header navbar navbar-default navbar-fixed-top">
	<!-- begin container-fluid -->
	<div class="container-fluid">
		<!-- begin mobile sidebar expand / collapse button -->
		<div class="navbar-header">
			<a href="${rootUrl }app/index" class="navbar-brand">
<!-- 			<span class="taiji-logo"></span> -->
			黔通卡客服系统</a>
			<button type="button" class="navbar-toggle" data-click="sidebar-toggled">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		<!-- end mobile sidebar expand / collapse button -->
		
		<!-- begin header navigation right -->
		<ul class="nav navbar-nav navbar-right">
			<li>
				<div class="clock clearfix" data-correct-interval="30000">
					<div id="Date"></div>
					<ul>
						<li id="hours"></li>
					    <li id="point">:</li>
					    <li id="min"></li>
					    <li id="point">:</li>
					    <li id="sec"></li>
					</ul>
				</div>
			</li>
<!-- 			<li class="dropdown"> -->
<!-- 				<a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle f-s-14"> -->
<!-- 					<i class="fa fa-bell-o"></i> -->
<!-- 					<span class="label">0</span><span  >消息</span> -->
<!-- 				</a> -->
<!-- 			</li> -->
			<li class="dropdown navbar-user">
				<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
					<span class="hidden-xs "><i class="fa fa-user m-r-5"></i>${loginUser.name }<c:if test="${loginUser.staff ne null }">(${loginUser.staff.serviceHall.name})</c:if></span> <b class="caret"></b>
				</a>
				<ul class="dropdown-menu animated fadeInLeft">
					<li class="arrow"></li>
					<li><a href="${rootUrl }app/modPasswd" id="modPasswd"><i class="fa fa-key m-r-5"></i><span>修改密码</span> </a></li>
					<li><a href="${rootUrl }app/modMyself" id="modMyself" ><i class="fa fa-edit m-r-5"></i><span>修改信息</span> </a></li>
					<li class="divider"></li>
					<li><a href="${rootUrl }app/common/logout"  ><i class="fa fa-sign-out m-r-5"></i><span>退出</span> </a></li>
				</ul>
			</li>
		</ul>
		<!-- end header navigation right -->
	</div>
	<!-- end container-fluid -->
</div>