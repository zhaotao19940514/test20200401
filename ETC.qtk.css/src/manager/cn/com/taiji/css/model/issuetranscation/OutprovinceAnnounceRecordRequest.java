package cn.com.taiji.css.model.issuetranscation;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.FileOutProvinceDetailHis;

public class OutprovinceAnnounceRecordRequest extends JpaPageableDataRequest<FileOutProvinceDetailHis> {
	    private String cardId;
	    private String vehiclePlate;
	    private String enTime;
	    private String exTime;
	    public OutprovinceAnnounceRecordRequest() {
	    	
	    }
		
		@Override
		public HqlBuilder toSelectHql() {
			HqlBuilder hql = new HqlBuilder("from " + "FileOutProvinceDetailHis " +
					"  where 1=1 ");
			if(cardId!=null&&!cardId.isEmpty())hql.append(" and cardId =:cardId", cardId);
			if(vehiclePlate!=null&&!vehiclePlate.isEmpty())hql.append(" and license =:vehiclePlate", vehiclePlate);
	        if(enTime!=null&&!enTime.isEmpty())	hql.append(" and exTime >=:enTime", enTime);
	        if(exTime!=null&&!exTime.isEmpty())	hql.append(" and exTime <=:exTime", exTime);
			return hql;
		}

		public String getCardId() {
			return cardId;
		}

		public void setCardId(String cardId) {
			this.cardId = cardId;
		}

		

		public String getVehiclePlate() {
			return vehiclePlate;
		}

		public void setVehiclePlate(String vehiclePlate) {
			this.vehiclePlate = vehiclePlate;
		}

		public String getEnTime() {
			return enTime;
		}

		public void setEnTime(String enTime) {
			this.enTime = enTime;
		}

		public String getExTime() {
			return exTime;
		}

		public void setExTime(String exTime) {
			this.exTime = exTime;
		}

}
