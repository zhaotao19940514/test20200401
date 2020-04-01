package cn.com.taiji.css.model.qtzt.response;

import cn.com.taiji.css.model.qtzt.QtztJsonResponse;

public class IssueOrderResponse extends QtztJsonResponse {
	private IssueOrderResponseData data;

	public IssueOrderResponseData getData() {
		return data;
	}

	public void setData(IssueOrderResponseData data) {
		this.data = data;
	}
	
}
