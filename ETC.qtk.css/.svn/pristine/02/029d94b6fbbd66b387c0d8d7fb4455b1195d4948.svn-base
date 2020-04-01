/*
 * Date: 2015年4月9日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.com.taiji.common.manager.net.http.AbstractHttpFileCommHandleManager;
import cn.com.taiji.common.manager.net.http.HttpFileCommHandleManager;
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.manager.net.http.handler.BinFileServiceHandler;
import cn.com.taiji.common.model.file.FileProtocolConstant;
import cn.com.taiji.common.model.file.FileProtocolRequest;
import cn.com.taiji.common.model.file.FileProtocolResponse;
import cn.com.taiji.common.pub.IPTools;
import cn.com.taiji.css.manager.comm.handler.CommSampleServiceHandler;
import cn.com.taiji.css.manager.comm.handler.MyFileCommHandleListener;
import cn.com.taiji.css.manager.comm.handler.SampleServiceHandler;
import cn.com.taiji.css.model.comm.FileProtocolError;
import cn.com.taiji.css.model.comm.protocol.CssServiceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年4月9日 下午7:11:05<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class BinFileCommHandleManager extends AbstractHttpFileCommHandleManager implements HttpFileCommHandleManager
{
	@Autowired
	private MyFileCommHandleListener listener;
	private Map<CssServiceType, BinFileServiceHandler> handlers = Maps.newHashMap();
	@Autowired
	private SampleServiceHandler sampleHandler;
	@Autowired
	private CommSampleServiceHandler jsonSampleHandler;

	@Override
	protected FileProtocolResponse handleRequest(String filename, InputStream fs, HttpServletRequest request)
			throws ServiceHandleException
	{
		String[] strs = filename.split("_");
		CssServiceType serviceType = CssServiceType.fromName(strs[0]);
		if (serviceType == null) throw FileProtocolError.NOT_SUPPORT.toHandleException(filename);
		BinFileServiceHandler handler = handlers.get(serviceType);
		if (handler == null) throw new RuntimeException("该服务类型的handler未注册，请先在init方法中初始化：" + serviceType);
		return handler.handleRequest(filename, fs, request);
	}

	@Override
	protected boolean checkAuth(FileProtocolRequest protocol, HttpServletRequest request) throws IOException
	{
		String ip = IPTools.getIpAddr(request);
		logger.debug("FileProtocol 来自{}的请求...", ip);
		String auth = request.getHeader(FileProtocolConstant.AUTH_HEADER.getValue());
		if (!hasText(auth)) return true;
		return !auth.contains("failed");
	}

	@PostConstruct
	public void init()
	{
		addListener(listener);
		// init the serviceType handlers here
		handlers.put(CssServiceType.SAMPLE, sampleHandler);
		handlers.put(CssServiceType.COMMSAMPLE, jsonSampleHandler);
	}
}
