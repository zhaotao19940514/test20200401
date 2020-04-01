/**
 * @Title AppAjaxResponseCode.java
 * @Package cn.com.taiji.css.entity.dict
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月31日 下午7:54:13
 * @version V1.0
 */
package cn.com.taiji.css.entity.dict;

/**
 * @ClassName AppAjaxResponseCode
 * @Description TODO
 * @author yaonl
 * @date 2018年07月31日 19:54:13
 * @E_mail yaonanlin@163.com
 */
public enum AppAjaxResponseCode {
	SUCCESS("成功","1"){},
	FAILED("失败","2"){},
	EXCEPTION("异常","3"){},
	;
	private String value;
	private String errorCode;
	private AppAjaxResponseCode(String value, String errorCode) {
		this.value = value;
		this.errorCode = errorCode;
	}
	public String getValue() {
		return value;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

