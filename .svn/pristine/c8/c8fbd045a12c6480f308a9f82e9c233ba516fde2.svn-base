package cn.com.taiji.css.web.customerservice.finance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.PosReverseManager;
import cn.com.taiji.css.manager.ocx.PosCommandManager;
import cn.com.taiji.css.model.issuetranscation.IcbcContractRequest;
import cn.com.taiji.css.model.ocx.PosBaseResponse;
import cn.com.taiji.css.model.ocx.PosCommandRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CssPosTradeDetail;

@Controller
@RequestMapping("/customerservice/finance/posreverse")
public class PosReverseController extends MyLogController{
	private final String prefix = "customerservice/finance/posreverse/";	
	@Autowired
	private PosReverseManager posReverseManager;
	@Autowired
	private PosCommandManager posCommandManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") IcbcContractRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
  
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") IcbcContractRequest queryModel, Model model,HttpServletRequest request,HttpServletResponse response) throws ManagerException
	{
		List<CssPosTradeDetail> pos = posReverseManager.findByReferNo(queryModel.getReferNo(),LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pos);
		return prefix+"queryResult";
	}
	
	
	@JsonProperty
	@RequestMapping(value="posCommonCos",method = RequestMethod.POST)
	public void posCommonCos(@RequestBody PosCommandRequest requestModel,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		PosBaseResponse command = posCommandManager.getCommand(requestModel,LoginHelper.getLoginUser(request),null);
		super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_POSREVERSE, CssOperateLogType.REQUEST, null, "冲正业务", requestModel);
		try {
			HttpMimeResponseHelper.responseJson(command.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
	}
	
	@JsonProperty
	@RequestMapping(value="result",method = RequestMethod.GET) 
// @RequestParam("yourData")是必不可少的，因为他指定了链接中的参数名称
    public String result(@RequestParam("split") String split,HttpServletRequest request,HttpServletResponse response) {
		posReverseManager.save(split,LoginHelper.getLoginUser(request));
        return "SUCCESS";
    }
	
}
