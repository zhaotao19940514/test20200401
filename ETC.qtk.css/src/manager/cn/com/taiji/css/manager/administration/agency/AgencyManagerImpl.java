/**
 * @Title AgencyManagerImpl.java
 * @Package cn.com.taiji.css.model.administration.agency
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月28日 下午2:48:57
 * @version V1.0
 */
package cn.com.taiji.css.manager.administration.agency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.model.request.agency.AgencyRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.dict.AgencyType;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;

/**
 * @ClassName AgencyManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年08月28日 14:48:57
 * @E_mail yaonanlin@163.com
 */
@Service
public class AgencyManagerImpl extends AbstractManager implements AgencyManager {

	@Autowired
	private AgencyRepo agencyRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;

	@Override
	public Pagination page(AgencyRequest req) {
		return agencyRepo.page(req);
	}

	@Transactional(rollbackFor = ManagerException.class)
	@Override
	public Agency add(Agency agencyToAdd) throws ManagerException {
		validAgency(agencyToAdd);
		dbValid(agencyToAdd);
		Agency agency = toEntity(agencyToAdd);
		agencyRepo.save(agency);
		return agency;
	}

	private void dbValid(Agency agency) throws ManagerException {
		Agency dbAgency = agencyRepo.findByAgencyId(agency.getAgencyId());
		if (dbAgency != null)
			throw new ManagerException("机构编号:" + agency.getAgencyId() + " 信息已存在");
	}

	private Agency toEntity(Agency agencyToAdd) throws ManagerException {
		Agency agency = new Agency();
		BeanTools.copyProperties(agencyToAdd, agency, new String[] { "id" });
		String agencyId = agency.getAgencyId();
		agency.setIssuerId(agencyId.substring(0, 6));
		String typeCode = agencyId.substring(6, 8);
		if(AgencyType.exist(typeCode)){
			agency.setType(AgencyType.from(typeCode));
		}else{
			throw new ManagerException("机构类型编码错误 无此类型："+ typeCode);
		}
		if(agencyToAdd.getAccountId()==null || agencyToAdd.getAccountId()=="") {
			agency.setAccountId(agencyId);
		}else {
			agency.setAccountId(agencyToAdd.getAccountId());
		}
		return agency;
	}

	private void validAgency(Agency agency) {
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(agency.getAgencyId())){
			mve.addViolation("agencyId", "合作机构编号不能为空");
		}else if(!agency.getAgencyId().startsWith("520101")){
			mve.addViolation("agencyId", "贵州合作机构编号应以520101开头");
		}
		if (StringTools.hasText(agency.getAgencyId()) && agency.getAgencyId().length() != 11)
			mve.addViolation("agencyId", "长度错误");
		if (!StringTools.hasText(agency.getContact()))
			mve.addViolation("contact", "机构联系人名称不能为空");
		if (!StringTools.hasText(agency.getName()))
			mve.addViolation("name", "合作机构名称不能为空");
		if (!StringTools.hasText(agency.getTel()))
			mve.addViolation("tel", "机构联系人电话不能为空");
		if (!StringTools.hasText(agency.getFileDir()))
			mve.addViolation("fileDir", "系统目录设置不能为空");
		if (mve.hasViolation())
			throw mve;
	}

	@Override
	public Agency findById(String id) {
		Agency agency = agencyRepo.findById(id).orElse(null);
		return agency;
	}

	@Transactional(rollbackFor = ManagerException.class)
	@Override
	public Agency edit(Agency agencyToEdit) throws ManagerException {
		if (!StringTools.hasText(agencyToEdit.getId()))
			throw new ManagerException("id值不能为空");
		validAgency(agencyToEdit);
		Agency dbAgency = validAgencyId(agencyToEdit);
		setServiceHallInBatch(agencyToEdit,dbAgency);
		agencyRepo.save(agencyToEdit);
		return agencyToEdit;
	}
	// 联动批量修改网点冗余字段 
	private void setServiceHallInBatch(Agency agencyToEdit, Agency dbAgency) {
		if(!dbAgency.getAgencyId().equals(agencyToEdit.getAgencyId())){
			List<ServiceHall> serviceHalls = serviceHallRepo.listByAgentId(dbAgency.getAgencyId());
			serviceHalls.forEach(serviceHall->{
				serviceHall.setAgencyId(agencyToEdit.getAgencyId());
			});
			serviceHallRepo.saveAll(serviceHalls);
		}
		if(!dbAgency.getFileDir().equals(agencyToEdit.getFileDir())){
			List<ServiceHall> serviceHalls = serviceHallRepo.listByAgentId(agencyToEdit.getAgencyId());
			serviceHalls.forEach(serviceHall->{
				serviceHall.setAgencyCode(agencyToEdit.getFileDir());
			});
			serviceHallRepo.saveAll(serviceHalls);
		}
		if(!dbAgency.getName().equals(agencyToEdit.getName())){
			List<ServiceHall> serviceHalls = serviceHallRepo.listByAgentId(agencyToEdit.getAgencyId());
			serviceHalls.forEach(serviceHall->{
				serviceHall.setAgencyName(agencyToEdit.getName());
			});
			serviceHallRepo.saveAll(serviceHalls);
		}
	}

	private Agency validAgencyId(Agency agencyToEdit) throws ManagerException {
		Agency findById = agencyRepo.findById(agencyToEdit.getId()).orElse(null);
		if(!findById.getAgencyId().equals(agencyToEdit.getAgencyId())){
			Agency findByAgencyId = agencyRepo.findByAgencyId(agencyToEdit.getAgencyId());
			if(findByAgencyId!=null){
				throw new ManagerException("已有此机构编号:"+agencyToEdit.getAgencyId()+" ,无法修改");
			}
		}
		return findById;
	}

	@Transactional(rollbackFor = ManagerException.class)
	@Override
	public void delete(Agency agency) throws ManagerException {
		AssertUtil.notNull(agency);
		AssertUtil.hasText(agency.getAgencyId());
		List<ServiceHall> serviceHalls = serviceHallRepo.listByAgentId(agency.getAgencyId());
		if(serviceHalls!=null && serviceHalls.size()>0)
			throw new ManagerException("该合作机构下有服务网点，无法删除！请先删除该机构下所有服务网点");
		agencyRepo.delete(agency);
	}

	@Override
	public List<Agency> findAll() {
		return agencyRepo.findAll();
	}

	@Override
	public List<Agency> queryByName(String name) {
		List<Agency> list = agencyRepo.listByName(name);
		return list;
	}

	@Override
	public Agency findAccount(String accountId) {
		Agency account = agencyRepo.findByAgencyId(accountId);
		return account;
	}
}
