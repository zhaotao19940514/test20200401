/**
 * @Title ObuInfoMarkType.java
 * @Package cn.com.taiji.css.entity.dict
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午2:52:29
 * @version V1.0
 */
package cn.com.taiji.css.entity.dict;

/**
 * @ClassName ObuInfoMarkType
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 14:52:29
 * @E_mail yaonanlin@163.com
 */
public enum ObuInfoMarkType {
	CommInterfaceType("通信接口类型"){},
	SystemInfo("系统信息"){},
	VehicleInfo("车辆信息"){},
	;
	
	private ObuInfoMarkType(String value) {
		this.value = value;
	}
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

