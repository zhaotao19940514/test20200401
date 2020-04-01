package cn.com.taiji.css.model;

import java.io.File;
import java.util.Calendar;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.qtk.entity.ServiceHall;

public class UserRequset extends BaseModel{
	
	private File file;
	
	private String fileName;
	
	private String filePath;
	
	private String name;

	private Integer male;
	
	private String mobile;
	 
	private String loginName;
	
	private String passwd;
	
	private String staffId;
	
	private String roleId;
	
	private String roleName;
	
	private String serviceHallId;
	
	//员工姓名
	private String staffName;
	//员工类型	默认为1
	private Integer staffType;
	//注册日期 yyyyMMdd
	private String releaseDate;
	//状态	默认为1
	private Integer status;
	//录入时间
	private Calendar createTime;
	
	//private String name;
	
	private ServiceHall serviceHall;
	
	private String passWord;
	
	
	
	
	
	/**
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * @param filePath 要设置的 filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @param fileName 要设置的 fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	/**
	 * @return file
	 */
	public File getFile() {
		return file;
	}


	/**
	 * @param file 要设置的 file
	 */
	public void setFile(File file) {
		this.file = file;
	}


	/**
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}


	/**
	 * @param roleName 要设置的 roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	/**
	 * @return roleId
	 */
	public String getRoleId() {
		return roleId;
	}


	/**
	 * @param roleId 要设置的 roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return male
	 */
	public Integer getMale() {
		return male;
	}


	/**
	 * @param male 要设置的 male
	 */
	public void setMale(Integer male) {
		this.male = male;
	}


	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile 要设置的 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}


	/**
	 * @param loginName 要设置的 loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	/**
	 * @return passwd
	 */
	public String getPasswd() {
		return passwd;
	}


	/**
	 * @param passwd 要设置的 passwd
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	/**
	 * @return staffId
	 */
	public String getStaffId() {
		return staffId;
	}


	/**
	 * @param staffId 要设置的 staffId
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	/**
	 * @return serviceHallId
	 */
	public String getServiceHallId() {
		return serviceHallId;
	}


	/**
	 * @param serviceHallId 要设置的 serviceHallId
	 */
	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}


	/**
	 * @return staffName
	 */
	public String getStaffName() {
		return staffName;
	}


	/**
	 * @param staffName 要设置的 staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	/**
	 * @return staffType
	 */
	public Integer getStaffType() {
		return staffType;
	}


	/**
	 * @param staffType 要设置的 staffType
	 */
	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}


	/**
	 * @return releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}


	/**
	 * @param releaseDate 要设置的 releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}


	/**
	 * @param status 要设置的 status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	/**
	 * @return createTime
	 */
	public Calendar getCreateTime() {
		return createTime;
	}


	/**
	 * @param createTime 要设置的 createTime
	 */
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}


	/**
	 * @return serviceHall
	 */
	public ServiceHall getServiceHall() {
		return serviceHall;
	}


	/**
	 * @param serviceHall 要设置的 serviceHall
	 */
	public void setServiceHall(ServiceHall serviceHall) {
		this.serviceHall = serviceHall;
	}


	/**
	 * @return passWord
	 */
	public String getPassWord() {
		return passWord;
	}


	/**
	 * @param passWord 要设置的 passWord
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	
	public UserResponse validate() {
		UserResponse response = new UserResponse();
		response.setStatus(-1);
		if(loginName==null || loginName=="") {
			response.setMessage("登陆名不能为空！");
		}else if(serviceHallId ==null || serviceHallId=="") {
			response.setMessage("网点编号不能为空！");
		}else if(staffId==null || staffId=="") {
			response.setMessage("员工编号不能为空！");
		}else if(roleName==null || roleName=="") {
			response.setMessage("角色不能为空！");
		}else if(staffName==null || staffName=="") {
			response.setMessage("员工姓名不能为空！");
		}else if(male==null) {
			response.setMessage("性别不能为空！");
		}else if(mobile==null || mobile =="") {
			response.setMessage("手机号不能为空！");
		}else if(serviceHallId.length()!= 19) {
			response.setMessage("网点编号长度必须为19位！");
		}else if(loginName.length()<3 || loginName.length()>16 ) {
			response.setMessage("登录名需在3——16之间！");
		}else if(mobile.length()!= 11) {
			response.setMessage("手机号码长度必须为11位！");
		}else {
			response.setStatus(1);
		}
		
		return response;
	}
}
