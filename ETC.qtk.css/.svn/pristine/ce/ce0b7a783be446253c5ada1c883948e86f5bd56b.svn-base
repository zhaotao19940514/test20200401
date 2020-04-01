package cn.com.taiji.css.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.common.pub.StringTools;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-6-9 上午09:05:13<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "QTK_CSS_ROLE")
public class Role extends StringUUIDEntity
{
	public static final String DEFAULT_ID = "default";
	@NotNull
	@Size(max = 30)
	private String name;
	@NotNull
	@Min(value=1)
	@Max(value=999)
	private Integer list;// 排序
	private String info;
	private boolean system;// 系统自带?
	private Unit unit;
	public Role()
	{

	}

	public Role(String id)
	{
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "unit_id")
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Column(nullable = false)
	public boolean isSystem()
	{
		return system;
	}

	public void setSystem(boolean system)
	{
		this.system = system;
	}

	@Column(nullable = false, length = 100, unique = true)
	public String getName()
	{
		return StringTools.hasText(name) ? name.trim() : name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(nullable = false)
	public Integer getList()
	{
		return list;
	}

	public void setList(Integer list)
	{
		this.list = list;
	}

	@Column(length = 255)
	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}
}
