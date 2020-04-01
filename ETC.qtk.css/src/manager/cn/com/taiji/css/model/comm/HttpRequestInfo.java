/*
 * Date: 2014年5月14日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model.comm;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.pub.IPTools;

/**
 * 请求客户端的信息，有些信息是登录成功时保存在服务端的（UUID,device）.
 * 
 * @author Peream <br>
 *         Create Time：2014年5月14日 上午11:29:34<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class HttpRequestInfo extends BaseModel
{
	private String ip;

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	/**
	 * 可以通过参数传入一些存在服务端的信息【参考HZ.apply】
	 * 
	 * @param request
	 * @return
	 */
	public static HttpRequestInfo newInstance(HttpServletRequest request)
	{
		HttpRequestInfo info = new HttpRequestInfo();
		String ip = IPTools.getIpAddr(request);
		info.setIp(ip);
		return info;
	}
}
