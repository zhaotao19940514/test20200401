package cn.com.taiji.css.model.customerservice.report;

import cn.com.taiji.common.entity.BaseEntity;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.qtk.entity.DuplicateRefund;

public class DuplicateRefundLogEntity extends BaseEntity {

	private DuplicateRefund duplicateRefund;
	private User user;

	public DuplicateRefundLogEntity() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateRefundLogEntity(DuplicateRefund duplicateRefund, User user) {
		super();
		this.duplicateRefund = duplicateRefund;
		this.user = user;
	}

	public DuplicateRefund getDuplicateRefund() {
		return duplicateRefund;
	}

	public void setDuplicateRefund(DuplicateRefund duplicateRefund) {
		this.duplicateRefund = duplicateRefund;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
