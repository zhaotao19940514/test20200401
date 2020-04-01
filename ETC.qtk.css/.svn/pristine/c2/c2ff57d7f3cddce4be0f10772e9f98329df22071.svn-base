package cn.com.taiji.css.manager.system;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import cn.com.taiji.css.model.system.Workday;

public interface WorkdayManager {
	/** 
	 *月日历视图中一个月的显示的天
	 */
	List<Workday> listByMonth(int year, Month month);
	/**
	 * 获取下一个工作日
	 */
	LocalDate nextWorkday(LocalDate day);
	/**
	 * 获取上一个工作日
	 */
	LocalDate prevWorkday(LocalDate day);
	/**
	 * 计算日期之间有几个工作日
	 */
	int countWorkday(LocalDate from, LocalDate to);
	/**
	 * 是否工作日，当前实现返回默认情况
	 * 可修改此方法，结合数据库表来返回状态
	 */
	boolean isWorkday(LocalDate date);

}
