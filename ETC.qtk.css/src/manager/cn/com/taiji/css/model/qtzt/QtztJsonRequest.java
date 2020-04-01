package cn.com.taiji.css.model.qtzt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.taiji.common.model.BaseModel;
@Component
public class QtztJsonRequest extends BaseModel{
   
	public static String appId;
  
    public static String token;
 
    public static String sign;
   
    public static String stamp;
  
    public static Integer zip;
   
    public static Integer keyType;
  
    public static String key;
    
    private String code;
    private String staffId;
    private String terminalId;
    private String agentId;
    private Integer channelType;
    private String channelId;
  
//    public  String orgCode;
    
    public QtztJsonRequestData  data;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public QtztJsonRequestData getData() {
		return data;
	}

	public void setData(QtztJsonRequestData data) {
		this.data = data;
	}

	
//	public String getOrgCode() {
//		return orgCode;
//	}
//	
//	public void setOrgCode(String orgCode) {
//		this.orgCode = orgCode;
//	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toJsonWithData() {
		String str =toJson();
		System.out.println(str);
	   // String rpStr = str.replace(":", "=").replace(",","&" ).replace("\"", "");   
	    int index = str.indexOf("}");
		String dataStr =str.substring(1, index+1);
		String fileStr = str.substring(index+1,str.length()-1);
		int daindex = dataStr.indexOf("{");
		String data1 = dataStr.substring(0,daindex);
		String data2 = dataStr.substring(daindex,dataStr.length());
		data1=data1.replace(":", "=").replace("\"", "");
		data2 =data2.replace("\"", "'");
		fileStr = fileStr.replace(":", "=").replace("\"", "").replace(",", "&");
		str =data1+data2+fileStr;
		int firstindex =str.indexOf(",");
		String orgStr1 = str.substring(0,firstindex+1).replace(",", "&");
		String orgStr2 =str.substring(firstindex+1,str.length());
		str =orgStr1+orgStr2;
		int index1 = str.indexOf("data"); 
		   String head =str.substring(0,index1);
		    head = head.replace(",", "&");
		   String foot = str.substring(index1, str.length());
		
//		dataStr = dataStr.replace("\"", "\'");
//		//System.out.println(dataStr);
//		String fileStr = str.substring(0,index+6);
//        fileStr =fileStr.replace(":", "=").replace(",","&" ).replace("\"", "");
//        String strs = fileStr+dataStr;
//	    strs =strs.substring(1,strs.length()-1);
//		String[] strList = strs.split("&");
//		String senStr ="";
//		for (String string : strList) {
//			senStr+=string+"&";
//		}
//	    senStr = senStr.substring(0, senStr.length()-1);
  	    return head+foot;
	}
	
	public String getAppId() {
		return appId;
	}
	 @Value("#{commProperties.appId}")
	public void setAppId(String appId) {
		QtztJsonRequest.appId = appId;
	}

	public String getToken() {
		return token;
	}
	 @Value("#{commProperties.token}")
	public void setToken(String token) {
		QtztJsonRequest.token = token;
	}

	public String getSign() {
		return sign;
	}
	   @Value("#{commProperties.sign}")
	public void setSign(String sign) {
		QtztJsonRequest.sign = sign;
	}

	public String getStamp() {
		return stamp;
	}
	 @Value("#{commProperties.stamp}")
	public void setStamp(String stamp) {
		QtztJsonRequest.stamp = stamp;
	}

	public Integer getZip() {
		return zip;
	}
	  @Value("#{commProperties.zip}")
	public void setZip(Integer zip) {
		QtztJsonRequest.zip = zip;
	}

	public Integer getKeyType() {
		return keyType;
	}
	 @Value("#{commProperties.keyType}")
	public void setKeyType(Integer keyType) {
		QtztJsonRequest.keyType = keyType;
	}
   
	public String getKey() {
		return key;
	}
	  @Value("#{commProperties.key}")
	public void setKey(String key) {
		QtztJsonRequest.key = key;
	}





}
