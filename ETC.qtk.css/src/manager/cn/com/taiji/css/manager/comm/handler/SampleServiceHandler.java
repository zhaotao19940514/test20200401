/*
 * Date: 2015年9月22日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.comm.handler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.file.FileProtocolConstant;
import cn.com.taiji.common.model.file.FileProtocolResponse;
import cn.com.taiji.common.pub.FileCopyTools;
import cn.com.taiji.css.model.comm.protocol.CssServiceError;
import cn.com.taiji.css.model.comm.protocol.CssServiceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年9月22日 下午3:01:24<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SampleServiceHandler extends AbstractBinFileServiceHandler
{
	public SampleServiceHandler()
	{
		super(CssServiceType.SAMPLE);
	}

	@Override
	protected FileProtocolResponse handleInternal(String filename, InputStream fs, HttpServletRequest request)
			throws ServiceHandleException
	{
		int ran = new Random().nextInt(10);
		// 使用异常的方式返回处理失败的请求
		if (ran > 8) throw CssServiceError.SAMPLE.toHandleException(ran);
		FileProtocolResponse rs = new FileProtocolResponse();
		// 服务端处理程序决定是否要生成临时文件，生成临时文件时记得设置response中的tmpFile属性
		try
		{
			File reqFile = generateTmpFile(filename);
			FileCopyTools.copy(fs, new FileOutputStream(reqFile));
			logger.info("请求文件的大小:{},此处进行解析处理等操作", reqFile.length());
			if (!reqFile.delete()) logger.error("delete file error:{}", reqFile.getAbsolutePath());
			ran = new Random().nextInt(2);
			String resFileName = ran > 0 ? "lib/ojdbc7.jar" : "jsp/index.jsp";
			File resFile = new File(FileHelper.getWebappPath() + "/WEB-INF/" + resFileName);
			if (filename.contains("peream"))
			{
				resFileName = "eclipse.7z";
				resFile = new File("D:/eclipse.7z");
			}
			long resSize = resFile.length();
			rs.setFilename("RESPONSE_" + filename + resFileName.replace("/", "_"));
			if (resSize > 0 && resSize < FileProtocolConstant.IN_MEM_MAXSIZE.getSize())
			{
				byte[] bytes = FileCopyTools.copyToByteArray(resFile);
				rs.setBinFile(new ByteArrayInputStream(bytes));
			}
			else
			{
				File file = generateTmpFile(filename);
				FileCopyTools.copy(new FileInputStream(resFile), new FileOutputStream(file));
				rs.setBinFile(new FileInputStream(file));
				rs.setTmpFile(file);// 设置临时文件，父类中会自动删除
			}
		}
		catch (IOException e)
		{
			logger.error("", e);
			throw new RuntimeException(e.getMessage());
		}
		return rs;
	}

}
