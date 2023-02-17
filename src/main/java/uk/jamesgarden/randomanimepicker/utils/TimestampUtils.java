package uk.jamesgarden.randomanimepicker.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Objects;
import org.ocpsoft.prettytime.PrettyTime;

public class TimestampUtils {

  private static final PrettyTime TIME_FORMATTER = new PrettyTime();

  public static String instantToDate(Instant instant) {
    if (Objects.isNull(instant)) {
      return null;
    }
    var date = Date.from(instant);
    var formatter = new SimpleDateFormat("d MMM yyyy");
    return formatter.format(date);
  }

  public static Instant instantFromEpochSecond(Integer epochSecond) {
    if (Objects.isNull(epochSecond)) {
      return null;
    }
    return Instant.ofEpochSecond(epochSecond);
  }

  public static String formatTimeSince(Instant instant) {
    return TIME_FORMATTER.format(instant);
  }
}
