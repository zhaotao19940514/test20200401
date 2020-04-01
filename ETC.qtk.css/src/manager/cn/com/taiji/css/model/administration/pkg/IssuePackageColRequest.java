package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.qtk.entity.IssupePackageServiceHall;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.dict.AgencyName;

public class IssuePackageColRequest extends JpaSortDataRequest<ServiceHall> {

//	private String issuePackageId;
	private String agencyCode;

	public IssuePackageColRequest() {
		this(null);
	}

	

	public IssuePackageColRequest(String agencyCode) {
		super();
//		this.issuePackageId = issuePackageId;
		this.agencyCode = agencyCode;
	}



//	public String getIssuePackageId() {
//		return issuePackageId;
//	}
//
//
//
//	public void setIssuePackageId(String issuePackageId) {
//		this.issuePackageId = issuePackageId;
//	}

	
	

	

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from ServiceHall where 1=1");
		hql.append(" and agencyCode=:agencyCode", agencyCode);
//		hql.append(" and exists(select b from " + IssupePackageServiceHall.class.getName()
//				+ " b where b.serviceHallId=r and b.issuePackageId.id=:issuePackageId )", issuePackageId);
		return hql;
	}



	public String getAgencyCode() {
		return agencyCode;
	}



	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}



	



	

}
