package cn.com.taiji.css.entity.dict;

/**
 * 发行套餐记录表状态
 * @author T440
 *
 */
public enum IssuePackagePayStatus {
	PAY("已支付",1){},
	NOPAY("未支付",0){},
	REPEAL("作废",2){};
	private String value;
	private Integer code;
	private IssuePackagePayStatus(String value, Integer code) {
		this.value = value;
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
