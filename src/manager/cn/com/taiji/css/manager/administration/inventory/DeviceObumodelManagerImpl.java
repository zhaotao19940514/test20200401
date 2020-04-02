package cn.com.taiji.css.manager.administration.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.model.administration.inventory.DevicemodelRequest;
import cn.com.taiji.qtk.entity.CssObuInfo;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;
import cn.com.taiji.qtk.repo.jpa.CssObuInfoRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoBatchRepo;

@Service("devicemodelManager")
public class DeviceObumodelManagerImpl extends AbstractManager implements DeviceObumodelManager {
	@Autowired
	private CssObuInfoRepo cssObuInfoRepo;
	@Autowired
	private StorageObuInfoBatchRepo storageObuInfoBatchRepo;

	@Override
	public Pagination queryPage(DevicemodelRequest queryModel) {
		return cssObuInfoRepo.page(queryModel);
	}

	@Override
	public CssObuInfo add(CssObuInfo obuModel) {
		validate(obuModel);
		CssObuInfo obu = new CssObuInfo();
		BeanTools.copyProperties(obuModel, obu, new String[] { "id" });
		return cssObuInfoRepo.save(obu);
	}

	@Override
	public CssObuInfo findById1(String id) {
		return cssObuInfoRepo.findByIds(id);
	}

	@Override
	public CssObuInfo updateCssObuInfo(CssObuInfo obuModel) throws ManagerException {
		AssertUtil.notNull(obuModel.getId());
		validate(obuModel);
		CssObuInfo dbobu = cssObuInfoRepo.findByIds(obuModel.getId());
		if (null == dbobu)
			throw new ManagerException("未找到该obu设备信息");
		BeanTools.copyProperties(obuModel, dbobu);
		return cssObuInfoRepo.save(dbobu);

	}

	private void validate(CssObuInfo obu) {
		MyViolationException mve = new MyViolationException();
		if (obu.getBrand() == null)
			mve.addViolation("brand", "obu品牌为空");
		if (obu.getModel() == null)
			mve.addViolation("model", "obu型号为空");
		if (obu.getRemarks().length()>50)
			mve.addViolation("remarks", "obu备注内容长度应该在50 以内");
		if (obu.getType() == null)
			mve.addViolation("type", "obu类型为空");
		if (mve.hasViolation()) {
			throw mve;
		}
	}

	@Override
	public void delete(String id) throws ManagerException {
		CssObuInfo obu = findById1(id);
		List<StorageObuInfoBatch> sobr = storageObuInfoBatchRepo.listByTypeAndBrand(obu.getBrand());
		if(sobr.size()>0) {
			throw new ManagerException("该设备已使用不允许删除");
		}
		cssObuInfoRepo.delete(obu);
	}

}
