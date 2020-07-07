package tests.cn.com.taiji.css.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelWithCOSResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelWithCOSV2Request;
import cn.com.taiji.dsi.model.comm.protocol.reckon.CardAccountReckonRequest;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceRepo;
import tests.MyNotTransationalTest;

public class reckonTest extends MyNotTransationalTest {
	@Autowired
	private AccountCardBalanceRepo accountCardBalanceRepo;
	@Autowired
	private FinanceBinService financeBinService;
	
	@Test
	public void testBalance1() {
		CardCancelWithCOSV2Request request = new CardCancelWithCOSV2Request();
		CardCancelWithCOSResponse response = new CardCancelWithCOSResponse();
		commset(request);
		request.setBankCardId("6217790001204814392");
		request.setBankType(15);
		request.setCardId("52011328220201197282");
		request.setCusName("吴应阜");
		request.setCusTel("13985097425");
		request.setOperateType(1);
		request.setCusType(1);
		try {
			response = financeBinService.cardCancelWithCOSV2(request);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	 
	 private void commset(CardCancelWithCOSV2Request req) {
			req.setAgentId("52010102002");
			req.setChannelId("5201010200226250002");
			req.setTerminalId("999999999999");
			req.setChannelType(2);
			req.setStaffId("jh100");
			req.setSubmitTime("2017-11-09T16:30:30");
		} 
}
