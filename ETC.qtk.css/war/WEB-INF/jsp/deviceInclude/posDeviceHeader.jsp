<c:if test="${loginUser.staff.serviceHall.config.posDeviceType eq 'ICBC_MISPOS' }">
	<script type='text/javascript' src='${rootUrl}myjs/chromeversion/misposClient.js'></script>
</c:if>

<c:if test="${loginUser.staff.serviceHall.config.posDeviceType eq 'ICBC_MISPOS_IE' }">
    <script type='text/javascript' src='${rootUrl}myjs/ieversion/misposClient.js'></script>
</c:if>