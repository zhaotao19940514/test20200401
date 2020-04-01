package cn.com.taiji.css.manager.qtzt;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.model.qtzt.QtztJsonRequest;
import cn.com.taiji.css.model.qtzt.QtztJsonRequestData;
import cn.com.taiji.css.model.qtzt.QtztJsonResponse;

@Service
public class QtztInterfaceJsonManagerImpl extends AbstractManager implements QtztInterfaceJsonManager {
	@Value("#{commProperties.appId}")
	public String appId;
	@Value("#{commProperties.token}")  
    public String token;
	@Value("#{commProperties.sign}")
    public String sign;
	@Value("#{commProperties.stamp}")
    public String stamp;
	@Value("#{commProperties.zip}")
    public Integer zip;
	@Value("#{commProperties.keyType}")
    public Integer keyType;
	@Value("#{commProperties.key}")
    public String key;
	@Value("#{commProperties.qtztUrl}")
    public  String qtztUrl;
	
	
	
	
	@PostConstruct
    private void init()
    {
    	QtztJsonRequest.appId = appId;
    	QtztJsonRequest.token=token;
    	QtztJsonRequest.sign=sign;
    	QtztJsonRequest.stamp=stamp;
    	QtztJsonRequest.zip=zip;
    	QtztJsonRequest.keyType=keyType;
    	QtztJsonRequest.key=key;
    }
	@Override
	public QtztJsonResponse sendQtzt(QtztJsonRequest req, Class<? extends QtztJsonResponse> typeClass) throws ManagerException {
		QtztJsonRequestData reqData = req.getData();
		req.setCode(reqData.getQtztTransType().getCode());
		QtztTransType t =reqData.getQtztTransType();
		String str = req.toJsonWithData();
		logger.info("发送内容:"+str);
		Map<QtztTransType, Class<? extends QtztJsonResponse>> map = Maps.newHashMap();
		map.put(reqData.getQtztTransType(), typeClass);
		QtztJsonResponse res = null;
		try {
			String	strs = QtztHttpHelper.jsonPost(qtztUrl + t.getUrl(), str, "utf-8");
			logger.info("响应内容:"+strs);
			res = JsonTools.json2Object(strs, map.get(t));
		} catch (IOException e) {
			throw new ManagerException("连接黔通接口数据异常！");
		};     
	    return res; 
	}
	
	
}
