/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.cardobuquery;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.customerservice.cardobuquery.CarddeviceRequest;

/**
 * @ClassName RechargeManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:38
 * @E_mail yaonanlin@163.com
 */
@Service
public class CarddeviceManagerImpl extends AbstractManager implements CarddeviceManager{

	/* (non-Javadoc)
	 * @see cn.com.taiji.css.manager.customerservice.finance.RechargeManager#queryPage(cn.com.taiji.css.model.customerservice.finance.RechargeRequest)
	 */

	

	@Override
	public Pagination queryPage(CarddeviceRequest queryModel) {
		// TODO 自动生成的方法存根
		return null;
	}


}

