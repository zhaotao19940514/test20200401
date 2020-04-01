/*
 * Date: 2016年3月9日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.comm.handler;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.net.http.handler.BinServiceListener;
import cn.com.taiji.common.model.file.BinServiceEvent;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月9日 下午3:48:20<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class BinServiceLogListener extends AbstractManager implements BinServiceListener
{
	@Override
	public void onServiceFinished(BinServiceEvent event)
	{
		//FIXME　save the log here
		logger.debug(event.toJson());
	}

}
