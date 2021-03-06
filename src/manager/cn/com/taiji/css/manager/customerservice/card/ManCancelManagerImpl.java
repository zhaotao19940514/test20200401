package cn.com.taiji.css.manager.customerservice.card;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.customerservice.card.ManCancelRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.manager.comm.client.ReckonBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeResponse;
import cn.com.taiji.dsi.model.comm.protocol.reckon.CardAccountReckonRequest;
import cn.com.taiji.dsi.model.comm.protocol.reckon.CardAccountReckonResponse;
import cn.com.taiji.qtk.entity.AccountCardBalanceOperate;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.AccountRefundLog;
import cn.com.taiji.qtk.entity.CancelledCardDetail;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.AccountCardBalanceOperateType;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceOperateRepo;
import cn.com.taiji.qtk.repo.jpa.AccountRefundDetailRepo;
import cn.com.taiji.qtk.repo.jpa.AccountRefundLogRepo;
import cn.com.taiji.qtk.repo.jpa.CancelledCardDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;
import cn.com.taiji.qtk.repo.jpa.RefundImpFailMessageRepo;

/**
 * @ClassName ManCancelManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年12月18日
 */
@Service
public class ManCancelManagerImpl extends AbstractDsiCommManager implements ManCancelManager{

	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private CancelledCardDetailRepo cancelledCardDetailRepo;
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private AccountCardBalanceOperateRepo accountCardBalanceOperateRepo;
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;
	@Autowired
	private FinanceBinService financeBinService;
	@Autowired
	private ReckonBinService reckonBinService;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Autowired
	private AccountRefundDetailRepo accountRefundDetailRepo;
	@Autowired
	private RefundImpFailMessageRepo refundImpFailMessageRepo;
	@Autowired
	private AccountRefundLogRepo accountRefundLogRepo;

	@Override
	public LargePagination<CancelledCardDetail> queryPage(ManCancelRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		CardInfo cardInfo=  cardInfoRepo.findByCardId(queryModel.getCardId());
		
		boolean falg =false;
		try {
			falg = agencyVarifyManager.varifyAgency(user, cardInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("渠道校验失败："+e.getMessage());
		}
		if(falg) {
			List<CancelledCardDetail> listCard = agencyCheck(queryModel);
			LargePagination<CancelledCardDetail> list = new LargePagination<CancelledCardDetail>();
			list.setResult(listCard);
			return list;
		}else { 
			throw new ManagerException("当前渠道无权操作此卡");
		}
		
	}
	
	private List<CancelledCardDetail> agencyCheck(ManCancelRequest queryModel) {
		List<CancelledCardDetail> detailList = Lists.newArrayList();
		if(StringTools.hasText(queryModel.getCardId())) {
			detailList = cancelledCardDetailRepo.listByCardId(queryModel.getCardId());
		}else if(StringTools.hasText(queryModel.getVehicleId())) {
			detailList = cancelledCardDetailRepo.listByVehicleId(queryModel.getVehicleId());
		}
		return detailList;
		
	}
	@Override
	public CancelledCardDetail findById(String cardId) {
		
		return cancelledCardDetailRepo.findByCardId(cardId);
	}

	@Override
	public Long findAccountCardBalanceBycardId(String cardId,User user) {
		CardAccountReckonRequest req = new CardAccountReckonRequest();
		super.commSet(req,user);
		req.setCardId(cardId);
		req.setCountType(1);
		CardAccountReckonResponse res = null;
		try {
			 res = reckonBinService.cardAccountReckon(req);
			return res.getPostBalance();
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CustomerInfo findByCustomerInfo(String cardId) {
		CardInfo cardInfo = cardInfoRepo.findByCardId(cardId);
		return customerInfoRepo.findByCustomerId(cardInfo.getCustomerId());
	}

	@Override
	public AccountCardBalanceOperate findByCardId(String cardId) {
		
		List<AccountCardBalanceOperate> list = accountCardBalanceOperateRepo.listByCardId(cardId);
		if(null!=list&&list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public AppAjaxResponse confirmRefund(ManCancelRequest queryModel, User user) {
		//生成业务流水 1表示支出
		AppAjaxResponse appAjaxRes = new AppAjaxResponse();
		AccountChargeResponse accountChargeRes= new AccountChargeResponse(); 
		try {
			AccountCardBalanceOperate accountCardBalanceOperate = accountCardBalanceOperateRepo.listByCardId(queryModel.getCardId()).get(0);
			if(accountCardBalanceOperate.getBalanceType()==1) {
				appAjaxRes.setMessage("请勿重复退款,请查看交易流水进行确认");
				appAjaxRes.setStatus(0);
			}else {
				if((AccountCardBalanceOperateType.ACCOUNT).equals(queryModel.getBalanceType())) {
					accountChargeRes = saveAccount(queryModel.getCustomerId(),queryModel.getCardBalance(),user);
					appAjaxRes.setMessage(accountChargeRes.getMessage());
					appAjaxRes.setStatus(accountChargeRes.getStatus());
				}else if(AccountCardBalanceOperateType.BANK_CARD.equals(queryModel.getBalanceType())) {
					fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDCANCEL, ChargeType.BANKCARD,1, (long)queryModel.getCardBalance(),queryModel.getCardId(),null,null);
					appAjaxRes.setStatus(1);
				}else {
					fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDCANCEL, ChargeType.CASH,1, (long)queryModel.getCardBalance(),queryModel.getCardId(),null,null);
					appAjaxRes.setStatus(1);
				}
				accountCardBalanceOperate.setBalanceType(1);
				accountCardBalanceOperateRepo.save(accountCardBalanceOperate);
				appAjaxRes.setStatus(1);
			}
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		return appAjaxRes;
	}
	//调账户交易接口
	private AccountChargeResponse saveAccount(String customerId,Long cardBalance,User user) {
		AccountChargeResponse accountChargeRes= new AccountChargeResponse(); 
		AccountChargeRequest accountChargeReq = new AccountChargeRequest();
		super.commSet(accountChargeReq,user);
		accountChargeReq.setUserId(customerId);
		accountChargeReq.setFee(cardBalance);
		accountChargeReq.setTradeType(101);
		try {
			accountChargeRes = financeBinService.accountCharge(accountChargeReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountChargeRes;
	}
	@Override
	public boolean cancel12ArgueTime(String cardId) throws ManagerException {
		LocalDate nowDate = LocalDate.now();
		CancelledCardDetail detail = cancelledCardDetailRepo.findByCardId(cardId);
		if(null==detail) {
			CardInfo cardinfo = cardInfoRepo.findByCardId(cardId);
			if(null!=cardinfo) {
				if(cardinfo.getStatus()!=4&&cardinfo.getStatus()!=5) {
					throw new ManagerException("该卡未注销");
				}else {
					saveCancelDetail(cardId, cardinfo);
					detail = cancelledCardDetailRepo.findByCardId(cardId);
				}
			}else {
				throw new ManagerException("未查到注销卡信息");
			}
			
			
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate cancelDate = LocalDate.parse(format.format(detail.getCreateTime().getTime())).plusDays(12); 
		return nowDate.compareTo(cancelDate) > 0;
	}

	private void saveCancelDetail(String cardId, CardInfo cardinfo) {
		CancelledCardDetail cancelDetail = new CancelledCardDetail();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(cardinfo.getStatusChangeTime().replace("T"," ")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cancelDetail.setCreateTime(cal);
		cancelDetail.setCancellationTime(cardinfo.getStatusChangeTime().substring(0, 10).replace("-",""));
		cancelDetail.setCardId(cardId);
		cancelDetail.setStatus(1);
		cancelDetail.setCardType(cardinfo.getCardType()/100);
		cancelDetail.setStaffId("无");
		cancelDetail.setChannelId(cardinfo.getChannelId());
		cancelDetail.setAgencyId(cardinfo.getAgencyId());
		cancelDetail.setVehicleId(cardinfo.getVehicleId());
		cancelDetail.setReplaceStatus(0);
		cancelledCardDetailRepo.save(cancelDetail);
	}
	
	@Override
	public AccountCardBalanceOperate queryBankCard(String cardId) {
		AccountCardBalanceOperate accountCardBalanceOperate =  accountCardBalanceOperateRepo.findByCardId(cardId);
		return accountCardBalanceOperate;
	}
	@Override
	public AppAjaxResponse updateBankCard(ManCancelRequest queryModel) {
		AppAjaxResponse appAjax = new AppAjaxResponse();
		AccountCardBalanceOperate accountCardBalanceOperate = queryBankCard(queryModel.getCardId());
		accountCardBalanceOperate.setBankCardId(queryModel.getBankcard());
		 accountCardBalanceOperateRepo.save(accountCardBalanceOperate);
		 appAjax.setStatus(1);
		return appAjax;
	}

	@Override
	public AccountRefundDetail findRefundBalance(String cardId) {
		return accountRefundDetailRepo.findByCardId(cardId);
	}

	@Override
	public List<AccountRefundLog> listRefundMsg(String cardId) {
		List<AccountRefundLog> list = accountRefundLogRepo.findByCardId(cardId);
		if(list!=null) {
			return list;
		}
		return null;
	}
}

