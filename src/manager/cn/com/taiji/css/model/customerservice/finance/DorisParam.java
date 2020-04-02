/**
 * @Title DorisParam.java
 * @Package tests.cn.com.taiji.dsi.manager.httpclienthelper
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月3日 下午9:04:18
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.finance;

import org.apache.http.NameValuePair;

import cn.com.taiji.common.model.BaseModel;

/**
 * @ClassName DorisParam
 * @Description TODO
 * @author yaonl
 * @date 2018年09月03日 21:04:18
 * @E_mail yaonanlin@163.com
 */
public class DorisParam extends BaseModel implements NameValuePair{
	private String name;
	private String value;
	
	public DorisParam(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}
}

