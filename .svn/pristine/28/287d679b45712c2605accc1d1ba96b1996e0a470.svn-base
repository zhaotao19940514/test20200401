package cn.com.taiji.css.manager.system;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.echart.ChartDataBuilder;
import cn.com.taiji.common.manager.echart.SingleChartDataBuilder;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.manager.pub.WordTemplateHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.echart.AxisKeyValue;
import cn.com.taiji.common.model.echart.AxisTimeSplitter;
import cn.com.taiji.common.model.echart.AxisTimeUnit;
import cn.com.taiji.common.model.echart.ChartData;
import cn.com.taiji.common.model.echart.ChartSerie;
import cn.com.taiji.common.model.echart.ChartType;
import cn.com.taiji.common.model.pub.DefaultFreemarkerContentInfo;
import cn.com.taiji.common.model.pub.FormatDateTimeMethodModel;
import cn.com.taiji.css.entity.SystemLog;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.repo.jpa.SystemLogRepo;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-20 下午03:05:30<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("systemLogManager")
public class SystemLogManagerImpl extends AbstractManager implements SystemLogManager
{
	@Autowired
	private SystemLogRepo logRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional
	public String add(SystemLog log)
	{
		log.setUser(userRepo.findById(log.getUser().getId()).get());
		logRepo.save(log);
		return log.getId();
	}

	@Override
	public Pagination queryPage(SystemLogPageRequest req)
	{
		return logRepo.page(req);
	}

	@Override
	public ChartData multiGroup()
	{
		LocalDateTime endTime = LocalDateTime.now();
		LocalDateTime beginTime = endTime.minusDays(8).plusSeconds(1);
		/*
		 * time userid count(*) 
		 * 20160302 9bf98b04418e42beac464c484db5d995 4 20160302 admin 12
		 * 20160303 9bf98b04418e42beac464c484db5d995 2 20160303 admin 12 20160304 admin 11 20160307
		 * 9bf98b04418e42beac464c484db5d995 1 20160307 admin 5 20160308 admin 2 20160309
		 * 9bf98b04418e42beac464c484db5d995 1 20160309 admin 15
		 */
		User other = userRepo.findByLoginName("ccc");
		if (other == null) other = new User();
		List<Object[]> list = logRepo.groupStat(beginTime, endTime, Arrays.asList("admin", other.getId()));
		// 用java代码按userid再分组，图表中每个user为一个serie
		Map<String, List<AxisKeyValue>> map = list.stream().collect(Collectors.groupingBy(os -> String.valueOf(os[1]),
				Collectors.mapping(os -> new AxisKeyValue(os[0], os[2]), Collectors.toList())));
		ChartDataBuilder builder = ChartDataBuilder.create();
		// 设置图例
		builder.setLegend(Arrays.asList("admin", "ccc"));
		// 按天分割好时间轴刻度
		builder.setxAxisSpliter(AxisTimeSplitter.of(beginTime, endTime, AxisTimeUnit.DAY).setFormat("yyyyMMdd"));
		// 设置y轴数值范围，一般不需要
		builder.setyAxisRange(0, 20);
		// 给某天没有数据的填充0
		builder.addSerieWithFill(new ChartSerie("admin", ChartType.LINE),
				map.getOrDefault("admin", new ArrayList<AxisKeyValue>()), 0);
		builder.addSerieWithFill(new ChartSerie("ccc", ChartType.LINE),
				map.getOrDefault(other.getId(), new ArrayList<AxisKeyValue>()), 0);
		return builder.build();
	}

	@Override
	public ChartData singleGroup()
	{
		LocalDateTime beginTime = LocalDateTime.now().minusMonths(1);
		// loginName,count
		List<AxisKeyValue> list = logRepo.groupStat(beginTime).stream().map(os -> new AxisKeyValue(os[0], os[1]))
				.collect(Collectors.toList());
		SingleChartDataBuilder builder = SingleChartDataBuilder.create(list).setLegend("用户操作次数").setType(ChartType.BAR);
		builder.setTitle(toLogString("月用户操作日志排行({}起)", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(beginTime)));
		return builder.build();
	}

	@Override
	public File export() throws Exception
	{
		DefaultFreemarkerContentInfo info = new DefaultFreemarkerContentInfo();
		info.setTemplateUrl("file:" + FileHelper.getWebappPath() + "/template/log.html").setAlwaysNew(true);
		info.setContents(generateData()).setSavePath(FileHelper.getTmpPath());
		return WordTemplateHelper.generateWord(info);
	}

	private Map<String, Object> generateData()
	{
		Random rand = new Random();
		int i = rand.nextInt(10);
		Pagination p = this.logRepo.pageAll(i, 16);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", p.getResult());
		Pagination p2 = this.logRepo.pageAll(i + 1, 16);
		map.put("datas2", p2.getResult());
		map.put("myFormatDateTime", new FormatDateTimeMethodModel());
		return map;
	}

}
