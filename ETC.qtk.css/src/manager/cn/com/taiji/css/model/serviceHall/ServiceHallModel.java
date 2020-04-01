package cn.com.taiji.css.model.serviceHall;

import cn.com.taiji.qtk.entity.ServiceHall;

public class ServiceHallModel extends ServiceHall {
	private boolean hasChild;

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
}
