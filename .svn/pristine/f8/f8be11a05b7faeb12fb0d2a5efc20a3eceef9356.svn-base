package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.apply.inportInfomation.BatchIssueBaseInfoShowModel;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationRequset;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationResponse;
import cn.com.taiji.css.model.customerservice.finance.CardRefundExcelModel;
import cn.com.taiji.css.model.customerservice.finance.RefundInpExceptionResponse;

public interface InportRefundManager {

	
	public List<CardRefundExcelModel> getLines(File importFile) throws IOException;
	
	public  InportInfomationResponse saveFile(MultipartFile file) throws ManagerException;
	
	public LargePagination<BatchIssueBaseInfoShowModel> query(InportInfomationRequset queryModel);
	
	public List<String> queryBatch();
	
	public File getExcelFilePath(CardRefundExcelModel queryModel,User user) throws ManagerException;


	List<RefundInpExceptionResponse> importExcel(List<CardRefundExcelModel> lines, HttpServletResponse response);
}



