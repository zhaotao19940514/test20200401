package cn.com.taiji.css.manager.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.css.config.manager.TaskInfo;
import cn.com.taiji.css.manager.apply.quickapply.BatchIssueManager;

@Service
public class BatchIssueOrderTask extends AbstractCronTask {
	@Autowired
	private BatchIssueManager manager;

	protected BatchIssueOrderTask() {
		super(TaskInfo.BATCH_ISSUE_ORDER);
	}

	@Override
	public void run() {
		try {
			manager.batchIssue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
