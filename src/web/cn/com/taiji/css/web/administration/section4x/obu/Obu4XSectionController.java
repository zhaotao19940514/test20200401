package cn.com.taiji.css.web.administration.section4x.obu;

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
import cn.com.taiji.css.manager.administration.section4x.obu.Obu4XSectionManage;
import cn.com.taiji.css.model.administration.section4x.Card4XSectionRequest;
import cn.com.taiji.css.model.administration.section4x.Obu4XSectionRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.DeviceVersion;

/**
 * 
 * @author lz
 *
 */
@Controller
@RequestMapping("/administration/section4x/obu")
public class Obu4XSectionController extends MyLogController {

	private final String prefix = "administration/section4x/obu/";

	@Autowired
	private Obu4XSectionManage obu4XSectionManage;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") Obu4XSectionRequest queryModel, Model model) {
		model.addAttribute("version", DeviceVersion.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") Obu4XSectionRequest queryModel, Model model,
			HttpServletRequest request) throws ManagerException {
		model.addAttribute("pagn", obu4XSectionManage.findAll(queryModel));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void addGet(@ModelAttribute("addModel") Obu4XSectionRequest addModel, Model model) {
		model.addAttribute("version", DeviceVersion.values());
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addPost(@ModelAttribute("addModel") Obu4XSectionRequest addModel, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		obu4XSectionManage.add(addModel,LoginHelper.getLoginUser(request).getStaffId());
		addSuccess(response, "添加号段成功！");
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_SECTION4X_MANAGEMENT, CssOperateLogType.ADD, null, "4xOBU号段添加", addModel);
	}
	
	@RequestMapping(value = "/batchAdd", method = RequestMethod.GET)
	public void batchAddGet(@ModelAttribute("addModel") Obu4XSectionRequest addModel, Model model) {
		model.addAttribute("version", DeviceVersion.values());
	}
	
	@RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
	public void batchAddPost(@ModelAttribute("addModel") Obu4XSectionRequest addModel, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		obu4XSectionManage.batchAdd(addModel,LoginHelper.getLoginUser(request).getStaffId());
		addSuccess(response, "添加号段成功！");
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_SECTION4X_MANAGEMENT, CssOperateLogType.ADD, null, "4xOBU号段批量添加", new Card4XSectionRequest());
	}

//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//	public String delete(@PathVariable("id") String id, Model model, HttpServletRequest request,
//			HttpServletResponse response) throws ManagerException {
//		obu4XSectionManage.delete(id);
//		addSuccess(response, "删除号段成功！");
//		return prefix + "result";
//	}
}
