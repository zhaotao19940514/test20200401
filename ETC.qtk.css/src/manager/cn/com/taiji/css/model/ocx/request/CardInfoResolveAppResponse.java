/**
 * @Title CardInfoResolveAppResponse.java
 * @Package cn.com.taiji.css.model.ocx.request
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月8日 下午5:24:32
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx.request;

import cn.com.taiji.css.model.appajax.AppAjaxResponse;

/**
 * @ClassName CardInfoResolveAppResponse
 * @Description TODO
 * @author yaonl
 * @date 2018年09月08日 17:24:32
 * @E_mail yaonanlin@163.com
 */
public class CardInfoResolveAppResponse extends AppAjaxResponse {
	private String file0015Json;
	private String file0016Json;
	private String file000EJson;
	public String getFile0015Json() {
		return file0015Json;
	}
	public String getFile0016Json() {
		return file0016Json;
	}
	public String getFile000EJson() {
		return file000EJson;
	}
	public void setFile0015Json(String file0015Json) {
		this.file0015Json = file0015Json;
	}
	public void setFile0016Json(String file0016Json) {
		this.file0016Json = file0016Json;
	}
	public void setFile000EJson(String file000eJson) {
		file000EJson = file000eJson;
	}
}

