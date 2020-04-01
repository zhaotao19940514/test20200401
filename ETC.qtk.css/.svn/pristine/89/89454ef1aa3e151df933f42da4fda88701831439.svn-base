package cn.com.taiji.css.web.blacklistquery;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.query.cardblacklist.CardBlackListIncrQueryManager;
import cn.com.taiji.css.repo.request.cardblacklist.CardBLackListIncrQueryRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardBlackListIncr;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-30 上午10:08:19<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/acl/cardBlackListIncr")
public class CardBlackListIncrQueryController extends MyLogController
{
	@Autowired
	private CardBlackListIncrQueryManager cardBlackListIncrManager;
	
	private final String prefix = "acl/cardBlackListIncr/";
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardBLackListIncrQueryRequest req, Model model, HttpServletRequest request)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage",method = RequestMethod.POST)
	public String view(@ModelAttribute("queryModel") CardBLackListIncrQueryRequest req, Model model)
	{
		LargePagination<CardBlackListIncr> findById = cardBlackListIncrManager.findById(req);
		model.addAttribute("pagn", findById);
		return prefix + "queryResult";
	}

}
