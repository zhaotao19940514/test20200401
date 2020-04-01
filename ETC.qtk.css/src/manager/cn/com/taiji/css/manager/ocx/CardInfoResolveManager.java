/**
 * @Title CardInfoResolveManager.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月8日 下午5:09:32
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.ocx.request.CardInfoResolveAppRequest;

/**
 * @ClassName CardInfoResolveManager
 * @Description TODO
 * @author yaonl
 * @date 2018年09月08日 17:09:32
 * @E_mail yaonanlin@163.com
 */
public interface CardInfoResolveManager {
	AppAjaxResponse resolve(CardInfoResolveAppRequest req,User user);
}

