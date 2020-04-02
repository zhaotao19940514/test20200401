/*
 * Date: 2011-12-15
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cn.com.taiji.common.dao.jpa.BaseDaoJpa;
import cn.com.taiji.common.entity.BaseEntity;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-12-15 下午12:30:26<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class MyBaseDao<E extends BaseEntity> extends BaseDaoJpa<E>
{
	protected MyBaseDao()
	{

	}

	@PersistenceContext(unitName = "entityManager")
	public void setJpaEntityManager(EntityManager entityManager)
	{
		super.setEntityManager(entityManager);
	}
}
