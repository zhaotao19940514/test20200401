package cn.com.taiji.css.web.administration.section4x.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.section4x.operationlog.Operation4xLogManager;
import cn.com.taiji.css.manager.administration.section4x.rollback.Section4xRollBackManager;
import cn.com.taiji.css.model.administration.section4x.Operation4xLogRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.Operation4xType;

/**
 * 
 * @author lz
 *
 */
@Controller
@RequestMapping("/administration/section4x/operationlog")
public class OperationLogController extends MyLogController {

	private final String prefix = "administration/section4x/operationlog/";

	@Autowired
	private Operation4xLogManager operation4xLogManager;
	
	@Autowired
	private Section4xRollBackManager section4xRollBackManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") Operation4xLogRequest queryModel, Model model) {
		model.addAttribute("type", Operation4xType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") Operation4xLogRequest queryModel, Model model,
			HttpServletRequest request) throws ManagerException {
		model.addAttribute("pagn", operation4xLogManager.getLog(queryModel));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/rollback/{batchNo}/{rollBackType}", method = RequestMethod.POST)
	public void rollback(HttpServletResponse response,HttpServletRequest request,@PathVariable("batchNo") String batchNo,@PathVariable("rollBackType") Integer rollBackType) throws ManagerException {
		section4xRollBackManager.doRollBack(batchNo, rollBackType,LoginHelper.getLoginUser(request).getStaffId());
		addSuccess(response, "回滚号段成功！");
	}
}
