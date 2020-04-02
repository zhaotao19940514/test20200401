/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.apply.baseinfo;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.BatchIssueStatus;
import cn.com.taiji.css.manager.apply.baseinfo.InportInfomationManager;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationRequset;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationResponse;
import cn.com.taiji.css.web.MyLogController;

/**
 * lz
 */
@Controller
@RequestMapping("/apply/baseinfo/inport")
public class InportInfomationController extends MyLogController {
	
	private final String prefix = "apply/baseinfo/inport/";
	
	@Autowired
	private InportInfomationManager inportInfomationManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") InportInfomationRequset queryModel, Model model)
	{
		model.addAttribute("types", BatchIssueStatus.values());
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") InportInfomationRequset queryModel, Model model,HttpServletResponse response) throws IOException
	{
		model.addAttribute("pagn", inportInfomationManager.query(queryModel));
		return prefix+"queryResult";
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String setupFile(@ModelAttribute("pageModel") User user,HttpServletRequest request, Model model)
	{
		return prefix + "file";
	}
	
	@RequestMapping( value ="/handleFile" , method = RequestMethod.POST )
	   public void fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response){
		InportInfomationResponse inportInfomationResponse = new InportInfomationResponse();
		try {
			inportInfomationResponse=inportInfomationManager.saveFile(file);
			HttpMimeResponseHelper.responseJson(inportInfomationResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public void loginPost( @RequestBody UserRequset queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) 
	{	
		InportInfomationResponse inportInfomationResponse=new InportInfomationResponse();
			File f =new File(queryModel.getFilePath()+queryModel.getFileName());
				try {
					inportInfomationResponse=inportInfomationManager.importExcel(inportInfomationManager.getLines(f));
				} catch (IOException e) {
					e.printStackTrace();
					inportInfomationResponse.setMessage("读取Excel文件失败，请重新上传！");
					inportInfomationResponse.setStatus(-1);
				} catch (ManagerException e) {
					e.printStackTrace();
					inportInfomationResponse.setMessage("读取Excel文件失败，请重新上传！");
					inportInfomationResponse.setStatus(-1);
				}
			if(inportInfomationResponse.getStatus()==1) {
				inportInfomationResponse.setMessage("此Excel内的数据已全部导入！");
			}
			try {
				HttpMimeResponseHelper.responseJson(inportInfomationResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	   
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void getExcelFile(@RequestBody InportInfomationRequset queryModel ,HttpServletRequest request, Model model,HttpServletResponse response) throws ManagerException
	{
		File export = inportInfomationManager.getExcelFilePath(queryModel,request);
		try {
			HttpMimeResponseHelper.doDownLoad(request, response, export, export.getName());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("下载文件失败",e);
		}
	}
}

