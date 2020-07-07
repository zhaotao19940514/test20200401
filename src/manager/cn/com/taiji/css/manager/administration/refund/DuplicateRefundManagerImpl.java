package cn.com.taiji.css.manager.administration.refund;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.refund.DuplicateRefundPageRequest;
import cn.com.taiji.qtk.entity.DuplicateRefund;
import cn.com.taiji.qtk.repo.jpa.DuplicateRefundRepo;

@Service
public class DuplicateRefundManagerImpl implements DuplicateRefundManager{

	@Autowired
	private DuplicateRefundRepo duplicateRefundRepo;
	@Override
	public LargePagination<DuplicateRefund> queryLargePage(DuplicateRefundPageRequest duplicateRefundPageRequest) {
		return duplicateRefundRepo.largePage(duplicateRefundPageRequest);
	}
	
	@Override
	public DuplicateRefund findById(String id)
	{
		DuplicateRefund duplicateRefund = duplicateRefundRepo.findById(id).orElse(null);
		if(duplicateRefund == null) return null; 
		return duplicateRefund;
	}

	@Override
	public void paymentBack(DuplicateRefund duplicateRefund, User loginUser) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		MyViolationException myViolationException = new MyViolationException();
		if(duplicateRefund.getPay() == -1) {
			myViolationException.addViolation("pay", "请选择是否回款");
		}
		
		DuplicateRefund duplicateRefund1 = duplicateRefundRepo.findById(duplicateRefund.getId()).orElse(null);
		if(duplicateRefund1.getUpdateTime() != null) {
			//user_id + 用户在update_time 执行了确定退款操作，银行回款结果+ pay
			String str = duplicateRefund1.getUserId()+"\\"+duplicateRefund1.getUpdateTime()+"\\"+duplicateRefund1.getPay();
			duplicateRefund1.setRemark(str);
		}
		duplicateRefund1.setUpdateTime(format);
		duplicateRefund1.setPay(duplicateRefund.getPay());
		duplicateRefund1.setStatus(1);
		duplicateRefund1.setUserId(loginUser.getStaffId());
		if(myViolationException.hasViolation()) {
			throw myViolationException;
		}
		duplicateRefundRepo.save(duplicateRefund1);
	}

	
}
