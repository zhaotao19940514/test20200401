package cn.com.taiji.css.manager.issuetranscation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.issuetranscation.CardConsumptionRequest;
import cn.com.taiji.qtk.repo.jpa.TrafficRecordDetailRepo;

@Service
public class CardConsumptionManagerImpl extends AbstractManager implements CardConsumptionManager{
   
	@Autowired
	private TrafficRecordDetailRepo   trafficRecordDetailRepo;
	@Override
	public Pagination page(CardConsumptionRequest request) {		
		if(!StringUtils.hasText(request.getCardId())&&!StringUtils.hasText(request.getVehiclePlate()))
		return null;
		return trafficRecordDetailRepo.page(request);
	}
   
}
