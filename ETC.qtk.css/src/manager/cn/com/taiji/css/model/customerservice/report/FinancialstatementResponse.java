/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.report;

import java.util.List;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.qtk.entity.Financialstatement;


public class FinancialstatementResponse extends BaseModel  {
	
	private List<Financialstatement> lists;
	
	private List<String> categories; 
	
	private List<Integer> data;

	
	
	/**
	 * @return lists
	 */
	public List<Financialstatement> getLists() {
		return lists;
	}

	/**
	 * @param lists 要设置的 lists
	 */
	public void setLists(List<Financialstatement> lists) {
		this.lists = lists;
	}

	/**
	 * @return categories
	 */
	public List<String> getCategories() {
		return categories;
	}

	/**
	 * @param categories 要设置的 categories
	 */
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	/**
	 * @return data
	 */
	public List<Integer> getData() {
		return data;
	}

	/**
	 * @param data 要设置的 data
	 */
	public void setData(List<Integer> data) {
		this.data = data;
	}
	
	
		
}

