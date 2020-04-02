/**
 * @Title OperateLogManagerImpl.java
 * @Package cn.com.taiji.css.manager.system
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月28日 下午1:42:29
 * @version V1.0
 */
package cn.com.taiji.css.manager.system;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.OperateLog;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.receipt.ReceiptBaseModel;
import cn.com.taiji.css.model.system.request.CssOperateLogRequest;
import cn.com.taiji.css.repo.jpa.OperateLogRepo;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;

/**
 * @ClassName OperateLogManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年07月28日 13:42:29
 * @E_mail yaonanlin@163.com
 */
@Service
public class OperateLogManagerImpl extends AbstractManager implements OperateLogManager {
	@Autowired
	private OperateLogRepo logRepo;
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Transactional
	@Override
	public void saveLog(OperateLog operateLog) {
		logRepo.save(operateLog);
	}

	@Override
	public Pagination queryPage(CssOperateLogRequest request) {
		request.valid();
		return logRepo.page(request);
	}

	@Override
	public LargePagination<OperateLog> queryLargePage(CssOperateLogRequest request) {
		request.valid();
		return logRepo.largePage(request);
	}

	@Override
	public OperateLog findById(String id) {
		Optional<OperateLog> log = logRepo.findById(id);
		return log.get();
	}

	@Override
	public ReceiptBaseModel toReceiptModel(String logId) {
		Optional<OperateLog> op = logRepo.findById(logId);
		if(op.isPresent()){
			OperateLog log = op.get();
			ReceiptBaseModel model = new ReceiptBaseModel();
			if(StringTools.hasText(log.getRelatedCardId())){
				model.setCardId(log.getRelatedCardId());
			}
			if(StringTools.hasText(log.getRelatedCustomerId())){
				CustomerInfo customerInfo = customerInfoRepo.findByCustomerId(log.getRelatedCustomerId());
				model.setCustomerInfo(customerInfo);
				if(customerInfo != null){
					model.setCustomerIDType(CustomerIDType.valueOfCode(customerInfo.getCustomerIdType()));
				}
				
			}
			if(StringTools.hasText(log.getRelatedRechargeId())){
				model.setChargeDetail(chargeDetailRepo.findByChargeId(log.getRelatedRechargeId()));
			}
			if(StringTools.hasText(log.getRelatedObuId())){
				model.setObuId(log.getRelatedObuId());
			}
			User user = null;
			if(StringTools.hasText(log.getOperatorId())){
				Optional<User> userOp = userRepo.findById(log.getOperatorId());
				if(userOp.isPresent()){
					user = userOp.get();
					model.setOperator(user);
				}
			}
			model.setLog(log);
			String chargeTypeStr = CssUtil.getPropertyValueFromJsonData("chargeType", log.getData());
			String tradeFeeStr = CssUtil.getPropertyValueFromJsonData("tradeFee", log.getData());
			if(StringTools.hasText(chargeTypeStr)){
				model.setChargeType(ChargeType.fromCode(Integer.valueOf(chargeTypeStr)));
			}
			if(StringTools.hasText(tradeFeeStr)){
				model.setTradeFee(Long.valueOf(tradeFeeStr));
			}
			model.setPrintTime(LocalDateTime.now());
			return model;
		}else{
			return null;
		}
	}
	
}

