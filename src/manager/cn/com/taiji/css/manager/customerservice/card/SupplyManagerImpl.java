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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.LmkStorageCard;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.manager.administration.pkg.AccountManager;
import cn.com.taiji.css.manager.administration.pkg.ReplaceEquipmentManager;
import cn.com.taiji.css.manager.apply.baseinfo.VehicleManagementManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.customerservice.card.ApplyCardRequest;
import cn.com.taiji.css.model.customerservice.card.ApplyCardResponse;
import cn.com.taiji.css.model.customerservice.card.CancelRequest;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.css.model.customerservice.card.SupplyRequest;
import cn.com.taiji.css.repo.jpa.LmkStorageCardRepo;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.manager.comm.client.StorageBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelResponse;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardOrderConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardConfirmResponse;
import cn.com.taiji.dsi.model.common.storage.InventoryCheckRequest;
import cn.com.taiji.dsi.model.common.storage.InventoryCheckResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.Package;
import cn.com.taiji.qtk.entity.ReplaceCardLog;
import cn.com.taiji.qtk.entity.ReplaceEquipmentCostDetail;
import cn.com.taiji.qtk.entity.StorageCardInfo;
import cn.com.taiji.qtk.entity.StoreAccCardBalance;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.IssueStatusType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.entity.dict.StorageStatus;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.CarIssuePackageInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.PackageRepo;
import cn.com.taiji.qtk.repo.jpa.ReplaceCardLogRepo;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.StoreAccCardBalanceRepo;

/**
 * @ClassName SupplyManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Service
public class SupplyManagerImpl extends AbstractDsiCommManager implements SupplyManager {

	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private AgencyRepo agencyRepo;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private ReleaseBinService releaseBinService;
	/*@Autowired
	private MaintenanceBinService maintenanceBinService;*/
	@Autowired
	private CancelManager cancelManager;
	@Autowired
	private PackageRepo packageRepo;
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private ReplaceEquipmentManager replaceEquipmentManager;
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;
	/*@Autowired
	private CancelledCardDetailRepo cancelledCardDetailRepo;*/
	@Autowired
	private StorageBinService storageBinService;
	@Autowired
	private CarIssuePackageInfoRepo carIssuePackageInfoRepo;
	@Autowired
	private StorageCardInfoRepo storageCardInfoRepo;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Autowired
	private VehicleManagementManager vehicleManager;
	@Autowired
	private ReplaceCardLogRepo replaceCardLogRepo;
	@Autowired
	private Card4XCheckFromPcManager card4XCheckFromPcManager;
	@Autowired
	private LmkStorageCardRepo lmkStorageCardRepo;
	@Autowired
	private StoreAccCardBalanceRepo storeAccCardBalanceRepo;
	@Override
	public LargePagination<CardInfo> queryPage(SupplyRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		List<CardInfo> listCard = new ArrayList<CardInfo>();
		listCard = agencyCheck(queryModel,user);
		LargePagination<CardInfo> list = new LargePagination<CardInfo>();
		if(listCard.size()==0) {
			list=null;
		}else {
			list.setResult(listCard);
		}
		return list;
	}
	
	private List<CardInfo> agencyCheck(SupplyRequest queryModel, User user) throws ManagerException {
		List<CardInfo> listCard = new ArrayList<CardInfo>();
		CardInfo cardInfo = null;
		if (StringTools.hasText(queryModel.getCardId())) {
			cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
			boolean falg =false;
			try {
				falg = agencyVarifyManager.varifyAgency(user, cardInfo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ManagerException("渠道校验失败："+e.getMessage());
			}
			if(falg) {
				if(null!=cardInfo) {
					listCard.add(cardInfo);
				}
			}else {
				throw new ManagerException("当前渠道无权操作此卡");
			}
			
		}else if (StringTools.hasText(queryModel.getVehicleId())) {
			List<CardInfo> cardList = cardInfoRepo.listByVehicleId(queryModel.getVehicleId());
			if(cardList.size()>0) {
				boolean falg =false;
				for(CardInfo info:cardList) {
					try {
						falg = agencyVarifyManager.varifyAgency(user, info);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ManagerException("渠道校验失败："+e.getMessage());
					}
					if(falg) {
						listCard.add(info);
					}
				}
				if(listCard.size()==0) {
					throw new ManagerException("当前渠道无权操作此卡");
				}
			}
			
		}
		return listCard;

	}
	@Override
	public CardCancelResponse oldCardCancel(@Valid ApplyCardRequest appReq, User user) throws ManagerException {
		
		CardCancelResponse cancelRes = new CardCancelResponse();
		if(card4XCheckFromPcManager.check4X(appReq.getNewCardId())) {
			cancelRes.setMessage("请换2X卡继续发行！");
			cancelRes.setStatus(-1);
			return cancelRes;
		};
		CancelRequest cancelReq = new CancelRequest(); 
		CardInfoQueryRequest cardInfoQueryRequest = new CardInfoQueryRequest();
		super.commSet(cardInfoQueryRequest,user);
		cardInfoQueryRequest.setCardId(appReq.getCardId());
		saveReplaceLog(appReq, user);
		Long cardCost = cardRefund(user);
		if(appReq.getSupReason()==1) {
			try {
				if(appReq.getSupplyOrReplace()) {
					fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDREPLACE, ChargeType.COMMONPOS,0, (long)cardCost*100,appReq.getCardId(),null,null);
				}else {
					fundSerialDetaiManager.saveFundSerial(user, ServiceType.CARDSUPPLY, ChargeType.COMMONPOS,0, (long)cardCost*100,appReq.getCardId(),null,null);

				}
			} catch (ManagerException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			if(null!=appReq.getNewCardId()) {
				InventoryCheckResponse invenRes = inventoryCheck(appReq.getNewCardId(), user);
				if(!invenRes.isFlag()) {
					cancelRes.setMessage(invenRes.getMessage());
					cancelRes.setStatus(4);
					return cancelRes;
				}
			}
		    /*cardInfoQueryResponse =  cardInfoQuery(cardInfoQueryRequest, cardInfoQueryResponse);
		    if(cardInfoQueryResponse.getCardStatus()==6) {*/
		    	AppCardStatusChangeResponse  cardCanRes= cardPrecancel(appReq,user);
		    	if(cardCanRes.getStatus()==1 &&appReq.getProvider()==0){
		    		cancelReq.setCardId(appReq.getCardId());
		    		cancelReq.setCardBalance(appReq.getCardBalance());
		    		cancelReq.setSupplyOrCancel(false);
		    		cancelReq.setType(0);
		    		//cardType  2表示储值卡
		    		cancelReq.setCardType(appReq.getCardType());
		    		/*AppCardStatusChangeResponse cardCancelWithCOSRes = new AppCardStatusChangeResponse();
		    		PreCancelRequest preCancelRes = new PreCancelRequest();
		    		preCancelRes.setApplyStep(appReq.getApplyStep());
		    		preCancelRes.setCardType(appReq.getCardType());
		    		preCancelRes.setProvider(appReq.getProvider());
		    		preCancelRes.setCardId(appReq.getCardId());
		    		cardCancelWithCOSRes = cancelManager.doPreCancel(preCancelRes, user);*/
		    			try {
			    			cancelRes = cancelManager.doCancel(cancelReq, user);
			    		} catch (Exception e) {
			    			cancelRes.setMessage(e.getMessage());
			    			e.printStackTrace();
			    		}
		    		
		    	}else {
		    		cancelRes.setStatus(cardCanRes.getStatus());
		    		cancelRes.setMessage(cardCanRes.getMessage());
		    	}
		} catch (ManagerException e) {
			cancelRes.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return cancelRes;
	}
	private AppCardStatusChangeResponse cardPrecancel(@Valid ApplyCardRequest appReq,User user) throws ManagerException {
		
		AppCardStatusChangeResponse  cardCanRes= new AppCardStatusChangeResponse();
		PreCancelRequest pCanReq = new PreCancelRequest();
		pCanReq.setCardId(appReq.getCardId());
		pCanReq.setProvider(appReq.getProvider());
		pCanReq.setBalanceType("BALANCE_SUPPLY");
		pCanReq.setApplyStep(1);
		//补卡退款走现金   换卡退款走余额补领
		if(appReq.getSupplyOrReplace()) {
			pCanReq.setRefundType(3);
		}else {
			pCanReq.setRefundType(2);
		}
		//2表示储值卡
		//pCanReq.setCardType(appReq.getCardType());
		pCanReq.setCardType(2);
		pCanReq.setSupplyOrCancel(false);
		/*if(appReq.getSupplyOrReplace()) {
			pCanReq.setProvider(1);
		}else {
			pCanReq.setProvider(0);
		}*/
		try {
			cardCanRes = cancelManager.doPreCancel(pCanReq, user);
		} catch (Exception e1) {
			e1.printStackTrace();
			cardCanRes.setMessage("卡注销时发生内部错误,请联系管理员");
		}
		return cardCanRes;
	}


	private CardInfoQueryResponse cardInfoQuery(CardInfoQueryRequest cardInfoQueryRequest,
			CardInfoQueryResponse cardInfoQueryResponse) throws ManagerException {
		try {
			cardInfoQueryResponse = inqueryBinService.cardInfoQuery(cardInfoQueryRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("服务器内部错误，请联系管理员。", e);
		}
		return cardInfoQueryResponse;
	}

	@Override
	public AppCardStatusChangeResponse cardApplyAndConfirm(ApplyCardRequest appRequest,User user) throws ManagerException {
		
		if(appRequest.getWhetherToOpenCard()) appRequest.validate();//如果为发卡，调用数据校验方法
		ApplyCardResponse appResponse = new ApplyCardResponse();
		AppCardStatusChangeResponse response = new AppCardStatusChangeResponse();
		if(card4XCheckFromPcManager.check4X(appRequest.getNewCardId())) {
			response.setMessage("请换2X卡继续发行！");
			response.setStatus(-1);
			return response;
		};
		CardInfoQueryRequest cardInfoQueryRequest = new CardInfoQueryRequest();
		CardInfoQueryResponse cardInfoQueryResponse = null;
		super.commSet(cardInfoQueryRequest,user);
		cardInfoQueryRequest.setCardId(appRequest.getCardId());
		
		cardInfoQueryResponse =  cardInfoQuery(cardInfoQueryRequest, cardInfoQueryResponse);
		if (appRequest.getApplyStep() == 1) {
			try {
				lmkCheck(appRequest.getNewCardId());
				commCardApply(appRequest, appResponse,user);
			} catch (ManagerException e) {
				e.printStackTrace();
				response.setManagerException(e);
			}

		} else {
			try {
				commCardOrderConfirm(appRequest, appResponse,user);
			} catch (ManagerException e) {
				e.printStackTrace();
				response.setManagerException(e);
				return response;
			}
			if (appResponse.getOrderStatus() == 2) {
				try {
					commCardConfirm(appRequest, appResponse,user);
					if(appResponse.getSuccess() && appResponse.getCardId() != null) {
						if(appRequest.getWhetherToOpenCard()) {//如果是发行新卡，发行成功，将发行套餐记录的发卡状态改成已发行状态
							List<CarIssuePackageInfo> list = carIssuePackageInfoRepo.findByVehicleIdCreateTimeDesc(user.getStaff().getServiceHall().getServiceHallId(), appRequest.getVehicleId());
							CarIssuePackageInfo carIssuePackageInfo = list.get(0);
							if(carIssuePackageInfo.getCardIssueStatus() != null && carIssuePackageInfo.getCardIssueStatus() == IssueStatusType.NOTISSUANT.getCode()) {
								carIssuePackageInfo.setCardIssueStatus(IssueStatusType.ISSUANT.getCode());
								carIssuePackageInfo.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
								carIssuePackageInfoRepo.save(carIssuePackageInfo);
							}
						}
						//发行或补卡成功，将库存状态改成已发行
						StorageCardInfo storageCardInfo = storageCardInfoRepo.findByCardIdCheck(appResponse.getCardId());
						storageCardInfo.setStatus(StorageStatus.ISSUE.getCode());
						storageCardInfo.setStatusChangeTime(QTKUtils.getDateString());
						storageCardInfoRepo.save(storageCardInfo);
					}
				} catch (ManagerException e) {
					e.printStackTrace();
					response.setManagerException(e);
					return response;
				}
			}
		}
		response.setCardId(appResponse.getCardId());
		response.setOldCardId(appResponse.getOldCardId());
		response.setCommand(appResponse.getCommand());
		response.setCosRecordId(appResponse.getCosRecordId());
		response.setOrderStatus(appResponse.getOrderStatus());
		response.setMessage(appResponse.getMessage());
		response.setStatus(appResponse.getStatus());
		return response;
	}
	
	private ApplyCardResponse commCardApply(ApplyCardRequest appRequest, ApplyCardResponse appResponse,User user)
			throws ManagerException {
		
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
		
		CardApplyRequest req = new CardApplyRequest(); 
		super.commSet(req,user);
		req.setCardId(appRequest.getNewCardId());
		req.setCardType(transformCardType(appRequest));
		req.setCosProvider(1);
		
		// 应急车设置开卡版本号 传42则会写为4X版本 不传或值不为42 则写为非4X版本
		VehicleInfo vehicleInfo = vehicleManager.findByVehicleId(appRequest.getVehicleId());
		if(vehicleInfo.getEmergencyFlag()!=null && vehicleInfo.getEmergencyFlag()==1){
			req.setCardVersion(42);
		}
		
		LocalDateTime now = LocalDateTime.now();
		String enableTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		String expireTime = now.plusYears(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		req.setEnableTime(enableTime.substring(0,enableTime.indexOf('T')).replace("-", ""));
		req.setExpireTime(expireTime.substring(0,expireTime.indexOf('T')).replace("-", ""));
		if(!appRequest.getWhetherToOpenCard()){
			CardInfo cardInfo= cardInfoRepo.findByCardId(appRequest.getCardId());
			req.setUserId(cardInfo.getCustomerId());
			req.setVehicleId(cardInfo.getVehicleId());
		}else{
			req.setUserId(appRequest.getUserId());
			req.setVehicleId(appRequest.getVehicleId());
		}
		try {
			CardApplyResponse res = releaseBinService.cardApply(req);
			appResponse.setCommand(res.getCommand());
			appResponse.setCosRecordId(res.getCosRecordId());
			appResponse.setMessage(res.getMessage());
			appResponse.setStatus(res.getStatus());
			appResponse.setOldCardId(appRequest.getOldCardId());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("开卡申请失败，请联系管理员", e);
		}
		if(appRequest.getWhetherToOpenCard()) {//如果是发行新卡，发行申请成功，将卡号记录进发行套餐记录中
			List<CarIssuePackageInfo> list = carIssuePackageInfoRepo.findByVehicleIdCreateTimeDesc(user.getStaff().getServiceHall().getServiceHallId(), appRequest.getVehicleId());
			CarIssuePackageInfo carIssuePackageInfo = list.get(0);
			if(carIssuePackageInfo.getCardIssueStatus() != null && carIssuePackageInfo.getCardIssueStatus() == IssueStatusType.NOTISSUANT.getCode()) {
				carIssuePackageInfo.setCardId(appRequest.getNewCardId());
				carIssuePackageInfo.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
				carIssuePackageInfoRepo.save(carIssuePackageInfo);
			}
		}
		return appResponse;
	}

	private void commCardOrderConfirm(ApplyCardRequest appRequest, ApplyCardResponse appResponse,User user)
			throws ManagerException {
		CardOrderConfirmRequest req = new CardOrderConfirmRequest();
		super.commSet(req,user);
		req.setCosType(1);// 1发行 4卡签绑定 9销卡
		req.setCosRecordId(appRequest.getCosRecordId());
		req.setCardId(appRequest.getNewCardId());
		req.setCommand(appRequest.getCommand());
		req.setResponse(appRequest.getCosResponse());
		try {
			CardOrderConfirmResponse res = inqueryBinService.applyCardOrderConfirm(req);
			appResponse.setCommand(res.getCommand());
			appResponse.setStatus(res.getStatus());
			appResponse.setMessage(res.getMessage());
			appResponse.setCosRecordId(req.getCosRecordId());
			appResponse.setOrderStatus(res.getOrderStatus());
			appResponse.setOldCardId(appRequest.getOldCardId());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("开卡指令确认失败，请联系管理员", e);
		}
	}
	private void commCardConfirm(ApplyCardRequest appRequest, ApplyCardResponse appResponse,User user) throws ManagerException {
		CardConfirmRequest req = new CardConfirmRequest();
		super.commSet(req,user);
		req.setCardId(appRequest.getNewCardId());
		LocalDateTime now = LocalDateTime.now();
		String enableTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		String expireTime = now.plusYears(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		req.setEnableTime(enableTime);
		req.setExpireTime(expireTime);
		if (appRequest.getWhetherToOpenCard()) {//true  执行开卡操作；  false  执行补卡操作
			Integer cardType = transformCardType(appRequest)*100+appRequest.getBindingType()*10+appRequest.getCardCategory();
			req.setCardType(cardType);
			try {
				req.setBrand(appRequest.getBrand());
			} catch (Exception e) {
				e.printStackTrace();
				req.setBrand(0);
			}
			if(appRequest.getModel() == null) {
				req.setModel("0");
			}else {
				req.setModel(appRequest.getModel()); 
			}
			req.setPackageNum(appRequest.getPkgId());
			req.setAccountOrganization(appRequest.getPackageNo());
		}else {
			saveStoreBalance(appRequest.getNewCardId());
			CardInfo caInfo = cardInfoRepo.findByCardId(appRequest.getOldCardId());
			
			req.setCardType(transformCardType(appRequest)==1? 111 : 211);
			System.out.println(caInfo.getBrand());
			req.setBrand(caInfo.getBrand()==null?0:caInfo.getBrand());
			req.setModel("0");
			/*Integer pkgnum = appRequest.getPkgId();
			//记账卡套餐
			if(null!=pkgnum&&pkgnum!=0) {
				req.setPackageNum(pkgnum);
			}else {
				
			}*/
			if("23".equals(appRequest.getOldCardId().substring(8,10))&&null!=caInfo.getPackageId()){
				Integer packpagenum = packageRepo.findId(caInfo.getPackageId()).getPackageNum();
				req.setPackageNum(packpagenum);
			}else if("23".equals(appRequest.getOldCardId().substring(8,10))&&null==caInfo.getPackageId()){
				throw new ManagerException("记账卡套餐编号不能为空");
			}else if("22".equals(appRequest.getOldCardId().substring(8,10))) {
				req.setPackageNum(0);
			}
			// 传记账机构编号
			String packageNo = queryPackageNo(appRequest, appResponse,user);
			//appRequest.setPackageNo(packageNo);
			req.setAccountOrganization(packageNo);
		}
		// 记账卡 套餐编号 FIXME 应该通过发行套餐编号 查出发行套餐 并设置相应值
		req.setNetId(appRequest.getNetId());
		try {
			CardConfirmResponse res = releaseBinService.cardConfirm(req);
			appResponse.setMessage(res.getMessage());
			appResponse.setStatus(res.getStatus());
			appResponse.setCardId(res.getCardId());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("开卡确认失败，请联系管理员", e);
		}
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

	private String queryPackageNo(ApplyCardRequest appRequest, ApplyCardResponse appResponse,User user) throws ManagerException {
		CardInfoQueryRequest cardInfoQueryRequest = new CardInfoQueryRequest();
		CardInfoQueryResponse cardInfoQueryResponse = null;
		super.commSet(cardInfoQueryRequest,user);
		cardInfoQueryRequest.setCardId(appRequest.getCardId());
		cardInfoQueryResponse = cardInfoQuery(cardInfoQueryRequest, cardInfoQueryResponse);
		Agency agency = agencyRepo.findByAgencyId(cardInfoQueryResponse.getAgencyId());
		if(null!=agency) {
			return agency.getPackageNo();
		}else {
			return null;
		}
	}

	@Override
	public CardInfo findById(@Valid String id) {

		return cardInfoRepo.findByCardId(id);
	}
	private Integer transformCardType(ApplyCardRequest appReq) {
		Integer cardType = appReq.getNewCardType();
		switch (cardType) {
		case 22:
			cardType = 2;
			break;
		case 23:
			cardType = 1;
		default:
			break;
		} 
		return cardType;
	}

	@Override
	public List<Package> queryPackage(ApplyCardRequest appReq,User user) {
		List<Package> list =accountManager.findByServiceHallId(user.getStaff().getServiceHall().getServiceHallId());
		if(null!=list) {
			return list;
		}
		return null;
	}
	
	@Override //卡设备费
	public Long cardRefund(User user) {
		List<ReplaceEquipmentCostDetail> recdList = replaceEquipmentManager.findReplaceEquipment(user);
		double cardCost = recdList.get(0).getCardCost();
		return (long)cardCost;
	}
	private InventoryCheckResponse inventoryCheck(String cardId,User user) {
		InventoryCheckRequest invenReq = new InventoryCheckRequest();
		super.commSet(invenReq,user);
		invenReq.setCardId(cardId);
		invenReq.setType(1);
		invenReq.setServiceHallId(user.getStaff().getServiceHallId());
		InventoryCheckResponse invenRes = null;
		try {
			invenRes = storageBinService.inventoryCheck(invenReq);
		} catch (ApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invenRes;
	}
	private void saveReplaceLog(ApplyCardRequest appReq, User user) {
		ReplaceCardLog replaceCardLog = new ReplaceCardLog();
		replaceCardLog.setChannelId(user.getStaff().getServiceHall().getServiceHallId());
		replaceCardLog.setCreateTime(CssUtil.getNowDateTimeStrWithoutT());
		if (appReq.getSupReason() == 1) {
			replaceCardLog.setHandleType(1);
		} else {
			replaceCardLog.setHandleType(0);
		}
		replaceCardLog.setNewCardId(appReq.getNewCardId());
		replaceCardLog.setOldCardId(appReq.getCardId());
		if (appReq.getSupplyOrReplace()) {
			replaceCardLog.setServiceType("REPLACE");
		} else {
			replaceCardLog.setServiceType("SUPPLY");
		}

		replaceCardLog.setStaffId(user.getStaff().getStaffId());

		replaceCardLogRepo.save(replaceCardLog);
	}
	
	private void lmkCheck(String cardId) throws ManagerException {
		List<LmkStorageCard> checkList = lmkStorageCardRepo.listByCardIdCheck(cardId);
		if(checkList != null && checkList.size() > 0) {
			throw new ManagerException("卡号：" + cardId + "是昆仑卡，不能在PC端发行！");
		}
	};
}
