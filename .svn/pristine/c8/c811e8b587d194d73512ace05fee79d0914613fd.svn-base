/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.obu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.obu.PreactiveRequest;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallApplyResponse;
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
public class PreactiveManagerImpl extends AbstractDsiCommManager implements PreactiveManager{
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private OfflineinstallManager offlineinstallManager;

	@Override
	public Pagination queryPage(PreactiveRequest queryModel) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public InstallApplyResponse doPreactive(String obuId,User user) throws ManagerException {
		OBUInfo obuInfo = queryObuInfo(obuId);
		InstallApplyRequest obuReq= new InstallApplyRequest();
		InstallApplyResponse obuRes = new InstallApplyResponse();
		
		if(offlineinstallManager.queryObuInfo(obuId, null, user)) {
			if(obuInfo.getStatus()!=11) {
				obuRes.setMessage("该OBU已预激活");
				obuRes.setStatus(0);
				return obuRes;
			}
			super.commSet(obuReq,user);
			obuReq.setObuId(obuId);
			obuReq.setVehicleId(obuInfo.getVehicleId());
			
			try {
				obuRes= releaseBinService.installApply(obuReq);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obuRes;
	}
	public OBUInfo queryObuInfo(String obuId) {
		return oBUInfoRepo.findByObuId(obuId);
	}


}

