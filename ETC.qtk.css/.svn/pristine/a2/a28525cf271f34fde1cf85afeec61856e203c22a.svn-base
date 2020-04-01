package cn.com.taiji.css.model.acl;

import java.util.ArrayList;
import java.util.List;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.css.entity.AppResource;

/**
 * 栏目菜单
 * 
 * @author Peream <br>
 *         Create Time：2011-5-17 下午01:37:09<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class ColumnMenu extends BaseModel
{
	private AppResource columnResource;// 栏目资源
	private boolean hasTabMenu;// 是否拥有标签菜单
	private List<AppResource> tabResources = new ArrayList<AppResource>();// 标签资源

	private boolean hasColumnResource;// 是否拥有本栏目菜单（角色配置时用）

	public AppResource getColumnResource()
	{
		return columnResource;
	}

	public void setColumnResource(AppResource columnResource)
	{
		this.columnResource = columnResource;
	}

	public boolean isHasTabMenu()
	{
		return hasTabMenu;
	}

	public void setHasTabMenu(boolean hasTabMenu)
	{
		this.hasTabMenu = hasTabMenu;
	}

	public List<AppResource> getTabResources()
	{
		return tabResources;
	}

	public void setTabResources(List<AppResource> tabResources)
	{
		this.tabResources = tabResources;
	}

	public boolean isHasColumnResource()
	{
		return hasColumnResource;
	}

	public void setHasColumnResource(boolean hasColumnResource)
	{
		this.hasColumnResource = hasColumnResource;
	}
}
