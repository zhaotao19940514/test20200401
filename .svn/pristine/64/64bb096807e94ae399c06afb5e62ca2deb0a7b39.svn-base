/*
 * Date: 2015年4月10日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model.comm;

import cn.com.taiji.common.manager.net.http.ServiceHandleException;

/**
 * 公共错误，每个命令还有自己的错误。
 * 
 * @author Peream <br>
 *         Create Time：2015年4月10日 上午11:13:42<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public enum FileProtocolError
{
	TYPE_NOT_SUPPORT(945, "不支持此类文件") {},
	RES_FILE_NOT_EXIST(946, "响应文件不存在") {},
	REQ_DATA_ERR(947, "请求数据格式错误") {},
	REQ_NAME_ERR(948, "请求文件名格式不正确") {},
	NOT_SUPPORT(949, "协议中未定义此类文件名") {};
	private final int code;
	private final String msg;

	private FileProtocolError(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public ServiceHandleException toHandleException(Object append)
	{
		return new ServiceHandleException(msg + (append != null ? ":" + append : ""), code);
	}

	public int getCode()
	{
		return code;
	}

	public String getMsg()
	{
		return msg;
	}

}
