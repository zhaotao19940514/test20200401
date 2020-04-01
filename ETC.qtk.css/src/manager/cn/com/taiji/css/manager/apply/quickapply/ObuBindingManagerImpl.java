package cn.com.taiji.css.manager.apply.quickapply;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.customerservice.card.AppCardStatusChangeResponse;
import cn.com.taiji.css.model.apply.quickapply.CardObuBindingRequest;
import cn.com.taiji.css.model.customerservice.card.ApplyCardResponse;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardObuBindingApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardObuBindingApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardObuBindingConfirmResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
@Service
public class ObuBindingManagerImpl extends AbstractDsiCommManager implements ObuBindingManager{

	@Autowired
	private CardInfoRepo cardInfoRepo;
	/*@Autowired
	private OBUInfoRepo obuInfoRepo;*/
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private InqueryBinService inqueryBinService;

	@Override
	public AppCardStatusChangeResponse cardObuBinding(@Valid CardObuBindingRequest appRequest, User user) {
		ApplyCardResponse appResponse = new ApplyCardResponse();
		AppCardStatusChangeResponse response = new AppCardStatusChangeResponse();
		if (appRequest.getApplyStep() == 1) {
			try {
				cardObuBindingPort(appRequest, appResponse,user);
			} catch (ManagerException e) {
				e.printStackTrace();
				response.setManagerException(e);
				return response;
			}

		}else {
			try {
				commCardOrderConfirm(appRequest, appResponse,user);
			} catch (ManagerException e) {
				e.printStackTrace();
				response.setManagerException(e);
				return response;
			}
		}
		response.setCardId(appRequest.getCardId());
		response.setCommand(appResponse.getCommand());
		response.setCosRecordId(appResponse.getCosRecordId());
		response.setOrderStatus(appResponse.getOrderStatus());
		response.setMessage(appResponse.getMessage());
		response.setStatus(appResponse.getStatus());
		return response;
	}
	
	private void cardObuBindingPort(CardObuBindingRequest appRequest, ApplyCardResponse appResponse,User user) throws ManagerException {
		if(appRequest.getCardId() == null) throw new ManagerException("卡号为空，请确认卡与读卡器正常！");
		CardInfo cardInfo = cardInfoRepo.findByCardId(appRequest.getCardId());
		if(cardInfo == null) throw new ManagerException("未找到卡信息！卡号："+appRequest.getCardId());

		/*List<OBUInfo> listObu = obuInfoRepo.listByVehicleIdS(cardInfo.getVehicleId());
		if(listObu == null || listObu.size() == 0) throw new ManagerException("该卡对应车辆下未找到在用OBU信息！车牌号："+cardInfo.getVehicle().getVehiclePlate());
		if(listObu.size() > 1) throw new ManagerException("该卡对应的车辆下有多个在用OBU！车牌号："+listObu.get(0).getVehicle().getVehiclePlate());
		appRequest.setObuId(listObu.get(0).getObuId());*/
		appRequest.setObuId("0");
		
		CardObuBindingApplyRequest req = new CardObuBindingApplyRequest();
		super.commSet(req,user);
		req.setCardId(appRequest.getCardId());
		req.setObuId(appRequest.getObuId());
		
		CardObuBindingApplyResponse response;
		try {
			response = releaseBinService.cardObuBindingApply(req);
			appResponse.setCommand(response.getCommand());
			appResponse.setCosRecordId(response.getCosRecordId());
			appResponse.setMessage(response.getMessage());
			appResponse.setStatus(response.getStatus());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException(e.getMessage() + "绑定失败！");
		}
		
	}
	
	private void commCardOrderConfirm(CardObuBindingRequest appRequest, ApplyCardResponse appResponse,User user)
			throws ManagerException {
		CardOrderConfirmRequest req = new CardOrderConfirmRequest();
		super.commSet(req,user);
		req.setCosType(4);// 1发行 4卡签绑定 9销卡
		req.setCosRecordId(appRequest.getCosRecordId());
		req.setCardId(appRequest.getCardId());
		req.setCommand(appRequest.getCommand());
		req.setResponse(appRequest.getCosResponse());
		try {
			CardObuBindingConfirmResponse res = inqueryBinService.obuBindingCardOrderConfirm(req);
			appResponse.setCommand(res.getCommand());
			appResponse.setStatus(res.getStatus());
			appResponse.setMessage(res.getMessage());
			appResponse.setCosRecordId(req.getCosRecordId());
			appResponse.setOrderStatus(res.getOrderStatus());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("卡签绑定指令确认失败，请联系管理员", e);
		}
	}

}
