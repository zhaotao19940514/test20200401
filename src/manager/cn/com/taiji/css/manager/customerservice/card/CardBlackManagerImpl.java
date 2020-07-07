package cn.com.taiji.css.manager.customerservice.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.card.CardBlackRequest;
import cn.com.taiji.qtk.entity.BlackCard;
import cn.com.taiji.qtk.entity.BlackCardHis;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.BlackCardHisRepo;
import cn.com.taiji.qtk.repo.jpa.BlackCardRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;

@Service
public class CardBlackManagerImpl extends AbstractDsiCommManager implements CardBlackManager {

	@Autowired
	private BlackCardRepo blackCardRepo;
	@Autowired
	private BlackCardHisRepo blackCardHisRepo;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Override
	public List<BlackCard> queryPage(CardBlackRequest queryModel, User user) throws ManagerException {
		queryModel.validate();
		String cardId;
		if (queryModel.getCardId()!=null) {
			cardId=queryModel.getCardId();
		}else {
			CardInfo cardInfo = cardInfoRepo.findByVehicleId(queryModel.getVehicleId());
			cardId=cardInfo.getCardId();
		}
		return blackCardRepo.findListByCardId(cardId);
	}
	@Override
	public List<BlackCardHis> queryPageHis(CardBlackRequest queryModel, User loginUser)
			throws ManagerException {
		
		queryModel.validate();
		String cardId;
		if (queryModel.getCardId()!=null) {
			cardId=queryModel.getCardId();
		}else {
			CardInfo cardInfo = cardInfoRepo.findByVehicleId(queryModel.getVehicleId());
			cardId=cardInfo.getCardId();
		}
		return blackCardHisRepo.findListByCardId(cardId);
	}
	@Override
	public List<List<String>> queryYgzLog(String sTime,String eTime,String cardId,String status, User loginUser) throws ManagerException {
		List<Object[]> findLog = blackCardRepo.findLog(sTime.substring(0, 10), eTime.substring(0, 10), cardId,status);
		List<List<String>> logs=Lists.newArrayList();
		if(findLog.size()>-1) {
			for(Object[] q:findLog) {
				List<String> loggerStrings =Lists.newArrayList();
				loggerStrings.add(q[0].toString());//id
				loggerStrings.add(q[1].toString());//SEND_TIME
				loggerStrings.add(q[2].toString());//RESPONSE_JSON
				loggerStrings.add(q[3].toString());//EXCEPTION_TRACE
				logs.add(loggerStrings);
			}
		}
		return logs;
	}

}
