package cn.com.taiji.css.manager.administration.notify;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.notify.NotifyDeskRequest;
import cn.com.taiji.css.model.administration.notify.NotifyRequest;
import cn.com.taiji.qtk.entity.Notify;

public interface NotifyManager {
	Pagination queryPage(NotifyRequest queryModel);
	
	Pagination queryNotifyDeskPage(NotifyDeskRequest queryModel);
	public Notify add(Notify notifyModel) throws ManagerException;

	public Notify findById1(String id);

	Notify updateNotify(Notify notifyModel,User user) throws ManagerException;

	void delete(String id);
	
	Notify update(String id);
	
	Notify qxZd(String id);
	
//	public String getScreenShotBase64(Notify notifyModel) throws ManagerException;

}
