/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.finance;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.issuetranscation.CardAnnounceRecordModel;
import cn.com.taiji.css.model.customerservice.finance.BalanceReckonModel;
import cn.com.taiji.css.model.customerservice.finance.BalanceReckonRequest;
import cn.com.taiji.qtk.entity.AccountCardBalance;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.TrafficRecordDetailNewRepo;
/**
 * 
 *@ClassName BalanceReckonManagerImpl.java
 *@Description: 
 *@author:zhaot
 *@date: 2020年7月7日
 */
@Service
public class BalanceReckonManagerImpl extends AbstractDsiCommManager implements BalanceReckonManager{
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private AccountCardBalanceRepo accountCardBalanceRepo;
	@Autowired
	private TrafficRecordDetailNewRepo traNewRepo;
	@Autowired
	private ChargeDetailRepo chargeRepo;
	
	
	@Override
	public LargePagination<CardInfo> queryPage(BalanceReckonRequest queryModel, User user) throws ManagerException {
		
		return cardInfoRepo.largePage(queryModel);
	}
	@Override
	public BalanceReckonModel balanceReckon(String cardId) {
		BalanceReckonModel model = new BalanceReckonModel();
		List<CardAnnounceRecordModel> modelList =Lists.newArrayList();	
		//华软初始卡账
		AccountCardBalance accountBalance = accountCardBalanceRepo.findAccountCardBalanceByCardId(cardId);
		model.setAccountBalance(accountBalance.getInitAmount()==null?0L:accountBalance.getInitAmount());
		List<Object[]> list = traNewRepo.listConsumeDetailByCardId(cardId);
		objectList(list, modelList);
		//去重
		modelList = modelList.stream().filter(distinctByKey(o -> ((CardAnnounceRecordModel)o).getCompareTime())).collect(Collectors.toList());
		Long trafficBalance  = modelList.stream().mapToLong(CardAnnounceRecordModel::getFee).sum();
		model.setTrafficBalance(trafficBalance==null?0L:trafficBalance);
		
		List<ChargeDetail> chargeList = chargeRepo.findListAfterTradeTime(cardId, "20181228233658");
		Long chargeBalance  = chargeList.stream().mapToLong(ChargeDetail::getRechargeAmount).sum();
		model.setChargeBalance(chargeBalance==null?0L:chargeBalance);
		model.setReckonBalnace(model.getAccountBalance()+model.getChargeBalance()-model.getTrafficBalance());
		return model;
	}
	private void objectList(List<Object[]> objList, List<CardAnnounceRecordModel> modelList) {
		int len = objList.size();
		for(int i=0;i<len;i++) {
			CardAnnounceRecordModel entity = new CardAnnounceRecordModel();
			entity.setCardId(objList.get(i)[0].toString());
			if(null!=objList.get(i)[1]) {
				entity.setVehiclePlate(objList.get(i)[1].toString());
			}
			if(null!=objList.get(i)[2]) {
				entity.setConsumptionType(objList.get(i)[2].toString());
			}
			if(null!=objList.get(i)[3]) {
				entity.setEnTime(objList.get(i)[3].toString());
			}
			if(null!=objList.get(i)[4]) {
				entity.setEnName(objList.get(i)[4].toString());
			}
			if(null!=objList.get(i)[5]) {
				entity.setExTime(objList.get(i)[5].toString());
			}
			if(null!=objList.get(i)[6]) {
				entity.setExName(objList.get(i)[6].toString());
			}
			if(null!=objList.get(i)[7]) {
				entity.setPreBalance(Long.parseLong(objList.get(i)[7].toString()));
			}
			if(null!=objList.get(i)[8]) {
				entity.setFee(Long.parseLong(objList.get(i)[8].toString()));
			}
			if(null!=objList.get(i)[9]) {
				entity.setPostBalance(Long.parseLong(objList.get(i)[9].toString()));
			}
			if(null!=objList.get(i)[10]) {
				entity.setPassId(objList.get(i)[10].toString());
			}
			if(null!=objList.get(i)[11]) {
				entity.setListNo(objList.get(i)[11].toString());
			}
			entity.setCompareTime(rexCompile(entity.getExTime()+entity.getEnTime()+entity.getFee()));
			modelList.add(entity);
		}
	}
	private String rexCompile(String time) {
    	// 数字匹配
    	Matcher matcher = Pattern.compile("\\d+").matcher(time);
    	String mathTime ="";
    	while (matcher.find()) {
    		mathTime+=matcher.group(); // 打印出：11
    	}
		return mathTime;
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object>
	  keyExtractor) { 
		  Map<Object, Boolean> map = new ConcurrentHashMap<>(); 
		  return
		  t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	  }
}

