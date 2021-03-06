
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.customerservice.finance.CardAccountRefundRequest;
import cn.com.taiji.css.model.customerservice.finance.CardRefundConfirmRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.manager.comm.client.ReckonBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.ChargeNoTransRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.ChargeNoTransResponse;
import cn.com.taiji.dsi.model.comm.protocol.reckon.CardAccountReckonRequest;
import cn.com.taiji.dsi.model.comm.protocol.reckon.CardAccountReckonResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.AccountCardBalance;
import cn.com.taiji.qtk.entity.AccountCardBalanceOperate;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.AccountRefundLog;
import cn.com.taiji.qtk.entity.CCBCardBalance;
import cn.com.taiji.qtk.entity.CancelledCardDetail;
import cn.com.taiji.qtk.entity.CardAccountBalance;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CcbConsumeDetails;
import cn.com.taiji.qtk.entity.CcbReChargeDetails;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.FileInprovinceDetail;
import cn.com.taiji.qtk.entity.FileOutprovinceDetail;
import cn.com.taiji.qtk.entity.LkfRechargeDetails;
import cn.com.taiji.qtk.entity.MicroPayMentDetail;
import cn.com.taiji.qtk.entity.PaymentBackDetail;
import cn.com.taiji.qtk.entity.StoreAccCardBalance;
import cn.com.taiji.qtk.entity.TrafficRecordDetail;
import cn.com.taiji.qtk.entity.TrafficRecordDetailNew;
import cn.com.taiji.qtk.entity.dict.AccountCardBalanceOperateType;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceOperateRepo;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.AccountRefundDetailRepo;
import cn.com.taiji.qtk.repo.jpa.AccountRefundLogRepo;
import cn.com.taiji.qtk.repo.jpa.CCBCardBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.CancelledCardDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CcbConsumeDetailsRepo;
import cn.com.taiji.qtk.repo.jpa.CcbReChargeDetailsRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;
import cn.com.taiji.qtk.repo.jpa.FileInprovinceDetailRepo;
import cn.com.taiji.qtk.repo.jpa.FundSerialDetailRepo;
import cn.com.taiji.qtk.repo.jpa.LkfConsumeDetailsRepo;
import cn.com.taiji.qtk.repo.jpa.LkfRechargeDetailsRepo;
import cn.com.taiji.qtk.repo.jpa.PaymentBackDetailRepo;
import cn.com.taiji.qtk.repo.jpa.StoreAccCardBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.TrafficRecordDetailNewRepo;
import cn.com.taiji.qtk.repo.jpa.TrafficRecordDetailRepo;


@Service
public class CardRefundConfirmManagerImpl extends AbstractDsiCommManager implements CardRefundConfirmManager{
	
	
	@Autowired
	private AccountRefundDetailRepo accountRefundDetailRepo;
	@Autowired
	private AccountRefundLogRepo accountRefundLogRepo;
	/*@Autowired
	private CardAccountRefundManager cardAccountRefundManager;*/
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private ChargeDetailRepo chargeRepo;
	@Autowired
	private FileInprovinceDetailRepo inRepo;
	@Autowired
	private CancelledCardDetailRepo cancelledCardDetailRepo;
	@Autowired
	private ReckonBinService reckonBinService;
	@Autowired
	private AccountCardBalanceOperateRepo accountCardBalanceOperateRepo;
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;
	@Autowired
	private FinanceBinService financeBinService;
	@Autowired
	private CcbConsumeDetailsRepo ccbConsumeDetailsRepo;
	@Autowired
	private CcbReChargeDetailsRepo ccbReChargeDetailsRepo;
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private AccountCardBalanceRepo accountCardBalanceRepo;
	@Autowired
	private CCBCardBalanceRepo cCBCardBalanceRepo;
	@Autowired
	private LkfConsumeDetailsRepo lkfConsumeDetailsRepo;
	@Autowired
	private LkfRechargeDetailsRepo lkfRechargeDetailsRepo;
	@Autowired
	private FundSerialDetailRepo fundSerialDetailRepo;
	@Autowired
	private TrafficRecordDetailNewRepo traNewRepo;
	@Autowired
	private TrafficRecordDetailRepo traRepo;
	@Autowired
	private PaymentBackDetailRepo payBackRepo;
	@Autowired
	private StoreAccCardBalanceRepo storeAccCardBalanceRepo;
	
	
	@Override
	public  LargePagination<AccountRefundDetail> queryPage(CardAccountRefundRequest queryModel, User user) throws ManagerException, IOException {
		queryModel.validate();
		LargePagination<AccountRefundDetail> large = new LargePagination<AccountRefundDetail>();
		List<AccountRefundDetail> list = Lists.newArrayList();
		AccountRefundDetail account=getAccountRefund(queryModel,user);
		list.add(account);
		large.setResult(list);
		return large;
	}

	private AccountRefundDetail getAccountRefund(CardAccountRefundRequest queryModel, User user) throws IOException, ManagerException {
		//整理注销信息表数据
		updateCancelDetailCardType(queryModel);
		CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
		AccountCardBalanceOperate oprt = findOprtByCardId(queryModel.getCardId());
		if(null==cardInfo) {
			throw new ManagerException("业务校验失败:未查询到卡信息");
		}
		if(null!=oprt&&oprt.getType().equals(AccountCardBalanceOperateType.BALANCE_SUPPLY)) {
			throw new ManagerException("业务校验失败:该卡退款方式为余额补领，不能进行退款");
		}
		if(cardInfo.getCardType()/100==1) {
			throw new ManagerException("业务校验失败:该卡类型为记账卡");
		}
		//渠道校验
		agencyCheck(cardInfo, user);
		CancelledCardDetail CancelledCardDetail = cancelledCardDetailRepo.findByCardId(queryModel.getCardId());
		if(null==CancelledCardDetail) {
			throw new ManagerException("业务校验失败:未查询到注销信息");
		}
		if(!cancel12ArgueTime(CancelledCardDetail)) {
			throw new ManagerException("业务校验失败:注销未过争议期");
		}
		
		AccountRefundDetail accountRefundDetail= accountRefundDetailRepo.findByCardId(queryModel.getCardId());
		if(null!=accountRefundDetail) {
			return accountRefundDetail;
		}
		 accountRefundDetail = new AccountRefundDetail();
		accountRefundDetail.setCardId(queryModel.getCardId());
		Long accountCardBalance = 0L;
		//建行卡号10-12位为03
		String ccbCardMark=cardInfo.getCardId().substring(10,12);
		if(Long.parseLong(CancelledCardDetail.getCancellationTime())>=20200101) {
			StoreAccCardBalance reckonAccountByCardId = storeAccCardBalanceRepo.findByCardId(queryModel.getCardId());
			if(null!=reckonAccountByCardId) {
				if(reckonAccountByCardId.getHandleDate().equals("20200101")) {
					throw new ManagerException("业务处理失败:卡账正在计算,请10分钟后重试");
				}
				reckonAccountByCardId = reckonAccountByCardId(queryModel.getCardId());
				accountRefundDetail.setRefundBalance(reckonAccountByCardId.getBalance());
				accountRefundDetail.setAccountCardBalance(reckonAccountByCardId.getBalance());
				accountRefundDetail.setRefundType(RefundDetailType.GLYQR);
			}else {
				saveStoreBalance(queryModel.getCardId());
				throw new ManagerException("业务处理失败:卡账正在计算,请10分钟后重试");
			}
		}else {
			if(ccbCardMark.equals("03")) {
				accountCardBalance = reckonCcbAccountBalance(user, queryModel.getCardId(), accountRefundDetail);
			}else {
				accountRefundDetail.setNoChargeFee(0L);
				accountCardBalance = findAccountCardBalanceBycardId(queryModel.getCardId(),user);
			}
			//计算后的卡账
			accountRefundDetail.setAccountCardBalance(accountCardBalance);
			//注销时读取的卡内余额
			if(null!=oprt) {
				accountRefundDetail.setCancelBalance(oprt.getBalance()==null?0:oprt.getBalance());
			}
			//最后一笔交易后余额
			Long postBalance = getPostBalance(queryModel.getCardId());
			accountRefundDetail.setPostBalance(postBalance);
			//退款数据处理规则
			if(ccbCardMark.equals("03")) {
				jhAccountReckonRule(accountRefundDetail, accountCardBalance, oprt==null?0L:oprt.getBalance()==null?0L:oprt.getBalance(), postBalance);
			}else {
				accountReckonRule(accountRefundDetail, accountCardBalance, oprt==null?0L:oprt.getBalance()==null?0L:oprt.getBalance(), postBalance);
			}
		}
		
		accountRefundDetail.setAgencyId(cardInfo.getAgencyId());
		accountRefundDetail.setCreateTime(QTKUtils.getDateString());
		accountRefundDetail.setCustomerId(cardInfo.getCustomerId());
		accountRefundDetail.setStaffId(user.getStaff().getStaffId());
		Calendar calendar = CancelledCardDetail.getCreateTime();
		accountRefundDetail.setCancelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
		//set用户信息
		setCustomerInfo(cardInfo, accountRefundDetail);
		//set银行卡号
		setBankCardId(queryModel.getCardId(), accountRefundDetail);
		accountRefundDetailRepo.save(accountRefundDetail);
		return accountRefundDetail;
	}
	
	
	private List<String> getRefundListByAgencyId(CardAccountRefundRequest queryModel, User user) {
		List<String> listCancelDetails =Lists.newArrayList();
		List<String> listRefunds =Lists.newArrayList();
		List<String> resultList = Lists.newArrayList();
		Calendar beforDate = null;
		Calendar afterDate =null;
		
		
		String agencyId = user.getStaff().getServiceHall().getAgencyId();
		if(null!=queryModel.getBeforeDate()) {
			 beforDate = getCalenderDate(queryModel.getBeforeDate());
			 afterDate = getCalenderDate(queryModel.getAfterDate());
		}
		if(agencyId.equals("52010102018")||agencyId.equals("52010102002")) {
			if(null!=beforDate) {
				listCancelDetails = cancelledCardDetailRepo.cardIdsByDate_ccb(beforDate,afterDate);
				listRefunds = accountRefundDetailRepo.cardIdsByDate_ccb(queryModel.getBeforeDate(), queryModel.getAfterDate());
			}else {
				//queryFlag = true;
				listCancelDetails.add(queryModel.getCardId());
				resultList.add(queryModel.getCardId());
			}
			if(null!=queryModel.getBeforeDate()&&null!=listCancelDetails&&listCancelDetails.size()!=0) {
				listCancelDetails.removeAll(listRefunds);
				if(null!=listCancelDetails&&listCancelDetails.size()>100) {
					resultList = listCancelDetails.subList(0, 100);
				}else {
					resultList=listCancelDetails;
				}
			}
		}else if(agencyId.equals("52010104001")){
			if(null!=beforDate) {
				listCancelDetails = cancelledCardDetailRepo.cardIdsByDate_hcb(beforDate,afterDate);
				listRefunds = accountRefundDetailRepo.cardIdsByDate_hcb(queryModel.getBeforeDate(), queryModel.getAfterDate());
			}else {
				//queryFlag = true;
				listCancelDetails.add(queryModel.getCardId());
				resultList.add(queryModel.getCardId());
			}
			if(null!=queryModel.getBeforeDate()&&null!=listCancelDetails&&listCancelDetails.size()!=0) {
				listCancelDetails.removeAll(listRefunds);
				if(null!=listCancelDetails&&listCancelDetails.size()>100) {
					resultList = listCancelDetails.subList(0, 100);
				}else {
					resultList=listCancelDetails;
				}
			}
		}else {
			if(null!=beforDate) {
				listCancelDetails = cancelledCardDetailRepo.cardIdsByDate(beforDate,afterDate);
				listRefunds = accountRefundDetailRepo.cardIdsByDate(queryModel.getBeforeDate(), queryModel.getAfterDate());
			}else {
				//queryFlag = true;
				listCancelDetails.add(queryModel.getCardId());
				resultList.add(queryModel.getCardId());
			}
			if(null!=queryModel.getBeforeDate()&&null!=listCancelDetails&&listCancelDetails.size()!=0) {
				listCancelDetails.removeAll(listRefunds);
				if(null!=listCancelDetails&&listCancelDetails.size()>200) {
					resultList = listCancelDetails.subList(0, 200);
				}else {
					resultList=listCancelDetails;
				}
			}
		}
		return resultList;
	}
	private LargePagination<CancelledCardDetail> queryCancelDetailData(CardAccountRefundRequest queryModel) {
		CardRefundConfirmRequest confirmRequest = new CardRefundConfirmRequest();
		confirmRequest.setCardId(queryModel.getCardId());
		confirmRequest.setRefundType(queryModel.getRefundType());
		confirmRequest.setBeforeDate(queryModel.getBeforeDate());
		confirmRequest.setAgencyId(queryModel.getAgencyId());
		confirmRequest.setAfterDate(queryModel.getAfterDate());
		LargePagination<CancelledCardDetail> largeList = byPageCancelData(confirmRequest);
		return largeList;
	}
	
	private LargePagination<CancelledCardDetail> byPageCancelData(CardRefundConfirmRequest queryModel) {
		queryModel.setPageNo(1);
		queryModel.setPageSize(100);
		LargePagination<CancelledCardDetail> largeList = accountRefundDetailRepo.largePage(queryModel);
		return largeList;
	}
	
	//处理表中createTime为null数据
	@Override
	public void updateCancelDetailCreateTime(CardAccountRefundRequest queryModel) {
		List<CancelledCardDetail> cancelList = cancelledCardDetailRepo.listByCreateTimeIsNull(queryModel.getCardId());
		if(null!=cancelList&&cancelList.size()!=0) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<String> cardIds = cancelList.stream().map(p -> p.getCardId()).collect(Collectors.toList());
			List<CardInfo> cardList = cardInfoRepo.listByCardId(cardIds);
			Map<String,List<CardInfo>> cardMap = cardList.parallelStream().collect(Collectors.groupingBy(CardInfo::getCardId));
			List<CancelledCardDetail> cancelLists =  Lists.newArrayList();
			String statusTime = null;
			String createTime = null;
			for(CancelledCardDetail card:cancelList) {
				Calendar calendar = Calendar.getInstance();
				CancelledCardDetail cancelledCardDetail = card;
				 if(null!=cardMap.get(card.getCardId())) {
					 statusTime=cardMap.get(card.getCardId()).get(0).getStatusChangeTime();
					 createTime = statusTime.substring(0, 10)+" "+statusTime.substring(11,19);
					 try {
						calendar.setTime(simpleDateFormat.parse(createTime));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 cancelledCardDetail.setCreateTime(calendar);
					 cancelLists.add(cancelledCardDetail);
				 }
			}
			cancelledCardDetailRepo.saveAll(cancelLists);
			
		}
	}
	//注销信息表中存在没有卡状态的数据，需要补全
	private void updateCancelDetailCardType(CardAccountRefundRequest queryModel) {
		List<CancelledCardDetail> cancelList = Lists.newArrayList();
		if(null!=queryModel.getBeforeDate()) {
			Calendar beforDate = getCalenderDate(queryModel.getBeforeDate());
			Calendar afterDate = getCalenderDate(queryModel.getAfterDate());
			cancelList = cancelledCardDetailRepo.listBetweenCreateTime(beforDate, afterDate);
		}
		if(null!=queryModel.getCardId()) {
			CancelledCardDetail detail = cancelledCardDetailRepo.findByCardId(queryModel.getCardId());
			cancelList.add(detail);
		}
		if(null!=cancelList&&cancelList.size()!=0) {
			List<String> cardIds = cancelList.stream().map(p -> p.getCardId()).collect(Collectors.toList());
			List<CardInfo> cardList = cardInfoRepo.listByCardId(cardIds);
			Map<String,List<CardInfo>> cardMap = cardList.parallelStream().collect(Collectors.groupingBy(CardInfo::getCardId));
			List<CancelledCardDetail> cancelLists =  Lists.newArrayList();
			for(CancelledCardDetail card:cancelList) {
				CancelledCardDetail cancelledCardDetail = card;
				 if(null!=cardMap.get(card.getCardId())) {
					 cancelledCardDetail.setCardType(cardMap.get(card.getCardId()).get(0).getCardType()/100);
					 cancelLists.add(cancelledCardDetail);
				 }
			}
			cancelledCardDetailRepo.saveAll(cancelLists);
		}
	}
	private Calendar getCalenderDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date beforeDate = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beforeDate);
			return calendar;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	//建行卡账计算规则
	private void jhAccountReckonRule(AccountRefundDetail accountRefundDetail, Long accountCardBalance, Long cancelBalance,
			Long postBalance) {
		//是否需人工处理  0:需要  1：不需要
		Integer needByHandle = 0;
		if(null!=cancelBalance&&cancelBalance!=0) {
				needByHandle=1;
				accountRefundDetail.setRefundBalance(cancelBalance);
		}
		if(null!=cancelBalance&&cancelBalance!=0&&null!=accountCardBalance&&accountCardBalance!=0) {
			if(cancelBalance.equals(accountCardBalance)) {
				needByHandle=1;
				accountRefundDetail.setRefundBalance(cancelBalance);
			}
		}else if(null!=cancelBalance&&cancelBalance!=0&&null!=postBalance&&postBalance!=0) {
			if(postBalance.equals(accountCardBalance)) {
				needByHandle=1;
				accountRefundDetail.setRefundBalance(postBalance);
			}
		}else if(null!=postBalance&&postBalance!=0&&null!=accountCardBalance&&accountCardBalance!=0) {
			if(postBalance.equals(accountCardBalance)) {
				needByHandle=1;
				accountRefundDetail.setRefundBalance(postBalance);
			}else if(accountCardBalance>0L&&Math.abs(postBalance-accountCardBalance)<30000) {
				accountRefundDetail.setRefundBalance(accountCardBalance<postBalance?accountCardBalance:postBalance);
				needByHandle=1;
			}
			
		}
		else if(accountCardBalance==0&&(null==cancelBalance||cancelBalance==0)&&(null==postBalance||postBalance==0)) {
			needByHandle=1;
		}
		accountRefundDetail.setNeedByHandle(needByHandle);
		if(needByHandle==1) {
			accountRefundDetail.setRefundType(RefundDetailType.GLYQR);
			//accountRefundDetail.setType(3);
			accountRefundDetail.setRefundBalance(accountRefundDetail.getRefundBalance()+accountRefundDetail.getNoChargeFee());
		}else {
			accountRefundDetail.setRefundType(RefundDetailType.WTJTF);
		}
	}
	
	private void accountReckonRule(AccountRefundDetail accountRefundDetail, Long accountCardBalance, Long cancelBalance,
			Long postBalance) {
		//是否需人工处理  0:需要  1：不需要
		Integer needByHandle = 0;
		Long refundBalance = 0L;
		
		if(null==cancelBalance) {
			cancelBalance=0L;
		}
		if(null==postBalance) {
			postBalance=0L;
		}
		if(null==accountCardBalance) {
			accountCardBalance=0L;
		}
		if(null!=cancelBalance&&cancelBalance!=0) {
			if(cancelBalance.equals(accountCardBalance)||cancelBalance.equals(postBalance)) {
				needByHandle=1;
				accountRefundDetail.setRefundBalance(cancelBalance);
			}
		}else if(accountCardBalance==0&&(null==cancelBalance||cancelBalance==0)&&(null==postBalance||postBalance==0)) {
			accountRefundDetail.setRefundBalance(0L);
			needByHandle=1;
		}else if(accountCardBalance.equals(postBalance)) {
			needByHandle=1;
			accountRefundDetail.setRefundBalance(accountCardBalance);
		}
		else if(accountCardBalance!=cancelBalance&&accountCardBalance>0&&cancelBalance>0) {
			accountRefundDetail.setRefundBalance(accountCardBalance<cancelBalance?accountCardBalance:cancelBalance);
			needByHandle=1;
		}else if(accountCardBalance!=postBalance&&accountCardBalance>0&&postBalance>0) {
			accountRefundDetail.setRefundBalance(accountCardBalance<postBalance?accountCardBalance:postBalance);
			needByHandle=1;
		}else if(cancelBalance!=postBalance&&cancelBalance>0&&postBalance>0) {
			accountRefundDetail.setRefundBalance(cancelBalance<postBalance?cancelBalance:postBalance);
			needByHandle=1;
		}
		
		accountRefundDetail.setNeedByHandle(needByHandle);
		if(needByHandle==1) {
			accountRefundDetail.setRefundType(RefundDetailType.GLYQR);
			//accountRefundDetail.setType(3);
		}else {
			accountRefundDetail.setRefundType(RefundDetailType.WTJTF);
		}
	}
	private void setCustomerInfo(CardInfo cardInfo, AccountRefundDetail accountRefundDetail) {
		CustomerInfo customerInfo = customerInfoRepo.findByCustomerId(cardInfo.getCustomerId());
		if(null!=customerInfo) {
			accountRefundDetail.setCustomerName(customerInfo.getCustomerName());
			accountRefundDetail.setCustomerIdNum(customerInfo.getCustomerIdNum());
			accountRefundDetail.setCustomerIdType(customerInfo.getCustomerIdType());
		}
	}
	
	private void setBankCardId(String str, AccountRefundDetail accountRefundDetail) {
		AccountCardBalanceOperate accountCardBalanceOperate = accountCardBalanceOperateRepo.findByCardId(str);
		if(null!=accountCardBalanceOperate) {
			accountRefundDetail.setBankCardId(accountCardBalanceOperate.getBankCardId());
		}
	}
	
	//计算建行卡账  历史卡账时间截止到2018-08-25
	private Long reckonCcbAccountBalance(User user, String str, AccountRefundDetail accountRefundDetail)
			throws IOException {
		Long accountCardBalance;
		//通行流水总金额
		Long consumeSum = inRepo.getSumByFee(str, "2018-08-24 23:59:59","2018-08-24T23:59:59");
		//圈存流水总金额
		Long chargeSum = ccbReChargeDetailsRepo.sumFeeAndExTime(str);
		if(null==consumeSum) {
			consumeSum=0L;
		}
		if(null==chargeSum) {
			chargeSum=0L;
		}
		//获取历史卡账
		CCBCardBalance cCBCardBalance = cCBCardBalanceRepo.findByCardId(str);
		if(null!=cCBCardBalance) {
			accountCardBalance = cCBCardBalance.getCardBalance()-consumeSum+chargeSum;
		}else {
			accountCardBalance=chargeSum-consumeSum;
		}
		//充值未圈存数据
		ChargeNoTransResponse response = getChargeNoTransFee(user,str);
		if(null!=response.getNoChargeFee()) {
			accountRefundDetail.setNoChargeFee(response.getNoChargeFee());
		}else {
			accountRefundDetail.setNoChargeFee(0L);
		}
		return accountCardBalance;
	}
	
	//充值未圈存
	private ChargeNoTransResponse getChargeNoTransFee(User user, String str) throws IOException {
		ChargeNoTransRequest req = new ChargeNoTransRequest();
		super.commSet(req,user);
		req.setCardId(str);
		ChargeNoTransResponse response = financeBinService.chargeNoTrans(req);
		return response;
	}
	private Long findAccountCardBalanceBycardId(String cardId,User user) {
		AccountCardBalance accountCardBalance = accountCardBalanceRepo.findAccountCardBalanceByCardId(cardId);
		CardAccountReckonRequest req = new CardAccountReckonRequest();
		super.commSet(req,user);
		req.setCardId(cardId);
		
		CardAccountReckonResponse res = null;
		try {
			//初始化卡卡账
			if(null==accountCardBalance) {
				req.setCountType(0);
				 res = reckonBinService.cardAccountReckon(req);
			}
			req.setCountType(2);
			res = reckonBinService.cardAccountReckon(req);
			return res.getPostBalance();
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private AccountCardBalanceOperate findOprtByCardId(String cardId) {
		return accountCardBalanceOperateRepo.findByCardId(cardId);
	}
	
	@Override
	public AppAjaxResponse updateRefundBalance(CardAccountRefundRequest queryModel, User user, HttpServletRequest request) {
		AccountRefundDetail entity = accountRefundDetailRepo.findByCardId(queryModel.getRefundCardId());
		entity.setRefundBalance(queryModel.getRefundBalance());
		accountRefundDetailRepo.save(entity);
		queryModel.setRefundType(4);
		AppAjaxResponse res = null;
		//AppAjaxResponse res = cardAccountRefundManager.saveRefundDetailLog(queryModel,user,request);
		return res;
	}

	/**
	 * 确认退费  完成退费
	 */
	@Override
	public AppAjaxResponse confirmBalance(CardAccountRefundRequest queryModel, User user,
			HttpServletRequest request) {
		AccountRefundDetail entity = accountRefundDetailRepo.findByCardId(queryModel.getRefundCardId());
		if(queryModel.getRefundType()==2) {
			entity.setRefundType(RefundDetailType.GLYQR);
			//entity.setType(4);
		}else if(queryModel.getRefundType()==3||queryModel.getRefundType()==4) {
			entity.setRefundType(RefundDetailType.YWCTF);
			entity.setCompleTime(CssUtil.getNowDateTimeStrWithoutT());
			//entity.setType(6);
			//该笔退费为二次核定时，需将两次的退费加起来
			if(null==entity.getCompleBalance()) {
				entity.setCompleBalance(entity.getRefundBalance());
			}else {
				entity.setCompleBalance(entity.getRefundBalance()+entity.getCompleBalance());
			}
			//entity.setCompleTime(CssUtil.getNowDateTimeStrWithoutT());
		}
		AppAjaxResponse res = new AppAjaxResponse();
		try {
			accountRefundDetailRepo.save(entity);
			if(queryModel.getRefundType()==3||queryModel.getRefundType()==4) {
				AccountRefundDetail accountRefundDetail = accountRefundDetailRepo.findByCardId(queryModel.getRefundCardId());
				
				//验证资金记录表里是否存在重复数据
				long countFundSerial = validationFundSerial(queryModel.getRefundCardId(),entity.getRefundBalance());
				if(countFundSerial==0) {
					fundSerialDetaiManager.saveFundSerial(user, ServiceType.CANCELREFUND, ChargeType.CASH,1, accountRefundDetail.getRefundBalance(),queryModel.getRefundCardId(),null,null);
				}
			}
			saveRefundDetailLog(queryModel,user,request);
			res.setStatus(1);
			if(queryModel.getRefundType()==1) {
				res.setMessage("确认成功");
			}else{
				res.setMessage("成功");
			}
		} catch (Exception e) {
			res.setStatus(0);
			if(queryModel.getRefundType()==1) {
				res.setMessage("确认失败");
			}else if(queryModel.getRefundType()==2) {
				res.setMessage("失败");
			}
			
			e.printStackTrace();
		}
		
		return res;
	}
	/*@Override
	public CardAccountBalance queryData(CardAccountRefundRequest queryModel) {
		List<CardAccountBalance> list =  queryList(queryModel.getCardId());
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
	}*/
	
	private String rexCompile(String time) {
		if(null==time) {
			return "0";
		}
    	// 数字匹配
    	Matcher matcher = Pattern.compile("\\d+").matcher(time);
    	String mathTime ="";
    	while (matcher.find()) {
    		mathTime+=matcher.group(); // 打印出：11
    	}
		return mathTime;
	}
	
	private Long getPostBalance(String cardId) {
		CancelledCardDetail cardDetail = cancelledCardDetailRepo.findByCardId(cardId);
		List<ChargeDetail> list_1 = Lists.newArrayList();
		List<CardAccountBalance> list = new ArrayList<CardAccountBalance>();
		Long postBalance = 0L;
		
		
		//如果是建行
		String biaoshi=cardDetail.getCardId().substring(10,12);
		if(biaoshi.equals("03")) {
			List<Object[]> listObj = Lists.newArrayList();
			List<Object[]> listCCBChargeObj = Lists.newArrayList();
			listObj = ccbConsumeDetailsRepo.ListBalanceAndExTime(cardId);
			if(null!=listObj&&listObj.size()!=0) {
				objectList(listObj, list);
				listCCBChargeObj=ccbReChargeDetailsRepo.ListBalanceAndTradeTime(cardId);
				if(null!=listCCBChargeObj&&listCCBChargeObj.size()!=0) {
					ccbChargeDetailList(listCCBChargeObj,list);
				}
				postBalance = getCcbPostBalance(list);
			}
		}else {
			postBalance = getLastPostBalance(cardId, list_1);
		}
		return postBalance;
	}
	private Long getCcbPostBalance(List<CardAccountBalance> list) {
		list = list.stream().filter(distinctByKey(o -> ((CardAccountBalance)o).getCompareTime())).collect(Collectors.toList());
		list.sort((o1, o2) -> o1.getCompareTime().compareTo(o2.getCompareTime()));
		//补充充值数据中的交易前、后金额
		for(int i=1;i<list.size();i++) {
			if(list.get(i).getTransType()==1) {
				if(list.get(i-1).getPostBalance()!=0L) {
					list.get(i).setPreBalance(list.get(i-1).getPostBalance());
					if(null!=list.get(i-1).getPostBalance()&&null!=list.get(i).getFee()) {
						list.get(i).setPostBalance(list.get(i-1).getPostBalance()+list.get(i).getFee());
					}
				}
			}
		}
		list.sort((o1, o2) -> o2.getCompareTime().compareTo(o1.getCompareTime()));
		if(null!=list&&list.size()!=0) {
			return list.get(0).getPostBalance();
		}
		return 0L;
	}
	
	
	
	private Long getLastPostBalance(String cardId, List<ChargeDetail> list_1) {
		List<Object[]> listObj = inRepo.getPostBalanceAndExTime(cardId);
		List<CardAccountBalance> list = new ArrayList<CardAccountBalance>();
		if(null!=listObj&&listObj.size()!=0) {
			objectList1(listObj, list);
			list.sort((o1, o2) -> o2.getCompareTime().compareTo(o1.getCompareTime()));
			list = list.stream().filter(distinctByKey(o -> ((CardAccountBalance)o).getCompareTime())).collect(Collectors.toList());
			if(null!=list&&list.size()!=0) {
				String tradeTime = list.get(0).getExTime();
				tradeTime= rexCompile(tradeTime);
				list_1=chargeRepo.findListAfterTradeTime(cardId,tradeTime);
			}else {
				list_1 = chargeRepo.findNewCharge(cardId);
			}
		}else {
			list_1 = chargeRepo.findNewCharge(cardId);
		}
		//补充充值数据中的交易前、后金额
		if(null!=list_1&&list_1.size()!=0) {
			int feeCount = 0;
			for(int i=0;i<list_1.size();i++) {
				feeCount+=list_1.get(i).getPaidAmount();
			}
			
			return null!=list&&list.size()!=0?feeCount+list.get(0).getPostBalance():feeCount;
			/*if(null!=list&&list.size()!=0) {
				return feeCount+list.get(0).getPostBalance();
			}*/
		}else {
			if(null!=list&&list.size()!=0) {
				return list.get(0).getPostBalance();
			}
//			else if(null==listObj||null!=list_1) {
//				return list_1.stream().map(ChargeDetail::getPaidAmount).reduce(0L, (a, b) -> a + b);
//			}
			else {
				return 0L;
			}
		}
	}
	@Override
	public List<CardAccountBalance> queryList(String cardId) {
		//CancelledCardDetail cardDetail = cancelledCardDetailRepo.findByCardId(cardId);
		CardInfo findByCardId = cardInfoRepo.findByCardId(cardId);
		List<ChargeDetail> list_1 = Lists.newArrayList();
		List<TrafficRecordDetail> list_2 = Lists.newArrayList();;
		List<CardAccountBalance> list_result = new ArrayList<CardAccountBalance>();
		List<CardAccountBalance> list = new ArrayList<CardAccountBalance>();
		List<CcbConsumeDetails> listConsume = Lists.newArrayList();
		List<CcbReChargeDetails> ccbChargeList = Lists.newArrayList();
		List<FileInprovinceDetail> listInpro = Lists.newArrayList();
		List<Object[]> listObj = Lists.newArrayList();
		List<Object[]> listCCBChargeObj = Lists.newArrayList();
		
		
		if(findByCardId.getAgencyId().equals("52010102018")||findByCardId.getAgencyId().equals("52010102002")) {
			listObj = ccbConsumeDetailsRepo.ListBalanceAndExTime(cardId);
			if(null!=listObj&&listObj.size()!=0) {
				objectList(listObj, list);
				listCCBChargeObj=ccbReChargeDetailsRepo.ListBalanceAndTradeTime(cardId);
				if(null!=listCCBChargeObj&&listCCBChargeObj.size()!=0) {
					ccbChargeDetailList(listCCBChargeObj,list);
				}
			}
		}else {
			listObj = inRepo.ListBalanceAndExTime(cardId);
			if(null!=listObj&&listObj.size()!=0) {
				objectList(listObj, list);
			}
			list_1 = chargeRepo.findNewCharge(cardId);
		}
		
		//充值表
		ccbConsumeList(listConsume, list);
		//省内流水表
		inprovinceList(listInpro, list);
		//去重
		list = list.stream().filter(distinctByKey(o -> ((CardAccountBalance)o).getCompareTime())).collect(Collectors.toList());
		ccbChargeList(ccbChargeList,list);
		chargeList(list_1, list);
		List<CardAccountBalance> list2 = list;
		list2.sort((o1, o2) -> o1.getCompareTime().compareTo(o2.getCompareTime()));
		//补充充值数据中的交易前、后金额
		for(int i=1;i<list2.size();i++) {
			if(list2.get(i).getTransType()==1) {
				if(list2.get(i-1).getPostBalance()!=0L) {
					list2.get(i).setPreBalance(list2.get(i-1).getPostBalance());
					if(null!=list2.get(i-1).getPostBalance()&&null!=list2.get(i).getFee()) {
						list2.get(i).setPostBalance(list2.get(i-1).getPostBalance()+list2.get(i).getFee());
					}
				}
			}
		}
		list2.sort((o1, o2) -> o2.getCompareTime().compareTo(o1.getCompareTime()));
		//校验连续性
		if(0!=list2.size()&&1!=list2.size()) {
			for(int i=list2.size()-1;i>0;i--) {
				if(list2.get(i).getTransType()!=1&&0!=list2.get(i-1).getPostBalance()) {
					
					if(!list2.get(i-1).getPreBalance().equals(list2.get(i).getPostBalance())) {
						list2.get(i).setContinuey(0L);
					}else {
						list2.get(i).setContinuey(1L);
					}
				}else {
					if(i!=list2.size()-1&&0!=list2.get(i-1).getPostBalance()) {
						if(!list2.get(i-1).getPreBalance().equals(list2.get(i).getPostBalance())) {
							list2.get(i).setContinuey(0L);
						}else {
							list2.get(i).setContinuey(1L);
						}
					}
				}
			}
		}
		List<CardAccountBalance> list1 = list2;
		/*//取前100条
		if(list1.size()>100) {
			for(int i=0;i<100;i++) {
				CardAccountBalance result = list1.get(i);
				list_result.add(result);
			}
			return list_result;
		}*/
		return list1;
		
	}
	
	  public static <T> Predicate<T> distinctByKey(Function<? super T, Object>
	  keyExtractor) { 
		  Map<Object, Boolean> map = new ConcurrentHashMap<>(); 
		  return
		  t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	  }
	
	private void ccbChargeDetailList(List<Object[]> listCCBChargeObj, List<CardAccountBalance> list) {
		int len = listCCBChargeObj.size();
		String compareTime = null;
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(listCCBChargeObj.get(i)[1].toString());
			entity.setPreBalance(0L);
			if(null!=listCCBChargeObj.get(i)[2]) {
				entity.setFee(Long.parseLong(listCCBChargeObj.get(i)[2].toString()));
			}
			entity.setPostBalance(0L);
			//entity.setPostBalance(list_1.get(i).getPreBalance()+list_1.get(i).getRechargeAmount());
			compareTime = listCCBChargeObj.get(i)[3].toString();
			entity.setCompareTime(Long.parseLong(compareTime));
			entity.setChargeTime(compareTime.substring(0,4)+"-"+compareTime.substring(4,6)+"-"+compareTime.substring(6,8)+" "+compareTime.substring(8,10)+":"+compareTime.substring(10,12)+":"+compareTime.substring(12,14));
			entity.setTradeType("充值交易");
			entity.setTransType(1);
			list.add(entity);
		}
		
	}
	private void ccbConsumeList(List<CcbConsumeDetails> list_ccbConsume,List<CardAccountBalance> list) {
		int len = list_ccbConsume.size();
		if(len>=50) {
			len=50;
		}else {
			len=list_ccbConsume.size();
		}
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_ccbConsume.get(i).getCardId());
			entity.setPreBalance(list_ccbConsume.get(i).getPreBalance());
			entity.setFee(list_ccbConsume.get(i).getFee());
			entity.setPostBalance(list_ccbConsume.get(i).getPostBalance());
			entity.setEnTime(list_ccbConsume.get(i).getEnTime());
			entity.setEnTolllaneName(list_ccbConsume.get(i).getEnName());
			entity.setExTime(list_ccbConsume.get(i).getExTime());
			entity.setExTolllaneName(list_ccbConsume.get(i).getExName());
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getExTime())));
			entity.setChargeTime("");
			if(null!=list_ccbConsume.get(i).getExnetNo()) {
				if(list_ccbConsume.get(i).getExnetNo().substring(0,2).equals("52")) {
					entity.setTradeType("省内交易");
				}else {
					entity.setTradeType("省外交易");
				}
			}
			
			entity.setTransType(4);
			list.add(entity);
		}
	}
	private void trafficRecordDetailList(List<TrafficRecordDetail> list_2,
			List<CardAccountBalance> list) {
		int len = list_2.size();
		if(len>=100) {
			len=100;
		}else {
			len=list_2.size();
		}
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_2.get(i).getCardId());
			entity.setPreBalance(list_2.get(i).getPreBalance());
			entity.setFee(list_2.get(i).getFee());
			entity.setPostBalance(list_2.get(i).getPostBalance());
			entity.setEnTime(list_2.get(i).getEnTime());
			entity.setEnTolllaneName(list_2.get(i).getEntolllaneName());
			entity.setExTime(list_2.get(i).getExTime().replace("T"," "));
			entity.setExTolllaneName(list_2.get(i).getExtolllaneName());
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getExTime())));
			entity.setChargeTime("");
			entity.setTradeType(list_2.get(i).getSerialType().getValue());
			entity.setTransType(4);
			list.add(entity);
		}
	}
	private void microList(List<FileOutprovinceDetail> list_3, List<MicroPayMentDetail> list_4,
			List<CardAccountBalance> list) {
		int len = list_4.size();
		if(len>=100) {
			len=100;
		}else {
			len=list_4.size();
		}
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_4.get(i).getCardId());
			entity.setPreBalance(list_4.get(i).getPreBalance());
			entity.setFee(list_4.get(i).getFee());
			entity.setPostBalance(list_4.get(i).getPostBalance());
			entity.setEnTime(list_4.get(i).getEnTime());
			entity.setEnTolllaneName(list_4.get(i).getEnTollNo());
			entity.setExTime(list_4.get(i).getExTime().replace("T"," "));
			entity.setExTolllaneName(list_4.get(i).getExTollNo());
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getExTime())));
			entity.setChargeTime("");
			entity.setTradeType("小额交易");
			entity.setTransType(4);
			list.add(entity);
		}
	}
	private void outprovinceList(List<FileOutprovinceDetail> list_3, List<CardAccountBalance> list) {
		int len = list_3.size();
		if(len>=100) {
			len=100;
		}else {
			len=list_3.size();
		}
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_3.get(i).getCardId());
			entity.setPreBalance(list_3.get(i).getPreBalance());
			entity.setFee(list_3.get(i).getFee());
			entity.setPostBalance(list_3.get(i).getPostBalance());
			entity.setEnTime(list_3.get(i).getEnTime());
			entity.setEnTolllaneName(list_3.get(i).getEnName());
			entity.setExTime(list_3.get(i).getExTime().replace("T"," "));
			entity.setExTolllaneName(list_3.get(i).getExName());
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getExTime())));
			entity.setChargeTime("");
			entity.setTradeType("省外交易");
			entity.setTransType(3);
			list.add(entity);
		}
	}
	private void objectList1(List<Object[]> objList, List<CardAccountBalance> list) {
		int len = objList.size();
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setPostBalance(Long.parseLong(objList.get(i)[0].toString()));
			if(null!=objList.get(i)[1]) {
				entity.setExTime(objList.get(i)[1].toString());
			}
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getExTime())));
			list.add(entity);
		}
	}
	private void objectList(List<Object[]> objList, List<CardAccountBalance> list) {
		int len = objList.size();
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(objList.get(i)[1].toString());
			entity.setPreBalance(Long.parseLong(objList.get(i)[2].toString()));
			entity.setFee(Long.parseLong(objList.get(i)[3].toString()));
			entity.setPostBalance(Long.parseLong(objList.get(i)[4].toString()));
			if(null!=objList.get(i)[5]) {
				entity.setEnTolllaneName(objList.get(i)[5].toString());
			}
			if(null!=objList.get(i)[6]) {
				entity.setEnTime(objList.get(i)[6].toString());
			}
			if(null!=objList.get(i)[7]) {
				entity.setExTolllaneName(objList.get(i)[7].toString());
			}
			if(null!=objList.get(i)[8]) {
				entity.setExTime(objList.get(i)[8].toString());
			}
			entity.setChargeTime("");
			entity.setTransType(2);
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getExTime())));
			if(objList.get(i)[9].toString().equals("52")) {
				entity.setTradeType("省内交易");
			}else {
				entity.setTradeType("省外交易");
			}
			list.add(entity);
		}
	}
	
	private void objectListLkf(List<Object[]> objList, List<CardAccountBalance> list) {
		int len = objList.size();
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(objList.get(i)[0].toString());
			entity.setPreBalance(Long.parseLong(objList.get(i)[1].toString()));
			entity.setFee(Long.parseLong(objList.get(i)[2].toString()));
			entity.setPostBalance(Long.parseLong(objList.get(i)[3].toString()));
			if(null!=objList.get(i)[4]) {
				entity.setEnTolllaneName(objList.get(i)[4].toString());
			}
			if(null!=objList.get(i)[5]) {
				entity.setExTolllaneName(objList.get(i)[5].toString());
			}
			if(null!=objList.get(i)[6]) {
				entity.setExTime(objList.get(i)[6].toString());
			}
			String time = entity.getExTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				time = sdf.format(sdf.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			entity.setCompareTime(Long.parseLong(rexCompile(time)));
			entity.setTransType(2);
			list.add(entity);
		}
	}
	private void inprovinceList(List<FileInprovinceDetail> list_2, List<CardAccountBalance> list) {
		int len = list_2.size();
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_2.get(i).getCardId());
			entity.setPreBalance(list_2.get(i).getPreBalance());
			entity.setFee(list_2.get(i).getFee());
			entity.setPostBalance(list_2.get(i).getPostBalance());
			entity.setEnTime(list_2.get(i).getEnTime());
			entity.setEnTolllaneName(list_2.get(i).getEnTolllaneName());
			entity.setExTime(list_2.get(i).getExTime());
			entity.setExTolllaneName(list_2.get(i).getExTolllaneName());
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getExTime())));
			entity.setChargeTime("");
			entity.setTradeType("省内交易");
			entity.setTransType(2);
			list.add(entity);
		}
	}
	private void ccbChargeList(List<CcbReChargeDetails> list_Charge,List<CardAccountBalance> list) {
		int len = list_Charge.size();
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_Charge.get(i).getCardId());
			entity.setPreBalance(0L);
			entity.setFee(list_Charge.get(i).getFee());
			entity.setPostBalance(0L);
			entity.setChargeTime(list_Charge.get(i).getTradeTime().replace("T"," "));
			entity.setCompareTime(Long.parseLong(rexCompile(entity.getChargeTime())));
			entity.setTradeType("充值交易");
			entity.setTransType(1);
			list.add(entity);
		}
	}
	private void chargeList(List<ChargeDetail> list_1, List<CardAccountBalance> list) {
		int len = list_1.size();
		String compareTime = null;
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_1.get(i).getCardId());
			entity.setPreBalance(0L);
			entity.setFee(list_1.get(i).getRechargeAmount());
			entity.setPostBalance(0L);
			//entity.setPostBalance(list_1.get(i).getPreBalance()+list_1.get(i).getRechargeAmount());
			compareTime = list_1.get(i).getChargeId();
			compareTime = compareTime.substring(compareTime.length()-16, compareTime.length()-2);
			entity.setCompareTime(Long.parseLong(compareTime));
			entity.setChargeTime(compareTime.substring(0,4)+"-"+compareTime.substring(4,6)+"-"+compareTime.substring(6,8)+" "+compareTime.substring(8,10)+":"+compareTime.substring(10,12)+":"+compareTime.substring(12,14));
			entity.setTradeType("充值交易");
			entity.setTransType(1);
			list.add(entity);
		}
	}
	
	private void chargeListLkf(List<LkfRechargeDetails> list_1, List<CardAccountBalance> list) {
		int len = list_1.size();
		for(int i=0;i<len;i++) {
			CardAccountBalance entity = new CardAccountBalance();
			entity.setCardId(list_1.get(i).getCardNo());
			entity.setPreBalance(0L);
			entity.setFee(list_1.get(i).getRealPrice());
			entity.setPostBalance(0L);
			//entity.setPostBalance(list_1.get(i).getPreBalance()+list_1.get(i).getRechargeAmount());
			entity.setChargeTime(list_1.get(i).getTradeTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			String time = entity.getChargeTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				time = sdf.format(sdf.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			entity.setCompareTime(Long.parseLong(rexCompile(time)));
			entity.setTradeType("充值交易");
			entity.setTransType(1);
			list.add(entity);
		}
	}
		
	@Override
	public AppAjaxResponse saveRefundInfo(CardAccountRefundRequest queryModel, User user,HttpServletRequest request) {
		AppAjaxResponse response  = new AppAjaxResponse();
		response = saveRefundDetail(queryModel, user,request);
		return response;
	}

	private AppAjaxResponse saveRefundDetail(CardAccountRefundRequest queryModel, User user,HttpServletRequest request) {
		AccountRefundDetail entity = accountRefundDetailRepo.findByCardId(queryModel.getRefundCardId());
		CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getRefundCardId());
		if(null!=cardInfo) {
			entity.setCustomerId(cardInfo.getCustomerId());
		}
		entity.setStaffId(user.getStaffId());
		if(entity.getRefundType().equals(RefundDetailType.ECHDTF)) {
			entity.setRefundBalance(queryModel.getRefundBalance()-entity.getCompleBalance());
			entity.setCompleBalance(entity.getRefundBalance());
		}else {
			entity.setRefundBalance(queryModel.getRefundBalance());
			entity.setCompleBalance(queryModel.getRefundBalance());
		}
		entity.setRefundType(RefundDetailType.GLYQR);
		//entity.setType(4);
		accountRefundDetailRepo.save(entity);
		//保存日志
		saveRefundDetailLog(queryModel,user,request);
		
		AppAjaxResponse response = new AppAjaxResponse();
		response.setStatus(1);
		response.setMessage("成功");
		return response;
	}
	@Override
	public AppAjaxResponse saveRefundDetailLog(CardAccountRefundRequest queryModel, User user,HttpServletRequest request) {
		String remoteIp = LoginHelper.getLoginIP(request);
		AccountRefundLog entity = new AccountRefundLog();
		entity.setCardId(queryModel.getRefundCardId());
		CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getRefundCardId());
		if(null!=cardInfo) {
			entity.setCustomerId(cardInfo.getCustomerId());
		}
		entity.setStaffId(user.getStaffId());
		entity.setRefundBalance(queryModel.getRefundBalance());
		LocalDateTime now = LocalDateTime.now();
		String enableTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		entity.setCreateTime(enableTime);
		entity.setChannelId(user.getStaff().getServiceHall().getServiceHallId());
		entity.setRemoteIp(remoteIp);
		AccountRefundDetail accountRefundDetail = accountRefundDetailRepo.findByCardId(queryModel.getRefundCardId());
		String biaoshi=cardInfo.getCardId().substring(10,12);
		
		if(queryModel.getRefundType()==1) {
			if(biaoshi.equals("03")) {
				entity.setRecord("登录用户:"+user.getLoginName()+",工号为:"+user.getStaffId()+"已人工提交退款,金额为:"+((double)queryModel.getRefundBalance())/100+"元");
			}else {
				entity.setRecord("登录用户:"+user.getLoginName()+",工号为:"+user.getStaffId()+"已人工确认退款,金额为:"+((double)accountRefundDetail.getRefundBalance())/100+"元");
			}
		}else if(queryModel.getRefundType()==3) {
			entity.setRecord("登录用户:"+user.getLoginName()+",工号为:"+user.getStaffId()+"已人工完成该退款,金额为:"+((double)accountRefundDetail.getRefundBalance())/100+"元");
		}else if(queryModel.getRefundType()==4) {
			entity.setRecord("登录用户:"+user.getLoginName()+",工号为:"+user.getStaffId()+"已修改退款,修改后金额为:"+((double)queryModel.getRefundBalance())/100+"元");
		}else if(queryModel.getRefundType()==5) {
			entity.setRecord("登录用户:"+user.getLoginName()+",工号为:"+user.getStaffId()+"对该笔退款进行了冲正。");
		}else if(queryModel.getRefundType()==6) {
			entity.setRecord("登录用户:"+user.getLoginName()+",工号为:"+user.getStaffId()+"对该笔退款进行了二次核定。");
		}else {
			entity.setRecord("登录用户:"+user.getLoginName()+",工号为:"+user.getStaffId()+"添加描述");
			entity.setDiscription(queryModel.getDescription());
		}
		
		accountRefundLogRepo.save(entity);
		AppAjaxResponse response = new AppAjaxResponse();
		response.setStatus(1);
		response.setMessage("成功");
		return response;
	}

	@Override
	public AccountRefundDetail findByCardId(String cardId) {
		return accountRefundDetailRepo.findByCardId(cardId);
	}


	@Override
	public CardAccountBalance queryData(CardAccountRefundRequest queryModel) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AppAjaxResponse byLittleRefund(CardRefundConfirmRequest queryModel) {
		
		AccountRefundDetail accountRefundDetail = accountRefundDetailRepo.findByCardId(queryModel.getCardId());
		AppAjaxResponse ajaxRes = new AppAjaxResponse();
		if(null==accountRefundDetail) {
			ajaxRes.setStatus(0);
			ajaxRes.setMessage("未查询到该笔退款,请联系管理员");
			return ajaxRes;
		}else {
			//accountRefundDetail.setRefundType(2);
			if(queryModel.getAccountCardBalance()>queryModel.getCardBalance()) {
				accountRefundDetail.setRefundBalance(queryModel.getCardBalance());
			}else if(queryModel.getAccountCardBalance()<queryModel.getCardBalance()) {
				accountRefundDetail.setRefundBalance(queryModel.getAccountCardBalance());
			}else {
				ajaxRes.setStatus(0);
				ajaxRes.setMessage("卡内余额与卡账余额一致，无法以较小金额退款，若确认金额无误，请直接点击确认按钮");
				return ajaxRes;
			}
			accountRefundDetailRepo.save(accountRefundDetail);
			ajaxRes.setMessage("成功");
		}
		
		return ajaxRes;
	}


	@Override
	public AppAjaxResponse confirmRefund(CardRefundConfirmRequest queryModel) {
		AccountRefundDetail accountRefundDetail = accountRefundDetailRepo.findByCardId(queryModel.getCardId());
		AppAjaxResponse ajaxRes = new AppAjaxResponse();
		if(null==accountRefundDetail) {
			ajaxRes.setStatus(0);
			ajaxRes.setMessage("未查询到该笔退款,请联系管理员");
			return ajaxRes;
		}else {
			//accountRefundDetail.setRefundType(2);
			accountRefundDetailRepo.save(accountRefundDetail);
			ajaxRes.setMessage("成功");
		}
		
		return ajaxRes;
	}
	@Override
	public AppAjaxResponse saveDiscript(CardAccountRefundRequest queryModel, User user,HttpServletRequest request) {
		queryModel.setRefundType(7);
		AppAjaxResponse ajaxResponse = new AppAjaxResponse();
		try {
			saveRefundDetailLog(queryModel,user,request);
			ajaxResponse.setStatus(1);
			ajaxResponse.setMessage("保存成功");
		} catch (Exception e) {
			ajaxResponse.setStatus(0);
			ajaxResponse.setMessage("保存失败");
			e.printStackTrace();
		}
		
		return ajaxResponse;
	}
	@Override
	public List<AccountRefundLog> operateRecode(String cardId) throws ManagerException {
		List<AccountRefundLog> list = accountRefundLogRepo.findByCardId(cardId);
		if(list.size()==0) {
			throw new ManagerException("暂无操作记录");
		}
		return list;
	}
	@Override
	public List<CardAccountBalance> queryListLkf(String cardId) {
		List<LkfRechargeDetails> list_1 = Lists.newArrayList();
		List<TrafficRecordDetail> list_2 = Lists.newArrayList();;
		List<CardAccountBalance> list_result = new ArrayList<CardAccountBalance>();
		List<CardAccountBalance> list = new ArrayList<CardAccountBalance>();
		List<FileInprovinceDetail> listInpro = Lists.newArrayList();
		List<Object[]> listObj = Lists.newArrayList();
		
		
			listObj = lkfConsumeDetailsRepo.listLkfConsume(cardId.substring(4, cardId.length()));
			if(null!=listObj&&listObj.size()!=0) {
				objectListLkf(listObj, list);
			}
			list_1 = lkfRechargeDetailsRepo.getLastTradeTime(cardId);
		
		//省内流水表
		chargeListLkf(list_1, list);
		List<CardAccountBalance> list2 = list;
		list2.sort((o1, o2) -> o1.getCompareTime().compareTo(o2.getCompareTime()));
		//补充充值数据中的交易前、后金额
		for(int i=1;i<list2.size();i++) {
			if(list2.get(i).getTransType()==1) {
				if(list2.get(i-1).getPostBalance()!=0L) {
					list2.get(i).setPreBalance(list2.get(i-1).getPostBalance());
					if(null!=list2.get(i-1).getPostBalance()&&null!=list2.get(i).getFee()) {
						list2.get(i).setPostBalance(list2.get(i-1).getPostBalance()+list2.get(i).getFee());
					}
				}
			}
		}
		list2.sort((o1, o2) -> o2.getCompareTime().compareTo(o1.getCompareTime()));
		//校验连续性
		if(0!=list2.size()&&1!=list2.size()) {
			for(int i=list2.size()-1;i>0;i--) {
				if(list2.get(i).getTransType()!=1&&0!=list2.get(i-1).getPostBalance()) {
					
					if(!list2.get(i-1).getPreBalance().equals(list2.get(i).getPostBalance())) {
						list2.get(i).setContinuey(0L);
					}else {
						list2.get(i).setContinuey(1L);
					}
				}else {
					if(i!=list2.size()-1&&0!=list2.get(i-1).getPostBalance()) {
						if(!list2.get(i-1).getPreBalance().equals(list2.get(i).getPostBalance())) {
							list2.get(i).setContinuey(0L);
						}else {
							list2.get(i).setContinuey(1L);
						}
					}
				}
			}
		}
		
		List<CardAccountBalance> list1 = list2;
		return list1;
		
	}
	@Override
	@Transactional
	public AppAjaxResponse againCheck(CardRefundConfirmRequest req,User user,HttpServletRequest request) {
		AccountRefundDetail accountRefundDetail = accountRefundDetailRepo.findByCardId(req.getRefundCardId());
		AppAjaxResponse ajaxRes = new AppAjaxResponse();
		if(accountRefundDetail.getRefundType().equals(RefundDetailType.YWCTF)) {
			accountRefundDetail.setRefundBalance(null);
			accountRefundDetail.setRefundType(RefundDetailType.ECHDTF);
			//accountRefundDetail.setType(9);
			accountRefundDetail.setNeedByHandle(0);
			accountRefundDetail.setTwoAudit(1);
		}
		try {
			accountRefundDetailRepo.save(accountRefundDetail);
			ajaxRes.setStatus(1);
			ajaxRes.setMessage("成功");
		} catch (Exception e) {
			ajaxRes.setStatus(0);
			ajaxRes.setMessage("保存数据失败");
			e.printStackTrace();
		}
		CardAccountRefundRequest queryModel = new CardAccountRefundRequest();
		queryModel.setRefundCardId(req.getRefundCardId());
		queryModel.setRefundType(6);
		//添加日志
		saveRefundDetailLog(queryModel,user,request);
		return ajaxRes;
	}
	
	private long validationFundSerial(String cardId,Long handleMoney) {
		
		
		return fundSerialDetailRepo.countBycardIdAndServiceTypeAndMoney(cardId, ServiceType.CANCELREFUND, handleMoney);
	}
	//校验是否过争议期
	public boolean cancel12ArgueTime(CancelledCardDetail cancelDetail) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate nowDate = LocalDate.now();
		LocalDate cancelDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(cancelDetail.getCreateTime().getTime()), dateTimeFormatter).plusDays(12);
		return nowDate.compareTo(cancelDate) > 0;
	}
	
	//退款渠道校验
	/**
	 * 退款渠道校验规则
	 * 河南双合、世纪恒通、黔通自营不能办理工行、建行、货车帮的退费，其他渠道的都可以
	 * 货车帮只能办理自己渠道的退费
	 * 工行自己负责退款业务
	 * 建行有两个渠道52010102018、52010102002
	 * @throws ManagerException 
	 */
	@Override
	public boolean agencyCheck(CardInfo cardInfo,User user) throws ManagerException {
		String userAgencyId = user.getStaff().getServiceHall().getAgencyId();
		String cardAgencyId= cardInfo.getAgencyId();
		String ccbAgencyIds = "52010102018,52010102002";
		String qtzlAagencyIds = "52010106004,52010188006,52010188033";
		String hcbAagencyId = "52010104001";
		String icbcAagencyId = "52010102007,52010102005";
		String notAagencyIds = "52010102007,52010102005,52010102018,52010102002";
		if(userAgencyId.contains(cardAgencyId)) {
			return true;
		}
		if(ccbAgencyIds.contains(userAgencyId)&&ccbAgencyIds.contains(cardAgencyId)) {
			return true;
		}else if(qtzlAagencyIds.contains(userAgencyId)&&!notAagencyIds.contains(cardAgencyId)) {
			return true;
		}else {
			throw new ManagerException("业务校验失败:本渠道无法办理该卡的退款业务");
		}
	}
	public StoreAccCardBalance reckonAccountByCardId(String cardId) {
		StoreAccCardBalance storeAccCardBalance = storeAccCardBalanceRepo.findByCardId(cardId);
		if(null==storeAccCardBalance) {
			return null;
		}
		storeAccCardBalance.setCreateTime(new Date());
		storeAccCardBalance.setHandleDate(new SimpleDateFormat("yyyMMdd").format(new Date()));
		List<TrafficRecordDetailNew> traNewList = traNewRepo.listByCardId(storeAccCardBalance.getCardId());
		//去重
		traNewList = traNewList.stream().filter(distinctByKey(o -> ((TrafficRecordDetailNew)o).getListNo())).collect(Collectors.toList());
		List<TrafficRecordDetail> traList = traRepo.listByCardId2020(storeAccCardBalance.getCardId());
		//去重
		traList = traList.stream().filter(distinctByKey(o -> ((TrafficRecordDetail)o).getListNo())).collect(Collectors.toList());
		List<ChargeDetail> chargeList = chargeRepo.findListAfterTradeTime2020(storeAccCardBalance.getCardId());
		List<PaymentBackDetail> payList = payBackRepo.listByCardId(storeAccCardBalance.getCardId());
		
		long sumTraNew = traNewList.stream().mapToLong(TrafficRecordDetailNew::getFee).sum();
		long sumTra = traList.stream().mapToLong(TrafficRecordDetail::getFee).sum();
		long sumcharge = chargeList.stream().mapToLong(ChargeDetail::getPaidAmount).sum();
		long paycharge = payList.stream().mapToLong(PaymentBackDetail::getFee).sum();
		storeAccCardBalance.setBalance(storeAccCardBalance.getInitBalance()+sumcharge+paycharge-sumTra-sumTraNew);
		storeAccCardBalanceRepo.save(storeAccCardBalance);
		return storeAccCardBalance;
	}
	
	private void saveStoreBalance(String cardId) {
		StoreAccCardBalance acc = new StoreAccCardBalance();
		acc.setAgencyId(cardInfoRepo.findByCardId(cardId).getAgencyId());
		acc.setBalance(0L);
		acc.setCardId(cardId);
		acc.setCreateTime(new Date());
		acc.setHandleDate("20200101");
		acc.setInitBalance(0L);
		acc.setIsException(0);
		acc.setPostBalance(0L);
		acc.setPreBalance(0L);
		acc.setReckonBalance(0L);
		storeAccCardBalanceRepo.save(acc);
	}
	
}

