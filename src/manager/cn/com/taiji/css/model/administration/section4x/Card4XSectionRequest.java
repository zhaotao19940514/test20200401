package cn.com.taiji.css.model.administration.section4x;

import java.util.List;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.Card4XSection;
import cn.com.taiji.qtk.entity.dict.DeviceVersion;

public class Card4XSectionRequest extends JpaPageableDataRequest<Card4XSection> {

	private String startId;
	private String endId;
	private Integer version;
	private List<String> batchInfo;
	private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public List<String> getBatchInfo() {
		return batchInfo;
	}

	public void setBatchInfo(List<String> batchInfo) {
		this.batchInfo = batchInfo;
	}

	public String getStartId() {
		return startId;
	}

	public void setStartId(String startId) {
		this.startId = startId;
	}

	public String getEndId() {
		return endId;
	}

	public void setEndId(String endId) {
		this.endId = endId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + Card4XSection.class.getName() + " a where 1=1 ");
		hql.append(" and startId =:startId", startId);
		hql.append(" and endId =:endId", endId);
		hql.append(" and to_number( startId ) <= to_number( :startId ) ", cardId);
		hql.append(" and to_number( endId ) >= to_number( :endId ) ", cardId);
		if (null != version)
			hql.append(" and version =:version", DeviceVersion.getDeviceVersionFromCode(version));
		hql.append(" order by startId");
		return hql;
	}

	public void paramCheck() {
		MyViolationException mve = new MyViolationException();
		if (!hasText(startId) || startId == null || !startId.startsWith("5201") || !startId.matches("[0-9]+")
				|| startId.length() != 20)
			mve.addViolation("startId", "起始号段输入错误");
		if (!hasText(endId) || endId == null || !endId.startsWith("5201") || !endId.matches("[0-9]+")
				|| endId.length() != 20)
			mve.addViolation("endId", "终止号段输入错误");
		if (null == version)
			mve.addViolation("version", "卡版本不能为空");
		if (version != null && version != 4)
			mve.addViolation("version", "卡版本选择错误");
		if (mve.hasViolation()) {
			throw mve;
		}
	}

}
