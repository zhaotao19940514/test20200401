/*
 * Date: 2011-6-18
 * author: Peream  (peream@gmail.com)
 *
 */
package tests;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.com.taiji.common.pub.FileCopyTools;
import cn.com.taiji.css.dao.HsqlDBInitDao;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-6-18 下午07:46:15<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@ContextConfiguration(locations = { "classpath:tests/hsqldb.xml" })
public class TestHsqldbClient extends MyBaseTest
{
	private static final String SPACE = "\t";
	@Autowired
	private HsqlDBInitDao dao;

	@Test
	public void queryForList()
	{
		String sql = "select * from acl_resource limit 500";
		List<Map<String, Object>> list = dao.queryForList(sql);
		int i = 1;
		System.out.println(getRowTitle(list));
		for (Map<String, Object> row : list)
		{
			System.out.print(i + SPACE);
			for (Entry<String, Object> en : row.entrySet())
			{
				System.out.print(en.getValue() + SPACE);
			}
			System.out.println();
			i++;
		}
		System.out.println();
	}

	@Test
	public void update() throws Exception
	{
		String sql = "delete from acl_role where id='sample' ";
		int count = dao.update(sql);
		System.out.println("受影响的记录数:" + count);
		// sleep 一段时间等待语句生效
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	public void init() throws Exception
	{
		File file = new File("resources/sql/hsqldb_init.sql");
		initFile(file);
		file = new File("resources/sql/init_data.sql");
		initFile(file);
		// sleep 一段时间等待初始化完成，很重要！！！,记得查看log，看完成没
		TimeUnit.SECONDS.sleep(10);
	}
	
	private void initFile(File file) throws IOException
	{
		List<String> list = FileCopyTools.copyToLines(file, "UTF-8", true);
		// List<String> list = Lists.newArrayList("delete from comm_ftp_line");
		for (String sql : list)
		{
			if (sql.startsWith("#") || sql.startsWith("--") || sql.startsWith("commit")) continue;
			System.out.println(sql);
			dao.execute(sql);
		}
	}

	private String getRowTitle(List<Map<String, Object>> rows)
	{
		if (rows.size() == 0) return "\n未查询到符合条件的记录";
		StringBuilder title = new StringBuilder("\n序号" + SPACE);
		for (Entry<String, Object> en : rows.get(0).entrySet())
		{
			title.append(en.getKey()).append(SPACE);
		}
		return title.toString();
	}
}
