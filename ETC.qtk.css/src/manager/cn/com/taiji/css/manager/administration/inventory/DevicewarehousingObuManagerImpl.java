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
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.DevicePackageColRequest;
import cn.com.taiji.css.model.administration.inventory.ObuInBoundAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageObuInfoBatchRequest;
import cn.com.taiji.dsi.manager.comm.client.StorageBinService;
import cn.com.taiji.dsi.model.common.storage.OBUInBoundRequest;
import cn.com.taiji.dsi.model.common.storage.OBUInBoundResponse;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.CssCardInfo;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.StorageObuInfo;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;
import cn.com.taiji.qtk.entity.StorageObuInfoHis;
import cn.com.taiji.qtk.entity.dict.StorageStatus;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.CssCardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoBatchRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoHisRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoRepo;

@Service("devicewarehousingObuManager")
public class DevicewarehousingObuManagerImpl extends AbstractDsiCommManager implements DevicewarehousingObuManager {
	@Autowired
	private StorageObuInfoBatchRepo storageObuinfoBatchRepo;
	@Autowired
	private StorageObuInfoRepo storageObuInfoRepo;
	@Autowired
	private StorageObuInfoHisRepo storageObuInfoHisRepo;
	@Autowired
	private OBUInfoRepo obuInfoRepo;
	@Autowired
	private CssCardInfoRepo cssCardInfoRepo;
	@Autowired
	private StorageBinService storageBinService;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private AgencyRepo agencyRepo;	

	@Override
	public Pagination queryPage(StorageObuInfoBatchRequest queryModel) {
		return storageObuinfoBatchRepo.page(queryModel);
	}

	@Override
	public ObuInBoundAppResponse add(StorageObuInfoBatch obuInfoBatchModel, User user) throws ManagerException {
		validate(obuInfoBatchModel);
		long start = Long.valueOf(obuInfoBatchModel.getStartId()).longValue();
		long end = Long.valueOf(obuInfoBatchModel.getEndId()).longValue();
		if (start > end) {
			throw new ManagerException("起始编号应该小于结束编号");
		}
		if(start+Long.valueOf(obuInfoBatchModel.getInboundNum()).longValue()-1L !=end)
			throw new ManagerException("起始编号与结束编号之间的数量和入库数量不一致");
		if (obuInfoBatchModel.getServiceHallName() == null) {
			throw new ManagerException("网点名为空");
		}
		if(obuInfoBatchModel.getBrand()!=null) {
			List<CssCardInfo> listByObuBrand = cssCardInfoRepo.listByObuBrand(obuInfoBatchModel.getBrand());
			if(listByObuBrand == null || listByObuBrand.size()==0) {
				throw new ManagerException("请先在卡签设备型号管理中添加电子标签和对应的品牌");
			}
		}
		OBUInBoundResponse obuInBound = obuInBound(obuInfoBatchModel, user);
		ObuInBoundAppResponse response = new ObuInBoundAppResponse();
		StorageObuInfoBatch infoBatch = storageObuinfoBatchRepo.findByBatchId(obuInBound.getBatchId());
		response.setStorageObuInfoBatch(infoBatch);
		response.setStatus(obuInBound.getStatus());
		response.setSuccess(obuInBound.getSuccess());
		response.setMessage(obuInBound.getMessage());
		return response;
		
	}

	private OBUInBoundResponse obuInBound(StorageObuInfoBatch obuInfoBatchModel, User user) throws ManagerException {
//		ServiceHall serviceHall = serviceHallRepo.findById(obuInfoBatchModel.getServiceHall().getId());
//		if(serviceHall == null) {
//			throw new ManagerException("入库网点不能选择渠道");
//		}
		ServiceHall serviceHall = serviceHallRepo.findByName(obuInfoBatchModel.getServiceHallName());
		String agencyId  = user.getStaff().getServiceHall().getAgencyId();
		if(!serviceHall.getAgencyId().equals(agencyId)) {
			throw new ManagerException("当前用户无此操作权限!");
		}
		OBUInBoundRequest req = new OBUInBoundRequest();
		super.commSet(req, user);
		req.setStorageNum(obuInfoBatchModel.getInboundNum());
		req.setEndId(obuInfoBatchModel.getEndId());
		req.setStartId(obuInfoBatchModel.getStartId());
		req.setModel(obuInfoBatchModel.getModel());
		req.setBrand(obuInfoBatchModel.getBrand());
		req.setServiceHallId(serviceHall.getServiceHallId());
		req.setType(obuInfoBatchModel.getType());
		req.setUserName(obuInfoBatchModel.getUserName());
		OBUInBoundResponse res;
		try {
			res = storageBinService.obuInBound(req);
		} catch (ApiRequestException |IOException e) {
			e.printStackTrace();
			throw new ManagerException("失败：" + e.getMessage());
		} 
		return res;
	}

	private void validate(StorageObuInfoBatch obuInfoBatchModel) {
		MyViolationException mve = new MyViolationException();
		if (obuInfoBatchModel.getModel() == null)
			mve.addViolation("model", "电子标签型号为空");
		if (obuInfoBatchModel.getType() == null)
			mve.addViolation("type", "电子标签类型为空");
		if (obuInfoBatchModel.getBrand() == null)
			mve.addViolation("brand", "电子标签品牌为空");
		if (obuInfoBatchModel.getServiceHallName() == null)
			mve.addViolation("serviceHallName", "电子标签服务网点名称为空");
		if (obuInfoBatchModel.getInboundNum() == null)
			mve.addViolation("inBoundNum", "电子标签入库数量为空");
		if (obuInfoBatchModel.getStartId() == null) {
			mve.addViolation("startId", "电子标签入库起始id为空");
		} else {
			if (obuInfoBatchModel.getStartId().length() != 16)
				mve.addViolation("startId", "电子标签入库起始id长度错误");
		}
		if (obuInfoBatchModel.getEndId() == null) {
			mve.addViolation("endId", "电子标签入库结束id为空");
		} else {
			if (obuInfoBatchModel.getEndId().length() != 16)
				mve.addViolation("endId", "电子标签入库结束id长度错误");
		}
		if (mve.hasViolation()) {
			throw mve;
		}
	}
	
	@Override
	public CardNoCalculateResponse generateEndId(CardNoCalculateRequest cardNoCalculateRequest) {
		Long start = Long.valueOf(cardNoCalculateRequest.getStartId());
		Long inBound = Long.valueOf(cardNoCalculateRequest.getInBoundNum());
		Long end = start+inBound-(long)1;
		String endId = String.valueOf(end);
		CardNoCalculateResponse response = new CardNoCalculateResponse();
		response.setEndId(endId);
		return response;
	}

	@Override
	public StorageObuInfoBatch findById1(String id) {
		return storageObuinfoBatchRepo.findByIds(id);
	}
	@Transactional
	@Override
	public void updateStorageObuInfoBatch(StorageObuInfoBatch obuInfoBatchModel) throws ManagerException {
		// 入库 -1  调拨-2  冲正-9  发行-4
		List<OBUInfo> obu = obuInfoRepo.listByStorageObuId(obuInfoBatchModel.getStartId(),obuInfoBatchModel.getEndId());
		if(obu.size()>0) throw new ManagerException("该obu库存批次中存在已发行的obu,不能冲正!");
		obuInfoBatchModel.setStatus(StorageStatus.REVERSAL.getCode().toString());
		List<StorageObuInfoHis> lists = Lists.newArrayList();
		List<StorageObuInfo> storageObuInfos = storageObuInfoRepo.ListByobuIds(obuInfoBatchModel.getStartId(), obuInfoBatchModel.getEndId());
		if(storageObuInfos.size() !=0)
			throw new ManagerException("该库存批次obu中存在已发行的obu，不能冲正!");
		List<StorageObuInfo> storageObuInfo = storageObuInfoRepo.ListByobuId(obuInfoBatchModel.getStartId(), obuInfoBatchModel.getEndId());
		if(storageObuInfo.size() ==0||storageObuInfo.size()!=obuInfoBatchModel.getInboundNum())
			throw new ManagerException("该库存批次obu中存在已被调拨,发行等原因不是入库状态的obu，不能冲正!");
		for(int i = 0;i<storageObuInfo.size();i++) {
			StorageObuInfoHis soi = new StorageObuInfoHis();
			BeanUtils.copyProperties(storageObuInfo.get(i), soi);
			lists.add(soi);
		}
		try {
			storageObuInfoHisRepo.persistAll(lists);
			storageObuInfoRepo.deleteAll(storageObuInfo);
			storageObuinfoBatchRepo.save(obuInfoBatchModel);
		} catch (Exception e) {
			throw new ManagerException("冲正失败!");
		}
	}

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
