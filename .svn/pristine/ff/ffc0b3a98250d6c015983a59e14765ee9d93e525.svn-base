/**
 * @Title TestApplyCardModel.java
 * @Package cn.com.taiji.css.model.ocxtest
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月12日 下午4:33:47
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.validation.MyViolationException;

/**
 * @ClassName TestApplyCardModel
 * @Description TODO
 * @author yaonl
 * @date 2018年07月12日 16:33:47
 * @E_mail yaonanlin@163.com
 */
public class ApplyCardRequest extends BaseModel {
	//是否为开卡    true为开卡   false为补卡
	private Boolean whetherToOpenCard = false;
	//supply:false,replace true
	private boolean supplyOrReplace;
	private Integer applyStep;//请求到第几次
	private String cosResponse;
	private String command;
	private String cosRecordId;
	private String cardId;
	private String oldCardId;
	private String userId;
	private Integer cardType;
	private String enableTime;
	private String expireTime;
	private String vehicleId;
	//套餐id
	private Integer pkgId;
	private String netId;
	//记账卡机构编号
	private String packageNo;
	private String newCardId;
	private Integer newCardType;
	//绑定类型
	private Integer bindingType;
	//卡片类别
	private Integer cardCategory;
	//卡片品牌
	private Integer brand;
	//卡片型号
	private String model;
	private Integer breakAmount;
	private Integer supReason;
	private Integer provider;
	private Long  cardBalance;
	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	public Integer getPkgId() {
		return pkgId;
	}
	public void setPkgId(Integer pkgId) {
		this.pkgId = pkgId;
	}
	public Integer getApplyStep() {
		return applyStep;
	}
	public String getCosResponse() {
		return cosResponse;
	}
	public String getCommand() {
		return command;
	}
	public String getCosRecordId() {
		return cosRecordId;
	}
	public String getCardId() {
		return cardId;
	}
	public String getUserId() {
		return userId;
	}
	public String getEnableTime() {
		return enableTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setApplyStep(Integer applyStep) {
		this.applyStep = applyStep;
	}
	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	
	public Long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Long cardBalance) {
		this.cardBalance = cardBalance;
	}
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	
	public String getNewCardId() {
		return newCardId;
	}
	public void setNewCardId(String newCardId) {
		this.newCardId = newCardId;
	}
	public String getOldCardId() {
		return oldCardId;
	}
	public void setOldCardId(String oldCardId) {
		this.oldCardId = oldCardId;
	}
	public Integer getNewCardType() {
		return newCardType;
	}
	public void setNewCardType(Integer newCardType) {
		this.newCardType = newCardType;
	}
	public Integer getBindingType() {
		return bindingType;
	}
	public void setBindingType(Integer bindingType) {
		this.bindingType = bindingType;
	}
	public Integer getCardCategory() {
		return cardCategory;
	}
	public void setCardCategory(Integer cardCategory) {
		this.cardCategory = cardCategory;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getBrand() {
		return brand;
	}
	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public Boolean getWhetherToOpenCard() {
		return whetherToOpenCard;
	}
	public void setWhetherToOpenCard(Boolean whetherToOpenCard) {
		this.whetherToOpenCard = whetherToOpenCard;
	}
	
	public Integer getBreakAmount() {
		return breakAmount;
	}
	public void setBreakAmount(Integer breakAmount) {
		this.breakAmount = breakAmount;
	}
	
	public Integer getSupReason() {
		return supReason;
	}
	public void setSupReason(Integer supReason) {
		this.supReason = supReason;
	}
	
	public boolean getSupplyOrReplace() {
		return supplyOrReplace;
	}
	public void setSupplyOrReplace(boolean supplyOrReplace) {
		this.supplyOrReplace = supplyOrReplace;
	}
	
	public Integer getProvider() {
		return provider;
	}
	public void setProvider(Integer provider) {
		this.provider = provider;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();		
		if(newCardId == null) mve.addViolation("newCardId", "未获取到新卡卡号,请检查是否已读取新卡信息！");
		if(bindingType == null) mve.addViolation("bindingType", "不能为空！");
		if(cardCategory == null) mve.addViolation("cardCategory", "不能为空！");
		
		if (mve.hasViolation()) throw mve;
	}
	
}

