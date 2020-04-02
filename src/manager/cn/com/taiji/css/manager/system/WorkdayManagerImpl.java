package cn.com.taiji.css.manager.system;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.model.echart.AxisTimeSplitter;
import cn.com.taiji.common.model.echart.AxisTimeUnit;
import cn.com.taiji.css.model.system.Workday;

/**
 * @author wanglijun
 * 		   Create Time 2016年7月20日 下午3:43:50
 */
@Service("workdayManager")
public class WorkdayManagerImpl extends AbstractManager implements WorkdayManager
{

	@Override
	public List<Workday> listByMonth(int year, Month month) {
		LocalDate date = LocalDate.of(year, month, 1);
		LocalDate from=date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
		LocalDate to=date.with(TemporalAdjusters.lastDayOfMonth()).with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		AxisTimeSplitter splitter=AxisTimeSplitter.of(from.atTime(0, 0, 0), to.atTime(23, 59, 59), AxisTimeUnit.DAY);
		return splitter.rawSplit().stream()
				.map(ldt -> ldt.toLocalDate())
				.map(ld -> new Workday(ld, isWorkday(ld)))
				.collect(Collectors.toList());
	}
	
	@Override
	public LocalDate nextWorkday(LocalDate day){
		return relativeWorkday(day, ld->ld.plus(1,ChronoUnit.DAYS));
	}
	
	
	@Override
	public LocalDate prevWorkday(LocalDate day){
		return relativeWorkday(day, ld->ld.minus(1,ChronoUnit.DAYS));
	}
	
	@Override
	public int countWorkday(LocalDate from,LocalDate to){
		AxisTimeSplitter splitter=AxisTimeSplitter.of(from.atTime(0, 0, 0), to.atTime(23, 59, 59), AxisTimeUnit.DAY);
		return (int)splitter.rawSplit().stream().filter(ldt->isWorkday(ldt.toLocalDate())).count();
	}
	
	private LocalDate relativeWorkday(LocalDate day,Function<LocalDate,LocalDate> f){
		LocalDate nextDay=f.apply(day);
		while(!isWorkday(nextDay)){
			nextDay=f.apply(nextDay);
		}
		return nextDay;
	}
	
	@Override
	public boolean isWorkday(LocalDate date){
		return date.getDayOfWeek()!=DayOfWeek.SATURDAY&&date.getDayOfWeek()!=DayOfWeek.SUNDAY;
	}
	
	
	
	public static void main(String[] args) {
		WorkdayManagerImpl m=new WorkdayManagerImpl();
		System.out.println(m.listByMonth(2015, Month.NOVEMBER));
		System.out.println(m.listByMonth(2015, Month.JUNE));
		System.out.println(m.listByMonth(2015, Month.FEBRUARY));//4周
		System.out.println(m.nextWorkday(LocalDate.of(2015, 06, 05)));
		System.out.println(m.nextWorkday(LocalDate.of(2015, 06, 06)));
		System.out.println(m.nextWorkday(LocalDate.of(2015, 06, 07)));
		System.out.println(m.countWorkday(LocalDate.of(2015, 06, 5), LocalDate.of(2015, 06, 8)));
	}
}
