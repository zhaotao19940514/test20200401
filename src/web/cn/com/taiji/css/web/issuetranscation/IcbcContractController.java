package cn.com.taiji.css.web.issuetranscation;

import java.io.IOException;

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

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.issuetranscation.IcbcContractManager;
import cn.com.taiji.css.manager.ocx.PosCommandManager;
import cn.com.taiji.css.model.issuetranscation.IcbcContractRequest;
import cn.com.taiji.css.model.ocx.PosBaseResponse;
import cn.com.taiji.css.model.ocx.PosCommandRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

@Controller
@RequestMapping("/contract")
public class IcbcContractController extends MyLogController{
	private final String prefix = "contract/";	
	@Autowired
	private IcbcContractManager icbcContractManager;
	@Autowired
	private PosCommandManager posCommandManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") IcbcContractRequest queryModel, Model model)
	{
		model.addAttribute("CssVehiclePlateColorType", VehiclePlateColorType.withoutUnknown(7));
		model.addAttribute("CustomerIDType", CustomerIDType.values());
		return prefix+"manage";
	}
  
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") IcbcContractRequest queryModel, Model model) throws ManagerException
	{
		Pagination pagn = icbcContractManager.page(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/view/{id}/{bankId}", method = RequestMethod.GET)
	public String managePost(@PathVariable("id")String id,@PathVariable("bankId")String bankId,Model model)
	{
		model.addAttribute("vo", icbcContractManager.findCardInfo(id));
		model.addAttribute("bank", bankId);
		return prefix+"view";
	}
	
	@JsonProperty
	@RequestMapping(value="posCommonCos",method = RequestMethod.POST)
	public void posCommonCos(@RequestBody PosCommandRequest requestModel,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		PosBaseResponse command = posCommandManager.getCommand(requestModel,LoginHelper.getLoginUser(request),null);
		try {
			HttpMimeResponseHelper.responseJson(command.toJson(), response);
			icbcContractManager.save(requestModel,request);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
	}
	
	@JsonProperty
	@RequestMapping(value="result",method = RequestMethod.GET) 
// @RequestParam("yourData")是必不可少的，因为他指定了链接中的参数名称
    public String result(@RequestParam("split") String split) {
		icbcContractManager.update(split);
        return "SUCCESS";
    }
	
}
