<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<tr>
	<td>
		<a href="${rootUrl }app/acl/user/view/${vo.id}" class="taiji_modal">${fn:escapeXml(vo.loginName)}</a>
	</td>
	<td><span><a class="editable" href="#" data-pk="${vo.id}" data-type="text" data-name="name" data-title="修改名字"  >
		${fn:escapeXml(vo.name)}
	</a></span></td>
	<td><span>${fn:escapeXml(vo.unit.name)}</span></td>
	<td>
	<a class="editable male" href="#" data-pk="${vo.id}" data-type="select" data-name="male" data-title="修改性别"  
		data-value="${vo.male?1:0}" data-source="{'1':'男','0':'女'}">
		${vo.male?"男":"女"}
		</a>
	</td>
	<td>${vo.status.value }</td>
	<td>
	<a class="editable" href="#" data-pk="${vo.id}" data-type="select" data-name="roleId" data-title="修改角色" 
		data-value="${vo.role.id}" data-source="${rootUrl }app/acl/role/getAll" data-mode="inline">
		${vo.role.name }
	</a>
	</td>
	<td>
	   ${fn:escapeXml(vo.staff.staffId)}
	</td>
	<td >
		<a href="${rootUrl }app/acl/user/info/${vo.id}" class="taiji_collapse taiji_acl" >查看（折叠）</a>
		<div class="dropdown " style="display: inline-block;">
		 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" ><i class="fa fa-navicon"></i></a>
			<ul class="dropdown-menu dropdown-menu-right"  >
			  	<li><a href="${rootUrl }app/acl/user/view/${vo.id}" class="taiji_modal taiji_acl" >查看（弹出）</a></li>
				<li><a href="${rootUrl }app/acl/user/edit/${vo.id}" class="taiji_modal taiji_acl">修改</a></li>
				<li>
				<c:if test="${vo.status=='NORMAL' }">
				<a href="${rootUrl }app/acl/user/status/${vo.id}/INVALID" data-selector="#userName" class="taiji_operate {confirm_message:'是否停用用户:${vo.name} ',refresh:true} taiji_acl">停用</a>
				</c:if>
				<c:if test="${vo.status=='INVALID' }">
				<a href="${rootUrl }app/acl/user/status/${vo.id}/NORMAL"  data-selector="#userName" class="taiji_operate {confirm_message:'是否启用用户:${vo.name} ',refresh:true} taiji_acl">启用</a>
				</c:if>
			  </li>
			</ul>
		</div>
		
	</td>
</tr>
