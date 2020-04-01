
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;


public class CardAccountRefundRequest extends JpaDateTimePageableDataRequest<AccountRefundDetail> {
		private String cardId;
		private String refundCardId;
		private Long refundBalance;
		private String beforeDate;
		private String afterDate;
		private Long updateBalance;
		private Integer refundType;
		private String description;
		private String agencyId;
		private Integer isConfirm;
		private Long compleBalance;
		private String compleTime;
		private String aclBankCardId;
			
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
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getAgencyId() {
			return agencyId;
		}

		public void setAgencyId(String agencyId) {
			this.agencyId = agencyId;
		}
		
		public Integer getIsConfirm() {
			return isConfirm;
		}

		public void setIsConfirm(Integer isConfirm) {
			this.isConfirm = isConfirm;
		}
		

		public Long getCompleBalance() {
			return compleBalance;
		}

		public void setCompleBalance(Long compleBalance) {
			this.compleBalance = compleBalance;
		}

		public String getCompleTime() {
			return compleTime;
		}

		public void setCompleTime(String compleTime) {
			this.compleTime = compleTime;
		}

		public String getAclBankCardId() {
			return aclBankCardId;
		}

		public void setAclBankCardId(String aclBankCardId) {
			this.aclBankCardId = aclBankCardId;
		}

		public void validate() {
			MyViolationException mve = new MyViolationException();		
			if(null==cardId) {
				mve.addViolation("cardId", "请输入卡号");
			}
			
			if (mve.hasViolation()) throw mve;
		}
		
		@Override
		public HqlBuilder toSelectHql() {
			HqlBuilder hql = new HqlBuilder("from " + "AccountRefundDetail" +
					"  where 1=1 ");
			hql.append(" and cardId=:cardId", cardId);
			hql.append(" and cancelTime between :beforeDate",beforeDate);
			hql.append(" and :afterDate",afterDate);
			if(!agencyId.equals("52010106004")&&!agencyId.equals("52010188006")&&!agencyId.equals("52010188033")) {
				if(agencyId.equals("52010102018")||agencyId.equals("52010102002")) {
					hql.append(" and agencyId in ('52010102018','52010102002')");
				}else {
					hql.append(" and agencyId=:agencyId", agencyId);
				}
			}else {
				hql.append(" and agencyId not in ('52010104001','52010102007','52010102005','52010102018','52010102002')");
			}
			if(null!=refundType) {
				hql.append(" and refundType=:refundType", RefundDetailType.valueOfCode(refundType));
			}
		
		
//		  if(isConfirm==1) {
//			  if(agencyId.equals("52010102018")||agencyId.equals("52010102002")) {
//			  hql.append(" and refundType in('"+RefundDetailType.WTJTF+"','"+RefundDetailType.YTJTF+"','"+RefundDetailType.ECHDTF+"')"); 
//		  }else {
//			  hql.append(" and refundType in('"+RefundDetailType.WTJTF+"','"+RefundDetailType.ECHDTF+"')"); 
//		  	}
//		  }
		 
		 
			hql.append(" order by cancelTime asc");
			return hql;
		}
		
}

