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
import cn.com.taiji.css.model.customerservice.card.LossRequest;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
/**
 * @ClassName LossManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Service
public class LossManagerImpl extends AbstractDsiCommManager implements LossManager{
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Override
	public LargePagination<CardInfo> queryPage(LossRequest queryModel,User user) throws ManagerException {
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

	@Override
	public CardStatusChangeResponse doLossCard(String id,User user) throws Exception{
		
		CardInfoQueryRequest cardInfoQueryRequest =  new CardInfoQueryRequest();
		CardInfoQueryResponse cardInfoQueryResponse =new CardInfoQueryResponse();
		super.commSet(cardInfoQueryRequest,user);
		cardInfoQueryRequest.setCardId(id);
		
		CardStatusChangeRequest cardStatusChangeRequest = new CardStatusChangeRequest();
		CardStatusChangeResponse cardStatusChangeResponse = new CardStatusChangeResponse();
		super.commSet(cardStatusChangeRequest,user);
		cardStatusChangeRequest.setCardId(id);
		cardStatusChangeRequest.setStatus(6);
		try {
			cardInfoQueryResponse = inqueryBinService.cardInfoQuery(cardInfoQueryRequest);
		} catch (Exception e) {
			cardStatusChangeResponse.setStatus(0);
			cardStatusChangeResponse.setMessage("接口调用失败,请联系管理员.");
			return cardStatusChangeResponse;
		}
			if(cardInfoQueryResponse.getStatus()==1) {
				int status = cardInfoQueryResponse.getCardStatus();
				//判断卡状态是否为“正常=1”、“无卡挂起=3”、“有卡挂起=2”
					if(status==1||status==2||status==3) {
						cardStatusChangeResponse =  maintenanceBinService.cardStatusChange(cardStatusChangeRequest);
					}else {
						cardStatusChangeResponse.setMessage("此卡不能办理挂失业务,正常或挂起状态才能办理挂失。");
					}
			}else {
				cardStatusChangeResponse.setMessage("未查询到该卡信息,请检查此卡是否已开卡。");
			}
			
			return cardStatusChangeResponse;
	}

	@Override
	public CardInfo findById(String id) 
	{
		return cardInfoRepo.findByCardId(id);
	}
	
	
	public boolean querycheck(LossRequest queryModel, User user) throws ManagerException {

		String agencyId = user.getStaff().getServiceHall().getAgencyId();
		CardInfo cardInfo = null;
		List<CardInfo> cardList = agencyCheck(queryModel,user);
		if(cardList.size()!=0) {
			cardInfo = agencyCheck(queryModel,user).get(0);
		}
		if (null != cardInfo) {
			if (!agencyId.equals(cardInfo.getAgencyId())) {
				// 建行要求52010102002
				if ("52010102018".equals(agencyId) && "52010102002".equals(cardInfo.getAgencyId())) {
					return true;
				} else if ("52010102002".equals(agencyId) && "52010102018".equals(cardInfo.getAgencyId())) {
					return true;
				} else {
					throw new ManagerException("该卡不能在该渠道进行办理");
				}
			} else {
				
				return true;
			}
		}
		return false;
	}

	private List<CardInfo> agencyCheck(LossRequest queryModel, User user) throws ManagerException {
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
				boolean falg =false;
				for(CardInfo info:cardList) {
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

	

}

