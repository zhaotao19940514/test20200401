
package cn.com.taiji.css.manager.customerservice.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.finance.RefundCorrectCheckRequest;
import cn.com.taiji.qtk.entity.ReckonAccountRefundDetail;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ReckonAccountRefundDetailRepo;


@Service
public class RefundCorrectCheckManagerImpl extends AbstractDsiCommManager implements RefundCorrectCheckManager{
	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private ReckonAccountRefundDetailRepo reckonDetailRepo;
	
	
	@Override
	public LargePagination<ReckonAccountRefundDetail> queryPage(RefundCorrectCheckRequest queryModel)  throws ManagerException{
		
		return reckonDetailRepo.largePage(queryModel);
	}

}

