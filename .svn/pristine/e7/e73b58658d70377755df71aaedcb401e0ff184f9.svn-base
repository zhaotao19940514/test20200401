package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.qtk.entity.ServiceHall;

public class DevicePackageColRequest extends JpaSortDataRequest<ServiceHall> {

//	private String issuePackageId;
//	private AgencyName agencyName;
	//客服合作机构编号
	private String agencyId;
	private String agencyCode;
	
	public DevicePackageColRequest() {
		super();
	}

	public DevicePackageColRequest(String agencyId) {
		super();
//		this.issuePackageId = issuePackageId;
//		this.agencyName = agencyName;
//		this.agencyCode = agencyCode;
		this.agencyId = agencyId;
	}

//	public String getIssuePackageId() {
//		return issuePackageId;
//	}
//	public void setIssuePackageId(String issuePackageId) {
//		this.issuePackageId = issuePackageId;
//	}

//	public AgencyName getAgencyName() {
//		return agencyName;
//	}
//	public void setAgencyName(AgencyName agencyName) {
//		this.agencyName = agencyName;
//	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}



	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from ServiceHall where 1=1");
		hql.append(" and agencyId=:agencyId", agencyId);
		hql.append(" order by nlssort(NAME, 'NLS_SORT=SCHINESE_PINYIN_M'),id");
//		hql.append(" and agencyCode=:agencyCode", agencyCode);
//		hql.append(" and exists(select b from " + IssupePackageServiceHall.class.getName()
//				+ " b where b.serviceHallId=r and b.issuePackageId.id=:issuePackageId )", issuePackageId);
		return hql;
	}
	
}
