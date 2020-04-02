/**
 * @Title ObuDeviceConfig.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月5日 下午9:00:03
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.google.common.collect.Maps;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;
import cn.com.taiji.qtk.entity.dict.CssObuDeviceType;

/**
 * @ClassName ObuDeviceConfig
 * @Description TODO
 * @author yaonl
 * @date 2018年09月05日 21:00:03
 * @E_mail yaonanlin@163.com
 */
public class ObuDeviceServerConfig {
	private CssObuDeviceType type;
	private String codeBase;// ocx codebase路径
	private String readSysInfoPrefix=""; // 读指令前缀 
	private String readVehicleInfoPrefix="";
	private String writeSysInfoPrefix=""; // 写指令前缀
	private String writeVehicleInfoPrefix="";
	private String readSysInfoPattern; // 读出的系统信息格式
	private String readVehicleInfoPattern; // 读出的车辆信息格式
	private String writeSysInfoPattern; // 写系统信息格式
	private String writeVehicleInfoPattern; // 写车辆信息格式
	private Map<String, String> sysInfoHandleMap;// 生成指令时要更新的系统信息字段及值
	private String charset = CssUtil.UTF8;
	
	public static ObuDeviceServerConfig getConfig(ServiceHallDeviceConfig deviceConfig) throws ManagerException{
		CssObuDeviceType deviceType = deviceConfig.getObuDeviceType();
		if(deviceType==null) throw new ManagerException("当前工号所属网点："+deviceConfig.getServiceHallId()+" obu设备类型为空。请联系管理员重新配置。" );
		ObuDeviceServerConfig obuDeviceServerConfig = ObuDeviceServerConfig.getConfigMap().get(deviceType);
		return obuDeviceServerConfig;
	}
	
	public static Map<CssObuDeviceType, ObuDeviceServerConfig> getConfigMap(){
		Map<CssObuDeviceType, ObuDeviceServerConfig> map = Maps.newHashMap();
		// 握奇OBU配置
		Map<String, String> watchSysInfoHandleMap = Maps.newHashMap(); // 要更新的系统信息字段及值
		watchSysInfoHandleMap.put(ObuCommandConstant.CONTRACT_ENABLETIME, CssUtil.getNowDate().replace("-", ""));
		watchSysInfoHandleMap.put(ObuCommandConstant.CONTRACT_EXPIRETIME, LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		ObuDeviceServerConfig watchConfig = new ObuDeviceServerConfig()
			.setCodeBase("obuReaderOcx/watch/OBUReader.CAB")// ${rootUrl}...
			.setType(CssObuDeviceType.WATCH_OBU)
			.setReadSysInfoPattern(ObuCommandConstant.PROVINCE,
									ObuCommandConstant.CONTRACT_TYPE,
									ObuCommandConstant.CONTRACT_VERSION,
									ObuCommandConstant.CONTRACT_SERIALNO,
									ObuCommandConstant.CONTRACT_ENABLETIME,
									ObuCommandConstant.CONTRACT_EXPIRETIME,
									ObuCommandConstant.OBU_NUM,
									ObuCommandConstant.PLATE_NUM,
									ObuCommandConstant.PLATE_COLOR)
			// 此处返回的车牌信息为空 经由前端js填充。。。详见watchObuReader.js
			.setWriteSysInfoPattern(ObuCommandConstant.CONTRACT_ENABLETIME,
									ObuCommandConstant.CONTRACT_EXPIRETIME,
									ObuCommandConstant.PLATE_NUM,
									ObuCommandConstant.PLATE_COLOR)
			.setReadVehicleInfoPattern(getDefaultVehicleInfoPattern())
			.setWriteVehicleInfoPattern(getDefaultVehicleInfoPattern())
			.setSysInfoHandleMap(watchSysInfoHandleMap);
		map.put(CssObuDeviceType.WATCH_OBU,watchConfig);
		map.put(CssObuDeviceType.WATCH_OBU_IE,watchConfig);
				
		// 聚利OBU配置
		Map<String, String> juliSysInfoHandleMap = Maps.newHashMap();
		juliSysInfoHandleMap.put(ObuCommandConstant.CONTRACT_ENABLETIME, CssUtil.getNowDate().replace("-", ""));
		juliSysInfoHandleMap.put(ObuCommandConstant.CONTRACT_EXPIRETIME, LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		juliSysInfoHandleMap.put(ObuCommandConstant.CONTRACT_PROVIDER,ObuCommandConstant.QTZL_ISSUERID );
		ObuDeviceServerConfig juliConfig = new ObuDeviceServerConfig()
			.setCodeBase("obuReaderOcx/ObuApiOcx.ocx")// ${rootUrl}...
			.setType(CssObuDeviceType.JULI_OBU)
			.setReadSysInfoPattern(ObuCommandConstant.CONTRACT_PROVIDER,
									ObuCommandConstant.CONTRACT_TYPE,
									ObuCommandConstant.CONTRACT_VERSION,
									ObuCommandConstant.CONTRACT_SERIALNO,
									ObuCommandConstant.CONTRACT_ENABLETIME,
									ObuCommandConstant.CONTRACT_EXPIRETIME,
									ObuCommandConstant.LOAD_STATUS)
			.setWriteSysInfoPattern(ObuCommandConstant.CONTRACT_PROVIDER,
									ObuCommandConstant.CONTRACT_TYPE,
									ObuCommandConstant.CONTRACT_VERSION,
									ObuCommandConstant.CONTRACT_SERIALNO,
									ObuCommandConstant.CONTRACT_ENABLETIME,
									ObuCommandConstant.CONTRACT_EXPIRETIME,
									ObuCommandConstant.LOAD_STATUS,
									// 车辆颜色在写入时应为传四位 由obuOfflineReader.js文件处理
									// 如车牌颜色为1 则写入时为0001
									ObuCommandConstant.PLATE_COLOR,
									ObuCommandConstant.PLATE_NUM,
									ObuCommandConstant.RESERVED
									)
			.setReadVehicleInfoPattern(getDefaultVehicleInfoPattern())
			.setWriteVehicleInfoPattern(getDefaultVehicleInfoPattern())
			.setReadSysInfoPrefix(ObuCommandConstant.JULI_READ_SYS_PREFIX)
			.setReadVehicleInfoPrefix(ObuCommandConstant.JULI_READ_VEH_PREFIX)
			.setWriteSysInfoPrefix(ObuCommandConstant.JULI_WRITE_SYS_PREFIX)
			.setWriteVehicleInfoPrefix(ObuCommandConstant.JULI_WRITE_VEH_PREFIX)
			.setSysInfoHandleMap(juliSysInfoHandleMap);
		map.put(CssObuDeviceType.JULI_OBU,juliConfig);
		map.put(CssObuDeviceType.JULI_OBU_IE,juliConfig);
		return map;
	}
	
	private static String getDefaultVehicleInfoPattern() {
		return new StringBuilder()
		.append(ObuCommandConstant.PLATE_NUM).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.PLATE_COLOR).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.VEHICLE_TYPE).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.USER_TYPE).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.VEHICLE_LENGTH).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.VEHICLE_WIDTH).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.VEHICLE_HEIGHT).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.WHEELS_COUNT).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.AXLES_COUNT).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.WHEEL_BASE).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.LOAD_OR_SEAT).append(ObuCommandConstant.SEPERATOR)
		// 车辆特征 聚利 车辆特征长度限制为16字符
		.append(ObuCommandConstant.VEHICLE_FEATURE).append(ObuCommandConstant.SEPERATOR)
		.append(ObuCommandConstant.ENGINE_NUM)
		.toString();
	}
	
	private Integer getIndex(String[] split,String paramName) throws ManagerException {
		for (int i = 0; i < split.length; i++) {
			String value = split[i];
			if(paramName.equals(value)){
				return i;
			}
		}
		return -1;
//		throw new ManagerException("指令参数名或指令格式错误，请联系管理员！");
	}

	public Integer getParamIndexByName(String paramName,String pattern) throws ManagerException{
		String[] split = pattern.split(",");
		Integer index = getIndex(split,paramName);
		return index;
	}
	
	public CssObuDeviceType getType() {
		return type;
	}

	public String getCodeBase() {
		return codeBase;
	}

	public Integer getExpectedReadSysInfoLength() {
		String[] split = this.readSysInfoPattern.split(ObuCommandConstant.SEPERATOR);
		return split.length;
	}

	public Integer getExpectedReadVehicleInfoLength() {
		String[] split = this.readVehicleInfoPattern.split(ObuCommandConstant.SEPERATOR);
		return split.length;
	}
	
	public Integer getExpectedWriteSysInfoLength() {
		String[] split = this.writeSysInfoPattern.split(ObuCommandConstant.SEPERATOR);
		return split.length;
	}

	public Integer getExpectedWriteVehicleInfoLength() {
		String[] split = this.writeVehicleInfoPattern.split(ObuCommandConstant.SEPERATOR);
		return split.length;
	}

	public ObuDeviceServerConfig setType(CssObuDeviceType type) {
		this.type = type;
		return this;
	}

	public ObuDeviceServerConfig setCodeBase(String codeBase) {
		this.codeBase = codeBase;
		return this;
	}

	
	public Map<String, String> getSysInfoHandleMap() {
		return sysInfoHandleMap;
	}

	public ObuDeviceServerConfig setSysInfoHandleMap(Map<String, String> sysInfoHandleMap) {
		this.sysInfoHandleMap = sysInfoHandleMap;
		return this;
	}
	
	public String getReadSysInfoPrefix() {
		return readSysInfoPrefix;
	}

	public String getReadVehicleInfoPrefix() {
		return readVehicleInfoPrefix;
	}

	public String getWriteSysInfoPrefix() {
		return writeSysInfoPrefix;
	}

	public String getWriteVehicleInfoPrefix() {
		return writeVehicleInfoPrefix;
	}

	public String getReadSysInfoPattern() {
		return readSysInfoPattern;
	}

	public String getReadVehicleInfoPattern() {
		return readVehicleInfoPattern;
	}

	public String getWriteSysInfoPattern() {
		return writeSysInfoPattern;
	}

	public String getWriteVehicleInfoPattern() {
		return writeVehicleInfoPattern;
	}

	public ObuDeviceServerConfig setReadSysInfoPrefix(String readSysInfoPrefix) {
		this.readSysInfoPrefix = readSysInfoPrefix;
		return this;
	}

	public ObuDeviceServerConfig setReadVehicleInfoPrefix(String readVehicleInfoPrefix) {
		this.readVehicleInfoPrefix = readVehicleInfoPrefix;
		return this;
	}

	public ObuDeviceServerConfig setWriteSysInfoPrefix(String writeSysInfoPrefix) {
		this.writeSysInfoPrefix = writeSysInfoPrefix;
		return this;
	}

	public ObuDeviceServerConfig setWriteVehicleInfoPrefix(String writeVehicleInfoPrefix) {
		this.writeVehicleInfoPrefix = writeVehicleInfoPrefix;
		return this;
	}

	private String generatePattern(String... constantParams) {
		return CssUtil.arrayToString(constantParams);
	}
	
	public ObuDeviceServerConfig setReadSysInfoPattern(String... readSysInfoPattern) {
		String pattern = generatePattern(readSysInfoPattern);
		this.setReadSysInfoPattern(pattern);
		return this;
	}

	public ObuDeviceServerConfig setReadVehicleInfoPattern(String... readVehicleInfoPattern) {
		String pattern = generatePattern(readVehicleInfoPattern);
		this.setReadVehicleInfoPattern(pattern);
		return this;
	}

	public ObuDeviceServerConfig setWriteSysInfoPattern(String... writeSysInfoPattern) {
		String pattern = generatePattern(writeSysInfoPattern);
		this.setWriteSysInfoPattern(pattern);
		return this;
	}
	
	public ObuDeviceServerConfig setWriteVehicleInfoPattern(String... constantParams) {
		String pattern = generatePattern(constantParams);
		this.setWriteVehicleInfoPattern(pattern);
		return this;
	}

	public ObuDeviceServerConfig setReadSysInfoPattern(String readSysInfoPattern) {
		this.readSysInfoPattern = readSysInfoPattern;
		return this;
	}

	public ObuDeviceServerConfig setReadVehicleInfoPattern(String readVehicleInfoPattern) {
		this.readVehicleInfoPattern = readVehicleInfoPattern;
		return this;
	}

	public ObuDeviceServerConfig setWriteSysInfoPattern(String writeSysInfoPattern) {
		this.writeSysInfoPattern = writeSysInfoPattern;
		return this;
	}

	public ObuDeviceServerConfig setWriteVehicleInfoPattern(String writeVehicleInfoPattern) {
		this.writeVehicleInfoPattern = writeVehicleInfoPattern;
		return this;
	}

	public String getCharset() {
		return charset;
	}

	public ObuDeviceServerConfig setCharset(String charset) {
		this.charset = charset;
		return this;
	}

}

