package cn.com.taiji.css.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.com.taiji.qtk.entity.CancelledCardDetail;

@Repository
public class CancelReportDaoJpa extends MyBaseDao<CancelledCardDetail> implements CancelReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCancelDetailByAgencyIdAndStartTimeAndEndTime(String agencyId, String startTime,
			String endTime, String vehicleIsGui) {
		String sql = "SELECT cus.customer_name,can.vehicle_id,can.STAFF_ID,can.CREATE_TIME,ser.name "
				+ "from QTK_CUSTOMERINFO cus"
				+ " join QTK_CANCELLEDCARD_DETAIL can on can.user_idnum=cus.customer_idnum "
				+ "join QTK_SERVICEHALL ser on can.channel_id=ser.SERVICEHALL_ID "
				+ "where can.agency_id = ?1 "
				+ "and can.CARD_TYPE='1' "
				+ "and can.create_time between to_date( ?2 ,'yyyy-MM-dd HH24:mi:ss') "
				+ "and to_date( ?3 ,'yyyy-MM-dd HH24:mi:ss') ";
		if("1".equals(vehicleIsGui)) {
			sql = sql +"and SUBSTR(can.vehicle_id, 0, 1) = '贵' ";
		}
		sql = sql + "ORDER BY can.create_time";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, agencyId);
		query.setParameter(2, startTime);
		query.setParameter(3, endTime);
		return query.getResultList();
	}
}
