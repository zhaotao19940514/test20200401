package cn.com.taiji.css.manager.apply.quickapply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.BatchIssueBaseInfo;
import cn.com.taiji.css.entity.dict.BatchIssueStatus;
import cn.com.taiji.css.manager.qtzt.QtztInterfaceJsonManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.qtzt.QtztJsonRequest;
import cn.com.taiji.css.model.qtzt.request.IssueOrderRequestData;
import cn.com.taiji.css.model.qtzt.response.IssueOrderResponse;
import cn.com.taiji.css.model.qtzt.response.IssueOrderResponseData;
import cn.com.taiji.css.repo.jpa.BatchIssueBaseInfoRepo;

@Service
public class BatchIssueManagerImpl extends AbstractManager implements BatchIssueManager {
	@Autowired
	private QtztInterfaceJsonManager qtztInterfaceJsonManager;
	@Autowired
	private BatchIssueBaseInfoRepo batchIssueBaseInfoRepo;
	@Override
	public void batchIssue() {
		List<BatchIssueBaseInfo> list = batchIssueBaseInfoRepo.listByStatusAscTime(BatchIssueStatus.WAITHANDLE);
		if(list == null || list.size() <= 0) {
			return;
		}
		List<BatchIssueBaseInfo> updateList = Lists.newArrayList();
		for (BatchIssueBaseInfo temp : list) {
			IssueOrderRequestData reqData = new IssueOrderRequestData();
			dataProcess(temp, reqData);
			try {
				IssueOrderResponseData order = issueOrder(reqData);
				if("0000".equals(order.getRespCode())) {
					temp.setStatus(BatchIssueStatus.SUCCESS);
				}else {
					temp.setStatus(BatchIssueStatus.FAIL);
				}
				temp.setUpdateTime(CssUtil.getNowDateTimeStrWithoutT());
				temp.setRespCode(order.getRespCode());
				temp.setRespMessage(order.getRespMessage());
				temp.setFinishId(order.getFinishId());
				updateList.add(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(updateList.size() == 10) {
				batchIssueBaseInfoRepo.saveAll(updateList);
				updateList = Lists.newArrayList();
			}
		}
		if(updateList.size() > 0) {
			batchIssueBaseInfoRepo.saveAll(updateList);
		}
		
	}
	private void dataProcess(BatchIssueBaseInfo model, IssueOrderRequestData reqData) {
		reqData.setPackageNum(5);
		model.setPackageNum(5);
		reqData.setBusinessType(1);
		model.setBusinessType(1);
		reqData.setOrderType(1);
		model.setOrderType(1);
		reqData.setIsOnline(2);
		model.setIsOnline(2);
		reqData.setUserType(model.getUserType());
		reqData.setUserName(model.getUserName());
		reqData.setIdentNo(model.getIdentNo());
		reqData.setIdentType(model.getIdentType());
		reqData.setAddress(model.getAddress());
		reqData.setUserMobile(model.getUserMobile());
		reqData.setDepartment(model.getDepartment());
		reqData.setAgentName(model.getAgentName());
		reqData.setAgentIdType(model.getAgentIdType());
		reqData.setAgentIdNum(model.getAgentIdNum());
		reqData.setVehiclePlate(model.getVehiclePlate());
		reqData.setVehiclePlateColor(model.getVehiclePlateColor());
		reqData.setVehicleType(model.getVehicleType());
		reqData.setVehicleOwner(model.getVehicleOwner());
		reqData.setOwnerName(model.getOwnerName());
		reqData.setOwnerIdType(model.getOwnerIdType());
		reqData.setOwnerIdNum(model.getOwnerIdNum());
		reqData.setOwnerTel(model.getOwnerTel());
		reqData.setType(model.getType());
		reqData.setUseCharacter(model.getUseCharacter());
		reqData.setRegisterDate(model.getRegisterDate());
		reqData.setIssueDate(model.getIssueDate());
		reqData.setVin(model.getVin());
		reqData.setEngineNum(model.getEngineNum());
		reqData.setFileNum(model.getFileNum());
		reqData.setApprovedCount(model.getApprovedCount());
		reqData.setTotalMass(model.getTotalMass());
		reqData.setMaintenanceMass(model.getMaintenanceMass());
		reqData.setPermittedWeight(model.getPermittedWeight());
		reqData.setOutsideDimensions(model.getOutsideDimensions());
		reqData.setPermittedTowWeight(model.getPermittedTowWeight());
		reqData.setTestRecord(model.getTestRecord());
		reqData.setWheelCount(model.getWheelCount());
		reqData.setAxleCount(model.getAxleCount());
		reqData.setAxleDistance(model.getAxleDistance());
		reqData.setAxisType(model.getAxisType());
		reqData.setOrderNo(model.getOrderNo());
		reqData.setPostal(model.getPostal());
		reqData.setReceiverMobile(model.getReceiverMobile());
		reqData.setReceiverName(model.getReceiverName());
		reqData.setProvince(model.getProvince());
		reqData.setCity(model.getCity());
		reqData.setCounty(model.getCounty());
		reqData.setDistrict(model.getDistrict());
		reqData.setDetailAddress(model.getDetailAddress());
		reqData.setRemarks(model.getRemarks());
		reqData.setAgencyId(model.getAgencyId());
	}
	@Override
	public IssueOrderResponseData issueOrder(IssueOrderRequestData reqData) throws ManagerException {
		QtztJsonRequest req = new QtztJsonRequest();
		req.setData(reqData);
		req.setAgentId(reqData.getAgencyId());
		IssueOrderResponse res = (IssueOrderResponse) qtztInterfaceJsonManager.sendQtzt(req,IssueOrderResponse.class);
		if(res != null && res.getRcode() != null && res.getRcode() == 0 && res.getData() != null) {
			return res.getData();
		}else {
			throw new ManagerException("汇联通发行订单创建请求失败！" + (res == null ? "":("响应错误信息：" +res.getRmsg())));
		}
	}
	private void saveCommParam(QtztJsonRequest req) {
		req.setTerminalId("020000000000");
		req.setStaffId("zyyt01");
		req.setAgentId("52010188037");
		req.setChannelId("5201018803701150001");
		req.setChannelType(2);
	}
}
