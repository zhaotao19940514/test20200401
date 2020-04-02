package cn.com.taiji.css.web.customerservice.report;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.pub.FileCopyTools;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.manager.customerservice.report.LssuancePerBankManager;
import cn.com.taiji.css.model.customerservice.report.QueryTimes;
import cn.com.taiji.css.web.MyBaseController;

/**
 * 
 * ALL贵籍 & ALL非贵籍 各行发行量统计（拆分五大行）
 *
 */
@Controller
@RequestMapping("/customerservice/report/lssuancePerBank")
public class LssuancePerBankCountController extends MyBaseController{
	
	private final String prefix = "customerservice/report/lssuancePerBank/";
	
	@Autowired
	private LssuancePerBankManager lssuancePerBankManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") QueryTimes queryModel,Model model)
	{
		return prefix+"manage";
	}

	
	@RequestMapping(value = "/manage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String managePost(@RequestBody QueryTimes queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException, IOException
	{  
		return JsonTools.toJsonStr(lssuancePerBankManager.page(queryModel));
	}
		
	@RequestMapping("/export")
	public @ResponseBody String export(@Valid @ModelAttribute QueryTimes qm) throws Exception
	{
		if (qm.getStartTime() == null || qm.getEndTime() == null)
		{
			throw new ManagerException("请选择日志时间范围！");
		}
		String uuid = "cbf86dbeb3ca4250b309da7897ec8e5a";
		File file = lssuancePerBankManager.getResult(qm);
		FileCopyTools.copy(file, new File(FileHelper.getDataPath() + "/lssuancePerBank/" + uuid));
		return uuid;

	}

	@RequestMapping("/export/file")
	public void export(@RequestParam("result") String result, HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		HttpMimeResponseHelper.doDownLoad(request, response, new File(FileHelper.getDataPath() + "/lssuancePerBank/" + result),
				"各行发行量查询.xls");
	}

}
