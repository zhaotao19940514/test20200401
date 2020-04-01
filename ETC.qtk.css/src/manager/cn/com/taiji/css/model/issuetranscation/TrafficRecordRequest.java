package cn.com.taiji.css.model.issuetranscation;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.TrafficRecordDetail;

public class TrafficRecordRequest extends JpaPageableDataRequest<TrafficRecordDetail> {
	private String cardId;
	private String vehiclePlate;
	private String enTime;
	private String exTime;

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "TrafficRecordDetail " +
				"  where serialType !='INPROVINCE' and serialType !='INPARK'");		
		if(cardId!=null&&!cardId.isEmpty())hql.append(" and cardId =:cardId", cardId);
		if(vehiclePlate!=null&&!vehiclePlate.isEmpty())hql.append(" and vehiclePlate =:vehiclePlate", vehiclePlate);
        if(enTime!=null&&!enTime.isEmpty())	hql.append(" and exTime >=:enTime", enTime);
        if(exTime!=null&&!exTime.isEmpty())	hql.append(" and exTime <=:exTime", exTime);
		return hql;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public String getEnTime() {
		return enTime;
	}

	public void setEnTime(String enTime) {
		this.enTime = enTime;
	}

	public String getExTime() {
		return exTime;
	}

	public void setExTime(String exTime) {
		this.exTime = exTime;
	}

}
