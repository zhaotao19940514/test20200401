package cn.com.taiji.css.manager.apply.baseinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.apply.customermanager.AccountTradeRequest;
import cn.com.taiji.qtk.entity.AccountTradeDetail;
import cn.com.taiji.qtk.repo.jpa.AccountTradeDetailRepo;
@Service
public class AccountTradeManagerImpl extends AbstractManager implements AccountTradeManager{
	
	@Autowired
	private AccountTradeDetailRepo accountTradeDetailRepo;
	@Override
	public LargePagination<AccountTradeDetail> queryPage(AccountTradeRequest queryModel) {
		
		return accountTradeDetailRepo.largePage(queryModel);
	}

}
