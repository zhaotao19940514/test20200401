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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.obu.OBUHangRequest;
import cn.com.taiji.css.model.customerservice.obu.OBUUnhangRequest;
import cn.com.taiji.css.model.customerservice.obu.RewriteRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
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
public class OBUUnhangManagerImpl extends AbstractDsiCommManager implements OBUUnhangManager{

	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private VehicleInfoRepo veInfoRepo;
	@Autowired
	private ExchangeManager exchangeManager;
	@Override
	public LargePagination<OBUInfo> queryPage(OBUUnhangRequest queryModel,User user) throws ManagerException {
		
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

	@Override
	public void doUnHangObu(@Valid OBUHangRequest queryModel, User user) {
		
		OBUInfoQueryRequest oBUInfoQueryReq = new OBUInfoQueryRequest();
		OBUInfoQueryResponse oBUInfoQueryRes = new OBUInfoQueryResponse();
		super.commSet(oBUInfoQueryReq,user);
		oBUInfoQueryReq.setObuId(queryModel.getObuId());
		
		OBUStatusChangeRequest OBUCReq = new OBUStatusChangeRequest();
		OBUStatusChangeResponse OBUCRes = new OBUStatusChangeResponse();
		super.commSet(OBUCReq,user);
		OBUCReq.setObuId(queryModel.getObuId());
		OBUCReq.setStatus(1);
		try {
			oBUInfoQueryRes = inqueryBinService.obuInfoQuery(oBUInfoQueryReq);
			if(oBUInfoQueryRes.getStatus()==1||oBUInfoQueryRes.getStatus()==6) {
				OBUCRes = maintenanceBinService.obuStatusChange(OBUCReq);
			}else {
				OBUCRes.setStatus(0);
				OBUCRes.setMessage("obu状态为正常或挂失才能执行挂起业务");
			}
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public OBUInfo findById(String obuId) {
		return oBUInfoRepo.findByObuId(obuId);
	}

	@Override
	public OBUStatusChangeResponse updateObuStatus(@Valid RewriteRequest queryModel, User user) {
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
	@Override
	public OBUInfoChangeResponse reWriteVehicleInfo(RewriteRequest queryModel, User loginUser) throws ManagerException {
		OBUInfoChangeResponse obuInfoRes = new OBUInfoChangeResponse();
		VehicleInfo vehInfo = queryVeInfo(queryModel);
		
		/*VehicleInfo oldVehInfo = veInfoRepo.findByVehicleId(queryModel.getVehicleId());
		if(!vehInfo.getCustomerId().equals(oldVehInfo.getCustomerId())) {
			obuInfoRes.setMessage("该车牌不属于该用户");
			obuInfoRes.setStatus(0);
			return obuInfoRes;
		}*/
		obuInfoRes = obuInfoChange(queryModel,loginUser,vehInfo.getCustomerId());
		return obuInfoRes;
	}
	
	public VehicleInfo queryVeInfo(RewriteRequest queryModel) {
		VehicleInfo info= null;
		if(StringTools.hasText(queryModel.getVehPlate())&&null!=queryModel.getVehColor()) {
			String vehicleId =  queryModel.getVehPlate()+"_"+queryModel.getVehColor();
			 info= veInfoRepo.findByVehicleId(vehicleId);
		}
		return info;
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

}

