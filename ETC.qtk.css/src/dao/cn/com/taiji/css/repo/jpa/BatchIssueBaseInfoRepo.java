package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.BatchIssueBaseInfo;
import cn.com.taiji.css.entity.dict.BatchIssueStatus;

public interface BatchIssueBaseInfoRepo extends AbstractJpaRepo<BatchIssueBaseInfo, String> {
	@Query("from BatchIssueBaseInfo where status=?1 and batchId=?2")
	public List<BatchIssueBaseInfo> listByStatusAndBatchId(BatchIssueStatus status,String batchId);
	@Query("from BatchIssueBaseInfo where status=?1 order by updateTime asc,id asc")
	public List<BatchIssueBaseInfo> listByStatusAscTime(BatchIssueStatus status);
	@Query(nativeQuery=true,value="select distinct BATCH_ID from QTK_BATCH_ISSUE_BASEINFO ")
	public List<String> list();
	@Query(nativeQuery=true,value="select BATCH_ID,create_time from " + 
			"(select BATCH_ID,create_time, row_number() over(partition by BATCH_ID order by CREATE_TIME) rn from QTK_BATCH_ISSUE_BASEINFO) " + 
			"where rn = 1")
	public List<Object[]> queryBatchIdAndCreateTime();
}
