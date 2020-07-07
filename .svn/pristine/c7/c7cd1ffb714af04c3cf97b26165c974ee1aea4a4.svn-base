
package cn.com.taiji.css.manager.customerservice.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.report.RegionStatisticsModel;
import cn.com.taiji.qtk.repo.jpa.AccountTradeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.ChannelAccountBalanceRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.FundSerialDetailRepo;
import cn.com.taiji.qtk.repo.jpa.PaymentBackDetailRepo;
import cn.com.taiji.qtk.repo.jpa.RegionRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;


@Service
public class ChannelTradeManagerImpl extends AbstractDsiCommManager implements ChannelTradeManager{

	@Autowired
	private ChannelAccountBalanceRepo chanRepo;
	
	
	@Override
	public List<RegionStatisticsModel> queryPage(RegionStatisticsModel queryModel, HttpServletRequest request) throws ManagerException {
		
		List<RegionStatisticsModel> modelList = Lists.newArrayList();
		String beforeDate = queryModel.getStartDate();
		String afterDate = queryModel.getEndDate();
		List<Object[]> objList = chanRepo.listByHandleDate(beforeDate,afterDate,queryModel.getType());
		for(Object[] o:objList){
			RegionStatisticsModel mo = new RegionStatisticsModel();
			mo.setRegionName(o[0].toString());
			mo.setAmount(Long.parseLong(o[1].toString()));
			modelList.add(mo);
		}
		return modelList;
	}
	
}

