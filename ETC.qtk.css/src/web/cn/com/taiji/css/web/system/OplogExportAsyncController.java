/*
 * Date: 2012-11-8
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web.system;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.manager.quartz.DBExclusiveTask;
import cn.com.taiji.common.model.AsyncNoteModel;
import cn.com.taiji.css.manager.system.OplogExportTask;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;
import cn.com.taiji.css.web.MyAsyncController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2012-11-8 上午11:12:20<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/system/oplog/asyncExport")
public class OplogExportAsyncController extends MyAsyncController<String>
{
	private OplogExportTask exportTask;

	@Autowired
	public OplogExportAsyncController(DataSource ds, OplogExportTask task)
	{
		super(new DBExclusiveTask(task, MyFinals.CRON_TASK_TABLE, ds, TASK_PREFIX + "oplogExport", 60));
		this.exportTask=task;
	}

	@RequestMapping("/runTask")
	public void runAsyncTask(@Valid @ModelAttribute SystemLogPageRequest qm,
			@RequestParam(name="file",required=false) MultipartFile file, 
			HttpServletResponse response)
			throws IOException, ManagerException
	{
		if (qm.getStartTime()==null||qm.getEndTime()==null){
			throw new ManagerException("请选择日志时间范围！");
		}
		logger.info(""+file);
		if (isRunning())
		{
			addSuccess(response, "任务正在执行");//在以form提交时需要addSuccess，有文件的时候是form提交
			responseJson(new AsyncNoteModel("任务正在执行").toJson(), response);
			return;
		}
		exportTask.taskInit(qm);
		super.asyncDoTask();
		addSuccess(response, "任务将在后台执行");//在以form提交时需要addSuccess，有文件的时候是form提交
		responseJson(new AsyncNoteModel("任务将在后台执行").toJson(), response);
	}

	@RequestMapping("/runTask/process")
	public void taskProcess(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		super.responseProcess(request, response, exportTask.getMsg(), exportTask.getPercent());
	}

	@RequestMapping("/download")
	public void download(@RequestParam("fileName")String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpMimeResponseHelper.doDownLoad(request, response, new File(FileHelper.getDataPath()+"/"+fileName), "导出.xlsx");
	}
	
	@Override
	protected String getSucessResult()
	{
		return exportTask.getResult();
	}
}
