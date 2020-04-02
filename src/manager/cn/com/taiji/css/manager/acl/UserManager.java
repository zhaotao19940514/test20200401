package cn.com.taiji.css.manager.acl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.User.UserStatus;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.repo.request.acl.UserPageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2010-5-31 上午10:38:23<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface UserManager
{
	public String add(User user, User loginUser) throws JsonManagerException;

	public User findById(String id);

	public User findByLoginName(String loginName);

	public Pagination queryPage(UserPageRequest req, User loginUser);

	LargePagination<User> queryLargePage(UserPageRequest req, User user);
	
	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @param loginUser TODO
	 * @return
	 * @throws ManagerException
	 */
	public User update(User user, User loginUser) throws JsonManagerException;

	/**
	 * 修改用户状态
	 * 
	 * @param id
	 * @param status
	 * @throws ManagerException
	 */
	public User updateStatus(String id, UserStatus status) throws ManagerException;

	/**
	 * 修改密码
	 * 
	 * @param passwd
	 * @param uid
	 * @return
	 * @throws ManagerException
	 */
	public User modPasswd(String passwd, String uid) throws ManagerException;
	public List<UserRequset> getLines(File importFile) throws IOException;
	public  ExpenseRefundApplicationResponse saveFile(MultipartFile file) throws ManagerException;
	public UserResponse importExcel(List<UserRequset> user) throws ManagerException;

}
