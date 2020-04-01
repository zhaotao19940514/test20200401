package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.StorageCardInfo;

public class StorageCardInfoRequest extends JpaDateTimePageableDataRequest<StorageCardInfo>{
	private String cardId;
	private Integer status;
	private String agencyId;
	private String startCardId;
	private String endCardId;
	//归属网点id
	private String serviceHallId;
	private String serviceHallName;
	//批次号
	private String batchId;
	
	public StorageCardInfoRequest() {
		super();
		this.orderBy = "cardId";
		this.desc = true;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getStartCardId() {
		return startCardId;
	}

	public void setStartCardId(String startCardId) {
		this.startCardId = startCardId;
	}

	public String getEndCardId() {
		return endCardId;
	}

	public void setEndCardId(String endCardId) {
		this.endCardId = endCardId;
	}

	public String getServiceHallId() {
		return serviceHallId;
	}

	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}

	public String getServiceHallName() {
		return serviceHallName;
	}

	public void setServiceHallName(String serviceHallName) {
		this.serviceHallName = serviceHallName;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "StorageCardInfo " + "  where 1=1 ");
		if(serviceHallName != null) {
			serviceHallName = "%" + serviceHallName + "%";
		}
		if(agencyId!=null && !agencyId.equals("52010106004")) {
			if(startCardId != null && endCardId == null) {
				hql.append(" and cardId =:cardId ", startCardId);
			}
			if(startCardId == null && endCardId != null) {
				hql.append(" and cardId =:cardId ", endCardId);
			}
			if(startCardId != null && endCardId != null) {
				hql.append(" and cardId >=:startCardId ", startCardId);
				hql.append(" and cardId <=:endCardId ", endCardId);
			}
			hql.append(" and status =:status ", status);
			hql.append(" and substr(serviceHallId,1,11) =:agencyId ", agencyId);
			hql.append(" and serviceHallId =:serviceHallId ", serviceHallId);
			hql.append(" and serviceHallName like :serviceHallName ", serviceHallName);
			hql.append(" and batchId =:batchId ", batchId);
//			hql.append(" and status != 1");
		}else {
			if(startCardId != null && endCardId == null) {
				hql.append(" and cardId =:cardId ", startCardId);
			}
			if(startCardId == null && endCardId != null) {
				hql.append(" and cardId =:cardId ", endCardId);
			}
			if(startCardId != null && endCardId != null) {
				hql.append(" and cardId >=:startCardId ", startCardId);
				hql.append(" and cardId <=:endCardId ", endCardId);
			}
			hql.append(" and status =:status ", status);
			hql.append(" and serviceHallId =:serviceHallId ", serviceHallId);
			hql.append(" and serviceHallName like :serviceHallName ", serviceHallName);
			hql.append(" and batchId =:batchId ", batchId);
		}
		System.out.println(hql);
		return hql;
	}

}
