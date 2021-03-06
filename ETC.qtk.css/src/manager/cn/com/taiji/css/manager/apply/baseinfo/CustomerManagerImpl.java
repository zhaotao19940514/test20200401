package cn.com.taiji.css.manager.apply.baseinfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.EncodeTool;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.manager.util.FileWriter;
import cn.com.taiji.css.manager.util.MyPatterns;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.apply.customermanager.CustomerManagerRequest;
import cn.com.taiji.css.model.apply.customermanager.CustomerManagerResponse;
import cn.com.taiji.css.model.apply.customermanager.EgCustomerManagerRequest;
import cn.com.taiji.css.model.apply.quickapply.InfoCheckRequset;
import cn.com.taiji.css.model.apply.quickapply.InfoCheckResponse;
import cn.com.taiji.dsi.manager.comm.client.ReleaseBinService;
import cn.com.taiji.dsi.manager.comm.client.ValidationBinService;
import cn.com.taiji.dsi.model.comm.protocol.releases.UserInfoSubmitRequest;
import cn.com.taiji.dsi.model.comm.protocol.releases.UserInfoSubmitResponse;
import cn.com.taiji.dsi.model.comm.protocol.validation.PlateCheckRequest;
import cn.com.taiji.dsi.model.comm.protocol.validation.PlateCheckResponse;
import cn.com.taiji.dsi.model.util.QTKUtils;
import cn.com.taiji.qtk.entity.Admin;
import cn.com.taiji.qtk.entity.CustomerIdNumPng;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.repo.jpa.AdminRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerIdNumPngRepo;
import cn.com.taiji.qtk.repo.jpa.CustomerInfoRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;
@Service
public class CustomerManagerImpl extends AbstractDsiCommManager implements CustomerManager{
	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private CustomerIdNumPngRepo customerIdNumPngRepo;
	@Autowired
	private ReleaseBinService releaseBinService;
	@Autowired
	private VehicleInfoRepo vehicleInfoRepo;
	@Autowired
	private ValidationBinService validationBinService;
	
	@Override
	public LargePagination<CustomerInfo> queryPage(CustomerManagerRequest req) {
		if(StringTools.hasText(req.getCustomerId()) || req.getCustomerIdType() != null || StringTools.hasText(req.getCustomerIdNum()) 
				|| StringTools.hasText(req.getAgentName()) || StringTools.hasText(req.getCustomerName()) 
				) {
			return customerInfoRepo.largePage(req);
		}else {
			return null;
		}
	}

	@Override
	public Pagination queryPage(EgCustomerManagerRequest req) {
		Pagination page = customerInfoRepo.page(req);
		return page;
	}
	
	@Override
	public CustomerInfo findByCustomerId(String customerId) throws ManagerException {
		CustomerInfo customerInfo;
		try {
			customerInfo = customerInfoRepo.findByCustomerId(customerId);
		} catch (IncorrectResultSizeDataAccessException e) {
			e.printStackTrace();
			throw new ManagerException("该证件存在多个用户账户，请确认单位用户已添加部门参数，个人用户请联系黔通智联处理！");
		}
		return customerInfo;
	}
	
	@Override
	@Transactional
	public String add(QuickApplyPngModel model, User user) throws ManagerException {
		validate(model);//数据校验
		String customerCheck = customerCheck(model.getCustomerType(), model.getCustomerIdType(), model.getCustomerIdNum(), model.getDepartment());
		if(customerCheck != null) throw new ManagerException("该客户已开过户！");
		try {
			savePic(model);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ManagerException("保存图片失败！请联系管理员");
		}
		UserInfoSubmitRequest req = new UserInfoSubmitRequest();
		super.commSet(req,user);
		req.setOperation(1);
		req.setUserType(model.getCustomerType());
		req.setUserIdType(model.getCustomerIdType());
		req.setUserIdNum(model.getCustomerIdNum());
		req.setUserName(model.getCustomerName());
		req.setTel(model.getTel());
		req.setAddress(model.getAddress());
		req.setEmergencyFlag(model.getEmergencyFlag());
		if(model.getCustomerType() == 2) {
			req.setDepartment(model.getDepartment());
			req.setAgentName(model.getAgentName());
			req.setAgentIdType(model.getAgentIdType());
			req.setAgentIdNum(model.getAgentIdNum());
		}
		UserInfoSubmitResponse res;
		try {
			res = releaseBinService.userInfoSubmitV2(req);
			if(res.getUserId() == null) {
				throw new ManagerException(res.getMessage());
			}
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("客户信息保存失败",e);
		}
		return res.getUserId();
	}
	@Transactional
	private void savePic(QuickApplyPngModel model) throws ManagerException {
		if(model.getFile()==null){
			return;
//			throw new ManagerException("未获取到上传的文件");
			}
		if (model.getFile()!=null && model.getFile().length > 0)
		for (int i = 0; i < model.getFile().length; i++) {
			MultipartFile file = model.getFile()[i];
			CustomerIdNumPng custIdNumPng = new CustomerIdNumPng();
			String parentDirRelativePath = MyFinals.QUICK_APPLY_CUSTOMER_IMG+File.separator+model.getCustomerIdType()+File.separator+model.getCustomerIdNum();
			String fileAbsolutePath = FileWriter.savePic(file,custIdNumPng, parentDirRelativePath);
			custIdNumPng.setCustomerIdNum(model.getCustomerIdNum());
			custIdNumPng.setCustomerIdType(model.getCustomerIdType());
			custIdNumPng.setFilePath(fileAbsolutePath);
			custIdNumPng.setFileName(FileWriter.generateFileName(custIdNumPng,file));
			custIdNumPng.setInsertTime(QTKUtils.getDateString());
			customerIdNumPngRepo.save(custIdNumPng);
		}
	}
	
	@Override
	@Transactional
	public String update(QuickApplyPngModel model, User user) throws ManagerException {
		validate(model);//数据校验
		try {
			savePic(model);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new ManagerException("保存图片失败！请联系管理员");
		}
		UserInfoSubmitRequest req = new UserInfoSubmitRequest();
		super.commSet(req,user);
		req.setOperation(2);
		req.setUserType(model.getCustomerType());
		req.setUserIdType(model.getCustomerIdType());
		req.setUserIdNum(model.getCustomerIdNum());
		req.setUserName(model.getCustomerName());
		req.setTel(model.getTel());
		req.setAddress(model.getAddress());
		if(model.getCustomerType() == 2) {
			req.setDepartment(model.getDepartment());
			req.setAgentName(model.getAgentName());
			req.setAgentIdType(model.getAgentIdType());
			req.setAgentIdNum(model.getAgentIdNum());
		}
		UserInfoSubmitResponse res;
		try {
			res = releaseBinService.userInfoSubmitV2(req);
			if(res.getUserId() == null) {
				throw new ManagerException(res.getMessage());
			}
		} catch (ApiRequestException  | IOException e) {
			e.printStackTrace();
			throw new ManagerException("客户信息更新失败",e);
		}
		return res.getUserId();
	}
	
	@Override
	@Transactional
	public CustomerManagerResponse updatePassWord(CustomerManagerRequest request, User user) throws ManagerException {
		CustomerManagerResponse response=new CustomerManagerResponse();
		response.setStatus(-1);
		CustomerInfo customerInfo = customerInfoRepo.findByCustomerId(request.getCustomerId());
		String passWord=request.getPassword();
		String newPassWord=request.getNewPassWord();
		//如果库内密码为空，进行初始化密码
		if(customerInfo.getPassword()!=null) {
			if(passWord==null || newPassWord==null) {
				response.setMessage("旧密码与新密码都不得为空！");
				return response;
			}
			//如果用户输入的旧密码与库内不符，拒绝修改
			if(!customerInfo.getPassword().equals(passWord)) {
				response.setMessage("旧密码输入错误,无权修改密码！");
				return response;
			}
		}
			customerInfo.setPassword(newPassWord);
			customerInfoRepo.save(customerInfo);
			response.setMessage("用户账密码 初始化/修改 成功!");
			response.setStatus(1);
		return response;
	}
	
	@Override
	@Transactional
	public CustomerManagerResponse initializationPassWord(CustomerManagerRequest request, User user) throws ManagerException {
			CustomerManagerResponse response=new CustomerManagerResponse();
			Admin admin=adminRepo.findByUserId(user.getId());
			if(admin!=null) {// 管理员
				CustomerInfo customerInfo = customerInfoRepo.findByCustomerId(request.getCustomerId());
				customerInfo.setPassword("123");
				customerInfoRepo.save(customerInfo);
				response.setMessage("初始化用户账密码成功!");
				response.setStatus(1);
			}else {
				response.setMessage("当前登录工号无权操作初始化用户账密码!");
				response.setStatus(-1);
			}
			return response;
	}
	
	
	/**
	 * 客户信息校验
	 * @param model
	 */
	private void validate(QuickApplyPngModel model) 
	{
		MyViolationException mve = new MyViolationException();		
		if(model.getCustomerType() == null) mve.addViolation("customerType", "客户类型不能为空！");
		if(model.getCustomerIdType() == null) {
			mve.addViolation("customerIdType", "证件类型不能为空！");
		}else {
			if(CustomerIDType.valueOfCode(model.getCustomerIdType()) == null) {
				mve.addViolation("customerIdType", "该证件类型不存在！");
			}else {
				if(model.getCustomerIdNum() != null) {
					switch (model.getCustomerIdType()) {
					case 101:
						if(!MyPatterns.checkIdCode(model.getCustomerIdNum())) {
							mve.addViolation("customerIdNum", "身份证格式不正确！");
						}
						break;
					case 103:
						if(!MyPatterns.checkGaCnIdCodeFormat(model.getCustomerIdNum())) {
							mve.addViolation("customerIdNum", "港澳通行证证格式不正确！");
						}
						break;
					case 104:
						if(!MyPatterns.checkTwCnIdCodeFormat(model.getCustomerIdNum())) {
							mve.addViolation("customerIdNum", "台湾通行证格式不正确！");
						}
						break;
					case 105:
						if(!MyPatterns.checkSgzCnIdCodeFormat(model.getCustomerIdNum())) {
							mve.addViolation("customerIdNum", "军官证格式不正确！");
						}
						break;
					case 201:
						if(!MyPatterns.checkCreditCodeFormat(model.getCustomerIdNum())) {
							mve.addViolation("customerIdNum", "社会信用代码格式不正确！");
						}
						break;
					default:
						break;
					}
				}
			}
		}
		if(model.getCustomerIdNum() == null) mve.addViolation("customerIdNum", "证件号不能为空！");
		if(model.getCustomerName() == null) {
			mve.addViolation("customerName", "客户名称不能为空！");
		}else {
			if(model.getCustomerName().length() > 50) mve.addViolation("customerName", "客户名称不能大于50个字符！");
		}
		if(model.getTel() == null) {
			mve.addViolation("tel", "联系方式不能为空！");
		}else {
			if(!MyPatterns.checkMobileFormat(model.getTel()))
				mve.addViolation("tel", "手机号格式不正确！");
		} 
		if(model.getAddress() == null) {
			mve.addViolation("address", "地址不能为空！");
		}else{
			if(model.getAddress().length() > 100) mve.addViolation("address", "地址不能大于100字符！");
		}
		if(model.getCustomerType() != null) {
			if(model.getCustomerType() == 2) {
				if(model.getDepartment() == null) {
					mve.addViolation("department", "分支机构名称不能为空！");
				}else {
					if(model.getDepartment().length() > 50) mve.addViolation("department", "分支机构名称不能大于50个字符！");
				}
				if(model.getAgentName() == null) {
					mve.addViolation("agentName", "经办人姓名不能为空！");
				}else {
					if(model.getAgentName().length() > 50) mve.addViolation("agentName", "经办人姓名不能大于50个字符！");
				}
				if(model.getAgentIdType() == null) {
					mve.addViolation("agentIdType", "经办人证件类型不能为空！");
				}else {
					if(model.getAgentIdNum() != null) {
						switch (model.getAgentIdType()) {
						case 101:
							if(!MyPatterns.checkIdCode(model.getAgentIdNum())) {
								mve.addViolation("agentIdNum", "身份证格式不正确！");
							}
							break;
						case 103:
							if(!MyPatterns.checkGaCnIdCodeFormat(model.getAgentIdNum())) {
								mve.addViolation("agentIdNum", "港澳通行证证格式不正确！");
							}
							break;
						case 104:
							if(!MyPatterns.checkTwCnIdCodeFormat(model.getAgentIdNum())) {
								mve.addViolation("agentIdNum", "台湾通行证格式不正确！");
							}
							break;
						case 105:
							if(!MyPatterns.checkSgzCnIdCodeFormat(model.getAgentIdNum())) {
								mve.addViolation("agentIdNum", "军官证格式不正确！");
							}
							break;
						case 201:
							if(!MyPatterns.checkCreditCodeFormat(model.getAgentIdNum())) {
								mve.addViolation("agentIdNum", "社会信用代码格式不正确！");
							}
							break;
						default:
							break;
						}
					}
				}
				if(model.getAgentIdNum() == null) mve.addViolation("agentIdNum", "经办人证件号不能为空！");
			}
		}
		
		if(model.getFile() != null && model.getFile().length > 0) {
			List<String> list = new ArrayList<>();
			list.add("image/png");
			list.add("image/jpg");
			list.add("image/jpeg");
			Boolean flag = CssUtil.validateFileType(model.getFile(), list);
			if(!flag) {
				 mve.addViolation("file", "只能上传png、jpg、jpeg格式的照片！");
			}
		}
		
		if (mve.hasViolation()) throw mve;
	}

	@Override
	public List<String> getScreenShotBase64(CustomerInfo customerInfo) throws ManagerException {
		
		List<CustomerIdNumPng> list = customerIdNumPngRepo.findByOwnerInfoAndFileType(customerInfo.getCustomerIdNum(), customerInfo.getCustomerIdType());
		List<String> listq = Lists.newArrayList();
		if(list.size()==0){return null;}
		for(int i = 0;i<list.size();i++) {
			CustomerIdNumPng customerIdNumPng = list.get(i);
			String fileName = customerIdNumPng.getFileName();
			String filePath = customerIdNumPng.getFilePath();
			if(fileName == null || filePath == null){continue;}
			File file = new File(filePath + fileName);
			
			if(!file.exists()){continue;}
			String encodeBase64="";
			try {
				encodeBase64 = EncodeTool.encodeBase64(file);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("图片转码错误！");
			}
			String suffix = fileName.substring(fileName.lastIndexOf('.'));
			if(".png".equalsIgnoreCase(suffix)){encodeBase64 = "data:image/png;base64,"+encodeBase64;}
			else if(".jpg".equalsIgnoreCase(suffix)){encodeBase64 ="data:image/jpg;base64,"+encodeBase64;}
			else if(".jpeg".equalsIgnoreCase(suffix)){encodeBase64 ="data:image/jpeg;base64,"+encodeBase64;}
			listq.add(encodeBase64);
		}
		return listq;
}

	@Override
	public InfoCheckResponse quickApplyCheck(InfoCheckRequset req,User user) {
		InfoCheckResponse response = new InfoCheckResponse();
		if(req.getCheckType() == null) {
			response.setManagerException(new ManagerException("校验类型为空！"));
			return response;
		}
		if(req.getCheckType() == 1) {
			try {
				String checkResult = customerCheck(req.getCustomerType(), req.getCustomerIdType(), req.getCustomerIdNum(), req.getDepartment());
				if(checkResult == null) {
					response.setSuccess(true);
					response.setMessage("该客户本地不存在!");
				}else {
					response.setSuccess(false);
					response.setMessage("该客户本地已存在!");
					response.setCustomerId(checkResult);
				}
			} catch (ManagerException e) {
				e.printStackTrace();
				response.setManagerException(e);
				return response;
			}
		}else if(req.getCheckType() == 2) {
			String checkResult = null;
			try {
				checkResult = vehicleCheck(req.getVehiclePlate(), req.getVehiclePlateColor());
				if(checkResult == null) {
					response.setSuccess(true);
					PlateCheckResponse plateCheckResponse = uniqueVehiclePlateCheck(req, user);
					if(plateCheckResponse.getInfo() == null ) {
						response.setMessage("该车辆本地不存在!--唯一性校验："+plateCheckResponse.getMessage());
					}else {
						response.setMessage("该车辆本地不存在!--唯一性校验："+plateCheckResponse.getInfo());
					}
				}else {
					response.setSuccess(false);
					PlateCheckResponse plateCheckResponse = uniqueVehiclePlateCheck(req, user);
					if(plateCheckResponse.getInfo() == null ) {
						response.setMessage("该车辆本地已存在!--唯一性校验："+plateCheckResponse.getMessage());
					}else {
						response.setMessage("该车辆本地已存在!--唯一性校验："+plateCheckResponse.getInfo());
					}
					response.setCustomerId(checkResult);
				}
			} catch (ManagerException e) {
				e.printStackTrace();
				response.setManagerException(e);
				response.setCustomerId(checkResult);
				return response;
			}
		}else {
			response.setManagerException(new ManagerException("非法的校验类型"));
			return response;
		}
		return response;
	}
	
	/**
	 * 用户信息是否存在校验
	 * @param customerType
	 * @param customerIdType
	 * @param customerIdNum
	 * @param department
	 * @return 用户信息已存在返回用户编号,不存在返回null
	 * @throws ManagerException 
	 */
	private String customerCheck(Integer customerType, Integer customerIdType, String customerIdNum, String department) throws ManagerException {
		if(customerType == null) throw new ManagerException("用户类型不能为空！");
		if(customerIdType == null) throw new ManagerException("用户证件类型不能为空！");
		if(customerIdNum == null) throw new ManagerException("用户证件号不能为空！");
		if(customerType == 2) {
			if (department == null) throw new ManagerException("单位用户部门不能为空！");
		}
		if(customerType == 1) {
			CustomerInfo info;
			try {
				info = customerInfoRepo.findByBrief(customerIdType, customerIdNum);
			} catch (IncorrectResultSizeDataAccessException e) {
				e.printStackTrace();
				throw new ManagerException("该证件存在多个用户账户，请确认单位用户已添加部门参数，个人用户请联系黔通智联处理！");
			}
			if(info == null) {
				return null;
			}else {
				return info.getCustomerId();
			}
		}else {
			CustomerInfo info;
			try {
				info = customerInfoRepo.findByBrief(customerIdType, customerIdNum, department);
			} catch (IncorrectResultSizeDataAccessException e) {
				e.printStackTrace();
				throw new ManagerException("该证件存在多个用户账户，请确认单位用户已添加部门参数，个人用户请联系黔通智联处理！");
			}
			if(info == null) {
				return null;
			}else {
				return info.getCustomerId();
			}
		}
	}

	/**
	 * 车辆信息是否存在与可发行校验
	 * @param vehiclePlate
	 * @param vehiclePlateColor
	 * @return 车辆已存在返回对应的用户编号  不存在返回null
	 * @throws ManagerException 
	 */
	private String vehicleCheck(String vehiclePlate, Integer vehiclePlateColor) throws ManagerException {
		if(vehiclePlate == null) throw new ManagerException("车牌号不能为空！");
		if(vehiclePlateColor == null) throw new ManagerException("车牌颜色不能为空！");
		VehicleInfo info = vehicleInfoRepo.findByVehicleId(vehiclePlate+"_"+vehiclePlateColor);
		if(info == null) {
			return null;
		}else {
			return info.getCustomerId();
		}
	}
	
	private PlateCheckResponse uniqueVehiclePlateCheck(InfoCheckRequset icReq,User user) throws ManagerException {
		PlateCheckRequest req = new PlateCheckRequest();
		super.commSet(req, user);
		req.setVehiclePlate(icReq.getVehiclePlate());
		req.setVehicleColor(icReq.getVehiclePlateColor());
		req.setVehicleType(icReq.getType());
		req.setReleaseType(0);
		PlateCheckResponse response;
		try {
			response = validationBinService.plateCheck(req);
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException(e.getMessage()+"联网中心车牌校验出错！");
		}
		return response;
	}

}
