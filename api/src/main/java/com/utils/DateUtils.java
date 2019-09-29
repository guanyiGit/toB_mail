package com.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理
 */
public class DateUtils {
//    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);

        }
        return null;
    }

    /**
     * string转date
     *
     * @param
     */

    public static Date stringToDate(String date, String parttern) {
        SimpleDateFormat ft = new SimpleDateFormat(DATE_TIME_PATTERN);
        ;
        ;
        if (parttern != null && parttern != "") {
            ft = new SimpleDateFormat(parttern);
        }
        Date dt = null;
        try {
            dt = ft.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static void main(String[] Args) {
    		Calendar c=Calendar.getInstance();
    		int y=c.get(Calendar.YEAR)+2;
    		List<Integer>list=new ArrayList<>();
    		for(int i=0;i<10;i++) {
    			list.add(--y);
    		}
    		System.out.println(list);
    }

    public static Date DateToPatterDate(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            String stringDate = df.format(date);
            SimpleDateFormat st = new SimpleDateFormat(pattern);
            Date currentDate = null;
            try {
                currentDate = st.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return currentDate;
        }
        return null;

    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static Long getTimeBefore(Date date) {
        Date now = new Date();
//        long l = now.getTime() - date.getTime();
        long l = date.getTime() - now.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
//            r += day + "天";
            r += day;
        } else if (hour > 0) {
//            r += hour + "小时";
        } else if (min > 0) {
//            r += min + "分";
        } else if (s > 0) {
//            r += s + "秒";
        }
//        r += "前";
        return day;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 给日期添加 若干个月
     */
    public static Date addMonth(Date newDate, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        calendar.add(Calendar.MONTH, month);
        Date time = calendar.getTime();
        return time;
    }
    
    /**
     * 获取下拉框年度列表()
     */
    public static List<Integer>getYearList(){
    	Calendar c=Calendar.getInstance();
		int y=c.get(Calendar.YEAR)+2;
		List<Integer>list=new ArrayList<>();
		for(int i=0;i<10;i++) {
			list.add(--y);
		}
    	return list;
    }
}
