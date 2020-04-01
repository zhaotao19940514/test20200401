package cn.com.taiji.css.manager.serviceHall;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.issuetranscation.CardAnnounceRecordModel;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallChargeCountModel;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallChargeCountRequest;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
@Service
public class ServiceHallChargeCountManagerImpl extends AbstractManager implements ServiceHallChargeCountManager{
    @Autowired
    private ChargeDetailRepo chargeDetailRepo;
    @Autowired
    private ServiceHallRepo serviceHallRepo;
	
	@Override
	public Pagination page(ServiceHallChargeCountRequest request) {
		if(request.getHandleDate()==null && request.getStartDate()==null && request.getEndDate()==null)
		return null;
		ServiceHall sh = serviceHallRepo.findByServiceHallId(request.getServiceHallId());
		List<ServiceHallChargeCountModel> modelList = Lists.newArrayList();
		logger.info("handleDate:"+request.getHandleDate());
		logger.info("channelId:"+request.getServiceHallId());
		
        	List<Object[]>infoList =chargeDetailRepo.groupByChannelWithCharge(request.getServiceHallId(), request.getStartDate(), request.getEndDate());
            for (Object[] objects : infoList) {
            	if(objects.length>0) {
	            	ServiceHallChargeCountModel model = new ServiceHallChargeCountModel();
	                model.setCount(Long.valueOf(objects[1].toString()));
	                model.setAmount(Long.valueOf(objects[2].toString()));
	                model.setHandleDate(objects[0].toString());
	                model.setServiceHallName(sh.getName());
	                modelList.add(model); 
            	}
			}
		Comparator<ServiceHallChargeCountModel> comparator = (t1, t2) -> t1.getHandleDate().compareTo(t2.getHandleDate());
		modelList.sort(comparator.reversed());
		Pagination page = new Pagination();
		page.setResult(modelList);
		page.setPageSize(30);
		page.setTotalCount(modelList.size());
		page.compute();		
		return page;
	}
   
	
	
}
