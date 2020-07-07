package cn.com.taiji.css.manager.issuetranscation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.issuetranscation.IssueTranscationRequest;
import cn.com.taiji.qtk.entity.IssueTransactionDetail;
import cn.com.taiji.qtk.repo.jpa.IssueTransactionDetailRepo;
@Service
public class IssuetranscationManagerImpl extends AbstractManager implements IssuetranscationManager {

	@Autowired
	private IssueTransactionDetailRepo issueTransactionDetailRepo;
	
	@Override
	public Pagination page(IssueTranscationRequest request) {		
		return issueTransactionDetailRepo.page(request);
	}

	@Override
	public AppAjaxResponse update(String rowId) {
		IssueTransactionDetail issueTransactionDetail = issueTransactionDetailRepo.findById(rowId).get();
		issueTransactionDetail.setHandleDate(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now()));
		AppAjaxResponse res = new AppAjaxResponse();
		try {
			issueTransactionDetailRepo.save(issueTransactionDetail);
			res.setStatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.setStatus(-1);
		}
		return res;
	}
public static void main(String[] args) {
	
}
}
