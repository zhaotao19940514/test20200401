/**
 * @Title ServiceHallDeviceConfigManagerImpl.java
 * @Package cn.com.taiji.css.manager.serviceHall.deviceconfig
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月6日 下午7:59:39
 * @version V1.0
 */
package cn.com.taiji.css.manager.serviceHall.deviceconfig;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigEditByAgencyModel;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigEditModel;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigRequest;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;
import cn.com.taiji.qtk.entity.dict.CssCardDeviceType;
import cn.com.taiji.qtk.entity.dict.CssObuDeviceType;
import cn.com.taiji.qtk.entity.dict.CssPosDeviceType;
import cn.com.taiji.qtk.repo.jpa.ServiceHallDeviceConfigRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;

/**
 * @ClassName ServiceHallDeviceConfigManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年09月06日 19:59:39
 * @E_mail yaonanlin@163.com
 */
@Service
public class ServiceHallDeviceConfigManagerImpl extends AbstractManager implements ServiceHallDeviceConfigManager {
	@Autowired
	private ServiceHallDeviceConfigRepo configRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Override
	public Object page(@Valid ServiceHallDeviceConfigRequest req) {
		return configRepo.page(req);
	}

	@Override
	public ServiceHallDeviceConfig findById(String id) {
		return configRepo.findById(id).orElse(null);
	}
	
	@Transactional(rollbackFor=ManagerException.class)
	@Override
	public ServiceHallDeviceConfig add(ServiceHallDeviceConfig configToAdd) throws ManagerException {
		validAdd(configToAdd);
		validServiceHallId(configToAdd);
		ServiceHallDeviceConfig config = saveAdd(configToAdd);
		return config;
	}

	private ServiceHallDeviceConfig saveAdd(ServiceHallDeviceConfig configToAdd) {
		ServiceHallDeviceConfig config = new ServiceHallDeviceConfig();
		BeanTools.copyProperties(configToAdd, config,new String[]{"id"});
		config.setCreateTime(CssUtil.getNowDateTimeStrWithT());
		config.setUpdateTime(CssUtil.getNowDateTimeStrWithT());
		ServiceHall serviceHall = serviceHallRepo.findByServiceHallId(configToAdd.getServiceHallId());
		config.setServiceHall(serviceHall);
		configRepo.persist(config);
		setupServiceHallFk(config);
		return config;
	}

	private void setupServiceHallFk(ServiceHallDeviceConfig config) {
		ServiceHall serviceHall = serviceHallRepo.findByServiceHallId(config.getServiceHallId());
		if(serviceHall!=null){
			configRepo.flush();
			serviceHall.setConfig(config);
			serviceHallRepo.save(serviceHall);
		}
	}
	
	private void validServiceHallId(ServiceHallDeviceConfig configToAdd) throws ManagerException {
		ServiceHallDeviceConfig dbConfig = configRepo.findByServiceHallId(configToAdd.getServiceHallId());
		if(dbConfig != null){
			throw new ManagerException("网点:"+configToAdd.getServiceHallId()+" 已存在配置，请查询并修改");
		}
	}

	private void validAdd(ServiceHallDeviceConfig config){
		MyViolationException mve = new MyViolationException();
//		if(config.getCardDeviceType()==null){
//			mve.addViolation("cardDeviceType", "卡设备类型不能为空");
//		}
//		if(config.getObuDeviceType()==null){
//			mve.addViolation("obuDeviceType", "OBU设备类型不能为空");
//		}
//		if(config.getPosDeviceType()==null){
//			mve.addViolation("posDeviceType", "POS设备类型不能为空");
//		}
		if(!StringTools.hasText(config.getServiceHallId())){
			mve.addViolation("serviceHallId", "网点不能为空");
		}
		if(mve.hasViolation()){
			throw mve;
		}
	}
	
	@Transactional(rollbackFor=ManagerException.class)
	@Override
	public void editByAgencyId(ServiceHallDeviceConfigEditByAgencyModel configToEdit) {
		configToEdit.valid();
		// 按agencyId找到所有网点配置  并为没有配置的生成实体
		List<ServiceHallDeviceConfig> configs = getConfigsByAgencyId(configToEdit.getAgencyId());
		// 将所有网点配置变更为指定配置
		setConfigs(configs,configToEdit);
		// 保存配置
		saveConfigs(configs);
	}

	private void setConfigs(List<ServiceHallDeviceConfig> configs,ServiceHallDeviceConfigEditByAgencyModel configToEdit) {
		configs.forEach(config->{
			config.setCardDeviceType(configToEdit.getCardDeviceType());
			config.setObuDeviceType(configToEdit.getObuDeviceType());
			config.setPosDeviceType(configToEdit.getPosDeviceType());
			config.setUpdateTime(CssUtil.getNowDateTimeStrWithT());
		});
	}

	private void saveConfigs(List<ServiceHallDeviceConfig> configs) {
		configRepo.saveAll(configs);
		configRepo.flush();
		Map<String, ServiceHallDeviceConfig> configMap = configs.parallelStream().collect(Collectors.toMap(ServiceHallDeviceConfig::getServiceHallId, c->c));
		List<ServiceHall> serviceHalls = configs.parallelStream().map(c->c.getServiceHall()).collect(Collectors.toList());
		serviceHalls.forEach(serviceHall->{
			serviceHall.setConfig(configMap.get(serviceHall.getServiceHallId()));
		});
		serviceHallRepo.saveAll(serviceHalls);
	}

	private List<ServiceHallDeviceConfig> getConfigsByAgencyId(String agencyId) {
		// 机构下所有网点
		List<ServiceHall> serviceHalls = serviceHallRepo.listByAgentId(agencyId);
		// 机构下所有网点编号
		List<String> serviceHallIds = serviceHalls.parallelStream().map(s->s.getServiceHallId()).collect(Collectors.toList());
		// 库内已有的配置
		List<ServiceHallDeviceConfig> dbConfigs = configRepo.listByServiceHallIds(serviceHallIds);
		// 库内已有配置的网点编号
		List<String> dbConfigsServiceHallIds = dbConfigs.parallelStream().map(c->c.getServiceHallId()).collect(Collectors.toList());
		// 未配置的网点
		List<ServiceHall> unsetServiceHalls = serviceHalls.parallelStream().filter(s->!dbConfigsServiceHallIds.contains(s.getServiceHallId())).collect(Collectors.toList());
		// 未配置的网点生成默认配置
		List<ServiceHallDeviceConfig> newConfigs= generateConfig(unsetServiceHalls);
		List<ServiceHallDeviceConfig> result = Lists.newArrayList();
		result.addAll(dbConfigs);
		result.addAll(newConfigs);
		return result;
	}
	
	private List<ServiceHallDeviceConfig> generateConfig(List<ServiceHall> unsetServiceHalls) {
		List<ServiceHallDeviceConfig> configs = Lists.newArrayList();
		unsetServiceHalls.forEach(serviceHall->{
			ServiceHallDeviceConfig config = new ServiceHallDeviceConfig();
			config.setCardDeviceType(CssCardDeviceType.getDefalutType());
			config.setObuDeviceType(CssObuDeviceType.getDefalutType());
			config.setPosDeviceType(CssPosDeviceType.getDefalutType());
			config.setServiceHall(serviceHall);
			config.setServiceHallId(serviceHall.getServiceHallId());
			config.setCreateTime(CssUtil.getNowDateTimeStrWithT());
			configs.add(config);
		});
		return configs;
	}

	@Transactional(rollbackFor=ManagerException.class)
	@Override
	public ServiceHallDeviceConfig delete(String id) throws ManagerException {
		ServiceHallDeviceConfig config = configRepo.findById(id).get();
		if(config==null)
			throw new ManagerException("未找到此配置，请刷新页面查看");
		configRepo.delete(config);
		// 去除serviceHall外键
		ServiceHall serviceHall = serviceHallRepo.findByServiceHallId(config.getServiceHallId());
		if(serviceHall!=null){
			serviceHall.setConfig(null);
			serviceHallRepo.save(serviceHall);
		}
		return config;
	}
	@Transactional(rollbackFor=ManagerException.class)
	@Override
	public ServiceHallDeviceConfig edit(ServiceHallDeviceConfigEditModel configToEdit) throws ManagerException {
		configToEdit.valid();
		ServiceHallDeviceConfig config = toEntity(configToEdit);
		configRepo.save(config);
		setupServiceHallFk(config);
		return config;
	}

	private ServiceHallDeviceConfig toEntity(ServiceHallDeviceConfigEditModel configToEdit) {
		ServiceHallDeviceConfig config = configRepo.findById(configToEdit.getId()).get();
		// 编辑配置时 保持原网点编号
		BeanTools.copyProperties(configToEdit, config, new String[]{"serviceHallId","serviceHall","createTime"});
		config.setUpdateTime(CssUtil.getNowDateTimeStrWithT());
		return config;
	}

	@Override
	public ServiceHallDeviceConfig findByServiceHallId(String serviceHallId) {
		ServiceHallDeviceConfig config = configRepo.findByServiceHallId(serviceHallId);
		return config;
	}

}

