package cn.com.taiji.css.web.issuetranscation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.issuetranscation.CardAnnounceRecordManager;
import cn.com.taiji.css.manager.issuetranscation.CardAnnounceRecordModel;
import cn.com.taiji.css.model.issuetranscation.CardAnnounceRecordRequest;
import cn.com.taiji.css.web.MyLogController;
@Controller
@RequestMapping("/cardAnnounceRecord")
public class CardAnnounceRecordController extends MyLogController{
  
	private final String prefix = "cardAnnounceRecord/";	
	@Autowired
	private CardAnnounceRecordManager cardAnnounceRecordManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardAnnounceRecordRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
  
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardAnnounceRecordRequest queryModel, Model model)
	{
		LargePagination<CardAnnounceRecordModel> pagn = cardAnnounceRecordManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
}
