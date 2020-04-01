package cn.com.taiji.css.model.customerservice.report;

import java.util.ArrayList;
import java.util.List;

import cn.com.taiji.common.manager.ManagerException;

public class LssuancePerBankModel {
	//贵籍发行信息
	private String channelName;	//银行名称
	private String guiCount;		//发行量-贵
	private String noGuiCount;		//发行量-非贵
	
	public String getChannelName() {
		return channelName;
	}
	public String getGuiCount() {
		return guiCount;
	}
	public String getNoGuiCount() {
		return noGuiCount;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public void setGuiCount(String guiCount) {
		this.guiCount = guiCount;
	}
	public void setNoGuiCount(String noGuiCount) {
		this.noGuiCount = noGuiCount;
	}
	
	

	
	public List<LssuancePerBankModel> getLssuancePerBank(List<Object[]> result,List<Object[]> result2) throws ManagerException{
		//查询贵籍
		List<LssuancePerBankModel> Distribution = new ArrayList<LssuancePerBankModel>();
		for (Object[] objects : result) {
			LssuancePerBankModel lssuancePerBankModel = new LssuancePerBankModel();
			lssuancePerBankModel.setChannelName((String) objects[0]);
			lssuancePerBankModel.setGuiCount(objects[1].toString());
			lssuancePerBankModel.setNoGuiCount("");
			Distribution.add(lssuancePerBankModel);
		}
		//查询非贵籍
		for1:
		for (Object[] objects : result2) {
			for (LssuancePerBankModel lssuancePerBankModels : Distribution) {
				if((boolean) objects[0].toString().equals(lssuancePerBankModels.getChannelName())) {
					lssuancePerBankModels.setNoGuiCount(objects[1].toString());
					continue for1;
				}
			}
			LssuancePerBankModel lssuancePerBankModel = new LssuancePerBankModel();
			lssuancePerBankModel.setChannelName((String) objects[0]);
			lssuancePerBankModel.setGuiCount("");
			lssuancePerBankModel.setNoGuiCount(objects[1].toString());
			Distribution.add(lssuancePerBankModel);
		}
		return Distribution;
	}
	
	public List<NativePlaceCirculationModel> getNativePlaceCirculation(List<Object[]> listByEnableTime, List<Object[]> listByEnableTime2){
		//获取贵籍
		List<NativePlaceCirculationModel> nativePlaceCirculations = new ArrayList<NativePlaceCirculationModel>();

		for (Object[] objects : listByEnableTime2) {
			NativePlaceCirculationModel na = new NativePlaceCirculationModel();
			na.setName(objects[0].toString());
			na.setGuiCount(objects[1].toString());
			na.setNoGuiCount("");
			nativePlaceCirculations.add(na);
		}
		//获取非贵籍
		for1:
		for (Object[] objects : listByEnableTime) {
			for (NativePlaceCirculationModel nativePlaceCirculationModel : nativePlaceCirculations) {
				if(objects[0].toString().equals(nativePlaceCirculationModel.getName())) {
					nativePlaceCirculationModel.setNoGuiCount(objects[1].toString());
					continue for1;
				}
			}
			NativePlaceCirculationModel na = new NativePlaceCirculationModel();
			na.setName(objects[0].toString());
			na.setGuiCount("");
			na.setNoGuiCount(objects[1].toString());
			nativePlaceCirculations.add(na);
		}
		
		return nativePlaceCirculations;
	}
	
}
