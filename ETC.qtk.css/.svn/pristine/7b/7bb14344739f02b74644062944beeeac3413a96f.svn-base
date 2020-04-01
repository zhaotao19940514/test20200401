/**
 * @Title OperateLogManager.java
 * @Package cn.com.taiji.css.manager.system
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月28日 下午1:42:11
 * @version V1.0
 */
package cn.com.taiji.css.manager.system;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.OperateLog;
import cn.com.taiji.css.model.receipt.ReceiptBaseModel;
import cn.com.taiji.css.model.system.request.CssOperateLogRequest;

/**
 * @ClassName OperateLogManager
 * @Description TODO
 * @author yaonl
 * @date 2018年07月28日 13:42:11
 * @E_mail yaonanlin@163.com
 */
public interface OperateLogManager {
	void saveLog(OperateLog operateLog);
	Pagination queryPage(CssOperateLogRequest request);
	LargePagination<OperateLog> queryLargePage(CssOperateLogRequest request);
	OperateLog findById(String id);
	ReceiptBaseModel toReceiptModel(String logId);
}

