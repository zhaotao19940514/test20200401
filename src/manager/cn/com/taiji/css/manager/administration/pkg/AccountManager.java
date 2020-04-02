package cn.com.taiji.css.manager.administration.pkg;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.pkg.AccountRequest;
import cn.com.taiji.qtk.entity.Package;

public interface AccountManager {
	Pagination queryPage(AccountRequest queryModel);
	public Package add(Package packageInfo) throws ManagerException;
	public Package update(Package packageInfo) throws ManagerException;
	public Package findId(String id);
	public List<Package>findAll();
	public List<Package>findByServiceHallId(String serviceHallId);
	List<ZTreeItem> getCurrentConf(String packageId);
}
