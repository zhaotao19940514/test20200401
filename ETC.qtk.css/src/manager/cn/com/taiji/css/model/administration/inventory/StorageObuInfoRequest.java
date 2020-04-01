package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.StorageObuInfo;

public class StorageObuInfoRequest extends JpaDateTimePageableDataRequest<StorageObuInfo>{
	private String obuId;
	private Integer status;
	private String agencyId;
	private String startObuId;
	private String endObuId;
	private String serviceHallId;
	private String serviceHallName;
	private String batchId;
	
	public StorageObuInfoRequest() {
		super();
		this.orderBy = "obuId";
		this.desc = true;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getObuId() {
		return obuId;
	}

	public void setObuId(String obuId) {
		this.obuId = obuId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartObuId() {
		return startObuId;
	}

	public void setStartObuId(String startObuId) {
		this.startObuId = startObuId;
	}

	public String getEndObuId() {
		return endObuId;
	}

	public void setEndObuId(String endObuId) {
		this.endObuId = endObuId;
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
		HqlBuilder hql =null;
		hql = new HqlBuilder("from " + "StorageObuInfo " + "where 1=1 ");
		if(serviceHallName != null) {
			serviceHallName = "%" + serviceHallName + "%";
		}
		if(agencyId!=null && !agencyId.equals("52010106004")) {
			if(startObuId != null && endObuId == null) {
				hql.append(" and obuId =:obuId ", startObuId);
			}
			if(startObuId == null && endObuId != null) {
				hql.append(" and obuId =:obuId ", endObuId);
			}
			if(startObuId != null && endObuId != null) {
				hql.append(" and obuId >=:startObuId ", startObuId);
				hql.append(" and obuId <=:endObuId ", endObuId);
			}
			hql.append(" and status =:status ", status);
			hql.append(" and substr(servicehallId,1,11) =:agencyId ", agencyId);
			hql.append(" and serviceHallId =:serviceHallId ", serviceHallId);
			hql.append(" and serviceHallName like :serviceHallName ", serviceHallName);
			hql.append(" and batchId =:batchId ", batchId);
//			hql.append(" and status != 1");
		}else {
			if(startObuId != null && endObuId == null) {
				hql.append(" and obuId =:obuId ", startObuId);
			}
			if(startObuId == null && endObuId != null) {
				hql.append(" and obuId =:obuId ", endObuId);
			}
			if(startObuId != null && endObuId != null) {
				hql.append(" and obuId >=:startObuId ", startObuId);
				hql.append(" and obuId <=:endObuId ", endObuId);
			}
			hql.append(" and status =:status ", status);
			hql.append(" and serviceHallId =:serviceHallId ", serviceHallId);
			hql.append(" and serviceHallName like :serviceHallName ", serviceHallName);
			hql.append(" and batchId =:batchId ", batchId);
		}
		return hql;
	}

}
