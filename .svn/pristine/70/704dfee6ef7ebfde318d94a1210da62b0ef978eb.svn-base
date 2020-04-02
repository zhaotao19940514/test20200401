package cn.com.taiji.css.manager.administration.pkg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.pkg.IssuePackageColRequest;
import cn.com.taiji.css.model.administration.pkg.IssueRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.entity.IssupePackageServiceHall;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.dict.VehicleType;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.IssuePackageInfoRepo;
import cn.com.taiji.qtk.repo.jpa.IssuePackageServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;

@Service
public class IssueManagerImpl extends AbstractManager implements IssueManager {
	@Autowired
	private IssuePackageInfoRepo issuePackageInfoRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private AgencyRepo agencyRepo;	
	@Autowired
	private IssuePackageServiceHallRepo issuePackageServiceHallRepo;

	@Override
	public Pagination queryPage(IssueRequest queryModel) {
		return issuePackageInfoRepo.page(queryModel);
	}

	@Override
	@Transactional
	public IssuePackageInfo add(IssuePackageInfo issuePackageInfo) throws ManagerException {
		valid(issuePackageInfo);
		if (issuePackageInfo.getRechargeMoney() == null || issuePackageInfo.getObuCostType() == null
				|| issuePackageInfo.getCardCostType() == null)
			throw new ManagerException("请选择刷卡类型!");
		if (issuePackageInfo.getAgencyIds()==null||issuePackageInfo.getAgencyIds().length==0)throw new ManagerException("请选择发行套餐适配网点!");
		issuePackageInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		String[] agencyIds = issuePackageInfo.getAgencyIds();
		IssuePackageInfo ipi = issuePackageInfoRepo.findByPackageNum(issuePackageInfo.getPackageNum());
		if (ipi != null)
			throw new ManagerException("套餐名或套餐编号已存在");
		IssuePackageInfo packageInfo = issuePackageInfoRepo.save(issuePackageInfo);
		List<IssupePackageServiceHall> list = new ArrayList<>();
		for (String str : agencyIds) {
			IssupePackageServiceHall ips = new IssupePackageServiceHall();
			ips.setIssuePackageId(packageInfo.getId());
			ips.setServiceHallId(str);
			list.add(ips);
		}
		issuePackageServiceHallRepo.saveAll(list);
		packageInfo.setAgencyIds(null);
		return packageInfo;
	}



	@Transactional
	@Override
	public IssuePackageInfo update(IssuePackageInfo issuePackageInfo, String userName) throws ManagerException {
		valid(issuePackageInfo);
		if (issuePackageInfo.getRechargeMoney() == null || issuePackageInfo.getObuCostType() == null
				|| issuePackageInfo.getCardCostType() == null)
			throw new ManagerException("请选择刷卡类型!");
		if (issuePackageInfo.getAgencyIds()==null||issuePackageInfo.getAgencyIds().length==0)throw new ManagerException("请选择发行套餐适配网点!");
		IssuePackageInfo ipi = issuePackageInfoRepo.findId(issuePackageInfo.getId());
		logger.info(ipi.toString());
		String[] agencyIds = issuePackageInfo.getAgencyIds();
		BeanUtils.copyProperties(issuePackageInfo, ipi, null, "id");
		ipi.setCreatePerson(userName);
		ipi.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		updateIssuePackageServiceHall(issuePackageInfo.getId(), agencyIds);       
		IssuePackageInfo p = issuePackageInfoRepo.save(ipi);
		p.setAgencyIds(null);
		return p;
	}
    @Transactional
	private void updateIssuePackageServiceHall(String packageId, String[] agencyIds) throws ManagerException {
		AssertUtil.allElementsHasValue(packageId, agencyIds);
		IssuePackageInfo ipi = issuePackageInfoRepo.findId(packageId);
		if (ipi == null)
			throw new ManagerException("发行套餐不存在,可能已经被删除!");
		issuePackageServiceHallRepo.deleteByIssuePackageId(packageId);
		for (String agencyId : agencyIds) {
			if (issuePackageServiceHallRepo.findByAgencyAndPackage(agencyId, packageId) != null)
				continue;
			issuePackageServiceHallRepo.save(new IssupePackageServiceHall(packageId, agencyId));
		}
	}

	@Override
	public IssuePackageInfo findId(String id) {
		return issuePackageInfoRepo.findId(id);
	}

	public void valid(IssuePackageInfo issuePackageInfo) throws ManagerException {
		MyViolationException mv = new MyViolationException();
		if (issuePackageInfo.getPackageName() == null || issuePackageInfo.getPackageName().isEmpty())
			mv.addViolation("packageName", "套餐名为空");
		if (issuePackageInfo.getPackageNum() == null || issuePackageInfo.getPackageNum().isEmpty())
			mv.addViolation("packageNum", "套餐编号非法");
		if (issuePackageInfo.getRechargeMoney() == null)
			mv.addViolation("rechargeMoney", "充值金额为空");
		if (issuePackageInfo.getObuCost() == null)
			mv.addViolation("obuCost", "obu费用为空");
		if (issuePackageInfo.getCardCost() == null)
			mv.addViolation("cardCost", "卡费用为空");
		if (issuePackageInfo.getEnableTime() == null || issuePackageInfo.getEnableTime().isEmpty())
			mv.addViolation("enableTime", "生效时间为空");
		if (issuePackageInfo.getExpireTime() == null || issuePackageInfo.getExpireTime().isEmpty())
			mv.addViolation("expireTime", "失效时间为空");				
		if (mv.hasViolation())throw mv;			
	}

	@Override
	public List<IssuePackageInfo> findAll() {
		return issuePackageInfoRepo.findAll();
	}

	@Override
	public List<IssuePackageInfo> findByServiceHallId(String serviceHallId,Integer type) {
		ServiceHall serviceHall  =serviceHallRepo.findByServiceHallId(serviceHallId);
		List<IssupePackageServiceHall> ips = issuePackageServiceHallRepo.findByServiceId(serviceHall.getId());
		List<String> collect = ips.parallelStream().map(o -> o.getIssuePackageId()).collect(Collectors.toList());			
		boolean isCar = VehicleType.isCar(type);
		String carType = null;
		if(isCar) {
			carType="客车";
		}else{
			carType="货车";						
		}
		if(collect.size()>0&&carType!=null) {			
			List<IssuePackageInfo> ipiList = issuePackageInfoRepo.ListById(collect, carType);
		    List<IssuePackageInfo> enablePackage= new ArrayList<IssuePackageInfo>();
			Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (IssuePackageInfo issuePackageInfo : ipiList) {
			     	try {
						Date ipiDate = sdf.parse(issuePackageInfo.getExpireTime());
					    if(!date.after(ipiDate))enablePackage.add(issuePackageInfo);
			     	} catch (ParseException e) {
						e.printStackTrace();
					}   	
			}
         return enablePackage;			
		}
	    return new ArrayList<IssuePackageInfo>();
	}
	
	@Override
	public List<ZTreeItem> getCurrentConf(String packageId) {
		List<ZTreeItem> rs = new ArrayList<ZTreeItem>();
		List<Agency> agencyList = agencyRepo.findAll();
		for (Agency agency : agencyList) {
			if(("52010102002").equals(agency.getAgencyId())||("52010102007").equals(agency.getAgencyId()))continue;
			if(agency.getFileDir()==null)continue;
			List<ServiceHall> serviceHallList = serviceHallRepo.list(new IssuePackageColRequest(agency.getFileDir()));
			if (serviceHallList.size() > 0) {
				ZTreeItem item = new ZTreeItem();
				item.setId(agency.getFileDir());
				item.setName(agency.getName());
				List<ZTreeItem> children = handleChildren(serviceHallList, packageId);
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

	private List<ZTreeItem> handleChildren(List<ServiceHall> res, String packageId) {
		List<ZTreeItem> list = new ArrayList<ZTreeItem>();
		List<IssupePackageServiceHall> ipsList = issuePackageServiceHallRepo.findByIssuePackageId(packageId);
		for (ServiceHall serviceHall : res) {
			ZTreeItem item = new ZTreeItem();
			item.setId(serviceHall.getId());
			item.setName(serviceHall.getName());			
			//IssupePackageServiceHall ips = issuePackageServiceHallRepo.findByAgencyAndPackage(serviceHall.getId(),
//					packageId);
//			if (ips != null)
//				item.setChecked(true);
		/*	item.setChildren(handleChildren(			
					serviceHallRepo.list(new IssuePackageListRequest(serviceHall.getId(), packageId)), packageId));*/
			if(ipsList.size()>0) {
				for (IssupePackageServiceHall ips : ipsList) {
					if(ips.getServiceHallId().equals(serviceHall.getId())) {
						item.setChecked(true);
					}
				}
			}
			list.add(item);
		}
		return list;
	}

	@Override
	public List<IssuePackageInfo> listByEmergency(List<String> packageNums) {
		List<IssuePackageInfo> ipiList = issuePackageInfoRepo.listByPackageNums(packageNums);
	    List<IssuePackageInfo> enablePackage= new ArrayList<IssuePackageInfo>();
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (IssuePackageInfo issuePackageInfo : ipiList) {
		     	try {
					Date ipiDate = sdf.parse(issuePackageInfo.getExpireTime());
				    if(!date.after(ipiDate))enablePackage.add(issuePackageInfo);
		     	} catch (ParseException e) {
					e.printStackTrace();
				}   	
		}
		return enablePackage;	
	}
}
