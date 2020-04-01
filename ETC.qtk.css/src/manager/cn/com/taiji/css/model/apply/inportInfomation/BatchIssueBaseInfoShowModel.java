package cn.com.taiji.css.model.apply.inportInfomation;

/**
 * 前端展示
 * @author lz
 *
 */
public class BatchIssueBaseInfoShowModel {

	private String userType;
	private String identType;
	private String identNo;
	private String vehiclePlate;
	private String vehiclePlateColor ;
	private String userMobile;
	private String respMessage;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getIdentType() {
		return identType;
	}
	public void setIdentType(String identType) {
		this.identType = identType;
	}
	public String getIdentNo() {
		return identNo;
	}
	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public String getVehiclePlateColor() {
		return vehiclePlateColor;
	}
	public void setVehiclePlateColor(String vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getRespMessage() {
		return respMessage;
	}
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	private String orderNo;
}
