/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.card;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.qtk.entity.Card4XSection;
import cn.com.taiji.qtk.repo.jpa.Card4XSectionRepo;

/**
 * @ClassName Card4XCheckFromPcManagerImpl.java
 * @author fxd
 * @Description 
 * @date2019年08月19日
 */
@Service
public class Card4XCheckFromPcManagerImpl extends AbstractDsiCommManager implements Card4XCheckFromPcManager{

	@Autowired
	private Card4XSectionRepo card4XSectionRepo;
	@Override
	public boolean check4X(String cardId) throws ManagerException {
		if(!StringTools.hasText(cardId)) throw new ManagerException("卡号不能为空");
		if(!cardId.matches("\\d+") || cardId.length()!=20) {
			throw new ManagerException(cardId+"卡号异常,不为20位纯数字");
		}
		Long cardIdL=Long.valueOf(cardId.substring(4, 16));
		List<Card4XSection> listCardId = card4XSectionRepo.listCardId();
		if(listCardId.size()>0) {
			for(Card4XSection card4XSection : listCardId) {
				String startId=card4XSection.getStartId();
				String endId=card4XSection.getEndId();
				Long stCardIdL=Long.valueOf(startId.substring(4, 20));
				Long edCardIdL=Long.valueOf(endId.substring(4, 20));
				if(cardIdL>=stCardIdL && cardIdL<=edCardIdL) {
					System.out.println("stCardIdL: "+stCardIdL);
					System.out.println("edCardIdL: "+edCardIdL);
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println("52011640230201131091".substring(4, 20));
	}
	
	
}
