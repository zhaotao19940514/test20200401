/**
 * @Title ObuVehicleInfoCommandRequest.java
 * @Package cn.com.taiji.css.model.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午3:05:28
 * @version V1.0
 */
package cn.com.taiji.css.model.ocx;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.manager.util.MyPatterns;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;

/**
 * @ClassName ObuVehicleInfoCommandRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 15:05:28
 * @E_mail yaonanlin@163.com
 */
public class ObuOfflineVehicleInfoCommandRequest extends BaseOfflineObuInfoRequest {
	private String plateNum;
	private Integer plateColor;
	private Integer vehicleType;
	private Integer userType;
	private Integer vehicleLength;
	private Integer vehicleWidth;
	private Integer vehicleHeight; 
	private Integer wheelsCount;
	private Integer axleCount;
	private Integer wheelBase;
	private Integer loadOrSeat;
	private String vehicleFeature; 
	private String engineNum;
	public Integer getWheelBase() {
		return wheelBase;
	}
	public void setWheelBase(Integer wheelBase) {
		this.wheelBase = wheelBase;
	}
	public Integer getPlateColor() {
		return plateColor;
	}
	public Integer getVehicleType() {
		return vehicleType;
	}
	public void setPlateColor(Integer plateColor) {
		this.plateColor = plateColor;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public String getVehicleFeature() {
		return vehicleFeature;
	}
	public String getEngineNum() {
		return engineNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public void setVehicleFeature(String vehicleFeature) {
		this.vehicleFeature = vehicleFeature;
	}
	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	public Integer getUserType() {
		return userType;
	}
	public Integer getVehicleLength() {
		return vehicleLength;
	}
	public Integer getVehicleWidth() {
		return vehicleWidth;
	}
	public Integer getVehicleHeight() {
		return vehicleHeight;
	}
	public Integer getWheelsCount() {
		return wheelsCount;
	}
	public Integer getAxleCount() {
		return axleCount;
	}
	public Integer getLoadOrSeat() {
		return loadOrSeat;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public void setVehicleLength(Integer vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	public void setVehicleWidth(Integer vehicleWidth) {
		this.vehicleWidth = vehicleWidth;
	}
	public void setVehicleHeight(Integer vehicleHeight) {
		this.vehicleHeight = vehicleHeight;
	}
	public void setWheelsCount(Integer wheelsCount) {
		this.wheelsCount = wheelsCount;
	}
	public void setAxleCount(Integer axleCount) {
		this.axleCount = axleCount;
	}
	public void setLoadOrSeat(Integer loadOrSeat) {
		this.loadOrSeat = loadOrSeat;
	}
	public String[] toParamArray(){
		return new String[]{
			 plateNum,
			 String.valueOf(plateColor.intValue()),
			 String.valueOf(vehicleType.intValue()),
			 String.valueOf(userType.intValue()),
			 String.valueOf(vehicleLength.intValue()),
			 String.valueOf(vehicleWidth.intValue()),
			 String.valueOf(vehicleHeight.intValue()), 
			 String.valueOf(wheelsCount==null?0:wheelsCount.intValue()),
			 String.valueOf(axleCount==null?0:axleCount.intValue()),
			 String.valueOf(wheelBase==null?0:wheelBase.intValue()),
			 String.valueOf(loadOrSeat.intValue()),
			 vehicleFeature,
			 engineNum
		};
	}
	public void valid() throws ManagerException{
		super.valid();
		MyViolationException mve = new MyViolationException();
		if(!StringTools.hasText(plateNum)){
			mve.addViolation("plateNum","车牌号码不能为空");
		}else if(plateNum.length()>12){
			mve.addViolation("plateNum","车牌号码长度错误");
		}else if(MyPatterns.checkPlateNumFormat(plateNum)){
			mve.addViolation("plateNum","车牌号码格式错误");
		}
//		BLUE("蓝色",0){},
//		YELLOW("黄色",1){},
//		BLACK("黑色",2){},
//		WHITE("白色",3){},
//		GRADIENTGREEN("渐变绿色",4){},
//		YELLOWGREENDOUBLESPELL("黄绿双拼色",5){},
//		BLUEWHITEFADE("蓝白渐变色",6){},
//		NOTDETERMINED("未确定",9){};
		
		if(plateColor==null){
			mve.addViolation("plateColor","车牌颜色不能为空");
		}else if(VehiclePlateColorType.valueOfCode(plateColor) == null){
			mve.addViolation("plateColor","车牌颜色值错误");
		}
//		else if(VehiclePlateColorType.valueOfCode(plateColor)){
//			mve.addViolation("","车牌颜色格式错误");
//		}
		if(vehicleType==null){
			mve.addViolation("vehicleType","车辆类型不能为空");
		}
		try {
			VehicleType.fromCode(vehicleType);
		} catch (RuntimeException e) {
			e.printStackTrace();
			mve.addViolation("vehicleType","车辆类型值错误");
		}
		if(userType==null){
			mve.addViolation("userType","用户类型不能为空");
		}else if(Integer.valueOf(userType)>255 || Integer.valueOf(userType)<0 ){
			mve.addViolation("userType","用户类型值错误");
		}
		if(vehicleLength==null){
			mve.addViolation("vehicleLength","车长不能为空");
		}else if(Integer.valueOf(vehicleLength)>255 || Integer.valueOf(vehicleLength)<0 ){
			mve.addViolation("vehicleLength","车长值错误");
		}
		if(vehicleWidth==null){
			mve.addViolation("vehicleWidth","车宽不能为空");
		}else if(Integer.valueOf(vehicleWidth)>255 || Integer.valueOf(vehicleWidth)<0 ){
			mve.addViolation("vehicleWidth","车宽值错误");
		}
		if(vehicleHeight==null){
			mve.addViolation("vehicleHeight","车高不能为空");
		}else if(Integer.valueOf(vehicleHeight)>255 || Integer.valueOf(vehicleHeight)<0 ){
			mve.addViolation("vehicleHeight","车高值错误");
		}
		if(wheelsCount==null){
			mve.addViolation("wheelsCount","车轮数不能为空");
		}else if(Integer.valueOf(wheelsCount)>255 || Integer.valueOf(wheelsCount)<0 ){
			mve.addViolation("wheelsCount","车轮数值错误");
		}
		if(axleCount==null){
			mve.addViolation("axleCount","车轴数不能为空");
		}else if(Integer.valueOf(axleCount)>255 || Integer.valueOf(axleCount)<0 ){
			mve.addViolation("axleCount","车轴数值错误");
		}
		if(wheelBase==null){
			mve.addViolation("wheelBase","轴距不能为空");
		}else if(Integer.valueOf(wheelBase)>65535 || Integer.valueOf(wheelBase)<0 ){
			mve.addViolation("wheelBase","轴距值错误");
		}
		if(loadOrSeat==null){
			mve.addViolation("loadOrSeat","载重/客座不能为空");
		}else if(Integer.valueOf(loadOrSeat)>16777215 || Integer.valueOf(loadOrSeat)<0 ){
			mve.addViolation("loadOrSeat","载重/客座值错误");
		}
		if(vehicleFeature==null){
			mve.addViolation("vehicleFeature","车辆特征不能为空");
		}else if(vehicleFeature.length()>16){
			mve.addViolation("vehicleFeature","车辆特征长度错误");
		}
		if(!StringTools.hasText(engineNum)){
			mve.addViolation("engineNum","发动机编号不能为空");
		}else if(engineNum.length()>16){
			mve.addViolation("engineNum","发动机编号长度错误");
		}
	}
}

