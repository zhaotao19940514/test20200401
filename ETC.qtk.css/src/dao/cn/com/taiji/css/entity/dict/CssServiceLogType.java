/**
 * @Title CssServiceType.java
 * @Package cn.com.taiji.css.entity.dict
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月27日 下午8:36:49
 * @version V1.0
 */
package cn.com.taiji.css.entity.dict;

import cn.com.taiji.common.pub.AssertUtil;

/**
 * @ClassName CssServiceType
 * @Description TODO
 * @author yaonl
 * @date 2018年07月27日 20:36:49
 * @E_mail yaonanlin@163.com
 */
public enum CssServiceLogType {
	ALL("全部","","",false){},// 页面展示用
	// 开户服务
	APPLY_QUICKAPPLY_QUICKAPPLY("开户服务-快速办理-快速开户","quickApply","开户",true){},
	APPLY_QUICKAPPLY_DEVICECHECK("开户服务-快速办理-设备检测","deviceCheck","设备检测",false){},
	APPLY_QUICKAPPLY_OBUBINDING("开户服务-快速办理-卡签绑定","obuBinding","卡签绑定",true){},
	APPLY_QUICKAPPLY_EQUIPMENTISSUE("开户服务-快速办理-设备发行","equipmentIssue","设备发行",true){},
	APPLY_QUICKAPPLY_EQUIPMENTISSUE_ISSUEPACKAGESELECT("开户服务-快速办理-发行套餐保存","issuePackage","选择发行套餐",false){},
	APPLY_QUICKAPPLY_EQUIPMENTISSUE_ISSUEPACKAGEPAY("开户服务-快速办理-发行套餐支付","issuePackage","支付发行套餐",true){},
	APPLY_QUICKAPPLY_EQUIPMENTISSUE_CARD("开户服务-快速办理-卡发行","openCard","开卡",true){},
	APPLY_QUICKAPPLY_EQUIPMENTISSUE_OBU("开户服务-快速办理-obu发行","openObu","开OBU",true){},
	APPLY_BASEINFO_CUSTOMER("开户服务-基础信息-用户信息","customer","用户信息",false){},
	APPLY_BASEINFO_CUSTOMEACCOUNT("开户服务-基础信息-用户账户信息","customeAccount","用户账户",false){},
	APPLY_BASEINFO_VEHICLE("开户服务-基础信息-车辆信息","vehicleInfo","车辆信息",false){},
	APPLY_BASEINFO_CARD("开户服务-基础信息-ETC卡信息","cardInfo","卡信息",false){},
	APPLY_BASEINFO_OBU("开户服务-基础信息-OBU信息","obuInfo","obu信息",false){},
//	APPLY_BASEINFO_EMERGENCYTRUE("开户服务-基础信息-车辆标记应急","emergencyTrue","车辆标记应急"){},
//	APPLY_BASEINFO_EMERGENCYFALSE("开户服务-基础信息-车辆取消应急","emergencyFalse","车辆取消应急"){},
	APPLY_EMERGENCY_QUICKAPPLY("开户服务-应急车辆业务-快速开户","egQuickApply","开户",false){},
	APPLY_EMERGENCY_OBUBINDING("开户服务-应急车辆业务-卡签绑定","egObuBinding","卡签绑定",false){},
	APPLY_EMERGENCY_EQUIPMENTISSUE_ISSUEPACKAGESELECT("开户服务-应急车辆业务-发行套餐保存","egIssuePackage","选择发行套餐",false){},
	APPLY_EMERGENCY_EQUIPMENTISSUE_ISSUEPACKAGEPAY("开户服务-应急车辆业务-发行套餐支付","egIssuePackage","支付发行套餐",false){},
	APPLY_EMERGENCY_EQUIPMENTISSUE_CARD("开户服务-应急车辆业务-卡发行","egOpenCard","开卡",false){},
	APPLY_EMERGENCY_EQUIPMENTISSUE_OBU("开户服务-应急车辆业务-obu发行","egOpenObu","开OBU",false){},
	APPLY_EMERGENCY_CUSTOMER("开户服务-应急车辆业务-用户信息","egCustomer","用户信息",false){},
	APPLY_EMERGENCY_VEHICLE("开户服务-应急车辆业务-车辆信息","egVehicleInfo","车辆信息",false){},
	// 售后服务
	CUSTOMERSERVICE_CARD_LOSS("售后服务-卡片服务-卡挂失","cardLoss","卡挂失",true){},
	CUSTOMERSERVICE_CARD_UNLOSS("售后服务-卡片服务-卡解除挂失","cardUnloss","卡解挂",true){},
	CUSTOMERSERVICE_CARD_SUPPLY("售后服务-卡片服务-补卡","cardSupply","补卡",true){},
	CUSTOMERSERVICE_CARD_PRECANCEL("售后服务-卡片服务-预销卡","cardPrecancel","预销卡",true){},
	CUSTOMERSERVICE_CARD_CANCELRELEASE("售后服务-卡片服务-预销卡释放车牌","cardCancelRelease","预销卡释放车牌",true){},
	CUSTOMERSERVICE_CARD_CANCEL("售后服务-卡片服务-销卡","cardCancel","销卡",true){},
	CUSTOMERSERVICE_CARD_MANCANCEL("售后服务-卡片服务-销卡管理","manCancel","销卡管理",true){},
	CUSTOMERSERVICE_CARD_REPLACE("售后服务-卡片服务-换卡","cardReplace","换卡",true){},
	CUSTOMERSERVICE_CARD_HANG("售后服务-卡片服务-卡挂起","cardHang","卡挂起",true){},
	CUSTOMERSERVICE_CARD_REWRITE("售后服务-卡片服务-卡重写","cardRewrite","卡重写",true){},
	CUSTOMERSERVICE_CARD_UNHANG("售后服务-卡片服务-卡解除挂起","cardUnhang","卡解除挂起",true){},
	CUSTOMERSERVICE_CARD_PINUNLOCKING("售后服务-卡片服务-卡PIN码解锁","cardPinUnlocking","卡PIN码解锁",true){},
	CUSTOMERSERVICE_OBU_PREACTIVE("售后服务-电子标签服务-预激活","obuPreactive","预激活",true){},
	CUSTOMERSERVICE_OBU_OFFLINEINSTALL("售后服务-电子标签服务-离线安装","obuOfflineInstall","离线安装obu",true){},
	CUSTOMERSERVICE_OBU_EXCHANGE("售后服务-电子标签服务-更换","obuExchange","obu更换",true){},
	CUSTOMERSERVICE_OBU_CANCEL("售后服务-电子标签服务-注销","obuCancel","obu注销",true){},
	CUSTOMERSERVICE_OBU_REWRITE("售后服务-电子标签服务-重写","obuRewrite","obu重写",true){},
	CUSTOMERSERVICE_OBU_HANG("售后服务-电子标签服务-挂起","obuHang","obu挂起",true){},
	CUSTOMERSERVICE_OBU_UNHANG("售后服务-电子标签服务-解除挂起","obuUnhang","obu解除挂起",true){},
	CUSTOMERSERVICE_FINANCE_RECHARGE("售后服务-资金服务-充值","recharge","充值",true){},
	CUSTOMERSERVICE_FINANCE_CARDRECHARGE("售后服务-资金服务-圈存","cardRecharge","圈存",true){},
	CUSTOMERSERVICE_FINANCE_CANCELACCOUNT("售后服务-资金服务-销户","cancelAccount","销户",true){},
	CUSTOMERSERVICE_FINANCE_CARDRECHARGEFIX("售后服务-资金服务-圈存修复","cardRechargeFix","圈存修复",true){},
	CUSTOMERSERVICE_FINANCE_HALFAUDITING("售后服务-资金服务-半条审核","halfAuditing","半条审核",true){},
	CUSTOMERSERVICE_FINANCE_FORCEFIX("售后服务-资金服务-强制修复","forceFix","强制修复",true){},
	CUSTOMERSERVICE_FINANCE_CARDREVERSE("售后服务-资金服务-圈存冲正","cardReverse","圈存冲正",true){},
	CUSTOMERSERVICE_FINANCE_CARDREFUNDCOST("售后服务-资金服务-注销退款","cardRefundCost","注销退款",true){},
	CUSTOMERSERVICE_FINANCE_ACCOUNTREVERSE("售后服务-资金服务-账户冲正","accountReverse","账户冲正",true){},
	CUSTOMERSERVICE_FINANCE_POSREVERSE("售后服务-资金服务-POS冲正","posReverse","POS冲正",true){},
	CUSTOMERSERVICE_FINANCE_USERACCOUNTMANAGE("售后服务-资金服务-用户账密码管理","userAccountManage","用户账密码管理",true){},
	CUSTOMERSERVICE_FINANCE_INITIALIZATIONUSERACCOUNT("售后服务-资金服务-用户账密码初始化","initializationUserAccount","用户账密码初始化",true){},
	CUSTOMERSERVICE_FINANCE_BALANCESUPPLY("售后服务-资金服务-余额补领","balanceSupply","余额补领",true){},
	CUSTOMERSERVICE_FINANCE_SUPPLYCARDBALANCE("售后服务-资金服务-补卡额","supplyCardBalance","补卡额",true){},
	CUSTOMERSERVICE_FINANCE_REFUNDCHARGE("售后服务-资金服务-消费退费圈存","refundCharge","消费退费圈存",true){},
	CUSTOMERSERVICE_CARDOBUQUERY_CARDDEVICE("售后服务-卡签查询-卡信息读取","cardInfoRead","卡信息读取",true){},
	CUSTOMERSERVICE_CARDOBUQUERY_OBUDEVICE("售后服务-卡签查询-OBU信息读取","vehicleInfoRead","OBU信息读取",true){},
	// 行政管理
	ADMINISTRATION_INVENTORY_DEVICECARDMODEL("行政管理-库存管理-卡型号管理","cardModel","卡型号管理",false){},
	ADMINISTRATION_INVENTORY_SUPPLYPAYMENT("行政管理--保证金管理-补交保证金","cardModel","补交保证金",false){},
	ADMINISTRATION_INVENTORY_DEVICEOBUMODEL("行政管理-库存管理-电子标签型号管理","obuModel","标签型号管理",false){},
	ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGCARD("行政管理-库存管理-卡入库","cardWarehousing","卡入库",false){},
	ADMINISTRATION_INVENTORY_DEVICEWAREHOUSINGOBU("行政管理-库存管理-电子标签入库","obuWarehousing","标签入库",false){},
	ADMINISTRATION_INVENTORY_DEVICEALLOCATIONCARD("行政管理-库存管理-卡调拨","cardAllocation","卡调拨",false){},
	ADMINISTRATION_INVENTORY_DEVICEALLOCATIONOBU("行政管理-库存管理-电子标签调拨","obuAllocation","obu调拨",false){},
	ADMINISTRATION_PKG_ISSUE("行政管理-套餐管理-发行套餐管理","pkgIssue","发行套餐",false){},
	ADMINISTRATION_PKG_REPLACE("行政管理-套餐管理-补换设备费用管理","pkgReplace","补换设备费用",false){},
	ADMINISTRATION_PKG_ACCOUNT("行政管理-套餐管理-记账卡套餐管理","pkgAccount","记账卡套餐",false){},
	ADMINISTRATION_PKG_CHANGE("行政管理-套餐管理-套餐变更管理","pkgChange","套餐变更",false){},
	ADMINISTRATION_PKG_ISSUERECORDS("行政管理-套餐管理-发行套餐记录管理","pkgRecords","发行套餐记录管理",false){},
	ADMINISTRATION_PKG_ISSUERECORDS_PAY("行政管理-套餐管理-发行套餐记录支付","pkgRecords","发行套餐记录支付",false){},
	ADMINISTRATION_PKG_ISSUERECORDS_REPEAL("行政管理-套餐管理-发行套餐记录作废","pkgRecords","发行套餐记录作废",false){},
	ADMINISTRATION_NOTIFY_NOTIFY("行政管理-通知公告-通知公告","notify","通知公告",false){},
	ADMINISTRATION_AGENCY_MANAGEMENT("行政管理-机构管理","agency","机构管理",false){},
	ADMINISTRATION_AGENCY_VARIFYMANAGE("行政管理-机构管理-机构权限管理","agencyVarify","机构权限管理",false){},
	ADMINISTRATION_SERVICEHALL_MANAGEMENT("行政管理-网点管理","serviceHall","网点管理",false){},
	ADMINISTRATION_STAFF_MANAGEMENT("行政管理-工号管理","staff","工号管理",false){},
	ADMINISTRATION_SECTION4X_MANAGEMENT("行政管理-4X号段管理","section4X","4X号段管理",false){},
	ADMINISTRATION_PERCANCEL("行政管理-预注销管理","cancelRelease","预注销管理",false){},
	ADMINISTRATION_SERVICEHALL_DEVICECONFIG("行政管理-设备配置管理","serivceDeviceConfig","设备配置管理",false){},
	CUSTOMERSERVICE_OBU_LOSS("售后服务-电子标签服务-挂失","obuLoss","obu挂失",true){},
	CUSTOMERSERVICE_OBU_UNLOSS("售后服务-电子标签服务-解挂","obuUnloss","obu解挂",true){},
	CUSTOMERSERVICE_OBU_TRANSFER("售后服务-电子标签服务-过户","obuTransfer","obu过户",true){},
	;
	private String serviceName;
	private String shortEnName;
	private String shortCnName;
	private boolean wetherPrintReceipt;
	
	private CssServiceLogType(String serviceName, String shortEnName, String shortCnName, boolean wetherPrintReceipt) {
		this.serviceName = serviceName;
		this.shortEnName = shortEnName;
		this.shortCnName = shortCnName;
		this.wetherPrintReceipt = wetherPrintReceipt;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getShortEnName() {
		return shortEnName;
	}

	public String getShortCnName() {
		return shortCnName;
	}

	public void setShortEnName(String shortEnName) {
		this.shortEnName = shortEnName;
	}

	public void setShortCnName(String shortCnName) {
		this.shortCnName = shortCnName;
	}
	
	public static CssServiceLogType fromShortEnName(String enName){
		AssertUtil.hasText(enName);
		return null;
	}
	
	public boolean isWetherPrintReceipt() {
		return wetherPrintReceipt;
	}

	public void setWetherPrintReceipt(boolean wetherPrintReceipt) {
		this.wetherPrintReceipt = wetherPrintReceipt;
	}
}

