package cn.com.taiji.css.manager.administration.agency.permission;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.administration.agency.permission.AgencyPermissionModel;
import cn.com.taiji.css.model.administration.agency.permission.AgencyPermissionRequest;
import cn.com.taiji.qtk.entity.Agency;

/**
 * 
 * @author lz
 *
 */
public interface AgencyPermissionManager {

	/**
	 * 批量删除
	 * @param batchDeleteModel
	 * @throws ManagerException
	 */
	public void batchDelete(AgencyPermissionRequest batchDeleteModel)throws ManagerException;
	
	/**
	 * 批量增加
	 * @param batchAddModel
	 * @throws ManagerException
	 */
	public void batchAdd(AgencyPermissionRequest batchAddModel)throws ManagerException;
	
	/**
	 * 修改数据
	 * @param id
	 * @param editModel
	 * @return
	 */
	public AgencyPermissionModel edit(AgencyPermissionRequest editModel) throws ManagerException;

	/**
	 * 
	 * @param id
	 * @throws ManagerException
	 */
	public void delete(String id) throws ManagerException;
	
	/**
	 * 
	 * @param addModel
	 * @return
	 * @throws ManagerException
	 */
	public AgencyPermissionModel add(AgencyPermissionRequest addModel)throws ManagerException;
	
	/**
	 * 分页查询
	 * @param queryModel
	 * @return
	 * @throws ManagerException
	 */
	public LargePagination<AgencyPermissionModel> query(AgencyPermissionRequest queryModel)throws ManagerException;
	
	/**
	 * 模糊查询机构名称
	 * @param name
	 * @return
	 */
	public List<Agency> findLikeName(String name);
	
	/**
	 * 获取当前表中数据
	 * @param id
	 * @return
	 * @throws ManagerException
	 */
	public AgencyPermissionModel getAgencyVarifyInfo(String id) throws ManagerException;
	
	/**
	 * 获取机构信息
	 * @return
	 */
	public List<Agency> getAgencyInfo();
	
	/**
	 * 获取机构Id
	 * @param
	 * @return
	 */
	public List<Agency> queryIdLikeName(String name);
	
	/**
	 * 获取机构id
	 * @param id
	 * @return
	 */
	public String getAgencyIdById(String id);
}
