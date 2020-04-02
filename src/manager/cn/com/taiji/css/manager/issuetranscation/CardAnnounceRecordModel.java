package cn.com.taiji.css.manager.issuetranscation;

import cn.com.taiji.common.model.BaseModel;

public class CardAnnounceRecordModel extends BaseModel{
    private String cardId;
    private String vehiclePlate;
    private String consumptionType;
    private String enTime;
    private String enName;
    private String exTime;
    private String exName;
    private Long preBalance;
    private Long postBalance;
    private Long fee;
    private String compareTime;
    private String passId;
	
    public String getConsumptionType() {
		return consumptionType;
	}
	public void setConsumptionType(String consumptionType) {
		this.consumptionType = consumptionType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public String getEnTime() {
		return enTime;
	}
	public void setEnTime(String enTime) {
		this.enTime = enTime;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getExTime() {
		return exTime;
	}
	public void setExTime(String exTime) {
		this.exTime = exTime;
	}
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	public Long getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(Long preBalance) {
		this.preBalance = preBalance;
	}
	public Long getPostBalance() {
		return postBalance;
	}
	public void setPostBalance(Long postBalance) {
		this.postBalance = postBalance;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	
	public String getCompareTime() {
		return compareTime;
	}
	public void setCompareTime(String compareTime) {
		this.compareTime = compareTime;
	}
	public String getPassId() {
		return passId;
	}
	public void setPassId(String passId) {
		this.passId = passId;
	}
	
	
//	@Override
//	public int compareTo(CardAnnounceRecordModel o) {
//		SimpleDateFormat sdf = new SimpleDateFormat();
//		if (o.getExTime() != null) {
//			try {
//				if (after(sdf.parse(this.getExTime()), sdf.parse(o.getExTime()))) {
//					return -1;
//				} else if (after(sdf.parse(o.getExTime()), sdf.parse(this.getExTime()))) {
//					return 1;
//				}
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//		return 0;
//	}
//    
//	private  boolean after(Date date1,Date date2){
//    	Calendar c1 = Calendar.getInstance();
//    	c1.setTime(date1);
//    	Calendar c2 = Calendar.getInstance();
//    	c2.setTime(date2);
//    	return c2.after(c1);
//    }

}
