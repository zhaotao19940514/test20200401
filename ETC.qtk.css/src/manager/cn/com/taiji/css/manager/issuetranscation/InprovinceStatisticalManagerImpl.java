package cn.com.taiji.css.manager.issuetranscation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.model.issuetranscation.InprovinceStatisticalModel;
import cn.com.taiji.css.model.issuetranscation.InprovinceStatisticalRequest;
import cn.com.taiji.qtk.repo.jpa.FileInprovinceDetailRepo;

@Service
public class InprovinceStatisticalManagerImpl extends AbstractManager implements InprovinceStatisticalManager {
	@Autowired
	private FileInprovinceDetailRepo fileInprovinceDetailRepo;

	@Override
	public Pagination findByTypeAndDate(InprovinceStatisticalRequest req) throws ManagerException {
		valid(req);

		Integer type = req.getAgencyType();
		List<InprovinceStatisticalModel> modelList = Lists.newArrayList();
		switch (type) {
		case 1:
			modelList = findByCzk(req);
			break;

		case 2:
            modelList = findByJzk(req);
			break;
		case 3:
			 modelList = findByJhk(req);
			break;
		case 4:
			 modelList = findByGhk(req);
			break;
		default:
			break;
		}

		Pagination page = new Pagination();
		page.setResult(modelList);
		page.setPageSize(30);
		page.setTotalCount(modelList.size());
		page.compute();		
		return page;

	}

	public void valid(InprovinceStatisticalRequest req) throws ManagerException {
		MyViolationException mv = new MyViolationException();
		if (req.getAgencyType() == null)
			mv.addViolation("agencyType", "请选择查询渠道！");
		if (req.getStartTime() == null || req.getStartTime().isEmpty())
			mv.addViolation("startTime", "请选择开始时间");
		if (req.getEndTime() == null || req.getEndTime().isEmpty())
			mv.addViolation("endTime", "请选择结束时间");
		if (mv.hasViolation())
			throw mv;
	}

	private List<InprovinceStatisticalModel> findByCzk(InprovinceStatisticalRequest req) {
		List<Object[]> resultList = fileInprovinceDetailRepo.countByCZKAndDate(req.getStartTime(), req.getEndTime());
		List<InprovinceStatisticalModel> modelList = Lists.newArrayList();
		for (Object[] objects : resultList) {
			InprovinceStatisticalModel model = new InprovinceStatisticalModel();
			logger.info("1--"+objects[0]);
			logger.info("2--"+objects[1]);
			logger.info("3--"+objects[2]);
			model.setTimeStampDate(String.valueOf(objects[0]));
			model.setTotalCount(Long.valueOf(objects[1].toString()));
			model.setTotalMoney(Long.valueOf(objects[2].toString()));
			model.setType(1);
			modelList.add(model);
		}
		return modelList;
	}

	private List<InprovinceStatisticalModel> findByJzk(InprovinceStatisticalRequest req) {
		List<Object[]> resultList = fileInprovinceDetailRepo.countByJZKAndDate(req.getStartTime(), req.getEndTime());
		List<InprovinceStatisticalModel> modelList = Lists.newArrayList();
		for (Object[] objects : resultList) {
			InprovinceStatisticalModel model = new InprovinceStatisticalModel();
			model.setTimeStampDate(String.valueOf(objects[0]));
			model.setTotalCount(Long.valueOf(objects[1].toString()));
			model.setTotalMoney(Long.valueOf(objects[2].toString()));
			model.setType(2);
			modelList.add(model);
		}
		return modelList;
	}

	private List<InprovinceStatisticalModel> findByJhk(InprovinceStatisticalRequest req) {
		List<Object[]> resultList = fileInprovinceDetailRepo.countByJHKAndDate(req.getStartTime(), req.getEndTime());
		List<InprovinceStatisticalModel> modelList = Lists.newArrayList();
		for (Object[] objects : resultList) {
			InprovinceStatisticalModel model = new InprovinceStatisticalModel();
			model.setTimeStampDate(String.valueOf(objects[0]));
			model.setTotalCount(Long.valueOf(objects[1].toString()));
			model.setTotalMoney(Long.valueOf(objects[2].toString()));
			model.setType(3);
			modelList.add(model);
		}
		return modelList;
	}

	private List<InprovinceStatisticalModel> findByGhk(InprovinceStatisticalRequest req) {
		List<Object[]> resultList = fileInprovinceDetailRepo.countByGHKAndDate(req.getStartTime(), req.getEndTime());
		List<InprovinceStatisticalModel> modelList = Lists.newArrayList();
		for (Object[] objects : resultList) {
			InprovinceStatisticalModel model = new InprovinceStatisticalModel();
			model.setTimeStampDate(String.valueOf(objects[0]));
			model.setTotalCount(Long.valueOf(objects[1].toString()));
			model.setTotalMoney(Long.valueOf(objects[2].toString()));
			model.setType(4);
			modelList.add(model);
		}
		return modelList;
	}
}
