package cn.com.taiji.css.manager.customerservice.report;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.css.model.customerservice.report.DailyStatisticsModel;
import cn.com.taiji.css.model.util.ZipUtil;
import cn.com.taiji.qtk.repo.jpa.DailyStatisticsRepo;

/**
 * 日常报表导出
 * @author HHW
 */
@Service
public class DailyStatisticsManagerImpl implements DailyStatisticsManager {
	private final static String FILE_PATH = FileHelper.getDataPath() + "/dailyStatistics";
	private final static String TMP = "/tmp/";
	private static String LocalDateStartTime;
	private static String LocalDateEndTime;
	private static LocalDateTime startTime;
	private static LocalDateTime endTime;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DailyStatisticsRepo dailyStatisticsRepo;
	
	@Override
	public void run(String date) {
		LocalDate localdate = LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDateStartTime = localdate.plusDays(-1).toString()+"T17:00:00";
		LocalDateEndTime = localdate.toString()+"T16:59:59";
		String startTimeText = localdate.plusDays(-1).toString()+" 17:00:00";
		String endTimeText = localdate.toString()+" 16:59:59";
		startTime = LocalDateTime.parse(startTimeText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		endTime = LocalDateTime.parse(endTimeText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(startTime.toString() + "  "+ endTime.toString());
		createDailyWorkBook(date);
	}
	
	/**
	 * 生成每日报表
	 * @param date 
	 */
	public synchronized void createDailyWorkBook(String date) {
		System.out.println("------createDailyWorkBook------");
		logger.info("================开始查询每日报表================");
		if (new File(FILE_PATH + getZipFileName(date)).exists()) {
			 return;
		}
		//生成报表
		List<DailyStatisticsModel> staticticsModels = getStaticticsModels();
		for (DailyStatisticsModel dailyStatisticsModel : staticticsModels) {
			try {
				DailyStatisticsModel.createWorkBook(dailyStatisticsModel, FILE_PATH+TMP);
				logger.info(dailyStatisticsModel.getType().getWorkBookName() + "已生成！！");;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//生成压缩文件
		try {
//			ZipHelper.zip(new File(FILE_PATH+TMP), true, "utf-8", new File(FILE_PATH));
			ZipUtil.compress(FILE_PATH+TMP,FILE_PATH + '/' +endTime.toLocalDate().toString().replace("-", ""));
		} catch (Exception e) {
			logger.info("生成ZIP文件失败");
			e.printStackTrace();
		}
		logger.info("================生成每日报表结束================");
	}
	private String getZipFileName(String date) {
		String zipFileName = "每日报表" + LocalDate.parse(date).toString().replace("-", "");
		return zipFileName;
	}
	private List<DailyStatisticsModel> getStaticticsModels() {
		int i = 0;
		List<DailyStatisticsModel> dailyStatisticsModels = new ArrayList<DailyStatisticsModel>();
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query1(LocalDateStartTime, LocalDateEndTime),1));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query2(LocalDateEndTime),2));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query3(LocalDateStartTime, LocalDateEndTime),3));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query4(LocalDateStartTime, LocalDateEndTime),4));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query5(LocalDateStartTime, LocalDateEndTime),5));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query6(LocalDateStartTime, LocalDateEndTime),6));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query7(LocalDateStartTime, LocalDateEndTime),7));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query8(LocalDateStartTime, LocalDateEndTime),8));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query9(LocalDateStartTime, LocalDateEndTime),9));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query10(LocalDateStartTime, LocalDateEndTime),10));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query11(getPrecedingMonth(), LocalDateEndTime),11));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query12(LocalDateEndTime),12));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query13(LocalDateEndTime),13));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query14(startTime, endTime),14));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query15(endTime),15));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query16(LocalDateEndTime),16));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query17(LocalDateStartTime, LocalDateEndTime),17));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query18(LocalDateEndTime),18));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query19(startTime, endTime),19));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query20(LocalDateStartTime, LocalDateEndTime),20));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query21(LocalDateStartTime, LocalDateEndTime),21));
		System.out.println(++i);
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query22(startTime, endTime),22));
		System.out.println(++i);
//		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query23));
		dailyStatisticsModels.add(new DailyStatisticsModel(dailyStatisticsRepo.query24(LocalDateEndTime),24));
		System.out.println(++i);
		logger.info("查询日常报表结束，开始生成报表文件");
		return dailyStatisticsModels;
	}
	private String getPrecedingMonth() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(LocalDateEndTime.replace("T", " "),df);
		date = date.minusMonths(1);
		LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
		String startDate = lastDay.toLocalDate().toString();
		String dateTime = startDate + LocalDateEndTime.substring(10, LocalDateEndTime.length());
		return dateTime;
	}
}
