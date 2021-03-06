
package cn.com.taiji.css.manager.customerservice.obu;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.obu.OBUCancelRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;


@Service
public class OBUCancelManagerImpl extends AbstractDsiCommManager implements OBUCancelManager{

	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private InqueryBinService inqueryBinService;
	
	@Autowired
	private ExchangeManager exchangeManager;
//	@Autowired
//	private OfflineinstallManager offlineinstallManager;
	@Override
	public LargePagination<OBUInfo> queryPage(OBUCancelRequest queryModel,User user) throws ManagerException {
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
		return oBUInfoRepo.largePage(queryModel);
	}
	/*public boolean queryCheck(OBUCancelRequest queryModel,User user) throws ManagerException {
		String agencyId=  user.getStaff().getServiceHall().getAgencyId();
		OBUInfo  obuInfo = agencyCheck(queryModel);
		if(null!=obuInfo) {
			String channleId = obuInfo.getRegisteredChannelId().substring(0, 11);
			if(!agencyId.equals(channleId)){
				if("52010102018".equals(agencyId)&&"52010102002".equals(channleId)) {
					return true;
				}else if("52010102002".equals(agencyId)&&"52010102018".equals(channleId)) {
					return true;
				}else {
					throw new ManagerException("该OBU不能在该渠道进行办理");
				}
			}else {
				return true;
			}
		}
		return false;
		
		
	}
	private OBUInfo agencyCheck(OBUCancelRequest queryModel) throws ManagerException
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
		
	}*/
	@Override
	public OBUStatusChangeResponse doOBUCancel(OBUCancelRequest queryModel, User loginUser) throws ManagerException {
		//数据校验
		OBUInfo oBUInfo = findById(queryModel.getObuId());
		OBUStatusChangeResponse obuSRes = new OBUStatusChangeResponse();
		
//		try {
//			if(oBUInfo.getRegisteredChannelId().substring(0, 11).equals("52010188037")) {
//				obuSRes.setMessage("暂无权限注销部认证平台的OBU！");
//				return obuSRes;
//			}
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			obuSRes.setMessage("此OBU的申请网点编号格式有误！");
//			return obuSRes;
//		}
		if(null==oBUInfo.getRegisteredTime()||oBUInfo.getRegisteredTime().equals("")) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			oBUInfo.setRegisteredTime(format.format(now));
			oBUInfoRepo.save(oBUInfo);
		}
		
		OBUInfoQueryResponse oBUInfoQueryRes = OBUInfoQuery(queryModel.getObuId(),loginUser);
		Integer obuStatus = oBUInfoQueryRes.getObuStatus();
		if(obuStatus==1||obuStatus==2||obuStatus==3||obuStatus==6) {
			try {
				 obuSRes =  oBUStatusChange(queryModel,loginUser);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("OBU注销失败。请联系管理员");
			}
		}
		return obuSRes;
		
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
	private OBUStatusChangeResponse oBUStatusChange(OBUCancelRequest queryModel, User loginUser) throws IOException {
		OBUStatusChangeRequest oBUStatusChangeReq= new OBUStatusChangeRequest();
		oBUStatusChangeReq.setStatus(queryModel.getObuStatus());
		oBUStatusChangeReq.setObuId(queryModel.getObuId());
		super.commSet(oBUStatusChangeReq,loginUser);
		OBUStatusChangeResponse obuSRes = maintenanceBinService.obuStatusChange(oBUStatusChangeReq);
		return obuSRes;
	}

	@Override
	public OBUInfo findById(String obuId) {
		return oBUInfoRepo.findByObuId(obuId);
	}

}

