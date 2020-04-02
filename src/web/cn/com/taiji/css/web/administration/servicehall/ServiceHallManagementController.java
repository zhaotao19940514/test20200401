/**
 * @Title ServiceHallController.java
 * @Package cn.com.taiji.css.web.administration.servicehall
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月28日 下午2:49:20
 * @version V1.0
 */
package cn.com.taiji.css.web.administration.servicehall;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.LabelIdPair;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.administration.agency.AgencyManager;
import cn.com.taiji.css.manager.serviceHall.ServiceHallManager;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.ServiceHall;

/**
 * @ClassName ServiceHallController
 * @Description TODO
 * @author yaonl
 * @date 2018年08月28日 14:49:20
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/administration/servicehall/")
public class ServiceHallManagementController extends MyLogController {
	private final String prefix = "administration/servicehall/";
	@Autowired
	private ServiceHallManager serviceHallManager;
	@Autowired
	private AgencyManager agencyManager;

	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ServiceHallRequest req) {
		return prefix + "manage";
	}

	@RequestMapping(value = "manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ServiceHallRequest req, Model model) {
		model.addAttribute("pagn", serviceHallManager.page(req));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String toAdd(@ModelAttribute("addModel") ServiceHall serviceHallToAdd,Model model) {
		List<Agency> agencies = agencyManager.findAll();
		model.addAttribute("agencies", agencies);
		return prefix + "add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String doAdd(@ModelAttribute("addModel") ServiceHall serviceHallToAdd, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ManagerException {
		ServiceHall serviceHall = serviceHallManager.add(serviceHallToAdd);
		addSuccess(response, "添加网点成功！");
		model.addAttribute("vo",serviceHall);
		doAddLog(request, CssServiceLogType.ADMINISTRATION_SERVICEHALL_MANAGEMENT, serviceHall);
		return prefix + "result";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String toEdit(@ModelAttribute("editModel") ServiceHall serviceHallToEdit, @PathVariable("id") String id, Model model) {
		ServiceHall serviceHall = serviceHallManager.findById(id);
		List<Agency> agencies = agencyManager.findAll();
		model.addAttribute("agencies", agencies);
		model.addAttribute("editModel", serviceHall);
		return prefix + "edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String doEdit(@ModelAttribute("editModel") ServiceHall serviceHallToEdit, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ManagerException {
		ServiceHall serviceHall = serviceHallManager.edit(serviceHallToEdit);
		addSuccess(response, "修改网点信息成功");
		model.addAttribute("vo", serviceHall);
		doUpdateLog(request, CssServiceLogType.ADMINISTRATION_SERVICEHALL_MANAGEMENT, serviceHall);
		return prefix + "result";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws ManagerException {
		ServiceHall serviceHall = serviceHallManager.findById(id);
		serviceHallManager.delete(serviceHall);
		addSuccess(response, "删除网点成功");
		doDeleteLog(request, CssServiceLogType.ADMINISTRATION_SERVICEHALL_MANAGEMENT, serviceHall);
		return prefix + "result";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		ServiceHall serviceHall = serviceHallManager.findById(id);
		model.addAttribute("vo", serviceHall);
		return prefix + "view";
	}
	
	@RequestMapping("/queryByName")
	public void queryByName(@RequestParam("queryServiceHallId") String name, HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		List<LabelIdPair> pairs = LabelIdPair.fromList(serviceHallManager.queryByName(name), "name", "serviceHallId");
		super.responseJson(JsonTools.toJsonStr(pairs), response);
	}
	
	@RequestMapping("/queryByNameForModal")
	public void queryByNameForModal(@RequestParam("serviceHallId") String name, HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		List<LabelIdPair> pairs = LabelIdPair.fromList(serviceHallManager.queryByName(name), "name", "serviceHallId");
		super.responseJson(JsonTools.toJsonStr(pairs), response);
	}
	
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String setupFile(@ModelAttribute("pageModel") User user,HttpServletRequest request, Model model)
	{
		return prefix + "file";
	}
	
	@RequestMapping( value ="/handleFile" , method = RequestMethod.POST )
	   public void fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response){
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse = new ExpenseRefundApplicationResponse();
		try {
			expenseRefundApplicationResponse=serviceHallManager.saveFile(file);
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public void loginPost( @RequestBody UserRequset queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) 
	{	
		UserResponse userResponse=new UserResponse();
			File f =new File(queryModel.getFilePath()+queryModel.getFileName());
				try {
					userResponse=serviceHallManager.importExcel(serviceHallManager.getLines(f));
				} catch (IOException e) {
					e.printStackTrace();
					userResponse.setMessage("读取Excel文件失败，请重新上传！");
					userResponse.setStatus(-1);
				} catch (ManagerException e) {
					e.printStackTrace();
					userResponse.setMessage("此Excel内存在重复的网点编号，请核查！");
					userResponse.setStatus(-1);
				}
			if(userResponse.getStatus()==1) {
				userResponse.setMessage("此Excel内的数据已全部导入！");
			}
			try {
				HttpMimeResponseHelper.responseJson(userResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}
