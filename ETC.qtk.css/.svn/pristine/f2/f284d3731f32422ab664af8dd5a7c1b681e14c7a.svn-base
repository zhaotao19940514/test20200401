package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;

public class StorageCardInfoBatchRequest extends JpaDateTimePageableDataRequest<StorageCardInfoBatch> {
	private String serviceHallId;
	// 品牌
	private String searchBrand;
	// 型号
	private Integer type;
	// 入库单编号
	private String batchId;
	// 状态
	private String status;
	
	private String cardId;
	
	private String agencyId;
	

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getSearchBrand() {
		return searchBrand;
	}

	public void setSearchBrand(String searchBrand) {
		this.searchBrand = searchBrand;
	}

	public String getServiceHallId() {
		return serviceHallId;
	}

	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}

	public String getBatchId() {
		return batchId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "StorageCardInfoBatch " + "  where 1=1 ");
		if(agencyId!=null && !agencyId.equals("52010106004")) {
//			hql.append(" and serviceHallId=:serviceHallId", serviceHallId);
			hql.append(" and substr(serviceHallId,1,11) =:agencyId ", agencyId);
			hql.append(" and brand =:searchBrand ", searchBrand);
			hql.append(" and type =:type ", type);
			hql.append(" and status =:status ", status);
			hql.append(" and startId <=:startId ", cardId);
			hql.append(" and endId >=:endId ", cardId);
			hql.append("order by createTime desc,id");
		}else {
			hql.append(" and serviceHallId=:serviceHallId", serviceHallId);
			hql.append(" and brand =:searchBrand ", searchBrand);
			hql.append(" and type =:type ", type);
			hql.append(" and status =:status ", status);
			hql.append(" and startId <=:startId ", cardId);
			hql.append(" and endId >=:endId ", cardId);
			hql.append("order by createTime desc,id");
		}
		return hql;
	}
}
