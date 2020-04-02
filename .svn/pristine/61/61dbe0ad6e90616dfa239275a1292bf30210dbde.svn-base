package cn.com.taiji.css.repo.jpa;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.ICBCContract;

public interface IcbcContractRepo extends AbstractJpaRepo<ICBCContract, String>
{
	@Query("from ICBCContract where opResult is null")
	public ICBCContract opREsultIsNull();
}
