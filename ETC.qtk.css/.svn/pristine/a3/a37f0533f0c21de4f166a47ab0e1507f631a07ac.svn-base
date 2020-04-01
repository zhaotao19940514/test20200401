package cn.com.taiji.css.manager.customerservice.report;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.css.model.customerservice.report.LssuancePerBankModel;
import cn.com.taiji.css.model.customerservice.report.NativePlaceCirculationModel;
import cn.com.taiji.css.model.customerservice.report.QueryTimes;
import cn.com.taiji.css.repo.jpa.LssuancePerBankRepo;
import cn.com.taiji.css.repo.jpa.NativePlaceCirculationRepo;

@Service
public class LssuancePerBankManagerImpl implements LssuancePerBankManager {
	
	@Autowired
	private NativePlaceCirculationRepo nativePlaceCirculationRepo;
	@Autowired
	private LssuancePerBankRepo lssuancePerBankRepo;
	private LssuancePerBankModel model = new LssuancePerBankModel();

	@Override
	public Map<String, List<?>> page(QueryTimes queryModel) throws ManagerException{
		queryModel.Violation();
		//获取4张表的数据存入map集合中
		List<Object[]> result = lssuancePerBankRepo.ListByGTime(queryModel.getStartTime().toString(), queryModel.getEndTime().toString());
		List<Object[]> result2 = lssuancePerBankRepo.ListByNGTime(queryModel.getStartTime().toString(), queryModel.getEndTime().toString());
		List<LssuancePerBankModel> lssuancePerBank = model.getLssuancePerBank(result, result2);
		List<Object[]> listByEnableTime = nativePlaceCirculationRepo.listByNgEnableTime(queryModel.getStartTime().toString(),
				queryModel.getEndTime().toString());
		List<Object[]> listByEnableTime2 = nativePlaceCirculationRepo.listByYgEnableTime(queryModel.getStartTime().toString(),
				queryModel.getEndTime().toString());
		List<NativePlaceCirculationModel> nativePlaceCirculation = model.getNativePlaceCirculation(listByEnableTime, listByEnableTime2);
		Map<String,List<?>> map = new HashMap<String, List<?>>();
		map.put("result1", lssuancePerBank);
		map.put("result2", nativePlaceCirculation);
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public File getResult(QueryTimes qm) throws Exception{
		//获取数据
		Map<String, List<?>> result = this.page(qm);
		List<LssuancePerBankModel> lssuancePerBank = (List<LssuancePerBankModel>) result.get("result1");
		List<NativePlaceCirculationModel> nativePlaceCirculation = (List<NativePlaceCirculationModel>) result.get("result2");
		//获取路径
		File file = new File(FileHelper.getWebappPath()+"/data/lssuancePerBank/贵籍&非贵籍各行发行量.xlsx");
		FileOutputStream out = new FileOutputStream(file);
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("ALL贵籍&非贵籍拆分五大行");
		sheet.setColumnWidth(0, 20 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		//创建表头
		HSSFRow row1 = sheet.createRow(0);
		
		//创建表头的单元格-------------------------------
		row1.createCell(0).setCellValue("单位");
		row1.createCell(1).setCellValue("贵籍发行量");
		row1.createCell(2).setCellValue("非贵籍发行量");
		for (int i = 0,j = 1; i < lssuancePerBank.size(); i++, j++) {
			HSSFRow row = sheet.createRow(j);
			row.createCell(0).setCellValue(lssuancePerBank.get(i).getChannelName());
			row.createCell(1).setCellValue(lssuancePerBank.get(i).getGuiCount());		
			row.createCell(2).setCellValue(lssuancePerBank.get(i).getNoGuiCount());		
		}
		
		HSSFSheet sheet2 = wb.createSheet("ALL贵籍&非贵籍");
		sheet2.setColumnWidth(0, 20 * 256);
		sheet2.setColumnWidth(1, 20 * 256);
		sheet2.setColumnWidth(2, 20 * 256);
		//创建表头
		HSSFRow sheet2row1 = sheet2.createRow(0);
		//创建表头的单元格-------------------------------
		sheet2row1.createCell(0).setCellValue("单位");
		sheet2row1.createCell(1).setCellValue("贵籍发行量");
		sheet2row1.createCell(2).setCellValue("非贵籍发行量");
		for (int i = 0, j = 1; i < nativePlaceCirculation.size(); i++, j++) {
			HSSFRow row = sheet2.createRow(j);
			row.createCell(0).setCellValue(nativePlaceCirculation.get(i).getName());
			row.createCell(1).setCellValue(nativePlaceCirculation.get(i).getGuiCount());
			row.createCell(2).setCellValue(nativePlaceCirculation.get(i).getNoGuiCount());
		}
		
		wb.write(out);
		//关流
		out.close();
		return file;
	}


}        

//public void excelWrite() throws Exception {
//	//获得Excel文件输出流
//	FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Admin\\Desktop\\测试数据.xls"));
//	//创建excel工作簿对象
//	HSSFWorkbook wb = new HSSFWorkbook();
//	//创建excel页
//	HSSFSheet sheet = wb.createSheet("POI导出测试");
//	//创建表头
//	HSSFRow row1 = sheet.createRow(0);
//	//创建表头的单元格-------------------------------
//		HSSFCell cell1_1 = row1.createCell(0);
//		cell1_1.setCellValue("学号");
//		HSSFCell cell1_2 = row1.createCell(1);
//		cell1_2.setCellValue("姓名");
//		HSSFCell cell1_3 = row1.createCell(2);
//		cell1_3.setCellValue("年级");
//		HSSFCell cell1_4 = row1.createCell(3);
//		cell1_4.setCellValue("年龄");
//		HSSFCell cell1_5 = row1.createCell(4);
//		cell1_5.setCellValue("性别");
//	//--------------------------------------------
//		//写入一行内容：
//		HSSFRow row2 = sheet.createRow(1);
//			HSSFCell cell2_1 = row2.createCell(0);
//			cell2_1.setCellValue(1);
//			HSSFCell cell2_2 = row2.createCell(1);
//			cell2_2.setCellValue("阿荣");
//			HSSFCell cell2_3 = row2.createCell(2);
//			cell2_3.setCellValue("17(3)");
//			HSSFCell cell2_4 = row2.createCell(3);
//			cell2_4.setCellValue(20);
//			HSSFCell cell2_5 = row2.createCell(4);
//			cell2_5.setCellValue("男");
//			
//			
//		//输出excel的错误形式：
//		//out.write(fs.getBytes(), 0, fs.getBytes().length);
//		//正确形式：
//		wb.write(out);
//		//关流
//		out.close();
//}