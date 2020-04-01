package cn.com.taiji.css.manager.apply.baseinfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.BatchIssueBaseInfo;
import cn.com.taiji.css.model.apply.inportInfomation.BatchIssueBaseInfoShowModel;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationRequset;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationResponse;

public interface InportInfomationManager {

	InportInfomationResponse importExcel(List<BatchIssueBaseInfo> InportInfomationRequset) throws ManagerException;
	
	
	public List<BatchIssueBaseInfo> getLines(File importFile) throws IOException;
	
	public  InportInfomationResponse saveFile(MultipartFile file) throws ManagerException;
	
	public LargePagination<BatchIssueBaseInfoShowModel> query(InportInfomationRequset queryModel);
	
	public List<InportInfomationRequset> queryBatch();
	
	public File getExcelFilePath(InportInfomationRequset queryModel,HttpServletRequest request) throws ManagerException;
}



