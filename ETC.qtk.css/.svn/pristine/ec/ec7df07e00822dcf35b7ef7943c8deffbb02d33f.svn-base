
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.model.customerservice.finance.CardreverseRequest;
import cn.com.taiji.css.model.customerservice.finance.CardreverseResponse;
import cn.com.taiji.css.model.customerservice.finance.RechargeRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardReverseDebitRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardReverseDebitResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardReverseInitalizeRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardReverseInitalizeResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.ReverseConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.ReverseConfirmResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;

@Service
public class CardreverseManagerImpl extends AbstractDsiCommManager implements CardreverseManager{

	
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	@Autowired
	private FinanceBinService financeBinService;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private RechargeManager rechargeManager;
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Override
	public LargePagination<ChargeDetail> queryPage(CardreverseRequest request,User user) throws ManagerException{
		if(user.getRole().isSystem()==false) {
			if(request.getCardId()!=null) {
				String agencyId=user.getStaff().getServiceHall().getAgencyId();
				CardInfo cardInfo = cardInfoRepo.findByCardId(request.getCardId());
				if(cardInfo==null) {
					throw new ManagerException("该卡尚未发行!");
				}
				boolean falg =false;
				try {
					falg = agencyVarifyManager.varifyAgency(user, cardInfo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ManagerException("渠道校验失败："+e.getMessage());
				}
				if(falg) {
					request.setAgencyId(agencyId);
				}else {
					throw new ManagerException("当前渠道无权操作此卡");
				}
			}else {
				throw new ManagerException("请输入要查询的卡号");
			}
			}
		return chargeDetailRepo.largePage(request) ;
		
	}
	
	
	
	@Override 	
	public CardreverseResponse cardReverseInitWithCOS(CardreverseRequest request,User user) throws ManagerException  {
		//冲正初始化
		CardreverseResponse response=new CardreverseResponse();
		
		CardReverseInitalizeRequest cardReverseInitalizeRequest =new CardReverseInitalizeRequest();
		super.commSet(cardReverseInitalizeRequest,user);
		//给参数赋值
		cardReverseInitalizeRequest.setFee(request.getFee());
		cardReverseInitalizeRequest.setCardId(request.getCardId());
		cardReverseInitalizeRequest.setChargeId(request.getChargeId());
		try {												
			//冲正申请
			CardReverseInitalizeResponse cardReverseInitalizeResponse=financeBinService.cardReverseInitWithCOS(cardReverseInitalizeRequest);
			if(cardReverseInitalizeResponse==null) {
				response.setMessage("圈存冲正申请接口调用失败！");
				response.setStatus(-1);
			}
			response.setCos(cardReverseInitalizeResponse.getCos());
			response.setMessage(cardReverseInitalizeResponse.getMessage());
			response.setStatus(cardReverseInitalizeResponse.getStatus());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("冲正初始化接口调用异常");
			
		}
		return response;
	}
	@Override
	public CardreverseResponse cardReverseDebitWithCOS(CardreverseRequest request,User user) throws ManagerException  {
		CardreverseResponse response = new CardreverseResponse();
		RechargeRequest rechargeRequest =new RechargeRequest();
		ChargeDetail chargeDetail =chargeDetailRepo.findByChargeId(request.getChargeId());
		//冲正消费
		CardReverseDebitRequest cardReverseDebitRequest =new CardReverseDebitRequest();
		super.commSet(cardReverseDebitRequest,user);
		//给参数赋值
		cardReverseDebitRequest.setCommand(request.getCommand());
		cardReverseDebitRequest.setFee(request.getFee());
		cardReverseDebitRequest.setChargeId(request.getChargeId());
		cardReverseDebitRequest.setCardId(request.getCardId());
		cardReverseDebitRequest.setInitializeResponse(request.getInitializeResponse());
		cardReverseDebitRequest.setTradeType(request.getTradeType());
		try {	
			CardReverseDebitResponse cardReverseDebitResponse=financeBinService.cardReverseDebitWithCOS(cardReverseDebitRequest);
			if(chargeDetail.getTradeType()==3  && cardReverseDebitResponse.getStatus()==1 && !cardReverseDebitResponse.getMessage().equals("业务校验失败:卡内最新一条交易不是充值交易.")) {
				CardInfo cardinfo=cardInfoRepo.findByCardId(request.getCardId());
				if(cardinfo.getCustomerId()!=null) {
					CustomerInfo customerInfo=customerInfoRepo.findByCustomerId(cardinfo.getCustomerId());
					rechargeRequest.setCustomerId(customerInfo.getCustomerId());
					rechargeRequest.setPassWord(customerInfo.getPassword());
					rechargeRequest.setTradeFee(request.getFee());
					rechargeRequest.setChargeType(105);
					rechargeRequest.setCardId(request.getCardId());
					rechargeRequest.setTradeType(102);
					AccountChargeResponse accountChargeResponse= rechargeManager.accountCharge(rechargeRequest, user);
					if(accountChargeResponse.getStatus()!=1) {
						response.setMessage(accountChargeResponse.getMessage());
						response.setStatus(-1);
						return response;
					}
				}
			}
				response.setCommand(cardReverseDebitResponse.getCommand());
				response.setMessage(cardReverseDebitResponse.getMessage());
				response.setStatus(cardReverseDebitResponse.getStatus());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("冲正消费接口调用异常");
		}
		return response;
		
		
	}
	
	public CardreverseResponse cardReverseConfirmWithCOS(CardreverseRequest request,User user) throws ManagerException  {
		CardreverseResponse response =new CardreverseResponse();
		//冲正确认
		ReverseConfirmRequest reverseConfirmRequest =new ReverseConfirmRequest();
		super.commSet(reverseConfirmRequest,user);
		//给参数赋值
		reverseConfirmRequest.setChargeId(request.getChargeId());
		reverseConfirmRequest.setCommand(request.getCommand());
		reverseConfirmRequest.setFee(request.getFee());
		reverseConfirmRequest.setCosResponse(request.getCosResponse());
		try {												
			ReverseConfirmResponse reverseConfirmResponse=financeBinService.cardReverseConfirmWithCOS(reverseConfirmRequest);
			response.setReverseConfirmStatus(reverseConfirmResponse.getReverseConfirmStatus());
			response.setStatus(reverseConfirmResponse.getStatus());
			response.setMessage(reverseConfirmResponse.getMessage());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("冲正确认接口调用异常");
		}
		return response;
	}


	
	

}

