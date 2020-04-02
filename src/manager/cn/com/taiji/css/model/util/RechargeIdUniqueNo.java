/**
 * @Title RechargeIdUniqueNo.java
 * @Package cn.com.taiji.dsi.manager.util
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月21日 下午8:34:47
 * @version V1.0
 */
package cn.com.taiji.css.model.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName RechargeIdUniqueNo
 * @Description TODO
 * @author yaonl
 * @date 2018年07月21日 20:34:47
 * @E_mail yaonanlin@163.com
 */
public class RechargeIdUniqueNo {
	private static Object lock = new Object();
	private static Integer RECHARGE_ID_SERIALNO_MIN = 1;
	private static Integer RECHARGE_ID_SERIALNO_MAX = 999;
	private static AtomicInteger RECHARGEID_ATOMICINTEGER = new AtomicInteger(RECHARGE_ID_SERIALNO_MIN);
	
	public static String getSerialNo(){
		synchronized (lock) {
			int serialNo = RECHARGEID_ATOMICINTEGER.getAndIncrement();
			if(serialNo == RECHARGE_ID_SERIALNO_MAX){
				RECHARGEID_ATOMICINTEGER.set(RECHARGE_ID_SERIALNO_MIN);
			}
			String serialNoStr = String.valueOf(serialNo);
			if(serialNoStr.length()==1) serialNoStr = "0"+serialNoStr;
			return serialNoStr;
		}
	}
}

