package cn.com.taiji.css.manager.timing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.css.entity.SimpleTimeTask;
import cn.com.taiji.css.entity.SimpleTimeTask.TaskStatus;

/**
 * 
 * @author wanglijun
 * 		   Create Time 2017年6月8日 上午10:15:53
 * @since 1.0
 * @version 1.0
 */
public class SimpleTimeTaskRepoMock {

	private HashMap<String, SimpleTimeTask> values=new HashMap<String, SimpleTimeTask>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E> Pagination page(JpaPageableDataRequest<E> qm) {
		Pagination pg = new Pagination(qm.getPageNo(), values.keySet().size(), qm.getPageSize());
		pg.setResult(new ArrayList(values.values()));
		return pg;
	}
	
	public <S extends SimpleTimeTask> S save(S entity) {
		values.put(entity.getId(), entity);
		return entity;
	}
	
	public SimpleTimeTask findOne(String id) {
		return values.get(id);
	}
	
	public void delete(SimpleTimeTask entity) {
		values.remove(entity.getId());
	}

	public List<SimpleTimeTask> listByStatus(TaskStatus new1) {
		return new ArrayList<SimpleTimeTask>();
	}
	

}
