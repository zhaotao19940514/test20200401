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
import cn.com.taiji.css.entity.User;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class CarddeviceRequest extends BaseModel {

	
	// 开户人名称
	private String customerName;
	
	// 开户人证件类型
	private Integer customerIdType;

	// 开户人证件号
	private String customerIdNum;
	
	//卡号
	private String cardId;
	
	//卡类型
	private Integer cardType;
	
	// 车牌号
	private String vehiclePlate;

	// 车牌颜色
	private Integer vehiclePlateColor;
	
	//生效时间
	private String enableTime;
	
	//失效时间
	private String expireTime;
	
	//卡片余额
	private Long preBalance;
	
	//用户信息
	private User user;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerIdType() {
		return customerIdType;
	}

	public void setCustomerIdType(Integer customerIdType) {
		this.customerIdType = customerIdType;
	}

	public String getCustomerIdNum() {
		return customerIdNum;
	}

	public void setCustomerIdNum(String customerIdNum) {
		this.customerIdNum = customerIdNum;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}

	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}

	public String getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public Long getPreBalance() {
		return preBalance;
	}

	public void setPreBalance(Long preBalance) {
		this.preBalance = preBalance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
	
	
	
	
	
}

