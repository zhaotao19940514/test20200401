package cn.com.taiji.css.web.administration.inventory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.model.LabelIdPair;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.serviceHall.ServiceHallManager;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.serviceHall.ServiceHallModel;
import cn.com.taiji.css.web.BaseLogController;
@Controller
@RequestMapping("/hall")
public class ServiceHallController extends BaseLogController{
	@Autowired
	private ServiceHallManager serviceHallManager;
	
	private final String prefix = "hall/";
	
	/***
	 * 服务网点
	 *//*
	/**
	 * tree
	 */
	@RequestMapping(value = "/getByParent")
	public void getByParent(@RequestParam("agencyId")String agencyId, Model model,HttpServletResponse response) throws Exception
	{	
		List<ServiceHallModel> list=serviceHallManager.listByAgentId(agencyId);
		List<ZTreeItem> treeList=list.stream().map(u->{
			return new ZTreeItem().setId(u.getId()).setName(u.getName()).setIsParent(u.isHasChild());
		}).collect(Collectors.toList());
		response.getWriter().print(JsonTools.toJsonStr(treeList));
	}
	@RequestMapping("/listByName")
	public void listByName(@RequestParam("name") String name, HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		List<LabelIdPair> pairs = LabelIdPair.fromList(serviceHallManager.listByName(name, LoginHelper.getLoginUser(request)), "name", "id");
		super.responseJson(JsonTools.toJsonStr(pairs), response);
	}

}
