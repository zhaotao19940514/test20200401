package cn.com.taiji.css.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.common.pub.GBStringTools;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.pub.validation.PatternFactory;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.Staff;

/**
 * @author Peream <br>
 *         Create Time：2010-1-29 上午09:46:51<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "QTK_CSS_USER")
public class User extends StringUUIDEntity
{
	public static final String DEFAULT_PWD = "123456";
	@NotNull
	@Size(max = 50, message = "{userName.error}")
	private String name;
	private String namePy;
	@NotNull
	private Boolean male;
	@Email
	private String email;
	@NotNull
	@Pattern(regexp = PatternFactory.mobileRegexp, message = "{mobile.error}")
	private String mobile;
	private String tel;
	private String fax;
	@NotNull
	@Size(max = 20, message = "登录名长度不能超过20")
	private String loginName;
	private String passwd;
	private UserStatus status = UserStatus.NORMAL;
	private Role role;
	private String roleId;
	private String oldPasswd;// 老密码
	private String confirm_pw;
	private Unit unit;
	private Staff staff;// 工号实体
	@NotNull
	private String staffId;// 工号字符串
	@ManyToOne
	@JoinColumn(name = "STAFF_FK")
	public Staff getStaff() {
		return staff;
	}
	@Column(name = "STAFF_ID")
	public String getStaffId() {
		return staffId;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public static String getDefaultPwd() {
		return DEFAULT_PWD;
	}
	@Transient
	public String getConfirm_pw()
	{
		return confirm_pw;
	}

	public void setConfirm_pw(String confirm_pw)
	{
		this.confirm_pw = confirm_pw;
	}

	@Transient
	public String getOldPasswd()
	{
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd)
	{
		this.oldPasswd = oldPasswd;
	}

	@Transient
	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	
	@ManyToOne
	@JoinColumn(name = "unit_id")
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	@Column(nullable = false, length = 100)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "name_py", nullable = false, length = 100)
	public String getNamePy()
	{
		if (StringTools.hasText(name)) namePy = GBStringTools.str2PY(name, '?', false);
		return namePy;
	}

	public void setNamePy(String namePy)
	{
		this.namePy = namePy;
	}

	@Column(nullable = false)
	public Boolean getMale()
	{
		return male;
	}

	public void setMale(Boolean male)
	{
		this.male = male;
	}

	@Column(length = 100)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(length = 50)
	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	@Column(length = 50)
	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	@Column(length = 50)
	public String getFax()
	{
		return fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	@Column(name = "login_name", unique = true, length = 100, nullable = false)
	public String getLoginName()
	{
		if (StringTools.hasText(loginName)) loginName = loginName.toLowerCase();
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	@Column(length = 50, nullable = false)
	public String getPasswd()
	{
		return passwd;
	}

	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	public UserStatus getStatus()
	{
		return status;
	}

	public void setStatus(UserStatus status)
	{
		this.status = status;
	}

	public enum UserStatus
	{
		NORMAL("正常") {},
		INVALID("禁用") {};

		private String value;

		private UserStatus(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}

	}

	public enum SystemUser
	{
		ADMIN("admin");
		private String value;

		private SystemUser(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}

		public static boolean isSystemUser(String loginName)
		{
			SystemUser[] users = SystemUser.values();
			for (SystemUser user : users)
				if (user.getValue().equals(loginName)) return true;
			return false;
		}
	}

	/**
	 * 示例展现如何多属性联合校验
	 * 
	 */
	public void validate()
	{
		MyViolationException mve = new MyViolationException();
		if (!"ccc".equals(loginName) && "chenpa@mail.taiji.com.cn".equals(email))
			mve.addViolation("email", "这是ccc的邮箱哦亲.");
		if (!(loginName!=null && loginName.length()>=3 && loginName.length()<=16))
			mve.addViolation("loginName", "登录名需在3——16位之间哦亲.");
		if (mve.hasViolation()) throw mve;
	}
	
	/**
	 * 获取用户查询单位范围的code
	 * @return
	 */
	@Transient
	public String getUnitLikeCode(){
		if(SystemUser.isSystemUser(loginName)){
			return null;
		}
		if(unit==null){
			return "---";
		}
		return unit.getCode();
	}
}
