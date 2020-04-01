package cn.com.taiji.css.manager.apply.quickapply;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.apply.recharge.AccountRechargeRequest;

public interface AccountRechargeManager {
	Pagination queryPage(AccountRechargeRequest queryModel);
}
