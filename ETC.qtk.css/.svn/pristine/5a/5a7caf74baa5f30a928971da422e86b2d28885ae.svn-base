/*
 * Date: 2014年6月12日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年6月12日 上午9:50:12<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SampleAsyncTask extends AbstractManager implements Runnable
{
	private String msg;
	private double percent;
	private String result;

	@Override
	public void run()
	{
		percent=0;
		result=null;
		double total = 216;
		double hasDone = 0;
		int i = 0;
		try
		{
			while (hasDone < total)
			{
				//do something
				TimeUnit.SECONDS.sleep(3);
				hasDone = i * 12.4;
				percent = hasDone / total;
				msg = toLogString("已完成任务：{}/{}", hasDone, total);
				System.out.println("执行任务中..." + i);
				if (hasDone > total){
					result = "myresult";// 任务执行完成，设置结果
					msg = "任务执行完成";
					percent = 1;
				}
				i++;
			}
		}
		catch (InterruptedException e)
		{
			logger.error("", e);
		}
	}

	public String getMsg()
	{
		return msg;
	}

	public String getResult() {
		return result;
	}

	public double getPercent()
	{
		return percent;
	}

}
