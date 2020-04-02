package cn.com.taiji.css.model.administration.notice;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.BankSignVersionDetail;

/**
 * 
 *@ClassName NoticeRequest.java
 *@Description: 
 *@author:zhaot
 *@date: 2020年2月28日
 */
public class NoticeConfigRequest extends JpaDateTimePageableDataRequest<BankSignVersionDetail> {
	private String rowId;
	private String agencyId;
	private String openNotify;
	private String openObuNotify;
	private String cardNotice;
	private String bankSignUrl;
	private Integer cardNfSwitch;
	private Integer activeNfSwitch;
	private Integer oBUNfSwitch;
	private Integer cardNoticeSwitch;
	private Integer signNoticeSwitch;
	private String agencyName;
	private String checkContract;
	private String signNotice;
	private String changeCard;
	
	public String getAgencyId() {
		return agencyId;
	}
	
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	
	public String getOpenNotify() {
		return openNotify;
	}

	public String getChangeCard() {
		return changeCard;
	}

	public void setChangeCard(String changeCard) {
		this.changeCard = changeCard;
	}

	public void setOpenNotify(String openNotify) {
		this.openNotify = openNotify;
	}

	public String getOpenObuNotify() {
		return openObuNotify;
	}

	public void setOpenObuNotify(String openObuNotify) {
		this.openObuNotify = openObuNotify;
	}

	public String getCardNotice() {
		return cardNotice;
	}

	public void setCardNotice(String cardNotice) {
		this.cardNotice = cardNotice;
	}

	public String getBankSignUrl() {
		return bankSignUrl;
	}

	public void setBankSignUrl(String bankSignUrl) {
		this.bankSignUrl = bankSignUrl;
	}
	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getCheckContract() {
		return checkContract;
	}

	public void setCheckContract(String checkContract) {
		this.checkContract = checkContract;
	}

	public String getSignNotice() {
		return signNotice;
	}

	public void setSignNotice(String signNotice) {
		this.signNotice = signNotice;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public void setId(String rowId) {
		this.rowId = rowId;
	}

	public Integer getCardNfSwitch() {
		return cardNfSwitch;
	}

	public void setCardNfSwitch(Integer cardNfSwitch) {
		this.cardNfSwitch = cardNfSwitch;
	}

	public Integer getActiveNfSwitch() {
		return activeNfSwitch;
	}

	public void setActiveNfSwitch(Integer activeNfSwitch) {
		this.activeNfSwitch = activeNfSwitch;
	}

	public Integer getOBUNfSwitch() {
		return oBUNfSwitch;
	}



	public Integer getCardNoticeSwitch() {
		return cardNoticeSwitch;
	}

	public void setCardNoticeSwitch(Integer cardNoticeSwitch) {
		this.cardNoticeSwitch = cardNoticeSwitch;
	}

	public Integer getSignNoticeSwitch() {
		return signNoticeSwitch;
	}

	public void setSignNoticeSwitch(Integer signNoticeSwitch) {
		this.signNoticeSwitch = signNoticeSwitch;
	}
	public Integer getoBUNfSwitch() {
		return oBUNfSwitch;
	}

	public void setoBUNfSwitch(Integer oBUNfSwitch) {
		this.oBUNfSwitch = oBUNfSwitch;
	}

	public void validate() {
		MyViolationException mve = new MyViolationException();
//		if(null==cardId&&null==vehicleId&&null==vehicleColor)mve.addViolation("cardId", "请填写卡号");
//		if(null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
//		if(null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleColor", "请填写车牌号");
//		if (mve.hasViolation()) throw mve;
//		if(null!=vehicleId&&null!=vehicleColor) {
//			vehicleId=vehicleId+"_"+vehicleColor;
//		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "BankSignVersionDetail " +
				"  where 1=1 ");
		hql.append(" and agencyId=:agencyId", agencyId);
		return hql;
	}
}

