
package cn.com.taiji.css.manager.customerservice.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.customerservice.report.RegionStatisticsModel;


public interface RegionStatisticsManager {

	/**
	 * @param queryModelO
	 * @return
	 * @throws ManagerException 
	 */
	List<RegionStatisticsModel> queryPage(RegionStatisticsModel queryModel,HttpServletRequest request) throws ManagerException;

	List<RegionStatisticsModel> queryChannelData(String reginNameAndDate);
	
	List<RegionStatisticsModel> queryRegionRefundData(String reginNameAndDate);
}

