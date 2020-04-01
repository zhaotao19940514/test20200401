
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.EncodeTool;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.administration.agency.AgencyVarifyManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.manager.util.FileWriter;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingResponse;
import cn.com.taiji.css.model.customerservice.finance.SupplyCardBalanceRequest;
import cn.com.taiji.css.model.customerservice.finance.SupplyCardBalanceResponse;
import cn.com.taiji.dsi.manager.comm.client.FinanceBinService;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeByCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeByCOSResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeCheckRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeCheckResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardChargeConfirmByCOSResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.ChargeDetailBak;
import cn.com.taiji.qtk.entity.SupplyCardBalanceDetail;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailBakRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.SupplyCardBalanceDetailRepo;


@Service
public class SupplyCardBalanceManagerImpl extends AbstractDsiCommManager implements SupplyCardBalanceManager{

	
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	@Autowired
	private SupplyCardBalanceDetailRepo supplyCardBalanceDetailRepo;
	
	@Autowired
	private FinanceBinService financeBinService;
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	@Autowired
	private ChargeDetailBakRepo bakRepo;
	@Autowired
	private AgencyVarifyManager agencyVarifyManager;
	@Override
	public LargePagination<SupplyCardBalanceDetail> queryPage(SupplyCardBalanceRequest queryModel,HttpServletRequest request) throws ManagerException {
		if(LoginHelper.getLoginUser(request).getRole().isSystem()==false) {
			CardInfo cardInfo = cardInfoRepo.findByCardId(queryModel.getCardId());
			if(cardInfo==null) {
				throw new ManagerException("查无此卡信息!");
			}
			String agencyId=LoginHelper.getLoginUser(request).getStaff().getServiceHall().getAgencyId();
			boolean falg =false;
			try {
				falg = agencyVarifyManager.varifyAgency(LoginHelper.getLoginUser(request), cardInfo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ManagerException("渠道校验失败："+e.getMessage());
			}
			if(falg) {
				queryModel.setAgencyId(agencyId);
			}else {
				throw new ManagerException("当前渠道无权操作此卡");
			}
		}
		return supplyCardBalanceDetailRepo.largePage(queryModel);
	}
	
	@Override
	public SupplyCardBalanceResponse FindCardInfoByCardId(SupplyCardBalanceRequest request, User user)
			throws ManagerException {
		SupplyCardBalanceResponse supplyCardBalanceResponse =new SupplyCardBalanceResponse();
		CardInfo cardInfo=cardInfoRepo.findByCardId(request.getCardId());
		if(cardInfo!=null&&cardInfo.getCustomer()!=null&&cardInfo.getVehicle()!=null) {
			supplyCardBalanceResponse.setCardId(request.getCardId());
			supplyCardBalanceResponse.setCustomerName(cardInfo.getCustomer().getCustomerName());
			supplyCardBalanceResponse.setCustomerIdNum(cardInfo.getCustomer().getCustomerIdNum());
			supplyCardBalanceResponse.setVehiclePlate(cardInfo.getVehicle().getVehiclePlate());
			supplyCardBalanceResponse.setVehiclePlateColor(cardInfo.getVehicle().getVehiclePlateColor());
			supplyCardBalanceResponse.setExpireTime(cardInfo.getExpireTime());
			supplyCardBalanceResponse.setEnableTime(CssUtil.getNowDateTimeStrWithoutT());
			supplyCardBalanceResponse.setStatus(1);
		}else {
			supplyCardBalanceResponse.setStatus(-1);
		}
		return supplyCardBalanceResponse;
	}

	
	@Override
	public boolean agencyCheck(User user,String cardId) throws ManagerException
	{
		String agencyId=  user.getStaff().getServiceHall().getAgencyId();
		CardInfo cardInfo = cardInfoRepo.findByCardId(cardId);
		return(agencyId.equals(cardInfo.getAgencyId()));
	}

	@Override
	public SupplyCardBalanceResponse save(SupplyCardBalanceRequest request, User user) throws ManagerException {
		SupplyCardBalanceResponse response = new SupplyCardBalanceResponse();
		SupplyCardBalanceDetail supplyCardBalanceDetail =new SupplyCardBalanceDetail();
		supplyCardBalanceDetail.setCardId(request.getCardId());
		supplyCardBalanceDetail.setCardBalance(request.getPaidAmount());
		supplyCardBalanceDetail.setVehiclePlate(request.getVehiclePlate());
		supplyCardBalanceDetail.setVehiclePlateColor(request.getVehiclePlateColor());
		supplyCardBalanceDetail.setCustomerName(request.getCustomerName());
		supplyCardBalanceDetail.setCustomerIdNum(request.getCustomerIdNum());
		supplyCardBalanceDetail.setEnableTime(request.getEnableTime());
		supplyCardBalanceDetail.setExpireTime(request.getExpireTime());
		supplyCardBalanceDetail.setStatus(2);
		supplyCardBalanceDetailRepo.save(supplyCardBalanceDetail);
		response.setMessage("补卡额入库成功!,请点击对应的补卡额流水后的上传图片按钮!");
		response.setStatus(1);
		return response;
	}
	
	
	public  HalfauditingResponse savePng(MultipartFile file,String cardId,String enableTime) throws ManagerException {
		HalfauditingResponse response =new HalfauditingResponse();
		SupplyCardBalanceDetail supplyCardBalanceDetail =supplyCardBalanceDetailRepo.findByCardIdAndFileType(cardId, enableTime);
		enableTime = enableTime.replaceAll("[\\pP\\pS\\pZ]", "");
		String parentDirRelativePath = MyFinals.HALFAUDITING_IMG+File.separator+cardId+File.separator+enableTime;
		String fileAbsolutePath = FileWriter.savePic(file,supplyCardBalanceDetail, parentDirRelativePath);
		supplyCardBalanceDetail.setFilePath(fileAbsolutePath);
		supplyCardBalanceDetail.setFileName(FileWriter.generateFileName(supplyCardBalanceDetail,file));
		supplyCardBalanceDetailRepo.save(supplyCardBalanceDetail);
		response.setMessage("上传成功");
		return response;
}
	
	@Override
	public List<String> getScreenShotBase64(String  cardId,String enableTime) throws ManagerException {
		
		SupplyCardBalanceDetail supplyCardBalanceDetail =supplyCardBalanceDetailRepo.findByCardIdAndFileType(cardId, enableTime);
		List<String> listq = Lists.newArrayList();
			if(supplyCardBalanceDetail.getFilePath()==null){return null;}
			File file = new File(supplyCardBalanceDetail.getFilePath());
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
	        			String suffix = supplyCardBalanceDetail.getFileName().substring(supplyCardBalanceDetail.getFileName().lastIndexOf('.'));
	        			if(".png".equalsIgnoreCase(suffix)){encodeBase64 = "data:image/png;base64,"+encodeBase64;}
	        			else if(".jpg".equalsIgnoreCase(suffix)){encodeBase64 ="data:image/jpg;base64,"+encodeBase64;}
	        			listq.add(encodeBase64);	
	            }
	            }
		return listq;
}
	@Override
	public Model modelAdd(String cardId,String enableTime,Model model) throws ManagerException {
		model.addAttribute("customerIdType", CustomerIDType.values());
		List<String> screenShotBase64 = getScreenShotBase64(cardId,enableTime);
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

	
	/**
	 * 圈存检测
	 * @param 卡片编号 充值金额 充值前金额
	 * @return  command  chargeStatus rechargeId
	 * @throws Exception
	 * 
	 * CardChargeCheckRequest request
	 */
	@Override
	public CardrechargeResponse  CardChargeCheck(CardrechargeRequest request,User user) throws ManagerException  {
		CardrechargeResponse cardrechargeResponse=new CardrechargeResponse();
		//圈存检测参数
		CardChargeCheckRequest cardChargeCheckRequest =new CardChargeCheckRequest();
		super.commSet(cardChargeCheckRequest,user);
		String rechargeId = super.generateRechargeId(cardChargeCheckRequest);
			
		cardChargeCheckRequest.setCardId(request.getCardId());
		cardChargeCheckRequest.setFee(request.getPaidAmount()+request.getGiftAmount());
		cardChargeCheckRequest.setTradeType(5);
		cardChargeCheckRequest.setPreBalance(request.getPreBalance());
		try {
			CardChargeCheckResponse res = financeBinService.cardChargeCheckV2(cardChargeCheckRequest);
			cardrechargeResponse.setPaidAmount(request.getPaidAmount());
			cardrechargeResponse.setGiftAmount(request.getGiftAmount());
			cardrechargeResponse.setCommand(res.getCommand());
			cardrechargeResponse.setChargeStatus(res.getChargeStatus());
			cardrechargeResponse.setRechargeId(rechargeId);
			cardrechargeResponse.setMessage(res.getMessage());
			cardrechargeResponse.setStatus(res.getStatus());
			cardrechargeResponse.setPostBalance(request.getPaidAmount());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存检测失败,请检查卡片是否在读卡器上");
		}
		return cardrechargeResponse;
	}

	/**
	 * 圈存申请
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override 	
	public CardrechargeResponse  CardChargeByCOS(CardrechargeRequest request,User user) throws ManagerException {
		//圈存申请参数
		CardChargeByCOSRequest cardChargeByCOSRequest=new CardChargeByCOSRequest(); 
		CardrechargeResponse response =new CardrechargeResponse();
		//圈存申请
		super.commSet(cardChargeByCOSRequest,user);
		cardChargeByCOSRequest.setRechargeId(request.getRechargeId());
		cardChargeByCOSRequest.setCardId(request.getCardId());
		cardChargeByCOSRequest.setFee(request.getPaidAmount()+request.getGiftAmount());
		cardChargeByCOSRequest.setPreBalance(request.getPreBalance());
		cardChargeByCOSRequest.setTradeType(5);
		cardChargeByCOSRequest.setCommand(request.getCommand());
		cardChargeByCOSRequest.setCosResponse(request.getCosResponse());
		try {
			CardChargeByCOSResponse res = financeBinService.cardChargeByCOSV2(cardChargeByCOSRequest);
			response.setPaidAmount(request.getPaidAmount());
			response.setGiftAmount(request.getGiftAmount());
			response.setMessage(res.getMessage());
			response.setCommand(res.getCommand());
			response.setCommandType(res.getCommandType());
			response.setStatus(res.getStatus());
			response.setRechargeId(request.getRechargeId());
			response.setMessage(res.getMessage());
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存申请失败");
		}
		return response;
	}
	
	
	
	/**
	 * 圈存确认
	 * @param 交易流水号  实收金额   赠送金额  圈存指令结果   圈存指令
	 * @return  充值后金额
	 * @throws Exception
	 */
	public CardrechargeResponse  CardChargeConfirmByCOS(CardrechargeRequest request,User user) throws ManagerException {
		//圈存确认参数
		CardChargeConfirmByCOSRequest cardChargeConfirmByCOSRequest=new CardChargeConfirmByCOSRequest();
		CardrechargeResponse response =new CardrechargeResponse();
		//圈存确认
		super.commSet(cardChargeConfirmByCOSRequest,user);
		cardChargeConfirmByCOSRequest.setRechargeId(request.getRechargeId());
		cardChargeConfirmByCOSRequest.setPaidAmount(request.getPaidAmount());
		cardChargeConfirmByCOSRequest.setGiftAmount(request.getGiftAmount());
		cardChargeConfirmByCOSRequest.setCommand(request.getCommand());
		cardChargeConfirmByCOSRequest.setCosResponse(request.getCosResponse());
		try { 
			CardChargeConfirmByCOSResponse res = financeBinService.cardChargeConfirmByCOS(cardChargeConfirmByCOSRequest);
			if(res==null) {
				response.setStatus(-1);
				response.setMessage("圈存确认接口调用失败");
			}else if(res.getPostBalance()!=null) {
				response.setPostBalance(res.getPostBalance());
				SupplyCardBalanceDetail supplyCardBalanceDetail =supplyCardBalanceDetailRepo.findByCardIdAndFileType(request.getCardId(), request.getEnableTime());
				supplyCardBalanceDetail.setStatus(1);
				supplyCardBalanceDetailRepo.save(supplyCardBalanceDetail);
				ChargeDetail chargeDetail = chargeDetailRepo.findByChargeId(request.getRechargeId());
				if(chargeDetail==null)
					throw new ManagerException("未找到流水:"+request.getRechargeId());
				ChargeDetailBak bak = chargeDetail.toBak();
				//圈存备份表中的补卡额标识
				bak.setStatus(-1);
				bakRepo.save(bak);
				chargeDetailRepo.deleteByChargeId(request.getRechargeId());
				response.setStatus(res.getStatus());
				response.setMessage(res.getMessage());
			}
			
		} catch (ApiRequestException | IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存确认失败,请重新圈存");
		}
		return response;
	}
	

	
}

