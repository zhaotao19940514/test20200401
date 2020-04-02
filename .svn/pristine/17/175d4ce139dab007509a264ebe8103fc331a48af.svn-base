<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div id="sidebar" class="sidebar">
	<!-- begin sidebar scrollbar -->
	<div data-scrollbar="true" data-height="100%">
		<!-- begin sidebar nav -->
				
			<ul class="nav">
				<li class="nav-header "  class="nav-profile">导航菜单条</li>
				<li id="index">
					<a href="${rootUrl}app/index?myMenuId=index">
					<i class="fa fa-home"></i>
					<span>首页</span></a>
				</li>
				<c:forEach items="${roleMenu}" var="menu" varStatus="menuStatus" >
				<li class="has-sub " id="menu${menuStatus.count }">
					<a href="#">
						<i class="${menu.type.logoClass}"></i>
						<b class="caret pull-right"></b> 
						<span>${menu.type.value }</span>
					</a>
					<ul class="sub-menu">
					<c:forEach items="${menu.columnMenus}" var="columnMenu">
						<c:choose>
						<c:when test="${columnMenu.hasTabMenu }">
							<li class="has-sub"  id="${columnMenu.columnResource.id}">
								<a href="#">
						            <b class="caret pull-right"></b>
						           ${fn:escapeXml(columnMenu.columnResource.name) }
						        </a>
								<ul class="sub-menu">
									<c:forEach items="${columnMenu.tabResources }" var="tabResource">
										<li  id="${ tabResource.id}"><a href="${rootUrl }${tabResource.url}?myMenuId=${tabResource.id}"><span>${fn:escapeXml(tabResource.name) }</span></a></li>
									</c:forEach>
								</ul>
							</li>
						</c:when>
						<c:otherwise>
							<li id="${columnMenu.columnResource.id}" ><a href="${rootUrl}${columnMenu.columnResource.url }?myMenuId=${columnMenu.columnResource.id}"><span>${fn:escapeXml(columnMenu.columnResource.name) }</span></a></li>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</ul>
				</li>
				
				</c:forEach>
			       <!-- begin sidebar minify button -->
				<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
			       <!-- end sidebar minify button -->
			</ul>

				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>		
		<div class="sidebar-bg"></div>	