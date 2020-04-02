/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.cardobuquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.dict.AccountStatus;
import cn.com.taiji.css.manager.customerservice.cardobuquery.ObudeviceManager;
import cn.com.taiji.css.model.customerservice.cardobuquery.ObudeviceRequest;
import cn.com.taiji.css.web.BaseLogController;

/**
 * @ClassName RechargeController
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:14:54
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/customerservice/cardobuquery/obudevice")
public class ObudeviceController extends BaseLogController {
	private final String prefix = "customerservice/cardobuquery/obudevice/";
	
	@Autowired
	private ObudeviceManager obudeviceManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ObudeviceRequest queryModel, Model model)
	{
		model.addAttribute("status", AccountStatus.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ObudeviceRequest queryModel, Model model)
	{
		Pagination pagn = obudeviceManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
}

