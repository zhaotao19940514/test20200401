package cn.com.taiji.css.repo.request.obublacklist;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.ObuBlackList;

public class ObuBLackListQueryRequest extends JpaPageableDataRequest<ObuBlackList>{

	private String obuId;
	
	
	public String getObuId() {
		return obuId;
	}


	public void setObuId(String obuId) {
		this.obuId = obuId;
	}


	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + ObuBlackList.class.getName() + " a where 1=1 ");
		hql.append(" and obuId =:obuId", obuId);
		return hql;
	}

}
