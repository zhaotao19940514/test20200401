package cn.com.taiji.css.manager.administration.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.DevicePackageColRequest;
import cn.com.taiji.css.model.administration.inventory.ScheDulingAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageOperationRequest;
import cn.com.taiji.dsi.manager.comm.client.StorageBinService;
import cn.com.taiji.dsi.model.common.storage.ScheDulingRequest;
import cn.com.taiji.dsi.model.common.storage.ScheDulingResponse;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.StorageObuInfo;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;
import cn.com.taiji.qtk.entity.dict.StorageStatus;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoBatchRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoRepo;

@Service("deviceallocationObuManager")
public class DeviceallocationObuManagerImpl extends AbstractDsiCommManager implements DeviceallocationObuManager {
	@Autowired
	private StorageObuInfoBatchRepo storageObuInfoBatchRepo;
	@Autowired
	private StorageObuInfoRepo storageObuInfoRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private StorageBinService storageBinService;
	@Autowired
	private AgencyRepo agencyRepo;	

	@Override
	public ScheDulingAppResponse add(StorageOperationRequest req, User user) throws ManagerException {
		validate(req);
		long start = Long.valueOf(req.getStartId()).longValue();
		long end = Long.valueOf(req.getEndId()).longValue();
		if (start > end) {
			throw new ManagerException("起始编号应该小于结束编号");
		}
		if(start+Long.valueOf(req.getOutboundNum()).longValue()-1L !=end)
			throw new ManagerException("起始编号与结束编号之间的数量和调拨数量不一致");
		if (req.getOutServiceHall().getName() == null) {
			throw new ManagerException("调出网点名为空");
		}
		if (req.getInServiceHall().getName() == null) {
			throw new ManagerException("调入网点名为空");
		}
		if (req.getInServiceHall().getName().equals(req.getOutServiceHall().getName())) {
			throw new ManagerException("调入网点与调出网点不能相同");
		}
		
//		saveStorageObuBatch(req, user);
		
		ScheDulingResponse scheDuling = ScheDuling(req, user);
		ScheDulingAppResponse response = new ScheDulingAppResponse();
		StorageObuInfoBatch infoBatch = storageObuInfoBatchRepo.findByBatchId(scheDuling.getBatchId());
		response.setStorageObuInfoBatch(infoBatch);
		response.setStatus(scheDuling.getStatus());
		response.setSuccess(scheDuling.getSuccess());
		response.setMessage(scheDuling.getMessage());
		return response;
	}

//	private void saveStorageObuBatch(StorageObuInfoBatch obuInfoBatchModel, User user) throws ManagerException {
//		
//		StorageObuInfo storageObuInfo = storageObuInfoRepo.findByObuIdCheck(obuInfoBatchModel.getStartId());
//		if(null==storageObuInfo) {
//			throw new ManagerException("该网点未入库该obu编号");
//		}
//		List<Object[]> batchIdList = storageObuInfoRepo.listBatchId(obuInfoBatchModel.getStartId(), obuInfoBatchModel.getEndId());
//		if(!storageObuInfo.getBatchId().substring(storageObuInfo.getBatchId().length()-5, storageObuInfo.getBatchId().length()).contains("-")&&batchIdList.size()>1) {
//			throw new ManagerException("起始编号与结束编号之间不属于同一批次");
//		}
//		StorageObuInfoBatch storageObuinfoBatch = storageObuInfoBatchRepo.findByBatchId(storageObuInfo.getBatchId());
//		if(null==storageObuinfoBatch) {
//			storageObuinfoBatch = new StorageObuInfoBatch();
//			storageObuinfoBatch.setBrand(storageObuInfo.getBrand());
//			storageObuinfoBatch.setBanchId(storageObuInfo.getBatchId());
//			Date date = new Date();
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date);
//			storageObuinfoBatch.setCreateTime(calendar);
//			storageObuinfoBatch.setStartId(obuInfoBatchModel.getStartId());
//			storageObuinfoBatch.setEndId(obuInfoBatchModel.getEndId());
//			storageObuinfoBatch.setOutServiceHallName(user.getStaff().getServiceHall().getName());
//			storageObuinfoBatch.setServiceHallName(obuInfoBatchModel.getServiceHalls().getName());
//			storageObuinfoBatch.setServiceHallId(obuInfoBatchModel.getServiceHallId());
//			storageObuinfoBatch.setServiceHallName(obuInfoBatchModel.getServiceHallName());
//			storageObuinfoBatch.setUserName(user.getStaffId());
//			storageObuinfoBatch.setStatus(storageObuInfo.getStatus().toString());
//			storageObuinfoBatch.setModel(storageObuInfo.getModel());
//			storageObuinfoBatch.setInBoundNum(obuInfoBatchModel.getInBoundNum());
//			storageObuinfoBatch.setReleaseNum(0);
//			storageObuinfoBatch.setType("标准型");
//			storageObuInfoBatchRepo.save(storageObuinfoBatch);
//		}
//	}

	private ScheDulingResponse ScheDuling(StorageOperationRequest model, User user) throws ManagerException {
		ServiceHall outServiceHall = serviceHallRepo.findByServiceHallId(model.getOutServiceHall().getServiceHallId());
		ServiceHall inServiceHall = serviceHallRepo.findByServiceHallId(model.getInServiceHall().getServiceHallId());
		if(outServiceHall == null) {
			throw new ManagerException("调出网点不能选择渠道");
		}
		if(inServiceHall == null) {
			throw new ManagerException("调入网点不能选择渠道");
		}
		String agencyId  = user.getStaff().getServiceHall().getAgencyId();
		if(!"52010106004".equals(agencyId)) {
			if(!outServiceHall.getAgencyId().equals(agencyId)) {
				throw new ManagerException("该工号只允许调拨本渠道的网点");
			}
			if(!inServiceHall.getAgencyId().equals(agencyId)) {
				throw new ManagerException("该工号只能在本渠道网点之间调拨");
			}
		}
		ScheDulingRequest req = new ScheDulingRequest();
		super.commSet(req, user);
		req.setOperationType(2);//1-卡调拨  2-obu调拨
		req.setStorageNum(model.getOutboundNum());
		req.setEndId(model.getEndId());
		req.setStartId(model.getStartId());
		req.setOutServiceHallId(outServiceHall.getServiceHallId());
		req.setServiceHallId(inServiceHall.getServiceHallId());
		req.setUserName(model.getUserName());
		ScheDulingResponse res;
		try {
			res = storageBinService.scheduling(req);
		} catch (ApiRequestException |IOException e) {
			e.printStackTrace();
			throw new ManagerException("失败：" + e.getMessage());
		} 
		return res;
	}

	private void validate(StorageOperationRequest model) {
		MyViolationException mve = new MyViolationException();
		if (model.getModel() == null)
			mve.addViolation("model", "电子标签型号为空");
		if (model.getOutServiceHall() == null)
			mve.addViolation("serviceHall", "黔通卡服务网点为空");
		if (model.getOutboundNum() == null)
			mve.addViolation("outBoundNum", "调拨数量为空");
		if (model.getStartId() == null) {
			mve.addViolation("startId", "调拨起始id为空");
		} else {
			if (model.getStartId().length() != 16)
				mve.addViolation("startId", "调拨起始id长度错误");
		}
		if (model.getEndId() == null) {
			mve.addViolation("endId", "调拨结束id为空");
		} else {
			if (model.getEndId().length() != 16)
				mve.addViolation("endId", "调拨结束id长度错误");
		}
		if (mve.hasViolation()) {
			throw mve;
		}
	}

	@Override
	public CardNoCalculateResponse generateEndId(CardNoCalculateRequest cardNoCalculateRequest) {
		Long start = Long.valueOf(cardNoCalculateRequest.getStartId());
		Long inBound = Long.valueOf(cardNoCalculateRequest.getOutBoundNum());
		Long end = start+inBound-(long)1;
		String endId = String.valueOf(end);
		CardNoCalculateResponse response = new CardNoCalculateResponse();
		response.setEndId(endId);
		return response;
	}

	@Override
	public StorageObuInfoBatch findById1(String id) {
		return storageObuInfoBatchRepo.findByIds(id);
	}
	@Transactional
	@Override
	public void updateStorageCardInfoBatch(StorageObuInfoBatch storageObuInfoBatch) throws ManagerException {
		//校验 ， 如果该批次中存在不是待确认的状态的卡，throw exception
		if(!storageObuInfoBatch.getStatus().equals(StorageStatus.APPLY.getCode().toString())||storageObuInfoBatch.getStatus()==null)
				throw new ManagerException("该obu批次的库存状态错误，不能进行调拨确认!");
		List<StorageObuInfo> obuInfo = storageObuInfoRepo.listByBatchIdAndStatus(storageObuInfoBatch.getBatchId(), StorageStatus.APPLY.getCode());
		if(storageObuInfoBatch.getOutboundNum().intValue() != obuInfo.size()) 
			throw new ManagerException("OBU库存详情数量与调拨批次中的数量不一致，不能进行调拨确认!");
		storageObuInfoBatch.setStatus(StorageStatus.FLITTING.getCode().toString());
		storageObuInfoBatch.setInboundDate(CssUtil.getNowDateTimeStrWithoutT());
		storageObuInfoBatch.setInboundNum(obuInfo.size());
		List<StorageObuInfo> lists = Lists.newArrayList();
		for(int i = 0;i<obuInfo.size();i++) {
			obuInfo.get(i).setStatus(StorageStatus.FLITTING.getCode());
			obuInfo.get(i).setStatusChangeTime(CssUtil.getNowDateTimeStrWithoutT());
			lists.add(obuInfo.get(i));
		}
		try {
			storageObuInfoRepo.saveAll(lists);
			storageObuInfoBatchRepo.save(storageObuInfoBatch);
		} catch (Exception e) {
			throw new ManagerException("obu调拨确认失败!");
		}
	}

//	@Override
//	public void reversalStorageObuInfoBatch(StorageObuInfoBatch storageObuInfoBatch) throws ManagerException {
//		if(!storageObuInfoBatch.getStatus().equals("5")||storageObuInfoBatch.getStatus()==null)
//			throw new ManagerException("该obu批次的库存状态错误，不能进行调拨确认!");
//		List<StorageObuInfo> lists = Lists.newArrayList();
//		List<StorageObuInfo> listByCardIdLocation = storageObuInfoRepo.listByObuIdAndStatus(storageObuInfoBatch.getStartId(), storageObuInfoBatch.getEndId());
//		if(listByCardIdLocation.size()!=storageObuInfoBatch.getOutBoundNum())
//			throw new ManagerException("库存中obu状态为调拨申请的数量与调拨申请数量不一致，不能进行调拨冲正!");
//		storageObuInfoBatch.setStatus("9");
//		storageObuInfoBatch.setCreateTime(Calendar.getInstance());
//		for(int i = 0;i<listByCardIdLocation.size();i++) {
//			listByCardIdLocation.get(i).setStatus(9);
//			listByCardIdLocation.get(i).setStatusChangeTime(QTKUtils.getDateString());
//			listByCardIdLocation.get(i).setCreateTime(Calendar.getInstance());
//			lists.add(listByCardIdLocation.get(i));
//		}
//		try {
//			storageObuInfoRepo.saveAll(lists);
//			storageObuinfoBatchRepo.save(storageObuInfoBatch);
//		} catch (Exception e) {
//			throw new ManagerException("obu调拨冲正失败!");
//		}
//	}

	@Override
	public List<ZTreeItem> getCurrentConf() {
		List<ZTreeItem> rs = new ArrayList<ZTreeItem>();
		List<Agency> agencyList = agencyRepo.findAll();
		for (Agency agency : agencyList) {
//			if(("52010102002").equals(agency.getAgencyId())||("52010102007").equals(agency.getAgencyId()))continue;
			if(agency.getFileDir()==null)continue;
			List<ServiceHall> serviceHallList = serviceHallRepo.list(new DevicePackageColRequest(agency.getAgencyId()));
			if (serviceHallList.size() > 0) {
				ZTreeItem item = new ZTreeItem();
				item.setId(agency.getAgencyId());
				item.setName(agency.getName());
				List<ZTreeItem> children = handleChildren(serviceHallList);
				boolean checked = false;
				for (ZTreeItem r : children) {
					if (r.isChecked() == true) {
						checked = true;
					}
				}
				item.setChecked(checked);
//				logger.info(String.valueOf(checked));
				item.setChildren(children);
				rs.add(item);
			}
		}
		return rs;
	}
	private List<ZTreeItem> handleChildren(List<ServiceHall> res) {
		List<ZTreeItem> list = new ArrayList<ZTreeItem>();
		for (ServiceHall serviceHall : res) {
			ZTreeItem item = new ZTreeItem();
			item.setId(serviceHall.getServiceHallId());
			item.setName(serviceHall.getName());
			/*IssupePackageServiceHall ips = issuePackageServiceHallRepo.findByAgencyAndPackage(serviceHall.getId(),
					packageId);
			if (ips != null)*/
//				item.setChecked(true);
//			item.setChildren(handleChildren(
//					serviceHallRepo.list(new IssuePackageListRequest(serviceHall.getId(), packageId)), packageId));
			list.add(item);
		}

		return list;
}
}
