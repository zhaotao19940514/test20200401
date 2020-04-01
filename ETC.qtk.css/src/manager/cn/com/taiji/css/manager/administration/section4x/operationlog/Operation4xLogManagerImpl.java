package cn.com.taiji.css.manager.administration.section4x.operationlog;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.administration.section4x.Operation4xLogRequest;
import cn.com.taiji.qtk.entity.Operation4xLog;
import cn.com.taiji.qtk.repo.jpa.Operation4xLogRepo;

@Service
public class Operation4xLogManagerImpl implements Operation4xLogManager {

	@Autowired
	private Operation4xLogRepo operation4xLogRepo;
	
	@Override	
	public Pagination getLog(Operation4xLogRequest queryModel) {
		Pagination page = operation4xLogRepo.page(queryModel);
		@SuppressWarnings("unchecked")
		List<Operation4xLog> result = (List<Operation4xLog>) page.getResult();
		if(result.size()==0) {
			return page;
		}
		LocalDateTime finalChangeTime = LocalDateTime.parse(result.get(0).getOperationTime());
		for(Operation4xLog queryResult : result) {
			if(finalChangeTime.minusDays(31).isBefore(LocalDateTime.parse(queryResult.getOperationTime()))) {
				queryResult.setRollBack(true);
			}else {
				queryResult.setRollBack(false);
			}
			if(queryResult.getOperation4xType().toString().indexOf("CARD")>=0) {
				//卡相关
				queryResult.setRollBackType(2);
			}else {
				//obu相关
				queryResult.setRollBackType(1);
			}
			if(queryResult.getOperation4xType().toString().indexOf("ROLL")>=0) {
				queryResult.setRollBack(false);
			}
		}
		return page;
	}
}
