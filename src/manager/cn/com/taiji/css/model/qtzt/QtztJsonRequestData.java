package cn.com.taiji.css.model.qtzt;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.com.taiji.css.manager.qtzt.QtztTransType;

public class QtztJsonRequestData {
	@JsonIgnore
	private QtztTransType qtztTransType;

	public QtztJsonRequestData(QtztTransType qtztTransType) {
		super();
		this.qtztTransType = qtztTransType;
	}

	public QtztTransType getQtztTransType() {
		return qtztTransType;
	}

	
	
	
}
