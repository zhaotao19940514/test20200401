package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CardCancelRefund;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;

public class CardRefundExcelModel extends JpaDateTimePageableDataRequest<CardCancelRefund>{
	private String id;
    private String cardId;
    private String customerName;
    private String tel;
    private String bankName;
    private String bankCardId;
    private Long postBalance;
    private String message;
    private RefundDetailType refundDetailType;
    private String stTime;
    private String edTime;
    private String errorMsg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public Long getPostBalance() {
		return postBalance;
	}
	public void setPostBalance(Long postBalance) {
		this.postBalance = postBalance;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public RefundDetailType getRefundDetailType() {
		return refundDetailType;
	}
	public void setRefundDetailType(RefundDetailType refundDetailType) {
		this.refundDetailType = refundDetailType;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getStTime() {
		return stTime;
	}
	public void setStTime(String stTime) {
		this.stTime = stTime;
	}
	public String getEdTime() {
		return edTime;
	}
	public void setEdTime(String edTime) {
		this.edTime = edTime;
	}
	@Override
	public HqlBuilder toSelectHql() {
		return null;
	}
	public void paramCheck() {
		MyViolationException mve = new MyViolationException();
		if (stTime == null) mve.addViolation("stTime", "请选择起始日期");
		if (edTime == null || "".equals(edTime)) mve.addViolation("edTime", "请选择结束日期");
		if (mve.hasViolation()) {
			throw mve;
		}
	}
    
	

}
