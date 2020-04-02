package cn.com.taiji.css.manager.report.gybftpdetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.dao.jpa.GybFtpDetailDao;
import cn.com.taiji.css.model.request.report.gybftpdetail.GybFtpDetailRequest;
import cn.com.taiji.css.model.request.report.gybftpdetail.GybFtpDetailShowModel;

@Service
public class GybFtpDetailManageImpl implements GybFtpDetailManage{

	private final String FILE_PREFIX="313_";
	private final String FILE_SUFFIX="_0000000000001_TB_CardBlackList.txt";
	
	@Autowired
	private GybFtpDetailDao gybFtpDetailDao;
	
	@Override
	public List<GybFtpDetailShowModel> getDetail(GybFtpDetailRequest queryModel) throws ManagerException {
		queryModel.paramCheck();
		String startTime = queryModel.getStartTime();
		String endTime = queryModel.getEndTime();
		Long longStartTime = Long.valueOf(startTime);
		Long longEndTime = Long.valueOf(endTime);
		List<String> fileNames = Lists.newArrayList();
		String fileName;
		if(longStartTime-longEndTime>=0) {
			fileName= FILE_PREFIX + startTime +FILE_SUFFIX;
			fileNames.add(fileName);
		}else {
			while(longStartTime-longEndTime<=0) {
				fileName= FILE_PREFIX + longEndTime + FILE_SUFFIX;
				fileNames.add(fileName);
				longEndTime--;
			}
		}
		//获取对应状态的提交数量
		List<Object[]> inBlack = gybFtpDetailDao.findDetailByStatusAndFileNameIn(fileNames,1);
		List<GybFtpDetailShowModel> showModelList = Lists.newArrayList();
		for(Object[] detail : inBlack) {
			GybFtpDetailShowModel model = new GybFtpDetailShowModel();
			model.setFileName(detail[0]==null ? "暂无" : detail[0].toString());
			model.setSubmitInBlackAmount(detail[1]==null ? "暂无" : detail[1].toString());
			model.setInBlackAmount(detail[2]==null ? "暂无" : detail[2].toString());
			showModelList.add(model);
		}
		List<Object[]> outBlack = gybFtpDetailDao.findDetailByStatusAndFileNameIn(fileNames,2);
		for(GybFtpDetailShowModel model : showModelList) {
			String showModelFileName = model.getFileName();
			for(Object[] detail : outBlack) {
				if(detail[0].toString().equals(showModelFileName)) {
					model.setSubmitOutBlackAmount(detail[1]==null ? "暂无" : detail[1].toString());
					model.setOutBlackAmount(detail[2]==null ? "暂无" : detail[2].toString());
				}
			}
		}
		return showModelList;
	}

}
