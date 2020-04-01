package cn.com.taiji.css.model;

import javax.validation.constraints.NotNull;

import cn.com.taiji.css.entity.dict.SysConfType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-11-16 下午01:58:58<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class SystemPara extends AbstractSysConf
{
	@NotNull
	private String printHead = "页眉";

	public SystemPara()
	{
		super(SysConfType.SYSTEM_PARA);
	}

	public String getPrintHead()
	{
		return printHead;
	}

	public void setPrintHead(String printHead)
	{
		this.printHead = printHead;
	}

	public static SystemPara newInstance(String jsonStr)
	{
		return fromJson(jsonStr, SystemPara.class);
	}
}
