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
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.card.LossRequest;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelOrderConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelWithCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelWithCOSResponse;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmRequest;
import cn.com.taiji.qtk.entity.AccountCardBalanceOperate;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceOperateRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;

/**
 * @ClassName PreCancelManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Service
public class PreCancelManagerImpl extends AbstractDsiCommManager implements PreCancelManager{

	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private InqueryBinService inqueryBinService;
	
	@Autowired
	private FinanceBinService financeBinService;
	@Autowired
	private AccountCardBalanceOperateRepo accountCardBalanceOperateRepo;
	@Autowired
	private LossManager lossManager;
	@Override
	public LargePagination<CardInfo> queryPage(PreCancelRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		if(!user.getRole().isSystem()) {
			LossRequest lossReq = new LossRequest();
			lossReq.setCardId(queryModel.getCardId());
			lossReq.setVehicleId(queryModel.getVehicleId());
			lossReq.setVehicleColor(queryModel.getVehicleColor());
			boolean queryFlag = lossManager.querycheck(lossReq,user);
			if(!queryFlag) {
				return null;
			}
		}
		return cardInfoRepo.largePage(queryModel);
	}

	

	@Override
	public CardInfo findById(@Valid String id) {
		
		return cardInfoRepo.findByCardId(id);
	}

	

	@Override
	public AppCardStatusChangeResponse doPreCancel(@Valid PreCancelRequest queryModel,User user) throws Exception {
		CardCancelWithCOSResponse cardCOSRes = new CardCancelWithCOSResponse();
		AppCardStatusChangeResponse response = new AppCardStatusChangeResponse();
		CardCancelOrderConfirmResponse cardBindRes= new CardCancelOrderConfirmResponse();
		
			//卡注销申请接口
			if(queryModel.getApplyStep()==1) {
				 cardCOSRes =  preCancelWithCOS(queryModel,user);
				 response.setCommand(cardCOSRes.getCommand());
				 response.setCosRecordId(cardCOSRes.getCosRecordId());
				 response.setStatus(cardCOSRes.getStatus());
				 response.setMessage(cardCOSRes.getMessage());
				if(queryModel.getProvider()==0) {
					addAcountCardBanlance(queryModel);
				}
			 }else if(queryModel.getApplyStep()!=1&&queryModel.getProvider()==1){
				 cardBindRes = doCardOrder(queryModel,user);
				 response.setCommand(cardBindRes.getCommand());
				 response.setOrderStatus(cardBindRes.getOrderStatus());
				 response.setStatus(cardBindRes.getStatus());
				 response.setMessage(cardBindRes.getMessage());
				 response.setCardBalance(cardBindRes.getCardBalance());
				 
				 if(cardBindRes.getOrderStatus()==2) {
					 addAcountCardBanlance(queryModel);
				 }
			 }
		/*}else {
			cardCancelWithCOSResponse.setMessage("当前卡状态为预注销或注销，不能办理预注销业务。");
		}*/
		return response;
	}



	private void addAcountCardBanlance(PreCancelRequest queryModel) {
		AccountCardBalanceOperate accCardOpre = new AccountCardBalanceOperate();
		accCardOpre.setCardId(queryModel.getCardId());
		accCardOpre.setCosProvider(queryModel.getProvider());
		accountCardBalanceOperateRepo.save(accCardOpre);
	}
	
	private CardCancelWithCOSResponse preCancelWithCOS(PreCancelRequest queryModel,User user) {
		CardCancelWithCOSRequest cardCancelWithCOSRequest = new CardCancelWithCOSRequest();
		CardCancelWithCOSResponse  cardCOSRes= new CardCancelWithCOSResponse();
		super.commSet(cardCancelWithCOSRequest,user);
		cardCancelWithCOSRequest.setCardId(queryModel.getCardId());
		cardCancelWithCOSRequest.setCosProvider(queryModel.getProvider());
		try {
			cardCOSRes = financeBinService.cardCancelWithCOS(cardCancelWithCOSRequest);
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
		}
		return cardCOSRes;
		
	}


	public CardCancelOrderConfirmResponse doCardOrder(PreCancelRequest preReq,User user) {
		CardOrderConfirmRequest cardOrderConfirmReq = new CardOrderConfirmRequest();
		CardCancelOrderConfirmResponse CardObuConfirmRes = new  CardCancelOrderConfirmResponse();
		super.commSet(cardOrderConfirmReq,user);
		cardOrderConfirmReq.setCardId(preReq.getCardId());
		cardOrderConfirmReq.setResponse(preReq.getCosResponse());
		cardOrderConfirmReq.setCommand(preReq.getCommand());
	    cardOrderConfirmReq.setCosRecordId(preReq.getCosRecordId());
		 try {
			CardObuConfirmRes = inqueryBinService.cancelCardOrderConfirm(cardOrderConfirmReq);
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
		}
		return CardObuConfirmRes;
	}






}

