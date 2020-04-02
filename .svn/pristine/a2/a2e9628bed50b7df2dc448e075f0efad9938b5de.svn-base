package cn.com.taiji.css.dao.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 
 * @author Peream <br>
 *         Create Time：2010-3-18 下午02:13:44<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class MyBaseJdbcDao extends JdbcDaoSupport
{
	@Autowired
	public void setJdbcDataSource(DataSource dataSource)
	{
		super.setDataSource(dataSource);
	}
}
