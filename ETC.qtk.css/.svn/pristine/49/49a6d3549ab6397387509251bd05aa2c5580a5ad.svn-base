/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import javax.validation.constraints.Size;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.User.UserStatus;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午3:01:54<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class UserPageRequest extends JpaPageableDataRequest<User>
{
	@Size(min = 3, max = 16, message = "{userName.error}")
	private String userName;
	private UserStatus status;
	private String unitName;
	private String unitLikeCode;
	private String staffId;
	
	public UserPageRequest()
	{
		this.orderBy = "namePy";
	}

	
	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public String getUnitLikeCode() {
		return unitLikeCode;
	}


	public void setUnitLikeCode(String unitLikeCode) {
		this.unitLikeCode = unitLikeCode;
	}


	public String getUserName()
	{
		return userName;
	}

	public UserStatus getStatus()
	{
		return status;
	}

	public UserPageRequest setUserName(String userName)
	{
		this.userName = userName;
		return this;
	}

	public UserPageRequest setStatus(UserStatus status)
	{
		this.status = status;
		return this;
	}

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + User.class.getName() + " a where 1=1 ");
		hql.append(" and (name like :name or namePy like :namePy  or loginName like :loginName)", like(userName));
		hql.append(" and status=:status", status);
		hql.append(" and staffId=:staffId", staffId);
		hql.append(" and unit.name like :unitName",like(unitName));
		hql.append(" and exists(select b from Unit b where b.id=a.unit.id and b.code like :code)",rightLike(unitLikeCode));
		return hql;
	}

}
