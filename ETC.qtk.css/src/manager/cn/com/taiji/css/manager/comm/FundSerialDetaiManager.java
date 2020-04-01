package cn.com.taiji.css.manager.comm;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.ServiceType;

public interface FundSerialDetaiManager {
	public void saveFundSerial(User user, ServiceType serviceType, ChargeType chargeType, Integer handleType, Long handleMoney) throws ManagerException;

	public void saveFundSerial(User user, ServiceType serviceType, ChargeType chargeType, Integer handleType,
			Long handleMoney, String cardId, String obuId, String vehicleId) throws ManagerException;

}
