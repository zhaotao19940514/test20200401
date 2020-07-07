package cn.com.taiji.css.manager.daily;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.repo.jpa.QueryOutOBUDisposeRepo;

@Service
public class QueryOutOBUDisposeManagerImpl extends AbstractManager implements QueryOutOBUDisposeManager {
	
	@Autowired
	private QueryOutOBUDisposeRepo queryOutOBUDisposeRepo;
	private static AtomicBoolean isRunning = new AtomicBoolean(false);
	 
	@Override
	public File run(String date, String filePath) throws ManagerException {
		if (isRunning.compareAndSet(false, true)) {
			try {
				logger.info("开始执行");
				return execute(date,filePath);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				isRunning.set(false);
				logger.info("结束执行");
			}
		} else {
			throw new ManagerException("任务进行中,请勿重复执行!!");
		}
		return null;
	}

	private File execute(String date, String filePath) throws ManagerException {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMdd");
		String nowdate = LocalDate.now().format(ofPattern).toString();
		if (date.equals(nowdate)) {
			if (LocalDateTime.now().isBefore(LocalDateTime.parse(LocalDate.now()+"T12:00:00"))) {
				throw new ManagerException("查询当日时数据时,请在12点之后执行!!");
			}
		}
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdir();
		}
		if (file.exists()) {
			return file;
		}
		try {
			XSSFWorkbook wb = createWorkbook(date);
			FileOutputStream out = new FileOutputStream(file);
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("文件生成失败!!");
		}
		return file;
	}
	
	private XSSFWorkbook createWorkbook(String date) {
		DateTimeFormatter localDF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate endTime = LocalDate.parse(date,df);
		LocalDate startTime = endTime.minusDays(1);
		List<String> data = queryOutOBUDisposeRepo.findOutOBUByHandheld(
				startTime.format(df), 
				endTime.format(df), 
				LocalDateTime.parse(startTime.format(localDF) + "T12:00:00"), 
				LocalDateTime.parse(endTime.format(localDF) + "T11:59:59"))
				;
		XSSFWorkbook wb = new XSSFWorkbook();
		int rowIndex = 0;
		int cellIndex = 0;
		XSSFSheet sheet1 = wb.createSheet(date+"手持机处理量");
		XSSFRow row1 = sheet1.createRow(rowIndex++);
		row1.createCell(0).setCellValue("车牌");;
		if (data != null && data.size() > 0) {
			for (String vehicle : data) {
				XSSFRow row = sheet1.createRow(rowIndex++);
				row.createCell(0).setCellValue(vehicle);
			}
		}
		List<Object[]> data2 = queryOutOBUDisposeRepo.findOutOBUByCustomerService(
				startTime.format(localDF) + " 12:00:00", 
				endTime.format(localDF) + " 11:59:59");
		XSSFSheet sheet2 = wb.createSheet("客服系统处理量");
		rowIndex = 0;
		cellIndex = 0;
		XSSFRow sheet2row1 = sheet2.createRow(rowIndex++);
		sheet2row1.createCell(cellIndex++).setCellValue("车牌");
		sheet2row1.createCell(cellIndex++).setCellValue("OBU_ID");
		sheet2row1.createCell(cellIndex++).setCellValue("操作用户名");
		if (data2 != null && data2.size() > 0) {
			for (Object[] obj : data2) {
				Object vehicle = obj[0];
//				Object ObuId = obj[1];
//				Object userName = obj[2];
				cellIndex = 0;
				XSSFRow row = sheet2.createRow(rowIndex++);
				if (vehicle != null) {
					row.createCell(cellIndex++).setCellValue(vehicle.toString());
				}
//				if (ObuId != null) {
//					row.createCell(cellIndex++).setCellValue(ObuId.toString());
//				}
//				if (userName != null) {
//					row.createCell(cellIndex++).setCellValue(userName.toString());
//				}
			}
		}
		return wb;
	}

}

