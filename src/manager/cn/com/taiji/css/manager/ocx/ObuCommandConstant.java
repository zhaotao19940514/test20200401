/**
 * @Title ObuCommandConstant.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月6日 下午2:46:06
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import cn.com.taiji.css.manager.util.CssUtil;

/**
 * @ClassName ObuCommandConstant
 * @Description TODO
 * @author yaonl
 * @date 2018年09月06日 14:46:06
 * @E_mail yaonanlin@163.com
 */
public class ObuCommandConstant {
	public static final String SEPERATOR = CssUtil.COMMON_SEPERATOR;
	
	public static final String QTZL_ISSUERID = "B9F3D6DD00020001"; // 贵州的固定编号 
	// obu指令生成模块 车辆信息属性名定义
	public static final String PLATE_NUM = "plateNum"; // 车辆号
	public static final String PLATE_COLOR = "plateColor"; // 车牌颜色
	public static final String VEHICLE_TYPE = "vehicleType"; // 车辆类型
	public static final String USER_TYPE = "userType"; // 车辆用户类型
	public static final String VEHICLE_LENGTH = "vehicleLength"; // 车长
	public static final String VEHICLE_WIDTH = "vehicleWidth"; // 车宽
	public static final String VEHICLE_HEIGHT = "vehicleHeight"; // 车高
	public static final String WHEELS_COUNT = "wheelsCount"; // 车轮数
	public static final String AXLES_COUNT = "axlesCount"; // 车轴数
	public static final String WHEEL_BASE = "wheelBase"; // 轴距
	public static final String LOAD_OR_SEAT = "loadOrSeat"; // 载重/座位数
	public static final String VEHICLE_FEATURE = "vehicleFeature"; // 车辆特征
	public static final String ENGINE_NUM = "engineNum"; // 发动机编号
	
	// obu指令生成模块 系统信息属性名定义
	public static final String CONTRACT_PROVIDER = "contractProvider"; // 合同服务商编号
	public static final String PROVINCE = "province"; // 省份
	public static final String CONTRACT_TYPE = "contractType"; // 协约类型
	public static final String CONTRACT_VERSION = "contractVersion"; // 合同版本
	public static final String CONTRACT_SERIALNO = "contractSerialNo"; // 合同编号 
	public static final String CONTRACT_ENABLETIME = "contractEnableTime"; // 合同生效时间
	public static final String CONTRACT_EXPIRETIME = "contractExpireTime"; // 合同到期时间
	public static final String OBU_NUM = "obuNum";// obu内部编号
	public static final String LOAD_STATUS = "loadStatus";// 拆卸状态
	public static final String RESERVED = "reserved";// 保留字段 聚利最长100个字符
	
	// 部分厂商指令独特常量
	public static final String JULI_READ_SYS_PREFIX = "0,系统信息,";
	public static final String JULI_READ_VEH_PREFIX = "0,车辆信息,";
	public static final String JULI_WRITE_SYS_PREFIX = "系统信息,";
	public static final String JULI_WRITE_VEH_PREFIX = "车辆信息,";
}

