
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.manager.util.FileWriter;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingRequest;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.ApplicationRepairPng;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.repo.jpa.ApplicationRepairPngRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;


@Service
public class HalfauditingManagerImpl extends AbstractDsiCommManager implements HalfauditingManager{

	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private ApplicationRepairPngRepo applicationRepairPngRepo;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Override
	public LargePagination<ChargeDetail> queryPage(HalfauditingRequest queryModel,User user) throws ManagerException {
		if(user.getRole().isSystem()==false) {
			if(queryModel.getCardId()!=null) {
				CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
				if(cardInfo==null) {
					throw new ManagerException("查无此卡信息!");
				}
				String agencyId=user.getStaff().getServiceHall().getAgencyId();
				boolean falg =false;
				try {
					falg = agencyVarifyManager.varifyAgency(user, cardInfo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ManagerException("渠道校验失败："+e.getMessage());
				}
				if(falg) {
					queryModel.setAgencyId(agencyId);
				}else {
					throw new ManagerException("当前渠道无权操作此卡");
				}
			}else {
				throw new ManagerException("请输入要查询的卡号");
			}
			}
		return chargeDetailRepo.largePage(queryModel);
	}

	@Override
	public String updateStatus(String chargeId) {
		String info="";
		if(chargeId!=null) {
			ChargeDetail chargeDetail=chargeDetailRepo.findByChargeId(chargeId);
			if(chargeDetail!=null) {
				// 状态码为8时 为申请审核半条状态        此时也无法继续圈存  需由相关人员确认后才可进行下笔圈存
				chargeDetail.setStatus(8);
				chargeDetailRepo.save(chargeDetail);
				info="申请审核成功，请等待业务人员处理";
				return  info;
			}
			info="此条流水不存在";
			return info;
		}
		info="请选择要申请审核的流水";
		return info;
	}
	
	
	public  HalfauditingResponse savePng(MultipartFile file,String chargeId) throws ManagerException {
			HalfauditingResponse response =new HalfauditingResponse();
			ApplicationRepairPng applicationRepairPng = new ApplicationRepairPng();
			String parentDirRelativePath = MyFinals.HALFAUDITING_IMG+File.separator+chargeId;
			String fileAbsolutePath = FileWriter.savePic(file,applicationRepairPng, parentDirRelativePath);
			applicationRepairPng.setChargeId(chargeId);
			applicationRepairPng.setFilePath(fileAbsolutePath);
			applicationRepairPng.setFileName(FileWriter.generateFileName(applicationRepairPng,file));
			applicationRepairPng.setInsertTime(QTKUtils.getDateString());
			applicationRepairPngRepo.save(applicationRepairPng);
			response.setMessage("上传成功");
			return response;
	}

}

