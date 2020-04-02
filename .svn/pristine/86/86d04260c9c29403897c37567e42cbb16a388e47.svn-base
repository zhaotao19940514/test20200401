package cn.com.taiji.css.manager.apply.baseinfo;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.EncodeTool;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.manager.util.FileWriter;
import cn.com.taiji.css.manager.util.MyPatterns;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.apply.customermanager.VehicleCheckRequset;
import cn.com.taiji.css.model.apply.customermanager.VehicleCheckResponse;
import cn.com.taiji.css.model.apply.customermanager.VehicleInfoRequest;
import cn.com.taiji.css.model.apply.customermanager.VehicleInfoResponse;
import cn.com.taiji.css.model.apply.customermanager.VehicleManagementRequest;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.releases.VehicleInfoSubmitRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.VehicleInfoSubmitResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.VehicleInterrelatePng;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInterrelatePngRepo;
@Service
public class VehicleManagementManagerImpl extends AbstractDsiCommManager implements VehicleManagementManager{
	@Autowired
	private VehicleInfoRepo vehicleInfoRepo;
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private OBUInfoRepo obuInfoRepo;
	@Autowired
	private VehicleInterrelatePngRepo vehicleInterrelatePngRepo;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	
	@Override
	public LargePagination<VehicleInfo> queryPage(VehicleManagementRequest req) {
		if(req.getOwnerIdType() != null || StringTools.hasText(req.getOwnerIdNum()) 
				|| StringTools.hasText(req.getOwnerName()) || StringTools.hasText(req.getCustomerId()) 
				|| StringTools.hasText(req.getVehiclePlate()) || req.getVehiclePlateColor() != null 
				) {
			LargePagination<VehicleInfo> page = vehicleInfoRepo.largePage(req);
			List<VehicleInfo> vehicleInfos = page.getResult();
			if(vehicleInfos.size() == 0) return page;//未查到车辆信息,返回原值
			Map<String,List<VehicleInfo>> vehMap = vehicleInfos.parallelStream().collect(Collectors.groupingBy(VehicleInfo::getVehicleId));
			List<String> vehicleIds = vehicleInfos.parallelStream().map(VehicleInfo::getVehicleId).collect(Collectors.toList());
			// 查卡
			List<CardInfo> cardInfos = cardInfoRepo.listByVehicleIdsCheck(vehicleIds);
			Map<String,List<CardInfo>> cardMap = cardInfos.parallelStream().collect(Collectors.groupingBy(CardInfo::getVehicleId));
			vehicleIds.forEach(id->{
				List<CardInfo> cardList = cardMap.get(id);
				vehMap.get(id).get(0).setHasCard((cardList!=null)&&(cardList.size()>0));
			});
			// 查obu
			List<OBUInfo> obuInfos = obuInfoRepo.listByVehicleIdsCheck(vehicleIds);
			Map<String,List<OBUInfo>> obuMap = obuInfos.parallelStream().collect(Collectors.groupingBy(OBUInfo::getVehicleId));
			vehicleIds.forEach(id->{
				List<OBUInfo> obuList = obuMap.get(id);
				vehMap.get(id).get(0).setHasObu(obuList!=null && obuList.size()>0);
			});
			List<VehicleInfo> pageResult = Lists.newArrayList();
			/*Collection<List<VehicleInfo>> values = vehMap.values();
			for (List<VehicleInfo> listVeh : values) {
				pageResult.addAll(listVeh);
			}*/
			for (int i = 0; i < vehicleInfos.size(); i++) {
				pageResult.add(vehMap.get(vehicleInfos.get(i).getVehicleId()).get(0));
			}
			page.setResult(pageResult);
			return page;
		}else {
			return null;
		}
	}
	@Override
	public LargePagination<VehicleInfo> baseQueryPage(VehicleManagementRequest req) {
		if(req.getOwnerIdType() != null || StringTools.hasText(req.getOwnerIdNum()) 
				|| StringTools.hasText(req.getOwnerName()) || StringTools.hasText(req.getCustomerId()) 
				|| StringTools.hasText(req.getVehiclePlate()) || req.getVehiclePlateColor() != null || req.getEmergencyFlag() != null
				) {
			LargePagination<VehicleInfo> page = vehicleInfoRepo.largePage(req);
			return page;
		}else {
			return null;
		}
	}
	@Override
	public VehicleInfo findByVehicleId(String vehicleId) {
		VehicleInfo info = vehicleInfoRepo.findByVehicleId(vehicleId);
		return info;
	}
	
	@Override
	public VehicleInfoResponse findByIdEdit(String id) throws ManagerException {
		Optional<VehicleInfo> findById = vehicleInfoRepo.findById(id);
		if(!findById.isPresent()) {
			throw new ManagerException("未查到车辆信息！");
		}
		VehicleInfo vehicleInfo = findById.get();
		VehicleInfoResponse response = new VehicleInfoResponse();
		response.setId(vehicleInfo.getId());
		response.setVehicleId(vehicleInfo.getVehicleId());
		response.setVehiclePlate(vehicleInfo.getVehiclePlate());
		response.setVehiclePlateColor(vehicleInfo.getVehiclePlateColor());
		response.setCustomerId(vehicleInfo.getCustomerId());
		response.setOwnerName(vehicleInfo.getOwnerName());
		response.setOwnerIdType(vehicleInfo.getOwnerIdType());
		response.setOwnerIdNum(vehicleInfo.getOwnerIdNum());
		response.setOwnerTel(vehicleInfo.getOwnerTel());
		response.setOwnerAddress(vehicleInfo.getOwnerAddress());
		response.setContacts(vehicleInfo.getContacts());
		response.setRegisteredType(vehicleInfo.getRegisteredType());
		response.setChannelId(vehicleInfo.getChannelId());
		response.setRegisteredTime(vehicleInfo.getRegisteredTime());
		response.setFileNum(vehicleInfo.getFileNum());
		response.setVehicleType(vehicleInfo.getVehicleType());
		response.setVehicleModel(vehicleInfo.getVehicleModel());
		response.setUseCharacter(vehicleInfo.getUseCharacter());
		response.setVIN(vehicleInfo.getVIN());
		response.setEngineNum(vehicleInfo.getEngineNum());
		response.setRegisterDate(vehicleInfo.getRegisterDate());
		response.setIssueDate(vehicleInfo.getIssueDate());
		response.setApprovedCount(vehicleInfo.getApprovedCount());
		response.setTotalMass(vehicleInfo.getTotalMass());
		response.setMaintenanceMass(vehicleInfo.getMaintenanceMass());
		response.setPermittedWeight(vehicleInfo.getPermittedWeight());
		response.setOutsideDimensions(vehicleInfo.getOutsideDimensions());
		response.setPermittedTowWeight(vehicleInfo.getPermittedTowWeight());
		response.setTestRecord(vehicleInfo.getTestRecord());
		response.setWheelCount(vehicleInfo.getWheelCount());
		response.setAxleCount(vehicleInfo.getAxleCount());
		response.setAxleDistance(vehicleInfo.getAxleDistance());
		response.setAxisType(vehicleInfo.getAxisType());
		response.setCustomerInfo(vehicleInfo.getCustomerInfo());
		response.setCreateTime(vehicleInfo.getCreateTime());
		response.setUpdateTime(vehicleInfo.getUpdateTime());
		response.setType(vehicleInfo.getType());
		response.setRefrigeratedTrucks(vehicleInfo.getRefrigeratedTrucks());
		response.setEmergencyFlag(vehicleInfo.getEmergencyFlag());
		String outsideDimensions = vehicleInfo.getOutsideDimensions();
		if(outsideDimensions != null) {
			String[] osd = outsideDimensions.split(CssConstant.OUTSIDEDIMENSIONS_SAMBOL);
			if(osd.length == 3) {
				try {
					response.setVehicleLength(Integer.valueOf(osd[0]));
					response.setVehicleWidth(Integer.valueOf(osd[1]));
					response.setVehicleHeight(Integer.valueOf(osd[2]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return response;
	}

	
	@Override
	@Transactional
	public String addCar(VehicleInfoRequest vehicleInfo, User user) throws ManagerException {
		vehicleInfo.validateVehicleInfo();//车辆信息校验
		CustomerInfo info = customerInfoRepo.findByCustomerId(vehicleInfo.getCustomerId());
		if(info == null) throw new ManagerException("客户信息不存在！");//校验客户信息是否存在
		VehicleInfo findByVehicleId = vehicleInfoRepo.findByVehicleId(vehicleInfo.getVehiclePlate()+"_"+vehicleInfo.getVehiclePlateColor());
		if(findByVehicleId != null) throw new MyViolationException("vehiclePlate", "该颜色的车牌已存在！");
		try {
			savePic(vehicleInfo);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ManagerException("保存图片失败！请联系管理员");
		}
		VehicleInfoSubmitRequest req = new VehicleInfoSubmitRequest();
		super.commSet(req,user);
		req.setOperation(1);
		req.setVehiclePlate(vehicleInfo.getVehiclePlate());
		req.setVehiclePlateColor(vehicleInfo.getVehiclePlateColor());
		req.setUserId(vehicleInfo.getCustomerId());
		req.setType(vehicleInfo.getType());
		req.setOwnerName(vehicleInfo.getOwnerName());
		req.setOwnerIdType(vehicleInfo.getOwnerIdType());
		req.setOwnerIdNum(vehicleInfo.getOwnerIdNum());
		req.setOwnerTel(vehicleInfo.getOwnerTel());
		req.setAddress(vehicleInfo.getOwnerAddress());
		req.setContact(vehicleInfo.getContacts());
		req.setVehicleType(vehicleInfo.getVehicleType());
		req.setVehicleModel(vehicleInfo.getVehicleModel());
		req.setUseCharacter(vehicleInfo.getUseCharacter());
		req.setVin(vehicleInfo.getVIN());
		req.setEngineNum(vehicleInfo.getEngineNum());
		req.setRegisterDate(vehicleInfo.getRegisterDate());
		req.setIssueDate(vehicleInfo.getIssueDate());
		req.setFileNum(vehicleInfo.getFileNum());
		req.setApprovedCount(vehicleInfo.getApprovedCount());
		req.setTotalMass(vehicleInfo.getTotalMass());
		req.setMaintenanceMass(vehicleInfo.getMaintenanceMass());
		req.setPermittedWeight(vehicleInfo.getPermittedWeight());
		req.setOutsideDimensions(outsideDimensionsTransformation(vehicleInfo));
		req.setPermittedTowWeight(vehicleInfo.getPermittedTowWeight());
		req.setTestRecord(vehicleInfo.getTestRecord());
		req.setWheelCount(vehicleInfo.getWheelCount());
		req.setAxleCount(vehicleInfo.getAxleCount());
		req.setAxleDistance(vehicleInfo.getAxleDistance());
		req.setAxisType(vehicleInfo.getAxisType());
		req.setRefrigeratedTrucks(vehicleInfo.getRefrigeratedTrucks());
		req.setEmergencyFlag(vehicleInfo.getEmergencyFlag());
		VehicleInfoSubmitResponse vsr;
		try {
			vsr = releaseBinService.vehicleInfoSubmitV2(req);
			if(vsr.getVehicleId() == null) {
				throw new ManagerException(vsr.getMessage());
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("车辆信息保存失败",e);
		}
		return vsr.getVehicleId();
	}
	
	@Override
	@Transactional
	public String update(@Valid VehicleInfoRequest vehicleInfo, User user) throws ManagerException {
		vehicleInfo.validateVehicleInfo();
		CustomerInfo info = customerInfoRepo.findByCustomerId(vehicleInfo.getCustomerId());
		if(info == null) throw new ManagerException("未查到对应的客户信息！,请联系管理员");
		List<CardInfo> list = cardInfoRepo.findByVehicleIdS(vehicleInfo.getVehiclePlate() + "_" + vehicleInfo.getVehiclePlateColor());
		if(list != null && list.size() > 0 && list.get(0).getAgencyId() != null) {
			if(!user.getStaff().getServiceHall().getAgencyId().equals(list.get(0).getAgencyId())) {
				if(!agencyVarifyManager.varifyAgency(user, list.get(0))) {
					throw new ManagerException("该车辆在其他渠道已发行有卡，只能在发卡渠道修改车辆信息！");
				}
			}
		}
		/*List<OBUInfo> obuList = obuInfoRepo.listByVehicleIdS(vehicleInfo.getVehiclePlate() + "_" + vehicleInfo.getVehiclePlateColor());
		if(obuList != null && obuList.size() > 0  && obuList.get(0).getRegisteredChannelId() != null) {
			if(!AgencyIdSkipPairModel.skipAgencyId(user.getStaff().getServiceHall().getAgencyId(), obuList.get(0).getRegisteredChannelId().substring(0, 11)))
				throw new ManagerException("该车辆在其他渠道已发行有OBU，只能在发OBU渠道修改车辆信息！");
		}*/
		try {
			savePic(vehicleInfo);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ManagerException("保存图片失败！请联系管理员");
		}
		VehicleInfoSubmitRequest req = new VehicleInfoSubmitRequest();
		super.commSet(req,user);
		req.setOperation(2);
		req.setVehiclePlate(vehicleInfo.getVehiclePlate());
		req.setVehiclePlateColor(vehicleInfo.getVehiclePlateColor());
		req.setUserId(vehicleInfo.getCustomerId());
		req.setType(vehicleInfo.getType());
		req.setOwnerName(vehicleInfo.getOwnerName());
		req.setOwnerIdType(vehicleInfo.getOwnerIdType());
		req.setOwnerIdNum(vehicleInfo.getOwnerIdNum());
		req.setOwnerTel(vehicleInfo.getOwnerTel());
		req.setAddress(vehicleInfo.getOwnerAddress());
		req.setContact(vehicleInfo.getContacts());
		req.setVehicleType(vehicleInfo.getVehicleType());
		req.setVehicleModel(vehicleInfo.getVehicleModel());
		req.setUseCharacter(vehicleInfo.getUseCharacter());
		req.setVin(vehicleInfo.getVIN());
		req.setEngineNum(vehicleInfo.getEngineNum());
		req.setRegisterDate(vehicleInfo.getRegisterDate());
		req.setIssueDate(vehicleInfo.getIssueDate());
		req.setFileNum(vehicleInfo.getFileNum());
		req.setApprovedCount(vehicleInfo.getApprovedCount());
		req.setTotalMass(vehicleInfo.getTotalMass());
		req.setMaintenanceMass(vehicleInfo.getMaintenanceMass());
		req.setPermittedWeight(vehicleInfo.getPermittedWeight());
		req.setOutsideDimensions(outsideDimensionsTransformation(vehicleInfo));
		req.setPermittedTowWeight(vehicleInfo.getPermittedTowWeight());
		req.setTestRecord(vehicleInfo.getTestRecord());
		req.setWheelCount(vehicleInfo.getWheelCount());
		req.setAxleCount(vehicleInfo.getAxleCount());
		req.setAxleDistance(vehicleInfo.getAxleDistance());
		req.setAxisType(vehicleInfo.getAxisType());
		req.setRefrigeratedTrucks(vehicleInfo.getRefrigeratedTrucks());
		req.setEmergencyFlag(vehicleInfo.getEmergencyFlag());
		VehicleInfoSubmitResponse vsr;
		try {
			vsr = releaseBinService.vehicleInfoSubmitV2(req);
			if(vsr.getVehicleId() == null) {
				throw new ManagerException(vsr.getMessage());
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("车辆信息修改失败",e);
		}
		return vsr.getVehicleId();
	}
	
	@Override
	public String delete(@Valid VehicleInfoRequest vehicleInfo, User user) throws ManagerException {
		vehicleInfo.validateVehicleInfo();
		VehicleInfoSubmitRequest req = new VehicleInfoSubmitRequest();
		super.commSet(req,user);
		req.setOperation(3);
		req.setVehiclePlate(vehicleInfo.getVehiclePlate());
		req.setVehiclePlateColor(vehicleInfo.getVehiclePlateColor());
		req.setUserId(vehicleInfo.getCustomerId());
		req.setType(vehicleInfo.getType());
		req.setOwnerName(vehicleInfo.getOwnerName());
		req.setOwnerIdType(vehicleInfo.getOwnerIdType());
		req.setOwnerIdNum(vehicleInfo.getOwnerIdNum());
		req.setOwnerTel(vehicleInfo.getOwnerTel());
		req.setAddress(vehicleInfo.getOwnerAddress());
		req.setContact(vehicleInfo.getContacts());
		req.setVehicleType(vehicleInfo.getVehicleType());
		req.setVehicleModel(vehicleInfo.getVehicleModel());
		req.setUseCharacter(vehicleInfo.getUseCharacter());
		req.setVin(vehicleInfo.getVIN());
		req.setEngineNum(vehicleInfo.getEngineNum());
		req.setRegisterDate(vehicleInfo.getRegisterDate());
		req.setIssueDate(vehicleInfo.getIssueDate());
		req.setFileNum(vehicleInfo.getFileNum());
		req.setApprovedCount(vehicleInfo.getApprovedCount());
		req.setTotalMass(vehicleInfo.getTotalMass());
		req.setMaintenanceMass(vehicleInfo.getMaintenanceMass());
		req.setPermittedWeight(vehicleInfo.getPermittedWeight());
		req.setOutsideDimensions(vehicleInfo.getOutsideDimensions());
		req.setPermittedTowWeight(vehicleInfo.getPermittedTowWeight());
		req.setTestRecord(vehicleInfo.getTestRecord());
		req.setWheelCount(vehicleInfo.getWheelCount());
		req.setAxleCount(vehicleInfo.getAxleCount());
		req.setAxleDistance(vehicleInfo.getAxleDistance());
		req.setAxisType(vehicleInfo.getAxisType());
		req.setRefrigeratedTrucks(vehicleInfo.getRefrigeratedTrucks());
		req.setEmergencyFlag(vehicleInfo.getEmergencyFlag());
		VehicleInfoSubmitResponse vsr;
		try {
			vsr = releaseBinService.vehicleInfoSubmitV2(req);
			if(vsr.getVehicleId() == null) {
				throw new ManagerException(vsr.getMessage());
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException(e.getMessage()+"--删除车辆失败!",e);
		}
		return vsr.getVehicleId();
	}
	
	@Override
	public AppAjaxResponse vehicleCheck(VehicleCheckRequset vcr) {
		AppAjaxResponse appAjaxResponse = new VehicleCheckResponse();
		if (vcr.getVehiclePlate() == null || vcr.getVehiclePlateColor() == null) {
			appAjaxResponse.setManagerException(new ManagerException("车牌、车牌颜色必填！"));
		} else if (MyPatterns.checkPlateNumFormat(vcr.getVehiclePlate())) {
			String vehicleId = vcr.getVehiclePlate() + "_" + vcr.getVehiclePlateColor();
			VehicleInfo vehicleInfo = vehicleInfoRepo.findByVehicleId(vehicleId);
			if (vehicleInfo != null) {
				appAjaxResponse.setManagerException(new ManagerException("该颜色的车牌已存在！"));
			} else {
				appAjaxResponse.setSuccess(true);
			}
		} else {
			appAjaxResponse.setManagerException(new ManagerException("车牌格式不正确！"));
		}

		return appAjaxResponse;
	}
	
	/**
	 * 将车辆外廓长、宽、高转换成  长X宽X高
	 * @param vehicleInfo
	 * @return
	 */
	private String outsideDimensionsTransformation(VehicleInfoRequest vehicleInfo) {
		String vehicleLength = "0";
		String vehicleWidth = "0";
		String vehicleHeight = "0";
		if(vehicleInfo.getVehicleLength() != null) vehicleLength = vehicleInfo.getVehicleLength().toString();
		if(vehicleInfo.getVehicleWidth() != null) vehicleWidth = vehicleInfo.getVehicleWidth().toString();
		if(vehicleInfo.getVehicleHeight() != null) vehicleHeight = vehicleInfo.getVehicleHeight().toString();
		String outsideDimensions = vehicleLength + CssConstant.OUTSIDEDIMENSIONS_SAMBOL + vehicleWidth + CssConstant.OUTSIDEDIMENSIONS_SAMBOL + vehicleHeight;
		return outsideDimensions;
	}
	
	/**
	 * 保存车辆相关照片
	 * @param model
	 * @throws ManagerException
	 */
	@Transactional
	private void savePic(VehicleInfoRequest model) throws ManagerException {
		if(model.getDrivingLicensePic()==null && model.getVehiclePic() == null){
			return;
//			throw new ManagerException("未获取到上传的文件");
		}
		if (model.getDrivingLicensePic() != null && model.getDrivingLicensePic().length > 0)
			for (int i = 0; i < model.getDrivingLicensePic().length; i++) {
				MultipartFile file = model.getDrivingLicensePic()[i];
				VehicleInterrelatePng vehicleInterrelatePng = new VehicleInterrelatePng();
				String parentDirRelativePath = MyFinals.QUICK_APPLY_VEHICLE_IMG + File.separator
						+ model.getVehiclePlateColor() + File.separator + model.getVehiclePlate();
				String fileAbsolutePath = FileWriter.savePic(file, vehicleInterrelatePng, parentDirRelativePath);
				vehicleInterrelatePng.setType(1);
				vehicleInterrelatePng.setVehiclePlate(model.getVehiclePlate());
				vehicleInterrelatePng.setVehiclePlateColor(model.getVehiclePlateColor());
				vehicleInterrelatePng.setFilePath(fileAbsolutePath);
				vehicleInterrelatePng.setFileName(FileWriter.generateFileName(vehicleInterrelatePng, file));
				vehicleInterrelatePng.setInsertTime(QTKUtils.getDateString());
				vehicleInterrelatePngRepo.save(vehicleInterrelatePng);
			}
		if (model.getVehiclePic()!=null && model.getVehiclePic().length > 0)
			for (int i = 0; i < model.getVehiclePic().length; i++) {
				MultipartFile file = model.getVehiclePic()[i];
				VehicleInterrelatePng vehicleInterrelatePng = new VehicleInterrelatePng();
				String parentDirRelativePath = MyFinals.QUICK_APPLY_VEHICLE_IMG+File.separator+model.getVehiclePlateColor()+File.separator+model.getVehiclePlate();
				String fileAbsolutePath = FileWriter.savePic(file,vehicleInterrelatePng, parentDirRelativePath);
				vehicleInterrelatePng.setType(2);
				vehicleInterrelatePng.setVehiclePlate(model.getVehiclePlate());
				vehicleInterrelatePng.setVehiclePlateColor(model.getVehiclePlateColor());
				vehicleInterrelatePng.setFilePath(fileAbsolutePath);
				vehicleInterrelatePng.setFileName(FileWriter.generateFileName(vehicleInterrelatePng,file));
				vehicleInterrelatePng.setInsertTime(QTKUtils.getDateString());
				vehicleInterrelatePngRepo.save(vehicleInterrelatePng);
			}
	}
	@Override
	public List<String> getVehicleInfoPicBase64(String vehicleId, Integer type) throws ManagerException {
		if(vehicleId == null) throw new ManagerException("车辆编号错误！");
		String[] arr = vehicleId.split("_");
		if(arr.length != 2) throw new ManagerException("车辆编号错误！");
		String vehiclePlate = arr[0];
		Integer vehiclePlateColor;
		try {
			vehiclePlateColor = Integer.valueOf(arr[1]);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			throw new ManagerException("车辆编号错误！");
		}
		List<VehicleInterrelatePng> list = vehicleInterrelatePngRepo.listByVehicleInterrelatePngOfType(vehiclePlate, vehiclePlateColor, type);
		List<String> listPic = Lists.newArrayList();
		if(list.size()==0){return listPic;}
		for(int i = 0;i<list.size();i++) {
			VehicleInterrelatePng vehicleInterrelatePng = list.get(i);
			String fileName = vehicleInterrelatePng.getFileName();
			String filePath = vehicleInterrelatePng.getFilePath();
			if(fileName == null || filePath == null){continue;}
			File file = new File(filePath + fileName);
			
			if(!file.exists()){continue;}
			String encodeBase64="";
			try {
				encodeBase64 = EncodeTool.encodeBase64(file);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("图片转码错误！");
			}
			String suffix = fileName.substring(fileName.lastIndexOf('.'));
			if(".png".equalsIgnoreCase(suffix)){encodeBase64 = "data:image/png;base64,"+encodeBase64;}
			else if(".jpg".equalsIgnoreCase(suffix)){encodeBase64 ="data:image/jpg;base64,"+encodeBase64;}
			else if(".jpeg".equalsIgnoreCase(suffix)){encodeBase64 ="data:image/jpeg;base64,"+encodeBase64;}
			listPic.add(encodeBase64);
		}
		return listPic;
	}
	@Override
	public VehicleInfo findById(String id) throws ManagerException {
		Optional<VehicleInfo> findById = vehicleInfoRepo.findById(id);
		VehicleInfo vehicleInfo = findById.orElse(null);
		if(vehicleInfo != null) {
			if(vehicleInfo.getVehicleType() != null) {
				if(!(vehicleInfo.getVehicleType().equals("0") || vehicleInfo.getVehicleType().equals("1") || vehicleInfo.getVehicleType().equals("2"))) {
					throw new ManagerException("车辆信息有误，请先到基础信息->车辆信息，修改车辆信息！");
				}
			}else {
				throw new ManagerException("车辆信息有误，请先到基础信息->车辆信息，修改车辆信息！");
			}
		}else {
			throw new ManagerException("通过id未查询到车辆信息！");
		}
		return vehicleInfo;
	}
	@Override
	@Transactional
	public VehicleInfo emergencyAlter(String uuId, Integer type) throws ManagerException {
		VehicleInfo vehicleInfo = findById(uuId);
		if(type.intValue() == 1) {
			vehicleInfo.setEmergencyFlag(1);//1-应急车辆
		}else if (type.intValue() == 0) {
			vehicleInfo.setEmergencyFlag(0);//0-非应急车辆
		}else {
			throw new ManagerException("错误的应急车辆类型！");
		}
		vehicleInfo.setUpdateTime(Calendar.getInstance());
		VehicleInfo info;
		try {
			info = vehicleInfoRepo.save(vehicleInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("数据入库失败！");
		}
		return info;
	}

}
