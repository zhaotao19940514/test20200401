/*
 * Date: 2015年6月25日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.comm.handler;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.manager.net.http.handler.AbstractBinServiceHandler;
import cn.com.taiji.common.model.file.FileProtocolResponse;
import cn.com.taiji.css.model.comm.FileProtocolError;
import cn.com.taiji.css.model.comm.protocol.CssServiceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年6月25日 下午8:31:06<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractBinFileServiceHandler extends AbstractBinServiceHandler
{
	protected final CssServiceType serviceType;

	protected AbstractBinFileServiceHandler(CssServiceType serviceType)
	{
		this.serviceType = serviceType;
	}

	protected static String getFileSuffix(String filename)
	{
		int index = filename.lastIndexOf('.');
		return filename.substring(index).toLowerCase();
	}

	@Override
	public final FileProtocolResponse handleRequest(String filename, InputStream fs, HttpServletRequest request)
			throws ServiceHandleException
	{
		boolean b = serviceType.getReqNamePattern().matcher(filename).matches();
		if (!b) throw FileProtocolError.REQ_NAME_ERR.toHandleException(filename);
		return handleInternal(filename, fs, request);
	}

	/**
	 * 实现请求的具体响应
	 * 
	 * @param filename
	 * @param fs
	 * @param request
	 * @return
	 * @throws ServiceHandleException
	 */
	protected abstract FileProtocolResponse handleInternal(String filename, InputStream fs, HttpServletRequest request)
			throws ServiceHandleException;

}
