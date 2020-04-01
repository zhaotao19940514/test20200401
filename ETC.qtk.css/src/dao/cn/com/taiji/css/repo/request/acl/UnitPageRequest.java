package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.css.entity.Unit;

public class UnitPageRequest extends JpaPageableDataRequest<Unit>{

	private String name;//单位名称
	private Integer unitLevel;//级别
	private String likeCode;
	
	
	public UnitPageRequest() {
		super();
		this.orderBy="globalList";
		this.pageSize=100;
	}

	public String getLikeCode() {
		return likeCode;
	}

	public void setLikeCode(String likeCode) {
		this.likeCode = likeCode;
	}

	public String getName() {
		return name;
	}

	public Integer getUnitLevel() {
		return unitLevel;
	}

	public void  setName(String name) {
		this.name=name;
	}

	public void  setUnitLevel(Integer unitLevel) {
		this.unitLevel=unitLevel;
	}

	@Override
	public HqlBuilder toSelectHql(){
		HqlBuilder hql = new HqlBuilder("from Unit where 1=1 ");
		hql.append(" and name=:name", name);
		hql.append(" and unitLevel=:unitLevel", unitLevel);
		hql.append(" and code like :code",rightLike(likeCode));
		return hql;
	}

}
