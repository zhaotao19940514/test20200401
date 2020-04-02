/**
 * @Title PosCommandRequest.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月26日 下午2:33:18
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx;

import cn.com.taiji.qtk.entity.dict.CssPosTradeType;

/**
 * @ClassName PosCommandRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年07月26日 14:33:18
 * @E_mail yaonanlin@163.com
 */
public class PosConsumeCommandRequest extends PosBaseModel {
	public PosConsumeCommandRequest() {
		this.setPosTradeType(CssPosTradeType.CONSUME);
	}
}

