/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.apply.baseinfo;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.customerservice.cardobuquery.ObuRequest;
import cn.com.taiji.qtk.entity.OBUInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface ObuManager {

	/**
	 * @param queryModel
	 * @return
	 */
	LargePagination<OBUInfo> queryPage(ObuRequest queryModel);

	public OBUInfo findByObuId(String obuId);


}

