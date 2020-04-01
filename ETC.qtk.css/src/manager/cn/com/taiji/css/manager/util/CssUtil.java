/**
 * @Title CssUtil.java
 * @Package cn.com.taiji.css.manager.util
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月26日 下午3:33:02
 * @version V1.0
 */
package cn.com.taiji.css.manager.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.taiji.common.manager.ManagerException;

/**
 * @ClassName CssUtil
 * @Description TODO
 * @author yaonl
 * @date 2018年07月26日 15:33:02
 * @E_mail yaonanlin@163.com
 */
@Component
public class CssUtil {
	public static final String UTF8 = "UTF-8";
	public static final String GB2312 = "GB2312";
	public static final String COMMON_SEPERATOR = ",";
	public static String TERMINAL_ID;
	public static String getNowDateTimeStrWithT(){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	}
	public static String getNowDateTimeStrWithoutT(){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	public static String getNowDate(){
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	/**
	 * 例如 partternStr-0000 suffixStr-1 ==>生成 0001
	 * @param partternStr
	 * @param suffixStr
	 * @return
	 */
	public static String generateLimitedStr(String partternStr,String suffixStr){
		return partternStr.substring(0,partternStr.length()-suffixStr.length())+suffixStr;
	}
	public String getTERMINAL_ID() {
		return TERMINAL_ID;
	}
	@Value("#{commProperties.terminalId}")
	public void setTERMINAL_ID(String terminalId) {
		TERMINAL_ID = terminalId;
	}
	
	public static InetAddress getLocalHostLANAddress() throws ManagerException {
	    try {
	        InetAddress candidateAddress = null;
	        // 遍历所有的网络接口
	        for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
	            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
	            // 在所有的接口下再遍历IP
	            for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
	                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
	                if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
	                    if (inetAddr.isSiteLocalAddress()) {
	                        // 如果是site-local地址，就是它了
	                        return inetAddr;
	                    } else if (candidateAddress == null) {
	                        // site-local类型的地址未被发现，先记录候选地址
	                        candidateAddress = inetAddr;
	                    }
	                }
	            }
	        }
	        if (candidateAddress != null) {
	            return candidateAddress;
	        }
	        // 如果没有发现 non-loopback地址.只能用最次选的方案
	        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	        return jdkSuppliedAddress;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new ManagerException("获取ip失败,发生未知错误");
	    }
	}
	
	public static String getPropertyValueFromJsonData(String propertyName,String data){
		JSONObject jsonObject = JSON.parseObject(data);
		String propertyValue = jsonObject.getString(propertyName);
		return propertyValue;
	}
	
	public static String arrayToString(String[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i != arr.length - 1) {
				sb.append(COMMON_SEPERATOR);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 验证文件的类型和编码格式
	 * @param files  需要验证的文件
	 * @param list	文件类型和编码     eg:"image/png"  图片文件/png编码
	 * 文件类型和编码格式  请查阅MIME文件类型
	 * @return
	 */
	public static Boolean validateFileType(MultipartFile[] files,List<String> list) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				Boolean flag = true;
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).equals(file.getContentType())) {
						flag = false;
						break;
					}
				}
				if(flag) return false;
			}
		return true;
	}
	/**
	 * 生成随机字符串  包含a-z、A-Z、0-9
	 * @param length  生成随机字符串长度
	 * @return
	 */
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
}