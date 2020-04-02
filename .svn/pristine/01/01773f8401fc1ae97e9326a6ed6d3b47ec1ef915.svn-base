package cn.com.taiji.css.manager.administration.section4x.rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.util.PeriodModel;
import cn.com.taiji.qtk.entity.Card4XSection;
import cn.com.taiji.qtk.entity.Obu4XSection;
import cn.com.taiji.qtk.entity.Operation4xLog;
import cn.com.taiji.qtk.entity.Section4xBackUp;
import cn.com.taiji.qtk.entity.dict.DeviceVersion;
import cn.com.taiji.qtk.entity.dict.Operation4xType;
import cn.com.taiji.qtk.repo.jpa.Card4XSectionRepo;
import cn.com.taiji.qtk.repo.jpa.Obu4XSectionRepo;
import cn.com.taiji.qtk.repo.jpa.Operation4xLogRepo;
import cn.com.taiji.qtk.repo.jpa.Section4xBackUpRepo;

@Service
public class Section4xRollBackManagerImpl implements Section4xRollBackManager {

	@Autowired
	private Card4XSectionRepo card4xSectionRepo;
	@Autowired
	private Obu4XSectionRepo obu4xSectionRepo;
	@Autowired
	private Operation4xLogRepo operation4xLogRepo;
	@Autowired
	private Section4xBackUpRepo section4xBackUpRepo;

	@Transactional
	@Override
	public void doRollBack(String batchNo, Integer rollBackType, String staffId) throws ManagerException {
		if (rollBackType.intValue() == 1) {
			obuRollBack(batchNo, rollBackType, staffId);
		} else if (rollBackType.intValue() == 2) {
			cardRollBack(batchNo, rollBackType, staffId);
		} else {
			throw new ManagerException("未知回滚类型");
		}
	}

	private void cardRollBack(String batchNo, Integer rollBackType, String staffId) throws ManagerException {
		// 通过批次号和添加类型拿到此次添加的日期
		List<Operation4xType> operation4xTypes = Lists.newArrayList();
		operation4xTypes.add(Operation4xType.ADD_CARD);
		operation4xTypes.add(Operation4xType.BATCH_ADD_CARD);
		List<Operation4xLog> operations = operation4xLogRepo.findByBatchNoAndOperationType(batchNo, operation4xTypes);
		if (operations.size() == 0) {
			throw new ManagerException("批次号错误，该批次号不存在");
		}
		String date = operations.get(0).getOperationTime().replace("T", " ");
		// 获取备份表数据
		List<Section4xBackUp> cardBackUpEntities = getBackUpEntities(rollBackType);
		List<Card4XSection> backupData = backupSectionModel2CardModel(cardBackUpEntities);
		// 获取日志表中 相应数据
		List<Operation4xLog> rollBackEntities = operation4xLogRepo.findRollBackEntities(date,
				DeviceVersion.CARD_4X.toString());
		List<Card4XSection> operationData = operationModel2CardModel(rollBackEntities);
		operationData.addAll(backupData);
		List<Card4XSection> finalData = Lists.newArrayList();
		try {
			// 合并号段
			finalData = mergeCardSection(operationData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("合并号段出现错误，请联系管理员");
		}
		List<Card4XSection> oldEntities = card4xSectionRepo.findAll();
		// 入库
		card4xSectionRepo.saveAll(finalData);
		// 删除原有的
		card4xSectionRepo.deleteAll(oldEntities);
		// 日志入库
		Operation4xLog logEntity = new Operation4xLog();
		logEntity.setOperation4xType(Operation4xType.ROLL_BACK_CARD);
		logEntity.setOperationTime(LocalDateTime.now().toString().substring(0, 19));
		logEntity.setStaffId(staffId);
		logEntity.setBatchNo(UUID.randomUUID().toString());
		logEntity.setDeviceVersion(DeviceVersion.CARD_4X);
		logEntity.setStartId("回滚的批次号为:");
		logEntity.setEndId(batchNo);
		operation4xLogRepo.save(logEntity);
	}

	private List<Section4xBackUp> getBackUpEntities(Integer rollBackType) throws ManagerException {
		Map<DeviceVersion, List<Section4xBackUp>> backUpEntitiesMap = section4xBackUpRepo.findAll().parallelStream()
				.collect(Collectors.groupingBy(Section4xBackUp::getVersion));
		if (rollBackType.intValue() == 1) {
			// obu回滚
			return backUpEntitiesMap.get(DeviceVersion.OBU_4X);
		} else if (rollBackType.intValue() == 2) {
			// 卡回滚
			return backUpEntitiesMap.get(DeviceVersion.CARD_4X);
		}
		return null;
	}

	private List<Card4XSection> backupSectionModel2CardModel(List<Section4xBackUp> section) {
		List<Card4XSection> modelList = Lists.newArrayList();
		LocalDateTime createTime = LocalDateTime.now();
		for (Section4xBackUp sectionModel : section) {
			Card4XSection cardModel = new Card4XSection();
			cardModel.setStartId(sectionModel.getStartId());
			cardModel.setVersion(DeviceVersion.CARD_4X);
			cardModel.setCreateTime(createTime);
			cardModel.setEndId(sectionModel.getEndId());
			modelList.add(cardModel);
		}
		return modelList;
	}

	private List<Card4XSection> operationModel2CardModel(List<Operation4xLog> logModels) {
		List<Card4XSection> cardModels = Lists.newArrayList();
		LocalDateTime createTime = LocalDateTime.now();
		for (Operation4xLog logModel : logModels) {
			Card4XSection cardModel = new Card4XSection();
			cardModel.setStartId(logModel.getStartId());
			cardModel.setEndId(logModel.getEndId());
			cardModel.setVersion(DeviceVersion.CARD_4X);
			cardModel.setCreateTime(createTime);
			cardModels.add(cardModel);
		}
		return cardModels;
	}

	private List<Card4XSection> mergeCardSection(List<Card4XSection> cardSections) throws Exception {
		List<PeriodModel> beforeMerge = Lists.newArrayList();
		for (Card4XSection card4xSection : cardSections) {
			beforeMerge.add(new PeriodModel(Long.valueOf(card4xSection.getStartId().substring(1)),
					Long.valueOf(card4xSection.getEndId().substring(1))));
		}
		List<PeriodModel> doMergeSortList = PeriodModel.doMergeSortList(beforeMerge);
		List<Card4XSection> result = Lists.newArrayList();
		for (PeriodModel periodModel : doMergeSortList) {
			Card4XSection cardModel = new Card4XSection();
			cardModel.setCreateTime(LocalDateTime.now());
			cardModel.setVersion(DeviceVersion.CARD_4X);
			cardModel.setStartId("5" + periodModel.getStartId());
			cardModel.setEndId("5" + periodModel.getEndId());
			result.add(cardModel);
		}
		return result;
	}

	private void obuRollBack(String batchNo, Integer rollBackType, String staffId) throws ManagerException {
		// 通过批次号和操作类型拿到此次添加的日期
		List<Operation4xType> operation4xTypes = Lists.newArrayList();
		operation4xTypes.add(Operation4xType.ADD_OBU);
		operation4xTypes.add(Operation4xType.BATCH_ADD_OBU);
		List<Operation4xLog> operations = operation4xLogRepo.findByBatchNoAndOperationType(batchNo, operation4xTypes);
		if (operations.size() == 0) {
			throw new ManagerException("批次号错误，该批次号不存在");
		}
		String date = operations.get(0).getOperationTime().replace("T", " ");
		List<Section4xBackUp> obuBackUpEntities = getBackUpEntities(rollBackType);
		List<Obu4XSection> backupData = backupSectionModel2ObuModel(obuBackUpEntities);
		// 获取日志表中 相应数据
		List<Operation4xLog> rollBackEntities = operation4xLogRepo.findRollBackEntities(date,
				DeviceVersion.OBU_4X.toString());
		List<Obu4XSection> operationData = operationModel2ObuModel(rollBackEntities);
		operationData.addAll(backupData);
		List<Obu4XSection> finalData = Lists.newArrayList();
		try {
			// 合并号段
			finalData = mergeObuSection(operationData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("合并号段出现错误，请联系管理员");
		}
		List<Obu4XSection> oldEntities = obu4xSectionRepo.findAll();
		// 入库
		obu4xSectionRepo.saveAll(finalData);
		// 删除原有的
		obu4xSectionRepo.deleteAll(oldEntities);
		// 日志入库
		Operation4xLog logEntity = new Operation4xLog();
		logEntity.setOperation4xType(Operation4xType.ROLL_BACK_OBU);
		logEntity.setOperationTime(LocalDateTime.now().toString().substring(0, 19));
		logEntity.setStaffId(staffId);
		logEntity.setBatchNo(UUID.randomUUID().toString());
		logEntity.setDeviceVersion(DeviceVersion.OBU_4X);
		logEntity.setStartId("回滚的批次号为:");
		logEntity.setEndId(batchNo);
		operation4xLogRepo.save(logEntity);
	}

	private List<Obu4XSection> backupSectionModel2ObuModel(List<Section4xBackUp> section) {
		List<Obu4XSection> modelList = Lists.newArrayList();
		LocalDateTime createTime = LocalDateTime.now();
		for (Section4xBackUp sectionModel : section) {
			Obu4XSection obuModel = new Obu4XSection();
			obuModel.setStartId(sectionModel.getStartId());
			obuModel.setVersion(DeviceVersion.OBU_4X);
			obuModel.setCreateTime(createTime);
			obuModel.setEndId(sectionModel.getEndId());
			modelList.add(obuModel);
		}
		return modelList;
	}

	private List<Obu4XSection> operationModel2ObuModel(List<Operation4xLog> logModels) {
		List<Obu4XSection> obuModels = Lists.newArrayList();
		LocalDateTime createTime = LocalDateTime.now();
		for (Operation4xLog logModel : logModels) {
			Obu4XSection obuModel = new Obu4XSection();
			obuModel.setStartId(logModel.getStartId());
			obuModel.setEndId(logModel.getEndId());
			obuModel.setVersion(DeviceVersion.OBU_4X);
			obuModel.setCreateTime(createTime);
			obuModels.add(obuModel);
		}
		return obuModels;
	}

	private List<Obu4XSection> mergeObuSection(List<Obu4XSection> obuSections) throws Exception {
		List<PeriodModel> beforeMerge = Lists.newArrayList();
		for (Obu4XSection obu4xSection : obuSections) {
			beforeMerge.add(
					new PeriodModel(Long.valueOf(obu4xSection.getStartId()), Long.valueOf(obu4xSection.getEndId())));
		}
		List<PeriodModel> doMergeSortList = PeriodModel.doMergeSortList(beforeMerge);
		List<Obu4XSection> result = Lists.newArrayList();
		for (PeriodModel periodModel : doMergeSortList) {
			Obu4XSection obuModel = new Obu4XSection();
			obuModel.setCreateTime(LocalDateTime.now());
			obuModel.setVersion(DeviceVersion.OBU_4X);
			obuModel.setStartId(periodModel.getStartId().toString());
			obuModel.setEndId(periodModel.getEndId().toString());
			result.add(obuModel);
		}
		return result;
	}
}
