
package cn.com.taiji.css.manager.customerservice.finance;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingRequest;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingResponse;
import cn.com.taiji.qtk.entity.ChargeDetail;


public interface HalfauditingManager  {

	LargePagination<ChargeDetail> queryPage(HalfauditingRequest queryModel,User user) throws ManagerException;
	
	
	String updateStatus(String chargeId);

	HalfauditingResponse savePng(MultipartFile file,String chargeId) throws ManagerException ;

}

