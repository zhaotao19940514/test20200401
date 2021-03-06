/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.card;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.model.customerservice.card.CancelRequest;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeRequest;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeResponse;
import cn.com.taiji.css.model.customerservice.card.LossRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.QtkServiceError;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardInfoChangeApplyByCosRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardInfoChangeApplyByCosResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardInfoChangeConfirmByCosRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardInfoChangeConfirmByCosResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.Temporary4XCard;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.Temporary4XCardRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;

@Service
public class CardInformationChangeManagerImpl extends AbstractDsiCommManager implements CardInformationChangeManager{

	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private VehicleInfoRepo vehicleInfoRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Autowired
	private Temporary4XCardRepo temporary4XCardRepo;
	@Override
	public LargePagination<CardInfo> queryPage(CancelRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		Temporary4XCard findByCardId = temporary4XCardRepo.findByCardId(queryModel.getCardId());
		//针对4.1 4X版本写卡做特殊处理，不做渠道校验
		if(findByCardId!=null) {
			queryModel.setAgencyId(user.getStaff().getServiceHall().getAgencyId());
			return cardInfoRepo.largePage(queryModel);
		}
		if(!user.getRole().isSystem()) {
			CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
			LossRequest lossReq = new LossRequest();
			lossReq.setCardId(queryModel.getCardId());
			lossReq.setVehicleId(queryModel.getVehicleId());
			lossReq.setVehicleColor(queryModel.getVehicleColor());
			boolean falg =false;
			try {
				falg = agencyVarifyManager.varifyAgency(user, cardInfo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ManagerException("渠道校验失败："+e.getMessage());
			}
			if(falg) {
				queryModel.setAgencyId(user.getStaff().getServiceHall().getAgencyId());
				return cardInfoRepo.largePage(queryModel);
			}else {
				throw new ManagerException("当前渠道无权操作此卡");
			}
		}
		return null;
	}
	
	
	@Override
	public CardInfo findById(@Valid String id) {
		// TODO Auto-generated method stub
		return cardInfoRepo.findByCardId(id);
	}

	@Override
	public CardInformationChangeRequest modelAdd(String cardId) throws ManagerException {
		 CardInformationChangeRequest queryModel = new CardInformationChangeRequest(); 
		CardInfo cardInfo=cardInfoRepo.findByCardId(cardId);
		if(cardInfo==null) {
			throw new ManagerException("未找到该卡号对应的卡片信息,请联系管理员补全信息!");
		}
		if(cardInfo.getCustomer()==null) {
			throw new ManagerException("未找到该卡号对应的用户信息,请联系管理员补全信息!");
		}
		queryModel.setCardId(cardInfo.getCardId());
		if(cardInfo.getVehicle()!=null) {
			queryModel.setVehiclePlate(cardInfo.getVehicle().getVehiclePlate());
			queryModel.setVehiclePlateColor(cardInfo.getVehicle().getVehiclePlateColor());
		}
		queryModel.setCustomerName(cardInfo.getCustomer().getCustomerName());
		queryModel.setCustomerIdNum(cardInfo.getCustomer().getCustomerIdNum());
		queryModel.setExpireTime(cardInfo.getExpireTime());
		queryModel.setStatus(cardInfo.getStatus());
		return queryModel;
	}



	@Override
	public CardInformationChangeResponse applyCardOrderConfirm(CardInformationChangeRequest cardInformationChangeRequest,User user) throws ServiceHandleException {
		CardInformationChangeResponse cardInformationChangeResponse=new CardInformationChangeResponse();
		CardInfoChangeApplyByCosResponse cardInfoResponse =new CardInfoChangeApplyByCosResponse();
		CardInfoChangeApplyByCosRequest cardInfoRequest =new CardInfoChangeApplyByCosRequest();
		CardInfoChangeConfirmByCosRequest cardInfoChangeConfirmByCosRequest =new CardInfoChangeConfirmByCosRequest();
		CardInfoChangeConfirmByCosResponse cardInfoChangeConfirmByCosResponse = new CardInfoChangeConfirmByCosResponse();
		try {
			if(cardInformationChangeRequest.getApplyStep()==1) {//卡信息变更申请接口
				cardInfoRequest.setCardId(cardInformationChangeRequest.getCardId());
				cardInfoRequest.setVehiclePlate(cardInformationChangeRequest.getVehiclePlate());
				cardInfoRequest.setVehiclePlateColor(cardInformationChangeRequest.getVehiclePlateColor().toString());
				cardInfoRequest.setCustomerName(cardInformationChangeRequest.getCustomerName());
				cardInfoRequest.setCustomerIdNum(cardInformationChangeRequest.getCustomerIdNum());
				cardInfoRequest.setExpireTime(cardInformationChangeRequest.getExpireTime());
				
				String vehicleId = cardInfoRequest.getVehiclePlate() + "_" + cardInfoRequest.getVehiclePlateColor();
				VehicleInfo vehicleInfo = vehicleInfoRepo.findByVehicleId(vehicleId);
				if(vehicleInfo == null){
					throw QtkServiceError.CHECK_FAILED.toHandleException("未找到车辆信息:"+vehicleId);
				}
				if(vehicleInfo.getEmergencyFlag()!=null && vehicleInfo.getEmergencyFlag()==1){
					cardInfoRequest.setCardVersion(42);
				}
				super.commSet(cardInfoRequest,user);
				cardInfoResponse=maintenanceBinService.cardInfoChange(cardInfoRequest);
				cardInformationChangeResponse.setCommand(cardInfoResponse.getCommand());
				cardInformationChangeResponse.setCosRecordId(cardInfoResponse.getCosRecordId());
				cardInformationChangeResponse.setMessage(cardInfoResponse.getMessage());
				cardInformationChangeResponse.setStatus(cardInfoResponse.getStatus());
				cardInformationChangeResponse.setCardId(cardInformationChangeRequest.getCardId());
				cardInformationChangeResponse.setOrderStatus(null);
			}else  {
			  // 4.1写卡指令未执行完毕
				try {
					commCardOrderConfirm(cardInformationChangeRequest, cardInformationChangeResponse,user);
				} catch (ManagerException e) {
					e.printStackTrace();
					cardInformationChangeResponse.setMessage(e+"");
					return cardInformationChangeResponse;
				}
				if ( 2 == cardInformationChangeResponse.getOrderStatus()) {    // 4.1写卡指令执行完毕
					cardInfoChangeConfirmByCosRequest.setCardId(cardInformationChangeRequest.getCardId());
					cardInfoChangeConfirmByCosRequest.setCosRecordId(cardInformationChangeRequest.getCosRecordId());
					super.commSet(cardInfoChangeConfirmByCosRequest,user);
					cardInfoChangeConfirmByCosResponse=maintenanceBinService.cardInfoChange(cardInfoChangeConfirmByCosRequest);
					cardInformationChangeResponse.setCardInfoChangeStatus(cardInfoChangeConfirmByCosResponse.getCardInfoChangeStatus());
					cardInformationChangeResponse.setCardId(cardInformationChangeRequest.getCardId());
					cardInformationChangeResponse.setStatus(cardInfoChangeConfirmByCosResponse.getStatus());
					cardInformationChangeResponse.setMessage(cardInfoChangeConfirmByCosResponse.getMessage());
				}
			}
			
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			cardInformationChangeResponse.setMessage(e+"");
		}
		return cardInformationChangeResponse;
	}


	@Override
	public CardInformationChangeResponse cardInfoChange(CardInformationChangeRequest request,User user)
			throws ManagerException {
		CardInformationChangeResponse response=new CardInformationChangeResponse();
		CardInfo cardInfo=cardInfoRepo.findByCardId(request.getCardId());
		VehicleInfo vehicleInfo =vehicleInfoRepo.findByVehicleId(request.getVehiclePlate()+"_"+request.getVehiclePlateColor());
		if(vehicleInfo==null) {
			response.setMessage(request.getVehiclePlate()+"_"+request.getVehiclePlateColor()+"车辆信息不存在，请联系管理员！");
			response.setStatus(-1);
			return response;
		}
		cardInfo.setVehicleId(vehicleInfo.getVehicleId());
		cardInfo.setVehicle(vehicleInfo);
		cardInfoRepo.save(cardInfo);
		response.setStatus(1);
		response.setMessage("卡重写车牌成功！");
		return response;
	}
	
	private void commCardOrderConfirm(CardInformationChangeRequest requset, CardInformationChangeResponse response,User user)
			throws ManagerException {
		CardOrderConfirmRequest req = new CardOrderConfirmRequest();
		super.commSet(req,user);
		req.setCosType(2);//cosType 写卡操作类型 1- 开卡. 2- 修改卡内信息  4- 卡签绑定  9- 销卡
		req.setCosRecordId(requset.getCosRecordId());
		req.setCardId(requset.getCardId());
		req.setCommand(requset.getCommand());
		req.setResponse(requset.getCosResponse());
		try {
			CardOrderConfirmResponse res = inqueryBinService.cardInfoChangeConfirm(req);
			response.setCommand(res.getCommand());
			response.setStatus(res.getStatus());
			response.setMessage(res.getMessage());
			response.setCosRecordId(req.getCosRecordId());
			response.setOrderStatus(res.getOrderStatus());
			response.setCardId(requset.getCardId());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("更改卡信息指令返回失败，请联系管理员", e);
		}
	}
	
	
	
}

