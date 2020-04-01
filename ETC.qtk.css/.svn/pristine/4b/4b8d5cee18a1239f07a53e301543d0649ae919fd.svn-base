package cn.com.taiji.css.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cn.com.taiji.qtk.entity.FtpFileRecordDetail;

@Repository
public class GybFtpDetailDaoJpa extends MyBaseDao<FtpFileRecordDetail> implements GybFtpDetailDao {

	@Override
	public List<Object[]> findDetailByStatusAndFileNameIn(List<String> fileNames,int status) {
		StringBuffer sql = new StringBuffer("SELECT t1.FILE_NAME,t1.submit,t2.SUCCESS from (SELECT FILE_NAME,status,count(1) as submit from qtk_ftp_file_record where file_name in(" );
		for(int i=0;i<fileNames.size();i++) {
			String fileName = fileNames.get(i);
			if(i+1>=fileNames.size()) {
				sql.append("'"+fileName+"'");
			}else {
				sql.append("'"+fileName+"',");
			}
		}
		sql.append(")and status in (1,2) GROUP BY status,FILE_NAME)t1 join (select FILE_NAME,status as SUCCESS_status,count(1) as success from qtk_ftp_file_record  where file_name in(");
		for(int i=0;i<fileNames.size();i++) {
			String fileName = fileNames.get(i);
			if(i+1>=fileNames.size()) {
				sql.append("'"+fileName+"'");
			}else {
				sql.append("'"+fileName+"',");
			}
		}
		sql .append(")and substr(RES_MESSAGE,instr(RES_MESSAGE,'\"info\":')+8,2)='成功' group by status,FILE_NAME)t2 on t1.file_name=t2.file_name where t1.status=?1 and t1.status=t2.SUCCESS_status ORDER BY t1.FILE_NAME,t1.status");
		String stringSql = sql.toString();
		Query query = entityManager.createNativeQuery(stringSql);
		query.setParameter(1, status);
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		return resultList;
	}

}
