/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.finance.BalanceReckonRequest;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
/**
 * 
 *@ClassName BalanceReckonManagerImpl.java
 *@Description: 
 *@author:zhaot
 *@date: 2020年7月7日
 */
@Service
public class BalanceReckonManagerImpl extends AbstractDsiCommManager implements BalanceReckonManager{
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Override
	public LargePagination<CardInfo> queryPage(BalanceReckonRequest queryModel, User user) throws ManagerException {
		
		return cardInfoRepo.largePage(queryModel);
	}

}

