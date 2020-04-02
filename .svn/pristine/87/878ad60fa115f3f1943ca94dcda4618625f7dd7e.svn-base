package cn.com.taiji.css.manager.administration.pkg;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.pkg.IssueRequest;
import cn.com.taiji.qtk.entity.IssuePackageInfo;

public interface IssueManager {
	Pagination queryPage(IssueRequest queryModel);
	public IssuePackageInfo add(IssuePackageInfo  IssuePackageInfo) throws ManagerException;
	public IssuePackageInfo update(IssuePackageInfo IssuePackageInfo,String userName) throws ManagerException;
	public IssuePackageInfo findId(String id);	
	public List<IssuePackageInfo> findAll();
	public List<IssuePackageInfo> findByServiceHallId(String serviceHallId,Integer type);
	List<ZTreeItem> getCurrentConf(String packageId);
	
	public List<IssuePackageInfo> listByEmergency(List<String> packageNums);

}
