package cn.com.taiji.css.manager.apply.baseinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.Staff;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.IssuePackageInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StaffRepo;

@Service
public class CommonQuerymanagerImpl extends AbstractManager implements CommonQuerymanager {
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private AgencyRepo agencyRepo;
	@Autowired
	private StaffRepo staffRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private IssuePackageInfoRepo issuePackageInfoRepo;
	@Override
	public ServiceHall findServiceHallByServicehallId(String servicehallId) {
		ServiceHall model = serviceHallRepo.findByServiceHallId(servicehallId);
		return model;
	}
	@Override
	public Agency findAgencyByAgencyId(String agencyId) {
		Agency model = agencyRepo.findByAgencyId(agencyId);
		return model;
	}
	@Override
	public Staff findStaffByStaffId(String staffId) {
		Staff model = staffRepo.findByStaffId(staffId);
		return model;
	}
	@Override
	public User findUserByStaffId(String staffId) {
		List<User> list = userRepo.findByStaffId(staffId);
		User model = null;
		if(list != null && list.size() > 0) {
			model = list.get(0);
		}
		return model;
	}
	@Override
	public User findUserByLoginName(String loginName) {
		User model = userRepo.findByLoginName(loginName);
		return model;
	}
	@Override
	public IssuePackageInfo findIssuePackageByPackageNum(String packageNum) {
		IssuePackageInfo model = issuePackageInfoRepo.findByPackageNum(packageNum);
		return model;
	}

}
