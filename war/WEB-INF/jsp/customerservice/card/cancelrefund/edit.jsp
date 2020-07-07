<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			provincePut();
			if($('#province').val()!=''&&$('#province').val()!='undefined'){
				sellPut($('#province').val());
			}
			var bankType = $('#bankType').val();
			bankTest(bankType);
			var cusType='${refund.cusType}';
			var refundType='${refund.operateType}';
			var administrative = '${administrative}';
			$.ajaxSetup({
				cache : false
			});
			
			if(administrative!='true'){
				 $("#selRefundType").attr("disabled",true);
			}
			if(cusType==1){
				document.getElementById("bankAddres").style.display='none';
				$("#fileForm").hide();
				 $("#cusName").attr("disabled",true);
			}else{
				if('${customerName}'!='${refund.cusName}'){
					$("#fileForm").show();
				}else{
					$("#fileForm").hide();
				}
			}
			if(refundType!='BANK_CARD'&&refundType!='CASHTOBANK'){
				document.getElementById("selBankType").style.display='none';
				document.getElementById("selCusName").style.display='none';
				document.getElementById("selBankCardId").style.display='none';
				document.getElementById("banktr").style.display='none';
				document.getElementById("bankAddres").style.display='none';
			}
			$("#province").bind("change", function(){
				sellPut($('#province').val());
			});
			$("#submit").click(function(){
				var cusName = $("#cusName").val();
				var cusTel = $("#cusTel").val();
				var selRefundType = $("#selRefundType").val();
				var branchName = $("#branchName").val();
				if(selRefundType==1||selRefundType==4){
					var bankCardId = $("#bankCardId").val().replace(/\s*/g,'');
					if(""==bankCardId){
						$.Taiji.showWarn("银行卡号不能为空!");
						return;
					}
					var regStr = /^([1-9]{1})(\d{11,30})$/;
					if(!regStr.test(bankCardId)){
						$.Taiji.showWarn("请输入正确的银行卡号!");
						return;
					}
					/* if(null==cusName||cusName=='undefined'||cusName==''){
						$.Taiji.showWarn("开户人名称不能为空!");
						return;
					} */
					if(null==cusTel||cusTel=='undefined'||cusTel==''){
						$.Taiji.showWarn("请输入联系人手机号!");
						return;
					}
					var telReg=/^[1][3,4,5,7,8][0-9]{9}$/;
					if(!telReg.test(cusTel)){
						$.Taiji.showWarn("请输入正确的手机号!");
						return;
					}
					if(cusType==2&&(bankType==14||bankType==15)){
						if(branchName==null||branchName=='undefined'||branchName==''){
							$.Taiji.showWarn("请输入支行名称!");
							return;
						}
					}
				}	
				updateCancelData('${refund.cardId}');
			});
			function provincePut(){
				var map = getMap();
				var list = new Array();
				map.forEach(function (value, key, map) {
		            list.push(key);
		        });
				var provinceHtml='<option value=${refund.province}>${refund.province}</option>';
				for(var i=0;i<list.length;i++){
					provinceHtml+='<option value='+list[i]+'>'+list[i]+'</option>';
				}
				$("#province").html(provinceHtml);
			}
			function sellPut(key){
				var map = getMap();
				var sellmap = map.get(key);
				var list = sellmap.split(",");
				var sellHtml='<option value=${refund.sell}>${refund.sell}</option>';
				for(var i=0;i<list.length;i++){
					sellHtml+='<option value='+list[i]+'>'+list[i]+'</option>';
				}
				$("#sell").html(sellHtml);
			}
			function getMap(){
				var map = new Map();
				map.set("贵州省","贵阳市,黔东南州,黔南州,遵义市,黔西南州,毕节地区,铜仁地区,安顺市,六盘水市");
				map.set("四川省","成都市,绵阳市,广元市,达州市,南充市,德阳市,广安市,阿坝州,巴中市,遂宁市,内江市,凉山州,攀枝花市,乐山市,自贡市,泸州市,雅安市,宜宾市,资阳市,眉山市,甘孜州");
				map.set("北京市","北京市");
				map.set("重庆市","重庆市");
				map.set("上海市","上海市");
				map.set("天津市","天津市");
				map.set("广东省","东莞市,广州市,中山市,深圳市,惠州市,江门市,珠海市,汕头市,佛山市,湛江市,河源市,肇庆市,潮州市,清远市,韶关市,揭阳市,阳江市,云浮市,茂名市,梅州市,汕尾市");
				map.set("山东省","济南市,青岛市,临沂市,济宁市,菏泽市,烟台市,泰安市,淄博市,潍坊市,日照市,威海市,滨州市,东营市,聊城市,德州市,莱芜市,枣庄市");
				map.set("江苏省","苏州市,徐州市,盐城市,无锡市,南京市,南通市,连云港市,常州市,扬州市,镇江市,淮安市,泰州市,宿迁市");
				map.set("河南省","郑州市,南阳市,新乡市,安阳市,洛阳市,信阳市,平顶山市,周口市,商丘市,开封市,焦作市,驻马店市,濮阳市,三门峡市,漯河市,许昌市,鹤壁市,济源市");
				 map.set("河北省","石家庄市,唐山市,保定市,邯郸市,邢台市,河北区,沧州市,秦皇岛市,张家口市,衡水市,廊坊市,承德市");
				map.set("浙江省","温州市,宁波市,杭州市,台州市,嘉兴市,金华市,湖州市,绍兴市,舟山市,丽水市,衢州市");
				map.set("陕西省","西安市,咸阳市,宝鸡市,汉中市,渭南市,安康市,榆林市,商洛市,延安市,铜川市");
				map.set("西藏区","拉萨市,山南地区,林芝地区,日喀则地区,阿里地区,昌都地区,那曲地区");
				map.set("云南省","昆明市,红河州,大理州,文山州,德宏州,曲靖市,昭通市,楚雄州,保山市,玉溪市,丽江地区,临沧地区,思茅地区,西双版纳州,怒江州,迪庆州");
				map.set("湖南省","长沙市,邵阳市,常德市,衡阳市,株洲市,湘潭市,永州市,岳阳市,怀化市,郴州市,娄底市,益阳市,张家界市,湘西州");
				map.set("福建省","漳州市,泉州市,厦门市,福州市,莆田市,宁德市,三明市,南平市,龙岩市");
				map.set("安徽省","芜湖市,合肥市,六安市,宿州市,阜阳市,安庆市,马鞍山市,蚌埠市,淮北市,淮南市,宣城市,黄山市,铜陵市,亳州市,池州市,巢湖市,滁州市");
				map.set("海南省","三亚市,海口市,琼海市,文昌市,东方市,昌江县,陵水县,乐东县,五指山市,保亭县,澄迈县,万宁市,儋州市,临高县,白沙县,定安县,琼中县,屯昌县");
				map.set("江西省","南昌市,赣州市,上饶市,吉安市,九江市,新余市,抚州市,宜春市,景德镇市,萍乡市,鹰潭市");
				map.set("湖北省","武汉市,宜昌市,襄樊市,荆州市,恩施州,孝感市,黄冈市,十堰市,咸宁市,黄石市,仙桃市,随州市,天门市,荆门市,潜江市,鄂州市,神农架林区");
				map.set("山西省","太原市,大同市,运城市,长治市,晋城市,忻州市,临汾市,吕梁市,晋中市,阳泉市,朔州市");
				map.set("辽宁省","大连市,沈阳市,丹东市,辽阳市,葫芦岛市,锦州市,朝阳市,营口市,鞍山市,抚顺市,阜新市,本溪市,盘锦市,铁岭市");
				map.set("黑龙江","齐齐哈尔市,哈尔滨市,大庆市,佳木斯市,双鸭山市,牡丹江市,鸡西市,黑河市,绥化市,鹤岗市,伊春市,大兴安岭地区,七台河市");
				map.set("甘肃省","兰州市,天水市,庆阳市,武威市,酒泉市,张掖市,陇南地区,白银市,定西地区,平凉市,嘉峪关市,临夏回族自治州,金昌市,甘南州");
				map.set("青海省","西宁市,海西州,海东地区,海北州,果洛州,玉树州,黄南藏族自治州");
				map.set("吉林省","吉林市,长春市,白山市,白城市,延边州,松原市,辽源市,通化市,四平市");
				map.set("广西壮族自治区","贵港市,玉林市,北海市,南宁市,柳州市,桂林市,梧州市,钦州市,来宾市,河池市,百色市,贺州市,崇左市,防城港市"); 
				map.set("内蒙古自治区","赤峰市,包头市,通辽市,呼和浩特市,乌海市,鄂尔多斯市,呼伦贝尔市,兴安盟,巴彦淖尔盟,乌兰察布盟,锡林郭勒盟,阿拉善盟");
				map.set("新疆维吾尔自治区","乌鲁木齐市,伊犁州,昌吉州,石河子市,哈密地区,阿克苏地区,巴音郭楞州,喀什地区,塔城地区,克拉玛依市,和田地区,阿勒泰州,吐鲁番地区,阿拉尔市,博尔塔拉州,五家渠市,克孜勒苏州,图木舒克市");
				map.set("宁夏回族自治区","银川市,吴忠市,中卫市,石嘴山市,固原市");
				map.set("台湾省","台北市,高雄市,台中市,新竹市,基隆市,台南市,嘉义市");
				map.set("澳门特别行政区","澳门特别行政区");
				map.set("香港特别行政区","香港特别行政区");
				return map;
			}	
		});
		function refundTypeChange(){
			var selRefundType = $("#selRefundType").val();
			if(selRefundType==1||selRefundType==4){
				document.getElementById("selBankType").style.display='';
				document.getElementById("selCusName").style.display='';
				document.getElementById("selBankCardId").style.display='';
				document.getElementById("banktr").style.display='';
				if(cusType==1){
					document.getElementById("bankAddres").style.display='none';
					$("#fileForm").hide();
					 $("#cusName").attr("disabled",true);
				}else{
					if('${customerName}'!='${refund.cusName}'){
						$("#fileForm").show();
					}else{
						$("#fileForm").hide();
					}
				}
			}else if(selRefundType==2){
				document.getElementById("selBankType").style.display='none';
				document.getElementById("selCusName").style.display='none';
				document.getElementById("selBankCardId").style.display='none';
				document.getElementById("banktr").style.display='none';
				document.getElementById("bankAddres").style.display='none';
				
			}
		}
		function onblurText(){
			var customerName = '${customerName}';
			var cusName = $("#cusName").val();
			var cusType='${refund.cusType}';
			if(cusType==2){
				if(''!=cusName&&cusName!='undefined'){
					if(cusName!=customerName){
						$("#fileForm").show();
					}else{
						$("#fileForm").hide();
					}
				}
			}
			
		}
		//单位用户根据选择的银行确定是否需要填写支行
		function bankTest(v) {
			var cusType = '${refund.cusType}';
			if(cusType==2&&(v==14||v==15)){
				$("#branchName1").show();
			}else{
				$("#branchName1").hide();
			}
		}
		</script>
	</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">修改注销信息</h4>
</div>
<div class="modal-body">
<form:form modelAttribute="pageModelEdit" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/customerservice/card/loss/edit" method="post">
	<table class="table table-bordered table-striped">
	<tr>
			<td>
				<label for='cusType' class="control-label"><b>开户类型</b></label>
			</td>
			 <td>
			 	<label id='cusType'>
				 	<c:if test="${refund.cusType==1}">
						<h4>个人</h4>
					</c:if>
					<c:if test="${refund.cusType==2}">
						<h4>单位</h4>
					</c:if>
			 	</label>
			 		
				
			</td> 
		</tr>
		 <tr id='selBankType'>
			<td>
				<label  class="control-label"><b>请选择开户行</b></label>
			</td>
			 <td>
			<select id="bankType" style="width:100%;height:35px"  title="" data-style="btn-warning" onchange="bankTest(this.value);">
					<c:forEach var="ba" items="${bankType}">
						<c:if test="${ba.code eq refund.bankType}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if> 
					</c:forEach>
					<c:forEach var="ba" items="${bankType}">
						<c:if test="${ba.code ne refund.bankType}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if> 
					</c:forEach>
			</select>
			
			</td> 
		</tr>
		<tr id="branchName1">
			<td>
				<label for='branchName' class="control-label">请输入支行名称</label>
			</td>
			<td>
				<form:input path="branchName" cssClass="form-control  m-r-5" value="${refund.branchName}" required="required" htmlEscape="false" disabled="false" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"/>
			</td>
		</tr>
		<tr id='bankAddres'>
			<td>
				<label for='bankAddres' class="control-label"><b>请选择开户行所在省、市</b></label>
			</td>
			 <td>
				<select id='province' style='height:30px;width:100px'>
				</select>
				<select id='sell' style='height:30px;width:100px'>
				</select>
			</td> 
		</tr>
		<tr>
			<td class="titile">退款方式:</td>
			<td class="details">
				<select id="selRefundType" style="width:100%;height:35px"  title="" data-style="btn-warning" onchange='refundTypeChange();'>
					<c:forEach var="ba" items="${accountCardBalanceOperateType}">
						<c:if test="${ba eq refund.operateType}">
							<option value="${ba.code}">${ba.value } </option>
						</c:if>
					</c:forEach> 
					<c:forEach var="ba" items="${accountCardBalanceOperateType}">
						<c:if test="${refund.operateType eq 'BANK_CARD'&& ba ne refund.operateType && ba ne 'ACCOUNT' &&ba ne 'BALANCE_SUPPLY' && ba ne 'CASHTOBANK'}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if>
						<c:if test="${refund.operateType eq 'CASH'&& ba ne refund.operateType && ba ne 'ACCOUNT' &&ba ne 'BALANCE_SUPPLY' && ba ne 'BANK_CARD'}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if>
						<c:if test="${refund.operateType eq 'BALANCE_SUPPLY'&& ba ne refund.operateType && ba ne 'ACCOUNT'  && ba ne 'CASHTOBANK'}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if>
						<c:if test="${refund.operateType eq 'ACCOUNT'&& ba ne refund.operateType && ba ne 'BALANCE_SUPPLY'  && ba ne 'CASHTOBANK'}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if>
						<c:if test="${refund.operateType eq 'CASHTOBANK'&& ba ne refund.operateType && ba ne 'ACCOUNT' &&ba ne 'BALANCE_SUPPLY' && ba ne 'BANK_CARD'}">
							<option value="${ba.code }">${ba.value }</option>
						</c:if>
					</c:forEach> 
				</select>
			</td>
				</tr>
		<tr id='selCusName'>
			<td>
				<label class="control-label">请输入开户人名称</label>
			</td>
			<td>
				<form:input path="cusName" cssClass="form-control  m-r-5" value="${refund.cusName }" required="required" htmlEscape="false" disabled="false" onblur="onblurText(); "/>
			</td>
		</tr>
		<tr id='selBankCardId'>
			<td>
				<label class="control-label">请输入银行卡号</label>
			</td>
			<td>
				<form:input path="bankCardId" cssClass="form-control  m-r-5" onkeyup="this.value =this.value.replace(/\s/g,'').replace(/[^\d]/g,'').replace(/(\d{4})(?=\d)/g,'$1 ');"
				required="required" value="${refund.bankCardId }" htmlEscape="false"/>
			</td>
		</tr>
		<tr id = "banktr">
			<td>
				<label class="control-label">请输入联系方式</label>
			</td>
			<td>
				<form:input path="cusTel" cssClass="form-control  m-r-5" value="${refund.cusTel }" required="required" htmlEscape="false"/>
			</td>
		</tr> 
			</table>
	
</form:form>
<!-- 图片上传 -->
<form:form action="${rootUrl }app/customerservice/card/cancel/fileUpload"  id="fileForm" method="post" enctype="multipart/form-data"  cssClass="form-horizontal" >
	<table class="table table-bordered table-striped">
		<tr>
			<td>
				<input type="hidden" id='cardId' name='cardId' value='${cardId}'/>
				<label for='attach' class="control-label"><b>请上传附件</b></label>
			</td>
			 <td>
				<input id="file" type="file" class="form-control" multiple="multiple" accept="image/png,image/jpg,image/jpeg" name="file" />
				<p style="font-size: 10px;color: red;">注：只能上传png、jpg、jpeg格式的照片！</p>
			</td> 
		</tr>

			</table>
</form:form>	
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" id="closeBtn" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>