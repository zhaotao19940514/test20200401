/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.card;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.UnlossRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName UnlossManager.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
public interface UnlossManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(UnlossRequest queryModel,User user) throws ManagerException;

	CardStatusChangeResponse doUnLossCard(@Valid String id,User user) throws Exception;
	
	public CardInfo findById(String id);

	void readFileContent(HttpServletRequest request) throws IOException;

	void readReChargeFileContent(HttpServletRequest request) throws IOException;

	void readCCBCancelContent(HttpServletRequest request);

	void deleteCancelDetail(String cardId) throws ManagerException;

	void readLkfContent(HttpServletRequest request);

	//void exportOutProvince(HttpServletRequest request);

}

