
package cn.com.taiji.css.manager.customerservice.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.customerservice.report.CountModel;


public interface DailySheetManager {

	/**
	 * @param queryModelO
	 * @return
	 * @throws ManagerException 
	 */
	List<CountModel> queryPage(CountModel queryModel,HttpServletRequest request) throws ManagerException;
	
	List<CountModel> page(CountModel queryModel,HttpServletRequest request) throws ManagerException;
	
	List<CountModel> staffId(CountModel queryModel,HttpServletRequest request) throws ManagerException;
	
	Long sumCash(CountModel queryModel) throws ManagerException;
}

