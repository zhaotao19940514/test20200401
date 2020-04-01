package cn.com.taiji.css.web.administration.section4x.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.section4x.card.Card4XSectionManage;
import cn.com.taiji.css.model.administration.section4x.Card4XSectionRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.DeviceVersion;

/**
 * 
 * @author lz
 *
 */
@Controller
@RequestMapping("/administration/section4x/card")
public class Card4XSectionController extends MyLogController {

	private final String prefix = "administration/section4x/card/";

	@Autowired
	private Card4XSectionManage card4XSectionManage;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") Card4XSectionRequest queryModel, Model model) {
		model.addAttribute("version", DeviceVersion.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") Card4XSectionRequest queryModel, Model model,
			HttpServletRequest request) throws ManagerException {
		model.addAttribute("pagn", card4XSectionManage.findAll(queryModel));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addGet(@ModelAttribute("addModel") Card4XSectionRequest addModel, Model model) {
		model.addAttribute("version", DeviceVersion.values());
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addPost(@ModelAttribute("addModel") Card4XSectionRequest addModel, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		card4XSectionManage.add(addModel,LoginHelper.getLoginUser(request).getStaffId());
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_SECTION4X_MANAGEMENT, CssOperateLogType.ADD, null, "4x卡号段添加", addModel);
		addSuccess(response, "添加号段成功！");
		
	}
	
	@RequestMapping(value = "/batchAdd", method = RequestMethod.GET)
	public void batchAddGet(@ModelAttribute("addModel") Card4XSectionRequest addModel, Model model) {
		model.addAttribute("version", DeviceVersion.values());
	}
	
	@RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
	public void batchAddPost(@ModelAttribute("addModel") Card4XSectionRequest addModel, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		card4XSectionManage.batchAdd(addModel,LoginHelper.getLoginUser(request).getStaffId());
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_SECTION4X_MANAGEMENT, CssOperateLogType.ADD, null, "4x卡号段批量添加", new Card4XSectionRequest());
		addSuccess(response, "添加号段成功！");
	}

//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//	public String delete(@PathVariable("id") String id, Model model, HttpServletRequest request,
//			HttpServletResponse response) throws ManagerException {
//		card4XSectionManage.delete(id);
//		addSuccess(response, "删除号段成功！");
//		return prefix + "result";
//	}
}
