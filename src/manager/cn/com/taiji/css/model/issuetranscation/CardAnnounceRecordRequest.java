package cn.com.taiji.css.model.issuetranscation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CardAnnouncRecordView;

public class CardAnnounceRecordRequest extends JpaDateTimePageableDataRequest<CardAnnouncRecordView> {

	private String cardId;
	private String vehiclePlate;
	private String beforeDate;
	private String afterDate;
	
	public CardAnnounceRecordRequest() {
		this.orderBy="exTime";
		this.desc=true;
	}
	
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CardAnnouncRecordView " +
				"  where 1=1 ");
		hql.append(" and cardId =:cardId", cardId);
		hql.append(" and vehiclePlate =:vehiclePlate", vehiclePlate);
		LocalDateTime[] times = toDateTimes();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if(times[0]!=null)
		{
			hql.append(" and enTime>=:createtimeMin",
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
	
	

	public String getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(String beforeDate) {
		this.beforeDate = beforeDate;
	}

	public String getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	public void validate() {
		MyViolationException mve = new MyViolationException();		
		if(cardId == null&&null==vehiclePlate&&null==vehiclePlate) mve.addViolation("cardId", "请填写卡号");
		if(beforeDate == null&&null!=afterDate) mve.addViolation("startTime", "请填写起始时间");
		if(beforeDate != null&&null==afterDate) mve.addViolation("endTime", "请填写结束时间");
		if (mve.hasViolation()) throw mve;
//		if(null!=beforeDate&&null!=afterDate) {
//			beforeDate=beforeDate+" 01:00:00";
//			afterDate=afterDate+" 23:59:59";
//		}
	}


	







}
