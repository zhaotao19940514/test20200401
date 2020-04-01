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
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.inventory.DevicewarehousingObuManager;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.ObuInBoundAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageObuInfoBatchRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;
import cn.com.taiji.qtk.entity.dict.CssObuType;

@Controller
@RequestMapping("/administration/inventory/devicewarehousing/obu")
public class DevicewarehousingObuController extends MyLogController {
	private final String prefix = "administration/inventory/devicewarehousing/obu/";
	@Autowired
	private DevicewarehousingObuManager devicewarehousingObuManager;
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") StorageObuInfoBatchRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") StorageObuInfoBatchRequest queryModel,
			HttpServletRequest request, Model model) throws ManagerException {
		User user = LoginHelper.getLoginUser(request);
		String agencyId =  user.getStaff().getServiceHall().getAgencyId();
		queryModel.setAgencyId(agencyId);
		Pagination pagn = devicewarehousingObuManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		String serviceHallId = user.getStaff().getServiceHallId();
		model.addAttribute("serviceHallId", serviceHallId);
		return prefix + "queryResult";

	}

	/**
	 * 添加
	 * 
	 * @throws ServiceHandleException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") StorageObuInfoBatch obuInfoBatchModel,
			HttpServletRequest request, Model model) throws ServiceHandleException, IOException {
//		List<ZTreeItem> list = devicewarehousingObuManager.getCurrentConf();
//		String json = JsonTools.toJsonStr(list);
//		model.addAttribute("json", json);
		
//		String batchId = "RK-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + CssUtil.getRandomString(5);
//		obuInfoBatchModel.setBatchId(batchId);
		obuInfoBatchModel.setModel("电子标签");
		obuInfoBatchModel.setServiceHallName("黔通智联中心点");
		obuInfoBatchModel.setUserName(LoginHelper.getLoginUser(request).getLoginName());
		model.addAttribute("obuType", CssObuType.values());
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") StorageObuInfoBatch obuInfoBatchModel,
			HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		ObuInBoundAppResponse res = devicewarehousingObuManager.add(obuInfoBatchModel, LoginHelper.getLoginUser(request));
		if(res.getSuccess()==true){
			super.doAddLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGOBU,res.getStorageObuInfoBatch());
			logger.debug("success !!!");
		}
		if(res.getMessage().contains("失败")) {
			throw new ManagerException(res.getMessage());
		}
		addSuccess(response, res.getMessage());
		return prefix + "result";
	}
	/**
	 * ajax
	 * @param cardNoCalculateRequest
	 * @param request
	 * @param model
	 * @param response
	 * @throws ManagerException
	 */
	@RequestMapping(value = "/adda", method = RequestMethod.POST)
	public void cardCheck(@RequestBody CardNoCalculateRequest cardNoCalculateRequest,HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
		CardNoCalculateResponse res = devicewarehousingObuManager.generateEndId(cardNoCalculateRequest);
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
		StorageObuInfoBatch storageObuInfoBatch = devicewarehousingObuManager.findById1(id);
		model.addAttribute("model", storageObuInfoBatch);
		devicewarehousingObuManager.updateStorageObuInfoBatch(storageObuInfoBatch);
		super.doDeleteLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGOBU, storageObuInfoBatch);
		addSuccess(response, "冲正成功");	
		return prefix + "result";
	}
}
