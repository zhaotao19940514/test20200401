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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.obu.OBUUnLossRequest;
import cn.com.taiji.css.model.customerservice.obu.OfflineinstallRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
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
public class OBUUnLossManagerImpl extends AbstractDsiCommManager implements OBUUnLossManager{
	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private OfflineinstallManager offlineinstallManager;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private ExchangeManager exchangeManager;
	
	@Override
	public LargePagination<OBUInfo> queryPage(OBUUnLossRequest queryModel, User user) throws ManagerException {
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
	public OBUStatusChangeResponse unLossOBU(OBUUnLossRequest queryModel, User user) throws ManagerException {
		
		OBUStatusChangeResponse obuSRes = new OBUStatusChangeResponse();
		OBUInfoQueryResponse  obuInfoRes = obuInfoQuery(queryModel,user);
		if(obuInfoRes.getObuStatus()==6) {
			 obuSRes =  obuStatusChange(queryModel,user);
		}else {
			obuSRes.setStatus(0);
			obuSRes.setMessage("OBU为挂失状态才能解挂");
		}
		
		return obuSRes;
	}
	
	private OBUStatusChangeResponse obuStatusChange(OBUUnLossRequest queryModel,User user) {
		OBUStatusChangeRequest  obuchReq = new OBUStatusChangeRequest();
		OBUStatusChangeResponse obuSRes = new OBUStatusChangeResponse();
		super.commSet(obuchReq,user);
		obuchReq.setObuId(queryModel.getObuId());
		obuchReq.setStatus(1);
		try {
			obuSRes = maintenanceBinService.obuStatusChange(obuchReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obuSRes;
	}
	
	private OBUInfoQueryResponse obuInfoQuery(OBUUnLossRequest queryModel,User user) {
		OBUInfoQueryRequest obuInfoReq  = new OBUInfoQueryRequest();
		super.commSet(obuInfoReq,user);
		obuInfoReq.setObuId(queryModel.getObuId());
		OBUInfoQueryResponse obuInfoRes = new OBUInfoQueryResponse();
		try {
			obuInfoRes = inqueryBinService.obuInfoQuery(obuInfoReq);
		} catch (ApiRequestException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obuInfoRes;
	}
	
	@Override
	public OBUInfo findById(String obuId) {
		return oBUInfoRepo.findByObuId(obuId);
	}
	

	



}

