/* Copyright  2017 TaiJi Computer Corporation Limited
 * All rights reserved.
 * 
 * Date: 2017年10月19日
 * author: tangyoucai  (1240495602@qq.com)
 *
 */
package cn.com.taiji.css.entity.dict;

public enum PlanType
{
	DEVELOP("软件开发") {},
	SYSTEM("系统集成") {},
	DATA("数据汇聚") {},
	OTHER("其他事项细节工作") {},
	TAXSERVICE("税控服务") {},
	COMMUNCATION("需沟通处理") {};
	
	private String value;

	private PlanType(String value)
	{
		this.value=value;
	}

	public String getValue()
	{
		return value;
	}

	public static PlanType[] getDevelopType(){
		return new PlanType[]{DEVELOP, SYSTEM, DATA, TAXSERVICE};
	}

}

