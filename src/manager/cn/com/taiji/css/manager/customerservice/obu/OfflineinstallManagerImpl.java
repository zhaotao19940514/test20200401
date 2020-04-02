/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.obu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.finance.AgencyIdSkipPairModel;
import cn.com.taiji.css.model.customerservice.obu.OfflineinstallRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallConfirmResponse;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;

/**
 * @ClassName RechargeManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:38
 * @E_mail yaonanlin@163.com
 */
@Service
public class OfflineinstallManagerImpl extends AbstractDsiCommManager implements OfflineinstallManager{

	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private ExchangeManager exchangeManager;
	
	@Override
	public LargePagination<OBUInfo> queryPage(OfflineinstallRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		if(null!=queryModel.getVehicleId()) {
			List<OBUInfo> obuinfoList = oBUInfoRepo.listByVehicleId(queryModel.getVehicleId());
			if(null!=obuinfoList&&obuinfoList.size()!=0) {
				for(OBUInfo info:obuinfoList) {
					exchangeManager.doFullObuInfo(info.getObuId());
				}
			}
		}else {
			exchangeManager.doFullObuInfo(queryModel.getObuId());
		}
		/*if(!user.getRole().isSystem()) {
			boolean queryFlag = queryCheck(queryModel,user);
			if(!queryFlag) {
				return null;
			}
		}*/
		return oBUInfoRepo.largePage(queryModel);
	}

	@Override
	public InstallApplyResponse offlineInstall(OfflineinstallRequest queryModel,User user) {
		OBUInfo oBUInfo = queryObuInfo(queryModel.getObuId());
		InstallApplyRequest installApplyReq = new InstallApplyRequest();
		InstallApplyResponse installApplyRes = new InstallApplyResponse();
		installApplyReq.setObuId(queryModel.getObuId());
		installApplyReq.setVehicleId(oBUInfo.getVehicleId());
		super.commSet(installApplyReq,user);
		try {
			installApplyRes = releaseBinService.installApply(installApplyReq);
			InstallConfirmResponse res = OBUConfirmInstall(queryModel.getObuId(),user);
			installApplyRes.setMessage(res.getMessage());
			installApplyRes.setStatus(res.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return installApplyRes;
		
	}
	public  InstallConfirmResponse OBUConfirmInstall(String obuId,User user) throws ManagerException {
		
		InstallConfirmRequest installConfirmReq = new InstallConfirmRequest();
		InstallConfirmResponse installConfirmRes= new InstallConfirmResponse();
		super.commSet(installConfirmReq,user);
		installConfirmReq.setObuId(obuId);
		installConfirmReq.setInstallStatus(0);
		installConfirmReq.setFailReason("");
		//installConfirmReq.setInstallType(1);
		
		OBUInfoQueryResponse oBUInfoQueryRes = OBUInfoQuery(obuId,user);
			installConfirmReq.setInstallType(2);
		installConfirmReq.setInstallChannelId(oBUInfoQueryRes.getChannelId());
		try {
			installConfirmRes = releaseBinService.installConfirm(installConfirmReq);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("OBU确认安装失败.");
		} 
		return installConfirmRes;
	}
	public OBUInfoQueryResponse OBUInfoQuery(String obuId,User user) throws ManagerException {
		
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
	public OBUInfo queryObuInfo(String obuId) {
		return oBUInfoRepo.findByObuId(obuId);
	}
	
	private OBUInfo agencyCheck(OfflineinstallRequest queryModel) throws ManagerException
	{
		OBUInfo obuInfo = null;
		if(StringTools.hasText(queryModel.getObuId())&&!StringTools.hasText(queryModel.getVehicleId())) {
			obuInfo = oBUInfoRepo.findByObuId(queryModel.getObuId());
		}else if(!StringTools.hasText(queryModel.getObuId())&&StringTools.hasText(queryModel.getVehicleId())) {
			 List<OBUInfo> list = oBUInfoRepo.listByVehicleId(queryModel.getVehicleId());
			 if(list.size()!=0) {
				 obuInfo = list.get(0);
			 }
		}else {
			obuInfo = oBUInfoRepo.findByObuIdAndVehicleId(queryModel.getObuId(), queryModel.getVehicleId());
		}
		return obuInfo;
		
	}
	public boolean queryCheck(OfflineinstallRequest queryModel,User user) throws ManagerException {
		String agencyId=  user.getStaff().getServiceHall().getAgencyId();
		OBUInfo  obuInfo = agencyCheck(queryModel);
		if(null!=obuInfo) {
			if(!agencyId.equals(obuInfo.getRegisteredChannelId().substring(0, 11))){
				if(AgencyIdSkipPairModel.skipAgencyId(agencyId, obuInfo.getRegisteredChannelId().substring(0, 11))) {
					return true;
				}
				throw new ManagerException("该OBU不能在该渠道进行办理");
			}else {
				return true;
			}
		}
		return false;
		
		
	}
	@Override
	public boolean queryObuInfo(String obuId,String vehicleId,User user) throws ManagerException {
			String agencyId=  user.getStaff().getServiceHall().getAgencyId();
			OfflineinstallRequest offReq  = new OfflineinstallRequest();
			offReq.setObuId(obuId);
			offReq.setVehicleId(vehicleId);
			OBUInfo  obuInfo = agencyCheck(offReq);
			if(null!=obuInfo) {
				String oAgencyId = obuInfo.getRegisteredChannelId().substring(0, 11);
				if(agencyId.equals(oAgencyId)) {
					return true;
				}else if(!agencyId.equals(oAgencyId)){
					throw new ManagerException("该卡不能在该网点进行办理,请到注册网点进行该业务办理");
				}
			}
			return false;
	}
	
}

