/**
 * @Title ServiceHallDeviceConfigRequest.java
 * @Package cn.com.taiji.css.model.request.serviceHall.deviceconfig
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月6日 下午8:02:42
 * @version V1.0
 */
package cn.com.taiji.css.model.request.serviceHall.deviceconfig;


import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;
import cn.com.taiji.qtk.entity.dict.CssCardDeviceType;
import cn.com.taiji.qtk.entity.dict.CssObuDeviceType;
import cn.com.taiji.qtk.entity.dict.CssPosDeviceType;

/**
 * @ClassName ServiceHallDeviceConfigRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年09月06日 20:02:42
 * @E_mail yaonanlin@163.com
 */
public class ServiceHallDeviceConfigRequest extends JpaPageableDataRequest<ServiceHallDeviceConfig> {
	private String queryAgencyId;
	private String queryServiceHallId;
	private CssCardDeviceType queryCardDeviceType;
	private CssObuDeviceType queryObuDeviceType;
	private CssPosDeviceType queryPosDeviceType;
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from ServiceHallDeviceConfig where 1=1 ");
		hql.append(" and serviceHallId = :queryServiceHallId", queryServiceHallId);
		hql.append(" and cardDeviceType = :queryCardDeviceType", queryCardDeviceType);
		hql.append(" and obuDeviceType = :queryObuDeviceType", queryObuDeviceType);
		hql.append(" and posDeviceType = :queryPosDeviceType", queryPosDeviceType);
		return hql;
	}

	public void valid() {
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(queryAgencyId) && !StringTools.hasText(queryServiceHallId)) {
			mve.addViolation("queryAgencyId", "机构不能为空");
			mve.addViolation("queryServiceHallId", "网点不能为空");
		}
		if (mve.hasViolation())
			throw mve;
	}

	public String getQueryAgencyId() {
		return queryAgencyId;
	}

	public String getQueryServiceHallId() {
		return queryServiceHallId;
	}

	public void setQueryAgencyId(String queryAgencyId) {
		this.queryAgencyId = queryAgencyId;
	}

	public void setQueryServiceHallId(String queryServiceHallId) {
		this.queryServiceHallId = queryServiceHallId;
	}

	public CssCardDeviceType getQueryCardDeviceType() {
		return queryCardDeviceType;
	}

	public CssObuDeviceType getQueryObuDeviceType() {
		return queryObuDeviceType;
	}

	public CssPosDeviceType getQueryPosDeviceType() {
		return queryPosDeviceType;
	}

	public void setQueryCardDeviceType(CssCardDeviceType queryCardDeviceType) {
		this.queryCardDeviceType = queryCardDeviceType;
	}

	public void setQueryObuDeviceType(CssObuDeviceType queryObuDeviceType) {
		this.queryObuDeviceType = queryObuDeviceType;
	}

	public void setQueryPosDeviceType(CssPosDeviceType queryPosDeviceType) {
		this.queryPosDeviceType = queryPosDeviceType;
	}
	
}
