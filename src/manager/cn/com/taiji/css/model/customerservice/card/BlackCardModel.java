package cn.com.taiji.css.model.customerservice.card;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/***
 * CARD
 */
import java.util.List;

import com.google.common.collect.Lists;

import cn.com.taiji.qtk.entity.BlackCard;
import cn.com.taiji.qtk.entity.BlackCardHis;

public class BlackCardModel {
	//发行方编号
	private String issuerId;
	//用户卡黑名单生成时间
	private String creationTime;
	//类型
	private int type;
	//用户卡编号
	private String cardId;
	//状态
	private int status;
	//录入时间
	private String createTime;
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public static List<BlackCardModel> toBlackCardModel(List<BlackCard> blackCards){
		List<BlackCardModel> blackCardModels = Lists.newArrayList();
		for (BlackCard blackCard : blackCards) {
			BlackCardModel blackCardModel = new BlackCardModel();
			blackCardModel.setCardId(blackCard.getCardId());
			blackCardModel.setCreationTime(blackCard.getCreationTime());
			blackCardModel.setIssuerId(blackCard.getIssuerId());
			blackCardModel.setStatus(1);
			blackCardModel.setType(blackCard.getType());
			Calendar createTime2 = blackCard.getCreateTime();
			Date time = createTime2.getTime();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr=sdf.format(time);
			blackCardModel.setCreateTime(dateStr);
			blackCardModels.add(blackCardModel);
		}
		return blackCardModels;
		
	}
	public static List<BlackCardModel> toBlackCardHisModel(List<BlackCardHis> blackCardHiss){
		List<BlackCardModel> blackCardModels = Lists.newArrayList();
		for (BlackCardHis blackCardHis : blackCardHiss) {
			BlackCardModel blackCardModel = new BlackCardModel();
			blackCardModel.setCardId(blackCardHis.getCardId());
			blackCardModel.setCreationTime(blackCardHis.getCreationTime());
			blackCardModel.setIssuerId(blackCardHis.getIssuerId());
			blackCardModel.setStatus(2);
			blackCardModel.setType(blackCardHis.getType());
			Calendar createTime2 = blackCardHis.getCreateTime();
			Date time = createTime2.getTime();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr=sdf.format(time);
			blackCardModel.setCreateTime(dateStr);
			blackCardModels.add(blackCardModel);
		}
		return blackCardModels;
		
	}
}

