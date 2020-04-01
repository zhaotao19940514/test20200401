
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.EncodeTool;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.finance.ForcefixCardBalanceResponse;
import cn.com.taiji.css.model.customerservice.finance.ForcefixRequest;
import cn.com.taiji.css.model.customerservice.finance.RechargeRequest;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.ForceFixReqeuest;
import cn.com.taiji.dsi.model.comm.protocol.finance.ForceFixResponse;
import cn.com.taiji.qtk.entity.ApplicationRepairPng;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.repo.jpa.ApplicationRepairPngRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;


@Service
@ComponentScan
public class ForcefixManagerImpl extends AbstractDsiCommManager implements ForcefixManager{

	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
		
	@Autowired
	private ApplicationRepairPngRepo applicationRepairPngRepo;
	
	
	@Autowired
	private RechargeManager rechargeManager;
	@Autowired
	private FinanceBinService financeBinService;
	
	@Override
	public LargePagination<ChargeDetail> queryPage(ForcefixRequest queryModel) {
		return chargeDetailRepo.largePage(queryModel);
	}

	@Override
	public ForcefixCardBalanceResponse updateStatus(String chargeId,Long cardBalance,User user) throws ManagerException, ApiRequestException, IOException  {
		ForcefixCardBalanceResponse forcefixCardBalanceResponse= new ForcefixCardBalanceResponse();
		forcefixCardBalanceResponse.setStatus(-1);	
			ChargeDetail chargeDetail=chargeDetailRepo.findByChargeId(chargeId);
			if(chargeDetail!=null) {
				if(chargeDetail.getPreBalance()==null) {
					forcefixCardBalanceResponse.setMessage("流水号:"+chargeId+"在数据库中的卡充值前余额为空,请联系管理员!");
					return forcefixCardBalanceResponse;
				}
				ForceFixReqeuest requset=new ForceFixReqeuest();
				requset.setChargeId(chargeId);
				ForceFixResponse response=financeBinService.rechargeOutlierContinueCharge(requset);
				ChargeDetail cd=response.getChargeDetail();
					if(cd!=null) {
						//现金收费  先将其充值到对应得账户去 然后再从账户中消费掉这笔钱
						if( cd.getTradeType()==0) {
							RechargeRequest rechargeRequest =new RechargeRequest();
							rechargeRequest.setCardId(cd.getCardId());
							rechargeRequest.setTradeFee(cd.getPaidAmount()+cd.getGiftAmount());
							AccountChargeResponse accountChargeResponse=rechargeManager.accountReverse(rechargeRequest, user);
							if(accountChargeResponse.getStatus()==-1) {
								forcefixCardBalanceResponse.setMessage(accountChargeResponse.getMessage());
								return forcefixCardBalanceResponse;
							}
						}
						forcefixCardBalanceResponse.setMessage("强制修复成功!");
						forcefixCardBalanceResponse.setStatus(1);
						return forcefixCardBalanceResponse;
					}
				}else {
				forcefixCardBalanceResponse.setMessage("未找到该半条流水,请联系管理员!");
			}
		
		return forcefixCardBalanceResponse;
	}
	
	
	
	@Override
	public ForcefixCardBalanceResponse deleteStatus(String chargeId,Long cardBalance) throws ManagerException, ApiRequestException, IOException{
		ForcefixCardBalanceResponse forcefixCardBalanceResponse= new ForcefixCardBalanceResponse();
		ForceFixReqeuest requset=new ForceFixReqeuest();
		requset.setChargeId(chargeId);
		ForceFixResponse chargeDetail=financeBinService.rechargeOutlierClearData(requset);
		if(chargeDetail!=null) {
			forcefixCardBalanceResponse.setMessage("强制修复成功!");
			forcefixCardBalanceResponse.setStatus(1);
			return forcefixCardBalanceResponse;
		}
		return forcefixCardBalanceResponse;
	}

	
	@Override
	public List<String> getScreenShotBase64(String  chargeId) throws ManagerException {
		
		List<ApplicationRepairPng> list = applicationRepairPngRepo.findByOwnerInfoAndFileType(chargeId);
		List<String> listq = Lists.newArrayList();
		if(list.size()==0){return null;}
		for(int i = 0;i<list.size();i++) {
			ApplicationRepairPng applicationRepairPng = list.get(i);
			if(applicationRepairPng.getFilePath()==null){return null;}
			File file = new File(applicationRepairPng.getFilePath());
//			File file = new File("D:\\home\\tp");
			if(!file.exists()){return null;}
			 File[] files = file.listFiles();
			 if (null == files || files.length == 0) {
	                System.out.println("文件夹是空的!");
	            }else {
	                for (File file2 : files) {
	                	String encodeBase64="";
	        			try {
	        				encodeBase64 = EncodeTool.encodeBase64(file2);
	        			} catch (IOException e) {
	        				e.printStackTrace();
	        				throw new ManagerException("截图转码错误！");
	        			}
//	        			String suffix = ".png";
	        			String suffix = applicationRepairPng.getFileName().substring(applicationRepairPng.getFileName().lastIndexOf('.'));
	        			if(".png".equalsIgnoreCase(suffix)){encodeBase64 = "data:image/png;base64,"+encodeBase64;}
	        			else if(".jpg".equalsIgnoreCase(suffix)){encodeBase64 ="data:image/jpg;base64,"+encodeBase64;}
	        			listq.add(encodeBase64);	
	            }
	            }
		}
		return listq;
}

	@Override
	public Model modelAdd(String chargeId,Model model) throws ManagerException {
		model.addAttribute("customerIdType", CustomerIDType.values());
		List<String> screenShotBase64 = getScreenShotBase64(chargeId);
		if(screenShotBase64 == null) throw new ManagerException("没查到此条数据对应的上传图片！");
		if (screenShotBase64.size() == 2) {
			model.addAttribute("imgBase64", screenShotBase64.get(0));
			model.addAttribute("imgBase641", screenShotBase64.get(1));
		}
		if (screenShotBase64.size() == 1) {
			model.addAttribute("imgBase64", screenShotBase64.get(0));
		}
		if (screenShotBase64.size() >= 3) {
			model.addAttribute("imgBase64", screenShotBase64.get(0));
			model.addAttribute("imgBase641", screenShotBase64.get(1));
			model.addAttribute("imgBase642", screenShotBase64.get(2));
		}
		return model;
	}
	
	
	@Override
	public ChargeDetail findBychargeId(String chargeId,Model model) throws ManagerException {
		
		ChargeDetail chargeDetail=chargeDetailRepo.findByChargeId(chargeId);
		return chargeDetail;
	}
		
	
	
	
}



