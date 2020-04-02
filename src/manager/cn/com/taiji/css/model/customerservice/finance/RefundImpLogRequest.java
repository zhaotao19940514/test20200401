
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.RefundImpLog;


public class RefundImpLogRequest extends JpaDateTimePageableDataRequest<RefundImpLog> {
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
		
		@Override
		public HqlBuilder toSelectHql() {
			if(beforeDate != null && afterDate != null) {
				afterDate = afterDate + " 23:59:59";
			}
			if(beforeDate != null && afterDate == null) {
				afterDate = beforeDate + " 23:59:59";
			}
			if(beforeDate == null && afterDate != null) {
				beforeDate = afterDate;
				afterDate = afterDate + " 23:59:59";
			}
			HqlBuilder hql = new HqlBuilder("from " + "RefundImpLog" +
					"  where 1=1 ");
			hql.append(" and createTime between :beforeDate",beforeDate);
			hql.append(" and :afterDate",afterDate);
		/*
		 * if(agencyId.equals("52010102018")||agencyId.equals("52010102002")) {
		 * hql.append(" and agencyId in ('52010102018','52010102002')"); }else {
		 * hql.append(" and agencyId=:agencyId", agencyId); }
		 */
			hql.append(" order by createTime desc,id");
			return hql;
		}
		
			


}

