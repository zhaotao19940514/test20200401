/**
 * @Title StaffRequest.java
 * @Package cn.com.taiji.css.model.request.staff
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月30日 上午11:21:47
 * @version V1.0
 */
package cn.com.taiji.css.model.request.staff;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.Staff;

/**
 * @ClassName StaffRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月30日 11:21:47
 * @E_mail yaonanlin@163.com
 */
public class StaffRequest extends JpaPageableDataRequest<Staff> {
	// 网点编号
	private String queryServiceHallId;
	// 员工编号
	private String queryStaffId;
	// 员工姓名
	private String queryStaffName;

	public StaffRequest() {
		super();
		orderBy = "staffId";
		desc = false;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from Staff where 1=1 ");
		hql.append(" and serviceHallId = :queryServiceHallId", queryServiceHallId);
		hql.append(" and staffId = :queryStaffId", queryStaffId);
		hql.append(" and staffName like :queryStaffName", like(queryStaffName));
		return hql;
	}

	public String getQueryServiceHallId() {
		return queryServiceHallId;
	}

	public String getQueryStaffId() {
		return queryStaffId;
	}

	public String getQueryStaffName() {
		return queryStaffName;
	}

	public void setQueryServiceHallId(String queryServiceHallId) {
		this.queryServiceHallId = queryServiceHallId;
	}

	public void setQueryStaffId(String queryStaffId) {
		this.queryStaffId = queryStaffId;
	}

	public void setQueryStaffName(String queryStaffName) {
		this.queryStaffName = queryStaffName;
	}
}
