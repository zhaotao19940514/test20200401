package cn.com.taiji.css.manager.administration.section4x.card;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.model.administration.section4x.Card4XSectionModel;
import cn.com.taiji.css.model.administration.section4x.Card4XSectionRequest;
import cn.com.taiji.css.model.util.PeriodModel;
import cn.com.taiji.qtk.entity.Card4XSection;
import cn.com.taiji.qtk.entity.Operation4xLog;
import cn.com.taiji.qtk.entity.dict.DeviceVersion;
import cn.com.taiji.qtk.entity.dict.Operation4xType;
import cn.com.taiji.qtk.repo.jpa.Card4XSectionRepo;
import cn.com.taiji.qtk.repo.jpa.Operation4xLogRepo;

@Service
public class Card4XSectionManageImpl implements Card4XSectionManage {

	@Autowired
	private Card4XSectionRepo card4XSectionRepo;

	@Autowired
	private Operation4xLogRepo operation4xLogRepo;

	@Override
	@Transactional
	public void add(Card4XSectionRequest request, String staffId) throws ManagerException {
		// 参数校验
		request.paramCheck();
		if (Long.valueOf(request.getEndId().substring(3)) < Long.valueOf(request.getEndId().substring(3))) {
			throw new ManagerException("起始号段不允许比终止号段大" );
		}
		if (Long.valueOf(request.getStartId().substring(3)).toString().length() != request.getStartId().substring(3).length()) {
			throw new ManagerException("不支持此起始号段" );
		}
		if (Long.valueOf(request.getEndId().substring(3)).toString().length() != request.getEndId().substring(3).length()) {
			throw new ManagerException("不支持此终止号段");
		}
		List<Card4XSection> allExsitEntity = card4XSectionRepo.findAll();
		if (allExsitEntity.size() == 0) {
			// 无数据
			Card4XSection entity = new Card4XSection();
			entity.setCreateTime(LocalDateTime.now());
			entity.setVersion(DeviceVersion.getDeviceVersionFromCode(request.getVersion()));
			entity.setStartId(request.getStartId());
			entity.setEndId(request.getEndId());
			card4XSectionRepo.save(entity);

		} else {
			// 有历史数据
			List<PeriodModel> union = Lists.newArrayList();
			for (Card4XSection entity : allExsitEntity) {
				Long startId = Long.valueOf(entity.getStartId().substring(3));
				Long endId = Long.valueOf(entity.getEndId().substring(3));
				PeriodModel mergeEntity = new PeriodModel(startId, endId);
				union.add(mergeEntity);
			}
			PeriodModel saveEntity = new PeriodModel(Long.valueOf(request.getStartId().substring(3)),
					Long.valueOf(request.getEndId().substring(3)));
			union.add(saveEntity);
			saveAndDelete(union, allExsitEntity);
		}
		// 写入日志
		Operation4xLog log = getLog(staffId, request.getStartId(), request.getEndId(),
				DeviceVersion.getDeviceVersionFromCode(request.getVersion()), UUID.randomUUID().toString(),
				LocalDateTime.now().toString().substring(0, 19), Operation4xType.ADD_CARD);
		operation4xLogRepo.save(log);
	}

	@Override
	public LargePagination<Card4XSectionModel> findAll(Card4XSectionRequest request) throws ManagerException {
		MyViolationException mve = new MyViolationException();
		if (request.getCardId() != null) {
			if (!request.getCardId().startsWith("5201") || !request.getCardId().matches("[0-9]+")
					|| request.getCardId().length() != 20) {
				mve.addViolation("cardId", "卡号输入错误");
			}
		}
		if (mve.hasViolation()) {
			throw mve;
		}
		LargePagination<Card4XSectionModel> pagn = new LargePagination<Card4XSectionModel>();
		List<Card4XSectionModel> modelList = new ArrayList<Card4XSectionModel>();
		if (request.getCardId() != null) {
			List<Card4XSection> queryresult = card4XSectionRepo.findByCardId(request.getCardId());
			for (Card4XSection card4XSection : queryresult) {
				Card4XSectionModel model = new Card4XSectionModel();
				model.setId(card4XSection.getId());
				model.setDate(card4XSection.getCreateTime().toString());
				model.setStartId(card4XSection.getStartId());
				model.setEndId(card4XSection.getEndId());
				model.setVersion(card4XSection.getVersion().getName());
				model.setAmount(Long.valueOf(card4XSection.getEndId().substring(3, 20))
						- Long.valueOf(card4XSection.getStartId().substring(3, 20)) + 1 + "");
				modelList.add(model);
			}
			pagn.setCurrentPage(1);
			pagn.setHasMore(false);
			pagn.setPageSize(1);
			pagn.setResult(modelList);
			return pagn;
		}

		LargePagination<Card4XSection> queryResult = card4XSectionRepo.largePage(request);
		List<Card4XSection> queryResultList = queryResult.getResult();
		for (Card4XSection card4XSection : queryResultList) {
			Card4XSectionModel model = new Card4XSectionModel();
			model.setId(card4XSection.getId());
			model.setDate(card4XSection.getCreateTime().toString());
			model.setStartId(card4XSection.getStartId());
			model.setEndId(card4XSection.getEndId());
			model.setVersion(card4XSection.getVersion().getName());
			model.setAmount(Long.valueOf(card4XSection.getEndId().substring(3, 20))
					- Long.valueOf(card4XSection.getStartId().substring(3, 20)) + 1 + "");
			modelList.add(model);
		}
		pagn.setCurrentPage(queryResult.getCurrentPage());
		pagn.setHasMore(queryResult.isHasMore());
		pagn.setPageSize(pagn.getPageSize());
		pagn.setResult(modelList);
		return pagn;
	}

	@Override
	public void delete(String id) throws ManagerException {
		Card4XSection queryResult = card4XSectionRepo.findById(id).get();
		if (queryResult == null) {
			throw new ManagerException("该卡号段不存在");
		}
		card4XSectionRepo.delete(queryResult);
	}

	@Override
	@Transactional
	public void batchAdd(Card4XSectionRequest request, String staffId) throws ManagerException {
		batchAddParamCheck(request);
		List<PeriodModel> union = Lists.newArrayList();
		List<String> cardSection = Lists.newArrayList();
		Long startId = 0L;
		Long endId = 0L;
		for (String section : request.getBatchInfo()) {
			if (section == null) {
				throw new ManagerException("有多余‘,’导致数据校验失败，请检查后重试，请参考示例" + "非法号段为：" + section);
			}
			String[] splitResult = section.split("-");
			if (splitResult.length != 2) {
				throw new ManagerException("不支持除 - 外的分割符，请参考示例" + "非法号段为：" + section);
			}
			if (splitResult[0] == null || !splitResult[0].startsWith("5201") || !splitResult[0].matches("[0-9]+")
					|| splitResult[0].length() != 20) {
				throw new ManagerException("批量起始号段填写错误" + splitResult[0]);
			}
			if (splitResult[1] == null || !splitResult[1].startsWith("5201") || !splitResult[1].matches("[0-9]+")
					|| splitResult[1].length() != 20) {
				throw new ManagerException("批量终止号段填写错误" + splitResult[1]);
			}
			startId = Long.valueOf(splitResult[0].toString().substring(3));
			endId = Long.valueOf(splitResult[1].toString().substring(3));
			if (startId.toString().length() != splitResult[0].toString().substring(3).length()) {
				throw new ManagerException("批量信息中有不支持的起始号段" + "，号段为：" + splitResult[0].toString());
			}
			if (startId.toString().length() != splitResult[0].toString().substring(3).length()) {
				throw new ManagerException("批量信息中有不支持的终止号段" + "，号段为：" + splitResult[1].toString());
			}
			if (endId < startId) {
				throw new ManagerException("终止号段不允许不起始号段小，请检查后再试" + "非法号段为：" + section);
			}
			union.add(new PeriodModel(Long.valueOf(splitResult[0].substring(3)),
					Long.valueOf(splitResult[1].substring(3))));
			cardSection.add("start:" + splitResult[0].toString() + " end:" + splitResult[1].toString());
		}
		List<Card4XSection> allExsitEntity = card4XSectionRepo.findAll();
		for (Card4XSection card4XSectionEntity : allExsitEntity) {
			union.add(new PeriodModel(Long.valueOf(card4XSectionEntity.getStartId().substring(3)),
					Long.valueOf(card4XSectionEntity.getEndId().substring(3))));
		}
		saveAndDelete(union, allExsitEntity);
		// 日志相关
		List<Operation4xLog> logList = new ArrayList<Operation4xLog>();
		String uuid = UUID.randomUUID().toString();
		String operationTime = LocalDateTime.now().toString().substring(0, 19);
		for (String operationDeatil : cardSection) {
			Operation4xLog log = getLog(staffId,
					operationDeatil.substring(operationDeatil.indexOf("start:") + 6,operationDeatil.indexOf("start:") + 26),
					operationDeatil.substring(operationDeatil.indexOf("end:") + 4,operationDeatil.indexOf("end:") + 24),
					DeviceVersion.getDeviceVersionFromCode(request.getVersion()), uuid, operationTime,
					Operation4xType.BATCH_ADD_CARD);
			logList.add(log);
		}
		operation4xLogRepo.persistAll(logList);
	}

	private void saveAndDelete(List<PeriodModel> beforeMerge, List<Card4XSection> allModel) throws ManagerException {
		String start = "520";
		List<PeriodModel> afterMerge = Lists.newArrayList();
		try {
			afterMerge = PeriodModel.doMergeSortList(beforeMerge);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException(e.getMessage());
		}
		List<Card4XSection> saveList = new ArrayList<Card4XSection>();
		for (PeriodModel afterMergeModel : afterMerge) {
			Card4XSection saveEntity = new Card4XSection();
			saveEntity.setStartId(start + afterMergeModel.getStartId());
			saveEntity.setEndId(start + afterMergeModel.getEndId());
			saveEntity.setVersion(DeviceVersion.CARD_4X);
			saveEntity.setCreateTime(LocalDateTime.now());
			saveList.add(saveEntity);
		}
		card4XSectionRepo.persistAll(saveList);
		card4XSectionRepo.deleteAll(allModel);
	}

	private Operation4xLog getLog(String staffId, String startId, String endId, DeviceVersion version, String no,
			String time, Operation4xType type) {
		Operation4xLog log = new Operation4xLog();
		log.setStaffId(staffId);
		log.setStartId(startId);
		log.setEndId(endId);
		log.setDeviceVersion(version);
		log.setBatchNo(no);
		log.setOperationTime(time);
		log.setOperation4xType(type);
		return log;
	}

	private void batchAddParamCheck(Card4XSectionRequest request) throws ManagerException {
		if (request.getBatchInfo().size() == 0)
			throw new ManagerException("请填写卡批量信息");
		if (request.getVersion() == null)
			throw new ManagerException("请填写卡版本");
		if (request.getVersion() != 4)
			throw new ManagerException("卡版本选择错误");
	}
}
