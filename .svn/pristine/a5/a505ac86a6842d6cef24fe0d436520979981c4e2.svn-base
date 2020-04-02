/**
 * @Title PosCommandManager.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月25日 下午5:20:33
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;


import java.io.File;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.ocx.PosBaseModel;
import cn.com.taiji.css.model.ocx.PosBaseResponse;
import cn.com.taiji.css.model.ocx.PosConfirmRequest;
import cn.com.taiji.css.model.ocx.PosConfirmResponse;
import cn.com.taiji.qtk.entity.dict.CssPosTradeType;

/**
 * @ClassName PosCommandManager
 * @Description TODO
 * @author yaonl
 * @date 2018年07月25日 17:20:33
 * @E_mail yaonanlin@163.com
 */
public interface PosCommandManager {
	PosBaseResponse getCommand(PosBaseModel model,User user,CssPosTradeType specifyTradeType) throws ManagerException;
	PosConfirmResponse consumeConfirm(PosConfirmRequest model,User user) throws ManagerException;
	File posConfigIniCreate(Integer portNum,boolean isIcbc);
}

