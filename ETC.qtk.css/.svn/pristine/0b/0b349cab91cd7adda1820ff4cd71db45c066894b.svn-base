/**
 * @Title PosUtil.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月25日 下午8:22:51
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.model.ocx.PosBaseModel;
import cn.com.taiji.qtk.entity.dict.CssPosTradeType;

/**
 * @ClassName PosUtil
 * @Description TODO
 * @author yaonl
 * @date 2018年07月25日 20:22:51
 * @E_mail yaonanlin@163.com
 */
@Component
public class PosUtil {
	private static String platId;
	private static String operId;
	
	public static String getCommand(PosBaseModel model){
		model.setPlatId(platId);
		model.setOperId(operId);
		StringBuilder command = new StringBuilder();
		String[] propertyNameSequence = PosBaseModel.propertyNameSequence();
		for (int i = 0; i < propertyNameSequence.length; i++) {
			String propertyName = propertyNameSequence[i];
			String propertyValue = model.getPropertyValue(String.class, propertyName);
			if(StringTools.hasText(propertyValue)){
				command.append(propertyValue);
			}
			if(i!=propertyNameSequence.length-1){
				command.append(",");
			}
		}
		return command.toString();
	}
	
	public static PosBaseModel toModel(String command) throws ManagerException{
		String[] params = command.split(",");
		if(params.length!=17 && params.length!=21) throw new ManagerException("指令格式长度错误:length "+params.length);
		String[] propertyNameSequence = PosBaseModel.propertyNameSequence();
		PosBaseModel model = new PosBaseModel();
		for (int i = 0; i < params.length; i++) {
			String propertyName = propertyNameSequence[i];
			String propertyValue = params[i];
			model.setPropertyValue(propertyName, propertyValue);
		}
		return model;
	}
	@Value("#{commProperties.platId}")
	private void setPlatId(String platId) {
		PosUtil.platId = platId;
	}
	@Value("#{commProperties.operId}")
	private void setOperId(String operId) {
		PosUtil.operId = operId;
	}

	public static void main(String[] args) {
		PosBaseModel model = new PosBaseModel();
		model.setPropertyValue("amount", 1234L);
//		model.setAmount(1234L);
		model.setPropertyValue("posTradeType", CssPosTradeType.CONSUME);
		String command = getCommand(model);
		System.out.println(command);
	}
	
	
}

