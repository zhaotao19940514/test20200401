
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.manager.util.FileWriter;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationModel;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CardRefundDetail;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.AuditStatusType;
import cn.com.taiji.qtk.entity.dict.CardType;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;
import cn.com.taiji.qtk.repo.jpa.CardRefundDetailRepo;
import cn.com.taiji.qtk.repo.jpa.FileInprovinceDetailRepo;
import cn.com.taiji.qtk.repo.jpa.VehicleInfoRepo;

@Service
public class ExpenseRefundApplicationManagerImpl extends AbstractDsiCommManager
		implements ExpenseRefundApplicationManager {

	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private CardRefundDetailRepo cardRefundDetailRepo;
	@Autowired
	private VehicleInfoRepo vehicleInfoRepo;
	@Autowired
	private FileInprovinceDetailRepo fileInprovinceDetailRepo;

	@Override
	public List<ExpenseRefundApplicationModel> page(ExpenseRefundApplicationRequest request) {
		if(request.getBeforeDate()==null) {
			request.setBeforeDate("1999-01-01");
		}
		if(request.getAfterDate()==null) {
			request.setAfterDate(CssUtil.getNowDate());
		}
		List<Object[]> listConsumeAll = fileInprovinceDetailRepo.ListConsumeAll(request.getCardId(),request.getBeforeDate(),request.getAfterDate());
		List<ExpenseRefundApplicationModel> expenseRefundApplicationModel=new ArrayList<ExpenseRefundApplicationModel>();
		for(Object[] consumerAll : listConsumeAll) {
			ExpenseRefundApplicationModel model=new ExpenseRefundApplicationModel();
			String id="";
			String vehiclePlate="";
			String enTime="";
			String enName="";
			String exTime="";
			String exName="";
			Long preBalance=0L;
			Long postBalance=0L;
			Long fee=0L;
			Long refundFee=0L;
			Integer status=0;
			if(consumerAll.length>0) {
				if(consumerAll[0]!=null) {
					id=consumerAll[0].toString();
				}
				if(consumerAll[2]!=null) {
					vehiclePlate=consumerAll[2].toString();
				}
				if(consumerAll[3]!=null) {
					enTime=consumerAll[3].toString();
				}
				if(consumerAll[4]!=null) {
					enName=consumerAll[4].toString();
				}
				if(consumerAll[5]!=null) {
					exTime=consumerAll[5].toString();
				}
				if(consumerAll[6]!=null) {
					exName=consumerAll[6].toString();
				}
				if(consumerAll[7]!=null) {
					preBalance=Long.valueOf(consumerAll[7].toString());
				}
				if(consumerAll[8]!=null) {
					fee=Long.valueOf(consumerAll[8].toString());
				}
				if(consumerAll[9]!=null) {
					postBalance=Long.valueOf(consumerAll[9].toString());
				}
				if(consumerAll[10]!=null) {
					status=Integer.valueOf(consumerAll[10].toString());
				}
				if(consumerAll[11]!=null) {
					refundFee=Long.valueOf(consumerAll[11].toString());
				}
				model.setId(id);
				model.setCardId(request.getCardId());
				model.setVehiclePlate(vehiclePlate);
				model.setEnTime(enTime);
				model.setEnName(enName);
				model.setExTime(exTime);
				model.setExName(exName);
				model.setPreBalance(preBalance);
				model.setPostBalance(postBalance);
				model.setFee(fee);
				model.setRefundFee(refundFee);
				model.setStatus(status);
			}
			if(request.getStatus()==1) {
				expenseRefundApplicationModel.add(model);
			}else if(request.getStatus().equals(model.getStatus())) {
				expenseRefundApplicationModel.add(model);
			}
			
		}
		
		return expenseRefundApplicationModel;

	}

	@Override
	public ExpenseRefundApplicationRequest findById(String cardId,String id) throws ManagerException {
		  List<Object[]> listConsumeById = fileInprovinceDetailRepo.ListConsumeById(cardId, id);
		  ExpenseRefundApplicationModel model=new ExpenseRefundApplicationModel();
		  for(int a=0;a<listConsumeById.size();a++) {
			  Object[] consumeById =listConsumeById.get(a);
			  	if(a==1){
			  		break;
			  	}
				String vehiclePlate="";
				String enTime="";
				String enName="";
				String exTime="";
				String exName="";
				Long preBalance=0L;
				Long postBalance=0L;
				Long fee=0L;
				Long refundFee=0L;
				Integer status=0;
				if(consumeById.length>0) {
					if(consumeById[0]!=null) {
						id=consumeById[0].toString();
					}
					if(consumeById[2]!=null) {
						vehiclePlate=consumeById[2].toString();
					}
					if(consumeById[3]!=null) {
						enTime=consumeById[3].toString();
					}
					if(consumeById[4]!=null) {
						enName=consumeById[4].toString();
					}
					if(consumeById[5]!=null) {
						exTime=consumeById[5].toString();
					}
					if(consumeById[6]!=null) {
						exName=consumeById[6].toString();
					}
					if(consumeById[7]!=null) {
						preBalance=Long.valueOf(consumeById[7].toString());
					}
					if(consumeById[8]!=null) {
						fee=Long.valueOf(consumeById[8].toString());
					}
					if(consumeById[9]!=null) {
						postBalance=Long.valueOf(consumeById[9].toString());
					}
					if(consumeById[10]!=null) {
						status=Integer.valueOf(consumeById[10].toString());
					}
					if(consumeById[11]!=null) {
						refundFee=Long.valueOf(consumeById[11].toString());
					}
					model.setId(id);
					model.setCardId(cardId);
					model.setVehiclePlate(vehiclePlate);
					model.setEnTime(enTime);
					model.setEnName(enName);
					model.setExTime(exTime);
					model.setExName(exName);
					model.setPreBalance(preBalance);
					model.setFee(fee);
					model.setPostBalance(postBalance);
					model.setRefundFee(refundFee);
					model.setStatus(status);
				}
		  }
		  ExpenseRefundApplicationRequest request = new ExpenseRefundApplicationRequest(); 
		  CardRefundDetail cardRefundDetail = cardRefundDetailRepo.findCardRefundDetailById(id); 
		  if(cardRefundDetail!=null&&cardRefundDetail.getStatus()!=0) {
				throw new ManagerException("此流水已申请审核!");
			}
			CardInfo cardInfo= cardInfoRepo.findByCardId(cardId);
			if(cardInfo==null) {
				throw new ManagerException("未查到对应的卡信息,请联系管理员!");
			}
			if(cardInfo.getStatus()==4 || cardInfo.getStatus()==5) {  //如果此卡为注销或者预注销状态,将银行卡输入框开放出来
				request.setCardType(2);
			}else {
				if(CardType.isAccountCardType(cardInfo.getCardType())) {
					request.setCardType(2);	
				}else {
					request.setCardType(1);
				}
			}
		  VehicleInfo vehicleInfo = vehicleInfoRepo.findByVehicleId(cardInfo.getVehicleId()); 
		  if (vehicleInfo != null) {
		  request.setVehiclePlate(vehicleInfo.getVehiclePlate());
		  request.setVehiclePlateColor(vehicleInfo.getVehiclePlateColor()); 
		  }
		  request.setTradeTime(model.getExTime());
		  request.setLoginId(model.getId());
		  request.setLoginCardId(cardId);
		  request.setTradeFee(model.getFee()/100);// 展示给页面为元 
		  return  request;
		 }

//	@Override
//	public ExpenseRefundApplicationRequest findAccountDetailById(String id) throws ManagerException {
//		ExpenseRefundApplicationRequest request = new ExpenseRefundApplicationRequest();
//		AccountCardDetail accountCardDetail = accountCardDetailRepo.findAccountCardDetailById(id);
//		if (accountCardDetail == null) {
//			throw new ManagerException("未查到对应的交易流水信息,请联系管理员!");
//		}
//		request.setId(accountCardDetail.getId());
//		return request;
//	}

	@Override
	public ExpenseRefundApplicationResponse save(ExpenseRefundApplicationRequest request, User user)
			throws ManagerException {
		CardInfo cardInfo = cardInfoRepo.findByCardId(request.getCardId());
		ExpenseRefundApplicationResponse response = new ExpenseRefundApplicationResponse();
		response=request.validate(request, cardInfo);
		if(response.getStatus()==-1) {
			return response;
		}
		CardRefundDetail cardRefundDetail = new CardRefundDetail();
		cardRefundDetail.setCardId(request.getCardId());
		cardRefundDetail.setUserId(user.getStaff().getStaffId());
		cardRefundDetail.setVehiclePlate(request.getVehiclePlate());
		cardRefundDetail.setVehiclePlateColor(request.getVehiclePlateColor());
		cardRefundDetail.setRefundType(request.getRefundType());
		cardRefundDetail.setBank(request.getBank());
		cardRefundDetail.setBankCard(request.getBankCard());
		cardRefundDetail.setBankUserName(request.getBankUserName());
		cardRefundDetail.setPhone(request.getPhone());
		cardRefundDetail.setCreateTime(CssUtil.getNowDateTimeStrWithT());
		cardRefundDetail.setTradeTime(request.getTradeTime());
		cardRefundDetail.setDetailedDescription(request.getDetailedDescription());
		cardRefundDetail.setStatus(AuditStatusType.PEDINGREFUNDAUDIT.getCode());
		cardRefundDetail.setId(request.getId());
		cardRefundDetail.setTradeFee(request.getTradeFee());
		cardRefundDetailRepo.save(cardRefundDetail);
		response.setMessage("消费退费申请入库成功!,请选择待受理查询后点击上传图片按钮!");
		response.setStatus(1);
		return response;
	}

	public ExpenseRefundApplicationResponse saveFile(MultipartFile file, String id,String status) throws ManagerException {
		ExpenseRefundApplicationResponse response = new ExpenseRefundApplicationResponse();
		CardRefundDetail cardRefundDetail = cardRefundDetailRepo.findCardRefundDetailById(id);
		String parentDirRelativePath="";
		String fileAbsolutePath="";
		if("-1".equals(status)) {
			 parentDirRelativePath = MyFinals.EXPENSE_REFUND_APPLY_FILE + File.separator + id  ;
			 fileAbsolutePath = FileWriter.savePic(file, cardRefundDetail, parentDirRelativePath);
			 cardRefundDetail.setFilePath(fileAbsolutePath);
			 cardRefundDetail.setFileName(FileWriter.generateFileName(cardRefundDetail,file));
		}else {
			 parentDirRelativePath = MyFinals.EXPENSE_REFUND_FILE + File.separator + id  ;
			 fileAbsolutePath = FileWriter.savePic(file, cardRefundDetail, parentDirRelativePath);
			 cardRefundDetail.setRefundFilePath(fileAbsolutePath);
			 cardRefundDetail.setRefundFileName(FileWriter.generateFileName(cardRefundDetail,file));
			 cardRefundDetail.setStatus(7);
		}
		cardRefundDetailRepo.save(cardRefundDetail);
		response.setMessage("上传成功");
		return response;
	}

	@Override
	public ExpenseRefundApplicationResponse select(ExpenseRefundApplicationRequest request) throws ManagerException {
		ExpenseRefundApplicationResponse response = new ExpenseRefundApplicationResponse();
		CardRefundDetail cardRefundDetail = cardRefundDetailRepo.findCardRefundDetailById(request.getId());
		if (cardRefundDetail == null) {
			response.setMessage("消费退费申请数据异常,请联系管理员");
		}
		response.setId(cardRefundDetail.getId());
		if (cardRefundDetail.getRefundFee() != null) {
			response.setRefundFee(cardRefundDetail.getRefundFee() / 100);
		}

		response.setCardId(cardRefundDetail.getCardId());
		response.setStatus(cardRefundDetail.getStatus());
		response.setTradeFee(cardRefundDetail.getTradeFee());
		response.setCreateTime(cardRefundDetail.getCreateTime());
		response.setVehiclePlate(cardRefundDetail.getVehiclePlate());
		response.setVehiclePlateColor(cardRefundDetail.getVehiclePlateColor());
		if (cardRefundDetail.getFileName() != null && cardRefundDetail.getFilePath() != null) {
			response.setFileName(cardRefundDetail.getFileName());
			response.setFilePath(cardRefundDetail.getFilePath());
		}
		response.setUserId(cardRefundDetail.getUserId());
		response.setDetailedDescription(cardRefundDetail.getDetailedDescription());
		response.setRefundDescription(cardRefundDetail.getRefundDescription());
		response.setRefundType(cardRefundDetail.getRefundType());
		if (cardRefundDetail.getBankCard() != null) {
			response.setBankCard(cardRefundDetail.getBankCard());
			response.setBank(cardRefundDetail.getBank());
			response.setBankUserName(cardRefundDetail.getBankUserName());
			response.setPhone(cardRefundDetail.getPhone());
		}
		return response;
	}

	

	@Override
	public ExpenseRefundApplicationResponse trueFinance(ExpenseRefundApplicationRequest request, User user)
			throws ManagerException {
		ExpenseRefundApplicationResponse response = new ExpenseRefundApplicationResponse();
		CardRefundDetail cardRefundDetail = cardRefundDetailRepo.findCardRefundDetailById(request.getId());
		cardRefundDetail.setStatus(7);// 已银行卡退费
		cardRefundDetailRepo.save(cardRefundDetail);
		response.setStatus(1);
		response.setMessage("确认银行退费成功!");
		return response;
	}


	public HttpServletResponse download(ExpenseRefundApplicationRequest queryModel, HttpServletResponse response,
			HttpServletRequest request) throws FileNotFoundException {
		CardRefundDetail cardRefundDetail = cardRefundDetailRepo.findCardRefundDetailById(queryModel.getId());
		// 下载本地文件
		String fileName = ""; // 文件的默认保存名
		InputStream inStream = null;
		if(-1==queryModel.getStatus()) {
			fileName=cardRefundDetail.getFileName();
			// 读到流中
			inStream = new FileInputStream("" + cardRefundDetail.getFilePath() + fileName);// 文件的存放路径
		}else {
			fileName=cardRefundDetail.getRefundFileName();
			inStream = new FileInputStream("" + cardRefundDetail.getRefundFilePath() + fileName);// 文件的存放路径
		}
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/*
	 * @Override public void getFile(String id, HttpSession session,
	 * HttpServletResponse response) throws IOException { CardRefundDetail
	 * cardRefundDetail=cardRefundDetailRepo.findCardRefundDetailById(id);
	 * if(cardRefundDetail.getFilePath()==null) { return; } String
	 * fileurl=cardRefundDetail.getFilePath(); String basePath =
	 * session.getServletContext().getRealPath("/"); // 获取基本路径 if (null!=fileurl &&
	 * !fileurl.equals("")) { 第一步:根据文件路径获取文件 File file = new File(basePath +
	 * fileurl); if (file.exists()) { // 文件存在 第二步：根据已存在的文件，创建文件输入流 InputStream
	 * inputStream = new BufferedInputStream(new FileInputStream(file));
	 * 第三步：创建缓冲区，大小为流的最大字符数 byte[] buffer = new byte[inputStream.available()]; //
	 * int available() 返回值为流中尚未读取的字节的数量 第四步：从文件输入流读字节流到缓冲区 inputStream.read(buffer);
	 * 第五步： 关闭输入流 inputStream.close();
	 * 
	 * String fileName = file.getName();// 获取文件名 response.reset();
	 * response.addHeader("Content-Disposition", "attachment;filename=" + new
	 * String(fileName.getBytes("utf-8"), "iso8859-1"));
	 * response.addHeader("Content-Length", "" + file.length());
	 * 
	 * 第六步：创建文件输出流 OutputStream outputStream = new
	 * BufferedOutputStream(response.getOutputStream());
	 * response.setContentType("application/octet-stream"); 第七步：把缓冲区的内容写入文件输出流
	 * outputStream.write(buffer); 第八步：刷空输出流，并输出所有被缓存的字节 outputStream.flush();
	 * 第九步：关闭输出流 outputStream.close();
	 * 
	 * } //end if (file.exists()) } else { return; }
	 * 
	 * }
	 */
	public String downFile(String fileName, String fileFullPath, HttpServletResponse response) throws ManagerException {
		File file = new File(fileFullPath);
		FileInputStream fileInputStream = null;
		try {
			if (file.exists()) {
				fileInputStream = new FileInputStream(file);// 读取文件流
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment filename=" + fileName);
				byte[] buffer = new byte[4096];
				int len;
				ServletOutputStream outputStream = response.getOutputStream();
				while ((len = fileInputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
				return null;
			}
		} catch (Exception e) {
			throw new ManagerException();
		}

		return fileFullPath;

	}
	
	


}
