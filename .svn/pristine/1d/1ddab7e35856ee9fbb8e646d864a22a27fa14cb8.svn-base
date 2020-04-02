package cn.com.taiji.css.web.issuetranscation;

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
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.FileCopyTools;
import cn.com.taiji.css.manager.issuetranscation.InprovinceStatisticalManager;
import cn.com.taiji.css.model.issuetranscation.InprovinceStatisticalRequest;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/inprovinceStatistical")
public class InprovinceStatisticalController extends MyLogController {
	private final String prefix = "inprovinceStatistical/";	
	@Autowired
	private InprovinceStatisticalManager inprovinceStatisticalManager;
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") InprovinceStatisticalRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
  
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") InprovinceStatisticalRequest queryModel, Model model) throws ManagerException
	{
		Pagination pagn = inprovinceStatisticalManager.findByTypeAndDate(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping("/export")
	public @ResponseBody String export(@Valid @ModelAttribute InprovinceStatisticalRequest qm) throws Exception
	{
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

}
