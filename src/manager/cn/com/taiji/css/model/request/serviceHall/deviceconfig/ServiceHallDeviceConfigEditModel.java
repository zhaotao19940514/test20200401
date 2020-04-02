/**
 * @Title ServiceHallDeviceConfigEditModel.java
 * @Package cn.com.taiji.css.model.request.serviceHall.deviceconfig
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月6日 下午8:49:01
 * @version V1.0
 */
package cn.com.taiji.css.model.request.serviceHall.deviceconfig;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.dict.CssCardDeviceType;
import cn.com.taiji.qtk.entity.dict.CssObuDeviceType;
import cn.com.taiji.qtk.entity.dict.CssPosDeviceType;

/**
 * @ClassName ServiceHallDeviceConfigEditModel
 * @Description TODO
 * @author yaonl
 * @date 2018年09月06日 20:49:01
 * @E_mail yaonanlin@163.com
 */
public class ServiceHallDeviceConfigEditModel extends BaseModel {
	private String id;
	private CssCardDeviceType cardDeviceType;
	private CssObuDeviceType obuDeviceType;
	private CssPosDeviceType posDeviceType;
	public String getId() {
		return id;
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
	public void setId(String id) {
		this.id = id;
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
	
	public void valid() throws ManagerException {
		if(!StringTools.hasText(id))
			throw new ManagerException("网点设备配置实体id不能为空");
		MyViolationException mve = new MyViolationException();
//		if(cardDeviceType==null){
//			mve.addViolation("cardDeviceType", "卡设备类型不能为空");
//		}
//		if(obuDeviceType==null){
//			mve.addViolation("obuDeviceType", "obu设备类型不能为空");
//		}
//		if(posDeviceType==null){
//			mve.addViolation("posDeviceType", "pos设备类型不能为空");
//		}
		if(mve.hasViolation()) 
			throw mve;
	}
	
}

