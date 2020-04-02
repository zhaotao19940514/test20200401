package cn.com.taiji.css.manager.administration.pkg;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.pkg.ReplaceEquipmentRequest;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.ReplaceEquipmentCostDetail;
import cn.com.taiji.qtk.repo.jpa.ReplaceEquipmentCostRepo;
@Service
public class ReplaceEquipmentManagerImpl extends AbstractManager implements ReplaceEquipmentManager{
    @Autowired
    private ReplaceEquipmentCostRepo  replaceEquipmentCostRepo;	
	@Override
	public Pagination queryPage(ReplaceEquipmentRequest queryModel) {		
		return replaceEquipmentCostRepo.page(queryModel);
	}

	@Override
	@Transactional
	public ReplaceEquipmentCostDetail add(ReplaceEquipmentCostDetail replaceEquipmentCostDetail,User user)
			throws ManagerException {
		valid(replaceEquipmentCostDetail);
		if(replaceEquipmentCostRepo.findAll().size()>0) throw new ManagerException("已有补换设备费用信息！");
		replaceEquipmentCostDetail.setCreateTime(QTKUtils.getDateString());
		replaceEquipmentCostDetail.setCreateUser(user.getName());
		return replaceEquipmentCostRepo.save(replaceEquipmentCostDetail);
	}

	@Override
	@Transactional
	public ReplaceEquipmentCostDetail update(ReplaceEquipmentCostDetail replaceEquipmentCostDetail,User user)
			throws ManagerException {		
		valid(replaceEquipmentCostDetail);
		ReplaceEquipmentCostDetail resd = replaceEquipmentCostRepo.findId(replaceEquipmentCostDetail.getId());
		BeanUtils.copyProperties(replaceEquipmentCostDetail, resd, null, "id");
		resd.setCreateTime(QTKUtils.getDateString());
		resd.setCreateUser(user.getName());
		return replaceEquipmentCostRepo.save(resd);
	}

	@Override
	public ReplaceEquipmentCostDetail findId(String id) throws ManagerException {
		return replaceEquipmentCostRepo.findId(id);
	}
    
	private void valid(ReplaceEquipmentCostDetail replaceEquipmentCostDetail){
		MyViolationException mv = new MyViolationException();
		if(replaceEquipmentCostDetail.getCardCost()<0||replaceEquipmentCostDetail.getCardCost()==null)mv.addViolation("cardCost", "卡费用为空或非法!");
	    if(replaceEquipmentCostDetail.getObuCost()<0||replaceEquipmentCostDetail.getObuCost()==null)mv.addViolation("obuCost", "obu费用为空或非法!");
	    if(mv.hasViolation()) throw mv;
	}

	@Override
	public List<ReplaceEquipmentCostDetail> findReplaceEquipment(User user) {
		//建行补换卡
		String agencyId =user.getStaff().getServiceHall().getAgencyId();
		if(("52010102018").equals(agencyId)||("52010102002").equals(agencyId)) {
			ReplaceEquipmentCostDetail ccb = new ReplaceEquipmentCostDetail();
			ccb.setCardCost((double)0);
			ccb.setObuCost((double)0);
			List<ReplaceEquipmentCostDetail> list = Lists.newArrayList();
			list.add(ccb);
			return list;
		}
		return replaceEquipmentCostRepo.findAll();
	}
}
