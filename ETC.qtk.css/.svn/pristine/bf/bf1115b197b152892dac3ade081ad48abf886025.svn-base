package cn.com.taiji.css.manager.administration.pkg;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.pkg.ReplaceEquipmentRequest;
import cn.com.taiji.qtk.entity.ReplaceEquipmentCostDetail;

public interface ReplaceEquipmentManager {
	Pagination queryPage(ReplaceEquipmentRequest queryModel);
	public ReplaceEquipmentCostDetail add(ReplaceEquipmentCostDetail replaceEquipmentCostDetail,User user) throws ManagerException;
	public ReplaceEquipmentCostDetail update(ReplaceEquipmentCostDetail replaceEquipmentCostDetail,User user) throws ManagerException;
	public ReplaceEquipmentCostDetail findId(String id) throws ManagerException;
	public List<ReplaceEquipmentCostDetail> findReplaceEquipment(User user);

}
