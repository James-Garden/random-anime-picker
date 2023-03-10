package uk.jamesgarden.randomanimepicker.utils;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.ocpsoft.prettytime.PrettyTime;

public class DateTimeFormatUtil {

  private DateTimeFormatUtil() {
  }

  private static final PrettyTime TIME_SINCE_FORMATTER = new PrettyTime();
  private static final String DATE_PATTERN = "d MMMM yyyy";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

  public static String formatZonedDateTime(ZonedDateTime zonedDateTime) {
    return DATE_FORMATTER.format(zonedDateTime);
  }

  public static String formatTimeSince(Instant instant) {
    return TIME_SINCE_FORMATTER.format(instant);
  }
}
