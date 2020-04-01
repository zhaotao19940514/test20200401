
package cn.com.taiji.css.model.customerservice.finance;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.CardrechargefixManager;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;

public class CardrechargeCheckRequest extends CardrechargeRequest {
	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	@Autowired
	private CardrechargefixManager cardrechargefixManager;
	
	private String cardId;
	
	private Integer chargeType;
	
	public CardrechargeResponse validate(CardrechargeCheckRequest cardrechargeCheckRequest,HttpServletRequest request) throws ManagerException {
		MyViolationException mve = new MyViolationException();		
		if(cardId == null) mve.addViolation("cardId", "请检查是否读取或者输入卡号！");
		if(chargeType == null) mve.addViolation("chargeType", "请选择收费类型！");
		CardrechargeResponse cardrechargeResponse=new CardrechargeResponse();
		CardInfo cardInfo = cardInfoRepo.findByCardId(cardrechargeCheckRequest.getCardId());
		if(cardInfo==null) {
			cardrechargeResponse.setMessage("查无此卡信息");
			cardrechargeResponse.setStatus(-1);
			return cardrechargeResponse;
		}else if(cardrechargeCheckRequest.getChargeType()==null) {
			cardrechargeResponse.setMessage("请选择消费类型");
			cardrechargeResponse.setStatus(-1);
			return cardrechargeResponse;
		}else if(cardrechargeCheckRequest.getPaidAmount()+cardrechargeCheckRequest.getGiftAmount()>=5000000) {
			cardrechargeResponse.setMessage("单笔圈存交易不能大于或等于5万元!");
			cardrechargeResponse.setStatus(-1);
			return cardrechargeResponse;
		}
		if (mve.hasViolation()) throw mve;
		return cardrechargeResponse;
	}
	
}

