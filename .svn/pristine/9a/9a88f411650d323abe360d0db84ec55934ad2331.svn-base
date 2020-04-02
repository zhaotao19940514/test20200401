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
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.DevicePackageColRequest;
import cn.com.taiji.css.model.administration.inventory.ScheDulingAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageOperationRequest;
import cn.com.taiji.dsi.manager.comm.client.StorageBinService;
import cn.com.taiji.dsi.model.common.storage.ScheDulingRequest;
import cn.com.taiji.dsi.model.common.storage.ScheDulingResponse;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.StorageCardInfo;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;
import cn.com.taiji.qtk.entity.dict.StorageStatus;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoBatchRepo;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoRepo;

@Service("deviceallocationManager")
public class DeviceallocationManagerImpl extends AbstractDsiCommManager implements DeviceallocationManager {
	@Autowired
	private StorageCardInfoBatchRepo storageCardInfoBatchRepo;
	@Autowired
	private StorageBinService storageBinService;
	@Autowired
	private StorageCardInfoRepo storageCardInfoRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private AgencyRepo agencyRepo;	

	@Transactional
	@Override
	public ScheDulingAppResponse add(StorageOperationRequest req, User user) throws ManagerException {
		validate(req);
		long start = Long.valueOf(req.getStartId().substring(4)).longValue();
		long end = Long.valueOf(req.getEndId().substring(4)).longValue();
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
			throw new ManagerException("调入网点与调出网点不能相同！");
		}
		if(req.getModel().equals("211")) {
			if(!req.getStartId().substring(8,10).equals("22")) {
				throw new ManagerException("调拨卡类型和卡调拨起始编号对应的卡类型不一致");
			}
			if(!req.getEndId().substring(8,10).equals("22")) {
				throw new ManagerException("调拨卡类型和卡调拨结束编号对应的卡类型不一致");
			}
		}
		if(req.getModel().equals("111")) {
			if(!req.getStartId().substring(8,10).equals("23")) {
				throw new ManagerException("调拨卡类型和卡调拨起始编号对应的卡类型不一致");
			}
			if(!req.getEndId().substring(8,10).equals("23")) {
				throw new ManagerException("调拨卡类型和卡调拨结束编号对应的卡类型不一致");
			}
		}
		ScheDulingResponse scheDuling = ScheDuling(req, user);
		ScheDulingAppResponse response = new ScheDulingAppResponse();
		StorageCardInfoBatch batch = storageCardInfoBatchRepo.findByBatchId(scheDuling.getBatchId());
		response.setStorageCardInfoBatch(batch);
		response.setStatus(scheDuling.getStatus());
		response.setSuccess(scheDuling.getSuccess());
		response.setMessage(scheDuling.getMessage());
		return response;
	}

	private void validate(StorageOperationRequest req) {
		MyViolationException mve = new MyViolationException();
		if (req.getModel() == null)
			mve.addViolation("model", "黔通卡型号为空");
		if (req.getOutServiceHall() == null)
			mve.addViolation("serviceHall", "黔通卡服务网点为空");
		if (req.getOutboundNum() == null)
			mve.addViolation("outboundNum", "调拨数量为空");
		if (req.getStartId() == null) {
			mve.addViolation("startId", "调拨起始id为空");
		} else {
			if (req.getStartId().length() != 20)
				mve.addViolation("startId", "调拨起始id长度错误");
			if(!req.getStartId().substring(0,4).equals("5201"))
				mve.addViolation("startId", "贵州入库起始id起始4位应该为5201");
		}
		if (req.getEndId() == null) {
			mve.addViolation("endId", "调拨结束id为空");
		} else {
			if (req.getEndId().length() != 20)
				mve.addViolation("endId", "调拨结束id长度错误");
			if(!req.getEndId().substring(0,4).equals("5201"))
				mve.addViolation("endId", "贵州入库结束id起始4位应该为5201");
		}
		if (mve.hasViolation()) {
			throw mve;
		}
	}

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
		req.setOperationType(1);//1-卡调拨  2-obu调拨
		req.setStorageNum(model.getOutboundNum());
		req.setEndId(model.getEndId());
		req.setStartId(model.getStartId());
		req.setServiceHallId(inServiceHall.getServiceHallId());
		req.setOutServiceHallId(outServiceHall.getServiceHallId());
		req.setUserName(model.getUserName());
		ScheDulingResponse res;
		try {
			res = storageBinService.scheduling(req);
		} catch (ApiRequestException| IOException e) {
			e.printStackTrace();
			throw new ManagerException("失败：" + e.getMessage());
		} 
		return res;
	}

	@Override
	public CardEndCalculateResponse generateEndId(CardEndCalculateRequest cardEndCalculateRequest) {
		Long start = Long.valueOf(cardEndCalculateRequest.getStartId().substring(3));
		Long inBound = Long.valueOf(cardEndCalculateRequest.getOutboundNum());
		Long end = start+inBound-(long)1;
		String endId = "520"+String.valueOf(end);
		CardEndCalculateResponse response = new CardEndCalculateResponse();
		response.setEndId(endId);
		return response;
	}
	/**
	 * 根据id查询
	 */
	@Override
	public StorageCardInfoBatch findById1(String id) {
		return storageCardInfoBatchRepo.findByIds(id);
	}
	@Transactional
	@Override
	public void updateStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) throws ManagerException {
		//校验 ， 如果该批次中存在不是待确认的状态的卡，throw exception
		if(!storageCardInfoBatch.getStatus().equals(StorageStatus.APPLY.getCode().toString())||storageCardInfoBatch.getStatus()==null)
			throw new ManagerException("该卡批次的库存状态不是调拨申请，不能进行调拨确认!");
		List<StorageCardInfo> listByCardIdLocation = storageCardInfoRepo.listByBatchIdAndStatus(storageCardInfoBatch.getBatchId(), StorageStatus.APPLY.getCode());
		if(storageCardInfoBatch.getOutboundNum().intValue() != listByCardIdLocation.size()) 
			throw new ManagerException("卡库存详情数量与调拨批次中的数量不一致，不能进行调拨确认!");
		storageCardInfoBatch.setStatus(StorageStatus.FLITTING.getCode().toString());
		storageCardInfoBatch.setInboundDate(CssUtil.getNowDateTimeStrWithoutT());
		storageCardInfoBatch.setInboundNum(listByCardIdLocation.size());
		List<StorageCardInfo> lists = Lists.newArrayList();
		for(int i = 0;i<listByCardIdLocation.size();i++) {
			listByCardIdLocation.get(i).setStatus(StorageStatus.FLITTING.getCode());
			listByCardIdLocation.get(i).setStatusChangeTime(CssUtil.getNowDateTimeStrWithoutT());
			lists.add(listByCardIdLocation.get(i));
		}
		try {
			storageCardInfoRepo.saveAll(lists);
			storageCardInfoBatchRepo.save(storageCardInfoBatch);
		} catch (Exception e) {
			throw new ManagerException("卡片调拨确认失败!");
		}
	}

//	@Override
//	public void reversalStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) throws ManagerException {
//		if(!storageCardInfoBatch.getStatus().equals("5")||storageCardInfoBatch.getStatus()==null)
//			throw new ManagerException("该卡批次的库存状态错误，不能进行调拨冲正!");
//		List<StorageCardInfo> lists = Lists.newArrayList();
//		List<StorageCardInfo> listByCardIdLocation = storageCardInfoRepo.listByCardIdAndStatus(storageCardInfoBatch.getStartId(), storageCardInfoBatch.getEndId());
//		if(listByCardIdLocation.size()!=storageCardInfoBatch.getOutboundNum())
//			throw new ManagerException("库存中卡状态为调拨申请的数量与调拨申请数量不一致，不能进行调拨冲正!");
//		storageCardInfoBatch.setStatus("9");
//		storageCardInfoBatch.setCreateTime(Calendar.getInstance());
//		for(int i = 0;i<listByCardIdLocation.size();i++) {
//			listByCardIdLocation.get(i).setStatus(9);
//			listByCardIdLocation.get(i).setStatusChangeTime(QTKUtils.getDateString());
//			listByCardIdLocation.get(i).setCreateTime(Calendar.getInstance());
//			lists.add(listByCardIdLocation.get(i));
//		}
//		try {
//			storageCardInfoRepo.saveAll(lists);
//			storageCardInfoBatchRepo.save(storageCardInfoBatch);
//		} catch (Exception e) {
//			throw new ManagerException("卡片调拨冲正失败!");
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
