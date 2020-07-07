package cn.com.taiji.css.manager.administration.refund;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.refund.DuplicateRefundPageRequest;
import cn.com.taiji.qtk.entity.DuplicateRefund;

public interface DuplicateRefundManager{
	LargePagination<DuplicateRefund> queryLargePage(DuplicateRefundPageRequest duplicateRefundPageRequest);

	public DuplicateRefund findById(String id);

	void paymentBack( DuplicateRefund duplicateRefund, User loginUser);
}