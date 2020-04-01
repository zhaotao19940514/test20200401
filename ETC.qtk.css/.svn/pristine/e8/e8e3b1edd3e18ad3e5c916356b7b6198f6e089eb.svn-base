/*
 * Date: 2012-3-7
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.css.repo.jpa.ScheduleLogRepo;
import cn.com.taiji.css.repo.request.system.ScheduleLogPageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2012-3-7 下午3:12:30<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("scheduleLogManager")
public class ScheduleLogManagerImpl extends AbstractManager implements ScheduleLogManager
{
	@Autowired
	private ScheduleLogRepo logRepo;

	@Override
	public Pagination queryPage(ScheduleLogPageRequest req)
	{
		AssertUtil.notNull(req);
		return logRepo.page(req);
	}

}
