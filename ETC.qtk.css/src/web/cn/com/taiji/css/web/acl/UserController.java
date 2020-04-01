package cn.com.taiji.css.web.acl;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.NoteModel;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.User.UserStatus;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.RoleManager;
import cn.com.taiji.css.manager.acl.UserManager;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.repo.request.acl.UserPageRequest;
import cn.com.taiji.css.web.BaseLogController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-30 上午10:08:19<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/acl/user")
public class UserController extends BaseLogController
{
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleManager roleManager;
	private final String prefix = "acl/user/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") UserPageRequest req, Model model, HttpServletRequest request)
	{
		model.addAttribute("statuses", UserStatus.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") UserPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", this.userManager.queryLargePage(req, LoginHelper.getLoginUser(request)));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") User user,HttpServletRequest request, Model model)
	{
		model.addAttribute("roles", roleManager.getAll(LoginHelper.getLoginUser(request)));
		return prefix + "add";
	}

	@RequestMapping(value = "/openAdd", method = RequestMethod.GET)
	public String setupOpenAdd(@ModelAttribute("pageModel") User user, HttpServletRequest request,Model model)
	{
		model.addAttribute("roles", roleManager.getAll(LoginHelper.getLoginUser(request)));
		return prefix + "openAdd";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public String setupAdd(@ModelAttribute("pageModel") User user,
			@RequestParam(value = "userName", required = false) String userName, HttpServletRequest request,Model model)
	{
		user.setName(userName);
		model.addAttribute("roles", roleManager.getAll(LoginHelper.getLoginUser(request)));
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") User user, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
		User loginUser=LoginHelper.getLoginUser(request);
		String id = userManager.add(user, loginUser);
		addSuccess(response, "添加用户成功");
		model.addAttribute("vo", userManager.findById(id));
		super.addSysLog(request, "添加用户({})成功", user.getName());
		return prefix + "result";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request,Model model)
	{
		model.addAttribute("roles", roleManager.getAll(LoginHelper.getLoginUser(request)));
		model.addAttribute("pageModel", userManager.findById(id));
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") User user, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
		User loginUser=LoginHelper.getLoginUser(request);
		User po = userManager.update(user, loginUser);
		addSuccess(response, "修改用户成功");
		model.addAttribute("vo", po);
		super.addSysLog(request, "修改用户({})成功", user.getName());
		return prefix + "result";
	}
	
	@RequestMapping(value = "/editGrid", method = RequestMethod.POST)
	public void processEditGrid(@RequestParam("pk")String id,@RequestParam("name")String name,@RequestParam("value")String value,
			HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		User oldUser=userManager.findById(id);
		switch (name) {
		case "name":
			oldUser.setName(value);
			break;
		case "male":
			oldUser.setMale(value.equals("1"));
			break;
		case "roleId":
			throw new ManagerException("修改角色时抛个异常！");
		}
		User loginUser=LoginHelper.getLoginUser(request);
		userManager.update(oldUser, loginUser);
		responseJson(new NoteModel(true, "修改成功").toJson(), response);
	}

	@RequestMapping(value = "/status/{id}/{status}", method = RequestMethod.POST)
	public String changeStatus(@PathVariable(value = "id") String id, @PathVariable("status") UserStatus status,
			Model model, HttpServletResponse response) throws ManagerException
	{
		model.addAttribute("vo", userManager.updateStatus(id, status));
		addSuccess(response, "修改用户状态成功");
		return prefix + "result";
	}

	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("pageModel", userManager.findById(id));
		return prefix + "view";
	}

	@RequestMapping(value = "/info/{id}")
	public String info(@PathVariable("id") String id, Model model) throws Exception
	{
		Thread.sleep(300);
		model.addAttribute("pageModel", userManager.findById(id));
		return prefix + "info";
	}

	@RequestMapping("/isLoginNameValid")
	public void isLoginNameValid(@RequestParam("loginName") String loginName, HttpServletResponse response)
			throws IOException
	{
		logger.debug("loginName={}", loginName);
		response.getWriter().print(userManager.findByLoginName(loginName) == null);
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
			expenseRefundApplicationResponse=userManager.saveFile(file);
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public void loginPost( @RequestBody UserRequset queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{	
		UserResponse userResponse=new UserResponse();
		try {
			File f =new File(queryModel.getFilePath()+queryModel.getFileName());
			userResponse=userManager.importExcel(userManager.getLines(f));
			if(userResponse.getStatus()==1) {
				userResponse.setMessage("此Excel内的数据已全部导入！");
			}
			HttpMimeResponseHelper.responseJson(userResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
