package cn.com.taiji.css.web.customerservice.report;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.css.manager.customerservice.report.DailyStatisticsManager;
import cn.com.taiji.css.model.customerservice.report.QueryTimes;
import cn.com.taiji.css.web.MyBaseController;

/**
 * 
 * 日报统计
 * HHW
 *
 */
@Controller
@RequestMapping("/customerservice/report/dailyStatistics")
public class DailyStatisticsController extends MyBaseController{
	
	private final String prefix = "customerservice/report/dailyStatistics/";
	@Autowired
	private DailyStatisticsManager dailyStatisticsManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") QueryTimes queryModel,Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/download/{date}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@PathVariable("date")String date, HttpServletRequest request, HttpServletResponse response) throws IOException, ManagerException{
		if (date == null ) {
			date = LocalDate.now().toString().replace("-", "");
		}
		HttpHeaders headers = new HttpHeaders();    
        File file = new File(FileHelper.getDataPath() + "/dailyStatistics/" + date + ".zip");
        logger.info("导出:"+file.getName());
		if (!file.exists()) {
			throw new ManagerException("文件未生成！");
		}
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", date+".zip");    
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), 
        		headers, HttpStatus.CREATED);    
	}
}
