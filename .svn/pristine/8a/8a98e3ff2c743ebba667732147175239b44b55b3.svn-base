/*
 * Date: 2015年4月22日
 * author: Peream  (peream@gmail.com)
 *
 */
package tests;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

import cn.com.taiji.common.pub.FileCopyTools;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年4月22日 上午11:32:15<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class DB2SqlScript extends MyBaseTest
{
	@Test
	public void initSql() throws Exception
	{
		File file = new File("resources/sql/init_data.sql");
		String str = FileCopyTools.copyToStr(file);
		str = str.replace("commit;", "");
		str = str.replace(";", "\nGO");
		file = new File("resources/sql/init_data_db2.sql");
		FileCopyTools.copy(str, new FileWriter(file));
	}
}
