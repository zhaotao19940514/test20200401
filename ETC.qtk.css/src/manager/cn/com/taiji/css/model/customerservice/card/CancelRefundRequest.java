/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CardCancelRefund;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class CancelRefundRequest extends JpaDateTimePageableDataRequest<CardCancelRefund> {
	private String cardId;
	private String beforeDate;
	private String afterDate;
	private Integer bankType;
	private Integer cusType;
	private String cusName;
	private String cusTel;
	private String province;
	private String sell;
	private String bankCardId;
	private String vehiclePlate;
	private Integer vehicleColor;
	private String vehicleId;
	private Integer refundType;
	private Integer searchExportMark;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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
	public Integer getBankType() {
		return bankType;
	}
	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}
	public Integer getCusType() {
		return cusType;
	}
	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusTel() {
		return cusTel;
	}
	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSell() {
		return sell;
	}
	public void setSell(String sell) {
		this.sell = sell;
	}
	
	
	public Integer getSearchExportMark() {
		return searchExportMark;
	}
	public void setSearchExportMark(Integer searchExportMark) {
		this.searchExportMark = searchExportMark;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public Integer getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(Integer vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public Integer getRefundType() {
		return refundType;
	}
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();
		if(cardId == null&&null==vehiclePlate&&null==vehicleColor&&afterDate == null&&null==beforeDate&&null==refundType) mve.addViolation("cardId", "请填写卡号");
		if(null!=vehiclePlate&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(null==vehiclePlate&&null!=vehicleColor) mve.addViolation("vehiclePlate", "请填写车牌号");
		if(beforeDate == null&&null!=afterDate) mve.addViolation("beforeDate", "注销日期不能为空");
		if(afterDate == null&&null!=beforeDate) mve.addViolation("afterDate", "注销日期不能为空");
		if (mve.hasViolation()) throw mve;
		if(null!=vehiclePlate) {
			vehicleId = vehiclePlate+"_"+vehicleColor;
		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CardCancelRefund " +
				"  where 1=1 ");
		hql.append(" and cardId=:cardId", cardId);
		hql.append(" and vehicleId=:vehicleId", vehicleId);
		hql.append(" and agencyId not in('52010102018','52010102002','52010102007','52010102005')");
		if(null!=refundType&&refundType!=9&&searchExportMark==0) {
			hql.append(" and refundType=:refundType", RefundDetailType.valueOfCode(refundType));
		}
		if(null!=beforeDate) {
			hql.append(" and to_date(substr(cancelTime,1,10),'yyyy-MM-dd')");
			hql.append(" between to_date(:beforeDate,'yyyy-MM-dd') ",beforeDate);
			hql.append(" and to_date(:afterDate,'yyyy-MM-dd') ",afterDate);
		}
		if(searchExportMark==1) {
			hql.append(" and bankCardId is not null");
			hql.append(" and refundBalance>0");
			hql.append(" and (fileName is null or (fileName is not null and attachStatus=2))");
			hql.append(" and refundType=:refundType", RefundDetailType.GLYQR);
		}
		hql.append(" order by cancelTime desc");
		return hql;
	}
}

