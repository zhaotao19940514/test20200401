package cn.com.taiji.css.manager.issuetranscation;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.ICBCContract;
import cn.com.taiji.css.entity.dict.OperationType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.model.issuetranscation.IcbcContractRequest;
import cn.com.taiji.css.model.ocx.PosCommandRequest;
import cn.com.taiji.css.repo.jpa.IcbcContractRepo;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
@Service
public class IcbcContractManagerImpl extends AbstractManager implements IcbcContractManager {

	@Autowired
	private IcbcContractRepo icbcContractRepo;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	@Override
	public Pagination page(IcbcContractRequest request) throws ManagerException {
		validate(request);
		return icbcContractRepo.page(request);
	}

	private void validate(IcbcContractRequest request) throws ManagerException {
		if(request.getEtcCardId() == null && request.getBankCardId() == null)
			throw new ManagerException("卡号和银行卡号最少填写一个");
	}

	@Override
	public void save(PosCommandRequest requestModel,HttpServletRequest request) {
		ICBCContract icbcContract = new ICBCContract();
		icbcContract.setCreateTime(LocalDateTime.now());
		icbcContract.setOpTime(LocalDateTime.now());
		icbcContract.setBankCardId(requestModel.getCardNo());
		icbcContract.setEtcCardId(requestModel.getEtcCardNo());
		if ("02".equals(requestModel.getTransType())) {
			icbcContract.setOpType(OperationType.BIND);			
		}else if ("03".equals(requestModel.getTransType())) {
			icbcContract.setOpType(OperationType.REMOVE);	
		}else{
			return;
		}
		icbcContract.setUserID(LoginHelper.getLoginUser(request).getId());
		icbcContract.setStaffId(LoginHelper.getLoginUser(request).getStaffId());
		icbcContract.setOpOrder(requestModel.getTransType());
		icbcContractRepo.save(icbcContract);
	}

	@Override
	public CardInfo findCardInfo(String id) {
		CardInfo cardInfo = cardInfoRepo.findByCardId(id);	
		return cardInfo;
	}

	@Override
	public void update(String split) {
		ICBCContract icbcContract = icbcContractRepo.opREsultIsNull();
		String[] split2 = split.split(",");
	    icbcContract.setOpResult(split2[11]);
	    icbcContractRepo.save(icbcContract);
	}

	
}
