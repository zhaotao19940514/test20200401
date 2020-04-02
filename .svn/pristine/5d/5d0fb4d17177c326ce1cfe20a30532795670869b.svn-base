package cn.com.taiji.css.manager.administration.pkg;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.administration.pkg.ChangeRequest;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardPackageChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardPackageChangeResponse;
import cn.com.taiji.qtk.entity.CardPackageView;
import cn.com.taiji.qtk.entity.Package;
import cn.com.taiji.qtk.repo.jpa.CardPackageViewRepo;
import cn.com.taiji.qtk.repo.jpa.PackageRepo;
@Service
public class ChangeManagerImpl extends AbstractDsiCommManager implements ChangeManager {
   @Autowired
   private CardPackageViewRepo cardPackageViewRepo;
   @Autowired
   private PackageRepo packageRepo;
   
   @Autowired
   private MaintenanceBinService maintenanceBinService;	
	@Override
	public Pagination queryPage(ChangeRequest queryModel) {
		if(!StringTools.hasText(queryModel.getCardId())) {
			return null;
		}
		return cardPackageViewRepo.page(queryModel);
	}

	@Override
	public CardPackageView findById(String id) {
		return cardPackageViewRepo.findById(id).orElse(null);
	}

	@Override
	public CardPackageChangeResponse updatePackage(CardPackageView cardPackageView,User user) throws ManagerException {
	    Package packageInfo = packageRepo.findByPackageName(cardPackageView.getPackageName());
	    CardPackageChangeRequest req = new CardPackageChangeRequest();
		super.commSet(req,user);
	    req.setCardId(cardPackageView.getCardId());
		req.setPackageNum(packageInfo.getPackageNum());
		req.setEnableTime(cardPackageView.getStartTime());
		CardPackageChangeResponse res =null;
		try {
		   res =   maintenanceBinService.cardPackge(req);
		   if(res.getStatus()!=1) {
			   throw new ManagerException(res.getMessage());
		   }
		} catch (Exception e) {
		   throw new ManagerException("调用卡套餐变更接口异常！");
		}
		return res; 
	}

	@Override
	public List<Package> findAllPackage() {
		return packageRepo.findByPackageState();
	}

}
