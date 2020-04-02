package cn.com.taiji.css.model.administration.agency.permission;

import org.springframework.data.annotation.Transient;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.AgencyVarify;
import cn.com.taiji.qtk.entity.dict.CardTypeSimple;

/**
 * 
 * @author lz
 *
 */
public class AgencyPermissionRequest extends JpaDateTimePageableDataRequest<AgencyVarify> {
	private String agencyId;
	private String permittedAgencyId;
	private Integer cardTypeCode;
	@Transient
	private Integer batchType;
	@Transient
	private String id;
	@Transient
	private String agencyList;//树状图参数

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from AgencyVarify where 1=1 ");
		hql.append(" and agencyId = :agencyId", agencyId);
		hql.append(" and permittedAgencyId = :permittedAgencyId", permittedAgencyId);
		if (cardTypeCode != null) {
			hql.append(" and cardType = :cardType", CardTypeSimple.fromCode(cardTypeCode));
		}
		return hql;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getPermittedAgencyId() {
		return permittedAgencyId;
	}

	public void setPermittedAgencyId(String permittedAgencyId) {
		this.permittedAgencyId = permittedAgencyId;
	}

	public Integer getCardTypeCode() {
		return cardTypeCode;
	}

	public void setCardTypeCode(Integer cardTypeCode) {
		this.cardTypeCode = cardTypeCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBatchType() {
		return batchType;
	}

	public void setBatchType(Integer batchType) {
		this.batchType = batchType;
	}

	public String getAgencyList() {
		return agencyList;
	}

	public void setAgencyList(String agencyList) {
		this.agencyList = agencyList;
	}
	
	public void batchParamCheck() {
		MyViolationException mve = new MyViolationException();
		if(!hasText(agencyId)) mve.addViolation("agencyId", "机构名称不能为空");
		if(null == batchType)	mve.addViolation("batchType", "批量操作类型不能为空");
		if(null == cardTypeCode)	mve.addViolation("cardTypeCode", "卡类型不能为空");
		if(mve.hasViolation()) {
			throw mve;
		}
	}
	
	public void addParamCheck() {
		MyViolationException mve = new MyViolationException();
		if(!hasText(agencyId)) mve.addViolation("agencyId", "机构名称不能为空");
		if(!hasText(permittedAgencyId)) mve.addViolation("permittedAgencyId", "被控机构名称不能为空");
		if(null == cardTypeCode)	mve.addViolation("cardTypeCode", "卡类型不能为空");
		if(mve.hasViolation()) {
			throw mve;
		}
	}


}
