<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
	$(function(){
// 		$("#myView").taiji();
	    $("#viewTable").taiji();
	    var childrenCount = $("#dynamicTr").children().length;
	    if(childrenCount>4){
	    	$("#dynamicTr").after("<tr id='firstDynamicTr'></tr>");
	    	if(childrenCount==6){
	    		var eleFive = $("#dynamicTr th:first").next().next().next().next();
	    		var eleSix = eleFive.next();
	    		$("#firstDynamicTr").append(eleFive);
	    		$("#firstDynamicTr").append(eleSix);
	    	}else if (childrenCount==8) {
	    		var eleFive = $("#dynamicTr th:first").next().next().next().next();
                var eleSix = eleFive.next();
                $("#firstDynamicTr").append(eleFive);
                $("#firstDynamicTr").append(eleSix);
			}else{
                $("#firstDynamicTr").after("<tr id='secondDynamicTr'></tr>");
                var eleFive = $("#dynamicTr th:first").next().next().next().next();
                var eleSix = eleFive.next();
                $("#secondDynamicTr").append(eleFive);
                $("#secondDynamicTr").append(eleSix);
			}
	    }
	});
</script>
<style type="text/css">
    td{
	    word-wrap:break-word;
	    word-break:break-all;
    }
</style>
</head>
<body>
<div class="modal-header" style="float: left;width: 100%;">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title" style="text-align: center;">日志详情</h4>
</div>
<div class="modal-body" id="myView" style="float: left;width: 100%;">
<table class="table table-bordered" id="viewTable">
<!-- 	 	<tr> -->
<!-- 			<th colspan="8">日志详情</th> -->
<!-- 		</tr> -->
		<tr>
			<th >请求方IP:</th>
			<td >${fn:escapeXml(vo.remoteIp)}</td>
			<th >服务器IP:</th>
            <td>${fn:escapeXml(vo.serverIp)}</td>
		</tr>
		<tr>
		    <th >请求地址:</th>
            <td colspan="3">${fn:escapeXml(vo.url)}</td>
		</tr>
		<tr>
		    <th >操作者用户名:</th>
            <td >
                <a href="${rootUrl }app/system/operateLog/viewUser/${vo.operatorId}" id="a_userView" class="taiji_modal">${fn:escapeXml(vo.username)}</a>
            </td>
		    <th >操作时间:</th>
            <td >${fn:escapeXml(vo.operateTime)}</td>
		</tr>
		<tr>
            <th >创建时间:</th>
            <td >${fn:escapeXml(vo.createTime)}</td>
            <th >业务类型:</th>
            <td >${fn:escapeXml(vo.serviceName)}</td>
		</tr>
		<tr>
		    <th >操作类型:</th>
            <td >${fn:escapeXml(vo.operateType.value)}</td>
            <th >操作项:</th>
            <td >${fn:escapeXml(vo.operateItem)}</td>
        <tr id="dynamicTr">
            <c:if test="${fn:escapeXml(vo.relatedCustomerId ne null)}">
	            <th >关联用户编号:</th>
	            <td >
	               <a href="${rootUrl }app/system/operateLog/viewCustomer/${vo.relatedCustomerId}" class="taiji_modal">${fn:escapeXml(vo.relatedCustomerId)}</a>
	            </td>
	        </c:if>
<!--         </tr> -->
<!-- 		 <tr> -->
			<c:if test="${fn:escapeXml(vo.relatedVehicleId ne null)}">
	            <th >关联车辆编号:</th>
	            <td>
	               <a href="${rootUrl }app/system/operateLog/viewVehicleInfo/${vo.relatedVehicleId}" class="taiji_modal">
    	               ${fn:escapeXml(vo.relatedVehicleId)}
	               </a>
	            </td>
	        </c:if>
            <c:if test="${fn:escapeXml(vo.relatedCardId ne null)}">
	            <th >关联卡编号:</th>
	            <td >
	               <a href="${rootUrl }app/system/operateLog/viewCardInfo/${vo.relatedCardId}" class="taiji_modal">
	                   ${fn:escapeXml(vo.relatedCardId)}
	               </a>
	            </td>
            </c:if>
<!--         </tr> -->
<!--         <tr> -->
            <c:if test="${fn:escapeXml(vo.relatedObuId ne null)}">
	            <th >关联obu编号:</th>
	            <td >
	               <a href="${rootUrl }app/system/operateLog/viewObuInfo/${vo.relatedObuId}" class="taiji_modal">
	                   ${fn:escapeXml(vo.relatedObuId)}
	               </a>
	            </td>
            </c:if>
	        <c:if test="${fn:escapeXml(vo.relatedRechargeId ne null)}">
	            <th >关联充值流水号:</th>
	            <td colspan="3">
	               <a href="${rootUrl }app/system/operateLog/viewChargeDetail/${vo.relatedRechargeId}" class="taiji_modal_lg">
	                   ${fn:escapeXml(vo.relatedRechargeId)}
	               </a>
	            </td>
	        </c:if>
        </tr>
        <tr>
            <th >日志描述:</th>
            <td colspan="3">${fn:escapeXml(vo.discription)}</td>
        </tr>
<!--         <tr> -->
<!--             <th >操作数据:</th> -->
<%--             <td colspan="7">${fn:escapeXml(vo.data)}</td> --%>
<!--         </tr> -->
        <tr>
            
        </tr>
</table>
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
</div>
</body>
</html>