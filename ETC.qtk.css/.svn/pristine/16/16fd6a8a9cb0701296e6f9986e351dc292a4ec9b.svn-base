package cn.com.taiji.css.manager.issuetranscation;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.issuetranscation.CardAnnounceRecordRequest;
import cn.com.taiji.css.model.issuetranscation.InprovinceAnnounceRecordRequest;
import cn.com.taiji.css.model.issuetranscation.MicroPayMentRequest;
import cn.com.taiji.css.model.issuetranscation.OutprovinceAnnounceRecordRequest;
import cn.com.taiji.css.model.issuetranscation.TrafficRecordRequest;
import cn.com.taiji.qtk.entity.FileInprovinceDetail;
import cn.com.taiji.qtk.entity.FileOutProvinceDetailHis;
import cn.com.taiji.qtk.entity.MicroPayMentDetail;
import cn.com.taiji.qtk.entity.TrafficRecordDetail;
import cn.com.taiji.qtk.entity.dict.SettlementSerialType;
import cn.com.taiji.qtk.repo.jpa.FileInprovinceDetailRepo;
import cn.com.taiji.qtk.repo.jpa.FileOutProvinceDetailHisRepo;
import cn.com.taiji.qtk.repo.jpa.MicroPayMentDetailRepo;
import cn.com.taiji.qtk.repo.jpa.TrafficRecordDetailRepo;
@Service
public class CardAnnounceRecordManagerImpl extends AbstractManager implements CardAnnounceRecordManager{
	@Autowired
	private FileInprovinceDetailRepo fileInprovinceDetailRepo;
	@Autowired
	private FileOutProvinceDetailHisRepo fileOutProvinceDetailHisRepo;
	@Autowired
	private MicroPayMentDetailRepo microPayMentDetailRepo;
	@Autowired
	private TrafficRecordDetailRepo trafficRecordDetailRepo;
	@Override
	public Pagination page(CardAnnounceRecordRequest request) {	
		request.validate();
		List<CardAnnounceRecordModel> modelList =Lists.newArrayList();	
		List<Object[]> list= Lists.newArrayList();
		if(null!=request.getCardId()&&null==request.getBeforeDate()) {
			list = trafficRecordDetailRepo.listConsumeDetailByCardId(request.getCardId());
		}else if(null!=request.getCardId()&&null!=request.getBeforeDate()){
			list = trafficRecordDetailRepo.listConsumeDetailByCardIdAndTime(request.getCardId(), request.getBeforeDate(), request.getAfterDate());
		}
		else if(null!=request.getVehiclePlate()) {
			list = trafficRecordDetailRepo.listConsumeDetailByVehiclePlate(request.getVehiclePlate());
		}
		
		objectList(list, modelList);
		//去重
		modelList = modelList.stream().filter(distinctByKey(o -> ((CardAnnounceRecordModel)o).getCompareTime())).collect(Collectors.toList());
		modelList.sort((o1, o2) -> o2.getCompareTime().compareTo(o1.getCompareTime()));
		
		Pagination page = new Pagination();
		page.setPageSize(request.getPageSize());
		page.setTotalCount(modelList.size());
		page.setResult(modelList);
		page.compute();		
		return page;	
	}
	
	private void objectList(List<Object[]> objList, List<CardAnnounceRecordModel> modelList) {
		int len = objList.size();
		for(int i=0;i<len;i++) {
			CardAnnounceRecordModel entity = new CardAnnounceRecordModel();
			entity.setCardId(objList.get(i)[0].toString());
			if(null!=objList.get(i)[1]) {
				entity.setVehiclePlate(objList.get(i)[1].toString());
			}
			if(null!=objList.get(i)[2]) {
				entity.setConsumptionType(objList.get(i)[2].toString());
			}
			if(null!=objList.get(i)[3]) {
				entity.setEnTime(objList.get(i)[3].toString());
			}
			if(null!=objList.get(i)[4]) {
				entity.setEnName(objList.get(i)[4].toString());
			}
			if(null!=objList.get(i)[5]) {
				entity.setExTime(objList.get(i)[5].toString());
			}
			if(null!=objList.get(i)[6]) {
				entity.setExName(objList.get(i)[6].toString());
			}
			if(null!=objList.get(i)[7]) {
				entity.setPreBalance(Long.parseLong(objList.get(i)[7].toString()));
			}
			if(null!=objList.get(i)[8]) {
				entity.setFee(Long.parseLong(objList.get(i)[8].toString()));
			}
			if(null!=objList.get(i)[9]) {
				entity.setPostBalance(Long.parseLong(objList.get(i)[9].toString()));
			}
			if(null!=objList.get(i)[10]) {
				entity.setPassId(objList.get(i)[10].toString());
			}
			entity.setCompareTime(rexCompile(entity.getExTime()+entity.getEnTime()+entity.getFee()));
			modelList.add(entity);
		}
	}
	
	
//	public void betweenDateList(List<CardAnnounceRecordModel> modelList,CardAnnounceRecordRequest request) {
//		Long beforeDate = Long.parseLong(request.getBeforeDate().replace("-", ""));
//		Long afterDate = Long.parseLong(request.getAfterDate().replace("-", ""));
//		int beIndex = 0;
//		int afIndex = 0;
//		if(beforeDate<=modelList.get(0).getCompareTime()) {
//			beIndex
//		}
//		
//		
//	}
	private String rexCompile(String time) {
    	// 数字匹配
    	Matcher matcher = Pattern.compile("\\d+").matcher(time);
    	String mathTime ="";
    	while (matcher.find()) {
    		mathTime+=matcher.group(); // 打印出：11
    	}
		return mathTime;
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object>
	  keyExtractor) { 
		  Map<Object, Boolean> map = new ConcurrentHashMap<>(); 
		  return
		  t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	  }

	private List<CardAnnounceRecordModel> inToModel(List<FileInprovinceDetail> inList){
		List<CardAnnounceRecordModel> cardmList =Lists.newArrayList(); 
		for (FileInprovinceDetail fileInprovinceDetail : inList) {
			CardAnnounceRecordModel model = new CardAnnounceRecordModel();
			model.setCardId("5201"+fileInprovinceDetail.getCardId());
			model.setVehiclePlate(fileInprovinceDetail.getVehiclePlate());
			model.setEnTime(fileInprovinceDetail.getEnTime());
			model.setEnName(fileInprovinceDetail.getEnTolllaneName());
            model.setExTime(fileInprovinceDetail.getExTime());
            model.setExName(fileInprovinceDetail.getExTolllaneName());
            model.setPreBalance(fileInprovinceDetail.getPreBalance());
            model.setPostBalance(fileInprovinceDetail.getPostBalance());
            model.setFee(fileInprovinceDetail.getFee());
            model.setConsumptionType("省内通行");
            cardmList.add(model);
		}
		return cardmList;
	}
	
	private List<CardAnnounceRecordModel> outToModel(List<FileOutProvinceDetailHis> outList){
		List<CardAnnounceRecordModel> cardmList =Lists.newArrayList();
		for (FileOutProvinceDetailHis fileOutProvinceDetail : outList) {
			CardAnnounceRecordModel model = new CardAnnounceRecordModel();
			model.setCardId("5201"+fileOutProvinceDetail.getCardId());
			model.setVehiclePlate(fileOutProvinceDetail.getLicense());
			if(fileOutProvinceDetail.getEnTime()!=null) {
				model.setEnTime(fileOutProvinceDetail.getEnTime().replace("T", " "));
			}
			if(fileOutProvinceDetail.getExTime()!=null) {
				model.setExTime(fileOutProvinceDetail.getExTime().replace("T", " "));
			}
			model.setEnName(fileOutProvinceDetail.getEnName());
            model.setExName(fileOutProvinceDetail.getExName());
            model.setPreBalance(fileOutProvinceDetail.getPreBalance());
            model.setPostBalance(fileOutProvinceDetail.getPostBalance());
            model.setFee(fileOutProvinceDetail.getFee());
            model.setConsumptionType("省外通行");
            cardmList.add(model);
		}
		return cardmList;
	}
	
	private List<CardAnnounceRecordModel> microToModel(List<MicroPayMentDetail>microList){
		List<CardAnnounceRecordModel> cardmList =Lists.newArrayList();
		for (MicroPayMentDetail microPayMentDetail : microList) {
			CardAnnounceRecordModel model = new CardAnnounceRecordModel();
			model.setCardId("5201"+microPayMentDetail.getCardId());
			model.setVehiclePlate(microPayMentDetail.getLicense());
			if(microPayMentDetail.getEnTime()!=null) {
				model.setEnTime(microPayMentDetail.getEnTime().replace("T", " "));
			}
			if(microPayMentDetail.getExTime()!=null) {
				model.setExTime(microPayMentDetail.getExTime().replace("T", " "));
			}
			model.setEnName(microPayMentDetail.getParkName());
            model.setExName(microPayMentDetail.getParkName());
            model.setPreBalance(microPayMentDetail.getPreBalance());
            model.setPostBalance(microPayMentDetail.getPostBalance());
            model.setFee(microPayMentDetail.getFee());
            model.setConsumptionType("省内停车场");
            cardmList.add(model);
		}
		return cardmList;
	}
	 
	private List<FileInprovinceDetail> pageQueryIn(CardAnnounceRecordRequest request){
		int pageNo = 1;
		InprovinceAnnounceRecordRequest req = new InprovinceAnnounceRecordRequest();
		if(request.getCardId()!=null)req.setCardId(request.getCardId().substring(4, request.getCardId().length()));
		if(request.getVehiclePlate()!=null)req.setVehiclePlate(request.getVehiclePlate());
		if(request.getStartTime()!=null&&request.getEndTime()!=null) {
			req.setEnTime(request.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			req.setExTime(request.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		req.setPageSize(20000);
		List<FileInprovinceDetail> pInList = Lists.newArrayList();
		for (;;) {
			req.setPageNo(pageNo);
			LargePagination<FileInprovinceDetail> largePage = fileInprovinceDetailRepo.largePage(req);
			List<FileInprovinceDetail> result = largePage.getResult();
			pInList.addAll(result);
			if (!largePage.isHasMore())
				break;
			pageNo++;
		}
		return pInList;
	}
	private List<FileOutProvinceDetailHis> pageQueryOut(CardAnnounceRecordRequest request){
		int pageNo = 1;
		OutprovinceAnnounceRecordRequest req = new OutprovinceAnnounceRecordRequest();
		if(request.getCardId()!=null)req.setCardId(request.getCardId().substring(4, request.getCardId().length()));
		if(request.getVehiclePlate()!=null)req.setVehiclePlate(request.getVehiclePlate());
		if(request.getStartTime()!=null&&request.getEndTime()!=null) {
			req.setEnTime(request.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			req.setExTime(request.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		req.setPageSize(20000);
		List<FileOutProvinceDetailHis> pOutList = Lists.newArrayList();
		for (;;) {
			req.setPageNo(pageNo);
			LargePagination<FileOutProvinceDetailHis> largePage = fileOutProvinceDetailHisRepo.largePage(req);
			List<FileOutProvinceDetailHis> result = largePage.getResult();
			pOutList.addAll(result);
			if (!largePage.isHasMore())
				break;
			pageNo++;
		}
		return pOutList;
	}
	
	private List<MicroPayMentDetail> pageMicro(CardAnnounceRecordRequest request){
		int pageNo = 1;
		MicroPayMentRequest req = new MicroPayMentRequest();
		if(request.getCardId()!=null)req.setCardId(request.getCardId().substring(4, request.getCardId().length()));
		if(request.getVehiclePlate()!=null)req.setVehiclePlate(request.getVehiclePlate());
		if(request.getStartTime()!=null&&request.getEndTime()!=null) {
			req.setEnTime(request.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			req.setExTime(request.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		req.setPageSize(20000);
		List<MicroPayMentDetail> pMicroList = Lists.newArrayList();
		for (;;) {
			req.setPageNo(pageNo);
			LargePagination<MicroPayMentDetail> largePage = microPayMentDetailRepo.largePage(req);
			List<MicroPayMentDetail> result = largePage.getResult();
			pMicroList.addAll(result);
			if (!largePage.isHasMore())
				break;
			pageNo++;
		}
		return pMicroList;
	}
	
	private List<TrafficRecordDetail> pageTrafficRecord(CardAnnounceRecordRequest request){
		int pageNo = 1;
		TrafficRecordRequest req = new TrafficRecordRequest();
		if(request.getCardId()!=null)req.setCardId(request.getCardId());
		if(request.getVehiclePlate()!=null)req.setVehiclePlate(request.getVehiclePlate());
		if(request.getStartTime()!=null&&request.getEndTime()!=null) {
			req.setEnTime(request.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			req.setExTime(request.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		req.setPageSize(20000);
		List<TrafficRecordDetail> pMicroList = Lists.newArrayList();
		for (;;) {
			req.setPageNo(pageNo);
			LargePagination<TrafficRecordDetail> largePage = trafficRecordDetailRepo.largePage(req);
			List<TrafficRecordDetail> result = largePage.getResult();
			pMicroList.addAll(result);
			if (!largePage.isHasMore())
				break;
			pageNo++;
		}
		return pMicroList;
	}
	private List<CardAnnounceRecordModel> trafficRecordToModel(List<TrafficRecordDetail>trafficRecordList){
		List<CardAnnounceRecordModel> cardmList =Lists.newArrayList();
		for (TrafficRecordDetail trafficRecordDetail : trafficRecordList) {
			CardAnnounceRecordModel model = new CardAnnounceRecordModel();
			model.setCardId(trafficRecordDetail.getCardId());
			model.setVehiclePlate(trafficRecordDetail.getVehiclePlate());
			if(trafficRecordDetail.getEnTime()!=null) {
				model.setEnTime(trafficRecordDetail.getEnTime().replace("T", " "));
			}
			if(trafficRecordDetail.getExTime()!=null) {
				model.setExTime(trafficRecordDetail.getExTime().replace("T", " "));
			}
			model.setEnName(trafficRecordDetail.getEntolllaneName());
            model.setExName(trafficRecordDetail.getExtolllaneName());
            model.setPreBalance(trafficRecordDetail.getPreBalance());
            model.setPostBalance(trafficRecordDetail.getPostBalance());
            model.setFee(trafficRecordDetail.getFee());
//            if(trafficRecordDetail.getSerialType()==SettlementSerialType.INPARK) {           	
//            	model.setConsumptionType("省内停车场");
//            }
            if(trafficRecordDetail.getSerialType()==SettlementSerialType.OUTPROVINCE) {
            	model.setConsumptionType("省外通行");
            }
            if(trafficRecordDetail.getSerialType()==SettlementSerialType.OUTGASSTATION) {
            	model.setConsumptionType("省外加油站");
            }
            if(trafficRecordDetail.getSerialType()==SettlementSerialType.OUTPARK) {
            	model.setConsumptionType("省外停车场");
            }
            if(trafficRecordDetail.getSerialType()==SettlementSerialType.OUTSERVICEAREA) {
            	model.setConsumptionType("省外服务区");
            }
            cardmList.add(model);
		}
		return cardmList;
	}
	   
}
