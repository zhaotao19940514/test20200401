/**
 * @Title AccountStatus.java
 * @Package cn.com.taiji.css.entity.dict
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:19:34
 * @version V1.0
 */
package cn.com.taiji.css.entity.dict;

/**
 * @ClassName AccountStatus
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:19:34
 * @E_mail yaonanlin@163.com
 */
public enum AccountStatus {
	ON("启用",0){},
	PRECANCEL("预注销",1){},
	CANCEL("注销",2){},
	;
	private AccountStatus(String value,int code) {
		this.value = value;
		this.code = code;
	}
	private String value;
	private int code;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}

