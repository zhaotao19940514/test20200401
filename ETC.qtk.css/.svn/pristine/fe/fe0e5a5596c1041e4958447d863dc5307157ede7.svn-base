/*
 * Date: 2016年3月21日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model.comm.protocol;

import cn.com.taiji.common.model.file.FileProtocolRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月21日 上午11:58:25<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractCssRequest extends AbstractCssProtocol
{
	/**
	 * 将请求转换成二进制请求，可以通过覆盖此方法实现请求转换
	 * 
	 * @param enableGzip
	 * @param filename
	 * @param enableMd5
	 * @return
	 */
	public FileProtocolRequest toRequest(boolean enableGzip, String filename, boolean enableMd5)
	{
		String suffix = extractSuffix(filename);
		switch (suffix)
		{
		case "json":
			return newJsonRequest(filename, enableGzip, this, enableMd5);
		case "xml":
			return newXmlRequest(filename, enableGzip, this, enableMd5);
		default:
			throw new RuntimeException("不支持此类型文件:" + suffix);
		}
	}
}
