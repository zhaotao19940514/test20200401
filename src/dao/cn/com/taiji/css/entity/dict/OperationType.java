/**
 * @Title AgencyType.java
 * @Package cn.com.taiji.qtk.entity.dict
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月28日 下午3:59:52
 * @version V1.0
 */
package cn.com.taiji.css.entity.dict;


public enum OperationType {
	BIND("绑定", "1") {
	},
	REMOVE("解绑", "2") {
	},;
	private String value;
	private String code;

	private OperationType(String value, String code) {
		this.value = value;
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public String getCode() {
		return code;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
