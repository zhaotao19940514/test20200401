package cn.com.taiji.css.manager;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.css.entity.SystemInfo;
import cn.com.taiji.css.entity.dict.SysConfType;
import cn.com.taiji.css.model.AbstractSysConf;
import cn.com.taiji.css.model.CronPara;
import cn.com.taiji.css.model.SystemPara;
import cn.com.taiji.css.repo.jpa.SystemInfoRepo;
import spring.cn.com.taiji.common.annotation.PostInitialize;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-11-16 下午01:14:14<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("systemInfoManager")
public class SystemInfoManagerImpl extends AbstractManager implements SystemInfoManager
{
	private SystemPara systemPara;
	private CronPara cronPara;
	@Autowired
	private SystemInfoRepo sysInfoRepo;

	@Override
	@Transactional
	public void updateSystemPara(SystemPara para)
	{
		AssertUtil.notNull(para, "SystemPara param can not be null");
		update(para, SystemInfo.SYSTEM_USER_ID);
		initSystemPara();
		logger.debug("Admin update the SystemPara.");
	}

	private void update(AbstractSysConf sysConf, String userId)
	{
		update(sysConf, userId, false);
	}

	private void update(AbstractSysConf sysConf, String userId, boolean ziped)
	{
		SystemInfo info = sysInfoRepo.findOne(sysConf.getConfType(), userId);
		if (info != null)
		{
			String jsonStr = info.isZiped() ? AbstractSysConf.compress(sysConf.toJson()) : sysConf.toJson();
			info.setJsonStr(jsonStr);
			info.setUpdateTime(LocalDateTime.now());
			sysInfoRepo.save(info);
			return;
		}
		info = sysConf.toSystemInfo(null, userId, ziped);
		sysInfoRepo.save(info);
	}

	private <E extends AbstractSysConf> E initOne(Class<E> clazz, SysConfType confType, String userId)
	{
		SystemInfo info = sysInfoRepo.findOne(confType, userId);
		try
		{
			if (info == null) return clazz.newInstance();
			String jsonStr = info.isZiped() ? AbstractSysConf.uncompress(info.getJsonStr()) : info.getJsonStr();
			return AbstractSysConf.newInstance(clazz, jsonStr);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void initSystemPara()
	{
		systemPara = initOne(SystemPara.class, SysConfType.SYSTEM_PARA, SystemInfo.SYSTEM_USER_ID);
		logger.debug("系统参数初始化成功……{}", systemPara);
	}

	@Override
	public SystemPara getSystemPara()
	{
		if (this.systemPara == null) this.initSystemPara();
		return this.systemPara;
	}

	private void initCronPara()
	{
		cronPara = initOne(CronPara.class, SysConfType.CRON_PARA, SystemInfo.SYSTEM_USER_ID);
	}

	@Override
	public CronPara getCronPara()
	{
		initCronPara();
		return cronPara;
	}

	@Override
	@Transactional
	public void updateCronPara(CronPara para) throws ManagerException
	{
		AssertUtil.notNull(para, "CronPara param can not be null");
		para.valid();
		update(para, SystemInfo.SYSTEM_USER_ID);
		initCronPara();
	}

	@Override
	@Transactional
	public void txSample(boolean rollback, boolean rollbackAlways) throws ManagerException
	{
		SystemInfo info = new SystemInfo();
		info.setId("-1");
		info.setConfType(SysConfType.SYSTEM_PARA);

		info.setUserId("for test");
		info.setJsonStr("回滚时数据库中无此记录，否则会有");
		txInPrivateMethod(info, rollback);
		if (rollbackAlways) throw new RuntimeException("总是回滚,数据库中应该无数据");
	}

	@Transactional(rollbackFor = { ManagerException.class })
	private void txInPrivateMethod(SystemInfo info, boolean rollback) throws ManagerException
	{
		sysInfoRepo.save(info);
		if (rollback) throw new ManagerException("异常抛出，查看数据库是否回滚.");
	}

	@Override
	@Transactional
	public void deleteById(String id)
	{
		sysInfoRepo.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(SystemInfo info)
	{
		sysInfoRepo.delete(info);
	}

	@PostInitialize
	public void init()
	{
		initSystemPara();
		initCronPara();
	}
}
