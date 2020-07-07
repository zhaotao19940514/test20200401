package cn.com.taiji.css.manager.customerservice.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.taiji.css.model.customerservice.report.DailyEverydayRequest;

public interface DailyEverydayManager {
	public void export(DailyEverydayRequest queryModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public void makeExel(String time) throws Exception;
}
