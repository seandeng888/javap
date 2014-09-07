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
 * Title:�������ڡ�ʱ�䴦���ʵ����
 * </pre>
 * @author dengsc
 * @version 1.00.00
 * <pre>
 * �޸ļ�¼
 *    �޸ĺ�汾:     �޸��ˣ�  �޸�����:     �޸�����: 
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
     * ��ָ����ʽȡϵͳ��ǰʱ��
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
     * ����������ַ�������ת��Ϊyyyy-mm-dd��ʽ������
     * @param date
     * @return Date
     */
    public static Date getSelectDate(String date)  throws Exception {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    
    	return new Date(sdf.parse(date).getTime());
    }

    /**
     * ��Ĭ�ϸ�ʽȡϵͳ��ǰʱ�䣬 Ĭ�ϸ�ʽΪ��yyyyMMddHHmmss
     * 
     * @return String
     */
    public static String getDateTime() {
        return getDateTime("yyyyMMddHHmmss");
    }

    // ȡ��ϵͳ��ǰʱ��,yyyyMMdd
    public static String getDate() {
        return getDateTime("yyyyMMdd");
    }

    // ȡ��ϵͳ��ǰʱ�䣬HHmmss
    public static String getTime() {
        return getDateTime("HHmmss");
    }

    /**
     * �Ƿ�Ϊ�Ϸ��������ַ���
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
     * / ��ʱ��ת��Ϊ�ִ� ��ʽ��yyyy-MM-dd HH:mm:ss @param date ��ת����ʱ�� @return
     */
    public static String date2Str(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return date2Str(date, format);
    }

    /***************************************************************************
     * / ��ʱ��ת��Ϊ�ִ� ��ʽ��yyyy-MM-dd  @param date ��ת����ʱ�� @return
     */
    public static String date3Str(Date date) {
        String format = "yyyy-MM-dd";
        return date2Str(date, format);
    }
    /**
     * ��ʱ��ת��Ϊ�ִ�
     * 
     * @param date
     *            ��ת����ʱ��
     * @param format
     *            ת����ʽ
     * @return String
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * ��ʱ���ʽ��һ�ָ�ʽת��Ϊ��һ�ָ�ʽΪ�ִ�, �ɸ�ʽĬ��Ϊ "yyyyMMddHHmmss"
     * 
     * @param dateStr
     *            String �����ַ���
     * @param newFormat
     *            String �¸�ʽ
     * @throws ParseException
     * @return String
     */
    public static String formatDateTime(String dateStr, String newFormat) {
        return formatDateTime(dateStr, newFormat, "yyyyMMddHHmmss");
    }

    /**
     * ��ʱ���ʽ��һ�ָ�ʽת��Ϊ��һ�ָ�ʽΪ�ִ�
     * 
     * @param dateStr
     *            String �����ַ���
     * @param newFormat
     *            String �¸�ʽ
     * @param oldFormat
     *            String �ɸ�ʽ
     * @throws ParseException
     * @return String
     */
    public static String formatDateTime(String dateStr, String newFormat, String oldFormat) {
        Date date = str2Date(dateStr, oldFormat);
        return date2Str(date, newFormat);
    }

    /**
     * ���ִ�ת��Ϊ����
     * 
     * @param sDate
     *            �ִ���ʽ������
     * @param format
     *            �ִ���ʽ
     * @return ת��Ϊ��������
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
     * ȡĳһ�������� n ֵ�������, n �� dateField �������ꡢ�¡��� ��������or���ٵ�ʱ��õ��µ�����
     * 
     * @param date
     *            Date ��������
     * @param counts
     *            int ��������ֵ
     * @param dateField
     *            int ������������ֶ�, ȡֵ��Χ���� Calendar.YEAY �� Calendar.MONTH ��
     *            Calendar.DATE �� .... Calendar.SECOND ��
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
     * ���������� n ��
     * 
     * @param date
     *            Date ��������,���Ϊnull��ȡ��ǰ����
     * @param days
     *            int ����������
     * @return Date
     */
    public static Date addDate(Date date, int days) {
        return addDate(date, days, Calendar.DATE);
    }

    /**
     * ���ַ������������� n ��
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
     * ���������� n ����
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
     * ���ַ������������� n ����
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
     * ȡ�������һ�� ��ȡ����������,�ټ�һ,����ĩ
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
     * ȡ�������һ�� ��ȡ����������,�ټ�һ,����ĩ
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
     * �õ�����
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
     * ��ȡĳ�յ�ǰ����
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
     * ������ʼ��������ֹ���ڼ��㹤�����ڵ�����
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
     * ��java.util.Date��java.sql.Date������ת��
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
     * ���ĳһ���ڵĺ�һ��
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

    // ���ĳһ���ڵĺ�n��.
    public static Date getNextNDate(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + n);
        return getSqlDate(calendar.getTime());
    }

    /**
     * ���ĳһ���ڵ�ǰһ��
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
     * ���ĳ��ĳ�µ�һ�������
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
     * ���ĳ��ĳ�����һ�������
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
     * �������չ���java.sql.Date����
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
     * ȡ��ĳ�µ�����
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
     * ���ĳ��ĳ���ȵ����һ�������
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
     * ���ĳ��ĳ���ȵĵ�һ�������
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
     * ���ĳ��ĵ�һ�������
     * 
     * @param year
     * @return Date
     */
    public static Date getFirstDayOfYear(int year) {
        return getFirstDayOfMonth(year, 1);
    }

    /**
     * ���ĳ������һ�������
     * 
     * @param year
     * @return Date
     */
    public static Date getLastDayOfYear(int year) {
        return getLastDayOfMonth(year, 12);
    }

    /**
     * String��java.sql.Date������ת��
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
     * �ַ���ת��Ϊ����java.util.Date
     * 
     * @param dateText
     *            �ַ���
     * @param format
     *            ���ڸ�ʽ
     * @param lenient
     *            ����Խ���־
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
     * �ַ���ת��Ϊ����java.util.Date
     * 
     * @param dateString
     *            �ַ���
     * @param format
     *            ���ڸ�ʽ
     * @return Date
     */
    public static Date stringToDate(String dateString, String format) {
        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * �����ַ�����ʽת��
     * 
     * @param src
     *            �����ַ���
     * @param srcfmt
     *            Դ���ڸ�ʽ
     * @param desfmt
     *            Ŀ�����ڸ�ʽ
     * @return String
     */
    public static String stringToString(String src, String srcfmt, String desfmt) {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }

    /**
     * ��������-��������
     * 
     * @param date
     * @param mnt
     * @return java.sql.Date
     */
    public static java.sql.Date dateIncreaseByMonth(java.sql.Date date, int mnt) {
        // ��sql.dataת��Ϊutil.data
        java.util.Date utilDate = new java.util.Date(date.getTime());
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(utilDate);
        cal.add(Calendar.MONTH, mnt);
        utilDate = (java.util.Date) cal.getTime();
        // ��util.dataת��Ϊsql.data
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    /**
     * 
     * ���ݹ��������ж����ܼ�
     * 
     * @param date
     *            ��������
     * @return int�͵��ܼ�
     */
    public static int getDayOfWeek(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // ��ΪJAVA�е����ڱ�ʵ�����ڴ�һ�����Լ�1�����
        return (calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    /**
     * ���ĳ��ĳ���ǲ�������һ������
     * @param year ��
     * @param month ��
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
     * ��ȡ��ʱ��
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
