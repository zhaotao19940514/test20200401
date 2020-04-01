package cn.com.taiji.css.model.customerservice.finance;

import java.util.List;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.BaseModel;

public class AgencyIdSkipPairModel extends BaseModel {
	private String agencyIdFirst;
	private String agencyIdSecond;
	public AgencyIdSkipPairModel(String agencyIdFirst, String agencyIdSecond) {
		super();
		this.agencyIdFirst = agencyIdFirst;
		this.agencyIdSecond = agencyIdSecond;
	}
	
	private static List<AgencyIdSkipPairModel> getSkipPairList(){
		List<AgencyIdSkipPairModel> models = Lists.newArrayList();
		//中国建设银行贵州省分行   52010102018
		//建行					   52010102002
		models.add(new AgencyIdSkipPairModel("52010102018","52010102002"));
		return models;
	}
	
	//由于某些合作机构有两个渠道号,需要做特殊的渠道校验
	public static boolean skipAgencyId(String targetAgencyId1,String targetAgencyId2) throws ManagerException {
		if(targetAgencyId1==null || targetAgencyId2==null) {
			throw new ManagerException("该卡或OBU无合作机构编号或当前登录账号无合作机构编号");
		}
		List<AgencyIdSkipPairModel> skipPairList = getSkipPairList();
		boolean flag = false;
		for (AgencyIdSkipPairModel model : skipPairList) {
			flag = (model.getAgencyIdFirst().equals(targetAgencyId1) && model.getAgencyIdSecond().equals(targetAgencyId2))
					|| (model.getAgencyIdFirst().equals(targetAgencyId2) && model.getAgencyIdSecond().equals(targetAgencyId1));
			if(flag) break;
		}
		return flag;
	}
	

	public String getAgencyIdFirst() {
		return agencyIdFirst;
	}

	public void setAgencyIdFirst(String agencyIdFirst) {
		this.agencyIdFirst = agencyIdFirst;
	}

	public String getAgencyIdSecond() {
		return agencyIdSecond;
	}

	public void setAgencyIdSecond(String agencyIdSecond) {
		this.agencyIdSecond = agencyIdSecond;
	}
	
	
}
