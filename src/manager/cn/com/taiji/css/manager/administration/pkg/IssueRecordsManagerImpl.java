package cn.com.taiji.css.manager.administration.pkg;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.IssuePackagePayStatus;
import cn.com.taiji.css.manager.apply.quickapply.EquipmentIssueManager;
import cn.com.taiji.css.manager.comm.FundSerialDetaiManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.administration.pkg.IssuerecordsQueryRequest;
import cn.com.taiji.css.model.apply.customermanager.PackageTotalMoneyResponse;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.repo.jpa.CarIssuePackageInfoRepo;
import cn.com.taiji.qtk.repo.jpa.IssuePackageInfoRepo;

@Service
public class IssueRecordsManagerImpl extends AbstractManager implements IssueRecordsManager {
	
	@Autowired
	private IssuePackageInfoRepo issuePackageInfoRepo;
	@Autowired
	private CarIssuePackageInfoRepo carIssuePackageInfoRepo;
	@Autowired
	private EquipmentIssueManager equipmentIssueManager;
	@Autowired
	private FundSerialDetaiManager fundSerialDetaiManager;

	@Override
	public LargePagination<CarIssuePackageInfo> queryPage(IssuerecordsQueryRequest req) {
		LargePagination<CarIssuePackageInfo> largePage = carIssuePackageInfoRepo.largePage(req);
		return largePage;
	}

	@Override
	public CarIssuePackageInfo findById(String id) throws ManagerException {
		Optional<CarIssuePackageInfo> findById = carIssuePackageInfoRepo.findById(id);
		return findById.get();
	}

	@Override
	@Transactional(rollbackFor=ManagerException.class)
	public CarIssuePackageInfo verifyPay(User user, CarIssuePackageInfo packInfo) throws ManagerException {
		if(!StringTools.hasText(packInfo.getRemarks())) {
			throw new ManagerException("请录入备注信息！");
		}
		CarIssuePackageInfo carIssuePackageInfo = findById(packInfo.getId());
		if(carIssuePackageInfo == null) {
			throw new ManagerException("未查到对应的发行套餐记录！");
		}
		carIssuePackageInfo.setRemarks(packInfo.getRemarks());
		if(carIssuePackageInfo.getStatus() == IssuePackagePayStatus.PAY.getCode())
			throw new ManagerException("该套餐已成功支付！");
		if(carIssuePackageInfo.getStatus() == IssuePackagePayStatus.REPEAL.getCode())
			throw new ManagerException("该套餐已作废！");
		String serviceHallId = user.getStaff().getServiceHall().getServiceHallId();
		if(serviceHallId.equals("5201010600401140003")) 
			serviceHallId = carIssuePackageInfo.getServiceHallId();
		List<CarIssuePackageInfo> list = carIssuePackageInfoRepo.findByVehicleIdCreateTimeDesc(serviceHallId, carIssuePackageInfo.getVehicleId());
		if (list != null && list.size() > 0 && !list.get(0).getId().equals(carIssuePackageInfo.getId()))
			throw new ManagerException("只能确认支付该车在本网点的最新一条记录！");
		carIssuePackageInfo.setStatus(IssuePackagePayStatus.PAY.getCode());
		carIssuePackageInfo.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
		CarIssuePackageInfo info;
		try {
			info = carIssuePackageInfoRepo.save(carIssuePackageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("确认支付套餐费用失败！");
		}
		
		IssuePackageInfo p =issuePackageInfoRepo.findByPackageNum(carIssuePackageInfo.getPackageNum());
		PackageTotalMoneyResponse moneyPlayType = equipmentIssueManager.moneyPlayType(p);
		Double cash = moneyPlayType.getTotalCash()*100;
		Double pos = moneyPlayType.getTotalPos()*100;
		try {
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ISSUEPACKAGE, ChargeType.CASH, 0, cash.longValue() , null , null ,carIssuePackageInfo.getVehicleId());
			fundSerialDetaiManager.saveFundSerial(user, ServiceType.ISSUEPACKAGE, ChargeType.COMMONPOS, 0, pos.longValue() , null , null ,carIssuePackageInfo.getVehicleId());
		} catch (ManagerException e) {
			e.printStackTrace();
			throw new ManagerException("确认支付套餐费用失败！"+e.getMessage());
		}
		return info;
	}

	@Override
	public CarIssuePackageInfo verifyRepeal(User user, String id) throws ManagerException {
		CarIssuePackageInfo carIssuePackageInfo = findById(id);
		if(carIssuePackageInfo.getStatus() == IssuePackagePayStatus.PAY.getCode())
			throw new ManagerException("该套餐已成功支付！不能作废");
		if(carIssuePackageInfo.getStatus() == IssuePackagePayStatus.REPEAL.getCode())
			throw new ManagerException("该套餐已作废！");
		carIssuePackageInfo.setStatus(IssuePackagePayStatus.REPEAL.getCode());
		carIssuePackageInfo.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
		CarIssuePackageInfo info;
		try {
			info = carIssuePackageInfoRepo.save(carIssuePackageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("作废套餐失败！");
		}
		return info;
	}

}
