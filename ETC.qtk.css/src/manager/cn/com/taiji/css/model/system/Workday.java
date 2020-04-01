package cn.com.taiji.css.model.system;

import java.time.LocalDate;

import cn.com.taiji.common.model.BaseModel;

public class Workday extends BaseModel
{
	private LocalDate day;
	private boolean inWork;

	public Workday(LocalDate day, boolean inWork)
	{
		super();
		this.day = day;
		this.inWork = inWork;
	}

	public LocalDate getDay()
	{
		return day;
	}

	public void setDay(LocalDate day)
	{
		this.day = day;
	}

	public boolean isInWork()
	{
		return inWork;
	}

	public void setInWork(boolean inWork)
	{
		this.inWork = inWork;
	}

	@Override
	public String toString()
	{
		return day + "-" + isInWork();
	}

}
