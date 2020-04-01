/**
 * @Title AgencyManager.java
 * @Package cn.com.taiji.css.model.administration.agency
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月28日 下午2:48:41
 * @version V1.0
 */
package cn.com.taiji.css.manager.administration.agency;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.request.agency.AgencyRequest;
import cn.com.taiji.qtk.entity.Agency;

/**
 * @ClassName AgencyManager
 * @Description TODO
 * @author yaonl
 * @date 2018年08月28日 14:48:41
 * @E_mail yaonanlin@163.com
 */
public interface AgencyManager {

	Pagination page(AgencyRequest req);

	Agency add(Agency agencyToAdd) throws ManagerException;

	Agency findById(String id);

	Agency edit(Agency agencyToEdit) throws ManagerException;

	void delete(Agency agency) throws ManagerException;
	
	List<Agency> findAll();

	public List<Agency> queryByName(String name);

	Agency findAccount(String accountId);
}

