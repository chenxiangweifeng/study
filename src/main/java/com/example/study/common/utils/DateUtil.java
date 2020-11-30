package com.example.study.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * @author chenxiangweifeng
 * @date 2020-4-27
 */
@Slf4j
public class DateUtil {
	
	public static final String YMDHMS_DATE_FORMAT_T = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String YMDHMS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String YMD_DATE_FORMAT = "yyyy-MM-dd";
	public static final String YYYY_MM_MONTH_FORMAT = "yyyy-MM";
	public static final String CSV_DATE_FORMAT = "yyyyMMddHHmmss";
	public static final String YMDHMS_DATE_FORMAT_N = "yyyy/MM/dd HH:mm:ss";
	public static final String YMDHMS_ELASTIC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String YMDHMS_EIGHT_REGION_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00";
	/*** 1毫秒 */
	public static final long ONE_MILLI_SECOND = 1l;
	/*** 1秒 */
	public static final long ONE_SECOND = ONE_MILLI_SECOND * 1000;
	/*** 1分钟 */
	public static final long ONE_MINUTE = ONE_SECOND * 60;
	/*** 1小时 */
	public static final long ONE_HOUR = ONE_MINUTE * 60;
	/*** 1天 */
	public static final long ONE_DAY = ONE_HOUR * 24;
	
	public static String formatESDate(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(YMDHMS_ELASTIC_DATE_FORMAT).format(date);
	}
	public static String formatTimeRegionDate(Date date) {
		if (date == null) {
			return StringUtils.EMPTY;
		}
		return new SimpleDateFormat(YMDHMS_EIGHT_REGION_FORMAT).format(date);
	}
	
	public static Date praseDate(String dateStr) throws ParseException {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT).parse(dateStr);
	}
	
	public static Date praseESDate(String dateStr) throws ParseException {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		return new SimpleDateFormat(YMDHMS_ELASTIC_DATE_FORMAT).parse(dateStr);
	}
	
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT).format(date);
	}
	
	public static String formatYMDDate(Date date){
		if (date != null) {
			return new SimpleDateFormat(YMD_DATE_FORMAT).format(date);
		}else {
			return "";
		}
	}
	
	public static String formatYMDate(Date date){
		if (date != null) {
			return new SimpleDateFormat(YYYY_MM_MONTH_FORMAT).format(date);
		}else {
			return "";
		}
	}
	
	public static String formatYMDDateT(Date date){
		if (date != null) {
			return new SimpleDateFormat(YMDHMS_DATE_FORMAT_T).format(date);
		}else {
			return "";
		}
	}

	public static  Date format(String data,String parsePatterns){
		try {
			Date date = DateUtils.parseDate(data, parsePatterns);
			return date;
		} catch (ParseException e) {
			return null;
		}

	}
	
	public static String formatYMDDateT(BigInteger now){
		Date date = new Date(now.longValue());
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT_T).format(date);
	}
	
	public static String convertToESUTC(String timeStr) throws ParseException {
		if (StringUtils.isBlank(timeStr)) {
			return "";
		}
		Date date = new SimpleDateFormat(YMDHMS_DATE_FORMAT).parse(timeStr);
		return new SimpleDateFormat(YMDHMS_ELASTIC_DATE_FORMAT).format(date);
	}
	
	public static String convertToUTC(String timeStr) throws ParseException {
		if (StringUtils.isBlank(timeStr)) {
			return "";
		}
		Date date = new SimpleDateFormat(YMDHMS_DATE_FORMAT).parse(timeStr);
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT_T).format(date);
	}
	
	public static String convertESToGMT(String timeStr) {
		if (StringUtils.isBlank(timeStr)) {
			return "";
		}
		Date date = null;
		try {
			date = new SimpleDateFormat(YMDHMS_ELASTIC_DATE_FORMAT).parse(timeStr);
		} catch (ParseException e) {
			log.error(" convertESToGMT exception ", e);
		}
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT_T).format(date);
	}
	
	public static String convertESToGMT(Date time) {
		if (time == null) {
			return "";
		}
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT_T).format(time);
	}
	
	public static String convertToYMD(String timeStr) {
		if (StringUtils.isBlank(timeStr)) {
			return "";
		}
		Date date = new Date();
		try {
			date = new SimpleDateFormat(YMDHMS_ELASTIC_DATE_FORMAT).parse(timeStr);
		} catch (ParseException e) {
			log.error(" convertToYMD exception ", e);
		}
		return new SimpleDateFormat(YMD_DATE_FORMAT).format(date);
	}
	
	public static String convertToYMDHMS(String timeStr) {
		if (StringUtils.isBlank(timeStr)) {
			return "";
		}
		Date date = new Date();
		try {
			date = new SimpleDateFormat(YMDHMS_ELASTIC_DATE_FORMAT).parse(timeStr);
		} catch (ParseException e) {
			log.error(" convertToYMDHMS exception ", e);
		}
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT).format(date);
	}
	
	public static String convertToYM(String timeStr) {
		if (StringUtils.isBlank(timeStr)) {
			return "";
		}
		Date date = new Date();
		try {
			date = new SimpleDateFormat(YMDHMS_ELASTIC_DATE_FORMAT).parse(timeStr);
		} catch (ParseException e) {
			log.error(" convertToYM exception ", e);
		}
		return new SimpleDateFormat(YYYY_MM_MONTH_FORMAT).format(date);
	}

	
	/**
	 * 获取当天开始时间
	 * @return
	 */
	public static Date getCurrentDayStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	/**
	 * 获取当天结束时间
	 * @return
	 */
	public static Date getCurrentDayEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 获取某天的起始时间
	 * 获取当前时间day天之前的日期Date
	 * @param day
	 * @param date
	 * @return
	 */
	public static Date getOneDayOnset(Integer day, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1 * day);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取某天结束时间
	 * @param day
	 * @param date
	 * @return
	 */
	public static Date getOneDayEnd(Integer day, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1 * day);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 获取某天的起始时间
	 * 获取当前时间day天之前的日期Date
	 * @param date
	 * @param date
	 * @return
	 */
	public static Date getOneYearOnset(Integer month, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1 * month);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 两个时间的间隔（计算的数据如果不是整形会被四舍五入，请选择适当的间隔单元）
	 * 
	 * @param date1 日期
	 * @param date2 日期
	 * @param unit 间隔单元 如 ONE_HOUR(一小时) ONE_DAY(一天) ONE_YEAR(一年)
	 * @return 日期差值
	 */
	private static long compare(Date date1, Date date2, long unit) {
		long num1 = date1.getTime();
		long num2 = date2.getTime();
		return Math.round((num1 - num2) / (float)unit);
	}

	/**
	 * 两个时间的间隔（计算的数据如果不是整形会被进位）
	 *
	 * @param date1 日期
	 * @param date2 日期
	 * @param unit 间隔单元 如 ONE_HOUR(一小时) ONE_DAY(一天) ONE_YEAR(一年)
	 * @return 日期差值
	 */
	private static long compareCarryBit(Date date1, Date date2, long unit) {
		long num1 = date1.getTime();
		long num2 = date2.getTime();
		double tmp = Math.ceil((num1 - num2) / (double)unit);
		return new Double(tmp).longValue();
	}

	/**
	 *
	 * @param date1
	 * @param date2
	 * @return  月份相差几个月
	 * @throws ParseException
	 */
	public static int getMonthSpace(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(sdf.parse(date1));
		aft.setTime(sdf.parse(date2));
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		return Math.abs(month+result);

	}

	public static int getMonthSpaceYm(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(sdf.parse(date1));
		aft.setTime(sdf.parse(date2));
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		return Math.abs(month+result);

	}

	/**
	 * 两个时间的间隔的天数（计算的数据如果不是整形会被四舍五入）
	 * 
	 * @param date1 日期
	 * @param date2 日期
	 * @return 日期相差天数
	 */
	public static Long compareToDay(Date date1, Date date2) {
		return compare(date1, date2, ONE_DAY);
	}

	/**
	 * 两个时间的间隔的天数（计算的数据如果不是整形会进位1天）
	 *
	 * @param date1 日期
	 * @param date2 日期
	 * @return 日期相差天数
	 */
	public static Long compareToDayCarryBit(Date date1, Date date2) {
		return compareCarryBit(date1, date2, ONE_DAY);
	}

	/**
	 * 两个时间的间隔的天数（计算的数据如果不是整形会被四舍五入）
	 *
	 * @param date1 日期
	 * @param date2 日期
	 * @return 日期相差天数
	 */
	public static Long compareToMinute(Date date1, Date date2) {
		return compare(date1, date2, ONE_MINUTE);
	}


	/**
	 * 两个时间的间隔的天数（计算的数据如果不是整形会被四舍五入）
	 *
	 * @param date1 日期
	 * @param date2 日期
	 * @return 日期相差天数
	 */
	public static Long compareToSecond(Date date1, Date date2) {
		return compare(date1, date2, ONE_SECOND);
	}
	/**
	 * 当前时间加上15天时间
	 */
	public static String addFiftyDays() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 24 * 15);
		return formatDate(cal.getTime());
	}

	/**
	 * 获取当前时间,精确到秒
	 * @return
	 */
	public static String getCurrentTime2String(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int YY = cal.get(Calendar.YEAR) ;
		int MM = cal.get(Calendar.MONTH)+1;
		int DD = cal.get(Calendar.DATE);
		int HH = cal.get(Calendar.HOUR_OF_DAY);
		int mm = cal.get(Calendar.MINUTE);
		int SS = cal.get(Calendar.SECOND);
		String time = "" + YY + MM + DD + HH + mm + SS ;
		return time;
	}

	/**
	 * 获取当前时间,精确到微秒
	 * @return
	 */
	public static String getCurrentAccurateTime2String(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int YY = cal.get(Calendar.YEAR) ;
		int MM = cal.get(Calendar.MONTH)+1;
		int DD = cal.get(Calendar.DATE);
		int HH = cal.get(Calendar.HOUR_OF_DAY);
		int mm = cal.get(Calendar.MINUTE);
		int SS = cal.get(Calendar.SECOND);
		int MS = cal.get(Calendar.MILLISECOND);
		String time = "" + YY + MM + DD + HH + mm + SS + MS;
		return time;
	}
	
	/**
	 * 获取当天开始时间
	 * @return
	 */
	public static long getCurrentDayStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime().getTime();
	}
	
	/**
	 * 获取当天结束时间
	 * @return
	 */
	public static long getCurrentDayEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime().getTime();
	}
	
	/**
	 * 获取昨天开始时间
	 */
	public static Date getBeforeDayStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);  
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取昨天结束时间
	 */
	public static Date getBeforeDayEnd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);  
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 获取昨天结束时间
	 */
	public static Date getBeforeNDayEnd(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1*n);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * @date 2020-6-10
	 * 上（N）周前的开始时间
	 * @param week
	 * @return
	 */
	public static Date getBeforeNweekStart(int week){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-7*week);
		cal.set(Calendar.DAY_OF_WEEK,2); // 欧美人把周日当成第一天，我们把周一当成是每周的第一天
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		return cal.getTime();
	}

	/**
	 * @date 2020-6-10
	 * 上（N）周前的结束时间
	 * @param week
	 * @return
	 */
	public static Date getBeforeNweekEnd(int week){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,(week)*-7);
		cal.set(Calendar.DAY_OF_WEEK,1); //     // 第一天就是 Sunday 我们把周日当成最后一天
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,59);
		return cal.getTime();
	}

	
	/**
	 * 获取上月月初
	 */
	public static Date getBeforeMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);  
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取某月月初
	 */
	public static Date getMonthStart(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1 * month);  
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取某月月末
	 */
	public static Date getMonthEnd(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1 * month);  
		cal.set(Calendar.DAY_OF_MONTH, 0); 
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	/**
	 * 获取上月月末
	 */
	public static Date getBeforeMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);  
		cal.set(Calendar.DAY_OF_MONTH, 0); 
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	/**
	 * 获取当月月初
	 * @return
	 */
	public static Date getCurrentMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);  
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取当月月末
	 * @return
	 */
	public static Date getCurrentMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);  
		cal.set(Calendar.DAY_OF_MONTH, 0); 
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

    public static Date getBeforeNMonthTime(int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH,-num);
		Date beforeNMonthTime = cal.getTime();
		return beforeNMonthTime;
    }
    
    public static String formatCSVDate(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(CSV_DATE_FORMAT).format(date);
	}


	public static String formatYMDDateN(Date date){
		if (date != null) {
			return new SimpleDateFormat(YMDHMS_DATE_FORMAT_N).format(date);
		}else {
			return "";
		}
	}

	public static Date prasetYMDDateN(String dateStr) throws ParseException {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		return new SimpleDateFormat(YMDHMS_DATE_FORMAT_N).parse(dateStr);
	}

	public  static String utcToSystemDefault( String time,String  pattern){
		String  res ="";
		ZonedDateTime parse = ZonedDateTime.parse(time);
		if (parse !=null){
			ZonedDateTime zonedDateTime = parse.withZoneSameInstant(ZoneId.systemDefault());
			res = zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
		}
		return  res;
	}
	public static Date getFiveMinutesAfter() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 5);
		return cal.getTime();
	}
   /**
    * @description //当前时间的前后多少分钟比如当前时间前15分钟，前1小时
    */
	public static Date getCurrentDateAfter(int calendar,int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.add(calendar, minutes);
		return cal.getTime();
	}

	/**
	 * 获取开始时间
	 */
	public static Calendar getEndTime() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}

	/**
	 * 获取结束时间
	 */
	public static Calendar getStartTime(Calendar cal) {
		cal.add(Calendar.SECOND, -10);
		return cal;
	}

    public static void main(String [] args) throws ParseException {
//		Date beforeNMonthTime = getBeforeNMonthTime(12);
//		Date start = getBeforeDayStart();
//		Date end = getBeforeDayEnd();
//		System.out.println(DateUtil.formatDate(start));
//		System.out.println(formatYMDDateT(new Date()));
//		System.out.println(convertESToGMT("2019-11-01T10:52:30.725"));
//		String d = "2019-11-27";
//		Date date = (new SimpleDateFormat(YMD_DATE_FORMAT)).parse(d);
//		System.out.println(getCurrentDayStartDate().getTime() == date.getTime());
//		System.out.println(compareAfter(DateUtil.praseDate("2020-04-21 17:50:38"),new Date()));
	}

	public static Date getDateEnd(Date date) {
		if (date==null){
			return null;
		}
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		return calendar.getTime();
	}

	public static Date getDateBegin(Date date) {
		if (date==null){
			return null;
		}
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	}

	public static Date addYear(Date createTime, Integer useTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createTime);//设置起时间
		cal.add(Calendar.YEAR, useTime);//增加N年
		return cal.getTime();
	}
	public static Date addMonth(Date createTime, Integer useTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createTime);//设置起时间
		cal.add(Calendar.MONTH, useTime);//增加N月
		return cal.getTime();
	}
	/**
	 * 计算两个日期之间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1,Date date2){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}


	public static boolean compareBefore(Date praseDate, Date date) {
		return praseDate.before(date);
	}

	public static boolean compareAfter(Date praseDate, Date date) {
		return praseDate.after(date);
	}


	/**
	 * 获取本月日期
	 * @return
	 */
	public static String getMonth() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_MONTH_FORMAT);
		String time = format.format(c.getTime());
		return time;
	}

	/**
	 * 获取上月日期
	 * @return
	 */
	public static String getLastMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_MONTH_FORMAT);
		String time = format.format(c.getTime());
		return time;
	}


}
