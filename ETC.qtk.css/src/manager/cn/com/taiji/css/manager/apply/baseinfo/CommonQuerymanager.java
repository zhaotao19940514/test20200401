package cn.com.taiji.css.manager.apply.baseinfo;

import cn.com.taiji.css.entity.User;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.Staff;

public interface CommonQuerymanager {
	public ServiceHall findServiceHallByServicehallId(String servicehallId);
	
	public Agency findAgencyByAgencyId(String agencyId);
	
	public Staff findStaffByStaffId(String staffId);
	
	public User findUserByStaffId(String staffId);
	
	public User findUserByLoginName(String loginName);
	
	public IssuePackageInfo findIssuePackageByPackageNum(String packageNum);
}
