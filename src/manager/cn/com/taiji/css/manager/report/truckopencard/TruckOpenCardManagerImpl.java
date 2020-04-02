package cn.com.taiji.css.manager.report.truckopencard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.css.dao.jpa.TruckOpenCardDao;
import cn.com.taiji.css.model.customerservice.report.QueryTimes;

@Service
public class TruckOpenCardManagerImpl implements TruckOpenCardManager {
	
	@Autowired
	TruckOpenCardDao truckOpenCardDao;
 	
	@Override
	public List<Object[]> page(QueryTimes time) {
		time.Violation();
		List<Object[]> findCardByBank = truckOpenCardDao.findCardByBank(time);
		return findCardByBank;
	}

}
