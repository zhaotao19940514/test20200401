/**
 * @Title RechargeDetailManagerImpl.java
 * @Package cn.com.taiji.css.manager.apply.baseinfo
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月21日 下午12:35:47
 * @version V1.0
 */
package cn.com.taiji.css.manager.apply.baseinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;

/**
 * @ClassName RechargeDetailManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年08月21日 12:35:47
 * @E_mail yaonanlin@163.com
 */
@Service
public class ChargeDetailManagerImpl extends AbstractManager implements ChargeDetailManager {
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	@Override
	public ChargeDetail findByChargeDetailId(String chargeDetailId) {
		return chargeDetailRepo.findByChargeId(chargeDetailId);
	}
}

