/*
 * Date: 2015年11月3日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.jpa;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.SystemInfo;
import cn.com.taiji.css.entity.dict.SysConfType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月3日 下午4:37:40<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface SystemInfoRepo extends AbstractJpaRepo<SystemInfo, String>
{
	@Query("from SystemInfo  where confType=?1 and userId=?2")
	public SystemInfo findOne(SysConfType confType, String userId);
}
