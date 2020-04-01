package cn.com.taiji.css.manager.apply.quickapply;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.apply.customermanager.CssInventoryCheckResponse;
import cn.com.taiji.css.model.apply.customermanager.InventoryVerifyRequset;
import cn.com.taiji.css.model.apply.customermanager.PackageTotalMoneyResponse;
import cn.com.taiji.css.model.apply.quickapply.CarIssuePackageCheckRequest;
import cn.com.taiji.css.model.apply.quickapply.CarIssuePackageCheckResponse;
import cn.com.taiji.css.model.apply.quickapply.ObuApplyAndConfirmRequest;
import cn.com.taiji.css.model.apply.quickapply.ObuApplyAndConfirmResponse;
import cn.com.taiji.css.model.apply.quickapply.PayIssuePackageRequest;
import cn.com.taiji.css.model.apply.quickapply.SaveAndPayIssuePackageResponse;
import cn.com.taiji.css.model.apply.quickapply.SaveIssuePackageRequest;
import cn.com.taiji.css.model.apply.quickapply.VehiclePlateOnlyCheckResponse;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;

public interface EquipmentIssueManager {
	
	/**
	 * OBU发行申请与确认
	 * @param appRequest
	 * @param user
	 * @return
	 * @throws ManagerException
	 */
	public ObuApplyAndConfirmResponse obuApplyAndConfirm(ObuApplyAndConfirmRequest appRequest,User user) throws ManagerException;

	/**
	 * 库存校验
	 * @param ivq
	 * @param user
	 * @return
	 * @throws ManagerException
	 */
	public CssInventoryCheckResponse inventoryVerify(InventoryVerifyRequset ivq,User user) throws ManagerException; 

	/**
	 * 车牌唯一性校验   卡、obu
	 * @param type 1-卡  2-obu 0-卡、obu
	 * @param vehicle
	 * @param user
	 * @return
	 */
	public VehiclePlateOnlyCheckResponse vehiclePlateOnlyCheck(Integer type, VehicleInfo vehicle, User user);
	
	/**
	 * 保存发行套餐
	 * @param appReq
	 * @param user
	 * @return
	 */
	public SaveAndPayIssuePackageResponse saveCarIssuePackage(SaveIssuePackageRequest appReq, User user);
	
	/**
	 * 支付发行套餐费用(将支付状态改为已支付)
	 * @param user
	 * @param carIssuePackageInfo 保存的发行套餐记录信息
	 * @return
	 */
	public SaveAndPayIssuePackageResponse payCarIssuePackage(User user, PayIssuePackageRequest req);
	
	/**
	 * 车辆选择发行套餐校验
	 * @param user
	 * @param req
	 * @return
	 */
	public CarIssuePackageCheckResponse carIssuePackageCheck(User user, CarIssuePackageCheckRequest req);
	
	/**
	 * 根据套餐编号查找套餐
	 * @param packageNum
	 * @return
	 */
	public IssuePackageInfo findByPackageNum(String packageNum);
	
	/**
	 * 根据车辆编号和网点编号查询最新添加的一条不是作废状态的车辆发行套餐记录信息
	 * @param servicehallId
	 * @param vehicleId
	 * @return
	 */
	public CarIssuePackageInfo findCarIssuePackageByVehicleId(String servicehallId, String vehicleId);
	
	/**
	 * 计算套餐金额
	 * @param p
	 * @return
	 */
	public PackageTotalMoneyResponse moneyPlayType(IssuePackageInfo p);
	
	/**
	 * 发行业务回执信息处理
	 * @param id 车辆信息UUID
	 * @param user
	 * @return
	 * @throws ManagerException
	 */
	public List<List<String>> returnReceipt(String id,User user) throws ManagerException;
	
	/**
	 * 车辆信息校验,不符合时抛出异常
	 * @param vehicle
	 * @throws ManagerException
	 */
	public void VehicleInfoCheck(VehicleInfo vehicle) throws ManagerException;
	
	/**
	 * 用户信息校验，不符合时抛出异常
	 * @param customerInfo
	 * @throws ManagerException
	 */
	public void CustomerInfoCheck(CustomerInfo customer) throws ManagerException;
	
	/**
	 * 已选发行套餐校验
	 * 校验有收费的套餐，是否存在半条（未付款的套餐记录），存在由管理员处理；是否存在有未发行的设备，存在不能再选发行套餐
	 * 方式：抛出相关异常
	 * @param user
	 * @param vehicleId
	 * @throws ManagerException
	 */
	public void ExistingIssuepackageCheck(User user, String vehicleId) throws ManagerException;
}
