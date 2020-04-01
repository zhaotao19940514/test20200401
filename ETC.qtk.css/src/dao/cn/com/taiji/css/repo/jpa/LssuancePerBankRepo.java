package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.qtk.entity.CardInfo;

public interface LssuancePerBankRepo extends AbstractJpaRepo<CardInfo, String>{
	
	@Query(nativeQuery=true,value="select channel_name, count(*) as count from ( " + 
			"    select card_id, channel_name from qtk_cardinfo c,dasp_order_notice o  " + 
			"    where c.vehicle_id = o.vehicle_id " + 
			"    and substr(c.vehicle_id,0,1)='贵'  " + 
			"    and card_enableTime>=?1  " + 
			"    and card_enableTime<=?2  " + 
			"    and c.AGENCY_ID='52010188037' " + 
			"    group by card_id ,channel_name) " + 
			"    group by channel_name")
	public List<Object[]> ListByGTime(String startTime, String endTime);
	
	@Query(nativeQuery=true,value="select channel_name, count(*) as count from ( " + 
			"    select card_id, channel_name from qtk_cardinfo c,dasp_order_notice o  " + 
			"    where c.vehicle_id = o.vehicle_id " + 
			"    and substr(c.vehicle_id,0,1)!='贵'  " + 
			"    and card_enableTime>=?1  " + 
			"    and card_enableTime<=?2  " + 
			"    and c.AGENCY_ID='52010188037' " + 
			"    group by card_id ,channel_name) " + 
			"    group by channel_name")
	public List<Object[]> ListByNGTime(String startTime, String endTime);

}
