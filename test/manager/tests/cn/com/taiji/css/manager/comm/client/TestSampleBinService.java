/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.comm.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.manager.net.http.binclient.Response2FileHandler;
import cn.com.taiji.common.model.file.FileProtocolRequest;
import cn.com.taiji.common.pub.FileCopyTools;
import cn.com.taiji.common.pub.SecurityUtil;
import cn.com.taiji.common.pub.TimeTools;
import cn.com.taiji.common.pub.SecurityUtil.HashType;
import cn.com.taiji.css.model.comm.protocol.SampleCommRequest;
import cn.com.taiji.css.model.comm.protocol.SampleCommResponse;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午4:44:18<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestSampleBinService extends MyBaseBinCommTest
{
	@Autowired
	private SampleBinService service;

	@Test
	public void commSample() throws Exception
	{
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
		{
			SampleCommRequest req = new SampleCommRequest().setAge(16 + i).setName("安培");
			SampleCommResponse res = service.commSample(req);
			echo(res);
		}
		System.out.println("time:" + (System.currentTimeMillis() - begin));
	}

	@Test
	public void filePost() throws Exception
	{
		FileProtocolRequest request = new FileProtocolRequest();
		File file = new File("resources/test/1.jpg");
		String md5 = SecurityUtil.hash(file, HashType.MD5, true);
		long begin = System.currentTimeMillis();
		byte[] bytes = FileCopyTools.copyToByteArray(file);
		for (int i = 0; i < 1; i++)
		{
			if (i % 50 == 0)
				System.out.println("已执行:" + i + "\t耗时:" + TimeTools.asHumanStr(System.currentTimeMillis() - begin));
			if (i == 3)
				request.setAuthStr("xxxfailedxxx"); // failed test
			else
				request.setAuthStr("myTicketId");
			request.setBinFile(new ByteArrayInputStream(bytes));
			request.setFilename("SAMPLE_without_peream_md5.jpg");
			service.binSample(request, new Response2FileHandler("resources/test"));
			request.setMd5(md5);// md5
			request.setEnableGzip(false);
			request.setBinFile(new ByteArrayInputStream(bytes));
			request.setFilename("SAMPLE_enable_md5_pream.jpg");
			service.binSample(request, new Response2FileHandler("resources/test"));
		}
		System.out.println("time:" + TimeTools.asHumanStr(System.currentTimeMillis() - begin));
	}

	@Test
	public void bigPost() throws Exception
	{
		FileProtocolRequest request = new FileProtocolRequest();
		File file = new File("resources/ballot.tar.bz");
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 1; i++)
		{
			if (i % 50 == 0)
				System.out.println("已执行:" + i + "\t耗时:" + TimeTools.asHumanStr(System.currentTimeMillis() - begin));
			request.setBinFile(new FileInputStream(file));
			request.setFilename("SAMPLE_without_md5.tar.bz");
			service.binSample(request, new Response2FileHandler("resources/test"));
			request.setFilename("SAMPLE_enable_md5_peream.tar.bz");
			String md5 = SecurityUtil.hash(file, HashType.MD5, true);
			request.setBinFile(new FileInputStream(file));
			request.setMd5(md5);
			request.setEnableGzip(false);
			service.binSample(request, new Response2FileHandler("resources/test"));
		}
		System.out.println("time:" + TimeTools.asHumanStr(System.currentTimeMillis() - begin));
	}

}
