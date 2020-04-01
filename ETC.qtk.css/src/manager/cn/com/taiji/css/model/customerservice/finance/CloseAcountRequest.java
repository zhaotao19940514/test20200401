/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.manager.util.MyPatterns;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;

public class CloseAcountRequest extends JpaDateTimePageableDataRequest<CustomerInfo> {
		//状态
		private Integer status;

		// 用户编号
		private String customerId;

		// 开户人证件号
		private String customerIdNum;
		
		// 开户人证件类型
		private CustomerIDType customerIdType;
		
		// 开户人名称
		private String customerName;
		
		// 余额
		private Long balance;
		
		// 银行卡号
		private String bankCard;
		
		// 取现方式
		private String accountCardBalanceOperateType;
		
		
		public Integer getStatus() {
			return status;
		}



		public void setStatus(Integer status) {
			this.status = status;
		}



		public String getCustomerId() {
			return customerId;
		}



		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}



		public String getCustomerIdNum() {
			return customerIdNum;
		}



		public void setCustomerIdNum(String customerIdNum) {
			this.customerIdNum = customerIdNum;
		}



		public CustomerIDType getCustomerIdType() {
			return customerIdType;
		}



		public void setCustomerIdType(CustomerIDType customerIdType) {
			this.customerIdType = customerIdType;
		}



		public String getCustomerName() {
			return customerName;
		}



		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		
	
		public Long getBalance() {
			return balance;
		}



		public void setBalance(Long balance) {
			this.balance = balance;
		}



		public String getBankCard() {
			return bankCard;
		}



		public void setBankCard(String bankCard) {
			this.bankCard = bankCard;
		}


		public String getAccountCardBalanceOperateType() {
			return accountCardBalanceOperateType;
		}



		public void setAccountCardBalanceOperateType(String accountCardBalanceOperateType) {
			this.accountCardBalanceOperateType = accountCardBalanceOperateType;
		}

		public void validate() {
			MyViolationException mve = new MyViolationException();		
			if(customerIdType == null ) mve.addViolation("customerIdType", "请选择证件类型！");
			if(customerIdNum  == null ) mve.addViolation("customerIdNum", "请输入证件号！");
			
			if (customerIdType != null) {
				if(CustomerIDType.valueOfCode(customerIdType.getTypeCode()) == null)
					mve.addViolation("ownerIdType", "没有此证件类型！"+customerIdType.getTypeCode());
				
				if(customerIdNum != null) {
					switch (customerIdType.getTypeCode()) {
					case 101:
						if(!MyPatterns.checkIdCode(customerIdNum)) {
							mve.addViolation("customerIdNum", "身份证格式不正确！");
						}
						break;
					case 103:
						if(!MyPatterns.checkGaCnIdCodeFormat(customerIdNum)) {
							mve.addViolation("customerIdNum", "港澳通行证证格式不正确！");
						}
						break;
					case 104:
						if(!MyPatterns.checkTwCnIdCodeFormat(customerIdNum)) {
							mve.addViolation("customerIdNum", "台湾通行证格式不正确！");
						}
						break;
					case 105:
						if(!MyPatterns.checkSgzCnIdCodeFormat(customerIdNum)) {
							mve.addViolation("customerIdNum", "军官证格式不正确！");
						}
						break;
					case 201:
						if(!MyPatterns.checkCreditCodeFormat(customerIdNum)) {
							mve.addViolation("customerIdNum", "社会信用代码格式不正确！");
						}
						break;
					
					default:
						break;
					}
				}
			}
			
			if (mve.hasViolation()) throw mve;
		}
		

		@Override
		public HqlBuilder toSelectHql() {
			HqlBuilder hql = new HqlBuilder("from " + "CustomerInfo " +
					"  where 1=1 ");
			hql.append(" and customerId =:customerId",customerId);
			hql.append(" and status =1");
			return hql;
		}

	
}

