
package cn.com.taiji.css.manager.customerservice.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.finance.RefundImpLogRequest;
import cn.com.taiji.qtk.entity.RefundImpFailMessage;
import cn.com.taiji.qtk.entity.RefundImpLog;
import cn.com.taiji.qtk.repo.jpa.AccountRefundDetailRepo;
import cn.com.taiji.qtk.repo.jpa.RefundImpFailMessageRepo;
import cn.com.taiji.qtk.repo.jpa.RefundImpLogRepo;


@Service
public class RefundImpLogManagerImpl extends AbstractDsiCommManager implements RefundImpLogManager{
	
	
	@Autowired
	private AccountRefundDetailRepo accountRefundDetailRepo;
	
	@Autowired
	private RefundImpLogRepo refundImpLogRepo;
	@Autowired
	private RefundImpFailMessageRepo refundImpFailMessageRepo;
	
	
	@Override
	public LargePagination<RefundImpLog> queryPage(RefundImpLogRequest queryModel, User user) throws ManagerException {
		return refundImpLogRepo.largePage(queryModel);
	}


	@Override
	public List<RefundImpFailMessage> listFailMessage(String id) {
		return refundImpFailMessageRepo.listByLogId(id);
		
	}

	@Override
	public List<RefundImpFailMessage> queryDetails(RefundImpLogRequest queryModel)
			throws ManagerException {
		List<RefundImpFailMessage> list = refundImpFailMessageRepo.listByCard(queryModel.getCardId());
		return list;
	}
	
}

