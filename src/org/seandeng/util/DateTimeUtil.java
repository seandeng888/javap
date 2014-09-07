package org.seandeng.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 
 * <pre>
 * Title:用于日期、时间处理的实用类
 * </pre>
 * @author dengsc
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class DateTimeUtil {
    

    /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of
     * December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th
     * day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    public static String ISO_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;
    
    /**
     * 按指定格式取系统当前时间
     * 
     * @param format
     * @return String
     */
    public static String getDateTime(String format) {
    	
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
        String strDate = sdf.format(new java.util.Date());
        return strDate.toString();
    }
    
    /**
     * 根据输入的字符串日期转换为yyyy-mm-dd格式日期类
     * @param date
     * @return Date
     */
    public static Date getSelectDate(String date)  throws Exception {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
    	return new Date(sdf.parse(date).getTime());
    }

    /**
     * 按默认格式取系统当前时间， 默认格式为：yyyyMMddHHmmss
     * 
     * @return String
     */
    public static String getDateTime() {
        return getDateTime("yyyyMMddHHmmss");
    }

    // 取得系统当前时间,yyyyMMdd
    public static String getDate() {
        return getDateTime("yyyyMMdd");
    }

    // 取得系统当前时间，HHmmss
    public static String getTime() {
        return getDateTime("HHmmss");
    }

    /**
     * 是否为合法的日期字符串
     * 
     * @param strDate
     * @param format
     * @return boolean
     */
    public static boolean isValidDate(String strDate, String format) {
    	
        boolean islegal = false;
        try {
            String newDate = date2Str(str2Date(strDate, format), format);
            if (newDate.equals(strDate)) {
                islegal = true;
            }
        }
        catch (Exception e) {
            return false;
        }

        return islegal;
    }

    /***************************************************************************
     * / 把时间转换为字串 格式：yyyy-MM-dd HH:mm:ss @param date 待转换的时间 @return
     */
    public static String date2Str(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return date2Str(date, format);
    }

    /***************************************************************************
     * / 把时间转换为字串 格式：yyyy-MM-dd  @param date 待转换的时间 @return
     */
    public static String date3Str(Date date) {
        String format = "yyyy-MM-dd";
        return date2Str(date, format);
    }
    /**
     * 把时间转换为字串
     * 
     * @param date
     *            待转换的时间
     * @param format
     *            转换格式
     * @return String
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 把时间格式由一种格式转换为另一种格式为字串, 旧格式默认为 "yyyyMMddHHmmss"
     * 
     * @param dateStr
     *            String 日期字符串
     * @param newFormat
     *            String 新格式
     * @throws ParseException
     * @return String
     */
    public static String formatDateTime(String dateStr, String newFormat) {
        return formatDateTime(dateStr, newFormat, "yyyyMMddHHmmss");
    }

    /**
     * 把时间格式由一种格式转换为另一种格式为字串
     * 
     * @param dateStr
     *            String 日期字符串
     * @param newFormat
     *            String 新格式
     * @param oldFormat
     *            String 旧格式
     * @throws ParseException
     * @return String
     */
    public static String formatDateTime(String dateStr, String newFormat, String oldFormat) {
        Date date = str2Date(dateStr, oldFormat);
        return date2Str(date, newFormat);
    }

    /**
     * 把字串转换为日期
     * 
     * @param sDate
     *            字串形式的日期
     * @param format
     *            字串格式
     * @return 转换为日期类型
     * 
     * @throws ParseException
     */
    public static Date str2Date(String sDate, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(sDate);
        }
        catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 取某一日期增减 n 值后的日期, n 由 dateField 决定是年、月、日 根据增加or减少的时间得到新的日期
     * 
     * @param date
     *            Date 参照日期
     * @param counts
     *            int 增减的数值
     * @param dateField
     *            int 需操作的日期字段, 取值范围如下 Calendar.YEAY 年 Calendar.MONTH 月
     *            Calendar.DATE 日 .... Calendar.SECOND 秒
     * @return Date
     */
    public static Date addDate(Date date, int counts, int dateField) {
        GregorianCalendar curGc = new GregorianCalendar();

        if (date != null){
            curGc.setTime(date);
        }

        curGc.add(dateField, counts);

        return curGc.getTime();
    }

    /**
     * 将日期增减 n 天
     * 
     * @param date
     *            Date 参照日期,如果为null则取当前日期
     * @param days
     *            int 增减的天数
     * @return Date
     */
    public static Date addDate(Date date, int days) {
        return addDate(date, days, Calendar.DATE);
    }

    /**
     * 将字符串型日期增减 n 天
     * 
     * @param date
     *            String
     * @param days
     *            int
     * @param format
     *            String
     * @return String
     */
    public static String addDate(String date, int days, String format) {
        return date2Str(addDate(str2Date(date, format), days, Calendar.DATE), format);
    }

    /**
     * 将日期增加 n 个月
     * 
     * @param date
     *            Date
     * @param months
     *            int
     * @return Date
     */
    public static Date addMonth(Date date, int months) {
        return addDate(date, months, Calendar.MONTH);
    }

    /**
     * 将字符串型日期增加 n 个月
     * 
     * @param date
     *            String
     * @param months
     *            int
     * @param format
     *            String
     * @return String
     */
    public static String addMonth(String date, int months, String format) {
        return date2Str(addDate(str2Date(date, format), months, Calendar.MONTH), format);
    }

    /**
     * 取得月最后一天 先取得下月月首,再减一,得月末
     * 
     * @param date
     *            String
     * @return String
     */
    public static String lastDateOfMonth(String date) {
        return date2Str(addDate(addDate(str2Date(date.substring(0, 6) + "01", "yyyyMMdd"), 1,
                Calendar.MONTH), -1, Calendar.DATE), "yyyyMMdd");
    }
    
    /**
     * 取得月最后一天 先取得下月月首,再减一,得月末
     * 
     * @param date YYYY-MM
     *            
     * @return String
     */
    public static String lastDateOfMonth2(String date) {
        return date2Str(addDate(addDate(str2Date(date.substring(0, 7) + "-01", "yyyy-MM-dd"), 1,
                Calendar.MONTH), -1, Calendar.DATE), "yyyy-MM-dd");
    }

    /**
     * 得到星期
     * 
     * @param date
     *            String
     * @return String
     */
    public static String getWeekDay(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("E");
        return formatter.format(str2Date(date, "yyyyMMdd"));
    }
    
    /**
     * 获取某日的前几日
     * 
     * @param date
     * @param i
     * @return Date
     */
    public static Date getPreviousDate(Date date, long i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, (int) (day - i));
        return DateTimeUtil.getSqlDate(calendar.getTime());
    }

    /**
     * 根据起始日期与终止日期计算工作日期的天数
     * 
     * @param startDate
     * @param endDate
     * @return long
     * @throws Exception
     */
    public static long calculateDate(String startDate, String endDate) throws Exception {
        long DAY = 24L * 60L * 60L * 1000L;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return (df.parse(endDate).getTime() - df.parse(startDate).getTime()) / DAY;
    }

    public static String getLocalTimeString() {
        Calendar calendar = Calendar.getInstance();
        java.text.SimpleDateFormat smd = new SimpleDateFormat(ISO_DATETIME_PATTERN);

        String workDate = smd.format(calendar.getTime());
        return workDate;
    }

    /**
     * 由java.util.Date到java.sql.Date的类型转换
     * 
     * @param date
     * @return Date
     */
    public static Date getSqlDate(java.util.Date date) {
        return new Date(date.getTime());
    }

    public static Date nowDate() {
        Calendar calendar = Calendar.getInstance();
        return getSqlDate(calendar.getTime());
    }

    /**
     * 获得某一日期的后一天
     * 
     * @param date
     * @return Date
     */
    public static Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        return getSqlDate(calendar.getTime());
    }

    // 获得某一日期的后n天.
    public static Date getNextNDate(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + n);
        return getSqlDate(calendar.getTime());
    }

    /**
     * 获得某一日期的前一天
     * 
     * @param date
     * @return Date
     */
    public static Date getPreviousDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return getSqlDate(calendar.getTime());
    }

    /**
     * 获得某年某月第一天的日期
     * 
     * @param year
     * @param month
     * @return Date
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        return getSqlDate(calendar.getTime());
    }

    /**
     * 获得某年某月最后一天的日期
     * 
     * @param year
     * @param month
     * @return Date
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        return getPreviousDate(getSqlDate(calendar.getTime()));
    }

    /**
     * 由年月日构建java.sql.Date类型
     * 
     * @param year
     * @param month
     * @param date
     * @return Date
     */
    public static Date buildDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return getSqlDate(calendar.getTime());
    }

    /**
     * 取得某月的天数
     * 
     * @param year
     * @param month
     * @return int
     */
    public static int getDayCountOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 0);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得某年某季度的最后一天的日期
     * 
     * @param year
     * @param quarter
     * @return Date
     */
    public static Date getLastDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = quarter * 3;
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 获得某年某季度的第一天的日期
     * 
     * @param year
     * @param quarter
     * @return Date
     */
    public static Date getFirstDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = (quarter - 1) * 3 + 1;
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 获得某年的第一天的日期
     * 
     * @param year
     * @return Date
     */
    public static Date getFirstDayOfYear(int year) {
        return getFirstDayOfMonth(year, 1);
    }

    /**
     * 获得某年的最后一天的日期
     * 
     * @param year
     * @return Date
     */
    public static Date getLastDayOfYear(int year) {
        return getLastDayOfMonth(year, 12);
    }

    /**
     * String到java.sql.Date的类型转换
     * 
     * @param param
     * @return Date
     */
    public static java.sql.Date stringToDate(String param) {
        if ((param == null) || (param.equals(""))) {
            return null;
        } else {
            java.util.Date date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(param);
                return new java.sql.Date(date.getTime());
            }
            catch (ParseException ex) {
                return null;
            }
        }
    }

    public static String dateToString(Date date, String pattern) {
    	
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);
            return sfDate.format(date);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转换为日期java.util.Date
     * 
     * @param dateText
     *            字符串
     * @param format
     *            日期格式
     * @param lenient
     *            日期越界标志
     * @return Date
     */
    public static Date stringToDate(String dateText, String format, boolean lenient) {
        if (dateText == null) {
            return null;
        }
        DateFormat df = null;
        try {
            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }

            df.setLenient(false);
            return getSqlDate(df.parse(dateText));
        } catch (ParseException e)  {
            return null;
        }
    }

    /**
     * 字符串转换为日期java.util.Date
     * 
     * @param dateString
     *            字符串
     * @param format
     *            日期格式
     * @return Date
     */
    public static Date stringToDate(String dateString, String format) {
        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * 日期字符串格式转换
     * 
     * @param src
     *            日期字符串
     * @param srcfmt
     *            源日期格式
     * @param desfmt
     *            目标日期格式
     * @return String
     */
    public static String stringToString(String src, String srcfmt, String desfmt) {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }

    /**
     * 日期增加-按月增加
     * 
     * @param date
     * @param mnt
     * @return java.sql.Date
     */
    public static java.sql.Date dateIncreaseByMonth(java.sql.Date date, int mnt) {
        // 将sql.data转换为util.data
        java.util.Date utilDate = new java.util.Date(date.getTime());
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(utilDate);
        cal.add(Calendar.MONTH, mnt);
        utilDate = (java.util.Date) cal.getTime();
        // 将util.data转换为sql.data
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    /**
     * 
     * 根据工作日期判断是周几
     * 
     * @param date
     *            工作日期
     * @return int型的周几
     */
    public static int getDayOfWeek(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 因为JAVA中的星期比实际星期大一，所以减1则完成
        return (calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    /**
     * 获得某年某月是不是属于一个季度
     * @param year 年
     * @param month 月
     * @return Date
     */
    public static Date quarterLastDate(int year, int month) {
        int quarter = 0;
        if (month >= 1 && month <= 3){
            quarter = 1;
        }
        if (month >= 4 && month <= 6){
            quarter = 2;
        }
        if (month >= 7 && month <= 9){
            quarter = 3;
        }
        if (month >= 10 && month <= 12){
            quarter = 4;
        }
        return DateTimeUtil.getLastDayOfQuarter(year, quarter);
    } 

    /**
     * 获取短时间
     * 
     * 
     * @param workDate
     * @return String
     */
    public static String getShortWorkDate(String workDate) {
        String year = workDate.substring(0, 4);
        String month = workDate.substring(5, 7);
        String day = workDate.substring(8, 10);
        return year + month + day;
    }
}
