<%@ page contentType="text/html;charset=utf-8"%>
<label class="control-label">每页条数</label>
<form:select path="pageSize"  cssClass="form-control">
	<form:option value="2">2</form:option>
	<form:option value="10">10</form:option>
	<form:option value="16">16</form:option>
	<form:option value="20">20</form:option>
	<form:option value="50">50</form:option>
	<form:option value="100">100</form:option>
</form:select>
