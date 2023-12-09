package social.spielapp.android.util

import java.text.DateFormat
import java.util.Date


class DateUtil {
    companion object {
        @JvmStatic
        fun timestampToString(timestamp: Long): String {
            val date = Date(timestamp)
            return DateFormat.getDateTimeInstance().format(date)
        }
    }
}
