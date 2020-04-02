/*
 * Date: 2015年4月9日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web.comm;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.model.file.HttpFileProtocolModel;
import cn.com.taiji.common.web.BaseFileCommController;
import cn.com.taiji.css.manager.comm.BinFileCommHandleManager;
import cn.com.taiji.css.manager.comm.handler.BinServiceLogListener;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年4月9日 下午7:08:46<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
public class BinFileServiceController extends BaseFileCommController
{
	@Autowired
	private BinFileCommHandleManager handleManager;

	@Autowired
	public BinFileServiceController(BinServiceLogListener listener)
	{
		super(listener);
	}

	@CrossOrigin(origins = { "http://127.0.0.1" }, allowedHeaders = { "binfile-md5", "binfile-auth",
			"binfile-memsize" }, allowCredentials = "true", methods = { RequestMethod.POST }, maxAge = 3600)
	@RequestMapping(value = "/common/binapi", method = RequestMethod.POST)
	public void fileRequest(@ModelAttribute HttpFileProtocolModel model, HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		response.addCookie(new Cookie("sample", "acookie"));
		response.setCharacterEncoding("utf-8");
		super.handleFileComm(model, handleManager, request, response);
	}
}
