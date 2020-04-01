package cn.com.taiji.css.model.administration.agency.permission;

public class AgencyPermissionModel {

	private String id;
	private  String agencyName;
	private  String permittedAgencyName;
	private  String cardTypeInfo;
	
	
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getPermittedAgencyName() {
		return permittedAgencyName;
	}
	public void setPermittedAgencyName(String permiteedAgencyName) {
		this.permittedAgencyName = permiteedAgencyName;
	}
	public String getCardTypeInfo() {
		return cardTypeInfo;
	}
	public void setCardTypeInfo(String cardTypeInfo) {
		this.cardTypeInfo = cardTypeInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
