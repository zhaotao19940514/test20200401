package cn.com.taiji.css.model.issuetranscation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.IssueTransactionDetail;

public class IssueTranscationRequest extends JpaDateTimePageableDataRequest<IssueTransactionDetail>  {

	private String cardId;
	private String vehiclePlate;
	
	public IssueTranscationRequest() {
		this.orderBy="tradeTime";
		this.desc=true;
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

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "IssueTransactionDetail " +
				"  where 1=1 ");
		hql.append(" and cardId =:cardId", cardId);
		hql.append(" and vehiclePlate =:vehiclePlate", vehiclePlate);
		LocalDateTime[] times = toDateTimes();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		if(times[0]!=null)
		{
			hql.append(" and handleDate>=:createtimeMin",
					(times[0].format(formatter)));
		}
		if(times[1]!=null)
		{
			hql.append(" and handleDate<=:createtimeMax",
					(times[1].format(formatter)));
		}
		return hql;
	}
		

}
