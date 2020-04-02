
package cn.com.taiji.css.manager.customerservice.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.customerservice.report.CountModel;


public interface FinancialstatementManager {

	List<CountModel> page(CountModel queryModel,HttpServletRequest request) throws ManagerException;
	
	List<CountModel> findByRegionId(CountModel querModel) throws ManagerException; 
	
	List<CountModel> findByChannelId(CountModel querModel) throws ManagerException; 
	
	List<CountModel> findByAgencyId(CountModel querModel,HttpServletRequest request) throws ManagerException; 
}

