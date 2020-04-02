/**
 * @Title MisPosController.java
 * @Package cn.com.taiji.css.web.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月1日 下午9:29:34
 * @version V1.0
 */
package cn.com.taiji.css.web.ocx;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.ocx.PosCommandManager;
import cn.com.taiji.css.model.ocx.PosBaseResponse;
import cn.com.taiji.css.model.ocx.PosCommandRequest;
import cn.com.taiji.css.model.ocx.PosConfirmRequest;
import cn.com.taiji.css.model.ocx.PosConfirmResponse;
import cn.com.taiji.css.model.ocx.PosConsumeCommandRequest;
import cn.com.taiji.css.web.BaseLogController;
import cn.com.taiji.qtk.entity.dict.CssPosTradeType;

/**
 * @ClassName MisPosController
 * @Description TODO
 * @author yaonl
 * @date 2018年08月01日 21:29:34
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/ocx/mispos")
public class MisPosController extends BaseLogController {
	@Autowired
	private PosCommandManager posCommandManager;
	@RequestMapping(value="posTradeCos",method = RequestMethod.POST)
	public void posConsumeCos(@RequestBody PosConsumeCommandRequest requestModel,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		PosBaseResponse command = posCommandManager.getCommand(requestModel,LoginHelper.getLoginUser(request),CssPosTradeType.CONSUME);
		try {
			addSysLog(request, "用户({})获取了pos消费交易指令,交易指令:{}",LoginHelper.getLoginUser(request).getId(),command.getCommand());
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
	
	@RequestMapping(value="configIniCreation",method=RequestMethod.POST)
	public void configIniCreation(@RequestParam("portNum") Integer portNum,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File config = posCommandManager.posConfigIniCreate(portNum,false);
		try {
			HttpMimeResponseHelper.doDownLoad(request, response, config, "KeeperClient.ini");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回下载文件数据失败");
		}
	}
	
	@RequestMapping(value="configIniCreationIcbc",method=RequestMethod.POST)
	public void configIniCreationIcbc(@RequestParam("portNum") Integer portNum,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File config = posCommandManager.posConfigIniCreate(portNum,true);
		try {
			HttpMimeResponseHelper.doDownLoad(request, response, config, "KeeperClient.ini");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回下载文件数据失败");
		}
	}
	
	@RequestMapping(value="posCommonCos",method = RequestMethod.POST)
	public void posCommonCos(@RequestBody PosCommandRequest requestModel,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		PosBaseResponse command = posCommandManager.getCommand(requestModel,LoginHelper.getLoginUser(request),null);
		try {
			addSysLog(request, "用户({})获取了pos"+requestModel.getPosTradeType().getValue()+"交易指令,交易指令:{}",LoginHelper.getLoginUser(request).getId(),command.getCommand());
			HttpMimeResponseHelper.responseJson(command.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
	}
}

