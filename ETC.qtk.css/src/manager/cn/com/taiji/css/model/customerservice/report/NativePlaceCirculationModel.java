package cn.com.taiji.css.model.customerservice.report;

import cn.com.taiji.common.model.BaseModel;

public class NativePlaceCirculationModel extends BaseModel{
	

	private String name;	//机构名
	private String GuiCount;	//数量
	private String noGuiCount;	//数量
	
	public String getName() {
		return name;
	}
	public String getGuiCount() {
		return GuiCount;
	}
	public String getNoGuiCount() {
		return noGuiCount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGuiCount(String guiCount) {
		GuiCount = guiCount;
	}
	public void setNoGuiCount(String noGuiCount) {
		this.noGuiCount = noGuiCount;
	}
	
	
	

	
}
