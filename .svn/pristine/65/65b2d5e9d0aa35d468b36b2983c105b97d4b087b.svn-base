package cn.com.taiji.css.manager.administration.notice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.administration.notice.NoticeRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.qtk.entity.BankSignDetail;
import cn.com.taiji.qtk.entity.dict.BankSignSendType;
import cn.com.taiji.qtk.repo.jpa.BankSignDetailRepo;

/**
 * 
 *@ClassName NoticeManagerImpl.java
 *@Description: 
 *@author:zhaot
 *@date: 2020年2月28日
 */
@Service
public class NoticeManagerImpl extends AbstractDsiCommManager implements NoticeManager{

	@Autowired
	private BankSignDetailRepo bankRepo;

	@Override
	public LargePagination<BankSignDetail> queryPage(NoticeRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		
		return bankRepo.largePage(queryModel);
		
	}

	@Override
	public AppAjaxResponse update(String rowId) {
		AppAjaxResponse res = new AppAjaxResponse();
		try {
			BankSignDetail findById = bankRepo.findById(rowId).get();
			if(null!=findById) {
				findById.setSendStatus(BankSignSendType.WAIT_SEND);
				findById.setSendCount(1);
			}
			bankRepo.save(findById);
			res.setStatus(1);
		} catch (Exception e) {
			res.setStatus(0);
		}
		return res;
	}


	
}

