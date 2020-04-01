package cn.com.taiji.css.manager.administration.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.notify.NotifyDeskRequest;
import cn.com.taiji.css.model.administration.notify.NotifyRequest;
import cn.com.taiji.qtk.entity.Notify;
import cn.com.taiji.qtk.repo.jpa.NotifyRepo;

@Service("notifyManager")
public class NotifyManagerImpl extends AbstractManager implements NotifyManager{
	@Autowired
	private NotifyRepo notifyRepo;
	@Override
	public Pagination queryPage(NotifyRequest queryModel) {
		return notifyRepo.page(queryModel);
	}
	
	@Override
	public Notify add(Notify notifyModel) throws ManagerException {

		validate(notifyModel);
		if (notifyModel.getContent() == null) {
			throw new ManagerException("通知内容不能为空");
		} 
		if (notifyModel.getContent().length()>5242880) {
			throw new ManagerException("通知内容大小不能超过10M");
		}
		Notify notify = new Notify();
		notify.setTitle(notifyModel.getTitle());
		notify.setContent(notifyModel.getContent());
		notify.setStatus(notifyModel.getStatus());
		notify.setReleaseDate(notifyModel.getReleaseDate());
		notify.setName(notifyModel.getName());
		notify.setZd(0);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		notify.setTopTime(sdf1.format(new Date()));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		notify.setCreateTime(sdf.format(date));
		return notifyRepo.save(notify);
	}
	private void validate(Notify notifyModel) {
		MyViolationException mve = new MyViolationException();
		if (notifyModel.getStatus() == null) {
			mve.addViolation("status", "发布状态为空");
		}

		if (notifyModel.getReleaseDate() == null)
			mve.addViolation("releaseDate", "发布日期为空");
		if (notifyModel.getTitle() == null) {
			mve.addViolation("title", "通知标题为空");
		} else {
			if (notifyModel.getTitle().length() > 30) {
				mve.addViolation("title", "通知标题长度应该小于30");
			}
		}

		if (mve.hasViolation()) {
			throw mve;
		}
	}

	@Override
	public Notify findById1(String id) {
		return notifyRepo.findByIds(id);
	}

	@Override
	public Notify updateNotify(Notify notifyModel,User user) throws ManagerException {
		AssertUtil.notNull(notifyModel.getId());
		validate(notifyModel);
		if (notifyModel.getContent() == null) {
			throw new ManagerException("通知内容不能为空");
		}
		if (notifyModel.getContent().length()>5242880) {
			throw new ManagerException("通知内容大小不能超过10M");
		}
		Notify dbnotify = notifyRepo.findByIds(notifyModel.getId());
		if (null == dbnotify)
			throw new ManagerException("未找到该通知公告信息");
		dbnotify.setContent(notifyModel.getContent());
		dbnotify.setReleaseDate(notifyModel.getReleaseDate());
		dbnotify.setTitle(notifyModel.getTitle());
		dbnotify.setStatus(notifyModel.getStatus());
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dbnotify.setCreateTime(sdf.format(date));
		String userName = user.getName();
		dbnotify.setName(userName);
		return notifyRepo.save(dbnotify);
	}

	@Override
	public void delete(String id) {
		Notify notify = findById1(id);
		notifyRepo.delete(notify);
	}

	@Override
	public Notify update(String id) {
		Notify notify = findById1(id);
		notify.setZd(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		notify.setTopTime(sdf.format(new Date()));
		return notifyRepo.save(notify);
	}

	@Override
	public Notify qxZd(String id) {
		Notify notify = findById1(id);
		notify.setZd(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		notify.setTopTime(sdf.format(new Date()));
		return notifyRepo.save(notify);
	}

	@Override
	public Pagination queryNotifyDeskPage(NotifyDeskRequest queryModel) {
		return notifyRepo.page(queryModel);
	}
}
