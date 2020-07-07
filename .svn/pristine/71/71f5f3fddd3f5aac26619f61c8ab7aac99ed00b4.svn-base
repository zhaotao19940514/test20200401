package cn.com.taiji.css.web.customerservice.cardobuquery;

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
import cn.com.taiji.css.manager.daily.QueryOutOBUDisposeManager;
import cn.com.taiji.css.model.request.queryoutobudispose.QueryOutOBUDisposeRequest;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/customerservice/cardobuquery/queryOutOBUDispose")
public class QueryOutOBUDisposeController extends BaseLogController {
	private final String prefix = "customerservice/cardobuquery/queryOutOBUDispose/";
	private String excelFilePath = FileHelper.getDataPath() + "/queryOutOBUDispose";
	
	@Autowired
	private QueryOutOBUDisposeManager queryOutOBUDisposeManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") QueryOutOBUDisposeRequest queryModel, Model model,
			HttpServletRequest req)
	{
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String outManagePost(@Valid @ModelAttribute("queryModel") QueryOutOBUDisposeRequest queryModel,
			Model model, HttpServletRequest req) throws ManagerException {
		return prefix + "queryResult";
	}
	
	@RequestMapping(value = "/export")
	public @ResponseBody String export(@Valid @ModelAttribute("queryModel") QueryOutOBUDisposeRequest queryModel) throws Exception
	{
		File file = queryOutOBUDisposeManager.run(queryModel.getDate(), excelFilePath + "/" + queryModel.getDate() + ".xlsx");	
		if (file == null) {
			throw new ManagerException("导出时,文件生成失败");
		}
		return file.getName();
	}

	@RequestMapping("/export/file")
	public void export(@RequestParam("result") String result, HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		HttpMimeResponseHelper.doDownLoad(request, response, new File(excelFilePath + "/" + result),
				result);
	}

}

