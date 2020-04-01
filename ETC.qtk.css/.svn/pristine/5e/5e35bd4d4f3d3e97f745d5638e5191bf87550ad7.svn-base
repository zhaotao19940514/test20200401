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
 * @author FXD
 * @date 2019年07月25日 17:19:34
 * @E_mail yaonanlin@163.com
 */
public enum BatchIssueStatus {
	INFOMATIONLOSE("数据校验失败",-1){},
	SUCCESS("订单生成成功",1){},
	WAITHANDLE("待生成订单",0){},
	FAIL("订单生成失败",2){},
	;
	private BatchIssueStatus(String value,int code) {
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
	
	public static BatchIssueStatus fromCode(Integer code){
		for(BatchIssueStatus type:BatchIssueStatus.values()){
			if(type.getCode()==code){
				return type;
			}
		}
		throw new RuntimeException("没有此订单状态："+code);	
	}
	
}

