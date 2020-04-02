package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.ReplaceEquipmentCostDetail;

public class ReplaceEquipmentRequest extends JpaDateTimePageableDataRequest<ReplaceEquipmentCostDetail> {

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + " ReplaceEquipmentCostDetail " + "  where 1=1 ");
		return hql;
	}

}
