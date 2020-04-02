package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;

public class StorageObuInfoBatchRequest extends JpaDateTimePageableDataRequest<StorageObuInfoBatch> {

	private String serviceHallId;
	// 品牌
	private String searchBrand;
	// 型号
	private String type;
	// 状态
	private String status;
	
	private String obuId;
	
	private String agencyId;
	
	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getServiceHallId() {
		return serviceHallId;
	}

	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}

	public String getSearchBrand() {
		return searchBrand;
	}

	public void setSearchBrand(String searchBrand) {
		this.searchBrand = searchBrand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObuId() {
		return obuId;
	}

	public void setObuId(String obuId) {
		this.obuId = obuId;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "StorageObuInfoBatch " + "  where 1=1 ");
		if(agencyId!=null && !agencyId.equals("52010106004")) {
//			hql.append(" and serviceHallId=:serviceHallId", serviceHallId);
			hql.append(" and substr(serviceHallId,1,11) =:agencyId ", agencyId);
			hql.append(" and brand =:searchBrand ", searchBrand);
			hql.append(" and type =:type ", type);
			hql.append(" and status =:status ", status);
			hql.append(" and startId <=:startId ", obuId);
			hql.append(" and endId >=:endId ", obuId);
			hql.append("order by createTime desc");	
		}else {
			hql.append(" and serviceHallId=:serviceHallId", serviceHallId);
			hql.append(" and brand =:searchBrand ", searchBrand);
			hql.append(" and type =:type ", type);
			hql.append(" and status =:status ", status);
			hql.append(" and startId <=:startId ", obuId);
			hql.append(" and endId >=:endId ", obuId);
			hql.append("order by createTime desc");	
		}
			return hql;
	}

}
