
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.RechargeRequest;
import cn.com.taiji.css.model.util.RechargeIdUniqueNo;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeByCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeByCOSResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeCheckRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeCheckResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSResponse;
import cn.com.taiji.qtk.entity.AccountBalance;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.repo.jpa.AccountBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;


@Service
public class CardrechargeManagerImpl extends AbstractDsiCommManager implements CardrechargeManager{

	
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	@Autowired
	private FinanceBinService financeBinService;

	@Autowired
	private AccountBalanceRepo accountBalanceRepo;
	
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;
	
	@Autowired
	private RechargeManager rechargeManager;
	
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	
	@Override
	public LargePagination<ChargeDetail> queryPage(CardrechargeRequest queryModel,HttpServletRequest request) throws ManagerException {
		if(LoginHelper.getLoginUser(request).getRole().isSystem()==false) {
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
			if(falg) {
				queryModel.setAgencyId(LoginHelper.getLoginUser(request).getStaff().getServiceHall().getAgencyId());
				return chargeDetailRepo.largePage(queryModel);
			}else {
				throw new ManagerException("当前渠道无权操作此卡");
			}
		}
		return null;
		
	}
	
	/**
	 * 圈存检测
	 * @param 卡片编号 充值金额 充值前金额
	 * @return  command  chargeStatus rechargeId
	 * @throws Exception
	 * 
	 * CardChargeCheckRequest request
	 */
	@Override
	public CardrechargeResponse  CardChargeCheck(CardrechargeRequest request,User user) throws ManagerException  {
		CardrechargeResponse cardrechargeResponse=check(request,user);
		if(cardrechargeResponse.getStatus()==2) {
		//圈存检测参数
		CardChargeCheckRequest cardChargeCheckRequest =new CardChargeCheckRequest();
		super.commSet(cardChargeCheckRequest,user);
		String rechargeId = super.generateRechargeId(cardChargeCheckRequest);
			
		cardChargeCheckRequest.setCardId(request.getCardId());
		cardChargeCheckRequest.setFee(request.getPaidAmount()+request.getGiftAmount());
		if(request.getChargeType()==105) {
			cardChargeCheckRequest.setTradeType(3);
			CustomerInfo customerInfo=customerInfoRepo.findByCustomerId(cardrechargeResponse.getCustomerId());
			if(customerInfo==null) {
				cardrechargeResponse.setMessage("该卡没有用户信息!");
				cardrechargeResponse.setStatus(-1);
				return cardrechargeResponse;
			}
			if(customerInfo.getPassword()==null) {
				cardrechargeResponse.setMessage("此用户未设置用户账密码，请在用户账密码管理中进行密码初始化!");
				cardrechargeResponse.setStatus(-1);
				return cardrechargeResponse;
			}
			if(!customerInfo.getPassword().equals(request.getPassWord())) {
				cardrechargeResponse.setMessage("用户账密码错误，无权操作此账户!");
				cardrechargeResponse.setStatus(-1);
				return cardrechargeResponse;
			}
		}else {
			cardChargeCheckRequest.setTradeType(0);
		}
		cardChargeCheckRequest.setPreBalance(request.getPreBalance());
		try {
			CardChargeCheckResponse res = financeBinService.cardChargeCheckV2(cardChargeCheckRequest);
			if(res==null) {
				cardrechargeResponse.setMessage("圈存检测失败！");
				cardrechargeResponse.setStatus(-1);
				return cardrechargeResponse;
			}
			if(res != null &&res.getCommand()!=null) {
				if(res.getStatus()==1) {
					save(request,user);	
				}
			}
			cardrechargeResponse.setPaidAmount(request.getPaidAmount());
			cardrechargeResponse.setGiftAmount(request.getGiftAmount());
			cardrechargeResponse.setCommand(res.getCommand());
			cardrechargeResponse.setChargeStatus(res.getChargeStatus());
			cardrechargeResponse.setRechargeId(rechargeId);
			cardrechargeResponse.setMessage(res.getMessage());
			cardrechargeResponse.setTradeType(cardChargeCheckRequest.getTradeType());
			cardrechargeResponse.setPassWord(request.getPassWord());
			cardrechargeResponse.setStatus(res.getStatus());
			cardrechargeResponse.setPostBalance(request.getPaidAmount());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			cardrechargeResponse.setMessage(e+"");
			cardrechargeResponse.setStatus(-1);
		}
		}
		return cardrechargeResponse;
	}

	/**
	 * 圈存申请
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override 	
	public CardrechargeResponse  CardChargeByCOS(CardrechargeRequest request,User user) throws ManagerException {
		//圈存申请参数
		CardChargeByCOSRequest cardChargeByCOSRequest=new CardChargeByCOSRequest(); 
		CardrechargeResponse response =new CardrechargeResponse();
		/*//现金收费  先将其充值到对应得账户去 然后再从账户中消费掉这笔钱
		if(request.getChargeType()==123 || request.getChargeType()==124 || request.getChargeType()==126) {
			RechargeRequest rechargeRequest =new RechargeRequest();
			rechargeRequest.setCardId(request.getCardId());
			rechargeRequest.setTradeFee(request.getPaidAmount()+request.getGiftAmount());
			rechargeRequest.setChargeType(request.getChargeType());
			AccountChargeResponse accountChargeResponse=rechargeManager.accountCharge(rechargeRequest, user);
			if(accountChargeResponse.getStatus()==-1) {
				response.setMessage(accountChargeResponse.getMessage());
				return response;
			}
		}*/
		//圈存申请
		super.commSet(cardChargeByCOSRequest,user);
		String intRand=RechargeIdUniqueNo.getSerialNo();
		String rechargeId="1"+cardChargeByCOSRequest.getChannelId()+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+intRand;
		cardChargeByCOSRequest.setRechargeId(rechargeId);
		cardChargeByCOSRequest.setCardId(request.getCardId());
		cardChargeByCOSRequest.setFee(request.getPaidAmount()+request.getGiftAmount());
		cardChargeByCOSRequest.setPreBalance(request.getPreBalance());
		cardChargeByCOSRequest.setTradeType(request.getTradeType());
		cardChargeByCOSRequest.setCommand(request.getCommand());
		cardChargeByCOSRequest.setCosResponse(request.getCosResponse());
		try {
			//交易类型为3时进行账户消费,消费成功则进行圈存申请
			if(request.getTradeType()==3 && request.getCommandType()!=1) {
				RechargeRequest rechargeRequest =new RechargeRequest();
				rechargeRequest.setChargeId(rechargeId);
				rechargeRequest.setCardId(request.getCardId());
				rechargeRequest.setPassWord(request.getPassWord());
				rechargeRequest.setTradeFee(request.getPaidAmount()+request.getGiftAmount());
				AccountChargeResponse accountChargeResponse=rechargeManager.accountReverse(rechargeRequest, user);
				if(accountChargeResponse.getStatus()!=1 || accountChargeResponse.getMessage().equals("业务校验失败:消费用户账户失败：账户金额不足！") ) {
					response.setMessage(accountChargeResponse.getMessage());
					response.setStatus(-1);
					return response;
				}
			}
			CardChargeByCOSResponse res = financeBinService.cardChargeByCOSV2(cardChargeByCOSRequest);
			if(res==null || res.getStatus()!=1) {
				response.setMessage("圈存申请接口调用失败!");
				response.setStatus(-1);
				return response;
			}
			if(res.getCommandType()<=0) {
				response.setMessage(res.getMessage());
				response.setStatus(-1);
				return response;
			}
				response.setPaidAmount(request.getPaidAmount());
				response.setGiftAmount(request.getGiftAmount());
				response.setMessage(res.getMessage());
				response.setCommand(res.getCommand());
				response.setCommandType(res.getCommandType());
				response.setStatus(res.getStatus());
				response.setRechargeId(rechargeId);
				response.setMessage(res.getMessage());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setStatus(-1);
			response.setMessage(e+"");
		}
		return response;
	}
	
	
	
	/**
	 * 圈存确认
	 * @param 交易流水号  实收金额   赠送金额  圈存指令结果   圈存指令
	 * @return  充值后金额
	 * @throws Exception
	 */
	public CardrechargeResponse  CardChargeConfirmByCOS(CardrechargeRequest request,User user) throws ManagerException {
		//圈存确认参数
		CardChargeConfirmByCOSRequest cardChargeConfirmByCOSRequest=new CardChargeConfirmByCOSRequest();
		CardrechargeResponse response =new CardrechargeResponse();
		//圈存确认
		super.commSet(cardChargeConfirmByCOSRequest,user);
		cardChargeConfirmByCOSRequest.setRechargeId(request.getRechargeId());
		cardChargeConfirmByCOSRequest.setPaidAmount(request.getPaidAmount());
		cardChargeConfirmByCOSRequest.setGiftAmount(request.getGiftAmount());
		cardChargeConfirmByCOSRequest.setCommand(request.getCommand());
		cardChargeConfirmByCOSRequest.setCosResponse(request.getCosResponse());
		try { 
			CardChargeConfirmByCOSResponse res = financeBinService.cardChargeConfirmByCOS(cardChargeConfirmByCOSRequest);
			if(res==null) {
				response.setMessage("圈存确认失败!");
				response.setStatus(-1);
				return response;
			}
			if(res.getPostBalance()!=null) {
				response.setPostBalance(res.getPostBalance());
				response.setStatus(res.getStatus());
				response.setMessage(res.getMessage());
			}else {
				response.setStatus(-1);
				response.setMessage(res.getMessage());
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setStatus(-1);
			response.setMessage(e+"");
		}
		return response;
	}



	@Override
	public CardrechargeResponse FindAccountBalanceByCardId(CardrechargeRequest request, User user)
			throws ManagerException {
		CardrechargeResponse cardrechargeResponse =new CardrechargeResponse();
		AccountBalance accountBalance=new AccountBalance();
		CardInfo cardInfo=cardInfoRepo.findByCardId(request.getCardId());
		if(cardInfo!=null) {
			accountBalance=accountBalanceRepo.findAccountsByCustomerId(cardInfo.getCustomerId());
			if(accountBalance==null) {
				cardrechargeResponse.setAccountBalance(0L);
				}else {
					cardrechargeResponse.setAccountBalance(accountBalance.getAccountBalance());
				}
		}else {
			cardrechargeResponse.setMessage("卡信息不存在!");
			cardrechargeResponse.setStatus(-1);
		}
		return cardrechargeResponse;
	}

	
	@Override
	public boolean agencyCheck(User user,String cardId) throws ManagerException
	{
		String agencyId=  user.getStaff().getServiceHall().getAgencyId();
		CardInfo cardInfo = cardInfoRepo.findByCardId(cardId);
		if(!agencyId.equals(cardInfo.getAgencyId())){
			//建行要求
			if("52010102018".equals(agencyId)&&"52010102002".equals(cardInfo.getAgencyId())) {
				return true;
			}else if("52010102002".equals(agencyId)&&"52010102018".equals(cardInfo.getAgencyId())) {
				return true;
			}else {
				throw new ManagerException("该卡不能在该渠道进行办理");
			}
		}else {
			return true;
		}
		/*return(agencyId.equals(cardInfo.getAgencyId()));*/
				
		
		
	}
	
	private CardrechargeResponse check(CardrechargeRequest queryModel,User user) throws ManagerException {
		CardrechargeResponse cardrechargeResponse=new CardrechargeResponse();
		CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
		List<ChargeDetail> chargeDetail =chargeDetailRepo.findByCardIdForCheck(queryModel.getCardId());
		cardrechargeResponse.setStatus(-1);
		boolean falg =false;
		try {
			falg = agencyVarifyManager.varifyAgency(user, cardInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("渠道校验失败："+e.getMessage());
		}
		if(!falg){
			throw new ManagerException("当前渠道无权操作此卡");
		}else if(chargeDetail!=null && chargeDetail.size()!=0) {
			cardrechargeResponse.setMessage("此卡存在半条审核申请,无法进行圈存,请联系管理员!");
		}else if(queryModel.getCardId()=="") {
			cardrechargeResponse.setMessage("请输入圈存的卡号!");
		}else if(queryModel.getPaidAmount()<=0){
			cardrechargeResponse.setMessage("请输入圈存的金额 !");
		}else if(queryModel.getChargeType()==null) {
			cardrechargeResponse.setMessage("请选择消费类型");
		}else if(cardInfo==null) {
			cardrechargeResponse.setMessage("查无此卡信息");
		}else if(queryModel.getPaidAmount()+queryModel.getGiftAmount()>=5000000) {
			cardrechargeResponse.setMessage("单笔圈存交易不能大于或等于5万元!");
		}else {
			cardrechargeResponse.setStatus(2);
			cardrechargeResponse.setCustomerId(cardInfo.getCustomerId());
		}
		return cardrechargeResponse;
		
	}

	
	private  void  save(CardrechargeRequest queryModel ,User user) throws ManagerException {
		Long fee=queryModel.getPaidAmount();
		//收入 为0  支出为1
		Integer handleType =0;
		if(queryModel.getChargeType()==123) {//现金收费
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDCHARGE, ChargeType.CASH, handleType, fee);
		}else if(queryModel.getChargeType()==124) {//通用POS
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDCHARGE, ChargeType.COMMONPOS,handleType, fee);
		}/*else if(queryModel.getChargeType()==125) {//专用POS     将来使用
		fundSerialDetaiManager.saveFundSerial(LoginHelper.getLoginUser(request), ServiceType.CARDCHARGE, ChargeType.POS, fee);
		}*/else if(queryModel.getChargeType()==126) {//对公转账
		fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDCHARGE, ChargeType.TRANSFERCHARGE, handleType,fee);
		}else if(queryModel.getChargeType()==105) {//账户消费
		fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDCHARGE, ChargeType.ACCOUNT_CONSUME, handleType,fee);
		}
	}

	@Override
	public ChargeType[] setChargeType(CardrechargeRequest queryModel, User user) {
		if("52010106004".equals(user.getStaff().getServiceHall().getAgencyId())) {
			return ChargeType.getAccountChargeEnums();
		}
		return ChargeType.getCardChargeEnums();
	}
	
	
	

	
	

	
}

