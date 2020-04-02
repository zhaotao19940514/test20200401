package cn.com.taiji.css.manager.apply.quickapply;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.apply.quickapply.CardRechargeRequest;

@Service
public class CardRechargeManagerImpl extends AbstractManager  implements CardRechargeManager{

	@Override
	public Pagination queryPage(CardRechargeRequest queryModel) {
		return null;
	}

	
}
