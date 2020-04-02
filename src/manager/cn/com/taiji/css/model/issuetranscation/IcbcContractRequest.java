package cn.com.taiji.css.model.issuetranscation;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.css.entity.ICBCContract;

public class IcbcContractRequest extends JpaDateTimePageableDataRequest<ICBCContract>  {

	private String userName;
	private String userId;
	private String etcCardId;
	private String bankCardId;
	private String plate;
	private Integer color;
	private String userIdType;
	private String userPhoneNo;
	private Integer referNo;  //pos机交易检索号

	
	
	



	public Integer getReferNo() {
		return referNo;
	}



	public void setReferNo(Integer referNo) {
		this.referNo = referNo;
	}



	public String getPlate() {
		return plate;
	}



	public void setPlate(String plate) {
		this.plate = plate;
	}




	public Integer getColor() {
		return color;
	}



	public void setColor(Integer color) {
		this.color = color;
	}



	public String getUserIdType() {
		return userIdType;
	}



	public void setUserIdType(String userIdType) {
		this.userIdType = userIdType;
	}



	public String getUserPhoneNo() {
		return userPhoneNo;
	}



	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getEtcCardId() {
		return etcCardId;
	}



	public void setEtcCardId(String etcCardId) {
		this.etcCardId = etcCardId;
	}



	public String getBankCardId() {
		return bankCardId;
	}



	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}



	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from ICBCContract where 1=1");
		hql.append(" and etcCardId =:etcCardId",etcCardId);
		hql.append(" and bankCardId =:bankCardId",bankCardId);
		System.out.println(hql);
		return hql;
	}
		

}
