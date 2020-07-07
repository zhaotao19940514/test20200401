package cn.com.taiji.css.manager.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.css.config.manager.TaskInfo;
import cn.com.taiji.css.manager.customerservice.report.DailyEverydayManager;

@Service
public class DailyEverydayTask extends AbstractCronTask{
	@Autowired
	private DailyEverydayManager dailyEverydayManager;
	protected DailyEverydayTask() {
		super(TaskInfo.DAILY_EVERYDAY);
	}

	@Override
	public void run() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String format = df.format(date);
		try {
			dailyEverydayManager.makeExel(format);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("dailyEverydayManager执行异常");
		}
	}

}
