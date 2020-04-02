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

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.CancelRequest;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;

/**
 * @ClassName CancelManager.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
public interface CancelManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(CancelRequest queryModel,User user) throws ManagerException;

	CardCancelResponse doCancel( @Valid CancelRequest queryModel,User user) throws ManagerException, Exception;

	CardInfo findById(@Valid String id);

	AppCardStatusChangeResponse doPreCancel(@Valid PreCancelRequest queryModel, User loginUser) throws ManagerException;

	public CustomerInfo getCusInfo(String customerId);

	CardCancelResponse saveFile(String cardId, MultipartFile[] file) throws IOException;
}

