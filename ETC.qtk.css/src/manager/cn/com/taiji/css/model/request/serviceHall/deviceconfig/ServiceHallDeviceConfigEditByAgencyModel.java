/**
 * @Title ServiceHallDeviceConfigEditByAgencyModel.java
 * @Package cn.com.taiji.css.model.request.serviceHall.deviceconfig
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月6日 下午8:41:22
 * @version V1.0
 */
package cn.com.taiji.css.model.request.serviceHall.deviceconfig;

import javax.validation.constraints.NotNull;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.dict.CssCardDeviceType;
import cn.com.taiji.qtk.entity.dict.CssObuDeviceType;
import cn.com.taiji.qtk.entity.dict.CssPosDeviceType;

/**
 * @ClassName ServiceHallDeviceConfigEditByAgencyModel
 * @Description TODO
 * @author yaonl
 * @date 2018年09月06日 20:41:22
 * @E_mail yaonanlin@163.com
 */
public class ServiceHallDeviceConfigEditByAgencyModel extends BaseModel {
	@NotNull
	private String agencyId;
	@NotNull
	private CssCardDeviceType cardDeviceType;
	private CssObuDeviceType obuDeviceType;
	private CssPosDeviceType posDeviceType;

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public CssCardDeviceType getCardDeviceType() {
		return cardDeviceType;
	}

	public CssObuDeviceType getObuDeviceType() {
		return obuDeviceType;
	}

	public CssPosDeviceType getPosDeviceType() {
		return posDeviceType;
	}

	public void setCardDeviceType(CssCardDeviceType cardDeviceType) {
		this.cardDeviceType = cardDeviceType;
	}

	public void setObuDeviceType(CssObuDeviceType obuDeviceType) {
		this.obuDeviceType = obuDeviceType;
	}

	public void setPosDeviceType(CssPosDeviceType posDeviceType) {
		this.posDeviceType = posDeviceType;
	}

	public void valid() {
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(agencyId)) {
			mve.addViolation("agencyId", "合作机构不能为空");
		}
//		if (cardDeviceType == null) {
//			mve.addViolation("cardDeviceType", "卡设备类型不能为空");
//		}
//		if (obuDeviceType == null) {
//			mve.addViolation("obuDeviceType", "OBU设备类型不能为空");
//		}
//		if (posDeviceType == null) {
//			mve.addViolation("posDeviceType", "POS设备类型不能为空");
//		}
		if (mve.hasViolation())
			throw mve;
	}
}
