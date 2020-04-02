package cn.com.taiji.css.web.system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.AsyncNoteModel;
import cn.com.taiji.css.manager.system.OplogExportTask;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;
import cn.com.taiji.css.web.MultiUserTaskController;


@Controller
@RequestMapping("/system/oplogExport/multiUser")
public class OplogExportMultiUserTaskController extends MultiUserTaskController<String,OplogExportTask>
{
	@Autowired
	@Qualifier("myExecutor")
	private ThreadPoolTaskExecutor executor;

	@RequestMapping(value = "/runTask")
	public void runTask(@Valid @ModelAttribute SystemLogPageRequest qm,
			@RequestParam(name="file",required=false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response) throws JsonManagerException, IOException{
		if (qm.getStartTime()==null||qm.getEndTime()==null){
			throw new JsonManagerException("请选择日志时间范围！");
		}
		if (isRunning(request)) {
			throw new JsonManagerException("您后台还有正在运行的任务！");
		}
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		OplogExportTask exportTask = wac.getBean(OplogExportTask.class);
		exportTask.taskInit(qm);
		super.asyncDoTask(request,exportTask);
		addSuccess(response, "任务将在后台执行");//在以form提交时需要addSuccess，有文件的时候是form提交
		responseJson(new AsyncNoteModel("任务将在后台执行").toJson(), response);
	}
	
	
	@RequestMapping(value = "/process")
	public void process(HttpServletRequest request,HttpServletResponse response) throws IOException{
		OplogExportTask task = getOwnTask(request);
		super.responseProcess(request, response,task==null?"":task.getMsg(), task==null?0:task.getPercent());
	}


	@Override
	protected ThreadPoolTaskExecutor getExecutor()
	{
		return executor;
	}


	@Override
	protected String getSucessResult(HttpServletRequest request){
		OplogExportTask task = getOwnTask(request);
		return task==null?"":task.getResult();
	}

}
