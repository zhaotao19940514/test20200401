package cn.com.taiji.css.manager.administration.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.CardInBoundAppResponse;
import cn.com.taiji.css.model.administration.inventory.DevicePackageColRequest;
import cn.com.taiji.css.model.administration.inventory.StorageCardInfoBatchRequest;
import cn.com.taiji.dsi.manager.comm.client.StorageBinService;
import cn.com.taiji.dsi.model.common.storage.CardInBoundRequest;
import cn.com.taiji.dsi.model.common.storage.CardInBoundResponse;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CssCardInfo;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.StorageCardInfo;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;
import cn.com.taiji.qtk.entity.StorageCardInfoHis;
import cn.com.taiji.qtk.entity.dict.StorageStatus;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CssCardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoBatchRepo;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoHisRepo;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoRepo;

@Service("devicewarehousingCardManager")
public class DevicewarehousingCardManagerImpl extends AbstractDsiCommManager implements DevicewarehousingCardManager {
	@Autowired
	private StorageCardInfoBatchRepo storageCardInfoBatchRepo;
	@Autowired
	private StorageCardInfoRepo storageCardInfoRepo;
	@Autowired
	private StorageCardInfoHisRepo storageCardInfoHisRepo;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private StorageBinService storageBinService;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private AgencyRepo agencyRepo;	
	@Autowired
	private CssCardInfoRepo cssCardInfoRepo;
	@Override
	public Pagination queryPage(StorageCardInfoBatchRequest queryModel) {
		return storageCardInfoBatchRepo.page(queryModel);
	}
	@Transactional
	@Override
	public CardInBoundAppResponse add(StorageCardInfoBatch cardInfoBatchModel, User user) throws ManagerException {
		validate(cardInfoBatchModel);
		long start = Long.valueOf(cardInfoBatchModel.getStartId().substring(4)).longValue();
		long end = Long.valueOf(cardInfoBatchModel.getEndId().substring(4)).longValue();
		if (start > end) {
			throw new ManagerException("起始编号应该小于结束编号");
		}
		if(start+Long.valueOf(cardInfoBatchModel.getInboundNum()).longValue()-(long)1 !=end)
			throw new ManagerException("起始编号与结束编号之间的数量和入库数量不一致");
		if (cardInfoBatchModel.getServiceHallName() == null) {
			throw new ManagerException("网点名为空");
		}
		if(cardInfoBatchModel.getType()==211) {
			if(!cardInfoBatchModel.getStartId().substring(8,10).equals("22")) {
				throw new ManagerException("入库卡类型和卡入库起始编号对应的卡类型不一致");
			}
			if(!cardInfoBatchModel.getEndId().substring(8,10).equals("22")) {
				throw new ManagerException("入库卡类型和卡入库结束编号对应的卡类型不一致");
			}
			List<CssCardInfo> listByCZType = cssCardInfoRepo.listByCZType(cardInfoBatchModel.getBrand());
			if(listByCZType.size()==0||listByCZType==null) {
				throw new ManagerException("请先在卡签设备型号管理中添加储值卡和对应的品牌");
			}
		}
		if(cardInfoBatchModel.getType()==111) {
			if(!cardInfoBatchModel.getStartId().substring(8,10).equals("23")) {
				throw new ManagerException("入库卡类型和卡入库起始编号对应的卡类型不一致");
			}
			if(!cardInfoBatchModel.getEndId().substring(8,10).equals("23")) {
				throw new ManagerException("入库卡类型和卡入库结束编号对应的卡类型不一致");
			}
			List<CssCardInfo> listByJZType = cssCardInfoRepo.listByJZType(cardInfoBatchModel.getBrand());
			if(listByJZType == null || listByJZType.size()==0) {
				throw new ManagerException("请先在卡签设备型号管理中添加记账卡和对应的品牌");
			}
		}
		CardInBoundResponse cardInBound = cardInBound(cardInfoBatchModel, user);
		CardInBoundAppResponse response = new CardInBoundAppResponse();
		StorageCardInfoBatch infoBatch = storageCardInfoBatchRepo.findByBatchId(cardInBound.getBatchId());
		response.setStorageCardInfoBatch(infoBatch);
		response.setStatus(cardInBound.getStatus());
		response.setSuccess(cardInBound.getSuccess());
		response.setMessage(cardInBound.getMessage());
		return response;
	}

	public CardInBoundResponse cardInBound(StorageCardInfoBatch cardInfoBatchModel, User user) throws ManagerException {
//		ServiceHall serviceHall = serviceHallRepo.findById(cardInfoBatchModel.getServiceHall().getId());
//		if(serviceHall == null) {
//			throw new ManagerException("入库网点不能选择渠道");
//		}
		ServiceHall serviceHall = serviceHallRepo.findByName(cardInfoBatchModel.getServiceHallName());
		String agencyId  = user.getStaff().getServiceHall().getAgencyId();
		if(!serviceHall.getAgencyId().equals(agencyId)) {
			throw new ManagerException("当前用户无此操作权限!");
		}
		CardInBoundRequest req = new CardInBoundRequest();
		super.commSet(req, user);
		req.setStorageNum(cardInfoBatchModel.getInboundNum());
		req.setEndId(cardInfoBatchModel.getEndId());
		req.setStartId(cardInfoBatchModel.getStartId());
		req.setModel(cardInfoBatchModel.getModel());
		req.setBrand(cardInfoBatchModel.getBrand());
		req.setServiceHallId(serviceHall.getServiceHallId());
		req.setCardType(String.valueOf(cardInfoBatchModel.getType()));
		req.setUserName(cardInfoBatchModel.getUserName());
		CardInBoundResponse res;
		try {
			res = storageBinService.cardInBound(req);
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("失败：" + e.getMessage());
		}
		return res;
	}

	private void validate(StorageCardInfoBatch cardInfoBatch) {
		MyViolationException mve = new MyViolationException();
		if (cardInfoBatch.getModel() == null)
			mve.addViolation("model", "黔通卡型号为空");
		if (cardInfoBatch.getType() == null) {
			mve.addViolation("type", "黔通卡类型为空");
		}
		if (cardInfoBatch.getBrand() == null)
			mve.addViolation("brand", "黔通卡品牌为空");
		if (cardInfoBatch.getServiceHallName() == null)
			mve.addViolation("serviceHallName", "黔通卡服务网点名称为空");
		if (cardInfoBatch.getInboundNum() == null)
			mve.addViolation("inboundNum", "入库数量为空");
		if (cardInfoBatch.getStartId() == null) {
			mve.addViolation("startId", "入库起始id为空");
		} else {
			if(cardInfoBatch.getStartId().length()<4) {
				mve.addViolation("startId", "贵州入库起始id起始4位应该为5201并且长度应该为20位");
			}else {
				if (cardInfoBatch.getStartId().length() != 20)
					mve.addViolation("startId", "入库起始id长度错误");
				if(!cardInfoBatch.getStartId().substring(0,4).equals("5201"))
					mve.addViolation("startId", "贵州入库起始id起始4位应该为5201");
			}
		}
		if (cardInfoBatch.getEndId() == null) {
			mve.addViolation("endId", "入库结束id为空");
		} else {
			if (cardInfoBatch.getEndId().length() != 20)
				mve.addViolation("endId", "入库结束id长度错误");
			if(!cardInfoBatch.getEndId().substring(0,4).equals("5201"))
				mve.addViolation("endId", "贵州入库结束id起始4位应该为5201");
		}
		if (mve.hasViolation()) {
			throw mve;
		}
	}

	@Override
	public StorageCardInfoBatch findById1(String id) {
		return storageCardInfoBatchRepo.findByIds(id);
	}
/*	@Transactional
	public ScheDulingResponse updateCssCardInfo(StorageCardInfoBatch storageCardInfoBatchModel, User user)
			throws ManagerException {
		AssertUtil.notNull(storageCardInfoBatchModel.getId());
		validate1(storageCardInfoBatchModel);
		StorageCardInfoBatch dbCard = storageCardInfoBatchRepo.findByIds(storageCardInfoBatchModel.getId());
		if (null == dbCard)
			throw new ManagerException("未找到该批次黔通卡信息");
		Long start = Long.valueOf(storageCardInfoBatchModel.getStartId().substring(3));
		Long end = Long.valueOf(storageCardInfoBatchModel.getEndId().substring(3));
		if (start > end) {
			throw new ManagerException("起始编号应该小于结束编号");
		}
		if (storageCardInfoBatchModel.getServiceHalls().getName() == null) {
			throw new ManagerException("调入网点名为空");
		}
		if (storageCardInfoBatchModel.getServiceHalls().getName()
				.equals(storageCardInfoBatchModel.getServiceHallName())) {
			throw new ManagerException("调入网点与调入网点不应该一致");
		}
		return ScheDuling(storageCardInfoBatchModel, user);
	}

	@Test
	public ScheDulingResponse ScheDuling(StorageCardInfoBatch cardInfoBatchModel, User user) throws ManagerException {
		ServiceHall serviceHall = serviceHallRepo.findByName(cardInfoBatchModel.getServiceHallName());
		ServiceHall serviceHall1 = serviceHallRepo.findById(cardInfoBatchModel.getServiceHalls().getId());
		ScheDulingRequest req = new ScheDulingRequest();
		super.commSet(req, user);
		req.setStorageNum(cardInfoBatchModel.getInboundNum());
		req.setEndId(cardInfoBatchModel.getEndId());
		req.setStartId(cardInfoBatchModel.getStartId());
		req.setModel("黔通卡");
		req.setServerHallId(serviceHall.getServiceHallId());
		req.setServiceHallName(serviceHall1.getName());
		req.setBatchId(cardInfoBatchModel.getBatchId());
		req.setSubmitTime(QTKUtils.getDateString());
		req.setBrand(cardInfoBatchModel.getBrand());
		ScheDulingResponse res;
		try {
			res = storageBinService.scheduling(req);
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("库存调拨接口调用异常");
		}
		return res;
	}

	private void validate1(StorageCardInfoBatch cardInfoBatchModel) {
		MyViolationException mve = new MyViolationException();
		if (cardInfoBatchModel.getModel() == null)
			mve.addViolation("model", "黔通卡型号为空");
		if (cardInfoBatchModel.getServiceHallName() == null)
			mve.addViolation("serviceHallName", "所属网点为空");
		if (cardInfoBatchModel.getInboundNum() == null)
			mve.addViolation("inboundNum", "调拨数量为空");
		if (cardInfoBatchModel.getStartId() == null) {
			mve.addViolation("startId", "调拨起始id为空");
		} else {
			if (cardInfoBatchModel.getStartId().length() != 20)
				mve.addViolation("startId", "调拨起始id长度错误");
			if(!cardInfoBatchModel.getStartId().substring(0,4).equals("5201"))
				mve.addViolation("startId", "贵州调拨起始id起始4位应该为5201");
		}
		if (cardInfoBatchModel.getEndId() == null) {
			mve.addViolation("endId", "调拨结束id为空");
		} else {
			if (cardInfoBatchModel.getEndId().length() != 20)
				mve.addViolation("endId", "调拨结束id长度错误");
			if(!cardInfoBatchModel.getEndId().substring(0,4).equals("5201"))
				mve.addViolation("startId", "贵州调拨起始id起始4位应该为5201");
		}
		if (mve.hasViolation()) {
			throw mve;
		}
	}*/

	/*@Override
	public String generateBatchId() {
		String batchId = "DB-" + new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		return batchId;
	}*/

	@Override
	public CardEndCalculateResponse generateEndId(CardEndCalculateRequest cardEndCalculateRequest) throws ManagerException {
		CardEndCalculateResponse response = new CardEndCalculateResponse();
		if(cardEndCalculateRequest.getStartId().length()<4) {
			throw new ManagerException("入库起始编号位数不正确");
		}else {
			Long start = Long.valueOf(cardEndCalculateRequest.getStartId().substring(3));
			System.out.println(start);
			Long inBound = Long.valueOf(cardEndCalculateRequest.getInboundNum());
			Long end = start+inBound-(long)1;
			String endId = "520"+String.valueOf(end);
			response.setEndId(endId);
		}
		
		return response;
	}
	@Transactional
	@Override
	public void updateStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) throws ManagerException {
		// 入库 -1  调拨-2  冲正-9  发行-4
		List<CardInfo> card = cardInfoRepo.listByStorageCardId(storageCardInfoBatch.getStartId(),storageCardInfoBatch.getEndId());
		if(card.size()>0) throw new ManagerException("该卡批次中存在已发行的卡,不能冲正!");
		storageCardInfoBatch.setStatus(StorageStatus.REVERSAL.getCode().toString());
		List<StorageCardInfoHis> lists = Lists.newArrayList();
		List<StorageCardInfo> storageCardInfos = storageCardInfoRepo.ListByCardId3(storageCardInfoBatch.getStartId(), storageCardInfoBatch.getEndId());
		if(storageCardInfos.size() !=0)
			throw new ManagerException("该批次中的卡存在已发行状态的卡，不能冲正!");
		List<StorageCardInfo> storageCardInfo = storageCardInfoRepo.findByCardId3(storageCardInfoBatch.getStartId(), storageCardInfoBatch.getEndId());
		if(storageCardInfo.size() ==0 ||storageCardInfo.size()!=storageCardInfoBatch.getInboundNum())
			throw new ManagerException("该批次中的卡存在已被调拨或者其他原因已经不是入库状态，不能冲正!");
		for(int i = 0;i<storageCardInfo.size();i++) {
			StorageCardInfoHis sci = new StorageCardInfoHis();
			BeanUtils.copyProperties(storageCardInfo.get(i), sci);
			lists.add(sci);
		}
		try {
			storageCardInfoHisRepo.persistAll(lists);
			storageCardInfoRepo.deleteAll(storageCardInfo);
			storageCardInfoBatchRepo.save(storageCardInfoBatch);
		} catch (Exception e) {
			throw new ManagerException("冲正失败!");
		}
	}
/**
 * ztree
 */
	@Override
	public List<ZTreeItem> getCurrentConf(String agencyId) {
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
				logger.info(String.valueOf(checked));
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
