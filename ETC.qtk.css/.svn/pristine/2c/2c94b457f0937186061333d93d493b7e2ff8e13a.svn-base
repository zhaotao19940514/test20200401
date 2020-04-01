package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.qtk.entity.IssupePackageServiceHall;
import cn.com.taiji.qtk.entity.ServiceHall;

public class IssuePackageListRequest extends JpaSortDataRequest<ServiceHall> {

	private String serviceHallId;
	private String issuePackageId;
  
	public IssuePackageListRequest()
	{
		this(null,null);
	}
	
     
	public IssuePackageListRequest(String serviceHallId, String issuePackageId) {
		super();
		this.serviceHallId = serviceHallId;
		this.issuePackageId = issuePackageId;
	}


	public String getServiceHallId() {
		return serviceHallId;
	}


	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}


	public String getIssuePackageId() {
	return issuePackageId;
	}


	public void setIssuePackageId(String issuePackageId) {
		this.issuePackageId = issuePackageId;
	}


	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + ServiceHall.class.getName() + " r where 1=1 ");
		hql.append(" and r.id=:id", serviceHallId);
		hql.append(" and exists(select b from " + IssupePackageServiceHall.class.getName()
				+ " b where b.serviceHallId=r and b.issuePackageId.id=:issuePackageId )", issuePackageId);
		return hql;	
	}

}
