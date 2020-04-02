
package cn.com.taiji.css.model.customerservice.finance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Transient;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CardAnnouncRecordView;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.CardType;
import cn.com.taiji.qtk.entity.dict.RefundType;
import cn.com.taiji.qtk.entity.dict.TradeType;


public class ExpenseRefundApplicationRequest extends JpaDateTimePageableDataRequest<CardAnnouncRecordView>  {
	
    
	private String id;
	
	private String loginId;
	
	private String loginCardId;
	
	private String cardId;
	// 车牌号
	private String vehiclePlate;
	// 车牌颜色
	private Integer vehiclePlateColor;
    //交易类型
	private TradeType tradeType;
	//卡类型
	private Integer cardType;
	// 银行卡号
	private String bankCard;
	// 开户行
	private String bank;
	
	private String enableTime;
	
	//操作人员工号
	private String userId;
	// 开户行户名
	private String bankUserName;
	// 户主联系方式
	private String phone;
	//退费类型
	private RefundType refundType;
	//详细描述
	private String detailedDescription;
	// 录入时间
	private String createTime;
	// 交易时间
	private String tradeTime;
	// 交易金额
	private Long tradeFee;
	//出口站名
	private String  exName;
	//审核状态
	private Integer status;
	
	private String pathid;
	
	private Long refundFee;
	
	private String beforeDate;
	private String afterDate;
	
	private String fileName;
	
	private String filePath;
	
	private String stTime;
	
	private String edTime;
	
	
	
	
	
	

	public String getStTime() {
		return stTime;
	}



	public void setStTime(String stTime) {
		this.stTime = stTime;
	}



	public String getEdTime() {
		return edTime;
	}



	public void setEdTime(String edTime) {
		this.edTime = edTime;
	}



	/**
	 * @return loginId
	 */
	public String getLoginId() {
		return loginId;
	}



	/**
	 * @param loginId 要设置的 loginId
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}



	/**
	 * @return loginCardId
	 */
	public String getLoginCardId() {
		return loginCardId;
	}



	/**
	 * @param loginCardId 要设置的 loginCardId
	 */
	public void setLoginCardId(String loginCardId) {
		this.loginCardId = loginCardId;
	}



	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}



	/**
	 * @param fileName 要设置的 fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	/**
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}



	/**
	 * @param filePath 要设置的 filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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



	public Long getRefundFee() {
		return refundFee;
	}



	public void setRefundFee(Long refundFee) {
		this.refundFee = refundFee;
	}



	public String getPathid() {
		return pathid;
	}



	public void setPathid(String pathid) {
		this.pathid = pathid;
	}



	public ExpenseRefundApplicationRequest() {
		this.orderBy="createTime";
		this.desc=false;
	}
	
	
	
	@Transient
	public String getBank() {
		return bank;
	}




	public void setBank(String bank) {
		this.bank = bank;
	}




	public Integer getCardType() {
		return cardType;
	}




	public void setCardType(Integer cardType) {
		this.cardType = cardType;
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
	
	@Transient
	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	@Transient
	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}

	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}
	
	/*public AuditStatusType getAuditStatusType() {
		return auditStatusType;
	}

	public void setAuditStatusType(AuditStatusType auditStatusType) {
		this.auditStatusType = auditStatusType;
	}*/
	
	


	
	public String getEnableTime() {
		return enableTime;
	}


	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}

	
	
	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getTradeTime() {
		return tradeTime;
	}


	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}



	public Long getTradeFee() {
		return tradeFee;
	}


	public void setTradeFee(Long tradeFee) {
		this.tradeFee = tradeFee;
	}


	public String getExName() {
		return exName;
	}


	public void setExName(String exName) {
		this.exName = exName;
	}

	
	


	@Transient
	public String getBankCard() {
		return bankCard;
	}




	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	
	
	

	@Transient
	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}



	@Transient
	public String getBankUserName() {
		return bankUserName;
	}




	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}



	@Transient
	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}



	@Transient
	public RefundType getRefundType() {
		return refundType;
	}



	public void setRefundType(RefundType refundType) {
		this.refundType = refundType;
	}



	@Transient
	public String getDetailedDescription() {
		return detailedDescription;
	}




	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}




	@Transient
	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}


	public void validate() {
		MyViolationException mve = new MyViolationException();
		if(cardId==null) {
			mve.addViolation("cardId", "卡号不能为空！");
		}
		if (mve.hasViolation()) throw mve;
	}

	public ExpenseRefundApplicationResponse validate(ExpenseRefundApplicationRequest request,CardInfo cardInfo) {
		ExpenseRefundApplicationResponse response=new ExpenseRefundApplicationResponse();
		if(CardType.isAccountCardType(cardInfo.getCardType()) ||  cardInfo.getStatus()==4 ||  cardInfo.getStatus()==5 ||  cardInfo.getStatus()==9) {
			if(request.getBankCard()==null || request.getBankCard().trim().length()<=0) {
				response.setMessage("银行卡号不能为空！");
				response.setStatus(-1);
				return response;
			}else if(request.getBank()==null || request.getBank().trim().length()<=0) {
				response.setMessage("开户行不能为空！");
				response.setStatus(-1);
				return response;
			}else if(request.getBankUserName()==null || request.getBankUserName().trim().length()<=0) {
				response.setMessage("银行卡开户姓名不能为空！");
				response.setStatus(-1);
				return response;
			}
			
		}
		if(request.getPhone()==null || request.getPhone().trim().length()<=0) {
			response.setMessage("联系方式不能为空！");
			response.setStatus(-1);
			return response;
		}
		response.setStatus(1);
		return response;
	}
	
	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CardAnnouncRecordView " +
				"  where 1=1 ");
		hql.append(" and cardId =:cardId", cardId);
		hql.append(" and vehiclePlate =:vehiclePlate", vehiclePlate);
		LocalDateTime[] times = toDateTimes();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		if(times[0]!=null)
		{
			hql.append(" and enTime>=:createtimeMin",
					(times[0].format(formatter)));
		}
		if(times[1]!=null)
		{
			hql.append(" and exTime<=:createtimeMax",
					(times[1].format(formatter)));
		}
		return hql;		
	}

}
