package cn.com.taiji.css.manager.administration.pkg;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.pkg.IssuerecordsQueryRequest;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;

public interface IssueRecordsManager {
	LargePagination<CarIssuePackageInfo> queryPage(IssuerecordsQueryRequest req);
	/**
	 * 根据uuid查询发行套餐记录
	 * @param id
	 * @return
	 * @throws ManagerException
	 */
	public CarIssuePackageInfo findById(String id) throws ManagerException;
	/**
	 * 根据uuid修改发行套餐支付状态为已支付（1）
	 * @param user
	 * @param packInfo
	 * @return
	 * @throws ManagerException
	 */
	public CarIssuePackageInfo verifyPay(User user, CarIssuePackageInfo packInfo) throws ManagerException;
	/**
	 * 根据uuid修改发行套餐，将发行套餐作废（2）
	 * @param user
	 * @param id
	 * @return
	 * @throws ManagerException
	 */
	public CarIssuePackageInfo verifyRepeal(User user, String id) throws ManagerException;
}
