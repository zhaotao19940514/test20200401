/*
 * Date: 2015年4月10日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model.comm.protocol;

import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.css.model.comm.FileProtocolError;

/**
 * 状态名单的错误码枚举，公共错误 {@link FileProtocolError}
 * 
 * @author Peream <br>
 *         Create Time：2015年4月10日 上午11:13:42<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public enum CssServiceError
{
	CMD_NOT_EXIST(700, "命令不存在") {},
	VALID_FAILED(701, "请求内容校验失败") {},
	SAMPLE(800, "示例处理错误") {},;
	private final int code;
	private final String msg;

	private CssServiceError(int code, String msg)
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
