package cn.com.taiji.css.manager.apply.quickapply;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.apply.quickapply.VehicleInfoQuickQueryRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;
@Service("quickApplyManager")
public class QuickApplyManagerImpl extends AbstractDsiCommManager implements QuickApplyManager{

	@Autowired
	private VehicleInfoRepo vehicleInfoRepo;
	@Autowired
	private AgencyRepo agencyRepo;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private OBUInfoRepo obuInfoRepo;

	@Override
	public Pagination queryPage(VehicleInfoQuickQueryRequest req) {
		if(StringTools.hasText(req.getCustomerId())) {
			Pagination page = vehicleInfoRepo.page(req);
			List<VehicleInfo> vehicleInfos = page.getResult(VehicleInfo.class);
			if(vehicleInfos.size() == 0) return page;//未查到车辆信息,返回null
			Map<String,List<VehicleInfo>> vehMap = vehicleInfos.parallelStream().collect(Collectors.groupingBy(VehicleInfo::getVehicleId));
			List<String> vehicleIds = vehicleInfos.parallelStream().map(VehicleInfo::getVehicleId).collect(Collectors.toList());
			// 查卡
			List<CardInfo> cardInfos = cardInfoRepo.listByVehicleIdsCheck(vehicleIds);
			Map<String,List<CardInfo>> cardMap = cardInfos.parallelStream().collect(Collectors.groupingBy(CardInfo::getVehicleId));
			vehicleIds.forEach(id->{
				List<CardInfo> cardList = cardMap.get(id);
				vehMap.get(id).get(0).setHasCard((cardList!=null)&&(cardList.size()>0));
			});
			// 查obu
			List<OBUInfo> obuInfos = obuInfoRepo.listByVehicleIdsCheck(vehicleIds);
			Map<String,List<OBUInfo>> obuMap = obuInfos.parallelStream().collect(Collectors.groupingBy(OBUInfo::getVehicleId));
			vehicleIds.forEach(id->{
				List<OBUInfo> obuList = obuMap.get(id);
				vehMap.get(id).get(0).setHasObu(obuList!=null && obuList.size()>0);
			});
			List<VehicleInfo> pageResult = Lists.newArrayList();
			/*Collection<List<VehicleInfo>> values = vehMap.values();
			for (List<VehicleInfo> listVeh : values) {
				pageResult.addAll(listVeh);
			}*/
			for (int i = 0; i < vehicleInfos.size(); i++) {
				pageResult.add(vehMap.get(vehicleInfos.get(i).getVehicleId()).get(0));
			}
			page.setResult(pageResult);
			return page;
		}else {
			return null;
		}
	}

	@Override
	public Agency findByAgencyId(String channelId) {
		Agency agency = agencyRepo.findByAgencyId(channelId);
		return agency;
	}
	
}
