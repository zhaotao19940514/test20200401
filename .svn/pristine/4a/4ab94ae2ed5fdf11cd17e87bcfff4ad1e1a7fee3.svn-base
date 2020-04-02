/*
 * Date: 2011-6-2
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web.system;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.pub.FileCopyTools;
import cn.com.taiji.css.entity.dict.LogType;
import cn.com.taiji.css.manager.system.SystemLogManager;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;
import cn.com.taiji.css.web.MyBaseController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-6-2 下午02:54:56<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/system/oplog")
public class OplogController extends MyBaseController
{
	@Autowired
	private SystemLogManager logManager;
	private final String prefix = "system/oplog/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SystemLogPageRequest queryModel, Model model)
	{
		model.addAttribute("logTypes", LogType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SystemLogPageRequest queryModel, Model model)
	{
		model.addAttribute("pagn", this.logManager.queryPage(queryModel));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/multiRemove", method = RequestMethod.POST)
	public void multiRemove(@RequestParam("ids") String[] ids, HttpServletResponse response)
	{
		for (String id : ids)
		{
			logger.info(id);
		}
		addSuccess(response, "批量删除成功");
	}

	@RequestMapping(value = "/chart/multi")
	public void chartMulti(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpMimeResponseHelper.responseJson(logManager.multiGroup(), request, response);
	}

	@RequestMapping(value = "/chart/single")
	public void chartSingle(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpMimeResponseHelper.responseJson(logManager.singleGroup(), request, response);
	}

	@RequestMapping("/export")
	public @ResponseBody String export(@Valid @ModelAttribute SystemLogPageRequest qm) throws Exception
	{
		if (qm.getStartTime() == null || qm.getEndTime() == null)
		{
			throw new ManagerException("请选择日志时间范围！");
		}
		String uuid = "cbf76dbeb3ca4250b309da6166ec8e5a";
		File file = new File(FileHelper.getWebappPath() + "/template/oplogTemplate.xlsx");
		FileCopyTools.copy(file, new File(FileHelper.getDataPath() + "/" + uuid));
		return uuid;

	}

	@RequestMapping("/export/file")
	public void export(@RequestParam("result") String result, HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		HttpMimeResponseHelper.doDownLoad(request, response, new File(FileHelper.getDataPath() + "/" + result),
				"导出.xlsx");
	}

	/**
	 * 仅仅是一个示例而已，所以没用使用参数
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/word")
	public void word(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		File file = logManager.export();
		try
		{
			addSuccess(response, "下载成功");
			HttpMimeResponseHelper.doDownLoad(request, response, file, "操作日志.doc");
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
			logger.warn("取消下载或者下载中断");
		}
		finally
		{
			if (!file.delete()) logger.warn("delete pdf file failed:{}", file.getAbsolutePath());
		}
	}

}
