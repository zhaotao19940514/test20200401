/**
 * @Title ObuCommandTest.java
 * @Package tests.cn.com.taiji.css.manager.obucommand
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午3:32:43
 * @version V1.0
 */
package tests.cn.com.taiji.css.manager.obucommand;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.ObuInfoMarkType;
import cn.com.taiji.css.manager.ocx.ObuCommandManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.ocx.ObuOfflineSysInfoCommandRequest;
import cn.com.taiji.css.model.ocx.ObuOfflineVehicleInfoCommandRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.CommQtkRequset;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSResponse;
import tests.MyNotTransationalTest;

/**
 * @ClassName ObuCommandTest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 15:32:43
 * @E_mail yaonanlin@163.com
 */
public class ObuCommandTest extends MyNotTransationalTest {
	
	
	
	@Autowired private ReleaseBinService releaseBinService;
	 
	  @Autowired private MaintenanceBinService maintenanceBinService;
	  
	  @Autowired private FinanceBinService financeBinService;
	  
	  @Autowired private InqueryBinService inqueryBinService;

	  private void commset(CommQtkRequset req) { 
		  req.setAgentId("52010106004");
		  req.setChannelId("5201010200601130001"); 
		  req.setChannelType(2);
		  req.setStaffId("无"); 
		  req.setSubmitTime("2017-11-09T16:30:30"); 
		 }
	  
	 public void CardChangeConfirmByCOSv2() throws Exception {
		  CardChargeConfirmByCOSRequest req = new CardChargeConfirmByCOSRequest(); 
		  commset(req);
		  req.setRechargeId("252010106004042200022018061209555638");
		  req.setPaidAmount(50000L);
		  req.setGiftAmount(0L);
		  req.setCosResponse("000126FC000F0100FCDC44B61243F0AB9000");
		  req.setCommand("805200000B2019031614252910B9A3CC04");
		  CardChargeConfirmByCOSResponse res =financeBinService.cardChargeConfirmByCOS(req); 
		  System.out.println(res.toJson());
	 }
	/*
	 * @Autowired private ObuCommandManager obuCommandManager;
	 * 
	 * @Test public void getCommand() throws ManagerException{
	 * ObuOfflineSysInfoCommandRequest sysRequest = new
	 * ObuOfflineSysInfoCommandRequest(); //
	 * sysRequest.setInfoMark(ObuInfoMarkType.SystemInfo); sysRequest.setOldInfo(
	 * "0,系统信息,B9F3D6DD00010001,16,40,5202164300000018,20180408,20280408,00");
	 * AppAjaxResponse sysRes = obuCommandManager.generateCommand(sysRequest);
	 * echo(sysRes); ObuOfflineVehicleInfoCommandRequest vehRequest = new
	 * ObuOfflineVehicleInfoCommandRequest(); vehRequest.setAxleCount(2);
	 * vehRequest.setEngineNum("4LSH4260271"); //
	 * vehRequest.setInfoMark(ObuInfoMarkType.VehicleInfo);
	 * vehRequest.setLoadOrSeat(5);
	 * vehRequest.setOldInfo("0,车辆信息,贵JZZ069,0,1,0,15,35,15,0,0,0,5,0,4LSH4260271");
	 * vehRequest.setPlateColor(0); vehRequest.setPlateNum("贵JZZ069");
	 * vehRequest.setUserType(0); vehRequest.setVehicleFeature("0");
	 * vehRequest.setVehicleHeight(15); vehRequest.setVehicleLength(15);
	 * vehRequest.setVehicleType(1); vehRequest.setVehicleWidth(35);
	 * vehRequest.setWheelsCount(4); vehRequest.setWheelBase(100); AppAjaxResponse
	 * vehRes = obuCommandManager.generateCommand(vehRequest); echo(vehRes); }
	 */}

