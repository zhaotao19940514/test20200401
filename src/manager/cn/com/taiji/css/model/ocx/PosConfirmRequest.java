/**
 * @Title PosConfirmRequest.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月26日 下午3:00:23
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;

/**
 * @ClassName PosConfirmRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年07月26日 15:00:23
 * @E_mail yaonanlin@163.com
 */
public class PosConfirmRequest extends PosBaseModel {
	//obuId 在 父类中
	private String command;// 执行的command
	private String posResponse;// Pos机返回结果
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getPosResponse() {
		return posResponse;
	}
	public void setPosResponse(String posResponse) {
		this.posResponse = posResponse;
	}
	@Override
	public void valid() throws ManagerException{
		super.valid();
		if(!StringTools.hasText(posResponse)){
			throw new ManagerException("返回结果不能为空");
		}
		String[] split = posResponse.split(",");
		if(split.length!=17 && split.length!=21){
			throw new ManagerException("响应长度不正确");
		}
		if(!StringTools.hasText(split[10])){
			throw new ManagerException("返回码不能为空");
		}
	}
	
}

