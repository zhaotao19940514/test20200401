/**
 * @Title StaffManager.java
 * @Package cn.com.taiji.css.manager.staff
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月30日 上午11:16:03
 * @version V1.0
 */
package cn.com.taiji.css.manager.staff;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.administration.staff.StaffModel;
import cn.com.taiji.css.model.request.staff.StaffRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.Staff;

/**
 * @ClassName StaffManager
 * @Description TODO
 * @author yaonl
 * @date 2018年08月30日 11:16:03
 * @E_mail yaonanlin@163.com
 */
public interface StaffManager {
	Pagination page(StaffRequest req);

	Staff add(StaffModel staffToAdd) throws ManagerException;

	Staff findById(String id);

	Staff edit(StaffModel staffToEdit) throws ManagerException;

	void delete(Staff staff);

	StaffModel findByIdForEdit(String id);

	StaffModel addModel(StaffModel staffModel);

	Agency findbyAgencyId(String agencyId);
}

