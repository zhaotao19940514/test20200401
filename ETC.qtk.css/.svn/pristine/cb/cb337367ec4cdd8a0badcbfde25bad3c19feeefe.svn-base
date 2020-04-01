/**
 * @Title ReceiptBaseModel.java
 * @Package cn.com.taiji.css.model.receipt
 * @Description TODO
 * @author yaonanlin
 * @date 2019年3月27日 上午11:21:00
 * @version V1.0
 */
package cn.com.taiji.css.model.receipt;

import java.time.LocalDateTime;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.css.entity.OperateLog;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;

/**
 * @ClassName ReceiptBaseModel
 * @Description TODO
 * @author yaonl
 * @date 2019年03月27日 11:21:00
 * @E_mail yaonanlin@163.com
 */
public class ReceiptBaseModel extends BaseModel {
	private CustomerInfo customerInfo;// 用户
	private CustomerIDType customerIDType;
	private String vehiclePlate;// 车牌号
	private String cardId;// 卡片
	private String obuId;// obu
	private ChargeDetail chargeDetail;// 圈存流水
	private User operator;// 操作用户
	private OperateLog log; // 业务日志
	
	//账户充值字段
	private ChargeType chargeType; // 账户充值交易类型
	private Long tradeFee; // 账户充值金额 (分)
	private LocalDateTime printTime; // 回执打印时间
	
	//套餐费用
	private CarIssuePackageInfo issuePackage;
	
	public CustomerIDType getCustomerIDType() {
		return customerIDType;
	}
	public void setCustomerIDType(CustomerIDType customerIDType) {
		this.customerIDType = customerIDType;
	}
	public CarIssuePackageInfo getIssuePackage() {
		return issuePackage;
	}
	public void setIssuePackage(CarIssuePackageInfo issuePackage) {
		this.issuePackage = issuePackage;
	}
	public LocalDateTime getPrintTime() {
		return printTime;
	}
	public void setPrintTime(LocalDateTime printTime) {
		this.printTime = printTime;
	}
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public String getCardId() {
		return cardId;
	}
	public String getObuId() {
		return obuId;
	}
	public ChargeDetail getChargeDetail() {
		return chargeDetail;
	}
	public User getOperator() {
		return operator;
	}
	public OperateLog getLog() {
		return log;
	}
	public Long getTradeFee() {
		return tradeFee;
	}
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public void setObuId(String obuId) {
		this.obuId = obuId;
	}
	public void setChargeDetail(ChargeDetail chargeDetail) {
		this.chargeDetail = chargeDetail;
	}
	public void setOperator(User operator) {
		this.operator = operator;
	}
	public void setLog(OperateLog log) {
		this.log = log;
	}
	public void setTradeFee(Long tradeFee) {
		this.tradeFee = tradeFee;
	}
	public ChargeType getChargeType() {
		return chargeType;
	}
	public void setChargeType(ChargeType chargeType) {
		this.chargeType = chargeType;
	}
	
}

