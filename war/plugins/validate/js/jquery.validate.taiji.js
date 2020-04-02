// 手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) {
	var length = value.length;
	return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
 }, "请检查您的手机号码");

//电话号码验证
jQuery.validator.addMethod("phone", function(value, element) {
	var ph = /(^0[1-9][0-9]{1,2}\-[1-9][0-9]{6,7}$)|(^[1-9][0-9]{6,7}$)|(^0[1-9][0-9]{1,2}\-[1-9][0-9]{6,7}\-[0-9]{1,4}$)|(^[1-9][0-9]{6,7}\-[0-9]{1,4}$)|(^0{0,1}13[0-9]{9}$)|(^0{0,1}15[0-9]{9}$|^0{0,1}18[0-9]{9}$)/;
	return this.optional(element) || (ph.test(value));
	 }, "请检查您的电话号码");

//邮政编码验证
jQuery.validator.addMethod("zipcode", function(value, element) {
	var tel = /^\d{6}$/;
	return this.optional(element) || (tel.test(value));
 }, "请检查您的邮政编码");

//身份证---不建议使用
jQuery.validator.addMethod("idcard",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	if(value.length != 15 && value.length != 18) return false;//"身份证号共有 15 码或18位";
	var Ai=value.length==18?value.substring(0,17):value.slice(0,6)+"19"+value.slice(6,16);
	if (!/^\d+$/.test(Ai))  return false;//"身份证除最后一位外，必须为数字！";
	var yyyy=Ai.slice(6,10) ,  mm=Ai.slice(10,12)-1  ,  dd=Ai.slice(12,14);
	var d=new Date(yyyy,mm,dd) ,  now=new Date();
	var year=d.getFullYear() ,  mon=d.getMonth() , day=d.getDate();
	if (year!=yyyy || mon!=mm || day!=dd || d>now || year<1900) return false;//"身份证输入错误！";
	return true;
},"请检查您的身份证号码");

//身份证
jQuery.validator.addMethod("idCode",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	var exp = /(^[1-9]{1}[0-9]{17}$)|(^[1-9]{1}[0-9]{16}X$)|(^[1-9]{1}[0-9]{16}x$)/;
	var reg = value.match(exp);
	if(reg==null)
        return false;
   
	var inYear = (value.length==18)?value.substring(6,10):"19"+value.substring(6,8); 
	var inMonth = (value.length==18)?value.substring(10,12)-1:value.substring(8,10)-1; 
	var inDay = (value.length==18)?value.substring(12,14):value.substring(10,12); 
	var d = new Date(inYear,inMonth,inDay);
	var now = new Date();
	var year = d.getFullYear();
	if(year<1900)
		return false;
	var month = d.getMonth();
	var day = d.getDate();
	if (inYear!=year || inMonth!=month || inDay!=day || d>now || year<1800) return false;
	return true;
},"请检查您的身份证号码");

//身份证(更新机动车时使用)
jQuery.validator.addMethod("idCodeUpdate",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	var exp = /(^[1-9]{1}[0-9]{14}$)|(^[1-9]{1}[0-9]{17}$)|(^[1-9]{1}[0-9]{16}X$)|(^[1-9]{1}[0-9]{16}x$)/;
	var reg = value.match(exp);
	if(reg==null)
		return false;
	
	var inYear = (value.length==18)?value.substring(6,10):"19"+value.substring(6,8); 
	var inMonth = (value.length==18)?value.substring(10,12)-1:value.substring(8,10)-1; 
	var inDay = (value.length==18)?value.substring(12,14):value.substring(10,12); 
	var d = new Date(inYear,inMonth,inDay);
	var now = new Date();
	var year = d.getFullYear();
	if(year<1900)
		return false;
	var month = d.getMonth();
	var day = d.getDate();
	if (inYear!=year || inMonth!=month || inDay!=day || d>now || year<1800) return false;
	return true;
},"请检查您的身份证号码");

//台湾居民来往大陆通行证--10位数字
jQuery.validator.addMethod("twCnIdCode",function(value,element){
	var exp = /^\d{10}$/;
	//var exp=/^[0-9a-zA-Z]{10}$/;
	return this.optional(element) || (exp.test(value));
},"证件号码为10位数字");

//港澳居民来往内地通行证
jQuery.validator.addMethod("gaCnIdCode",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	if(value.length > 20) return false;
	return true;
},"请检查港澳居民来往内地通行证号码");

//港澳证件号码也是10位，8位证件号+2位版本号共10位，字符集只允许大写英文字母加数字组合，香港证件号码第一位为H
jQuery.validator.addMethod("HKGIdCode",function(value,element){
	var exp=/^H\d{10}$/;
	return this.optional(element) || (exp.test(value));
},"请 检查港澳居民来往内地通行证号码");

//港澳证件号码也是10位，8位证件号+2位版本号共10位，字符集只允许大写英文字母加数字组合，澳门证件号码第一位为M
jQuery.validator.addMethod("MACIdCode",function(value,element){
	var exp=/^M\d{10}$/;
	return this.optional(element) || (exp.test(value));
},"请 检查港澳居民来往内地通行证号码");

//军官证、士兵证、军官离退休证
jQuery.validator.addMethod("sgzIdCode",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	if(value.length > 20) return false;
	return true;
},"请检查 军官证、士兵证、军官离退休证 号码");

//护照
jQuery.validator.addMethod("passportCnIdCode",function(value,element){
	var reg=/^[0-9a-zA-Z]{1,20}$/;
	return this.optional(element) || (reg.test(value));
},"请检查护照号码");

//纯中文
jQuery.validator.addMethod("chinese",function(value,element){
	var exp = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (exp.test(value));
},"只能输入汉字");

//中文姓
jQuery.validator.addMethod("chineseName",function(value,element){
	var exp = /^[\u4e00-\u9fa5·\*]+$/;
	return this.optional(element) || (exp.test(value));
},"必须是中文");

//驾驶证号
jQuery.validator.addMethod("driverId",function(value,element){
	var exp = /(^[1-9]{1}[0-9]{11}$)|(^[1-9]{1}[0-9]{9}$)/;
	return this.optional(element) || (exp.test(value));
},"请检查您的驾驶证号");

//营业执照(登记证)注册号
jQuery.validator.addMethod("busLicence",function(value,element){
    var exp = /(^\d{6}$)|(^\d{13}$)|(^\d{15}$)/;
    return this.optional(element) || (exp.test(value));
},"请检查 营业执照(登记证)注册号码");


//国税纳税人识别号
jQuery.validator.addMethod("countryTaxCode",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	if(value.length > 20) return false;
	var reg=/^[^\u4e00-\u9fa5]+$/;
	if(null == value.match(reg))
        return false;
	return true;
},"请检查 国税纳税人识别号码");

//地税纳税人识别号
jQuery.validator.addMethod("localTaxCode",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	var localTax = $("input[name$='localTax']").val();
	if(localTax == null || localTax == '') return "dependency-mismatch";
	if(localTax == '0.00') {
		var countryTaxCode = $("input[name='countryTaxCode']").val();
		if(countryTaxCode == value) return true;
		else return false;
	}
	var exp=/^[0-9A-Z]{9}[0-9A-Z]{0,16}$/;
	var reg = value.match(exp);
	if(reg==null)
		return false;
	var orgCodeValue = $("input[id$='orgCode']").val();
	var tmpValue = value.substring(value.length -9);
	if(orgCodeValue != tmpValue)return false;
	return true;
	
},"请检查 地税纳税人识别号码");

//密码6-20位
jQuery.validator.addMethod("password",function(value,element){
	if ( this.optional(element) )
		return "dependency-mismatch";
	if(value.length < 6  || value.length > 20) return false;
	return true;
},"请检查密码长度");


//IP验证
jQuery.validator.addMethod("ip", function(value, element) {
	var tel =/^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/;
	return this.optional(element) || (tel.test(value));
 }, "请检查IP地址");

//域名验证
jQuery.validator.addMethod("domain", function(value, element) {
	var tel =/^(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/;
	return this.optional(element) || (tel.test(value));
 }, "请检查服务器地址");

//非汉字
jQuery.validator.addMethod("noCharacter", function(value, element) {
	//var reg = /^[\u4e00-\u9fa5]+$/;
	var reg=/^[^\u4e00-\u9fa5]+$/;
	return this.optional(element) || (reg.test(value));
 }, "不能输入中文");

//组织机构代码9位 数字+字母
jQuery.validator.addMethod("orgCode", function(value, element) {
	var reg=/^[0-9A-Z]{9}$/;
	return this.optional(element) || (reg.test(value));
}, "请检查组织机构代码格式");

//验证车牌号码
jQuery.validator.addMethod("licenceCode", function(value, element) {
	var reg=/^[0-9a-zA-Z]{1,15}$/;
	return this.optional(element) || (reg.test(value));
}, "请检查车牌号码格式");

jQuery.validator.addMethod("workCode", function(value, element) {
	var reg=/^[0-9a-zA-Z]{1,20}$/;
	return this.optional(element) || (reg.test(value));
}, "请检查工作居住证号格式");
//更新指标名字
jQuery.validator.addMethod("replacePersonName",function(value,element){
	var exp = /^[\u4e00-\u9fa5a-zA-Z·\*]+$/;
	return this.optional(element) || (exp.test(value));
},"请输入正确的名字");