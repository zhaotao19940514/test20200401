package cn.com.taiji.css.manager.apply.quickapply;


import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.apply.quickapply.VehicleInfoQuickQueryRequest;
import cn.com.taiji.qtk.entity.Agency;

public interface QuickApplyManager {

	Pagination queryPage(VehicleInfoQuickQueryRequest req);
	
	
	/**
	 * 根据合作发行方编号查询合作发行方信息
	 * @param channelId
	 * @return
	 */
	public Agency findByAgencyId(String channelId);
}
