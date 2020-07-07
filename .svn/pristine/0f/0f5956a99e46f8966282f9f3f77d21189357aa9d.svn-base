package cn.com.taiji.css.manager.quartz;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.css.config.manager.TaskInfo;
import cn.com.taiji.css.manager.customerservice.report.DailyStatisticsManager;

@Service
public class DailyWorkBookTask extends AbstractCronTask {
	@Autowired
	private DailyStatisticsManager manager;

	protected DailyWorkBookTask() {
		super(TaskInfo.DAILY_WORKBOOK);
	}

	@Override
	public void run() {
		String date = LocalDate.now().toString();
		try {
			manager.run(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
