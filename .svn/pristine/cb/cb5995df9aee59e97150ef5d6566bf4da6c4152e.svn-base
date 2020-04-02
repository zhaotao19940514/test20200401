package cn.com.taiji.css.manager.customerservice.finance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.PosReverse;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.repo.jpa.PosReverseRepo;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CssPosTradeDetail;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CssPosTradeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;
@Service
public class PosReverseManagerImpl extends AbstractManager implements PosReverseManager {

	@Autowired
	private CssPosTradeDetailRepo posRepo;
	@Autowired
	private PosReverseRepo prRepo;
	@Autowired
	private CardInfoRepo cardRepo;
	@Autowired
	private OBUInfoRepo obuRepo;
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public List<CssPosTradeDetail> findByReferNo(Integer referNo,User user) throws ManagerException {
		String date = LocalDate.now().toString().replace("-", "");
		//String date = "20190423";
		List<CssPosTradeDetail> pos = posRepo.findByReferNo(referNo,date);
		CssPosTradeDetail cssPosTradeDetail = pos.get(0);
		User findById = userRepo.findById(cssPosTradeDetail.getOperatorId()).get();
		if (!(findById.getStaff().getServiceHallId()).equals(user.getStaff().getServiceHallId())) {
			return null;
		}
		if (cssPosTradeDetail.getVehiclePlate() != null) {
			CardInfo findByVehicleIdAndStatus = cardRepo.findByVehicleIdAndStatus(cssPosTradeDetail.getVehiclePlate()+"_"+cssPosTradeDetail.getVehicleColor());
			if (findByVehicleIdAndStatus != null) {
				return null;
			}
			OBUInfo findByVehicleId = obuRepo.findByVehicleId(cssPosTradeDetail.getVehiclePlate()+"_"+cssPosTradeDetail.getVehicleColor());
			if (findByVehicleId != null) {
				return null;
			}
		}
		if (cssPosTradeDetail.getObuId() != null) {
			OBUInfo findInstallConfirmOBU = obuRepo.findInstallConfirmOBU(cssPosTradeDetail.getObuId());
			if (findInstallConfirmOBU != null) {
				return null;
			}
		}
		if (cssPosTradeDetail.getCardId() != null) {
			CardInfo findByCardId = cardRepo.findByCardId(cssPosTradeDetail.getCardId());
			if (findByCardId != null && findByCardId.getStatus() == 1) {
				return null;
			}
		}
		PosReverse findByRefernoAndTime = prRepo.findByRefernoAndTime(cssPosTradeDetail.getReferNo().toString(), LocalDate.now());
		if (findByRefernoAndTime != null) {
			cssPosTradeDetail.setCreateTime(findByRefernoAndTime.getCreateTime().toString());
			//使用iscompleted储存是否已被撤销（默认为true。false：已被撤销）
			cssPosTradeDetail.setIsCompleted(false);
			//使用returnCode储存该次撤销的操作人工号
			cssPosTradeDetail.setReturnCode(findByRefernoAndTime.getOperator());
		}
		ArrayList<CssPosTradeDetail> p = Lists.newArrayList();
		p.add(cssPosTradeDetail);
		return p;
	}

	
	@Override
	public void save(String split,User user) {
		PosReverse posReverse = new PosReverse();
		String[] split2 = split.split(",");
		posReverse.setAmount(Integer.valueOf(split2[5]));
		posReverse.setReferno(split2[20]);
		posReverse.setResult(split2[11]);
		posReverse.setMessage(split);
		posReverse.setCreateTime(LocalDate.now());
		posReverse.setOperator(user.getLoginName());
		prRepo.save(posReverse);
	}
	


	
}
