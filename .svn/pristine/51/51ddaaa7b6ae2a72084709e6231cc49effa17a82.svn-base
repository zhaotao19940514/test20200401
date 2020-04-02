<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<body>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h4 class="modal-title">车辆信息</h4>
	</div>
	<div class="modal-body" id="vehicleInfoView">
		<table class="table table-bordered">
			<tr>
				<th>车牌号:</th>
				<td>${fn:escapeXml(vo.vehiclePlate)}</td>
				<th>车牌颜色:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 0}">蓝色</c:if>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 1}">黄色</c:if>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 2}">黑色</c:if>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 3}">白色</c:if>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 4}">渐变绿色</c:if>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 5}">黄绿双拼色</c:if>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 6}">蓝白渐变色</c:if>
                    <c:if test="${fn:escapeXml(vo.vehiclePlateColor) eq 9}">未确认</c:if>
                </td>
			</tr>
			<tr>
				<th>用户编号</th>
				<td>${fn:escapeXml(vo.customerId)}</td>
				<th>所有人名称</th>
                <td>${fn:escapeXml(vo.ownerName)}</td>
			</tr>
			<tr>
				<th>所有人证件类型:</th>
				<td>
				    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 101}">身份证（含临时身份证）</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 102}">护照（限外籍人士）</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 103}">港澳居民来往内地通行证</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 104}">台湾居民来往大陆通行证</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 105}">军官证</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 106}">武警警察身份证</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 201}">统一社会信用代码证书</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 202}">组织机构代码证</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 203}">营业执照</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 204}">事业单位法人证书</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 205}">社会团体法人登记证书</c:if>
                    <c:if test="${fn:escapeXml(vo.ownerIdType) eq 206}">律师事务所执业许可证</c:if>
				</td>
				<th>所有人证件号码:</th>
				<td>${fn:escapeXml(vo.ownerIdNum)}</td>
			</tr>
			<tr>
				<th>所有人联系方式:</th>
				<td>${fn:escapeXml(vo.ownerTel)}</td>
				<th>录入方式:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.registeredType) eq 1}">线上</c:if>
                    <c:if test="${fn:escapeXml(vo.registeredType) eq 2}">线下</c:if>
                </td>
			</tr>
			<tr>
			    <th>所有人联系地址:</th>
                <td colspan="3">${vo.ownerAddress}</td>
			</tr>
			<tr>
			    <th>指定联系人列表:</th>
                <td colspan="3">${fn:escapeXml(vo.contacts)}</td>
			</tr>
			<tr>
				<th>渠道编号:</th>
				<td>${fn:escapeXml(vo.channelId)}</td>
                <th>录入时间:</th>
                <td>${fn:escapeXml(fn:replace(vo.registeredTime,'T',' '))}</td>
			</tr>
			<tr>
                <th>档案编号:</th>
                <td>${fn:escapeXml(vo.fileNum)}</td>
                <th>行驶证车辆类型:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.type) eq 1}">一型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 2}">二型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 3}">三型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 4}">四型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 11}">一型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 12}">二型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 13}">三型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 14}">四型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 15}">五型货车</c:if>
                </td>
            </tr>
            <tr>
                <th>行驶证品牌型号:</th>
                <td>${fn:escapeXml(vo.vehicleModel)}</td>
                <th>车辆使用性质:</th>
                <td>${fn:escapeXml(vo.useCharacter)}</td>
            </tr>
            <tr>
                <th>车辆识别代号:</th>
                <td>${fn:escapeXml(vo.VIN)}</td>
                <th>车辆发动机号码:</th>
                <td>${fn:escapeXml(vo.engineNum)}</td>
            </tr>
            <tr>
                <th>注册日期:</th>
                <td>${fn:escapeXml(vo.registerDate)}</td>
                <th>发证日期:</th>
                <td>${fn:escapeXml(vo.issueDate)}</td>
            </tr>
            <tr>
                <th>核定载人数:</th>
                <td>${fn:escapeXml(vo.approvedCount)}</td>
                <th>总质量:</th>
                <td>${fn:escapeXml(vo.totalMass)}</td>
            </tr>
            <tr>
                <th>整备质量:</th>
                <td>${fn:escapeXml(vo.maintenanceMass)}</td>
                <th>核定载质量:</th>
                <td>${fn:escapeXml(vo.permittedWeight)}</td>
            </tr>
            <tr>
                <th>外廓尺寸:</th>
                <td>${fn:escapeXml(vo.outsideDimensions)}</td>
                <th>准牵引总质量:</th>
                <td>${fn:escapeXml(vo.permittedTowWeight)}</td>
            </tr>
            <tr>
                <th>检验记录:</th>
                <td>${fn:escapeXml(vo.testRecord)}</td>
                <th>车轮数:</th>
                <td>${fn:escapeXml(vo.wheelCount)}</td>
            </tr>
            
            <tr>
                <th>车轴数:</th>
                <td>${fn:escapeXml(vo.axleCount)}</td>
                <th>轴距:</th>
                <td>${fn:escapeXml(vo.axleDistance)}</td>
            </tr>
            <tr>
                <th>轴型:</th>
                <td>${fn:escapeXml(vo.axisType)}</td>
                <th>收费车型:</th>
                <td>
                    <c:if test="${fn:escapeXml(vo.type) eq 1}">一型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 2}">二型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 3}">三型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 4}">四型客车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 11}">一型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 12}">二型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 13}">三型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 14}">四型货车</c:if>
                    <c:if test="${fn:escapeXml(vo.type) eq 15}">五型货车</c:if>
                </td>
            </tr>
            <tr>
                <th>创建时间:</th>
                <td>
                    <fmt:formatDate value="${vo.createTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <th>更新时间:</th>
                <td>
                    <fmt:formatDate value="${vo.updateTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
		</table>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	</div>

</body>
</html>