package cn.com.taiji.css.manager.serviceHall;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.ServiceHallRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallRequest;
import cn.com.taiji.css.model.serviceHall.ServiceHallModel;
import cn.com.taiji.qtk.entity.ServiceHall;

public interface ServiceHallManager {
	/**/
	public List<ServiceHallModel> listByAgentId(String agencyId);

	public ServiceHall findById1(String id);

	public List<ServiceHall> listByName(String name, User loginUser);

	public Pagination page(ServiceHallRequest req);

	public ServiceHall add(ServiceHall serviceHall) throws ManagerException;

	public ServiceHall edit(ServiceHall serviceHall) throws ManagerException;

	public ServiceHall findById(String id);

	public void delete(ServiceHall serviceHall) throws ManagerException;

	public List<ServiceHall> queryByName(String name);
	
	public List<ServiceHallRequset> getLines(File importFile) throws IOException;
	
	public  ExpenseRefundApplicationResponse saveFile(MultipartFile file) throws ManagerException;
	
	public UserResponse importExcel(List<ServiceHallRequset> user) throws ManagerException;
}
