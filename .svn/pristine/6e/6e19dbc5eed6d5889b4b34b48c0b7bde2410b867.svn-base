
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.customerservice.finance.CloseAcountRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountCancelRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountCancelResponse;
import cn.com.taiji.qtk.entity.AccountBalance;
import cn.com.taiji.qtk.entity.AccountCardBalanceOperate;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.AccountCardBalanceOperateType;
import cn.com.taiji.qtk.repo.jpa.AccountBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceOperateRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;


@Service
public class CloseAcountManagerImpl extends AbstractDsiCommManager implements CloseAcountManager{

	
	
	@Autowired
	private FinanceBinService financeBinService;
	
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	
	@Autowired
	private AccountCardBalanceOperateRepo accountCardBalanceOperateRepo;
	
	@Autowired
	private AccountBalanceRepo accountBalanceRepo;

	@Override
	public LargePagination<CustomerInfo> queryPage(CloseAcountRequest queryModel,HttpServletRequest request) throws ManagerException {
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo=customerInfoRepo.findByBrief(queryModel.getCustomerIdType().getTypeCode(), queryModel.getCustomerIdNum());
		if(customerInfo==null) {
			throw new ManagerException("未查到该信息对应的用户信息!");
		}
		String agencyId=LoginHelper.getLoginUser(request).getStaff().getServiceHall().getAgencyId();
		if(!customerInfo.getChannelId().substring(0, 11).equals(agencyId)) {
			throw new ManagerException("当前合作机构无权查询此卡!");
		} 
		AccountBalance  accountBalance=	accountBalanceRepo.findAccountsByCustomerId(customerInfo.getCustomerId());
		if(accountBalance==null) {
			throw new ManagerException("未找到此账户的账户信息 或者此账户已注销");
		}
		queryModel.setCustomerId(customerInfo.getCustomerId());
		return customerInfoRepo.largePage(queryModel);
	}
	
	
	@Override
	public AccountCancelResponse  accountCancel(CloseAcountRequest queryModel,HttpServletRequest request) {
		AccountCancelRequest accountCancelRequest  =new AccountCancelRequest();
		AccountCancelResponse accountCancelResponse=new AccountCancelResponse();
		super.commSet(accountCancelRequest,LoginHelper.getLoginUser(request));
			accountCancelRequest.setUserId(queryModel.getCustomerId());
				
				try {
					accountCancelResponse=financeBinService.accountCancel(accountCancelRequest);
				} catch (ApiRequestException | IOException e) {
					e.printStackTrace();
				}
				if(accountCancelResponse!=null) {
					 if(accountCancelResponse.getRefund()>=0) {
						accountCancelResponse.setMessage("销户成功!");
						accountCancelResponse.setStatus(1);
						queryModel.setBalance(accountCancelResponse.getRefund());
						moneySave(queryModel, LoginHelper.getLoginUser(request));
					}else {
						accountCancelResponse.setStatus(-1);
					}
				}
		return accountCancelResponse;
	}
	
	


	private  void moneySave(CloseAcountRequest queryModel,User user) {
		AccountCardBalanceOperate accountCardBalanceOperate =new AccountCardBalanceOperate();
		accountCardBalanceOperate.setBalance(queryModel.getBalance());
		accountCardBalanceOperate.setBankCardId(queryModel.getBankCard());
		accountCardBalanceOperate.setOperateStatus(0);
		accountCardBalanceOperate.setCreateDate(CssUtil.getNowDate());
		accountCardBalanceOperate.setCreateTime(CssUtil.getNowDateTimeStrWithT());
		accountCardBalanceOperate.setChannelId(user.getStaff().getServiceHall().getAgencyId());
		accountCardBalanceOperate.setStaffId(user.getStaffId());
		accountCardBalanceOperate.setCustomerId(queryModel.getCustomerId());
		if(queryModel.getAccountCardBalanceOperateType().equals("现金")) {
			accountCardBalanceOperate.setType(AccountCardBalanceOperateType.CASH);
		}else {
			accountCardBalanceOperate.setType(AccountCardBalanceOperateType.BANK_CARD);
		}
		accountCardBalanceOperateRepo.save(accountCardBalanceOperate);
		
		
	}
	
	

}

