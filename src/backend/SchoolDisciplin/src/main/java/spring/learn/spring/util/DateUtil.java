package spring.learn.spring.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    private static ThreadLocal formatDayMonthYearThread = new ThreadLocal() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    "dd/MM/yyyy");
        }
    };

    private static ThreadLocal formatMonthYearThread = new ThreadLocal() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    "MM/yyyy");
        }
    };

    private static ThreadLocal formatYearThread = new ThreadLocal() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    "yyyy");
        }
    };

    private static ThreadLocal formatMonthThread = new ThreadLocal() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(
                    "MM");
        }
    };

    // todo make it thread save
    public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date = new Date();
        return dateFormat.format(date);
    }

    private static SimpleDateFormat getSimpleDateFormatDayMonthYear() {
        return (SimpleDateFormat) formatDayMonthYearThread.get();
    }

    private static SimpleDateFormat getSimpleDateFormatMonthYear() {
        return (SimpleDateFormat) formatMonthYearThread.get();
    }

    private static SimpleDateFormat getSimpleDateFormatYear() {
        return (SimpleDateFormat) formatYearThread.get();
    }

    private static SimpleDateFormat getSimpleDateFormatMonth() {
        return (SimpleDateFormat) formatMonthThread.get();
    }

    public static final Date parse(String date) {
        try {
            return getSimpleDateFormatDayMonthYear().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String formatDayMonthYear(Date date) /* throws ParseException */ { // todo
        return getSimpleDateFormatDayMonthYear().format(date);
    }

    public static final String formatMonthYear(Date date) /* throws ParseException */ { // todo
        return getSimpleDateFormatMonthYear().format(date);
    }

    public static final String formatYear(Date date) /* throws ParseException */ { // todo
        return getSimpleDateFormatYear().format(date);
    }

    public static final String formatMonth(Date date) /* throws ParseException */ { // todo
        return getSimpleDateFormatMonth().format(date);
    }

    public static String getMonth(Date dateOperation) {
        return DateUtil.formatMonth(dateOperation);
    }

    public static String getYear(Date dateOperation) {
        return DateUtil.formatYear(dateOperation);
    }

    public static String getCurrentMonthYear() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        return month + "/" + year;
    }

    public static String getDate() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEEE dd/MM/yyyy 'à' HH:mm:ss", Locale.FRANCE);
        return dateFormat.format(dateNow);
    }

}