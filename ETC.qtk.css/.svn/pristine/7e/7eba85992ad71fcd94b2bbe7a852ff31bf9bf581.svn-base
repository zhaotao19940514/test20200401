
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationModel;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.qtk.entity.CardInfo;


public interface ExpenseRefundApplicationManager {
	
	List<ExpenseRefundApplicationModel> page(ExpenseRefundApplicationRequest request);
	
	ExpenseRefundApplicationRequest findById(String cardId, String id) throws ManagerException;
//	ExpenseRefundApplicationRequest findAccountDetailById(String id) throws ManagerException;
	ExpenseRefundApplicationResponse  trueFinance(ExpenseRefundApplicationRequest request,User user) throws ManagerException ;
	ExpenseRefundApplicationResponse  save(ExpenseRefundApplicationRequest request,User user) throws ManagerException ;
	ExpenseRefundApplicationResponse  select(ExpenseRefundApplicationRequest request) throws ManagerException ;
	ExpenseRefundApplicationResponse saveFile(MultipartFile file,String id,String status) throws ManagerException ;
	
	public HttpServletResponse download(ExpenseRefundApplicationRequest queryModel, HttpServletResponse response,HttpServletRequest request) throws IOException;
	
	
	
}

