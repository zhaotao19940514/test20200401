package cn.com.taiji.css.web.administration.notice;

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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.notice.NoticeManager;
import cn.com.taiji.css.model.administration.notice.NoticeRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.BankSignDetail;
import cn.com.taiji.qtk.entity.dict.BankSignSendType;
import cn.com.taiji.qtk.entity.dict.BankSignServiceType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName SupplyPaymentController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/administration/notice/send")
public class NoticeController extends MyLogController {
	private final String prefix = "administration/notice/send/";
	@Autowired
	private NoticeManager noticeManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") NoticeRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") NoticeRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<BankSignDetail> pagn = noticeManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		model.addAttribute("bankSignSendType", BankSignSendType.values());
		model.addAttribute("bankSignServiceType", BankSignServiceType.values());
		model.addAttribute("customerIDType",CustomerIDType.values());
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestParam("rowId") String rowId, Model model, HttpServletRequest request,HttpServletResponse response) throws ManagerException
	{
		AppAjaxResponse res = noticeManager.update(rowId);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

