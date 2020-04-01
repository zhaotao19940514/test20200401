package cn.com.taiji.css.web;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.acl.UserModel;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.common.web.util.WebTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.User.UserStatus;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.UserManager;
import cn.com.taiji.css.model.MySessionNames;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author Peream<br>
 *         邮箱：peream@gmail.com<br>
 *         创建日期：2008-3-28 下午01:59:51
 * @since 1.0
 * @version 1.0
 */
public abstract class LoginValidator extends AbstractManager
{
	final private static int try_count=3,expire=5;
	private static Cache<String, Integer> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(expire, TimeUnit.MINUTES)
			.build();
	public static User validate(UserModel userModel,HttpServletRequest request, UserManager manager) throws ManagerException{
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(userModel.getUsername()))
			mve.addViolation("username", "用户名必填");
		if(!StringTools.hasText(userModel.getPassword()))
			mve.addViolation("password","密码必填");
		if(!StringTools.hasText(userModel.getValidCode()))
			mve.addViolation("validCode","验证码必填");
		if(mve.hasViolation())throw mve;
		String sessionValidCode=(String) WebTools.getSessionAttribute(request, MySessionNames.VALID_CODE);
		if(!userModel.getValidCode().equalsIgnoreCase(sessionValidCode)){
			request.getSession().removeAttribute(MySessionNames.VALID_CODE);
			throw new MyViolationException("validCode","验证码错误");
		}
		checkFailedCount(userModel.getUsername());
		User loginUser = manager.findByLoginName(userModel.getUsername());
		if(loginUser==null){
			addFailedCount(userModel.getUsername());
			throw new MyViolationException("username","用户不存在");
		}
		if (loginUser.getStatus() == UserStatus.INVALID)
		{
			addFailedCount(userModel.getUsername());
			throw new MyViolationException("username","用户已停用");
		}
		if (!LoginHelper.isPassValid(userModel.getPassword(), loginUser))
		{
			addFailedCount(userModel.getUsername());
			request.getSession().removeAttribute(MySessionNames.VALID_CODE);
			throw new MyViolationException("password","密码错误");
		}
		clearFailedCount(userModel.getUsername());
		return loginUser;
	}
	
	
	
	private static void addFailedCount(String userName)
	{
		Integer count=cache.getIfPresent(userName);
		cache.put(userName, count==null?1:count+1);
	}
	
	private static void checkFailedCount(String userName) throws ManagerException
	{
		Integer count=cache.getIfPresent(userName);
		if (count!=null&&count>=try_count)
		{
			String msg = toLogString("登录失败次数超过{}次，{}分钟内禁止登录",try_count,expire);
			throw new ManagerException(msg);
		}
	}
	
	
	private static void clearFailedCount(String userName) 
	{
		cache.invalidate(userName);
	}
	
	
}
