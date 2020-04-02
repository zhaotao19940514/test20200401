<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
    $(function() {
        $.ajaxSetup({
            cache : false
        });
        $("#submit").click(function() {
            $("#myManage").triggerHandler("taijiModalPost", [ $("#editForm"), {
                table : "edit"
            } ]);
        });
        $("#showAgencyId").text("机构编号:"+$("#agencyId").val());
        $("#agencyId").change(function(){
            $("#showAgencyId").text("");
            $("#showAgencyId").text("机构编号:"+$("#agencyId").val());
        });
    });
</script>
</head>
<body>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
            aria-hidden="true">×</button>
        <h4 class="modal-title">网点设备配置-按机构批量配置</h4>
    </div>
    <div class="modal-body">
        <form:form modelAttribute="editModel" id="editForm" name="editForm"
            cssClass="form-horizontal"
            action="${rootUrl }app/administration/servicehall/deviceconfig/editByAgency" method="post">
            <table class="table table-bordered">
                <tr>
                    <th><label class="control-label">机构编号:</label></th>
	                <td>
	                    <form:select path="agencyId" cssClass="form-control">
	                        <form:option value="">请选择</form:option>
	                        <form:options items="${agencies}" itemLabel="name" itemValue="agencyId"/>
	                    </form:select>
	                    <div id="showAgencyId" style="color:blue;"></div>
	                </td>
                </tr>
                <tr>
                    <th><label class="control-label">卡设备类型</label></th>
                    <td>
                        <form:select path="cardDeviceType">
                            <form:option value="">请选择</form:option>
                            <form:options itemLabel="value" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th><label class="control-label">OBU设备类型</label></th>
                    <td>
                        <form:select path="obuDeviceType">
                            <form:option value="">请选择</form:option>
                            <form:options itemLabel="value" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th><label class="control-label">POS设备类型</label></th>
                    <td>
                        <form:select path="posDeviceType">
                            <form:option value="">请选择</form:option>
                            <form:options itemLabel="value" />
                        </form:select>
                    </td>
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