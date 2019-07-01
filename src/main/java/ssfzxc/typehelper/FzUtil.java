package ssfzxc.typehelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ssf on 2018-04-05.
 */
public final class FzUtil {

    private static final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal;

    static {
        dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                return simpleDateFormat;
            }
        };
    }

    public static boolean isNumeric(Object obj) {
        if(obj == null) {
            return false;
        } else {
            char[] chars = obj.toString().toCharArray();
            int length = chars.length;
            if(length < 1) {
                return false;
            } else {
                int i = 0;
                if(length > 1 && chars[0] == 45) {
                    i = 1;
                }

                while(i < length) {
                    if(!Character.isDigit(chars[i])) {
                        return false;
                    }

                    ++i;
                }

                return true;
            }
        }
    }

    public static Date parseDateTime(String str) {
        return parseDateTime("yyyy-MM-dd HH:mm:ss", str);
    }

    public static Date parseDateTime(String pattern, String str) {
        SimpleDateFormat format = dateFormatThreadLocal.get();
        format.applyPattern(pattern);

        try {
            return format.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
