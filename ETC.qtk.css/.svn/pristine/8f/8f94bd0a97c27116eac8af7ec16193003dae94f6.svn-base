package cn.com.taiji.css.manager.administration.pkg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import cn.com.taiji.css.model.administration.pkg.AccountRequest;
import cn.com.taiji.css.model.administration.pkg.PackageColRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.Package;
import cn.com.taiji.qtk.entity.PackageServiceHall;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.PackageRepo;
import cn.com.taiji.qtk.repo.jpa.PackageServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;

@Service
public class AccountManagerImpl extends AbstractManager implements AccountManager {
    @Autowired
    private PackageRepo packageRepo;
    @Autowired
    private AgencyRepo agencyRepo;
    @Autowired
	private ServiceHallRepo serviceHallRepo;
    @Autowired
	private PackageServiceHallRepo packageServiceHallRepo;
	
    @Override
	public Pagination queryPage(AccountRequest queryModel) {		
		return packageRepo.page(queryModel);
	}
    
	@Transactional
	@Override
	public Package add(Package packageInfo) throws ManagerException {
		valid(packageInfo);
		if(packageInfo.getAgencyIds()==null||packageInfo.getAgencyIds().length==0)throw new ManagerException("请选择优惠套餐适配网点!");
		Package pi = packageRepo.findByPackageNum(packageInfo.getPackageNum());
		if(pi!=null)throw new ManagerException("套餐编号已存在");
		packageInfo.setCreateTime(Calendar.getInstance());       
		String[] agencyIds = packageInfo.getAgencyIds();
		List<String> agencyList =Arrays.asList(agencyIds);
		List<ServiceHall> allList = new ArrayList<>();
		int endIndex = 0;
		for (int i = 0; i < agencyList.size(); i += 1000) {
			endIndex = i + 1000 > agencyList.size() ? agencyList.size() : i + 1000;
			List<String> subList = agencyList.subList(i, endIndex);
			List<ServiceHall> serviceHallList = serviceHallRepo.listByAgentId(subList);
			allList.addAll(serviceHallList);
		}   
		List<String> collect = allList.parallelStream().map(o -> o.getName()).collect(Collectors.toList());		
		String serviceHalls =StringUtils.join(collect, ",");
		packageInfo.setServiceHallList(serviceHalls);
		packageInfo.setStartTime(parseTime(packageInfo.getStartTime()));
	    packageInfo.setEndTime(parseTime(packageInfo.getEndTime()));
		Package  p = packageRepo.save(packageInfo);
		List<PackageServiceHall> list = new ArrayList<>();
		for (String  agency : agencyIds) {

			PackageServiceHall psh = new PackageServiceHall();
			psh.setPackageId(p.getId());
			psh.setServiceHallId(agency);
			list.add(psh);			
		}
		packageServiceHallRepo.saveAll(list);
		return p;
	}    
	@Transactional
	@Override
	public Package update(Package packageInfo) throws ManagerException {
		valid(packageInfo);
        if(packageInfo.getAgencyIds()==null||packageInfo.getAgencyIds().length==0)throw new ManagerException("请选择优惠套餐适配网点!");
		Package p = packageRepo.findId(packageInfo.getId());
		String[] agencyIds = packageInfo.getAgencyIds();
		BeanUtils.copyProperties(packageInfo, p,null,"id");
		updatePackageServiceHall(packageInfo.getId(),agencyIds);		
		List<String> agencyList =Arrays.asList(agencyIds);
		List<ServiceHall> allList = new ArrayList<>();
		int endIndex = 0;
		for (int i = 0; i < agencyList.size(); i += 1000) {
			endIndex = i + 1000 > agencyList.size() ? agencyList.size() : i + 1000;
			List<String> subList = agencyList.subList(i, endIndex);
			List<ServiceHall> serviceHallList = serviceHallRepo.listByAgentId(subList);
			allList.addAll(serviceHallList);
		}    	
		List<String> collect = allList.parallelStream().map(o -> o.getName()).collect(Collectors.toList());	
		String serviceHalls =StringUtils.join(collect, ",");
		p.setServiceHallList(serviceHalls);
		p.setStartTime(parseTime(p.getStartTime()));
	    p.setEndTime(parseTime(p.getEndTime()));
		Package packageDetail =  packageRepo.save(p);
		return packageDetail;	
	}

    @Transactional
	private void updatePackageServiceHall(String packageId,String[] agencyIds) throws ManagerException {
		AssertUtil.allElementsHasValue(packageId);
		Package packageInfo = packageRepo.findId(packageId);
		if(packageInfo==null) throw new ManagerException("套餐不存在,可能已经被删除!");
		packageServiceHallRepo.deleteByPackage(packageId);
		for (String agencyId : agencyIds) {
			packageServiceHallRepo.save(new PackageServiceHall(packageId,agencyId));
		}
	}
	
	@Override
	public Package findId(String id) {
		return packageRepo.findId(id);
	}

	@Override
	public List<Package> findAll() {
		return packageRepo.findAll();
	}
	public void valid(Package packageInfo) throws ManagerException {
		MyViolationException mv = new MyViolationException();
        if(packageInfo.getPackageName()==null||packageInfo.getPackageName().isEmpty()) mv.addViolation("packageName", "套餐名为空");
        if(packageInfo.getPackageNum()==null||packageInfo.getPackageNum()<0) mv.addViolation("packageNum","套餐编号非法");
        if(packageInfo.getPackageState()==null)mv.addViolation("packageState", "套餐状态非法");
        if(packageInfo.getStartTime()==null||packageInfo.getStartTime().isEmpty())mv.addViolation("startTime", "生效效时间为空");
        if(packageInfo.getEndTime()==null||packageInfo.getEndTime().isEmpty())mv.addViolation("endTime", "失效时间为空");
        if(mv.hasViolation()) throw mv;        	
	}

	@Override
	public List<Package> findByServiceHallId(String serviceHallId) {	
		ServiceHall serviceHall = serviceHallRepo.findByServiceHallId(serviceHallId);
		List<PackageServiceHall> list = packageServiceHallRepo.findByServieHallId(serviceHall.getId());
		List<String> collect = list.parallelStream().map(o ->o.getPackageId()).collect(Collectors.toList());
		Date date = new Date();
		List<Package> enablePackage = new ArrayList<Package>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if(collect.size()>0) { 		
			List<Package> packageList = packageRepo.findByPackageId(collect);
			for (Package packageInfo : packageList) {
				 try {
					Date enableDate = sdf.parse(packageInfo.getEndTime());
					if(!date.after(enableDate)) enablePackage.add(packageInfo);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		return enablePackage;
		}
	  return new ArrayList<Package>();
	}

	@Override
	public List<ZTreeItem> getCurrentConf(String packageId) {
		List<ZTreeItem> rs = new ArrayList<ZTreeItem>();
		List<Agency> agencyList = agencyRepo.findAll();
		for (Agency agency : agencyList) {
			if(("52010102002").equals(agency.getAgencyId())||("52010102007").equals(agency.getAgencyId()))continue;
			if(agency.getFileDir()==null)continue;
			List<ServiceHall> serviceHallList = serviceHallRepo.list(new PackageColRequest(agency.getFileDir()));
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
				item.setChildren(children);
				rs.add(item);
			}
		}
		return rs;
	}
   
	private List<ZTreeItem> handleChildren(List<ServiceHall> res, String packageId) {
		List<ZTreeItem> list = new ArrayList<ZTreeItem>();
		List<PackageServiceHall> ipsList = packageServiceHallRepo.findByPackageId(packageId);
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
				for (PackageServiceHall ips : ipsList) {
					if(ips.getServiceHallId().equals(serviceHall.getId())) {
						item.setChecked(true);
					}
				}
			}
			list.add(item);
		}
		return list;
	}
	
	private String parseTime(String packageTime) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	     String parseTime = null;
	     try {
		    parseTime = format.format(sdf.parse(packageTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseTime;
	}
}
