package cn.com.taiji.css.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.com.taiji.common.manager.quartz.RunnableProxy;
import cn.com.taiji.common.manager.quartz.TaskListener;
import cn.com.taiji.common.model.AsyncProcessModel;
import cn.com.taiji.common.model.AsyncSucessModel;
import cn.com.taiji.common.model.quartz.TaskEvent;
import cn.com.taiji.common.web.JsonValidController;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.quartz.SimpleExclusiveTask;

/**
 * 单虚拟机内，多用户并发、单用户互斥任务
 * 测试：
 * 1、任务启动后，关掉浏览器，tasks能自动清掉
 * 2、多用户并发
 * 3、单用户不能并发
 * @author wanglijun
 * 		   Create Time 2017年7月7日 下午2:58:08
 * @since 1.0
 * @version 1.0
 * @param <E>
 * @param <T>
 */
public abstract class MultiUserTaskController<E,T extends Runnable> extends JsonValidController
{
	
	private ConcurrentHashMap<User, RunnableProxy>  tasks=new ConcurrentHashMap<User, RunnableProxy>();
	
	public final boolean isRunning(HttpServletRequest request)
	{
		RunnableProxy proxyTask = tasks.get(LoginHelper.getLoginUser(request));
		return proxyTask!=null;//注：有任务就代表running
	}

	/**
	 * percent==0返回startmodel（预留），percent==1返回successModel
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 * @param percent
	 * @throws IOException
	 * @see {@link #getSucessResult(HttpServletRequest)}
	 */
	protected final void responseProcess(HttpServletRequest request, HttpServletResponse response, String msg,
			double percent) throws IOException
	{
		if (percent < 0) throw new RuntimeException("不是有效的进度数值." + percent);
		if (percent == 1)
		{
			AsyncSucessModel<E> rs = new AsyncSucessModel<E>();
			rs.setMsg(msg);
			rs.setTime(Calendar.getInstance());
			rs.setResult(getSucessResult(request));
			responseJson(rs.toJson(), request, response);
			return;
		}
		AsyncProcessModel model = new AsyncProcessModel();
		model.setMsg(msg);
//		model.setRunning(isRunning());
		tasks.forEach((u,t)->{
			logger.debug(u.getLoginName()+":"+t.getTask());
		});
		model.setPercent(percent);
		responseJson(model.toJson(), request, response);
	}

	/**
	 * 返回当前用户正在执行的task，如果没有返回null
	 * @return
	 */
	protected final T getOwnTask(HttpServletRequest request){
		RunnableProxy proxyTask = tasks.get(LoginHelper.getLoginUser(request));
		if(proxyTask==null)return null;
		@SuppressWarnings("unchecked")
		T task=(T) proxyTask.getTask();
		return task;
	}
	
	protected final void asyncDoTask(HttpServletRequest request, T task)
	{
		User user = LoginHelper.getLoginUser(request);
		SimpleExclusiveTask proxyTask = new SimpleExclusiveTask(task,user.getId(),taskListener);
		tasks.put(user, proxyTask);
		getExecutor().execute(proxyTask);
	}
	
	protected abstract ThreadPoolTaskExecutor getExecutor();
	
	/**
	 * 可以通过覆盖本方法得到返回值
	 * @param request 
	 * @return
	 */
	protected E getSucessResult(HttpServletRequest request)
	{
		return null;
	}
	
	private TaskListener taskListener=new TaskListener() {
		
		@Override
		public void taskFinish(TaskEvent event) {
			User user=new User();
			user.setId(event.getTaskName());
			try {
				//给轮询留时间
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RunnableProxy proxyTask = tasks.remove(user);
			logger.debug("清除任务{}->{}",user.getLoginName(),proxyTask.getTask());
		}
		
		@Override
		public void taskBegin(TaskEvent event) {
			
		}
	};
}
