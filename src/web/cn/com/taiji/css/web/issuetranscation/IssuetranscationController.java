package cn.com.taiji.css.web.issuetranscation;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.issuetranscation.IssuetranscationManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.issuetranscation.IssueTranscationRequest;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/issuetranscation")
public class IssuetranscationController extends MyLogController{
	private final String prefix = "issuetranscation/";	
	@Autowired
	private IssuetranscationManager issuetranscationManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") IssueTranscationRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
  
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") IssueTranscationRequest queryModel, Model model)
	{
		Pagination pagn = issuetranscationManager.page(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestParam("rowId") String rowId, Model model, HttpServletRequest request,HttpServletResponse response) throws ManagerException
	{
		AppAjaxResponse res = issuetranscationManager.update(rowId);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
