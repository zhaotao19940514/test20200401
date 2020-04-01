package cn.com.taiji.css.manager.administration.agency.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.administration.agency.permission.AgencyPermissionModel;
import cn.com.taiji.css.model.administration.agency.permission.AgencyPermissionRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.AgencyVarify;
import cn.com.taiji.qtk.entity.dict.CardTypeSimple;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.AgencyVarifyRepo;

/**
 * 
 * @author lz
 *
 */
@Service
public class AgencyPermissionManagerImpl implements AgencyPermissionManager {

	@Autowired
	private AgencyVarifyRepo agencyVarifyRepo;

	@Autowired
	private AgencyRepo agencyRepo;

	@Override
	public LargePagination<AgencyPermissionModel> query(AgencyPermissionRequest queryModel) throws ManagerException {
		LargePagination<AgencyVarify> largePage = agencyVarifyRepo.largePage(queryModel);
		if (largePage.getResult().isEmpty()) {
			return null;
		}
		Map<String, String> agencyMap = getAllAgencyInfo();
		List<AgencyPermissionModel> modelList = new ArrayList<AgencyPermissionModel>();
		for (AgencyVarify pa : largePage.getResult()) {
			AgencyPermissionModel model = setShowModel(agencyMap.get(pa.getAgencyId()),
					agencyMap.get(pa.getPermittedAgencyId()), pa.getCardType().getValue(), pa.getId());
			modelList.add(model);
		}
		LargePagination<AgencyPermissionModel> modelPage = new LargePagination<AgencyPermissionModel>();
		modelPage.setResult(modelList);
		modelPage.setPageSize(largePage.getPageSize());
		modelPage.setCurrentPage(largePage.getCurrentPage());
		modelPage.setHasMore(largePage.isHasMore());
		return modelPage;
	}

	public List<Agency> findLikeName(String name) {
		return agencyRepo.listByName(name);
	}

	@Transactional
	public AgencyPermissionModel add(AgencyPermissionRequest addModel) throws ManagerException {
		addModel.addParamCheck();
		// 是否有相同数据
		AgencyVarify queryResult = agencyVarifyRepo.listByAgencyIdAndPermittedAgencyIdAndCardType(
				addModel.getAgencyId(), addModel.getPermittedAgencyId(),
				CardTypeSimple.fromCode(addModel.getCardTypeCode()));
		if (queryResult != null) {
			throw new ManagerException("该权限信息已存在，请检查后再试");
		}
		AgencyVarify saveEntity = null;
		// 判断条件
		boolean condition = addModel.getCardTypeCode() == CardTypeSimple.ALL.getCode();
		if (condition) {
			// 全省ETC卡，查询是否存在低权限信息
			List<AgencyVarify> lowPermissionAgency = agencyVarifyRepo.findLowPermissionAgency(addModel.getAgencyId(),
					addModel.getPermittedAgencyId());
			if (lowPermissionAgency.size() != 0) {
				for (AgencyVarify agency : lowPermissionAgency) {
					agencyVarifyRepo.deleteById(agency.getId());
				}
			}
			AgencyVarify agencyInfo = new AgencyVarify();
			agencyInfo.setAgencyId(addModel.getAgencyId());
			agencyInfo.setPermittedAgencyId(addModel.getPermittedAgencyId());
			agencyInfo.setCardType(CardTypeSimple.fromCode(addModel.getCardTypeCode()));
			// 保存
			saveEntity = agencyVarifyRepo.save(agencyInfo);
		} else {
			// 非全省ETC卡，查询是否存在最高权限信息
			List<AgencyVarify> higherAgency = agencyVarifyRepo.findHigherPermissionAgency(addModel.getAgencyId(),
					addModel.getPermittedAgencyId());
			if (higherAgency.size() != 0) {
				throw new ManagerException("当前选择渠道控制权限已达最高");
			}
			// 查询是否可以合并的信息
			if (CardTypeSimple.VALUE.getCode() == addModel.getCardTypeCode()) {
				List<AgencyVarify> accountInfo = agencyVarifyRepo.findCardTypeByACCOUNT(addModel.getAgencyId(),
						addModel.getPermittedAgencyId());
				// 不存在冲突信息
				if (accountInfo.size() == 0) {
					AgencyVarify agencyInfo = new AgencyVarify();
					agencyInfo.setAgencyId(addModel.getAgencyId());
					agencyInfo.setPermittedAgencyId(addModel.getPermittedAgencyId());
					agencyInfo.setCardType(CardTypeSimple.fromCode(addModel.getCardTypeCode()));
					// 保存
					saveEntity = agencyVarifyRepo.save(agencyInfo);
				} else {
					for (AgencyVarify agencyInfo : accountInfo) {
						agencyVarifyRepo.deleteById(agencyInfo.getId());
					}
					AgencyVarify agencyInfo = new AgencyVarify();
					agencyInfo.setAgencyId(addModel.getAgencyId());
					agencyInfo.setPermittedAgencyId(addModel.getPermittedAgencyId());
					agencyInfo.setCardType(CardTypeSimple.ALL);
					// 保存
					saveEntity = agencyVarifyRepo.save(agencyInfo);
				}
			} else {
				// 添加记账卡
				List<AgencyVarify> valueInfo = agencyVarifyRepo.findCardTypeByVALUE(addModel.getAgencyId(),
						addModel.getPermittedAgencyId());
				if (valueInfo.size() == 0) {
					AgencyVarify agencyInfo = new AgencyVarify();
					agencyInfo.setAgencyId(addModel.getAgencyId());
					agencyInfo.setPermittedAgencyId(addModel.getPermittedAgencyId());
					agencyInfo.setCardType(CardTypeSimple.fromCode(addModel.getCardTypeCode()));
					// 保存
					saveEntity = agencyVarifyRepo.save(agencyInfo);
				} else {
					// 删除低权限信息
					for (AgencyVarify agencyInfo : valueInfo) {
						agencyVarifyRepo.deleteById(agencyInfo.getId());
					}
					AgencyVarify agencyInfo = new AgencyVarify();
					agencyInfo.setAgencyId(addModel.getAgencyId());
					agencyInfo.setPermittedAgencyId(addModel.getPermittedAgencyId());
					agencyInfo.setCardType(CardTypeSimple.ALL);
					// 保存
					saveEntity = agencyVarifyRepo.save(agencyInfo);
				}
			}
		}
		Map<String, String> agencyInfo = getAllAgencyInfo();
		AgencyPermissionModel showModel = setShowModel(agencyInfo.get(saveEntity.getAgencyId()),
				agencyInfo.get(saveEntity.getPermittedAgencyId()), saveEntity.getCardType().getValue(),
				saveEntity.getId());
		return showModel;
	}

	public AgencyPermissionModel getAgencyVarifyInfo(String id) throws ManagerException {
		AgencyVarify queryResult = agencyVarifyRepo.findById(id).get();
		if (queryResult == null) {
			throw new ManagerException("该权限资源不存在或已被删除，请刷新后重试");
		}
		Map<String, String> allAgencyInfo = getAllAgencyInfo();
		AgencyPermissionModel showModel = setShowModel(allAgencyInfo.get(queryResult.getAgencyId()),
				allAgencyInfo.get(queryResult.getPermittedAgencyId()), queryResult.getCardType().getValue(),
				queryResult.getId());
		return showModel;
	}

	@Transactional
	public AgencyPermissionModel edit(AgencyPermissionRequest editModel) throws ManagerException {
		if(editModel.getPermittedAgencyId()==null) {
			throw new ManagerException("请选择被控机构");
		}
		if(editModel.getCardTypeCode()==null) {
			throw new ManagerException("请选择卡类型");
		}
		AgencyVarify queryResult = agencyVarifyRepo.findById(editModel.getId()).get();
		if (queryResult == null) {
			throw new ManagerException("该权限资源不存在或已删除，请刷新后重试");
		}
		// 修改
		queryResult.setPermittedAgencyId(editModel.getPermittedAgencyId());
		queryResult.setCardType(CardTypeSimple.fromCode(editModel.getCardTypeCode()));
		// 判断资源是否冲突
		AgencyVarify sameInfo = agencyVarifyRepo.listByAgencyIdAndPermittedAgencyIdAndCardType(
				queryResult.getAgencyId(), queryResult.getPermittedAgencyId(), queryResult.getCardType());
		if (sameInfo != null) {
			throw new ManagerException("试图修改的数据已存在，请检查后再试");
		}
		AgencyVarify saveEntity = null;
		if (CardTypeSimple.ALL.getCode() == queryResult.getCardType().getCode()) {
			// 判断低权限数据
			List<AgencyVarify> lowPermision = agencyVarifyRepo.findLowPermissionAgency(queryResult.getAgencyId(),
					queryResult.getPermittedAgencyId());
			if (0==lowPermision.size()) {
				saveEntity = agencyVarifyRepo.save(queryResult);
			} else {
				// 删除低权限信息
				for (AgencyVarify agencyInfo : lowPermision) {
					agencyVarifyRepo.deleteById(agencyInfo.getId());
				}
				saveEntity = agencyVarifyRepo.save(queryResult);
			}
		} else {
			List<AgencyVarify> higherAgency = agencyVarifyRepo.findHigherPermissionAgency(queryResult.getAgencyId(),
					queryResult.getPermittedAgencyId());
			if (higherAgency.size() != 0) {
				throw new ManagerException("当前选择渠道控制权限已达最高");
			}
			// 添加
			saveEntity = agencyVarifyRepo.save(queryResult);
		}
		Map<String, String> allAgencyInfo = getAllAgencyInfo();
		AgencyPermissionModel showModel = setShowModel(allAgencyInfo.get(saveEntity.getAgencyId()),
				allAgencyInfo.get(saveEntity.getPermittedAgencyId()), saveEntity.getCardType().getValue(),
				saveEntity.getId());
		return showModel;
	}

	@Transactional
	public void batchAdd(AgencyPermissionRequest batchAddModel) throws ManagerException {
		batchParameterCheck(batchAddModel);
		// 已有数据Map
		Map<String, CardTypeSimple> currentData = new HashMap<String, CardTypeSimple>();
		List<AgencyVarify> allAgencyVarify = agencyVarifyRepo.findAll();
		for (AgencyVarify agencyVarify : allAgencyVarify) {
			String key = agencyVarify.getAgencyId() + "," + agencyVarify.getPermittedAgencyId();
			CardTypeSimple value = agencyVarify.getCardType();
			currentData.put(key, value);
		}
		// 需要添加的数据map
		Map<String, CardTypeSimple> addData = new HashMap<String, CardTypeSimple>();
		if (0 == batchAddModel.getBatchType()) {
			// 控制渠道添加
			for (int i = 0; i < batchAddModel.getAgencyList().length(); i += 12) {
				String key = batchAddModel.getAgencyId() + "," + batchAddModel.getAgencyList().substring(i, i + 11);
				CardTypeSimple value = CardTypeSimple.fromCode(batchAddModel.getCardTypeCode());
				addData.put(key, value);
			}
		} else if (1 == batchAddModel.getBatchType()) {
			// 被控制渠道添加
			for (int i = 0; i < batchAddModel.getAgencyList().length(); i += 12) {
				String key = batchAddModel.getAgencyList().substring(i, i + 11) + "," + batchAddModel.getAgencyId();
				CardTypeSimple value = CardTypeSimple.fromCode(batchAddModel.getCardTypeCode());
				addData.put(key, value);
			}
		}
		List<AgencyVarify> addList = new ArrayList<AgencyVarify>();
		Iterator<Entry<String, CardTypeSimple>> iterator = addData.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, CardTypeSimple> entry = iterator.next();
			if (currentData.get(entry.getKey()) == entry.getValue()) {
				iterator.remove();
			} else {
				int x = 0;
				String agencyId = entry.getKey().substring(0, 11);
				String permittedAgencyId = entry.getKey().substring(12, 23);
				CardTypeSimple cardType = currentData.get(entry.getKey());
				AgencyVarify deleteData = agencyVarifyRepo.listByAgencyIdAndPermittedAgencyIdAndCardType(agencyId,
						permittedAgencyId, cardType);
				if (deleteData != null) {
					agencyVarifyRepo.deleteById(deleteData.getId());
					x = 1;
				}
				// 增加至addList
				AgencyVarify agencyVarify = new AgencyVarify();
				agencyVarify.setAgencyId(agencyId);
				agencyVarify.setPermittedAgencyId(permittedAgencyId);
				if (x == 1) {
					agencyVarify.setCardType(CardTypeSimple.ALL);
				} else {
					agencyVarify.setCardType(CardTypeSimple.fromCode(batchAddModel.getCardTypeCode()));
				}
				addList.add(agencyVarify);
			}
		}
		agencyVarifyRepo.saveAll(addList);
	}

	@Transactional
	public void batchDelete(AgencyPermissionRequest batchDeleteModel) throws ManagerException {
		// 参数检查
		batchParameterCheck(batchDeleteModel);
		// 已有数据Map
		Map<String, CardTypeSimple> currentData = new HashMap<String, CardTypeSimple>();
		List<AgencyVarify> allAgencyVarify = agencyVarifyRepo.findAll();
		for (AgencyVarify agencyVarify : allAgencyVarify) {
			String key = agencyVarify.getAgencyId() + "," + agencyVarify.getPermittedAgencyId();
			CardTypeSimple value = agencyVarify.getCardType();
			currentData.put(key, value);
		}
		Map<String, CardTypeSimple> deleteData = new HashMap<String, CardTypeSimple>();
		List<AgencyVarify> deleteList = new ArrayList<AgencyVarify>();
		if (0 == batchDeleteModel.getBatchType()) {
			// 根据控制渠道批量删除
			for (int i = 0; i < batchDeleteModel.getAgencyList().length(); i += 12) {
				String key = batchDeleteModel.getAgencyId() + ","
						+ batchDeleteModel.getAgencyList().substring(i, i + 11);
				CardTypeSimple value = CardTypeSimple.fromCode(batchDeleteModel.getCardTypeCode());
				deleteData.put(key, value);
			}
		} else if (1 == batchDeleteModel.getBatchType()) {
			// 根据被控制渠道批量删除

			for (int i = 0; i < batchDeleteModel.getAgencyList().length(); i += 12) {
				String key = batchDeleteModel.getAgencyList().substring(i, i + 11) + ","
						+ batchDeleteModel.getAgencyId();
				CardTypeSimple value = CardTypeSimple.fromCode(batchDeleteModel.getCardTypeCode());
				deleteData.put(key, value);
			}
		}
		// 对比数据
		Iterator<Entry<String, CardTypeSimple>> iterator = deleteData.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, CardTypeSimple> entry = iterator.next();
			// 已有数据与删除数据相同
			if (currentData.get(entry.getKey()) == entry.getValue()) {
				String agencyId = entry.getKey().substring(0, 11);
				String permittedAgencyId = entry.getKey().substring(12, 23);
				AgencyVarify agencyVarify = agencyVarifyRepo.listByAgencyIdAndPermittedAgencyIdAndCardType(agencyId,
						permittedAgencyId, entry.getValue());
				deleteList.add(agencyVarify);
			}
		}

		agencyVarifyRepo.deleteAll(deleteList);
	}

	@Override
	public void delete(String id) throws ManagerException {
		Optional<AgencyVarify> queryResult = agencyVarifyRepo.findById(id);
		if (null == queryResult.get()) {
			throw new ManagerException("该权限资源不存在或已被删除，请刷新后重试");
		}
		agencyVarifyRepo.deleteById(id);
	}

	@Override
	public List<Agency> getAgencyInfo() {
		List<Agency> allAgency = agencyRepo.findAll();
		return allAgency;
	}

	public List<Agency> queryIdLikeName(String name) {
		List<Agency> queryResult = agencyRepo.listByName(name);
		if (queryResult.size() != 0 && queryResult.size() == 1) {
			return queryResult;
		}
		return null;
	}

	public String getAgencyIdById(String id) {
		return agencyVarifyRepo.findById(id).get().getAgencyId();
	}

	/**
	 * 
	 * @return 所有渠道的Map k:agencyID v:渠道名称
	 */
	private Map<String, String> getAllAgencyInfo() {
		List<Agency> allAgencyInfo = agencyRepo.findAll();
		Map<String, String> agencyInfo = new HashMap<String, String>();
		for (Agency agency : allAgencyInfo) {
			agencyInfo.put(agency.getAgencyId(), agency.getName());
		}
		return agencyInfo;
	}

	/**
	 * 创建前端显示对象
	 * 
	 * @param agencyName          控制渠道名称
	 * @param permiteedAgencyName 被控制渠道名称
	 * @param cardTypeInfo        卡类型
	 * @param id
	 * @return
	 */
	private AgencyPermissionModel setShowModel(String agencyName, String permiteedAgencyName, String cardTypeInfo,
			String id) {
		AgencyPermissionModel model = new AgencyPermissionModel();
		model.setAgencyName(agencyName);
		model.setPermittedAgencyName(permiteedAgencyName);
		model.setCardTypeInfo(cardTypeInfo);
		model.setId(id);
		return model;
	}

	/**
	 * 批量操作 参数检查
	 * 
	 * @param model
	 * @throws ManagerException
	 */
	private void batchParameterCheck(AgencyPermissionRequest model) throws ManagerException {
		if (null == model.getAgencyId())
			throw new ManagerException("请选择渠道后重试");
		if (null == model.getBatchType())
			throw new ManagerException("请选择批量操作类型后重试");
		if (null == model.getCardTypeCode())
			throw new ManagerException("请选择卡类型后渠道重试");
		if (null == model.getAgencyList())
			throw new ManagerException("请选择批量操作的渠道后重试");
	}

}
