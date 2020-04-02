/**
 * @Title CssConstant.java
 * @Package cn.com.taiji.css.manager.util
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月26日 下午5:51:43
 * @version V1.0
 */
package cn.com.taiji.css.manager.util;

import java.io.File;

import cn.com.taiji.common.manager.pub.FileHelper;

/**
 * @ClassName CssConstant
 * @Description 常量定义类
 * @author yaonl
 * @date 2018年07月26日 17:51:43
 * @E_mail yaonanlin@163.com
 */
public class CssConstant {
	private static final String PATH_SEPERATOR = File.separator;
	private static final String WAR_PATH = FileHelper.getWebappPath();
	private static final String DATA_PATH = FileHelper.getDataPath();
	public static final String CARD_OCX_DRIVER_PATH = WAR_PATH + PATH_SEPERATOR +"cardReaderOcx"+PATH_SEPERATOR+"CardReaderOcxRegesiter_x86_x64.rar";
	public static final String JL_CARD_OCX_DRIVER_PATH = WAR_PATH + PATH_SEPERATOR +"cardReaderOcx"+PATH_SEPERATOR+ "jl" + PATH_SEPERATOR +"jlCardReaderOcxRegesiter_x86_x64.zip";
	public static final String CCB_CARD_OCX_DRIVER_PATH = WAR_PATH + PATH_SEPERATOR +"cardReaderOcx"+PATH_SEPERATOR+"ccb"+File.separator+"ccb_gwi_register_x86_x64.rar";
	public static final String OBU_OCX_DRIVER_PATH = WAR_PATH+PATH_SEPERATOR+"obuReaderOcx"+PATH_SEPERATOR+"obuApiOcxOffline_x86_x64.rar";
	public static final String POS_OCX_DRIVER_PATH = WAR_PATH+PATH_SEPERATOR+"posOcx"+PATH_SEPERATOR+"PosOcxRegister_x86_x64.rar";
	public static final Boolean TRADE_COMPLETE = true;
	public static final Boolean TRADE_UNCOMPLETE = false;
	public static final Integer COMM_CHANNEL_TYPE = 2;
	public static final String POS_COMMAND_SEPERATOR = ",";
	public static final String WATCH_OBU_OCX_DRIVER_PATH = WAR_PATH + PATH_SEPERATOR +"obuReaderOcx"+PATH_SEPERATOR+"watch"+PATH_SEPERATOR+"watchObuReaderRegister_x86_x64.rar";
	public static final String FIRE_FOX_GREEN_PATH = DATA_PATH+PATH_SEPERATOR+"firefox"+PATH_SEPERATOR+"firefox_green.rar";
	public static final String CHROME_GREEN_PATH = DATA_PATH+PATH_SEPERATOR+"chrome"+PATH_SEPERATOR+"chrome-bin.rar";
	public static final String CONFIG_DOC = DATA_PATH+PATH_SEPERATOR+"doc"+PATH_SEPERATOR+"deviceConfig.doc";
	public static final String USER_EXCEL = DATA_PATH+PATH_SEPERATOR+"xlsx"+PATH_SEPERATOR+"userUpload.xlsx";
	public static final String SERVICE_EXCEL = DATA_PATH+PATH_SEPERATOR+"xlsx"+PATH_SEPERATOR+"serviceUpload.xlsx";
	public static final String INFOMATION_EXCEL = DATA_PATH+PATH_SEPERATOR+"xlsx"+PATH_SEPERATOR+"infomationUpload.xlsx";
	public static final String CARD_REFUND_EXCEL= DATA_PATH+PATH_SEPERATOR+"xlsx"+PATH_SEPERATOR+"cardRefundUpload.xlsx";
	public static final String OUTSIDEDIMENSIONS_SAMBOL = "X";
	public static final String REFUND_EXCEL = DATA_PATH+PATH_SEPERATOR+"refund"+PATH_SEPERATOR+"refundUpload.xlsx";
	public static final String TRANSACTION_REFUND_VALUE_EXCEL = DATA_PATH+PATH_SEPERATOR+"transaction"+PATH_SEPERATOR+"transactionRefundValue.xlsx";
	public static final String TRANSACTION_REFUND_ACCOUNT_EXCEL = DATA_PATH+PATH_SEPERATOR+"transaction"+PATH_SEPERATOR+"transactionRefundAccount.xlsx";
}

