package cn.com.taiji.css.manager.apply.quickapply;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.IssuePackagePayStatus;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.apply.baseinfo.VehicleManagementManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.manager.customerservice.obu.Obu4XCheckFromPcManager;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.manager.util.CssUtil;
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
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.manager.comm.client.StorageBinService;
import cn.com.taiji.dsi.manager.comm.client.ValidationBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.validation.PlateCheckRequest;
import cn.com.taiji.dsi.model.comm.protocol.validation.PlateCheckResponse;
import cn.com.taiji.dsi.model.common.storage.InventoryCheckRequest;
import cn.com.taiji.dsi.model.common.storage.InventoryCheckResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.StorageObuInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.CustomerType;
import cn.com.taiji.qtk.entity.dict.IssueStatusType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.entity.dict.StorageStatus;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;
import cn.com.taiji.qtk.repo.jpa.CarIssuePackageInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;
import cn.com.taiji.qtk.repo.jpa.IssuePackageInfoRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;
@Service
public class EquipmentIssueManagerImpl extends AbstractDsiCommManager implements EquipmentIssueManager{
	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private OBUInfoRepo obuInfoRepo;
	@Autowired
	private StorageBinService storageBinService;
	@Autowired
	private ValidationBinService validationBinService;
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private VehicleInfoRepo vehicleInfoRepo;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private StorageObuInfoRepo storageObuInfoRepo;
	@Autowired
	private CarIssuePackageInfoRepo carIssuePackageInfoRepo;
	@Autowired
	private IssuePackageInfoRepo issuePackageInfoRepo;
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private VehicleManagementManager vehicleManagementManager;
	@Autowired
	private Obu4XCheckFromPcManager obu4XCheckFromPcManager;
	@Transactional
	@Override
	public ObuApplyAndConfirmResponse obuApplyAndConfirm(ObuApplyAndConfirmRequest appRequest, User user)
			throws ManagerException {
		ObuApplyAndConfirmResponse response = new ObuApplyAndConfirmResponse();
		if(obu4XCheckFromPcManager.check4X(appRequest.getObuId())) {
			response.setMessage("请换2XOBU继续发行！");
			response.setStatus(-1);
			return response;
		}
		if(appRequest.isApplyOrChange()) {
			OBUInfoQueryResponse obuInfoRes= OBUInfoQuery(appRequest.getOldObuId(),user);
			appRequest.setCustomerId(obuInfoRes.getUserId());
			appRequest.setVehicleId(obuInfoRes.getVehicleId());
			appRequest.setNetId(obuInfoRes.getNetId());
			appRequest.setModel(obuInfoRes.getModel());
			appRequest.setBrand(obuInfoRes.getBrand());
		}
		if(appRequest.isApplyOrChange()&&appRequest.getType()==1) {
			InventoryCheckResponse invenRes = new InventoryCheckResponse();
			invenRes = inventoryCheck(appRequest.getObuId(),user);
			if(!invenRes.isFlag()) {
				response.setStatus(0);
				response.setMessage(invenRes.getMessage());
				return response;
			}
		}
		
		if(appRequest.getType() == 1) {
			response = obuApply(appRequest, user);
		}else if(appRequest.getType() == 2) {
			response = obuConfirm(appRequest, user);
			if(response.getSuccess() && response.getObuId() != null) {//发行OBU确认成功，将库存改为已发行
				if(!appRequest.isApplyOrChange()) {//发行   更改发行套餐记录为以发行
					CarIssuePackageInfo carIssuePackageInfo = findCarIssuePackageByVehicleId(user.getStaff().getServiceHall().getServiceHallId(), appRequest.getVehicleId());
					if(carIssuePackageInfo != null) {
						if(carIssuePackageInfo.getObuIssueStatus() != null && carIssuePackageInfo.getObuIssueStatus().intValue() == IssueStatusType.NOTISSUANT.getCode().intValue()) {
							carIssuePackageInfo.setObuIssueStatus(IssueStatusType.ISSUANT.getCode());
							carIssuePackageInfo.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
							carIssuePackageInfoRepo.save(carIssuePackageInfo);
						}
					}
				}
				StorageObuInfo storageObuInfo = storageObuInfoRepo.findByObuIdCheck(appRequest.getObuId());
				storageObuInfo.setStatus(StorageStatus.ISSUE.getCode());
				storageObuInfo.setStatusChangeTime(QTKUtils.getDateString());
				storageObuInfoRepo.save(storageObuInfo);
			}
		}else {
			response = new ObuApplyAndConfirmResponse();
			response.setManagerException(new ManagerException("请求类型错误，请联系管理员！"));
		}
		
		return response;
	}
	
	/**
	 * obu发行申请
	 * @param appRequest
	 * @param user
	 * @return
	 * @throws ManagerException
	 */
	private ObuApplyAndConfirmResponse obuApply(ObuApplyAndConfirmRequest appRequest,User user)
			throws ManagerException {
		ObuApplyAndConfirmResponse appResponse = new ObuApplyAndConfirmResponse();
		OBUApplyRequest req = new OBUApplyRequest();
		if(obu4XCheckFromPcManager.check4X(appRequest.getObuId())) {
			appResponse.setMessage("请换2XOBU继续发行！");
			appResponse.setStatus(-1);
			return appResponse;
		}
		super.commSet(req, user);
		req.setObuId(appRequest.getObuId());
		req.setUserId(appRequest.getCustomerId());
		req.setVehicleId(appRequest.getVehicleId());
		try {
			OBUApplyResponse response = releaseBinService.obuApply(req);
			if(response.getStatus() == 1) {
				VehicleInfo vehicle = vehicleInfoRepo.findByVehicleId(appRequest.getVehicleId());
				if(vehicle != null) {
					appResponse.setPlateNum(vehicle.getVehiclePlate());
					appResponse.setPlateColor(vehicle.getVehiclePlateColor());
					appResponse.setVehicleType(vehicle.getType());
					//用户类型待定
					if(vehicle.getEmergencyFlag() != null && vehicle.getEmergencyFlag().intValue() == 1) {
						appResponse.setUserType(26);
					}else {
						appResponse.setUserType(0);
					}
					String outsideDimensions = vehicle.getOutsideDimensions();
					if(outsideDimensions != null) {
						String[] osd = outsideDimensions.split(CssConstant.OUTSIDEDIMENSIONS_SAMBOL);
						if(osd.length == 3) {
							try {
								appResponse.setVehicleLength(mmToDm(osd[0]));
								appResponse.setVehicleWidth(mmToDm(osd[1]));
								appResponse.setVehicleHeight(mmToDm(osd[2]));
							} catch (Exception e) {
								e.printStackTrace();
								appResponse.setVehicleLength(0);
								appResponse.setVehicleWidth(0);
								appResponse.setVehicleHeight(0);
							}
						}else {
							appResponse.setVehicleLength(0);
							appResponse.setVehicleWidth(0);
							appResponse.setVehicleHeight(0);
						}
					}else {
						appResponse.setVehicleLength(0);
						appResponse.setVehicleWidth(0);
						appResponse.setVehicleHeight(0);
					}
					appResponse.setWheelsCount(vehicle.getWheelCount());
					appResponse.setAxleCount(vehicle.getAxleCount());
					if (vehicle.getAxleDistance() == null) {
						appResponse.setWheelBase(0);
					}else {
						appResponse.setWheelBase(vehicle.getAxleDistance());
					}
					if(String.valueOf(vehicle.getType()).length() == 2){//如果是货车写载重
						if (vehicle.getPermittedWeight() == null) {
							appResponse.setLoadOrSeat(0);
						}else {
							appResponse.setLoadOrSeat(vehicle.getPermittedWeight());
						}
					}else{
						if (vehicle.getApprovedCount() == null) {
							appResponse.setLoadOrSeat(0);
						}else {
							appResponse.setLoadOrSeat(vehicle.getApprovedCount());
						}
					}
					//obu写入特征字段长度不能大于16位，因为汉字占2位，如果vehicleModel长度大于8，将其截取前8位
					if(vehicle.getVehicleModel() != null && vehicle.getVehicleModel().length() > 8) {
						vehicle.setVehicleModel(vehicle.getVehicleModel().substring(0, 8));
					}
					//obu写入发动机编号长度不能大于16位，如果车辆信息大于，截取前16位
					if(vehicle.getEngineNum() != null && vehicle.getEngineNum().length() > 16) {
						vehicle.setEngineNum(vehicle.getEngineNum().substring(0, 16));
					}
					appResponse.setVehicleFeature(vehicle.getVehicleModel());
					appResponse.setEngineNum(vehicle.getEngineNum());
					appResponse.setStatus(response.getStatus());
					appResponse.setMessage(response.getMessage());
					appResponse.setSuccess(true);
				}else {
					appResponse.setManagerException(new ManagerException("获取车辆信息失败！"));
				}
			}else {
				appResponse.setManagerException(new ManagerException(response.getMessage() + "OBU发行申请出错！"));
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			appResponse.setManagerException(new ManagerException(e.getMessage() + "OBU发行申请出错！"));
		}
		if(!appRequest.isApplyOrChange()) {//发行   将obu编号记录入发行套餐记录
			CarIssuePackageInfo carIssuePackageInfo = findCarIssuePackageByVehicleId(user.getStaff().getServiceHall().getServiceHallId(), appRequest.getVehicleId());
			if(carIssuePackageInfo != null) {
				if(carIssuePackageInfo.getObuIssueStatus() != null && carIssuePackageInfo.getObuIssueStatus().intValue() == IssueStatusType.NOTISSUANT.getCode().intValue()) {
					carIssuePackageInfo.setObuId(appRequest.getObuId());
					carIssuePackageInfo.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
					carIssuePackageInfoRepo.save(carIssuePackageInfo);
				}
			}
		}
		return appResponse;
	}
	//将车辆外廓尺寸String的(mm)转换成int的(dm)
	private static int mmToDm(String osd) {
		return Double.valueOf(Math.ceil(Double.valueOf(osd)/100)).intValue();
	}
	//OBU发行确认
	private ObuApplyAndConfirmResponse obuConfirm(ObuApplyAndConfirmRequest appRequest,User user) throws ManagerException {
		ObuApplyAndConfirmResponse appResponse = new ObuApplyAndConfirmResponse();
		OBUConfirmRequest req = new OBUConfirmRequest();
		super.commSet(req, user);
		req.setObuId(appRequest.getObuId());
		req.setNetId("5201");
		try {
			req.setBrand(appRequest.getBrand());
		} catch (Exception e) {
			e.printStackTrace();
			req.setBrand(0);
		}
		if(appRequest.getModel() == null) {
			req.setModel("0");
		}else {
			req.setModel(appRequest.getModel());
		}
		LocalDateTime now = LocalDateTime.now();
		String enableTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		String expireTime = now.plusYears(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		req.setEnableTime(enableTime);
		req.setExpireTime(expireTime);
		try {
			OBUConfirmResponse response = releaseBinService.obuConfirm(req);
			appResponse.setObuId(response.getObuId());
			appResponse.setMessage(response.getMessage());
			appResponse.setStatus(response.getStatus());
			if(response.getObuId() == null) {
				appResponse.setSuccess(false);
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			appResponse.setManagerException(new ManagerException(e.getMessage() + "OBU发行确认失败！"));
		}
		return appResponse;
	}
	
	@Override
	public CssInventoryCheckResponse inventoryVerify(InventoryVerifyRequset ivq,User user) throws ManagerException {
		InventoryCheckRequest req = new InventoryCheckRequest();
		CssInventoryCheckResponse response = new CssInventoryCheckResponse();
		super.commSet(req, user);
		req.setType(ivq.getType());
		if(ivq.getType() == 1) {
			req.setCardId(ivq.getCardId());
		}
		if(ivq.getType() == 2) {
			req.setObuId(ivq.getObuId());
		}
		req.setServiceHallId(user.getStaff().getServiceHallId());
		InventoryCheckResponse icr;
		try {
			icr = storageBinService.inventoryCheck(req);
			if(icr.isFlag()) {
				response.setBrand(icr.getBrand());
				response.setModel(icr.getModel());
				response.setCardType(icr.getCardType());
			}else {
				response.setSuccess(false);
				response.setMessage(icr.getMessage());
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			response.setManagerException(new ManagerException(e.getMessage()+"库存校验失败！"));
		}
		return response;
	}
	
	@Override
	@Transactional
	public SaveAndPayIssuePackageResponse saveCarIssuePackage(SaveIssuePackageRequest appReq, User user) {
		SaveAndPayIssuePackageResponse response = new SaveAndPayIssuePackageResponse();
		if(appReq.getRemarks() != null && appReq.getRemarks().length() > 120) {
			response.setManagerException(new ManagerException("备注信息不能大于120个字符"));
			return response;
		}
		try {
			ExistingIssuepackageCheck(user, appReq.getVehicleId());//刷pos机记录，半条校验,校验不通过，返回结果
		} catch (ManagerException e1) {
			e1.printStackTrace();
			response.setManagerException(e1);
			return response;
		}
		IssuePackageInfo p =issuePackageInfoRepo.findByPackageNum(appReq.getPackageNum());
		Double totalMoney =p.getRechargeMoney()+p.getCardCost()+p.getObuCost();
		CarIssuePackageInfo cipi = new CarIssuePackageInfo();
		cipi.setStaffId(user.getStaffId());
		cipi.setServiceHallId(user.getStaff().getServiceHallId());
	    cipi.setVehicleId(appReq.getVehicleId());
		cipi.setStatus(IssuePackagePayStatus.NOPAY.getCode());
	    cipi.setPackageNum(appReq.getPackageNum());
		cipi.setCreateTime(Calendar.getInstance());
	    cipi.setHandleTime(CssUtil.getNowDateTimeStrWithoutT());
	    cipi.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
		cipi.setReceiveMoney(Double.valueOf(totalMoney));
		Integer cardIssueStatus = IssueStatusType.CANNOTISSUE.getCode();
		Integer obuIssueStatus = IssueStatusType.CANNOTISSUE.getCode();
		if(p.getIssueType() == 1) {
			cardIssueStatus = IssueStatusType.NOTISSUANT.getCode();
		}else if (p.getIssueType() == 2) {
			obuIssueStatus = IssueStatusType.NOTISSUANT.getCode();
		}else if (p.getIssueType() == 3) {
			cardIssueStatus = IssueStatusType.NOTISSUANT.getCode();
			obuIssueStatus = IssueStatusType.NOTISSUANT.getCode();
		}
		cipi.setCardIssueStatus(cardIssueStatus);
		cipi.setObuIssueStatus(obuIssueStatus);
		cipi.setRechargeMoney(p.getRechargeMoney());
		cipi.setObuCost(p.getObuCost());
		cipi.setCardCost(p.getCardCost());
		cipi.setRechargeType(p.getRechargeType());
		cipi.setObuCostType(p.getObuCostType());
		cipi.setCardCostType(p.getCardCostType());
		cipi.setRemarks(appReq.getRemarks());
		CarIssuePackageInfo info;
		try {
			info = carIssuePackageInfoRepo.save(cipi);
			response.setMessage("保存发行套餐成功！");
		} catch (Exception e) {
			e.printStackTrace();
			response.setManagerException(new ManagerException("保存发行套餐失败！"));
			return response;
		}
		response.setCarIssuePackageInfo(info);
		return response;
	}
	
	@Override
	@Transactional
	public SaveAndPayIssuePackageResponse payCarIssuePackage(User user, PayIssuePackageRequest req) {
		CarIssuePackageInfo carIssuePackageInfo = carIssuePackageInfoRepo.findById(req.getCarIssuePackageInfo().getId()).get();
		SaveAndPayIssuePackageResponse response = new SaveAndPayIssuePackageResponse();
		carIssuePackageInfo.setStatus(IssuePackagePayStatus.PAY.getCode());
		carIssuePackageInfo.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
		CarIssuePackageInfo info;
		try {
			info = carIssuePackageInfoRepo.save(carIssuePackageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			response.setManagerException(new ManagerException("支付套餐费用失败！"));
			return response;
		}
		
		IssuePackageInfo p =issuePackageInfoRepo.findByPackageNum(carIssuePackageInfo.getPackageNum());
		PackageTotalMoneyResponse moneyPlayType = moneyPlayType(p);
		Double cash = moneyPlayType.getTotalCash()*100;
		Double pos = moneyPlayType.getTotalPos()*100;
		try {
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ISSUEPACKAGE, ChargeType.CASH, 0, cash.longValue() , null , null ,carIssuePackageInfo.getVehicleId());
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ISSUEPACKAGE, ChargeType.COMMONPOS, 0, pos.longValue() , null , null ,carIssuePackageInfo.getVehicleId());
			response.setMessage("支付发行费用成功！");
		} catch (ManagerException e) {
			e.printStackTrace();
			response.setManagerException(new ManagerException("支付套餐费用失败！"+e.getMessage()));
		}
		response.setCarIssuePackageInfo(info);
//		response.setIssuePackage(p);
		return response;
	}
	
	@Override
	public IssuePackageInfo findByPackageNum(String packageNum) {
		return issuePackageInfoRepo.findByPackageNum(packageNum);
	}
	
	@Override
	public CarIssuePackageInfo findCarIssuePackageByVehicleId(String servicehallId, String vehicleId) {
		List<CarIssuePackageInfo> list = carIssuePackageInfoRepo.findByVehicleIdCreateTimeDesc(servicehallId, vehicleId);
		if(list.size() > 0) return list.get(0);
		return null;
	}
	
	@Override
	public PackageTotalMoneyResponse moneyPlayType(IssuePackageInfo p) {
		Double totalMoney =p.getRechargeMoney()+p.getCardCost()+p.getObuCost();
		PackageTotalMoneyResponse ptr = new PackageTotalMoneyResponse();
		//现金总金额
		Double totalCash = 0.0;
		//POS总金额
		Double totalPos = 0.0;
		if(p.getRechargeType() == 0) {
			totalPos += p.getRechargeMoney();
		}else {
			totalCash += p.getRechargeMoney();
		}
		if(p.getObuCostType() == 0) {
			totalPos += p.getObuCost();
		}else {
			totalCash += p.getObuCost();
		}
		if(p.getCardCostType() == 0) {
			totalPos += p.getCardCost();
		}else {
			totalCash += p.getCardCost();
		}
		String issuantDevice = "";
		if(p.getIssueType() == 1) {
			issuantDevice = "ETC卡";
		}else if(p.getIssueType() == 2) {
			issuantDevice = "OBU";
		}else if(p.getIssueType() == 3) {
			issuantDevice = "ETC卡、OBU";
		}else {
			ptr.setManagerException(new ManagerException("发行套餐可发行设备错误！"));
			return ptr;
		}
		ptr.setTotalMoney(totalMoney);
		ptr.setTotalCash(totalCash);
		ptr.setTotalPos(totalPos);
		ptr.setRechargeMoney(p.getRechargeMoney());
		ptr.setObuCost(p.getObuCost());
		ptr.setCardCost(p.getCardCost());
		ptr.setRechargeType(p.getRechargeType());
		ptr.setObuCostType(p.getObuCostType());
		ptr.setCardCostType(p.getCardCostType());
		ptr.setIssueType(p.getIssueType());
		ptr.setIssuantDevice(issuantDevice);
		return ptr;
	}
	
	@Override
	public VehiclePlateOnlyCheckResponse vehiclePlateOnlyCheck(Integer type, VehicleInfo vehicle, User user){
		VehiclePlateOnlyCheckResponse appResponse = new VehiclePlateOnlyCheckResponse();
		if(type == 1) {
			List<CardInfo> listByVehicleIdsCheck = cardInfoRepo.listByVehicleIdsCheck(Lists.newArrayList(vehicle.getVehicleId()));
			if(listByVehicleIdsCheck != null && listByVehicleIdsCheck.size() > 0) {
				appResponse.setManagerException(new ManagerException(vehicle.getVehicleId()+"该车已发行有卡！卡号：" + listByVehicleIdsCheck.get(0).getCardId()));
				return appResponse;
			}
		}
		if(type == 2) {
			List<OBUInfo> listByVehicleIdsCheck = obuInfoRepo.listByVehicleIdsCheck(Lists.newArrayList(vehicle.getVehicleId()));
			if(listByVehicleIdsCheck != null && listByVehicleIdsCheck.size() > 0) {
				appResponse.setManagerException(new ManagerException(vehicle.getVehicleId()+"该车已发行有OBU！OBU号：" + listByVehicleIdsCheck.get(0).getObuId()));
				return appResponse;
			}
		}
		if(type == 0) {
			List<CardInfo> cardListByVehicleIdsCheck = cardInfoRepo.listByVehicleIdsCheck(Lists.newArrayList(vehicle.getVehicleId()));
			List<OBUInfo> obuListByVehicleIdsCheck = obuInfoRepo.listByVehicleIdsCheck(Lists.newArrayList(vehicle.getVehicleId()));
			if(cardListByVehicleIdsCheck != null && cardListByVehicleIdsCheck.size() > 0) {
				if(obuListByVehicleIdsCheck != null && obuListByVehicleIdsCheck.size() > 0) {
					appResponse.setManagerException(new ManagerException(vehicle.getVehicleId()+"该车已发行有卡、OBU！卡号：" + cardListByVehicleIdsCheck.get(0).getCardId() + "OBU号：" + obuListByVehicleIdsCheck.get(0).getObuId()));
					return appResponse;
				}else {
					appResponse.setManagerException(new ManagerException(vehicle.getVehicleId()+"该车已发行有卡！卡号：" + cardListByVehicleIdsCheck.get(0).getCardId()));
					return appResponse;
				}
			}else {
				if(obuListByVehicleIdsCheck != null && obuListByVehicleIdsCheck.size() > 0) {
					appResponse.setManagerException(new ManagerException(vehicle.getVehicleId()+"该车已发行有OBU！OBU号：" + obuListByVehicleIdsCheck.get(0).getObuId()));
					return appResponse;
				}
			}
			
		}
		if(vehicle.getEmergencyFlag() != null && vehicle.getEmergencyFlag().intValue() == 1) {
			return appResponse;
		}
		PlateCheckRequest req = new PlateCheckRequest();
		super.commSet(req, user);
		req.setVehiclePlate(vehicle.getVehiclePlate());
		req.setVehicleColor(vehicle.getVehiclePlateColor());
		req.setVehicleType(vehicle.getType());
		req.setReleaseType(type);
		PlateCheckResponse response;
		try {
			response = validationBinService.plateCheck(req);
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			appResponse.setManagerException(new ManagerException(e.getMessage()+"联网中心车牌校验出错！"));
			return appResponse;
		}
		if(response.getResult() == null) {
			appResponse.setManagerException(new ManagerException(response.getMessage()));
			return appResponse;
		}
		if(!response.getResult()) {
			appResponse.setManagerException(new ManagerException(response.getInfo()));
			return appResponse;
		}
		return appResponse;
	}
	
	private OBUInfoQueryResponse OBUInfoQuery(String obuId,User user) throws ManagerException {
		
		OBUInfoQueryRequest oBUInfoQueryReq = new OBUInfoQueryRequest();
		OBUInfoQueryResponse oBUInfoQueryRes = new OBUInfoQueryResponse();
		super.commSet(oBUInfoQueryReq,user);
		oBUInfoQueryReq.setObuId(obuId);
		
		try {
			oBUInfoQueryRes = inqueryBinService.obuInfoQuery(oBUInfoQueryReq);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new ManagerException("OBU信息查询失败。");
		}
		
		return oBUInfoQueryRes;
		
	}

	@Override
	public CarIssuePackageCheckResponse carIssuePackageCheck(User user, CarIssuePackageCheckRequest req) {
		CarIssuePackageCheckResponse response = new CarIssuePackageCheckResponse();
		CarIssuePackageInfo carIssuePackage = findCarIssuePackageByVehicleId(user.getStaff().getServiceHall().getServiceHallId(), req.getVehicleId());
		if(carIssuePackage == null || (carIssuePackage.getStatus() != IssuePackagePayStatus.PAY.getCode() && carIssuePackage.getStatus() != IssuePackagePayStatus.NOPAY.getCode())) {
			response.setSuccess(false);
			response.setMessage("请先保存并支付发行套餐！");
			return response;
		}
		if(carIssuePackage.getStatus() == IssuePackagePayStatus.NOPAY.getCode()) {
			response.setSuccess(false);
			response.setMessage("已保存发行套餐，但未支付成功，请重选择发行套餐！");
			return response;
		}
		if(req.getType() != null && req.getType() == 1) {
			if(carIssuePackage.getCardIssueStatus() != null && carIssuePackage.getCardIssueStatus() == IssueStatusType.NOTISSUANT.getCode()) {
				response.setSuccess(true);
				response.setMessage("已确认支付并保存发行套餐,未发卡！");
			}else {
				response.setSuccess(false);
				response.setMessage("请先保存并支付发行套餐！");
			}
		}else if (req.getType() != null && req.getType() == 2) {
			if(carIssuePackage.getObuIssueStatus() != null && carIssuePackage.getObuIssueStatus() == IssueStatusType.NOTISSUANT.getCode()) {
				response.setSuccess(true);
				response.setMessage("已确认支付并保存发行套餐,未发OBU！");
			}else {
				response.setSuccess(false);
				response.setMessage("请先保存并支付发行套餐！");
			}
		}else {
			response.setSuccess(false);
			response.setMessage("发行套餐校验失败，请联系管理员！");
		}
		return response;
	}
	private InventoryCheckResponse inventoryCheck(String obuId,User user) {
		InventoryCheckRequest invenReq = new InventoryCheckRequest();
		super.commSet(invenReq,user);
		invenReq.setObuId(obuId);
		invenReq.setType(2);
		invenReq.setServiceHallId(user.getStaff().getServiceHallId());
		InventoryCheckResponse invenRes = null;
		try {
			invenRes = storageBinService.inventoryCheck(invenReq);
		} catch (ApiRequestException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return invenRes;
	}

	@Override
	public List<List<String>> returnReceipt(String id, User user) throws ManagerException {
		List<List<String>> list = new ArrayList<>();
		List<String> a = new ArrayList<>();
		List<String> b = new ArrayList<>();
		List<String> c = new ArrayList<>();
		List<String> d = new ArrayList<>();
		List<String> e = new ArrayList<>();
		VehicleInfo vehicleInfo = vehicleManagementManager.findById(id);
		if (vehicleInfo == null) throw new ManagerException("未查到车辆信息！请联系管理员。");
		CustomerInfo customerInfo = customerInfoRepo.findByCustomerId(vehicleInfo.getCustomerId());
		if (customerInfo == null) throw new ManagerException("未查到客户信息！请联系管理员。");
		List<CarIssuePackageInfo> listR = carIssuePackageInfoRepo.findByVehicleIdCreateTimeDesc(user.getStaff().getServiceHall().getServiceHallId(), vehicleInfo.getVehicleId());
		CarIssuePackageInfo carIssuePackageInfo = null;
		IssuePackageInfo issuePackageInfo = null;
		CardInfo cardInfo = null;
		OBUInfo obuInfo = null;
		if (listR != null && listR.size() > 0) {
			carIssuePackageInfo = listR.get(0);
			if (carIssuePackageInfo.getPackageNum() != null) issuePackageInfo = findByPackageNum(carIssuePackageInfo.getPackageNum());
			if (carIssuePackageInfo.getCardIssueStatus().intValue() == IssueStatusType.ISSUANT.getCode().intValue() && carIssuePackageInfo.getCardId() != null) cardInfo = cardInfoRepo.findByCardId(carIssuePackageInfo.getCardId());
			if (carIssuePackageInfo.getObuIssueStatus().intValue() == IssueStatusType.ISSUANT.getCode().intValue() && carIssuePackageInfo.getObuId() != null) obuInfo = obuInfoRepo.findByObuId(carIssuePackageInfo.getObuId());
		}
		a.add("客户类型：" + (CustomerType.valueOfCode(customerInfo.getCustomerType()) != null ? CustomerType.valueOfCode(customerInfo.getCustomerType()).getValue() : ""));
		a.add("客户名称：" + customerInfo.getCustomerName());
		a.add("证件类型：" + (CustomerIDType.valueOfCode(customerInfo.getCustomerIdType()) != null ? CustomerIDType.valueOfCode(customerInfo.getCustomerIdType()).getValue() : ""));
		a.add("证件号码：" + customerInfo.getCustomerIdNum());
		a.add("手机号码：" + customerInfo.getTel());

		b.add("车牌号码：" + vehicleInfo.getVehiclePlate());
		b.add("车牌颜色：" + (VehiclePlateColorType.valueOfCode(vehicleInfo.getVehiclePlateColor()) != null ? VehiclePlateColorType.valueOfCode(vehicleInfo.getVehiclePlateColor()).getValue() : ""));
		b.add("收费车型：" + (VehicleType.valueOfCode(vehicleInfo.getType()) != null ? VehicleType.valueOfCode(vehicleInfo.getType()).getValue() : ""));
		b.add("品牌型号：" + vehicleInfo.getVehicleModel());
		b.add("指定联系人：" + vehicleInfo.getContacts());
		b.add("车辆识别代号：" + vehicleInfo.getVIN());
		b.add("核定座位数：" + (vehicleInfo.getApprovedCount() != null ? vehicleInfo.getApprovedCount() : ""));
		b.add("核定载质量(kg)：" + (vehicleInfo.getPermittedWeight() != null ? vehicleInfo.getPermittedWeight() : ""));
		
		if(issuePackageInfo != null) {
			String issuantDevice = "";
			if(issuePackageInfo.getIssueType() == 1) {
				issuantDevice = "ETC卡";
			}else if(issuePackageInfo.getIssueType() == 2) {
				issuantDevice = "OBU";
			}else if(issuePackageInfo.getIssueType() == 3) {
				issuantDevice = "ETC卡、OBU";
			}else {
				issuantDevice = "异常！";
			}
			c.add("发行套餐：" + issuePackageInfo.getPackageName());
			c.add("总费用：" + carIssuePackageInfo.getReceiveMoney().toString() + "元");
			c.add("可发行设备：" + issuantDevice);
			c.add("充值费用：" + issuePackageInfo.getRechargeMoney().toString() + "元");
			c.add("卡费用：" + issuePackageInfo.getCardCost().toString() + "元");
			c.add("电子标签费用：" + issuePackageInfo.getObuCost().toString() + "元");
		}else {
			c.add("发行套餐：" + "未查到发行套餐信息");
		}
		
		StringBuilder cardString = new StringBuilder();
		if(cardInfo != null) {
			if(cardInfo.getCardType() != null) {
				cardString.append(cardType(cardInfo.getCardType()));
				cardString.append("——卡号：");
				cardString.append(cardInfo.getCardId());
			}else {
				cardString.append("卡号：");
				cardString.append(cardInfo.getCardId());
			}
		}
		StringBuilder obuString = new StringBuilder();
		if(obuInfo != null) {
			obuString.append("电子标签——电子标签号：");
			obuString.append(obuInfo.getObuId());
		}
		if(!cardString.toString().equals("")) {
			d.add("办理业务：" + cardString.toString());
			if(!obuString.toString().equals("")) {
				d.add("办理业务：" + obuString.toString());
			}
		}else if(!obuString.toString().equals("")) {
			d.add("办理业务：" + obuString.toString());
		}else {
			d.add("办理业务：未查到办理信息");
		}

		e.add("业务办理时间：" + (carIssuePackageInfo != null ? carIssuePackageInfo.getHandleTime() : ""));
		e.add("业务办理网点：" + user.getStaff().getServiceHall().getName());
		e.add("业务办理人：" + user.getName());
		e.add("打印时间：" + CssUtil.getNowDateTimeStrWithoutT());
		e.add("");
		e.add("客户签字：" + "____________");
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		return list;
	}

	private String cardType(Integer type) {
		String temp = String.valueOf(type).substring(0, 1);
		if ("1".equals(temp))
			return "记账卡";
		else
			return "储值卡";
	}
	
	@Override
	public void VehicleInfoCheck(VehicleInfo vehicle) throws ManagerException {
		if (vehicle.getType() != null) {
			if (VehicleType.isCar(vehicle.getType())) {
				if(vehicle.getApprovedCount() == null || vehicle.getApprovedCount() < 1)
					throw new ManagerException("核定座位数有误，请先到：基础信息->车辆信息修改车辆信息。");
			} else if (VehicleType.isWeightCar(vehicle.getType())) {
				if(vehicle.getPermittedWeight() == null || vehicle.getPermittedWeight() < 1)
					throw new ManagerException("核定载重有误，请先到：基础信息->车辆信息修改车辆信息。");
			}
		}else {
			throw new ManagerException("车型有误，请先到：基础信息->车辆信息修改车辆信息。");
		}
		
	}

	@Override
	public void CustomerInfoCheck(CustomerInfo customer) throws ManagerException {
		if(customer.getCustomerName() == null || customer.getCustomerName().equals(" "))
			throw new ManagerException("用户名称不能为空，请先到：基础信息->用户信息，修改用户信息。");
	}

	@Override
	public void ExistingIssuepackageCheck(User user, String vehicleId) throws ManagerException {
		CarIssuePackageInfo packageInfo = findCarIssuePackageByVehicleId(user.getStaff().getServiceHall().getServiceHallId(), vehicleId);
		Double totalPos = 0.0;
		if(packageInfo == null) return;
		if(packageInfo.getStatus() == 1) {//如果为已支付状态，判断是否有收费且已完成发行
			if(!(packageInfo.getReceiveMoney() > 0)) return; //若果套餐收费总金额不大于0，放过校验
			if(packageInfo.getCardIssueStatus() == IssueStatusType.NOTISSUANT.getCode() && packageInfo.getObuIssueStatus() == IssueStatusType.NOTISSUANT.getCode())
				throw new ManagerException("上一条收费发行套餐记录，卡和签未完成发行");
			if(packageInfo.getCardIssueStatus() == IssueStatusType.NOTISSUANT.getCode())
				throw new ManagerException("上一条收费发行套餐记录，卡未完成发行");
			if(packageInfo.getObuIssueStatus() == IssueStatusType.NOTISSUANT.getCode())
				throw new ManagerException("上一条收费发行套餐记录，签未完成发行");
			return;//满足所有条件，校验通过
		}
		if(packageInfo.getRechargeType() != null && packageInfo.getRechargeType() == 0)
			totalPos += packageInfo.getRechargeMoney();
		if(packageInfo.getObuCostType() != null && packageInfo.getObuCostType() == 0)
			totalPos += packageInfo.getObuCost();
		if(packageInfo.getCardCostType() != null && packageInfo.getCardCostType() == 0)
			totalPos += packageInfo.getCardCost();
		if(totalPos > 0) throw new ManagerException("上一条刷POS发行套餐记录存在半条，请联系管理员处理！");
		if(packageInfo.getReceiveMoney() > 0) throw new ManagerException("上一条收费发行套餐记录存在半条，请联系管理员处理！");
	}

}
