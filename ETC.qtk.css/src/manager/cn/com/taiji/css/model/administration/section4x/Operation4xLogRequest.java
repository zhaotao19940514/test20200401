package cn.com.taiji.css.model.administration.section4x;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.Operation4xLog;
import cn.com.taiji.qtk.entity.dict.Operation4xType;

public class Operation4xLogRequest extends JpaPageableDataRequest<Operation4xLog> {

	private Operation4xType operation4xType;
	private String startTime;
	private String endTime;
	private String staffId;
	private String batchNo;
	private String cardId;


	public Operation4xType getOperation4xType() {
		return operation4xType;
	}

	public void setOperation4xType(Operation4xType operation4xType) {
		this.operation4xType = operation4xType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + Operation4xLog.class.getName() + " where 1=1 ");
		hql.append(" and operationTime >=:startTime ", startTime);
		hql.append(" and operationTime <=:endTime ", endTime);
		hql.append(" and operation4xType =:operation4xType ", operation4xType);
		hql.append(" and staffId =:staffId ", staffId);
		hql.append(" and batchNo =:batchNo ", batchNo);
		hql.append(" and to_number( startId ) <= to_number( :startId ) ", cardId);
		hql.append(" and to_number( endId ) >= to_number( :endId ) ", cardId);
		hql.append(" order by operationTime desc,id,operation4xType");
		logger.info(hql.toString());
		return hql;
	}
}
