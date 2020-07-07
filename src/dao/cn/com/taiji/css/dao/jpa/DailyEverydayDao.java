package cn.com.taiji.css.dao.jpa;

import java.util.List;

import cn.com.taiji.common.dao.jpa.AbstractJpaDao;
import cn.com.taiji.qtk.entity.CardInfo;

public interface DailyEverydayDao extends AbstractJpaDao<CardInfo>{
	List<Object[]> openCard(String timeDate);
	List<Object[]> openOBU(String timeDate);
	List<Object[]> amountDeposited(String timeDate);
	List<Object[]> halfAmountDeposited(String timeDate);
	List<Object[]> accountsPrepaidPhone(String timeDate);
	List<Object[]> accountConsumption(String timeDate);
	List<Object[]> accountReversal(String timeDate);
	List<Object[]> impact(String timeDate);
	List<Object[]> refund(String timeDate);
	List<Object[]> payAmount(String timeDate);
}
