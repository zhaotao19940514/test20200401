/*
 * Date: 2013年11月5日
 * author: Peream  (peream@gmail.com)
 *
 */
package tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import cn.com.taiji.common.pub.json.JsonTools;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年11月5日 下午5:22:30<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class CommonTest extends MyBaseTest
{
	@Test
	public void all() throws Exception
	{
		Map<Integer, Data> maps = new HashMap<Integer, Data>(15000000);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
				"resources/ballot.txt")), "UTF-8"));
		long begin = System.currentTimeMillis();
		for (int i = 1; i < 15000000; i++)
		{
			// maps.put(i, new Data("01234567890123", "陈培安", i));
			writer.write(new Data("01234567890123", "陈培安", i).toJson());
			writer.newLine();
			if (i % 50000 == 0)
			{
				writer.flush();
				System.out.println("time used:" + (System.currentTimeMillis() - begin) + "\ti=" + i);
			}
		}
		writer.close();
		System.out.println("time used:" + (System.currentTimeMillis() - begin));
		begin = System.currentTimeMillis();
		Data data = maps.get(810216);
		System.out.println(data);
		System.out.println("get used:" + (System.currentTimeMillis() - begin));
		TimeUnit.MINUTES.sleep(2);
	}

	@Test
	public void readFromFile() throws Exception
	{
		long begin = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/ballot.txt"),
				"UTF-8"));
		String line = null;
		Map<Integer, Data> map = new HashMap<Integer, Data>(15000000);
		int i = 0;
		while ((line = reader.readLine()) != null)
		{
			i++;
			Data data = JsonTools.json2Object(line, Data.class);
			map.put(data.getList(), data);
			if (i % 50000 == 0)
			{
				System.out.println("time used:" + (System.currentTimeMillis() - begin) + "\ti=" + i);
			}
		}
		reader.close();
		System.out.println("time used:" + (System.currentTimeMillis() - begin));
		System.out.println(map.size());
		TimeUnit.MINUTES.sleep(2);
	}

	@Test
	public void atomicIntger()
	{
		AtomicInteger ai = new AtomicInteger(0);
		System.out.println(ai.get());
		ai.incrementAndGet();
		System.out.println(ai.get());
		ai.decrementAndGet();
		System.out.println(ai.get());
		ai.decrementAndGet();
		System.out.println(ai.get());
		ai.incrementAndGet();
		System.out.println(ai.get());
	}

}
