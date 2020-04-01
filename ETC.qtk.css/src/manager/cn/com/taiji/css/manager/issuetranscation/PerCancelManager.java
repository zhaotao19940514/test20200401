package cn.com.taiji.css.manager.issuetranscation;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.qtk.entity.PerCancel;

public interface PerCancelManager {
	public PerCancel update(PerCancel perCancel,String userName) throws ManagerException;
	public List<PerCancel> findAll();
	List<ZTreeItem> getCurrentConf();

}
