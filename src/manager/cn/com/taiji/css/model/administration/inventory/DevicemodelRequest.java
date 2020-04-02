package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.CssObuInfo;

/**
 * @author HEJUN
 *
 */
public class DevicemodelRequest extends JpaDateTimePageableDataRequest<CssObuInfo> {
	private String searchBrand;

	public String getSearchBrand() {
		return searchBrand;
	}

	public void setSearchBrand(String searchBrand) {
		this.searchBrand = searchBrand;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CssObuInfo " + "  where 1=1 ");
		hql.append(" and brand =:searchBrand ", searchBrand);
		hql.append("order by handleDate desc");
		return hql;
	}
}
