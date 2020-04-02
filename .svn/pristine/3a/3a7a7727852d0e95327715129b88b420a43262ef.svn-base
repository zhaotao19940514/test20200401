/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.obu;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.model.customerservice.obu.OBUTransferRequest;
import cn.com.taiji.css.model.customerservice.obu.OBUTransferResponse;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.Black15MinusLimit;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.repo.jpa.Black15MinusLimitRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;

/**
 * @ClassName RechargeManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:38
 * @E_mail yaonanlin@163.com
 */
@Service
public class OBUTransferManagerImpl extends AbstractDsiCommManager implements OBUTransferManager{
	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private VehicleInfoRepo vehicleInfoRepo;
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private ExchangeManager exchangeManager;
	@Autowired
	private Black15MinusLimitRepo black15MinusLimitRepo;
	
	
	@Override
	public OBUInfo findById(String obuId) {
		
		return oBUInfoRepo.findByObuId(obuId);
	}
	@Override
	public LargePagination<VehicleInfo> queryPage(OBUTransferRequest queryModel, User user) throws ManagerException {
		
		queryModel.validate();
		exchangeManager.doFullObuInfo(queryModel.getObuId());
		LargePagination<VehicleInfo> page = vehicleInfoRepo.largePage(queryModel);
		List<VehicleInfo> vehicleInfos = page.getResult();
		if(vehicleInfos.size() == 0) return page;
		Map<String,List<VehicleInfo>> vehMap = vehicleInfos.parallelStream().collect(Collectors.groupingBy(VehicleInfo::getVehicleId));
		List<String> vehicleIds = vehicleInfos.parallelStream().map(VehicleInfo::getVehicleId).collect(Collectors.toList());
		List<OBUInfo> obuInfos = oBUInfoRepo.listByVehicleIdsCheck(vehicleIds);
		obuInfos.stream().filter((p)->(p.getVehicleId()!=null));
		Map<String,List<OBUInfo>> obuMap = obuInfos.parallelStream().collect(Collectors.groupingBy(OBUInfo::getVehicleId));
		vehicleIds.forEach(id->{
			List<OBUInfo> obuList = obuMap.get(id);
			vehMap.get(id).get(0).setHasObu(obuList!=null && obuList.size()>0);
		});
		Collection<List<VehicleInfo>> values = vehMap.values();
		List<VehicleInfo> pageResult = Lists.newArrayList();
		for (List<VehicleInfo> listVeh : values) {
			pageResult.addAll(listVeh);
		}
		page.setResult(pageResult);
		
		return page;
	}
	
	@Override
	public OBUTransferResponse infoChange(@Valid OBUTransferRequest queryModel, User user) {
		
		
		
		OBUInfoChangeRequest obuInfoCReq = new OBUInfoChangeRequest();
		OBUInfoChangeResponse obuInfoRes = new OBUInfoChangeResponse();
		OBUTransferResponse response = new OBUTransferResponse();
		super.commSet(obuInfoCReq,user);
		
		//obu是否已被注销
		OBUInfo obuInfo = findById(queryModel.getObuId());
		if(obuInfo.getStatus()==4||obuInfo.getStatus()==5) {
			response.setMessage("过户失败:OBU已注销，不能过户");
			response.setStatus(0);
			return response;
		}
		List<Black15MinusLimit>  listLit= black15MinusLimitRepo.listByObuId(queryModel.getObuId());
		if(null!=listLit&&listLit.size()!=0) {
			for(Black15MinusLimit bl:listLit) {
				if(!cancel30ArgueTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bl.getCreateTime().getTime()))) {
					response.setMessage("过户失败:OBU无签挂起后，15分钟内不能过户");
					response.setStatus(0);
					return response;
				}
			}
		}
		
		VehicleInfo vehicleInfo =  vehicleInfoRepo.findByVehicleId(queryModel.getVehicleId());
		obuInfoCReq.setObuId(queryModel.getObuId());
		obuInfoCReq.setVehicleId(queryModel.getVehicleId());
		obuInfoCReq.setUserId(vehicleInfo.getCustomerId());
		try {
			obuInfoRes=maintenanceBinService.obuInfoChange(obuInfoCReq);
			if(obuInfoRes.getStatus()==1) {
				OBUStatusChangeResponse obuSRes = null;
				obuSRes =  oBUStatusChange(queryModel,user);
				
				OBUInfo oBUInfo=oBUInfoRepo.findByObuIdAndVehicleId(obuInfoCReq.getObuId(),obuInfoCReq.getVehicleId());
				/*
				 * returnWhite(obuInfoCReq.getObuId(),1,user);
				 * returnWhite(obuInfoCReq.getObuId(),2,user);
				 */
				if(oBUInfo==null) {
					response.setMessage("OBU数据更新失败！请联系管理员");;
					response.setStatus(-1);
					return response;
				}
				response.setoBUInfo(oBUInfo);
				response.setPlateNum(vehicleInfo.getVehiclePlate());
				response.setPlateColor(vehicleInfo.getVehiclePlateColor());
				response.setVehicleType(vehicleInfo.getType());
				//用户类型待定
				if(vehicleInfo.getCustomerInfo().getCustomerType() == null) {
					response.setUserType(1);
				}else {
					response.setUserType(vehicleInfo.getCustomerInfo().getCustomerType());
				}
				response.setWheelsCount(vehicleInfo.getWheelCount());
				response.setAxleCount(vehicleInfo.getAxleCount());
				if (vehicleInfo.getAxleDistance() == null) {
					response.setWheelBase(0);
				}else {
					response.setWheelBase(vehicleInfo.getAxleDistance());
				}
				if(vehicleInfo.getVehicleType().equals("1")){
					if (vehicleInfo.getPermittedWeight() == null) {
						response.setLoadOrSeat(0);
					}else {
						response.setLoadOrSeat(vehicleInfo.getPermittedWeight());
					}
				}else{
					if (vehicleInfo.getApprovedCount() == null) {
						response.setLoadOrSeat(0);
					}else {
						response.setLoadOrSeat(vehicleInfo.getApprovedCount());
					}
				}
				//obu写入特征字段长度不能大于16位，因为汉字占2位，如果vehicleModel长度大于8，将其截取前8位
				if(vehicleInfo.getVehicleModel() != null && vehicleInfo.getVehicleModel().length() > 8) {
					vehicleInfo.setVehicleModel(vehicleInfo.getVehicleModel().substring(0, 8));
				}
				//obu写入发动机编号长度不能大于16位，如果车辆信息大于，截取前16位
				if(vehicleInfo.getEngineNum() != null && vehicleInfo.getEngineNum().length() > 16) {
					vehicleInfo.setEngineNum(vehicleInfo.getEngineNum().substring(0, 16));
				}
				response.setVehicleFeature(vehicleInfo.getVehicleModel());
				response.setEngineNum(vehicleInfo.getEngineNum());
				response.setStatus(response.getStatus());
				response.setMessage(response.getMessage());
				String outsideDimensions = vehicleInfo.getOutsideDimensions();
				if(outsideDimensions != null) {
					String[] osd = outsideDimensions.split(CssConstant.OUTSIDEDIMENSIONS_SAMBOL);
					if(osd.length == 3) {
						try {
							response.setVehicleLength(mmToDm(osd[0]));
							response.setVehicleWidth(mmToDm(osd[1]));
							response.setVehicleHeight(mmToDm(osd[2]));
						} catch (Exception e) {
							e.printStackTrace();
							response.setVehicleLength(0);
							response.setVehicleWidth(0);
							response.setVehicleHeight(0);
						}
					}else {
						response.setVehicleLength(0);
						response.setVehicleWidth(0);
						response.setVehicleHeight(0);
					}
				}else {
					response.setVehicleLength(0);
					response.setVehicleWidth(0);
					response.setVehicleHeight(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setMessage(obuInfoRes.getMessage());
		response.setStatus(obuInfoRes.getStatus());
		return response;
	}
	/*
	 * private ObuBlackListUploadResponse returnWhite(String obuId,Integer type,User
	 * user) { ObuBlackListUploadResponse res = new ObuBlackListUploadResponse();
	 * ObuBlackListUploadRequest req = new ObuBlackListUploadRequest();
	 * req.setObuId(obuId); req.setStatus(2); req.setType(type); super.commSet(req,
	 * user);
	 * 
	 * try { res = maintenanceBinService.ObuBlackListUpload(req); } catch (Exception
	 * e) { e.printStackTrace(); } return res; }
	 */
	
	
	//将车辆外廓尺寸String的(mm)转换成int的(dm)
	private static int mmToDm(String osd) {
		return Double.valueOf(Math.ceil(Double.valueOf(osd)/100)).intValue();
	}
	private OBUStatusChangeResponse oBUStatusChange(OBUTransferRequest queryModel, User loginUser) throws IOException {
		OBUStatusChangeRequest oBUStatusChangeReq= new OBUStatusChangeRequest();
		oBUStatusChangeReq.setStatus(1);
		oBUStatusChangeReq.setObuId(queryModel.getObuId());
		super.commSet(oBUStatusChangeReq,loginUser);
		OBUStatusChangeResponse obuSRes = maintenanceBinService.obuStatusChange(oBUStatusChangeReq);
		return obuSRes;
	}
	
	@Override
	public VehicleInfo findByVehicleInfo(String vehicleId) {
		// TODO Auto-generated method stub
		return vehicleInfoRepo.findByVehicleId(vehicleId);
	}
	@Override
	public Object findByCustomerInfo(String customerId) {
		return customerInfoRepo.findByCustomerId(customerId);
	}
	@Override
	public Integer findBrandById(String vehicleId) {
		List<OBUInfo> obuInfo = oBUInfoRepo.listByVehicleId(vehicleId);
		return obuInfo.get(0).getBrand();
	}
	public boolean cancel30ArgueTime(String time) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime nowTime = LocalDateTime.now();
		LocalDateTime cancelDate = LocalDateTime.parse(time, dateTimeFormatter).plusMinutes(15);
		return nowTime.compareTo(cancelDate) > 0;
	}
}

