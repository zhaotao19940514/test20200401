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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.model.customerservice.card.ReplaceRequest;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;

/**
 * @ClassName ReplaceManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Service
public class ReplaceManagerImpl extends AbstractManager implements ReplaceManager{

	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	
	@Override
	public LargePagination<CardInfo> queryPage(ReplaceRequest queryModel,User user) throws ManagerException {
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
	private List<CardInfo> agencyCheck(ReplaceRequest queryModel, User user) throws ManagerException {
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
						if(null!=info) {
							listCard.add(info);
						}
					}else {
						throw new ManagerException("当前渠道无权操作此卡");
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
	public CardInfo findById(@Valid String id) {
		return cardInfoRepo.findByCardId(id);
	}


}

