/*
 * Date: 2013-5-14
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.com.taiji.common.manager.pub.ZipHelper;
import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.pub.EncodeTool;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.SystemInfo;
import cn.com.taiji.css.entity.dict.SysConfType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013-5-14 下午4:42:55<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractSysConf extends BaseModel
{
	protected final SysConfType confType;

	protected AbstractSysConf(SysConfType confType)
	{
		this.confType = confType;
	}

	@JsonIgnore
	public SysConfType getConfType()
	{
		return confType;
	}

	public final SystemInfo toSystemInfo(String id, String userId, boolean ziped)
	{
		SystemInfo rs = new SystemInfo();
		if (StringTools.hasText(id)) rs.setId(id);
		rs.setUserId(userId);
		String jsonStr = ziped ? compress(this.toJson()) : this.toJson();
		rs.setJsonStr(jsonStr);
		rs.setZiped(ziped);
		rs.setConfType(confType);
		rs.setUpdateTime(LocalDateTime.now());
		return rs;
	}

	public static <T extends AbstractSysConf> T newInstance(Class<T> clazz, String jsonStr)
	{
		return fromJson(jsonStr, clazz);
	}

	public static String uncompress(String str)
	{
		try
		{
			String zipStr = new String(EncodeTool.decodeBase64(str), "UTF-8");
			return ZipHelper.uncompress(zipStr);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String compress(String str)
	{
		try
		{
			String zipStr = ZipHelper.compress(str);
			return EncodeTool.encodeBase64UTF8(zipStr);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
