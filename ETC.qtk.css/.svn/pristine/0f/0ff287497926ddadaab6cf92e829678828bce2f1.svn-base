package cn.com.taiji.css.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import cn.com.taiji.common.entity.StringUUIDEntity;

/**
 * 简单定时任务
 */
//@Entity
//@Table(name = "css_time_task")
public class SimpleTimeTask extends StringUUIDEntity {

	@NotNull
	private String name;// 任务名称
	@NotNull
	private SimpleTaskType taskType;;
	@NotNull
	private LocalDateTime startTime;// 执行时间
	@NotNull
	private String job;// 任务
	private TaskStatus status=TaskStatus.NEW;// 状态
	private User user;// 提交人
	private LocalDateTime creatTime;// 提交时间

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "start_time")
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	@Column(name = "job")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "task_type")
	public SimpleTaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(SimpleTaskType taskType) {
		this.taskType = taskType;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CREATE_TIME")
	public LocalDateTime getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(LocalDateTime creatTime) {
		this.creatTime = creatTime;
	}

	public enum TaskStatus {
		NEW("未执行"), SUCCESS("成功"), FAILED("失败");

		private String value;

		private TaskStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum SimpleTaskType {
		MMS("定时彩信"), SMS("定时短信");
		private String value;

		private SimpleTaskType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

}
