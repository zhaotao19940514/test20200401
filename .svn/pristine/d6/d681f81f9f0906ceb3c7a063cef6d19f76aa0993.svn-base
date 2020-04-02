/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model.comm.protocol;

import java.io.InputStream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.css.model.comm.FileProtocolError;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午12:03:17<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@XStreamAlias("request")
public class SampleCommRequest extends AbstractCssRequest
{
	private String name;
	private int age;

	public static SampleCommRequest from(InputStream in, String filename) throws ServiceHandleException
	{
		String suffix = extractSuffix(filename);
		switch (suffix)
		{
		case "json":
			return json2Request(SampleCommRequest.class, in);
		case "xml":
			return xml2Request(SampleCommRequest.class, in);
		default:
			throw FileProtocolError.TYPE_NOT_SUPPORT.toHandleException(suffix);
		}
	}

	public SampleCommRequest valid() throws ServiceHandleException
	{
		if (age < 10) throw CssServiceError.VALID_FAILED.toHandleException("你的年龄太小.");
		return this;
	}

	public String getName()
	{
		return name;
	}

	public int getAge()
	{
		return age;
	}

	public SampleCommRequest setName(String name)
	{
		this.name = name;
		return this;
	}

	public SampleCommRequest setAge(int age)
	{
		this.age = age;
		return this;
	}

}
