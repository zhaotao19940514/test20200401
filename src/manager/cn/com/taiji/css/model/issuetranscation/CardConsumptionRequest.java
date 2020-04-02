package cn.com.taiji.css.model.issuetranscation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.TrafficRecordDetail;

public class CardConsumptionRequest extends JpaDateTimePageableDataRequest<TrafficRecordDetail> {

	private String cardId;
	private String vehiclePlate;
     
	public CardConsumptionRequest() {
		this.orderBy="exTime";
		this.desc =true;
	}
	
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "TrafficRecordDetail " +
				"  where 1=1 ");
		hql.append(" and cardId =:cardId", cardId);
		hql.append(" and vehiclePlate =:vehiclePlate", vehiclePlate);
		LocalDateTime[] times = toDateTimes();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if(times[0]!=null)
		{
			hql.append(" and exTime>=:createtimeMin",
					(times[0].format(formatter)));
		}
		if(times[1]!=null)
		{
			hql.append(" and exTime<=:createtimeMax",
					(times[1].format(formatter)));
		}
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



}
