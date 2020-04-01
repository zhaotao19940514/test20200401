package cn.com.taiji.css.manager.system;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.pub.ExcelTemplateHelper;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.manager.pub.WorkbookWithDataHandler;
import cn.com.taiji.common.model.pub.DefaultContentInfo;
import cn.com.taiji.css.entity.SystemLog;
import cn.com.taiji.css.repo.jpa.SystemLogRepo;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;

@Service
@Scope("prototype")
public class OplogExportTask extends AbstractManager implements Runnable, WorkbookWithDataHandler<List<SystemLog>>
{
	@Autowired
	private SystemLogRepo repo;
	private String result;
	private int index;
	private int total;
	private SystemLogPageRequest request;

	@Override
	public void run()
	{
		List<SystemLog> list = repo.list(request, 1000);
		this.total = list.size();

		DefaultContentInfo info = new DefaultContentInfo();
		info.setTemplateUrl("file:" + FileHelper.getWebappPath() + "/template/oplogTemplate.xlsx");
		info.setAlwaysNew(true);
		String fileName = toLogString("{}.xlsx", UUID.randomUUID().toString().replaceAll("-", ""));
		info.setSavePath(FileHelper.getDataPath());
		info.setFileName(fileName);
		try
		{
			File file = ExcelTemplateHelper.generateExcel(info, true, list, this);
			taskResult(file.getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			taskResult("error");
		}

	}

	private void taskResult(String fileName)
	{
		this.result = fileName;
	}

	public void taskInit(SystemLogPageRequest request)
	{
		result = null;
		index = 0;
		total = Integer.MAX_VALUE;
		this.request = request;
	}

	public double getPercent()
	{
		double percent = 0.0;
		if (total == 0)
		{
			percent = 0.9999;
		}
		else
		{
			percent = new Integer(index).doubleValue() / total;
			percent = (percent == 1 ? 0.9999 : percent);
		}
		return result == null ? percent : 1.0;
	}

	public String getResult()
	{
		return result;
	}

	public String getMsg()
	{
		return toLogString("正在写入excel({}/{})", index, total);
	}

	@Override
	public void fillWorkbook(Workbook workbook, List<SystemLog> data) throws Exception
	{
		workbook.setSheetName(0, "导出记录");
		long sleep = 30 * 1000 / data.size();
		Sheet sheet = workbook.getSheetAt(0);
		for (; index < data.size(); index++)
		{
			SystemLog log = data.get(index);
			Cell cell = ExcelTemplateHelper.getCell(sheet, index + 1, 0);
			cell.setCellValue(log.getLogType().getValue());
			cell = ExcelTemplateHelper.getCell(sheet, index + 1, 1);
			cell.setCellValue(log.getUser().getName());
			cell = ExcelTemplateHelper.getCell(sheet, index + 1, 2);
			cell.setCellValue(log.getOptime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
			cell = ExcelTemplateHelper.getCell(sheet, index + 1, 3);
			cell.setCellValue(log.getInfo());
			TimeUnit.MILLISECONDS.sleep(sleep);
		}

	}

}
