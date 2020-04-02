<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
    $(function() {
        $.ajaxSetup({cache : false});
        $("#submit").click(function() {
            $("#myManage").triggerHandler("taijiModalPost", [ $("#editForm"), {
                table : "edit"
            } ]);
        });
        var name = '${editModel.serviceHall.name}';
        var serviceHallId = '${editModel.serviceHallId}';
        $("#editForm .chosen-single span").text(name);
        $("#serviceHallId").append('<option selected="selected" value="'+serviceHallId+'">'+name+'</option>');
    });
</script>
<style type="text/css">
     th{
         width：30%;
     }
</style>
</head>
<body>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
            aria-hidden="true">×</button>
        <h4 class="modal-title">工号管理--添加</h4>
    </div>
    <div class="modal-body">
        <form:form modelAttribute="editModel" id="editForm" name="editForm"
            cssClass="form-horizontal"
            action="${rootUrl }app/administration/staff/edit" method="post">
            <input type="hidden" name="id" value="${editModel.id }"/>
            <table class="table table-bordered">
                <th colspan="4">工号信息</th>
                <tr>
                    <th>服务网点:</th>
                    <td>
                        <form:select path="serviceHallId" cssClass="taiji_autocomplete form-control" data-url="${rootUrl }app/administration/servicehall/queryByNameForModal" data-placeholder="请选择网点" style="width:35.5em;">
                            <option></option>
                        </form:select>
                    </td>
                </tr>
                <tr>
					<th>资金渠道:</th>
					<td>
						<form:select path="accountId" cssClass="taiji_autocomplete form-control" data-url="${rootUrl }app/administration/agency/queryByNameForModal" data-placeholder="请选择资金渠道" style="width:35.5em;">
                            <option></option>
                        </form:select>
                    </td>
				</tr>
                <tr>
                    <th>员工编号:</th>
                    <td><form:input path="staffId" cssClass="form-control"
                            maxlength="32" placeholder="员工编号 必填" /></td>
                </tr>
                <tr>
                    <th>员工姓名:</th>
                    <td><form:input path="staffName" cssClass="form-control"
                            maxlength="32" placeholder="员工姓名 必填"/></td>
                </tr>
                <tr>
                    <td colspan="2" style="color:blue;">三个密码均不填写时视为不修改密码</td>
                </tr>
                <tr>
                    <th>原工号密码:</th>
                    <td><form:password path="originPassword"
                            class="form-control" maxlength="32" placeholder="原密码 必填" /></td>
                </tr>
                <tr>
                    <th>新工号密码:</th>
                    <td><form:password path="password"
                            class="form-control" maxlength="32" placeholder="新密码 必填" /></td>
                </tr>
                <tr>
                    <th>新工号确认密码:</th>
                    <td><form:password path="confirmPassword" 
                            class="form-control" maxlength="32" placeholder="新确认密码 必填" /></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a> <a
            href="#" class="btn btn-sm btn-success" id="submit">保存</a>
    </div>

</body>
</html>