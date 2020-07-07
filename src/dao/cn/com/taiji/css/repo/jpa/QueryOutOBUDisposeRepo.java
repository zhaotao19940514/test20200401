package cn.com.taiji.css.repo.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.qtk.entity.VehicleInfo;

public interface QueryOutOBUDisposeRepo extends AbstractJpaRepo<VehicleInfo, String> {

	@Query(nativeQuery = true , value = "select substr(mo.REQUEST_STR,instr(mo.REQUEST_STR,'vehiclePlate\":\"',1,1)+15,instr(mo.REQUEST_STR,'\",\"axleCount',1,1)-instr(mo.REQUEST_STR,'vehiclePlate\":\"',1,1)-15) vehicle  " + 
			"from qtk_monitor_transinface_detail mo   " + 
			"where  mo.handle_date between ?1 and ?2 and mo.create_time   " + 
			"between ?3 " + 
			"and ?4 " + 
			"and mo.service_type='VEHICLEINFOSUBMITV2'   " + 
			"and mo.agency_id in('52010102006','52010102038')  ")
	public List<String> findOutOBUByHandheld(String startDate, String endDate, LocalDateTime startLocalDateTime,LocalDateTime endLocalDateTime);
	
	@Query(nativeQuery = true , value = "select l.related_vehicle_id,l.related_obu_id,l.user_name  " + 
			"  from qtk_css_operate_log l, qtk_staff f, qtk_css_user r  " + 
			" where l.user_name = r.login_name  " + 
			"   and l.service_type = 'CUSTOMERSERVICE_OBU_REWRITE'  " + 
			"   and f.agency_id = '52010106004' and  " + 
			"   l.operate_time >= ?1 and  " + 
			"   l.operate_time <= ?2  " + 
			"   and l.operate_type='ADD'  " + 
			"group by l.related_vehicle_id,l.related_obu_id,l.user_name ")
	public List<Object[]> findOutOBUByCustomerService(String startLocalDateTime, String endLocalDateTime);

}
