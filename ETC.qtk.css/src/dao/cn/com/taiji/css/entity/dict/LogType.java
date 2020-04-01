package cn.com.taiji.css.entity.dict;

public enum LogType
{
	LOGIN("用户登录") {},
	SYSTEM("系统管理") {};
	private String value;

	private LogType(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

}