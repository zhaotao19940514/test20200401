/*
 * Date: 2014年9月29日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.config.manager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年9月29日 下午3:15:59<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public enum TaskGroup
{
	SYSTEM("系统内置") {},
	SAMPLE("示例") {},
	;
	private final String value;

	private TaskGroup(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

}
