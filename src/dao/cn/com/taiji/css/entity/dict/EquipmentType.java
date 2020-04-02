package cn.com.taiji.css.entity.dict;

public enum EquipmentType {
	CZK("储值卡", 1) {},
	JZK("记账卡", 2) {},
	DZBQ("电子标签", 3) {};
	private String value;
	private int code;
	
	private EquipmentType(String value, int code) {
		this.value = value;
		this.code = code;
	}

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
