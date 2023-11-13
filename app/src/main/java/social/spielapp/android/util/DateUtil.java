package social.spielapp.android.util;

import java.text.DateFormat;
import java.util.Date;

public class DateUtil {
    public static String timestampToString(long timestamp) {
        Date date = new Date(timestamp);
        return DateFormat.getDateTimeInstance().format(date);
    }
}
