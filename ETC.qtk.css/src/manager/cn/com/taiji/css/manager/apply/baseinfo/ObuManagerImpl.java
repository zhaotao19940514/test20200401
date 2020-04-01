/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.apply.baseinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.model.customerservice.cardobuquery.ObuRequest;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;

/**
 * @ClassName RechargeManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:38
 * @E_mail yaonanlin@163.com
 */
@Service
public class ObuManagerImpl extends AbstractManager implements ObuManager{

	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Override
	public LargePagination<OBUInfo> queryPage(ObuRequest req) {
		if(StringTools.hasText(req.getCustomerId()) || StringTools.hasText(req.getObuId())  
				|| StringTools.hasText(req.getVehicleId()) || req.getStatus() != null 
				|| StringTools.hasText(req.getVehiclePlate()) || req.getVehiclePlateColor() != null
				) {
			return oBUInfoRepo.largePage(req);
		}else {
			return null;
		}
	}
	@Override
	public OBUInfo findByObuId(String obuId) {
		return oBUInfoRepo.findByObuId(obuId);
	}


}

