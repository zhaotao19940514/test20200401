package cn.com.taiji.css.model.administration.refund;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.DuplicateRefund;


public class DuplicateRefundPageRequest extends JpaPageableDataRequest<DuplicateRefund>{

	private String transientListno ;
	private String cardId;
	private String vehiclePlate;
	private Integer status;
	
	public String getTransientListno() {
		return transientListno;
	}


	public void setTransientListno(String transientListno) {
		this.transientListno = transientListno;
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


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	



	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + DuplicateRefund.class.getName() + " a where 1=1 ");
		hql.append(" and vehiclePlate like :vehiclePlate", like(vehiclePlate));
		hql.append(" and cardId=:cardId", cardId);
		hql.append(" and transientListno=:transientListno",transientListno);
		hql.append(" and status=:status",status);
		return hql;
	}
	

}
