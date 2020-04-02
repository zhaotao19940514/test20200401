package cn.com.taiji.css.manager.serviceHall;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallChargeCountRequest;

public interface ServiceHallChargeCountManager {
	Pagination page(ServiceHallChargeCountRequest request);
}
