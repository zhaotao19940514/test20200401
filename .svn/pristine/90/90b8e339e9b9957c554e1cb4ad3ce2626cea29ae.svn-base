package cn.com.taiji.css.manager.apply.quickapply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.repo.jpa.CarIssuePackageInfoRepo;
import cn.com.taiji.qtk.repo.jpa.IssuePackageInfoRepo;

public class CarIssuePackageManagerImpl extends AbstractManager implements CarIssuePackageManager{
    @Autowired
    private CarIssuePackageInfoRepo carIssuePackageInfoRepo;
    @Autowired
    private IssuePackageInfoRepo issuePackageInfoRepo;
    
	
	@Override
	public List<CarIssuePackageInfo> findByVehicleId(String vehicleId) {
		return carIssuePackageInfoRepo.findByVehicleId(vehicleId);
	}
	
	@Override
	public IssuePackageInfo findPackage(String vehicleId) {
		List<CarIssuePackageInfo> list = carIssuePackageInfoRepo.findByVehicleId(vehicleId);
		IssuePackageInfo ipi = issuePackageInfoRepo.findByPackageName(list.get(0).getPackageNum());
		return ipi;
	}
     
}
