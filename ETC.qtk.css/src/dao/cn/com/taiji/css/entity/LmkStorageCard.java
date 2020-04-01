package cn.com.taiji.css.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;

@Entity
@Table(name = "LMK_STORAGE_CARD")
public class LmkStorageCard extends StringUUIDEntity {
	private String startId;
	private String endId;
	@Column(name = "START_ID")
	public String getStartId() {
		return startId;
	}
	public void setStartId(String startId) {
		this.startId = startId;
	}
	@Column(name = "END_ID")
	public String getEndId() {
		return endId;
	}
	public void setEndId(String endId) {
		this.endId = endId;
	}
	
}
