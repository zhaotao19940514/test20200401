package cn.com.taiji.css.web.testcontroller.lz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/administration/agency")
public class AllController extends MyLogController {

	private final String prefix = "testjsp/lz/";

	@RequestMapping(value = "/inventoryManage", method = RequestMethod.GET)
	public String manage1Get(Model model) {
		return prefix + "inventoryManage";
	}

	@RequestMapping(value = "/daySettleConfirmManage", method = RequestMethod.GET)
	public String manage2Get(Model model) {
		return prefix + "daySettleConfirmManage";
	}

	@RequestMapping(value = "/clerkDaySettleManage", method = RequestMethod.GET)
	public String manage3Get(Model model) {
		return prefix + "clerkDaySettleManage";
	}

	@RequestMapping(value = "/inventoryDaySettleManage", method = RequestMethod.GET)
	public String manage4Get(Model model) {
		return prefix + "inventoryDaySettleManage";
	}

	@RequestMapping(value = "/moneyDaySettleManage", method = RequestMethod.GET)
	public String manage5Get(Model model) {
		return prefix + "moneyDaySettleManage";
	}

	@RequestMapping(value = "/accountManage", method = RequestMethod.GET)
	public String manage6Get(Model model) {
		return prefix + "accountManage";
	}

	@RequestMapping(value = "/passBillManage", method = RequestMethod.GET)
	public String manage7Get(Model model) {
		return prefix + "passBillManage";
	}

	@RequestMapping(value = "/chargeBillManage", method = RequestMethod.GET)
	public String manage8Get(Model model) {
		return prefix + "chargeBillManage";
	}

	@RequestMapping(value = "/sellManage", method = RequestMethod.GET)
	public String manage9Get(Model model) {
		return prefix + "sellManage";
	}

	@RequestMapping(value = "/complaintManage", method = RequestMethod.GET)
	public String manage10Get(Model model) {
		return prefix + "complaintManage";
	}

	@RequestMapping(value = "/chanleBonusManage", method = RequestMethod.GET)
	public String manage11Get(Model model) {
		return prefix + "chanleBonusManage";
	}

	@RequestMapping(value = "/areaBonusManage", method = RequestMethod.GET)
	public String manage12Get(Model model) {
		return prefix + "areaBonusManage";
	}

	@RequestMapping(value = "/provincePaidManage", method = RequestMethod.GET)
	public String manage13Get(Model model) {
		return prefix + "provincePaidManage";
	}

	@RequestMapping(value = "/areaPaidManage", method = RequestMethod.GET)
	public String manage14Get(Model model) {
		return prefix + "areaPaidManage";
	}

	@RequestMapping(value = "/chanelPaidManage", method = RequestMethod.GET)
	public String manage15Get(Model model) {
		return prefix + "chanelPaidManage";
	}

	@RequestMapping(value = "/wasteManage", method = RequestMethod.GET)
	public String manage16Get(Model model) {
		return prefix + "wasteManage";
	}

	
	///////
	
	@RequestMapping(value = "/errorManage", method = RequestMethod.GET)
	public String manage17Get(Model model) {
		return prefix + "errorManage";
	}

	@RequestMapping(value = "/messageManage", method = RequestMethod.GET)
	public String manage18Get(Model model) {
		return prefix + "messageManage";
	}

	@RequestMapping(value = "/systemMonitorManage", method = RequestMethod.GET)
	public String manage19Get(Model model) {
		return prefix + "systemMonitorManage";
	}

}
