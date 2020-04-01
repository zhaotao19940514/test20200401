package cn.com.taiji.css.manager.apply.quickapply;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.YangCheng;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.repo.jpa.YangChengRepo;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.manager.comm.client.ValidationBinService;
import cn.com.taiji.dsi.model.comm.protocol.CommQtkRequset;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUConfirmRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUConfirmResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.VehicleInfoSubmitRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.VehicleInfoSubmitResponse;
import cn.com.taiji.dsi.model.comm.protocol.validation.PlateCheckRequest;
import cn.com.taiji.dsi.model.comm.protocol.validation.PlateCheckResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;

@Service
public class BatchOpenCardObuManagerImpl extends AbstractManager implements BatchOpenCardObuManager {

	 @Autowired
	 @Qualifier("myExecutor")
	 protected ThreadPoolTaskExecutor executor; 
	 @Autowired
	 private VehicleInfoRepo vehicleInfoRepo;
	 @Autowired
	 private ReleaseBinService releaseBinService;
	 @Autowired
	 private ValidationBinService validationBinService;
	 @Autowired
	 private YangChengRepo yangChengRepo;
	 @Override
	 public void batchOpen() {
		
		/*
		 * 获取车辆信息(建表 + 实体 + 查询方法)
		 * 创建起始卡号
		 for(){ 线程{ 0.车辆查询 1.车辆提交 2.车牌校验 3.开卡申请 4.开卡确认 5.开签申请 6.开签确认 7.安装确认 }
		 * 卡号++
		 *  }
		 */
		try {
			List<YangCheng> vehicleList =yangChengRepo.findAll();
//			List<String> vehicleInfoAll = vehicleInfoRepo.listVehicleId();
//			 获取车辆信息(建表 + 实体 + 查询方法)
//			52011640230280081900	5202198000081624
			int j=0;
//			创建起始卡号
			Long startCardId=11640230280081900L;
			Long startObuId =5202198000081624L;
			int partitionSize = QTKUtils.getPartitionSize(vehicleList.size(), 5);
			List<List<YangCheng>> partition = Lists.partition(vehicleList, partitionSize);
			List<String> loseVehicleId=new ArrayList<String>();
		   for (List<YangCheng> vehicleinfoList : partition) {
		    for (YangCheng vehicleInfo : vehicleinfoList) {
		    	long time1=System.currentTimeMillis();
		      try {
//		    	  0.车辆查询    如果已存在 则跳过车辆提交接口  否则调用车辆提交
//		    	  if(!vehicleInfoAll.contains(vehicleInfo.getVehicleId())) {
//		    		1.车辆提交
		    		  VehicleInfoSubmitRequest req =new VehicleInfoSubmitRequest();
		    		    commSet(req);
		    		    req.setOperation(1);
		    			req.setVehiclePlate(vehicleInfo.getVehiclePlate());
		    			req.setVehiclePlateColor(vehicleInfo.getVehiclePlateColor());
		    			req.setUserId(vehicleInfo.getCustomerId());
		    			req.setType(vehicleInfo.getType());
		    			req.setOwnerName(vehicleInfo.getOwnerName());
		    			req.setOwnerIdType(vehicleInfo.getOwnerIdType());
		    			req.setOwnerIdNum(vehicleInfo.getOwnerIdNum());
		    			req.setOwnerTel(vehicleInfo.getOwnerTel());
		    			req.setAddress(vehicleInfo.getOwnerAddress());
		    			req.setContact(vehicleInfo.getContacts());
		    			req.setVehicleType(vehicleInfo.getVehicleType());
		    			req.setVehicleModel(vehicleInfo.getVehicleModel());
		    			req.setUseCharacter(vehicleInfo.getUseCharacter());
		    			req.setVin(vehicleInfo.getVIN());
		    			req.setEngineNum(vehicleInfo.getEngineNum());
		    			req.setRegisterDate(vehicleInfo.getRegisterDate());
		    			req.setIssueDate(vehicleInfo.getIssueDate());
		    			req.setFileNum(vehicleInfo.getFileNum());
		    			req.setApprovedCount(vehicleInfo.getApprovedCount());
		    			req.setTotalMass(vehicleInfo.getTotalMass());
		    			req.setMaintenanceMass(vehicleInfo.getMaintenanceMass());
		    			req.setPermittedWeight(vehicleInfo.getPermittedWeight());
		    			req.setOutsideDimensions(vehicleInfo.getOutsideDimensions());
		    			req.setPermittedTowWeight(vehicleInfo.getPermittedTowWeight());
		    			req.setTestRecord(vehicleInfo.getTestRecord());
		    			req.setWheelCount(vehicleInfo.getWheelCount());
		    			req.setAxleCount(vehicleInfo.getAxleCount());
		    			req.setAxleDistance(vehicleInfo.getAxleDistance());
		    			req.setAxisType(vehicleInfo.getAxisType());
		    			req.setRefrigeratedTrucks(vehicleInfo.getRefrigeratedTrucks());
		    			req.setEmergencyFlag(vehicleInfo.getEmergencyFlag());
		    			VehicleInfoSubmitResponse vehicleInfoSubmitV2 = releaseBinService.vehicleInfoSubmitV2(req);
		    			System.out.println("vehicleInfoSubmitV2"+vehicleInfoSubmitV2);
		    			if(vehicleInfoSubmitV2.getStatus()!=1) {
		    				System.out.println(vehicleInfoSubmitV2.getMessage());
		    				loseVehicleId.add(vehicleInfo.getVehicleId());
		    				continue;
		    			}
//		    	  }
//		    	  2.车牌校验
		    	  PlateCheckRequest  plateCheckRequest =new PlateCheckRequest();
		    	  commSet(plateCheckRequest);
		    	  plateCheckRequest.setVehiclePlate(vehicleInfo.getVehiclePlate());
		    	  plateCheckRequest.setVehicleColor(vehicleInfo.getVehiclePlateColor());
		    	  plateCheckRequest.setVehicleType(vehicleInfo.getType());
		    	  plateCheckRequest.setReleaseType(0);
		    	  PlateCheckResponse plateCheck = validationBinService.plateCheck(plateCheckRequest);
		    	  System.out.println("plateCheck:"+plateCheck);
		    	  if(!plateCheck.getResult()) {
		    		  	System.out.println(plateCheck.getMessage());
	    				loseVehicleId.add(vehicleInfo.getVehicleId());
	    				continue;
		    	  }
//		    	  3.开卡申请 4.开卡确认 
		    	    String cardId="520"+startCardId.toString();
		    	    LocalDateTime now = LocalDateTime.now();
			  		String enableTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
			  		String expireTime = now.plusYears(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
			  		
			  		CardApplyRequest cardApplyRequest =new CardApplyRequest();
			  		commSet(cardApplyRequest);
			  		cardApplyRequest.setVehicleId(vehicleInfo.getVehicleId());
			  		cardApplyRequest.setCardId(cardId);
//			  		cardApplyRequest.setUserId("52010119101162538");吕轩
//			  		cardApplyRequest.setUserId("52010119101762900");胡祥
			  		cardApplyRequest.setUserId("52010119101851941");//好运达  52010119101851941
			  		cardApplyRequest.setCardType(1);
			  		cardApplyRequest.setCosProvider(0);
			  		CardConfirmRequest cardConfirmRequest=new CardConfirmRequest();
			  		commSet(cardConfirmRequest);
			  		cardConfirmRequest.setCardId(cardId);
			  		cardConfirmRequest.setAccountOrganization("29");
			  		cardConfirmRequest.setNetId("5201");
			  		cardConfirmRequest.setPackageNum(5);
			  		cardConfirmRequest.setCardType(111);
			  		cardConfirmRequest.setBrand(1);
			  		cardConfirmRequest.setModel("0");
			  		cardConfirmRequest.setEnableTime(enableTime);
			  		cardConfirmRequest.setExpireTime(expireTime);
			  		try {
			  			CardApplyResponse cardApply = releaseBinService.cardApply(cardApplyRequest);
			  			System.out.println("CardApplyResponse:"+cardApply);
			  			CardConfirmResponse cardConfirm = releaseBinService.cardConfirm(cardConfirmRequest);
			  			System.out.println("CardConfirmResponse:"+cardConfirm);
			  		} catch (ApiRequestException | IOException e) {
			  			e.printStackTrace();
			  		}
//			  		 5.开签申请 6.开签确认 7.安装确认
			  		String obuExpireTime = now.plusYears(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					OBUApplyRequest OBUApplyRequest =new OBUApplyRequest();
					commSet(OBUApplyRequest);
					OBUApplyRequest.setVehicleId(vehicleInfo.getVehicleId());
//					OBUApplyRequest.setUserId("52010119101762900");胡祥
//					OBUApplyRequest.setUserId("52010119101162538");吕轩
					OBUApplyRequest.setUserId("52010119101851941");//好运达  52010119101851941
					OBUApplyRequest.setObuId(startObuId.toString());
					OBUConfirmRequest OBUConfirmRequest=new OBUConfirmRequest();
					commSet(OBUConfirmRequest);
					OBUConfirmRequest.setObuId(startObuId.toString());
					OBUConfirmRequest.setNetId("5201");
					OBUConfirmRequest.setBrand(1);
					OBUConfirmRequest.setModel("0");
					OBUConfirmRequest.setEnableTime(enableTime);
					OBUConfirmRequest.setExpireTime(obuExpireTime);
					try {
						OBUApplyResponse obuApply = releaseBinService.obuApply(OBUApplyRequest);
						System.out.println("OBUApplyResponse:"+obuApply);
						OBUConfirmResponse obuConfirm = releaseBinService.obuConfirm(OBUConfirmRequest);
						System.out.println("OBUConfirmResponse:"+obuConfirm);
					} catch (ApiRequestException | IOException e) {
						e.printStackTrace();
					}
					InstallConfirmRequest InstallConfirmRequest = new InstallConfirmRequest();
					commSet(InstallConfirmRequest);
					InstallConfirmRequest.setObuId(startObuId.toString());
					InstallConfirmRequest.setInstallStatus(0);
					InstallConfirmRequest.setInstallType(1);
					InstallConfirmRequest.setInstallChannelId("0");
					InstallConfirmResponse installConfirm = releaseBinService.installConfirm(InstallConfirmRequest);
					System.out.println("InstallConfirmResponse:"+installConfirm);
		      } catch (Exception e) {
		       e.printStackTrace();
		      }
		      startCardId++;
		      startObuId++;
		      long time2=System.currentTimeMillis();
		      System.out.println("当前程序耗时："+(time2-time1)+"ms"+"共"+vehicleList.size()+"条，现执行到第"+j+"条数据");
		      j++;
		    }
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		
		
	}
	 	private void commSet(CommQtkRequset req){
	 		req.setTerminalId(CssUtil.TERMINAL_ID);
//			req.setAgentId("52010102914");//金润
//			req.setChannelId("5201010291401020002");
//			52010188110
	 		req.setAgentId("52010188111");//好运达
			req.setChannelId("5201018811100000001");
			req.setChannelType(CssConstant.COMM_CHANNEL_TYPE);
			req.setStaffId("xnkfx");
			req.setSubmitTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
		};
	
//		/**
//		 * 将车辆外廓长、宽、高转换成  长X宽X高
//		 * @param vehicleInfo
//		 * @return
//		 */
//		private String outsideDimensionsTransformation(YangCheng vehicleInfo) {
//			String vehicleLength = "0";
//			String vehicleWidth = "0";
//			String vehicleHeight = "0";
//			if(vehicleInfo.getVehicleLength() != null) vehicleLength = vehicleInfo.getVehicleLength().toString();
//			if(vehicleInfo.getVehicleWidth() != null) vehicleWidth = vehicleInfo.getVehicleWidth().toString();
//			if(vehicleInfo.getVehicleHeight() != null) vehicleHeight = vehicleInfo.getVehicleHeight().toString();
//			String outsideDimensions = vehicleLength + CssConstant.OUTSIDEDIMENSIONS_SAMBOL + vehicleWidth + CssConstant.OUTSIDEDIMENSIONS_SAMBOL + vehicleHeight;
//			return outsideDimensions;
//		}
	 
	 
	 
	
}
