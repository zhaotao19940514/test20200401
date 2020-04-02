/**
 * @Title AbstractDsiCommManager.java
 * @Package cn.com.taiji.css.manager.abstractcomm
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月14日 下午3:52:20
 * @version V1.0
 */
package cn.com.taiji.css.manager.abstractmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.util.CssConstant;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.model.util.RechargeIdUniqueNo;
import cn.com.taiji.dsi.model.comm.protocol.CommQtkRequset;

/**
 * @ClassName AbstractDsiCommManager
 * @Description TODO
 * @author yaonl
 * @date 2018年07月14日 15:52:20
 * @E_mail yaonanlin@163.com
 */
public abstract class AbstractDsiCommManager extends AbstractAgencyCheckManager {
	protected void commSet(CommQtkRequset req,User user){
		req.setTerminalId(CssUtil.TERMINAL_ID);
		req.setAgentId(user.getStaff().getServiceHall().getAgencyId());
		req.setChannelId(user.getStaff().getServiceHallId());
		req.setChannelType(CssConstant.COMM_CHANNEL_TYPE);
		req.setStaffId(user.getStaffId());
		req.setSubmitTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
	};
	public static void main(String[] args) {
		System.out.println("5201010200601130001".length());
	}
	
	protected String generateRechargeId(CommQtkRequset req){
		StringBuilder rechargeId = new StringBuilder();
		rechargeId.append(CssConstant.COMM_CHANNEL_TYPE)//渠道编号第1位固定为2
				.append(req.getChannelId())
				.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
				.append(RechargeIdUniqueNo.getSerialNo());//流水顺序码
		return rechargeId.toString();
	}
}

