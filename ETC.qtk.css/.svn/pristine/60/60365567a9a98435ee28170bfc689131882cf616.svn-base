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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.model.customerservice.obu.RewriteRequest;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.BalckObu;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.repo.jpa.BalckObuRepo;
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
public class RewriteManagerImpl extends AbstractDsiCommManager implements RewriteManager{

	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private VehicleInfoRepo veInfoRepo;
	@Autowired
	private ExchangeManager exchangeManager;
	@Autowired
	private BalckObuRepo balckObuRepo;
	
	@Override
	public LargePagination<OBUInfo> queryPage(RewriteRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		exchangeManager.doFullObuInfo(queryModel.getObuId());
		/*if(!user.getRole().isSystem()) {
			OfflineinstallRequest offReq = new OfflineinstallRequest();
			offReq.setObuId(queryModel.getObuId());
			offReq.setVehicleId(queryModel.getVehicleId());
			boolean queryFlag = offlineinstallManager.queryCheck(offReq,user);
			if(!queryFlag) {
				return null;
			}
		}*/
		return oBUInfoRepo.largePage(queryModel);
	}

	@Override
	public void doOBURewrite(RewriteRequest offReq, User loginUser) throws ManagerException {
		/*ObuApplyAndConfirmRequest appRequest= new ObuApplyAndConfirmRequest();
		
			Integer obuStatus = 2;
			OBUStatusChangeResponse OBUStatusChangeRes = oBUStatusChange(appRequest.getObuId(),loginUser,obuStatus);
			if(OBUStatusChangeRes.getStatus()==1) {
				appRequest.setOldObuId(offReq.getOldObuId());
				appRequest.setApplyOrChange(offReq.getApplyOrChange());
				appRequest.setType(offReq.getType());
				equipmentIssueManager.obuApplyAndConfirm(appRequest, loginUser);
			}*/
			//oBUStatusChange(appRequest.getOldObuId(),loginUser,1);
		
	}
	
	private OBUStatusChangeResponse oBUStatusChange(String obuId, User loginUser,Integer obustatus) throws IOException {
		OBUStatusChangeRequest oBUStatusChangeReq= new OBUStatusChangeRequest();
		oBUStatusChangeReq.setStatus(obustatus);
		super.commSet(oBUStatusChangeReq,loginUser);
		return maintenanceBinService.obuStatusChange(oBUStatusChangeReq);
	}

	@Override
	public VehicleInfo queryVeInfo(RewriteRequest queryModel) {
		VehicleInfo info= null;
		if(StringTools.hasText(queryModel.getVehPlate())&&null!=queryModel.getVehColor()) {
			String vehicleId =  queryModel.getVehPlate()+"_"+queryModel.getVehColor();
			 info= veInfoRepo.findByVehicleId(vehicleId);
		}
		return info;
	}

	@Override
	public VehicleInfo vehInfoByObuId(RewriteRequest queryModel, User loginUser) {
		
		VehicleInfo vehicleInfo = veInfoRepo.findByVehicleId(queryModel.getVehicleId());
		//obu写入特征字段长度不能大于16位，因为汉字占2位，如果vehicleModel长度大于8，将其截取前8位
		if(vehicleInfo != null && vehicleInfo.getVehicleModel() != null && vehicleInfo.getVehicleModel().length() > 8) {
			vehicleInfo.setVehicleModel(vehicleInfo.getVehicleModel().substring(0, 8));
		}
		//obu写入发动机编号长度不能大于16位，如果车辆信息大于，截取前16位
		if(vehicleInfo != null && vehicleInfo.getEngineNum() != null && vehicleInfo.getEngineNum().length() > 16) {
			vehicleInfo.setEngineNum(vehicleInfo.getEngineNum().substring(0, 16));
		}
		if(vehicleInfo != null && vehicleInfo.getOutsideDimensions() == null) {
			vehicleInfo.setOutsideDimensions("0"+CssConstant.OUTSIDEDIMENSIONS_SAMBOL+"0"+CssConstant.OUTSIDEDIMENSIONS_SAMBOL+"0");
		}else if (vehicleInfo != null && vehicleInfo.getOutsideDimensions() != null) {
			String[] str1 = vehicleInfo.getOutsideDimensions().split(CssConstant.OUTSIDEDIMENSIONS_SAMBOL);
			if(str1.length != 3) {
				vehicleInfo.setOutsideDimensions("0"+CssConstant.OUTSIDEDIMENSIONS_SAMBOL+"0"+CssConstant.OUTSIDEDIMENSIONS_SAMBOL+"0");
			}
		}
		return vehicleInfo;
	
	}

	@Override
	public OBUInfo findById(String obuId) {
		
		return oBUInfoRepo.findByObuId(obuId);
	}
	
	@Override
	public OBUInfoChangeResponse reWriteVehicleInfo(RewriteRequest queryModel, User loginUser) throws ManagerException {
		OBUInfoChangeResponse obuInfoRes = new OBUInfoChangeResponse();
		VehicleInfo vehInfo = queryVeInfo(queryModel);
		
		VehicleInfo oldVehInfo = veInfoRepo.findByVehicleId(queryModel.getVehicleId());
		if(!vehInfo.getCustomerId().equals(oldVehInfo.getCustomerId())) {
			obuInfoRes.setMessage("该车牌不属于该用户");
			obuInfoRes.setStatus(0);
			return obuInfoRes;
		}
		obuInfoRes = obuInfoChange(queryModel,loginUser,vehInfo.getCustomerId());
		//解除挂起黑名单
		releaseBlack(queryModel.getObuId());
		return obuInfoRes;
	}

	public OBUInfoChangeResponse obuInfoChange(RewriteRequest queryModel,User user,String userId) throws ManagerException {
		OBUInfoChangeResponse obuInfoRes=new OBUInfoChangeResponse();
		OBUInfoChangeRequest  obuInfoReq= new OBUInfoChangeRequest();
		String vehicleId = queryModel.getVehPlate()+"_"+queryModel.getVehColor();
		super.commSet(obuInfoReq,user);
		obuInfoReq.setObuId(queryModel.getObuId());
		obuInfoReq.setUserId(userId);
		obuInfoReq.setVehicleId(vehicleId);
		try {
			obuInfoRes = maintenanceBinService.obuInfoChange(obuInfoReq);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("调用接口错误，请联系管理员");
		}
		
		return obuInfoRes;
	}
	
	private OBUInfo findbyVehicleId(String vehicleId) {
		return oBUInfoRepo.findByVehicleId(vehicleId);
	}
	
	@Override
	public OBUStatusChangeResponse vehicleInfoCheck(RewriteRequest queryModel) throws ManagerException {
		OBUStatusChangeResponse oBUChangeRes = new OBUStatusChangeResponse();
		OBUInfo obuInfo = oBUInfoRepo.findByObuId(queryModel.getOldObuId());
		VehicleInfo vehiInfo = veInfoRepo.findByVehicleId(obuInfo.getVehicleId());
		exchangeManager.VehicleInfoCheck(vehiInfo, oBUChangeRes);
		return oBUChangeRes;
	}
	
	private void releaseBlack(String obuId) {
		
		BalckObu bo = balckObuRepo.findByObuIdWithTypeInBlack(obuId,2);
		if(null!=bo) {
			bo = balckObuRepo.findByObuIdInBlack(obuId);
//			if (bo == null)
//				throw QktServiceError.OBJECT_NULL.toHandleException("OBU黑名单不存在.");
			bo.setCreationTime(QTKUtils.getDateString());
			bo.setStatus(1);
			balckObuRepo.save(bo);
		}
		
	}
}

