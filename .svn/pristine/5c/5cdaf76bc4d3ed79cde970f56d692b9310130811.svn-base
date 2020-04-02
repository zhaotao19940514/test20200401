package cn.com.taiji.css.manager.administration.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.model.administration.inventory.CssCardRequest;
import cn.com.taiji.css.model.administration.inventory.DeviceCardModelRequest;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.CssCardInfo;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;
import cn.com.taiji.qtk.repo.jpa.CssCardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoBatchRepo;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoBatchRepo;

@Service("deviceCardModelManager")
public class DeviceCardModelManagerImpl extends AbstractManager implements DeviceCardModelManager {
	@Autowired
	private CssCardInfoRepo cssCardInfoRepo;
	@Autowired
	private StorageCardInfoBatchRepo storageCardInfoBatchRepo;
	@Autowired
	private StorageObuInfoBatchRepo storageObuInfoBatchRepo;
	@Override
	public Pagination queryPage(DeviceCardModelRequest queryModel) {
		return cssCardInfoRepo.page(queryModel);
	}

	@Override
	public CssCardInfo add(CssCardInfo cardModel) {
		validate(cardModel);
		CssCardInfo card = new CssCardInfo();
		BeanTools.copyProperties(cardModel, card, new String[] { "id" });
		card.setHandleDate(QTKUtils.getDateString());
		return cssCardInfoRepo.save(card);
	}

	private void validate(CssCardInfo card) {
		MyViolationException mve = new MyViolationException();
		if (card.getBrand() == null)
			mve.addViolation("brand", "品牌为空");
		if(card.getRemarks()!=null) {
			if(card.getRemarks().length()>50)
				mve.addViolation("remarks", "备注内容长度应在50以内");
		}
		if (card.getType() == null)
			mve.addViolation("type", "类型为空");
		if(card.getType()!= null) {
			if(card.getType().equals("3")) {
				if (card.getModel() == null)
					mve.addViolation("model", "电子标签设备型号为空");
			}
		}
		if (mve.hasViolation()) {
			throw mve;
		}
	}
	private void validate1(CssCardRequest card) {
		MyViolationException mve = new MyViolationException();
		if (card.getBrand() == null)
			mve.addViolation("brand", "品牌为空");
		if (card.getModel() == null)
			mve.addViolation("model", "设备型号为空");
		if(card.getRemarks()!=null) {
			if(card.getRemarks().length()>50)
				mve.addViolation("remarks", "备注内容长度应在50以内");
		}
		if (card.getType() == null)
			mve.addViolation("type", "类型为空");
		if (mve.hasViolation()) {
			throw mve;
		}
	}
	@Override
	public CssCardInfo findById1(String id) {
		return cssCardInfoRepo.findByIds(id);
	}

	@Override
	public CssCardInfo updateCssCardInfo(CssCardRequest cardModel) throws ManagerException {
		validate1(cardModel);
		String[] brand = cardModel.getBrand().split(",");
		String type = cardModel.getType();
		if("3".equals(type)) {
			cardModel.setBrand(brand[1]);
		}else {
			cardModel.setBrand(brand[0]);
		}
		CssCardInfo dbCard = cssCardInfoRepo.findByIds(cardModel.getId());
		if (null == dbCard)
			throw new ManagerException("未找到该黔通卡设备信息");
		
		BeanTools.copyProperties(cardModel, dbCard);
		dbCard.setHandleDate(QTKUtils.getDateString());
		return cssCardInfoRepo.save(dbCard);
	}

	@Override
	public void delete(String id) throws ManagerException {
		CssCardInfo card = findById1(id);
		if(card.getBrand().equals("1")||card.getBrand().equals("2")
				||card.getBrand().equals("3")||card.getBrand().equals("4")
				||card.getBrand().equals("5")||card.getBrand().equals("6")
				||card.getBrand().equals("7")||card.getBrand().equals("8")
				||card.getBrand().equals("9")||card.getBrand().equals("10")
				||card.getBrand().equals("11")||card.getBrand().equals("12")) {
//			List<StorageCardInfoBatch> scbr = storageCardInfoBatchRepo.listByTypeAndBrand(card.getBrand());
			List<StorageCardInfoBatch> ktp = null;
			if(card.getType().equals("1")) {
				ktp = storageCardInfoBatchRepo.listByCZType(card.getBrand());
			}else if(card.getType().equals("2")) {
				ktp = storageCardInfoBatchRepo.listByJZType(card.getBrand());
			}
			if(ktp.size()>0&&ktp != null) {
				throw new ManagerException("该卡片设备已使用不允许删除");
			}
		}else {
			List<StorageObuInfoBatch> sobr = storageObuInfoBatchRepo.listByTypeAndBrand(card.getBrand());
			if(sobr.size()>0) {
				throw new ManagerException("该设备已使用不允许删除");
			}
		}
		cssCardInfoRepo.delete(card);
	}

}
