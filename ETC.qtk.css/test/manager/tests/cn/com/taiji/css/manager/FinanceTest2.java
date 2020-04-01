package tests.cn.com.taiji.css.manager;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.releases.VehicleInfoSubmitV3Request;
import cn.com.taiji.dsi.model.comm.protocol.releases.VehicleInfoSubmitV3Response;
import tests.MyNotTransationalTest;

/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午4:44:18<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class FinanceTest2 extends MyNotTransationalTest {
	
	@Autowired
	private ReleaseBinService releaseBinService;
	
	
	@Test
	public void testVehicleSubmitV4() {
		VehicleInfoSubmitV3Request req = new VehicleInfoSubmitV3Request();
		commset(req);
		req.setAddress("1");
		req.setApprovedCount(1);
		req.setAxisType("1");
		req.setAxleCount(1);
		req.setAxleDistance(1);
		req.setContact("1");
		req.setCustomerType(201);
		req.setDaspAccept("2020-01-01T01:00:00");
		req.setDaspSign("1");
		req.setDaspVehicleId("贵ZW1S24_0");
		req.setEmergencyFlag(1);
		req.setEngineNum("1");
		req.setFileNum("1");
		req.setIssueDate("2017-05-20");
		req.setMaintenanceMass(1);
		req.setOperation(1);
		req.setOutsideDimensions("1000X1000X1000");
		req.setOwnerIdNum("111129198212040617");
		req.setOwnerIdType(201);
		req.setOwnerName("1");
		req.setOwnerTel("18500865675");
		req.setPermittedTowWeight(1);
		req.setPermittedWeight(1);
		req.setRefrigeratedTrucks(1);
		req.setRegisterDate("2017-05-20");
		req.setTestRecord("1");
		req.setTotalMass(1);
		req.setType(1);
		req.setUseCharacter(1);
		req.setUserId("52010119042570001");
		req.setVehicleModel("1");
		req.setVehiclePlate("贵ZW1S24");
		req.setVehiclePlateColor(1);
		req.setVehicleType("1");
		req.setVin("1");
		req.setWheelCount(1);
		try {
			VehicleInfoSubmitV3Response res=releaseBinService.vehicleInfoSubmitV4(req);
			echo(res);
		} catch (ApiRequestException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void obusubmitV2() {
//		ObuInfoSubmitV2Request req = new ObuInfoSubmitV2Request();
//		req.setExpireTime("2027-08-03");
//		req.setInstallType(2);
//		req.setObuId("1111173401087142");
//		req.setRegisteredChannelId("5201010600401130013");
//		req.setRegisteredTime("2017-08-03T09:43:08");
//		req.setRegisteredType(2);
//		req.setStatusChangeTime("2017-08-03T09:54:09");
//		try {
//			ObuInfoSubmitResponse res= releaseBinService.obuInfoSubmitV2(req);
//			echo("res:"+res);
//		} catch (ApiRequestException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	private void commset(VehicleInfoSubmitV3Request req) {
		req.setAgentId("52010102024");
		req.setChannelId("5201010201901020002");
		req.setTerminalId("999999999999");
		req.setChannelType(2);
		req.setStaffId("ghwetcfx01");
		req.setSubmitTime("2017-11-09T16:30:30");
	}
	public static void main(String[] args) {
		List<Integer> list = Lists.newArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		int sum = list.stream().reduce(0, (acc, value) -> acc + value);
		System.out.println(sum);
	}
}
