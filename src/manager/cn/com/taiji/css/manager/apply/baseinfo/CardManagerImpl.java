/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.apply.baseinfo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.cardobuquery.CardRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryResponse;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CardInfoTemporary;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoTemporaryRepo;

/**
 * @ClassName RechargeManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:38
 * @E_mail yaonanlin@163.com
 */
@Service
public class CardManagerImpl extends AbstractDsiCommManager implements CardManager{
	@Autowired
	private AgencyRepo agencyRepo;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private CardInfoTemporaryRepo cardInfoTemporaryRepo;
	
	@Override
	public LargePagination<CardInfo> queryPage(CardRequest req) {
		if(StringTools.hasText(req.getCustomerId()) || StringTools.hasText(req.getVehicleId()) 
				|| StringTools.hasText(req.getCardId()) || req.getCardType() != null 
				|| req.getStatus() != null || StringTools.hasText(req.getVehiclePlate()) 
				|| req.getVehiclePlateColor() != null
				) {
			return cardInfoRepo.largePage(req);
		}else {
			return null;
		}
	}

	@Override
	public List<Agency> queryAllAgency() {
		return agencyRepo.findAll();
	}

	@Override
	public CardInfo findByCardId(String cardId) {
		return cardInfoRepo.findByCardId(cardId);
	}

	@Override
	public CardInfoQueryResponse findByCardIdPort(String cardId, User user) throws ManagerException {
		CardInfoQueryRequest req = new CardInfoQueryRequest();
		CardInfoTemporary findByCardId = cardInfoTemporaryRepo.findByCardId(cardId);
		super.commSet(req,user);
		req.setCardId(cardId);
		CardInfoQueryResponse cardInfoQuery;
		try {
			cardInfoQuery = inqueryBinService.cardInfoQuery(req);
			if(findByCardId!=null && findByCardId.getAgencyId()!=null) {
				cardInfoQuery.setAgencyId(findByCardId.getAgencyId());
				if(findByCardId.getAgencyId().equals("52010188033")) {
					// 5201018803301030001  世纪恒通
					cardInfoQuery.setChannelId("5201018803301030001");
				}
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException(e.getMessage()+"---查询错误！");
		}
		return cardInfoQuery;
	}


}

