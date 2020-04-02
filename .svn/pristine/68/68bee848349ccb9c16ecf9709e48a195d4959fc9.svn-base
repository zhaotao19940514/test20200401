/**
 * @Title AgencyVarifyManagerImpl.java
 * @Package cn.com.taiji.css.manager.administration.agency
 * @Description TODO
 * @author yaonanlin
 * @date 2019年1月28日 下午2:20:39
 * @version V1.0
 */
package cn.com.taiji.css.manager.administration.agency;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.qtk.entity.Admin;
import cn.com.taiji.qtk.entity.AgencyVarify;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.CardTypeSimple;
import cn.com.taiji.qtk.repo.jpa.AdminRepo;
import cn.com.taiji.qtk.repo.jpa.AgencyVarifyRepo;

/**
 * @ClassName AgencyVarifyManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2019年01月28日 14:20:39
 * @E_mail yaonanlin@163.com
 */
@Service
public class AgencyVarifyManagerImpl extends AbstractManager implements AgencyVarifyManager{
	@Autowired
	private AgencyVarifyRepo agencyVarifyRepo;
	@Autowired
	private AdminRepo adminRepo;

	@Override
	public boolean varifyAgency(User user, CardInfo cardInfo) throws ManagerException {
		if(user==null) throw new ManagerException("渠道业务检验：未找到当前登录用户信息");
		if(cardInfo==null) throw new ManagerException("渠道业务检验：未找到相应卡信息");
		
		// 管理员校验
		Admin admin = adminRepo.findByUserId(user.getId());
		if(admin!=null && admin.getStatus() == 1){
			return true;
		}
		
		String userAgencyId = user.getStaff().getServiceHall().getAgencyId();
		if(!StringTools.hasText(userAgencyId)) 
			throw new ManagerException("用户:"+user.getId()+" 关联工号："+user.getStaffId() + "的渠道编号为空");
		String targetAgencyId = cardInfo.getAgencyId();
		if(!StringTools.hasText(targetAgencyId)) 
			throw new ManagerException("卡号:"+cardInfo.getCardId()+" 渠道编号为空");
		
		// 是否存在ALL卡类型的配置
		List<AgencyVarify> agencyVarifiesCardTypeAll = agencyVarifyRepo.listByAgencyIdAndCardType(userAgencyId, CardTypeSimple.ALL);
		// 若存在 先检验一次 通过则直接返回true
		if(agencyVarifiesCardTypeAll!=null && agencyVarifiesCardTypeAll.size()>0){
			List<String> permittedAgencyIds = agencyVarifiesCardTypeAll.parallelStream().map(av->av.getPermittedAgencyId()).collect(Collectors.toList());
			// 若该渠道卡可由当前用户渠道操作 返回true
			if(permittedAgencyIds.contains(targetAgencyId)){
				return true;
			}
		}
			// 否则继续
		
		// 按卡类型直接查询
		Integer cardType = cardInfo.getCardType();
		if(cardType == null) throw new ManagerException("渠道权限校验：卡号-"+cardInfo.getCardId() + " 卡类型为空 无法继续操作 请确认数据" );
		CardTypeSimple cardTypeSimple;
		if(cardType/100 == 1){
			cardTypeSimple = CardTypeSimple.ACCOUNT;
		}else{
			cardTypeSimple = CardTypeSimple.VALUE;
		}
		List<AgencyVarify> agencyVarifiesCardType = agencyVarifyRepo.listByAgencyIdAndCardType(userAgencyId, cardTypeSimple);
			// 若存在 校验 通过则返回true
		if(agencyVarifiesCardType!=null && agencyVarifiesCardType.size()>0){
			List<String> permittedAgencyIds = agencyVarifiesCardType.parallelStream().map(av->av.getPermittedAgencyId()).collect(Collectors.toList());
			// 若该渠道卡可由当前用户渠道操作 返回true
			if(permittedAgencyIds.contains(targetAgencyId)){
				return true;
			}
		}else{
			// 若不存在 则校验  // targetAgencyId.equals(userAgencyId); 通过则返回true
			if(targetAgencyId.equals(userAgencyId)){
				return true;
			}
		}
		// 若以上校验均未通过 则返回false
		return false;
	}
	
	
}

