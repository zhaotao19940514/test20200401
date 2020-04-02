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
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.obu.OBUHangRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.OBUInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.Black15MinusLimit;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.repo.jpa.Black15MinusLimitRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;

/**
 * @ClassName RechargeManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:38
 * @E_mail yaonanlin@163.com
 */
@Service
public class OBUHangManagerImpl extends AbstractDsiCommManager implements OBUHangManager{

	@Autowired
	private OBUInfoRepo oBUInfoRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private ExchangeManager exchangeManager;
	@Autowired
	private Black15MinusLimitRepo black15MinusLimitRepo;
	
	
	
	@Override
	public LargePagination<OBUInfo> queryPage(OBUHangRequest queryModel,User user) throws ManagerException {
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
	public OBUStatusChangeResponse doHangObu(@Valid OBUHangRequest queryModel, User user) {
		OBUInfoQueryRequest oBUInfoQueryReq = new OBUInfoQueryRequest();
		OBUInfoQueryResponse oBUInfoQueryRes = new OBUInfoQueryResponse();
		super.commSet(oBUInfoQueryReq,user);
		oBUInfoQueryReq.setObuId(queryModel.getObuId());
		
		OBUStatusChangeRequest OBUCReq = new OBUStatusChangeRequest();
		OBUStatusChangeResponse OBUCRes = new OBUStatusChangeResponse();
		super.commSet(OBUCReq,user);
		OBUCReq.setObuId(queryModel.getObuId());
		OBUCReq.setStatus(3);
		if(queryModel.getProvider()==1) {
			OBUCReq.setStatus(2);
		}
		try {
			oBUInfoQueryRes = inqueryBinService.obuInfoQuery(oBUInfoQueryReq);
			if(oBUInfoQueryRes.getStatus()==1||oBUInfoQueryRes.getStatus()==6) {
				OBUCRes = maintenanceBinService.obuStatusChange(OBUCReq);
				//挂起成功后，保存obu号，如果进行过户，会有15分钟黑名单限制
				if(OBUCRes.getStatus()==1&&queryModel.getProvider()==0) {
					Black15MinusLimit black15MinusLimit = new Black15MinusLimit();
					black15MinusLimit.setObuId(queryModel.getObuId());
					black15MinusLimit.setCreateTime(Calendar.getInstance());
					black15MinusLimit.setServiceName("ObuHang");
					black15MinusLimitRepo.save(black15MinusLimit);
				}
			}else {
				OBUCRes.setStatus(0);
				OBUCRes.setMessage("obu状态为正常或挂失才能执行挂起业务");
			}
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		return OBUCRes;
	}

	@Override
	public OBUInfo findById(String obuId) {
		return oBUInfoRepo.findByObuId(obuId);
	}

}

