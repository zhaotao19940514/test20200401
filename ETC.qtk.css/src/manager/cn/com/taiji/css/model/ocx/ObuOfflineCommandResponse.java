/**
 * @Title ObuInfoResponse.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午3:20:20
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx;

import cn.com.taiji.css.model.appajax.AppAjaxResponse;

/**
 * @ClassName ObuInfoResponse
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 15:20:20
 * @E_mail yaonanlin@163.com
 */
public class ObuOfflineCommandResponse extends AppAjaxResponse{
	private String response;
	public ObuOfflineCommandResponse() {
		super();
	}
	public ObuOfflineCommandResponse(String response) {
		super();
		this.response = response;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}

