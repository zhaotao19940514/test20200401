/**
 * @Title ServiceHallRequest.java
 * @Package cn.com.taiji.css.model.serviceHall.request
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月29日 上午11:19:27
 * @version V1.0
 */
package cn.com.taiji.css.model.request.serviceHall;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.ServiceHall;

/**
 * @ClassName ServiceHallRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月29日 11:19:27
 * @E_mail yaonanlin@163.com
 */
public class ServiceHallRequest extends JpaPageableDataRequest<ServiceHall> {
	// 客服合作机构编号
	private String queryAgencyId;
	// 服务网点名称
	private String queryName;
	// 联系人姓名
	private String queryContact;
	private String queryServiceHallId;

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from ServiceHall where 1=1 ");
		hql.append(" and agencyId = :queryAgencyId", queryAgencyId);
		hql.append(" and name like :queryName", like(queryName));
		hql.append(" and contact = :queryContact", queryContact);
		hql.append(" and serviceHallId = :queryServiceHallId", queryServiceHallId);
		hql.append(" order by createTime desc");
		return hql;
	}

	public String getQueryAgencyId() {
		return queryAgencyId;
	}

	public String getQueryName() {
		return queryName;
	}

	public String getQueryContact() {
		return queryContact;
	}

	public String getQueryServiceHallId() {
		return queryServiceHallId;
	}

	public void setQueryAgencyId(String queryAgencyId) {
		this.queryAgencyId = queryAgencyId;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public void setQueryContact(String queryContact) {
		this.queryContact = queryContact;
	}

	public void setQueryServiceHallId(String queryServiceHallId) {
		this.queryServiceHallId = queryServiceHallId;
	}
}
