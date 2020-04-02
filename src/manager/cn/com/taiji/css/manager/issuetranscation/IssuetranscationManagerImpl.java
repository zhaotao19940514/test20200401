package cn.com.taiji.css.manager.issuetranscation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.issuetranscation.IssueTranscationRequest;
import cn.com.taiji.qtk.repo.jpa.IssueTransactionDetailRepo;
@Service
public class IssuetranscationManagerImpl extends AbstractManager implements IssuetranscationManager {

	@Autowired
	private IssueTransactionDetailRepo issueTransactionDetailRepo;
	
	@Override
	public Pagination page(IssueTranscationRequest request) {		
		return issueTransactionDetailRepo.page(request);
	}

}
