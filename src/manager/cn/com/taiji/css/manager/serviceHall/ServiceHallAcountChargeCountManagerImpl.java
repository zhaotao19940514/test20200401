package cn.com.taiji.css.manager.serviceHall;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallChargeCountModel;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallChargeCountRequest;
import cn.com.taiji.qtk.repo.jpa.AccountTradeDetailRepo;
@Service
public class ServiceHallAcountChargeCountManagerImpl extends AbstractManager implements ServiceHallAcountChargeCountManager{

    
	@Autowired
	private AccountTradeDetailRepo accountTradeDetailRepo;
	@Override
	public Pagination page(ServiceHallChargeCountRequest request) {
		if(request.getHandleDate()==null && request.getStartDate()==null && request.getEndDate()==null)
		return null;
		List<ServiceHallChargeCountModel> modelList = Lists.newArrayList();
		logger.info("handleDate:"+request.getHandleDate());
		logger.info("channelId:"+request.getServiceHallId());
        List<Object[]> infoObject = accountTradeDetailRepo.groupByChannelWithDate(request.getStartDate(), request.getEndDate()); 
        List<Object[]> infoObjectReversal = accountTradeDetailRepo.groupByChannelWithDateReversal(request.getStartDate(), request.getEndDate());
        if(infoObject.size()>0) {
	           for (Object[] objects : infoObject) {
	        	   ServiceHallChargeCountModel model = new ServiceHallChargeCountModel();
	        	   logger.info("1--"+String.valueOf(objects[0]));
	        	   model.setServiceHallName((objects[0].toString()));
	        	   model.setCount(Long.valueOf(objects[1].toString()));
	        	   model.setAmount(Long.valueOf(objects[3].toString()));
	        	   Long charge = (Long.valueOf(objects[3].toString()));
	        	   String channelId=String.valueOf(objects[2]);
	        	   model.setHandleDate(request.getEndDate());
	        	   modelList.add(model); 
	        	   if(infoObjectReversal.size()>0) {
		        	   for(Object[] objectsReversal :infoObjectReversal) {
		        		   String channelId1=String.valueOf(objectsReversal[1]);
		        		   Long amount = (Long.valueOf(objectsReversal[2].toString()));
		        		   if(channelId.equals(channelId1)) {
		        			   //如果有账户冲正的则需要扣除上账金额
		        			   model.setAmount(charge-amount);
		        		   }
		        	   }
	        	   }
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
