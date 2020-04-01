package cn.com.taiji.css.dao.jpa;

import java.util.List;

import cn.com.taiji.common.dao.jpa.AbstractJpaDao;
import cn.com.taiji.qtk.entity.CancelledCardDetail;

public interface CancelReportDao extends AbstractJpaDao<CancelledCardDetail> {

	public List<Object[]> findCancelDetailByAgencyIdAndStartTimeAndEndTime(String agencyId,String startTime,String endTime,String vehicleIsGui);
}
