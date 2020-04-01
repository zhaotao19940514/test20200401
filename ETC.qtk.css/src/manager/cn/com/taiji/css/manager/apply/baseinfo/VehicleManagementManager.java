package cn.com.taiji.css.manager.apply.baseinfo;

import java.util.List;

import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.apply.customermanager.VehicleCheckRequset;
import cn.com.taiji.css.model.apply.customermanager.VehicleInfoRequest;
import cn.com.taiji.css.model.apply.customermanager.VehicleInfoResponse;
import cn.com.taiji.css.model.apply.customermanager.VehicleManagementRequest;
import cn.com.taiji.qtk.entity.VehicleInfo;

public interface VehicleManagementManager {
	LargePagination<VehicleInfo> queryPage(VehicleManagementRequest req);
	LargePagination<VehicleInfo> baseQueryPage(VehicleManagementRequest req);

	public VehicleInfo findByVehicleId(String vehicleId);
	
	/**
	 * 根据车辆信息表主键ID查找车辆信息
	 * @param id
	 * @return
	 * @throws ManagerException 
	 */
	public VehicleInfo findById(String id) throws ManagerException;
	
	/**
	 * 车辆信息编辑    返回数据库数据转换后的车辆信息
	 * @param id
	 * @return
	 * @throws ManagerException 
	 */
	public VehicleInfoResponse findByIdEdit(String id) throws ManagerException;
	
	public String addCar(VehicleInfoRequest VehicleInfoRequest, User user) throws ManagerException;

	public String update(@Valid VehicleInfoRequest vehicleInfo, User user) throws ManagerException;

	public String delete(@Valid VehicleInfoRequest vehicleInfo, User user) throws ManagerException;
	
	/**
	 * 车辆本地信息是否存在校验
	 * @param vcr
	 * @return
	 */
	public AppAjaxResponse vehicleCheck(VehicleCheckRequset vcr);
	
	/**
	 * 获取车辆相关照片    Base64编码
	 * @param vehicleId
	 * @param type  1-获取行驶证照片  2-获取车辆照片
	 * @return
	 * @throws ManagerException
	 */
	public List<String> getVehicleInfoPicBase64(String vehicleId,Integer type) throws ManagerException;
	
	/**
	 * 应急车辆更改
	 * @param uuId 车辆信息UUID
	 * @param type 1-标记为应急车辆  0-取消应急车辆标记
	 * @return
	 * @throws ManagerException
	 */
	public VehicleInfo emergencyAlter(String uuId, Integer type) throws ManagerException; 
}
