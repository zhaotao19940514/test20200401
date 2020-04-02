/**
 * @Title CardInfoResolveManagerImpl.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月8日 下午5:09:43
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.ocx.request.CardInfoResolveAppRequest;
import cn.com.taiji.css.model.ocx.request.CardInfoResolveAppResponse;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoResolveRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoResolveResponse;

/**
 * @ClassName CardInfoResolveManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年09月08日 17:09:43
 * @E_mail yaonanlin@163.com
 */
@Service
public class CardInfoResolveManagerImpl extends AbstractDsiCommManager implements CardInfoResolveManager {
	@Autowired
	private InqueryBinService inqueryBinService;
	
	@Override
	public AppAjaxResponse resolve(CardInfoResolveAppRequest req,User user) {
		return cardInfoResolveCommRequest(req,user);
	}

	private AppAjaxResponse cardInfoResolveCommRequest(CardInfoResolveAppRequest appRequest,User user) {
		CardInfoResolveRequest commRequest = new CardInfoResolveRequest();
		super.commSet(commRequest, user);
		setCommRequestParams(appRequest, commRequest);
		CardInfoResolveAppResponse appResponse = new CardInfoResolveAppResponse();
		try {
			CardInfoResolveResponse commResponse = inqueryBinService.cardInfoResolve(commRequest);
			appResponse.setFile0015Json(commResponse.getFile0015Json());
			appResponse.setFile0016Json(commResponse.getFile0016Json());
			appResponse.setFile000EJson(commResponse.getFile000EJson());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			return new AppAjaxResponse(new ManagerException("数据中心卡文件解析接口请求失败，请检查网络连通性！"));
		}
		return appResponse;
	}

	private void setCommRequestParams(CardInfoResolveAppRequest req, CardInfoResolveRequest request) {
		request.setFile0015Content(req.getFile0015CardResponse());
		request.setFile0016Content(req.getFile0016CardResponse());
		request.setFile000EContent(req.getFile000ECardResponse());
	}
	
}

