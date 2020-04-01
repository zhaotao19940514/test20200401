package cn.com.taiji.css.manager.apply.baseinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.pub.PoiExcelInfo;
import cn.com.taiji.css.entity.BatchIssueBaseInfo;
import cn.com.taiji.css.entity.dict.BatchIssueStatus;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.apply.inportInfomation.BatchIssueBaseInfoShowModel;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationRequset;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationResponse;
import cn.com.taiji.css.model.util.RechargeIdUniqueNo;
import cn.com.taiji.css.repo.jpa.BatchIssueBaseInfoRepo;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName InportInfomationManagerImpl
 * @Description TODO
 * @author fxd
 * @date 2019年07月24日 17:16:38
 */
@Service
public class InportInfomationManagerImpl extends AbstractDsiCommManager implements InportInfomationManager{
	
	@Autowired
	private BatchIssueBaseInfoRepo batchIssueBaseInfoRepo;
	private static final Map<Integer,String> userType = new HashMap<Integer, String>();
	/**
	 * 证件类型
	 */
	private static final Map<Integer,String> identType = new HashMap<Integer, String>();
	static	{
			identType.put(101 ,"身份证（含临时身份证）");
			identType.put(102 ,"护照（限外籍人士）");
			identType.put(103 ,"港澳居民来往内地通行证");
			identType.put(104 ,"台湾居民来往大陆通行证");
			identType.put(105 ,"军官证");
			identType.put(106 ,"武警警察身份证");
			identType.put(201 ,"统一社会信用代码证书");
			identType.put(202 ,"组织机构代码证");
			identType.put(203 ,"营业执照");
			identType.put(204 ,"事业单位法人证书");
			identType.put(205 ,"社会团体法人登记证书");
			identType.put(206 ,"律师事务所执业许可证");
			userType.put(1, "个人");
			userType.put(2, "单位");
		}
	
	
	/**
	 * 获取行数据
	 * @throws IOException 
	 * 
	 */
	public List<BatchIssueBaseInfo> getLines(File importFile) throws IOException
	{
		PoiExcelInfo info = new PoiExcelInfo();
		info.setColSize(MyFinals.INPORT_INFO_SERVICE);//总数据列数
		info.setExcelInput(new FileInputStream(importFile));
		info.setSheetIndex(0);
		info.setFromRow(2);
		info.setToRow(-1);
		info.setBreakOnRowNull(true);
		info.setXlsx(true);
		Workbook workbook = createWorkbook(info.getExcelInput(), info.isXlsx());
		List<BatchIssueBaseInfo> rs = new ArrayList<BatchIssueBaseInfo>();
		Sheet sheet = workbook.getSheetAt(info.getSheetIndex()); 
		if (sheet == null) return rs;
		int fromRow = info.getFromRow() < 0 ? 0 : info.getFromRow();
		int toRow = info.getToRow() < 0 ? Integer.MAX_VALUE : info.getToRow();
		if (toRow < fromRow) throw new IllegalArgumentException("结束行号不能小于开始行号.");
		String batchId=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))+CssUtil.getRandomString(5);
		for (int i = 0; i <= toRow; i++)
		{
			System.out.println("----------------------正在执行第:"+i);
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
			BatchIssueBaseInfo e = row2Model(i, rowData,batchId);
			if (e != null) rs.add(e);
		}
		return rs;
	}
	
	
	public  InportInfomationResponse saveFile(MultipartFile file) throws ManagerException {
		InportInfomationResponse response =new InportInfomationResponse();
		String parentDirRelativePath = MyFinals.BATCHISSUEBASEINFO;
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
	
	public BatchIssueBaseInfo row2Model(int row, Map<Integer, Cell> rowData,String batchId)
	{
		try {
//		String intRand=RechargeIdUniqueNo.getSerialNo();
		String orderId=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+CssUtil.getRandomString(5);

		if (row == 0) return null;
		if (rowData.size() != MyFinals.INPORT_INFO_SERVICE)
		{
			logger.info(row + "行数据不完整");
			return null;
		}
		rowData.get(0).setCellType(CellType.STRING);//  套餐编号
		rowData.get(1).setCellType(CellType.STRING);//	用户类型
		rowData.get(2).setCellType(CellType.STRING);//	用户姓名
		rowData.get(3).setCellType(CellType.STRING);//	证件类型
		rowData.get(4).setCellType(CellType.STRING);//	证件号
		rowData.get(5).setCellType(CellType.STRING);//	地址
		rowData.get(6).setCellType(CellType.STRING);//	手机号
		rowData.get(7).setCellType(CellType.STRING);//	部门
		rowData.get(8).setCellType(CellType.STRING);//	经办人姓名
		rowData.get(9).setCellType(CellType.STRING);//	经办人证件类型
		rowData.get(10).setCellType(CellType.STRING);//	经办人证件号
		rowData.get(11).setCellType(CellType.STRING);//	车牌号
		rowData.get(12).setCellType(CellType.STRING);//	车牌颜色
		rowData.get(13).setCellType(CellType.STRING);//	车辆类型
		rowData.get(14).setCellType(CellType.STRING);//	车辆所有人
		rowData.get(15).setCellType(CellType.STRING);//	机动车所有人名称
		rowData.get(16).setCellType(CellType.STRING);//	机动车所有人证件类型
		rowData.get(17).setCellType(CellType.STRING);//	所有人证件号码
		rowData.get(18).setCellType(CellType.STRING);//	所有人联系方式
		rowData.get(19).setCellType(CellType.STRING);//	收费车型
		rowData.get(20).setCellType(CellType.STRING);//	车辆使用性质
		rowData.get(21).setCellType(CellType.STRING);//	注册日期
		rowData.get(22).setCellType(CellType.STRING);//	发证日期
		rowData.get(23).setCellType(CellType.STRING);//	车辆识别代号
		rowData.get(24).setCellType(CellType.STRING);//	车辆发动机号
		rowData.get(25).setCellType(CellType.STRING);//	档案号
		rowData.get(26).setCellType(CellType.STRING);//	核定载人数
		rowData.get(27).setCellType(CellType.STRING);//	总质量
		rowData.get(28).setCellType(CellType.STRING);//	整备质量
		rowData.get(29).setCellType(CellType.STRING);//	核定载质量
		rowData.get(30).setCellType(CellType.STRING);//	外廓尺寸
		rowData.get(31).setCellType(CellType.STRING);//	准牵引总质量
		rowData.get(32).setCellType(CellType.STRING);//	检验记录
		rowData.get(33).setCellType(CellType.STRING);//	车轮数
		rowData.get(34).setCellType(CellType.STRING);//	车轴数
		rowData.get(35).setCellType(CellType.STRING);//	轴距
		rowData.get(36).setCellType(CellType.STRING);//	轴型
		rowData.get(37).setCellType(CellType.STRING);//	用户详细地址
		rowData.get(38).setCellType(CellType.STRING);//	机构编号（签约渠道机构编号）
		
//		rowData.get(38).setCellType(CellType.STRING);//	取货方式
//		rowData.get(39).setCellType(CellType.STRING);//	收件人号码
//		rowData.get(40).setCellType(CellType.STRING);//	收件人名称
//		rowData.get(41).setCellType(CellType.STRING);//	省
//		rowData.get(42).setCellType(CellType.STRING);//	市
//		rowData.get(43).setCellType(CellType.STRING);//	区
//		rowData.get(44).setCellType(CellType.STRING);//	街道片区
//		rowData.get(45).setCellType(CellType.STRING);//	用户详细地址
//		rowData.get(46).setCellType(CellType.STRING);//	机构编号（签约渠道机构编号）
		 
		 Integer packageNum;
		Integer userType;
		String userName;
		Integer identType;
		String identNo;
		String address;
		String userMobile;
		String department;
		String agentName;
		Integer agentIdType;
		String agentIdNum;
		String vehiclePlate;
		Integer vehiclePlateColor;
		Integer vehicleType;
		String vehicleOwner;
		String ownerName;
		Integer ownerIdType;
		String ownerIdNum;
		String ownerTel;
		Integer type;
		Integer useCharacter;
		String registerDate;
		String issueDate;
		String vin;
		String engineNum;
		String fileNum;
		Integer approvedCount;
		Integer totalMass;
		Integer maintenanceMass;
		Integer permittedWeight;
		String outsideDimensions;
		Integer permittedTowWeight;
		String testRecord;
		Integer wheelCount;
		Integer axleCount;
		Integer axleDistance;
		String axisType;
		String orderNo;
		String detailAddress;
		String agencyId;
		
			packageNum = Integer.valueOf(rowData.get(0).getStringCellValue().trim());
			 userType = Integer.valueOf(rowData.get(1).getStringCellValue().trim());
			 userName = rowData.get(2).getStringCellValue().trim();
			 identType = Integer.valueOf(rowData.get(3).getStringCellValue().trim());
			 identNo = rowData.get(4).getStringCellValue().trim();
			 address = rowData.get(5).getStringCellValue().trim();
			 userMobile = rowData.get(6).getStringCellValue().trim();
			 department = rowData.get(7).getStringCellValue().trim();
			 agentName = rowData.get(8).getStringCellValue().trim();
			 agentIdType = Integer.valueOf(rowData.get(9).getStringCellValue().trim());
			 agentIdNum = rowData.get(10).getStringCellValue().trim();
			 vehiclePlate = rowData.get(11).getStringCellValue().trim();
			 vehiclePlateColor = Integer.valueOf(rowData.get(12).getStringCellValue().trim());
			 vehicleType = Integer.valueOf(rowData.get(13).getStringCellValue().trim());
			 vehicleOwner = rowData.get(14).getStringCellValue().trim();
			 ownerName = rowData.get(15).getStringCellValue().trim();
			 ownerIdType = Integer.valueOf(rowData.get(16).getStringCellValue().trim());
			 ownerIdNum = rowData.get(17).getStringCellValue().trim();
			 ownerTel = rowData.get(18).getStringCellValue().trim();
			 type = Integer.valueOf(rowData.get(19).getStringCellValue().trim());
			 useCharacter = Integer.valueOf(rowData.get(20).getStringCellValue().trim());
			 registerDate = rowData.get(21).getStringCellValue().trim();
			 issueDate = rowData.get(22).getStringCellValue().trim();
			 vin = rowData.get(23).getStringCellValue().trim();
			 engineNum = rowData.get(24).getStringCellValue().trim();
			 fileNum = rowData.get(25).getStringCellValue().trim();
			 approvedCount = Integer.valueOf(rowData.get(26).getStringCellValue().trim());
			 totalMass = Integer.valueOf(rowData.get(27).getStringCellValue().trim());
			 maintenanceMass = Integer.valueOf(rowData.get(28).getStringCellValue().trim());
			 permittedWeight = Integer.valueOf(rowData.get(29).getStringCellValue().trim());
			 outsideDimensions = rowData.get(30).getStringCellValue().trim();
			 permittedTowWeight = Integer.valueOf(rowData.get(31).getStringCellValue().trim());
			 testRecord = rowData.get(32).getStringCellValue().trim();
			 wheelCount = Integer.valueOf(rowData.get(33).getStringCellValue().trim());
			 axleCount = Integer.valueOf(rowData.get(34).getStringCellValue().trim());
			 axleDistance = Integer.valueOf(rowData.get(35).getStringCellValue().trim());
			 axisType = rowData.get(36).getStringCellValue().trim();
			 orderNo = orderId;
			 detailAddress = rowData.get(37).getStringCellValue().trim();
			 agencyId = rowData.get(38).getStringCellValue().trim();
		

		BatchIssueBaseInfo info = new BatchIssueBaseInfo();
		info.setPackageNum(packageNum);
		info.setUserType(userType);
		info.setUserName(userName);
		info.setIdentNo(identNo);
		info.setIdentType(identType);
		info.setAddress(address);
		info.setUserMobile(userMobile);
		info.setDepartment(department);
		info.setAgentName(agentName);
		info.setAgentIdType(agentIdType);
		info.setAgentIdNum(agentIdNum);
		info.setVehiclePlate(vehiclePlate);
		info.setVehiclePlateColor(vehiclePlateColor);
		info.setVehicleType(vehicleType);
		info.setVehicleOwner(vehicleOwner);
		info.setOwnerName(ownerName);
		info.setOwnerIdType(ownerIdType);
		info.setOwnerIdNum(ownerIdNum);
		info.setOwnerTel(ownerTel);
		info.setType(type);
		info.setUseCharacter(useCharacter);
		info.setRegisterDate(registerDate);
		info.setIssueDate(issueDate);
		info.setVin(vin);
		info.setEngineNum(engineNum);
		info.setFileNum(fileNum);
		info.setApprovedCount(approvedCount);
		info.setTotalMass(totalMass);
		info.setMaintenanceMass(maintenanceMass);
		info.setPermittedWeight(permittedWeight);
		info.setOutsideDimensions(outsideDimensions);
		info.setPermittedTowWeight(permittedTowWeight);
		info.setTestRecord(testRecord);
		info.setWheelCount(wheelCount);
		info.setAxleCount(axleCount);
		info.setAxleDistance(axleDistance);
		info.setAxisType(axisType);
		info.setOrderNo(orderNo);
//		info.setPostal(postal);
//		info.setReceiverMobile(receiverMobile);
//		info.setReceiverName(receiverName);
//		info.setProvince(province);
//		info.setCity(city);
//		info.setCounty(county);
//		info.setDistrict(district);
		info.setDetailAddress(detailAddress);
		info.setAgencyId(agencyId);
		info.setCreateTime(CssUtil.getNowDateTimeStrWithoutT());
		info.setBatchId(batchId);
		return info;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("数据转换异常：--------------------------数据源为"+rowData);
		}
		return null;
	}
	
	
	// 插入数据
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public InportInfomationResponse importExcel(List<BatchIssueBaseInfo> inportInfomationRequset) throws ManagerException {
		InportInfomationResponse rsp=new InportInfomationResponse();
		List<BatchIssueBaseInfo> status=new ArrayList<BatchIssueBaseInfo>();
		int i =0;
		for(BatchIssueBaseInfo u: inportInfomationRequset) {
			i++;
			System.out.println("共"+inportInfomationRequset.size()+"条，现已校验到第"+i);
			rsp=u.validate();
			if(rsp.getStatus()==1) {
				//校验通过将状态赋值为待生成订单
				u.setStatus(BatchIssueStatus.WAITHANDLE);
				status.add(u);
				rsp.setMessage("数据导入成功！批次号："+u.getBatchId());
				rsp.setStatus(1);
			}else {
				//校验通过将状态赋值为数据校验失败
				u.setStatus(BatchIssueStatus.INFOMATIONLOSE);
				status.add(u);
				rsp.setMessage(rsp.getMessage());
				System.out.println("数据校验格式失败------车牌："+ u.getVehiclePlate() +"失败原因："+ rsp.getMessage());
				rsp.setStatus(-1);
			}
			
		}
		batchIssueBaseInfoRepo.persistAll(status);
		return rsp;
	}


	@Override
	public LargePagination<BatchIssueBaseInfoShowModel> query(InportInfomationRequset queryModel) {
		queryModel.paramCheck();
		LargePagination<BatchIssueBaseInfo> largePage = batchIssueBaseInfoRepo.largePage(queryModel);
		if (largePage.getResult().isEmpty()) {
			return null;
		}
		List<BatchIssueBaseInfoShowModel> modelList = new ArrayList<BatchIssueBaseInfoShowModel>();
		for(BatchIssueBaseInfo oldModel : largePage.getResult()) {
			BatchIssueBaseInfoShowModel newModel = new BatchIssueBaseInfoShowModel();
			newModel.setIdentNo(oldModel.getIdentNo());
			newModel.setIdentType(identType.get(oldModel.getIdentType()));
			newModel.setOrderNo(oldModel.getOrderNo());
			newModel.setRespMessage(oldModel.getRespMessage());
			newModel.setUserMobile(oldModel.getUserMobile());
			newModel.setUserType(userType.get(oldModel.getUserType()));
			newModel.setVehiclePlate(oldModel.getVehiclePlate());
			newModel.setVehiclePlateColor(VehiclePlateColorType.valueOfCode(oldModel.getVehiclePlateColor()).getValue());
			modelList.add(newModel);
		}
		LargePagination<BatchIssueBaseInfoShowModel> pagn = new LargePagination<BatchIssueBaseInfoShowModel>();
		pagn.setResult(modelList);
		pagn.setPageSize(largePage.getPageSize());
		pagn.setCurrentPage(largePage.getCurrentPage());
		pagn.setHasMore(largePage.isHasMore());
		return pagn;
	}
	
	
	@Override
	public List<InportInfomationRequset> queryBatch() {
		List<InportInfomationRequset> qqq=new ArrayList<InportInfomationRequset>();
		List<Object[]> queryBatch = batchIssueBaseInfoRepo.queryBatchIdAndCreateTime();
		for(Object[] q :queryBatch) {
			InportInfomationRequset  req =new InportInfomationRequset();
			req.setBatchId(q[0].toString());
			req.setCreateTime(q[1].toString());
			qqq.add(req);
		}
		return qqq;
	}
	
	
	public File getExcelFilePath(InportInfomationRequset queryModel,HttpServletRequest request) throws ManagerException {
		queryModel.paramCheck();
	    File file = new File("/home/filedata/batchExport/"+queryModel.getBatchId()+"-"+BatchIssueStatus.fromCode(queryModel.getStatus()).getValue()+".xls");
	    if(!file.getParentFile().exists()) {
	    	file.getParentFile().mkdirs();
	    }
	    if(file.exists()) {
	    	//文件存在
	    	return file;
	    }
		List<BatchIssueBaseInfo> list =  batchIssueBaseInfoRepo.listByStatusAndBatchId(BatchIssueStatus.fromCode(queryModel.getStatus()), queryModel.getBatchId());
        // 创建excel
        HSSFWorkbook wk = new HSSFWorkbook();
        // 创建一张工作表
        HSSFSheet sheet = wk.createSheet();
        // 2
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20*100);
        HSSFRow row = sheet.createRow(0);
        // 创建第一行的第一个单元格
        // 想单元格写值
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("序号");
        cell = row.createCell((short)1);
        cell.setCellValue("用户类型");
        cell = row.createCell((short)2);
        cell.setCellValue("证件类型	");
        cell = row.createCell((short)3);
        cell.setCellValue("证件号 ");
        cell = row.createCell((short)4);
        cell.setCellValue("车牌");
        cell = row.createCell((short)5);
        cell.setCellValue("车牌颜色	");
        cell = row.createCell((short)6);
        cell.setCellValue("手机号	");
        cell = row.createCell((short)7);
        cell.setCellValue("订单号	");
        cell = row.createCell((short)8);
        cell.setCellValue("响应信息");
        // 创建第一行
        for (short i=0;i<list.size();i++)
        {
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(i);
            row.createCell(1).setCellValue(list.get(i).getUserType()==1?"个人":"单位");
            row.createCell(2).setCellValue(list.get(i).getIdentType());
            row.createCell(3).setCellValue(list.get(i).getIdentNo());
            row.createCell(4).setCellValue(list.get(i).getVehiclePlate());
            row.createCell(5).setCellValue(list.get(i).getVehiclePlateColor());
            row.createCell(6).setCellValue(list.get(i).getOwnerTel());
            row.createCell(7).setCellValue(list.get(i).getOrderNo());
            row.createCell(8).setCellValue(list.get(i).getRespMessage());
        }
       try {
           wk.write(new FileOutputStream(file));
           wk.close();
	} catch (IOException e) {
		e.printStackTrace();
		throw new ManagerException("关闭流出错,请联系管理员");
	}
       return file;
	}


}

