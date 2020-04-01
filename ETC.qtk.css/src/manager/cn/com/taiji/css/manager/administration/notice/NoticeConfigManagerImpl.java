package cn.com.taiji.css.manager.administration.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.administration.notice.NoticeConfigRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.qtk.entity.BankSignVersionDetail;
import cn.com.taiji.qtk.repo.jpa.BankSignVersionDetailRepo;

/**
 * 
 *@ClassName NoticeManagerImpl.java
 *@Description: 
 *@author:zhaot
 *@date: 2020年2月28日
 */
@Service
public class NoticeConfigManagerImpl extends AbstractDsiCommManager implements NoticeConfigManager{

	@Autowired
	private BankSignVersionDetailRepo bankVersionRepo;

	@Override
	public LargePagination<BankSignVersionDetail> queryPage(NoticeConfigRequest queryModel,User user) throws ManagerException {
		queryModel.validate();
		
		return bankVersionRepo.largePage(queryModel);
		
	}

	@Override
	public BankSignVersionDetail findbyId(String rowId) {
		
		return bankVersionRepo.findById(rowId).get();
	}

	@Override
	public AppAjaxResponse edit(NoticeConfigRequest queryModel) {
		BankSignVersionDetail bankSignVersionDetail = bankVersionRepo.findById(queryModel.getRowId()).get();
		bankSignVersionDetail.setActiveNfSwitch(queryModel.getActiveNfSwitch());
		bankSignVersionDetail.setBankSignUrl(queryModel.getBankSignUrl());
		bankSignVersionDetail.setCardNfSwitch(queryModel.getCardNfSwitch());
		bankSignVersionDetail.setCardNotice(queryModel.getCardNotice());
		bankSignVersionDetail.setCardNoticeSwitch(queryModel.getCardNoticeSwitch());
		bankSignVersionDetail.setOBUNfSwitch(queryModel.getOBUNfSwitch());
		bankSignVersionDetail.setOpenNotify(queryModel.getOpenNotify());
		bankSignVersionDetail.setOpenObuNotify(queryModel.getOpenObuNotify());
		bankSignVersionDetail.setSignNoticeSwitch(queryModel.getSignNoticeSwitch());
		AppAjaxResponse res = new AppAjaxResponse();
		try {
			bankVersionRepo.save(bankSignVersionDetail);
			res.setStatus(1);
		} catch (Exception e) {
			res.setStatus(0);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public AppAjaxResponse add(NoticeConfigRequest queryModel) {
		AppAjaxResponse res = new AppAjaxResponse();
		BankSignVersionDetail findByAgencyId = bankVersionRepo.findByAgencyId(queryModel.getAgencyId());
		if(null!=findByAgencyId) {
			res.setStatus(0);
			res.setMessage("已存在该渠道");
		}else {
			findByAgencyId = new BankSignVersionDetail();
			findByAgencyId.setActiveNfSwitch(queryModel.getActiveNfSwitch());
			findByAgencyId.setAgencyId(queryModel.getAgencyId());
			findByAgencyId.setAgencyName(queryModel.getAgencyName());
			findByAgencyId.setBankSignUrl(queryModel.getBankSignUrl());
			findByAgencyId.setCardNfSwitch(queryModel.getCardNfSwitch());
			findByAgencyId.setCardNotice(queryModel.getCardNotice());
			findByAgencyId.setCardNoticeSwitch(queryModel.getCardNoticeSwitch());
			findByAgencyId.setCheckContract(queryModel.getCheckContract());
			findByAgencyId.setOBUNfSwitch(queryModel.getOBUNfSwitch());
			findByAgencyId.setOpenNotify(queryModel.getOpenNotify());
			findByAgencyId.setOpenObuNotify(queryModel.getOpenObuNotify());
			findByAgencyId.setSignNotice(queryModel.getSignNotice());
			findByAgencyId.setSignNoticeSwitch(queryModel.getSignNoticeSwitch());
			findByAgencyId.setChangeCard(queryModel.getChangeCard());
			bankVersionRepo.save(findByAgencyId);
		}
		
		return res;
	}


	
}

