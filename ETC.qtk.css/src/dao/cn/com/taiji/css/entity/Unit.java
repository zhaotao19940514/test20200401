package cn.com.taiji.css.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cn.com.taiji.common.entity.StringUUIDEntity;

/**
 * 单位管理
 *
 */
@Entity
@Table(name = "QTK_CSS_UNIT")
public class Unit extends StringUUIDEntity  {
	@NotNull
	@Size(max = 30)
	private String name;//单位名称
	private String code;//编号，生成后不再变化
	private String parentId;//父级ID
	private Integer unitLevel;//级别
	@NotNull
	@Digits(integer=4,fraction=0)
	private Integer list;//序号
	private String globalList;//全局排序号
	/**
	 * 本单位是否属于unit的下级
	 * @param unit
	 * @return
	 */
	public boolean belongTo(Unit unit){
		return code.startsWith(unit.getCode());
	}
	
	@Column(name = "LIST")
	public Integer getList() {
		return list;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	@Column(name = "PARENT_ID")
	public String getParentId() {
		return parentId;
	}

	@Column(name = "UNIT_LEVEL")
	public Integer getUnitLevel() {
		return unitLevel;
	}

	@Column(name = "GLOBAL_LIST")
	public String getGlobalList() {
		return globalList;
	}
	
	public void  setList(Integer list) {
		this.list=list;
	}

	public void  setName(String name) {
		this.name=name;
	}

	public void  setCode(String code) {
		this.code=code;
	}

	public void  setParentId(String parentId) {
		this.parentId=parentId;
	}

	public void  setUnitLevel(Integer unitLevel) {
		this.unitLevel=unitLevel;
	}

	public void setGlobalList(String globalList) {
		this.globalList = globalList;
	}

}
