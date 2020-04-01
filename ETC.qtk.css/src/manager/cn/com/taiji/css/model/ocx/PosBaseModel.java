/**
 * @Title PosConsumeModel.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月25日 下午8:08:23
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx;

import org.springframework.stereotype.Component;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.qtk.entity.dict.CssPosTradeType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName PosConsumeModel
 * @Description TODO
 * @author yaonl
 * @date 2018年07月25日 20:08:23
 * @E_mail yaonanlin@163.com
 */
@Component
public class PosBaseModel extends BaseModel {
	protected CssPosTradeType posTradeType; // 交易类型 目前只有消费
	protected String transType;// 交易类型代码value=>posTradeType.getCode();
	protected Long amount;// 交易金额
	protected String cardNo ;// 银行卡号（MISPOS系统返回）
	protected String oldAuthDate;// 原交易日期，隔日退货交易时候传送给MISPOS系统，预留字段
	protected Integer referNo;// 系统检索号（MISPOS系统返回，收银机MIS系统在撤销交易时需传给MISPOS系统，需记录到数据库中作为对账内容之一）
	protected String transTime;// 交易时间（MISPOS系统返回）
	protected String transDate;// 交易日期（MISPOS系统返回）
	protected String returnCode;// 返回码 “00”表示交易成功（MISPOS系统返回）
	protected String rspMessage;// 交易失败时，MISPOS系统返回中文错误描述信息（MISPOS系统返回）
	protected String terminalId;// MISPOS系统返回交易终端号
	protected String merchantId;// MISPOS系统返回商户号
	protected String ylMerchantId;// MISPOS系统返回银联商户号
	protected String platId;// 收银机号 暂定66666
	protected String operId;// 操作员号 暂定99999
	protected String etcCardNo;// etc卡号 绑定/解绑业务用
	protected String userName;// 客户姓名 暂不使用
	protected String userID;// 客户身份证件号 暂不使用
	/**
	 * 部分字段 指定格式 通过字段的set方法同时设置
	 */
	protected String amountStr;//ammount 定长12位字符串 10进制 单位：分  左侧补0
	protected String referNoStr;//referNo 定长8位字符串 单位：分  左侧补0
	protected String vehicleColorStr;//vehicleColor
	protected String userIdTypeStr;//userIdType
	/**
	 * 其它业务参数
	 */
	protected String obuId;
	protected String vehiclePlate;// 车牌号
	protected VehiclePlateColorType vehicleColor; //车牌颜色
	protected CustomerIDType userIdType; // 用户证件类型
	protected String userPhoneNo; // 用户手机号
	protected String cardId; 
	
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public String getVehicleColorStr() {
		return vehicleColorStr;
	}
	public String getUserIdTypeStr() {
		return userIdTypeStr;
	}
	public VehiclePlateColorType getVehicleColor() {
		return vehicleColor;
	}
	public CustomerIDType getUserIdType() {
		return userIdType;
	}
	public String getUserPhoneNo() {
		return userPhoneNo;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public void setVehicleColor(VehiclePlateColorType vehicleColor) {
		this.vehicleColorStr = String.valueOf(vehicleColor.getTypeCode());
		this.vehicleColor = vehicleColor;
	}
	public void setUserIdType(CustomerIDType userIdType) {
		this.userIdTypeStr = String.valueOf(userIdType.getTypeCode());
		this.userIdType = userIdType;
	}
	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	public String getAmountStr() {
		return amountStr;
	}
	public String getReferNoStr() {
		return referNoStr;
	}
	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
		this.amount = Long.valueOf(amountStr);
	}
	public void setReferNoStr(String referNoStr) {
		this.referNoStr = referNoStr;
		this.referNo = Integer.valueOf(referNoStr);
	}
	public String getObuId() {
		return obuId;
	}
	public void setObuId(String obuId) {
		this.obuId = obuId;
	}
	public CssPosTradeType getPosTradeType() {
		return posTradeType;
	}
	public String getTransType() {
		return transType;
	}
	public void setPosTradeType(CssPosTradeType posTradeType) {
		this.posTradeType = posTradeType;
		this.transType = posTradeType.getCode();
	}
	public void setTransType(String transType) {
		this.transType = transType;
		this.posTradeType = CssPosTradeType.fromCode(transType);
	}
	public String getCardNo() {
		return cardNo;
	}
	public String getEtcCardNo() {
		return etcCardNo;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserID() {
		return userID;
	}
	public String getOldAuthDate() {
		return oldAuthDate;
	}
	public Integer getReferNo() {
		return referNo;
	}
	public String getTransTime() {
		return transTime;
	}
	public String getTransDate() {
		return transDate;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public String getRspMessage() {
		return rspMessage;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public String getYlMerchantId() {
		return ylMerchantId;
	}
	public String getPlatId() {
		return platId;
	}
	public String getOperId() {
		return operId;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setEtcCardNo(String etcCardNo) {
		this.etcCardNo = etcCardNo;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setOldAuthDate(String oldAuthDate) {
		this.oldAuthDate = oldAuthDate;
	}
	public void setReferNo(Integer referNo) {
		this.referNo = referNo;
		this.referNoStr = CssUtil.generateLimitedStr("00000000", referNo.toString());
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public void setRspMessage(String rspMessage) {
		this.rspMessage = rspMessage;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public void setYlMerchantId(String ylMerchantId) {
		this.ylMerchantId = ylMerchantId;
	}
	public void setPlatId(String platId) {
		this.platId = platId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
		this.amountStr = CssUtil.generateLimitedStr("000000000000", amount.toString());
	}
	// 生成指令/反向生成model用 有指定顺序
	public static String[] propertyNameSequence(){
		return new String[]{
			"transType","cardNo","etcCardNo","userName",
			"userID","amountStr","oldAuthDate","referNoStr",
			"transTime","transDate","returnCode","rspMessage",
			"terminalId","merchantId","ylMerchantId","platId",
			"operId","vehiclePlate","vehicleColorStr","userIdTypeStr",
			"userPhoneNo"
		};
	}
	public boolean tradeSuccess() {
		return "00".equals(returnCode);
	}
	
	public void valid() throws ManagerException {
		if(posTradeType==null){
			throw new ManagerException("交易类型不能为空");
		}
		if(posTradeType==CssPosTradeType.CONSUME){
			if(amount==null){
				throw new ManagerException("交易金额不能为空");
			}
		}
		if(posTradeType == CssPosTradeType.CARDVALID){
			if(!StringTools.hasText(this.userID)){
				throw new ManagerException("未获取到用户证件号");
			}
			if(!StringTools.hasText(this.userName)){
				throw new ManagerException("未获取用户姓名");
			}
			if(!StringTools.hasText(this.vehiclePlate)){
				throw new ManagerException("未获取到车牌号");
			}
			if(this.vehicleColor == null){
				throw new ManagerException("未获取到车牌颜色");
			}
			if(this.userIdType == null){
				throw new ManagerException("未获取用户证件类型");
			}
			if(!StringTools.hasText(this.userPhoneNo)){
				throw new ManagerException("未获取用户手机号");
			}
		}
		if(posTradeType == CssPosTradeType.CARDBINDING){
			if(!StringTools.hasText(this.cardNo)){
				throw new ManagerException("未获取到银行卡号");
			}
			if(!StringTools.hasText(this.etcCardNo)){
				throw new ManagerException("未获取ETC卡号");
			}
			if(!StringTools.hasText(this.userID)){
				throw new ManagerException("未获取到用户证件号");
			}
			if(!StringTools.hasText(this.userName)){
				throw new ManagerException("未获取用户姓名");
			}
			if(!StringTools.hasText(this.vehiclePlate)){
				throw new ManagerException("未获取到车牌号");
			}
			if(this.vehicleColor == null){
				throw new ManagerException("未获取到车牌颜色");
			}
			if(this.userIdType == null){
				throw new ManagerException("未获取用户证件类型");
			}
			if(!StringTools.hasText(this.userPhoneNo)){
				throw new ManagerException("未获取用户手机号");
			}
		}
		if(posTradeType == CssPosTradeType.CARDUNBINDING){
			if(!StringTools.hasText(this.cardNo)){
				throw new ManagerException("未获取到银行卡号");
			}
			if(!StringTools.hasText(this.etcCardNo)){
				throw new ManagerException("未获取ETC卡号");
			}
			if(!StringTools.hasText(this.userID)){
				throw new ManagerException("未获取到用户证件号");
			}
			if(!StringTools.hasText(this.userName)){
				throw new ManagerException("未获取用户姓名");
			}
			if(!StringTools.hasText(this.vehiclePlate)){
				throw new ManagerException("未获取到车牌号");
			}
			if(this.vehicleColor == null){
				throw new ManagerException("未获取到车牌颜色");
			}
			if(this.userIdType == null){
				throw new ManagerException("未获取用户证件类型");
			}
			if(!StringTools.hasText(this.userPhoneNo)){
				throw new ManagerException("未获取用户手机号");
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Long.valueOf("000000000001"));
	}
}

