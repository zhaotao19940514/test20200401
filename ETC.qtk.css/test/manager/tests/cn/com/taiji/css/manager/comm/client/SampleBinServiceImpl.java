/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.comm.client;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.net.http.binclient.AbstractBinCommManager;
import cn.com.taiji.common.manager.net.http.binclient.AbstractBinResponseHandler;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.manager.net.http.binclient.Response2Modelhandler;
import cn.com.taiji.common.model.file.FileProtocolRequest;
import cn.com.taiji.css.model.comm.protocol.AbstractCssRequest;
import cn.com.taiji.css.model.comm.protocol.AbstractCssResponse;
import cn.com.taiji.css.model.comm.protocol.SampleCommRequest;
import cn.com.taiji.css.model.comm.protocol.SampleCommResponse;
import cn.com.taiji.css.model.comm.protocol.CssServiceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午4:37:39<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SampleBinServiceImpl extends AbstractBinCommManager implements SampleBinService
{
	private String authStr = "zhimakaimen";

	@Autowired
	public SampleBinServiceImpl(@Value("#{commProperties.cssServiceURL}") String cssServiceURL)
	{
		super(cssServiceURL);
	}

	@Override
	public SampleCommResponse commSample(SampleCommRequest request) throws IOException, ApiRequestException
	{
		String name = CssServiceType.COMMSAMPLE + "_REQ_" + getTimeMillStr() + ".json";
		return binPost(name, request, SampleCommResponse.class);
	}

	@Override
	public <T> T binSample(FileProtocolRequest request, AbstractBinResponseHandler<T> handler)
			throws IOException, ApiRequestException
	{
		return super.filePost(request, handler);
//		return null;
	}

	private <T extends AbstractCssResponse> T binPost(String filename, AbstractCssRequest req, Class<T> clazz)
			throws IOException, ApiRequestException
	{
		FileProtocolRequest request = req.toRequest(false, filename, true).setAuthStr(authStr);
		return super.filePost(request, new Response2Modelhandler<>(clazz));
	}
}
