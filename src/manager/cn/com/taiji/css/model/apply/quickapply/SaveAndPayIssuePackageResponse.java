package cn.com.taiji.css.model.apply.quickapply;

import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;

public class SaveAndPayIssuePackageResponse extends AppAjaxResponse {
	private CarIssuePackageInfo carIssuePackageInfo;
//	private IssuePackageInfo issuePackage;
	
	public CarIssuePackageInfo getCarIssuePackageInfo() {
		return carIssuePackageInfo;
	}

	public void setCarIssuePackageInfo(CarIssuePackageInfo carIssuePackageInfo) {
		this.carIssuePackageInfo = carIssuePackageInfo;
	}

//	public IssuePackageInfo getIssuePackage() {
//		return issuePackage;
//	}
//
//	public void setIssuePackage(IssuePackageInfo issuePackage) {
//		this.issuePackage = issuePackage;
//	}
	
}
