package cn.com.taiji.css.manager.apply.baseinfo;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.apply.customermanager.AccountTradeRequest;
import cn.com.taiji.qtk.entity.AccountTradeDetail;

public interface AccountTradeManager {
	LargePagination<AccountTradeDetail> queryPage(AccountTradeRequest queryModel);
}
