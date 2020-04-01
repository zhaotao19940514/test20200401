package cn.com.taiji.css.manager.issuetranscation;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.issuetranscation.IcbcContractRequest;
import cn.com.taiji.css.model.ocx.PosCommandRequest;
import cn.com.taiji.qtk.entity.CardInfo;

public interface IcbcContractManager {
	Pagination page(IcbcContractRequest request) throws ManagerException;
	
	void save(PosCommandRequest requestModel,HttpServletRequest request);

	CardInfo findCardInfo(String id);

	void update(String split);
}
