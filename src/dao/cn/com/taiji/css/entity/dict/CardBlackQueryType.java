package cn.com.taiji.css.entity.dict;

/**
 * 用户类型
 * 
 * @author yangjiaqi
 */
public enum CardBlackQueryType {

	blackUpload("下黑", 0) {
	},
	unBlackUpload("反白", 1) {
	},
//	全量("黑色", 2) {
//	}
	;
	private String value;
	private int Code;

	private CardBlackQueryType(String value, int typeCode) {

		this.value = value;
		this.Code = typeCode;
	}

	public String getValue() {

		return value;
	}

	public int getTypeCode() {

		return Code;
	}
	
	public static CardBlackQueryType valueOfCode(int code){
		for(CardBlackQueryType vo : CardBlackQueryType.values()){
			if(vo.getTypeCode() == code){
				return vo;
			}
		}
		return null;
	}
	
	public static CardBlackQueryType valueOfValues(String values){
		for(CardBlackQueryType vo : CardBlackQueryType.values()){
			String  vehiclePlateColor =vo.getValue();
			if(vehiclePlateColor.equals(values)){
				return vo;
			}
		}
		return null;
	}
	
}
