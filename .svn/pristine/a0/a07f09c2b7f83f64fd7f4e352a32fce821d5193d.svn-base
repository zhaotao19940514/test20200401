package cn.com.taiji.css.manager.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.css.config.manager.TaskInfo;
import cn.com.taiji.css.manager.apply.quickapply.BatchOpenCardObuManager;

@Service
public class BatchOpenCardAndObuTask extends AbstractCronTask {
	@Autowired
	private BatchOpenCardObuManager manager;

	protected BatchOpenCardAndObuTask() {
		super(TaskInfo.BATCH_OPEN_CARD_OBU);
	}

	@Override
	public void run() {
		try {
			manager.batchOpen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
