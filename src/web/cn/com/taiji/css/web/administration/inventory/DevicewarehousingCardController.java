package cn.com.taiji.css.web.administration.inventory;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.inventory.DevicewarehousingCardManager;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.CardInBoundAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageCardInfoBatchRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;

@Controller
@RequestMapping("/administration/inventory/devicewarehousing/card")
public class DevicewarehousingCardController extends MyLogController {
	private final String prefix = "administration/inventory/devicewarehousing/card/";
	@Autowired
	private DevicewarehousingCardManager devicewarehousingCardManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") StorageCardInfoBatchRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") StorageCardInfoBatchRequest queryModel,
			HttpServletRequest request, Model model) throws ManagerException {
		User user = LoginHelper.getLoginUser(request);
		String agencyId =  user.getStaff().getServiceHall().getAgencyId();
		queryModel.setAgencyId(agencyId);
		Pagination pagn = devicewarehousingCardManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		String serviceHallId = user.getStaff().getServiceHallId();
		model.addAttribute("serviceHallId", serviceHallId);
		return prefix + "queryResult";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") StorageCardInfoBatch cardInfoBatchModel,
			HttpServletRequest request, Model model) throws ManagerException {
//		String agencyId =  LoginHelper.getLoginUser(request).getStaff().getServiceHall().getAgencyId();
//		List<ZTreeItem> list = devicewarehousingCardManager.getCurrentConf(agencyId);
//		String json = JsonTools.toJsonStr(list);
//		model.addAttribute("json", json);
//		String batchId = "RK-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + CssUtil.getRandomString(5);
//		cardInfoBatchModel.setBatchId(batchId);
		cardInfoBatchModel.setModel("黔通卡");
		cardInfoBatchModel.setServiceHallName("黔通智联中心点");
		cardInfoBatchModel.setUserName(LoginHelper.getLoginUser(request).getLoginName());
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") StorageCardInfoBatch cardInfoBatchModel,
			HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		CardInBoundAppResponse res = devicewarehousingCardManager.add(cardInfoBatchModel,
				LoginHelper.getLoginUser(request));
		if(res.getMessage().contains("失败")) {
			throw new ManagerException(res.getMessage());
		}
		if(res.getSuccess()==true) {
			super.doAddLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGCARD,cardInfoBatchModel);
		}
		addSuccess(response, res.getMessage());
		return prefix + "result";
	}
	
/*	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request, Model model,@ModelAttribute("pageModel") StorageCardInfoBatch cardInfoBatchModel) {
		model.addAttribute("pageModel", devicewarehousingCardManager.findById1(id));
		String batchId = devicewarehousingCardManager.generateBatchId();
		model.addAttribute("randomBatchId",batchId);
		cardInfoBatchModel.setBatchId(batchId);
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") StorageCardInfoBatch storageCardInfoBatchModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws Exception {
		ScheDulingResponse po;
		po = devicewarehousingCardManager.updateCssCardInfo(storageCardInfoBatchModel, LoginHelper.getLoginUser(request));
		addSuccess(response, po.getMessage());
//		model.addAttribute("vo", po);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGCARD, po);
		return prefix + "result";

	}*/
	/**
	 * ajax 计算卡片入库结束编号
	 * @param cardNoCalculateRequest
	 * @param request
	 * @param model
	 * @param response
	 * @throws ManagerException
	 */
	@RequestMapping(value = "/adda", method = RequestMethod.POST)
	public void cardCheck(@RequestBody CardEndCalculateRequest cardNoCalculateRequest,HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
		CardEndCalculateResponse res = devicewarehousingCardManager.generateEndId(cardNoCalculateRequest);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("结果返回失败",e);
		}
	}
	
	/**
	 * 冲正 Lusb
	 * 
	 * @throws ManagerException
	 */
	@RequestMapping(value = "/delete/{id}")
	public String processEdit(@PathVariable("id") String id, Model model, HttpServletResponse response,
			HttpServletRequest request) throws ManagerException {
		//找到这一个库存批次
		StorageCardInfoBatch storageCardInfoBatch = devicewarehousingCardManager.findById1(id);
		model.addAttribute("model", storageCardInfoBatch);
		devicewarehousingCardManager.updateStorageCardInfoBatch(storageCardInfoBatch);
		super.doDeleteLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGCARD, storageCardInfoBatch);
		addSuccess(response, "冲正成功");	
		return prefix + "result";
	}
	
	/*
	 * tree
	 */
	/*@RequestMapping(value = "/conf", method = RequestMethod.GET)
	public String setupConf(@ModelAttribute("pageModel") StorageCardInfoBatch cardInfoBatchModel, Model model, HttpServletRequest request) throws IOException
	{
//		String ownerRoleId=LoginHelper.getLoginUser(request).getRole().getId();
		List<ZTreeItem> list = devicewarehousingCardManager.getCurrentConf();
		String json = JsonTools.toJsonStr(list);
		model.addAttribute("json", json);
		return prefix + "conf";
	}*/
}
