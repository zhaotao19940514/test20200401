package cn.com.taiji.css.web.customerservice.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.manager.customerservice.report.DailyEverydayManager;
import cn.com.taiji.css.model.customerservice.report.DailyEverydayRequest;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/customerservice/report/dailyEveryday")
public class DailyEverydayController extends MyLogController{
	
	@Autowired
	private DailyEverydayManager dailyEverydayManager;
    private final String prefix = "customerservice/report/dailyEveryday/";
    //   private final String dailyPath = "D:/dailyEveryday/";
    private final String dailyPath = "/home/dailyEveryday/";

	private String timeDate;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") DailyEverydayRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void managePost(@ModelAttribute("queryModel") DailyEverydayRequest queryModel, HttpServletRequest request, Model model,HttpServletResponse response) throws Exception
	{
		timeDate = queryModel.getTimeDate().replace("-", "");
		try {
			dailyEverydayManager.export(queryModel, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("文件生成异常");
		}
		addSuccess(response, "下载成功");
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void manageGet( HttpServletRequest request, Model model,HttpServletResponse response) throws Exception
	{
		File file= new File(dailyPath+timeDate+".xlsx");
		if(!file.exists()) {
			throw new ManagerException("文件不存在无法导出");
		}
		try {
			HttpMimeResponseHelper.doDownLoad(request, response,new FileInputStream(file),timeDate+"报表.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ManagerException("文件导出异常");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("文件导出失败");
		}
	}
	

}
