/*
 * Date: 2015年5月19日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.comm.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.net.http.FileCommHandleListener;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.file.AbstractFileProtocol;
import cn.com.taiji.common.model.file.FileProtocolRequest;
import cn.com.taiji.common.model.file.HandleFileProtocolEvent;
import cn.com.taiji.common.pub.FileCopyTools;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年5月19日 下午5:26:10<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class MyFileCommHandleListener extends AbstractManager implements FileCommHandleListener
{
	@Override
	public void fileCommHandled(HandleFileProtocolEvent event)
	{
		if (!logger.isTraceEnabled()) return;
		System.out.println("-------------------------------------------------------");
		handleProtocol(event.getRequest());
		handleProtocol(event.getResponse());
		// System.out.println("exec time:" + TimeTools.asHumanStr(event.getExecTime()));
		System.out.println("-------------------------------------------------------\n");
	}

	protected void handleProtocol(AbstractFileProtocol protocol)
	{
		String namePrefix = protocol instanceof FileProtocolRequest ? "request_" : "response_";
		String fileName = protocol.getFilename();
		File dest = new File(FileHelper.getTmpPath() + "/" + fileName);
		try
		{
			if (protocol.getTmpFile() == null)
			{
				InputStream in = protocol.getBinFile();
				FileCopyTools.copy(in, new FileOutputStream(dest));
				System.out.println("save " + namePrefix + " from inputstream：" + dest.length());
			}
			else
			{
				FileCopyTools.copy(protocol.getTmpFile(), dest);
				System.out.println("save " + namePrefix + " from tmp file:" + dest.length());
			}
		}
		catch (IOException e)
		{
			logger.error("", e);
		}
	}
}
