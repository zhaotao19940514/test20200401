package cn.com.taiji.css.manager.serviceHall;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.pub.PoiExcelInfo;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.ServiceHallRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallRequest;
import cn.com.taiji.css.model.serviceHall.ServiceHallListRequest;
import cn.com.taiji.css.model.serviceHall.ServiceHallModel;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;
import cn.com.taiji.qtk.entity.Staff;
import cn.com.taiji.qtk.entity.dict.CssCardDeviceType;
import cn.com.taiji.qtk.entity.dict.CssObuDeviceType;
import cn.com.taiji.qtk.entity.dict.CssPosDeviceType;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallDeviceConfigRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StaffRepo;

@Service("serviceHallManager")
public class ServiceHallManagerImpl extends AbstractManager implements ServiceHallManager {
	@Autowired
	private AgencyRepo agencyRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private ServiceHallDeviceConfigRepo serviceHallDeviceConfigRepo;
	@Autowired
	private StaffRepo staffRepo;
	@Override
	public ServiceHall findById1(String id) {
		return setHasChild(serviceHallRepo.findById(id).orElse(null));
	}

	private ServiceHallModel setHasChild(ServiceHall serviceHall) {
		ServiceHallModel model = new ServiceHallModel();
		BeanTools.copyProperties(serviceHall, model);
		model.setHasChild(serviceHallRepo.listByAgentId(serviceHall.getId()).size() > 0);
		return model;
	}

	@Override
	public List<ServiceHallModel> listByAgentId(String agencyId) {
		return serviceHallRepo.listByAgentId(agencyId).stream().map(this::setHasChild).collect(Collectors.toList());
	}

	@Override
	public List<ServiceHall> listByName(String name, User loginUser) {
		// AssertUtil.notNull(name);
		ServiceHallListRequest req = new ServiceHallListRequest(name, loginUser.getUnitLikeCode());
		return this.serviceHallRepo.list(req);
	}

	@Override
	public Pagination page(ServiceHallRequest req) {
		return serviceHallRepo.page(req);
	}

	@Transactional(rollbackFor=ManagerException.class)
	@Override
	public ServiceHall add(ServiceHall serviceHall) throws ManagerException {
		valid(serviceHall);
		dbValid(serviceHall);
		Agency agency = validAgencyId(serviceHall);
		ServiceHall entity = toEntity(serviceHall,agency);
		// 网点设备配置
		serviceHallRepo.save(entity);
		saveDeviceConfig(entity,null);
		return entity;
	}

	private void saveDeviceConfig(ServiceHall entity,String originServiceHallId) {
		if(entity.getServiceHallId().equals(originServiceHallId)) return ;
		ServiceHallDeviceConfig config = null;
		if(StringTools.hasText(originServiceHallId)){
			config = serviceHallDeviceConfigRepo.findByServiceHallId(originServiceHallId);
		}
		if(config == null){
			config = new ServiceHallDeviceConfig();
			config.setCardDeviceType(CssCardDeviceType.getDefalutType());
			config.setObuDeviceType(CssObuDeviceType.getDefalutType());
			config.setPosDeviceType(CssPosDeviceType.getDefalutType());
			config.setCreateTime(CssUtil.getNowDateTimeStrWithT());
		}
		config.setServiceHallId(entity.getServiceHallId());
		config.setUpdateTime(CssUtil.getNowDateTimeStrWithT());
		config.setServiceHall(entity);
		serviceHallDeviceConfigRepo.save(config);
		
		entity.setConfig(config);
		serviceHallRepo.save(entity);
	}

	private Agency validAgencyId(ServiceHall serviceHall) throws ManagerException {
		Agency agency = agencyRepo.findByAgencyId(serviceHall.getAgencyId());
		if(agency==null){
			throw new ManagerException("未找到机构编号："+serviceHall.getAgencyId());
		}
		return agency;
	}

	private ServiceHall toEntity(ServiceHall serviceHall, Agency agency) {
		ServiceHall entity = new ServiceHall();
		BeanTools.copyProperties(serviceHall, entity, new String[] { "id" });
		entity.setAgencyName(agency.getName());
		entity.setAgencyCode(agency.getFileDir());
		return entity;
	}

	private void dbValid(ServiceHall serviceHall) throws ManagerException {
		ServiceHall dbServiceHall = serviceHallRepo.findByServiceHallId(serviceHall.getServiceHallId());
		if (dbServiceHall != null)
			throw new ManagerException("已存在服务网点信息，编号：" + serviceHall.getServiceHallId());
	}

	private void valid(ServiceHall serviceHall) {
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(serviceHall.getAgencyId()))
			mve.addViolation("agencyId", "合作机构不能为空");
		if (!StringTools.hasText(serviceHall.getServiceHallId())) {
			mve.addViolation("serviceHallId", "服务网点编号不能为空");
		} else if (serviceHall.getServiceHallId().length() != 19) {
			mve.addViolation("serviceHallId", "服务网点编号长度错误，应为19位");
		}
		if (StringTools.hasText(serviceHall.getAgencyId()) && StringTools.hasText(serviceHall.getServiceHallId())
				&& serviceHall.getServiceHallId().length() == 19) {
			String shId = serviceHall.getServiceHallId().substring(0, 11);
			if (!shId.equals(serviceHall.getAgencyId())) {
				mve.addViolation("serviceHallId", "服务网点编号错误，前11位应与所选机构agencyId相同");
			}
		}
		if (!StringTools.hasText(serviceHall.getName()))
			mve.addViolation("name", "合作机构名称不能为空");
		if (!StringTools.hasText(serviceHall.getContact()))
			mve.addViolation("contact", "服务网点联系人不能为空");
		if (!StringTools.hasText(serviceHall.getTel()))
			mve.addViolation("tel", "服务网点联系人电话不能为空");
		if (!StringTools.hasText(serviceHall.getBusinessHours()))
			mve.addViolation("businessHours", "营业时间不能为空");
		if (!StringTools.hasText(serviceHall.getAddress()))
			mve.addViolation("address", "地址不能为空");
		if (mve.hasViolation())
			throw mve;
	}

	@Transactional(rollbackFor=ManagerException.class)
	@Override
	public ServiceHall edit(ServiceHall serviceHall) throws ManagerException {
		if (!StringTools.hasText(serviceHall.getId()))
			throw new ManagerException("id不能为空");
		valid(serviceHall);
		Agency agency = validAgencyId(serviceHall);
		ServiceHall dbServiceHall = validServiceHallId(serviceHall);
		serviceHall.setAgencyCode(agency.getFileDir());
		serviceHall.setAgencyName(agency.getName());
		// 网点设备配置
		serviceHallRepo.save(serviceHall);
		saveDeviceConfig(serviceHall,dbServiceHall.getServiceHallId());
		return serviceHall;
	}

	private ServiceHall validServiceHallId(ServiceHall serviceHall) throws ManagerException {
		ServiceHall findById = serviceHallRepo.findById(serviceHall.getId()).orElse(null);
		if(!findById.getServiceHallId().equals(serviceHall.getServiceHallId())){
			ServiceHall findByServiceHallId = serviceHallRepo.findByServiceHallId(serviceHall.getServiceHallId());
			if(findByServiceHallId!=null){
				throw new ManagerException("已有此网点编号:"+serviceHall.getServiceHallId()+" ,无法修改");
			}
		}
		return findById;
	}

	@Override
	public ServiceHall findById(String id) {
		return serviceHallRepo.findById(id).orElse(null);
	}

	@Transactional(rollbackFor=ManagerException.class)
	@Override
	public void delete(ServiceHall serviceHall) throws ManagerException {
		AssertUtil.notNull(serviceHall);
		List<Staff> staffs = staffRepo.listByServiceHallId(serviceHall.getServiceHallId());
		if(staffs!=null && staffs.size()>0 )
			throw new ManagerException("该网点下有工号，无法删除，请先删除该网点下所有工号！");
		// 删除网点设备配置
		deleteDeviceConfig(serviceHall);
		serviceHallRepo.delete(serviceHall);
	}

	private void deleteDeviceConfig(ServiceHall serviceHall) {
		ServiceHallDeviceConfig deviceConfig = serviceHallDeviceConfigRepo.findByServiceHallId(serviceHall.getServiceHallId());
		if(deviceConfig!=null){
			serviceHallDeviceConfigRepo.delete(deviceConfig);
		}
	}

	@Override
	public List<ServiceHall> queryByName(String name) {
		List<ServiceHall> list = serviceHallRepo.listByName(name);
		return list;
	}
	
	/**
	 * 获取行数据
	 * @throws IOException 
	 * 
	 */
	public List<ServiceHallRequset> getLines(File importFile) throws IOException
	{
		PoiExcelInfo info = new PoiExcelInfo();
		info.setColSize(MyFinals.TAX_INFO_SERVICE);//总数据列数
		info.setExcelInput(new FileInputStream(importFile));
		info.setSheetIndex(0);
		info.setFromRow(2);
		info.setToRow(-1);
		info.setBreakOnRowNull(true);
		info.setXlsx(true);
		Workbook workbook = createWorkbook(info.getExcelInput(), info.isXlsx());
		List<ServiceHallRequset> rs = new ArrayList<ServiceHallRequset>();
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
			ServiceHallRequset e = row2Model(i, rowData);
			if (e != null) rs.add(e);
		}
		return rs;
	}
	
	
	public  ExpenseRefundApplicationResponse saveFile(MultipartFile file) throws ManagerException {
		ExpenseRefundApplicationResponse response =new ExpenseRefundApplicationResponse();
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
	
	
	public ServiceHallRequset row2Model(int row, Map<Integer, Cell> rowData)
	{
		if (row == 0) return null;
		if (rowData.size() != MyFinals.TAX_INFO_SERVICE)
		{
			logger.info(row + "行数据不完整");
			return null;
		}
		rowData.get(1).setCellType(CellType.STRING);//网点名称
		rowData.get(2).setCellType(CellType.STRING);//网点编号
		rowData.get(3).setCellType(CellType.STRING);//网点联系人
		rowData.get(4).setCellType(CellType.STRING);//网点联系电话
		rowData.get(5).setCellType(CellType.STRING);//营业时间
		rowData.get(6).setCellType(CellType.STRING);//地址
		String name = rowData.get(1).getStringCellValue().replace(" ", "");
		String serviceHallId = rowData.get(2).getStringCellValue();
		String contact = rowData.get(3).getStringCellValue();
		String tel = rowData.get(4).getStringCellValue().replace(" ", "");
		String businessHours = rowData.get(5).getStringCellValue().replace(" ", "");
		String address = rowData.get(6).getStringCellValue().replace(" ", "");
		ServiceHallRequset info = new ServiceHallRequset();
		if(serviceHallId ==null || name==null || contact==null || tel==null || businessHours==null || address==null) {
			return null;
		}
		if(serviceHallId =="" || name=="" || contact=="" || tel=="" || businessHours=="" || address=="") {
			return null;
		}
		info.setServiceHallId(serviceHallId);
		info.setName(name);
		info.setContact(contact);
		info.setTel(tel);
		info.setBusinessHours(businessHours);
		info.setAddress(address);
		return info;
	}
	
	
	// 插入数据
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public UserResponse importExcel(List<ServiceHallRequset> serviceHallRequset) throws ManagerException {
		UserResponse rsp=new UserResponse();
		for(ServiceHallRequset u: serviceHallRequset) {
			rsp=u.validate();
			if(rsp.getStatus()==1) {
				ServiceHall serviceHall=serviceHallRepo.findByServiceHallId(u.getServiceHallId());
				ServiceHall sh=serviceHallRepo.findByName(u.getName());
				if(agencyRepo.findByAgencyId(u.getServiceHallId().substring(0, 11))==null) {
					rsp.setStatus(-1);
					rsp.setMessage("该"+u.getServiceHallId().substring(0, 11)+"合作机构不存在,请检查数据！");
					break;
				}else if(serviceHall!=null) {
					rsp.setStatus(-1);
					rsp.setMessage("该"+u.getServiceHallId()+"网点编号已存在,请检查数据！");
					break;
				}else if(sh!=null) {
					rsp.setStatus(-1);
					rsp.setMessage("该"+u.getName()+"网点名称已存在,请检查数据！");
					break;
				}
			}
		}
		if(rsp.getStatus()==1) {
			for(ServiceHallRequset u: serviceHallRequset) {
					ServiceHall  serviceHall=new ServiceHall();
					Agency agency =agencyRepo.findByAgencyId(u.getServiceHallId().substring(0, 11));
					serviceHall.setAgencyId(agency.getAgencyId());
					serviceHall.setAgencyName(agency.getName());
					serviceHall.setAgencyCode(agency.getFileDir());
					serviceHall.setName(u.getName());
					serviceHall.setServiceHallId(u.getServiceHallId());
					serviceHall.setTel(u.getTel());
					serviceHall.setContact(u.getContact());
					serviceHall.setBusinessHours(u.getBusinessHours());
					serviceHall.setAddress(u.getAddress());
					serviceHall.setStartTime(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					serviceHall.setEndTime(LocalDate.now().plusYears(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					serviceHallRepo.save(serviceHall);
			}
		}
		return rsp;
	}
	

}

