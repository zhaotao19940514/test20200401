package cn.com.taiji.css.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;

@Entity
@Table(name = "QTK_CSS_POS_REVERSE")
public class PosReverse extends StringUUIDEntity {

	private LocalDate createTime;
	private String referno;
	private Integer amount;
	private String result;
	private String message;
	private String operator;
	
	
	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Column(name = "CREATETIME")
	public LocalDate getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}
	@Column(name = "REFERNO")
	public String getReferno() {
		return referno;
	}
	public void setReferno(String referno) {
		this.referno = referno;
	}
	@Column(name = "AMOUNT")
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Column(name = "RESULT")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column(name = "RSP_MESSAGE")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
    

}
