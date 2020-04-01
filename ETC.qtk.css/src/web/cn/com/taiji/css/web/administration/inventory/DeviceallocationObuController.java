package cn.com.taiji.css.web.administration.inventory;

import java.io.IOException;
import java.util.List;

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
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.inventory.DeviceallocationObuManager;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.ScheDulingAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageOperationRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;

@Controller
@RequestMapping("/administration/inventory/deviceallocation/obu")
public class DeviceallocationObuController extends MyLogController {
	private final String prefix = "administration/inventory/deviceallocation/obu/";

	@Autowired
	private DeviceallocationObuManager deviceallocationObuManager;

	/**
	 * 添加
	 * 
	 * @throws ServiceHandleException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") StorageOperationRequest req,
			HttpServletRequest request, Model model) throws ServiceHandleException, IOException {
		List<ZTreeItem> list = deviceallocationObuManager.getCurrentConf();
		String json = JsonTools.toJsonStr(list);
		model.addAttribute("json", json);
		
//		String batchId = "DB-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + CssUtil.getRandomString(5);
//		req.setBatchId(batchId);
		req.setModel("电子标签");
		req.setUserName(LoginHelper.getLoginUser(request).getLoginName());
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") StorageOperationRequest req,
			HttpServletRequest request, Model model, HttpServletResponse response) throws ManagerException {
		ScheDulingAppResponse res;
			res = deviceallocationObuManager.add(req, LoginHelper.getLoginUser(request));
			if(res.getMessage().contains("失败")) {
				throw new ManagerException(res.getMessage());
			}
			if(res.getSuccess()==true) {
				super.doAddLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEALLOCATIONOBU, res.getStorageObuInfoBatch());
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
		CardNoCalculateResponse res = deviceallocationObuManager.generateEndId(cardNoCalculateRequest);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("结果返回失败",e);
		}
	}
	/**
	 * 调拨确认
	 * Lusb
	 * 
	 * @throws ManagerException
	 */
	@RequestMapping(value = "/confirm/{id}")
	public String processEdit(@PathVariable("id") String id, Model model, HttpServletResponse response,
			HttpServletRequest request) throws ManagerException {
		//找到这一个库存批次
		StorageObuInfoBatch storageObuInfoBatch = deviceallocationObuManager.findById1(id);
		model.addAttribute("model", storageObuInfoBatch);
		deviceallocationObuManager.updateStorageCardInfoBatch(storageObuInfoBatch);
		addSuccess(response, "调拨确认成功");
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEALLOCATIONOBU, storageObuInfoBatch);
		return prefix + "result";
	}
	
	/**
	 * 冲正 Lusb
	 * 
	 * @throws ManagerException
	 */
//	@RequestMapping(value = "/delete/{id}")
//	public String processEdits(@PathVariable("id") String id, Model model, HttpServletResponse response,
//			HttpServletRequest request) throws ManagerException {
//		//找到这一个库存批次
//		StorageObuInfoBatch storageObuInfoBatch = deviceallocationObuManager.findById1(id);
//		model.addAttribute("model", storageObuInfoBatch);
//		deviceallocationObuManager.reversalStorageObuInfoBatch(storageObuInfoBatch);
//		super.doDeleteLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGOBU, storageObuInfoBatch);
//		addSuccess(response, "冲正成功");	
//		return prefix + "result";
//	}
}
