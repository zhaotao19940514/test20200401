/*
 * Date: 2016年3月20日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model.comm.protocol;

import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.model.file.FileProtocolResponse;
import cn.com.taiji.css.model.comm.FileProtocolError;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月20日 上午11:38:48<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractCssResponse extends AbstractCssProtocol
{
	/**
	 * 将结果以FileProtocolResponse的方式返回
	 * 
	 * @param resFileName
	 * @return
	 * @throws ServiceHandleException
	 */
	public FileProtocolResponse toResponse(String resFileName) throws ServiceHandleException
	{
		String suffix = extractSuffix(resFileName);
		switch (suffix)
		{
		case "json":
			return toJsonResponse(resFileName);
		case "xml":
			return toXmlResponse(resFileName);
		default:
			throw FileProtocolError.TYPE_NOT_SUPPORT.toHandleException(suffix);
		}
	}
}
