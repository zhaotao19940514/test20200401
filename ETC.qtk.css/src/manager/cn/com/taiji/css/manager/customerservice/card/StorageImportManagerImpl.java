package cn.com.taiji.css.manager.customerservice.card;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.pub.PoiExcelInfo;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.qtk.entity.LkfInConsumeDetailsOl;

/**
 * @ClassName CancelRefundManagerImpl.java
 * @author zhaotao
 * @Description 
 * @date2018年11月12日
 */
@Service
public class StorageImportManagerImpl extends AbstractDsiCommManager implements StorageImportManager{

	/**
	 * 获取行数据
	 * @throws FileNotFoundException 
	 * 
	 */
	@Override
	public List<LkfInConsumeDetailsOl> getLines(File importFile) throws Exception
	{
		PoiExcelInfo info = new PoiExcelInfo();
		info.setColSize(37);//总数据列数
		info.setExcelInput(new FileInputStream(importFile));
		info.setSheetIndex(0);
		info.setFromRow(1);
		info.setToRow(-1);
		info.setBreakOnRowNull(true);
		info.setXlsx(true);
		Workbook workbook = createWorkbook(info.getExcelInput(), info.isXlsx());
		List<LkfInConsumeDetailsOl> rs = new ArrayList<LkfInConsumeDetailsOl>();
		Sheet sheet = workbook.getSheetAt(info.getSheetIndex()); 
		if (sheet == null) return rs;
		int fromRow = info.getFromRow() < 0 ? 0 : info.getFromRow();
		int toRow = info.getToRow() < 0 ? Integer.MAX_VALUE : info.getToRow();
		if (toRow < fromRow) throw new IllegalArgumentException("结束行号不能小于开始行号.");
		for (int i = 0; i <= toRow; i++)
		{
			if (i < fromRow) continue;
			Row row = sheet.getRow(i);
			if (info.isBreakOnRowNull() && row == null) break;
			if (row == null) continue;// 空行不退出时，忽略空行
			Map<Integer, Cell> rowData = new HashMap<Integer, Cell>();
			for (int j = 0; j < info.getColSize(); j++)
			{
				Cell cell = row.getCell(j);
				// 为空时，new一个空cell
				rowData.put(j, cell == null ? getCell(sheet, i, j) : cell);
			}
			LkfInConsumeDetailsOl e = row2Model(i, rowData);
			if (e != null) rs.add(e);
		}
		return rs;
	}
	private static Workbook createWorkbook(InputStream in, boolean xlsx) throws IOException
	{
		return xlsx ? new XSSFWorkbook(in) : new HSSFWorkbook(new POIFSFileSystem(in));
	}
	/**
	 * 获取指定单元格，存在返回值，不存在新建单元格
	 * 
	 * @param sheet
	 *            sheet页
	 * @param row
	 *            行号
	 * @param col
	 *            列号
	 * @return 单元格
	 */
	public static Cell getCell(Sheet sheet, int row, int col)
	{
		Row sheetRow = sheet.getRow(row);
		if (sheetRow == null) sheetRow = sheet.createRow(row);
		Cell cell = sheetRow.getCell(col);
		if (cell == null) cell = sheetRow.createCell(col);
		return cell;
	}
	public LkfInConsumeDetailsOl row2Model(int row, Map<Integer, Cell> rowData)
	{
		if (row == 0) return null;
		if (rowData.size() != 37)
		{
			logger.info(row + "行数据不完整");
			return null;
		}
		rowData.get(0).setCellType(CellType.STRING);
		rowData.get(1).setCellType(CellType.STRING);
		rowData.get(2).setCellType(CellType.STRING);
		rowData.get(3).setCellType(CellType.STRING);
		rowData.get(4).setCellType(CellType.STRING);
		
		rowData.get(5).setCellType(CellType.STRING);
		rowData.get(6).setCellType(CellType.STRING);
		rowData.get(7).setCellType(CellType.STRING);
		rowData.get(8).setCellType(CellType.STRING);
		rowData.get(9).setCellType(CellType.STRING);
		
		rowData.get(10).setCellType(CellType.STRING);
		rowData.get(11).setCellType(CellType.STRING);
		rowData.get(12).setCellType(CellType.STRING);
		rowData.get(13).setCellType(CellType.STRING);
		rowData.get(14).setCellType(CellType.STRING);
		
		rowData.get(15).setCellType(CellType.STRING);
		rowData.get(16).setCellType(CellType.STRING);
		rowData.get(17).setCellType(CellType.STRING);
		rowData.get(18).setCellType(CellType.STRING);
		rowData.get(19).setCellType(CellType.STRING);
		
		rowData.get(20).setCellType(CellType.STRING);
		rowData.get(21).setCellType(CellType.STRING);
		rowData.get(22).setCellType(CellType.STRING);
		rowData.get(23).setCellType(CellType.STRING);
		rowData.get(24).setCellType(CellType.STRING);
		
		rowData.get(25).setCellType(CellType.STRING);
		rowData.get(26).setCellType(CellType.STRING);
		rowData.get(27).setCellType(CellType.STRING);
		rowData.get(28).setCellType(CellType.STRING);
		rowData.get(29).setCellType(CellType.STRING);
		
		rowData.get(30).setCellType(CellType.STRING);
		rowData.get(31).setCellType(CellType.STRING);
		rowData.get(32).setCellType(CellType.STRING);
		rowData.get(33).setCellType(CellType.STRING);
		rowData.get(34).setCellType(CellType.STRING);
		
		rowData.get(35).setCellType(CellType.STRING);
		rowData.get(36).setCellType(CellType.STRING);
		String id =rowData.get(0).getStringCellValue().replace(" ", "");
		String clearTargetDate = rowData.get(1).getStringCellValue().replace(" ", "");
		String serviceProviderId = rowData.get(2).getStringCellValue().replace(" ", "");
		String issuerId = rowData.get(3).getStringCellValue().replace(" ", "");
		String messageId =rowData.get(4).getStringCellValue().replace(" ", "");
		String tradeCount =rowData.get(5).getStringCellValue().replace(" ", "");
		
		String amount =rowData.get(6).getStringCellValue().replace(" ", "");
		String transId = rowData.get(7).getStringCellValue().replace(" ", "");
		String opTime = rowData.get(8).getStringCellValue();
		String etcMoney =rowData.get(9).getStringCellValue().replace(" ", "");
		String vehType =rowData.get(10).getStringCellValue().replace(" ", "");
		
		String exName = rowData.get(11).getStringCellValue().replace(" ", "");
		String stationNo = rowData.get(12).getStringCellValue().replace(" ", "");
		String laneNo =rowData.get(13).getStringCellValue().replace(" ", "");
		
		
		String enName =rowData.get(14).getStringCellValue().replace(" ", "");
		String instationNo = rowData.get(15).getStringCellValue().replace(" ", "");
		String inlaneNo = rowData.get(16).getStringCellValue().replace(" ", "");
		String inopTime =rowData.get(17).getStringCellValue();
		String cardId =rowData.get(18).getStringCellValue().replace(" ", "");
		
		String cardType =rowData.get(19).getStringCellValue().replace(" ", "");
		String netNo = rowData.get(20).getStringCellValue().replace(" ", "");
		String vehPlate = rowData.get(21).getStringCellValue().replace(" ", "");
		String preBalance =rowData.get(22).getStringCellValue().replace(" ", "");
		String postBalance =rowData.get(23).getStringCellValue().replace(" ", "");
		
		String tac =rowData.get(24).getStringCellValue().replace(" ", "");
		String transType = rowData.get(25).getStringCellValue().replace(" ", "");
		String terminalNo = rowData.get(26).getStringCellValue().replace(" ", "");
		String terminalTransNo =rowData.get(27).getStringCellValue().replace(" ", "");
		String obuId =rowData.get(28).getStringCellValue().replace(" ", "");
		
		String eexitType =rowData.get(29).getStringCellValue().replace(" ", "");
		String conSumPtionType = rowData.get(30).getStringCellValue().replace(" ", "");
		String clearDate = rowData.get(31).getStringCellValue().replace(" ", "");
		String createTime =rowData.get(32).getStringCellValue();
		String detail =rowData.get(33).getStringCellValue().replace(" ", "");
		String laneType =rowData.get(35).getStringCellValue().replace(" ", "");
		String inlaneType =rowData.get(36).getStringCellValue().replace(" ", "");
		
		LkfInConsumeDetailsOl info = new LkfInConsumeDetailsOl();
		info.setAmount((long)(Double.parseDouble(amount)));
		info.setCardId(cardId);
		info.setCardType(Integer.parseInt(cardType));
		info.setClearDate(clearDate);
		info.setClearTargetDate(clearTargetDate);
		
		info.setConSumPtionType(Integer.parseInt(conSumPtionType));
		info.setCreateTime(LocalDateTime.parse(createTime.substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		info.setDetail(detail);
		info.setEexitType(Integer.parseInt(eexitType));
		info.setEnName(enName);
		
		info.setEtcMoney(Long.parseLong(etcMoney));
		info.setExName(exName);
		info.setId(id);
		info.setInlaneNo(Integer.parseInt(inlaneNo));
		info.setInlaneType(inlaneType);
		
		info.setInopTime(LocalDateTime.parse(inopTime.substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		info.setInstationNo(Integer.parseInt(instationNo));
		info.setIssuerId(issuerId);
		info.setLaneNo(Integer.parseInt(laneNo));
		info.setLaneType(laneType);
		
		info.setMessageId(messageId);
		info.setNetNo(netNo);
		info.setObuId(obuId);
		info.setOpTime(LocalDateTime.parse(opTime.substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		info.setPostBalance(Long.parseLong(postBalance));
		
		info.setPreBalance(Long.parseLong(preBalance));
		info.setServiceProviderId(serviceProviderId);
		info.setStationNo(Integer.parseInt(stationNo));
		info.setTac(tac);
		info.setTerminalNo(terminalNo);
		
		info.setTerminalTransNo(terminalTransNo);
		info.setTradeCount(Integer.parseInt(tradeCount));
		info.setTransId(transId);
		info.setTransType(transType);
		info.setVehPlate(vehPlate);
		info.setPartDate(info.getOpTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		info.setVehType(Integer.parseInt(vehType));
		/*String serviceHallName = rowData.get(2).getStringCellValue();
		String type = rowData.get(3).getStringCellValue();
		String count = rowData.get(4).getStringCellValue().replace(" ", "");
		StorageImportRequest info = new StorageImportRequest();
		info.setAgencyname(agencyName);
		info.setServiceHallName(serviceHallName);
		info.setCount(count);
		info.setType(type);*/
		return info;
	}
}

