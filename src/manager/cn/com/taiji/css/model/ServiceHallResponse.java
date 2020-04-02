
package cn.com.taiji.css.model;

import cn.com.taiji.common.model.BaseModel;


public class ServiceHallResponse extends BaseModel  {
    
	private String message;
	private Integer status;

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message 要设置的 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status 要设置的 status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
