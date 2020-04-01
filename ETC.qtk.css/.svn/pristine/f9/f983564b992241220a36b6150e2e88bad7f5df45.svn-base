<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<script>
function doPrint(){
	window.print();
}
</script>
<style type="text/css">
    .blanktd{
        width:15%;
    },
    body {
	   text-align: center;
    }
    
/*     td{ */
/* 	    word-wrap:break-word; */
/* 	    word-break:break-all; */
/*     } */
</style>
</head>
<body>
<div  id="myView" style="float: left;width: 100%;">
	<table class="table " id="receiptTable" style="width:60vw;margin-left:20vw;">
        <tr>
            <th colspan="3">${fn:escapeXml(vo.log.serviceType.shortCnName)}回执</th>
        </tr>
        <tr>
            <td >办理网点:${fn:escapeXml(vo.operator.staff.serviceHall.name)}</td>
            <td class="blanktd"></td>
            <td >办理时间:${fn:escapeXml(vo.log.operateTime)}</td>
        </tr>
        <c:if test="${fn:escapeXml(vo.customerInfo ne null)}">
	        <tr>
	            <td >用户:${fn:escapeXml(vo.customerInfo.customerName)}</td>
	            <td class="blanktd"></td>
	            <td></td>
	        </tr>
	        <tr>
                <td >证件类型:${fn:escapeXml(vo.customerIDType.value)}</td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
            <tr>
                <td >证件号:
                    <c:if test="${fn:length(vo.customerInfo.customerIdNum) gt 7 }">
	                    ${fn:escapeXml(fn:substring(vo.customerInfo.customerIdNum,0,4))}
	                    *****
	                    ${fn:escapeXml(fn:substring(vo.customerInfo.customerIdNum,
	                        fn:length(vo.customerInfo.customerIdNum) - 4,fn:length(vo.customerInfo.customerIdNum)))}
                    </c:if>
                    <c:if test="${fn:length(vo.customerInfo.customerIdNum) lt 8 }">
                        ${fn:escapeXml(fn:substring(vo.customerInfo.customerIdNum,0,2))}
                        *****
                        ${fn:escapeXml(fn:substring(vo.customerInfo.customerIdNum,
                            fn:length(vo.customerInfo.customerIdNum) - 2,fn:length(vo.customerInfo.customerIdNum)))}
                    </c:if>
                </td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.vehiclePlate ne null)}">
            <tr>
                <td >车牌号:${fn:escapeXml(vo.vehiclePlate)}</td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.cardId ne null)}">
            <tr>
                <td >卡号:${fn:escapeXml(vo.cardId)}</td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.chargeDetail ne null)}">
            <tr>
                <td >
                                                            充值前金额:
                    <fmt:formatNumber type="number" value="${vo.chargeDetail.preBalance/100}" maxFractionDigits="2"/>
                                                            元
                </td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.chargeDetail ne null)}">
            <tr>
                <td >充值金额:
					<fmt:formatNumber type="number" value="${vo.chargeDetail.rechargeAmount/100}" maxFractionDigits="2"/>
					元
				</td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.chargeDetail ne null)}">
            <tr>
                <td >
                                                                        充值后金额:
                        <fmt:formatNumber type="number" value="${(vo.chargeDetail.preBalance + vo.chargeDetail.rechargeAmount)/100}" maxFractionDigits="2"/>
                                                                        元
                </td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.tradeFee ne null)}">
            <tr>
                <td >
                                                                        账户充值金额:
                        <fmt:formatNumber type="number" value="${vo.tradeFee/100}" maxFractionDigits="2"/>
                                                                        元
                </td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.chargeType ne null)}">
            <tr>
                <td >
                                                                        账户充值交费方式:
                        ${fn:escapeXml(vo.chargeType.value)}
                </td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <c:if test="${fn:escapeXml(vo.obuId ne null)}">
            <tr>
                <td >obu号:${fn:escapeXml(vo.obuId)}</td>
                <td class="blanktd"></td>
                <td></td>
            </tr>
        </c:if>
        <tr>
            <td >操作员工号:${fn:escapeXml(vo.operator.staff.staffId)}</td>
            <td class="blanktd"></td>
            <td >
                                                        打印时间:
                   <javatime:format value="${vo.printTime }"  pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
	</table>
    <div style="width:100%;text-align: center;">
        <button id="printBtn" type="button" onclick="doPrint();">打印回执</button>
    </div>
</div>
</body>
</html>