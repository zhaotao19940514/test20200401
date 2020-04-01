package cn.com.taiji.css.model.apply.inportInfomation;

import cn.com.taiji.common.model.BaseModel;

public class InportInfomationResponse extends BaseModel {
	
	private String message;
	
	private Integer status;
	
	//文件路径
	private String filePath;
	//文件名
	private String fileName;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
