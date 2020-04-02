
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.model.customerservice.finance.AgencyIdSkipPairModel;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.CardrechargefixRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeByCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeByCOSResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeCheckRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeCheckResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeFixRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeFixResponse;
import cn.com.taiji.qtk.entity.Admin;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.repo.jpa.AdminRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;


@Service
public class CardrechargefixManagerImpl extends AbstractDsiCommManager implements CardrechargefixManager{
	
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	
	@Autowired
	private FinanceBinService financeBinService;
	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	@Autowired
	private RechargeManager rechargeManager;
	
	@Autowired
	private CardrechargefixManager cardrechargefixManager;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Autowired
	private AdminRepo adminRepo;
	@Override
	public List<ChargeDetail>  queryPage(CardrechargefixRequest queryModel,HttpServletRequest request) throws ManagerException {
		List<ChargeDetail> chargeDetail=chargeDetailRepo.findByCardIdStatus(queryModel.getCardId());
		List<ChargeDetail> chargeDetail1= new ArrayList<>();
		CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
		if(cardInfo==null) {
			throw new ManagerException("查无此卡信息!");
		}
		boolean falg =false;
		try {
			falg = agencyVarifyManager.varifyAgency(LoginHelper.getLoginUser(request), cardInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("渠道校验失败："+e.getMessage());
		}
		if(chargeDetail!=null) {
			if(falg) {
			for(int i=0;i<chargeDetail.size();i++) {
				ChargeDetail cd=chargeDetail.get(i)	;
				if(cd.getStatus()==0) {
					chargeDetail1.add(cd);
				}
			}
		}else {
			throw new ManagerException("当前渠道无权操作此卡");
		}
		}else {
			throw new ManagerException("该卡不存在!请确认此卡是否发行成功");
		}
		return chargeDetail1;
	}
	
	
	/**
	 * 圈存检测
	 * @param 卡片编号 充值金额 充值前金额
	 * @return  command  chargeStatus rechargeId
	 * @throws Exception
	 * 
	 * CardChargeCheckRequest request
	 */
	public CardrechargeResponse CardChargeCheck(CardrechargefixRequest request,User user) throws ManagerException  {
		CardrechargeResponse response = new CardrechargeResponse();
		CardChargeCheckResponse res=new CardChargeCheckResponse();
		//圈存检测参数
		CardChargeCheckRequest cardChargeCheckRequest =new CardChargeCheckRequest();
		super.commSet(cardChargeCheckRequest,user);
		cardChargeCheckRequest.setCardId(request.getCardId());
		cardChargeCheckRequest.setFee(request.getRechargeAmount());
		cardChargeCheckRequest.setPreBalance(request.getPreBalance());
		try {
			res = financeBinService.cardChargeCheckV2(cardChargeCheckRequest);
			response.setCommand(res.getCommand());
			response.setChargeStatus(res.getChargeStatus());
			response.setStatus(res.getStatus());
			response.setMessage(res.getMessage());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setMessage(res.getMessage());
			response.setStatus(-1);
		}
		return response;
	}
	
	//圈存修复
	public CardrechargeResponse CardChargeFix(CardrechargefixRequest request,User user) throws ManagerException{
		CardrechargeResponse response = new CardrechargeResponse();
		CardChargeFixResponse cardChargeFixResponse =new CardChargeFixResponse();
		CardChargeFixRequest cardChargeFixRequest =new CardChargeFixRequest();
		super.commSet(cardChargeFixRequest,user);
		cardChargeFixRequest.setRechargeId(request.getRechargeId());
		cardChargeFixRequest.setCommand(request.getCommand());
		cardChargeFixRequest.setCosResponse(request.getCosResponse());
		try {
			cardChargeFixResponse = financeBinService.cardChargeFixByCOSV2(cardChargeFixRequest);
			//
			response.setFixStatus(cardChargeFixResponse.getFixStatus());
			response.setRechargeId(cardChargeFixResponse.getRechargeId());
			response.setFee(cardChargeFixResponse.getFee());
			response.setStatus(cardChargeFixResponse.getStatus());
			if(cardChargeFixResponse.getPreBalance()!=null&&cardChargeFixResponse.getPreBalance()!=""){
				response.setPreBalance( Long.valueOf(cardChargeFixResponse.getPreBalance()));
			}
			
			if(cardChargeFixResponse.getTradeType()!=null&&cardChargeFixResponse.getPreBalance()!=""){ 
				Integer TradeType = Integer.valueOf(cardChargeFixResponse.getTradeType()); 
				response.setTradeType(TradeType);
			}
			response.setCommand(cardChargeFixResponse.getCommand());
			response.setMessage(cardChargeFixResponse.getMessage());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setMessage(cardChargeFixResponse.getMessage());
			response.setStatus(-1);
		}
		return response;
	}

	
	/**
	 * 圈存申请
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CardrechargeResponse CardChargeByCOS(CardrechargefixRequest request,User user) throws ManagerException {
		CardrechargeResponse response = new CardrechargeResponse();
		CardChargeByCOSResponse res=new CardChargeByCOSResponse();
		//圈存申请参数
		CardChargeByCOSRequest cardChargeByCOSRequest=new CardChargeByCOSRequest(); 
		//圈存申请
		super.commSet(cardChargeByCOSRequest,user);
		cardChargeByCOSRequest.setRechargeId(request.getRechargeId());
		cardChargeByCOSRequest.setCardId(request.getCardId());
		cardChargeByCOSRequest.setFee(request.getRechargeAmount());
		cardChargeByCOSRequest.setPreBalance(request.getPreBalance());
		cardChargeByCOSRequest.setTradeType(request.getTradeType());
		cardChargeByCOSRequest.setCommand(request.getCommand());
		cardChargeByCOSRequest.setCosResponse(request.getCosResponse());
		try {
			res = financeBinService.cardChargeByCOSV2(cardChargeByCOSRequest);
			if(res.getCommandType()<=0) {
				response.setMessage(res.getMessage());
				response.setStatus(-1);
				return response;
			}
			response.setMessage(res.getMessage());
			response.setCommand(res.getCommand());
			response.setCommandType(res.getCommandType());
			response.setStatus(res.getStatus());
			response.setRechargeId(request.getRechargeId());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setMessage(res.getMessage());
			response.setStatus(-1);
		}
		return response;
	}
	/**
	 * 圈存确认
	 * @param 交易流水号  实收金额   赠送金额  圈存指令结果   圈存指令
	 * @return  充值后金额
	 * @throws Exception
	 */
	public CardrechargeResponse CardChargeConfirmByCOS(CardrechargefixRequest request,User user) throws ManagerException {
		CardrechargeResponse response = new CardrechargeResponse();
		CardChargeConfirmByCOSResponse res = new CardChargeConfirmByCOSResponse();
		//圈存确认参数
		CardChargeConfirmByCOSRequest cardChargeConfirmByCOSRequest=new CardChargeConfirmByCOSRequest();
		//圈存确认
		super.commSet(cardChargeConfirmByCOSRequest,user);
		cardChargeConfirmByCOSRequest.setRechargeId(request.getRechargeId());
		cardChargeConfirmByCOSRequest.setPaidAmount(request.getPaidAmount());
		cardChargeConfirmByCOSRequest.setGiftAmount(request.getGiftAmount());
		cardChargeConfirmByCOSRequest.setCosResponse(request.getCosResponse());
		try {
			res = financeBinService.cardChargeConfirmByCOS(cardChargeConfirmByCOSRequest);
			response.setPostBalance(res.getPostBalance());
			response.setStatus(res.getStatus());
			response.setMessage(res.getMessage());
			/*//现金收费  先将其充值到对应得账户去 然后再从账户中消费掉这笔钱
			if(response.getPostBalance()!=null && request.getTradeType()==0) {
				RechargeRequest rechargeRequest =new RechargeRequest();
				rechargeRequest.setCardId(request.getCardId());
				rechargeRequest.setTradeFee(request.getPaidAmount()+request.getGiftAmount());
				AccountChargeResponse accountChargeResponse=rechargeManager.accountReverse(rechargeRequest, user);
				if(accountChargeResponse.getStatus()==-1) {
					response.setMessage(accountChargeResponse.getMessage());
					return response;
				}
			}*/
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setMessage(res.getMessage());
			response.setStatus(-1);
		}
		return response;
	}
	
	public  boolean skipAdmin(String userId) {
		Admin admin= adminRepo.findByUserId(userId);
		if(admin==null) {
			return false;
		}
		return true;
	}
	

	

}

