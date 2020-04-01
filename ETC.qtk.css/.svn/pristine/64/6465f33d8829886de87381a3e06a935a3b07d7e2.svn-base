/*
 * Date: 2011-6-18
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.dao.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.taiji.css.dao.HsqlDBInitDao;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-6-18 下午07:39:45<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Repository("hsqlDBInitDao")
public class HsqlDBInitDaoJdbc extends MyBaseJdbcDao implements HsqlDBInitDao
{
	public void execute(String sql)
	{
		getJdbcTemplate().execute(sql);
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql)
	{
		return getJdbcTemplate().queryForList(sql);
	}

	@Override
	public int update(String sql)
	{
		return getJdbcTemplate().update(sql);
	}
}
