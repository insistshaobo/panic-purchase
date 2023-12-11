package utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.util.TextUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期处理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
    /**
     * 时间格式(yyyyMMdd)
     */
    public final static String DATE_PATTERN_COMMON = "yyyyMMdd";
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy.MM.dd时)
     */
    public final static String DATE_PATTERN_DELIVERY_TIME = "yyyy.MM.dd";
    /**
     * 时间格式(yyyy.M.d时)
     */
    public final static String DATE_PATTERN_TIME = "yyyy.M.d";
    /**
     * 提货申请时间格式(yyyy-MM-dd HH时)
     */
    public final static String DATE_PATTERN_DELIVERY_TIME_TWO = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式(yyyy.MM.dd HH:mm:ss)
     */
    public final static String DATE_TIME_MINUTE_SECOND_PATTERN = "yyyy.MM.dd HH:mm:ss";
    /**
     * 时间格式(yyyy-MM-dd HH:mm)
     */
    public final static String DATE_TIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    /**
     * 时间格式(yyyy-MM)
     */
    public final static String DATE_TIME_YEAR_MONTH = "yyyy-MM";
    /**
     * 时间格式(yyyy/MM/dd HH:mm)
     */
    public final static String DATE_TIME_PATTERN_MINUTE = "yyyy/MM/dd HH:mm";

    public final static String DATE_TIME_PATTERN_DATE = "yyyy/MM/dd";

    /**
     * 时间格式(yyyy.MM.dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN_POINT = "yyyy.MM.dd HH:mm:ss";
    /**
     * 时间格式(yyyy.MM.dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN_ONE = "yyyy.MM.dd HH:mm:ss";

    public final static String DAY_TIME_PATTERN_ONE = "dd日HH时mm分ss秒";
    /**
     * 时间格式(MM月dd日 HH:mm)
     */
    public final static String DATE_TIME = "MM月dd日 HH:mm";

    /**
     * 时间格式(MM月dd日 HH:mm)
     */
    public final static String DATE_MONTH_DAY = "MM月dd日";

    /**
     * 时间格式(M月d日 )
     */
    public final static String DATE_MONTH_DAY_M_D = "M月d日";

    /**
     * 时间格式(MM.dd)
     */
    public final static String DATE_MONTH = "MM.dd";

    /**
     * 时间格式(yyyy年MM月dd日)
     */
    public final static String DATE_TIME_PATTERN_1 = "yyyy年MM月dd日";
    public final static String DATE_TIME_PATTERN_5 = "yyyy年MM月dd";

    public final static String DATE_TIME_PATTERN_10 = "yyyy年MM月";

    /**
     * 时间格式(yyyy年MM月dd日 HH:mm)
     */
    public final static String DATE_TIME_PATTERN_3 = "yyyy年MM月dd日 HH:mm";

    /**
     * 时间格式(yyyyMMdd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN_THREE = "yyyyMMdd HH:mm:ss";

    public final static String DATE_TIME_PATTERN_MINUTE_POINT = "yyyy.MM.dd HH:mm";

    public static final int MILLIS_IN_HOURS = 60 * 60 * 1000;
    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

    /**
     * 时间格式(yyyy.MM.dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN_2 = "yyyyMMddHHmmss";

    /**
     * 时间格式(yyyy.MM.dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN_4 = "yyyyMMddHHmm";

    /**
     * 时间格式(yyyy)
     */
    public final static String YEAR = "yyyy";

    /**
     * 时间格式(MM)
     */
    public final static String MONTH = "MM";

    /**
     * 时间格式：HH:mm
     */
    public final static String DATE_TIME_PATTERN_TIME = "HH:mm";
    public final static String DATE_HOUR_PATTERN_TIME = "H:mm";
    public final static String DATE_TIME_PATTERN_TIME_SECEND = "HH:mm:ss";
    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Long timeStamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String formatMinAndSec(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }


    /**
     * 日期解析
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回Date
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     *
     * @param week 周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return 返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }


    /**
     * 分秒不为0自动加小时返回
     * @param date
     * @param hours
     * @return
     */
    public static String addDateHoursMinAndSecIsNotZero(Date date, int hours) {
        String time="";
        int minutes = Integer.valueOf(new SimpleDateFormat("mm").format(date));
        int seconds = Integer.valueOf(new SimpleDateFormat("ss").format(date));
        if(minutes>0||seconds>0){
            date=addDateHours(date,1);
        }
        time=DateUtils.format(date,DateUtils.DATE_PATTERN_DELIVERY_TIME)+" "+new SimpleDateFormat("HH").format(date);

       return time;
    }


    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static Integer curreatYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        Integer curreatYear = Integer.valueOf(sdf.format(date));
        return curreatYear;
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static Integer curreatYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Integer curreatYear = Integer.valueOf(sdf.format(date));
        return curreatYear;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String currentMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(date);
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static String currentDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(date);
    }

    /**
     * true 代表平年 false代表闰年
     *
     * @return
     */
    public static Integer bissextileOrleapyear() {
        Integer currentYear = curreatYear();
        if (currentYear % 4 == 0 && currentYear % 100 != 0 || currentYear % 400 == 0) {
            //该年是闰年
            return 366;
        } else {
            //该年是平年
            return 365;
        }
    }

    /**
     * 将最近日期转换成 今天 昨天 明天 eg:今天 13:08
     *
     * @param date
     * @return
     */
    public static String getDateToString(Date date) {
        SimpleDateFormat format;
        String hintDate = "";
        //先获取年份
        int year = Integer.valueOf(new SimpleDateFormat("yyyy").format(date));
        //获取一年中的第几天
        int day = Integer.valueOf(new SimpleDateFormat("d").format(date));
        //获取当前年份 和 一年中的第几天
        Date currentDate = new Date(System.currentTimeMillis());
        int currentYear = Integer.valueOf(new SimpleDateFormat("yyyy").format(currentDate));
        int currentDay = Integer.valueOf(new SimpleDateFormat("d").format(currentDate));
        //计算 如果是去年的
        if (currentYear - year == 1) {
            //如果当前正好是 1月1日 计算去年有多少天，指定时间是否是一年中的最后一天
            if (currentDay == 1) {
                int yearDay;
                if (year % 400 == 0) {
                    yearDay = 366;//世纪闰年
                } else if (year % 4 == 0 && year % 100 != 0) {
                    yearDay = 366;//普通闰年
                } else {
                    yearDay = 365;//平年
                }
                if (day == yearDay) {
                    hintDate = "昨天";
                }
            }
        } else {
            if (currentDay - day == 1) {
                hintDate = "昨天";
            }
            if (currentDay - day == 0) {
                hintDate = "今天";
            }
        }
        if (TextUtils.isEmpty(hintDate)) {
            format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            return format.format(date);
        } else {
            return hintDate;
        }

    }

    /**
     * 比较当前时间和date类型时间，判断小于或等于给定时间间隔
     *
     * @param date     给定日期
     * @param interval 时间间隔 （单位：分钟）
     * @return
     */
    public static Boolean ifDateValid(Date date, Integer interval) {
        if (null == date) {
            return false;
        }
        Boolean bool = true;
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(new Date());    //当前系统时间
        dateTwo.setTime(date);    //给定时间
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long minute = (timeOne - timeTwo) / (1000 * 60);//转化minute
        //判断日期间隔是否小于或等于指定时间间隔
        if (minute > interval) {
            //大于指定时间间隔
            bool = false;
        }
        return bool;
    }

    /**
     * @Description: 比较指定时间和date类型时间，判断小于给定时间间隔
     * @Param: Date beginDate 开始时间, Date endDate 结束时间, Integer interval 时间间隔
     * @Author: dongshijie
     * @Date: 2022/11/25 16:34
     * @Version: V1.0
     **/
    public static Boolean ifDateInterval(Date beginDate, Date endDate,Integer interval){
        if (null == beginDate) {
            return false;
        }
        if (null == endDate) {
            return false;
        }
        Boolean bool = true;
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(beginDate);    //开始时间
        dateTwo.setTime(endDate);    //结束时间
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long minute = (timeTwo - timeOne) / (1000 * 60);//转化minute
        //判断日期间隔是否小于或等于指定时间间隔
        if (minute >= interval) {
            //大于指定时间间隔
            bool = false;
        }
        return bool;
    }


    /**
     * @param date2
     * @return [date, date2]
     * @Author pq
     * @Description 比较当前时间和date类型时间
     * @Date 2020-06-19
     * @Param * @param date
     **/
    public static Boolean isTwoDate(String date2, Date date) {
        boolean flag = false;
        if (null == date || null == date2) {
            return flag;
        }
        // 比较 年 月 日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象：年月日 时分秒
        try {
            Date sdfDate = sdf.parse(date2);    //假设 设定日期转换为 date 类型 Debug：Sun Nov 11 11:11:11 CST 2018
            flag = sdfDate.getTime() <= date.getTime();
            System.err.println("设定时间<当前时间" + flag);  // flag = true
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return flag;
    }


    /***
     * 计算当前时间和给定时间的时间间隔（以分钟为单位；）
     * @param date
     * @return
     */
    public static Long countInterval(Date date) {

        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(new Date());    //当前系统时间
        dateTwo.setTime(date);    //给定时间
        Long timeOne = dateOne.getTimeInMillis();
        Long timeTwo = dateTwo.getTimeInMillis();
        Long minute = (timeOne - timeTwo) / (1000 * 60);//转化minute
        return minute;
    }

    /***
     * 计算两个给定时间的时间间隔（以分钟为单位；）
     * @return
     */
    public static Long countInterval(Date startDate,Date endDate) {

        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(startDate);
        dateTwo.setTime(endDate);
        Long timeOne = dateOne.getTimeInMillis();
        Long timeTwo = dateTwo.getTimeInMillis();
        Long minute = (timeTwo - timeOne) / (1000 * 60);//转化minute
        return minute;
    }

    /***
     * 计算当前时间和给定时间的时间间隔（以秒为单位；）
     * @param date
     * @return
     */
    public static Long countIntervalSeconds(Date date) {

        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(new Date());    //当前系统时间
        dateTwo.setTime(date);    //给定时间
        Long timeOne = dateOne.getTimeInMillis();
        Long timeTwo = dateTwo.getTimeInMillis();
        Long second = (timeOne - timeTwo) / (1000);//转化second
        return second;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime,
                                         Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算两个时间隔间的天数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long countIntervalDays(Date startTime, Date endTime) {

        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(endTime);    //截至时间
        dateTwo.setTime(startTime);  //开始时间
        Long timeOne = dateOne.getTimeInMillis();
        Long timeTwo = dateTwo.getTimeInMillis();
        Long days = (timeOne - timeTwo) / (1000 * 60 * 60 * 24);//转化minute
        return days;
    }
    /**
     * 计算两个时间隔间的月数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer getMonthDiff(Date startTime, Date endTime) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startTime);
        c2.setTime(endTime);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        // 获取年的差值
        int yearInterval = year1 - year2;
        // 获取月数差值
        int monthInterval = month1 - month2;
        int monthsDiff = yearInterval * 12 + monthInterval;
        return Math.abs(monthsDiff);
    }




    /**
     * 判断指定时间和当前系统时间的时间间隔（以分钟为单位）
     *
     * @param date
     * @return
     */
    public static Long ifOverdue(Date date) {

        Long interval = Long.valueOf(0);
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(date);    //当前系统时间
        dateTwo.setTime(new Date());    //给定时间
        Long timeOne = dateOne.getTimeInMillis();
        Long timeTwo = dateTwo.getTimeInMillis();
        interval = (timeOne - timeTwo) / Long.valueOf(1000 * 60);//转化minute
        return interval;
    }

    /**
     * 判断指定时间与当前系统时间的时间间隔是否超过指定时间间隔（以分钟为单位）
     *
     * @param startDateTime
     * @param inteval
     * @return
     */
    public static boolean isOutDateInteval(Date startDateTime, Integer inteval) {

        Calendar nowTime = Calendar.getInstance();
        Calendar startTime = Calendar.getInstance();
        nowTime.setTime(new Date());
        startTime.setTime(startDateTime);
        startTime.add(Calendar.MINUTE, inteval);
        if (nowTime.after(startTime)) {
            return true;
        }
        return false;
    }

    /***
     * 判断当前时间是否等于给定时间
     * @param date
     * @return
     */
    public static Boolean countSeconds(Date date) {
        Boolean bool = false;
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(new Date());    //当前系统时间
        dateTwo.setTime(date);    //给定时间
        Long timeOne = dateOne.getTimeInMillis();
        Long timeTwo = dateTwo.getTimeInMillis();
        Long second = (timeOne - timeTwo) / (1000 * 60);//转化second
        if (second.compareTo(Long.valueOf(0)) >= 0) {
            bool = true;
        }
        return bool;
    }


    /**
     * 判断指定时间是否已过期（以秒为单位）
     *
     * @param date
     * @return
     */
    public static Boolean ifHasOverdue(Date date) {
        Boolean bool = false;
        Calendar Date1 = Calendar.getInstance();
        Calendar Date2 = Calendar.getInstance();
        Date1.setTime(new Date()); //当前系统时间
        Date2.setTime(date); //给定时间
        Long time1 = Date1.getTimeInMillis();
        Long time2 = Date2.getTimeInMillis();
        Long interval = (time1 - time2) / Long.valueOf(1000);//转化second
        if (interval.compareTo(Long.valueOf(0)) >= 0) {
            bool = true;
        }
        return bool;
    }

    /**
     * 获取今天开始的日期
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay() {
        Date cur = new Date();
        Calendar calendar = new GregorianCalendar();

        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String startStr = simpleDateFormat.format(dayStart);

        return dayStart;
    }

    public static String getStartOfDayStr() {
        Date cur = new Date();
        Calendar calendar = new GregorianCalendar();

        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startStr = simpleDateFormat.format(dayStart);

        return startStr;
    }

    /**
     * 获取今天结束的日期
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay() {
        Date cur = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date dayEnd = calendar.getTime();
        return dayEnd;
    }

    public static String getEndOfDayStr() {
        Date cur = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date dayEnd = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endStr = simpleDateFormat.format(dayEnd);
        return endStr;
    }

    /**
     * 日期加n分钟
     *
     * @param createTime
     * @return
     */
    public static Date getDateAddTenMins(Date createTime, Integer n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        calendar.add(Calendar.MINUTE, n);

        return calendar.getTime();
    }

    /**
     * 根据指定日期 获得时间段内所有的天
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<String> getAllDates(String beginTime, String endTime) {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = null;
        Date dEnd = null;
        try {
            dBegin = formatter.parse(beginTime);
            dEnd = formatter.parse(endTime);

            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);

            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);

            calBegin.add(Calendar.DAY_OF_MONTH, 0);
            lDate.add(formatter.format(calBegin.getTime()));
            // 测试此日期是否在指定日期之后
            while (dEnd.after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                lDate.add(formatter.format(calBegin.getTime()));
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lDate;
    }

    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取指定日期 当天 开始时间
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart = calendar.getTime();
        return dayStart;
    }

    /**
     * 获取指定日期 当天 结束时间
     *
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date dayEnd = calendar.getTime();
        return dayEnd;
    }

    /**
     * 获取指定日期 当天 结束时间
     *
     * @return
     */
    public static Date getEndTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayEnd = calendar.getTime();
        return dayEnd;
    }

    /**
     * 根据出生日期返回年龄
     *
     * @param birthDay
     * @return
     * @throws ParseException
     */
    public static int getAgeByBirth(Date birthDay) {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;//当前日期在生日之前，年龄减一
                }
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * @MethodName: DateUtils
     * @Description: 获取指定时间离一天结束剩余秒数
     * @Return:
     * @Author: zhangdizhong
     * @Date: 2019/12/14 19:09
     **/
    public static int getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

    public static long toDay(final long ms1, final long ms2) {
        long m1 = (ms1 + TimeZone.getDefault().getOffset(ms1)) / MILLIS_IN_DAY;
        long m2 = (ms2 + TimeZone.getDefault().getOffset(ms2)) / MILLIS_IN_DAY;
        return m2 - m1;
    }

    /**
     * @MethodName: getHoursByTwoTime
     * @Description: 获取两个时间只差，得到小时差
     * @Return:
     * @Author: zhangdizhong
     * @Date: 2020/7/16 16:01
     **/
    public static long getHoursByTwoTime(Date one, Date two) {
        if (one == null || two == null) {
            return 0;
        }
        long hours = (two.getTime() - one.getTime()) / MILLIS_IN_HOURS;
        return hours;
    }

    /**
     * 日期加n天
     *
     * @param createTime
     * @return
     */
    public static Date getDateAfterDays(Date createTime, Integer n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    /**
     * 日期解析
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回Date
     */
    public static Date parse(Date date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String s = sdf.format(date);
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算两个时间隔间的天数(去除传入时间的时分秒)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long differenceIntervalDays(Date startTime, Date endTime, String pattern) {

        Date startDate = parse(startTime, pattern);
        Date endDate = parse(endTime, pattern);
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(endDate);    //截至时间
        dateTwo.setTime(startDate);  //开始时间
        Long timeOne = dateOne.getTimeInMillis();
        Long timeTwo = dateTwo.getTimeInMillis();
        Long days = (timeOne - timeTwo) / (1000 * 60 * 60 * 24);//转化minute
        return days;
    }

    /**
     * 判断是否是过去的日期
     *
     * @param param
     * @return
     * @return
     */
    public static boolean isPastDate(Date param) {

        boolean flag = false;
        Date nowDate = new Date();
        Date pastDate = null;
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String str = sdf.format(param);
        //在日期字符串非空时执行
        if (str != null && !"".equals(str)) {
            try {
                //将字符串转为日期格式，如果此处字符串为非合法日期就会抛出异常。
                pastDate = sdf.parse(str);
                //调用Date里面的before方法来做判断
                flag = pastDate.before(nowDate);
                if (flag) {
                    System.out.println("该日期早于今日");
                } else {
                    System.out.println("该日期晚于今日");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("日期参数不可为空");
        }
        return flag;
    }


    public static void main(String[] args) {
//        String str1 = "2020-08-13 16:13:49";
//        String str2 = "2020-08-13 17:13:01";
//        Date date1 = parse(str1, DATE_TIME_PATTERN);
//        Date date2 = parse(str2, DATE_TIME_PATTERN);
//        System.out.println(getHoursByTwoTime(date1,date2));
//        Date dateAfterDays = getDateAfterDays(new Date(), 5);
//        String format = format(dateAfterDays, DATE_TIME_PATTERN_ONE);
//        System.out.println(format);

        String str = "19920421";
        Date date = DateUtils.parse(str, DateUtils.DATE_PATTERN_COMMON);
        int ageByBirth = getAgeByBirth(date);
        System.out.println(ageByBirth);
//        getAgeByBirth()

    }

    /**
     * @Description  根据年月，查询对应月份最后一天的 天数
     * @Author whh
     * @Date  2022/7/8 15:36
     * @Param [yearMonth]
     * @Return java.lang.String
     * @Version V7.9.7
     **/
    public static Integer lastDayByMonth(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);  //年
        int month = Integer.parseInt(yearMonth.split("-")[1]); //月
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        // cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.MONTH, month); //设置当前月的上一个月
        // 获取某月最大天数
        //int lastDay = cal.getActualMaximum(Calendar.DATE);
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        // 设置日历中月份的最大天数
        //cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(cal.getTime());
        return Integer.parseInt(format.substring(format.length()-2, format.length()));
    }

    /**
     * @Description  根据年月，查询对应月份最后一天的 年月日
     * @Author whh
     * @Date  2022/7/8 15:36
     * @Param [yearMonth]
     * @Return java.lang.String
     * @Version V7.9.7
     **/
    public static String lastDayAndMonth(String yearMonth) {
        int year = Integer.parseInt(yearMonth.split("-")[0]);  //年
        int month = Integer.parseInt(yearMonth.split("-")[1]); //月
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        // cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.MONTH, month); //设置当前月的上一个月
        // 获取某月最大天数
        //int lastDay = cal.getActualMaximum(Calendar.DATE);
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        // 设置日历中月份的最大天数
        //cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }


    /**
     * 获取两个日期之间的所有月份 (年月)
     *
     * @param startTime
     * @param endTime
     * @param isZeroStart 月份是否去0
     * @return：YYYY-MM
     */
    public static List<String> getMonthBetweenDate(String startTime, String endTime,Boolean isZeroStart) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M");
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                if(isZeroStart){
                    list.add(format.format(startDate));
                }else{
                    list.add(sdf.format(startDate));
                }
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.MONTH, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Description 获取两个日期之间的所有月份 (年月)
     * @Author whh
     * @Date  2022/7/12 10:54
     * @Param [startDate, endDate,isZeroStart]
     * @Return java.util.List<java.lang.String>
     * @Version V7.9.7
     **/
    public static List<String> getMonthBetweenDate(Date startDate, Date endDate ,Boolean isZeroStart) {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M");
        // 转化成日期类型
        //用Calendar 进行日期比较判断
        Calendar calendar = Calendar.getInstance();
        while (startDate.getTime() <= endDate.getTime()) {
            // 把日期添加到集合
            if(isZeroStart){
                list.add(format.format(startDate));
            }else{
                list.add(sdf.format(startDate));
            }

            // 设置日期
            calendar.setTime(startDate);
            //把日期增加一天
            calendar.add(Calendar.MONTH, 1);
            // 获取增加后的日期
            startDate = calendar.getTime();
        }
        return list;
    }


    /**
     * 判断日期是否是当天
     * @param str
     * @param formatStr
     * @return
     * @throws Exception
     */
    public static boolean isToday(String str, String formatStr){
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month2 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month3 = c2.get(Calendar.MONTH)+1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if(year1 == year2 && month2 == month3 && day1 == day2){
            return true;
        }
        return false;
    }

    /**
     * 字符串日期拼接时间
     * @param str
     * @param formatStr 后面拼接字符传
     * @return
     *
     */
    public static String dateAppendTime(String str, String formatStr){
        String rel="";
        if(StringUtils.isEmpty(str)){
            return null;
        }
        rel=str+formatStr;
        return rel;
    }

    /**
     * @Description: 根据 年-月-日 日期，转成 当前时间对应天的开始时间
     * 2023-05-31  ==》  2023-05-31 00:00:00
     * @Param: [str]
     * @Author: whh
     * @Date: 2023/5/31 13:39
     * @Version V8.3.9.8
     **/
    public static String getDatePatternStartTime(String date){
        LocalDateTime searchStartTime = LocalDateTime.of(java.time.LocalDate.parse(date, java.time.format.DateTimeFormatter.ofPattern(DateUtils.DATE_PATTERN)), LocalTime.MIN);
        return searchStartTime.format(java.time.format.DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN));
    }
    /**
     * @Description: 根据 年-月-日 日期，转成 当前时间对应天的结束时间
     * 2023-05-31  ==》  2023-05-31 23:59:59
     * @Param: [str]
     * @Author: whh
     * @Date: 2023/5/31 13:39
     * @Version V8.3.9.8
     **/
    public static String getDatePatternEndTime(String date){
        LocalDateTime searchStartTime = LocalDateTime.of(java.time.LocalDate.parse(date, java.time.format.DateTimeFormatter.ofPattern(DateUtils.DATE_PATTERN)), LocalTime.MAX);
        return searchStartTime.format(java.time.format.DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN));
    }


    public static boolean isTodayOrNo(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        if (calendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == dateCalendar.get(Calendar.DAY_OF_MONTH)) {
           return true;
        } else {
            return false;
        }
    }

    /**
     * @Description: 获取当前时间 到 指定时间，间隔毫秒数
     * @Param: [days：天, hour：时, minute：分, second：秒, nano：毫秒]
     * @Author: whh
     * @Date: 2023/11/15 15:33
     * @Version V8.4.14.1
     **/
    public static Long getCurrentTimeToSpecifiedTimeByNano(Long days, Integer hour, Integer minute, Integer second, Integer nano) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(days).withHour(hour).withMinute(minute).withSecond(second).withNano(nano);
        return ChronoUnit.MILLIS.between(LocalDateTime.now(), localDateTime);
    }

}