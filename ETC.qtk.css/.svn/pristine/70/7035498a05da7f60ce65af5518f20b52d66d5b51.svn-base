
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;


public class CardRefundConfirmToRequest extends JpaDateTimePageableDataRequest<AccountRefundDetail> {

	private String cardId;
	private String refundCardId;
	private Long refundBalance;
	private String beforeDate;
	private String afterDate;
	private Long updateBalance;
	private Integer refundType;
	
	private Long cardBalance;
	private Long accountCardBalance;
	private String agencyId;
	private RefundDetailType refundMethod;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getRefundCardId() {
		return refundCardId;
	}
	public void setRefundCardId(String refundCardId) {
		this.refundCardId = refundCardId;
	}
	public Long getRefundBalance() {
		return refundBalance;
	}
	public void setRefundBalance(Long refundBalance) {
		this.refundBalance = refundBalance;
	}
	public String getBeforeDate() {
		return beforeDate;
	}
	public void setBeforeDate(String beforeDate) {
		this.beforeDate = beforeDate;
	}
	public String getAfterDate() {
		return afterDate;
	}
	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}
	public Long getUpdateBalance() {
		return updateBalance;
	}
	public void setUpdateBalance(Long updateBalance) {
		this.updateBalance = updateBalance;
	}
	public Integer getRefundType() {
		return refundType;
	}
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}
	public Long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Long cardBalance) {
		this.cardBalance = cardBalance;
	}
	public Long getAccountCardBalance() {
		return accountCardBalance;
	}
	public void setAccountCardBalance(Long accountCardBalance) {
		this.accountCardBalance = accountCardBalance;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	
	public RefundDetailType getRefundMethod() {
		return refundMethod;
	}
	public void setRefundMethod(RefundDetailType refundMethod) {
		this.refundMethod = refundMethod;
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "AccountRefundDetail" +
				"  where 1=1 ");
		hql.append(" and cardId=:cardId", cardId);
		hql.append(" and to_date(substr(cancelTime,0,10),'yyyy-MM-dd') between to_date(:beforeDate,'yyyy-MM-dd') ",beforeDate);
		hql.append(" and to_date(:afterDate,'yyyy-MM-dd') ",afterDate);
		
		if(!agencyId.equals("52010106004")) {
			if(agencyId.equals("52010102018")||agencyId.equals("52010102002")) {
				hql.append(" and agencyId in ('52010102018','52010102002')");
			}else {
				hql.append(" and agencyId=:agencyId", agencyId);
			}
		}else {
			hql.append(" and agencyId not in ('52010104001','52010102007','52010102005','52010102018','52010102002')");
		}
		refundMethod = RefundDetailType.valueOfCode(refundType);
		hql.append(" and refundType=:refundMethod",refundMethod );
		return hql;
	}
	
	
}

