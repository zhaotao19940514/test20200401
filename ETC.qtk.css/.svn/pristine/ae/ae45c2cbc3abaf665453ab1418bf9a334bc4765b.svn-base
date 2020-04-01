/**
 * @Title AppAjaxResponse.java
 * @Package cn.com.taiji.css.model.appajaxresponse
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月31日 下午7:51:45
 * @version V1.0
 */
package cn.com.taiji.css.model.appajax;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.css.entity.dict.AppAjaxResponseCode;

/**
 * @ClassName AppAjaxResponse
 * @Description TODO
 * @author yaonl
 * @date 2018年07月31日 19:51:45
 * @E_mail yaonanlin@163.com
 */
public class AppAjaxResponse extends BaseModel {
	private String message;
	private AppAjaxResponseCode code;
	private Boolean success;
	private int status;
	private String response;
	public AppAjaxResponse() {
		super();
		code = AppAjaxResponseCode.SUCCESS;
		success = true;
	}
	public AppAjaxResponse(String message, AppAjaxResponseCode code, Boolean success, int status, String response) {
		super();
		this.message = message;
		this.code = code;
		this.success = success;
		this.status = status;
		this.response = response;
	}
	public AppAjaxResponse(String message, AppAjaxResponseCode code, Boolean success) {
		super();
		this.message = message;
		this.code = code;
		this.success = success;
	}
	public AppAjaxResponse(String response, String message, AppAjaxResponseCode code, Boolean success) {
		super();
		this.response = response;
		this.message = message;
		this.code = code;
		this.success = success;
	}
	public AppAjaxResponse(String response, String message, Boolean success) {
		super();
		this.response = response;
		this.message = message;
		this.code = AppAjaxResponseCode.SUCCESS;
		this.success = success;
	}
	public AppAjaxResponse(String response) {
		super();
		this.response = response;
		this.message = "操作成功";
		this.code = AppAjaxResponseCode.SUCCESS;
		this.success = true;
	}
	public AppAjaxResponse(ManagerException e) {
		super();
		this.message = e.getMessage();
		this.code = AppAjaxResponseCode.EXCEPTION;
		this.success = false;
	}
	public AppAjaxResponse setManagerException(ManagerException e){
		this.message = e.getMessage();
		this.code = AppAjaxResponseCode.EXCEPTION;
		this.success = false;
		return this;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public AppAjaxResponseCode getCode() {
		return code;
	}
	public Boolean getSuccess() {
		return success;
	}
	public int getStatus() {
		return status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setCode(AppAjaxResponseCode code) {
		this.code = code;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}

