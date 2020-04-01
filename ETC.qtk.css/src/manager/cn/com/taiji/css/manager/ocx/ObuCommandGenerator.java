/**
 * @Title ObuCommandGenerator.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月3日 下午6:41:26
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import java.io.UnsupportedEncodingException;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.manager.util.CssUtil;

/**
 * @ClassName ObuCommandGenerator
 * @Description TODO
 * @author yaonl
 * @date 2018年08月03日 18:41:26
 * @E_mail yaonanlin@163.com
 */
public class ObuCommandGenerator {
	public static String generateVehicleInfo(String oldObuVehicleInfo, String[] newVehicleInfo,
			ObuDeviceServerConfig config) throws ManagerException {
		if (!StringTools.hasText(oldObuVehicleInfo)) {
			throw new ManagerException("读取的obu车辆信息数量错误");
		}
		oldObuVehicleInfo = removePrefix(oldObuVehicleInfo, config.getReadVehicleInfoPrefix());
		// 读取的信息可能缺失 需要长度足够的数组
		String[] split = new String[config.getExpectedReadVehicleInfoLength()];
		String[] oldSplit = oldObuVehicleInfo.split(ObuCommandConstant.SEPERATOR);
		fillSplit(split, oldSplit);
		if (newVehicleInfo.length != config.getExpectedReadVehicleInfoLength()) {
			throw new ManagerException("传入新车辆信息数量错误");
		}
		// validVehicleInfo(split);
		// validVehicleInfo(newVehicleInfo);
		setProperty(newVehicleInfo, split);
		String[] vehicleCommands = toCommand(split, config, config.getWriteVehicleInfoPattern(), config.getReadVehicleInfoPattern());
		String info = config.getWriteVehicleInfoPrefix() + CssUtil.arrayToString(vehicleCommands);
		try {
			return new String(info.getBytes(CssUtil.UTF8), config.getCharset());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ManagerException("指令转码失失败");
		}
	}
	
	public static String generateSysInfo(String oldObuSysInfo, String newObuId, String plateNum, String plateColor, ObuDeviceServerConfig config)
			throws ManagerException {
		if (!StringTools.hasText(oldObuSysInfo)) {
			throw new ManagerException("读取的obu系统信息数量错误");
		}
		// 去前缀  
		oldObuSysInfo = removePrefix(oldObuSysInfo, config.getReadSysInfoPrefix());
		// 读取的信息可能缺失 需要长度足够的数组
		String[] split = new String[config.getExpectedReadSysInfoLength()];
		String[] oldSplit = oldObuSysInfo.split(ObuCommandConstant.SEPERATOR);
		fillSplit(split, oldSplit);
		// 取obuId
		Integer obuOldIdIndex = config.getParamIndexByName(ObuCommandConstant.CONTRACT_SERIALNO, config.getReadSysInfoPattern());
		String oldObuId = null;
		if(obuOldIdIndex>=0){
			oldObuId = split[obuOldIdIndex];
		}
		if (!newObuId.equals(oldObuId)) {
			throw new ManagerException("原obuId(" + oldObuId + ")与写入obuId(" + newObuId + ")不一致");
		}
		updateSysInfo(split, config);
		String[] sysCommand = toCommand(split, config, config.getWriteSysInfoPattern(), config.getReadSysInfoPattern());
		//覆盖车牌号、车牌颜色至指令
		sysCommand = updatePlateInfo(sysCommand,plateNum,plateColor,config);
		String info = config.getWriteSysInfoPrefix() + CssUtil.arrayToString(sysCommand);
		try {
			return new String(info.getBytes(CssUtil.UTF8), config.getCharset());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ManagerException("指令转码失失败");
		}
	}

	/**
	 * @param sysCommand
	 * @param plateNum
	 * @param plateColor
	 * @return 
	 * @throws ManagerException 
	 */
	private static String[] updatePlateInfo(String[] sysCommand, String plateNum, String plateColor, ObuDeviceServerConfig config) throws ManagerException {
		Integer plateNumIndex = config.getParamIndexByName(ObuCommandConstant.PLATE_NUM, config.getWriteSysInfoPattern());
		if(plateNumIndex>=0){
			sysCommand[plateNumIndex] = plateNum;
		}
		Integer plateColorIndex = config.getParamIndexByName(ObuCommandConstant.PLATE_COLOR, config.getWriteSysInfoPattern());
		if(plateColorIndex>=0){
			sysCommand[plateColorIndex] = plateColor;
		}
		return sysCommand;
	}

	private static void fillSplit(String[] split, String[] oldSplit) {
		for (int i = 0; i < split.length; i++) {
			if(i<oldSplit.length){
				split[i] = oldSplit[i];
			}else{
				split[i] = "0";
			}
		}
	}
	
	private static String[] toCommand(String[] split, ObuDeviceServerConfig config, String writeInfoPattern, String readInfoPattern) throws ManagerException {
		String[] writePatternSplit = writeInfoPattern.split(ObuCommandConstant.SEPERATOR);
		String[] commands = new String[writePatternSplit.length];
		for (int i = 0; i < writePatternSplit.length; i++) {
			String paramName = writePatternSplit[i];
			Integer paramIndex = config.getParamIndexByName(paramName, readInfoPattern);
			if(paramIndex>=0){
				String value = split[paramIndex];
				commands[i] = value;
			}
		}
		return commands;
	}
	
	private static void setProperty(String[] newVehicleInfo, String[] split) {
		for (int i = 0; i < split.length; i++) {
			if (StringTools.hasText(newVehicleInfo[i])) {
				split[i] = newVehicleInfo[i];
			}
		}
	}
	
	private static void updateSysInfo(String[] split,ObuDeviceServerConfig config) throws ManagerException {
		for (String key : config.getSysInfoHandleMap().keySet()) {
			String value = config.getSysInfoHandleMap().get(key);
			Integer index = config.getParamIndexByName(key, config.getReadSysInfoPattern());
			if(index>=0){
				split[index] = value;
			}
		}
	}
	
	private static String removePrefix(String info, String prefix) {
		if (StringTools.hasText(prefix)) {
			info = info.replace(prefix, "");
		}
		return info;
	}
	// private static void validVehicleInfo(String[] split) throws
	// ManagerException {
	// String prefix = split[0];
	// String infoMark = split[1];
	// String plateNum = split[2];
	// String plateColor = split[3];
	// String vehicleType = split[4];
	// String userType = split[5];
	// String vehicleLength = split[6];
	// String vehicleWidth = split[7];
	// String vehicleHeight = split[8];
	// String wheelsCount = split[9];
	// String axleCount = split[10];
	// String wheelBase = split[11];
	// String loadOrSeat = split[12];
	// String vehicleFeature = split[13];
	// String engineNum = split[14];
	// if (!StringTools.hasText(prefix)) {
	// throw new ManagerException("前缀不能为空");
	// } else if (prefix.length() != 1) {
	// throw new ManagerException("前缀值长度错误");
	// } else if (!"0".equals(prefix)) {
	// throw new ManagerException("前缀值错误");
	// }
	//
	// if (!StringTools.hasText(infoMark)) {
	// throw new ManagerException("信息标识不能为空");
	// } else if (infoMark.length() != 4) {
	// throw new ManagerException("信息标识值长度错误");
	// } else if (!ObuInfoMarkType.VehicleInfo.getValue().equals(infoMark)) {
	// throw new ManagerException("信息标识值错误");
	// }
	//
	// if (!StringTools.hasText(plateNum)) {
	// throw new ManagerException("车牌号码不能为空");
	// } else if (plateNum.length() > 12) {
	// throw new ManagerException("车牌号码长度错误");
	// } else if (MyPatterns.checkPlateNumFormat(plateNum)) {
	// throw new ManagerException("车牌号码格式错误");
	// }
	//
	// if (!StringTools.hasText(plateColor)) {
	// throw new ManagerException("车牌颜色不能为空");
	// } else if (plateColor.length() > 12) {
	// throw new ManagerException("车牌颜色长度错误");
	// }
	// if (!StringTools.hasText(vehicleType)) {
	// throw new ManagerException("车辆类型不能为空");
	// } else if (Integer.valueOf(vehicleType) > 255 ||
	// Integer.valueOf(vehicleType) < 0) {
	// throw new ManagerException("车辆类型值错误");
	// }
	// if (!StringTools.hasText(userType)) {
	// throw new ManagerException("用户类型不能为空");
	// } else if (Integer.valueOf(userType) > 255 || Integer.valueOf(userType) <
	// 0) {
	// throw new ManagerException("用户类型值错误");
	// }
	//
	// if (!StringTools.hasText(vehicleLength)) {
	// throw new ManagerException("车长不能为空");
	// } else if (Integer.valueOf(vehicleLength) > 255 ||
	// Integer.valueOf(vehicleLength) < 0) {
	// throw new ManagerException("车长值错误");
	// }
	// if (!StringTools.hasText(vehicleWidth)) {
	// throw new ManagerException("车宽不能为空");
	// } else if (Integer.valueOf(vehicleWidth) > 255 ||
	// Integer.valueOf(vehicleWidth) < 0) {
	// throw new ManagerException("车宽值错误");
	// }
	// if (!StringTools.hasText(vehicleHeight)) {
	// throw new ManagerException("车高不能为空");
	// } else if (Integer.valueOf(vehicleHeight) > 255 ||
	// Integer.valueOf(vehicleHeight) < 0) {
	// throw new ManagerException("车高值错误");
	// }
	// if (!StringTools.hasText(wheelsCount)) {
	// throw new ManagerException("车轮数不能为空");
	// } else if (Integer.valueOf(wheelsCount) > 255 ||
	// Integer.valueOf(wheelsCount) < 0) {
	// throw new ManagerException("车轮数值错误");
	// }
	// if (!StringTools.hasText(axleCount)) {
	// throw new ManagerException("车轴不能为空");
	// } else if (Integer.valueOf(axleCount) > 255 || Integer.valueOf(axleCount)
	// < 0) {
	// throw new ManagerException("车轴值错误");
	// }
	// if (!StringTools.hasText(wheelBase)) {
	// throw new ManagerException("轴距不能为空");
	// } else if (Integer.valueOf(wheelBase) > 255 || Integer.valueOf(wheelBase)
	// < 0) {
	// throw new ManagerException("轴距值错误");
	// }
	// if (!StringTools.hasText(loadOrSeat)) {
	// throw new ManagerException("载重/客座不能为空");
	// } else if (Integer.valueOf(loadOrSeat) > 16777215 ||
	// Integer.valueOf(loadOrSeat) < 0) {
	// throw new ManagerException("载重/客座值错误");
	// }
	//
	// if (!StringTools.hasText(vehicleFeature)) {
	// throw new ManagerException("车辆特征不能为空");
	// } else if (vehicleFeature.length() > 16) {
	// throw new ManagerException("车辆特征长度错误");
	// }
	// if (!StringTools.hasText(engineNum)) {
	// throw new ManagerException("发动机编号不能为空");
	// } else if (engineNum.length() > 16) {
	// throw new ManagerException("发动机编号长度错误");
	// }
	//
	// }

	// private static void validOldSysInfo(String[] split) throws
	// ManagerException {
	// String prefix = split[0];// 前缀 一般为0
	// String infoMark = split[1];// 信息标示 系统信息为 "系统信息"
	// String issuerId = split[2];// 服务提供商编码，字符16个固定长度
	// String protocolType = split[3];// 协约类型，字符2个固定长度
	// String contractVersion = split[4];// 合同版本，字符2个固定长度
	// String contractSerialNum = split[5];// 合同序列号，字符16个固定长度
	// String contractEnableDate = split[6];// 合同生效日期，字符8个固定长度
	// String contractExpireDate = split[7];// 合同失效日期，字符8个固定长度
	// String loadSatus = split[8];// 拆缷状态，数值0-1 ,0表示未拆，1表示已拆
	// if (!StringTools.hasText(prefix)) {
	// throw new ManagerException("前缀不能为空");
	// } else if (prefix.length() != 1) {
	// throw new ManagerException("前缀值长度错误");
	// } else if (!"0".equals(prefix)) {
	// throw new ManagerException("前缀值错误");
	// }
	// if (!StringTools.hasText(infoMark)) {
	// throw new ManagerException("信息标识不能为空");
	// } else if (infoMark.length() != 4) {
	// throw new ManagerException("信息标识值长度错误");
	// } else if (!ObuInfoMarkType.SystemInfo.getValue().equals(infoMark)) {
	// throw new ManagerException("信息标识值错误");
	// }
	// if (!StringTools.hasText(issuerId)) {
	// throw new ManagerException("服务提供商编码不能为空");
	// } else if (issuerId.length() != 16) {
	// throw new ManagerException("服务提供商编码长度错误");
	// }
	// if (!StringTools.hasText(protocolType)) {
	// throw new ManagerException("协约类型不能为空");
	// } else if (protocolType.length() != 2) {
	// throw new ManagerException("协约类型长度错误");
	// }
	// if (!StringTools.hasText(contractVersion)) {
	// throw new ManagerException("合同版本不能为空");
	// } else if (contractVersion.length() != 2) {
	// throw new ManagerException("合同版本长度错误");
	// }
	// if (!StringTools.hasText(contractSerialNum)) {
	// throw new ManagerException("合同序列号不能为空");
	// } else if (contractSerialNum.length() != 16) {
	// throw new ManagerException("合同序列号长度错误");
	// }
	// if (!StringTools.hasText(contractEnableDate)) {
	// throw new ManagerException("合同生效日期不能为空");
	// } else if (contractEnableDate.length() != 8) {
	// throw new ManagerException("合同生效日期长度错误");
	// }
	// if (!StringTools.hasText(contractExpireDate)) {
	// throw new ManagerException("合同失效日期不能为空");
	// } else if (contractExpireDate.length() != 8) {
	// throw new ManagerException("合同失效日期长度错误");
	// }
	// if (!StringTools.hasText(loadSatus)) {
	// throw new ManagerException("拆缷状态不能为空");
	// } else if (loadSatus.length() != 2) {
	// throw new ManagerException("拆缷状态长度错误");
	// }
	// }
}