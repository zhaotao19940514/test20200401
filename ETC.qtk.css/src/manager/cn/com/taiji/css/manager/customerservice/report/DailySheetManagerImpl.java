
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
import cn.com.taiji.qtk.entity.Region;
import cn.com.taiji.qtk.repo.jpa.DailySheetRepo;
import cn.com.taiji.qtk.repo.jpa.RegionRepo;


@Service
public class DailySheetManagerImpl extends AbstractDsiCommManager implements DailySheetManager{

	
	@Autowired
	private DailySheetRepo dailySheetRepo;
	@Autowired
	private RegionRepo regionRepo;
	@Override
	public List<CountModel> queryPage(CountModel financialstatementRequest,HttpServletRequest request) {
		if(financialstatementRequest.getStartDate()==null && financialstatementRequest.getEndDate()==null)
		return null;
			List<CountModel> modelList = Lists.newArrayList();
			CountModel countModel=new CountModel();
			//地区开卡数
        	List<Object[]> countOpenCard =dailySheetRepo.findCountOpenCardDayGroupByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
        	//地区开OBU数
        	List<Object[]> countOpenObu  =dailySheetRepo.findCountOpenObuGroupByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
        	//地区圈存充值金额
        	List<Object[]> sumChargeFee  =dailySheetRepo.findCountChargeGrouopByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
        	//地区圈存半条流水金额
        	List<Object[]> sumChargeFeeFix  =dailySheetRepo.findCountChargeFixGrouopByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
        	//地区账户充值金额
        	List<Object[]> sumAccountCharge  =dailySheetRepo.findCountAccountChargeGrouopByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
        	//账户冲正金额
			List<Object[]> sumAccountReversal  =dailySheetRepo.findCountAccountReversalGrouopByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
			//账户消费金额
			List<Object[]> sumAccountConsume  =dailySheetRepo.findCountAccountConsumeGrouopByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
			//地区卡设备费
			List<Object[]> countCard  =dailySheetRepo.findSumOpenCardGroupByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
			//地区签设备费
			List<Object[]> countObu  =dailySheetRepo.findSumOpenObuGroupByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
			//地区换卡费
			List<Object[]> countReplaceCard  =dailySheetRepo.findCountReplaceCardGroupByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
			//地区注销退款金额
			List<Object[]> sumCancelRefund  =dailySheetRepo.findCountCancelRefundGrouopByRegionId(financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate());
            for (String regionName : countModel.getRegionNameList()) {
            	CountModel countModelNew=new CountModel();
            	/**
            	 * financialstatementRequest.getStartDate(), financialstatementRequest.getEndDate()
            	 */
            	countModelNew.setStartDate(financialstatementRequest.getStartDate());
				countModelNew.setEndDate(financialstatementRequest.getEndDate());
            	countModelNew.setRegionName(regionName);
                for(Object[] openCard : countOpenCard) {
                	if(openCard.length>0) {
                		String regionName1=openCard[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setCardCount(Long.valueOf(openCard[0].toString()));
                		}
                	}
                }
                for(Object[] openObu : countOpenObu) {
                	if(openObu.length>0) {
                		String regionName1=openObu[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setObuCount(Long.valueOf(openObu[0].toString()));
                		}
                	}
                }
                for(Object[] chargeFee : sumChargeFee) {
                	if(chargeFee.length>0) {
                		String regionName1=chargeFee[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setAmount(Long.valueOf(chargeFee[0].toString()));
                		}
                	}
                }
                for(Object[] chargeFeeFix : sumChargeFeeFix) {
                	if(chargeFeeFix.length>0) {
                		String regionName1=chargeFeeFix[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setAmountFix(Long.valueOf(chargeFeeFix[0].toString()));
                		}
                	}
                }
                for(Object[] accountCharge : sumAccountCharge) {
                	if(accountCharge.length>0) {
                		String regionName1=accountCharge[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setAccountAmount(Long.valueOf(accountCharge[0].toString()));
                		}
                	}
                }
                for(Object[] accountReversal : sumAccountReversal) {
    				if(accountReversal.length>0) {
    					String regionName1=accountReversal[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setAccountReversal(Long.valueOf(accountReversal[0].toString()));
                		}
    				}
    			}
                for(Object[] accountConsume: sumAccountConsume) {
                	if(accountConsume.length>0) {
                		String regionName1=accountConsume[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setAccountConsume(Long.valueOf(accountConsume[0].toString()));
                		}
                	}
                }
                for(Object[] card : countCard) {
                	if(card.length>0) {
                		String regionName1=card[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setCardFee(Long.valueOf(card[0].toString()));
                		}
                	}
                }
                for(Object[] obu : countObu) {
                	if(obu.length>0) {
                		String regionName1=obu[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setObuFee(Long.valueOf(obu[0].toString()));
                		}
                	}
                }
                for(Object[] replaceCard : countReplaceCard) {
                	if(replaceCard.length>0) {
                		String regionName1=replaceCard[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setCardReplacementCount(Long.valueOf(replaceCard[0].toString()));
                		}
                	}
                }
                
                for(Object[] cancelRefund : sumCancelRefund) {
                	if(cancelRefund.length>0) {
                		String regionName1=cancelRefund[1].toString();
                		if(regionName.equals(regionName1)) {
                			countModelNew.setRefund(Long.valueOf(cancelRefund[0].toString()));
                		}
                	}
                }
                modelList.add(countModelNew);
			}
            
		return modelList;
	}

	@Override
	public List<CountModel> page(CountModel queryModel, HttpServletRequest request) throws ManagerException {
		if(queryModel.getStartDate()==null && queryModel.getEndDate()==null)
			return null;
				User user=LoginHelper.getLoginUser(request);
				String channelId=user.getStaff().getServiceHallId();
				Region region =regionRepo.findByChannleId(channelId);
				if(region==null || region.getRegionId()==null) {
					throw new ManagerException("当前登录工号无对应地区，无法进行查询，请联系管理员进行地区维护！");
				}
				List<Object[]>	channelList=dailySheetRepo.findChannelIdByRegionId(region.getRegionId());
				List<CountModel> modelList = Lists.newArrayList();
				//地区开卡数
	        	List<Object[]> countOpenCard =dailySheetRepo.findCountOpenCardDayByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
	        	//地区开OBU数
	        	List<Object[]> countOpenObu  =dailySheetRepo.findCountOpenObuByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
	        	//地区圈存充值金额
	        	List<Object[]> sumChargeFee  =dailySheetRepo.findCountChargeByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
	        	//地区圈存半条流水
	        	List<Object[]> sumChargeFeeFix  =dailySheetRepo.findCountChargeFixByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
	        	//地区账户充值金额
	        	List<Object[]> sumAccountCharge  =dailySheetRepo.findCountAccountChargeGrouopByChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
	        	//账户冲正金额
				List<Object[]> sumAccountReversal  =dailySheetRepo.findCountAccountReversalGrouopByChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
				//地区卡设备费
	        	List<Object[]> countCardFee  =dailySheetRepo.findSumOpenCardByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
	        	//地区签设备费
				List<Object[]> countObuFee  =dailySheetRepo.findSumOpenObuByRegionIdChannel(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
				//地区换卡费
				List<Object[]> countReplaceCard  =dailySheetRepo.findCountReplaceCardGroupByChannelIdAndRegion(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
				//地区注销退费金额
				List<Object[]> countCancelRefund  =dailySheetRepo.findCountCancelRefundGrouopByRegionName(queryModel.getStartDate(), queryModel.getEndDate(),region.getRegionId());
				
				
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
	
	
	@Override
	public List<CountModel> staffId(CountModel queryModel, HttpServletRequest request) throws ManagerException {
		if(queryModel.getStartDate()==null && queryModel.getEndDate()==null)
			return null;
				User user=LoginHelper.getLoginUser(request);
				String name=user.getStaff().getServiceHall().getName();
				String channelId=user.getStaff().getServiceHall().getServiceHallId();
				if(name==null || channelId==null) {
					throw new ManagerException("当前登录工号信息不完整，无法进行数据统计，请联系黔通智联管理员进行工号维护！");
				}
				List<CountModel> modelList = Lists.newArrayList();
				List<Object[]>	staffIdList=dailySheetRepo.findStaffIdByChannelId(channelId);
				//开卡数
				List<Object[]> countOpenCard =dailySheetRepo.findCountCardBychannelId(channelId, queryModel.getStartDate());
				//开签数
				List<Object[]> countOpenObu =dailySheetRepo.findCountObuBychannelId(channelId, queryModel.getStartDate());
				//按工号统计30元卡设备数量
				List<Object[]> countOpenCard30 =dailySheetRepo.findCount30CardFeeBychannelId(channelId, queryModel.getStartDate());
				//按工号统计卡设备费
				List<Object[]> sumOpenCardFee =dailySheetRepo.findSumCardFeeBychannelId(channelId, queryModel.getStartDate());
				//按工号统计200元签设备费数量
				List<Object[]> countOpenObu200 =dailySheetRepo.findCount200OBUFeeBychannelId(channelId, queryModel.getStartDate());
				//按工号统计签设备费金额（元）
				List<Object[]> sumOpenObuFee =dailySheetRepo.findSumObuFeeBychannelId(channelId, queryModel.getStartDate());
				//按工号统计圈存金额
				List<Object[]> sumCardChargeFee =dailySheetRepo.findSumChargeFeeBychannelId(channelId, queryModel.getStartDate());
				/*//按工号统计半条流水金额
				List<Object[]> sumCardChargeFeeFix =dailySheetRepo.findSumChargeFeeFixBychannelId(channelId, queryModel.getStartDate());
				//按工号统计账户充值流水
*/				List<Object[]> sumAcountChargeFee =dailySheetRepo.findSumAcountChargeBychannelId(channelId, queryModel.getStartDate());
				/*//按工号统计账户消费
				List<Object[]> sumAcountConsume =dailySheetRepo.findSumAcountConsumeBychannelId(channelId, queryModel.getStartDate());*/
				//按工号统计账户冲正
				List<Object[]> sumAcountReversal =dailySheetRepo.findSumAcountReversalBychannelId(channelId, queryModel.getStartDate());
				//按工号统计账户冲正
				List<Object[]> sumCardReplace =dailySheetRepo.findSumCardReplaceBychannelId(channelId, queryModel.getStartDate());
				//按工号统计注销退费金额
				List<Object[]> sumCancelRefund =dailySheetRepo.findSumCancelRefundBychannelId(channelId, queryModel.getStartDate());
				for(Object[] staffIdS: staffIdList) {
					String staffId=staffIdS[0].toString(); 
					String loginName=staffIdS[1].toString(); 
					CountModel countModel= new CountModel();
					countModel.setStaffId(staffId);
					countModel.setServiceHallName(name);
					for(Object[] openCard : countOpenCard) {
	                	if(openCard.length>0) {
	                		String staff=openCard[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setCardCount(Long.valueOf(openCard[2].toString()));
	                		}
	                	}
	                }
					for(Object[] openObu : countOpenObu) {
	                	if(openObu.length>0) {
	                		String staff=openObu[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setObuCount(Long.valueOf(openObu[2].toString()));
	                		}
	                	}
	                }
					for(Object[] openCard30 : countOpenCard30) {
	                	if(openCard30.length>0) {
	                		String staff=openCard30[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setCardFee30(Long.valueOf(openCard30[2].toString()));
	                		}
	                	}
	                }
					for(Object[] openCardfee : sumOpenCardFee) {
	                	if(openCardfee.length>0) {
	                		String staff=openCardfee[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setCardFee(Long.valueOf(openCardfee[2].toString()));
	                		}
	                	}
	                }
					for(Object[] openObu200 : countOpenObu200) {
	                	if(openObu200.length>0) {
	                		String staff=openObu200[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setObuFee200(Long.valueOf(openObu200[2].toString()));
	                		}
	                	}
	                }
					for(Object[] openObufee : sumOpenObuFee) {
	                	if(openObufee.length>0) {
	                		String staff=openObufee[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setObuFee(Long.valueOf(openObufee[2].toString()));
	                		}
	                	}
	                }
					for(Object[] cardChargeFee : sumCardChargeFee) {
	                	if(cardChargeFee.length>0) {
	                		String staff=cardChargeFee[0].toString();
	                		String status=cardChargeFee[3].toString();
	                		String tradeType=cardChargeFee[4].toString();
	                		if(staffId.equals(staff)) {
	                			if("3".equals(tradeType)) {
	                				countModel.setAccountConsume(Long.valueOf(cardChargeFee[2].toString()));
	                			} else if("0".equals(status)) {
	                				countModel.setAmountFix(Long.valueOf(cardChargeFee[2].toString()));
	                			}else if("1".equals(status)){
	                				countModel.setAmount(Long.valueOf(cardChargeFee[2].toString()));
	                			}
	                		}
	                	}
	                }
					for(Object[] acountChargeFee : sumAcountChargeFee) {
	                	if(acountChargeFee.length>0) {
	                		String staff=acountChargeFee[0].toString();
	                		if(loginName.equals(staff)) {
	                			countModel.setAccountAmount(Long.valueOf(acountChargeFee[1].toString()));
	                		}
	                	}
	                }
					/*for(Object[] acountConsume : sumAcountConsume) {
	                	if(acountConsume.length>0) {
	                		String staff=acountConsume[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setAccountConsume(Long.valueOf(acountConsume[2].toString()));
	                		}
	                	}
	                }*/
					for(Object[] acountReversal : sumAcountReversal) {
	                	if(acountReversal.length>0) {
	                		String staff=acountReversal[0].toString();
	                		if(loginName.equals(staff)) {
	                			countModel.setAccountReversal(Long.valueOf(acountReversal[1].toString()));
	                		}
	                	}
	                }
					for(Object[] cardReplace : sumCardReplace) {
	                	if(cardReplace.length>0) {
	                		String staff=cardReplace[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setCardReplacementCount(Long.valueOf(cardReplace[2].toString()));
	                		}
	                	}
	                }
					for(Object[] cancelRefund : sumCancelRefund) {
	                	if(cancelRefund.length>0) {
	                		String staff=cancelRefund[0].toString();
	                		if(staffId.equals(staff)) {
	                			countModel.setRefund(Long.valueOf(cancelRefund[1].toString()));
	                		}
	                	}
	                }
					
					modelList.add(countModel);
				}
		return modelList;
	}

	@Override
	public Long sumCash(CountModel queryModel) throws ManagerException {
		
		Long fee = dailySheetRepo.sumChannelIdAndDateAndCash(queryModel.getRegionName(), queryModel.getStartDate(), queryModel.getEndDate());
		return fee;
	}
	
	
}

