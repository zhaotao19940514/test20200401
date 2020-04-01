package cn.com.taiji.css.manager.report.cancelreport;

import java.io.File;
import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.request.report.cancelreport.AgencyCancelDeatilShowModel;
import cn.com.taiji.css.model.request.report.cancelreport.CancelReportRequest;
import cn.com.taiji.qtk.entity.Agency;

public interface CancelReportManage {

	public List<Agency> getAllAgency();
	
	public List<AgencyCancelDeatilShowModel> getCancelDetail(CancelReportRequest queryModel)throws ManagerException;
	
	public File export(CancelReportRequest queryModel)throws ManagerException;
	
}
