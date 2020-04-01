/**
 * @Title RechargeManagerImpl.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:38
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.obu;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.qtk.entity.Card4XSection;
import cn.com.taiji.qtk.entity.Obu4XSection;
import cn.com.taiji.qtk.repo.jpa.Card4XSectionRepo;
import cn.com.taiji.qtk.repo.jpa.Obu4XSectionRepo;

/**
 * @ClassName Card4XCheckFromPcManagerImpl.java
 * @author fxd
 * @Description 
 * @date2019年08月19日
 */
@Service
public class Obu4XCheckFromPcManagerImpl extends AbstractDsiCommManager implements Obu4XCheckFromPcManager{

	@Autowired
	private Obu4XSectionRepo obu4XSectionRepo;
	@Override
	public boolean check4X(String obuId) throws ManagerException {
		if(!StringTools.hasText(obuId)) throw new ManagerException("OBU号不能为空");
		if(!obuId.matches("\\d+") || obuId.length()!=16) {
			throw new ManagerException(obuId+"OBU号异常,不为16位纯数字");
		}
		Long cardIdL=Long.valueOf(obuId);
		List<Obu4XSection> listObuId = obu4XSectionRepo.listObuId();
		if(listObuId.size()>0) {
			for(Obu4XSection obu4XSection : listObuId) {
				String startId=obu4XSection.getStartId();
				String endId=obu4XSection.getEndId();
				Long stObuIdL=Long.valueOf(startId);
				Long edObuIdL=Long.valueOf(endId);
				if(cardIdL>=stObuIdL && cardIdL<=edObuIdL) {
					return true;
				}
			}
		}
		return false;
	}

	
	
}

