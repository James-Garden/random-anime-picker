package uk.jamesgarden.randomanimepicker.listentry.enums;

import java.util.Arrays;

public enum ListEntryStatus {

  PLAN_TO_WATCH("Plan to Watch", "plan_to_watch"),
  WATCHING("Watching", "watching"),
  COMPLETED("Completed", "completed"),
  ON_HOLD("On Hold", "on_hold"),
  DROPPED("Dropped", "dropped"),
  OTHER("Other", "other");

  private final String displayName;
  private final String apiText;

  ListEntryStatus(String displayName, String apiText) {
    this.displayName = displayName;
    this.apiText = apiText;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static ListEntryStatus parse(String text) {
    return Arrays.stream(ListEntryStatus.values())
        .filter(value -> value.apiText.equals(text))
        .findFirst()
        .orElse(OTHER);
  }
}
