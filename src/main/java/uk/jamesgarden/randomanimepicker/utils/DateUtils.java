package uk.jamesgarden.randomanimepicker.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Objects;

public class DateUtils {
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
}
