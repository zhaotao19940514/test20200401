/**
 * @Title OcxTestManagerImpl.java
 * @Package cn.com.taiji.css.manager.ocxtest
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月12日 下午4:36:06
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocxtest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.model.ocxtest.TestApplyCardRequest;
import cn.com.taiji.css.model.ocxtest.TestApplyCardResponse;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.CommQtkRequset;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardConfirmResponse;

/**
 * @ClassName OcxTestManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年07月12日 16:36:06
 * @E_mail yaonanlin@163.com
 */
@Service
public class CardOcxTestManagerImpl extends AbstractManager implements CardOcxTestManager {
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private InqueryBinService inqueryBinService;
	
	@Override
	public TestApplyCardResponse applyCard(TestApplyCardRequest appRequest) throws ManagerException {
		appRequest.validate();
		TestApplyCardResponse appResponse = new TestApplyCardResponse();
		if(appRequest.getApplyStep()==1){
			commCardApply(appRequest, appResponse);
		}else{
			commCardOrderConfirm(appRequest, appResponse);
			if (appResponse.getOrderStatus()==2) {
				commCardConfirm(appRequest, appResponse);
			}
		}
		return appResponse;
	}

	private void commCardConfirm(TestApplyCardRequest appRequest, TestApplyCardResponse appResponse) throws ManagerException {
		CardConfirmRequest req = new CardConfirmRequest();
		commset(req);
		// req.setCosRecordId("e6f2fdb671df48709733ac5cf0591229");
		req.setCardId(appRequest.getCardId());
		req.setCardType(appRequest.getCardType()==1?111:211);
		req.setEnableTime(appRequest.getEnableTime());
		req.setExpireTime(appRequest.getExpireTime());
		//记账卡 套餐编号   FIXME 应该通过发行套餐编号  查出发行套餐 并设置相应值 
		req.setNetId(appRequest.getNetId());
		req.setBrand(1);
		req.setModel("0");
		req.setPackageNum(Integer.valueOf(appRequest.getPkgId()));
		try {
			CardConfirmResponse res = releaseBinService.cardConfirm(req);
			appResponse.setMessage(res.getMessage());
			appResponse.setStatus(res.getStatus());
			appResponse.setCardId(res.getCardId());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("开卡确认失败，请联系管理员",e);
		}
	}

	private void commCardOrderConfirm(TestApplyCardRequest appRequest, TestApplyCardResponse appResponse) throws ManagerException {
		CardOrderConfirmRequest req = new CardOrderConfirmRequest();
		commset(req);
		req.setCosType(1);//1发行 4卡签绑定 9销卡
		req.setCosRecordId(appRequest.getCosRecordId());
		req.setCardId(appRequest.getCardId());
		req.setCommand(appRequest.getCommand());
		req.setResponse(appRequest.getCosResponse());
		try {
			CardOrderConfirmResponse res = inqueryBinService.applyCardOrderConfirm(req);
			appResponse.setCommand(res.getCommand());
			appResponse.setStatus(res.getStatus());
			appResponse.setMessage(res.getMessage());
			appResponse.setCosRecordId(req.getCosRecordId());
			appResponse.setOrderStatus(res.getOrderStatus());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("开卡指令确认失败，请联系管理员",e);
		}
	}

	private void commCardApply(TestApplyCardRequest appRequest, TestApplyCardResponse appResponse)
			throws ManagerException {
		CardApplyRequest req = new CardApplyRequest();
		commset(req);
		req.setCardId(appRequest.getCardId());
		req.setCardType(appRequest.getCardType());
		req.setCosProvider(1);
		String enableTime = appRequest.getEnableTime();
		req.setEnableTime(enableTime.substring(0,enableTime.indexOf('T')).replace("-", ""));
		String expireTime = appRequest.getExpireTime();
		req.setExpireTime(expireTime.substring(0,enableTime.indexOf('T')).replace("-", ""));
		req.setUserId(appRequest.getUserId());
		req.setVehicleId(appRequest.getVehicleId());
		req.setStaffId("无");//FIXME 应该从当前登陆用的信息中获取 
		try {
			CardApplyResponse res = releaseBinService.cardApply(req);
			appResponse.setCommand(res.getCommand());
			appResponse.setCosRecordId(res.getCosRecordId());
			appResponse.setMessage(res.getMessage());
			appResponse.setStatus(res.getStatus());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("开卡申请失败，请联系管理员",e);
		}
	}
	
	private void commset(CommQtkRequset req) {
		req.setTerminalId("020000000000");
		req.setAgentId("52010106004");
		req.setChannelId("5201010200601130001");
		req.setChannelType(2);
		req.setStaffId("无");
		req.setSubmitTime("2017-11-09T16:30:30");
	}
	
	public static void main(String[] args) {
		
	}
}

