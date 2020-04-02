package cn.com.taiji.css.manager.timing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.TimeTools;
import cn.com.taiji.css.entity.SimpleTimeTask;
import cn.com.taiji.css.entity.SimpleTimeTask.TaskStatus;
import cn.com.taiji.css.repo.request.timing.SimpleTimeTaskPageRequest;

import com.google.common.collect.Maps;


/**
 * 
 * @author wanglijun
 * 		   Create Time 2017年6月8日 下午3:23:39
 * @since 1.0
 * @version 1.0
 */
@Service("simpleTimeTaskManager")
public class SimpleTimeTaskManagerImpl extends AbstractManager implements SimpleTimeTaskManager {

	private Map<String,SimpleTimeTaskRunner> runners = Maps.newConcurrentMap();
	
	private SimpleTimeTaskRepoMock repo=new SimpleTimeTaskRepoMock();
	
	@Override
	@Transactional(readOnly = true)
	public Pagination queryPage(SimpleTimeTaskPageRequest req){
		return repo.page(req);
	}
	
	
	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public SimpleTimeTask add(SimpleTimeTask task) throws JsonManagerException {
		repo.save(task);
		try {
			startRunner(task);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JsonManagerException("启动定时任务失败！");
		}
		return task;
	}

	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public SimpleTimeTask updateTime(SimpleTimeTask model) throws JsonManagerException {
		SimpleTimeTask po=repo.findOne(model.getId());
		if(po.getStatus()!=TaskStatus.NEW){
			throw new JsonManagerException("只能修改未执行任务");
		}
		po.setStartTime(model.getStartTime());
		repo.save(po);
		try {
			destroyRunner(po);
			startRunner(po);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JsonManagerException("启动定时任务失败！");
		}
		return po;
	}

	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public void delete(String id) throws JsonManagerException {
		SimpleTimeTask po=repo.findOne(id);
		if(po==null)throw new JsonManagerException("找不到记录！");
		if(po.getStatus()!=TaskStatus.NEW)throw new JsonManagerException("任务不能删除！");
		repo.delete(po);
		destroyRunner(po);
	}
	
	@Override
	public SimpleTimeTask findById(String id) {
		return repo.findOne(id);
	}
	
	@PostConstruct
	private void initTaskRunner() throws Exception{
		List<SimpleTimeTask> tasks = repo.listByStatus(TaskStatus.NEW);
		for (SimpleTimeTask task : tasks) {
			startRunner(task);
		}
	}
	
	private SimpleTimeTaskRunner  startRunner(SimpleTimeTask task) throws Exception{
		if(task.getStartTime().compareTo(LocalDateTime.now())<0){
			return null;
		}
		SimpleTimeTaskRunner runner = new SimpleTimeTaskRunner(task.getName(), ()->{this.executeTask(task);}, true, TimeTools.localDateTime2date(task.getStartTime()));
		runners.put(task.getId(), runner);
		runner.start();
		return  runner;
	}
	

	public void executeTask(SimpleTimeTask task){
		logger.info("执行定时任务：task={},job={}",task.getName(),task.getJob());
		task.setStatus(TaskStatus.SUCCESS);
		destroyRunner(task);
	}
	
	private void destroyRunner(SimpleTimeTask task){
		SimpleTimeTaskRunner runner = runners.remove(task.getId());
		if(runner!=null){
			runner.stop();
		}
	}
	
	@Override
	public Map<String, SimpleTimeTaskRunner> getRunners() {
		return runners;
	}
	
}
