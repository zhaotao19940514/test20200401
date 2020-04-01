package tests.cn.com.taiji.css.manager;
/*
 * Date: 2016年3月1日
 * author: Peream  (peream@gmail.com)
 *
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.apply.baseinfo.CustomerManager;
import cn.com.taiji.css.manager.customerservice.card.AppCardStatusChangeResponse;
import cn.com.taiji.css.manager.customerservice.card.CancelManager;
import cn.com.taiji.css.manager.issuetranscation.CardAnnounceRecordManager;
import cn.com.taiji.css.manager.issuetranscation.CardAnnounceRecordModel;
import cn.com.taiji.css.model.apply.quickapply.InfoCheckRequset;
import cn.com.taiji.css.model.apply.quickapply.InfoCheckResponse;
import cn.com.taiji.css.model.customerservice.card.CancelRequest;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.css.model.issuetranscation.CardAnnounceRecordRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.manager.comm.client.InqueryBinService;
import cn.com.taiji.dsi.manager.comm.client.MaintenanceBinService;
import cn.com.taiji.dsi.manager.comm.client.ReckonBinService;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelResponse;
import cn.com.taiji.dsi.model.comm.protocol.fincharge.CardAccountReckonFeeRequest;
import cn.com.taiji.dsi.model.comm.protocol.fincharge.CardAccountReckonFeeResponse;
import cn.com.taiji.dsi.model.comm.protocol.fincharge.CreditFeeChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.fincharge.CreditFeeChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.fincharge.RefundSuccessUploadRequest;
import cn.com.taiji.dsi.model.comm.protocol.fincharge.RefundSuccessUploadResponse;
import cn.com.taiji.dsi.model.comm.protocol.inquire.VehicleInfoQueryRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.VehicleInfoQueryResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardBlackListUploadRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardBlackListUploadResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.VehicleInfoChangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.VehicleInfoChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.reckon.CardAccountReckonRequest;
import cn.com.taiji.dsi.model.comm.protocol.reckon.CardAccountReckonResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.CardApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUApplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.OBUApplyResponse;
import cn.com.taiji.dsi.model.comm.protocol.releases.ObuInfoSubmitRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.ObuInfoSubmitResponse;
import cn.com.taiji.qtk.entity.BankSignDetail;
import cn.com.taiji.qtk.entity.CancelledCardDetail;
import cn.com.taiji.qtk.entity.CardBlackTemp;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.TempTrafficRecordDetail;
import cn.com.taiji.qtk.entity.Testt;
import cn.com.taiji.qtk.entity.TrafficRecordDetail;
import cn.com.taiji.qtk.entity.Veh;
import cn.com.taiji.qtk.entity.Vehicle;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.BankSignSendType;
import cn.com.taiji.qtk.entity.dict.BankSignServiceType;
import cn.com.taiji.qtk.repo.jpa.BankSignDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CancelledCardDetailRepo;
import cn.com.taiji.qtk.repo.jpa.CardBlackTempRepo;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;
import cn.com.taiji.qtk.repo.jpa.OBUInfoRepo;
import cn.com.taiji.qtk.repo.jpa.TempTrafficRecordDetailRepo;
import cn.com.taiji.qtk.repo.jpa.TestRepo;
import cn.com.taiji.qtk.repo.jpa.TrafficRecordDetailRepo;
import cn.com.taiji.qtk.repo.jpa.VehRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleRepo;
import tests.MyNotTransationalTest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年3月1日 下午4:44:18<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class FinanceTest extends MyNotTransationalTest {
	@Autowired
	private FinanceBinService financeBinService;
	@Autowired
	private ReckonBinService reckonBinService;
	@Autowired
	private CancelManager cancelManager;
	@Autowired
	private CardBlackTempRepo cardBlackTempRepo;
	@Autowired
	private InqueryBinService inqueryBinService;
	@Autowired
	private MaintenanceBinService maintenanceBinService;
	@Autowired
	private CancelledCardDetailRepo  cancelledCardDetailRepo;
	@Autowired
	private TempTrafficRecordDetailRepo TempTrafficRecordDetailRepo;
	@Autowired
	private CardAnnounceRecordManager cardAnnounceRecordManager;
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private TestRepo testRepo;
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private VehRepo vehRepo;
	@Autowired
	private VehicleInfoRepo veRepo;
	@Autowired
	private CardInfoRepo cardRepo;
	@Autowired
	private VehicleRepo vehiRepo;
	@Autowired
	private CustomerInfoRepo cuRepo;
	@Autowired
	private BankSignDetailRepo bankSignDetailRepo;
	@Autowired
	private TrafficRecordDetailRepo trdRepo;
	@Autowired
	private OBUInfoRepo obuRepo;
	
	@Test
	public void testAll() throws Exception {
		
		VehicleInfoQueryRequest req = new VehicleInfoQueryRequest();
		//commset(req);
		req.setVehicleId("川EQ1378_0");
		VehicleInfoQueryResponse res = inqueryBinService.vehicleInfoQuery(req);
		echo(res);
	}
	@Test
	public void testCustomerInfo() {
		
	}
	
	
	@Test
	public void cardAnnounceRecord() {
		List<TempTrafficRecordDetail> list = TempTrafficRecordDetailRepo.findAll();
		List<TempTrafficRecordDetail>  tempList = Lists.newArrayList();
		for (TempTrafficRecordDetail entity : list) {
			CardAnnounceRecordRequest req = new CardAnnounceRecordRequest();
			req.setCardId(entity.getCardId());
			Pagination page = cardAnnounceRecordManager.page(req);
			if(null!=page&&page.getResult().size()!=0) {
				System.out.println(page.getResult().get(0));
				CardAnnounceRecordModel objList =  (CardAnnounceRecordModel) page.getResult().get(0);
				if(null!=objList) {
					entity.setVehiclePlate(objList.getVehiclePlate());
				}
					entity.setEnTime(objList.getEnTime());
					entity.setEntolllaneName(objList.getEnName());
					entity.setExTime(objList.getExTime());
					entity.setExtolllaneName(objList.getExName());
					entity.setPreBalance(objList.getPreBalance());
					entity.setFee(objList.getFee());
					entity.setPostBalance(objList.getPostBalance());
			}
			tempList.add(entity);
		}
		TempTrafficRecordDetailRepo.saveAll(tempList);
	}
	

	private void commset(OBUApplyRequest req) {
		req.setAgentId("52010102024");
		req.setChannelId("5201010201901020002");
		req.setTerminalId("999999999999");
		req.setChannelType(2);
		req.setStaffId("ghwetcfx01");
		req.setSubmitTime("2017-11-09T16:30:30");
	}

	@Test
	public void testCredit() {

		CreditFeeChangeRequest req = new CreditFeeChangeRequest();
		// commset(req);
		req.setCardId("52011328220201608008");
		req.setFee(1L);
		req.setSerialId("00000");
		try {
			CreditFeeChangeResponse res = financeBinService.creditFeeChange(req);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void CardAccountReckonFee() {

		CardAccountReckonFeeRequest req = new CardAccountReckonFeeRequest();
		//commset(req);
		req.setCardId("52011411220300718651");
		try {
			CardAccountReckonFeeResponse res = financeBinService.cardReckonFee(req);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void refundSuccessUpload() {

		RefundSuccessUploadRequest req = new RefundSuccessUploadRequest();
		 commset(req);
		req.setCardId("52011411220300718651");
		try {
			RefundSuccessUploadResponse res = financeBinService.refundSuccessUpload(req);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cardrefund() {
		CardAccountReckonRequest req = new CardAccountReckonRequest();
		commset(req);
		req.setCardId("52011328220201144337");
		req.setReckoncharge(0L);

		CardAccountReckonResponse res = null;
		// 初始化卡卡账
		req.setCountType(2);
		try {
			res = reckonBinService.cardAccountReckon(req);
		} catch (ApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
	}
	@Test
	public void returnWhite() {
		CardBlackListUploadResponse res = new CardBlackListUploadResponse();
		CardBlackListUploadRequest req = new CardBlackListUploadRequest();
		req.setCardId("52011640230200550558");
		req.setStatus(2);
		req.setType(4);
		commset(req);
		
		try {
			res = maintenanceBinService.CardBlackListUpload(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		echo("res:"+res);
	}
	@Test
	public void platecheck() {
		
	}
	
	
	@Test
	public void cancelList() throws Exception {
		List<CardBlackTemp> list = cardBlackTempRepo.findAll();
		AppCardStatusChangeResponse preRes = new AppCardStatusChangeResponse();
		PreCancelRequest preReq = new PreCancelRequest();
		CancelRequest canReq = new CancelRequest();
		CardCancelResponse canRes = new CardCancelResponse();
		preReq.setProvider(0);
		preReq.setBalanceType("ACCOUNT");
		preReq.setCardBalance(0L);
		preReq.setApplyStep(1);
		preReq.setCardType(1);
		canReq.setProvider(0);
		canReq.setBalanceType("ACCOUNT");
		canReq.setCardBalance(0L);
		canReq.setType(1);
		preReq.setApplyStep(1);
		preReq.setCardType(1);
		for (CardBlackTemp temp : list) {
			preReq.setCardId(temp.getCardId());
			canReq.setCardId(temp.getCardId());
			//doForceCancel(canReq);
			// preRes = doPreCancel(preReq); 
//			 if (preRes.getStatus() == 1) {
				 canReq.setCardId(temp.getCardId());
				 canReq.setCardBalance(-1L);
				 canReq.setCardType(1);
				 canReq.setType(1);
				 canRes =  doForceCancel(canReq);
//				 User user = new User(); 
//				 user.setStaffId("zt001"); canReq.setCardId(temp.getCardId());
//				 canReq.setCardType(2);
				// canRes = cancelManager.doCancel(canReq, user); 
//				 }
			 
			System.out.println("卡号:" + temp.getCardId() + "__" + preRes.getMessage());
		}
	}
	
	@Test
	public void obuCancel() throws IOException {
		List<CardBlackTemp> list = cardBlackTempRepo.findAll();
		OBUStatusChangeRequest req= new OBUStatusChangeRequest();
		OBUStatusChangeResponse res = new OBUStatusChangeResponse();
		for (CardBlackTemp temp : list) {
			req.setStatus(5);
			req.setObuId(temp.getCardId()); 
			res = oBUStatusChange(req);
		}
	}

	private OBUStatusChangeResponse oBUStatusChange(OBUStatusChangeRequest queryModel) throws IOException {
		OBUStatusChangeRequest oBUStatusChangeReq= new OBUStatusChangeRequest();
//		oBUStatusChangeReq.setStatus(queryModel.getObuStatus());
//		oBUStatusChangeReq.setObuId(queryModel.getObuId());
		super.commSet(oBUStatusChangeReq);
		OBUStatusChangeResponse obuSRes = maintenanceBinService.obuStatusChange(oBUStatusChangeReq);
		return obuSRes;
	}
	private AppCardStatusChangeResponse doPreCancel(PreCancelRequest preReq) throws ManagerException {
		User user = new User();
		user.setStaffId("zt001");
		AppCardStatusChangeResponse preRes = cancelManager.doPreCancel(preReq, user);
		return preRes;
	}
	
	private CardCancelResponse doForceCancel(CancelRequest req) {
		User user = new User();
		user.setStaffId("zt001");
		CardCancelResponse preRes = null;
		try {
			preRes = cancelManager.doCancel(req, user);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return preRes;
	}

	
	/**
	 * 根据文件路径创建输出流
	 * @param filePath ： 文件的路径
	 * @param content : 需要写入的内容
	 */
	public void writeFile( String filePath , String content ){
		FileOutputStream fos = null ;
		try {
			//1、根据文件路径创建输出流
			fos  = new FileOutputStream( filePath,true);

			//2、把string转换为byte数组；
			byte[] array = content.getBytes() ;
			//3、把byte数组输出；
			fos.write( array );
			String newLine = System.getProperty("line.separator");			 
			fos.write(newLine.getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if ( fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	
	public static boolean isCardTypeVaild(int cardType) {
		if (cardType / 100 != 1 && cardType / 100 != 2)
			return false;
		int sec = (cardType / 10) % 10;
		if (sec < 1 || sec > 5)
			return false;
		int thrid = cardType % 10;
		if (thrid < 1 || thrid > 3)
			return false;
		return true;
	}

	public static boolean cancel30ArgueTime() {
		String afterDate = "2019-05-05 14:21:23";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime nowTime = LocalDateTime.now();
		LocalDateTime cancelDate = LocalDateTime.parse(afterDate, dateTimeFormatter).plusMinutes(9);
		System.out.println(cancelDate);
		return nowTime.compareTo(cancelDate) > 0;
	}
	
	private static LocalDate getClearTime() {
		LocalDate time = clearBTime;
		clearBTime = clearBTime.plusMonths(1);
		return time;
	}

	public static void persistData() {
		do {
			String clearStartTime = getClearTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			System.out.println(clearStartTime);
			clearBTime = getClearTime();
			System.out.println("clearBTime" + clearBTime);
		} while (clearBTime.isBefore(clearSTime));

	}
	@Test
	public void saveCancelDetailData() throws ParseException {
		List<CardBlackTemp> list = cardBlackTempRepo.findAll();
		List<CancelledCardDetail> canList = Lists.newArrayList();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-07-12 12:00:00"));
		for (CardBlackTemp temp : list) {
			CancelledCardDetail can = new CancelledCardDetail();
			can.setCardId(temp.getCardId());
			can.setAgencyId("52010102028");
			can.setCancellationTime("20190712");
			can.setCreateTime(cal);
			can.setCardType(1);
			can.setVehicleId(temp.getId());
			can.setStatus(1);
			canList.add(can);
		}
		cancelledCardDetailRepo.saveAll(canList);
	}
	
	@Test
	public void vehiclePlate() {
		List<Testt> list = testRepo.findAll();
		InfoCheckRequset req = new InfoCheckRequset();
		User user = new User();
		for(Testt t:list) {
			req.setCheckType(2);
			req.setVehiclePlate(t.getVehiclePlate());
			req.setVehiclePlateColor(0);
			req.setType(Integer.parseInt(t.getType()));
			InfoCheckResponse res = customerManager.quickApplyCheck(req, user);
			t.setNewResult(res.getMessage());
			System.out.println(t.getVehiclePlate()+"___"+"res:"+res);
			testRepo.save(t);
		}
		
	};
	
	@Test
	public void refundSuccess() throws ApiRequestException, IOException {
		RefundSuccessUploadRequest req = new RefundSuccessUploadRequest();
		req.setCardId("52011411220300639026");
		commset(req);
		RefundSuccessUploadResponse res = financeBinService.refundSuccessUpload(req);
		System.out.println("res:"+res);
		
	}
	@Test
	public void cardApplyTest() {
		CardApplyRequest req = new CardApplyRequest();
		commset(req);
		req.setCardId("52011640230215566551");
		//req.setCardType();
		req.setEnableTime("20191113");
		req.setExpireTime("20291113");
		req.setUserId("52010116022840342");
		req.setVehicleId("贵CKR652_0");
		req.setCosProvider(1);
		try {
			CardApplyResponse res = releaseBinService.cardApply(req);
			System.out.println("res:"+res);
		} catch (ApiRequestException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObuApply() {
		OBUApplyRequest req = new OBUApplyRequest();
		commset(req);
		req.setObuId("5202193400433777");
		req.setUserId("52010115043040288");
		req.setVehicleId("贵ELR775_0");
		OBUApplyResponse res;
		try {
			res = releaseBinService.obuApply(req);
			System.out.println("res："+res);
		} catch (ApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testVehicleInfoQuery() {
		VehicleInfoChangeRequest req = new VehicleInfoChangeRequest();
		req.setVehicleId("贵CF4328_0");
		req.setUserId("52010118121941808");
		commset(req);
		try {
			VehicleInfoChangeResponse res = maintenanceBinService.vehicleInfoChange(req);
			System.out.println("res:"+res);
		} catch (ApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOBUApply() {
		
		OBUApplyRequest req = new OBUApplyRequest();
		commset(req);
		req.setObuId("5202192406196002");
		req.setUserId("52010119101958746");
		req.setVehicleId("赣CC4422_1");
		try {
			OBUApplyResponse res = releaseBinService.obuApply(req);
			System.out.println("res:"+res);
		} catch (ApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void vehicleInfoQuery() {
		VehicleInfoQueryResponse res = new VehicleInfoQueryResponse();
		VehicleInfoQueryRequest req = new VehicleInfoQueryRequest();
		commset(req);
		req.setVehicleId("贵AA8922_1");
		try {
			res = inqueryBinService.vehicleInfoQuery(req);
			System.out.println("res:"+res);
		} catch (ApiRequestException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void supplyVehicleInfo() {
		List<Veh> listVeh = vehRepo.findAll();
		//List<Veh> listVeh = vehRepo.listByVehicleId("蒙A77201_1");
		List<String> collect = listVeh.parallelStream().map(o -> o.getVehicleId()).collect(Collectors.toList());
		Map<String,List<Veh>> vehMap = listVeh.parallelStream().collect(Collectors.groupingBy(Veh::getVehicleId));
		List<VehicleInfo> list = Lists.newArrayList();
		VehicleInfo ve =null;
		int i=0;
		for(String co:collect) {
			ve = new VehicleInfo();
			ve = veRepo.findByVehicleId(co);
			Veh v = vehMap.get(co).get(0);
			if(null!=ve) {
				if(null!=ve.getVIN()) {
					ve.setVIN(v.getVIN());
				}
				if(null!=ve.getEngineNum()) {	
					ve.setEngineNum(v.getEngineNum());
				}
				ve.setApprovedCount(Integer.parseInt(v.getApprovedCount()));
				ve.setTotalMass(Integer.parseInt(v.getTotalMass()));
				ve.setMaintenanceMass(Integer.parseInt(v.getMaintenanceMass()));
				ve.setPermittedWeight(Integer.parseInt(v.getPermittedWeight()));
				ve.setOutsideDimensions(v.getOutsideDimensions());
				ve.setPermittedTowWeight(Integer.parseInt(v.getPermittedTowWeight()));
				ve.setType(Integer.parseInt(v.getType()));
				ve.setAxleCount(Integer.parseInt(v.getAxleCount()));
				ve.setWheelCount(Integer.parseInt(v.getWheelCount()));
				System.out.println(i++);
			}
			list.add(ve);
		}
		
		veRepo.saveAll(list);	
	}
	
	/**
	 * 贵阳银行推送预注销通知
	 */
	@Test
	public void sendPreNotify() {
		List<CardInfo> cardList= cardRepo.listCardInfoByChangeTime();
		System.out.println("cardList:"+cardList.size());
		List<BankSignDetail> list = Lists.newArrayList();
		for(CardInfo card:cardList) {
			CustomerInfo customerInfo = cuRepo.findByCustomerId(card.getCustomerId());
			if(null!=customerInfo) {
				System.out.println(card.getCardId());
				list.add(notice(card,customerInfo));
			}
		}
		System.out.println("list:"+list.size());
		try {
			bankSignDetailRepo.saveAll(list);
			System.out.println("导入成功");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public BankSignDetail notice(CardInfo cardInfo,CustomerInfo customerInfo) {
		try {
			BankSignDetail bank = new BankSignDetail();
			bank.setOrgCode(cardInfo.getAgencyId());
			String[] str = cardInfo.getVehicleId().split("_");
			bank.setVehiclePlate(str[0]);
			bank.setVehiclePlateColor(Integer.parseInt(str[1]));
			bank.setUserIdType(customerInfo.getCustomerIdType());
			bank.setUserIdNum(customerInfo.getCustomerIdNum());
			bank.setCardId(cardInfo.getCardId());
			bank.setSendStatus(BankSignSendType.WAIT_SEND);
			bank.setServiceType(BankSignServiceType.CARD_PRE_CANCELLATION_NOTIFY);
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			bank.setCreateTime(df.format(LocalDateTime.now()));
			return bank;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void testDeleteRepeatTrans() {
		List<Vehicle> vehiList =vehiRepo.listBy10000();
		List<TrafficRecordDetail> traList = Lists.newArrayList();
		for(Vehicle v:vehiList) {
			echo(v.getId());
			traList = trdRepo.listByListNo(v.getId());
			if(traList.size()>1) {
				for(int i=1;i<traList.size();i++) {
					trdRepo.delete(traList.get(i));
				}
			}
			vehiRepo.delete(v);
		}
		
	}
	
	@Test
	public void updateVehicleType() {
		List<Vehicle> vehiList =vehiRepo.findAll();
		echo("总数:"+vehiList.size());
		int i=0;
		for(Vehicle v:vehiList) {
			i++;
				VehicleInfo  veInfo = veRepo.findByVehicleId(v.getVehicleId());
				if(null!=veInfo) {
					echo(i+"_"+veInfo.getVehicleId());
					veInfo.setType(Integer.parseInt(v.getType()));
					veInfo.setApprovedCount(Integer.parseInt(v.getVehicleApprovedCount()));
					veInfo.setTotalMass(Integer.parseInt(v.getVehicleTotalMass()));
					veInfo.setMaintenanceMass(Integer.parseInt(v.getVehicleMaintenanceMass()));
					veInfo.setPermittedWeight(Integer.parseInt(v.getVehiclePermittedWeight()));
					veInfo.setOutsideDimensions(v.getVehicleOutsidedimensions());
					veInfo.setPermittedTowWeight(Integer.parseInt(v.getVehiclePermittedtowWeight()));
					veInfo.setWheelCount(Integer.parseInt(v.getVehicleWheelCount()));
					veInfo.setAxleCount(Integer.parseInt(v.getVehicleAxleCount()));
				//部平台数据提交
					//ygzUploader.asyncDoVehicleUpload(veInfo.clone(), Operation.ofCode(2), DataSource.DSI_INTERFACE);
					//ygzUploader.saveLocalVehicleRequestJson(veInfo.clone(), Operation.ofCode(2), DataSource.DSI_INTERFACE);
					veRepo.save(veInfo);
			}
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	
	@Test
	public void testObuInfoSubmitV2() {
		ObuInfoSubmitRequest res = new ObuInfoSubmitRequest();
		res.setExpireTime("11");
		res.setInstallChannelId("1");
		res.setInstallTime("1");
		res.setInstallType(null);
		res.setObuId("1");
		res.setRegisteredChannelId("1");
		res.setRegisteredTime("1");
		res.setRegisteredType("1");
		res.setStatusChangeTime("1");
		ObuInfoSubmitResponse res = releaseBinService.obuInfoSubmitV2(request);
		echo(res);
	}
//	
//	@Test
//	public void testVehicleSubmitV4() {
//		VehicleInfoSubmitV3Request req = new VehicleInfoSubmitV3Request();
//		commset(req);
//		req.setAddress("1");
//		req.setApprovedCount("1");
//		req.setAxisType(1);
//		req.setAxleCount(1);
//		req.setAxleDistance(1);
//		req.setContact("1");
//		req.setCustomerType(1);
//		req.setDaspAccept("1");
//		req.setDaspSign("1");
//		req.setDaspVehicleId("1");
//		req.setEmergencyFlag(1);
//		req.setEngineNum("1");
//		req.setFileNum("1");
//		req.setIssueDate("1");
//		req.setMaintenanceMass("1");
//		req.setOperation("1");
//		req.setOutsideDimensions("1");
//		req.setOwnerIdNum("1");
//		req.setOwnerIdType(1);
//		req.setOwnerName("1");
//		req.setOwnerTel("1");
//		req.setPermittedTowWeight("1");
//		req.setPermittedWeight("1");
//		req.setRefrigeratedTrucks("1");
//		req.setRegisterDate("1");
//		req.setTestRecord("1");
//		req.setTotalMass("1");
//		req.setType(1);
//		req.setUseCharacter("1");
//		req.setUserId("1");
//		req.setVehicleModel("1");
//		req.setVehiclePlate("1");
//		req.setVehicleType("1");
//		req.setVin("1");
//		req.setWheelCount("1");
//		VehicleInfoSubmitV3Response res = releaseBinService.vehicleInfoSubmitV4(request);
//		echo("res:"+res);
//	}
//	
	@Test
	 public void YC() {
	//  PlateValidateResponse response = ygzUploader.syncDoPlateCheck("黑EXA169", 0, 1, DataSource.DSI_INTERFACE);
	//  echo(response);
	  List<Cancel> vehicleIds =cancelRepo.listAll();
	  System.out.println("共"+vehicleIds.size());
	  int i=1;
	  for(Cancel cancel :vehicleIds) {
	   System.out.println("当前执行到第"+i+"条");
	   CardInfo cardInfo =cardInfoRepo.findByCardId(cancel.getVehicleId());
	   if(cardInfo !=null && cardInfo.getCustomerId() !=null) {
	    CustomerInfo findByCustomerId = customerInfoRepo.findByCustomerId(cardInfo.getCustomerId());
	    if(findByCustomerId!=null ) {
	     VehicleInfo vehicleInfo = vehicleInfoRepo.findByVehicleId(cancel.getVehicleId());
	     vehicleInfo.setCustomerId(cardInfo.getCustomerId());
	     vehicleInfo.setCustomerInfo(findByCustomerId);
	     vehicleInfoRepo.save(vehicleInfo);
	    }
	   }
	   i++;
	  }
	  
	 }
	@Test
	public void obucount() {
		List<Vehicle> veList = vehiRepo.findAll();
		int i=0;
		for(Vehicle ve:veList) {
			echo(ve.getId()+"__"+i++);
			if(null==ve.getVehicleId()) {
				continue;
			}
			if(ve.getVehicleId().length()==16) {
				OBUInfo findByObuId = obuRepo.findByObuId(ve.getVehicleId());
				if(null!=findByObuId) {
					ve.setVehiclePlateColor("1");
					vehiRepo.save(ve);
				}
			}else if(ve.getVehicleId().length()==33) {
				String[] obuIds = ve.getVehicleId().split("-");
				long count = obuRepo.countObuId(obuIds[0], obuIds[1]);
				ve.setVehiclePlateColor(String.valueOf(count));
				vehiRepo.save(ve);
			}
		}
		
	}
	public static void main(String[] args) {
//		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate now = LocalDate.now();
//		LocalDate preNow = now.minusDays(32);
//		System.out.println(preNow);
		/*
		 * System.out.println(format1.format(preNow));
		 * System.out.println("总的保证金总额:"+1068*50000);
		 * System.out.println("2019.6.30号（未注销）剩余保证金总额:"+40644655);
		 * System.out.println("2019.6.30已退保证金条数: "+219+" 条，最初缴保证金总额:"+219*
		 * 50000+" 。2019.6.30.已退保证金总额:"+9903820+"流水扣款:"+(10950000-9903820));
		 * System.out.println("2019.6.30（未注销）剩余保证金条数:"+(1068-219)+"应剩余金额:"+(53400000-
		 * 10950000)+".实剩余金额:"+40644655);
		 * System.out.println("2019.6.30（未注销）待补缴保证金总额:"+1046515);
		 * System.out.println("2019.6.30（未注销）后补缴的保证金总额:"+887630);
		 * System.out.println("2019.6.30（未注销）后流水划款的保证金总额:"+425850);
		 * System.out.println("2019.6.30（未注销）前补缴的保证金总额:"+606462);
		 * System.out.println("2019.6.30（未注销）前流水划款的保证金总额:"+897304);
		 * System.out.println("2019.6.30（未注销）老系统待补缴的保证金总金额："+1159825);
		 * System.out.println(
		 * "2019.6.30号（未注销）剩余保证金总额+2019.6.30（未注销）老系统待补缴的保证金总金额+2019.6.30（未注销）前流水划款的保证金总额-2019.6.30（未注销）前补缴的保证金总额:"
		 * +(40644655+1159825+897304-606462));
		 * System.out.println("应剩余保证金总额-实剩余保证金总额:"+(42450000-42095322));
		 */
		/*
		 * FinanceTest financeTest = new FinanceTest(); //电脑d盘中的abc.txt 文档 String
		 * filePath = "D:/abc.txt" ;
		 * 
		 * //要写入的内容 String content = "今天是2017/1/9,\\r\\n天气很好" ; for(int i=1;i<10;i++) {
		 * financeTest.writeFile( filePath , content ) ; }
		 */
//		List<Integer> list = Lists.newArrayList();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		int sum = list.stream().reduce(0, (acc, value) -> acc + value);
//		System.out.println(sum);
		Integer banktype = 1;
		System.out.println(banktype==null?null:BankType.fromCode(accOprt.getBankType()));
	}
}
