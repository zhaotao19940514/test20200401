/**
 * @Title BaseObuInfoRequest.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午2:23:51
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.pub.StringTools;

/**
 * @ClassName BaseObuInfoRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 14:23:51
 * @E_mail yaonanlin@163.com
 */
public class BaseOfflineObuInfoRequest extends BaseModel {
	private String oldInfo;
	
	public String getOldInfo() {
		return oldInfo;
	}
	public void setOldInfo(String oldInfo) {
		this.oldInfo = oldInfo;
	}
	public void valid() throws ManagerException{
		if(!StringTools.hasText(oldInfo)){
			throw new ManagerException("原信息不能为空");
		}
	}
}

