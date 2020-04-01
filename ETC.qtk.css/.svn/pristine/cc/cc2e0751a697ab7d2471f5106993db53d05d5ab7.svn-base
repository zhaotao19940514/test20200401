/* Copyright  2016 TaiJi Computer Corporation Limited
 * All rights reserved.
 * 
 * Date: 2016年5月25日
 * author: luhj  (luhj@mail.taiji.com.cn)
 *
 */
package cn.com.taiji.css.manager.oauth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.sso.model.comm.protocol.SsoUserInfoRequest;
import cn.com.taiji.sso.model.comm.protocol.SsoUserInfoResponse;
import cn.com.taiji.sso.model.comm.protocol.client.OAuthClientConfig;
import cn.com.taiji.sso.model.comm.protocol.client.OAuthClientHelper;
import cn.com.taiji.sso.model.comm.protocol.client.SsoCodeResponse;
import cn.com.taiji.sso.model.comm.protocol.client.SsoTokenResponse;

@Service
public class OAuthLoginManagerImpl extends AbstractManager implements OAuthLoginManager
{
	@Autowired
	private UserRepo userRepo;
	private static final OAuthClientConfig oauthConfig = OAuthClientConfig.newInstance("/oauth_client.properties");

	@Override
	public User oauthLogin(HttpServletRequest request, SsoCodeResponse codeRes) throws IOException
	{
		logger.debug("code response is " + codeRes);
		// 验证state参数是否跟发送给服务端的一致
		if (!StringTools.hasText(codeRes.getCode()) || !codeRes.getState().equals("state")) return null;
		logger.debug("receive code is {}", codeRes.getCode());
		// post请求获得token
		SsoTokenResponse response = OAuthClientHelper.getTokenRes(oauthConfig, codeRes.getCode());
		if (StringTools.hasText(response.getError()))
		{
			logger.debug("code response :{}", response);
			return null;
		}
		// token请求成功后，用token请求用户信息
		String token = response.getAccess_token();
		// refreshToken = response.getRefresh_token();
		logger.debug("response token is {}", token);
		User user = getUser(token);
		// 可以用前缀+“user的id”作key来缓存token和refreshToken
		return user;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param token
	 * @return
	 * @throws ApiRequestException
	 * @throws IOException
	 */
	private User getUser(String token) throws ApiRequestException, IOException
	{
		SsoUserInfoRequest infoRequest = new SsoUserInfoRequest();
		infoRequest.setClient_id(oauthConfig.getClientId()).setClient_secret(oauthConfig.getClientSecret());
		infoRequest.setAccess_token(token);
		SsoUserInfoResponse infoResponse = OAuthClientHelper.getSsoService(oauthConfig).ssoUserInfo(infoRequest);
		User user = userRepo.findByMobile(infoResponse.getMobile());
		
		// 维护系统自己的用户表
//		if (user == null)
//		{
//			user = new User();
//			user.setPasswd("7c4a8d09ca3762af61e59520943dc26494f8941b");
//			user.setMale(true);
//			user.setRole(roleManager.getDefaultRole());
//			return null;
//		}
//		user.setMobile(infoResponse.getMobile());
//		user.setName(infoResponse.getName());
//		user.setLoginName(infoResponse.getMobile());
//		user.setNamePy(infoResponse.getName());
//		userRepo.save(user);
		return user;
	}

	/**
	 * 刷新令牌
	 * 
	 * @param refreshToken
	 * @return
	 * @throws IOException
	 */
	public SsoTokenResponse refreshToken(String refreshToken) throws IOException
	{
		return OAuthClientHelper.refreshToken(oauthConfig, refreshToken);
	}

	@Override
	public String getAuthLoginUrl()
	{
		// 需带上state参数
		return oauthConfig.getLoginUrl() + "&state=state";
	}

}
