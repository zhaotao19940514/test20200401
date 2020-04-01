package cn.com.taiji.css.repo.request.timing;

import org.springframework.data.domain.Sort.Direction;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.css.entity.SimpleTimeTask;
import cn.com.taiji.css.entity.SimpleTimeTask.SimpleTaskType;
import cn.com.taiji.css.entity.SimpleTimeTask.TaskStatus;

public class SimpleTimeTaskPageRequest extends JpaDateTimePageableDataRequest<SimpleTimeTask>{

	private String name;//任务名称
	private TaskStatus status;//状态
	private SimpleTaskType taskType;//任务分类
    
	public SimpleTimeTaskPageRequest(){
		this.orderBy="startTime";
		this.desc = true;
		this.appendOrder("status", Direction.ASC);
	}
	

	public String getName() {
		return name;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void  setName(String name) {
		this.name=name;
	}

	public void  setStatus(TaskStatus status) {
		this.status=status;
	}
	
	public SimpleTaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(SimpleTaskType taskType) {
		this.taskType = taskType;
	}


	@Override
	public HqlBuilder toSelectHql(){
		HqlBuilder hql = new HqlBuilder("from SimpleTimeTask task where 1=1 ");
		hql.append(" and task.name=:name", name);
		hql.append(" and task.status=:status", status);
		hql.append(" and task.taskType=:taskType",taskType);
		return hql;
	}

}
