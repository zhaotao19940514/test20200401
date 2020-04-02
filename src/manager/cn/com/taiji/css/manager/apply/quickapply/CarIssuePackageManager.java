package cn.com.taiji.css.manager.apply.quickapply;

import java.util.List;

import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.IssuePackageInfo;

public interface CarIssuePackageManager {
   public List<CarIssuePackageInfo> findByVehicleId(String vehicleId);
   public IssuePackageInfo findPackage(String vehicleId);
}
