/**
 * 
 */
package com.gome.autodeploy.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gome.autodeploy.domain.common.WeekDto;

/**
 * @author bailu-ds
 *
 */
public class DateUtil {
	
	
	private static List<WeekDto> getWeeksByYear(final int year){ 
		//实现思路，首先计算当年有多少个周，然后找到每个周的开始日期和结束日期 
		// Calendar calendar = new GregorianCalendar(); 
		// // 在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。 
		// calendar.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一 
		// calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //每周从周一开始 
		// 上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。 
		// calendar.setMinimalDaysInFirstWeek(7); //设置每周最少为7天 
		// calendar.set(Calendar.YEAR, year); // 设置年度为指定的年 
		// //首先计算当年有多少个周,每年都至少有52个周，个别年度有53个周 
		Calendar cl = Calendar.getInstance(); 
		int week = cl.get(Calendar.WEEK_OF_YEAR);
		int weeks = getWeekNumByYear(year); 
		// System.out.println(year+"共有"+weeks+"个周"); 
		List<WeekDto> listWeek = new ArrayList<WeekDto>();
		for (int i=(week-1); i<=weeks; i++) {
			WeekDto weekDto = new WeekDto();
			weekDto.setWeek(i);
			weekDto.setName(year + "年第" + i + "周("+getYearWeekFirstDay(year,i) + "-" + getYearWeekEndDay(year,i) +")");
			weekDto.setValue(year + "-" + i);
			listWeek.add(weekDto);
		}
		return listWeek;
	} 
	
	/** 
	* 计算指定年度共有多少个周。 
	* @param year 格式 yyyy ，必须大于1900年度 小于9999年 
	* @return 
	*/ 
	private static int getWeekNumByYear(final int year){ 
		int result = 52;//每年至少有52个周 ，最多有53个周。 
		String date = getYearWeekFirstDay(year, 53); 
		if(date.substring(0, 4).equals(year+"")){ //判断年度是否相符，如果相符说明有53个周。 
			result = 53; 
		} 
		return result; 
	} 

	private static String getYearWeekFirstDay(int yearNum,int weekNum) { 
		Calendar cal = Calendar.getInstance(); 
		cal.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一 
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//每周从周一开始 
		// 上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。 
		cal.setMinimalDaysInFirstWeek(7); //设置每周最少为7天 
		cal.set(Calendar.YEAR, yearNum); 
		cal.set(Calendar.WEEK_OF_YEAR, weekNum); 

		//分别取得当前日期的年、月、日 
		return getFormatDate(cal.getTime()); 
	} 

	/** 
	* 计算某年某周的结束日期 
	* @param yearNum 格式 yyyy ，必须大于1900年度 小于9999年 
	* @param weekNum 1到52或者53 
	* @return 日期，格式为yyyy-MM-dd 
	*/ 
	private static String getYearWeekEndDay(int yearNum,int weekNum) { 
		Calendar cal = Calendar.getInstance(); 
		cal.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一 
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//每周从周一开始 
		// 上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。 
		cal.setMinimalDaysInFirstWeek(7); //设置每周最少为7天 
		cal.set(Calendar.YEAR, yearNum); 
		cal.set(Calendar.WEEK_OF_YEAR, weekNum); 

		return getFormatDate(cal.getTime()); 
	}
	
	private static String getFormatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		return format.format(date);
	}
	
	public static List<WeekDto> getWeekList() {
		Calendar cal = Calendar.getInstance();
		return getWeeksByYear(cal.get(Calendar.YEAR));
	} 
	
	public static void main(String[] args) {
		for (WeekDto dto : getWeekList()) {
			System.out.println("----------------");
			System.out.println(dto.getWeek());
			System.out.println(dto.getName());
			System.out.println(dto.getValue());
		}
	}
	
}
