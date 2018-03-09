package com.nantian.util.str;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author:zy
 * @createTime:2013-11-9上午11:28:34
 * @version:1.0
 * @Description	: 公共日期工具类
 */
public class DateUtil {
	
	private static SimpleDateFormat formatdate = new SimpleDateFormat("yyyyMMdd");
	
	public static String getTimeStr(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.HOUR_OF_DAY));
		sb.append(cal.get(Calendar.MINUTE));
		sb.append(cal.get(Calendar.SECOND));
		return sb.toString();
	}
	
	public static boolean checkIsSame(int field, Date date1, Date date2){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int field1 = cal.get(field);
		cal.setTime(date2);
		int field2 = cal.get(field);
		if(field1==field2){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 返回date加上count天所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减天数
	 * @return
	 */
	public static Date addDay(Date date, int count) {
		return compute(date, count, 5);
	}
	
	/**
	 * 返回date加上count分所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减天数
	 * @return
	 */
	public static Date addMiniute(Date date, int count) {
		return compute(date, count, Calendar.MINUTE);
	}
	
	/**
	 * 返回date加上count周所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减天数
	 * @return
	 */
	public static Date addWeek(Date date, int count) {
		return  compute(date, count, 3);
	}
	
	/**
	 * 返回date加上count月所对应的日期,count可以为正或负数
	 * @param date 输入日期
	 * @param count 增减天数
	 * @return
	 */
	public static Date addMonth(Date date, int count) {
		return compute(date, count,2);
	}
	/**
	 * 把日期由字符串转化为Date类型
	 * @param str 日期字符串
	 * @param format 字符串的格式,如yyyyMMdd
	 * @return
	 */
	public static Date parse(String str, String format) {
		
		if(str==null)return null;

		SimpleDateFormat fm = new SimpleDateFormat(format);
		Date curDate;
		
		try {
			curDate = fm.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("can not parse ["+str+"] to a 'Date' using format["+format+"]");
		}

		return curDate;
	}
	
	/**
	 * 把Date转化为String
	 * @param date Date实例
	 * @param format 字符串的格式,如yyyyMMdd
	 * @return
	 */
	public static String format(Date date, String format) {
		if(date==null){
			return "";
		}
		
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(date);
	}
	
	public static String getTime(long time) {
	  String str = "" ;
	  time = time / 1000;
	  int s = (int) (time % 60);
	  int m = (int) (time / 60 % 60);
	  int h = (int) (time / 3600);
	  str = h + ":" + m + ":" + s ;  
	  return str ;
	 }
	
	/**
	 * 计算两个时间之间间隔的分钟数
	 * @return
	 */
	public static long getTimeDifMinutes(Date date1, Date date2){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long minute=(time1-time2)/(1000*60);//转化minute
	    return minute;
	}
	
	private  static Date compute(Date date, int count, int periodFlag) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(periodFlag, count);
		return gc.getTime();
	}
	
	public static int getWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int month= cal.get(Calendar.MONTH)+1;
		return month;
	}
	
	public static boolean synchronizedTime(Date time){
		try {
			String dateStr = DateUtil.format(time, "yyyy-MM-dd");
			String timeStr = DateUtil.format(time, "HH:mm:ss");

			System.out
					.println("调整前的系统时间为["
							+ DateUtil.format(new Date(),
									"yyyy-MM-dd HH:mm:ss") + "]");
			Process p = Runtime.getRuntime().exec("cmd.exe /c date " + dateStr);
			int r = p.waitFor();
			if (r == 0) {
				System.out.println("调整日期成功!");
			} else {
				System.out.println("调整日期失败!");
			}
			p = Runtime.getRuntime().exec("cmd.exe /c time " + timeStr);
			r = p.waitFor();
			if (r == 0) {
				System.out.println("调整时间成功!");
			} else {
				System.out.println("调整时间失败!");
			}
			System.out
					.println("较准后的系统时间为["
							+ DateUtil.format(new Date(),
									"yyyy-MM-dd HH:mm:ss") + "]");
			
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public static String getCurrentDate() {
		
		Date d = new Date();
		return formatdate.format(d);
	}
	
	/**
	 * 取两日期间的天数
	 * date1-date2
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static long getDateDiff(String date1, String date2) throws Exception{
		try {
			long day=0;
			if(date1 != null && !date1.equals("null") && date2 != null && !date2.equals("null")  )
			{
				Date tmpdate1 = formatdate.parse(date1);
				Date tmpdate2 = formatdate.parse(date2);
				day = (tmpdate1.getTime() - tmpdate2.getTime())
							/ (24 * 60 * 60 * 1000);
			}
			if(day<0){
				day=-day;
			}
			return day;
		} catch (ParseException e) {
			throw new Exception("取两日期间的天数出错",e);
		}
	}
	
	/**
	 * 判断业务按钮是否在营业时间内
	 * @param opentime
	 * @return
	 */
	public static boolean isOpenTime(String opentime){
		if (opentime == null) {
			return false;
		}

		if (opentime.trim().length() != 8 || !opentime.trim().matches("\\d+")) {
			return false;
		}
		int start = Integer.parseInt(opentime.substring(0, 2)) * 60 + Integer.parseInt(opentime.substring(2, 4));
		int end = Integer.parseInt(opentime.substring(4, 6)) * 60 + Integer.parseInt(opentime.substring(6, 8));

		if (end - start <= 0) {
			return false;
		}

		String dateStr = new SimpleDateFormat("HHmm").format(new Date(System.currentTimeMillis()));
		int now = Integer.parseInt(dateStr.substring(0, 2)) * 60 + Integer.parseInt(dateStr.substring(2, 4));

		if (now >= start && now <= end) {
			return true;
		} else {
			return false;
		}
	}
}