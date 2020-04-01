
package cn.com.taiji.css.manager.customerservice.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.report.BusinessstatementResponse;
import cn.com.taiji.css.model.customerservice.report.FinancialstatementRequest;
import cn.com.taiji.qtk.entity.Businessstatement;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.repo.jpa.BusinessstatementRepo;
import cn.com.taiji.qtk.repo.jpa.FinancialstatementRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;


@Service
public class BusinessstatementManagerImpl extends AbstractDsiCommManager implements BusinessstatementManager{

	
	@Autowired
	private BusinessstatementRepo businessstatementRepo;
	@Autowired
	private FinancialstatementRepo financialstatementRepo;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	
	@Override
	public BusinessstatementResponse  queryPage(FinancialstatementRequest queryModel,HttpServletRequest request) throws ManagerException {
		List<Businessstatement> fins= businessstatementRepo.findDataByAgencyId(queryModel.getAgencyId(), queryModel.getStartTime(), queryModel.getEndTime());
		if(fins.size()==0) {//数据库内没有这笔查询记录，新增
		//充值金额
			//充值金额   圈存
			Date start=new Date();
			Date end=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				start=sdf.parse(queryModel.getStartTime());
				end  =sdf.parse(queryModel.getEndTime());
			} catch (ParseException e) {
				e.printStackTrace();
				throw new ManagerException(""+e);
			}
			Calendar startTime = Calendar.getInstance() ;
			Calendar endTime = Calendar.getInstance() ;
			startTime.setTime(start);
			endTime.setTime(end);
		List<Object[]> a=financialstatementRepo.findChargeDetailByCardId(queryModel.getAgencyId(), startTime, endTime);
		/*List<Object[]> a=financialstatementRepo.findChargeFeeByCardId(queryModel.getAgencyId(), queryModel.getStartTime(), queryModel.getEndTime());*/
		System.out.println("圈存记录统计成功！");
		//开卡数
		List<Object[]> d=businessstatementRepo.findCountCardByAgencyId(queryModel.getAgencyId(), startTime, endTime);
		//工本费
		
		//退款数
		List<Object[]> b=businessstatementRepo.findCountObuByAgencyId(queryModel.getAgencyId(), startTime, endTime);
		//退款金额
		List<Object[]> c=financialstatementRepo.findRefundsFeeByCardId(queryModel.getAgencyId(), startTime, endTime);
		for(int i=0;i<a.size();i++) {
			Businessstatement businessstatement= new Businessstatement();
			businessstatement.setStartTime(queryModel.getStartTime());
			businessstatement.setEndTime(queryModel.getEndTime());
			Object[] p=a.get(i);
			for(int j=0;j<p.length;j++) {
				String rep=p[j].toString();
				System.out.println(rep);
				if(j==0) {
					businessstatement.setSumchargeFee(Long.parseLong(rep));
				}
				if(j==1) {
					businessstatement.setChannleId(rep);
					ServiceHall sh= serviceHallRepo.findByServiceHallId(rep);
					if(sh!=null) {
						businessstatement.setChannleName(sh.getName());
					}
					businessstatement.setAgencyId(rep.substring(0, 11));
				}
			}
			businessstatementRepo.save(businessstatement);
		}
		
		for(int i=0;i<b.size();i++) {
			Businessstatement businessstatement= new Businessstatement();
			Object[] p=b.get(i);
			for(int j=0;j<p.length;j++) {
				if(p[j]==null) {
					p[j]="null";
				}
				String rep=p[j].toString();
				System.out.println(rep);
				if(j==0) {
					businessstatement=	businessstatementRepo.findDataByChannleId(rep, queryModel.getStartTime(), queryModel.getEndTime());
					if(businessstatement==null) {
						businessstatement= new Businessstatement();
						businessstatement.setChannleId(rep);
						ServiceHall sh= serviceHallRepo.findByServiceHallId(rep);
						if(sh!=null) {
							businessstatement.setChannleName(sh.getName());
						}
						businessstatement.setAgencyId(rep.substring(0, 11));
						businessstatement.setStartTime(queryModel.getStartTime());
						businessstatement.setEndTime(queryModel.getEndTime());
					}
				}
				if(j==1) {
					businessstatement.setCountObu(Long.parseLong(rep));;
				}
			}
			businessstatementRepo.save(businessstatement);
		}
		
		for(int i=0;i<d.size();i++) {
			Businessstatement businessstatement= new Businessstatement();
			Object[] p=d.get(i);
			for(int j=0;j<p.length;j++) {
				String rep=p[j].toString();
				System.out.println(rep);
				if(j==0) {
					businessstatement=	businessstatementRepo.findDataByChannleId(rep, queryModel.getStartTime(), queryModel.getEndTime());
					if(businessstatement==null) {
						businessstatement= new Businessstatement();
						businessstatement.setChannleId(rep);
						ServiceHall sh= serviceHallRepo.findByServiceHallId(rep);
						if(sh!=null) {
							businessstatement.setChannleName(sh.getName());
						}
						businessstatement.setAgencyId(rep.substring(0, 11));
						businessstatement.setStartTime(queryModel.getStartTime());
						businessstatement.setEndTime(queryModel.getEndTime());
					}
				}
				if(j==1) {
					businessstatement.setCountCard(Long.parseLong(rep));;
				}
			}
			businessstatementRepo.save(businessstatement);
		}
		
		for(int i=0;i<c.size();i++) {
			Businessstatement businessstatement= new Businessstatement();
			Object[] p=c.get(i);
			for(int j=0;j<p.length;j++) {
				String rep=p[j].toString();
				System.out.println(rep);
				if(j==0) {
					businessstatement=	businessstatementRepo.findDataByChannleId(rep, queryModel.getStartTime(), queryModel.getEndTime());
					if(businessstatement==null) {
						businessstatement= new Businessstatement();
						businessstatement.setChannleId(rep);
						ServiceHall sh= serviceHallRepo.findByServiceHallId(rep);
						if(sh!=null) {
							businessstatement.setChannleName(sh.getName());
						}
						businessstatement.setAgencyId(rep.substring(0, 11));
						businessstatement.setStartTime(queryModel.getStartTime());
						businessstatement.setEndTime(queryModel.getEndTime());
					}
				}
				if(j==1) {
					businessstatement.setSumrefundsFee(Long.parseLong(rep));
				}
			}
			businessstatementRepo.save(businessstatement);
		}
	
		fins= businessstatementRepo.findDataByAgencyId(queryModel.getAgencyId(), queryModel.getStartTime(), queryModel.getEndTime());
		}
		BusinessstatementResponse businessstatementResponse=new BusinessstatementResponse();
		businessstatementResponse.setLists(fins);
		return businessstatementResponse;
		
	}
	
	
}

