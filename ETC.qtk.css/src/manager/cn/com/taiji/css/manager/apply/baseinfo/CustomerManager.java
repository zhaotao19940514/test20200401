package cn.com.taiji.css.manager.apply.baseinfo;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.apply.customermanager.CustomerManagerRequest;
import cn.com.taiji.css.model.apply.customermanager.CustomerManagerResponse;
import cn.com.taiji.css.model.apply.customermanager.EgCustomerManagerRequest;
import cn.com.taiji.css.model.apply.quickapply.InfoCheckRequset;
import cn.com.taiji.css.model.apply.quickapply.InfoCheckResponse;
import cn.com.taiji.qtk.entity.CustomerInfo;

public interface CustomerManager {
	LargePagination<CustomerInfo> queryPage(CustomerManagerRequest req);
	
	Pagination queryPage(EgCustomerManagerRequest req);
	
	public CustomerInfo findByCustomerId(String customerId) throws ManagerException;
	public String add(QuickApplyPngModel model, User user) throws ManagerException;
	public String update(QuickApplyPngModel customerInfo, User user) throws ManagerException;
	public List<String> getScreenShotBase64(CustomerInfo customerInfo) throws ManagerException;
	/**
	 * 开户，用户信息和车辆信息校验
	 * @param req
	 * @return
	 */
	public InfoCheckResponse quickApplyCheck(InfoCheckRequset req,User user);

	CustomerManagerResponse updatePassWord(CustomerManagerRequest model, User user) throws ManagerException;
	
	CustomerManagerResponse initializationPassWord(CustomerManagerRequest model, User user) throws ManagerException;
}
