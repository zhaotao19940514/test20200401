package cn.com.taiji.css.model.serviceHall;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.qtk.entity.ServiceHall;

public class ServiceHallListRequest extends JpaSortDataRequest<ServiceHall> {

	private String name;
	private String unitLikeCode;

	public ServiceHallListRequest(String name, String unitLikeCode) {
		super();
		this.name = name;
		this.unitLikeCode = unitLikeCode;
	}

	public ServiceHallListRequest() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitLikeCode() {
		return unitLikeCode;
	}

	public void setUnitLikeCode(String unitLikeCode) {
		this.unitLikeCode = unitLikeCode;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + ServiceHall.class.getName() + " a where 1=1 ");
		hql.append(" and name like :name", like(name));
		// hql.append(" and exists(select b from Unit b where b.id=a.unit.id and b.code
		// like :code)",rightLike(unitLikeCode));
		return hql;
	}

}
