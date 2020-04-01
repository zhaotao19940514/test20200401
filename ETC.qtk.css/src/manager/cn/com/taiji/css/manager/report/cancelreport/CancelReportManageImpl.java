package cn.com.taiji.css.manager.report.cancelreport;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.ExcelTemplateHelper;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.manager.pub.WorkbookWithDataHandler;
import cn.com.taiji.common.model.pub.DefaultContentInfo;
import cn.com.taiji.css.dao.jpa.CancelReportDao;
import cn.com.taiji.css.model.request.report.cancelreport.AgencyCancelDeatilShowModel;
import cn.com.taiji.css.model.request.report.cancelreport.CancelReportRequest;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.repo.jpa.AgencyRepo;

@Service
public class CancelReportManageImpl implements CancelReportManage,WorkbookWithDataHandler<List<Object[]>> {

	@Autowired
	private AgencyRepo agencyRepo;

	@Autowired
	private CancelReportDao cancelReportDao;

	@Override
	public List<AgencyCancelDeatilShowModel> getCancelDetail(CancelReportRequest queryModel) throws ManagerException {
		queryModel.paramCheck();
		List<Object[]> querryResult = cancelReportDao.findCancelDetailByAgencyIdAndStartTimeAndEndTime(
				queryModel.getAgencyId(), queryModel.getStartTime(), queryModel.getEndTime(),
				queryModel.getVehicleIsGui());
		if (querryResult.size() >= 1000) {
			throw new ManagerException("查询数据过多无法正常显示,请调整时间后再试或导出文件查看");
		}
		List<AgencyCancelDeatilShowModel> modelList = Lists.newArrayList();
		for (Object[] detail : querryResult) {
			AgencyCancelDeatilShowModel model = new AgencyCancelDeatilShowModel();
			model.setStaffId(detail[0] != null ? detail[0].toString() : "");
			model.setVehicleId(detail[1] != null ? detail[1].toString() : "");
			model.setCustomerName(detail[2] != null ? detail[2].toString() : "");
			model.setCancelTime(detail[3] != null ? detail[3].toString() : "");
			model.setChannelName(detail[4] != null ? detail[4].toString() : "");
			modelList.add(model);
		}
		return modelList;
	}

	@Override
	public List<Agency> getAllAgency() {
		return agencyRepo.findAll();
	}

	@Override
	public File export(CancelReportRequest queryModel) throws ManagerException {
		queryModel.paramCheck();
		List<Object[]> querryResult = cancelReportDao.findCancelDetailByAgencyIdAndStartTimeAndEndTime(
				queryModel.getAgencyId(), queryModel.getStartTime(), queryModel.getEndTime(),
				queryModel.getVehicleIsGui());
		DefaultContentInfo info = new DefaultContentInfo();
		info.setTemplateUrl("file:" + FileHelper.getWebappPath() + "/template/cancelreport.xls");
		info.setAlwaysNew(true);
		info.setSavePath(FileHelper.getTmpPath());
		info.setFileName("cancelreport.xls");
		try {
			return ExcelTemplateHelper.generateExcel(info, false, querryResult, this);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("生成模板文件出错，请联系管理员");
		}
	}
	
	@Override
	public void fillWorkbook(Workbook workbook, List<Object[]> data) throws Exception {
		int j = 1;
		workbook.setSheetName(0, "shhet1");
		Sheet sheet = workbook.getSheetAt(0);
		Cell cell = ExcelTemplateHelper.getCell(sheet, 0, 0);
		for(int i = 0;i<data.size();i++){
			cell = ExcelTemplateHelper.getCell(sheet, j, 0);
			cell.setCellValue(i+1);
			cell = ExcelTemplateHelper.getCell(sheet, j, 1);
			cell.setCellValue(data.get(i)[0] == null? "" : data.get(i)[0].toString());
			cell = ExcelTemplateHelper.getCell(sheet, j, 2);
			cell.setCellValue(data.get(i)[1] == null? "" : data.get(i)[1].toString());
			cell = ExcelTemplateHelper.getCell(sheet, j, 3);
			cell.setCellValue(data.get(i)[2] == null? "" : data.get(i)[2].toString());
			cell = ExcelTemplateHelper.getCell(sheet, j, 4);
			cell.setCellValue(data.get(i)[3] == null? "" : data.get(i)[3].toString());
            cell = ExcelTemplateHelper.getCell(sheet, j, 5);
            cell.setCellValue(data.get(i)[4] == null? "" : data.get(i)[4].toString());
			j++;
		}
	}
}
