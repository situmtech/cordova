package es.situm.plugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final DateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
