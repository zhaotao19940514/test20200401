package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.pub.PoiExcelInfo;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.manager.util.FileWriter;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundAuditRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundAuditResponse;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.OrderChargeJsonRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.OrderChargeJsonResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.OrderCorrectJsonRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.OrderCorrectJsonResponse;
import cn.com.taiji.qtk.entity.CardRefundDetail;
import cn.com.taiji.qtk.repo.jpa.CardRefundDetailRepo;


@Service
public class ExpenseRefundAuditManagerImpl extends AbstractDsiCommManager implements ExpenseRefundAuditManager{

	
	@Autowired
	private CardRefundDetailRepo cardRefundDetailRepo;
	

	@Autowired
	private FinanceBinService financeBinService;
	
	@Override
	public LargePagination<CardRefundDetail> page(ExpenseRefundAuditRequest request) {
		if(request.getStatus()==1) {
			request.setStatus(null);
		}
		return cardRefundDetailRepo.largePage(request);
	}


	@Override
	public ExpenseRefundAuditResponse auditSure(String id) throws ManagerException {
			ExpenseRefundAuditResponse response=new ExpenseRefundAuditResponse();
			CardRefundDetail cardRefundDetail =cardRefundDetailRepo.findCardRefundDetailById(id);
			if(cardRefundDetail!=null&&cardRefundDetail.getStatus()!=0) {
				if(cardRefundDetail.getStatus()==-1) {
					cardRefundDetail.setStatus(1);
					cardRefundDetailRepo.save(cardRefundDetail);
				}
			}
			return response;
	}
	
	
	@Override
	public ExpenseRefundAuditResponse save(ExpenseRefundAuditRequest request, User user) throws ManagerException {
		ExpenseRefundAuditResponse response = new ExpenseRefundAuditResponse();
		if(request.getRefundFee()==0) {
			response.setMessage("退费审核金额不能为空!");
			return response;
		}
		CardRefundDetail cardRefundDetail  = cardRefundDetailRepo.findCardRefundDetailById(request.getId());
		if(cardRefundDetail.getBankCard()!=null) {
			cardRefundDetail.setStatus(5);//资金审核通过,将状态变更为审核通过待银行转账状态
			response.setMessage("消费退费审核通过!等待银行转账!");
		}else {
			cardRefundDetail.setStatus(4);//资金审核通过,请求喻伟接口平台接口，生成待使用订单，订单号前增加标识xftf
			OrderChargeJsonRequest req=new OrderChargeJsonRequest();
			OrderChargeJsonResponse rsp=new OrderChargeJsonResponse();
			req.setCardNo(request.getCardId());
			req.setAmount(request.getRefundFee()+"");
			try {
				rsp=financeBinService.orderCharge(req);
			} catch (ApiRequestException e) {
				e.printStackTrace();
				response.setStatus(-1);
				response.setMessage(e+"");
				return response;
			} catch (IOException e) {
				e.printStackTrace();
				response.setStatus(-1);
				response.setMessage(e+"");
				return response;
			}
			if(rsp.getStatus()!=1) {
				response.setMessage(rsp.getMessage());
				response.setStatus(-1);
				return response;
			}
			if(!("0".equals(rsp.getRc()))) {
				response.setMessage(rsp.getRmsg());
				response.setStatus(-1);
				return response;
			}
			response.setMessage("消费退费审核通过!已生成待圈存订单!");
			cardRefundDetail.setOrderChargeNo(rsp.getOrderNo());
		}
		cardRefundDetail.setRefundFee(request.getRefundFee());
		cardRefundDetail.setRefundDescription(request.getRefundDescription());
		cardRefundDetailRepo.save(cardRefundDetail);
		response.setStatus(1);
		return response;
	}
	
	
	@Override
	public ExpenseRefundAuditResponse update(ExpenseRefundAuditRequest request, User user) throws ManagerException {
		ExpenseRefundAuditResponse response = new ExpenseRefundAuditResponse();
		if(request.getRefundFee()==0) {
			response.setMessage("退费审核金额不能为空!");
			return response;
		}
		CardRefundDetail cardRefundDetail  = cardRefundDetailRepo.findCardRefundDetailById(request.getId());
		if(cardRefundDetail.getOrderChargeNo()!=null && cardRefundDetail.getOrderChargeNo()!="") {
			//先将旧的接口平台订单冲正
			OrderCorrectJsonRequest req=new OrderCorrectJsonRequest();
			OrderCorrectJsonResponse rsp=new OrderCorrectJsonResponse();
			req.setOutOrderNo(cardRefundDetail.getOrderChargeNo());
			req.setCardNo(cardRefundDetail.getCardId());
			try {
				rsp=financeBinService.orderCorrect(req);
			} catch (ApiRequestException | IOException e) {
				e.printStackTrace();
				response.setStatus(-1);
				response.setMessage(e+"");
				return response;
			}
			if(rsp.getStatus()!=1) {
				response.setMessage(rsp.getMessage());
				response.setStatus(-1);
				return response;
			}
			if(!("0".equals(rsp.getRc()))) {
				response.setMessage(rsp.getRmsg());
				response.setStatus(-1);
				return response;
			}
			//冲正成功后立即对修订后金额生成一条信息充值订单，金额为修订后金额
			OrderChargeJsonRequest reqCharge=new OrderChargeJsonRequest();
			OrderChargeJsonResponse rspCharge=new OrderChargeJsonResponse();
			reqCharge.setCardNo(cardRefundDetail.getCardId());
			reqCharge.setAmount(request.getRefundFee()+"");
			try {
				rspCharge=financeBinService.orderCharge(reqCharge);
			} catch (ApiRequestException e) {
				e.printStackTrace();
				response.setStatus(-1);
				response.setMessage(e+"");
				return response;
			} catch (IOException e) {
				e.printStackTrace();
				response.setStatus(-1);
				response.setMessage(e+"");
				return response;
			}
			if(rspCharge.getStatus()!=1) {
				response.setMessage(rsp.getMessage());
				response.setStatus(-1);
				return response;
			}
			if(!("0".equals(rspCharge.getRc()))) {
				response.setMessage(rsp.getRmsg());
				response.setStatus(-1);
				return response;
			}
			cardRefundDetail.setOrderChargeNo(rspCharge.getOrderNo());
		}
		cardRefundDetail.setRefundFee(request.getRefundFee());
		if(request.getRefundType()!=null) {
			cardRefundDetail.setRefundType(request.getRefundType());
		}
		if(request.getBank()!=null) {
			cardRefundDetail.setBank(request.getBank());
		}
		
		if(request.getBankCard()!=null) {
			cardRefundDetail.setBankCard(request.getBankCard());
		}
		
		if(request.getBankUserName()!=null) {
			cardRefundDetail.setBankUserName(request.getBankUserName());
		}
		
		if(request.getPhone()!=null) {
			cardRefundDetail.setPhone(request.getPhone());
		}
	
		if(request.getRefundDescription()!=null) {
			cardRefundDetail.setRefundDescription(request.getRefundDescription());
		}
		
		if(request.getFinanceDescription()!=null) {
			cardRefundDetail.setFinanceDescription(request.getFinanceDescription());
		}
		cardRefundDetailRepo.save(cardRefundDetail);
		response.setMessage("消费退费信息修改成功!");
		response.setStatus(1);
		return response;
	}
	
	
	public  ExpenseRefundApplicationResponse savePng(MultipartFile file,String id,String enableTime) throws ManagerException {
		ExpenseRefundApplicationResponse response =new ExpenseRefundApplicationResponse();
		CardRefundDetail cardRefundDetail =cardRefundDetailRepo.findCardRefundDetailById(id);
		enableTime = enableTime.replaceAll("[\\pP\\pS\\pZ]", "");
		String parentDirRelativePath = MyFinals.HALFAUDITING_IMG+File.separator+id+File.separator+enableTime;
		String fileAbsolutePath = FileWriter.savePic(file,cardRefundDetail, parentDirRelativePath);
		cardRefundDetail.setFilePath(fileAbsolutePath);
		cardRefundDetail.setFileName(FileWriter.generateFileName(cardRefundDetail,file));
		cardRefundDetailRepo.save(cardRefundDetail);
		response.setMessage("上传成功");
		return response;
}
	
	

	@Override
	public CardRefundDetail findById(String id) throws ManagerException {
		CardRefundDetail cardRefundDetail =cardRefundDetailRepo.findCardRefundDetailById(id);
		Long fee=cardRefundDetail.getTradeFee();
		cardRefundDetail.setTradeFee(fee/100);//数据库是分,页面需要展示为元
		return cardRefundDetail;
	}

	@Override
	public ExpenseRefundAuditResponse select(ExpenseRefundAuditRequest request) throws ManagerException {
		ExpenseRefundAuditResponse response=new ExpenseRefundAuditResponse();
		CardRefundDetail cardRefundDetail=cardRefundDetailRepo.findCardRefundDetailById(request.getId());
		if(cardRefundDetail.getCardId()!=null) {
			response.setId(cardRefundDetail.getId());
			if(cardRefundDetail.getRefundFee()!=null) {
				response.setRefundFee(cardRefundDetail.getRefundFee()/100);
			}
			response.setCardId(cardRefundDetail.getCardId());
			response.setStatus(cardRefundDetail.getStatus());
			response.setTradeFee(cardRefundDetail.getTradeFee()/100);
			response.setCreateTime(cardRefundDetail.getCreateTime());
			response.setVehiclePlate(cardRefundDetail.getVehiclePlate());
			response.setVehiclePlateColor(cardRefundDetail.getVehiclePlateColor());
			response.setPhone(cardRefundDetail.getPhone());
			if(cardRefundDetail.getFileName()!=null) {
				response.setFileName(cardRefundDetail.getFileName());
				response.setFilePath(cardRefundDetail.getFilePath());
			}
			response.setUserId(cardRefundDetail.getUserId());
			if(cardRefundDetail.getDetailedDescription()!=null) {
				response.setDetailedDescription(cardRefundDetail.getDetailedDescription());
			}
			if(cardRefundDetail.getRefundDescription()!=null) {
				response.setRefundDescription(cardRefundDetail.getRefundDescription());
			}
			response.setRefundType(cardRefundDetail.getRefundType());
			if(cardRefundDetail.getBankCard()!=null) {
				response.setBankCard(cardRefundDetail.getBankCard());
				response.setBank(cardRefundDetail.getBank());
				response.setBankUserName(cardRefundDetail.getBankUserName());
			}
		}else {
			response.setStatus(-1);
			response.setMessage("未查到此流水对应的消费退费申请记录,请联系管理员");
		}
		return response;
	}
	
	@Override
	public void exportExcel(ExpenseRefundAuditRequest queryModel,HttpServletRequest request,HttpServletResponse response) {
		HSSFWorkbook wb = new HSSFWorkbook(); 
		String wbname = "银行卡信息";
		HSSFSheet sheet = wb.createSheet(wbname); 
		HSSFRow row = sheet.createRow((int) 0);
		createSheet1(sheet,wb,row,queryModel);
		OutputStream out = null;
		
		try
		{
			response.reset();
            out = response.getOutputStream();
			response.setContentType("application/x-download;charset=UTF-8");
            String fileName = "export";
            String title = queryModel.getBeforeDate()+"_"+queryModel.getAfterDate()+"_待银行转账数据";
            fileName = java.net.URLEncoder.encode(title, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            wb.write(out);
            out.flush();
            out.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					
				}
				
			}
		}
	}
	private static CellStyle getTitleCellStyle(HSSFWorkbook wb){
	    CellStyle titleStyle = wb.createCellStyle();
	    HSSFFont titleFont = wb.createFont();
	    titleFont.setFontHeight((short) 400);
	    titleFont.setFontHeightInPoints((short)12);
	    titleStyle.setAlignment(HorizontalAlignment.CENTER);
	    titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	    titleStyle.setFont(titleFont);
	    return titleStyle;
	}
	private void createSheet1(HSSFSheet sheet,HSSFWorkbook wb,HSSFRow row,ExpenseRefundAuditRequest queryModel){
		CellStyle cellStyle =  getTitleCellStyle(wb);
		HSSFCell cell = row.createCell(0);
		cell = row.createCell(0);
		cell.setCellValue("卡号");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(1);
		cell.setCellValue("银行卡号");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(2);
		cell.setCellValue("银行卡开户人姓名");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(3);
		cell.setCellValue("联系方式");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(4);
		cell.setCellValue("录入时间");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(5);
		cell.setCellValue("退费金额(分)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(6);
		cell.setCellValue("工号");
		cell.setCellStyle(cellStyle);
		
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 8000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 10000);
		List<CardRefundDetail> list = null;
		if(StringUtils.hasText(queryModel.getCardId()) && StringUtils.hasText(queryModel.getBeforeDate()) && StringUtils.hasText(queryModel.getAfterDate()) ) {
			list = cardRefundDetailRepo.listCardRefundDetailByCardAndDate(queryModel.getCardId(), queryModel.getBeforeDate(), queryModel.getAfterDate(),queryModel.getStatus());
		}else if(StringUtils.hasText(queryModel.getCardId())){
			list = cardRefundDetailRepo.findCardRefundDetailByCardId(queryModel.getCardId(), queryModel.getStatus());
		}else if(StringUtils.hasText(queryModel.getBeforeDate()) && StringUtils.hasText(queryModel.getAfterDate())){
			list = cardRefundDetailRepo.listCardRefundDetailByDate(queryModel.getBeforeDate(), queryModel.getAfterDate(), queryModel.getStatus());
		}else {
			list = cardRefundDetailRepo.listCardRefundDetail(queryModel.getStatus());
		}
		
		
		int rowCount = 1;
		for(CardRefundDetail li:list) {
			row = sheet.createRow(rowCount);
			row.createCell(0).setCellValue(li.getCardId());
			row.createCell(1).setCellValue(li.getBankCard());
			row.createCell(2).setCellValue(li.getBankUserName());
			row.createCell(3).setCellValue(li.getPhone());
			row.createCell(4).setCellValue(li.getCreateTime());
			row.createCell(5).setCellValue(li.getRefundFee());
			row.createCell(6).setCellValue(li.getUserId());
			rowCount++;
		}
	}

	//驳回
	@Override
	public ExpenseRefundAuditResponse deleteById(ExpenseRefundAuditRequest request, User user) throws ManagerException {
		CardRefundDetail findById = cardRefundDetailRepo.findCardRefundDetailById(request.getId());
		cardRefundDetailRepo.delete(findById);
		ExpenseRefundAuditResponse response = new ExpenseRefundAuditResponse();
		response.setMessage("驳回消费退费申请成功！");
		response.setStatus(1);
		return response;
	}
	
	
	/**
	 * 获取行数据
	 * @throws IOException 
	 * 
	 */
	public List<ExpenseRefundAuditRequest> getLines(File importFile) throws IOException
	{
		PoiExcelInfo info = new PoiExcelInfo();
		info.setColSize(MyFinals.EXPENSE_REFUND_VALUE);//总数据列数
		info.setExcelInput(new FileInputStream(importFile));
		info.setSheetIndex(0);
		info.setFromRow(2);
		info.setToRow(-1);
		info.setBreakOnRowNull(true);
		info.setXlsx(true);
		Workbook workbook = createWorkbook(info.getExcelInput(), info.isXlsx());
		List<ExpenseRefundAuditRequest> rs = new ArrayList<ExpenseRefundAuditRequest>();
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
			ExpenseRefundAuditRequest e = row2Model(i, rowData);
			if (e != null) rs.add(e);
		}
		return rs;
	}
	
	
	public  ExpenseRefundAuditResponse saveFile(MultipartFile file) throws ManagerException {
		ExpenseRefundAuditResponse response =new ExpenseRefundAuditResponse();
		String parentDirRelativePath = MyFinals.REFUND_FILE;
		String fileAbsolutePath = savePic(file, parentDirRelativePath);
		String fileName=file.getOriginalFilename();
		response.setFilePath(fileAbsolutePath);
		response.setFileName(fileName);
		return response;
}
	
	private static String savePic(MultipartFile file,String parentDir) throws ManagerException{
		String destDirPath = FileHelper.getDataPath().concat(File.separator).concat(parentDir).concat(File.separator);
		File destDir = new File(destDirPath);
		if(!destDir.exists()){destDir.mkdirs();}
		String destFilePath = destDirPath+generateFileName(file.getOriginalFilename());
		File destFile = new File(destFilePath);
		saveFile(file,destFile);
		return destDirPath;
//		return getFilePathlWithOutRoot(destDirPath);
	}
	
	
	public static String generateFileName(String suffix) {
		return suffix;
	}
	public static String getSuffix(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
	public static String generateFileName(MultipartFile file){
		return getSuffix(file.getOriginalFilename());
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
	/**
	 * 
	 * @param file
	 * @param destFile
	 * @throws ManagerException
	 */
	private static void saveFile(MultipartFile file,File destFile) throws ManagerException{
		OutputStream out = null;
		try {
			out = new FileOutputStream(destFile);
			out.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("文件存储失败");
		}
		 finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new ManagerException("文件流关闭失败");
				}
			}
		}
	}
	
	
	public ExpenseRefundAuditRequest row2Model(int row, Map<Integer, Cell> rowData)
	{
		if (row == 0) return null;
		if (rowData.size() != MyFinals.EXPENSE_REFUND_VALUE)
		{
			logger.info(row + "行数据不完整");
			return null;
		}
		rowData.get(0).setCellType(CellType.STRING);//流水编号
		rowData.get(1).setCellType(CellType.STRING);//ETC卡号
		rowData.get(2).setCellType(CellType.STRING);//消费退费审核金额
		rowData.get(3).setCellType(CellType.STRING);//审核退费金额
		rowData.get(4).setCellType(CellType.STRING);//审核工号
		rowData.get(5).setCellType(CellType.STRING);//车辆编号
		rowData.get(6).setCellType(CellType.STRING);//联系方式
		rowData.get(7).setCellType(CellType.STRING);//状态标识 4为储值 （调用接口平台接口生产订单）  5为记账  
		rowData.get(8).setCellType(CellType.STRING);//银行名称
		rowData.get(9).setCellType(CellType.STRING);//银行卡号
		rowData.get(10).setCellType(CellType.STRING);//银行卡持有人姓名
		String id = rowData.get(0).getStringCellValue().replace(" ", "");
		String cardId = rowData.get(1).getStringCellValue().replace(" ", "");
		String tradeFee=rowData.get(2).getStringCellValue().replace(" ", "");
		String refundFee = rowData.get(3).getStringCellValue().replace(" ", "");
		String loginName = rowData.get(4).getStringCellValue().replace(" ", "");
		String vehicleId = rowData.get(5).getStringCellValue().replace(" ", "");
		String phone = rowData.get(6).getStringCellValue().replace(" ", "");
		String status= rowData.get(7).getStringCellValue().replace(" ", "");
		String bank=rowData.get(8).getStringCellValue().replace(" ", "");
		String bankCard=rowData.get(9).getStringCellValue().replace(" ", "");
		String bankUserName=rowData.get(10).getStringCellValue().replace(" ", "");
		ExpenseRefundAuditRequest info = new ExpenseRefundAuditRequest();
		if(id ==null || cardId==null || refundFee==null || loginName==null || vehicleId==null || phone==null) {
			return null;
		}
		if(id =="" || cardId=="" || refundFee=="" || loginName=="" || vehicleId=="" || phone=="") {
			return null;
		}
		info.setId(id);
		info.setCardId(cardId);
		info.setTradeFee(Long.parseLong(tradeFee));
		info.setVehiclePlate(vehicleId);
		info.setRefundFee(Long.parseLong(refundFee));
		info.setLoginName(loginName);
		info.setPhone(phone);
		info.setStatus(Integer.valueOf(status));
		info.setBank(bank);
		info.setBankCard(bankCard);
		info.setBankUserName(bankUserName);
		return info;
		
	}
	
	
	// 插入数据
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public ExpenseRefundAuditResponse importExcel(List<ExpenseRefundAuditRequest> expenseRefundAuditRequest) throws ManagerException {
		ExpenseRefundAuditResponse rsp=new ExpenseRefundAuditResponse();
		for(ExpenseRefundAuditRequest u: expenseRefundAuditRequest) {
			rsp=u.validateData();
			if(rsp.getStatus()==1) {
				CardRefundDetail findCardRefundDetailById = cardRefundDetailRepo.findCardRefundDetailById(u.getId());
				if(findCardRefundDetailById!=null) {
					if(findCardRefundDetailById.getOrderChargeNo()!=null && findCardRefundDetailById.getOrderChargeNo()!="") {
						rsp.setMessage("此通行流水："+u.getId()+"已生成接口平台订单!订单号为："+findCardRefundDetailById.getOrderChargeNo());
						rsp.setStatus(-1);
						return rsp;
					}
					rsp.setMessage("此通行流水已入库，不得重复录入！流水号为："+u.getId());
					rsp.setStatus(-1);
					return rsp; 
				}
				
				CardRefundDetail  c=new CardRefundDetail();
				c.setCardId(u.getCardId());
				c.setId(u.getId());
				c.setTradeFee(u.getTradeFee());
				c.setRefundFee(u.getRefundFee());
				c.setUserId(u.getLoginName());
				c.setVehiclePlate(u.getVehiclePlate());
				c.setPhone(u.getPhone());
				c.setCreateTime(CssUtil.getNowDateTimeStrWithT());
				if(u.getStatus()==4) {
					c.setStatus(6);//录入标识，如果生成订单成功则改为4
				}
				if(u.getStatus()==5) {//需录入银行卡号的
					c.setStatus(u.getStatus());
					c.setBankCard(u.getBankCard());
					c.setBank(u.getBank());
					c.setBankUserName(u.getBankUserName());
				}
				cardRefundDetailRepo.save(c);
				if(u.getStatus()==4) {
					OrderChargeJsonRequest reqCharge=new OrderChargeJsonRequest();
					OrderChargeJsonResponse rspCharge=new OrderChargeJsonResponse();
					reqCharge.setCardNo(u.getCardId());
					reqCharge.setAmount(u.getRefundFee().toString());
					try {
						rspCharge=financeBinService.orderCharge(reqCharge);
					} catch (ApiRequestException e) {
						e.printStackTrace();
						rsp.setStatus(-1);
						rsp.setMessage(e+"");
						return rsp;
					} catch (IOException e) {
						e.printStackTrace();
						rsp.setStatus(-1);
						rsp.setMessage(e+"");
						return rsp;
					}
					if(rspCharge.getStatus()!=1) {
						rsp.setMessage(rsp.getMessage());
						rsp.setStatus(-1);
						return rsp;
					}
					if(!("0".equals(rspCharge.getRc()))) {
						rsp.setMessage(rspCharge.getRmsg());
						rsp.setStatus(-1);
						return rsp;
					}
					c.setOrderChargeNo(rspCharge.getOrderNo());
					c.setStatus(u.getStatus());
					cardRefundDetailRepo.save(c);
				}
			}
		}
		return rsp;
	}


}

