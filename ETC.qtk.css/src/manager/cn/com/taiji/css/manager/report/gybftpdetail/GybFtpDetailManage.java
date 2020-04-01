package cn.com.taiji.css.manager.report.gybftpdetail;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.request.report.gybftpdetail.GybFtpDetailRequest;
import cn.com.taiji.css.model.request.report.gybftpdetail.GybFtpDetailShowModel;

public interface GybFtpDetailManage {
	public List<GybFtpDetailShowModel> getDetail(GybFtpDetailRequest queryModel) throws ManagerException;
}
