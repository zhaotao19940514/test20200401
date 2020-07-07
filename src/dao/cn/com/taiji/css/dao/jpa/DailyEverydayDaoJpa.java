package cn.com.taiji.css.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.com.taiji.qtk.entity.CardInfo;

@Repository
public class DailyEverydayDaoJpa extends MyBaseDao<CardInfo> implements DailyEverydayDao{
	//开卡
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> openCard(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT S.NAME,count(c.card_id) FROM QTK_CARDINFO C,QTK_SERVICEHALL S " + 
				" WHERE C.CHANNEL_ID=S.SERVICEHALL_ID " + 
				" AND substr(C.CARD_ENABLETIME,0,10)= ?1 " + 
				" and C.AGENCY_ID='52010106004' " + 
				" group by S.NAME " + 
				" order by S.NAME ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//OBU
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> openOBU(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT S.NAME,count(o.obu_id) FROM qtk_obuinfo o,QTK_SERVICEHALL S " + 
				" WHERE o.REGISTERED_CHANNEL_ID=S.SERVICEHALL_ID " + 
				" AND substr(o.ENABLE_TIME,0,10)= ?1 " + 
				" and substr(o.REGISTERED_CHANNEL_ID,0,11)='52010106004'  " + 
				" group by S.NAME " + 
				" order by S.NAME ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//网点圈存金额（不带半条，不带账户消费圈存）
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> amountDeposited(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT s.NAME,sum( c.RECHARGE_AMOUNT ) / 100 FROM " + 
				" qtk_charge_detail c, QTK_SERVICEHALL s WHERE" + 
				" c.STATUS = 1 AND c.TRADE_TYPE IS NOT NULL " + 
				" AND c.trade_type != 3 " + 
				" AND c.trade_type != 4 " + 
				" AND substr( c.CHARGE_ID, 2, 19 ) = s.SERVICEHALL_ID " + 
				" and c.TRADE_DATE= ?1 " + 
				" and c.AGENCYID='52010106004' " + 
				" GROUP BY s.NAME ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//网点圈存金额（半条流水，不带账户消费圈存）
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> halfAmountDeposited(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT s.NAME,sum( c.RECHARGE_AMOUNT ) / 100 FROM " + 
				" qtk_charge_detail c, QTK_SERVICEHALL s " + 
				" WHERE " + 
				" c.STATUS = 0 AND c.TRADE_TYPE IS NOT NULL " + 
				" AND c.trade_type != 3 " + 
				" AND c.trade_type != 4 " + 
				" AND substr( c.CHARGE_ID, 2, 19 ) = s.SERVICEHALL_ID " + 
				" and c.TRADE_DATE= ?1 " +
				" and c.AGENCYID='52010106004' " + 
				" GROUP BY s.NAME ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//账户充值
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> accountsPrepaidPhone(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT s.NAME,sum( a.TRADE_FEE ) / 100 FROM " + 
				" qtk_account_detail a,QTK_SERVICEHALL s " + 
				" WHERE " + 
				" a.CHANNEL_ID = s.SERVICEHALL_ID " + 
				" AND to_char ( a.create_time, 'yyyymmdd' ) = ?1 " + 
				" AND trade_type = 'ACCOUNT_CHARGE' " + 
				" and substr(a.CHANNEL_ID,0,11)='52010106004' " + 
				" GROUP BY s.NAME ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//账户消费 
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> accountConsumption(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT s.NAME, sum( a.TRADE_FEE ) / 100 FROM " + 
				" qtk_account_detail a,QTK_SERVICEHALL s " + 
				" WHERE " + 
				" a.CHANNEL_ID = s.SERVICEHALL_ID " + 
				" AND to_char ( a.create_time, 'yyyymmdd' ) = ?1 " + 
				" AND trade_type = 'ACCOUNT_CONSUME' " + 
				" and substr(a.CHANNEL_ID,0,11)='52010106004' " + 
				" GROUP BY s.NAME ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//账户冲正
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> accountReversal(String timeDate) {
		Query query = entityManager.createNativeQuery("SELECT s.NAME,sum( a.TRADE_FEE ) / 100  FROM " + 
				" qtk_account_detail a, QTK_SERVICEHALL s " + 
				" WHERE " + 
				" a.CHANNEL_ID = s.SERVICEHALL_ID " + 
				" AND to_char ( a.create_time, 'yyyymmdd' ) = ?1 " + 
				" AND trade_type = 'ACCOUNT_REVERSAL' " + 
				" and substr(a.CHANNEL_ID,0,11)='52010106004' " + 
				" GROUP BY s.NAME");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//账户圈存交易后进行圈存冲正后账户金额回退金额
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> impact(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT s.NAME,sum( a.TRADE_FEE ) / 100 FROM " + 
				" qtk_account_detail a,QTK_SERVICEHALL s  " + 
				" WHERE " + 
				" a.CHANNEL_ID = s.SERVICEHALL_ID " + 
				" AND to_char ( a.create_time, 'yyyymmdd' ) = ?1 " + 
				" AND trade_type = 'ACCOUNT_SUPPLYCHARGE' " + 
				" and substr(a.CHANNEL_ID,0,11)='52010106004' " + 
				" GROUP BY s.NAME ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//退费
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> refund(String timeDate) {
		Query query = entityManager.createNativeQuery(" select s.name,sum(fun.handle_money)/100 " + 
				" from qtk_fund_serial_detail fun, qtk_servicehall s " + 
				" where fun.servicehall_id = s.servicehall_id " + 
				" and fun.agency_id = '52010106004' " + 
				" and fun.handle_date = ?1 " + 
				" and fun.service_type = 'CANCELREFUND' " + 
				" group by s.name");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}
	//补交金额
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> payAmount(String timeDate) {
		Query query = entityManager.createNativeQuery(" SELECT ser.name ,payment.CREATE_DATE ,count(1),sum(fee)/100 " + 
				" from QTK_PAYMENTBACK payment join QTK_SERVICEHALL ser on ser.SERVICEHALL_ID=payment.SERVICEHALL_ID " + 
				" where payment.CREATE_DATE = ?1 and ser.AGENCY_ID='52010106004' GROUP BY ser.name,payment.CREATE_DATE ");
		query.setParameter(1, timeDate);
		return query.getResultList();
	}

}
