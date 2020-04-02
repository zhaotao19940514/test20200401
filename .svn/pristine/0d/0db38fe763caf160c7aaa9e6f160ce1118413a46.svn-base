package cn.com.taiji.css.manager.util;

import java.util.Calendar;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomUtils;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;

/**
 * 
 * 
 * 
 * @author Tom <br>
 *         <a href="mailto:tomesc@msn.com">tomesc@msn.com</a>
 * @since 1.0
 * @version 1.0
 */
public class MyPatterns
{
	// 只能18位,最后一位如果是X,必须是大写
	public static final String idCodeRegexp = "^[1-9]{1}[0-9]{17}$|^[1-9]{1}[0-9]{16}X$";
	public static final String mobileRegexp = "^13[0-9]{9}|14[0-9]{9}|15[0-9]{9}|17[0-9]{9}|18[0-9]{9}|199[0-9]{8}|16[0-9]{9}$";
	public static final String phoneRegexp = "^0[1-9][0-9]{1,2}\\-?[1-9][0-9]{6,7}$|^[1-9][0-9]{6,7}$|^0[1-9][0-9]{1,2}\\-?[1-9][0-9]{6,7}\\-[0-9]{1,4}$|^[1-9][0-9]{6,7}\\-[0-9]{1,4}$|^0{0,1}13[0-9]{9}$|^0{0,1}14[0-9]{9}$|^0{0,1}15[0-9]{9}$|^0{0,1}17[0-9]{9}$|^0{0,1}18[0-9]{9}$|^0{0,1}199[0-9]{8}$|16[0-9]{9}";
	public static final String zipRegexp = "^\\d{6}$";
	public static final String smsCodeRegexp = "^\\d{6}$";
	public static final String validCodeRegexp = "^[0-9a-zA-Z]{4}$";
	public static final String driverIdRegexp = "^[1-9]{1}[0-9]{11}$|^[1-9]{1}[0-9]{9}$";
	public static final String emailRegexp = "^((([a-zA-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\\.([a-zA-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-zA-Z]|\\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-zA-Z]|\\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-zA-Z]|\\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\\.)+(([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-zA-Z]|\\d|-|\\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\\.?$";
	public static final String chineseNameRegexp = "^[\u4e00-\u9fa5·]+$";
	public static final String noChineseRegexp = "^[^\u4e00-\u9fa5]+$";
	public static final String orgCodeRegexp = "^[0-9A-Z]{9}$";
	public static final String creditCodeRegexp = "^[0-9A-Z]{18}$";
	public static final String twCnIdCodeRegexp = "^(\\d{8}|\\d{10})$";
	public static final String localTaxCodeRegexp = "^[0-9A-Z]{15,20}$";
	public static final String localTaxWsCodeRegexp = "^[0-9]{20}$";
	public static final String countryTaxCodeRegexp = "^[0-9A-Z]{15,20}$";
	public static final String countryTaxWsCodeRegexp = "^[0-9]{15,20}$";
	public static final String acceptNoRegexp = "^[0-9a-zA-Z]{2,}$";
	public static final String licenceCodeRegexp = "^B[0-9a-zA-Z]{5,15}$";
	public static final String mylicenceCodeRegexp = "^[0-9A-Z]{4,7}$";// 除更新指标外，其他业务使用
	public static final String workCodeRegexp = "^[0-9A-Z]{1,20}$";
	public static final String intRegexp = "[0-9]*";
//	public static final String passwordRegexp = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![!@#$%^&*()_.']+$)[0-9A-Za-z!@#$%^&*()_.']{8,20}$";
	public static final String passwordRegexp = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![0-9a-z]+$)(?![0-9A-Z]+$)(?![!@#$%^&*()_.']+$)[0-9A-Za-z!@#$%^&*()_.']{8,20}$";
	public static final String sequenceCodeRegexp = "^\\d{4}$";
	public static final String gaCnIdCodeRegexp = "^[H,M]\\d{8,10}$";
	public static final String fileReferenceRegexp = "^[0-9]{2}[0-9]{7}$";// 其他指标申请档案编号
	public static final String defaultVal = "1000000000";// 默认机动车驾驶证档案编码，个人盗抢车用
	public static final String urlRegexp = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";// 验证URL

	public static final String ipRegexp = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";// 验证IP地址

	public static final String nickNameRegexp = "^[0-9a-zA-Z\\u4e00-\\u9fa5·\\*]{2,11}$";// 用户名验证
	public static final String frameNoRegexp = "^[0-9a-zA-Z]{2,30}$";// 车架号

	public static final String doubleRegexp = "^\\d{0,8}(\\.\\d{2})?$";// 验证double值

	public static final String intBarRegexp = "^[0-9\\-]*$";// 数字、横杠校验
	public static final String allZeroRegexp = "^[0]*$";// 全为0

	private static final Pattern idCodePattern = Pattern.compile(idCodeRegexp);
	private static final Pattern mobilePattern = Pattern.compile(mobileRegexp);
	private static final Pattern phonePattern = Pattern.compile(phoneRegexp);
	private static final Pattern zipCodePattern = Pattern.compile(zipRegexp);
	private static final Pattern acceptNoPattern = Pattern.compile(acceptNoRegexp);

	private static final Pattern smsCodePattern = Pattern.compile(smsCodeRegexp);
	private static final Pattern validCodePattern = Pattern.compile(validCodeRegexp);
	private static final Pattern driverIdPattern = Pattern.compile(driverIdRegexp);
	private static final Pattern emailPattern = Pattern.compile(emailRegexp);
	private static final Pattern chineseNamePattern = Pattern.compile(chineseNameRegexp);
	// private static final Pattern noChinesePattern =
	// Pattern.compile(noChineseRegexp);
	private static final Pattern orgCodePattern = Pattern.compile(orgCodeRegexp);
	private static final Pattern creditCodePattern = Pattern.compile(creditCodeRegexp);
	private static final Pattern twCnIdCodePattern = Pattern.compile(twCnIdCodeRegexp);
	private static final Pattern localTaxCodePattern = Pattern.compile(localTaxCodeRegexp);
	private static final Pattern localTaxWsCodePattern = Pattern.compile(localTaxWsCodeRegexp);
	private static final Pattern countryTaxCodePattern = Pattern.compile(countryTaxCodeRegexp);
	private static final Pattern countryTaxWsCodePattern = Pattern.compile(countryTaxWsCodeRegexp);
	private static final Pattern licenceCodePattern = Pattern.compile(licenceCodeRegexp);
	private static final Pattern workCodePattern = Pattern.compile(workCodeRegexp);
	public static final Pattern intPattern = Pattern.compile(intRegexp);
	public static final Pattern passwordPattern = Pattern.compile(passwordRegexp);// 密码8-20位
	public static final Pattern sequenceCodePattern = Pattern.compile(sequenceCodeRegexp);
	private static final Pattern gaCnIdCodePattern = Pattern.compile(gaCnIdCodeRegexp);
	private static final Pattern myLicenceCodePattern = Pattern.compile(mylicenceCodeRegexp);
	// private static final Pattern fileReferencePattern =
	// Pattern.compile(fileReferenceRegexp);
	private static final Pattern urlPattern = Pattern.compile(urlRegexp);
	private static final Pattern ipPattern = Pattern.compile(ipRegexp);

	private static final Pattern nickNamePattern = Pattern.compile(nickNameRegexp);
	private static final Pattern frameNopattern = Pattern.compile(frameNoRegexp);

	private static final Pattern doublepattern = Pattern.compile(doubleRegexp);
	// 车牌号：
	public static final String plateNumRegexp = "^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z0-9]{1}[A-Z0-9]{1}([京津沪渝桂蒙宁新藏冀晋辽吉黑苏浙皖赣闽鲁粤鄂湘豫川云贵陕甘青琼])?[A-NP-Z0-9]{1}[A-NP-Z0-9]{3}([A-NP-Z0-9挂学警港澳领试超外]{1}|应急)([A-NP-Z0-9外])?)|^([A-Z0-9]{7})$|^默A00000$|^(应急[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z0-9]{1}[A-NP-Z0-9]{4})$";
	private static final Pattern plateNumPattern = Pattern.compile(plateNumRegexp);
	// 证件号：
	public static final String idCodeNumRegexp = "^(11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)\\d{15}(\\d{1}|X)$";
	private static final Pattern idCodeNumPattern = Pattern.compile(idCodeNumRegexp);

	// 百旺特殊字符
	public static final String specialChartRegexp = "[^∏¥§℅€℃£℉№℡‰\\$¢∮※？\\?<>\\[\\]'\\&\"]*";
	private static final Pattern specialChartPattern = Pattern.compile(specialChartRegexp);

	private static final Pattern intBarPattern = Pattern.compile(intBarRegexp);
	private static final Pattern allZeroPattern = Pattern.compile(allZeroRegexp);

	public static boolean checkMyLicenceCode(String licenceCode)
	{
		return myLicenceCodePattern.matcher(licenceCode).matches();
	}

	/**
	 * 获得当前时间离今天24点的秒数
	 * 
	 * @return
	 */
	public static int getExpTime()
	{
		int time = 0;
		Calendar now = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		time = (int) ((end.getTimeInMillis() - now.getTimeInMillis()) / 1000);
		return time;
	}

	public static String trimAllChar(String value)
	{
		if (!StringTools.hasText(value)) return null;
		String tmp = value.replaceAll(",", "");
		tmp = tmp.replaceAll("<", "");
		tmp = tmp.replaceAll(">", "");
		tmp = tmp.replaceAll("&", "");
		tmp = tmp.replaceAll("\\s", "");
		return tmp;
	}

	public static String trimAllHtmlTagAndScriptTag(String string)
	{
		if (!StringTools.hasText(string)) return string;
		String tmp = string.replaceAll("<", "");
		tmp = tmp.replaceAll(">", "");
		tmp = tmp.replaceAll("\"", "");
		tmp = tmp.replaceAll("\'", "");
		tmp = tmp.replaceAll("\\%", "");
		tmp = tmp.replaceAll("javascript", "");
		tmp = tmp.replaceAll("script", "");
		return tmp;
	}

	public static boolean checkacceptNo(String acceptNo)
	{
		if (!StringTools.hasText(acceptNo)) return false;
		return acceptNoPattern.matcher(acceptNo).matches();
	}

	/**
	 * 
	 * 检查手机验证码格式，全数字，六位
	 * 
	 * @param smsCode
	 * @return
	 */
	public static boolean checkSmsCode(String smsCode)
	{
		if (!StringTools.hasText(smsCode)) return false;
		return smsCodePattern.matcher(smsCode).matches();
	}

	public static boolean checkValidCode(String validCode)
	{
		if (!StringTools.hasText(validCode)) return false;
		return validCodePattern.matcher(validCode).matches();
	}

	/**
	 * 获得某个位数的随机数
	 * 
	 * @param bit
	 * @return
	 */
	public static String getRandCode(int bit)
	{
		StringBuilder randomCode = new StringBuilder();
		for (int i = 0; i < bit; i++)
		{
			int code = RandomUtils.nextInt(0, 10);
			randomCode.append(code);
		}
		return randomCode.toString();
	}

	public static boolean checkDateExpired()
	{
		Calendar now = (Calendar) Calendar.getInstance().clone();
		int day = now.get(Calendar.DAY_OF_MONTH);
		if (20 < day && day <= 25) return true;
		return false;
	}

	private static int[] idWeight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
	private static String[] idLastCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

	/**
	 * 检测身份证号码格式
	 * 
	 * @param idCode
	 * @throws ManagerException
	 */
	public static boolean checkIdCode(String idCode)
	{
		if (!StringTools.hasText(idCode)) return false;
		boolean b = idCodePattern.matcher(idCode).matches();
		if (!b) return false;
		String inYear = (idCode.length() == 18) ? idCode.substring(6, 10) : "19" + idCode.substring(6, 8);
		String inMonth = (idCode.length() == 18) ? idCode.substring(10, 12) : idCode.substring(8, 10);
		String inDay = (idCode.length() == 18) ? idCode.substring(12, 14) : idCode.substring(10, 12);
		Calendar idCodeTime = Calendar.getInstance();
		idCodeTime.set(Integer.parseInt(inYear), Integer.parseInt(inMonth) - 1, Integer.parseInt(inDay));
		Calendar now = Calendar.getInstance();
		int year = idCodeTime.get(Calendar.YEAR);
		int month = idCodeTime.get(Calendar.MONTH) + 1;
		int day = idCodeTime.get(Calendar.DAY_OF_MONTH);
		if (!(Integer.parseInt(inYear) == year) || !(Integer.parseInt(inMonth) == month)
				|| !(Integer.parseInt(inDay) == day) || idCodeTime.after(now) || year < 1900)
			return false;

		int sum = 0;
		for (int i = 0; i < 17; i++)
		{
			sum += Integer.parseInt(idCode.substring(i, i + 1)) * idWeight[i];
		}
		int idIndex = sum % 11;
		return idLastCode[idIndex].equals(idCode.toUpperCase().substring(17));
	}

	public static boolean checkBirthday(Calendar birthday)
	{
		Calendar before = getBeforeYear(18);
		return birthday.getTimeInMillis() <= before.getTimeInMillis();
	}

	/**
	 * 检测手机号码格式
	 * 
	 * @param mobile
	 * @throws ManagerException
	 */
	public static boolean checkMobileFormat(String mobile)
	{
		if (!StringTools.hasText(mobile)) return false;
		if (mobile.length() != 11) return false;

		return mobilePattern.matcher(mobile).matches();
	}

	/**
	 * 检测港澳台身份证号 目前只检测长度且不能是港澳通行证
	 * 
	 * @param phone
	 * @throws ManagerException
	 */
	public static boolean checkGaIdCodeFormat(String gaIdCode)
	{
		if (!StringTools.hasText(gaIdCode)) return false;
		if (gaIdCode.length() > 10) return false;
		if (gaIdCode.contains("（") || gaIdCode.contains("）")) return false;
		return !gaCnIdCodePattern.matcher(gaIdCode).matches();
	}

	/**
	 * 
	 * 检测领馆人员身份证（外交部核发的有效证件） 目前只检测长度
	 * 
	 * @param lsCnIdCode
	 * @return
	 */
	public static boolean checkLsCnIdCodeFormat(String lsCnIdCode)
	{
		if (!StringTools.hasText(lsCnIdCode)) return false;
		// TODO 目前只能检测长度
		return lsCnIdCode.length() <= 14;
	}

	/**
	 * 
	 * 检测驻华机构代码 目前只检测长度
	 * 
	 * @param orgCode
	 * @return
	 */
	public static boolean checkZHJGFormat(String orgCode)
	{
		if (!StringTools.hasText(orgCode)) return false;
		// TODO 目前只能检测长度,小于9避免企业误填写机构代码
		return orgCode.length() <= 8;
	}

	/**
	 * 检测电话号码
	 * 
	 * @param phone
	 * @throws ManagerException
	 */
	public static boolean checkPhoneFormat(String phone)
	{
		if (!StringTools.hasText(phone)) return false;
		return phonePattern.matcher(phone).matches();
	}

	/**
	 * 检测邮编
	 * 
	 * @param zipcode
	 * @throws ManagerException
	 */
	public static boolean checkZipcodeFormat(String zipcode)
	{
		if (!StringTools.hasText(zipcode)) return false;
		return zipCodePattern.matcher(zipcode).matches();
	}

	/**
	 * 检测驾驶证号
	 * 
	 * @param driverId
	 * @throws ManagerException
	 */
	public static boolean checkDriverIdFormat(String driverId)
	{
		if (!StringTools.hasText(driverId)) return false;
		return driverIdPattern.matcher(driverId).matches();
	}

	/**
	 * 检测邮箱地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmailFormat(String email)
	{
		if (!StringTools.hasText(email)) return false;
		return emailPattern.matcher(email).matches();
	}

	/**
	 * 检测 中文名
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkChineseNameFormat(String name)
	{
		if (!StringTools.hasText(name)) return false;
		return chineseNamePattern.matcher(name.replace("#", "").replace("·", "")).matches();
	}

	public static boolean checkLicenceCodeFormat(String licenceCode)
	{
		if (!StringTools.hasText(licenceCode)) return false;
		return licenceCodePattern.matcher(licenceCode).matches();
	}

	public static boolean checkWorkCodeFormat(String workCode)
	{
		if (!StringTools.hasText(workCode)) return false;
		return workCodePattern.matcher(workCode).matches();
	}

	private static String symbol = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static int[] orgWeight = { 3, 7, 9, 10, 5, 8, 4, 2 };
	private static String[] orgLastCode = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "X", "0" };

	public static boolean checkOrgCodeFormat(String orgCode)
	{
		if (!StringTools.hasText(orgCode)) return false;
		boolean b = orgCodePattern.matcher(orgCode).matches();
		if (!b) return false;
		int sum = 0;
		for (int i = 0; i < 8; i++)
		{
			String temp = orgCode.substring(i, i + 1);
			sum += symbol.indexOf(temp) * orgWeight[i];
		}
		int idIndex = 11 - (sum % 11);
		return orgLastCode[idIndex].equals(orgCode.substring(8));
	}

	private static String creditSymbol = "0123456789ABCDEFGHJKLMNPQRTUWXY0";
	private static int[] creditOrgWeight = { 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28 };

	/**
	 * 验证统一社会信用代码
	 * 
	 * @param creditCode
	 * @return
	 */
	public static boolean checkCreditCodeFormat(String creditCode)
	{
		if (!StringTools.hasText(creditCode)) return false;
		boolean b = creditCodePattern.matcher(creditCode).matches();
		if (!b) return false;
		if (!checkOrgCodeFormat(creditCode.substring(8, 17))) return false;
		int sum = 0;
		for (int i = 0; i < 17; i++)
		{
			String temp = creditCode.substring(i, i + 1);
			sum += creditSymbol.indexOf(temp) * creditOrgWeight[i];
		}
		int idIndex = 31 - (sum % 31);
		return creditSymbol.substring(idIndex, idIndex + 1).equals(creditCode.substring(17));
	}

	/**
	 * 检测 台湾往来大陆通行证
	 * 
	 * @param TwCnIdCode
	 * @return
	 */
	public static boolean checkTwCnIdCodeFormat(String TwCnIdCode)
	{
		if (!StringTools.hasText(TwCnIdCode)) return false;
		return twCnIdCodePattern.matcher(TwCnIdCode).matches();
	}

	/**
	 * 检测 港澳往来内地通行证
	 * 
	 * @param gaCnIdCode
	 * @return
	 */
	public static boolean checkGaCnIdCodeFormat(String gaCnIdCode)
	{
		if (!StringTools.hasText(gaCnIdCode)) return false;
		return gaCnIdCodePattern.matcher(gaCnIdCode).matches();
	}

	/**
	 * 检测士官证
	 * 
	 * @param sgzCnIdCode
	 * @return
	 */
	public static boolean checkSgzCnIdCodeFormat(String sgzCnIdCode)
	{
		if (!StringTools.hasText(sgzCnIdCode)) return false;
		// TODO 目前只检测长度
		return sgzCnIdCode.length() <= 20;
	}

	public static boolean checkPassportCnIdCodeFormat(String passportCnIdCode)
	{
		if (!StringTools.hasText(passportCnIdCode)) return false;
		// TODO 目前只检测长度
		return passportCnIdCode.length() <= 14;
	}

	// 港澳台及境外人员身份证明(申请原机动车所使用的)
	public static boolean checkGatwIdCodeFormat(String idCode)
	{
		if (!StringTools.hasText(idCode)) return false;
		// TODO 目前只检测长度
		return idCode.length() <= 20;
	}

	/**
	 * 20140527 根据工商的要求进行修改
	 * 
	 * 纯数字的必须是13或15位， 含汉字的小于等于20位
	 * 
	 *** 
	 * 请严格按照营业执照上的注册号填写，如： a.新的注册号为15位数字 如 330100123456789； 也有一些企业的注册号为13位数字 如
	 * 3301081234567 b.有些外资企业的注册号为汉字数字组合 如 “企合粤深总字第012345号”，汉字部分也要输入完全。
	 *** 
	 * @param busLicence
	 * @return
	 */
	public static boolean checkBusLicenceFormat(String busLicence)
	{
		if (!StringTools.hasText(busLicence)) return false;
		if (busLicence.matches("^[0-9]*$"))
		{
			if (busLicence.length() == 13 || busLicence.length() == 15) return true;
		}
		else if (busLicence.matches("^[\u4e00-\u9fa5]+[0-9]+[\u4e00-\u9fa5]$") && busLicence.length() <= 20)
			return true;
		return false;
		// if (busLicence.length() != 6 && busLicence.length() != 13 &&
		// busLicence.length() != 15)
		// return false;
		// return busLicence.matches("[0-9]{1,}");
	}

	/**
	 * 国税号 为数字+大写字母，9-25位 并且后9为是 组织机构代码
	 * 
	 * @param countryTaxCode
	 * @param orgCode
	 * @return
	 */
	public static boolean checkCountryTaxCodeFormat(String countryTaxCode, String orgCode)
	{
		if (!StringTools.hasText(countryTaxCode)) return false;
		if (!countryTaxCodePattern.matcher(countryTaxCode).matches()) return false;
		return true;
	}

	// 国税文书号校验
	public static boolean checkGsWsCodeFormat(String gsWs)
	{
		if (!StringTools.hasText(gsWs)) return false;
		if (!countryTaxWsCodePattern.matcher(gsWs).matches()) return false;
		return true;
	}

	// 地税文书号校验
	public static boolean checkDsWsCodeFormat(String dsWs)
	{
		if (!StringTools.hasText(dsWs)) return false;
		if (!localTaxWsCodePattern.matcher(dsWs).matches()) return false;
		return true;
	}

	public static boolean checkSequenceCode(String sequenceCode)
	{
		if (!StringTools.hasText(sequenceCode)) return false;
		return sequenceCodePattern.matcher(sequenceCode).matches();
	}

	public static boolean checkPassword(String passwd)
	{
		if (!StringTools.hasText(passwd)) return false;
		return passwordPattern.matcher(passwd).matches();
	}

	/**
	 * 地税号 为数字+大写字母，9-25位 并且后9为是 组织机构代码
	 * 
	 * @param localTaxCode
	 * @param orgCode
	 * @return
	 */
	public static boolean checkLocalTaxCodeFormat(String localTaxCode, String orgCode)
	{
		if (!StringTools.hasText(localTaxCode)) return false;
		if (!localTaxCodePattern.matcher(localTaxCode).matches()) return false;
		return true;
	}

	/**
	 * 检查编码情况
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkCode(String str)
	{
		int length = str.length();
		int bytLength = str.getBytes().length;

		// 都是半角的情况
		if (bytLength == length)
		{
			return true;
		}

		// 都是全角的情况
		else if (bytLength == 2 * length)
		{
			return false;
		}

		// 有全角有半角
		else
		{
			return false;
		}
	}

	public static boolean checkFileReferenceFormat(String fileReference)
	{
		if (!StringTools.hasText(fileReference)) return false;
		// return fileReferencePattern.matcher(fileReference).matches();
		if (fileReference.length() < 9 || fileReference.length() > 10) return false;
		return true;
	}

	private static Calendar getBeforeYear(int before)
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, -before);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		return now;
	}

	/**
	 * 
	 * 2014年11月12日 下午4:44:27 检验输入项是否为数字
	 * 
	 * @param intRegexp
	 * @return
	 */
	public static boolean checkIntRege(String intRegexp)
	{
		if (!StringTools.hasText(intRegexp)) return false;
		return intPattern.matcher(intRegexp).matches();
	}

	/**
	 * 检测URL
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkUrlFormat(String url)
	{
		if (!StringTools.hasText(url)) return false;
		return urlPattern.matcher(url).matches();
	}

	/**
	 * 检测IP地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkIpFormat(String ip)
	{
		if (!StringTools.hasText(ip)) return false;
		return ipPattern.matcher(ip).matches();
	}

	/**
	 * 检测用户名
	 * 
	 * @param nickName
	 * @return
	 */
	public static boolean checkNickName(String nickName)
	{
		if (!StringTools.hasText(nickName)) return false;
		return nickNamePattern.matcher(nickName).matches();
	}

	public static boolean checkFrameNo(String frameNo)
	{
		if (!StringTools.hasText(frameNo)) return false;
		return frameNopattern.matcher(frameNo).matches();
	}

	/**
	 * 检测double值长度小于10（不包含小数点）；正实数后面补充“00”
	 * 
	 * @author : Zhanghaitao
	 * @date : 2017年10月16日 下午2:48:46
	 * @param db
	 * @return
	 */
	public static boolean checkDoubleFormat(String db)
	{
		if (!StringTools.hasText(db)) return false;
		return doublepattern.matcher(db).matches();
	}

	/**
	 * 检测车牌号
	 * 
	 * @param zipcode
	 * @throws ManagerException
	 */
	public static boolean checkPlateNumFormat(String plateNum)
	{
		if (!StringTools.hasText(plateNum)) return false;
		return plateNumPattern.matcher(plateNum).matches();
	}

	/**
	 * 检测身份证
	 * 
	 * @param zipcode
	 * @throws ManagerException
	 */
	public static boolean checkIdCodeNumFormat(String plateNum)
	{
		if (!StringTools.hasText(plateNum)) return false;
		return idCodeNumPattern.matcher(plateNum).matches();
	}

	/**
	 * 特殊字符检验 如不含特殊字符（百旺提供），返回true
	 * 
	 * @param string
	 * @return
	 */
	public static boolean checkSpecialChart(String string)
	{
		if (!StringTools.hasText(string)) return false;
		return specialChartPattern.matcher(string).matches();
	}

	public static boolean checkIntBarRege(String intBarRegexp)
	{
		if (!StringTools.hasText(intBarRegexp)) return false;
		return intBarPattern.matcher(intBarRegexp).matches();
	}

	public static boolean checkAllZeroRege(String allZeroRegexp)
	{
		if (!StringTools.hasText(allZeroRegexp)) return false;
		return allZeroPattern.matcher(allZeroRegexp).matches();
	}
}
