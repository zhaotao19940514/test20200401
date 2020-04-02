package cn.com.taiji.css.manager.customerservice.card;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.card.CardPinUnlockingRequset;
import cn.com.taiji.css.model.customerservice.card.CardPinUnlockingResponse;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardUnblockRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardUnblockResponse;

@Service
public class CardPinUnlockingManagerImpl extends AbstractDsiCommManager implements CardPinUnlockingManager {
	
	@Autowired
	private MaintenanceBinService maintenanceBinService;

	@Override
	public CardPinUnlockingResponse CardPinUnlocking(CardPinUnlockingRequset req, User user) throws ManagerException {
		CardUnblockRequest request = new CardUnblockRequest();
		CardPinUnlockingResponse response = new CardPinUnlockingResponse();
		commSet(request, user);
		request.setCommand(req.getCommand());
		request.setCosResponse(req.getCosResponse());
		request.setPinType(req.getPinType());
		try {
			CardUnblockResponse cardUnblock = maintenanceBinService.cardUnblock(request);
			if(cardUnblock.getStatus() != 1 || cardUnblock.getCardUnblockStatus() == null || !(cardUnblock.getCardUnblockStatus().equals("1") || cardUnblock.getCardUnblockStatus().equals("2") || cardUnblock.getCardUnblockStatus().equals("3"))) {
				response.setManagerException(new ManagerException("解锁接口出错，非法的解锁状态！" + cardUnblock.getMessage()));
				response.setStatus(cardUnblock.getStatus());
				return response;
			}else {
				if(cardUnblock.getCardUnblockStatus().equals("2")) {
					response.setManagerException(new ManagerException("解锁失败！" + cardUnblock.getMessage()));
					response.setStatus(0);
					return response;
				}
			}
			response.setCommand(cardUnblock.getCommand());
			response.setCardUnblockStatus(cardUnblock.getCardUnblockStatus());
			response.setPinType(cardUnblock.getPinType());
			response.setStatus(cardUnblock.getStatus());
			response.setMessage(cardUnblock.getMessage());
			response.setCardId(req.getCardId());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setManagerException(new ManagerException("解锁接口出错！" + e.getMessage()));
		}
		return response;
	}

}
