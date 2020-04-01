/**
 * @Title AgencyRequest.java
 * @Package cn.com.taiji.css.model.administration.agency
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月28日 下午2:57:00
 * @version V1.0
 */
package cn.com.taiji.css.model.request.agency;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.Agency;

/**
 * @ClassName AgencyRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月28日 14:57:00
 * @E_mail yaonanlin@163.com
 */
public class AgencyRequest extends JpaPageableDataRequest<Agency> {
	// 发行方编号
	private String queryIssuerId;
	// 客服合作机构编号
	private String queryAgencyId;
	// 客服资金机构编号
	private String accountId;
	// 机构名称
	private String queryName;
	// 联系人姓名
	private String queryContact;
	// 联系电话
	private String queryTel;
	
	public AgencyRequest() {
		super();
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from Agency where 1=1 ");
		hql.append(" and issuerId = :queryIssuerId",queryIssuerId);
		hql.append(" and agencyId = :queryAgencyId",queryAgencyId);
		hql.append(" and name like :queryName",like(queryName));
		hql.append(" and contact = :queryContact",queryContact);
		hql.append(" and tel = :queryTel",queryTel);
		hql.append(" order by createTime,agencyId desc");
		return hql;
	}
	public String getQueryIssuerId() {
		return queryIssuerId;
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
	public String getQueryTel() {
		return queryTel;
	}
	public void setQueryIssuerId(String queryIssuerId) {
		this.queryIssuerId = queryIssuerId;
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
	public void setQueryTel(String queryTel) {
		this.queryTel = queryTel;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}

