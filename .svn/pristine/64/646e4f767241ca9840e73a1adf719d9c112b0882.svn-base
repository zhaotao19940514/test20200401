package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.qtk.entity.CardInfo;

public interface NativePlaceCirculationRepo extends AbstractJpaRepo<CardInfo, String>{
	
	@Query(nativeQuery=true,value=" select  name ,count(1) " + 
				"from qtk_cardinfo c, qtk_agency a " + 
				"where substr(c.channel_id,0,11)=a.agency_id " +
				"and substr(c.vehicle_id,0,1)='贵' " + 
				"and card_enableTime>=?1 " + 
				"and card_enableTime<=?2 " + 
				"group by a.name")
	 public List<Object[]> listByYgEnableTime(String startEnableTime, String endEnableTime);
	 @Query(nativeQuery=true,value=" select  name ,count(1) " + 
				"from qtk_cardinfo c, qtk_agency a " + 
				"where substr(c.channel_id,0,11)=a.agency_id " +
				"and substr(c.vehicle_id,0,1)!='贵' " + 
				"and card_enableTime>=?1 " + 
				"and card_enableTime<=?2 " + 
				"group by a.name")
	 public List<Object[]> listByNgEnableTime(String startEnableTime, String endEnableTime);
	
}
