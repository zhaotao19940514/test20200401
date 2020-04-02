/**
 * @Title CssOperateLogType.java
 * @Package cn.com.taiji.css.entity.dict
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月27日 下午8:37:41
 * @version V1.0
 */
package cn.com.taiji.css.entity.dict;

/**
 * @ClassName CssOperateLogType
 * @Description TODO
 * @author yaonl
 * @date 2018年07月27日 20:37:41
 * @E_mail yaonanlin@163.com
 */
public enum CssOperateLogType {
	ALL("全部"){},
	ADD("添加"){},
	UPDATE("更新"){},
	DELETE("删除"){},
	QUERY("查询"){},
	REQUEST("请求"){},
	;
	private CssOperateLogType(String value) {
		this.value = value;
	}
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}

