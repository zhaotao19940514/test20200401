/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.model.customerservice.card.UnhangRequest;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.HangWriteList;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.HangWriteListRepo;

/**
 * @ClassName UnhangManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Service
public class UnhangManagerImpl extends AbstractDsiCommManager implements UnhangManager{

	/* (non-Javadoc)
	 * @see cn.com.taiji.css.manager.customerservice.finance.RechargeManager#queryPage(cn.com.taiji.css.model.customerservice.finance.RechargeRequest)
	 */
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Autowired
	private HangWriteListRepo hangWriteListRepo;
	
	@Override
	public LargePagination<CardInfo> queryPage(UnhangRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		List<CardInfo> listCard = new ArrayList<CardInfo>();
		listCard = agencyCheck(queryModel,user);
		LargePagination<CardInfo> list = new LargePagination<CardInfo>();
		if(listCard.size()==0) {
			list=null;
		}else {
			list.setResult(listCard);
		}
		return list;
	}
	
	private List<CardInfo> agencyCheck(UnhangRequest queryModel, User user) throws ManagerException {
		List<CardInfo> listCard = new ArrayList<CardInfo>();
		CardInfo cardInfo = null;
		if (StringTools.hasText(queryModel.getCardId())) {
			cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
			boolean falg =false;
			try {
				falg = agencyVarifyManager.varifyAgency(user, cardInfo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ManagerException("渠道校验失败："+e.getMessage());
			}
			if(falg) {
				if(null!=cardInfo) {
					listCard.add(cardInfo);
				}
			}else {
				throw new ManagerException("当前渠道无权操作此卡");
			}
		}else if (StringTools.hasText(queryModel.getVehicleId())) {
			List<CardInfo> cardList = cardInfoRepo.listByVehicleId(queryModel.getVehicleId());
			if(cardList.size()>0) {
				for(CardInfo info:cardList) {
					boolean falg =false;
					try {
						falg = agencyVarifyManager.varifyAgency(user, info);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ManagerException("渠道校验失败："+e.getMessage());
					}
					if(falg) {
						listCard.add(info);
					}
				}
				if(listCard.size()==0) {
					throw new ManagerException("当前渠道无权操作此卡");
				}
			}
			
		}
		return listCard;

	}
	@Override
	public CardStatusChangeResponse doUnHangCard(String cardId, User user) throws Exception {
		CardInfoQueryRequest cardInfoQueryRequest =  new CardInfoQueryRequest();
		super.commSet(cardInfoQueryRequest,user);
		cardInfoQueryRequest.setCardId(cardId);
		CardStatusChangeRequest cardStatusChangeRequest = new CardStatusChangeRequest();
		CardStatusChangeResponse cardStatusChangeResponse = new CardStatusChangeResponse();
		super.commSet(cardStatusChangeRequest,user);
		cardStatusChangeRequest.setCardId(cardId);
		cardStatusChangeRequest.setStatus(1);
		cardStatusChangeResponse = maintenanceBinService.cardStatusChange(cardStatusChangeRequest);
		if(cardStatusChangeResponse!=null  && cardStatusChangeResponse.getStatus()==1) {
			HangWriteList  hangWriteList = hangWriteListRepo.findHangWriteListByCardId(cardStatusChangeRequest.getCardId());
			if(hangWriteList==null) {  // 如果库内没有挂起反白队列则新增，有则跳过
				HangWriteList hangWrite =new HangWriteList();
				hangWrite.setCardId(cardStatusChangeRequest.getCardId());
				hangWrite.setStatus(0);
				try {
					hangWriteListRepo.save(hangWrite);
				} catch (Exception e) {
					e.printStackTrace();
					cardStatusChangeResponse.setStatus(-1);
					cardStatusChangeResponse.setMessage("卡编号:"+cardStatusChangeRequest.getCardId()+"反白队列入库失败！");
				}
			}
		}
		return cardStatusChangeResponse;
	}

	
	
	@Override
	public CardInfo findById(String cardId) {
		// TODO Auto-generated method stub
		return cardInfoRepo.findByCardId(cardId);
	}


}

