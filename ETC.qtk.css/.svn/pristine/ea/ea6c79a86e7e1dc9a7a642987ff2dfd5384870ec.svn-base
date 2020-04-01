/**
 * @Title StaffManagerImpl.java
 * @Package cn.com.taiji.css.manager.staff
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月30日 上午11:16:13
 * @version V1.0
 */
package cn.com.taiji.css.manager.staff;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.model.administration.staff.StaffModel;
import cn.com.taiji.css.model.request.staff.StaffRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.Staff;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StaffRepo;

/**
 * @ClassName StaffManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年08月30日 11:16:13
 * @E_mail yaonanlin@163.com
 */
@Service
public class StaffManagerImpl extends AbstractManager implements StaffManager {
	@Autowired
	private StaffRepo staffRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private AgencyRepo agencyRepo;
	@Override
	public Pagination page(StaffRequest req) {
		return staffRepo.page(req);
	}

	@Transactional
	@Override
	public Staff add(StaffModel staffToAdd) throws ManagerException {
		addValidStaff(staffToAdd);
		dbValid(staffToAdd);
		Staff staff = toAddEntity(staffToAdd);
		staffRepo.save(staff);
		return staff;
	}

	private void dbValid(StaffModel staff) throws ManagerException {
		Staff dbStaff = staffRepo.findByStaffId(staff.getStaffId());
		if (dbStaff != null)
			throw new ManagerException("工号:" + staff.getStaffId() + " 信息已存在");
	}

	private Staff toAddEntity(StaffModel staffToAdd) throws ManagerException {
		Staff staff = new Staff();
		ServiceHall serviceHall = serviceHallRepo.findByServiceHallId(staffToAdd.getServiceHallId());
		if(serviceHall==null)
			throw new ManagerException("未找到网点:"+staffToAdd.getServiceHallId());
		BeanTools.copyProperties(staffToAdd, staff, new String[] { "id","staffId" });
		staff.setStaffId(staffToAdd.getStaffId().trim());
		staff.setAgencyId(serviceHall.getAgencyId());
		staff.setPassWord(staffToAdd.getPassword());
		staff.setStaffType(1);
		staff.setStatus(1);
		staff.setServiceHall(serviceHall);
		staff.setCreateTime(Calendar.getInstance());
		staff.setReleaseDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		return staff;
	}

	private void addValidStaff(StaffModel model) {
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(model.getServiceHallId())){
			mve.addViolation("serviceHallId", "服务网点不能为空");
		}
		if (!StringTools.hasText(model.getStaffId())){
			mve.addViolation("staffId", "工号不能为空");
		}
		if (!StringTools.hasText(model.getStaffName())){
			mve.addViolation("staffName", "员工姓名不能为空");
		}
		if (!StringTools.hasText(model.getPassword())){
			System.out.println("password : " + model.getPassword());
			mve.addViolation("password", "密码不能为空");
		}
		if (!StringTools.hasText(model.getConfirmPassword())){
			mve.addViolation("confirmPassword", "确认密码不能为空");
		}
		if(StringTools.hasText(model.getPassword()) && StringTools.hasText(model.getConfirmPassword())
				&& !model.getPassword().equals(model.getConfirmPassword())){
			mve.addViolation("password", "密码不匹配");
			mve.addViolation("confirmPassword", "密码不匹配");
		}
		if (mve.hasViolation()){
			throw mve;
		}
	}

	@Override
	public Staff findById(String id) {
		Optional<Staff> staffOp = staffRepo.findById(id);
		return staffOp.get();
	}

	@Transactional
	@Override
	public Staff edit(StaffModel staffToEdit) throws ManagerException {
		if (!StringTools.hasText(staffToEdit.getId()))
			throw new ManagerException("id值不能为空");
		validEditStaff(staffToEdit);
		Staff dbStaff = validStaffId(staffToEdit);
		Staff staff = toEditEntity(staffToEdit,dbStaff);
		staffRepo.save(staff);
		return staff;
	}

	private Staff validStaffId(StaffModel staffToEdit) throws ManagerException {
		// 库内原记录
		Staff dbStaff = staffRepo.findById(staffToEdit.getId()).get();
		// 若修改了staffId
		if(!staffToEdit.getStaffId().equals(dbStaff.getStaffId())){
			Staff findByStaffId = staffRepo.findByStaffId(staffToEdit.getStaffId());
			if(findByStaffId!=null) throw new ManagerException("已有此工号:"+staffToEdit.getStaffId()+" ,无法修改");
		}
		return dbStaff;
	}
	// 返回是否修改密码
	private void validEditStaff(StaffModel model) {
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(model.getServiceHallId())){
			mve.addViolation("serviceHallId", "服务网点不能为空");
		}
		if (!StringTools.hasText(model.getStaffId())){
			mve.addViolation("staffId", "工号不能为空");
		}
		if (!StringTools.hasText(model.getStaffName())){
			mve.addViolation("staffName", "员工姓名不能为空");
		}
		if(StringTools.hasText(model.getPassword()) ||
				StringTools.hasText(model.getConfirmPassword()) ||
				StringTools.hasText(model.getOriginPassword())){
			if (!StringTools.hasText(model.getOriginPassword())){
				mve.addViolation("originPassword", "原密码不能为空");
			}else{
				Optional<Staff> staffOp = staffRepo.findById(model.getId());
				Staff staff = staffOp.get();
				if(!model.getOriginPassword().equals(staff.getPassWord())){
					mve.addViolation("originPassword", "原密码错误");
				}
			}
			if (!StringTools.hasText(model.getPassword())){
				mve.addViolation("password", "新密码不能为空");
			}
			if (!StringTools.hasText(model.getConfirmPassword())){
				mve.addViolation("confirmPassword", "新确认密码不能为空");
			}
			if(StringTools.hasText(model.getPassword()) && StringTools.hasText(model.getConfirmPassword())
					&& !model.getPassword().equals(model.getConfirmPassword())){
				mve.addViolation("password", "密码不匹配");
				mve.addViolation("confirmPassword", "密码不匹配");
			}
		}
		if (mve.hasViolation()){
			throw mve;
		}
	}
	
	private Staff toEditEntity(StaffModel staffToEdit,Staff dbStaff) throws ManagerException {
		Staff staff = new Staff();
		ServiceHall serviceHall = serviceHallRepo.findByServiceHallId(staffToEdit.getServiceHallId());
		if(serviceHall==null)
			throw new ManagerException("未找到网点:"+staffToEdit.getServiceHallId());
		Agency account = agencyRepo.findByAgencyId(staffToEdit.getAccountId());
		if(account==null)
			throw new ManagerException("未找到资金渠道:"+staffToEdit.getAccountId());
		// 页面三个密码全空时不修改密码
		BeanTools.copyProperties(staffToEdit, staff);
		if(!StringTools.hasText(staffToEdit.getOriginPassword())
				&& !StringTools.hasText(staffToEdit.getPassword())
				&& !StringTools.hasText(staffToEdit.getConfirmPassword())){
			// 设置密码为原密码
			staff.setPassWord(dbStaff.getPassWord());
		}else{
			// 设置密码为新密码
			staff.setPassWord(staffToEdit.getPassword());// 实体密码属性拼写有问题。。。 passWord 导致未copy到 手动设置
		}
		staff.setAgencyId(serviceHall.getAgencyId());//发行渠道编号
		staff.setAccountId(staffToEdit.getAccountId());//资金渠道编号
		staff.setStaffType(1);
		staff.setStatus(1);
		staff.setServiceHall(serviceHall);
		staff.setCreateTime(Calendar.getInstance());
		staff.setReleaseDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		return staff;
	}

	@Transactional
	@Override
	public void delete(Staff staff) {
		staffRepo.delete(staff);
	}

	@Override
	public StaffModel findByIdForEdit(String id) {
		Optional<Staff> staffOp = staffRepo.findById(id);
		Staff staff = staffOp.get();
		return toEditModel(staff); 
	}
	
	@Override
	public StaffModel addModel(StaffModel model) {
		//页面展现资金渠道
		Agency account=agencyRepo.findByAgencyId(model.getAccountId());
		model.setAccount(account);
		return model; 
	}

	private StaffModel toEditModel(Staff staff) {
		StaffModel model = new StaffModel();
		BeanTools.copyProperties(staff, model);
		model.setConfirmPassword("");
		model.setPassword("");
		model.setOriginPassword("");
		model.setServiceHall(staff.getServiceHall());
		return model;
	}

	@Override
	public Agency findbyAgencyId(String agencyId) {
		Agency agency = agencyRepo.findByAgencyId(agencyId);
		return agency;
	}
	
}

