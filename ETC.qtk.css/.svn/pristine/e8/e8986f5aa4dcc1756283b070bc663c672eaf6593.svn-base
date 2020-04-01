package cn.com.taiji.css.model.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

import cn.com.taiji.common.model.BaseModel;

/**
 * 号段 用于号段并集
 * 
 * @author yaonl
 * @date 2018年08月26日 11:35:08
 * @E_mail yaonanlin@163.com
 *
 */
public class PeriodModel extends BaseModel implements Comparable<PeriodModel> {
	private Long startId;
	private Long endId;

	public PeriodModel() {
		super();
	}

	public PeriodModel(Long startId, Long endId) {
		super();
		this.startId = startId;
		this.endId = endId;
	}

	public Long getStartId() {
		return startId;
	}

	public void setStartId(Long startId) {
		this.startId = startId;
	}

	public Long getEndId() {
		return endId;
	}

	public void setEndId(Long endId) {
		this.endId = endId;
	}

	public boolean merge(PeriodModel model) throws Exception {
		if (this.startId > model.getStartId()) {
			// 未排序
			throw new Exception("当前对象起始卡号>传入对象起始卡号,只支持升序排列的对象");
		}
		if (this.endId + 1 < model.startId) {
			return false;
		} else {
			this.endId = this.endId < model.endId ? model.endId : endId;
			return true;
		}
	}

	public static List<PeriodModel> doMergeSortList(List<PeriodModel> models) throws Exception {
		models.sort(Comparator.naturalOrder());
		List<PeriodModel> results = new ArrayList<PeriodModel>();
		PeriodModel merge = models.get(0);
		models.remove(0);
		while(models.size()>0) {
			while(merge.merge(models.get(0))) {
				models.remove(0);
				if(models.size()==0) {
					break;
				}
			}
			results.add(merge);
			if(models.size()>0) {
				merge=models.get(0);
			}
		}
		return results;
	}

	public static void main(String[] args) throws Exception {
		List<PeriodModel> list = getUnionList();
		List<PeriodModel> doMergeSortList = doMergeSortList(list);
		for(PeriodModel m :doMergeSortList) {
			System.out.println(m.getStartId()+"-"+m.getEndId());
		}
	}
	// 测试用
	public static List<PeriodModel> getUnionList() {
		List<String> noStrings = Lists.newArrayList(
				
				);
		List<PeriodModel> union = Lists.newArrayList();
		for (String noStr : noStrings) {
			String[] split = noStr.split("-");
			String no1 = split[0];
			String no2 = split[1];
			if(no1.length() == 20){
				no1 = no1.substring(3);
			}
			if(no2.length() == 20){
				no2 = no2.substring(3);
			}
			union.add(new PeriodModel(Long.valueOf(no1),Long.valueOf(no2)));
		}
		return union;
	}
	@Override
	public int compareTo(PeriodModel o) {
		if (this.getStartId() > o.startId) {
			return 1;
		} else if (this.getStartId() < o.startId) {
			return -1;
		} else {
			return 0;
		}
	}
}
