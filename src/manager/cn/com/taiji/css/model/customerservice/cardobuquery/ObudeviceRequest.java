/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.cardobuquery;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.css.entity.dict.AccountStatus;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class ObudeviceRequest extends BaseModel {
	private String accountName;
	private AccountStatus accountStatus;
	private String IdNum;
	public String getAccountName() {
		return accountName;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public String getIdNum() {
		return IdNum;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public void setIdNum(String idNum) {
		IdNum = idNum;
	}
}

