package cn.com.taiji.css.model.administration.notify;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.Notify;

public class NotifyDeskRequest extends JpaDateTimePageableDataRequest<Notify> {
	// 标题
	private String articleTitle;
	private String status;
	
	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "Notify " + "  where 1=1 ");
		hql.append(" and title =:articleTitle ", articleTitle);	
		hql.append(" and status ='1'");	
		hql.append("order by zd desc,topTime desc,releaseDate desc,createTime desc");
		return hql;
	}

}
