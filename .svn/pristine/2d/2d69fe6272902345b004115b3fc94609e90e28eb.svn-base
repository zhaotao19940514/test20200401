package cn.com.taiji.css.manager.acl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.pub.PoiExcelInfo;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.Role;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.User.SystemUser;
import cn.com.taiji.css.entity.User.UserStatus;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.repo.jpa.RoleRepo;
import cn.com.taiji.css.repo.jpa.UnitRepo;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.css.repo.request.acl.UserPageRequest;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.Staff;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;
import cn.com.taiji.qtk.repo.jpa.StaffRepo;

/**
 * 
 * @author Peream <br>
 *         Create Time：2010-5-31 上午10:39:21<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("userManager")
public class UserManagerImpl extends AbstractManager implements UserManager
{
	@Autowired
	private UnitRepo unitRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private StaffRepo staffRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;

	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public String add(User user, User loginUser) throws JsonManagerException
	{
		Role role = addValidate(user, loginUser);
		user.setRole(role);
		user.setPasswd(user.getPasswd());
		userRepo.save(user);
		return user.getId();
	}

	private void validateAndSetStaffId(User user){
		if(StringTools.hasText(user.getStaffId())){
			Staff staff = staffRepo.findByStaffId(user.getStaffId());
			if(staff == null){
				throw new MyViolationException("staffId", "未找到工号信息：" + user.getStaffId());
			}
			user.setStaff(staff);
		}else{
			throw new MyViolationException("staffId", "工号不能为空");
		}
	}
	private Role addValidate(User user, User loginUser) throws JsonManagerException
	{
		AssertUtil.notNull(user);
		user.validate();// 一般的多字段关联校验
		if (!hasText(user.getPasswd())) throw new MyViolationException("passwd", "不能为空");
		if (!hasText(user.getConfirm_pw())) throw new MyViolationException("confirm_pw", "不能为空");
		if (!user.getConfirm_pw().equals(user.getPasswd())) throw new MyViolationException("confirm_pw", "两次密码不一致");
		User tmp = userRepo.findByLoginName(user.getLoginName());
		if (tmp != null) throw new MyViolationException("loginName", "用户已经存在:" + user.getLoginName());
		Role role = roleRepo.findById(user.getRoleId()).orElse(null);
		if (role == null) throw new MyViolationException("roleId", "所选的角色不存在");
		Unit unit = unitRepo.findById(user.getUnit().getId()).orElse(null);
		// 校验选择的单位属于当前用户权限内的
		if (unit == null) throw new MyViolationException("unit\\.name", "所选的单位不存在");
		if (!unit.belongTo(loginUser.getUnit())) throw new JsonManagerException("单位超出范围");
		validateAndSetStaffId(user);
		return role;
	}

	@Override
	public User findById(String id)
	{
		User user = userRepo.findById(id).orElse(null);
		if (user == null) return null;
		if (user.getRole() != null) user.setRoleId(user.getRole().getId());
		return user;
	}


	@Override
	public User findByLoginName(String loginName)
	{
		return userRepo.findByLoginName(loginName);
	}

	@Override
	public Pagination queryPage(UserPageRequest req, User user)
	{
		req.setUnitLikeCode(user.getUnitLikeCode());
		return userRepo.page(req);
	}

	@Override
	public LargePagination<User> queryLargePage(UserPageRequest req, User user)
	{
		req.setUnitLikeCode(user.getUnitLikeCode());
		return userRepo.largePage(req);
	}
	
	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public User update(User user, User loginUser) throws JsonManagerException
	{
		User po = updateValidate(user, loginUser);
		BeanTools.copyProperties(user, po, new String[] { "id", "loginName", "role", "passwd", "status" });
		userRepo.save(po);
		return po;
	}


	private User updateValidate(User user, User loginUser) throws JsonManagerException
	{
		user.validate();// 一般的多字段关联校验
		User po = userRepo.findById(user.getId()).orElse(null);
		if (po == null) throw new JsonManagerException("用户不存在,id:" + user.getId());
		String roleId = user.getRoleId();
		if (!hasText(roleId)) return po;
		if (SystemUser.isSystemUser(po.getLoginName()) && !roleId.equals(po.getRole().getId()))
			throw new MyViolationException("roleId", "不能修改内置用户的角色:" + po.getLoginName());
		Role role = roleRepo.findById(roleId).orElse(null);
		if (role == null)
			logger.warn("角色不存在:{}", roleId);
		else
			po.setRole(role);
		Unit unit = unitRepo.findById(user.getUnit().getId()).orElse(null);
		if (unit == null) new MyViolationException("unit\\.name", "所选的单位不存在");
		if (!unit.belongTo(loginUser.getUnit())) throw new JsonManagerException("单位超出范围");
		user.setUnit(unit);
		validateAndSetStaffId(user);
		return po;
	}

	@Override
	@Transactional
	public User updateStatus(String id, UserStatus status) throws ManagerException
	{
		AssertUtil.notNull(status);
		User user = userRepo.findById(id).orElse(null);
		if (user == null) throw new ManagerException("用户不存在,id:" + id);
		if (SystemUser.isSystemUser(user.getLoginName()))
			throw new ManagerException("不能修改内置用户的状态:" + user.getLoginName());
		user.setStatus(status);
		return userRepo.save(user);
	}

	@Override
	@Transactional
	public User modPasswd(String passwd, String uid) throws ManagerException
	{
		Optional<User> user = userRepo.findById(uid);
		if (!user.isPresent()) throw new ManagerException("用户不存在");
		// user.setPasswd(SecurityUtil.encryptStr(passwd, HashType.SHA_512, true));
		user.get().setPasswd(passwd);
		return userRepo.save(user.get());
	}
	
	/**
	 * 获取行数据
	 * @throws IOException 
	 * 
	 */
	public List<UserRequset> getLines(File importFile) throws IOException
	{
		PoiExcelInfo info = new PoiExcelInfo();
		info.setColSize(MyFinals.TAX_INFO_COLNUMS);//总数据列数
		info.setExcelInput(new FileInputStream(importFile));
		info.setSheetIndex(0);
		info.setFromRow(2);
		info.setToRow(-1);
		info.setBreakOnRowNull(true);
		info.setXlsx(true);
		Workbook workbook = createWorkbook(info.getExcelInput(), info.isXlsx());
		List<UserRequset> rs = new ArrayList<UserRequset>();
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
			UserRequset e = row2Model(i, rowData);
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
	
	
	public UserRequset row2Model(int row, Map<Integer, Cell> rowData)
	{
		if (row == 0) return null;
		if (rowData.size() != MyFinals.TAX_INFO_COLNUMS)
		{
			logger.info(row + "行数据不完整");
			return null;
		}
		rowData.get(1).setCellType(CellType.STRING);//网点编号
		rowData.get(2).setCellType(CellType.STRING);//员工编号
		rowData.get(3).setCellType(CellType.STRING);//员工姓名
		rowData.get(4).setCellType(CellType.STRING);//员工性别
		rowData.get(5).setCellType(CellType.STRING);//登录名
		rowData.get(6).setCellType(CellType.STRING);//联系方式
		rowData.get(7).setCellType(CellType.STRING);//角色
		String serviceHallId = rowData.get(1).getStringCellValue().replace(" ", "");
		String staffId = rowData.get(2).getStringCellValue();
		String staffName = rowData.get(3).getStringCellValue();
		String male = rowData.get(4).getStringCellValue().replace(" ", "");
		String loginName = rowData.get(5).getStringCellValue().replace(" ", "");
		String mobile = rowData.get(6).getStringCellValue().replace(" ", "");
		String roleName=rowData.get(7).getStringCellValue().replace(" ", "");
		UserRequset info = new UserRequset();
		if(serviceHallId ==null || staffId==null || staffName==null || male==null || loginName==null || mobile==null) {
			return null;
		}
		if(serviceHallId =="" || staffId=="" || staffName=="" || male=="" || loginName=="" || mobile=="") {
			return null;
		}
		info.setServiceHallId(serviceHallId);
		info.setStaffId(staffId);
		info.setStaffName(staffName);
		info.setLoginName(loginName);
		info.setRoleName(roleName);
		Integer sex=0;
		if(male.equals("女")) {
		        sex=1;
		}
		info.setMale(sex);
		info.setMobile(mobile);
		return info;
	}
	
	
	// 插入数据
	private static String PASSWORD="ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413";
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public UserResponse importExcel(List<UserRequset> userRequset) throws ManagerException {
		UserResponse rsp=new UserResponse();
		for(UserRequset u: userRequset) {
			rsp=u.validate();
			if(rsp.getStatus()==1) {
				Staff st=staffRepo.findByStaffId(u.getStaffId());
				Role role=roleRepo.findByName(u.getRoleName());
				User us=userRepo.findByLoginName(u.getLoginName());
				if(serviceHallRepo.findByServiceHallId(u.getServiceHallId())==null) {
					rsp.setStatus(-1);
					rsp.setMessage("该"+u.getServiceHallId()+"渠道不存在,请检查数据！");
					break;
				}else if(st!=null) {
					rsp.setStatus(-1);
					rsp.setMessage("该"+u.getStaffId()+"工号已存在,请检查数据！");
					break;
				}else if(role==null) {
					rsp.setStatus(-1);
					rsp.setMessage("该"+u.getRoleName()+"角色不存在,请检查数据！");
					break;
				}else if(us!=null) {
					rsp.setStatus(-1);
					rsp.setMessage("该"+u.getLoginName()+"用户已存在,请检查数据！");
					break;
				}
			}
		}
		if(rsp.getStatus()==1) {
			for(UserRequset u: userRequset) {
					try {
						Role role=roleRepo.findByName(u.getRoleName());
						ServiceHall  serviceHall=serviceHallRepo.findByServiceHallId(u.getServiceHallId());
						Unit unit=role.getUnit();
						User user=new User();
						Staff staff=new Staff();
						staff.setStaffId(u.getStaffId());
						staff.setPassWord("123456");
						staff.setCreateTime(Calendar.getInstance());
						staff.setStaffType(1);
						staff.setStaffName(u.getStaffName());
						staff.setStatus(1);
						staff.setServiceHallId(u.getServiceHallId());
						staff.setServiceHall(serviceHall);
						staffRepo.save(staff);
						user.setUnit(unit);
						user.setName(staff.getStaffName());
						user.setRoleId(role.getId());
						user.setRole(role);
						user.setLoginName(u.getLoginName());
						user.setStaffId(u.getStaffId());
						user.setStaff(staff);
						boolean p=true;
						if(u.getMale()==1) {
							p=false;
						}
						user.setMale(p);
						user.setPasswd(PASSWORD);
						user.setMobile(u.getMobile());
						userRepo.save(user);
					} catch (Exception e) {
						e.printStackTrace();
						logger.debug("工号："+u.getStaffId()+"用户名："+u.getLoginName()+"此数据有异常，请处理后再导入");
						rsp.setStatus(-1);
						rsp.setMessage("工号："+u.getStaffId()+"用户名："+u.getLoginName()+"此数据有异常，请处理后再导入");
						break;
					}
			}
		}
		return rsp;
	}
	
}
