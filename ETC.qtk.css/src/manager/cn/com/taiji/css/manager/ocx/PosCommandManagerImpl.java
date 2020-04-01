/**
 * @Title PosCommandManagerImpl.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月25日 下午5:20:55
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.Files;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.pub.ProjectEnv;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.manager.util.FileWriter;
import cn.com.taiji.css.model.ocx.PosBaseModel;
import cn.com.taiji.css.model.ocx.PosBaseResponse;
import cn.com.taiji.css.model.ocx.PosConfirmRequest;
import cn.com.taiji.css.model.ocx.PosConfirmResponse;
import cn.com.taiji.css.model.ocx.PosConsumeCommandResponse;
import cn.com.taiji.qtk.entity.CssPosTradeDetail;
import cn.com.taiji.qtk.entity.dict.CssPosTradeType;
import cn.com.taiji.qtk.repo.jpa.CssPosTradeDetailRepo;

/**
 * @ClassName PosCommandManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年07月25日 17:20:55
 * @E_mail yaonanlin@163.com
 */
@Service
public class PosCommandManagerImpl extends AbstractManager implements PosCommandManager {
	@Autowired
	private CssPosTradeDetailRepo posTradeDetailRepo;
	@Transactional
	@Override
	public PosBaseResponse getCommand(PosBaseModel requestModel,User user,CssPosTradeType specifyTradeType) throws ManagerException {
		// 若有强制指定类型specifyTradeType 则使用指定类型
		if(specifyTradeType!=null){
			requestModel.setPosTradeType(specifyTradeType);
		}
		requestModel.setTransType(requestModel.getPosTradeType().getCode());
		// 参数校验 
		requestModel.valid();
		// 根据参数生成command
		String command = PosUtil.getCommand(requestModel);
		// 返回指令
		return new PosConsumeCommandResponse(command);
	}

	@Transactional
	@Override
	public PosConfirmResponse consumeConfirm(PosConfirmRequest requestModel,User user) throws ManagerException {
		requestModel.setPosTradeType(CssPosTradeType.CONSUME);
		// 参数校验
		requestModel.valid();
		// 业务校验
		PosConfirmResponse response = confirmBusinessValid(requestModel);
		if(response!=null){
			return response;
		}else{
			// 更新组装并保存detail 生成确认结果 
			response = assembleDetailWileConfirm(requestModel,user);
		}
		// 返回确认结果
		return response;
	}

	private PosConfirmResponse confirmBusinessValid(PosConfirmRequest model)
			throws ManagerException {
		if(model.getPosTradeType()!=CssPosTradeType.CONSUME){
			throw new ManagerException("非消费交易无需确认");
		}
		String[] split = model.getPosResponse().split(CssConstant.POS_COMMAND_SEPERATOR);
		if("05".equals(split[0]) && !"00".equals(split[10])){
			// split[0] 05 消费交易   
			// split[10] 00为成功
			PosConfirmResponse response = new PosConfirmResponse(false);
			response.setMessage(split[11]);
			return response;
		}
		return null;
	}
	public static void main(String[] args) {
		String[] split = "05,,,,,,,,,,FF,交易被取消,,,,66666,99999".split(CssConstant.POS_COMMAND_SEPERATOR);
		boolean flag = "05".equals(split[0]) && !"00".equals(split[10]);
		System.out.println(flag);
	}
	private PosConfirmResponse assembleDetailWileConfirm(PosConfirmRequest requestModel,User user) throws ManagerException {
		// pos机指令返回值转Model
		PosBaseModel posResponseModel = PosUtil.toModel(requestModel.getPosResponse());
		// 组装pos返回字段至detail
		CssPosTradeDetail detail = new CssPosTradeDetail();
		System.out.println(requestModel.toJson());
		assembleDetailWithPosResponseModel(requestModel, detail, user, posResponseModel);
		boolean tradeSuccess = posResponseModel.tradeSuccess();
		PosConfirmResponse posConfirmResponse = new PosConfirmResponse(tradeSuccess);
		// 生成确认结果
		if(tradeSuccess){
			posConfirmResponse.setMessage("交易成功，金额："+Double.valueOf((double)(detail.getAmount()/100)).toString());
		}else{
			posConfirmResponse.setMessage(posResponseModel.getRspMessage());
		}
		// 保存流水
		try {
			posTradeDetailRepo.persist(detail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("pos交易确认流水保存失败，请重试或联系管理员");
		}
		return posConfirmResponse;
	}

	private void assembleDetailWithPosResponseModel(PosConfirmRequest requestModel, CssPosTradeDetail detail, User user,
			PosBaseModel posResponseModel) {
		detail.setOperatorId(user.getId());
		detail.setCreateTime(CssUtil.getNowDateTimeStrWithT());
		detail.setUpdateTime(CssUtil.getNowDateTimeStrWithT());
		detail.setTradeTime(CssUtil.getNowDateTimeStrWithT());
		detail.setIsCompleted(posResponseModel.tradeSuccess()?CssConstant.TRADE_COMPLETE:CssConstant.TRADE_UNCOMPLETE);
		// 页面传入的值
		detail.setAmount(requestModel.getAmount());
		detail.setObuId(requestModel.getObuId());
		detail.setPosCommand(requestModel.getCommand());
		detail.setPosTradeType(requestModel.getPosTradeType());
		detail.setTransType(requestModel.getPosTradeType().getCode());
		detail.setPosResponse(requestModel.getPosResponse());
		// 解析执行结果得到的值
		detail.setBankCardNo(posResponseModel.getCardNo());
		detail.setReferNo(posResponseModel.getReferNo());
		detail.setTransTime(posResponseModel.getTransTime());
		detail.setTransDate(posResponseModel.getTransDate());
		detail.setReturnCode(posResponseModel.getReturnCode());
		detail.setRspMessage(posResponseModel.getRspMessage());
		detail.setTerminalId(posResponseModel.getTerminalId());
		detail.setMerchantId(posResponseModel.getMerchantId());
		detail.setYlMerchantId(posResponseModel.getYlMerchantId());
		
		detail.setVehiclePlate(requestModel.getVehiclePlate());
		detail.setVehicleColor(requestModel.getVehicleColor());
		detail.setUserIdType(requestModel.getUserIdType());
		detail.setUserPhoneNo(requestModel.getUserPhoneNo());
		detail.setCardId(requestModel.getCardId());
	}

	/*
	 * 生成pos配置文件
	 */
	@Override
	public File posConfigIniCreate(Integer portNum,boolean isIcbc) {
		if(portNum == null){
			throw new MyViolationException("portNum", "请填写端口号");
		}
		String dirPath = FileHelper.getWebappPath().concat(File.separator).concat("posOcx");
		ProjectEnv.mkdirs(dirPath);
		// 获取pos配置文件（ 端口配置已被注释）
		String pathname = null;
		if(isIcbc){
			pathname = dirPath.concat(File.separator).concat("KeeperClientIcbc.ini");
		}else{
			pathname = dirPath.concat(File.separator).concat("KeeperClient.ini");
		}
		File file = new File(pathname);
		// 生成临时文件 并修改端口内容 ComName=COMn(n为portNum)
		String tmpDirPath = dirPath.concat(File.separator).concat("tmp");
		ProjectEnv.mkdirs(tmpDirPath);
		String tmpPath = tmpDirPath.concat(File.separator).concat(UUID.randomUUID().toString()).concat(".ini");
		File tmp = new File(tmpPath);
		try {
			Files.copy(file, tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回下载
		String configStr = "\r\nComName=COM".concat(portNum.toString());
		FileWriter.write(configStr, isIcbc?750l:752l, tmp);// 指定位置写入配置
		return tmp;
	}

}

