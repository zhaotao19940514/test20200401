/**
 * @Title OcxTestController.java
 * @Package cn.com.taiji.css.web.ocxtest
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月12日 上午11:35:34
 * @version V1.0
 */
package cn.com.taiji.css.web.ocxtest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.ocx.PosCommandManager;
import cn.com.taiji.css.manager.ocxtest.CardOcxTestManager;
import cn.com.taiji.css.model.ocx.PosBaseResponse;
import cn.com.taiji.css.model.ocx.PosConfirmRequest;
import cn.com.taiji.css.model.ocx.PosConfirmResponse;
import cn.com.taiji.css.model.ocx.PosConsumeCommandRequest;
import cn.com.taiji.css.model.ocxtest.TestApplyCardRequest;
import cn.com.taiji.css.model.ocxtest.TestApplyCardResponse;
import cn.com.taiji.css.web.BaseLogController;
import cn.com.taiji.qtk.entity.dict.CssPosTradeType;

/**
 * @ClassName OcxTestController
 * @Description TODO
 * @author yaonl
 * @date 2018年07月12日 11:35:34
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/ocx/test/")
public class OcxTestController extends BaseLogController{
	private final String prefix = "ocx/test/";
	@Autowired
	private CardOcxTestManager cardOcxTestManager;
	@Autowired
	private PosCommandManager posCommandManager;
	@RequestMapping(value="card",method = RequestMethod.GET)
	public String cardManage(){
		return prefix+"card";
	}
	@RequestMapping(value="applyCard",method = RequestMethod.POST)
	public void applyCard(@RequestBody TestApplyCardRequest applyCardModel,HttpServletResponse response) throws ManagerException{
		logger.info(applyCardModel.toJson());
		TestApplyCardResponse applyCard = cardOcxTestManager.applyCard(applyCardModel);
		try {
			HttpMimeResponseHelper.responseJson(applyCard.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
	}
	
	@RequestMapping(value="pos",method = RequestMethod.GET)
	public String posManage(Model model){
		model.addAttribute("types",CssPosTradeType.values());
		return prefix+"pos";
	}
	@RequestMapping(value="posTradeCos",method = RequestMethod.POST)
	public void posConsumeCos(@RequestBody PosConsumeCommandRequest requestModel,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		PosBaseResponse command = posCommandManager.getCommand(requestModel,LoginHelper.getLoginUser(request),CssPosTradeType.CONSUME);
		try {
			addSysLog(request, "用户({})获取了pos交易指令,交易类型：{},交易指令:{}",LoginHelper.getLoginUser(request).getId(),requestModel.getPosTradeType(),command.getCommand());
			HttpMimeResponseHelper.responseJson(command.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
	}
	@RequestMapping(value="posTradeConfirm",method = RequestMethod.POST)
	public void posConsumeConfirm(@RequestBody PosConfirmRequest requestModel,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		PosConfirmResponse confirmResponse = posCommandManager.consumeConfirm(requestModel,LoginHelper.getLoginUser(request));
		try {
			addSysLog(request, "用户({})进行了pos消费交易确认,交易指令:{},金额:{}",LoginHelper.getLoginUser(request).getId(),requestModel.getCommand(),requestModel.getAmount().toString());
			HttpMimeResponseHelper.responseJson(confirmResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
	}
	@RequestMapping(value="download",method = RequestMethod.GET)
	public String downloadManage(){
		return prefix+"download";
	}
	public static void main(String[] args) {
		String s = "222CB324";
		System.out.println(Integer.valueOf(s, 16));
	}
	
	@RequestMapping(value="obu",method = RequestMethod.GET)
	public String obuManage(Model model){
		model.addAttribute("types",CssPosTradeType.values());
		return prefix+"obu";
	}
	
	@RequestMapping(value="wqobu",method = RequestMethod.GET)
	public String wqObuManage(Model model){
		return prefix+"wqobu";
	}
	
	@RequestMapping(value="jhcard",method = RequestMethod.GET)
	public String jhCardManage(Model model){
		return prefix+"jhcard";
	}
	
	@RequestMapping(value="jlcard",method = RequestMethod.GET)
	public String jlCardManage(Model model){
		return prefix+"jlcard";
	}
}

