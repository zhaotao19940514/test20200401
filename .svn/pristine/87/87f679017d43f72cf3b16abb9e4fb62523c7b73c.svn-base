package cn.com.taiji.css.manager.administration.deposit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.administration.deposit.SupplyPaymentRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardBlackListUploadRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardBlackListUploadResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CollateCardBalance;
import cn.com.taiji.qtk.entity.SupplyPaymentLog;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CollateCardBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.SupplyPaymentLogRepo;

/**
 * @ClassName ManCancelManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年12月18日
 */
@Service
public class SupplyPaymentManagerImpl extends AbstractDsiCommManager implements SupplyPaymentManager{

	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private CollateCardBalanceRepo collateCardBalanceRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;
	@Autowired
	private SupplyPaymentLogRepo supplyPaymentLogRepo;

	@Override
	public LargePagination<CardInfo> queryPage(SupplyPaymentRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		
		if(null!=queryModel.getVehicleId()) {
			CardInfo cardInfo = cardInfoRepo.findByVehicleId(queryModel.getVehicleId());
			if(null==cardInfo) {
				throw new ManagerException("未查询到该ETC卡信息");
			}
			queryModel.setCardId(cardInfo.getCardId());
		}
		LargePagination<CardInfo> large = cardInfoRepo.largePage(queryModel);
		List<CardInfo> list = large.getResult();
		List<CardInfo> cardList = Lists.newArrayList();
		for(CardInfo li:list) {
			List<CollateCardBalance> collList= collateCardBalanceRepo.findByCardId(li.getCardId());
			if(collList.size()!=0) {
				CardInfo cardInfo = li;
				cardInfo.setAccountId(String.valueOf(collList.get(0).getBalance()));
				cardInfo.setPackageId(collList.get(0).getUpdateTime());
				cardInfo.setStatus(collList.get(0).getBlackStatus());
				cardList.add(cardInfo);
			}else {
				throw new ManagerException("该卡无保证金套餐");
			}
		}
		large.setResult(cardList);
		return large;
		
	}

	@Override
	public CollateCardBalance findbyCollateCardBalance(String cardId) {
		
		return collateCardBalanceRepo.findByCardId(cardId).get(0);
	}

	@Override
	public AppAjaxResponse doSubmit(SupplyPaymentRequest queryModel,User user) {
		AppAjaxResponse appAjaxRes = new AppAjaxResponse();
		CardBlackListUploadResponse res = returnWhite(queryModel,user);
		if(res.getStatus()==1) {
			try{
				updateBalance(queryModel);
				fundSerialDetaiManager.saveFundSerial(user, ServiceType.SUPPLYPAY, ChargeType.COMMONPOS,0,queryModel.getPayBalance(),queryModel.getCardId(),null,null);
			} catch (Exception e) {
				appAjaxRes.setStatus(0);
				appAjaxRes.setMessage(e.getLocalizedMessage());
				e.printStackTrace();
			}
			
		}else {
			appAjaxRes.setStatus(0);
			appAjaxRes.setMessage(res.getMessage());
		}
		return appAjaxRes;
	}
	private CardBlackListUploadResponse returnWhite(SupplyPaymentRequest queryModel,User user) {
		CardBlackListUploadResponse res = new CardBlackListUploadResponse();
		CardBlackListUploadRequest req = new CardBlackListUploadRequest();
		req.setCardId(queryModel.getCardId());
		req.setStatus(2);
		req.setType(4);
		super.commSet(req, user);
		
		try {
			res = maintenanceBinService.CardBlackListUpload(req);
			saveLog(queryModel, user,res.getMessage());
		} catch (Exception e) {
			saveLog(queryModel, user,e.getLocalizedMessage());
			e.printStackTrace();
		}
		return res;
	}

	private SupplyPaymentLog saveLog(SupplyPaymentRequest queryModel, User user,String message) {
		SupplyPaymentLog log = new SupplyPaymentLog();
		log.setCardId(queryModel.getCardId());
		log.setChannelId(user.getStaff().getServiceHall().getServiceHallId());
		log.setCreateTime(CssUtil.getNowDateTimeStrWithoutT());
		log.setStaffId(user.getStaff().getStaffId());
		log.setMessage(message);
		supplyPaymentLogRepo.save(log);
		return log;
	}
	
	private void updateBalance(SupplyPaymentRequest queryModel) {
		CollateCardBalance entity = collateCardBalanceRepo.findByCardId(queryModel.getCardId()).get(0);
		entity.setBalance(entity.getBalance()+queryModel.getPayBalance());
		entity.setPayBalance(entity.getPayBalance()-queryModel.getPayBalance());
		entity.setBlackStatus(2);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		entity.setUpdateTime(format.format(now));
		collateCardBalanceRepo.save(entity);
	}
	
}

