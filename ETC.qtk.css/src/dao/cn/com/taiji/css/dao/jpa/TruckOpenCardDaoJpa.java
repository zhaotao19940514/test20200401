package cn.com.taiji.css.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.com.taiji.css.model.customerservice.report.QueryTimes;
import cn.com.taiji.qtk.entity.CardInfo;

@Repository
public class TruckOpenCardDaoJpa  extends MyBaseDao<CardInfo> implements TruckOpenCardDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCardByBank(QueryTimes time) {
		String sql = "select a.name,count(*)  " + 
				"from qtk_cardinfo card, qtk_vehicleinfo ve,qtk_agency a  " + 
				"where card.vehicle_id = ve.vehicle_id   " + 
				"and card.agency_id=a.agency_id  " + 
				"and card.card_enabletime between ?1 and ?2  " + 
				"and ve.type in (  " + 
				"'11','12','13','14','15'  " + 
				")   " + 
				"and card.card_status not in(0,4,5,9)  " + 
				"and card.agency_id in(  " + 
				"'52010102007','52010102002','52010102005','52010102018','52010188110','52010188029','52010102022','52010102042','52010102047','52010102911','52010102912','52010102027','52010102024','52010188054','52010102914','52010188111'  " + 
				")group by a.name ";
		
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, time.getStartTime());
		query.setParameter(2, time.getEndTime());
		return query.getResultList();
	}

}
