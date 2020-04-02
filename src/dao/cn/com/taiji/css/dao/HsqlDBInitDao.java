/*
 * Date: 2012-3-13
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Peream <br>
 *         Create Time：2012-3-13 下午4:05:37<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface HsqlDBInitDao
{
	public void execute(String sql);

	public List<Map<String, Object>> queryForList(String sql);

	public int update(String sql);
}
