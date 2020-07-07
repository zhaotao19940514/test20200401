package cn.com.taiji.css.model.customerservice.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.DailyStatisticsType;

public class DailyStatisticsModel {
	
	private List<Object[]> date;
	private DailyStatisticsType type;

	public DailyStatisticsModel() {
	}
	public DailyStatisticsModel(List<Object[]> date,int typeIndex) {
		this.date = date;
		this.type = DailyStatisticsType.getDailyStatisticsTypeByIndex(typeIndex);
	}

	public List<Object[]> getDate() {
		return date;
	}

	public void setDate(List<Object[]> date) {
		this.date = date;
	}
	public DailyStatisticsType getType() {
		return type;
	}
	public void setType(DailyStatisticsType type) {
		this.type = type;
	}
	
	/**
	 * 生成工作表
	 * @throws ManagerException 
	 * @throws FileNotFoundException 
	 */
	public static void createWorkBook(DailyStatisticsModel model, String filePath) throws ManagerException, FileNotFoundException {
		//创建
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		File workBookFile = new File(filePath + model.getType().getWorkBookName()+".xlsx");
		FileOutputStream out = new FileOutputStream(workBookFile);

		List<Object[]> date = model.getDate();
		//获取路径
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("导出工作表");
		sheet.createFreezePane(0,1,0,1);
		sheet.setColumnWidth(0, 20 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		//创建表头
		XSSFRow row1 = sheet.createRow(0);
		String[] cells = model.getType().getCells();
		//创建表头的单元格-------------------------------
		for (int i = 0; i < cells.length; i++) {
			row1.createCell(i).setCellValue(cells[i]);
		}
		for (int i = 0,j = 1; i < date.size(); i++, j++) {
			XSSFRow row = sheet.createRow(j);
			for (int k = 0; k < date.get(i).length; k++) {
				row.createCell(k).setCellValue(date.get(i)[k].toString());
			}
		}
		try {
			wb.write(out);
			//关流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
