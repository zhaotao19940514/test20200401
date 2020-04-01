/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.comm.client;

import java.io.IOException;

import cn.com.taiji.common.manager.net.http.binclient.AbstractBinResponseHandler;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.file.FileProtocolRequest;
import cn.com.taiji.css.model.comm.protocol.SampleCommRequest;
import cn.com.taiji.css.model.comm.protocol.SampleCommResponse;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午2:18:26<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface SampleBinService
{
	/**
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 *             http通信错误抛出IO异常
	 * @throws ApiRequestException
	 *             服务端处理错误抛出请求异常
	 */
	public SampleCommResponse commSample(SampleCommRequest request) throws IOException, ApiRequestException;

	public <T> T binSample(FileProtocolRequest request, AbstractBinResponseHandler<T> handler)
			throws IOException, ApiRequestException;
}
