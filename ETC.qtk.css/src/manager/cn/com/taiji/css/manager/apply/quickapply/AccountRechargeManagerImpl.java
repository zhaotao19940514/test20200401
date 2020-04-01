package cn.com.taiji.css.manager.apply.quickapply;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.apply.recharge.AccountRechargeRequest;

@Service
public class AccountRechargeManagerImpl extends AbstractManager  implements AccountRechargeManager{

	@Override
	public Pagination queryPage(AccountRechargeRequest queryModel) {
		return null;
	}
	
}
