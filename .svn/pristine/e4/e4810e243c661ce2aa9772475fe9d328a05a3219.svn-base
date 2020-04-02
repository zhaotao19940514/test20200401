<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div> 
<div id="taiji_search_data">
	<table class="table table-striped table-bordered  table-hover ">
		<thead>
			<tr>
				<th style="width:150px" >序号</th>
				<th style="min-width:160px">单位</th>
				<th style="min-width:160px">角色名称</th>
				<th ><span>描述</span></th>
				<th style="min-width:120px" class="taiji_sortable {orderBy:'list',desc:false}
				<c:if test="${queryModel.orderBy eq 'list' }">${queryModel.desc?'taiji_desc':'taiji_asc' }</c:if> "
				>排序号${queryModel.desc?'降序':'升序' }${not empty randomBoolean&&randomBoolean?'（动态表头）':''}
				</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roles}" var="vo"  varStatus="voStatus">
				<%@ include file="row.jsp"%>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="taiji_search_pager" >
	 <label id="taiji_search_totalcount">${pagn.totalCount }</label>
    <label id="taiji_search_pagecount">${pagn.pageCount }</label>
    <label id="taiji_search_pageNo">${pagn.currentPage }</label>
</div>
</div>
