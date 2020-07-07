package cn.com.taiji.css.entity.dict;

public enum DailyStatisticsType {
	DRGJHCFXL(1, "当日贵籍货车发行量", new String[] {"NAME","COUNT(1)"}){},
	LJGJHCBYL(2, "累计贵籍货车保有量", new String[] {"NAME","COUNT(1)"}){},
	ASFTJDRKKL(3, "按省份统计当日开卡量", new String[] {"substr(c.vehicle_id,0,1)","COUNT(1)"}){},
	BRZPTXGHFXL(4, "部认证平台下工行发行量", new String[] {"substr(vehicle_id,0,1)", "channel_name", "count(card_id)"}){},
	DW(5, "单位", new String[] {"select substr(vehicle_id,0,2)","COUNT(1)"}){},
	ALLGJ(6, "All贵籍", new String[] {"NAME","COUNT(1)"}){},
	ALLFGJ(7, "All非贵籍", new String[] {"NAME","COUNT(1)"}){},
	ALLGJCFWDH(8, "ALL贵籍拆分5大行", new String[] {"channel_name","COUNT(*)"}){},
	ALLFGJCFWDH(9, "All非贵籍拆分5大行", new String[] {"channel_name","COUNT(*)"}){},
	YC(10, "异常", new String[] {"NAME", "CARD_STATUS", "COUNT(1)"}){},
	BYXZ(11, "本月新增", new String[] {"NAME","COUNT(1)"}){},
	BY(12, "保有", new String[] {"NAME","COUNT(1)"}){},
	BRZBYLFWDH(13, "部认证保有量分5大行", new String[] {"channel_name","COUNT(*)"}){},
	ZX(14, "注销", new String[] {"NAME", "COUNT(C.ID)", "AGENCY_ID"}){},
	LJZX(15, "累计注销", new String[] {"NAME", "COUNT(C.ID)", "AGENCY_ID"}){},
	DZ(16, "地州", new String[] {"substr(c.vehicle_id,0,2)","COUNT(1)"}){},
	GDQDCX(17, "固定渠道查询", new String[] {"NAME","COUNT(1)"}){},
	GDQDCXLJ(18, "固定渠道查询累计", new String[] {"NAME","COUNT(1)"}){},
	DRZJAKLXHZSSDQHJGTJ(19, "当日注销按卡类型和所属地区和机构统计", new String[] {"NAME", "COUNT(C.ID)", "AGENCY_ID", "贵籍", "卡类型"}){},
	NXHC(20, "农信货车", new String[] {"NAME","COUNT(1)"}){},
	NXHCLJ(21, "农信货车累计", new String[] {"NAME","COUNT(1)"}){},
	QTZLZYZXXQ(22, "黔通智联自营注销详情", new String[] {"卡机构", "操作工号", "VEHICLE_ID", "卡类型", "注销网点"}){},
	DRFXSJ(23, "当日发行数据", new String[] {"NAME", "VEHICLE_PLATE", "VEHICLE_PLATECOLOR", "CARD_ID", "CARD_ENABLETIME", "CARD_STATUS"}){},
	KHFXL(24, "客货发行量", new String[] {"车型","贵籍","COUNT(1)"}){},
	;
	
	private Integer index;
	private String workBookName;//表名称
	private String[] cells;//表头
	private DailyStatisticsType(Integer index, String workBookName, String[] cells) {
		this.index = index;
		this.workBookName = workBookName;
		this.cells = cells;
	}
	public Integer getIndex() {
		return index;
	}
	public String getWorkBookName() {
		return workBookName;
	}
	public String[] getCells() {
		return cells;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public void setWorkBookName(String workBookName) {
		this.workBookName = workBookName;
	}
	public void setCells(String[] cells) {
		this.cells = cells;
	}
	
	public static DailyStatisticsType getDailyStatisticsTypeByIndex(Integer index) {
		DailyStatisticsType[] values = DailyStatisticsType.values();
		for (DailyStatisticsType dailyStatisticsType : values) {
			if (dailyStatisticsType.getIndex() == index) {
				return dailyStatisticsType;
			}
		}
		return null;
	}
	
}
