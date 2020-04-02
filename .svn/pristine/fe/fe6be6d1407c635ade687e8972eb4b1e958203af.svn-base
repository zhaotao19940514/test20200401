/**
 * @Title ObuCommandRequest.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午2:22:41
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;

/**
 * @ClassName ObuCommandRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 14:22:41
 * @E_mail yaonanlin@163.com
 */
public class ObuOfflineSysInfoCommandRequest extends BaseOfflineObuInfoRequest {
	private String newObuId;
	private String plateNum;
	private String plateColor;
	
	public String getPlateNum() {
		return plateNum;
	}
	public String getPlateColor() {
		return plateColor;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	public String getNewObuId() {
		return newObuId;
	}
	public void setNewObuId(String newObuId) {
		this.newObuId = newObuId;
	}
	public void valid() throws ManagerException{
		super.valid();
		if(!StringTools.hasText(newObuId)){
			throw new ManagerException("发行目标obuId不能为空");
		}
		if(!StringTools.hasText(plateColor)){
			throw new ManagerException("车牌颜色不能为空");
		}
		if(!StringTools.hasText(plateNum)){
			throw new ManagerException("车牌号不能为空");
		}
	}
}

