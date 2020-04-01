package cn.com.taiji.css.web.blacklistquery;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.query.cardblacklist.CardBlackListQueryManager;
import cn.com.taiji.css.repo.request.cardblacklist.CardBLackListQueryRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardBlackList;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-30 上午10:08:19<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/acl/cardBlackList")
public class CardBlackListQueryController extends MyLogController
{
	@Autowired
	private CardBlackListQueryManager cardBlackListManager;
	
	private final String prefix = "acl/cardBlackList/";
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardBLackListQueryRequest req, Model model, HttpServletRequest request)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage",method = RequestMethod.POST)
	public String view(@ModelAttribute("queryModel") CardBLackListQueryRequest req, Model model)
	{
		LargePagination<CardBlackList> findById = cardBlackListManager.findById(req);
		model.addAttribute("pagn", findById);
		return prefix + "queryResult";
	}

}
