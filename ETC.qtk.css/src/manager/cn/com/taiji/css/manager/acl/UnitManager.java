package cn.com.taiji.css.manager.acl;

import java.util.List;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.acl.UnitModel;
import cn.com.taiji.css.repo.request.acl.UnitPageRequest;

public interface UnitManager {

	public Pagination queryPage(UnitPageRequest req, User user);
	
	public Unit add(Unit model) throws JsonManagerException;
	
	public Unit update(Unit model) throws JsonManagerException;
	
	public void delete(String id)throws JsonManagerException;
	
	public Unit findById(String id);

	public List<UnitModel> listByParentId(String parentId);


}
