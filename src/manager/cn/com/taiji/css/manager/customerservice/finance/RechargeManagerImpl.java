
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.model.customerservice.finance.RechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.RechargeResponse;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeResponse;
import cn.com.taiji.qtk.entity.AccountTradeDetail;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.repo.jpa.AccountTradeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;


@Service
public class RechargeManagerImpl extends AbstractDsiCommManager implements RechargeManager{

	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	@Autowired
	private RechargeManager rechargeManager;
	
	@Autowired
	private FinanceBinService financeBinService;
	
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;
	
	@Autowired
	private AccountTradeDetailRepo accountTradeDetailRepo;
	
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	
	/*@Autowired
	private AccountTemporaryRepo accountTemporaryRepo;*/
	
	public  RechargeResponse  accountTradesQuery(RechargeRequest request,User user)throws ManagerException{
		
		RechargeResponse rechargeResponse =new RechargeResponse();
		String  userId=rechargeManager.findUserId(request,user);
		if(userId==null) {
			throw new ManagerException("未查到该信息对应的账户,请确认信息无误!");
		}
		request.setUserId(userId);
		//yyyyMMdd        如果用户不选择时间,默认输入当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date=df.format(new Date());
		if(request.getStartTime()==null) {
			request.setStartTime(date);
		}
		if(request.getEndTime()==null) {
			request.setEndTime(date);
		} 
		List<AccountTradeDetail> list=accountTradeDetailRepo.listByCustomerIdAndBetweenDate(request.getUserId(), request.getStartTime(),request.getEndTime());
		rechargeResponse.setResult(list);
		return rechargeResponse;
	}
	
	
	
	@Override
	public AccountChargeResponse accountCharge(RechargeRequest request,User user) throws ManagerException {
		AccountChargeResponse accountChargeResponse=check(request, user);
		if(accountChargeResponse.getStatus()==2) {
			String userId=findUserId(request,user);
			AccountChargeRequest accountChargeRequest  =new AccountChargeRequest();
			super.commSet(accountChargeRequest,user);
			if(userId!=null  && request.getTradeFee()!=null){
				accountChargeRequest.setUserId(userId);
					accountChargeRequest.setTradeType(request.getTradeType()); //账户充值
				accountChargeRequest.setFee(request.getTradeFee());
				//对用户账进行密码校验
				accountChargeResponse=userAccountPassWordCheck(request, accountChargeResponse, userId);
				if(accountChargeResponse.getStatus()==-1) {
					return accountChargeResponse;
				}
				try {
					accountChargeResponse=financeBinService.accountCharge(accountChargeRequest);
					if(accountChargeResponse.getPostBalance()>0) {
						save(request, user);
						accountChargeResponse.setStatus(1);
						accountChargeResponse.setMessage(accountChargeResponse.getMessage());
					}else {
						accountChargeResponse.setStatus(-1);
						accountChargeResponse.setMessage(accountChargeResponse.getMessage());
					}
				} catch (ApiRequestException | IOException e) {
					e.printStackTrace();
					accountChargeResponse.setStatus(-1);
					accountChargeResponse.setMessage(""+e);
				}
			}else {
				accountChargeResponse.setStatus(-1);
				accountChargeResponse.setMessage("未查到该信息对应的账户,请确认信息无误!");
			}
		}
		return accountChargeResponse;
	}



	private AccountChargeResponse userAccountPassWordCheck(RechargeRequest request, AccountChargeResponse accountChargeResponse,
			String userId) {
		CustomerInfo customerInfo=customerInfoRepo.findByCustomerId(userId);
		if(customerInfo==null) {
			accountChargeResponse.setMessage("该卡没有用户信息!");
			accountChargeResponse.setStatus(-1);
			return accountChargeResponse;
		}
		if(customerInfo.getPassword()==null) {
			accountChargeResponse.setMessage("此用户未设置用户账密码，请在用户账密码管理中进行密码初始化!");
			accountChargeResponse.setStatus(-1);
			return accountChargeResponse;

		}
		if(!customerInfo.getPassword().equals(request.getPassWord())) {
			accountChargeResponse.setMessage("用户账密码错误，无权操作此账户!");
			accountChargeResponse.setStatus(-1);
			return accountChargeResponse;
		}
		return accountChargeResponse;
	}
	
	
	/*@Override
	public AccountChargeResponse accountCharge(RechargeRequest request,User user) throws ManagerException {
		AccountChargeResponse accountChargeResponse=check(request, user);
		if(accountChargeResponse.getStatus()==2) {
			String userId=findUserId(request,user);
			AccountChargeRequest accountChargeRequest  =new AccountChargeRequest();
			super.commSet(accountChargeRequest,user);
			if(userId!=null  && request.getTradeFee()!=null){
				accountChargeRequest.setUserId(userId);
				accountChargeRequest.setTradeType(101); //账户充值
				accountChargeRequest.setFee(request.getTradeFee());
				try {
					accountChargeResponse=financeBinService.accountCharge(accountChargeRequest);
					if(accountChargeResponse.getPostBalance()>0) {
						save(request, user);
						accountChargeResponse.setStatus(1);
					}else {
						accountChargeResponse.setStatus(-1);
					}
				} catch (ApiRequestException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return accountChargeResponse;
	}*/
	
	//账户消费
	@Override
	public AccountChargeResponse accountReverse(RechargeRequest request,User user) throws ManagerException {
		AccountChargeResponse accountChargeResponse=checkForAccountReverse(request, user);
		if(accountChargeResponse.getStatus()==2) {
			String userId=findUserId(request,user);
			AccountChargeRequest accountChargeRequest  =new AccountChargeRequest();
			super.commSet(accountChargeRequest,user);
			if(userId!=null  && request.getTradeFee()!=null){
				accountChargeRequest.setCardId(request.getCardId());
				accountChargeRequest.setChargeId(request.getChargeId());
				accountChargeRequest.setUserId(userId);
				accountChargeRequest.setTradeType(105);  //  账户消费
				accountChargeRequest.setFee(request.getTradeFee());
				//对用户账进行密码校验
				accountChargeResponse=userAccountPassWordCheck(request, accountChargeResponse, userId);
				if(accountChargeResponse.getStatus()==-1) {
					return accountChargeResponse;
				}
				try {
					accountChargeResponse=financeBinService.accountCharge(accountChargeRequest);
					if(accountChargeResponse.getPostBalance()>=0) {
						accountChargeResponse.setStatus(1);
						accountChargeResponse.setMessage(accountChargeResponse.getMessage());
					}else {
						accountChargeResponse.setStatus(-1);
						accountChargeResponse.setMessage(accountChargeResponse.getMessage());
					}
				} catch (ApiRequestException | IOException e) {
					e.printStackTrace();
					accountChargeResponse.setStatus(-1);
					accountChargeResponse.setMessage(""+e);
				}
			}
		}
		return accountChargeResponse;
		
	}
	
	//账户冲正
	@Override
	public AccountChargeResponse accountreversal(RechargeRequest request,User user) throws ManagerException {
		AccountChargeResponse accountChargeResponse=checkForAccountReverse(request, user);
		if(accountChargeResponse.getStatus()==2) {
			String userId=findUserId(request,user);
			AccountChargeRequest accountChargeRequest  =new AccountChargeRequest();
			super.commSet(accountChargeRequest,user);
			if(userId!=null  && request.getTradeFee()!=null){
				accountChargeRequest.setUserId(userId);
				accountChargeRequest.setTradeType(104);  //  账户冲正
				accountChargeRequest.setFee(request.getTradeFee());
				//对用户账进行密码校验
				accountChargeResponse=userAccountPassWordCheck(request, accountChargeResponse, userId);
				if(accountChargeResponse.getStatus()==-1) {
					return accountChargeResponse;
				}
				try {
					accountChargeResponse=financeBinService.accountCharge(accountChargeRequest);
					if(accountChargeResponse.getPostBalance()>=0) {
						accountChargeResponse.setStatus(1);
						accountChargeResponse.setMessage(accountChargeResponse.getMessage());
					}else {
						accountChargeResponse.setStatus(-1);
						accountChargeResponse.setMessage(accountChargeResponse.getMessage());
					}
				} catch (ApiRequestException | IOException e) {
					e.printStackTrace();
					accountChargeResponse.setStatus(-1);
					accountChargeResponse.setMessage(""+e);
				}
			}
		}
		return accountChargeResponse;
		
	}
	
	@Override
	public String findUserId(RechargeRequest request,User user) throws ManagerException {
		if(request.getCardId()!=null&&request.getCardId()!="") {
			CardInfo cardInfo =cardInfoRepo.findByCardId(request.getCardId());
			
			//正常版本
			if(cardInfo==null) {
				throw new ManagerException("此"+request.getCardId()+"卡未发行!");
			}
			
			/*if(cardInfo==null) {
				throw new ManagerException("此"+request.getCardId()+"卡未发行!");
				System.out.println("此"+request.getCardId()+"卡未发行!");
				AccountTemporary accountTemporary =accountTemporaryRepo.findId(request.getTestId());
				accountTemporary.setStatus(-1);
				accountTemporaryRepo.save(accountTemporary);
				return null;
			}*/
			return  cardInfo.getCustomerId();
		}else if(request.getVehiclePlate()!=null && request.getVehiclePlateColor()!=null ) {
			CardInfo cardInfo =new CardInfo();
			try {
			cardInfo=cardInfoRepo.findVehicleId(request.getVehiclePlate()+"_"+request.getVehiclePlateColor());
			} catch (IncorrectResultSizeDataAccessException e) {
				throw new ManagerException("根据此车牌查到两张卡,请联系管理员!");
			}
			if(cardInfo==null) {
				throw new ManagerException("该车牌未发卡或者此卡已注销!");   // 注销之后车辆外键丢失    
			}
			return cardInfo.getCustomerId();
		}
		return null;
	}
	private AccountChargeResponse check(RechargeRequest request,User user) {
		AccountChargeResponse	accountChargeResponse=new AccountChargeResponse();
		String customerId = null;
		try {
			customerId = rechargeManager.findUserId(request,user);
			accountChargeResponse.setStatus(-1);
			if(request.getVehiclePlate()== "" &&  request.getCardId()=="") {
				accountChargeResponse.setMessage("卡号和车牌不能同时为空!"); 
			}else if(request.getTradeFee()<=0) {
				accountChargeResponse.setMessage("充值金额不能为空!"); 
			}else if(request.getTradeFee() % 100 != 0){
				accountChargeResponse.setMessage("充值金额必须为100元的整数倍!"); 
			}else if(request.getChargeType()==null){
				accountChargeResponse.setMessage("请选择收费类型!"); 
			}else  if(customerId==null) {
				accountChargeResponse.setMessage("未查到该信息对应的账户,请确认信息无误!");
			}else {
				accountChargeResponse.setStatus(2);
			}
		} catch (ManagerException e) {
			e.printStackTrace();
			accountChargeResponse.setStatus(-1);
			accountChargeResponse.setMessage(e+"");
		}
		return accountChargeResponse;
	}
	
	private AccountChargeResponse checkForAccountReverse(RechargeRequest request,User user) throws ManagerException {
		AccountChargeResponse	accountChargeResponse=new AccountChargeResponse();
		String  customerId=rechargeManager.findUserId(request,user);
		accountChargeResponse.setStatus(-1);
		if(request.getVehiclePlate()== "" &&  request.getCardId()=="") {
			accountChargeResponse.setMessage("卡号和车牌不能同时为空!"); 
		}else if(request.getTradeFee()<=0) {
			accountChargeResponse.setMessage("消费金额不能为空!"); 
		}else if(request.getTradeFee() % 100 != 0){
			accountChargeResponse.setMessage("消费金额必须为100元的整数倍!"); 
		}else  if(customerId==null) {
			accountChargeResponse.setMessage("未查到该信息对应的账户,请确认信息无误!");
		}else {
			accountChargeResponse.setStatus(2);
		}
		return accountChargeResponse;
	}
	
	

	private  void  save(RechargeRequest request ,User user) throws ManagerException {
		Long fee=request.getTradeFee();
		//收入 为0  支出为1
		Integer handleType =0;
		if(request.getChargeType()==123) {//现金收费
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ACCOUNTCHARGE, ChargeType.CASH,handleType, fee);
		}else if(request.getChargeType()==124) {//通用POS
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ACCOUNTCHARGE, ChargeType.COMMONPOS,handleType, fee);
		}/*else if(request.getChargeType()==125) {//专用POS
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ACCOUNTCHARGE, ChargeType.POS,,handleType, fee);
		}*/else if(request.getChargeType()==126) {//对公转账
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ACCOUNTCHARGE, ChargeType.TRANSFERCHARGE, handleType,fee);
		}else if(request.getChargeType()==105) {//账户消费
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ACCOUNTCHARGE, ChargeType.ACCOUNT_CONSUME,handleType, fee);
		}
	}
	

}

