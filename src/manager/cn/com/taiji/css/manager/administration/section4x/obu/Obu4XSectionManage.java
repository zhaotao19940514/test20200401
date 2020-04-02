package cn.com.taiji.css.manager.administration.section4x.obu;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.administration.section4x.Obu4XSectionModel;
import cn.com.taiji.css.model.administration.section4x.Obu4XSectionRequest;

/**
 * 
 * @author lz
 * @date 2019年8月19日 上午11:58:36 
 * 卡号段管理
 */
public interface Obu4XSectionManage {

	public  LargePagination<Obu4XSectionModel> findAll(Obu4XSectionRequest request) throws ManagerException;
	
	public void add(Obu4XSectionRequest request,String staffId) throws ManagerException;
	
	public void delete(String  id) throws ManagerException;
	
	public void batchAdd(Obu4XSectionRequest request,String staffId)throws ManagerException ;
}
