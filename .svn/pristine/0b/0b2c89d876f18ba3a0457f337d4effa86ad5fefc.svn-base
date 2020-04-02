package cn.com.taiji.css.web.issuetranscation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.issuetranscation.IssuetranscationManager;
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
	
}
