package cn.com.taiji.css.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.css.entity.dict.OperationType;

@Entity
@Table(name = "QTK_ICBC_CONTRACT")
public class ICBCContract extends StringUUIDEntity {

	private LocalDateTime createTime;
	private LocalDateTime opTime;
	private String etcCardId;
	private String bankCardId;
	private OperationType opType;
	private String staffId;
	private String userID;
	private String opOrder;
	private String opResult;
	

	@Column(name = "OP_ORDER")
	public String getOpOrder() {
		return opOrder;
	}
	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}
	@Column(name = "OP_RESULT")
	public String getOpResult() {
		return opResult;
	}
	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}
	@Column(name = "CREATE_TIME")
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	@Column(name = "OP_TIME")
	public LocalDateTime getOpTime() {
		return opTime;
	}
	public void setOpTime(LocalDateTime opTime) {
		this.opTime = opTime;
	}
	@Column(name = "ETC_CARD_ID")
	public String getEtcCardId() {
		return etcCardId;
	}
	public void setEtcCardId(String etcCardId) {
		this.etcCardId = etcCardId;
	}
	@Column(name = "BANK_CARD_ID")
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	@Column(name = "OP_TYPE")
	@Enumerated(EnumType.STRING)
	public OperationType getOpType() {
		return opType;
	}
	public void setOpType(OperationType opType) {
		this.opType = opType;
	}
	@Column(name = "STAFF_ID")
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	@Column(name = "USER_ID")
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
    
	
    

}
