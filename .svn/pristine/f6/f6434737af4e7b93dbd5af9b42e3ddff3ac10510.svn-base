package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.CssCardInfo;

public class DeviceCardModelRequest extends JpaDateTimePageableDataRequest<CssCardInfo> {
	private String searchBrand;
	private String searchType;

	public String getSearchBrand() {
		return searchBrand;
	}

	public void setSearchBrand(String searchBrand) {
		this.searchBrand = searchBrand;
	}
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CssCardInfo " + "  where 1=1 ");
		hql.append(" and brand =:searchBrand ", searchBrand);
		hql.append(" and type =:searchType ", searchType);
		hql.append("order by handleDate desc");
		return hql;
	}

}
