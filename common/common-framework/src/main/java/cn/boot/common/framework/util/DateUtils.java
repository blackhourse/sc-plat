package cn.boot.common.framework.util;

import java.time.LocalDate;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
public class DateUtils {
    /**
     * 获取今天的日期，格式为 20190512
     * @return
     */
    public static String getToday() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        StringBuilder buf = new StringBuilder(8);
        return buf.append(year).append(month < 10 ? "0" : "").append(month).append(day < 10 ? "0" : "").append(day)
                .toString();
    }

}
