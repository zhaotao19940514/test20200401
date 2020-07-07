package cn.com.taiji.css.manager.customerservice.report;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.ExcelTemplateHelper;
import cn.com.taiji.common.manager.pub.WorkbookWithDataHandler;
import cn.com.taiji.common.model.pub.DefaultContentInfo;
import cn.com.taiji.css.dao.jpa.DailyEverydayDao;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.model.customerservice.report.DailyEverydayRequest;

@Service
public class DailyEverydayManagerImpl implements DailyEverydayManager,WorkbookWithDataHandler<List<Object[]>>{
	
	@Autowired
	private DailyEverydayDao dailyEverydayDao;
	
//    private final String dailyPath = "D:/dailyEveryday/";
    private final String dailyPath = "/home/dailyEveryday/";
	private String timeDate;
	private String time;
	@Override
	public void export(DailyEverydayRequest queryModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		timeDate = queryModel.getTimeDate().replace("-", "");
		File files= new File(dailyPath);
		if(!files.exists()){//如果文件夹不存在
			files.mkdir();//创建文件夹
			}
		File file= new File(dailyPath+timeDate+".xlsx");
		if(!file.exists())
			makeExel(queryModel.getTimeDate());
		
	}
	@Override
	public void makeExel(String time) throws Exception {
		timeDate = time.replace("-", "");
		this.time = time;
		DefaultContentInfo info = new DefaultContentInfo();
		vidco(CssConstant.DAILY_EVERYDAY);
		info.setTemplateUrl("file:" + CssConstant.DAILY_EVERYDAY);
		info.setAlwaysNew(true);
		info.setSavePath(dailyPath);
		info.setFileName(timeDate+".xlsx");
		ExcelTemplateHelper.generateExcel(info, true, null, this);
	}
	@Override
	public void fillWorkbook(Workbook workbook, List<Object[]> data) throws Exception {
		workbook.setSheetName(0, "网点");
		Sheet sheet = workbook.getSheetAt(0);
		Cell cell = ExcelTemplateHelper.getCell(sheet, 0, 0);
		cell = ExcelTemplateHelper.getCell(sheet, 1, 0);
		List<Object[]> openCard = dailyEverydayDao.openCard(time);
		int i = 0;
		for (Object[] objects : openCard) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 0);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 1);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> openOBU = dailyEverydayDao.openOBU(time);
		i = 0;
		for (Object[] objects : openOBU) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 3);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 4);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> amountDeposited = dailyEverydayDao.amountDeposited(timeDate);
		i = 0;
		for (Object[] objects : amountDeposited) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 6);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 7);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> halfAmountDeposited = dailyEverydayDao.halfAmountDeposited(timeDate);
		i = 0;
		for (Object[] objects : halfAmountDeposited) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 9);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 10);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> accountsPrepaidPhone = dailyEverydayDao.accountsPrepaidPhone(timeDate);
		i = 0;
		for (Object[] objects : accountsPrepaidPhone) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 12);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 13);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> accountConsumption = dailyEverydayDao.accountConsumption(timeDate);
		i = 0;
		for (Object[] objects : accountConsumption) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 15);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 16);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> accountReversal = dailyEverydayDao.accountReversal(timeDate);
		i = 0;
		for (Object[] objects : accountReversal) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 19);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 20);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> impact = dailyEverydayDao.impact(timeDate);
		i = 0;
		for (Object[] objects : impact) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 22);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 23);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> refund = dailyEverydayDao.refund(timeDate);
		i = 0;
		for (Object[] objects : refund) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 25);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 26);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			i++;
		}
		List<Object[]> payAmount = dailyEverydayDao.payAmount(timeDate);
		i = 0;
		for (Object[] objects : payAmount) {
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 30);
			cell.setCellValue(objects[0] == null ? null : objects[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 31);
			cell.setCellValue(objects[1] == null ? null : objects[1].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 32);
			cell.setCellValue(objects[2] == null ? null : objects[2].toString());
			cell = ExcelTemplateHelper.getCell(sheet, i+1, 33);
			cell.setCellValue(objects[3] == null ? null : objects[3].toString());
			i++;
		}
	}

	
	private void vidco(String url) throws ManagerException{
		File file = new File(url);
		System.out.println(url);
		if(!file.exists()){
			throw new ManagerException("文件不存在");
		}
	}
}
