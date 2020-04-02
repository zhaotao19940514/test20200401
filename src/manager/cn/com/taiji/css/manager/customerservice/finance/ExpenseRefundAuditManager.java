
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundAuditRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundAuditResponse;
import cn.com.taiji.qtk.entity.CardRefundDetail;

public interface ExpenseRefundAuditManager {
	
	LargePagination<CardRefundDetail> page(ExpenseRefundAuditRequest request);
	CardRefundDetail findById(String id) throws ManagerException;
	ExpenseRefundAuditResponse auditSure(String id) throws ManagerException;
	ExpenseRefundAuditResponse  select(ExpenseRefundAuditRequest request) throws ManagerException ;
	ExpenseRefundAuditResponse  save(ExpenseRefundAuditRequest request,User user) throws ManagerException ;
	ExpenseRefundAuditResponse  update(ExpenseRefundAuditRequest request,User user) throws ManagerException ;
	ExpenseRefundAuditResponse  deleteById(ExpenseRefundAuditRequest request,User user) throws ManagerException ;
	ExpenseRefundApplicationResponse savePng(MultipartFile file,String cardId,String enableTime) throws ManagerException ;
	/*List<String> getScreenShotBase64(String  id,String enableTime) throws ManagerException;
	Model modelAdd(String cardId,String enableTime,Model model) throws ManagerException;*/
	
	void exportExcel(ExpenseRefundAuditRequest queryModel,HttpServletRequest request,HttpServletResponse response);
	
	public List<ExpenseRefundAuditRequest> getLines(File importFile) throws IOException;
	public  ExpenseRefundAuditResponse saveFile(MultipartFile file) throws ManagerException;
	public ExpenseRefundAuditResponse importExcel(List<ExpenseRefundAuditRequest> req) throws ManagerException;
}

