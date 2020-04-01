
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CardRefundDetail;
import cn.com.taiji.qtk.entity.dict.RefundType;


public class ExpenseRefundAuditRequest extends JpaDateTimePageableDataRequest<CardRefundDetail>  {
			//登录账号
			private String loginName;
			// 流水号
			private String id;
			// 卡编号
			private String cardId;
			// 录入时间
			private String createTime;
			// 交易金额
			private Long tradeFee;
			// 银行卡号
			private String bankCard;
			// 开户行
			private String bank;
			// 开户行户名
			private String bankUserName;
			// 户主联系方式
			private String phone;
			//申请退费状态
			private Integer status;
			//文件路径
			private String filePath;
			//文件名
			private String fileName;
			//操作用户ID
			private String userId;
			// 车牌号
			private String vehiclePlate;
			// 车牌颜色
			private Integer vehiclePlateColor;
			//通行时间
			private String tradeTime;
			//退费类型
			private RefundType refundType;
			//详细描述
			private String detailedDescription;
			//退费资金
			private Long refundFee;
			//退费审核描述
			private String refundDescription;
			//资金审核描述
			private String financeDescription;
			private String beforeDate;
			private String afterDate;
			
			
			public String getLoginName() {
				return loginName;
			}



			public void setLoginName(String loginName) {
				this.loginName = loginName;
			}



			public String getId() {
				return id;
			}



			public void setId(String id) {
				this.id = id;
			}



			public String getCardId() {
				return cardId;
			}



			public void setCardId(String cardId) {
				this.cardId = cardId;
			}



			public String getCreateTime() {
				return createTime;
			}



			public void setCreateTime(String createTime) {
				this.createTime = createTime;
			}



			public Long getTradeFee() {
				return tradeFee;
			}



			public void setTradeFee(Long tradeFee) {
				this.tradeFee = tradeFee;
			}



			public String getBankCard() {
				return bankCard;
			}



			public void setBankCard(String bankCard) {
				this.bankCard = bankCard;
			}



			public Integer getStatus() {
				return status;
			}



			public void setStatus(Integer status) {
				this.status = status;
			}



			public String getFilePath() {
				return filePath;
			}



			public void setFilePath(String filePath) {
				this.filePath = filePath;
			}



			public String getFileName() {
				return fileName;
			}



			public void setFileName(String fileName) {
				this.fileName = fileName;
			}

			
			
			public String getBank() {
				return bank;
			}



			public void setBank(String bank) {
				this.bank = bank;
			}



			public String getBankUserName() {
				return bankUserName;
			}



			public void setBankUserName(String bankUserName) {
				this.bankUserName = bankUserName;
			}



			public String getPhone() {
				return phone;
			}



			public void setPhone(String phone) {
				this.phone = phone;
			}

			


			public String getUserId() {
				return userId;
			}



			public void setUserId(String userId) {
				this.userId = userId;
			}



			public String getVehiclePlate() {
				return vehiclePlate;
			}



			public void setVehiclePlate(String vehiclePlate) {
				this.vehiclePlate = vehiclePlate;
			}



			public Integer getVehiclePlateColor() {
				return vehiclePlateColor;
			}



			public void setVehiclePlateColor(Integer vehiclePlateColor) {
				this.vehiclePlateColor = vehiclePlateColor;
			}



			public String getTradeTime() {
				return tradeTime;
			}



			public void setTradeTime(String tradeTime) {
				this.tradeTime = tradeTime;
			}



			public RefundType getRefundType() {
				return refundType;
			}



			public void setRefundType(RefundType refundType) {
				this.refundType = refundType;
			}



			public String getDetailedDescription() {
				return detailedDescription;
			}



			public void setDetailedDescription(String detailedDescription) {
				this.detailedDescription = detailedDescription;
			}



			public Long getRefundFee() {
				return refundFee;
			}



			public void setRefundFee(Long refundFee) {
				this.refundFee = refundFee;
			}



			public String getRefundDescription() {
				return refundDescription;
			}



			public void setRefundDescription(String refundDescription) {
				this.refundDescription = refundDescription;
			}



			public String getFinanceDescription() {
				return financeDescription;
			}



			public void setFinanceDescription(String financeDescription) {
				this.financeDescription = financeDescription;
			}



			
			public String getBeforeDate() {
				return beforeDate;
			}



			public void setBeforeDate(String beforeDate) {
				this.beforeDate = beforeDate;
			}



			public String getAfterDate() {
				return afterDate;
			}



			public void setAfterDate(String afterDate) {
				this.afterDate = afterDate;
			}


			public void validate() {
				MyViolationException mve = new MyViolationException();
				if(beforeDate!=null && afterDate==null) {
						mve.addViolation("afterDate", "查询时间不能为空！");
				}
				if(beforeDate==null && afterDate!=null) {
					mve.addViolation("beforeDate", "查询时间不能为空！");
			}
				if (mve.hasViolation()) throw mve;
			}

			public void validate1() {
				MyViolationException mve = new MyViolationException();
				if(refundFee==null) {
						mve.addViolation("refundFee", "退费审核金额不能为空！");
				}
				if (mve.hasViolation()) throw mve;
			}
			
			public ExpenseRefundAuditResponse validateData() {
				ExpenseRefundAuditResponse response = new ExpenseRefundAuditResponse();
				response.setStatus(-1);
				if(id==null || id=="") {
					response.setMessage("流水编号不能为空！");
				}else if(cardId ==null || cardId=="") {
					response.setMessage("卡号不能为空！");
				}else if(refundFee==null) {
					response.setMessage("退费审核金额不能为空！");
				}else if(loginName==null) {
					response.setMessage("员工编号不能为空！");
				}else if(vehiclePlate==null || vehiclePlate =="") {
					response.setMessage("车牌号不能为空！");
				}else if(phone==null) {
					response.setMessage("手机号码不能为空！");
				}else if(phone.length()!= 11) {
					response.setMessage("手机号码长度必须为11位！");
				}else {
					response.setStatus(1);
				}
				return response;
			}

			@Override
			public HqlBuilder toSelectHql() {
				HqlBuilder hql = new HqlBuilder("from " + "CardRefundDetail" +
						"  where 1=1 ");
				hql.append(" and cardId=:cardId",cardId);
				hql.append(" and status=:status",status);
				if(beforeDate!=null && afterDate!=null ) {
					hql.append(" and createTime");
					hql.append(" between :beforeDate",beforeDate);
					hql.append(" and :afterDate",afterDate);
				}
				return hql;
			}
	

}
