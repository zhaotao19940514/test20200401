/* Copyright  2017 TaiJi Computer Corporation Limited
 * All rights reserved.
 * 
 * Date: 2017年10月19日
 * author: tangyoucai  (1240495602@qq.com)
 *
 */
package cn.com.taiji.css.entity.dict;

public enum CompleteStatus
{
	UNFINISHED("未完成") {},
	FINISHED("已完成") {};
	
	private String value;

	private CompleteStatus(String value)
	{
		this.value=value;
	}

	public String getValue()
	{
		return value;
	}

	

}

