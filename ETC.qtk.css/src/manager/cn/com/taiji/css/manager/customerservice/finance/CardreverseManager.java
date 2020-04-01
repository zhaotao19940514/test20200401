
package cn.com.taiji.css.manager.customerservice.finance;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.CardreverseRequest;
import cn.com.taiji.css.model.customerservice.finance.CardreverseResponse;
import cn.com.taiji.qtk.entity.ChargeDetail;


public interface CardreverseManager {

	/**
	 * @param queryModelO
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<ChargeDetail> queryPage(CardreverseRequest queryModel,User user) throws ManagerException ;

	CardreverseResponse cardReverseInitWithCOS(CardreverseRequest request,User user) throws ManagerException;
	CardreverseResponse cardReverseDebitWithCOS(CardreverseRequest request,User user) throws ManagerException;
	CardreverseResponse cardReverseConfirmWithCOS(CardreverseRequest request,User user) throws ManagerException;
}

