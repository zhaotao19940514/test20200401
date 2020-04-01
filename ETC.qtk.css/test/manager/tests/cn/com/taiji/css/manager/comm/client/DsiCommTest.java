/**
 * @Title DsiCommTest.java
 * @Package tests.cn.com.taiji.css.manager.comm.client
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月10日 下午2:34:56
 * @version V1.0
 */
package tests.cn.com.taiji.css.manager.comm.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardBlackListUploadRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardBlackListUploadResponse;
import cn.com.taiji.dsi.model.common.basedata.StaffSubmitRequest;
import cn.com.taiji.dsi.model.common.basedata.StaffSubmitResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import tests.MyNotTransationalTest;

/**
 * @ClassName DsiCommTest
 * @Description TODO
 * @author yaonl
 * @date 2018年07月10日 14:34:56
 * @E_mail yaonanlin@163.com
 */
public class DsiCommTest extends MyNotTransationalTest{
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	//xftf1555051971931903   冲正单号
	@Test
	public void CreateStaffInfo() throws Exception {
		StaffSubmitRequest req= new StaffSubmitRequest();
		req.setHallId("5201010600401140003");
		req.setChannelType(2);
		req.setId("fxd1234");
		req.setAgentId("52010106004");
		req.setType(1);
		req.setName("范晓东");
		req.setPassWord("456789");
		req.setOperation(2);
		StaffSubmitResponse createStaff = maintenanceBinService.StaffSubmit(req);
		if(createStaff!=null) {
			System.out.println(createStaff.getStatus());
			System.out.println(createStaff.getMessage());
		}
	}
	
	
	
	@Test
	public void blackTest() throws Exception {
		CardBlackListUploadRequest req=new CardBlackListUploadRequest();
		req.setAgentId("52010106004");
		req.setChannelId("5201010600401140003");
		req.setChannelType(2);
		req.setStaffId("test");
		req.setSubmitTime("2019-05-08T00:00:00");
		req.setCardId("52011328220201499521");
		req.setType(1);
		req.setStatus(2);
		CardBlackListUploadResponse cardBlackListUpload = maintenanceBinService.CardBlackListUpload(req);
		System.out.println(cardBlackListUpload.getMessage());
	}
	
	
	@Test
	public void test01() throws Exception {
		CardInfo findByCardId = cardInfoRepo.findByCardId("52011328220201078742");
		System.out.println(findByCardId);
		
	}
	
	public static void main(String[] args) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate now = LocalDate.now();
		LocalDate end = now.minusDays(7);
		System.out.println(end);
	}
	
}

