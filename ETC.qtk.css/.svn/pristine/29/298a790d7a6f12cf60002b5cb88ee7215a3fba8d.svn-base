package cn.com.taiji.css.manager.administration.pkg;


import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.pkg.ChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardPackageChangeResponse;
import cn.com.taiji.qtk.entity.CardPackageView;
import cn.com.taiji.qtk.entity.Package;

public interface ChangeManager {
	Pagination queryPage(ChangeRequest queryModel);
	public CardPackageView findById(String id);
	public CardPackageChangeResponse updatePackage(CardPackageView cardPackageView,User user) throws ManagerException;
	public List<Package> findAllPackage();
}
