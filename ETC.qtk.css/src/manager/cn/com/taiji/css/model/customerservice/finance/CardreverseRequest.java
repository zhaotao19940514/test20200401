package cn.com.taiji.css.model.customerservice.finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.ChargeDetail;

public class CardreverseRequest extends JpaDateTimePageableDataRequest<ChargeDetail> {

	//端口号
	private String com;
	// 卡号
	private String cardId;
	//交易金额           ---单位：分
	private Long fee;
	private Long rechargeAmount; 
	//交易流水号
	private String chargeId;
	//合作机构编号    ---目前默认为‘020000000000’
	private String terminalId;
	//冲正初始化指令结果
	private String initializeResponse;
	//支付方式
	private Integer tradeType;
	//写卡指令
	private String command;
	//交易日期   年月日
	private String tradeDate;
	
	//交易时间    时分秒
	private String tradeTime;
	
	//机构
	private String agencyId;
	
	
	private String cosResponse;
	

	public String getCom() {
		return com;
	}


	public void setCom(String com) {
		this.com = com;
	}




	public String getCardId() {
		return cardId;
	}




	public void setCardId(String cardId) {
		this.cardId = cardId;
	}




	public Long getFee() {
		return fee;
	}




	public void setFee(Long fee) {
		this.fee = fee;
	}




	public String getChargeId() {
		return chargeId;
	}




	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}




	public String getTerminalId() {
		return terminalId;
	}




	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}




	public String getInitializeResponse() {
		return initializeResponse;
	}




	public void setInitializeResponse(String initializeResponse) {
		this.initializeResponse = initializeResponse;
	}




	public Integer getTradeType() {
		return tradeType;
	}




	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}




	public String getCommand() {
		return command;
	}




	public void setCommand(String command) {
		this.command = command;
	}

	
	



	public String getTradeDate() {
		return tradeDate;
	}




	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}




	public String getAgencyId() {
		return agencyId;
	}




	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}


	

	public Long getRechargeAmount() {
		return rechargeAmount;
	}




	public void setRechargeAmount(Long rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	
	


	public String getCosResponse() {
		return cosResponse;
	}




	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}

	


	public String getTradeTime() {
		return tradeTime;
	}


	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}


	@Override
	public HqlBuilder toSelectHql() {
		String tradeDates=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		HqlBuilder hql = new HqlBuilder("from " + "ChargeDetail" +
				"  where 1=1 ");
		hql.append(" and cardId =:cardId",cardId);
		hql.append(" and agencyId =:agencyId",agencyId);
		hql.append(" and status =1");
		hql.append(" and tradeDate =:tradeDate",tradeDates);
		hql.append(" order by tradeTime desc");
		
		return hql;
	}

}

