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

import cn.com.taiji.css.entity.User;
import cn.com.taiji.sso.model.comm.protocol.client.SsoCodeResponse;

public interface OAuthLoginManager
{

	User oauthLogin(HttpServletRequest request, SsoCodeResponse codeRes) throws IOException;

	String getAuthLoginUrl();

}
