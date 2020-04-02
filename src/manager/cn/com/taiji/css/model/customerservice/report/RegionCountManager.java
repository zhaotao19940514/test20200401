package cn.com.taiji.css.model.customerservice.report;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallChargeCountRequest;

public interface RegionCountManager {
	Pagination page(ServiceHallChargeCountRequest request);
}
