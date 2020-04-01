package cn.com.taiji.css.model.acl;

import java.util.List;

import cn.com.taiji.common.model.BaseModel;

public class ZTreeItem extends BaseModel {
	private String id;
	private String name;
	private boolean checked;//是否有权限
//	1、初始化节点数据时，根据 treeNode.children 属性判断，有子节点则设置为 true，否则为 false
	private List<ZTreeItem> children;
//	2、初始化节点数据时，如果设定 treeNode.isParent = true，即使无子节点数据，也会设置为父节点
	private boolean isParent;
	
	public boolean getIsParent() {
		return isParent;
	}
	public ZTreeItem setIsParent(boolean isParent) {
		this.isParent = isParent;
		return this;
	}
	public String getId() {
		return id;
	}
	public ZTreeItem setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ZTreeItem setName(String name) {
		this.name = name;
		return this;
	}
	public boolean isChecked() {
		return checked;
	}
	public ZTreeItem setChecked(boolean checked) {
		this.checked = checked;
		return this;
	}
	public List<ZTreeItem> getChildren() {
		return children;
	}
	public ZTreeItem setChildren(List<ZTreeItem> children) {
		this.children = children;
		return this;
	}
	
}
