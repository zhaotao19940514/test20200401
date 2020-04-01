
package cn.com.taiji.css.manager.customerservice.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.customerservice.report.CountModel;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.repo.jpa.CardInfoTemporaryRepo;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;
import cn.com.taiji.qtk.repo.jpa.DailySheetRepo;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;


@Service
public class FinancialstatementManagerImpl extends AbstractDsiCommManager implements FinancialstatementManager{

	
	@Autowired
	private DailySheetRepo dailySheetRepo;
	
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	@Autowired
	private CardInfoTemporaryRepo cardInfoTemporaryRepo;
	
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	@Override
	public List<CountModel> page(CountModel queryModel, HttpServletRequest request) throws ManagerException {
		if(queryModel.getStartDate()==null && queryModel.getEndDate()==null)
			return null;
				List<CountModel> modelList = Lists.newArrayList();
				User user=LoginHelper.getLoginUser(request);
				String channelId=user.getStaff().getServiceHallId();
				ServiceHall serviceHall=serviceHallRepo.findByServiceHallId(channelId);
				if(serviceHall==null) {
					return null;
				}
				
				Integer sta= Integer.valueOf(queryModel.getStartDate());
				Integer end= Integer.valueOf(queryModel.getEndDate());
				Long countCardLiuChangYun =0L;
				if(channelId.equals("5201018803301030001") && sta>=20190101 && end<=20190331) {//如果为世纪恒通 且日期在1月到3月间  则进此统计方法
					String countCard = cardInfoTemporaryRepo.countCard(queryModel.getStartDate(),queryModel.getEndDate());
					if(countCard!=null) {
						countCardLiuChangYun = Long.valueOf(countCard);
					}
				}
				//网点开卡数
				List<Object[]> countOpenCard = null ;
				if(channelId.equals("5201018803301030001") && sta>=20190101 && sta<=20190331 && end >20190331 ) {
					//开始时间在1-3月份内  结束时间在3月份外   则将1-3月统计临时表    3月份以外的按原sql查询，并进行相加
					String countCard = cardInfoTemporaryRepo.countCard(queryModel.getStartDate(),"20190331");
					if(countCard!=null) {
						countCardLiuChangYun = Long.valueOf(countCard);
					}
					countOpenCard =dailySheetRepo.findCountOpenCardByChannelId("20190401", queryModel.getEndDate(),channelId);
				}else {
				    countOpenCard =dailySheetRepo.findCountOpenCardByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
				}
	        	//网点开OBU数
	        	List<Object[]> countOpenObu  =dailySheetRepo.findCountOpenObuByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
	        	//网点圈存充值金额
	        	List<Object[]> sumChargeFee  =dailySheetRepo.findSumCardChargeByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
	        	//网点圈存修复金额
	        	List<Object[]> sumChargeFeeFix  =dailySheetRepo.findSumCardChargeFixByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
	        	//网点账户充值金额
	        	List<Object[]> sumAccountCharge  =dailySheetRepo.findCountAccountChargeGrouopByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
	        	//网点冲正金额
				List<Object[]> sumAccountReversal  =dailySheetRepo.findCountAccountReversalGrouopByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
				//网点消费金额
				List<Object[]> sumAccountConsume   =dailySheetRepo.findCountAccountConsumeGrouopByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
				//网点圈存冲正退款金额
				List<Object[]> sumAccountSupplyCharge   =dailySheetRepo.findCountAccountSupplyChargeGrouopByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
				//网点卡设备费
	        	List<Object[]> countCardFee  =dailySheetRepo.findSumOpenCardByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
	        	//网点签设备费
				List<Object[]> countObuFee  =dailySheetRepo.findSumOpenObuByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
				//网点换卡费
				List<Object[]> countReplaceCard  =dailySheetRepo.findCountReplaceCardGroupByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
				//网点退费金额
				List<Object[]> sumCancelMoney  =dailySheetRepo.findSumCancelGrouopByChannelId(queryModel.getStartDate(), queryModel.getEndDate(),channelId);
				
				CountModel countModelNew=new CountModel();
				countModelNew.setStartDate(queryModel.getStartDate());
				countModelNew.setEndDate(queryModel.getEndDate());
				countModelNew.setChannelId(channelId);
				String channelName= serviceHall.getName();
            	countModelNew.setServiceHallName(channelName);
	                for(Object[] openCard : countOpenCard) {
	                	if(openCard.length>0) {
	                		String channelName1=openCard[2].toString();
	                		if(channelName.equals(channelName1)) {
	                			if(channelId.equals("5201018803301030001")  && sta>=20190101 && end<=20190331) {
	                 				//  网点为世纪恒通  且日期在1-3月份内  直接以此数据为标准
	                 				countModelNew.setCardCount(Long.valueOf(countCardLiuChangYun));
	                 			}else {
	                 				countModelNew.setCardCount(Long.valueOf(openCard[0].toString())+countCardLiuChangYun);
	                 			}
	                		}
	                	}
	                }
	                for(Object[] openObu : countOpenObu) {
	                	if(openObu.length>0) {
	                		String channelName1=openObu[2].toString();
	                		if(channelName.equals(channelName1)) {
	                			if(channelId.equals("5201018803301030001")  && sta>=20190101 && end<=20190331) {
	                 				//  网点为世纪恒通  且日期在1-3月份内  直接以此数据为标准
	                 				countModelNew.setObuCount(Long.valueOf(countCardLiuChangYun));
	                 			}else {
	                 				countModelNew.setObuCount(Long.valueOf(openObu[0].toString())+countCardLiuChangYun);
	                 			}
	                			
	                		}
	                	}
	                }
	                for(Object[] chargeFee : sumChargeFee) {
	                	if(chargeFee.length>0) {
	                		String channelName1=chargeFee[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setAmount(Long.valueOf(chargeFee[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] chargeFeeFix : sumChargeFeeFix) {
	                	if(chargeFeeFix.length>0) {
	                		String channelName1=chargeFeeFix[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setAmountFix(Long.valueOf(chargeFeeFix[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] accountCharge : sumAccountCharge) {
	                	String channelName1=accountCharge[1].toString();
	                	if(accountCharge.length>0) {
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setAccountAmount(Long.valueOf(accountCharge[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] accountReversal : sumAccountReversal) {
        				if(accountReversal.length>0) {
        					String channelName1=accountReversal[1].toString();
                    		if(channelName.equals(channelName1)) {
                    			countModelNew.setAccountReversal(Long.valueOf(accountReversal[0].toString()));
                    		}
        				}
        			}
	                for(Object[] accountConsume : sumAccountConsume) {
        				if(accountConsume.length>0) {
        					String channelName1=accountConsume[1].toString();
                    		if(channelName.equals(channelName1)) {
                    			countModelNew.setAccountConsume(Long.valueOf(accountConsume[0].toString()));
                    		}
        				}
        			}
	                for(Object[] supplyCharge : sumAccountSupplyCharge) {
        				if(supplyCharge.length>0) {
        					String channelName1=supplyCharge[1].toString();
                    		if(channelName.equals(channelName1)) {
                    			countModelNew.setAccountSupplyCharge(Long.valueOf(supplyCharge[0].toString()));
                    		}
        				}
        			}
	                for(Object[] cardFee : countCardFee) {
	                	if(cardFee.length>0) {
	                		String channelName1=cardFee[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setCardFee(Long.valueOf(cardFee[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] obuFee : countObuFee) {
	                	if(obuFee.length>0) {
	                		String channelName1=obuFee[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setObuFee(Long.valueOf(obuFee[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] replaceCard : countReplaceCard) {
	                	if(replaceCard.length>0) {
	                		String channelName1=replaceCard[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setCardReplacementCount(Long.valueOf(replaceCard[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] cancelMoney : sumCancelMoney) {
	                	if(cancelMoney.length>0) {
	                		String channelName1=cancelMoney[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setRefund(Long.valueOf(cancelMoney[0].toString()));
	                		}
	                	}
	                }
	                modelList.add(countModelNew);
				
		return modelList;
	}


	@Override
	public List<CountModel> findByChannelId(CountModel querModel) throws ManagerException {
		List<Object[]> chargeDetailList=chargeDetailRepo.findChargeFixByChannelId(querModel.getChannelId(), querModel.getStartDate(), querModel.getEndDate());
		List<CountModel> ChargeDetails= Lists.newArrayList();
		for(Object[] chargeDetail : chargeDetailList) {
			CountModel countModel=new CountModel();
			if(chargeDetail.length>0) {
				String cardId=chargeDetail[0].toString();
				Long rechargeAmount=Long.valueOf(chargeDetail[1].toString());
				countModel.setCardId(cardId);
				countModel.setRechargeAmount(rechargeAmount);
			}
			ChargeDetails.add(countModel);
		}
		return ChargeDetails;
	}


	@Override
	public List<CountModel> findByAgencyId(CountModel queryModel,HttpServletRequest request) throws ManagerException {
		if(queryModel.getStartDate()==null )
			return null;
		String agencyId=null;
		if(queryModel.getAgencyIds()==null || queryModel.getAgencyIds().equals("")) {
			
			User user=LoginHelper.getLoginUser(request);
			agencyId=user.getStaff().getServiceHall().getAgencyId();
		}else {
			agencyId=queryModel.getAgencyIds();
		}
		List<ServiceHall> listByAgentId = serviceHallRepo.listByAgentId(agencyId);
		if(listByAgentId==null || listByAgentId.size()<=0) {
			throw new ManagerException("此合作机构下无任何网点存在！");
		}
		List<CountModel> modelList = Lists.newArrayList();
		Integer sta= Integer.valueOf(queryModel.getStartDate());
		Long countCardLiuChangYun =0L;
		if(agencyId.equals("52010188033") && sta>=20190101 && sta<=20190331) {//如果为世纪恒通 且日期在1月到3月间  则进此统计方法
			String countCard = cardInfoTemporaryRepo.countCard(queryModel.getStartDate());
			if(countCard!=null) {
				countCardLiuChangYun = Long.valueOf(countCard);
			}
		}
		//机构开卡
		List<Object[]> countOpenCard =dailySheetRepo.findCountOpenCardByAgencyId(queryModel.getStartDate(),agencyId);
		//机构开签  
		List<Object[]> countOpenObu =dailySheetRepo.findCountOpenObuByAgencyId(queryModel.getStartDate(),agencyId);
		//机构圈存充值金额(以status 区分半条   tradeType 区分账户还是现金)
		List<Object[]> sumChargeFee  =dailySheetRepo.findSumCardChargeByAgencyId(queryModel.getStartDate(),agencyId);
		//机构卡设备数量
		List<Object[]> countCard =dailySheetRepo.findCountReplaceCardGroupByAgencyId(queryModel.getStartDate(),agencyId);
		//机构卡设备费金额
		List<Object[]> sumCardFee =dailySheetRepo.findSumOpenCardByAgencyId(queryModel.getStartDate(),agencyId);
		//机构签设备数量
		List<Object[]> countObu  =dailySheetRepo.findCountReplaceObuGroupByAgencyId(queryModel.getStartDate(),agencyId);
		//机构签设备费金额
		List<Object[]> sumOBUFee  =dailySheetRepo.findSumOpenObuByAgencyId(queryModel.getStartDate(),agencyId);
		//机构账户充值金额
		List<Object[]> accountCharge  =dailySheetRepo.findCountAccountChargeByAgencyId(queryModel.getStartDate(),agencyId);
		//机构账户冲正金额
		List<Object[]> accountReversal  =dailySheetRepo.findCountAccountReversalByAgencyId(queryModel.getStartDate(),agencyId);
		//机构注销退费金额
		List<Object[]> sumCancelRefund  =dailySheetRepo.findCountCancelRefundGrouopByAgencyId(queryModel.getStartDate(),agencyId);
		for(ServiceHall serviceHall:listByAgentId) {
			CountModel countModelNew=new CountModel();
			countModelNew.setServiceHallName(serviceHall.getName());
			String serviceHallId=serviceHall.getServiceHallId();
			
			 for(Object[] openCard : countOpenCard) {
             	if(openCard.length>0) {
             		String channelId=openCard[0].toString();
             		if(serviceHallId.equals(channelId)) {
             			if(channelId.equals("5201018803301030001")  && sta>=20190101 && sta<=20190331) {
             				//  网点为世纪恒通  且日期在1-3月份内  直接以此数据为标准
             				countModelNew.setCardCount(Long.valueOf(countCardLiuChangYun));
             			}else {
             				countModelNew.setCardCount(Long.valueOf(openCard[2].toString()));
             			}
             		}
             	}
             }
			 for(Object[] openObu : countOpenObu) {
	             	if(openObu.length>0) {
	             		String channelId=openObu[0].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setObuCount(Long.valueOf(openObu[2].toString()));
             		}
             	}
             }	
			 for(Object[] chargeFee : sumChargeFee) {
	             	if(chargeFee.length>0) {
	             		String channelId=chargeFee[0].toString();
	             		String status=chargeFee[3].toString();
	             		String tradeType=chargeFee[4].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			if("3".equals(tradeType)) {
	             				countModelNew.setAccountConsume(Long.valueOf(chargeFee[2].toString()));
                			} else if("0".equals(status)) {
                				countModelNew.setAmountFix(Long.valueOf(chargeFee[2].toString()));
                			}else if("1".equals(status)){
                				countModelNew.setAmount(Long.valueOf(chargeFee[2].toString()));
                			}
	             		}
	             	}
	             }
			 for(Object[] Card : countCard) {
	             	if(Card.length>0) {
	             		String channelId=Card[0].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setCardFee(Long.valueOf(Card[2].toString()));
             		}
             	}
             }
			 for(Object[] cardFee : sumCardFee) {
	             	if(cardFee.length>0) {
	             		String channelId=cardFee[0].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setCardFee30(Long.valueOf(cardFee[2].toString()));
          		    }
                }
			 }
			 for(Object[] obu : countObu) {
	             	if(obu.length>0) {
	             		String channelId=obu[0].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setObuFee(Long.valueOf(obu[2].toString()));
       		        }
                }
			 }
			 for(Object[] obuFee : sumOBUFee) {
	             	if(obuFee.length>0) {
	             		String channelId=obuFee[0].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setObuFee200(Long.valueOf(obuFee[2].toString()));
    		        }
	             }
			 }
			 for(Object[] charge : accountCharge) {
	             	if(charge.length>0) {
	             		String channelId=charge[0].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setAccountAmount(Long.valueOf(charge[2].toString()));
    		        }
             }
			 }
			 for(Object[] reversal : accountReversal) {
	             	if(reversal.length>0) {
	             		String channelId=reversal[0].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setAccountReversal(Long.valueOf(reversal[2].toString()));
 		        }
	             }
			 }
			 for(Object[] cancelRefund : sumCancelRefund) {
	             	if(cancelRefund.length>0) {
	             		String channelId=cancelRefund[1].toString();
	             		if(serviceHallId.equals(channelId)) {
	             			countModelNew.setRefund(Long.valueOf(cancelRefund[0].toString()));
		        }
	             }
			 }
			 modelList.add(countModelNew);
		}
		return modelList;
	}


	@Override
	public List<CountModel> findByRegionId(CountModel queryModel) throws ManagerException {
		if(queryModel.getStartDate()==null && queryModel.getEndDate()==null)
			return null;
				List<Object[]>	channelList=dailySheetRepo.findChannelIdByRegionId(queryModel.getRegionId());
				List<CountModel> modelList = Lists.newArrayList();
				//地区开卡数
	        	List<Object[]> countOpenCard =dailySheetRepo.findCountOpenCardDayByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
	        	//地区开OBU数
	        	List<Object[]> countOpenObu  =dailySheetRepo.findCountOpenObuByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
	        	//地区圈存充值金额
	        	List<Object[]> sumChargeFee  =dailySheetRepo.findCountChargeByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
	        	//地区圈存半条流水
	        	List<Object[]> sumChargeFeeFix  =dailySheetRepo.findCountChargeFixByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
	        	//地区账户充值金额
	        	List<Object[]> sumAccountCharge  =dailySheetRepo.findCountAccountChargeGrouopByChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
	        	//账户冲正金额
				List<Object[]> sumAccountReversal  =dailySheetRepo.findCountAccountReversalGrouopByChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
				//地区卡设备费
	        	List<Object[]> countCardFee  =dailySheetRepo.findSumOpenCardByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
	        	//地区签设备费
				List<Object[]> countObuFee  =dailySheetRepo.findSumOpenObuByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
				//地区换卡费
				List<Object[]> countReplaceCard  =dailySheetRepo.findCountReplaceCardGroupByChannelIdAndRegion(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
				//地区注销退费金额
				List<Object[]> countCancelRefund  =dailySheetRepo.findCountCancelRefundGrouopByRegionName(queryModel.getStartDate(), queryModel.getEndDate(),queryModel.getRegionId());
				
				
				for(Object[] channel : channelList) {
					CountModel countModelNew=new CountModel();
					String channelName= channel[1].toString();
	            	countModelNew.setServiceHallName(channelName);
	                for(Object[] openCard : countOpenCard) {
	                	if(openCard.length>0) {
	                		String channelName1=openCard[2].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setCardCount(Long.valueOf(openCard[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] openObu : countOpenObu) {
	                	if(openObu.length>0) {
	                		String channelName1=openObu[2].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setObuCount(Long.valueOf(openObu[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] chargeFee : sumChargeFee) {
	                	if(chargeFee.length>0) {
	                		String channelName1=chargeFee[2].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setAmount(Long.valueOf(chargeFee[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] chargeFeeFix : sumChargeFeeFix) {
	                	if(chargeFeeFix.length>0) {
	                		String channelName1=chargeFeeFix[2].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setAmountFix(Long.valueOf(chargeFeeFix[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] accountCharge : sumAccountCharge) {
	                	if(accountCharge.length>0) {
	                		String channelName1=accountCharge[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setAccountAmount(Long.valueOf(accountCharge[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] accountReversal : sumAccountReversal) {
        				if(accountReversal.length>0) {
        					String channelName1=accountReversal[1].toString();
                    		if(channelName.equals(channelName1)) {
                    			countModelNew.setAccountReversal(Long.valueOf(accountReversal[0].toString()));
                    		}
        				}
        			}
	                for(Object[] cardFee : countCardFee) {
	                	if(cardFee.length>0) {
	                		String channelName1=cardFee[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setCardFee(Long.valueOf(cardFee[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] obuFee : countObuFee) {
	                	if(obuFee.length>0) {
	                		String channelName1=obuFee[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setObuFee(Long.valueOf(obuFee[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] replaceCard : countReplaceCard) {
	                	if(replaceCard.length>0) {
	                		String channelName1=replaceCard[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setCardReplacementCount(Long.valueOf(replaceCard[0].toString()));
	                		}
	                	}
	                }
	                for(Object[] cancelRefund : countCancelRefund) {
	                	if(cancelRefund.length>0) {
	                		String channelName1=cancelRefund[1].toString();
	                		if(channelName.equals(channelName1)) {
	                			countModelNew.setRefund(Long.valueOf(cancelRefund[0].toString()));
	                		}
	                	}
	                }
	                modelList.add(countModelNew);
				}
		return modelList;
	}
	
	
}

