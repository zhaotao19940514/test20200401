package cn.com.taiji.css.manager.comm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.qtk.entity.FundSerialDetail;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.ServiceType;
import cn.com.taiji.qtk.repo.jpa.FundSerialDetailRepo;

@Service
public class FundSerialDetaiManagerImpl extends AbstractManager implements FundSerialDetaiManager{
    @Autowired
    private FundSerialDetailRepo fundSerialDetailRepo;
	
	@Override
	@Transactional
	public void saveFundSerial(User user, ServiceType serviceType, ChargeType chargeType,Integer handleType, Long handleMoney) throws ManagerException {
		FundSerialDetail fundSerialDetail = new FundSerialDetail();
		fundSerialDetail.setServiceHallId(user.getStaff().getServiceHall().getServiceHallId());
		fundSerialDetail.setStaffId(user.getStaff().getStaffId());
		fundSerialDetail.setStaffName(user.getName());
		fundSerialDetail.setAgencyId(user.getStaff().getServiceHall().getAgencyId());
		fundSerialDetail.setServiceType(serviceType);
		fundSerialDetail.setChargeType(chargeType);
		fundSerialDetail.setHandleType(handleType);
		fundSerialDetail.setHandleMoney(handleMoney);
		fundSerialDetail.setHandleDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		fundSerialDetail.setHandleTime(Calendar.getInstance());
		try {
			fundSerialDetailRepo.save(fundSerialDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("保存资金流水明细失败!");
		}		
	}
	
	@Override
	@Transactional
	public void saveFundSerial(User user, ServiceType serviceType, ChargeType chargeType,Integer handleType, Long handleMoney,String cardId,String obuId,String vehicleId) throws ManagerException {
		FundSerialDetail fundSerialDetail = new FundSerialDetail();
		fundSerialDetail.setServiceHallId(user.getStaff().getServiceHall().getServiceHallId());
		fundSerialDetail.setStaffId(user.getStaff().getStaffId());
		fundSerialDetail.setStaffName(user.getName());
		fundSerialDetail.setAgencyId(user.getStaff().getServiceHall().getAgencyId());
		fundSerialDetail.setServiceType(serviceType);
		fundSerialDetail.setChargeType(chargeType);
		fundSerialDetail.setHandleType(handleType);
		fundSerialDetail.setHandleMoney(handleMoney);
		fundSerialDetail.setHandleDate(new SimpleDateFormat("yyyMMdd").format(new Date()));
		fundSerialDetail.setHandleTime(Calendar.getInstance());
		fundSerialDetail.setCardId(cardId);
		fundSerialDetail.setObuId(obuId);
		fundSerialDetail.setVehicleId(vehicleId);
		try {
			fundSerialDetailRepo.save(fundSerialDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("保存资金流水明细失败!");
		}		
	}

}
