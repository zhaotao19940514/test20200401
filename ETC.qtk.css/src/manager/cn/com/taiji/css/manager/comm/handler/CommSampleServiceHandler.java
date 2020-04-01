/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.comm.handler;

import java.io.InputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.model.file.FileProtocolResponse;
import cn.com.taiji.css.model.comm.protocol.SampleCommRequest;
import cn.com.taiji.css.model.comm.protocol.SampleCommResponse;
import cn.com.taiji.css.model.comm.protocol.CssServiceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午1:43:52<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class CommSampleServiceHandler extends AbstractBinFileServiceHandler
{
	public CommSampleServiceHandler()
	{
		super(CssServiceType.COMMSAMPLE);
	}

	@Override
	protected FileProtocolResponse handleInternal(String filename, InputStream fs, HttpServletRequest request)
			throws ServiceHandleException
	{
		SampleCommRequest req = SampleCommRequest.from(fs, filename).valid();//验证
		String resName = serviceType.name() + "_RES_" + getTimeMillStr() + getFileSuffix(filename);
		SampleCommResponse res = new SampleCommResponse().setTime(Calendar.getInstance())
				.setInfo(toLogString("您好{},明年你就{}岁了", req.getName(), req.getAge() + 1));
		return res.toResponse(resName);
	}

}
