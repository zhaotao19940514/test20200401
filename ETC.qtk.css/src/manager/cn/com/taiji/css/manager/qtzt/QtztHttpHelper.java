package cn.com.taiji.css.manager.qtzt;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import cn.com.taiji.common.manager.net.http.HttpClientHelper;
import cn.com.taiji.common.manager.net.http.Response2StringHandler;
import cn.com.taiji.common.model.net.http.HttpRequestPara;

public class QtztHttpHelper extends HttpClientHelper{
	public static String jsonPost(String uri, String requestJson, String defaultResEncoding) throws IOException
	{
		HttpPost post = new HttpPost(uri);
		StringEntity reqEntity = new StringEntity(requestJson, ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8));
		post.setEntity(reqEntity);
	    return httpRequest(post, true, HttpRequestPara.DEFAULT_CONN_TIMEOUT, new Response2StringHandler("utf-8"), -1);
	}
}
