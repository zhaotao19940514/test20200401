package cn.com.taiji.css.manager.administration.section4x.card;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.administration.section4x.Card4XSectionModel;
import cn.com.taiji.css.model.administration.section4x.Card4XSectionRequest;

/**
 * 
 * @author lz
 * @date 2019年8月19日 上午11:58:36 
 * 卡号段管理
 */
public interface Card4XSectionManage {

	public  LargePagination<Card4XSectionModel> findAll(Card4XSectionRequest request) throws ManagerException;
	
	public void add(Card4XSectionRequest request,String staffId) throws ManagerException;
	
	public void delete(String  id) throws ManagerException;
	
	public void batchAdd(Card4XSectionRequest request,String staffId)throws ManagerException ;
}
